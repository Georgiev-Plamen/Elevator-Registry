package bg.softuni.Elevator.Registryregister.service.impl;

import bg.softuni.Elevator.Registryregister.model.dto.AddElevatorDTO;
import bg.softuni.Elevator.Registryregister.model.dto.ElevatorDetailsDTO;
import bg.softuni.Elevator.Registryregister.model.dto.ElevatorListDTO;
import bg.softuni.Elevator.Registryregister.model.entity.Elevator;
import bg.softuni.Elevator.Registryregister.model.entity.User;
import bg.softuni.Elevator.Registryregister.model.user.AppUserDetails;
import bg.softuni.Elevator.Registryregister.repository.ElevatorRepository;
import bg.softuni.Elevator.Registryregister.repository.UserRepository;
import bg.softuni.Elevator.Registryregister.service.AppUserDetailsService;
import bg.softuni.Elevator.Registryregister.service.ElevatorService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ElevatorServiceImpl implements ElevatorService {

    private final ElevatorRepository elevatorRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public ElevatorServiceImpl(ElevatorRepository elevatorRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.elevatorRepository = elevatorRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void AddNewElevator(AddElevatorDTO addElevatorDTO) {
        elevatorRepository.save(map(addElevatorDTO));
    }

    private Elevator map(AddElevatorDTO addElevatorDTO) {
        Elevator mappedEntity = modelMapper.map(addElevatorDTO, Elevator.class);
//        mappedEntity.setAuthor(userRepository.getReferenceById());

        //TODO:
        //        mappedEntity.setAuthor(appUserDetailsService.getUserById());
        return mappedEntity;
    }

    public List<ElevatorListDTO> getAllElevators() {
        return elevatorRepository
                .findAll()
                .stream()
                .map(ElevatorServiceImpl::toAllElevator)
                .toList();
    }

    @Override
    public void deleteElevator(Long id) {
       elevatorRepository.deleteById(id);
    }

    @Override
    public ElevatorDetailsDTO getElevatorDetails(Long id) {
        return modelMapper.map(elevatorRepository.getReferenceById(id), ElevatorDetailsDTO.class);
    }

    @Override
    public void editElevator(Long id, ElevatorDetailsDTO elevatorDetailsDTO) {
        Elevator elevator = elevatorRepository.getReferenceById(id);

        elevator = modelMapper.map(elevatorDetailsDTO, Elevator.class);

        elevatorRepository.save(elevator);
    }

    private static ElevatorListDTO toAllElevator (Elevator elevator) {
        return new ElevatorListDTO(
                elevator.getId(),
                elevator.getType(),
                elevator.getManufacturer(),
                elevator.getSpeed(),
                elevator.getNumberOfStops(),
                elevator.getAuthor()
        );

    }

}
