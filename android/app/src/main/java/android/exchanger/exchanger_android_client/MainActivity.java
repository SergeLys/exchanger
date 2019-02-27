package android.exchanger.exchanger_android_client;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Exchanger $");

        ViewPager pager= findViewById(R.id.pager);
        pager.setAdapter(new MainAdapter(this, getSupportFragmentManager()));
    }
}
