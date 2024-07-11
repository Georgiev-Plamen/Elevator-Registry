package bg.softuni.Elevator.Registryregister.model.dto;

import bg.softuni.Elevator.Registryregister.model.entity.ElevatorType;

public record ElevatorListDTO (
    Long id,
    ElevatorType type,
    String manufacturer,
    int speed,
    int numberOfStops){

}
