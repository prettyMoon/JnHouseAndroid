package com.topwellsoft.jnhouse_android.realtime_order;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.topwellsoft.androidutils.PreferencesUtils;
import com.topwellsoft.baidumap.TPLocationListener;
import com.topwellsoft.jnhouse_android.constant.GlobalParas;


public class BaiduLocationStartupService extends Service {
    public static TPLocationListener locationListener;//百度定位器
    GuestLocationCallback brokerLocationCallback;

    public static BaiduLocationStartupService baiduLocationStartupService = null;


    public BaiduLocationStartupService() {
        super();

        init();
    }

    public void init() {
        if (baiduLocationStartupService != null) return;
        baiduLocationStartupService = this;

        if (GlobalParas.URL == null)
            GlobalParas.URL = (String)
                    PreferencesUtils.getObject(this, "URL");
        locationListener = new TPLocationListener();
        //  locationListener.reportLocationURL = JnhHouseBroker_Record.report_position;
      //  brokerLocationCallback = new GuestLocationCallback();
       // locationListener.getCallbackList().add(brokerLocationCallback);
        locationListener.startLocationWork();
    }

    public void onCreate() {

        super.onCreate();

        init();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}