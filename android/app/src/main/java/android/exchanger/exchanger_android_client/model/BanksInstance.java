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
        } else {
            instance.centralBank.update();
            for(Bank bank : instance.banks) bank.update();
        }
        return instance;
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
            if(bank.getCurrencies().get(1).getSellValue() < price){
                price = bank.getCurrencies().get(1).getSellValue();
                result = bank;
            }
        }
        return result;
    }

    public Bank getBestBuyingPriceEUR(){
        Double price = Double.MAX_VALUE;
        Bank result = null;

        for(Bank bank : banks){
            if(bank.getCurrencies().get(0).getSellValue() < price){
                price = bank.getCurrencies().get(0).getSellValue();
                result = bank;
            }
        }
        return result;
    }

    public Bank getBestSellingUSD(){
        Double price = 0.0;
        Bank result = null;

        for(Bank bank : banks){
            if(bank.getCurrencies().get(1).getBuyValue() > price){
                price = bank.getCurrencies().get(1).getBuyValue();
                result = bank;
            }
        }
        return result;
    }

    public Bank getBestSellingEUR(){
        Double price = 0.0;
        Bank result = null;

        for(Bank bank : banks){
            if(bank.getCurrencies().get(0).getBuyValue() > price){
                price = bank.getCurrencies().get(0).getBuyValue();
                result = bank;
            }
        }
        return result;
    }
}
