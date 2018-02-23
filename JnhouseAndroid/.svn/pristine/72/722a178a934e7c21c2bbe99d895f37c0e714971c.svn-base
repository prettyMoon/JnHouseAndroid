package jnhouse.topwellsoft.com.jnhouse_android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.fragment.chat_fragment.ConversationListFragment;

/**
 * Created by DELL on 2016/8/17.
 */
public class ConversationActivity extends FragmentActivity {
    private ImageView img_back;
    private TextView tv_title;
    private FragmentManager fm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        img_back = (ImageView) this.findViewById(R.id.question_img_back);
        tv_title = (TextView) this.findViewById(R.id.tv_middle);
        tv_title.setText("私信");
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportFragmentManager().beginTransaction().add(R.id.conversation_container, new ConversationListFragment()).commit();
    }
}
