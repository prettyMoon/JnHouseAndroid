package jnhouse.topwellsoft.com.jnhouse_android.ui.release;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.TabHost;

import com.topwellsoft.androidutils.StyleUtils;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.util.TabManager;

public class Lease extends FragmentActivity implements View.OnClickListener {
    private RadioButton radio_tab_entire, radio_tab_share,radio_tab_short;
    private TabManager mTabManager;
    private TabHost mTabHost;
    private Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lease);
        initView();
        StyleUtils.initStatusBar(getWindow());
        if (getIntent() != null){
            Log.i("###","Lease_getIntent!=null");
            int id = getIntent().getIntExtra("id",3);
            Log.i("###","id = " + id);
            if (id == 0){
                mTabHost.setCurrentTab(0);
                radio_tab_entire.setChecked(true);
                radio_tab_share.setChecked(false);
                radio_tab_short.setChecked(false);
            }else {
                if (id == 1){
                    mTabHost.setCurrentTab(1);
                    radio_tab_entire.setChecked(false);
                    radio_tab_share.setChecked(true);
                    radio_tab_short.setChecked(false);
                }else {
                    if (id == 2){
                        mTabHost.setCurrentTab(2);
                        radio_tab_entire.setChecked(false);
                        radio_tab_share.setChecked(false);
                        radio_tab_short.setChecked(true);
                    }
                }
            }
        }
    }



    public void initView(){

        setContentView(R.layout.activity_lease);
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();
        mTabManager = new TabManager(this, mTabHost, android.R.id.tabcontent);
        mTabManager.addTab(mTabHost.newTabSpec("发布整租").setIndicator(""),LeaseEntire.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("发布合租").setIndicator(""),LeaseShare.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("发布短租").setIndicator(""),LeaseShort.class, null);

        radio_tab_entire = (RadioButton) findViewById(R.id.lease_entire_rb);
        radio_tab_share = (RadioButton) findViewById(R.id.lease_share_rb);
        radio_tab_short = (RadioButton) findViewById(R.id.lease_short_rb);

        radio_tab_entire.setChecked(true);
        radio_tab_entire.setOnClickListener(this);
        radio_tab_share.setOnClickListener(this);
        radio_tab_short.setOnClickListener(this);
        FragmentManager fragmentManager=getSupportFragmentManager();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.lease_entire_rb:

                mTabHost.setCurrentTab(0);
                radio_tab_entire.setChecked(true);
                radio_tab_share.setChecked(false);
                radio_tab_short.setChecked(false);

                break;

            case R.id.lease_share_rb:

                mTabHost.setCurrentTab(1);
                radio_tab_entire.setChecked(false);
                radio_tab_share.setChecked(true);
                radio_tab_short.setChecked(false);

                break;

            case R.id.lease_short_rb:

                mTabHost.setCurrentTab(2);
                radio_tab_entire.setChecked(false);
                radio_tab_share.setChecked(false);
                radio_tab_short.setChecked(true);

                break;
        }
    }



}
