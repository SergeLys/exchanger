package android.exchanger.exchanger_android_client.model.bank;

import android.exchanger.exchanger_android_client.model.Currency;
import org.json.simple.JSONObject;
import java.util.HashMap;

public final class CentralBank implements Bank {

    private String url = "https://www.cbr-xml-daily.ru/daily_json.js";
    private HashMap<String, Currency> currencies;

    public CentralBank(){
        currencies = new HashMap<>();
        update();
    }

    @Override
    public void update(){
        Bank.getResponse(this.url,this);
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
    public HashMap<String, Currency> getCurrencies() {
        return currencies;
    }
}
