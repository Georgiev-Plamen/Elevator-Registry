package bg.softuni.Elevator.Registryregister.service;

import bg.softuni.Elevator.Registryregister.model.dto.UserRegistrationDTO;
import bg.softuni.Elevator.Registryregister.service.dto.UserProfileDto;

public interface UserService {
    void registerUser(UserRegistrationDTO userRegistrationDTO);

}
