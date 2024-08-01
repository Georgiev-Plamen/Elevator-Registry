package bg.softuni.ElevatorRegister.repository;

import bg.softuni.ElevatorRegister.model.entity.Role;
import bg.softuni.ElevatorRegister.model.entity.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {
    Optional<Role> findByName(UserRoleEnum name);
}
