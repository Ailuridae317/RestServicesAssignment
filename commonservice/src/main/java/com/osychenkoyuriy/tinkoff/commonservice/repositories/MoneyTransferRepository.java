package com.osychenkoyuriy.tinkoff.commonservice.repositories;

import com.osychenkoyuriy.tinkoff.commonservice.models.MoneyTransfer;
import org.springframework.data.repository.CrudRepository;

public interface MoneyTransferRepository extends CrudRepository<MoneyTransfer, Long> {
}
