package bg.softuni.Elevator.Registryregister.service.dto;

public record UserInfoDTO (
        Long id,
        String firstName,
        String lastName,
        String username,
        String email,
        String password) {

}
