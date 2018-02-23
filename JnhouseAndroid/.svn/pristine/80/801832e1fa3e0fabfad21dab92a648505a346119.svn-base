package com.topwellsoft.jnhouse_android.realtime_order;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.PreferencesUtils;
import com.topwellsoft.baidumap.TPLocationListener;
import com.topwellsoft.jnhouse_android.map_find_house.MapFindHouse;
import com.topwellsoft.network.NetworkUtils;
import com.topwellsoft.network.TpAjaxCallback;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONObject;


import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.ReadyOrderEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.MainTabsActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.calendar.OrderingActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.OperationDialog;
import jnhouse.topwellsoft.com.jnhouse_android.util.Application.JnHouseApplication;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;


/**
 * Created by admin on 2016/5/23.
 */
public class RealtimeOrderActivity extends Activity implements View.OnClickListener {

    MapView mapView;
    LinearLayout layout_btn;

    Button btn_by_people;
    Button btn_by_house;
    Button btn_by_store;
    ImageView select;
    ImageView icon_back;

    private boolean isShown = false;
    public LocationClient mLocationClient = null;
    public TPLocationListener myListener;
    private BaiduMap mBaiduMap;
    MapLoaded mapLoaded;
    UiSettings mUiSettings;

    private MarkerOptions markerOptions;
    private Marker marker;
    private float downX, downY;
    private float width, height;
    private float moveX, moveY;


    private float px;
    private float py;
    private MyDiaLog diaLog;
    private OperationDialog operationDialog;
    private String tip[] = {"您有未完成的预约订单，暂时无法使用此功能", "您有未完成的预约订单，暂时无法使用此功能"};
    private ReadyOrderEntity readyOrderEntity;
    RealtimeOrderLocationCallback realtimeOrderLocationCallback;

    RealtimeOrderActivity theActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        theActivity = this;
        setContentView(R.layout.tp_house_main);
        mapView = (MapView) findViewById(R.id.mapView);
        layout_btn = (LinearLayout) findViewById(R.id.layout_btn);

        btn_by_people = (Button) findViewById(R.id.btn_by_people);
        btn_by_house = (Button) findViewById(R.id.btn_by_house);
        btn_by_store = (Button) findViewById(R.id.btn_by_store);
        select = (ImageView) findViewById(R.id.select);
        icon_back = (ImageView) findViewById(R.id.icon_back);
        icon_back.setOnClickListener(this);
///////////////////////////////////////////////////////
        initEvent();
        initDialog();
        width = this.getWindow().getWindowManager().getDefaultDisplay().getWidth();
        height = this.getWindow().getWindowManager().getDefaultDisplay().getHeight();


        // 济房网，当前默认为济南

        LatLng p = new LatLng(36.67221, 117.0487851);
        MapStatus mMapStatus = new MapStatus.Builder().target(p).zoom(11).build();

        mBaiduMap = mapView.getMap();

        mUiSettings = mBaiduMap.getUiSettings();
        mUiSettings.setRotateGesturesEnabled(false);
        mUiSettings.setOverlookingGesturesEnabled(false);


        MapStatusUpdate u = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.animateMapStatus(u);
        mapLoaded = new MapLoaded();
        myListener = BaiduLocationStartupService.locationListener;

