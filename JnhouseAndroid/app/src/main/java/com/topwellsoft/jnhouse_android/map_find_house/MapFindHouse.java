package com.topwellsoft.jnhouse_android.map_find_house;

import android.app.Activity;


import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;

import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.StyleUtils;
import com.topwellsoft.baidumap.BaiduMapHelper;
import com.topwellsoft.baidumap.BubbleOverlay;
import com.topwellsoft.baidumap.CircleOverlay;
import com.topwellsoft.jnhouse_android.map_find_house.model.CountyLevelNewHouseMarkerData;
import com.topwellsoft.jnhouse_android.map_find_house.model.CountyLevelRentHouseMarkerData;
import com.topwellsoft.jnhouse_android.map_find_house.model.CountyLevelSecondHouseMarkerData;
import com.topwellsoft.jnhouse_android.map_find_house.model.LatLngCommunityRentHouseData;

import com.topwellsoft.jnhouse_android.map_find_house.model.LatLngCommunitySecondHouseData;
import com.topwellsoft.jnhouse_android.map_find_house.model.LatLngNewHouseData;
import com.topwellsoft.jnhouse_android.map_find_house.model.Map_NewHouse_Detil;
import com.topwellsoft.jnhouse_android.map_find_house.model.TradeAreaLevelNewHouseMarkerData;
import com.topwellsoft.jnhouse_android.map_find_house.model.TradeAreaLevelRentHouseMarkerData;
import com.topwellsoft.jnhouse_android.map_find_house.model.TradeAreaLevelSecondHouseMarkerData;
import com.topwellsoft.network.NetworkUtils;
import com.topwellsoft.network.TpAjaxCallback;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.RentingHouseAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.SecondHouseAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.manage.ImageManager;
import jnhouse.topwellsoft.com.jnhouse_android.model.RentHouseList;
import jnhouse.topwellsoft.com.jnhouse_android.ui.new_house.IndexNewHouseDetail;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;
import jnhouse.topwellsoft.com.jnhouse_android.view.FluidLayout;
import jnhouse.topwellsoft.com.jnhouse_android.view.PopListWindow;
import jnhouse.topwellsoft.com.jnhouse_android.view.Popwindow;
import jnhouse.topwellsoft.com.jnhouse_android.view.SmoothListView.SmoothListView;

public class MapFindHouse extends Activity implements View.OnClickListener {

    private static final String LTAG = "MapFindHouse";

    private enum MapState {New, Second, Rent}

    ;
    private MapState currentMapState;

    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private Button locationBtn, SearchBtn;
    UiSettings mUiSettings;

    private TextView title;
    MapLoaded mapLoaded;
    MapStatusChanged mapStatusChanged;
    Popwindow powind;
    PopListWindow poLiswindow;
    RentingHouseAdapter renAdapter;
    private ImageManager imgMan;

    //定位
    private LocationClient mLocation;
    private BDLocationListener bdLocationListener = new MyLocationListener();


    private PoiSearch mPoiSearch;

    private HashMap<Integer, CountyLevelNewHouseMarkerData> countyLevelNewHouseMarkerDataMap;//区县级新房
    private HashMap<Integer, TradeAreaLevelNewHouseMarkerData> tradeAreaLevelNewHouseMarkerDataMap;//商圈级新房

    private HashMap<Integer, CountyLevelSecondHouseMarkerData> countyLevelSecondHouseMarkerDataMap;//区县级二手房
    private HashMap<Integer, TradeAreaLevelSecondHouseMarkerData> tradeAreaLevelSecondHouseMarkerDataMap;//商圈级二手房

    private HashMap<Integer, CountyLevelRentHouseMarkerData> countyLevelRentHouseMarkerDataMap;//区县级出租房
    private HashMap<Integer, TradeAreaLevelRentHouseMarkerData> tradeAreaLevelRentHouseMarkerDataMap;//商圈级出租房


    private List<LatLngNewHouseData> newHouseDataList;//新房小区及售价
    private List<LatLngCommunitySecondHouseData> secondHouseDataList;//小区待售二手房数量标注
    private List<LatLngCommunityRentHouseData> rentHouseDataList;//小区出租房数量标注

    HashMap<Overlay, String> NewhsoueHashMap;
    private HashMap<String, Marker> currentZoomMarkerMap;

    private View countyLevelOverLayView;
    private CircleOverlay countyLevelOverLay;
    private View tradeAreaLevelOverLayView;
    private CircleOverlay tradeAreaLevelOverLay;


    private View newHouseOverLayView;
    private BubbleOverlay newHouseOverLay;
    private View secondHouseOverLayView;
    private BubbleOverlay secondHouseOverLay;
    private View rentHouseOverLayView;
    private BubbleOverlay rentHouseOverLay;


    private ImageView back;
    private float mapZoomLevel = 11;
    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        StyleUtils.initStatusBar(getWindow());
        setContentView(R.layout.map_find_house);
        Intent intent = getIntent();

        LinearLayout topView = (LinearLayout) this.findViewById(R.id.map_find_house_top_tab);

