//package web;
//
//
//import static org.mockito.Mockito.when;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import bg.softuni.ElevatorRegister.ElevatorRegisterApplication;
//import bg.softuni.ElevatorRegister.service.GasInstallationService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//@SpringBootTest(classes = ElevatorRegisterApplication.class)
//@AutoConfigureMockMvc
//public class GasInstallationControllerIT {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//
//
//
//    @MockBean
//    private GasInstallationService mockGasInstallationService;
//
//
//    @Test
//    public void testGasInstallation() throws Exception {
//
//        mockMvc.perform(post("/gas/addGasInstallation")
//                        .param("id", "9999")
//                        .param("damtnNumber", "asd1")
//                        .param("type", "gas")
//                        .param("manufacturer", "Izida")
//                        .param("model", "IZ-2")
//                        .param("pressure", "2")
//                        .with(csrf())
//                ).andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/"));
//
//        when(mockGasInstallationService.getGasInstallationDetails(9999L))
//
//    }
//}
