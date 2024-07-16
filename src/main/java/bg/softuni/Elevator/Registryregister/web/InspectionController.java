package bg.softuni.Elevator.Registryregister.web;

import bg.softuni.Elevator.Registryregister.model.dto.InspectionDTOs.AddInspectionDTO;
import bg.softuni.Elevator.Registryregister.service.InspectionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inspection")
public class InspectionController {
    private final InspectionService inspectionService;

    public InspectionController(InspectionService inspectionService) {
        this.inspectionService = inspectionService;
    }

    @GetMapping("/allInspection")
    public String allInspection (Model model) {
        model.addAttribute("allInspection", inspectionService.getAllInspections());

        return "allInspection";
    }

    @GetMapping("/addInspection")
    public String addInspection() {
        return "addInspection";
    }
    @PostMapping("/addInspection")
    public String addInspection (@AuthenticationPrincipal UserDetails userDetails, AddInspectionDTO addInspectionDTO ) {
        inspectionService.addNewInspection(addInspectionDTO, userDetails);

        return "redirect:/inspection/allInspection";
    }
}
