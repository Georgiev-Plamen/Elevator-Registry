package bg.softuni.PSIGAS.model.dto.InspectionDTOs;

import bg.softuni.PSIGAS.model.entity.Customer;
import bg.softuni.PSIGAS.model.entity.Elevator;

import java.util.List;

public record InspectionListDTO(
        Long id,
        String user,
        Customer customer,
        List<Elevator> elevators,
        String address,
        Double price,

        String status
) {
}
