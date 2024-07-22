package bg.softuni.Elevator.Registryregister.web;

import bg.softuni.Elevator.Registryregister.model.dto.ElevatorDTOs.AddElevatorDTO;
import bg.softuni.Elevator.Registryregister.model.dto.GasInstallationDTOs.AddGasInstallationDTO;
import bg.softuni.Elevator.Registryregister.model.dto.GasInstallationDTOs.GasInstallationDTO;
import bg.softuni.Elevator.Registryregister.service.CustomerService;
import bg.softuni.Elevator.Registryregister.service.GasInstallationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gas")
public class GasInstallationController {

    private final GasInstallationService gasInstallationService;
    private final CustomerService customerService;

    public GasInstallationController(GasInstallationService gasInstallationService, CustomerController customerController, CustomerService customerService) {
        this.gasInstallationService = gasInstallationService;
        this.customerService = customerService;
    }

    @GetMapping("/editGasInstallation/{id}")
    public String editGasInstallation(@PathVariable("id") Long id,
                                      Model model) {
        model.addAttribute("gasInstallationDetails", gasInstallationService.getGasInstallationDetails(id));

        return "editGasInstallation";
    }

    @PutMapping("/editGasInstallation/{id}")
    public String editGasInstallation(@PathVariable("id") Long id, GasInstallationDTO gasInstallationDTO) {
        gasInstallationService.editGasInstallation(id, gasInstallationDTO);

        return "redirect:/gas/allGasInstallations";
    }
    @GetMapping("/allGasInstallations")
    public String allGasInstallations (Model model) {
        model.addAttribute("allGasInstallations", gasInstallationService.getAllGasInstallation());

        return "allGasInstallations";
    }

    @GetMapping("/addGasInstallation")
        public String addGasInstallation (Model model) {
        model.addAttribute("allCustomers", customerService.getAllCustomers());

        return "addGasInstallation";
    }

    @PostMapping("/addGasInstallation")
    public String addGasInstallation(AddGasInstallationDTO addGasInstallationDTO) {
        gasInstallationService.addGasInstallation(addGasInstallationDTO);

        return "index";
    }

    @DeleteMapping("/{id}")
    public String deleteGasInstallation(@PathVariable("id") Long id) {
        gasInstallationService.deleteGasInstallationById(id);

        return "redirect:/gas/allGasInstallations";
    }
}
