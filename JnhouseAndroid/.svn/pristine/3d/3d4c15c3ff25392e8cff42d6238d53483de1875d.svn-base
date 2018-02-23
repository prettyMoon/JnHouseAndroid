package jnhouse.topwellsoft.com.jnhouse_android.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;

import com.topwellsoft.androidutils.StyleUtils;
import com.topwellsoft.jnhouse_android.map_find_house.MapRentHouse;
import com.topwellsoft.jnhouse_android.map_find_house.MapSecondHouse;

import java.util.ArrayList;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.ui.release.SellStore;
import jnhouse.topwellsoft.com.jnhouse_android.util.TabManager;


public class loan_counter extends FragmentActivity implements View.OnClickListener {

    private RadioButton radio_tab_gjj, radio_tab_sy, radio_tab_zh;
    private TabManager mTabManager;
    private TabHost mTabHost;
    private ImageView icon_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sell);
        initView();
        StyleUtils.initStatusBar(getWindow());
    }



    public void initView() {

        setContentView(R.layout.loan_main);
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);

        icon_back = (ImageView) findViewById(R.id.icon_back);
        icon_back.setOnClickListener(this);
        mTabHost.setup();
        mTabManager = new TabManager(this, mTabHost, android.R.id.tabcontent);
        mTabManager.addTab(mTabHost.newTabSpec("公积金贷款").setIndicator(""), Loan_Counter_Gj.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("商业贷款").setIndicator(""), Loan_Counter_Sy.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("组合贷款").setIndicator(""), Loan_Counter_Zh.class, null);
        radio_tab_gjj = (RadioButton) findViewById(R.id.loan_tab_gjj);
        radio_tab_sy = (RadioButton) findViewById(R.id.loan_tab_sy);
        radio_tab_zh = (RadioButton) findViewById(R.id.loan_tab_zh);
        radio_tab_gjj.setChecked(true);
        radio_tab_gjj.setOnClickListener(this);
        radio_tab_zh.setOnClickListener(this);
        radio_tab_sy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.loan_tab_gjj:

                mTabHost.setCurrentTab(0);
                radio_tab_zh.setChecked(false);
                radio_tab_gjj.setChecked(true);
                radio_tab_sy.setChecked(false);

                break;

            case R.id.loan_tab_sy:

                mTabHost.setCurrentTab(1);
                radio_tab_sy.setChecked(true);
                radio_tab_gjj.setChecked(false);
                radio_tab_zh.setChecked(false);

                break;

            case R.id.loan_tab_zh:

                mTabHost.setCurrentTab(2);
                radio_tab_sy.setChecked(false);
                radio_tab_gjj.setChecked(false);
                radio_tab_zh.setChecked(true);

                break;
            case R.id.icon_back:

                onBackPressed();
                break;
        }
    }

}
