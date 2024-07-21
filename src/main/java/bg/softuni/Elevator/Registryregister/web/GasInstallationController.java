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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gas")
public class GasInstallationController {

    private final GasInstallationService gasInstallationService;
    private final CustomerService customerService;

    public GasInstallationController(GasInstallationService gasInstallationService, CustomerController customerController, CustomerService customerService) {
        this.gasInstallationService = gasInstallationService;
        this.customerService = customerService;
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
}
