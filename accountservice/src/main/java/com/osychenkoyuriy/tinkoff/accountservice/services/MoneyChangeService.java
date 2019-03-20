package com.osychenkoyuriy.tinkoff.accountservice.services;

import com.osychenkoyuriy.tinkoff.accountservice.models.MoneyChange;

public interface MoneyChangeService {
    MoneyChange saveMoneyChange(MoneyChange moneyChange);
}
