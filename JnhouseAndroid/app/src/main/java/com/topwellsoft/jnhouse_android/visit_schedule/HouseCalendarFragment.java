package com.topwellsoft.jnhouse_android.visit_schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.PreferencesUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.calendar.CalendarAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.ui.calendar.CalendarDetailActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.calendar.CalendarEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.calendar.CalendarListEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.login.TpLoginFragmentActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;
import jnhouse.topwellsoft.com.jnhouse_android.view.SmoothListView.SmoothListView;

/**
 * Created by Administrator on 2016/8/10.
 */
public class HouseCalendarFragment extends Fragment implements SmoothListView.ISmoothListViewListener {
    private SmoothListView listView;
    private MyDiaLog diaLog;
    private CalendarListEntity calendarListEntity;
    private int page = 1;
    private CalendarAdapter adapter;
    private ArrayList<CalendarEntity> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.visit_schedule_inner_fragment, container, false);
        listView = (SmoothListView) view.findViewById(R.id.list_calendar);
        listView.setLoadMoreEnable(true);
        listView.setRefreshEnable(true);
        listView.setSmoothListViewListener(this);
        initDialog();
        getCalendarList(JnHouse_Record.House_schedule_list_u, 0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CalendarDetailActivity.class);
                intent.putExtra("id", adapter.getItem(position-1).getId());
                startActivity(intent);
            }
        });
        return view;
    }

    private void initDialog() {
        diaLog = new MyDiaLog(getActivity());
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(true);
        diaLog.show();
    }


    public void getCalendarList(String url, final int flag) {
        LoginEntity loginEntity = (LoginEntity) PreferencesUtils.getObject(getActivity(), "loginEntity");
        if (loginEntity == null || loginEntity.getUserUUID() == null) {
            ToastUtil.makeText(getActivity(), "请重新登录", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
            Intent intent =new Intent();
            intent.setClass(getActivity(), TpLoginFragmentActivity.class);
            startActivity(intent);
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put("user_id", loginEntity.getUser_id());
        params.put("type", "0");
        params.put("pageIndex", page + "");
        params.put("userUUID", loginEntity.getUserUUID());
        FinalHttp fh = new FinalHttp();
        fh.get(url, params, new AjaxCallBack<Object>() {
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("####", "onFailure");
                if (diaLog.isShowing()) {
                    diaLog.dismiss();
                }
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(Object t) {
                Log.i("#1###", t.toString());
                try {
                    JSONObject jsonObject = new JSONObject(t.toString());
                    Gson gson = new Gson();
                    calendarListEntity = gson.fromJson(t.toString(), new TypeToken<CalendarListEntity>() {
                    }.getType());
                    if (calendarListEntity != null) {
                        switch (calendarListEntity.getCode()) {
                            case 1:
                                showToast("您未登录");
                                break;
                            case -1:
                                showToast("异常");
                                break;
                            case 1301:
                                showToast("列表为空");
                                break;
                            case 0:
                                if (flag == 0) {//首次请求
                                    list = calendarListEntity.getDataList();
                                    adapter = new CalendarAdapter(getActivity(), list);
                                    listView.setAdapter(adapter);
                                }
                                if (flag == 1) {//下拉刷新
                                    list.clear();
                                    list = calendarListEntity.getDataList();
                                    adapter = new CalendarAdapter(getActivity(), list);
                                    listView.setAdapter(adapter);
                                    listView.stopRefresh();
                                }
                                if (flag == 2) {//上拉加载
                                    for (int i = 0; i < calendarListEntity.getDataList().size(); i++) {
                                        list.add(calendarListEntity.getDataList().get(i));
                                    }
                                    adapter.notifyDataSetChanged();
                                    listView.stopLoadMore();
                                }

                                break;
                            default:
                                break;
                        }
                    }

                    if (diaLog.isShowing()) {
                        diaLog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showToast(String str) {
        ToastUtil.makeText(getActivity(), str, ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
    }

    @Override
    public void onRefresh() {
        page = 1;
        getCalendarList(JnHouse_Record.House_schedule_list_u, 1);
    }

    @Override
    public void onLoadMore() {
        page++;
        getCalendarList(JnHouse_Record.House_schedule_list_u, 2);
    }
}
