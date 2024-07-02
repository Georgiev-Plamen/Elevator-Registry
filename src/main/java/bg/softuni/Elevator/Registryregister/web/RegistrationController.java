package bg.softuni.Elevator.Registryregister.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class RegistrationController {

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
