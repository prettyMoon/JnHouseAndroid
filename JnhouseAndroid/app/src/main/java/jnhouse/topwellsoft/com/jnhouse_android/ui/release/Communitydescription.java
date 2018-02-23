package jnhouse.topwellsoft.com.jnhouse_android.ui.release;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.util.EventUtil;

/**
 * Created by topwellsoft on 2016/6/29.
 */
public class Communitydescription extends AppCompatActivity {
    private EditText mEditText_description;
    private Button mButton_confirm,mButton_empty,mButton_back;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private final int REQUEST_CODE = 2;

    Handler mHandler = new Handler();
    Runnable mRunnable = new Runnable() {

        @Override
        public void run() {
            mEditText_description.setText("");

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_community_description);
        mButton_back = (Button) findViewById(R.id.rent_back_button);
        mEditText_description = (EditText) findViewById(R.id.community_description_et);
        mButton_confirm = (Button) findViewById(R.id.description_confirm_bt);
        mButton_empty = (Button) findViewById(R.id.description_empty_bt);

        mButton_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        mButton_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Communitydescription.this,"setOnClickListener",Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(Communitydescription.this,Lease.class);
                Fragment fragment=new Fragment();
                EventBus.getDefault().post(new EventUtil(mEditText_description.getText().toString()));
               onBackPressed();

            }
        });

        mButton_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandler.post(mRunnable);
            }
        });
    }

}
