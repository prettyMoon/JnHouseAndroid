package com.topwellsoft.jnhouse_android.realtime_order;


import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.topwellsoft.baidumap.LocationCallback;

import jnhouse.topwellsoft.com.jnhouse_android.R;

public class RealtimeOrderLocationCallback extends LocationCallback {
    public BaiduMap mBaiduMap;
    private MarkerOptions markerOptions;
    private Marker marker;

    @Override
    synchronized public void currentLocation(BDLocation location) {

        try {
            MyLocationData locData = new MyLocationData.Builder().latitude(location.getLatitude()).longitude(location.getLongitude()).build();
            //设置图标在地图上的位置
            //   mBaiduMap.setMyLocationData(locData);
            // 开始移动百度地图的定位地点到中心位置

            LatLng ll = new LatLng(locData.latitude, locData.longitude);

            MapStatus mMapStatus = new MapStatus.Builder().zoom(16).target(ll).build();
            MapStatusUpdate u = MapStatusUpdateFactory.newMapStatus(mMapStatus);
            mBaiduMap.animateMapStatus(u);


            //LatLng latLng = mBaiduMap.getMapStatus().target;
            //准备 marker 的图片
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.my_position);
            //准备 marker option 添加 marker 使用
            markerOptions = new MarkerOptions().icon(bitmap).position(ll);
            //获取添加的 marker 这样便于后续的操作
            marker = (Marker) mBaiduMap.addOverlay(markerOptions);

        } catch (Exception e) {
        }
    }
}