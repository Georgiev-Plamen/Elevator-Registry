package bg.softuni.PSIGAS.service.impl;

import bg.softuni.PSIGAS.model.dto.CustomerDTOs.AddCustomerDTO;
import bg.softuni.PSIGAS.model.dto.CustomerDTOs.CustomerDetailsDTO;
import bg.softuni.PSIGAS.model.dto.CustomerDTOs.CustomerListDTO;
import bg.softuni.PSIGAS.model.entity.Customer;
import bg.softuni.PSIGAS.repository.CustomerRepository;
import bg.softuni.PSIGAS.service.CustomerService;
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

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerDetailsDTO getCustomerDetails(Long id) {
        return modelMapper.map(customerRepository.getReferenceById(id), CustomerDetailsDTO.class);
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
