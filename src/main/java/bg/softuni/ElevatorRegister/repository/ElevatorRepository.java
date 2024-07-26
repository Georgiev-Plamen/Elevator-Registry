package bg.softuni.ElevatorRegister.repository;

import bg.softuni.ElevatorRegister.model.entity.Elevator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ElevatorRepository extends JpaRepository<Elevator, Long> {
    List<Elevator> findAllByOwnerId(Long id);

    Optional<Elevator> findElevatorByManufacturerNumber(String manNumber);
}
