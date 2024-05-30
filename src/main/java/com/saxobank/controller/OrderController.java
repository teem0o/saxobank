package com.saxobank.controller;

import com.saxobank.dto.OrderRequest;
import com.saxobank.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public String placeOrder(@RequestParam Long accountId, @RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(accountId, orderRequest);
    }
}
