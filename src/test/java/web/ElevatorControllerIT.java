package web;

import bg.softuni.ElevatorRegister.ElevatorRegisterApplication;
import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.AddElevatorDTO;
import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.ElevatorDetailsDTO;
import bg.softuni.ElevatorRegister.model.entity.Elevator;
import bg.softuni.ElevatorRegister.model.entity.ElevatorType;

import bg.softuni.ElevatorRegister.repository.ElevatorRepository;
import bg.softuni.ElevatorRegister.service.ElevatorService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest(classes = ElevatorRegisterApplication.class)
@AutoConfigureMockMvc
@WithMockUser(
        username = "plamen",
        roles = {"USER", "ADMIN"}
)
public class ElevatorControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ElevatorService elevatorService;

    @Autowired
    private ElevatorRepository elevatorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testViewAddElevator() throws Exception {
        AddElevatorDTO addElevatorDTO = new AddElevatorDTO();

        mockMvc.perform(get("/elevator/addElevator"))
                .andExpect(status().isOk())
                .andExpect(view().name("addElevator"));
    }

    @Test
    public void testAddElevator() throws Exception {

        mockMvc.perform(post("/elevator/addElevator")
                .param("manufacturer", "AS Sofia AD5" )
                .param("speed", "1")
                .param("city", "Burgas")
                .param("address", "burgaska")
                .param("numberOfStops", "2")
                .param("registerDate", "2024-05-05")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/elevator/allElevators"));
    }

    @Test
    public void testDeleteElevator() throws Exception {

        mockMvc.perform(delete("/elevator/{id}", 1l)
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/elevator/allElevator"));
    }

    @Test
    public void testViewAllElevators() throws Exception {

        mockMvc.perform(get("/elevator/allElevators")
                    .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("allElevators"));
    }

    @Test
    @WithMockUser(username = "plamen", roles = {"USER", "ADMIN"})
    public void testEditElevator() throws Exception {
        ElevatorDetailsDTO elevatorDetailsDTO = new ElevatorDetailsDTO();
        elevatorDetailsDTO.setType(ElevatorType.ПХАС);
        elevatorDetailsDTO.setManufacturer("AS Sofia AD");
        elevatorDetailsDTO.setSpeed(1);
        elevatorDetailsDTO.setCity("Burgas");
        elevatorDetailsDTO.setAddress("burgaska");
        elevatorDetailsDTO.setNumberOfStops(2);
        elevatorDetailsDTO.setRegisterDate(LocalDate.of(2024,05,05));
        elevatorDetailsDTO.setAuthor("plamen");

        Elevator elevator = modelMapper.map(elevatorDetailsDTO, Elevator.class);
        elevator.setId(1l);
        elevatorRepository.save(elevator);

        mockMvc.perform(put("/elevator/editElevator/{id}", 1l)
                        .param("type", "ПХАС")
                        .param("manufacturer", "AS Sofia AD" )
                        .param("speed", "2")
                        .param("city", "Burgas")
                        .param("address", "burgaskata")
                        .param("numberOfStops", "2")
                        .param("registerDate", "2024-06-05")
                        .param("author", "plamen")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/elevator/allElevators"));

        elevatorRepository.deleteAll();
    }
}
