package bg.softuni.ElevatorRegister.model.dto.UserDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserRegistrationDTO {

    @NotEmpty (message = "Username is required")
    @Size(min=4, max=20, message = "Username must be between 4 and 20 characters")
    private String username;
    @NotEmpty (message = "Email is required")
    @Email(message = "Please enter valid email")
    private String email;
    @NotEmpty(message = "First name is required")
    @Size(min=3, max=20, message = "First name must be between 3 and 20 characters")
    private String firstName;

    @NotEmpty
    @Size(min=3, max=20, message = "Last name must be between 3 and 20 characters")
    private String lastName;

    @NotEmpty (message = "Password is required")
    @Size(min=5, max=50, message = "Password must be between 3 and 20 characters")
    private String password;

    @NotEmpty
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public UserRegistrationDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
