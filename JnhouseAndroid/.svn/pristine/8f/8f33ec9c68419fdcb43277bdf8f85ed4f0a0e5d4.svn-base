package jnhouse.topwellsoft.com.jnhouse_android.ui.decoration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.ui.MainTabsActivity;

public class Confirm extends AppCompatActivity {
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        mButton = (Button) findViewById(R.id.confirm_button);
    }

    public void Click(View view){
        switch (view.getId()){
            case R.id.confirm_button:
                Intent intent = new Intent();
                intent.setClass(Confirm.this, MainTabsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}
