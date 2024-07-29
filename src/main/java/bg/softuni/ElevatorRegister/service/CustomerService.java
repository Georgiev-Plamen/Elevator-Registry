package bg.softuni.ElevatorRegister.service;

import bg.softuni.ElevatorRegister.model.dto.CustomerDTOs.AddCustomerDTO;
import bg.softuni.ElevatorRegister.model.dto.CustomerDTOs.CustomerDetailsDTO;
import bg.softuni.ElevatorRegister.model.dto.CustomerDTOs.CustomerListDTO;
import bg.softuni.ElevatorRegister.model.entity.Customer;

import java.util.List;

public interface CustomerService {

    void addNewCustomer(AddCustomerDTO addCustomerDTO);
    List<CustomerListDTO> getAllCustomers();
    void deleteCustomer(Long id);
    CustomerDetailsDTO getCustomerDetails(Long id);
    Customer getCustomerById (Long id);
    void editCustomer(Long id, CustomerDetailsDTO customerDetailsDTO);

}


