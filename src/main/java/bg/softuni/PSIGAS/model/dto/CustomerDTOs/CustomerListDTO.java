package bg.softuni.PSIGAS.model.dto.CustomerDTOs;

public record CustomerListDTO(
        Long id,
        String name,
        String vat,
        String address,
        String contactPerson,
        String telNumber,
        String email
) {
}
