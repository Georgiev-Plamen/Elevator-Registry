package bg.softuni.ElevatorRegister.repository;

import bg.softuni.ElevatorRegister.model.entity.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectionRepository extends JpaRepository <Inspection, Long>  {
}
