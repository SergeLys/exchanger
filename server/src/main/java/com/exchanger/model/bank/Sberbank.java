package com.exchanger.model.bank;

import com.exchanger.model.Currency;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;

public final class Sberbank implements Bank {

    private String url = "https://www.sberbank.ru/portalserver/proxy/?pipe=shortCachePipe&url=http%3A%2F%2Flocalhost%2Frates-web%2FrateService%2Frate%2Fcurrent%3FregionId%3D77%26rateCategory%3Dfirst%26currencyCode%3D840%26currencyCode%3D978";
    private List<Currency> currencies;

    public Sberbank(){
        currencies = new ArrayList<>();
        update();
    }

    @Override
    public void update(){
        Bank.getResponse(this.url,this);
    }

    @Override
    public Currency createEUR(Object obj){

        JSONObject jo = (JSONObject) obj;
        JSONObject eur = (JSONObject) jo.get("first");
        JSONObject eur2 = (JSONObject) eur.get("978");
        JSONObject eur3 = (JSONObject) eur2.get("0");

        return  new Currency("EUR",
                Double.valueOf(eur3.get("buyValue").toString()),
                Double.valueOf(eur3.get("sellValue").toString()));
    }

    @Override
    public Currency createUSD(Object obj){

        JSONObject jo = (JSONObject) obj;
        JSONObject eur = (JSONObject) jo.get("first");
        JSONObject eur2 = (JSONObject) eur.get("840");
        JSONObject eur3 = (JSONObject) eur2.get("0");

        return  new Currency("USD",
                Double.valueOf(eur3.get("buyValue").toString()),
                Double.valueOf(eur3.get("sellValue").toString()));
    }

    @Override
    public String getName() {
        return "Sberbank";
    }

    @Override
    public List<Currency> getCurrencies() {
        return currencies;
    }
}
