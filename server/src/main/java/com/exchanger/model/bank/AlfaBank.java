package com.exchanger.model.bank;

import com.exchanger.model.Currency;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class AlfaBank implements Bank {

    private String name = "Alfa Bank";
    private String url = "https://alfabank.ru/ext-json/0.2/exchange/cash?offset=0&limit=2&mode=rest";
    private List<Currency> currencies;

    public AlfaBank(){
        update();
    }

    @Override
    public void update(){
        String response = Bank.sendRequest(this.url);
        currencies = new ArrayList<>();
        try {
            Object obj = new JSONParser().parse(response);
            currencies.add(getEUR(obj));
            currencies.add(getUSD(obj));

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Currency getEUR(Object obj){

        Object eur = ((JSONObject) obj).get("eur");
        JSONArray array= (JSONArray) eur;
        JSONObject buyValue = (JSONObject) array.get(0);
        JSONObject sellValue = (JSONObject) array.get(2);

        return  new Currency("EUR",
                Double.valueOf(buyValue.get("value").toString()),
                Double.valueOf(sellValue.get("value").toString()));
    }

    @Override
    public Currency getUSD(Object obj){

        Object usd = ((JSONObject) obj).get("usd");
        JSONArray array = (JSONArray) usd;
        JSONObject buyValue = (JSONObject) array.get(0);
        JSONObject sellValue = (JSONObject) array.get(2);

        return  new Currency("USD",
                Double.valueOf(buyValue.get("value").toString()),
                Double.valueOf(sellValue.get("value").toString()));
    }

    public String getName() {
        return name;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }
}
