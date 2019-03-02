package android.exchanger.exchanger_android_client;

import android.annotation.SuppressLint;
import android.content.Context;
import android.exchanger.exchanger_android_client.model.bank.Bank;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BankAdapter extends ArrayAdapter<Bank> {

    private LayoutInflater inflater;
    private int layout;
    private List<Bank> banks;

    public BankAdapter(Context context, int resource, List<Bank> banks) {
        super(context, resource, banks);
        this.banks = banks;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    @SuppressLint("SetTextI18n")
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        TextView nameView = view.findViewById(R.id.name);
        TextView buyUsdView = view.findViewById(R.id.buyUSD);
        TextView buyEurView = view.findViewById(R.id.buyEUR);
        TextView sellUsdView = view.findViewById(R.id.sellUSD);
        TextView sellEurView = view.findViewById(R.id.sellEUR);

        Bank bank = banks.get(position);

        nameView.setText("Bank " + bank.getName());
        buyUsdView.setText("Buying USD: " + bank.getCurrencies().get("USD").getBuyValue().toString() +" RUB");
        sellUsdView.setText("Selling USD: " + bank.getCurrencies().get("USD").getSellValue().toString() + " RUB");
        buyEurView.setText("Buying EUR: " +bank.getCurrencies().get("EUR").getBuyValue().toString() + " RUB");
        sellEurView.setText("Selling EUR: " +bank.getCurrencies().get("EUR").getSellValue().toString() + " RUB");

        return view;
    }
}
