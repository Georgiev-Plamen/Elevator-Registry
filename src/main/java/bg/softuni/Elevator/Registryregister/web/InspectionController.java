package bg.softuni.Elevator.Registryregister.web;

import bg.softuni.Elevator.Registryregister.model.dto.InspectionDTOs.AddInspectionDTO;
import bg.softuni.Elevator.Registryregister.service.CustomerService;
import bg.softuni.Elevator.Registryregister.service.ElevatorService;
import bg.softuni.Elevator.Registryregister.service.InspectionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inspection")
public class InspectionController {
    private final InspectionService inspectionService;
    private final CustomerService customerService;
    private final ElevatorService elevatorService;

    public InspectionController(InspectionService inspectionService, CustomerService customerService, ElevatorService elevatorService) {
        this.inspectionService = inspectionService;
        this.customerService = customerService;
        this.elevatorService = elevatorService;
    }

    @GetMapping("/allInspections")
    public String allInspections (Model model) {

        model.addAttribute("allInspections", inspectionService.getAllInspections());

        return "allInspections";
    }

    @GetMapping("/addInspection")
    public String addInspection(Model model) {

        model.addAttribute("allCustomers", customerService.getAllCustomers());
        model.addAttribute("allElevators", elevatorService.getAllElevators());

        return "addInspection";
    }
    @PostMapping("/addInspection")
    public String addInspection (@AuthenticationPrincipal UserDetails userDetails, AddInspectionDTO addInspectionDTO ) {
        inspectionService.addNewInspection(addInspectionDTO, userDetails);

        return "redirect:/inspection/allInspections";
    }

    @PostMapping("/markAsDone/{id}")
    public String markAsDone (@PathVariable("id") Long id) {

        inspectionService.markAsDone(id);

        return "redirect:/inspection/allInspections";
    }

    @GetMapping("/editInspection/{id}")
    public String editInspection(@PathVariable("id") Long id, Model model) {

        model.addAttribute("allCustomers", customerService.getAllCustomers());
        model.addAttribute("allElevators", elevatorService.getAllElevators());
        model.addAttribute("inspectionDetails", inspectionService.getInspectionDetails(id));

        return "editInspection";
    }

    @PostMapping("/editInspection/{id}")
    public String editInspection(@PathVariable("id") Long id, AddInspectionDTO addInspectionDTO) {

        inspectionService.editInspection(id, addInspectionDTO);

        return "redirect:/inspection/allInspections";
    }
}
