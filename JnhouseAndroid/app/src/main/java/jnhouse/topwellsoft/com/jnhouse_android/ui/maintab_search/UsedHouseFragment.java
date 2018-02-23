package jnhouse.topwellsoft.com.jnhouse_android.ui.maintab_search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.NewHouseAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.SecondHouseAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.NewHouseEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.NewHouseList;
import jnhouse.topwellsoft.com.jnhouse_android.model.SecondHouseEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.SecondHouseList;
import jnhouse.topwellsoft.com.jnhouse_android.ui.new_house.IndexNewHouseDetail;
import jnhouse.topwellsoft.com.jnhouse_android.util.DateTimeUtils;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;
import jnhouse.topwellsoft.com.jnhouse_android.view.SmoothListView.SmoothListView;

/**
 * Created by Administrator on 2016/7/28.
 */
public class UsedHouseFragment extends Fragment implements SmoothListView.ISmoothListViewListener {
    private SmoothListView smoothListView;
    private SecondHouseAdapter mAdapter; // 主页数据
    private int pageIndex = 1;
    private List<SecondHouseEntity> datalist = new ArrayList<SecondHouseEntity>();
    public Handler threeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 1000) {
//                MainTabSearchActivity.diaLog.show();
                getFirstData(JnHouse_Record.Key_second_home_index);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.maintab_search_list, container, false);
        initView(view);
        return view;
    }

    public void initView(View view) {
        smoothListView = (SmoothListView) view.findViewById(R.id.maintab_listView);
        smoothListView.setSmoothListViewListener(this);
        smoothListView.setLoadMoreEnable(true);
        smoothListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SecondHouseEntity newHouseEntity = mAdapter.getItem(i - 1);
                Intent intent = new Intent();
                intent.putExtra("houseId", newHouseEntity.getId());
                intent.setClass(getActivity(), IndexNewHouseDetail.class);
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

//        getFirstData(JnHouse_Record.Key_second_home_index);
    }

    public void getFirstData(String url) {
        Log.i("####", "UsedHouseFragment");
        MainTabSearchActivity.diaLog.show();
        AjaxParams params = new AjaxParams();
        params.put("pageIndex", pageIndex + "");
        params.put("key_name", MainTabSearchActivity.key);//关键字检索
//        params.put("key_name", "");//关键字检索
        params.put("area_id", "");//城区
        params.put("trade_id", "");//商圈
        params.put("borough_avgprice", "");//价格
        params.put("price_min", "");//最小
        params.put("price_max", "");//最大
        params.put("house_room", "");//房型
        params.put("house_feature", "");//房源特色(带,字符串)
        params.put("list_sort", "");//排序
        params.put("house_toward", "");//朝向
        params.put("house_type", "");//类型
        params.put("house_line", "");//环线
        params.put("borough_properties", "");//开盘状态
        FinalHttp fp = new FinalHttp();
        fp.get(url, params, new AjaxCallBack<Object>() {
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("####", "onFailure");
                if (MainTabSearchActivity.diaLog.isShowing())
                    MainTabSearchActivity.diaLog.dismiss();
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
            }

            @Override
            public void onSuccess(Object o) {
                Log.i("####", "UsedHouseFragment");
                try {
                    JSONObject json = new JSONObject(o.toString());
                    Gson gson = new Gson();
                    SecondHouseList secondHouseList = gson.fromJson(o.toString(), new TypeToken<SecondHouseList>() {
                    }.getType());
                    datalist = secondHouseList.getData_list();
                    if (datalist != null) {
                        mAdapter = new SecondHouseAdapter(getActivity(), datalist);
                        smoothListView.setAdapter(mAdapter);
                    } else {
                        ToastUtil.makeText(getActivity(), "没有符合要求的房源", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                    }

                    if (MainTabSearchActivity.diaLog.isShowing())
                        MainTabSearchActivity.diaLog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getMoreData(String url) {

        AjaxParams params = new AjaxParams();
        params.put("pageIndex", pageIndex + "");
        params.put("key_name", MainTabSearchActivity.key);//关键字检索
        params.put("area_id", "");//城区
        params.put("trade_id", "");//商圈
        params.put("borough_avgprice", "");//价格
        params.put("price_min", "");//最小
        params.put("price_max", "");//最大
        params.put("house_room", "");//房型
        params.put("house_feature", "");//房源特色(带,字符串)
        params.put("list_sort", "");//排序
        params.put("house_toward", "");//朝向
        params.put("house_type", "");//类型
        params.put("house_line", "");//环线
        params.put("borough_properties", "");//开盘状态
        FinalHttp fp = new FinalHttp();
        fp.get(url, params, new AjaxCallBack<Object>() {
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
            }

            @Override
            public void onSuccess(Object o) {
                try {
                    JSONObject json = new JSONObject(o.toString());
                    Gson gson = new Gson();
                    SecondHouseList secondHouseList = gson.fromJson(o.toString(), new TypeToken<SecondHouseList>() {
                    }.getType());
                    for (int i = 0; i < secondHouseList.getData_list().size(); i++) {
                        datalist.add(secondHouseList.getData_list().get(i));
                    }
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                smoothListView.stopRefresh();
                smoothListView.setRefreshTime(DateTimeUtils.getTimeStr(DateTimeUtils.getNowTime(), "yyyy-MM-dd hh:mm:ss"));
                pageIndex = 1;
                datalist.clear();
                getFirstData(JnHouse_Record.Key_second_home_index);
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                smoothListView.stopLoadMore();
                pageIndex++;
                getMoreData(JnHouse_Record.Key_second_home_list);
            }
        }, 2000);
    }
}
