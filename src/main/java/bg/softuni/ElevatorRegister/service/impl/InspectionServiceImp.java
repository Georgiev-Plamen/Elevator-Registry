package bg.softuni.ElevatorRegister.service.impl;

import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.ElevatorDetailsDTO;
import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.ElevatorListDTO;
import bg.softuni.ElevatorRegister.model.dto.InspectionDTOs.AddInspectionDTO;
import bg.softuni.ElevatorRegister.model.dto.InspectionDTOs.InspectionDetailDTO;
import bg.softuni.ElevatorRegister.model.dto.InspectionDTOs.InspectionListDTO;
import bg.softuni.ElevatorRegister.model.entity.Elevator;
import bg.softuni.ElevatorRegister.model.entity.Inspection;
import bg.softuni.ElevatorRegister.model.entity.InspectionsStatus;
import bg.softuni.ElevatorRegister.model.user.AppUserDetails;
import bg.softuni.ElevatorRegister.repository.CustomerRepository;
import bg.softuni.ElevatorRegister.repository.ElevatorRepository;
import bg.softuni.ElevatorRegister.repository.InspectionRepository;
import bg.softuni.ElevatorRegister.repository.UserRepository;
import bg.softuni.ElevatorRegister.service.InspectionService;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class InspectionServiceImp implements InspectionService {

    private final InspectionRepository inspectionRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ElevatorRepository elevatorRepository;

    public InspectionServiceImp(InspectionRepository inspectionRepository, ModelMapper modelMapper, UserRepository userRepository, CustomerRepository customerRepository, ElevatorRepository elevatorRepository) {
        this.inspectionRepository = inspectionRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.elevatorRepository = elevatorRepository;
    }

    @Override
    public List<InspectionListDTO> getAllInspections() {
        return inspectionRepository
                .findAll()
                .stream()
                .map(InspectionServiceImp::toAllInspection)
                .toList();
    }

    @Override
    public void addNewInspection(AddInspectionDTO addInspectionDTO, UserDetails userDetails) {
        Inspection inspection = map(addInspectionDTO, userDetails);
        inspection.setStatus(InspectionsStatus.ЧАКА);

        inspectionRepository.save(inspection);
    }

    @Override
    public void markAsDone(Long id) {
        Inspection inspection = inspectionRepository.findById(id).get();
        inspection.getElevators().forEach(elevator -> elevator.setLastInspection(elevator.getNextInspection()));
        inspection.getElevators().forEach(elevator -> elevator.setNextInspection(elevator.getNextInspection().plusYears(1)));
        inspection.setStatus(InspectionsStatus.ФИНАЛИЗИРАНА);
//        inspection.setInspectionDate(LocalDate.now());
        inspectionRepository.save(inspection);
    }

    @Override
    public InspectionDetailDTO getInspectionDetails(Long id) {
        return modelMapper.map(inspectionRepository.findById(id).get(), InspectionDetailDTO.class);
    }

    @Override
    public void editInspection(Long id, AddInspectionDTO addInspectionDTO) {
        Inspection inspection = inspectionRepository.findById(id).get();
        inspection = modelMapper.map(addInspectionDTO, Inspection.class);
        inspectionRepository.save(inspection);
    }

    @Override
    public void deleteInspection(Long id) {
        inspectionRepository.deleteById(id);
    }

    @Override
    public List<ElevatorDetailsDTO> getAllElevatorsOfInspection(Long id) {
        return inspectionRepository.getReferenceById(id)
                .getElevators()
                .stream()
                .map(elevator -> modelMapper.map(elevator, ElevatorDetailsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createMultiplyInspection(List<Long> values, UserDetails userDetails, Long customerId) {
        Inspection inspection = new Inspection();
        inspection.setUser(userRepository.findByUsername(userDetails.getUsername()).get());
        inspection.setCustomer(customerRepository.getReferenceById(customerId));
        inspection.setElevators(elevatorRepository.findAllById(values));
        inspection.setAddress("Multi addresses");
        inspection.setStatus(InspectionsStatus.ЧАКА);
        inspectionRepository.save(inspection);
    }

    @Override
    @Scheduled(cron = "0 0 2 * * *")
    //Starts once of day
    public void deleteInspectionAfter5years() {
        LocalDate dateThreshold = LocalDate.now().minusYears(5);

        List<Inspection> oldInspections = inspectionRepository.findAll().stream()
                .filter(inspection -> inspection.getInspectionDate().isBefore(dateThreshold))
                .collect(Collectors.toList());

        for (Inspection inspection : oldInspections) {
            inspectionRepository.delete(inspection);
            System.out.println("Delete inspection with date: " + inspection.getInspectionDate());
        }
    }

    private Inspection map(AddInspectionDTO addInspectionDTO, UserDetails userDetails) {
        Inspection mappedInspection = modelMapper.map(addInspectionDTO, Inspection.class);
        mappedInspection.setCustomer(customerRepository.getReferenceById(addInspectionDTO.getCustomer().getId()));
        mappedInspection.setUser(userRepository.findByUsername(userDetails.getUsername()).get());
        return mappedInspection;
    }

    private static InspectionListDTO toAllInspection (Inspection inspection) {
        return new InspectionListDTO(
                inspection.getId(),
                inspection.getUser(),
                inspection.getCustomer(),
                inspection.getElevators(),
                inspection.getAddress(),
                inspection.getPrice(),
                inspection.getStatus().toString()
        );
    }
}
