package bg.softuni.ElevatorRegister.model.dto.InspectionDTOs;

import bg.softuni.ElevatorRegister.model.entity.Customer;
import bg.softuni.ElevatorRegister.model.entity.Elevator;
import bg.softuni.ElevatorRegister.model.entity.User;

import java.util.List;

public record InspectionListDTO(
        Long id,
        User user,
        Customer customer,
        List<Elevator> elevators,
        String address,
        Double price,
        String status
) {
}
