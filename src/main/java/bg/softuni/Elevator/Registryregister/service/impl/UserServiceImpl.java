package bg.softuni.Elevator.Registryregister.service.impl;

import bg.softuni.Elevator.Registryregister.model.dto.UserRegistrationDTO;
import bg.softuni.Elevator.Registryregister.model.entity.UserEntity;
import bg.softuni.Elevator.Registryregister.repository.UserRepository;
import bg.softuni.Elevator.Registryregister.service.UserService;
import org.modelmapper.ModelMapper;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        userRepository.save(modelMapper.map(userRegistrationDTO, UserEntity.class));
    }
}
