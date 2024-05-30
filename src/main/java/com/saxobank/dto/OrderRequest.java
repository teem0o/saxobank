package com.saxobank.dto;

import lombok.Data;

import java.util.Random;

@Data
public class OrderRequest {
    private String secret;
    private String symbol;
    private String buySell;
    private String orderType;
    private int positionSize;
    private String strategy;
    private double slPercentpertrade;

    public double getOrderValue() {
        return positionSize * new Random().nextInt(99) + 1; //random calculation logic
    }

}
