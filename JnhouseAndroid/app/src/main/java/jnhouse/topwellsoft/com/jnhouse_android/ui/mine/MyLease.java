package jnhouse.topwellsoft.com.jnhouse_android.ui.mine;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jnhouse.topwellsoft.com.jnhouse_android.R;

public class MyLease extends AppCompatActivity implements View.OnClickListener{
    private Button mButton_buy,mButton_rent,mButton_want,mButton_sell;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lease);
        mButton_buy = (Button) findViewById(R.id.mylease_buy_bt);
        mButton_rent = (Button) findViewById(R.id.mylease_rent_bt);
        mButton_want = (Button) findViewById(R.id.mylease_want_bt);
        mButton_sell = (Button) findViewById(R.id.mylease_sell_bt);

        mButton_buy.setOnClickListener(this);
        mButton_rent.setOnClickListener(this);
        mButton_want.setOnClickListener(this);
        mButton_sell.setOnClickListener(this);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE); // 画框
        drawable.setStroke(1, Color.GRAY);
        mButton_buy.setBackgroundDrawable(drawable);
        mButton_rent.setBackgroundDrawable(drawable);
        mButton_want.setBackgroundDrawable(drawable);
        mButton_sell.setBackgroundDrawable(drawable);

    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.mylease_buy_bt:
                intent.setClass(this, MineBuy.class);
                startActivity(intent);
                break;
            case R.id.mylease_rent_bt:
                intent.setClass(this,MineRent.class);
                startActivity(intent);
                break;
            case R.id.mylease_want_bt:
                intent.setClass(this,MineWant.class);
                startActivity(intent);
                break;
            case R.id.mylease_sell_bt:
                intent.setClass(this,MineSell.class);
                startActivity(intent);
                break;
        }

    }
}
