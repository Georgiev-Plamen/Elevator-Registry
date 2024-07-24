package bg.softuni.PSIGAS.repository;

import bg.softuni.PSIGAS.model.dto.ElevatorDTOs.ElevatorListDTO;
import bg.softuni.PSIGAS.model.entity.Elevator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ElevatorRepository extends JpaRepository<Elevator, Long> {
    List<Elevator> findAllByOwnerId(Long id);
}
