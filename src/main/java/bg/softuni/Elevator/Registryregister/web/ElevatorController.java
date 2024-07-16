package bg.softuni.Elevator.Registryregister.web;

import bg.softuni.Elevator.Registryregister.model.dto.ElevatorDTOs.AddElevatorDTO;
import bg.softuni.Elevator.Registryregister.model.dto.ElevatorDTOs.ElevatorDetailsDTO;
import bg.softuni.Elevator.Registryregister.service.ElevatorService;
import bg.softuni.Elevator.Registryregister.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/elevator")
public class ElevatorController {
    private final ElevatorService elevatorService;
    private final UserService userService;

    public ElevatorController(ElevatorService elevatorService, UserService userService) {
        this.elevatorService = elevatorService;
        this.userService = userService;
    }

    @GetMapping("/allElevators")
    public String allElevatorView (Model model) {

        model.addAttribute("allElevators", elevatorService.getAllElevators());

        return "allElevators";
    }


    @GetMapping("/addElevator")
    public String addElevatorView() {
        return "addElevator";
    }

    @PostMapping("/addElevator")
    public String addElevator(@AuthenticationPrincipal UserDetails userDetails, AddElevatorDTO addElevatorDTO) {
        elevatorService.AddNewElevator(addElevatorDTO, userDetails);
        return "redirect:/elevator/allElevators";
    }

    @DeleteMapping("/{id}")
    public String deleteElevator(@PathVariable("id") Long id) {
        elevatorService.deleteElevator(id);
        return "redirect:/elevator/allElevator";
    }

    @GetMapping("/editElevator/{id}")
    public String editElevator(@PathVariable("id") Long id,
                               Model model) {
        model.addAttribute("elevatorDetails", elevatorService.getElevatorDetails(id));
        model.addAttribute("addedBy", elevatorService.findAuthorOnElevator(elevatorService.getElevatorDetails(id)));
        model.addAttribute("allUser", userService.getAllUsers());

        return "editElevator";
    }

    @PutMapping("/editElevator/{id}")
    public String editElevator(@PathVariable("id") Long id, ElevatorDetailsDTO elevatorDetailsDTO) {

        elevatorService.editElevator(id, elevatorDetailsDTO);

        return "redirect:/elevator/allElevators";
    }



}
