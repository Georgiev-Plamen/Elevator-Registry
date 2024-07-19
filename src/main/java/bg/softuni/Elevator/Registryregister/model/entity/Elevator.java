package bg.softuni.Elevator.Registryregister.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

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
    private String city;
    private String address;
    private LocalDate registerDate;
    @ManyToOne
    private Customer owner;
    @ManyToOne(targetEntity = Inspection.class)
    private Inspection inspection;

    @ManyToOne(fetch = FetchType.EAGER)
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

    public User getAuthor() {
        return author;
    }

    public Elevator setAuthor(User author) {
        this.author = author;
        return this;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getRegisterDate()  {return registerDate;}

    public Elevator setRegisterDate(LocalDate firstCheck) {
        this.registerDate = firstCheck;
        return this;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public Inspection getInspection() {
        return inspection;
    }

    public void setInspection(Inspection inspection) {
        this.inspection = inspection;
    }
}
