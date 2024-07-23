package bg.softuni.PSIGAS.model.dto.ElevatorDTOs;

import bg.softuni.PSIGAS.model.entity.Customer;
import bg.softuni.PSIGAS.model.entity.ElevatorType;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;

public class AddElevatorDTO {
    private ElevatorType type;
    private Customer owner;
    private String manufacturer;
    private String manufacturerNumber;
    private String yearOfManufacture;
    private int speed;
    private int numberOfStops;
    private String city;
    private String address;
    private LocalDate registerDate;
    private User author;

    public AddElevatorDTO() {
    }

    public ElevatorType getType() {
        return type;
    }

    public void setType(ElevatorType type) {
        this.type = type;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
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

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate firstCheck) {
        this.registerDate = firstCheck;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
