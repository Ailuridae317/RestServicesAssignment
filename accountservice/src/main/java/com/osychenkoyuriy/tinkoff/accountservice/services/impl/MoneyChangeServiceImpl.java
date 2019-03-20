package com.osychenkoyuriy.tinkoff.accountservice.services.impl;

import com.osychenkoyuriy.tinkoff.accountservice.models.MoneyChange;
import com.osychenkoyuriy.tinkoff.accountservice.repositories.MoneyChangeRepository;
import com.osychenkoyuriy.tinkoff.accountservice.services.MoneyChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyChangeServiceImpl implements MoneyChangeService {

    @Autowired
    private MoneyChangeRepository moneyChangeRepository;

    @Override
    public MoneyChange saveMoneyChange(MoneyChange moneyChange){
        return moneyChangeRepository.save(moneyChange);
    }
}
