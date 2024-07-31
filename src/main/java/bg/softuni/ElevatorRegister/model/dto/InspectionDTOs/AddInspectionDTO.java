package bg.softuni.ElevatorRegister.model.dto.InspectionDTOs;

import bg.softuni.ElevatorRegister.model.entity.Customer;
import bg.softuni.ElevatorRegister.model.entity.Elevator;
import bg.softuni.ElevatorRegister.model.entity.InspectionsStatus;
import bg.softuni.ElevatorRegister.model.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddInspectionDTO {

    private Long id;
    private List<Elevator> elevators;
    private User user;
    private LocalDate lastInspection;
    private LocalDate nextInspection;
    private String address;
    private Double price;
    private Customer customer;
    private InspectionsStatus status;

    private LocalDate inspectionDate;

    public AddInspectionDTO() {
        this.elevators = new ArrayList<>();
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public void setElevators(List<Elevator> elevators) {
        this.elevators = elevators;
    }

    public LocalDate getLastInspection() {
        return lastInspection;
    }

    public void setLastInspection(LocalDate lastInspection) {
        this.lastInspection = lastInspection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getNextInspection() {
        return nextInspection;
    }

    public void setNextInspection(LocalDate nextInspection) {
        this.nextInspection = nextInspection;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public InspectionsStatus getStatus() {
        return status;
    }

    public void setStatus(InspectionsStatus status) {
        this.status = status;
    }

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }
}
