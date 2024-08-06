package bg.softuni.ElevatorRegister.web;

import bg.softuni.ElevatorRegister.model.dto.UserDTOs.UserLoginDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class LoginController {

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login");

        modelAndView.addObject("loginData", new UserLoginDTO());

        return modelAndView;
    }


    @GetMapping("/login-error")
    public ModelAndView viewLoginError() {
        ModelAndView mnv = new ModelAndView("login");

        mnv.addObject("showErrorMessage", true);
        mnv.addObject("loginData", new UserLoginDTO());

        return mnv;
    }
}
