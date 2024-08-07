package bg.softuni.ElevatorRegister.web;

import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.AddElevatorDTO;
import bg.softuni.ElevatorRegister.model.dto.ElevatorDTOs.ElevatorDetailsDTO;
import bg.softuni.ElevatorRegister.service.CustomerService;
import bg.softuni.ElevatorRegister.service.ElevatorService;
import bg.softuni.ElevatorRegister.service.UserService;
import bg.softuni.ElevatorRegister.service.exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/elevator")
public class ElevatorController {
    private final ElevatorService elevatorService;
    private final UserService userService;
    private final CustomerService customerService;

    @ModelAttribute("addElevatorDTO")
    public AddElevatorDTO addElevatorDTO() {
        return AddElevatorDTO.empty();
    }

    public ElevatorController(ElevatorService elevatorService, UserService userService, CustomerService customerService) {
        this.elevatorService = elevatorService;
        this.userService = userService;
        this.customerService = customerService;
    }

    @GetMapping("/allElevators")
    public String allElevatorView (Model model) {

        model.addAttribute("allElevators", elevatorService.getAllElevators());

        return "all-elevators";
    }


    @GetMapping("/addElevator")
    public String addElevatorView(Model model) {

        model.addAttribute("allCustomers", customerService.getAllCustomers());

        return "add-elevator";
    }

    @PostMapping("/addElevator")
    public String addElevator(@AuthenticationPrincipal UserDetails userDetails,
                              @Valid AddElevatorDTO addElevatorDTO,
                              BindingResult bindingResult,
                              RedirectAttributes rAtt) {

        if(bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("addElevatorDTO", addElevatorDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addElevatorDTO", bindingResult);

            return "redirect:/elevator/addElevator";
        }

        elevatorService.AddNewElevator(addElevatorDTO, userDetails);

        return "redirect:/elevator/allElevators";
    }

    @DeleteMapping("/{id}")
    public String deleteElevator(@PathVariable("id") Long id) {
        elevatorService.deleteElevator(id);
        return "redirect:/elevator/allElevators";
    }

    @GetMapping("/editElevator/{id}")
    public String editElevator(@PathVariable("id") Long id,
                               Model model) {
        model.addAttribute("elevatorDetails", elevatorService.getElevatorDetails(id));
        model.addAttribute("addedBy", elevatorService.findAuthorOnElevator(elevatorService.getElevatorDetails(id)));
        model.addAttribute("allCustomers", customerService.getAllCustomers());
        model.addAttribute("allUser", userService.getAllUsers());

        return "edit-elevator";
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleObjectNotFound(ObjectNotFoundException onfe) {
        ModelAndView modelAndView = new ModelAndView("elevator-not-found");
        modelAndView.addObject("id", onfe.getId());

        return modelAndView;
    }

    @PutMapping("/editElevator/{id}")
    public String editElevator(@PathVariable("id") Long id, ElevatorDetailsDTO elevatorDetailsDTO) {

        elevatorService.editElevator(id, elevatorDetailsDTO);

        return "redirect:/elevator/allElevators";
    }

}
