package bg.softuni.Elevator.Registryregister.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class ElevatorUserDetails extends User {

    private final String firstName;
    private final String lastName;
    public ElevatorUserDetails(String username,
                               String password,
                               Collection<? extends GrantedAuthority> authorities,
                               String firstName,
                               String lastName) {
        super(username, password, authorities);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
