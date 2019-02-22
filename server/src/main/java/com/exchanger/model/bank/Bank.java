package com.exchanger.model.bank;

import com.exchanger.model.Currency;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public interface Bank {

    static String sendRequest(String inpUrl) {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(inpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int code = connection.getResponseCode();

            if (code == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf8"));
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                connection.disconnect();
            }
        } catch (Exception ex){
            response.append(ex.getMessage());
        }
        return response.toString();
    }

    static void getResponse(String url, Bank bank){
        String response = Bank.sendRequest(url);
        bank.getCurrencies().clear();
        try {
            Object obj = new JSONParser().parse(response);
            bank.getCurrencies().add(bank.createEUR(obj));
            bank.getCurrencies().add(bank.createUSD(obj));

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    void update();

    Currency createEUR(Object obj);

    Currency createUSD(Object obj);

    List<Currency> getCurrencies();

    String getName();
}
