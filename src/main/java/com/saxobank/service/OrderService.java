package com.saxobank.service;

import com.saxobank.dto.OrderRequest;
import com.saxobank.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private AccountService accountService;

    private final String SAXO_API_URL = "https://gateway.saxobank.com/sim/openapi/trade/v2/orders";


    public String placeOrder(Long accountId, OrderRequest orderRequest) {
        if (!accountService.canPlaceOrder(accountId, orderRequest.getOrderValue())) {
            throw new RuntimeException("Order cannot be placed due to risk management rules");
        }
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SAXO_API_URL, orderRequest, String.class); //add auth headers
        Account account = accountService.getAccount(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setCurrentDayLoss(account.getCurrentDayLoss() + orderRequest.getOrderValue());
        account.setCurrentMaxLoss(account.getCurrentMaxLoss() + orderRequest.getOrderValue());
        accountService.updateAccount(account);
        return response;
    }
}
