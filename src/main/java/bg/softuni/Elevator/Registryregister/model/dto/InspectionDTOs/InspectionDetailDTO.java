package bg.softuni.Elevator.Registryregister.model.dto.InspectionDTOs;

import bg.softuni.Elevator.Registryregister.model.entity.Elevator;
import bg.softuni.Elevator.Registryregister.model.entity.InspectionsStatus;
import bg.softuni.Elevator.Registryregister.model.entity.User;

import java.time.LocalDate;
import java.util.List;

public class InspectionDetailDTO {

    private List<Elevator> elvators;
    private User user;
    private LocalDate currentInspection;
    private LocalDate nextInspection;
    private String address;
    private Double price;
    private Long customer;
    private InspectionsStatus status;

    public InspectionDetailDTO() {
    }

    public List<Elevator> getElvators() {
        return elvators;
    }

    public void setElvators(List<Elevator> elvators) {
        this.elvators = elvators;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getCurrentInspection() {
        return currentInspection;
    }

    public void setCurrentInspection(LocalDate currentInspection) {
        this.currentInspection = currentInspection;
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

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public InspectionsStatus getStatus() {
        return status;
    }

    public void setStatus(InspectionsStatus status) {
        this.status = status;
    }
}
