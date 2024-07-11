package bg.softuni.Elevator.Registryregister.service;

import bg.softuni.Elevator.Registryregister.model.dto.AddElevatorDTO;
import bg.softuni.Elevator.Registryregister.model.dto.ElevatorDetailsDTO;
import bg.softuni.Elevator.Registryregister.model.dto.ElevatorListDTO;

import java.util.List;

public interface ElevatorService {

    void AddNewElevator(AddElevatorDTO addElevatorDTO);

    List<ElevatorListDTO> getAllElevators();

    void deleteElevator(Long id);

    ElevatorDetailsDTO getElevatorDetails(Long id);

}
