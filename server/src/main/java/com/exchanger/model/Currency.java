package com.exchanger.model;

public class Currency {

    private String name;
    private Double buyValue, sellValue;

    public Currency(String name, Double buyValue, Double sellValue){
        this.name = name;
        this.buyValue = buyValue;
        this.sellValue = sellValue;
    }

    public String getName() {
        return name;
    }

    public Double getBuyValue() {
        return buyValue;
    }

    public Double getSellValue() {
        return sellValue;
    }
}
