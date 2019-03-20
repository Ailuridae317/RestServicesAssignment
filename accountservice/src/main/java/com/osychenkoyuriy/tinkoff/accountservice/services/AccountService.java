package com.osychenkoyuriy.tinkoff.accountservice.services;

import com.osychenkoyuriy.tinkoff.accountservice.models.Account;
import com.osychenkoyuriy.tinkoff.accountservice.models.Customer;
import com.osychenkoyuriy.tinkoff.accountservice.models.MoneyChange;

import java.io.IOException;
import java.util.List;

public interface AccountService {
    Boolean accountExists(Account account);
    Account createAccount(Customer customer) throws IOException;
    void deleteAccount(Account account);
    Account getAccount(Account account);
    Account moneyChange(MoneyChange moneyChange);
    List<Account> getAccountsForCustomerId(Customer customer);
}
