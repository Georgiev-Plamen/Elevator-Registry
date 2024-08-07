package bg.softuni.ElevatorRegister.service.impl;

import bg.softuni.ElevatorRegister.model.dto.CustomerDTOs.AddCustomerDTO;
import bg.softuni.ElevatorRegister.model.dto.CustomerDTOs.CustomerDetailsDTO;
import bg.softuni.ElevatorRegister.model.dto.CustomerDTOs.CustomerListDTO;
import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.CustomerElevatorDTO;
import bg.softuni.ElevatorRegister.model.entity.Customer;
import bg.softuni.ElevatorRegister.repository.CustomerRepository;
import bg.softuni.ElevatorRegister.repository.ElevatorRepository;
import bg.softuni.ElevatorRegister.repository.RoleRepository;
import bg.softuni.ElevatorRegister.service.CustomerService;
import bg.softuni.ElevatorRegister.service.ElevatorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ElevatorRepository elevatorRepository;
    private final ElevatorService elevatorService;
    private final ModelMapper modelMapper;
    public CustomerServiceImpl(CustomerRepository customerRepository, ElevatorRepository elevatorRepository, ElevatorService elevatorService, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.elevatorRepository = elevatorRepository;
        this.elevatorService = elevatorService;
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

    @Override
    public void deleteCustomer(Long id) {
        List<CustomerElevatorDTO> list = elevatorService.getAllCustomerElevator(id);
        elevatorRepository.deleteAllById(list.stream().map(e -> e.id()).toList());
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerDetailsDTO getCustomerDetails(Long id) {
        return modelMapper.map(customerRepository.getReferenceById(id), CustomerDetailsDTO.class);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public void editCustomer(Long id, CustomerDetailsDTO customerDetailsDTO) {
        Customer customer = customerRepository.findById(id).get();
        customer = modelMapper.map(customerDetailsDTO, Customer.class);

        customerRepository.save(customer);
    }


    private static CustomerListDTO toAllCustomer (Customer customer) {
        return new CustomerListDTO(
                customer.getId(),
                customer.getName(),
                customer.getVat(),
                customer.getAddress(),
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
