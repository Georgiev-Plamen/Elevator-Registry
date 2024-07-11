package bg.softuni.Elevator.Registryregister.model.dto;

import bg.softuni.Elevator.Registryregister.model.entity.ElevatorType;
import bg.softuni.Elevator.Registryregister.model.entity.User;

import java.time.LocalDate;

public class ElevatorDetailsDTO {

    private ElevatorType type;
    private String manufacturer;
    private String manufacturerNumber;
    private int yearOfManufacture;
    private int speed;
    private int numberOfStops;
    private LocalDate damtnDate;
    private int damtnNumber;
    private User author;
}
