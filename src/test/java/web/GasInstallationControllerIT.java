package web;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import bg.softuni.ElevatorRegister.service.GasInstallationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class GasInstallationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    // Use only if really needed.
    @MockBean
    private GasInstallationService mockGasInstallationService;

    @Test
    public void testGasInstallation() throws Exception {
        long id = 2;
        String damtnNumber = "1asd";
        String manufacturer = "asd";
        String power = "2";

        when(mockGasInstallationService.getGasInstallationDetails(id));

        mockMvc.perform(get("/api/gasInstallation")
                        .param("damtnNumber", damtnNumber)
                        .param("manufacturer", manufacturer)
                        .param("power", power)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.damtnNumber").value(damtnNumber))
                .andExpect(jsonPath("$.manufacturer").value(manufacturer))
                .andExpect(jsonPath("$.manufacturer").value(manufacturer))
                .andExpect(jsonPath("$.power").value(power));
    }
}
//
////    @Test
////    public void testConversionNotFound() throws Exception {
////        String from = "SUD";
////        String to = "ZWD";
////        BigDecimal amount = new BigDecimal("100");
////
////        when(mockExRateService.convert(from, to, amount))
////                .thenThrow(new ApiObjectNotFoundException("Test message", "TestId"));
////
////        mockMvc.perform(get("/api/convert")
////                .param("from", from)
////                .param("to", to)
////                .param("amount", String.valueOf(amount.intValue()))
////        ).andExpect(status().isNotFound());
////    }
//
//}