        realtimeOrderLocationCallback = new RealtimeOrderLocationCallback();
        realtimeOrderLocationCallback.mBaiduMap = mBaiduMap;
        mBaiduMap.setOnMapLoadedCallback(mapLoaded);

    }

    public void initOperationDialog(final int i) {
        operationDialog = new OperationDialog(this);
        operationDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        operationDialog.setCancelable(true);
        operationDialog.setCanceledOnTouchOutside(true);
        operationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        operationDialog.setListener(new OperationDialog.ButtonListener() {
            @Override
            public void LeftListener() {
                operationDialog.dismiss();
            }

            @Override
            public void RighttListener() {
                Intent intent = null;
                switch (i) {
                    case 0://预约中
                        intent = new Intent(theActivity, OrderingActivity.class);
                        intent.putExtra("id", readyOrderEntity.getOr_id());
                        startActivity(intent);
                        break;
//                    case 1://看房
//                        intent = new Intent(theActivity, CalendarActivity.class);
//                        intent.putExtra("flag", "schedule");
//                        startActivity(intent);
//                        break;
                }
                operationDialog.dismiss();
            }
        });
        operationDialog.show();
        operationDialog.setContent(tip[i]);
        operationDialog.setBtn_right("去查看");
        operationDialog.setBtn_left("取消");
        operationDialog.setCancelable(false);
    }

    public void initDialog() {
        diaLog = new MyDiaLog(theActivity);
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(true);
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

        btn_by_people.setOnClickListener(this);
        btn_by_house.setOnClickListener(this);
        btn_by_store.setOnClickListener(this);
        select.setX(MainTabsActivity.sx);
        select.setY(MainTabsActivity.sy);
        select.setOnClickListener(this);

        select.setOnTouchListener(new View.OnTouchListener() {
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
                        px = select.getX() + (moveX - downX);
                        py = select.getY() + (moveY - downY);
                        if (px > width - select.getWidth()) {
                            px = width - select.getWidth();
                        }
                        if (px < 0) {
                            px = 0;
                        }
                        if (py > height * 0.83f) {
                            py = height * 0.83f;
                        }
                        if (py < getStatusBarHeight()) {
                            py = getStatusBarHeight();
                        }

                        select.setX(px);
                        select.setY(py);
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

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.icon_back:
                onBackPressed();
                break;

            case R.id.btn_by_people:
                diaLog.show();
                getUserState(JnHouse_Record.Order_Init);
                break;
            case R.id.btn_by_house:
                houseMap();
                break;
            case R.id.btn_by_store:
                showToast("以门店找房");
                break;
            case R.id.select:
                float h = height * 0.83f + 120;
                float hh = layout_btn.getHeight();

                if (py >= h - hh) {
                    layout_btn.setY(h - hh);
                } else {
                    layout_btn.setY(py);
                }
                layout_btn.setX(0);
                isShown = !isShown;
                if (isShown) {
                    layout_btn.setVisibility(View.VISIBLE);
                } else {
                    layout_btn.setVisibility(View.INVISIBLE);
                }

                break;
            default:
                break;
        }
    }


    @Override
    public void onDestroy() {

        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();


    }

    @Override
    public void onPause() {

        mapView.onPause();
        super.onPause();
    }

    public void showToast(String str) {
        ToastUtil.makeText(this, str, ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
    }

    public void houseMap() {
        Intent intent = new Intent(theActivity, MapFindHouse.class);
        startActivity(intent);
    }

    public void getUserState(String url) {
        LoginEntity loginEntity = (LoginEntity) PreferencesUtils.getObject(this, "loginEntity");
        if (loginEntity == null || loginEntity.getUserUUID() == null) {
            ToastUtil.makeText(this, "请重新登录", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put("user_id", loginEntity.getUser_id());
        params.put("userUUID", loginEntity.getUserUUID());
        FinalHttp fh = new FinalHttp();

        NetworkUtils.asyncGet(url, params, new TpAjaxCallback() {

            @Override
            public void onFailure(int errorNo, String strMsg) {

                if (diaLog.isShowing()) {
                    diaLog.dismiss();
                }
            }

            @Override
            public void onSuccess(JSONObject jsonObject) {

                try {

                    Gson gson = new Gson();
                    readyOrderEntity = gson.fromJson(jsonObject.toString(), new TypeToken<ReadyOrderEntity>() {
                    }.getType());
                    if (readyOrderEntity != null) {
                        switch (readyOrderEntity.getCode()) {
                            case 1901:
                                initOperationDialog(readyOrderEntity.getState());
                                break;
                            case 0:
                                Intent intent = new Intent(theActivity, ByPersonActivity.class);
                                startActivity(intent);
                                break;
                            case 1:
                                //ToastUtil.makeText(getActivity(), "您未登录", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                break;
                            default:
                                break;
                        }
                    }

                    if (diaLog.isShowing()) {
                        diaLog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public class MapLoaded implements BaiduMap.OnMapLoadedCallback {
        @Override
        public void onMapLoaded() {

            myListener.checkLocation(realtimeOrderLocationCallback);

        }
    }
}
