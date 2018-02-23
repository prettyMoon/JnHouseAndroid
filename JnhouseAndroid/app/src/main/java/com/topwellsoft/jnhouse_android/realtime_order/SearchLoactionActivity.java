package com.topwellsoft.jnhouse_android.realtime_order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

/**
 * Created by Administrator on 2016/8/8.
 */
public class SearchLoactionActivity extends Activity {
    private PoiSearch mPoiSearch;
    private PoiSearch mPoiSearch2;
    private ImageView img_back;
    private EditText editText;
    private TextView tv_search;
    private ListView list_location;
    private int number = 10;
    private ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
    private LoactionAdapter adapter;
    private String city = "";
    private double lati;
    private double longi;
    private MyDiaLog diaLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location);
        initView();
        initDiaog();
        lati = getIntent().getDoubleExtra("lati", 36.400);
        longi = getIntent().getDoubleExtra("longi", 117.000);
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(poiListener);
//        searchNeayBy();
        mPoiSearch.searchInCity((new PoiCitySearchOption())
                .city(getCity(city))
                .keyword("美食")
                .pageNum(20));
        list_location.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("addr", adapter.getItem(position).get("addr").toString());
                intent.putExtra("lati", adapter.getItem(position).get("lati").toString());
                intent.putExtra("longi", adapter.getItem(position).get("longi").toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public void initDiaog() {
        diaLog = new MyDiaLog(SearchLoactionActivity.this);
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(true);
    }

    public void initView() {
        img_back = (ImageView) this.findViewById(R.id.search_img_back);
        editText = (EditText) this.findViewById(R.id.edit_middle);
        tv_search = (TextView) this.findViewById(R.id.search_right);
        list_location = (ListView) this.findViewById(R.id.list_location);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().equals("")) {
                    ToastUtil.makeText(SearchLoactionActivity.this, "请输入检索地点", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                } else {
                    Log.i("#####", "检索  city" + city);
                    diaLog.show();
                    mPoiSearch.searchInCity((new PoiCitySearchOption())
                            .city(getCity(city))
                            .keyword(editText.getText().toString())
                            .pageNum(20));
                }
            }
        });
    }

    private void searchNeayBy() {
        PoiNearbySearchOption option = new PoiNearbySearchOption();
        option.keyword("写字楼");
        option.sortType(PoiSortType.distance_from_near_to_far);
        Log.i("#####", "lati： " + lati);
        Log.i("#####", "longi： " + longi);
        //option.location(new LatLng(lati, longi));
        option.location(new LatLng(lati, longi));
        option.location(new LatLng(36.400, 117.000));
        option.pageCapacity(20);
        option.radius(1000);
        mPoiSearch2 = PoiSearch.newInstance();
        mPoiSearch2.setOnGetPoiSearchResultListener(poiListener);
        mPoiSearch2.searchNearby(option);
    }

    OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {
        public void onGetPoiResult(PoiResult result) {
            //获取POI检索结果
            if (result == null || result.getAllPoi() == null) {
                Log.i("#####", "123123");
                return;
            }
            city = result.getAllPoi().get(0).city;
            list.clear();
            for (int i = 0; i < (result.getAllPoi().size() > 15 ? 15 : result.getAllPoi().size()); i++) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("addr", result.getAllPoi().get(i).name);
                map.put("desc", result.getAllPoi().get(i).address);
                map.put("lati", result.getAllPoi().get(i).location.latitude + "");
                map.put("longi", result.getAllPoi().get(i).location.longitude + "");
                list.add(map);
            }
            adapter = new LoactionAdapter(SearchLoactionActivity.this, list);
            list_location.setAdapter(adapter);
            if (diaLog.isShowing()) diaLog.dismiss();
        }

        public void onGetPoiDetailResult(PoiDetailResult result) {
            //获取Place详情页检索结果
        }


        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }
    };

    public String getCity(String str) {
        if (str == null || str.equals("")) {
            return "济南";
        } else {
            return str;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }


}