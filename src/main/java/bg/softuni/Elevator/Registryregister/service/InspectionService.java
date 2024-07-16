package bg.softuni.Elevator.Registryregister.service;

import bg.softuni.Elevator.Registryregister.model.dto.InspectionDTOs.InspectionListDTO;

import java.util.List;

public interface InspectionService {
    List<InspectionListDTO> getAllInspections();
}
