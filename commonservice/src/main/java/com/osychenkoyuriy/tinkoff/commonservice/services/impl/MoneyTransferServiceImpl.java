package com.osychenkoyuriy.tinkoff.commonservice.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osychenkoyuriy.tinkoff.commonservice.models.Account;
import com.osychenkoyuriy.tinkoff.commonservice.models.MoneyTransfer;
import com.osychenkoyuriy.tinkoff.commonservice.repositories.MoneyTransferRepository;
import com.osychenkoyuriy.tinkoff.commonservice.services.MoneyTransferService;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {

    @Autowired
    private MoneyTransferRepository moneyTransferRepository;

    @Override
    public MoneyTransfer transferMoney(MoneyTransfer moneyTransfer) throws IOException {
        //send withdraw request
        if (transferFromCanBeDone(moneyTransfer) && transferToCanBeDone(moneyTransfer)) {
            transfer(moneyTransfer);
            moneyTransfer.setDate(new Date(System.currentTimeMillis()));
            return moneyTransferRepository.save(moneyTransfer);
        } else return null;
    }


    private Boolean transferFromCanBeDone(MoneyTransfer moneyTransfer) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8082/getAccount");

        String json = "{\"accountNumber\":" + moneyTransfer.getAccountNumberFrom() + "}";
        StringEntity entity = new StringEntity(json, "UTF-8");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        try {
            CloseableHttpResponse response = client.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200){
                HttpEntity httpEntity = response.getEntity();
                InputStream inputStream = httpEntity.getContent();
                Account accountFrom = objectMapper.readValue(inputStream, Account.class);
                if (accountFrom.getMoneyAmount().compareTo(moneyTransfer.getMoneyAmount())>=0)
                    return true;
                else return false;
            }
            else return false;
        } finally {
            client.close();
        }
    }

    private Boolean transferToCanBeDone(MoneyTransfer moneyTransfer) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8082/getAccount");

        String json = "{\"accountNumber\":" + moneyTransfer.getAccountNumberTo() + "}";
        StringEntity entity = new StringEntity(json, "UTF-8");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        try {
            CloseableHttpResponse response = client.execute(httpPost);
            if (response.getEntity().getContentLength() == 0){
                return false;
            }
            else return true;

        } finally {
            client.close();
        }
    }

    private void transfer(MoneyTransfer moneyTransfer) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPostFrom = new HttpPost("http://localhost:8082/transferFrom");
        HttpPost httpPostTo = new HttpPost("http://localhost:8082/transferTo");

        String jsonFrom = "{\"accountNumber\":" + moneyTransfer.getAccountNumberFrom() + ", \"moneyAmount\":" + moneyTransfer.getMoneyAmount() +"}";
        String jsonTo = "{\"accountNumber\":" + moneyTransfer.getAccountNumberTo() + ", \"moneyAmount\":" + moneyTransfer.getMoneyAmount() +"}";
        StringEntity entityFrom = new StringEntity(jsonFrom, "UTF-8");
        StringEntity entityTo = new StringEntity(jsonTo, "UTF-8");
        httpPostFrom.setEntity(entityFrom);
        httpPostFrom.setHeader("Accept", "application/json");
        httpPostFrom.setHeader("Content-type", "application/json");
        httpPostTo.setEntity(entityTo);
        httpPostTo.setHeader("Accept", "application/json");
        httpPostTo.setHeader("Content-type", "application/json");

        try {
            CloseableHttpResponse responseFrom = client.execute(httpPostFrom);
            CloseableHttpResponse responseTo = client.execute(httpPostTo);
        } finally {
            client.close();
        }
    }
}
