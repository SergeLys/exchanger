package android.exchanger.exchanger_android_client;

import android.content.Context;
import android.exchanger.exchanger_android_client.model.BanksInstance;
import android.exchanger.exchanger_android_client.model.bank.Bank;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainFragment extends Fragment {

    private int pageNumber;

    public static MainFragment newInstance(int page) {
        MainFragment fragment = new MainFragment();
        Bundle args=new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    static String getTitle(Context context, int position) {
        if(position == 0){
            return  "Banks list";
        }
        if(position == 1){
            return  "The best exchange";
        }
        return "undefined";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = null;

        if(pageNumber == 0){
            result = inflater.inflate(R.layout.activity_banks_list, container, false);
            List<Bank> banks = BanksInstance.getInstance().getBanksList();
            ListView banksView = result.findViewById(R.id.banksList);
            BankAdapter bankAdapter = new BankAdapter(getContext(), R.layout.banks_list_item, banks);
            banksView.setAdapter(bankAdapter);
        }
        if(pageNumber == 1){
            final String buyValueEUR, buyValueUSD, sellValueUSD, sellValueEUR;

            result = inflater.inflate(R.layout.activity_best, container, false);
            Bank bank = BanksInstance.getInstance().getBestBuyingPriceEUR();
            buyValueEUR =bank.getCurrencies().get("EUR").getBuyValue().toString();
            ((TextView)result.findViewById(R.id.bestBuyingEUR)).setText(String.format(
                    "Bank " + bank.getName() + " : " + buyValueEUR  + " RUB"));

            bank = BanksInstance.getInstance().getBestBuyingPriceUSD();
            buyValueUSD = bank.getCurrencies().get("USD").getBuyValue().toString();
            ((TextView)result.findViewById(R.id.bestBuyingUSD)).setText(String.format(
                    "Bank " + bank.getName() + " : " + buyValueUSD + " RUB"));

            bank = BanksInstance.getInstance().getBestSellingEUR();
            sellValueEUR = bank.getCurrencies().get("EUR").getSellValue().toString();
            ((TextView)result.findViewById(R.id.bestSellingEUR)).setText(String.format(
                    "Bank " + bank.getName() + " : " +  sellValueEUR + " RUB"));

            bank = BanksInstance.getInstance().getBestSellingUSD();
            sellValueUSD = bank.getCurrencies().get("USD").getSellValue().toString();
            ((TextView)result.findViewById(R.id.bestSellingUSD)).setText(String.format(
                    "Bank " + bank.getName() + " : " + sellValueUSD + " RUB"));

            final EditText buyUSD = result.findViewById(R.id.toBuyUSD);
            final TextView resultBuyUSD = result.findViewById(R.id.resultBuyUSD);
            buyUSD.setOnKeyListener((v, keyCode, event) -> {
                if(event.getAction() == KeyEvent.ACTION_DOWN &&
                        (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    String buffer = getBuyingResult(buyUSD.getText().toString(), sellValueUSD)
                            + " USD";
                    resultBuyUSD.setText(buffer);
                    return true;
                }
                return false;
            }
            );

            final EditText buyEUR = result.findViewById(R.id.toBuyEUR);
            final TextView resultBuyEUR = result.findViewById(R.id.resultBuyEUR);
            buyEUR.setOnKeyListener((v, keyCode, event) -> {
                        if(event.getAction() == KeyEvent.ACTION_DOWN &&
                                (keyCode == KeyEvent.KEYCODE_ENTER))
                        {
                            String buffer = getBuyingResult(buyEUR.getText().toString(), sellValueEUR)
                                    + " EUR";
                            resultBuyEUR.setText(buffer);
                            return true;
                        }
                        return false;
                    }
            );

            final EditText sellUSD = result.findViewById(R.id.toSellUSD);
            final TextView resultSellUSD = result.findViewById(R.id.resultSellUSD);
            sellUSD.setOnKeyListener((v, keyCode, event) -> {
                        if(event.getAction() == KeyEvent.ACTION_DOWN &&
                                (keyCode == KeyEvent.KEYCODE_ENTER))
                        {
                            String buffer = getSellingResult(sellUSD.getText().toString(),
                                    buyValueUSD) + " RUB";
                            resultSellUSD.setText(buffer);
                            return true;
                        }
                        return false;
                    }
            );

            final EditText sellEUR = result.findViewById(R.id.toSellEUR);
            final TextView resultSellEUR = result.findViewById(R.id.resultSellEUR);
            sellEUR.setOnKeyListener((v, keyCode, event) -> {
                        if(event.getAction() == KeyEvent.ACTION_DOWN &&
                                (keyCode == KeyEvent.KEYCODE_ENTER))
                        {
                            String buffer = getSellingResult(sellEUR.getText().toString(),
                                    buyValueEUR) + " RUB";
                            resultSellEUR.setText(buffer);
                            return true;
                        }
                        return false;
                    }
            );

        }

        return result;
    }

    private String getSellingResult(String inputValue, String coefficient){
        String result;
        try{
            Double variable = Double.valueOf(inputValue) * Double.valueOf(coefficient);
            result =  String.valueOf(Math.round(variable * 100.0) / 100.0);
        }
        catch (Exception ex){
            result = ex.getMessage();
        }
        return result;
    }

    private String getBuyingResult(String inputValue, String coefficient){
        String result;
        try{
            Double variable = Double.valueOf(inputValue) / Double.valueOf(coefficient);
            result =  String.valueOf(Math.round(variable * 100.0) / 100.0);
        }
        catch (Exception ex){
            result = ex.getMessage();
        }
        return result;
    }
}
