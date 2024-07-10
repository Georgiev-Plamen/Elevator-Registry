package bg.softuni.Elevator.Registryregister.service.impl;

import bg.softuni.Elevator.Registryregister.model.dto.AddElevatorDTO;
import bg.softuni.Elevator.Registryregister.model.entity.Elevator;
import bg.softuni.Elevator.Registryregister.repository.ElevatorRepository;
import bg.softuni.Elevator.Registryregister.service.ElevatorService;
import org.modelmapper.ModelMapper;

public class ElevatorServiceImpl implements ElevatorService {

    private final ElevatorRepository elevatorRepository;
    private final ModelMapper modelMapper;

    public ElevatorServiceImpl(ElevatorRepository elevatorRepository, ModelMapper modelMapper) {
        this.elevatorRepository = elevatorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void AddNewElevator(AddElevatorDTO addElevatorDTO) {
        elevatorRepository.save(map(addElevatorDTO));
    }

    private Elevator map(AddElevatorDTO addElevatorDTO) {
        Elevator mappedEntity = modelMapper.map(addElevatorDTO, Elevator.class);
        return mappedEntity;
    }


}
