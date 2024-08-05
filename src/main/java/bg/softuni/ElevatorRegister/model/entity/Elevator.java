package bg.softuni.ElevatorRegister.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name="elevators")
public class Elevator extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ElevatorType type;

    @NotEmpty
    private String manufacturer;
    @NotEmpty
    private String manufacturerNumber;

    @NotEmpty
    private String yearOfManufacture;

    @NotNull
    private int speed;
    @NotNull
    private int numberOfStops;
    private String city;
    @NotEmpty
    private String address;
    @NotNull
    private LocalDate registerDate;
    private LocalDate lastInspection;
    private LocalDate nextInspection;
    @ManyToOne
    private Customer owner;

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

    public Elevator setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
        return this;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public LocalDate getLastInspection() {
        return lastInspection;
    }

    public void setLastInspection(LocalDate lastInspection) {
        this.lastInspection = lastInspection;
    }

    public LocalDate getNextInspection() {
        return nextInspection;
    }

    public void setNextInspection(LocalDate nextInspection) {
        this.nextInspection = nextInspection;
    }
}
