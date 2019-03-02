package android.exchanger.exchanger_android_client;

import android.exchanger.exchanger_android_client.model.BanksInstance;
import android.exchanger.exchanger_android_client.model.bank.Bank;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Exchanger $");
        new UpdateRangesTask().execute();

    }

    private class UpdateRangesTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            if (!isOnline()){
                doInBackground();
            }
            BanksInstance.getInstance();
            return null;
        }

        @Override
        protected void onPreExecute() {
            findViewById(R.id.centralBank).setVisibility(View.INVISIBLE);
            findViewById(R.id.pager).setVisibility(View.INVISIBLE);
            ProgressBar pb = findViewById(R.id.progressBar);
            pb.setIndeterminate(true);
            pb.setVisibility(ProgressBar.VISIBLE);
        }


        @Override
        protected void onPostExecute(Void aVoid) {

            Bank bank = BanksInstance.getInstance().getCentralBank();
            ((TextView) findViewById(R.id.mainEUR)).setText(String.format("EUR : " +
                    bank.getCurrencies().get("EUR").getBuyValue().toString() + " RUB"));
            ((TextView) findViewById(R.id.mainUSD)).setText(String.format("USD : " +
                    bank.getCurrencies().get("USD").getBuyValue().toString() + " RUB"));

            ViewPager pager= findViewById(R.id.pager);
            pager.setAdapter(new MainAdapter(getBaseContext(), getSupportFragmentManager()));
            ProgressBar pb = findViewById(R.id.progressBar);
            pb.setIndeterminate(false);
            pb.setVisibility(ProgressBar.INVISIBLE);
            findViewById(R.id.centralBank).setVisibility(View.VISIBLE);
            findViewById(R.id.pager).setVisibility(View.VISIBLE);
        }
    }

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {

            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);

        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }
}
