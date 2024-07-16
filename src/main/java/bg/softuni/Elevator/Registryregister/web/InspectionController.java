package bg.softuni.Elevator.Registryregister.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inspection")
public class InspectionController {

    @GetMapping("/allInspection")
    public String allInspection (Model model) {
        model.addAttribute("allInspection", inspectionService.getAllInspections());

        return "allInspection";
    }
}
