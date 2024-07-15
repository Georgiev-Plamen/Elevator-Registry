package bg.softuni.Elevator.Registryregister.model.dto;

import bg.softuni.Elevator.Registryregister.model.entity.ElevatorType;
import bg.softuni.Elevator.Registryregister.model.entity.User;

import java.time.LocalDate;

public class ElevatorDetailsDTO {

    private Long id;
    private ElevatorType type;
    private String manufacturer;
    private String manufacturerNumber;
    private String yearOfManufacture;
    private int speed;
    private int numberOfStops;
    private LocalDate damtnDate;
    private String damtnNumber;
//    private User author;
    private LocalDate firstCheck;

    public ElevatorDetailsDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ElevatorType getType() {
        return type;
    }

    public void setType(ElevatorType type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturerNumber() {
        return manufacturerNumber;
    }

    public void setManufacturerNumber(String manufacturerNumber) {
        this.manufacturerNumber = manufacturerNumber;
    }

    public String getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(String yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getNumberOfStops() {
        return numberOfStops;
    }

    public void setNumberOfStops(int numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    public LocalDate getDamtnDate() {
        return damtnDate;
    }

    public void setDamtnDate(LocalDate damtnDate) {
        this.damtnDate = damtnDate;
    }

    public String getDamtnNumber() {
        return damtnNumber;
    }

    public void setDamtnNumber(String damtnNumber) {
        this.damtnNumber = damtnNumber;
    }

//    public User getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(User author) {
//        this.author = author;
//    }

    public LocalDate getFirstCheck() {
        return firstCheck;
    }

    public void setFirstCheck(LocalDate firstCheck) {
        this.firstCheck = firstCheck;
    }
}
