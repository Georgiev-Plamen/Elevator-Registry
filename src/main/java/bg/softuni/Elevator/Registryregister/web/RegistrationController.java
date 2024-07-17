package bg.softuni.Elevator.Registryregister.web;

import bg.softuni.Elevator.Registryregister.model.dto.UserDTOs.UserRegistrationDTO;
import bg.softuni.Elevator.Registryregister.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute("registerData", new UserRegistrationDTO());

        return "register";
    }

    @PostMapping("/register")
    public String register(UserRegistrationDTO userRegistrationDTO) {

        userService.registerUser(userRegistrationDTO);

        return "redirect:/";
    }

}
