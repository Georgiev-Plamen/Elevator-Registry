package bg.softuni.ElevatorRegister.model.dto.GasInstallationDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record GasInstallationDTO(
        Long id,
        @NotBlank @Size(min=1, max=10) String damtnNumber,
        LocalDate damtnDate,
        LocalDate registrationData,
        @NotBlank @Size(min=1, max=20) String type,
        @NotBlank @Size(min=1, max=20) String manufacturer,
        @NotBlank @Size(min=1, max=20)String model,
        @NotBlank @Size(min=1, max=5)String pressure,
        @NotBlank @Size(min=1, max=5)String power,
        Long ownerId
) {
}
