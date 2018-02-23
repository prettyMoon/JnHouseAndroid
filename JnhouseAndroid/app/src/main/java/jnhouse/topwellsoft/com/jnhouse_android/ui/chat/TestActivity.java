package jnhouse.topwellsoft.com.jnhouse_android.ui.chat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import jnhouse.topwellsoft.com.jnhouse_android.R;

/**
 * Created by Administrator on 2016/7/21.
 */
public class TestActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_test);
        final EditText edit = (EditText) this.findViewById(R.id.edit_aaa);
        this.findViewById(R.id.btn_aaa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = edit.getText().toString();
                EmChatHelper.startChatActivity(TestActivity.this, str,"notlist");
            }
        });
    }
}