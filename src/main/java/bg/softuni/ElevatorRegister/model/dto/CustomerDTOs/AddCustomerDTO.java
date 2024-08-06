package bg.softuni.ElevatorRegister.model.dto.CustomerDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddCustomerDTO {

    @NotNull(message = "Name may not be null")
    @Size(min=3, max=50)
    private String name;

    @NotNull
    @Size(min=6, max=15)
    private String vat;

    @Size(min=3, max=30)
    private String address;
    @NotNull
    @Size(min=5)
    private String contactPerson;
    @NotNull
    @Size(min = 5)
    private String telNumber;

    @Email
    private String email;

    public AddCustomerDTO(String name, String vat, String address, String contactPerson, String telNumber, String email) {
    }

    public static AddCustomerDTO empty() {
        return new AddCustomerDTO(null, null, null, null, null, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
