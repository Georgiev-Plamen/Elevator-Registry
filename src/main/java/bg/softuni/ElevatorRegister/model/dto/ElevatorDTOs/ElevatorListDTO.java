package bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs;

import bg.softuni.ElevatorRegister.model.entity.Customer;
import bg.softuni.ElevatorRegister.model.entity.ElevatorType;

import java.time.LocalDate;

public record ElevatorListDTO (
        Long id,
        Customer owner,
        ElevatorType type,
        String manufacturer,
        String address,
        int speed,
        int numberOfStops,
        LocalDate registerDate,
        LocalDate lastInspection,
        LocalDate nextInspection,
        String author
){

}
