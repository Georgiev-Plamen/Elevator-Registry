package bg.softuni.Elevator.Registryregister.service.impl;

import bg.softuni.Elevator.Registryregister.model.dto.AddElevatorDTO;
import bg.softuni.Elevator.Registryregister.model.dto.ElevatorDetailsDTO;
import bg.softuni.Elevator.Registryregister.model.dto.ElevatorListDTO;
import bg.softuni.Elevator.Registryregister.model.entity.Elevator;
import bg.softuni.Elevator.Registryregister.repository.ElevatorRepository;
import bg.softuni.Elevator.Registryregister.repository.UserRepository;
import bg.softuni.Elevator.Registryregister.service.AppUserDetailsService;
import bg.softuni.Elevator.Registryregister.service.ElevatorService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElevatorServiceImpl implements ElevatorService {

    private final ElevatorRepository elevatorRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final AppUserDetailsService appUserDetailsService;

    public ElevatorServiceImpl(ElevatorRepository elevatorRepository, ModelMapper modelMapper, UserRepository userRepository, AppUserDetailsService appUserDetailsService) {
        this.elevatorRepository = elevatorRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.appUserDetailsService = appUserDetailsService;
    }

    @Override
    public void AddNewElevator(AddElevatorDTO addElevatorDTO, UserDetails userDetails) {
        elevatorRepository.save(map(addElevatorDTO, userDetails));
    }

    private Elevator map(AddElevatorDTO addElevatorDTO, UserDetails userDetails) {
        Elevator mappedEntity = modelMapper.map(addElevatorDTO, Elevator.class);
        mappedEntity.setAuthor(userRepository.findByUsername(userDetails.getUsername()).get());
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
//        elevator.setType(elevatorDetailsDTO.getType());
//        elevator.setManufacturer(elevatorDetailsDTO.getManufacturer());
//        elevator.setManufacturer(elevatorDetailsDTO.getManufacturerNumber());
//        elevator.setYearOfManufacture(elevatorDetailsDTO.getYearOfManufacture());
//        elevator.setSpeed(elevatorDetailsDTO.getSpeed());
//        elevator.setNumberOfStops(elevatorDetailsDTO.getNumberOfStops());
//        elevator.setDamtnDate(elevatorDetailsDTO.getDamtnDate());
//        elevator.setDamtnNumber(elevatorDetailsDTO.getDamtnNumber());
//        elevator.setFirstCheck(elevatorDetailsDTO.getFirstCheck());

//        elevator.setAuthor(userRepository.findByUsername(elevatorDetailsDTO.getAuthor().getUsername()).get());

        elevatorRepository.save(elevator);
    }

    private static ElevatorListDTO toAllElevator (Elevator elevator) {
        return new ElevatorListDTO(
                elevator.getId(),
                elevator.getType(),
                elevator.getManufacturer(),
                elevator.getSpeed(),
                elevator.getNumberOfStops(),
                elevator.getFirstCheck()
        );

    }

}
