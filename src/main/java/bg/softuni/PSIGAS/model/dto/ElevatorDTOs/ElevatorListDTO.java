package bg.softuni.PSIGAS.model.dto.ElevatorDTOs;

import bg.softuni.PSIGAS.model.entity.ElevatorType;

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
