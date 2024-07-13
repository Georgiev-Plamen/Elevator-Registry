package bg.softuni.Elevator.Registryregister.web;

import bg.softuni.Elevator.Registryregister.model.dto.AddElevatorDTO;
import bg.softuni.Elevator.Registryregister.model.dto.ElevatorDetailsDTO;
import bg.softuni.Elevator.Registryregister.service.ElevatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/elevator")
public class ElevatorController {
    private final ElevatorService elevatorService;

    public ElevatorController(ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
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
    public String addElevator(AddElevatorDTO addElevatorDTO) {
        elevatorService.AddNewElevator(addElevatorDTO);
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

        return "editElevator";
    }

    @PutMapping("/editElevator/{id}")
    public String editElevator(@PathVariable("id") Long id, ElevatorDetailsDTO elevatorDetailsDTO) {

        elevatorService.editElevator(id, elevatorDetailsDTO);

        return "redirect:/elevator/allElevators";
    }



}