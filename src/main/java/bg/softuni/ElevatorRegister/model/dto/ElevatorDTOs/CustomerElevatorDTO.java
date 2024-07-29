package bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs;

import bg.softuni.ElevatorRegister.model.entity.ElevatorType;

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
        LocalDate registerDate,
        LocalDate lastInspection,
        LocalDate nextInspection
) {
}
