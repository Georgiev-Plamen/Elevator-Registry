package bg.softuni.Elevator.Registryregister.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="customers")
public class Customer extends BaseEntity{
    private String customerName;
    private String customerVat;
    private String customerAddress;
    private String contactPerson;
    private String telNumber;
    private String email;

    @OneToMany(targetEntity = Elevator.class, mappedBy = "owner")
    private List<Elevator> elevatorList;

    public Customer() {
        this.elevatorList = new ArrayList<>();
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerVat() {
        return customerVat;
    }

    public void setCustomerVat(String customerVat) {
        this.customerVat = customerVat;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Elevator> getElevatorList() {
        return elevatorList;
    }

    public void setElevatorList(List<Elevator> elevatorList) {
        this.elevatorList = elevatorList;
    }
}
