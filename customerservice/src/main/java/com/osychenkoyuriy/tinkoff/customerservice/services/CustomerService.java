package com.osychenkoyuriy.tinkoff.customerservice.services;

import com.osychenkoyuriy.tinkoff.customerservice.models.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(long id);
    List<Customer> getAllCustomers();
    Customer saveCustomer(Customer customer);
    void deleteCustomer(Customer customer);
    Boolean customerExists(Customer customer);
    Customer changeCustomer(Customer customer);
}
