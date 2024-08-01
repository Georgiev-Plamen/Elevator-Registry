package service.impl;

import bg.softuni.ElevatorRegister.model.dto.CustomerDTOs.CustomerListDTO;
import bg.softuni.ElevatorRegister.model.entity.Customer;
import bg.softuni.ElevatorRegister.repository.CustomerRepository;
import bg.softuni.ElevatorRegister.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void testGetCustomerById() {
        // Arrange
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("Deplan");
        customer.setVat("102120750");
        customer.setAddress("Chataldza");
        customer.setContactPerson("Pesho");
        customer.setTelNumber("0888888888");
        customer.setEmail("pesho@example.com");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));


        Customer result = customerService.getCustomerById(customerId);

        verify(customerRepository, times(1)).findById(customerId);

        assertNotNull(result, "Customer should not be null");
        assertEquals(customerId, result.getId(), "Customer ID should match");
        assertEquals("Deplan", result.getName(), "Customer name should match");
        assertEquals("102120750", result.getVat(), "Customer VAT should match");
        assertEquals("Chataldza", result.getAddress(), "Customer address should match");
        assertEquals("Pesho", result.getContactPerson(), "Customer contact person should match");
        assertEquals("0888888888", result.getTelNumber(), "Customer tel number should match");
        assertEquals("pesho@example.com", result.getEmail(), "Customer email should match");
    }

    @Test
    public void testGetAllCustomers() {
        // Arrange
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("John Doe");
        customer1.setVat("VAT123");
        customer1.setAddress("123 Main St");
        customer1.setContactPerson("Jane Doe");
        customer1.setTelNumber("555-1234");
        customer1.setEmail("john.doe@example.com");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setName("Alice Smith");
        customer2.setVat("VAT456");
        customer2.setAddress("456 Elm St");
        customer2.setContactPerson("Bob Smith");
        customer2.setTelNumber("555-5678");
        customer2.setEmail("alice.smith@example.com");

        List<Customer> customers = Arrays.asList(customer1, customer2);

        CustomerListDTO dto1 = new CustomerListDTO(1L, "John Doe", "VAT123", "123 Main St", "Jane Doe", "555-1234", "john.doe@example.com");
        CustomerListDTO dto2 = new CustomerListDTO(2L, "Alice Smith", "VAT456", "456 Elm St", "Bob Smith", "555-5678", "alice.smith@example.com");

        List<CustomerListDTO> expectedDtoList = Arrays.asList(dto1, dto2);

        // Mock the repository behavior
        when(customerRepository.findAll()).thenReturn(customers);

        // Act
        List<CustomerListDTO> result = customerService.getAllCustomers();

        // Assert
        // Verify that findAll() was called once
        verify(customerRepository, times(1)).findAll();

        // Verify the result
        assertEquals(expectedDtoList, result, "The list of CustomerListDTO should match the expected DTO list");
    }
}
