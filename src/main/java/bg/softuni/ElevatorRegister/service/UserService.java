package bg.softuni.ElevatorRegister.service;

import bg.softuni.ElevatorRegister.model.dto.UserDTOs.UserEditDTO;
import bg.softuni.ElevatorRegister.model.dto.UserDTOs.UserRegistrationDTO;
import bg.softuni.ElevatorRegister.service.dto.UserInfoDTO;
import bg.softuni.ElevatorRegister.service.dto.UserProfileDto;

import java.util.List;

public interface UserService {
    void registerUser(UserRegistrationDTO userRegistrationDTO);

    List<UserInfoDTO> getAllUsers();

    void deleteUser(Long id);

    UserProfileDto getUserDetails(Long id);

    void editUser(Long id, UserEditDTO userEditDTO);


}
