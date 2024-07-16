package bg.softuni.Elevator.Registryregister.service;

import bg.softuni.Elevator.Registryregister.model.dto.ElevatorDTOs.AddElevatorDTO;
import bg.softuni.Elevator.Registryregister.model.dto.ElevatorDTOs.ElevatorDetailsDTO;
import bg.softuni.Elevator.Registryregister.model.dto.ElevatorDTOs.ElevatorListDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ElevatorService {

    void AddNewElevator(AddElevatorDTO addElevatorDTO, UserDetails userDetails);

    List<ElevatorListDTO> getAllElevators();

    void deleteElevator(Long id);

    ElevatorDetailsDTO getElevatorDetails(Long id);

    void editElevator(Long id, ElevatorDetailsDTO elevatorDetailsDTO);

    String findAuthorOnElevator(ElevatorDetailsDTO elevatorDetailsDTO);

}
