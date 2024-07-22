package bg.softuni.Elevator.Registryregister.service;

import bg.softuni.Elevator.Registryregister.model.dto.InspectionDTOs.AddInspectionDTO;
import bg.softuni.Elevator.Registryregister.model.dto.InspectionDTOs.InspectionDetailDTO;
import bg.softuni.Elevator.Registryregister.model.dto.InspectionDTOs.InspectionListDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface InspectionService {
    List<InspectionListDTO> getAllInspections();

    void addNewInspection(AddInspectionDTO addInspectionDTO,UserDetails userDetails);

    void markAsDone(Long id);

    InspectionDetailDTO getInspectionDetails(Long id);

    void editInspection(Long id, AddInspectionDTO addInspectionDTO);
}
