package jnhouse.topwellsoft.com.jnhouse_android.ui.question;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import jnhouse.topwellsoft.com.jnhouse_android.R;

/**
 * Created by Administrator on 2016/7/15.
 */
public class OperationDialog extends AlertDialog implements View.OnClickListener {
    private Button btn_left, btn_right;
    private TextView tv_content;
    private ButtonListener listener;
    public static final int LEFT = 0;
    public static final int RIGHT = 0;
    public static final int BOTH = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_operation_layout);
        btn_left = (Button) this.findViewById(R.id.btn_left);
        btn_right = (Button) this.findViewById(R.id.btn_right);
        tv_content = (TextView) this.findViewById(R.id.text_content);
        btn_right.setOnClickListener(this);
        btn_left.setOnClickListener(this);
    }

    public OperationDialog(Context context) {
        super(context);

    }

    public void setContent(String content) {
        tv_content.setText(content);
    }

    public void setBtn_left(String content) {
        btn_left.setText(content);
    }

    public void setBtn_right(String content) {
        btn_right.setText(content);
    }

    public void setListener(ButtonListener l) {
        this.listener = l;
    }

    public void setButtonVisible(int mode) {
        if (mode == LEFT) {
            btn_left.setVisibility(View.GONE);
        } else if (mode == RIGHT) {
            btn_right.setVisibility(View.GONE);
        } else if (mode == BOTH) {
            btn_left.setVisibility(View.GONE);
            btn_right.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left:
                if (listener != null)
                    listener.LeftListener();
                break;
            case R.id.btn_right:
                if (listener != null)
                    listener.RighttListener();
                break;
            default:
                break;
        }
    }

    public interface ButtonListener {
        public void LeftListener();

        public void RighttListener();

    }
}
