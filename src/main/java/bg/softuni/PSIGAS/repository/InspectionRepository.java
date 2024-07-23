package bg.softuni.PSIGAS.repository;

import bg.softuni.PSIGAS.model.entity.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectionRepository extends JpaRepository <Inspection, Long>  {
}
