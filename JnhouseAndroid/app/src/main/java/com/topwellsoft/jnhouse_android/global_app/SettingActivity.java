package com.topwellsoft.jnhouse_android.global_app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.topwellsoft.androidutils.GlobalObjects;
import com.topwellsoft.androidutils.PreferencesUtils;
import com.topwellsoft.network.NetworkUtils;
import com.topwellsoft.network.TpAjaxCallback;

import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.manage.DataCleanManager;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.mine.AboutActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.mine.OptionActivity;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTextView_remove, mTextView_showcache, mTextView_update, mTextView_two_dimension_code, mTextView_about;
    private Button mButton_exit;
    private ImageView backImage;
    private LinearLayout cacheItem;
    private LinearLayout checkUpdateItem;

    SettingActivity theSettingActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        theSettingActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mTextView_remove = (TextView) findViewById(R.id.setting_remove_tv);
        mTextView_showcache = (TextView) findViewById(R.id.setting_showcache_tv);
        mTextView_update = (TextView) findViewById(R.id.setting_update_tv);
        mTextView_two_dimension_code = (TextView) findViewById(R.id.setting_two_dimension_code_tv);
        mTextView_about = (TextView) findViewById(R.id.setting_about_tv);

//        mButton_exit = (Button) findViewById(R.id.setting_exit_btn);
        cacheItem = (LinearLayout) findViewById(R.id.setting_cathe_item);
        checkUpdateItem = (LinearLayout) findViewById(R.id.setting_check_update);
        backImage = (ImageView) findViewById(R.id.setting_title_back);
        backImage.setOnClickListener(this);
//        mButton_exit.setOnClickListener(this);
        cacheItem.setOnClickListener(this);
        checkUpdateItem.setOnClickListener(this);

        try {
            mTextView_showcache.setText(DataCleanManager.getTotalCacheSize(this).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_title_back:
                onBackPressed();
                break;
            case R.id.setting_cathe_item:
                final LayoutInflater inflater = LayoutInflater.from(this);
                View v = inflater.inflate(R.layout.dialog_clearcache, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(v);
                final AlertDialog dialog = builder.create();//获取dialog
                dialog.show();//显示对话框
                Button mButton_confirm = (Button) v.findViewById(R.id.clearcache_confirm);
                Button mButton_cancle = (Button) v.findViewById(R.id.clearcache_concle);
                mButton_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DataCleanManager.clearAllCache(SettingActivity.this);
                        try {
                            mTextView_showcache.setText(DataCleanManager.getTotalCacheSize(SettingActivity.this).toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        dialog.cancel();
                        Toast.makeText(SettingActivity.this, "缓存已清除成功！", Toast.LENGTH_SHORT).show();
                    }
                });
                mButton_cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });


                break;
            case R.id.setting_check_update:
                LoginEntity info = (LoginEntity) PreferencesUtils.getObject(this, "loginEntity");

                if (info == null || info.getUserUUID() == null) {
                    ToastUtil.makeText(this, "请重新登录",
                            ToastUtil.LENGTH_SHORT)
                            .setAnimation(R.style.PopToast).show();
                    return;
                }

                AjaxParams params = new AjaxParams();
                params.put("clientType", "AndroidCustomer");
                params.put("userUUID", info.getUserUUID());
                NetworkUtils.asyncGet(GlobalAppServerAPI.checkUpdate, params, new TpAjaxCallback() {


                    public void onSuccess(JSONObject t) {
                        try {
                            String str =
                                    t.getString("clientVersion");
                            String str1 =
                                    t.getString("updateMessage");
                            PackageInfo pInfo = GlobalObjects.theApplication.getPackageManager().getPackageInfo(getPackageName(), 0);
                            String version = pInfo.versionName;

                            if (shouldUpdate(version, str))
                                showUpdateDialog(str1, theSettingActivity);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }


                    }


                });


                break;
            /*case R.id.setting_exit_btn:

                Intent intent = new Intent();
                intent.setClass(GlobalObjects.theApplication, TpLoginFragmentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;*/
        }

    }


    public static void checkUpdate(final Activity activity) {


        AjaxParams params = new AjaxParams();
        params.put("clientType", "AndroidCustomer");

        NetworkUtils.asyncGetSilent(GlobalAppServerAPI.checkUpdate, params, new TpAjaxCallback() {


            public void onSuccess(JSONObject t) {
                try {
                    String str =
                            t.getString("clientVersion");
                    String str1 =
                            t.getString("updateMessage");
                    PackageInfo pInfo = GlobalObjects.theApplication.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
                    String version = pInfo.versionName;

                    if (shouldUpdate(version, str))
                        showUpdateDialog(str1, activity);


                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }


            }


        });


    }


    private static boolean shouldUpdate(String currentVersion, String serverSideVersion) {
        String[] arr1 = currentVersion.split("\\.");
        String[] arr2 = serverSideVersion.split("\\.");
        if (Integer.parseInt(arr1[0]) < Integer.parseInt(arr2[0])) return true;
        if (Integer.parseInt(arr1[1]) < Integer.parseInt(arr2[1])) return true;
        if (Integer.parseInt(arr1[2]) < Integer.parseInt(arr2[2])) return true;

        return false;
    }


    private static void showUpdateDialog(String message, final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("检测到新版本");
        builder.setMessage(message);
        builder.setPositiveButton("下载", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent it = new Intent(activity, UpdateService.class);

                activity.startService(it);

            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        builder.show();
    }

    public void setting(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.setting_option:

                    startActivity(new Intent(SettingActivity.this, OptionActivity.class));
                    break;
                case R.id.setting_about_we:

                    startActivity(new Intent(SettingActivity.this, AboutActivity.class));
                    break;
            }

        }


    }
}
