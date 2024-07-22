package bg.softuni.Elevator.Registryregister.model.dto.GasInstallationDTOs;

import java.time.LocalDate;

public record AddGasInstallationDTO (

        String damtnNumber,
        LocalDate damtnDate,
        LocalDate registrationData,
        String type,
        String manufacturer,
        String model,
        String pressure,
        String power,
        Long customerId
) {
}
