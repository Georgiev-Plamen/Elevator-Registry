package service.impl;

import bg.softuni.ElevatorRegister.model.entity.Role;
import bg.softuni.ElevatorRegister.model.entity.User;
import bg.softuni.ElevatorRegister.model.entity.UserRoleEnum;
import bg.softuni.ElevatorRegister.repository.RoleRepository;
import bg.softuni.ElevatorRegister.repository.UserRepository;
import bg.softuni.ElevatorRegister.model.dto.UserDTOs.UserRegistrationDTO;
import bg.softuni.ElevatorRegister.service.AppUserDetailsService;
import bg.softuni.ElevatorRegister.service.dto.UserInfoDTO;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

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
    private RoleRepository mockRoleRepository;

    @Mock
    private AppUserDetailsService mockAppUserDetails;

    @BeforeEach
    void setUp() {

        toTest = new UserServiceImpl(
                mockUserRepository,
                mockAppUserDetails,
                new ModelMapper(),
                mockPasswordEncoder,
                mockRoleRepository
        );
    }

    @Test
    void testUserRegistration() {
        // Arrange
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO()
                .setUsername("gosho")
                .setEmail("gosho@gosho.com")
                .setFirstName("Gosho")
                .setLastName("Georgiev")
                .setPassword("123123")
                .setConfirmPassword("123123");

        when(mockPasswordEncoder.encode(userRegistrationDTO.getPassword()))
                .thenReturn(userRegistrationDTO.getPassword() + userRegistrationDTO.getPassword());

        when(mockUserRepository.findByEmail(userRegistrationDTO.getEmail()))
                .thenReturn(Optional.empty());
        when(mockUserRepository.findByUsername(userRegistrationDTO.getUsername()))
                .thenReturn(Optional.empty());

        Role userRole = new Role();
        userRole.setName(UserRoleEnum.USER);
        when(mockRoleRepository.findByName(UserRoleEnum.USER)).thenReturn(Optional.of(userRole));

        // Act
        toTest.registerUser(userRegistrationDTO);

        // Assert
        verify(mockUserRepository).save(userEntityCaptor.capture());

        User actualSavedEntity = userEntityCaptor.getValue();

//        assertNotNull(actualSavedEntity);
        assertEquals(userRegistrationDTO.getFirstName(), actualSavedEntity.getFirstName());
        assertEquals(userRegistrationDTO.getLastName(), actualSavedEntity.getLastName());
        assertEquals(userRegistrationDTO.getPassword() + userRegistrationDTO.getPassword(), actualSavedEntity.getPassword());
        assertEquals(userRegistrationDTO.getEmail(), actualSavedEntity.getEmail());

        // Verify the user role is set correctly
        List<Role> roles = actualSavedEntity.getRoles();  // Updated to getRoles()
        assertFalse(roles.isEmpty());
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setId(1l);
        user1.setFirstName("Pesho");
        user1.setLastName("Peshov");
        user1.setUsername("pesho");
        user1.setEmail("pesho@pesho");
        user1.setPassword("123");

        User user2 = new User();
        user2.setId(2l);
        user2.setFirstName("Gosho");
        user2.setLastName("Goshov");
        user2.setUsername("gosho");
        user2.setEmail("gosho@gosho");
        user2.setPassword("456");

        List<User> users = Arrays.asList(user1, user2);

        UserInfoDTO dto1 = new UserInfoDTO(1l,"Pesho", "Peshov","pesho","pesho@pesho", "123"); // Expected DTOs
        UserInfoDTO dto2 = new UserInfoDTO(2l,"Gosho", "Goshov","gosho","gosho@gosho", "456");
        List<UserInfoDTO> expectedDtoList = Arrays.asList(dto1, dto2);

        when(mockUserRepository.findAll()).thenReturn(users);

        List<UserInfoDTO> result = toTest.getAllUsers();

        verify(mockUserRepository, times(1)).findAll();

        assertEquals(expectedDtoList, result);
    }
}
