package jnhouse.topwellsoft.com.jnhouse_android.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import jnhouse.topwellsoft.com.jnhouse_android.R;

/**
 * Created by admin on 2016/5/23.
 */
public class TpMessageFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tp_message_fragment, container,
                false);
        return view;
    }


}
