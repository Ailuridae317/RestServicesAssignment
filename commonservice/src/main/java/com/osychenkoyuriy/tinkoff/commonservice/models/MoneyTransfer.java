package com.osychenkoyuriy.tinkoff.commonservice.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "money_transfers")
public class MoneyTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long accountNumberFrom;
    private long accountNumberTo;
    private BigDecimal moneyAmount;
    private Date date;

    public MoneyTransfer(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountNumberFrom() {
        return accountNumberFrom;
    }

    public void setAccountNumberFrom(long accountNumberFrom) {
        this.accountNumberFrom = accountNumberFrom;
    }

    public long getAccountNumberTo() {
        return accountNumberTo;
    }

    public void setAccountNumberTo(long accountNumberTo) {
        this.accountNumberTo = accountNumberTo;
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
}
