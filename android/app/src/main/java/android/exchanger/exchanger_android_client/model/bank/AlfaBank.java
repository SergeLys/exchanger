package android.exchanger.exchanger_android_client.model.bank;

import android.exchanger.exchanger_android_client.model.Currency;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;

public final class AlfaBank implements Bank {

    private String url = "https://alfabank.ru/ext-json/0.2/exchange/cash?offset=0&limit=2&mode=rest";
    private HashMap<String,Currency> currencies;

    public AlfaBank(){
        currencies = new HashMap<>();
        update();
    }

    @Override
    public void update(){
        Bank.getResponse(this.url,this);
    }

    @Override
    public Currency createEUR(Object obj){

        Object eur = ((JSONObject) obj).get("eur");
        JSONArray array= (JSONArray) eur;
        JSONObject buyValue = (JSONObject) array.get(0);
        JSONObject sellValue = (JSONObject) array.get(2);

        return  new Currency("EUR",
                Double.valueOf(buyValue.get("value").toString()),
                Double.valueOf(sellValue.get("value").toString()));
    }

    @Override
    public Currency createUSD(Object obj){

        Object usd = ((JSONObject) obj).get("usd");
        JSONArray array = (JSONArray) usd;
        JSONObject buyValue = (JSONObject) array.get(0);
        JSONObject sellValue = (JSONObject) array.get(2);

        return  new Currency("USD",
                Double.valueOf(buyValue.get("value").toString()),
                Double.valueOf(sellValue.get("value").toString()));
    }

    @Override
    public String getName() {
        return "Alfa-bank";
    }

    @Override
    public HashMap<String, Currency> getCurrencies() {
        return currencies;
    }
}
