package jnhouse.topwellsoft.com.jnhouse_android.ui.release;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.topwellsoft.androidutils.PreferencesUtils;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.login.TpLoginFragmentActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.mine.MyLease;

public class Release extends AppCompatActivity implements View.OnClickListener {
    private Intent intent = new Intent();
    private Button mButton_sell, mButton_lease, mButton_buy, mButton_rent, mButton_check;
    private ImageView iconBack;

    //    private LoginEntity info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);

        mButton_sell = (Button) findViewById(R.id.relative_sell_bt);

        mButton_lease = (Button) findViewById(R.id.relative_lease_bt);

        mButton_buy = (Button) findViewById(R.id.relative_buy_bt);

        mButton_rent = (Button) findViewById(R.id.relative_rent_bt);

        mButton_check = (Button) findViewById(R.id.relative_check_bt);

        iconBack=(ImageView)( findViewById(R.id.icon_back));
        iconBack.setOnClickListener(this);
        mButton_sell.setOnClickListener(this);
        mButton_lease.setOnClickListener(this);
        mButton_buy.setOnClickListener(this);
        mButton_rent.setOnClickListener(this);
        mButton_check.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_sell_bt:
                LoginEntity info = (LoginEntity) PreferencesUtils.getObject(this, "loginEntity");
                if (info != null) {
                    //跳转到我要出售
                    intent.setClass(Release.this, Sell.class);
                    startActivity(intent);
                } else {
                    intent.setClass(this, TpLoginFragmentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }
                break;
            case R.id.relative_lease_bt:
                info = (LoginEntity) PreferencesUtils.getObject(this, "loginEntity");
                if (info != null) {
                    //跳转到我要出租
                    intent.setClass(Release.this, Lease.class);
                    startActivity(intent);
                } else {
                    intent.setClass(this, TpLoginFragmentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }

                break;
            case R.id.relative_buy_bt:
                info = (LoginEntity) PreferencesUtils.getObject(this, "loginEntity");

                if (info != null) {
                    //跳转到我要购房
                    intent.setClass(Release.this, Buy.class);
                    startActivity(intent);
                } else {
                    intent.setClass(this, TpLoginFragmentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                break;
            case R.id.relative_rent_bt:
                info = (LoginEntity) PreferencesUtils.getObject(this, "loginEntity");

                if (info != null) {
                    //跳转到我要租房
                    intent.setClass(Release.this, Rent.class);
                    startActivity(intent);
                } else {
                    intent.setClass(this, TpLoginFragmentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }

                break;
            case R.id.relative_check_bt:
                info = (LoginEntity) PreferencesUtils.getObject(this, "loginEntity");
                if (info != null) {
                    intent.setClass(Release.this, MyLease.class);
                    startActivity(intent);
                } else {
                    intent.setClass(this, TpLoginFragmentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                break;
            case R.id.icon_back:
                onBackPressed();
                break;
        }

    }
}
