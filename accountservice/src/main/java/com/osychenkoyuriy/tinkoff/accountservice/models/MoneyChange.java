package com.osychenkoyuriy.tinkoff.accountservice.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "money_changes")
public class MoneyChange {

   public enum MoneyChangeType {
        Deposit, Withdraw, TransferFrom, TransferTo
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long accountNumber;
    private long accountId;
    private BigDecimal moneyAmount;
    @Enumerated(EnumType.STRING)
    private MoneyChangeType moneyChangeType;
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MoneyChangeType getMoneyChangeType() {
        return moneyChangeType;
    }

    public void setMoneyChangeType(MoneyChangeType moneyChangeType) {
        this.moneyChangeType = moneyChangeType;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
}
