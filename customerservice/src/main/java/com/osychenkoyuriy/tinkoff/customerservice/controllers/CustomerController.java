package com.osychenkoyuriy.tinkoff.customerservice.controllers;

import com.osychenkoyuriy.tinkoff.customerservice.models.Customer;
import com.osychenkoyuriy.tinkoff.customerservice.services.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping(path = "/changeCustomer", consumes = "application/json", produces = "application/json")
    public Customer changeCustomer(@RequestBody Customer customer){
        return customerService.changeCustomer(customer);
    }

    @PostMapping(path = "/createCustomer", consumes = "application/json", produces = "application/json")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @PostMapping(path = "/deleteCustomer", consumes = "application/json", produces = "application/json")
    public void deleteCustomer(@RequestBody Customer customer){
        customerService.deleteCustomer(customer);
    }

    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping(path = "/getCustomer", consumes = "application/json", produces = "application/json")
    public Customer getCustomer(@RequestBody Customer customer){
        return customerService.getCustomerById(customer.getId());
    }
}
