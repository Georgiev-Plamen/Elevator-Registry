package bg.softuni.PSIGAS.repository;

import bg.softuni.PSIGAS.model.entity.Elevator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElevatorRepository extends JpaRepository<Elevator, Long> {
}
