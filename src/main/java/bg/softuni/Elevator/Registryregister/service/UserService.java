package bg.softuni.Elevator.Registryregister.service;

import bg.softuni.Elevator.Registryregister.model.dto.UserEditDTO;
import bg.softuni.Elevator.Registryregister.model.dto.UserRegistrationDTO;
import bg.softuni.Elevator.Registryregister.service.dto.UserInfoDTO;
import bg.softuni.Elevator.Registryregister.service.dto.UserProfileDto;

import java.util.List;

public interface UserService {
    void registerUser(UserRegistrationDTO userRegistrationDTO);

    List<UserInfoDTO> getAllUsers();

    void deleteUser(Long id);

    UserProfileDto getUserDetails(Long id);

    void editUser(Long id, UserEditDTO userEditDTO);
}
