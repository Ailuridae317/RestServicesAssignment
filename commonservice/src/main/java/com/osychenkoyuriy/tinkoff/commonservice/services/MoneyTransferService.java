package com.osychenkoyuriy.tinkoff.commonservice.services;

import com.osychenkoyuriy.tinkoff.commonservice.models.MoneyTransfer;

import java.io.IOException;

public interface MoneyTransferService {
    MoneyTransfer transferMoney(MoneyTransfer moneyTransfer) throws IOException;
}
