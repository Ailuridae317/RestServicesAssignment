package com.osychenkoyuriy.tinkoff.accountservice.models;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long accountNumber;

    private BigDecimal moneyAmount;

    private long customerId;

    public Account(){}

    public Account(long accountNumber, BigDecimal moneyAmount, long customerId) {
        this.accountNumber = accountNumber;
        this.moneyAmount = moneyAmount;
        this.customerId = customerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
