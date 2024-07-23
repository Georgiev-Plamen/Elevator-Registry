package bg.softuni.PSIGAS.model.dto.GasInstallationDTOs;

import java.time.LocalDate;

public record GasInstallationDTO(

        Long id,
        String damtnNumber,
        LocalDate damtnDate,
        LocalDate registrationData,
        String type,
        String manufacturer,
        String model,
        String pressure,
        String power,
        Long ownerId
) {
}
