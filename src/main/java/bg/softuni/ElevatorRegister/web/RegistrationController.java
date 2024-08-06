package bg.softuni.ElevatorRegister.web;

import bg.softuni.ElevatorRegister.model.dto.UserDTOs.UserRegistrationDTO;
import bg.softuni.ElevatorRegister.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {

//        if(!model.containsAttribute("registerData")) {
//            model.addAttribute("registerData", new UserRegistrationDTO());
//        }

        model.addAttribute("registerData", new UserRegistrationDTO());

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO data,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);

            return "redirect:/users/register";
        }

        userService.registerUser(data);

        return "redirect:/users/login";
    }

}
