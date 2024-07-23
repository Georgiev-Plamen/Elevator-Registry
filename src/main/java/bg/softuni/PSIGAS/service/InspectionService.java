package bg.softuni.PSIGAS.service;

import bg.softuni.PSIGAS.model.dto.InspectionDTOs.AddInspectionDTO;
import bg.softuni.PSIGAS.model.dto.InspectionDTOs.InspectionDetailDTO;
import bg.softuni.PSIGAS.model.dto.InspectionDTOs.InspectionListDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface InspectionService {
    List<InspectionListDTO> getAllInspections();

    void addNewInspection(AddInspectionDTO addInspectionDTO, UserDetails userDetails);

    void markAsDone(Long id);

    InspectionDetailDTO getInspectionDetails(Long id);

    void editInspection(Long id, AddInspectionDTO addInspectionDTO);

    void deleteInspection(Long id);
}
