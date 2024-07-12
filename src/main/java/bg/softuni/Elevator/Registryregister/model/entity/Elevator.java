package bg.softuni.Elevator.Registryregister.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="elevators")
public class Elevator extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ElevatorType type;
    private String manufacturer;
    private String manufacturerNumber;
    private String yearOfManufacture;
    private int speed;
    private int numberOfStops;
    private LocalDate damtnDate;
    private int damtnNumber;

    @ManyToOne
    private User author;

    public Elevator() {
    }

    public ElevatorType getType() {
        return type;
    }

    public Elevator setType(ElevatorType type) {
        this.type = type;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Elevator setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getManufacturerNumber() {
        return manufacturerNumber;
    }

    public Elevator setManufacturerNumber(String manufacturerNumber) {
        this.manufacturerNumber = manufacturerNumber;
        return this;
    }

    public String getYearOfManufacture() {
        return yearOfManufacture;

    }

    public Elevator setYearOfManufacture(String yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
        return this;
    }

    public int getSpeed() {
        return speed;
    }

    public Elevator setSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    public int getNumberOfStops() {
        return numberOfStops;
    }

    public Elevator setNumberOfStops(int numberOfSops) {
        this.numberOfStops = numberOfSops;
        return this;
    }

    public LocalDate getDamtnDate() {
        return damtnDate;
    }

    public Elevator setDamtnDate(LocalDate damtnDate) {
        this.damtnDate = damtnDate;
        return this;
    }

    public int getDamtnNumber() {
        return damtnNumber;
    }

    public Elevator setDamtnNumber(int damtnNumber) {
        this.damtnNumber = damtnNumber;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Elevator setAuthor(User author) {
        this.author = author;
        return this;
    }
}
