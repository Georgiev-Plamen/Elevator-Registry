package bg.softuni.Elevator.Registryregister.service.impl;

import bg.softuni.Elevator.Registryregister.model.dto.InspectionDTOs.InspectionListDTO;
import bg.softuni.Elevator.Registryregister.model.entity.Inspection;
import bg.softuni.Elevator.Registryregister.repository.InspectionRepository;
import bg.softuni.Elevator.Registryregister.service.InspectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspectionServiceImp implements InspectionService {

    private final InspectionRepository inspectionRepository;

    public InspectionServiceImp(InspectionRepository inspectionRepository) {
        this.inspectionRepository = inspectionRepository;
    }

    @Override
    public List<InspectionListDTO> getAllInspections() {
        return inspectionRepository
                .findAll()
                .stream()
                .map(InspectionServiceImp::toAllInspection)
                .toList();
    }

    private static InspectionListDTO toAllInspection (Inspection inspection) {
        return new InspectionListDTO(
                inspection.getId(),
                inspection.getUser().getUsername(),
                //TODO: need to fix !!!
                inspection.getElevators()
        )
    }
}
