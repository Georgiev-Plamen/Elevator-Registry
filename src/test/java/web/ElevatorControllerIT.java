//package web;
//
//import bg.softuni.ElevatorRegister.ElevatorRegisterApplication;
//import bg.softuni.ElevatorRegister.model.entity.Elevator;
//import bg.softuni.ElevatorRegister.repository.ElevatorRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Optional;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest(classes = ElevatorRegisterApplication.class)
//@AutoConfigureMockMvc
//@WithMockUser(
//        username = "plamen",
//        roles = {"USER", "ADMIN"}
//)
//public class ElevatorControllerIT {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ElevatorRepository elevatorRepository;
//
//    @Test
//    public void testElevatorAdd() throws Exception {
//
//        mockMvc.perform(post("/elevator/addElevator")
//                .param("manufacturer", "AS Sofia")
//                .param("manufacturerNumber", "AS1")
//                .param("yearOfManufacture", "2000")
//                .param("speed", "1")
//                .param("numberOfStops", "8")
//                .param("city", "Burgas")
//                .param("address", "Chataldza 28")
//                .with(csrf())
//                ).andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/elevator/allElevators"));
//
//        Optional<Elevator> optionalElevator = elevatorRepository.findElevatorByManufacturerNumber("AS1");
//
//        Assertions.assertTrue(optionalElevator.isPresent());
//
//        Elevator elevator = optionalElevator.get();
//
//
//        Assertions.assertEquals("manufacturer", elevator.getManufacturer());
//        Assertions.assertEquals("numberOfStops", elevator.getNumberOfStops());
//        Assertions.assertEquals("city", elevator.getNumberOfStops());
//
//    }
//}
