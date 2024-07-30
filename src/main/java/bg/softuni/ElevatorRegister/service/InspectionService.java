package bg.softuni.ElevatorRegister.service;

import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.ElevatorDetailsDTO;
import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.ElevatorListDTO;
import bg.softuni.ElevatorRegister.model.dto.InspectionDTOs.AddInspectionDTO;
import bg.softuni.ElevatorRegister.model.dto.InspectionDTOs.InspectionDetailDTO;
import bg.softuni.ElevatorRegister.model.dto.InspectionDTOs.InspectionListDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface InspectionService {
    List<InspectionListDTO> getAllInspections();

    void addNewInspection(AddInspectionDTO addInspectionDTO, UserDetails userDetails);

    void markAsDone(Long id);

    InspectionDetailDTO getInspectionDetails(Long id);

    void editInspection(Long id, AddInspectionDTO addInspectionDTO);

    void deleteInspection(Long id);

    List<ElevatorDetailsDTO> getAllElevatorsOfInspection(Long id);

    void createMultiplyInspection(List<Long> values, UserDetails userDetails, Long customerId);
}
