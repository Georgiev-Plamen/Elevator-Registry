package bg.softuni.Elevator.Registryregister.web;

import bg.softuni.Elevator.Registryregister.model.dto.CustomerDTOs.AddCustomerDTO;
import bg.softuni.Elevator.Registryregister.service.CustomerService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/addCustomer")
    public String addCustomer() {
        return "addCustomer";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(AddCustomerDTO addCustomerDTO) {
        customerService.addNewCustomer(addCustomerDTO);

        return "redirect:/customer/allCustomers";
    }

    @GetMapping("/allCustomers")
    public String allCustomers(Model model) {
        model.addAttribute("allCustomers", customerService.getAllCustomers());

        return "allCustomers";
    }
}
