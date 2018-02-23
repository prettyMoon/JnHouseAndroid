package com.topwellsoft.jnhouse_android;

import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.topwellsoft.androidutils.AppLoginInterface;
import com.topwellsoft.androidutils.GlobalObjects;
import com.topwellsoft.androidutils.PreferencesUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;


public class AppLoginHelper implements AppLoginInterface {

    @Override
    public String silentLogin() {
        LoginEntity loginEntity = (LoginEntity)
                PreferencesUtils.getObject(GlobalObjects.theApplication.getApplicationContext(), "loginEntity");
        if (loginEntity.getUsername() == null) {
            Toast.makeText(GlobalObjects.theApplication.getApplicationContext(), "请重新登录.", Toast.LENGTH_SHORT).show();
            return null;
        }
        final String username = loginEntity.getUsername();
        final String password = loginEntity.getPassword();
        final AjaxParams params = new AjaxParams();
        params.put("username", username);
        params.put("password", password);
        params.put("logo", "0");


        Thread tt = new Thread(new Runnable() {//
            @Override
            public void run() {
                FinalHttp fh = new FinalHttp();
                fh.configRequestExecutionRetryCount(0);
                Object t = fh.getSync(JnHouse_Record.Key_login, params);
                try {
                    JSONObject jsonObject = new JSONObject(t.toString());
                    Gson gson = new Gson();
                    final LoginEntity loginEntity = gson.fromJson(t.toString(), new TypeToken<LoginEntity>() {
                    }.getType());
                    if (loginEntity != null) {
                        updateState(loginEntity);
                        switch (loginEntity.getCode()) {
                            case 0:
                                EMClient.getInstance().login(username, loginEntity.getHx_pwd(), new EMCallBack() {//回调
                                    @Override
                                    public void onSuccess() {
                                        EMClient.getInstance().groupManager().loadAllGroups();
                                        EMClient.getInstance().chatManager().loadAllConversations();
                                    }

                                    @Override
                                    public void onProgress(int progress, String status) {
                                    }

                                    @Override
                                    public void onError(int code, String message) {
                                        if (code == 204 || code == 101) { //如果未注册环信且原用户名是手机号则直接注册
                                            if (isMobileNO(username)) {
                                                new Thread(new Runnable() {//
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            EMClient.getInstance().createAccount(username, loginEntity.getHx_pwd());
                                                            //注册成功后直接登录
                                                            EMClient.getInstance().login(username, loginEntity.getHx_pwd(), new EMCallBack() {
                                                                @Override
                                                                public void onSuccess() {
                                                                    EMClient.getInstance().groupManager().loadAllGroups();
                                                                    EMClient.getInstance().chatManager().loadAllConversations();

                                                                }

                                                                @Override
                                                                public void onError(int i, String s) {
                                                                }

                                                                @Override
                                                                public void onProgress(int i, String s) {
                                                                }
                                                            });
                                                        } catch (HyphenateException e) {
                                                        }
                                                    }
                                                }).start();
                                            }

                                        } else {
                                        }
                                    }
                                });
                                break;
                            case -1://登录异常
                                break;
                            case 100: //用户名密码错误
                                break;
                            default:
                                break;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

        );
        tt.start();
        try {
            tt.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        loginEntity = (LoginEntity)
                PreferencesUtils.getObject(GlobalObjects.theApplication.getApplicationContext(), "loginEntity");
        if (loginEntity != null) return loginEntity.getUserUUID();
        else return null;
    }

    public boolean isMobileNO(String mobiles) {

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();

    }

    public void updateState(LoginEntity loginEntity) {
        PreferencesUtils.putObject(GlobalObjects.theApplication.getApplicationContext(), "loginEntity", loginEntity);
    }
}