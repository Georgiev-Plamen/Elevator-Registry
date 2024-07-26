package bg.softuni.ElevatorRegister.repository;

import bg.softuni.ElevatorRegister.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByVat(String number);
}
