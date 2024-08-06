package bg.softuni.ElevatorRegister.model.dto.GasInstallationDTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AddGasInstallationDTO (

        @NotEmpty
        @Size(min=2, max=30)
        String damtnNumber,
        LocalDate damtnDate,
        LocalDate registrationData,
        @NotEmpty
        @Size(min=3, max=30)
        String type,

        @NotEmpty
        @Size(min=2, max=30)
        String manufacturer,

        @NotEmpty
        @Size(min=2, max=30)
        String model,

        @NotEmpty
        @Size(min=1, max=30)
        String pressure,

        @NotEmpty
        @Size(min=1, max=30)
        String power,
        Long customerId
) {
}
