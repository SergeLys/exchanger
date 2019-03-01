package android.exchanger.exchanger_android_client;

import android.content.Context;
import android.exchanger.exchanger_android_client.model.BanksInstance;
import android.exchanger.exchanger_android_client.model.bank.Bank;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
            result = inflater.inflate(R.layout.activity_best, container, false);
            Bank bank = BanksInstance.getInstance().getBestBuyingPriceEUR();
            ((TextView)result.findViewById(R.id.bestBuyingEUR)).setText(String.format(
                    "Bank " + bank.getName() + " : " +
                            bank.getCurrencies().get("EUR").getBuyValue().toString() + " RUB"));
            bank = BanksInstance.getInstance().getBestBuyingPriceUSD();
            ((TextView)result.findViewById(R.id.bestBuyingUSD)).setText(String.format(
                    "Bank " + bank.getName() + " : " +
                            bank.getCurrencies().get("USD").getBuyValue().toString() + " RUB"));
            bank = BanksInstance.getInstance().getBestSellingEUR();
            ((TextView)result.findViewById(R.id.bestSellingEUR)).setText(String.format(
                    "Bank " + bank.getName() + " : " +
                            bank.getCurrencies().get("EUR").getSellValue().toString() + " RUB"));
            bank = BanksInstance.getInstance().getBestSellingUSD();
            ((TextView)result.findViewById(R.id.bestSellingUSD)).setText(String.format(
                    "Bank " + bank.getName() + " : " +
                            bank.getCurrencies().get("USD").getSellValue().toString() + " RUB"));

        }

        return result;
    }
}
