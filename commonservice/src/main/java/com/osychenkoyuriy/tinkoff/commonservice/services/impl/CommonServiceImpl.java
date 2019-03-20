package com.osychenkoyuriy.tinkoff.commonservice.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.osychenkoyuriy.tinkoff.commonservice.models.Account;
import com.osychenkoyuriy.tinkoff.commonservice.models.Customer;
import com.osychenkoyuriy.tinkoff.commonservice.services.CommonService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    @Override
    public Customer getCustomerInfo(Customer customer) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Account> accountList;
        Customer resultCustomer;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8081/getCustomer");

        String json = "{\"id\":" + customer.getId() + "}";
        StringEntity entity = new StringEntity(json, "UTF-8");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        try {
            CloseableHttpResponse response = client.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200){
                HttpEntity httpEntity = response.getEntity();
                InputStream inputStream = httpEntity.getContent();
                resultCustomer = objectMapper.readValue(inputStream, Customer.class);
                resultCustomer.setAccountList(getAccountList(resultCustomer));
                return resultCustomer;
            } else return null;
        } finally {
            client.close();
        }


    }

    public List<Account> getAccountList(Customer customer) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8082/getAccountsForCustomerId");

        String json = "{\"id\":" + customer.getId() + "}";
        StringEntity entity = new StringEntity(json, "UTF-8");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        try{
            CloseableHttpResponse response = client.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200){
                CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Account.class);
                HttpEntity httpEntity = response.getEntity();
                InputStream inputStream = httpEntity.getContent();

                return objectMapper.readValue(inputStream, collectionType);
            } else return  null;
        } finally {
            client.close();
        }
    }
}
