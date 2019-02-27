package android.exchanger.exchanger_android_client;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainAdapter extends FragmentPagerAdapter {

    private Context context;

    public MainAdapter(Context context, FragmentManager mgr) {
        super(mgr);
        this.context = context;
    }
    @Override
    public int getCount() {
        return(2);
    }

    @Override
    public String getPageTitle(int position) {
        return (MainFragment.getTitle(context, position));
    }

    @Override
    public Fragment getItem(int position) {
        return(MainFragment.newInstance(position));
    }
}
