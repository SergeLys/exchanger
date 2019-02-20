package com.exchanger.controller;

import com.exchanger.model.bank.AlfaBank;
import com.exchanger.model.bank.Bank;
import com.exchanger.model.bank.Sberbank;
import com.exchanger.model.Banks;
import com.exchanger.model.bank.VtbBank;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final Banks banks;

    public ApiController() {
        this.banks = Banks.getInstance();
    }

    @GetMapping("/banks")
    public List<Bank> getBanks() {
        return banks.getBanksList();
    }

    @GetMapping("/test")
    public Bank test() {
        return new VtbBank();
    }
}
