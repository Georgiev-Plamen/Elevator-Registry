package bg.softuni.ElevatorRegister.service.impl;

import bg.softuni.ElevatorRegister.model.dto.InspectionDTOs.AddInspectionDTO;
import bg.softuni.ElevatorRegister.model.dto.InspectionDTOs.InspectionDetailDTO;
import bg.softuni.ElevatorRegister.model.dto.InspectionDTOs.InspectionListDTO;
import bg.softuni.ElevatorRegister.model.entity.Inspection;
import bg.softuni.ElevatorRegister.model.entity.InspectionsStatus;
import bg.softuni.ElevatorRegister.model.user.AppUserDetails;
import bg.softuni.ElevatorRegister.repository.CustomerRepository;
import bg.softuni.ElevatorRegister.repository.ElevatorRepository;
import bg.softuni.ElevatorRegister.repository.InspectionRepository;
import bg.softuni.ElevatorRegister.repository.UserRepository;
import bg.softuni.ElevatorRegister.service.InspectionService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void addToInspection(Long id) {
//        Inspection inspection = new Inspection();
//        Elevator elevator = elevatorRepository.getReferenceById(id);
//        inspection.setElevators(List.of(elevator));
    }

    @Override
    public void createInspection(List<Long> values) {
        Inspection inspection = new Inspection();
//        inspection.setUser(userRepository.findByUsername(appUserDetails.getUsername()).get());
        inspection.setElevators(elevatorRepository.findAllById(values));


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
