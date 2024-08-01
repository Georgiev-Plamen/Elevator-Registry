package bg.softuni.ElevatorRegister.init;

import bg.softuni.ElevatorRegister.model.entity.Role;
import bg.softuni.ElevatorRegister.model.entity.User;
import bg.softuni.ElevatorRegister.model.entity.UserRoleEnum;
import bg.softuni.ElevatorRegister.repository.RoleRepository;
import bg.softuni.ElevatorRegister.repository.UserRepository;
import jakarta.persistence.Column;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class AdminRoleInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminRoleInit(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.roleRepository.count() == 0) {

            Arrays.stream(UserRoleEnum.values())
                    .forEach(roleName -> {
                        Role role = new Role();
                        role.setName(roleName);
                        this.roleRepository.saveAndFlush(role);
                    });
        }

        if (this.userRepository.count() == 0) {

            User user = new User();
            Optional<Role> optionalAdmin = this.roleRepository.findByName(UserRoleEnum.ADMIN);
            Optional<Role> optionalUser = this.roleRepository.findByName(UserRoleEnum.USER);

            List<Role> roles = new ArrayList<>();

            if (optionalAdmin.isPresent() && optionalUser.isPresent()) {
                roles.add(optionalAdmin.get());
                roles.add(optionalUser.get());
            }

            user.setUsername("plamen");
            user.setEmail("plamen_gg@mail.bg");
            user.setPassword(this.passwordEncoder.encode("123123"));
            user.setRole(roles);

            this.userRepository.saveAndFlush(user);
        }
    }
}
