package service.impl;

import bg.softuni.ElevatorRegister.model.entity.User;
import bg.softuni.ElevatorRegister.repository.UserRepository;
import bg.softuni.ElevatorRegister.model.dto.UserDTOs.UserRegistrationDTO;
import bg.softuni.ElevatorRegister.service.AppUserDetailsService;
import bg.softuni.ElevatorRegister.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private UserServiceImpl toTest;

    @Captor
    private ArgumentCaptor<User> userEntityCaptor;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private AppUserDetailsService mockAppUserDetails;

    @BeforeEach
    void setUp() {

        toTest = new UserServiceImpl(
                mockUserRepository,
                mockAppUserDetails,
                new ModelMapper(),
                mockPasswordEncoder
        );

    }

    @Test
    void testUserRegistration() {

        UserRegistrationDTO userRegistrationDTO =
                new UserRegistrationDTO()
                        .setUsername("gosho")
                        .setEmail("gosho@gosho.com")
                        .setFirstName("Gosho")
                        .setLastName("Georgiev")
                        .setPassword("123123");

        when(mockPasswordEncoder.encode(userRegistrationDTO.getPassword()))
                .thenReturn(userRegistrationDTO.getPassword()+userRegistrationDTO.getPassword());



        toTest.registerUser(userRegistrationDTO);


        verify(mockUserRepository).save(userEntityCaptor.capture());

        User actualSavedEntity = userEntityCaptor.getValue();

        Assertions.assertNotNull(actualSavedEntity);
        Assertions.assertEquals(userRegistrationDTO.getFirstName(), actualSavedEntity.getFirstName());
        Assertions.assertEquals(userRegistrationDTO.getLastName(), actualSavedEntity.getLastName());
        Assertions.assertEquals(userRegistrationDTO.getPassword() + userRegistrationDTO.getPassword(),
                actualSavedEntity.getPassword());
        Assertions.assertEquals(userRegistrationDTO.getEmail(), actualSavedEntity.getEmail());
    }
}
