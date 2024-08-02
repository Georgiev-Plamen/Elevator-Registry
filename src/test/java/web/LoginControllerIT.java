package web;

import bg.softuni.ElevatorRegister.ElevatorRegisterApplication;
import bg.softuni.ElevatorRegister.repository.UserRepository;
import bg.softuni.ElevatorRegister.service.CustomerService;
import bg.softuni.ElevatorRegister.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(classes = ElevatorRegisterApplication.class)
@AutoConfigureMockMvc
public class LoginControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testViewLogin() throws Exception {

        mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
}
