package service.impl;

import bg.softuni.ElevatorRegister.model.entity.Elevator;
import bg.softuni.ElevatorRegister.repository.ElevatorRepository;
import bg.softuni.ElevatorRegister.service.impl.ElevatorServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ElevatorServiceImplTest {

    private ElevatorServiceImpl elevatorService;

    @Mock
    private ElevatorRepository mockElevatorRepository;

    @Test
    void getElevatorDetails() {
        Elevator testElevator = new Elevator();
        testElevator.setCity("Burgas");
        testElevator.setAddress("Burgaska");
        testElevator.setManufacturer("Estikamet");
        testElevator.setManufacturerNumber("AS1");
        testElevator.setSpeed(1);
        testElevator.setNumberOfStops(8);

        when(mockElevatorRepository.findElevatorByManufacturerNumber("AS1"))
                .thenReturn(Optional.of(testElevator));

        Optional<Elevator> optionalElevator = mockElevatorRepository.findElevatorByManufacturerNumber("AS1");

        Assertions.assertTrue(optionalElevator.isPresent());

        Elevator elevator = optionalElevator.get();

        Assertions.assertEquals("Burgas", elevator.getCity());
        Assertions.assertEquals("Burgaska", elevator.getAddress());
        Assertions.assertEquals("Estikamet", elevator.getManufacturer());
        Assertions.assertEquals(1, elevator.getSpeed());
        Assertions.assertEquals(8, elevator.getNumberOfStops());
    }
}
