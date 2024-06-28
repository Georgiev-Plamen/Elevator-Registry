package bg.softuni.Elevator.Registryregister.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity extends BaseEntity{

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String firstName;
    private String lastName;
    private String password;


}
