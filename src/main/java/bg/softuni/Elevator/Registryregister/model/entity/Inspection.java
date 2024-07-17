package bg.softuni.Elevator.Registryregister.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="inspections")
public class Inspection extends BaseEntity{

        @OneToMany
        private List<Elevator> elevators;
        @ManyToOne
        private User user;
        private LocalDate currentInspection;
        private LocalDate nextInspection;
        private String address;
        private Double price;
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
