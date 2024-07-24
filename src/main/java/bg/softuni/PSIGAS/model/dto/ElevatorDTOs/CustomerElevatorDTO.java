package bg.softuni.PSIGAS.model.dto.ElevatorDTOs;

import bg.softuni.PSIGAS.model.entity.Customer;
import bg.softuni.PSIGAS.model.entity.ElevatorType;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public record CustomerElevatorDTO(

        Long id,
        ElevatorType type,
        String manufacturer,
        String manufacturerNumber,
        String yearOfManufacture,
        int speed,
        int numberOfStops,
        String city,
        String address,
        LocalDate registerDate
) {
}
