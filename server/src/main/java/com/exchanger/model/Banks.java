package com.exchanger.model;

import com.exchanger.model.bank.AlfaBank;
import com.exchanger.model.bank.Bank;
import com.exchanger.model.bank.Sberbank;
import com.exchanger.model.bank.VtbBank;

import java.util.ArrayList;
import java.util.List;

public final class Banks {

    private static Banks instance;
    private List<Bank> banks;

    private Banks(){
        banks = new ArrayList<>();
        banks.add(new Sberbank());
        banks.add(new AlfaBank());
        banks.add(new VtbBank());

    }

    public static Banks getInstance() {
        if(instance == null){
            instance = new Banks();
        }
        return instance;
    }

    public List<Bank> getBanksList(){
        return this.banks;
    }
}
