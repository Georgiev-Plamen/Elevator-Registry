package bg.softuni.Elevator.Registryregister.repository;

import bg.softuni.Elevator.Registryregister.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserDetails findByUsername(String username);
}
