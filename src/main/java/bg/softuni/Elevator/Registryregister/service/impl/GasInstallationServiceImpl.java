package bg.softuni.Elevator.Registryregister.service.impl;

import bg.softuni.Elevator.Registryregister.model.dto.GasInstallationDTOs.AddGasInstallationDTO;
import bg.softuni.Elevator.Registryregister.service.GasInstallationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

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
}
