package bg.softuni.Elevator.Registryregister.service.impl;

import bg.softuni.Elevator.Registryregister.model.dto.UserRegistrationDTO;
import bg.softuni.Elevator.Registryregister.model.entity.User;
import bg.softuni.Elevator.Registryregister.repository.UserRepository;
import bg.softuni.Elevator.Registryregister.service.AppUserDetailsService;
import bg.softuni.Elevator.Registryregister.service.UserService;
import bg.softuni.Elevator.Registryregister.service.dto.UserInfoDTO;
import bg.softuni.Elevator.Registryregister.service.dto.UserProfileDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AppUserDetailsService appUserDetailsService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, AppUserDetailsService appUserDetailsService, ModelMapper modelMapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.appUserDetailsService = appUserDetailsService;
        this.modelMapper = modelMapper;
        this.encoder = encoder;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {

        userRepository.save(map(userRegistrationDTO));
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

}
