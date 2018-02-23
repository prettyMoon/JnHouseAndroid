package com.topwellsoft.jnhouse_android.visit_schedule;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;

import butterknife.Bind;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

/**
 * Created by admin on 2016/5/23.
 */
public class VisitScheduleFragment extends Fragment implements View.OnClickListener {

    View view;

    RadioButton button1;

    RadioButton button2;

    private FragmentManager fm;
    private HouseCalendarFragment onefragment;
    private HouseHistoryFragment twofragment;

    FragmentActivity parentContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.visit_schedule_layout, container, false);
        button1 = (RadioButton) view.findViewById(R.id.button31);
        button2 = (RadioButton) view.findViewById(R.id.button32);

        onefragment = new HouseCalendarFragment();
        twofragment = new HouseHistoryFragment();

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);


        button1.setChecked(true);




        fm = parentContainer.getSupportFragmentManager();
        fm.beginTransaction().add(R.id.calendar_container, onefragment).commit();


        return view;
    }
    @Override
    public void onAttach(Activity activity) {
        parentContainer=(FragmentActivity) activity;
        super.onAttach(activity);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button31:
                fm.beginTransaction().replace(R.id.calendar_container, onefragment).commit();
                break;
            case R.id.button32:
                fm.beginTransaction().replace(R.id.calendar_container, twofragment).commit();
                break;

            default:
                break;

        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void initEvent() {

    }


    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onPause() {

        super.onPause();
    }

    public void showToast(String str) {
        ToastUtil.makeText(getActivity(), str, ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
    }


}
