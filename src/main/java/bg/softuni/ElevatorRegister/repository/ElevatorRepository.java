package bg.softuni.ElevatorRegister.repository;

import bg.softuni.ElevatorRegister.model.entity.Elevator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ElevatorRepository extends JpaRepository<Elevator, Long> {
    List<Elevator> findAllByOwnerId(Long id);
}
