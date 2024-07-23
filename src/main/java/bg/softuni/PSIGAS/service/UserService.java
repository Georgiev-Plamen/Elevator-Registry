package bg.softuni.PSIGAS.service;

import bg.softuni.PSIGAS.model.dto.UserDTOs.UserEditDTO;
import bg.softuni.PSIGAS.model.dto.UserDTOs.UserRegistrationDTO;
import bg.softuni.PSIGAS.service.dto.UserInfoDTO;
import bg.softuni.PSIGAS.service.dto.UserProfileDto;

import java.util.List;

public interface UserService {
    void registerUser(UserRegistrationDTO userRegistrationDTO);

    List<UserInfoDTO> getAllUsers();

    void deleteUser(Long id);

    UserProfileDto getUserDetails(Long id);

    void editUser(Long id, UserEditDTO userEditDTO);


}
