package bg.softuni.ElevatorRegister.service;

import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.AddElevatorDTO;
import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.CustomerElevatorDTO;
import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.ElevatorDetailsDTO;
import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.ElevatorListDTO;
import bg.softuni.ElevatorRegister.model.entity.Elevator;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ElevatorService {

    void AddNewElevator(AddElevatorDTO addElevatorDTO, UserDetails userDetails);

    List<ElevatorListDTO> getAllElevators();

    void deleteElevator(Long id);

    ElevatorDetailsDTO getElevatorDetails(Long id);

    void editElevator(Long id, ElevatorDetailsDTO elevatorDetailsDTO);

    String findAuthorOnElevator(ElevatorDetailsDTO elevatorDetailsDTO);

    List <ElevatorListDTO> getElevatorsDetails();

    List<CustomerElevatorDTO> getAllCustomerElevator(Long id);


}
