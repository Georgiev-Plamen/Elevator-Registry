package bg.softuni.Elevator.Registryregister.model.dto.CustomerDTOs;

public record CustomerListDTO(
        Long id,
        String customerName,
        String customerVat,
        String customerAddress,
        String contactPerson,
        String telNumber,
        String email
) {
}
