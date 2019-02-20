package com.exchanger.controller;

import com.exchanger.model.BanksInstance;
import com.exchanger.model.bank.Bank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final BanksInstance banks;

    public ApiController() {
        this.banks = BanksInstance.getInstance();
    }

    @GetMapping("/banks")
    public List<Bank> getBanks() {
        return banks.getBanksList();
    }

    @GetMapping("/best-buying")
    public HashMap<String, Bank> getBestBuyingBanks() {
        HashMap<String, Bank> response = new HashMap<>();
        response.put("EUR",banks.getBestBuyingPriceEUR());
        response.put("USD",banks.getBestBuyingPriceUSD());
        return response;
    }

    @GetMapping("/best-selling")
    public HashMap<String, Bank> getBestSellingBanks() {
        HashMap<String, Bank> response = new HashMap<>();
        response.put("EUR",banks.getBestSellingEUR());
        response.put("USD",banks.getBestSellingUSD());
        return response;
    }

    @GetMapping("/official-rate")
    public Bank getOfficialRate() {
        return banks.getCentralBank();
    }

    @GetMapping("/*")
    public HttpStatus error() {
        return HttpStatus.BAD_REQUEST;
    }
}
