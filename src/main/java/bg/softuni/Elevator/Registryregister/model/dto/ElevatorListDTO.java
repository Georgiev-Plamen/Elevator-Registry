package bg.softuni.Elevator.Registryregister.model.dto;

import bg.softuni.Elevator.Registryregister.model.entity.ElevatorType;
import bg.softuni.Elevator.Registryregister.model.entity.User;

import java.time.LocalDate;

public record ElevatorListDTO (
        Long id,
        ElevatorType type,
        String manufacturer,
        int speed,
        int numberOfStops,
        LocalDate lastCheck){

}
