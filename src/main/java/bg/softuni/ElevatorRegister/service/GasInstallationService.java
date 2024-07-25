package bg.softuni.ElevatorRegister.service;

import bg.softuni.ElevatorRegister.model.dto.GasInstallationDTOs.AddGasInstallationDTO;
import bg.softuni.ElevatorRegister.model.dto.GasInstallationDTOs.GasInstallationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.List;

public interface GasInstallationService {
    void addGasInstallation(AddGasInstallationDTO addGasInstallationDTO);

    List<GasInstallationDTO> getAllGasInstallation();

    ResponseEntity<Void> deleteGasInstallationById(Long id);

    GasInstallationDTO getGasInstallationDetails(Long id);

    RestClient.ResponseSpec editGasInstallation (Long id, GasInstallationDTO gasInstallationDTO);
}