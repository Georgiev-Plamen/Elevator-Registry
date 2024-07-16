package bg.softuni.Elevator.Registryregister.service;

import bg.softuni.Elevator.Registryregister.model.dto.CustomerDTOs.AddCustomerDTO;
import bg.softuni.Elevator.Registryregister.model.dto.CustomerDTOs.CustomerListDTO;

import java.util.List;

public interface CustomerService {

    void addNewCustomer(AddCustomerDTO addCustomerDTO);

    List<CustomerListDTO> getAllCustomers();
}
