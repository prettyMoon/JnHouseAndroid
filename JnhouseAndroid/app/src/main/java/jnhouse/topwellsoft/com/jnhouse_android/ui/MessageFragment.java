package jnhouse.topwellsoft.com.jnhouse_android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.topwellsoft.androidutils.PreferencesUtils;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.login.TpLoginFragmentActivity;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

/**
 * Created by DELL on 2016/8/17.
 */
public class MessageFragment extends Fragment implements View.OnClickListener {
    private LinearLayout layout_privil, layout_order, layout_system;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        initView(view);

        return view;

    }

    public void initView(View view) {
        layout_order = (LinearLayout) view.findViewById(R.id.order_message);
        layout_privil = (LinearLayout) view.findViewById(R.id.layout_privil_message);
        layout_system = (LinearLayout) view.findViewById(R.id.system_message);
        layout_order.setOnClickListener(this);
        layout_privil.setOnClickListener(this);
        layout_system.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.order_message://订单消息
                break;
            case R.id.layout_privil_message://私信
                intent = new Intent(getActivity(), ConversationActivity.class);
                startActivity(intent);
                break;
            case R.id.system_message://系统消息
                break;
            default:
                break;
        }
    }
}