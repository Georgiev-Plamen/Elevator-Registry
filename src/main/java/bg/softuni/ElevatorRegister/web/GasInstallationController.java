package bg.softuni.ElevatorRegister.web;

import bg.softuni.ElevatorRegister.model.dto.GasInstallationDTOs.AddGasInstallationDTO;
import bg.softuni.ElevatorRegister.model.dto.GasInstallationDTOs.GasInstallationDTO;
import bg.softuni.ElevatorRegister.service.CustomerService;
import bg.softuni.ElevatorRegister.service.GasInstallationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/gas")
public class GasInstallationController {

    private final GasInstallationService gasInstallationService;
    private final CustomerService customerService;

    @ModelAttribute("gasData")
    public AddGasInstallationDTO addGasInstallationDTO() {
        return new AddGasInstallationDTO(null,null,null,null,null,null,null,null,null);
    }

    public GasInstallationController(GasInstallationService gasInstallationService, CustomerService customerService) {
        this.gasInstallationService = gasInstallationService;
        this.customerService = customerService;
    }

    @GetMapping("/editGasInstallation/{id}")
    public String editGasInstallation(@PathVariable("id") Long id,
                                      Model model) {
        model.addAttribute("gasInstallationDetails", gasInstallationService.getGasInstallationDetails(id));

        return "edit-gas-installation";
    }

    @PutMapping("/editGasInstallation/{id}")
    public String editGasInstallation(@PathVariable("id") Long id, GasInstallationDTO gasInstallationDTO) {
        gasInstallationService.editGasInstallation(id, gasInstallationDTO);

        return "redirect:/gas/allGasInstallations";
    }
    @GetMapping("/allGasInstallations")
    public String allGasInstallations (Model model) {
        model.addAttribute("allGasInstallations", gasInstallationService.getAllGasInstallation());

        return "all-gas-installations";
    }

    @GetMapping("/addGasInstallation")
        public String addGasInstallation (Model model) {

        model.addAttribute("allCustomers", customerService.getAllCustomers());

        return "add-gas-installation";
    }

    @PostMapping("/addGasInstallation")
    public String addGasInstallation(@Valid AddGasInstallationDTO addGasInstallationDTO,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("gasData", addGasInstallationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.gasData", bindingResult);

            return "redirect:/gas/addGasInstallation";
        }

        gasInstallationService.addGasInstallation(addGasInstallationDTO);

        return "redirect:/gas/allGasInstallations";
    }

    @DeleteMapping("/{id}")
    public String deleteGasInstallation(@PathVariable("id") Long id) {
        gasInstallationService.deleteGasInstallationById(id);

        return "redirect:/gas/allGasInstallations";
    }
}
