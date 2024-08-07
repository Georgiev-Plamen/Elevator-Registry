package bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs;

import bg.softuni.ElevatorRegister.model.entity.Customer;
import bg.softuni.ElevatorRegister.model.entity.ElevatorType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;

public class AddElevatorDTO {

    private ElevatorType type;

    private Customer owner;

    @NotEmpty
    @Size(min=2, max=50)
    private String manufacturer;

    @NotEmpty
    private String manufacturerNumber;

    @NotEmpty
    @Size(min=1, max=10)
    private String yearOfManufacture;

    @Positive
    private int speed;
    @NotNull
    private int numberOfStops;
    private String city;
    @NotEmpty
    @Size(min=5, max=100)
    private String address;
    private LocalDate registerDate;
    private LocalDate lastInspection;
    private LocalDate nextInspection;
    private User author;

    public AddElevatorDTO() {
    }

    public AddElevatorDTO(ElevatorType type, Customer owner, String manufacturer, String manufacturerNumber, String yearOfManufacture, int speed, int numberOfStops, String city, String address, LocalDate registerDate, LocalDate lastInspection, LocalDate nextInspection, User author) {
    }

    public static AddElevatorDTO empty() {
        return new AddElevatorDTO(null,null,null,null,null,0,0,null, null, null, null,null,null);
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
