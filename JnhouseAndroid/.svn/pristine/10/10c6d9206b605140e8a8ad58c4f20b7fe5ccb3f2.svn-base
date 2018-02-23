package jnhouse.topwellsoft.com.jnhouse_android.ui.release;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.TabHost;

import com.topwellsoft.androidutils.StyleUtils;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.util.TabManager;

public class Sell extends FragmentActivity implements View.OnClickListener {
    private RadioButton radio_tab_second, radio_tab_office,radio_tab_store;
    private TabManager mTabManager;
    private TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sell);
        initView();
        StyleUtils.initStatusBar(getWindow());
        if (getIntent() != null) {
            Log.i("###", "Lease_getIntent!=null");
            int id = getIntent().getIntExtra("id", 3);
            Log.i("###", "id = " + id);
            if (id == 0) {
                mTabHost.setCurrentTab(0);
                radio_tab_second.setChecked(true);
                radio_tab_office.setChecked(false);
                radio_tab_store.setChecked(false);
            } else {
                if (id == 1) {
                    mTabHost.setCurrentTab(1);
                    radio_tab_second.setChecked(false);
                    radio_tab_office.setChecked(true);
                    radio_tab_store.setChecked(false);
                } else {
                    if (id == 2) {
                        mTabHost.setCurrentTab(2);
                        radio_tab_second.setChecked(false);
                        radio_tab_office.setChecked(false);
                        radio_tab_store.setChecked(true);
                    }
                }
            }
        }
    }
 
    public void initView(){

        setContentView(R.layout.activity_sell);
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();
        mTabManager = new TabManager(this, mTabHost, android.R.id.tabcontent);
        mTabManager.addTab(mTabHost.newTabSpec("二手房出售").setIndicator(""),SellSecond.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("写字楼出售").setIndicator(""),SellOffice.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("商铺出售").setIndicator(""),SellStore.class, null);

        radio_tab_second = (RadioButton) findViewById(R.id.sell_second_rb);
        radio_tab_office = (RadioButton) findViewById(R.id.sell_office_rb);
        radio_tab_store = (RadioButton) findViewById(R.id.sell_store_rb);

        radio_tab_second.setChecked(true);
        radio_tab_second.setOnClickListener(this);
        radio_tab_office.setOnClickListener(this);
        radio_tab_store.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.sell_second_rb:

                mTabHost.setCurrentTab(0);
                radio_tab_second.setChecked(true);
                radio_tab_office.setChecked(false);
                radio_tab_store.setChecked(false);

                break;

            case R.id.sell_office_rb:

                mTabHost.setCurrentTab(1);
                radio_tab_second.setChecked(false);
                radio_tab_office.setChecked(true);
                radio_tab_store.setChecked(false);

                break;

            case R.id.sell_store_rb:

                mTabHost.setCurrentTab(2);
                radio_tab_second.setChecked(false);
                radio_tab_office.setChecked(false);
                radio_tab_store.setChecked(true);

                break;
        }
    }
}
