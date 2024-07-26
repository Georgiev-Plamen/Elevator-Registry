package web;

import bg.softuni.ElevatorRegister.ElevatorRegisterApplication;
import bg.softuni.ElevatorRegister.model.entity.Customer;
import bg.softuni.ElevatorRegister.model.entity.Elevator;
import bg.softuni.ElevatorRegister.repository.CustomerRepository;
import bg.softuni.ElevatorRegister.repository.ElevatorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ElevatorRegisterApplication.class)
@AutoConfigureMockMvc
@WithMockUser(
        username = "plamen",
        roles = {"USER", "ADMIN"}
)
public class CustomerControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testAddCustomer() throws Exception {

        mockMvc.perform(post("/customer/addCustomer")
                        .param("name", "Test")
                        .param("vat", "999999999")
                        .param("address", "Burgas")
                        .param("contactPerson", "Testcho")
                        .param("telNumber", "0887888888")
                        .param("email", "test@test.test")
                        .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/customer/allCustomers"));

        Optional<Customer> optionalCustomer = customerRepository.findByVat("999999999");

        Assertions.assertTrue(optionalCustomer.isPresent());

        Customer customer = optionalCustomer.get();


        Assertions.assertEquals("Test", customer.getName());
        Assertions.assertEquals("Burgas", customer.getAddress());
        Assertions.assertEquals("Testcho", customer.getContactPerson());
        Assertions.assertEquals("0887888888", customer.getTelNumber());
        Assertions.assertEquals("test@test.test", customer.getEmail());

    }
}
