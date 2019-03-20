package com.osychenkoyuriy.tinkoff.accountservice.services.impl;

import com.osychenkoyuriy.tinkoff.accountservice.models.Account;
import com.osychenkoyuriy.tinkoff.accountservice.models.Customer;
import com.osychenkoyuriy.tinkoff.accountservice.models.MoneyChange;
import com.osychenkoyuriy.tinkoff.accountservice.repositories.AccountRepository;
import com.osychenkoyuriy.tinkoff.accountservice.services.AccountService;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MoneyChangeServiceImpl moneyChangeService;

    @Override
    public Boolean accountExists(Account account){
        if (accountRepository.existsById(account.getId()))
            return true;
        else
            return false;
    }

    @Override
    public Account createAccount(Customer customer) throws IOException {
        if (customerExists(customer)) {
            long accountNumber;
            do {
                accountNumber = ThreadLocalRandom.current().nextLong(1000000000);
            }
            while (accountRepository.existsAccountByAccountNumberEquals(accountNumber));
            return accountRepository.save(new Account(accountNumber, BigDecimal.valueOf(0), customer.getId()));
        } else return null;

    }

    @Override
    public void deleteAccount(Account account){
        if (accountExists(account))
            accountRepository.delete(account);
    }

    @Override
    public Account getAccount(Account account){
        if (accountRepository.findById(account.getId()).isPresent())
            return accountRepository.findById(account.getId()).get();
        else if (accountRepository.findAccountByAccountNumberEquals(account.getAccountNumber()) != null)
            return accountRepository.findAccountByAccountNumberEquals(account.getAccountNumber());
        else return null;
    }

    @Override
    public Account moneyChange(MoneyChange moneyChange){
        moneyChange.setDate(new Date(System.currentTimeMillis()));
        Account accountForMoneyChange = null;
        if (moneyChange.getAccountId() != 0){
            if (accountRepository.findById(moneyChange.getAccountId()).isPresent())
                accountForMoneyChange = accountRepository.findById(moneyChange.getAccountId()).get();
        } else if (moneyChange.getAccountNumber() != 0) {
            accountForMoneyChange = accountRepository.findAccountByAccountNumberEquals(moneyChange.getAccountNumber());
        }
        if ((moneyChange.getMoneyChangeType() == MoneyChange.MoneyChangeType.Withdraw || moneyChange.getMoneyChangeType() == MoneyChange.MoneyChangeType.TransferFrom) && accountForMoneyChange.getMoneyAmount().compareTo(moneyChange.getMoneyAmount())>=0)
        {
            accountForMoneyChange.setMoneyAmount(accountForMoneyChange.getMoneyAmount().subtract(moneyChange.getMoneyAmount()));
            moneyChangeService.saveMoneyChange(moneyChange);
            return accountRepository.save(accountForMoneyChange);

        }else if (moneyChange.getMoneyChangeType() == MoneyChange.MoneyChangeType.Deposit || moneyChange.getMoneyChangeType() == MoneyChange.MoneyChangeType.TransferTo){
            accountForMoneyChange.setMoneyAmount(accountForMoneyChange.getMoneyAmount().add(moneyChange.getMoneyAmount()));
            moneyChangeService.saveMoneyChange(moneyChange);
            return accountRepository.save(accountForMoneyChange);
        }else
            return null;
    }

    public Boolean customerExists(Customer customer) throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8081/getCustomer");

        String json = "{\"id\":" + customer.getId() + "}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        try {
            CloseableHttpResponse response = client.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200)
                return true;
            else
                return false;
        } finally {
            client.close();
        }


    }

    public List<Account> getAccountsForCustomerId(Customer customer){
        return accountRepository.findAccountsByCustomerIdEquals(customer.getId());
    }
}
