package android.exchanger.exchanger_android_client;

import android.exchanger.exchanger_android_client.model.BanksInstance;
import android.exchanger.exchanger_android_client.model.bank.Bank;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Exchanger $");

        Bank bank = BanksInstance.getInstance().getCentralBank();
        ((TextView) findViewById(R.id.mainEUR)).setText(String.format("EUR : " +
                bank.getCurrencies().get("EUR").getBuyValue().toString() + " RUB"));
        ((TextView) findViewById(R.id.mainUSD)).setText(String.format("USD : " +
                bank.getCurrencies().get("USD").getBuyValue().toString() + " RUB"));


        ViewPager pager= findViewById(R.id.pager);
        pager.setAdapter(new MainAdapter(this, getSupportFragmentManager()));
    }
}
