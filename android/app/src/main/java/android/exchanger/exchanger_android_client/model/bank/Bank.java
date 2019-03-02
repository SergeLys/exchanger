package android.exchanger.exchanger_android_client.model.bank;

import android.exchanger.exchanger_android_client.model.Currency;

import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public interface Bank {

    static String sendRequest(String inpUrl) {

        StringBuilder response = new StringBuilder();

                    try {
                        URL url = new URL(inpUrl);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.connect();
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
            bank.getCurrencies().put("EUR", bank.createEUR(obj));
            bank.getCurrencies().put("USD", bank.createUSD(obj));

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    void update();

    Currency createEUR(Object obj);

    Currency createUSD(Object obj);

    HashMap<String, Currency> getCurrencies();

    String getName();
}
