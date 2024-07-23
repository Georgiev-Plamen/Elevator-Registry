package bg.softuni.PSIGAS.service.impl;

import bg.softuni.PSIGAS.model.dto.UserDTOs.UserEditDTO;
import bg.softuni.PSIGAS.model.dto.UserDTOs.UserRegistrationDTO;
import bg.softuni.PSIGAS.model.entity.User;
import bg.softuni.PSIGAS.repository.UserRepository;
import bg.softuni.PSIGAS.service.AppUserDetailsService;
import bg.softuni.PSIGAS.service.UserService;
import bg.softuni.PSIGAS.service.dto.UserInfoDTO;
import bg.softuni.PSIGAS.service.dto.UserProfileDto;
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
