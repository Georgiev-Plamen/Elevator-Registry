package bg.softuni.Elevator.Registryregister.service;

import bg.softuni.Elevator.Registryregister.model.dto.InspectionDTOs.AddInspectionDTO;
import bg.softuni.Elevator.Registryregister.model.dto.InspectionDTOs.InspectionListDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface InspectionService {
    List<InspectionListDTO> getAllInspections();

    void addNewInspection(AddInspectionDTO addInspectionDTO,UserDetails userDetails);
}
