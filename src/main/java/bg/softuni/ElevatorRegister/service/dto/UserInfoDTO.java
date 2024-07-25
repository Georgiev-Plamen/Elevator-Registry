package bg.softuni.ElevatorRegister.service.dto;

public record UserInfoDTO (
        Long id,
        String firstName,
        String lastName,
        String username,
        String email,
        String password) {

}
