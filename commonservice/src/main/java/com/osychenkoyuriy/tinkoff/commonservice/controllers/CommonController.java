package com.osychenkoyuriy.tinkoff.commonservice.controllers;

import com.osychenkoyuriy.tinkoff.commonservice.models.Customer;
import com.osychenkoyuriy.tinkoff.commonservice.models.MoneyTransfer;
import com.osychenkoyuriy.tinkoff.commonservice.services.impl.CommonServiceImpl;
import com.osychenkoyuriy.tinkoff.commonservice.services.impl.MoneyTransferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CommonController {

    @Autowired
    private MoneyTransferServiceImpl moneyTransferService;

    @Autowired
    private CommonServiceImpl commonService;

    @PostMapping(path = "/getCustomerInfo", consumes = "application/json", produces = "application/json")
    public Customer getCustomerInfo(@RequestBody Customer customer) throws IOException {
        return commonService.getCustomerInfo(customer);
    }

    @PostMapping(path = "/transferMoney", consumes = "application/json", produces = "application/json")
    public MoneyTransfer moneyTransfer(@RequestBody MoneyTransfer moneyTransfer) throws IOException {
        return moneyTransferService.transferMoney(moneyTransfer);
    }
}
