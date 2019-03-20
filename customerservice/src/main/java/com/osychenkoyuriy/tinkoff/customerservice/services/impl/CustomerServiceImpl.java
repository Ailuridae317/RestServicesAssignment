package com.osychenkoyuriy.tinkoff.customerservice.services.impl;

import com.google.common.collect.Lists;
import com.osychenkoyuriy.tinkoff.customerservice.models.Customer;
import com.osychenkoyuriy.tinkoff.customerservice.repositories.CustomerRepository;
import com.osychenkoyuriy.tinkoff.customerservice.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer saveCustomer(Customer customer){
        if (!customerExists(customer))
            return customerRepository.save(customer);
        else
            return null;
    }

    @Override
    public void deleteCustomer(Customer customer){
        customerRepository.delete(customer);
    }

    @Override
    public Boolean customerExists(Customer customer) {
        if (customerRepository.existsById(customer.getId()))
            return true;
        else return false;
    }

    @Override
    public List<Customer> getAllCustomers(){
        return Lists.newArrayList(customerRepository.findAll());
    }

    @Override
    public Customer changeCustomer(Customer customer){
        if (customerExists(customer))
            return customerRepository.save(customer);
        else
            return null;
    }
}
