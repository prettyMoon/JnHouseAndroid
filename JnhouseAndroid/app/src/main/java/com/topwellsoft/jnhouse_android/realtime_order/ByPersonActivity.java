package com.topwellsoft.jnhouse_android.realtime_order;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.GlobalObjects;
import com.topwellsoft.androidutils.PreferencesUtils;
import com.topwellsoft.baidumap.LocationCallback;
import com.topwellsoft.baidumap.TPLocationListener;
import com.topwellsoft.jnhouse_android.realtime_order.model.BrokerInfo;
import com.topwellsoft.network.NetworkUtils;
import com.topwellsoft.network.TpAjaxCallback;


import net.tsz.afinal.http.AjaxParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.MainTabsActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.agent.AgentCardActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;

import jnhouse.topwellsoft.com.jnhouse_android.util.Application.JnHouseApplication;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

/**
 * Created by Administrator on 2016/8/8.
 */
public class ByPersonActivity extends Activity implements View.OnClickListener {
    @Bind(R.id.mMapView)
    MapView mapView;
    private BaiduMap mBaiduMap;
    UiSettings mUiSettings;
    MapLoaded mapLoaded;
    public TPLocationListener myListener;
    private MarkerOptions markerOptions;
    private Marker marker;
    @Bind(R.id.layout_order)
    LinearLayout linearlayout_map;
    @Bind(R.id.layout_btn)
    LinearLayout layout_btn;
    /////
    @Bind(R.id.select_two)
    ImageView select_two;
    /////
    @Bind(R.id.question_img_back)
    ImageView img_back;
    @Bind(R.id.tv_middle)
    TextView tv_title;
    /////
    @Bind(R.id.select_used)
    TextView select_used;
    @Bind(R.id.select_new)
    TextView select_new;
    @Bind(R.id.select_rent)
    TextView select_rent;
    @Bind(R.id.demand)
    EditText demand;
    @Bind(R.id.location)
    TextView tv_location;

    @Bind(R.id.btn_order)
    Button btn_order;
    /////
    @Bind(R.id.tv_order)
    TextView tv_order;
    /////
    @Bind(R.id.btn_see1)
    Button btn_see1;
    @Bind(R.id.btn_see2)
    Button btn_see2;
    @Bind(R.id.btn_by_people)
    Button btn_by_people;
    @Bind(R.id.btn_by_house)
    Button btn_by_house;
    @Bind(R.id.btn_by_store)
    Button btn_by_store;
    private MyDiaLog diaLog;
    private TextView tv[] = new TextView[3];
    private float downX, downY;
    private float width, height;
    private float moveX, moveY;
    private float px, py;
    private int house_type = 1;


    List<BrokerInfo> aroundBrokerInfoList;
    List<Overlay> aroundBrokerInfoOverlayList;
    HashMap<Overlay, String> aroundBrokerLoginNameHashMap;
    HashMap<Overlay, String> aroundBrokerIdHashMap;

