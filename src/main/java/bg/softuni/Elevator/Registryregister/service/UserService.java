package bg.softuni.Elevator.Registryregister.service;

import bg.softuni.Elevator.Registryregister.model.dto.UserDTOs.UserEditDTO;
import bg.softuni.Elevator.Registryregister.model.dto.UserDTOs.UserRegistrationDTO;
import bg.softuni.Elevator.Registryregister.service.dto.UserInfoDTO;
import bg.softuni.Elevator.Registryregister.service.dto.UserProfileDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void registerUser(UserRegistrationDTO userRegistrationDTO);

    List<UserInfoDTO> getAllUsers();

    void deleteUser(Long id);

    UserProfileDto getUserDetails(Long id);

    void editUser(Long id, UserEditDTO userEditDTO);


}
