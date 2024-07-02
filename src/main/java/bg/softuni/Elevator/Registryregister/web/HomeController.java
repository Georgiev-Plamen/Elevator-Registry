package bg.softuni.Elevator.Registryregister.web;

import bg.softuni.Elevator.Registryregister.model.user.ElevatorUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        if (userDetails instanceof ElevatorUserDetails elevatorUserDetails) {
            model.addAttribute("welcomeMessage", elevatorUserDetails.getUsername());
        } else {
            model.addAttribute("welcomeMessage", "Anonymous");
            }


        return "index";
    }
}
