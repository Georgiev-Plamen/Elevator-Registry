package bg.softuni.Elevator.Registryregister.model.dto;

import bg.softuni.Elevator.Registryregister.model.entity.ElevatorType;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;

public class AddElevatorDTO {
    private ElevatorType type;
    private String manufacturer;
    private String manufacturerNumber;
    private int yearOfManufacture;
    private int speed;
    private int numberOfStops;
    private LocalDate damtnDate;
    private int damtnNumber;
    private User author;

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

    public int getDamtnNumber() {
        return damtnNumber;
    }

    public void setDamtnNumber(int damtnNumber) {
        this.damtnNumber = damtnNumber;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
