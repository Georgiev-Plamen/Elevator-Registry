package bg.softuni.ElevatorRegister.web;

import bg.softuni.ElevatorRegister.model.dto.CustomerDTOs.AddCustomerDTO;
import bg.softuni.ElevatorRegister.model.dto.CustomerDTOs.CustomerDetailsDTO;
import bg.softuni.ElevatorRegister.service.CustomerService;
import bg.softuni.ElevatorRegister.service.ElevatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final ElevatorService elevatorService;

    public CustomerController(CustomerService customerService, ElevatorService elevatorService) {
        this.customerService = customerService;
        this.elevatorService = elevatorService;
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
