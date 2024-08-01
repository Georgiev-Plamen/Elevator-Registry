package bg.softuni.ElevatorRegister.service.impl;

import bg.softuni.ElevatorRegister.model.dto.UserDTOs.UserEditDTO;
import bg.softuni.ElevatorRegister.model.dto.UserDTOs.UserRegistrationDTO;
import bg.softuni.ElevatorRegister.model.entity.Role;
import bg.softuni.ElevatorRegister.model.entity.User;
import bg.softuni.ElevatorRegister.model.entity.UserRoleEnum;
import bg.softuni.ElevatorRegister.repository.RoleRepository;
import bg.softuni.ElevatorRegister.repository.UserRepository;
import bg.softuni.ElevatorRegister.service.AppUserDetailsService;
import bg.softuni.ElevatorRegister.service.UserService;
import bg.softuni.ElevatorRegister.service.dto.UserInfoDTO;
import bg.softuni.ElevatorRegister.service.dto.UserProfileDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AppUserDetailsService appUserDetailsService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, AppUserDetailsService appUserDetailsService, ModelMapper modelMapper, PasswordEncoder encoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.appUserDetailsService = appUserDetailsService;
        this.modelMapper = modelMapper;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {

        Optional<User> optionalUserByEmail = userRepository.findByEmail(userRegistrationDTO.getEmail());
        Optional<User> optionalUserByUsername = userRepository.findByUsername(userRegistrationDTO.getUsername());

        if (optionalUserByEmail.isPresent() || optionalUserByUsername.isPresent()) {
            return;
        }

        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
            return;
        }

        User user = map(userRegistrationDTO);

        Optional<Role> optionalRoles = roleRepository.findByName(UserRoleEnum.USER);
        List<Role> roles = new ArrayList<>();

        roles.add(optionalRoles.get());
        user.setRole(roles);

        userRepository.save(user);
    }

    @Override
    public List<UserInfoDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(UserServiceImpl::toAllUser)
                .toList();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserProfileDto getUserDetails(Long id) {
        return modelMapper.map(appUserDetailsService.getUserById(id), UserProfileDto.class);
    }

    private static UserInfoDTO toAllUser(User user) {

        return new UserInfoDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
                );
    }


    private User map(UserRegistrationDTO userRegistrationDTO) {
        User mappedEntity = modelMapper.map(userRegistrationDTO, User.class);
        mappedEntity.setPassword(encoder.encode(userRegistrationDTO.getPassword()));

        return mappedEntity;
    }

    @Override
    public void editUser(Long id, UserEditDTO userEditDTO) {
        User editUser = userRepository.findById(id).orElseThrow(null);

        editUser.setFirstName(userEditDTO.getFirstName());
        editUser.setLastName(userEditDTO.getLastName());
        editUser.setEmail(userEditDTO.getEmail());
        editUser.setUsername(userEditDTO.getUsername());

        userRepository.save(editUser);
    }
}
