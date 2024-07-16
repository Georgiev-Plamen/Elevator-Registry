package bg.softuni.Elevator.Registryregister.repository;

import bg.softuni.Elevator.Registryregister.model.entity.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectionRepository extends JpaRepository <Inspection, Long>  {
}