    private double latitude, longitude;
    private String order_lat, order_lng;
    private ChoseTimeDialog timeDialog;
    public static int SEARCH_LOCATION = 1000;
    private static boolean hastime = false;
    public static TextView see_time;
    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 1234) {
                if (msg.arg2 == 10) {
                    see_time.setText("随时看房");
                } else {
                    see_time.setText(ChoseTimeDialog.result_date[msg.arg2] + ChoseTimeDialog.time);
                }
                hastime = true;
            }
        }
    };

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_house);
        ButterKnife.bind(this);

        // 济房网，当前默认为济南
        LatLng p = new LatLng(36.67221, 117.0487851);
        MapStatus mMapStatus = new MapStatus.Builder().target(p).zoom(11).build();

        mBaiduMap = mapView.getMap();


        mUiSettings = mBaiduMap.getUiSettings();
        mUiSettings.setRotateGesturesEnabled(false);
        mUiSettings.setOverlookingGesturesEnabled(false);
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setZoomGesturesEnabled(true);
        aroundBrokerInfoList = new ArrayList<BrokerInfo>();
        aroundBrokerInfoOverlayList = new ArrayList<Overlay>();
        aroundBrokerLoginNameHashMap = new HashMap<Overlay, String>();
        aroundBrokerIdHashMap = new HashMap<Overlay, String>();
        MapStatusUpdate u = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.animateMapStatus(u);
        mapLoaded = new MapLoaded();
        myListener = BaiduLocationStartupService.locationListener;

        mBaiduMap.setOnMapLoadedCallback(mapLoaded);

        initEvent();
        init();
        initTimeDialog();
        initDiaog();
    }

    public void initDiaog() {
        diaLog = new MyDiaLog(ByPersonActivity.this);
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(true);
    }

    public void initTimeDialog() {
        timeDialog = new ChoseTimeDialog(ByPersonActivity.this);
        timeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        timeDialog.setCancelable(true);
        timeDialog.setListener(new ChoseTimeDialog.ButtonListener() {
            @Override
            public void SureListener() {
                Message message = new Message();
                message.arg1 = 1234;
                message.arg2 = timeDialog.pos;
                ByPersonActivity.handler.sendMessage(message);
                timeDialog.dismiss();
            }
        });
    }

    public void init() {
        linearlayout_map.setVisibility(View.GONE);
        tv_title.setText("以人找房");
        tv[0] = select_used;
        tv[1] = select_new;
        tv[2] = select_rent;
        width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
        height = getWindow().getWindowManager().getDefaultDisplay().getHeight();
        setTextState(0);
        select_two.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = event.getRawX();
                        downY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        moveX = event.getRawX();
                        moveY = event.getRawY();
                        px = select_two.getX() + (moveX - downX);
                        py = select_two.getY() + (moveY - downY);
                        if (px > width - select_two.getWidth()) {
                            px = width - select_two.getWidth();
                        }
                        if (px < 0) {
                            px = 0;
                        }
                        if (py > height) {
                            py = height;
                        }
                        if (py < getStatusBarHeight()) {
                            py = getStatusBarHeight();
                        }
                        select_two.setX(px);
                        select_two.setY(py);
                        downX = moveX;
                        downY = moveY;
                        break;
                    case MotionEvent.ACTION_UP:
                        MainTabsActivity.sx = px;
                        MainTabsActivity.sy = py;
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void initEvent() {
        see_time = (TextView) this.findViewById(R.id.see_time);
        select_used.setOnClickListener(this);
        select_new.setOnClickListener(this);
        select_rent.setOnClickListener(this);
        btn_see1.setOnClickListener(this);
        btn_see2.setOnClickListener(this);
        btn_by_people.setOnClickListener(this);
        btn_by_house.setOnClickListener(this);
        btn_by_store.setOnClickListener(this);
        btn_order.setOnClickListener(this);
        tv_order.setOnClickListener(this);
        tv_location.setOnClickListener(this);
        see_time.setOnClickListener(this);
        select_two.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    public void setTextState(int position) {
        for (int i = 0; i < 3; i++) {
            tv[i].setBackgroundResource(R.drawable.layout_agent_background);
            tv[i].setTextColor(Color.parseColor("#9e9e9e"));
        }
        tv[position].setBackgroundResource(R.drawable.background_white);
        tv[position].setTextColor(Color.parseColor("#ff0000"));
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.select_used://二手房
                setTextState(0);
                break;
            case R.id.select_new://新房
                setTextState(1);
                break;
            case R.id.select_rent://租房
                setTextState(2);
                break;
            case R.id.btn_see1://看房日程
                break;
            case R.id.btn_see2://看房记录
                break;
            case R.id.btn_by_people://以人找房
                break;
            case R.id.btn_by_house://以房找人
                break;
            case R.id.btn_by_store://以门店找房
                break;
            case R.id.tv_order://控制预约是否显示
                if (linearlayout_map.isShown()) {
                    linearlayout_map.setVisibility(View.GONE);
                } else {
                    linearlayout_map.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.location://位置
                intent = new Intent(ByPersonActivity.this, SearchLoactionActivity.class);
                intent.putExtra("lati", latitude);
                intent.putExtra("longi", longitude);
                startActivityForResult(intent, SEARCH_LOCATION);
                break;
            case R.id.see_time://选择时间
                timeDialog.show();
                break;
            case R.id.select_two://浮动按钮
                if (layout_btn.isShown()) {
                    layout_btn.setVisibility(View.GONE);
                } else {
                    layout_btn.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.question_img_back://返回
                finish();
                break;
            case R.id.btn_order:
                if (hastime) {
                    order();
                    //    ddddddd


                } else {
                    ToastUtil.makeText(ByPersonActivity.this, "请选择时间", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1000:
                    tv_location.setText(data.getStringExtra("addr"));
                    order_lat = data.getStringExtra("lati");
                    order_lng = data.getStringExtra("longi");
                    break;
            }
        }
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    private void order() {


        AjaxParams params = new AjaxParams();
        LoginEntity loginEntity = (LoginEntity) PreferencesUtils.getObject(ByPersonActivity.this, "loginEntity");

        params.put("lng", longitude + "");
        params.put("lat", latitude + "");
        params.put("user_id", loginEntity.getUser_id());
        params.put("user_phone", loginEntity.getUsername());
        params.put("user_name", loginEntity.getRealname());
        params.put("order_title", tv_location.getText().toString());
        params.put("order_lng", order_lng);
        params.put("order_lat", order_lat);
        params.put("house_type", house_type + "");
        params.put("visit_time", see_time.getText().toString());
        params.put("show_condition", demand.getText().toString());
        params.put("userUUID", loginEntity.getUserUUID());

        NetworkUtils.asyncPost(RealtimeOrderServerAPI.customerOrderMarker, params, new TpAjaxCallback() {
            @Override
            public void onSuccess(JSONObject jsonObject) {

                try {

                    Gson gson = new Gson();
                    ByHouseEntity byHouseEntity = gson.fromJson(jsonObject.toString(), new TypeToken<ByHouseEntity>() {
                    }.getType());
                    if (byHouseEntity.getCode() == 0) {
                        switch (byHouseEntity.getResult()) {
                            case 1:
                                ToastUtil.makeText(ByPersonActivity.this, "订单发出失败", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                break;
                            case 0:
                                ToastUtil.makeText(ByPersonActivity.this, "订单已发出", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                }, 2000);
                                break;
                            case 2:
                                ToastUtil.makeText(ByPersonActivity.this, "未提供位置信息", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                break;
                        }
                    } else {
                        ToastUtil.makeText(ByPersonActivity.this, "预约失败", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                    }
                    if (diaLog.isShowing()) diaLog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });


    }

    public class MapLoaded implements BaiduMap.OnMapLoadedCallback {
        @Override
        public void onMapLoaded() {

            myListener.checkLocation(new LocationCallback() {
                @Override
                public void currentLocation(BDLocation location) {
                    MyLocationData
                            locData = new MyLocationData.Builder().latitude(location.getLatitude()).longitude(location.getLongitude()).build();
                    //设置图标在地图上的位置
                    mBaiduMap.setMyLocationData(locData);
                    // 开始移动百度地图的定位地点到中心位置
                    LatLng ll = new LatLng(locData.latitude, locData.longitude);

                    MapStatus mMapStatus = new MapStatus.Builder().target(ll).zoom(16).build();
                    MapStatusUpdate u = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                    try {
                        mBaiduMap.animateMapStatus(u);
                    } catch (Exception e) {

                    }

                    //LatLng latLng = mBaiduMap.getMapStatus().target;
                    //准备 marker 的图片
                    BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.my_position);
                    //准备 marker option 添加 marker 使用
                    markerOptions = new MarkerOptions().icon(bitmap).position(ll);
                    //获取添加的 marker 这样便于后续的操作
                    marker = (Marker) mBaiduMap.addOverlay(markerOptions);
                    tv_location.setText(location.getAddress().city + "·" + location.getAddress().district);


                    AjaxParams params = new AjaxParams();
                    params.put("lat", Double.toString(location.getLatitude()));
                    params.put("lng", Double.toString(location.getLongitude()));
                    LoginEntity loginEntity = (LoginEntity)
                            PreferencesUtils.getObject(GlobalObjects.theApplication, "loginEntity");
                    params.put("userUUID", loginEntity.getUserUUID());
                    NetworkUtils.asyncGet(RealtimeOrderServerAPI.aroundBrokers, params, new TpAjaxCallback() {

                        public void onSuccess(JSONObject t) {
                            try {
                                if (t.getInt("code") != 0) return;
                                JSONArray arr = t.getJSONArray("broker_list");
                                int i;
                                Gson gson = new Gson();
                                aroundBrokerInfoList.clear();
                                aroundBrokerLoginNameHashMap.clear();
                                aroundBrokerIdHashMap.clear();
                                for (i = 0; i < arr.length(); i++) {
                                    BrokerInfo brokerInfo = gson.fromJson(arr.get(i).toString(), new TypeToken<BrokerInfo>() {
                                    }.getType());
                                    aroundBrokerInfoList.add(brokerInfo);
                                }
                                for (i = 0; i < aroundBrokerInfoOverlayList.size(); i++) {
                                    aroundBrokerInfoOverlayList.get(i).remove();


                                }
                                aroundBrokerInfoOverlayList.clear();
                                for (i = 0; i < aroundBrokerInfoList.size(); i++) {
                                    BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.broker_position);
                                    LatLng ll = new LatLng(aroundBrokerInfoList.get(i).getLat(), aroundBrokerInfoList.get(i).getLng());
                                    markerOptions = new MarkerOptions().icon(bitmap).position(ll);
                                    Marker marker1 = (Marker) mBaiduMap.addOverlay(markerOptions);

                                    aroundBrokerLoginNameHashMap.put(marker1, aroundBrokerInfoList.get(i).getBrokerLoginName().toString());
                                    aroundBrokerIdHashMap.put(marker1, aroundBrokerInfoList.get(i).getDbBrokerId().toString());
                                    aroundBrokerInfoOverlayList.add(marker1);
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    });


                }
            });
            mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker1) {
                    Intent intent = new Intent();
                    intent.setClass(ByPersonActivity.this, AgentCardActivity.class);
                    intent.putExtra("id", aroundBrokerLoginNameHashMap.get(marker1));
                    intent.putExtra("mobile", aroundBrokerLoginNameHashMap.get(marker1));
                    startActivity(intent);
                    return false;
                }
            });
        }
    }
}
