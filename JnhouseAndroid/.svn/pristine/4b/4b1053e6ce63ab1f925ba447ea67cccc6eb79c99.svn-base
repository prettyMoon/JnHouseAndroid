package com.topwellsoft.jnhouse_android.realtime_order;

import com.baidu.location.BDLocation;

import com.topwellsoft.androidutils.GlobalObjects;
import com.topwellsoft.androidutils.PreferencesUtils;
import com.topwellsoft.baidumap.LocationCallback;

import com.topwellsoft.network.NetworkUtils;
import com.topwellsoft.network.TpAjaxCallback;

import net.tsz.afinal.http.AjaxParams;

import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;

public class GuestLocationCallback extends LocationCallback {
    @Override
    public void currentLocation(BDLocation location) {
        try {
            LoginEntity loginEntity = (LoginEntity) PreferencesUtils.getObject(GlobalObjects.theApplication, "loginEntity");
            AjaxParams params = new AjaxParams();
            if (loginEntity != null) {
                params.put("userUUID", loginEntity.getUserUUID());
                params.put("lat", Double.toString(BaiduLocationStartupService.locationListener.location.getLatitude()));
                params.put("lng", Double.toString(BaiduLocationStartupService.locationListener.location.getLongitude()));
                //  NetworkUtils.asyncGetSilent(JnhHouseBroker_Record.report_position, params, new TpAjaxCallback() {

                //  });
            }
        } catch (Exception e) {
        }
    }

}