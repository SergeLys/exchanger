package com.exchanger.model.bank;

import com.exchanger.model.Currency;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;

public final class CentralBank implements Bank {

    private String url = "https://www.cbr-xml-daily.ru/daily_json.js";
    private List<Currency> currencies;

    public CentralBank(){
        update();
    }

    @Override
    public void update(){
        String response = Bank.sendRequest(this.url);
        currencies = new ArrayList<>();
        try {
            Object obj = new JSONParser().parse(response);
            currencies.add(createEUR(obj));
            currencies.add(createUSD(obj));

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Currency createEUR(Object obj){

        JSONObject jo = (JSONObject) obj;
        jo = (JSONObject) jo.get("Valute");
        jo = (JSONObject) jo.get("EUR");

        return  new Currency("EUR",
                Double.valueOf(jo.get("Previous").toString()),
                Double.valueOf(jo.get("Previous").toString()));
    }

    @Override
    public Currency createUSD(Object obj){

        JSONObject jo = (JSONObject) obj;
        jo = (JSONObject) jo.get("Valute");
        jo = (JSONObject) jo.get("USD");

        return  new Currency("USD",
                Double.valueOf(jo.get("Previous").toString()),
                Double.valueOf(jo.get("Previous").toString()));
    }

    @Override
    public String getName() {
        return "Central Bank";
    }

    @Override
    public List<Currency> getCurrencies() {
        return currencies;
    }
}