        back = (ImageView) (topView.findViewById(R.id.map_find_house_img_back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        RadioGroup rg =
                (RadioGroup) (topView.findViewById(R.id.map_find_house_top_segments));
        rg.check(R.id.map_find_house_tab_new);
        RadioButton rb =
                (RadioButton) (topView.findViewById(R.id.map_find_house_tab_new));
        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentMapState = MapState.New;
                LatLng p = new LatLng(36.67221, 117.0487851);
                MapStatus mMapStatus = new MapStatus.Builder().target(p).zoom(11).build();
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(mMapStatus));
                countyLevelNewHouse();
            }
        });
        rb =
                (RadioButton) (topView.findViewById(R.id.map_find_house_tab_second));
        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentMapState = MapState.Second;
                LatLng p = new LatLng(36.67221, 117.0487851);
                MapStatus mMapStatus = new MapStatus.Builder().target(p).zoom(11).build();
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(mMapStatus));
                mBaiduMap.clear();//mMapView.removeov
                countyLevelSecondHouse();
            }
        });

        rb =
                (RadioButton) (topView.findViewById(R.id.map_find_house_tab_rent));
        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentMapState = MapState.Rent;
                LatLng p = new LatLng(36.67221, 117.0487851);
                MapStatus mMapStatus = new MapStatus.Builder().target(p).zoom(11).build();
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(mMapStatus));
                mBaiduMap.clear();//mMapView.removeov
                countyLevelRentHouse();
            }
        });


        currentZoomMarkerMap = new HashMap<String, Marker>();

        // 济房网，当前默认为济南
        Bundle b = intent.getExtras();
        LatLng p = new LatLng(36.67221, 117.0487851);
        MapStatus mMapStatus = new MapStatus.Builder().target(p).zoom(11).build();


        mMapView = (MapView) findViewById(R.id.mMap);

        mBaiduMap = mMapView.getMap();
        mUiSettings = mBaiduMap.getUiSettings();
        mUiSettings.setRotateGesturesEnabled(false);
        mUiSettings.setOverlookingGesturesEnabled(false);

        MapStatusUpdate u = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.animateMapStatus(u);

        mapLoaded = new MapLoaded();
        mBaiduMap.setOnMapLoadedCallback(mapLoaded);
        mapStatusChanged = new MapStatusChanged();
        mBaiduMap.setOnMapStatusChangeListener(mapStatusChanged);
        mBaiduMap.setMyLocationEnabled(true);
        ///////////////////////////////////////
        countyLevelNewHouseMarkerDataMap = new HashMap<Integer, CountyLevelNewHouseMarkerData>();
        tradeAreaLevelNewHouseMarkerDataMap = new HashMap<Integer, TradeAreaLevelNewHouseMarkerData>();


        countyLevelSecondHouseMarkerDataMap = new HashMap<Integer, CountyLevelSecondHouseMarkerData>();
        tradeAreaLevelSecondHouseMarkerDataMap = new HashMap<Integer, TradeAreaLevelSecondHouseMarkerData>();

        countyLevelRentHouseMarkerDataMap = new HashMap<Integer, CountyLevelRentHouseMarkerData>();
        tradeAreaLevelRentHouseMarkerDataMap = new HashMap<Integer, TradeAreaLevelRentHouseMarkerData>();
        NewhsoueHashMap=new HashMap<Overlay, String>();
        newHouseDataList = new ArrayList<LatLngNewHouseData>();
        secondHouseDataList = new ArrayList<LatLngCommunitySecondHouseData>();
        rentHouseDataList = new ArrayList<LatLngCommunityRentHouseData>();

        countyLevelOverLayView = View.inflate(this, R.layout.map_find_house_circle, null);
        countyLevelOverLay = (CircleOverlay) countyLevelOverLayView.findViewById(R.id.circleOverlay);

        tradeAreaLevelOverLayView = View.inflate(this, R.layout.map_find_house_circle, null);
        tradeAreaLevelOverLay = (CircleOverlay) tradeAreaLevelOverLayView.findViewById(R.id.circleOverlay);

        newHouseOverLayView = View.inflate(this, R.layout.map_find_house_bubble, null);
        newHouseOverLay = (BubbleOverlay) newHouseOverLayView.findViewById(R.id.bubbleOverlay);

        secondHouseOverLayView = View.inflate(this, R.layout.map_find_house_bubble, null);
        secondHouseOverLay = (BubbleOverlay) secondHouseOverLayView.findViewById(R.id.bubbleOverlay);

        rentHouseOverLayView = View.inflate(this, R.layout.map_find_house_bubble, null);
        rentHouseOverLay = (BubbleOverlay) rentHouseOverLayView.findViewById(R.id.bubbleOverlay);


        currentMapState = MapState.New;
        init();
    }

    private void init() {
//
//        SearchBtn = (Button) findViewById(R.id.SearchBtn);
//        SearchBtn.setOnClickListener(this);
        locationBtn = (Button) findViewById(R.id.locationBtn);
        locationBtn.setOnClickListener(this);
        mLocation = new LocationClient(getApplicationContext());
        mLocation.registerLocationListener(bdLocationListener);
        this.initLocation();
        //poi检索
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(SearchListenr);
    }

    //poi搜索监听
    OnGetPoiSearchResultListener SearchListenr = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            //返回异常
            if (poiResult == null || poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
                ToastUtil.makeText(MapFindHouse.this, "没有搜索到结果", ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
                return;
            } else {

            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

        }

        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }
    };


    /*定位设置*/
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 4000;
        option.setScanSpan(0);//定位频率
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocation.setLocOption(option);

    }

    @Override
    protected void onPause() {
        super.onPause();
        // activity 暂停时同时暂停地图控件
        mMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // activity 恢复时同时恢复地图控件
        //  Projection pro= mBaiduMap.getProjection();

        mMapView.onResume();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // activity 销毁时同时销毁地图控件
        mMapView.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.index_second_house_title_back:
                onBackPressed();
                break;
            case R.id.locationBtn:
                mLocation.start();
                break;
        }
    }

    private void getNewHouseDetil(String houeId) {
        AjaxParams params = new AjaxParams();
        params.put("house_id", houeId);
        FinalHttp fp = new FinalHttp();
        fp.get(MapFindHouseServerAPI.Newhouse_Detile, params, new AjaxCallBack<Object>() {
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
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
                    Map_NewHouse_Detil detil = gson.fromJson(o.toString(), new TypeToken<Map_NewHouse_Detil>() {
                    }.getType());
                    if (detil != null) {
                        View contentView = LayoutInflater.from(MapFindHouse.this).inflate(R.layout.activiy_popupwindow, null);
                        powind = new Popwindow(MapFindHouse.this, itemsOnClick);
                        powind.setContentView(contentView);
                        final TextView tv1 = (TextView) contentView.findViewById(R.id.brog_name);
                        final TextView tv2 = (TextView) contentView.findViewById(R.id.newhouse_address);
                        final TextView tv3 = (TextView) contentView.findViewById(R.id.pice_newhouse);
                        final TextView tv4 = (TextView) contentView.findViewById(R.id.mianji_newhouse);
                        FluidLayout flt = (FluidLayout) contentView.findViewById(R.id.map_newhousefeature);
                        ImageView img = (ImageView) contentView.findViewById(R.id.bg_image);
                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        imgMan = new ImageManager(MapFindHouse.this);
                        imgMan.loadUrlImage(detil.getBorough_thumb(), img);
                        tv1.setText(detil.getBorough_name());
                        tv2.setText(detil.getBorough_address());
                           if (detil.getBorough_avgprice()=="0"){
                               tv3.setText("价格待定");
                           }else {tv3.setText(detil.getBorough_avgprice()+"元/平");}
                        tv4.setText(detil.getHouse_feature());
                        genTag(detil.getHouse_feature(), flt);
                        powind.showAtLocation(contentView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void genTag(String laber, FluidLayout fluidLayout) {
        fluidLayout.removeAllViews();
        fluidLayout.setGravity(Gravity.CENTER);
        List<String> tags = new ArrayList<>();
        if (laber != null && !laber.equals("")) {
            String[] s = laber.split(",");
            for (int i = 0; i < s.length; i++) {
                tags.add(s[i]);
            }
        }
        if (tags != null && tags.size() > 0) {
            for (int i = 0; i < tags.size(); i++) {
                TextView tv = new TextView(this);
                tv.setText(tags.get(i));
                tv.setTextSize(13);

                if (i == 12) {
                    if (!true) {
                        tv.setHeight(100);
                        tv.setGravity(Gravity.CENTER);
                    }
                    tv.setBackgroundResource(R.drawable.text_bg_highlight);

                } else {
                    if (true) {
                        tv.setBackgroundResource(R.drawable.text_bg);
                    }
                }
                if (i % 8 == 0) {
                    tv.setTextColor(Color.parseColor("#FF0000"));
                } else if (i % 28 == 0) {
                    tv.setTextColor(Color.parseColor("#66CD00"));
                } else {
                    tv.setTextColor(Color.parseColor("#666666"));
                }

                FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(12, 12, 12, 12);
                fluidLayout.addView(tv, params);
            }
        }

    }



    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            powind.dismiss();
            switch (v.getId()) {

            }


        }

    };


    public class MapLoaded implements BaiduMap.OnMapLoadedCallback {
        @Override
        public void onMapLoaded() {
            countyLevelNewHouse();

        }
    }

    private void latLngNewHouse() {
        int[] cc = new int[2];
        mMapView.getLocationOnScreen(cc);
        int height = mMapView.getHeight();
        int width = mMapView.getWidth();
        LatLng nw =
                mMapView.getMap().getProjection().fromScreenLocation(new Point(cc[0], cc[1]));
        cc[0] = cc[0] + width;
        cc[1] = cc[1] + height;

        LatLng se =
                mMapView.getMap().getProjection().fromScreenLocation(new Point(cc[0], cc[1]));
        FinalHttp fn = new FinalHttp();
        fn.configRequestExecutionRetryCount(0);
        AjaxParams params = new AjaxParams();
        params.put("wLng", Double.toString(nw.longitude));
        params.put("eLng", Double.toString(se.longitude));
        params.put("nLat", Double.toString(nw.latitude));
        params.put("sLat", Double.toString(se.latitude));
        fn.get(MapFindHouseServerAPI.latlngNewHouseMarker, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object t) {
                try {
                    countyLevelNewHouseMarkerDataMap.clear();
                    JSONObject json = new JSONObject(t.toString());
                    JSONArray clist =
                            json.getJSONArray("newHouse_list");
                    Gson gson = new Gson();
                    newHouseDataList.clear();
                    NewhsoueHashMap.clear();
                    for (int i = 0; i < clist.length(); i++) {
                        LatLngNewHouseData newHouseData = gson.fromJson(clist.get(i).toString(), new TypeToken<LatLngNewHouseData>() {
                        }.getType());
                        newHouseDataList.add(newHouseData);
                    }

                    mBaiduMap.clear();

                    for (int i = 0; i < newHouseDataList.size(); i++) {
                        LatLngNewHouseData data = newHouseDataList.get(i);
                        LatLng la = new LatLng(data.getLat(), data.getLng());
                        if (data.getAvgPrice() > 0)
                            newHouseOverLay.setText(Html.fromHtml(data.getHouseName() + "<br />" + data.getAvgPrice() + "元/" + "m<sup><small>2</small></sup>"));
                        else
                            newHouseOverLay.setText(Html.fromHtml(data.getHouseName() + "<br />价格待定"));
                        newHouseOverLayView.invalidate();
                        Bitmap bm =
                                BaiduMapHelper.getViewBitmap(newHouseOverLayView);
                        BitmapDescriptor ss = BitmapDescriptorFactory.fromBitmap(bm);
                        OverlayOptions ooA = new MarkerOptions().position(la).icon(ss)
                                .zIndex(9).perspective(true);

                        Marker mMaker = null;
                        Bundle bundle = new Bundle();
                        mMaker = (Marker) (mBaiduMap.addOverlay(ooA));

//                        bundle.putSerializable("info", data);
//                        mMaker.setExtraInfo(bundle);

                    }


                    mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {

//                            LatLngNewHouseData data = (LatLngNewHouseData) marker.getExtraInfo().getSerializable("info");
////                            Intent intent=new Intent();
////                            intent.putExtra("houseId",data.getHouse_id());
////                            intent.setClass(MapFindHouse.this,IndexNewHouseDetail.class);
////                            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
////                            startActivity(intent);
//                             String id=data.getHouse_id();
                            getNewHouseDetil(NewhsoueHashMap.get(marker));
                            return false;
                        }
                    });
                    System.out.println();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
            }

        });
    }

    private void latLngRentHouse() {
        int[] cc = new int[2];
        mMapView.getLocationOnScreen(cc);
        int height = mMapView.getHeight();
        int width = mMapView.getWidth();
        LatLng nw =
                mMapView.getMap().getProjection().fromScreenLocation(new Point(cc[0], cc[1]));
        cc[0] = cc[0] + width;
        cc[1] = cc[1] + height;
        LatLng se =
                mMapView.getMap().getProjection().fromScreenLocation(new Point(cc[0], cc[1]));
        FinalHttp fn = new FinalHttp();

        AjaxParams params = new AjaxParams();
        params.put("wLng", Double.toString(nw.longitude));
        params.put("eLng", Double.toString(se.longitude));
        params.put("nLat", Double.toString(nw.latitude));
        params.put("sLat", Double.toString(se.latitude));
        fn.configRequestExecutionRetryCount(0);
        fn.get(MapFindHouseServerAPI.latlngRentHouseMarker, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object t) {
                try {
                    // countyLevelNewHouseMarkerDataMap.clear();
                    JSONObject json = new JSONObject(t.toString());
                    JSONArray clist =
                            json.getJSONArray("community_list");
                    Gson gson = new Gson();
                    rentHouseDataList.clear();
                    for (int i = 0; i < clist.length(); i++) {
                        LatLngCommunityRentHouseData secondData = gson.fromJson(clist.get(i).toString(), new TypeToken<LatLngCommunityRentHouseData>() {
                        }.getType());
                        rentHouseDataList.add(secondData);
                    }

                    mBaiduMap.clear();

                    for (int i = 0; i < rentHouseDataList.size(); i++) {
                        LatLngCommunityRentHouseData data = rentHouseDataList.get(i);
                        LatLng la = new LatLng(data.getLat(), data.getLng());
                        if (data.getRentHouseCount() == 0) continue;

                        rentHouseOverLay.setText(data.getCommunityName() + "\n" + data.getRentHouseCount() + "套");

                        rentHouseOverLayView.invalidate();
                        Bitmap bm =
                                BaiduMapHelper.getViewBitmap(rentHouseOverLayView);
                        BitmapDescriptor ss = BitmapDescriptorFactory.fromBitmap(bm);
                        OverlayOptions ooA = new MarkerOptions().position(la).icon(ss)
                                .zIndex(9).perspective(true);

                        Marker mMaker = null;
                        Bundle bundle = new Bundle();

                        mMaker = (Marker) (mBaiduMap.addOverlay(ooA));
                        bundle.putSerializable("info", data);
                        mMaker.setExtraInfo(bundle);
                    }


                    mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            LatLngCommunityRentHouseData data=(LatLngCommunityRentHouseData) marker.getExtraInfo().getSerializable("info");
   ToastUtil.makeText(MapFindHouse.this,"222",ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
                            getRent(data.getCommunity_id(),"0");
                            return false;
                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void getRent(String id,final String flag){
                AjaxParams params=new AjaxParams();
                params.put("borough_id",id);
                FinalHttp fp=new FinalHttp();
                fp.get(MapFindHouseServerAPI.Renthouse_List, new AjaxCallBack<Object>() {
                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {
                        super.onFailure(t, errorNo, strMsg);
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
                            JSONObject json=new JSONObject(o.toString());
                            Gson gson=new Gson();
                          Log.i("####",o.toString());
                            RentHouseList rentHouseList = gson.fromJson(o.toString(), new TypeToken<RentHouseList>() {
                            }.getType());
                            View contentView = LayoutInflater.from(MapFindHouse.this).inflate(R.layout.activity_listpopwindow, null);
                            poLiswindow = new PopListWindow(MapFindHouse.this, itemsOnClick);
                            poLiswindow.setContentView(contentView);

                            final TextView tv1=(TextView)contentView.findViewById(R.id.map_borogh_name);
                            final TextView tv2=(TextView)contentView.findViewById(R.id.houseing);
                            SmoothListView smoothListView=(SmoothListView)contentView.findViewById(R.id.map_house_list);
                            if (rentHouseList.getData_list() != null && rentHouseList.getData_list().size() > 0) {

                                smoothListView.setLoadMoreEnable(rentHouseList.getData_list().size() > renAdapter.ONE_REQUEST_COUNT);

                                    renAdapter.setData(rentHouseList.getData_list());
                                    smoothListView.setAdapter(renAdapter);

                                poLiswindow.showAtLocation(contentView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                });

            }



            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
            }

        });
    }

    private void latLngSecondHouse() {
        int[] cc = new int[2];
        mMapView.getLocationOnScreen(cc);
        int height = mMapView.getHeight();
        int width = mMapView.getWidth();
        LatLng nw =
                mMapView.getMap().getProjection().fromScreenLocation(new Point(cc[0], cc[1]));
        cc[0] = cc[0] + width;
        cc[1] = cc[1] + height;
        LatLng se =
                mMapView.getMap().getProjection().fromScreenLocation(new Point(cc[0], cc[1]));
        FinalHttp fn = new FinalHttp();

        AjaxParams params = new AjaxParams();
        params.put("wLng", Double.toString(nw.longitude));
        params.put("eLng", Double.toString(se.longitude));
        params.put("nLat", Double.toString(nw.latitude));
        params.put("sLat", Double.toString(se.latitude));
        fn.configRequestExecutionRetryCount(0);
        fn.get(MapFindHouseServerAPI.latlngSecondHouseMarker, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object t) {
                try {
                    countyLevelSecondHouseMarkerDataMap.clear();
                    JSONObject json = new JSONObject(t.toString());
                    JSONArray clist =
                            json.getJSONArray("community_list");
                    Gson gson = new Gson();
                    secondHouseDataList.clear();
                    for (int i = 0; i < clist.length(); i++) {
                        LatLngCommunitySecondHouseData secondData = gson.fromJson(clist.get(i).toString(), new TypeToken<LatLngCommunitySecondHouseData>() {
                        }.getType());
                        secondHouseDataList.add(secondData);
                    }

                    mBaiduMap.clear();

                    for (int i = 0; i < secondHouseDataList.size(); i++) {
                        LatLngCommunitySecondHouseData data = secondHouseDataList.get(i);
                        LatLng la = new LatLng(data.getLat(), data.getLng());
                        if (data.getSecondHouseCount() == 0) continue;

                        secondHouseOverLay.setText(data.getCommunityName() + "\n" + data.getSecondHouseCount() + "套");

                        secondHouseOverLayView.invalidate();
                        Bitmap bm =
                                BaiduMapHelper.getViewBitmap(secondHouseOverLayView);
                        BitmapDescriptor ss = BitmapDescriptorFactory.fromBitmap(bm);
                        OverlayOptions ooA = new MarkerOptions().position(la).icon(ss)
                                .zIndex(9).perspective(true);

                        Marker mMaker = null;
                        Bundle bundle = new Bundle();

                        mMaker = (Marker) (mBaiduMap.addOverlay(ooA));
                        bundle.putSerializable("info", data);
                        mMaker.setExtraInfo(bundle);
                    }


                    mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {

                            LatLngNewHouseData data = (LatLngNewHouseData) marker.getExtraInfo().getSerializable("info");
//                            Intent intent=new Intent();
//                            intent.putExtra("houseId",data.getHouse_id());
//                            intent.setClass(MapFindHouse.this,IndexNewHouseDetail.class);
//                            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(intent);
//

                            return false;
                        }
                    });
                    System.out.println();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
            }

        });

    }

    private void countyLevelRentHouse() {

        int[] cc = new int[2];
        mMapView.getLocationOnScreen(cc);
        int height = mMapView.getHeight();
        int width = mMapView.getWidth();
        LatLng nw =
                mMapView.getMap().getProjection().fromScreenLocation(new Point(cc[0], cc[1]));
        cc[0] = cc[0] + width;
        cc[1] = cc[1] + height;

        LatLng se =
                mMapView.getMap().getProjection().fromScreenLocation(new Point(cc[0], cc[1]));

        AjaxParams params = new AjaxParams();
        params.put("wLng", Double.toString(nw.longitude));
        params.put("eLng", Double.toString(se.longitude));
        params.put("nLat", Double.toString(nw.latitude));
        params.put("sLat", Double.toString(se.latitude));

        NetworkUtils.asyncGet(MapFindHouseServerAPI.latlngCountyLevelRentHouseMarker, params, new TpAjaxCallback() {
            @Override
            public void onSuccess(JSONObject json) {
                try {
                    countyLevelRentHouseMarkerDataMap.clear();

                    JSONArray clist =
                            json.getJSONArray("countyMarker_list");
                    Gson gson = new Gson();
                    for (int i = 0; i < clist.length(); i++) {
                        CountyLevelRentHouseMarkerData countyData = gson.fromJson(clist.get(i).toString(), new TypeToken<CountyLevelRentHouseMarkerData>() {
                        }.getType());
                        countyLevelRentHouseMarkerDataMap.put(countyData.getId(), countyData);
                    }
                    Iterator it =
                            countyLevelRentHouseMarkerDataMap.keySet().iterator();
                    mBaiduMap.clear();
                    while (it.hasNext()) {
                        CountyLevelRentHouseMarkerData dd = countyLevelRentHouseMarkerDataMap.get(it.next());
                        LatLng la = new LatLng(dd.getLat(), dd.getLng());
                        countyLevelOverLay.setText(dd.getCountyName() + "\n" + dd.getRentHouseCount());
                        countyLevelOverLayView.invalidate();
                        Bitmap bm =
                                BaiduMapHelper.getViewBitmap(countyLevelOverLayView);
                        BitmapDescriptor ss = BitmapDescriptorFactory.fromBitmap(bm);
                        OverlayOptions ooA = new MarkerOptions().position(la).icon(ss)
                                .zIndex(9).perspective(true);

                        Object mMarkerA = (mBaiduMap.addOverlay(ooA));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        });


    }

    private void countyLevelSecondHouse() {
        int[] cc = new int[2];
        mMapView.getLocationOnScreen(cc);
        int height = mMapView.getHeight();
        int width = mMapView.getWidth();
        LatLng nw =
                mMapView.getMap().getProjection().fromScreenLocation(new Point(cc[0], cc[1]));
        cc[0] = cc[0] + width;
        cc[1] = cc[1] + height;

        LatLng se =
                mMapView.getMap().getProjection().fromScreenLocation(new Point(cc[0], cc[1]));

        AjaxParams params = new AjaxParams();
        params.put("wLng", Double.toString(nw.longitude));
        params.put("eLng", Double.toString(se.longitude));
        params.put("nLat", Double.toString(nw.latitude));
        params.put("sLat", Double.toString(se.latitude));

        NetworkUtils.asyncGet(MapFindHouseServerAPI.latlngCountyLevelSecondHouseMarker, params, new TpAjaxCallback() {
            @Override
            public void onSuccess(JSONObject json) {
                try {
                    countyLevelSecondHouseMarkerDataMap.clear();

                    JSONArray clist =
                            json.getJSONArray("countyMarker_list");
                    Gson gson = new Gson();
                    for (int i = 0; i < clist.length(); i++) {
                        CountyLevelSecondHouseMarkerData countyData = gson.fromJson(clist.get(i).toString(), new TypeToken<CountyLevelSecondHouseMarkerData>() {
                        }.getType());
                        countyLevelSecondHouseMarkerDataMap.put(countyData.getId(), countyData);
                    }
                    Iterator it =
                            countyLevelSecondHouseMarkerDataMap.keySet().iterator();
                    mBaiduMap.clear();
                    while (it.hasNext()) {
                        CountyLevelSecondHouseMarkerData dd = countyLevelSecondHouseMarkerDataMap.get(it.next());
                        LatLng la = new LatLng(dd.getLat(), dd.getLng());
                        countyLevelOverLay.setText(dd.getCountyName() + "\n" + dd.getSecondHouseCount());
                        countyLevelOverLayView.invalidate();
                        Bitmap bm =
                                BaiduMapHelper.getViewBitmap(countyLevelOverLayView);
                        BitmapDescriptor ss = BitmapDescriptorFactory.fromBitmap(bm);
                        OverlayOptions ooA = new MarkerOptions().position(la).icon(ss)
                                .zIndex(9).perspective(true);

                        Object mMarkerA = (mBaiduMap.addOverlay(ooA));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        });


    }

    private void countyLevelNewHouse() {
        int[] cc = new int[2];
        mMapView.getLocationOnScreen(cc);
        int height = mMapView.getHeight();
        int width = mMapView.getWidth();
        LatLng nw =
                mMapView.getMap().getProjection().fromScreenLocation(new Point(cc[0], cc[1]));
        cc[0] = cc[0] + width;
        cc[1] = cc[1] + height;

        LatLng se =
                mMapView.getMap().getProjection().fromScreenLocation(new Point(cc[0], cc[1]));
        FinalHttp fn = new FinalHttp();
        fn.configRequestExecutionRetryCount(0);
        AjaxParams params = new AjaxParams();
        params.put("wLng", Double.toString(nw.longitude));
        params.put("eLng", Double.toString(se.longitude));
        params.put("nLat", Double.toString(nw.latitude));
        params.put("sLat", Double.toString(se.latitude));
        fn.get(MapFindHouseServerAPI.latlngCountyLevelNewHouseMarker, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object t) {
                try {
                    countyLevelNewHouseMarkerDataMap.clear();
                    JSONObject json = new JSONObject(t.toString());
                    JSONArray clist =
                            json.getJSONArray("countyMarker_list");
                    Gson gson = new Gson();
                    for (int i = 0; i < clist.length(); i++) {
                        CountyLevelNewHouseMarkerData countyData = gson.fromJson(clist.get(i).toString(), new TypeToken<CountyLevelNewHouseMarkerData>() {
                        }.getType());
                        countyLevelNewHouseMarkerDataMap.put(countyData.getId(), countyData);
                    }
                    Iterator it =
                            countyLevelNewHouseMarkerDataMap.keySet().iterator();
                    mBaiduMap.clear();
                    while (it.hasNext()) {
                        CountyLevelNewHouseMarkerData dd = countyLevelNewHouseMarkerDataMap.get(it.next());
                        LatLng la = new LatLng(dd.getLat(), dd.getLng());
                        countyLevelOverLay.setText(dd.getCountyName() + "\n" + dd.getNewHouseCount());
                        countyLevelOverLayView.invalidate();
                        Bitmap bm =
                                BaiduMapHelper.getViewBitmap(countyLevelOverLayView);
                        BitmapDescriptor ss = BitmapDescriptorFactory.fromBitmap(bm);
                        OverlayOptions ooA = new MarkerOptions().position(la).icon(ss)
                                .zIndex(9).perspective(true);
                        Object mMarkerA = (mBaiduMap.addOverlay(ooA));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
            }

        });
    }

    private void tradeAreaLevelNewHouse() {
        int[] cc = new int[2];
        mMapView.getLocationOnScreen(cc);
        int height = mMapView.getHeight();
        int width = mMapView.getWidth();
        LatLng nw =
                mMapView.getMap().getProjection().fromScreenLocation(new Point(cc[0], cc[1]));
        cc[0] = cc[0] + width;
        cc[1] = cc[1] + height;

        LatLng se =
                mMapView.getMap().getProjection().fromScreenLocation(new Point(cc[0], cc[1]));
        FinalHttp fn = new FinalHttp();
        fn.configRequestExecutionRetryCount(0);
        AjaxParams params = new AjaxParams();
        params.put("wLng", Double.toString(nw.longitude));
        params.put("eLng", Double.toString(se.longitude));
        params.put("nLat", Double.toString(nw.latitude));
        params.put("sLat", Double.toString(se.latitude));
        fn.get(MapFindHouseServerAPI.latlngTradeLevelNewHouseMarker, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object t) {
                try {
                    tradeAreaLevelNewHouseMarkerDataMap.clear();
                    JSONObject json = new JSONObject(t.toString());
                    JSONArray clist =
                            json.getJSONArray("tradeMarker_list");
                    Gson gson = new Gson();
                    for (int i = 0; i < clist.length(); i++) {
                        TradeAreaLevelNewHouseMarkerData tradeData = gson.fromJson(clist.get(i).toString(), new TypeToken<TradeAreaLevelNewHouseMarkerData>() {
                        }.getType());
                        tradeAreaLevelNewHouseMarkerDataMap.put(tradeData.getId(), tradeData);
                    }

                    Iterator it =
                            tradeAreaLevelNewHouseMarkerDataMap.keySet().iterator();
                    mBaiduMap.clear();
                    while (it.hasNext()) {
                        TradeAreaLevelNewHouseMarkerData dd = tradeAreaLevelNewHouseMarkerDataMap.get(it.next());
                        if (dd.getNewHouseCount() == 0)
                            continue;
                        LatLng la = new LatLng(dd.getLat(), dd.getLng());
                        tradeAreaLevelOverLay.setText(dd.getTradeName() + "\n" + dd.getNewHouseCount());
                        tradeAreaLevelOverLayView.invalidate();
                        Bitmap bm =
                                BaiduMapHelper.getViewBitmap(tradeAreaLevelOverLayView);
                        BitmapDescriptor ss = BitmapDescriptorFactory.fromBitmap(bm);
                        OverlayOptions ooA = new MarkerOptions().position(la).icon(ss)
                                .zIndex(9).perspective(true);
                        Object mMarkerA = (mBaiduMap.addOverlay(ooA));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
            }
        });
    }

    private void tradeAreaLevelRentHouse() {

        int[] cc = new int[2];
        mMapView.getLocationOnScreen(cc);
        int height = mMapView.getHeight();
        int width = mMapView.getWidth();
        LatLng nw =
                mMapView.getMap().getProjection().fromScreenLocation(new Point(cc[0], cc[1]));
        cc[0] = cc[0] + width;
        cc[1] = cc[1] + height;

        LatLng se =
                mMapView.getMap().getProjection().fromScreenLocation(new Point(cc[0], cc[1]));
        FinalHttp fn = new FinalHttp();
        fn.configRequestExecutionRetryCount(0);
        AjaxParams params = new AjaxParams();
        params.put("wLng", Double.toString(nw.longitude));
        params.put("eLng", Double.toString(se.longitude));
        params.put("nLat", Double.toString(nw.latitude));
        params.put("sLat", Double.toString(se.latitude));
        fn.get(MapFindHouseServerAPI.latlngTradeLevelRentHouseMarker, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object t) {
                try {
                    tradeAreaLevelRentHouseMarkerDataMap.clear();
                    JSONObject json = new JSONObject(t.toString());
                    JSONArray clist =
                            json.getJSONArray("tradeMarker_list");
                    Gson gson = new Gson();
                    for (int i = 0; i < clist.length(); i++) {
                        TradeAreaLevelRentHouseMarkerData tradeData = gson.fromJson(clist.get(i).toString(), new TypeToken<TradeAreaLevelRentHouseMarkerData>() {
                        }.getType());
                        tradeAreaLevelRentHouseMarkerDataMap.put(tradeData.getId(), tradeData);
                    }
                    Iterator it =
                            tradeAreaLevelRentHouseMarkerDataMap.keySet().iterator();
                    mBaiduMap.clear();
                    while (it.hasNext()) {
                        TradeAreaLevelRentHouseMarkerData dd = tradeAreaLevelRentHouseMarkerDataMap.get(it.next());
                        if (dd.getRentHouseCount() == 0)
                            continue;
                        LatLng la = new LatLng(dd.getLat(), dd.getLng());
                        tradeAreaLevelOverLay.setText(dd.getTradeName() + "\n" + dd.getRentHouseCount());
                        tradeAreaLevelOverLayView.invalidate();
                        Bitmap bm =
                                BaiduMapHelper.getViewBitmap(tradeAreaLevelOverLayView);
                        BitmapDescriptor ss = BitmapDescriptorFactory.fromBitmap(bm);
                        OverlayOptions ooA = new MarkerOptions().position(la).icon(ss)
                                .zIndex(9).perspective(true);

                        Object mMarkerA = (mBaiduMap.addOverlay(ooA));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
            }
        });

    }

    private void tradeAreaLevelSecondHouse() {
        int[] cc = new int[2];
        mMapView.getLocationOnScreen(cc);
        int height = mMapView.getHeight();
        int width = mMapView.getWidth();
        LatLng nw =
                mMapView.getMap().getProjection().fromScreenLocation(new Point(cc[0], cc[1]));
        cc[0] = cc[0] + width;
        cc[1] = cc[1] + height;

        LatLng se =
                mMapView.getMap().getProjection().fromScreenLocation(new Point(cc[0], cc[1]));
        FinalHttp fn = new FinalHttp();
        fn.configRequestExecutionRetryCount(0);
        AjaxParams params = new AjaxParams();
        params.put("wLng", Double.toString(nw.longitude));
        params.put("eLng", Double.toString(se.longitude));
        params.put("nLat", Double.toString(nw.latitude));
        params.put("sLat", Double.toString(se.latitude));
        fn.get(MapFindHouseServerAPI.latlngTradeLevelSecondHouseMarker, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object t) {
                try {
                    tradeAreaLevelSecondHouseMarkerDataMap.clear();
                    JSONObject json = new JSONObject(t.toString());
                    JSONArray clist =
                            json.getJSONArray("tradeMarker_list");
                    Gson gson = new Gson();
                    for (int i = 0; i < clist.length(); i++) {
                        TradeAreaLevelSecondHouseMarkerData tradeData = gson.fromJson(clist.get(i).toString(), new TypeToken<TradeAreaLevelSecondHouseMarkerData>() {
                        }.getType());
                        tradeAreaLevelSecondHouseMarkerDataMap.put(tradeData.getId(), tradeData);
                    }
                    Iterator it =
                            tradeAreaLevelSecondHouseMarkerDataMap.keySet().iterator();
                    mBaiduMap.clear();
                    while (it.hasNext()) {
                        TradeAreaLevelSecondHouseMarkerData dd = tradeAreaLevelSecondHouseMarkerDataMap.get(it.next());
                        if (dd.getSecondHouseCount() == 0)
                            continue;
                        LatLng la = new LatLng(dd.getLat(), dd.getLng());
                        tradeAreaLevelOverLay.setText(dd.getTradeName() + "\n" + dd.getSecondHouseCount());
                        tradeAreaLevelOverLayView.invalidate();
                        Bitmap bm =
                                BaiduMapHelper.getViewBitmap(tradeAreaLevelOverLayView);
                        BitmapDescriptor ss = BitmapDescriptorFactory.fromBitmap(bm);
                        OverlayOptions ooA = new MarkerOptions().position(la).icon(ss)
                                .zIndex(9).perspective(true);

                        Object mMarkerA = (mBaiduMap.addOverlay(ooA));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
            }
        });
    }


    public class MapStatusChanged implements BaiduMap.OnMapStatusChangeListener {

        @Override
        public void onMapStatusChangeStart(MapStatus mapStatus) {

        }

        @Override
        public void onMapStatusChange(MapStatus mapStatus) {

        }

        @Override
        public void onMapStatusChangeFinish(MapStatus mapStatus) {
            mapZoomLevel =
                    mMapView.getMap().getMapStatus().zoom;

            if (mapZoomLevel >= 16) {
                if (currentMapState == MapState.New)
                    latLngNewHouse();
                else if (currentMapState == MapState.Second)
                    latLngSecondHouse();
                else if (currentMapState == MapState.Rent)
                    latLngRentHouse();


            } else if (mapZoomLevel >= 14 && mapZoomLevel < 16) {
                if (currentMapState == MapState.New)
                    tradeAreaLevelNewHouse();
                else if (currentMapState == MapState.Second)
                    tradeAreaLevelSecondHouse();
                else if (currentMapState == MapState.Rent)
                    tradeAreaLevelRentHouse();
            } else {
                if (currentMapState == MapState.New)
                    countyLevelNewHouse();
                else if (currentMapState == MapState.Second)
                    countyLevelSecondHouse();
                else if (currentMapState == MapState.Rent)
                    countyLevelRentHouse();
            }

        }
    }

    public class MapClicked implements BaiduMap.OnMapClickListener {
        @Override
        public void onMapClick(LatLng latLng) {

        }

        @Override
        public boolean onMapPoiClick(MapPoi mapPoi) {
            return false;
        }
    }

    //定位监听回调
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            MyLocationData myLocationDataata = new MyLocationData.Builder().accuracy(location.getRadius()).direction(100).latitude(location.getLatitude()).longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(myLocationDataata);
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());
        }

    }



}