package bg.softuni.Elevator.Registryregister.model.dto.InspectionDTOs;

import bg.softuni.Elevator.Registryregister.model.entity.Customer;
import bg.softuni.Elevator.Registryregister.model.entity.Elevator;

import java.time.LocalDate;
import java.util.List;

public record InspectionListDTO(
        Long id,
        String user,
        Customer customer,
        List<Elevator> elevators,
        String address,
        Double price
//        String status
) {
}
