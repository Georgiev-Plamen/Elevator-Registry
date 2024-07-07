package bg.softuni.Elevator.Registryregister.web;

import bg.softuni.Elevator.Registryregister.model.dto.UserLoginDTO;
import bg.softuni.Elevator.Registryregister.model.user.AppUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

//    @GetMapping("/")
//    public ModelAndView home() {
//        ModelAndView modelAndView = new ModelAndView("index");
//
//        modelAndView.addObject("profileData", userService.getProfileData());
//
//        return modelAndView;
//    }

//    @ModelAttribute("userData")
//    public UserLoginDTO userLoginDTO() {
//        return new UserLoginDTO();
//    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserDetails userDetails,
                        Model model) {

        if(userDetails instanceof AppUserDetails appUserDetails) {
            model.addAttribute("userData", appUserDetails.getFullName());
        } else {
            model.addAttribute("userData", "Гост");
        }

        return "index";
    }
}
