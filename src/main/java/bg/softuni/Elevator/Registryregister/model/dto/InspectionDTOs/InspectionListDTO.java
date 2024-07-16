package bg.softuni.Elevator.Registryregister.model.dto.InspectionDTOs;

import java.time.LocalDate;

public record InspectionListDTO(
        Long id,
        String user,
        String customer,
        Long elevatorId,
        String address,
        Double price,
        String status
) {
}
