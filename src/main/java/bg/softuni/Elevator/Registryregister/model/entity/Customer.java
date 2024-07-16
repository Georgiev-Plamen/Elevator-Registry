package bg.softuni.Elevator.Registryregister.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="customers")
public class Customer extends BaseEntity{
    private String companyName;
    private String companyVat;
    private String companyAddress;
    private String contactPerson;
    private String telNumber;
    private String email;

    @OneToMany(targetEntity = Elevator.class, mappedBy = "owner")
    private List<Elevator> elevatorList;

    public Customer() {
        this.elevatorList = new ArrayList<>();
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyVat() {
        return companyVat;
    }

    public void setCompanyVat(String companyVat) {
        this.companyVat = companyVat;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
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
