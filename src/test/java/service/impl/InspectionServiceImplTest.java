package service.impl;

import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.ElevatorDetailsDTO;
import bg.softuni.ElevatorRegister.model.dto.InspectionDTOs.AddInspectionDTO;
import bg.softuni.ElevatorRegister.model.entity.*;
import bg.softuni.ElevatorRegister.repository.CustomerRepository;
import bg.softuni.ElevatorRegister.repository.InspectionRepository;
import bg.softuni.ElevatorRegister.repository.UserRepository;
import bg.softuni.ElevatorRegister.service.impl.InspectionServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InspectionServiceImplTest {

    @InjectMocks
    private InspectionServiceImp inspectionService;

    @Mock
    private InspectionRepository inspectionRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testMarkAsDone() {
        Long inspectionId = 1L;
        LocalDate now = LocalDate.now();
        LocalDate nextInspectionDate = now.plusMonths(6);

        Elevator elevator1 = new Elevator();
        elevator1.setLastInspection(now.minusYears(1));
        elevator1.setNextInspection(nextInspectionDate);

        Elevator elevator2 = new Elevator();
        elevator2.setLastInspection(now.minusYears(2));
        elevator2.setNextInspection(nextInspectionDate);

        List<Elevator> elevators = new ArrayList<>();
        elevators.add(elevator1);
        elevators.add(elevator2);

        Inspection inspection = new Inspection();
        inspection.setElevators(elevators);
        inspection.setStatus(InspectionsStatus.ПРОЦЕС);

        when(inspectionRepository.findById(inspectionId)).thenReturn(Optional.of(inspection));

        inspectionService.markAsDone(inspectionId);

        for (Elevator elevator : elevators) {
            assertEquals(nextInspectionDate.plusYears(1), elevator.getNextInspection());
        }

        assertEquals(InspectionsStatus.ФИНАЛИЗИРАНА, inspection.getStatus());
    }

    @Test
    public void testDeleteInspection() {
        Long inspectionId = 1L;
        inspectionService.deleteInspection(inspectionId);

        verify(inspectionRepository, times(1)).deleteById(inspectionId);
    }

    @Test
    public void testEditInspection() {
        Long inspectionId = 1L;

        AddInspectionDTO addInspectionDTO = new AddInspectionDTO();
        List<Elevator> elevators = Arrays.asList(new Elevator(), new Elevator()); // List of mock elevators
        addInspectionDTO.setElevators(elevators);

        Inspection existingInspection = new Inspection();
        Inspection updatedInspection = new Inspection();

        when(inspectionRepository.findById(inspectionId)).thenReturn(Optional.of(existingInspection));
        when(modelMapper.map(addInspectionDTO, Inspection.class)).thenReturn(updatedInspection);

        inspectionService.editInspection(inspectionId, addInspectionDTO);

        verify(inspectionRepository, times(1)).findById(inspectionId);


        verify(modelMapper, times(1)).map(addInspectionDTO, Inspection.class);

        verify(inspectionRepository, times(1)).save(updatedInspection);
    }

    @Test
    public void testGetAllElevatorsOfInspection() {
        Long inspectionId = 1L;

        Elevator elevator1 = new Elevator();
        elevator1.setId(1L);
        elevator1.setType(ElevatorType.ПХАС);

        Elevator elevator2 = new Elevator();
        elevator2.setId(2L);
        elevator2.setType(ElevatorType.ПЕАС);

        List<Elevator> elevators = Arrays.asList(elevator1, elevator2);

        Inspection inspection = new Inspection();
        inspection.setElevators(elevators);

        ElevatorDetailsDTO dto1 = new ElevatorDetailsDTO();
        dto1.setId(1L);
        dto1.setType(ElevatorType.ПХАС);

        ElevatorDetailsDTO dto2 = new ElevatorDetailsDTO();
        dto2.setId(2L);
        dto2.setType(ElevatorType.ПЕАС);

        List<ElevatorDetailsDTO> expectedDtos = Arrays.asList(dto1, dto2);

        when(inspectionRepository.getReferenceById(inspectionId)).thenReturn(inspection);

        when(modelMapper.map(elevator1, ElevatorDetailsDTO.class)).thenReturn(dto1);
        when(modelMapper.map(elevator2, ElevatorDetailsDTO.class)).thenReturn(dto2);

        List<ElevatorDetailsDTO> result = inspectionService.getAllElevatorsOfInspection(inspectionId);

        verify(inspectionRepository, times(1)).getReferenceById(inspectionId);

        verify(modelMapper, times(1)).map(elevator1, ElevatorDetailsDTO.class);
        verify(modelMapper, times(1)).map(elevator2, ElevatorDetailsDTO.class);
        
        assertEquals(expectedDtos, result, "The list of ElevatorDetailsDTO should match the expected DTO list");
    }

}
