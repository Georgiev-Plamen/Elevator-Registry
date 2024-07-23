package service.impl;

import bg.softuni.PSIGAS.model.entity.User;
import bg.softuni.PSIGAS.model.user.AppUserDetails;
import bg.softuni.PSIGAS.repository.UserRepository;
import bg.softuni.PSIGAS.service.AppUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.when;

    @ExtendWith(MockitoExtension.class)
    class AppUserDetailsServiceTest {

        private static final String TEST_USERNAME = "plamen";
        private static final String NOT_EXISTENT_USERNAME = "noone";

        private AppUserDetailsService toTest;
        @Mock
        private UserRepository mockUserRepository;

        @BeforeEach
        void setUp() {
            toTest = new AppUserDetailsService(mockUserRepository);
        }

        @Test
        void testLoadUserByUsername_UserFound() {

            // Arrange
            User testUser = new User()
                    .setUsername(TEST_USERNAME)
                    .setPassword("123123")
                    .setFirstName("Pesho")
                    .setLastName("Petrov")
//                    .setRoles(List.of(
//                            new Role().setRole(UserRoleEnum.ADMIN),
//                            new Role().setRole(UserRoleEnum.USER)))
                    ;

            when(mockUserRepository.findByUsername(TEST_USERNAME))
                    .thenReturn(Optional.of(testUser));

            // Act
            UserDetails userDetails = toTest.loadUserByUsername(TEST_USERNAME);

            // Assert
            Assertions.assertInstanceOf(AppUserDetails.class, userDetails);

            AppUserDetails appUserDetails = (AppUserDetails) userDetails;

            Assertions.assertEquals(TEST_USERNAME, userDetails.getUsername());
            Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());
            Assertions.assertEquals(testUser.getFirstName(), appUserDetails.getFirstName());
            Assertions.assertEquals(testUser.getLastName(), appUserDetails.getLastName());
            Assertions.assertEquals(testUser.getFirstName() + " " + testUser.getLastName(),
                    appUserDetails.getFullName());

//            var expectedRoles = testUser.getRoles().stream().map(Role::getRole).map(r -> "ROLE_" + r).toList();
//            var actualRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

//            Assertions.assertEquals(expectedRoles, actualRoles);
        }

        @Test
        void testLoadUserByUsername_UserNotFound() {
            Assertions.assertThrows(
                    UsernameNotFoundException.class,
                    () -> toTest.loadUserByUsername(NOT_EXISTENT_USERNAME)
            );
        }

    }

