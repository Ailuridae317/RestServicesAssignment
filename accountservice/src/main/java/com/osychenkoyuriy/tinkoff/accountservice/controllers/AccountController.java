package com.osychenkoyuriy.tinkoff.accountservice.controllers;

import com.osychenkoyuriy.tinkoff.accountservice.models.Account;
import com.osychenkoyuriy.tinkoff.accountservice.models.Customer;
import com.osychenkoyuriy.tinkoff.accountservice.models.MoneyChange;
import com.osychenkoyuriy.tinkoff.accountservice.services.impl.AccountServiceImpl;
import com.osychenkoyuriy.tinkoff.accountservice.services.impl.MoneyChangeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class    AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @PostMapping(path = "/createAccount", consumes = "application/json", produces = "application/json")
    public Account createAccount(@RequestBody Customer customer) throws IOException {
        return accountService.createAccount(customer);
    }

    @PostMapping(path = "/deleteAccount", consumes = "application/json", produces = "application/json")
    public void deleteAccount(@RequestBody Account account){
        accountService.deleteAccount(account);
    }

    @PostMapping(path = "/getAccount", consumes = "application/json", produces = "application/json")
    public Account getAccount(@RequestBody Account account){
        return accountService.getAccount(account);
    }

    @PostMapping(path = "/deposit", consumes = "application/json", produces = "application/json")
    public Account deposit(@RequestBody MoneyChange moneyChange){
        moneyChange.setMoneyChangeType(MoneyChange.MoneyChangeType.Deposit);
        return accountService.moneyChange(moneyChange);
    }

    @PostMapping(path = "/withdraw", consumes = "application/json", produces = "application/json")
    public Account withdraw(@RequestBody MoneyChange moneyChange){
        moneyChange.setMoneyChangeType(MoneyChange.MoneyChangeType.Withdraw);
        return accountService.moneyChange(moneyChange);
    }

    @PostMapping(path = "/transferTo", consumes = "application/json", produces = "application/json")
    public Account transferTo(@RequestBody MoneyChange moneyChange){
        moneyChange.setMoneyChangeType(MoneyChange.MoneyChangeType.TransferTo);
        return accountService.moneyChange(moneyChange);
    }

    @PostMapping(path = "/transferFrom", consumes = "application/json", produces = "application/json")
    public Account transferFrom(@RequestBody MoneyChange moneyChange){
        moneyChange.setMoneyChangeType(MoneyChange.MoneyChangeType.TransferFrom);
        return accountService.moneyChange(moneyChange);
    }


    @PostMapping(path = "getAccountsForCustomerId", consumes = "application/json", produces = "application/json")
    public List<Account> getAccountForCustomerId(@RequestBody Customer customer){
        return accountService.getAccountsForCustomerId(customer);
    }


}
