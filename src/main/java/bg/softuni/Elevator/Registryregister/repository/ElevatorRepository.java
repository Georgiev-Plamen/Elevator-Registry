package bg.softuni.Elevator.Registryregister.repository;

import bg.softuni.Elevator.Registryregister.model.entity.Elevator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElevatorRepository extends JpaRepository<Elevator, Long> {
}
