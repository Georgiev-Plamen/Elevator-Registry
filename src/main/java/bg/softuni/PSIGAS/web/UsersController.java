package bg.softuni.PSIGAS.web;

import bg.softuni.PSIGAS.model.dto.UserDTOs.UserEditDTO;
import bg.softuni.PSIGAS.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String users(Model model) {

        model.addAttribute("allUser", userService.getAllUsers());

        return "users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {

        userService.deleteUser(id);

        return "redirect:/users/all";
    }

    @GetMapping("/editUser/{id}")
    public String editUserView(@PathVariable("id") Long id,
                               Model model) {
        model.addAttribute("userDetails", userService.getUserDetails(id));

        return "edit-user";
    }

    @PutMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") Long id, UserEditDTO userEditDTO) {

        userService.editUser(id, userEditDTO);

        return "redirect:/users/all";
    }
}
