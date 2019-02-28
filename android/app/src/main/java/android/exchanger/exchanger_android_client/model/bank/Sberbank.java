package android.exchanger.exchanger_android_client.model.bank;

import android.exchanger.exchanger_android_client.model.Currency;

import org.json.simple.JSONObject;

import java.util.HashMap;
public final class Sberbank implements Bank {

    private String url = "https://www.sberbank.ru/portalserver/proxy/?pipe=shortCachePipe&url=http%3A%2F%2Flocalhost%2Frates-web%2FrateService%2Frate%2Fcurrent%3FregionId%3D77%26rateCategory%3Dfirst%26currencyCode%3D840%26currencyCode%3D978";
    private HashMap<String, Currency> currencies;

    public Sberbank(){
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
    public HashMap<String, Currency> getCurrencies() {
        return currencies;
    }
}
