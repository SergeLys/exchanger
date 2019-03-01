package android.exchanger.exchanger_android_client.model;

import android.exchanger.exchanger_android_client.model.bank.*;

import java.util.ArrayList;
import java.util.List;

public final class BanksInstance {

    private static BanksInstance instance;
    private CentralBank centralBank;
    private List<Bank> banks;

    private BanksInstance(){
        centralBank = new CentralBank();
        banks = new ArrayList<>();
        banks.add(new Sberbank());
        banks.add(new AlfaBank());
        banks.add(new VtbBank());
    }

    public static BanksInstance getInstance() {
        if(instance == null){
            instance = new BanksInstance();
            instance.update();
        }
        return instance;
    }

    public void update(){
        instance.centralBank.update();
        for(Bank bank : instance.banks) bank.update();
    }

    public List<Bank> getBanksList(){
        return this.banks;
    }

    public CentralBank getCentralBank() {
        return centralBank;
    }

    public Bank getBestBuyingPriceUSD(){
        Double price = Double.MAX_VALUE;
        Bank result = null;

        for(Bank bank : banks){
            if(bank.getCurrencies().get("USD").getSellValue() < price){
                price = bank.getCurrencies().get("USD").getSellValue();
                result = bank;
            }
        }
        return result;
    }

    public Bank getBestBuyingPriceEUR(){
        Double price = Double.MAX_VALUE;
        Bank result = null;

        for(Bank bank : banks){
            if(bank.getCurrencies().get("EUR").getSellValue() < price){
                price = bank.getCurrencies().get("EUR").getSellValue();
                result = bank;
            }
        }
        return result;
    }

    public Bank getBestSellingUSD(){
        Double price = 0.0;
        Bank result = null;

        for(Bank bank : banks){
            if(bank.getCurrencies().get("USD").getBuyValue() > price){
                price = bank.getCurrencies().get("USD").getBuyValue();
                result = bank;
            }
        }
        return result;
    }

    public Bank getBestSellingEUR(){
        Double price = 0.0;
        Bank result = null;

        for(Bank bank : banks){
            if(bank.getCurrencies().get("EUR").getBuyValue() > price){
                price = bank.getCurrencies().get("EUR").getBuyValue();
                result = bank;
            }
        }
        return result;
    }
}
