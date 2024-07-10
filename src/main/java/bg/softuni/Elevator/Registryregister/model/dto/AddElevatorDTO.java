package bg.softuni.Elevator.Registryregister.model.dto;

import bg.softuni.Elevator.Registryregister.model.entity.ElevatorType;

import java.time.LocalDate;

public class AddElevatorDTO {
    private ElevatorType type;
    private String manufacturer;
    private String manufacturerNumber;
    private int yearOfManufacture;
    private int speed;
    private int numberOfSops;
    private LocalDate damtnDate;
    private int damtnNumber;

    public AddElevatorDTO() {
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

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getNumberOfSops() {
        return numberOfSops;
    }

    public void setNumberOfSops(int numberOfSops) {
        this.numberOfSops = numberOfSops;
    }

    public LocalDate getDamtnDate() {
        return damtnDate;
    }

    public void setDamtnDate(LocalDate damtnDate) {
        this.damtnDate = damtnDate;
    }

    public int getDamtnNumber() {
        return damtnNumber;
    }

    public void setDamtnNumber(int damtnNumber) {
        this.damtnNumber = damtnNumber;
    }
}
