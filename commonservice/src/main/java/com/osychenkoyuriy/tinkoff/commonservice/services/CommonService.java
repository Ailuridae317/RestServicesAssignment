package com.osychenkoyuriy.tinkoff.commonservice.services;

import com.osychenkoyuriy.tinkoff.commonservice.models.Customer;

import java.io.IOException;

public interface CommonService {
    Customer getCustomerInfo(Customer customer) throws IOException;
}
