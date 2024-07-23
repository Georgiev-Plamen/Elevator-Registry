package web;
//package bg.softuni.PSIGAS.web;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import bg.softuni.PSIGAS.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import bg.softuni.PSIGAS.model.entity.User;
import java.util.Optional;



@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testRegistration() throws Exception {

        mockMvc.perform(post("/users/register")
                        .param("email", "gosho@gosho.com")
                        .param("firstName", "Gosho")
                        .param("lastName", "Georgiev")
                        .param("password", "123123")
                        .param("username", "gosho")
                        .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Optional<User> userEntityOpt = userRepository.findByUsername("gosho");

        Assertions.assertTrue(userEntityOpt.isPresent());

        User user = userEntityOpt.get();

        Assertions.assertEquals("Gosho", user.getFirstName());
        Assertions.assertEquals("Georgiev", user.getLastName());

        Assertions.assertTrue(passwordEncoder.matches("123123", user.getPassword()));
    }
}