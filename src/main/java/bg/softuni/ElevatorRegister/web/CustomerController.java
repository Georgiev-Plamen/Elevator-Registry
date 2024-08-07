package bg.softuni.ElevatorRegister.web;

import bg.softuni.ElevatorRegister.model.dto.CustomerDTOs.AddCustomerDTO;
import bg.softuni.ElevatorRegister.model.dto.CustomerDTOs.CustomerDetailsDTO;
import bg.softuni.ElevatorRegister.model.dto.UserDTOs.UserRegistrationDTO;
import bg.softuni.ElevatorRegister.service.CustomerService;
import bg.softuni.ElevatorRegister.service.ElevatorService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final ElevatorService elevatorService;

    @ModelAttribute("addCustomerDTO")
    public AddCustomerDTO addCustomerDTO() {
        return AddCustomerDTO.empty();
    }

    public CustomerController(CustomerService customerService, ElevatorService elevatorService) {
        this.customerService = customerService;
        this.elevatorService = elevatorService;
    }

    @GetMapping("/addCustomer")
    public String addCustomer(Model model) {

//        if(!model.containsAttribute("addCustomerDTO")){
//            model.addAttribute("addCustomerDTO", AddCustomerDTO.empty());
//        }
        return "add-customer";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@Valid AddCustomerDTO addCustomerDTO,
                              BindingResult bindingResult,
                              RedirectAttributes rAtt) {

        if(bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("addCustomerDTO", addCustomerDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addCustomerDTO", bindingResult);
            return "redirect:/customer/addCustomer";
        }

        customerService.addNewCustomer(addCustomerDTO);


        return "redirect:/customer/allCustomers";
    }

    @GetMapping("/allCustomers")
    public String allCustomers(Model model) {
        model.addAttribute("allCustomers", customerService.getAllCustomers());

        return "all-customers";
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customer/allCustomers";
    }

    @GetMapping("/editCustomer/{id}")
    public String editCustomer(@PathVariable("id") Long id, Model model) {

        model.addAttribute("customerDetails", customerService.getCustomerDetails(id));

        return "edit-customer";
    }

    @PutMapping("/editCustomer/{id}")
    public String editCustomer(@PathVariable("id") Long id, CustomerDetailsDTO customerDetailsDTO) {
        customerService.editCustomer(id, customerDetailsDTO);

        return "redirect:/customer/allCustomers";
    }

//    @GetMapping("/customerElevators/{id}")
//    public String allCustomerElevator(@PathVariable("id") Long id, Model model) {
//
//        model.addAttribute("customersElevators", elevatorService.getAllCustomerElevator(id));
//        model.addAttribute("customerDetails", customerService.getCustomerDetails(id));
//        //todo need to create view
//
//        return "customerElevators";
//    }


}
