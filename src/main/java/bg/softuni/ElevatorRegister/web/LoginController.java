package bg.softuni.ElevatorRegister.web;

import bg.softuni.ElevatorRegister.model.dto.UserDTOs.UserLoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @PostMapping("/login-error")
    public ModelAndView viewLoginError() {
        ModelAndView mnv = new ModelAndView("login");

        mnv.addObject("showErrorMessage", true);
        mnv.addObject("loginData", new UserLoginDTO());

        return mnv;
    }
}
