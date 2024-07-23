package bg.softuni.PSIGAS.web;

import bg.softuni.PSIGAS.model.dto.InspectionDTOs.AddInspectionDTO;
import bg.softuni.PSIGAS.service.CustomerService;
import bg.softuni.PSIGAS.service.ElevatorService;
import bg.softuni.PSIGAS.service.InspectionService;
import bg.softuni.PSIGAS.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inspection")
public class InspectionController {
    private final InspectionService inspectionService;
    private final CustomerService customerService;
    private final ElevatorService elevatorService;
    private final UserService userService;

    public InspectionController(InspectionService inspectionService, CustomerService customerService, ElevatorService elevatorService, UserService userService) {
        this.inspectionService = inspectionService;
        this.customerService = customerService;
        this.elevatorService = elevatorService;
        this.userService = userService;
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
        model.addAttribute("allUser", userService.getAllUsers());
        model.addAttribute("inspectionDetails", inspectionService.getInspectionDetails(id));

        return "editInspection";
    }

    @PutMapping("/editInspection/{id}")
    public String editInspection(@PathVariable("id") Long id, AddInspectionDTO addInspectionDTO) {

        inspectionService.editInspection(id, addInspectionDTO);

        return "redirect:/inspection/allInspections";
    }

    @DeleteMapping("/{id}")
    public String deleteInspection(@PathVariable("id") Long id) {

        inspectionService.deleteInspection(id);

        return "redirect:/inspection/allInspections";
    }
}
