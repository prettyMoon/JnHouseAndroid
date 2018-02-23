package jnhouse.topwellsoft.com.jnhouse_android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.io.IOException;

import jnhouse.topwellsoft.com.jnhouse_android.R;
/**
 * Created by admin on 2016/6/7.
 */
public class StartActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                try {
                    isFirst();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 2 * 1000);

    }

    public void isFirst() throws IOException {

//        if (info != null) {
            Intent intent = new Intent();
            intent.setClass(this, MainTabsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
       /* } else {
            Intent intent = new Intent();
            intent.setClass(StartActivity.this, TpLoginFragmentActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }*/
    }
}
