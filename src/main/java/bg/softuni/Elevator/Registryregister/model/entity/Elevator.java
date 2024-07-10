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
    private int yearOfManufacture;
    private int speed;
    private int numberOfSops;
    private LocalDate damtnDate;
    private int damtnNumber;

    @ManyToOne
    private User author;

    public Elevator() {
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
