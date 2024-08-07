package web;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import bg.softuni.ElevatorRegister.ElevatorRegisterApplication;
import bg.softuni.ElevatorRegister.model.dto.GasInstallationDTOs.GasInstallationDTO;
import bg.softuni.ElevatorRegister.service.GasInstallationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = ElevatorRegisterApplication.class)
@AutoConfigureMockMvc
@WithMockUser(
        username = "plamen",
        roles = {"USER", "ADMIN"}
)
public class GasInstallationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GasInstallationService mockGasInstallationService;

    @Test
    public void testAllGasInstallations() throws Exception {
        GasInstallationDTO gasInstallation1 = new GasInstallationDTO(
                1L, "1234", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1),
                "gas", "Manufacturer1", "Model1", "Pressure1", "Power1",  1l);

        GasInstallationDTO gasInstallation2 = new GasInstallationDTO(
                2L, "5678", LocalDate.of(2023, 2, 1), LocalDate.of(2023, 2, 1),
                "gas", "Manufacturer2", "Model2", "Pressure2", "Power2",2L);

        List<GasInstallationDTO> gasInstallations = Arrays.asList(gasInstallation1, gasInstallation2);

        when(mockGasInstallationService.getAllGasInstallation()).thenReturn(gasInstallations);

        mockMvc.perform(get("/gas/allGasInstallations"))
                .andExpect(status().isOk())
                .andExpect(view().name("all-gas-installations"))
                .andExpect(model().attribute("allGasInstallations", gasInstallations));
    }

    @Test
    public void testDeleteGasInstallation() throws Exception {
        Long gasInstallationId = 9999L;

        mockMvc.perform(delete("/gas/{id}", gasInstallationId)
                        .with(csrf())) // Add CSRF token to simulate a real DELETE request
                .andExpect(status().is3xxRedirection()) // Expect a redirection after successful DELETE
                .andExpect(redirectedUrl("/gas/allGasInstallations")); // Expect redirection to allGasInstallations URL

        // Verify that the service method was called with the correct parameter
        verify(mockGasInstallationService).deleteGasInstallationById(gasInstallationId);
    }
}
