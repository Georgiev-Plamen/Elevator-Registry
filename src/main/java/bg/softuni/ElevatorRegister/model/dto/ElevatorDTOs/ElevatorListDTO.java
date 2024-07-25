package bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs;

import bg.softuni.ElevatorRegister.model.entity.ElevatorType;

import java.time.LocalDate;

public record ElevatorListDTO (
        Long id,
        ElevatorType type,
        String manufacturer,
        String address,
        int speed,
        int numberOfStops,
        LocalDate firstCheck,
        String author
){

}
