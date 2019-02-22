package com.exchanger.model.bank;

import com.exchanger.model.Currency;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.List;

public final class VtbBank implements Bank {

    private String url = "https://www.vtb.ru/api/currency-exchange/table-info?contextItemId={C5471052-2291-4AFD-9C2D-1DBC40A4769D}&conversionPlace=0&conversionType=0&renderingId=ede2e4d0-eb6b-4730-857b-06fd4975c06b&renderingParams=LegalStatus__{F2A32685-E909-44E8-A954-1E206D92FFF8};IsFromRuble__1;CardMaxPeriodDays__5;CardRecordsOnPage__5;ConditionsUrl__/personal/platezhi-i-perevody/obmen-valjuty/spezkassy/";
    private List<Currency> currencies;

    public VtbBank(){
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
        JSONArray array = (JSONArray) jo.get("GroupedRates");
        array = (JSONArray) ((JSONObject)array.get(1)).get("MoneyRates");
        jo = (JSONObject) array.get(0);

        return  new Currency("EUR",
                Double.valueOf(jo.get("BankBuyAt").toString()),
                Double.valueOf(jo.get("BankSellAt").toString()));
    }

    @Override
    public Currency createUSD(Object obj){

        JSONObject jo = (JSONObject) obj;
        JSONArray array = (JSONArray) jo.get("GroupedRates");
        array = (JSONArray) ((JSONObject)array.get(0)).get("MoneyRates");
        jo = (JSONObject) array.get(0);

        return  new Currency("USD",
                Double.valueOf(jo.get("BankBuyAt").toString()),
                Double.valueOf(jo.get("BankSellAt").toString()));
    }

    @Override
    public String getName() {
        return "VTB Bank";
    }

    @Override
    public List<Currency> getCurrencies() {
        return currencies;
    }
}
