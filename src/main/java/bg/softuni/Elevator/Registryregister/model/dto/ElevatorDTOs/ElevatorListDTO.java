package bg.softuni.Elevator.Registryregister.model.dto.ElevatorDTOs;

import bg.softuni.Elevator.Registryregister.model.entity.ElevatorType;

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
