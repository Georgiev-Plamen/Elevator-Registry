package bg.softuni.Elevator.Registryregister.service.impl;

import bg.softuni.Elevator.Registryregister.model.dto.CustomerDTOs.AddCustomerDTO;
import bg.softuni.Elevator.Registryregister.model.dto.CustomerDTOs.CustomerListDTO;
import bg.softuni.Elevator.Registryregister.model.entity.Customer;
import bg.softuni.Elevator.Registryregister.repository.CustomerRepository;
import bg.softuni.Elevator.Registryregister.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addNewCustomer(AddCustomerDTO addCustomerDTO) {
        customerRepository.save(map(addCustomerDTO));
    }

    @Override
    public List<CustomerListDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(CustomerServiceImpl::toAllCustomer)
                .toList();
    }
    private static CustomerListDTO toAllCustomer (Customer customer) {
        return new CustomerListDTO(
                customer.getId(),
                customer.getCustomerName(),
                customer.getCustomerVat(),
                customer.getCustomerAddress(),
                customer.getContactPerson(),
                customer.getTelNumber(),
                customer.getEmail()
        );


    }

    private Customer map(AddCustomerDTO addCustomerDTO) {
        Customer mappedCustomer = modelMapper.map(addCustomerDTO, Customer.class);

        return mappedCustomer;
    }
}
