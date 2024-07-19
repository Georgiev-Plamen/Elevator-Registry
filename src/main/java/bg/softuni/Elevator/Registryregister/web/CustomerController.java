package bg.softuni.Elevator.Registryregister.web;

import bg.softuni.Elevator.Registryregister.model.dto.CustomerDTOs.AddCustomerDTO;
import bg.softuni.Elevator.Registryregister.model.dto.CustomerDTOs.CustomerDetailsDTO;
import bg.softuni.Elevator.Registryregister.service.CustomerService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customer/allCustomers";
    }

    @GetMapping("/editCustomer/{id}")
    public String editCustomer(@PathVariable("id") Long id, Model model) {

        model.addAttribute("customerDetails", customerService.getCustomerDetails(id));

        return "editCustomer";
    }

    @PutMapping("/editCustomer/{id}")
    public String editCustomer(@PathVariable("id") Long id, CustomerDetailsDTO customerDetailsDTO) {
        customerService.editCustomer(id, customerDetailsDTO);

        return "redirect:/customer/allCustomers";
    }
}
