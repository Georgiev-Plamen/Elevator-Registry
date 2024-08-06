package bg.softuni.ElevatorRegister.model.dto.InspectionDTOs;

import bg.softuni.ElevatorRegister.model.entity.Customer;
import bg.softuni.ElevatorRegister.model.entity.Elevator;
import bg.softuni.ElevatorRegister.model.entity.InspectionsStatus;
import bg.softuni.ElevatorRegister.model.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class InspectionDetailDTO {
    private Long id;
    private List<Elevator> elevators;
    private User user;
    private LocalDate lastInspection;
    private LocalDate nextInspection;

    @NotEmpty
    @Size(min=5, max=50)
    private String address;

    @Positive
    private Double price;
    private Customer customer;
    private InspectionsStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public InspectionDetailDTO() {
//        elevators = new ArrayList<>();
//    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public void setElevators(List<Elevator> elevators) {
        this.elevators = elevators;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
