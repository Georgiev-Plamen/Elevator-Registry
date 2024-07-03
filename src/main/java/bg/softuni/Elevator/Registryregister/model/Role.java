package bg.softuni.Elevator.Registryregister.model;

import bg.softuni.Elevator.Registryregister.model.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name="roles")
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private UserRoles name;


    public Role() {
    }

    public UserRoles getName() {
        return name;
    }

    public void setName(UserRoles name) {
        this.name = name;
    }
}
