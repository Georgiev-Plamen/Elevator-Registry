package web;

import bg.softuni.ElevatorRegister.ElevatorRegisterApplication;
import bg.softuni.ElevatorRegister.model.entity.Elevator;
import bg.softuni.ElevatorRegister.repository.InspectionRepository;
import bg.softuni.ElevatorRegister.service.InspectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(classes = ElevatorRegisterApplication.class)
@AutoConfigureMockMvc
@WithMockUser(
        username = "plamen",
        roles = {"USER", "ADMIN"}
)
public class InspectionControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InspectionRepository inspectionRepository;

    @Autowired
    private InspectionService inspectionService;

    @Test
    public void testViewAllInspection() throws Exception {

        mockMvc.perform(get("/inspection/allInspections")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("all-inspections"));
    }

    @Test
    public void testDeleteInspection() throws Exception {

        mockMvc.perform(delete("/inspection/{id}", 1)
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/inspection/allInspections"));
    }

}
