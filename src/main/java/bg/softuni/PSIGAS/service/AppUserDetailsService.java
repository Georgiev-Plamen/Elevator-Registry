package bg.softuni.PSIGAS.service;

import bg.softuni.PSIGAS.model.entity.Role;
import bg.softuni.PSIGAS.model.entity.User;
import bg.softuni.PSIGAS.model.entity.UserRoleEnum;
import bg.softuni.PSIGAS.model.user.AppUserDetails;
import bg.softuni.PSIGAS.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return userRepository
                .findByUsername(username)
                .map(AppUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }

    private static UserDetails map(User user) {

        return new AppUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(Role::getRole).map(AppUserDetailsService::map).toList(),
                user.getFirstName(),
                user.getLastName()
                );
    }

    private static GrantedAuthority map(UserRoleEnum role) {
        return new SimpleGrantedAuthority(
                "ROLE_" + role
        );
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
