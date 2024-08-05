package bg.softuni.ElevatorRegister.service.impl;

import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.AddElevatorDTO;
import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.CustomerElevatorDTO;
import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.ElevatorDetailsDTO;
import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.ElevatorListDTO;
import bg.softuni.ElevatorRegister.model.entity.Elevator;
import bg.softuni.ElevatorRegister.repository.ElevatorRepository;
import bg.softuni.ElevatorRegister.repository.UserRepository;
import bg.softuni.ElevatorRegister.service.AppUserDetailsService;
import bg.softuni.ElevatorRegister.service.ElevatorService;
import bg.softuni.ElevatorRegister.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ElevatorServiceImpl implements ElevatorService {

    private final ElevatorRepository elevatorRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final AppUserDetailsService appUserDetailsService;
    private final List<ElevatorListDTO> elevators;


    public ElevatorServiceImpl(ElevatorRepository elevatorRepository, ModelMapper modelMapper, UserRepository userRepository, AppUserDetailsService appUserDetailsService, List<Elevator> elevators) {
        this.elevatorRepository = elevatorRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.appUserDetailsService = appUserDetailsService;
        this.elevators = new ArrayList<>();
    }


    @Override
    public void AddNewElevator(AddElevatorDTO addElevatorDTO, UserDetails userDetails) {
        elevatorRepository.save(map(addElevatorDTO, userDetails));
    }

    private Elevator map(AddElevatorDTO addElevatorDTO, UserDetails userDetails) {

        Elevator mappedEntity = modelMapper.map(addElevatorDTO, Elevator.class);
        mappedEntity.setLastInspection(addElevatorDTO.getRegisterDate());
        mappedEntity.setNextInspection(mappedEntity.getLastInspection().plusYears(1));
        mappedEntity.setAuthor(userRepository.findByUsername(userDetails.getUsername()).get());

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
        return modelMapper
                .map(elevatorRepository.getReferenceById(id), ElevatorDetailsDTO.class);
    }


    @Override
    public void editElevator(Long id, ElevatorDetailsDTO elevatorDetailsDTO) {
        Elevator elevator = elevatorRepository.getReferenceById(id);
        elevator = modelMapper.map(elevatorDetailsDTO, Elevator.class);
        elevator.setAuthor(userRepository.findByUsername(elevatorDetailsDTO.getAuthor()).get());

        elevatorRepository.save(elevator);
    }

    @Override
    public String findAuthorOnElevator(ElevatorDetailsDTO elevatorDetailsDTO) {
        Elevator elevator = elevatorRepository.findById(elevatorDetailsDTO.getId()).get();
        String author = elevator.getAuthor().getUsername();

        return author;
    }

    @Override
    public List<ElevatorListDTO> getElevatorsDetails() {
        return elevatorRepository
                .findAll()
                .stream()
                .map(ElevatorServiceImpl::toAllElevator)
                .toList();
    }

    @Override
    public List<CustomerElevatorDTO> getAllCustomerElevator(Long id) throws ObjectNotFoundException {
        return elevatorRepository
                .findAllByOwnerId(id)
                .stream()
                .map(ElevatorServiceImpl::toAllCustomerElevators)
                .toList();
    }

    private static CustomerElevatorDTO toAllCustomerElevators (Elevator elevator) {
        return new CustomerElevatorDTO(
                elevator.getId(),
                elevator.getType(),
                elevator.getManufacturer(),
                elevator.getManufacturerNumber(),
                elevator.getYearOfManufacture(),
                elevator.getSpeed(),
                elevator.getNumberOfStops(),
                elevator.getCity(),
                elevator.getAddress(),
                elevator.getRegisterDate(),
                elevator.getLastInspection(),
                elevator.getNextInspection()
        );
    }

    private static ElevatorListDTO toAllElevator (Elevator elevator) {
        return new ElevatorListDTO(
                elevator.getId(),
                elevator.getOwner(),
                elevator.getType(),
                elevator.getManufacturer(),
                elevator.getAddress(),
                elevator.getSpeed(),
                elevator.getNumberOfStops(),
                elevator.getRegisterDate(),
                elevator.getLastInspection(),
                elevator.getNextInspection(),
                elevator.getAuthor().getUsername()
        );
    }

    public List<ElevatorListDTO> getTop5ElevatorsByNextInspection() {
        return elevatorRepository.findAll().stream()
                .sorted(Comparator.comparing(Elevator::getNextInspection))
                .map(ElevatorServiceImpl::toAllElevator)
                .limit(5)
                .collect(Collectors.toList());
    }


}
