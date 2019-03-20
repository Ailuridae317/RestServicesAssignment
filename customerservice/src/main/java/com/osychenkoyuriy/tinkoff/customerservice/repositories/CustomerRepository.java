package com.osychenkoyuriy.tinkoff.customerservice.repositories;

import com.osychenkoyuriy.tinkoff.customerservice.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
