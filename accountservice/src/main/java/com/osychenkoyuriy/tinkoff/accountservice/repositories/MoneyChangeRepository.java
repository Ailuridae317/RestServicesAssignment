package com.osychenkoyuriy.tinkoff.accountservice.repositories;

import com.osychenkoyuriy.tinkoff.accountservice.models.MoneyChange;
import org.springframework.data.repository.CrudRepository;

public interface MoneyChangeRepository extends CrudRepository<MoneyChange, Long> {
}
