package bg.softuni.Elevator.Registryregister.repository;

import bg.softuni.Elevator.Registryregister.model.dto.CustomerDTOs.AddCustomerDTO;
import bg.softuni.Elevator.Registryregister.model.dto.CustomerDTOs.CustomerDetailsDTO;
import bg.softuni.Elevator.Registryregister.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
