package com.osychenkoyuriy.tinkoff.accountservice.repositories;

import com.osychenkoyuriy.tinkoff.accountservice.models.Account;
import com.osychenkoyuriy.tinkoff.accountservice.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findAccountByAccountNumberEquals(long accountNumber);
    Boolean existsAccountByAccountNumberEquals(long accountNumber);
    List<Account> findAccountsByCustomerIdEquals(long customerId);
}
