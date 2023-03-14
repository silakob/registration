package com.registration.registration.Service;

import com.registration.registration.Infrastructure.Models.Customer;
import com.registration.registration.Infrastructure.Models.FileInfo;
import com.registration.registration.Repository.CustomerRepository;
import com.registration.registration.Repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
//import java.io.File;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getCustomerByName(String firstName) {
        return customerRepository.findByfirstName(firstName);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> updateCustomer(Customer customer) {
        Optional<Customer> updateCustomer = customerRepository.findById(customer.getId());
        if (!updateCustomer.isPresent()) {
            return updateCustomer;
        }

        customer.setId(customer.getId());
        return Optional.of(customerRepository.save(customer));
    }

    public boolean deleteCustomer(Long id) {
        try {
            customerRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


}
