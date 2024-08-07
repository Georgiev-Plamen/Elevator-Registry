package bg.softuni.ElevatorRegister.model.dto.GasInstallationDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AddGasInstallationDTO (
        @NotBlank
        @Size(min=2, max=30)
        String damtnNumber,
        LocalDate damtnDate,
        LocalDate registrationData,
        @NotBlank
        @Size(min=3, max=30)
        String type,

        @NotBlank
        @Size(min=2, max=30)
        String manufacturer,

        @NotBlank
        @Size(min=2, max=30)
        String model,

        @NotBlank
        @Size(min=1, max=30)
        String pressure,

        @NotBlank
        @Size(min=1, max=30)
        String power,
        Long customerId
) {
//   public AddGasInstallationDTO() {
//           this(null, null, null, null, null, null, null, null, null);
//   }

}
