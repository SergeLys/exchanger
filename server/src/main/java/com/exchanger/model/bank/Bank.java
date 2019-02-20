package com.exchanger.model.bank;

import com.exchanger.model.Currency;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public interface Bank {

    static String sendRequest(String httpUrl) {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(httpUrl);
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

    void update();

    Currency getEUR(Object obj);

    Currency getUSD(Object obj);
}
