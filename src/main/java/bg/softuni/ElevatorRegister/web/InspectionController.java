package bg.softuni.ElevatorRegister.web;

import bg.softuni.ElevatorRegister.model.dto.InspectionDTOs.AddInspectionDTO;
import bg.softuni.ElevatorRegister.model.entity.Customer;
import bg.softuni.ElevatorRegister.service.CustomerService;
import bg.softuni.ElevatorRegister.service.ElevatorService;
import bg.softuni.ElevatorRegister.service.InspectionService;
import bg.softuni.ElevatorRegister.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/addInspection/{id}")
    public String addInspection(Model model,
                                @PathVariable("id") Long id) {

        model.addAttribute("customer", customerService.getCustomerById(id));
        model.addAttribute("customerElevators", elevatorService.getAllCustomerElevator(id));

        return "addInspection";
    }
    @PostMapping("/addInspection")
    public String addInspection (@AuthenticationPrincipal UserDetails userDetails,
                                 AddInspectionDTO addInspectionDTO) {
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

        model.addAttribute("customers", inspectionService.getInspectionDetails(id).getCustomer());
        model.addAttribute("allElevators", inspectionService.getAllElevatorsOfInspection(id));
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

    @GetMapping("/createMultiInspection/{id}")
    public String createMultiInspection(@PathVariable("id") Long id, Model model) {

        model.addAttribute("customerElevators", elevatorService.getAllCustomerElevator(id));
        model.addAttribute("customer", customerService.getCustomerById(id));

        return "customerElevators";
    }
    @PostMapping("/createMultiInspection")
    public String addToInspection (@RequestParam("options") List<String> values,
                                   @AuthenticationPrincipal UserDetails userDetails,
                                   Long customer) {

        List<Long> elevatorsID = values.stream().map(v -> Long.parseLong(v)).toList();
       inspectionService.createMultiplyInspection(elevatorsID, userDetails, customer);

       return "redirect:/inspection/allInspections";
    }

    @GetMapping("/selectCustomer")
    public String selectCustomer (
            Model model) {
        model.addAttribute("allCustomer", customerService.getAllCustomers());

        return "selectCustomer";
    }

    @PostMapping("/selectCustomer")
    public String selectCustomer (){

        return "redirect:/addInspection";
    }

}
