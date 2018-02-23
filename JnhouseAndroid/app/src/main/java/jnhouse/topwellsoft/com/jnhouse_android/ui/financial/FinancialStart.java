package jnhouse.topwellsoft.com.jnhouse_android.ui.financial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import jnhouse.topwellsoft.com.jnhouse_android.R;

/**
 * Created by chenchen on 2016/8/29.
 */
public class FinancialStart extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.financial_start_layout);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3*1000);
                    startActivity(new Intent(FinancialStart.this,FinancialEnd.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                finish();
            }
        }).start();
    }
}
