

package jnhouse.topwellsoft.com.jnhouse_android.util.Application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.topwellsoft.androidutils.GlobalObjects;
import com.topwellsoft.androidutils.PreferencesUtils;
import com.topwellsoft.baidumap.BaiduMapHelper;
import com.topwellsoft.baidumap.TPLocationListener;
import com.topwellsoft.jnhouse_android.AppLoginHelper;
import com.topwellsoft.jnhouse_android.constant.GlobalParas;
import com.topwellsoft.jnhouse_android.realtime_order.BaiduLocationStartupService;
import com.topwellsoft.network.NetworkUtils;

import java.util.List;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.model.SecondHouselyEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.EmChatHelper;

/**
 * Created by admin on 2016/6/8.
 */
public class JnHouseApplication extends Application {
    public static Context applicationContext;
    private static JnHouseApplication instance;
    public static AppLoginHelper appLoginHelper;

    // login user name
    public final String PREF_USERNAME = "username";

    /**
     * nickname for current user, the nickname instead of ID be shown when user receive notification from APNs
     */
    public static String currentUserNick = "";
    private List<SecondHouselyEntity> secondHouselyEntity;

    public List<SecondHouselyEntity> getSecondHouselyEntity() {
        return secondHouselyEntity;
    }

    public void setSecondHouselyEntity(List<SecondHouselyEntity> secondHouselyEntity) {
        this.secondHouselyEntity = secondHouselyEntity;
    }

    @Override
    public void onCreate() {
        MultiDex.install(this);
        super.onCreate();
        applicationContext = this;
        instance = this;
        GlobalObjects.initGlobalObjects(this);
        BaiduMapHelper.initBaiduMap(this);//初始化百度地图
        try {
            ApplicationInfo appInfo = this.getPackageManager().getApplicationInfo(getPackageName(), this.getPackageManager().GET_META_DATA);
            String msg = appInfo.metaData.getString("ServerURL");
            GlobalParas.URL = msg;
            GlobalObjects.urlPrefix = msg;
            GlobalObjects.statusBarColor = getResources().getColor(R.color.app_main_color);
            PreferencesUtils.putObject(this, "URL", GlobalParas.URL);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        //初始化环信sdk
        EmChatHelper.getInstance().init(applicationContext);
        appLoginHelper = new AppLoginHelper();
        NetworkUtils.loginHelper = appLoginHelper;

        //初始化默认登录状态
        SharedPreferences sharedPreferences = getSharedPreferences("hx_state", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("state", true);
        editor.commit();
        if (BaiduLocationStartupService.baiduLocationStartupService == null)
            BaiduLocationStartupService.baiduLocationStartupService = new BaiduLocationStartupService();
    }

    public static JnHouseApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
