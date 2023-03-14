package com.registration.registration.Controller;

import com.registration.registration.Infrastructure.Models.Customer;
import com.registration.registration.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/registration")
public class RegistrationController {
    private CustomerService service;

    @Autowired
    public RegistrationController(CustomerService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<?> getCustomers() {
        List<Customer> customers = service.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/id")
    public ResponseEntity<?> getCustomerById(Long id) {
        Optional<Customer> customer = service.getCustomerById(id);
        if(!customer.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping(params = "firstName")
    public ResponseEntity<?> getCustomerByFirstName(String firstName) {
        List<Customer> customers = service.getCustomerByName(firstName);
        return ResponseEntity.ok(customers);
    }

    @PostMapping()
    public ResponseEntity<?> createCustomer(@RequestBody Customer body) {
        Customer customer = service.createCustomer(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @PutMapping()
    public ResponseEntity<?> updateCustomer(@RequestBody Customer body){
        Optional<Customer> customer = service.updateCustomer(body);
        return  ResponseEntity.ok(customer);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteCustomer(Long id){
        if(!service.deleteCustomer(id)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
