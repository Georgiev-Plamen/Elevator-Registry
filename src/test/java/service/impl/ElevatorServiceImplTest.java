package service.impl;

import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.CustomerElevatorDTO;
import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.ElevatorDetailsDTO;
import bg.softuni.ElevatorRegister.model.entity.Elevator;
import bg.softuni.ElevatorRegister.model.entity.ElevatorType;
import bg.softuni.ElevatorRegister.model.entity.User;
import bg.softuni.ElevatorRegister.repository.ElevatorRepository;
import bg.softuni.ElevatorRegister.service.impl.ElevatorServiceImpl;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ElevatorServiceImplTest {

    @InjectMocks
    private ElevatorServiceImpl elevatorService;

    @Mock
    private ElevatorRepository mockElevatorRepository;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

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

    @Test
    public void testGetAllCustomerElevator() {
        // Arrange
        Long ownerId = 1L;

        Elevator elevator1 = new Elevator();
        elevator1.setId(1L);
        elevator1.setType(ElevatorType.ПХАС);
        elevator1.setManufacturer("AS Sofia");
        elevator1.setManufacturerNumber("12345");
        elevator1.setYearOfManufacture("2020");
        elevator1.setSpeed(3);
        elevator1.setNumberOfStops(10);
        elevator1.setCity("Sofia");
        elevator1.setAddress("Sofiiska");
        elevator1.setRegisterDate(LocalDate.of(2020, 1, 1));
        elevator1.setLastInspection(LocalDate.of(2021, 6, 15));
        elevator1.setNextInspection(LocalDate.of(2022, 6, 15));

        Elevator elevator2 = new Elevator();
        elevator2.setId(2L);
        elevator2.setType(ElevatorType.ПХАС);
        elevator2.setManufacturer("Kone");
        elevator2.setManufacturerNumber("54321");
        elevator2.setYearOfManufacture("2019");
        elevator2.setSpeed(2);
        elevator2.setNumberOfStops(8);
        elevator2.setCity("Plovdiv");
        elevator2.setAddress("plovdivska");
        elevator2.setRegisterDate(LocalDate.of(2019, 2, 2));
        elevator2.setLastInspection(LocalDate.of(2020, 7, 20));
        elevator2.setNextInspection(LocalDate.of(2021, 7, 20));

        List<Elevator> elevators = Arrays.asList(elevator1, elevator2);

        when(mockElevatorRepository.findAllByOwnerId(ownerId)).thenReturn(elevators);

        // Act
        List<CustomerElevatorDTO> result = elevatorService.getAllCustomerElevator(ownerId);

        // Assert
        assertEquals(2, result.size());

        CustomerElevatorDTO dto1 = result.get(0);
        assertEquals(1L, dto1.id());
        assertEquals(ElevatorType.ПХАС, dto1.type());
        assertEquals("AS Sofia", dto1.manufacturer());
        assertEquals("12345", dto1.manufacturerNumber());
        assertEquals("2020", dto1.yearOfManufacture());
        assertEquals(3, dto1.speed());
        assertEquals(10, dto1.numberOfStops());
        assertEquals("Sofia", dto1.city());
        assertEquals("Sofiiska", dto1.address());
        assertEquals(LocalDate.of(2020, 1, 1), dto1.registerDate());
        assertEquals(LocalDate.of(2021, 6, 15), dto1.lastInspection());
        assertEquals(LocalDate.of(2022, 6, 15), dto1.nextInspection());

        CustomerElevatorDTO dto2 = result.get(1);
        assertEquals(2L, dto2.id());
        assertEquals(ElevatorType.ПХАС, dto2.type());
        assertEquals("Kone", dto2.manufacturer());
        assertEquals("54321", dto2.manufacturerNumber());
        assertEquals("2019", dto2.yearOfManufacture());
        assertEquals(2, dto2.speed());
        assertEquals(8, dto2.numberOfStops());
        assertEquals("Plovdiv", dto2.city());
        assertEquals("plovdivska", dto2.address());
        assertEquals(LocalDate.of(2019, 2, 2), dto2.registerDate());
        assertEquals(LocalDate.of(2020, 7, 20), dto2.lastInspection());
        assertEquals(LocalDate.of(2021, 7, 20), dto2.nextInspection());
    }

    @Test
    public void testDeleteElevator() {
        Long elevatorId = 1L;
        elevatorService.deleteElevator(elevatorId);

        verify(mockElevatorRepository, times(1)).deleteById(elevatorId);
    }

    @Test
    public void testFindAuthorOnElevator() {

        Long elevatorId = 1L;
        String expectedUsername = "authorUsername";

        User author = new User();
        author.setUsername(expectedUsername);


        Elevator elevator = new Elevator();
        elevator.setId(elevatorId);
        elevator.setAuthor(author);

        when(mockElevatorRepository.findById(elevatorId)).thenReturn(Optional.of(elevator));

        ElevatorDetailsDTO elevatorDetailsDTO = new ElevatorDetailsDTO();
        elevatorDetailsDTO.setId(elevatorId);

        String result = elevatorService.findAuthorOnElevator(elevatorDetailsDTO);

        assertEquals(expectedUsername, result);
    }
}
