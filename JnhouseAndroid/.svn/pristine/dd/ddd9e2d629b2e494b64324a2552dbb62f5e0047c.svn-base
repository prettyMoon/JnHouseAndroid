package jnhouse.topwellsoft.com.jnhouse_android.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import jnhouse.topwellsoft.com.jnhouse_android.R;

public class Register extends AppCompatActivity {
   private EditText phone;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        phone=(EditText) findViewById(R.id.phone);
        register=(Button) findViewById(R.id.register);
    }



    public static boolean isMobileNO(String mobiles) {

        String telRegex = "[1][3578]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }
      public void verification (View v){
              if (isMobileNO(phone.getText().toString())==true){
                  register.setText("1111");
              }else {
                  register.setText("222");
              }
    }
}
