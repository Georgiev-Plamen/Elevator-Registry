package bg.softuni.ElevatorRegister.service.impl;

import bg.softuni.ElevatorRegister.model.dto.GasInstallationDTOs.AddGasInstallationDTO;
import bg.softuni.ElevatorRegister.model.dto.GasInstallationDTOs.GasInstallationDTO;
import bg.softuni.ElevatorRegister.service.GasInstallationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class GasInstallationServiceImpl implements GasInstallationService {

    private final RestClient gasInstallationRestClient;

    public GasInstallationServiceImpl(
            @Qualifier("gasInstallationRestClient") RestClient gasInstallationRestClient) {
        this.gasInstallationRestClient = gasInstallationRestClient;
    }

    @Override
    public void addGasInstallation(AddGasInstallationDTO addGasInstallationDTO) {
        gasInstallationRestClient
                .post()
                .uri("http://localhost:8081/gas")
                .body(addGasInstallationDTO)
                .retrieve();
    }

    @Override
    public List<GasInstallationDTO> getAllGasInstallation() {
        return gasInstallationRestClient
                .get()
                .uri("http://localhost:8081/gas")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<GasInstallationDTO>>() {});

    }

    @Override
    public ResponseEntity<Void> deleteGasInstallationById(Long id) {
        return gasInstallationRestClient
                .delete()
                .uri("http://localhost:8081/gas/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public GasInstallationDTO getGasInstallationDetails(Long id) {
        return gasInstallationRestClient
                .get()
                .uri("http://localhost:8081/gas/{id}", id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public RestClient.ResponseSpec editGasInstallation(Long id, GasInstallationDTO gasInstallationDTO) {
        return gasInstallationRestClient
                .put()
                .uri("http://localhost:8081/gas/{id}", id)
                .body(gasInstallationDTO)
                .retrieve();


    }
}
