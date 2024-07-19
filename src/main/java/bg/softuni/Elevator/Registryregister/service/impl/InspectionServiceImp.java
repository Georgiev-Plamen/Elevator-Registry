package bg.softuni.Elevator.Registryregister.service.impl;

import bg.softuni.Elevator.Registryregister.model.dto.InspectionDTOs.AddInspectionDTO;
import bg.softuni.Elevator.Registryregister.model.dto.InspectionDTOs.InspectionDetailDTO;
import bg.softuni.Elevator.Registryregister.model.dto.InspectionDTOs.InspectionListDTO;
import bg.softuni.Elevator.Registryregister.model.entity.Inspection;
import bg.softuni.Elevator.Registryregister.model.entity.InspectionsStatus;
import bg.softuni.Elevator.Registryregister.repository.CustomerRepository;
import bg.softuni.Elevator.Registryregister.repository.InspectionRepository;
import bg.softuni.Elevator.Registryregister.repository.UserRepository;
import bg.softuni.Elevator.Registryregister.service.InspectionService;
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

    public InspectionServiceImp(InspectionRepository inspectionRepository, ModelMapper modelMapper, UserRepository userRepository, CustomerRepository customerRepository) {
        this.inspectionRepository = inspectionRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
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
        inspection.setStatus(InspectionsStatus.ФИНАЛИЗИРАНА);
        inspectionRepository.save(inspection);
    }

    @Override
    public InspectionDetailDTO getInspectionDetails(Long id) {
        return modelMapper.map(inspectionRepository.findById(id).get(), InspectionDetailDTO.class);
    }

    private Inspection map(AddInspectionDTO addInspectionDTO, UserDetails userDetails) {
        Inspection mappedInspection = modelMapper.map(addInspectionDTO, Inspection.class);
        mappedInspection.setCustomer(customerRepository.getReferenceById(addInspectionDTO.getCustomer()));
        mappedInspection.setUser(userRepository.findByUsername(userDetails.getUsername()).get());
        return mappedInspection;
    }

    private static InspectionListDTO toAllInspection (Inspection inspection) {
        return new InspectionListDTO(
                inspection.getId(),
                inspection.getUser().getUsername(),
                inspection.getCustomer(),
                inspection.getElevators(),
                inspection.getAddress(),
                inspection.getPrice(),
                inspection.getStatus().toString()
        );
    }
}
