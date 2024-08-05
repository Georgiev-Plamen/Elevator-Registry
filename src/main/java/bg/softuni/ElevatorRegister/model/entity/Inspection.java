package bg.softuni.ElevatorRegister.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="inspections")
public class Inspection extends BaseEntity{

        @ManyToMany
        @JoinTable(
                name = "inspection_elevator",
                joinColumns = @JoinColumn(name = "inspection_id"),
                inverseJoinColumns = @JoinColumn(name = "elevator_id")
        )
        private List<Elevator> elevators;
        @ManyToOne
        private User user;

        @NotEmpty
        private String address;

        private Double price;
        private LocalDate inspectionDate;
        @ManyToOne(fetch = FetchType.EAGER)
        private Customer customer;
        @Enumerated(EnumType.STRING)
        private InspectionsStatus status;

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
