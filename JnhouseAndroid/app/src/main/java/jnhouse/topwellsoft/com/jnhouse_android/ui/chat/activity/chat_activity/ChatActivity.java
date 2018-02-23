package jnhouse.topwellsoft.com.jnhouse_android.ui.chat.activity.chat_activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.util.EasyUtils;
import com.topwellsoft.androidutils.PreferencesUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.FindUserEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.MainTabsActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.domain.ease_domain.ToChatUser;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.fragment.chat_fragment.ChatFragment;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.fragment.ease_fragment.EaseChatFragment;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;

public class ChatActivity extends BaseActivity {
    public static ChatActivity activityInstance;
    private EaseChatFragment chatFragment;
    private String toChatUsername;
    private String flag;
    public static String tochatusername;
    private String icon_str = "http://192.168.0.240:8080/jnhouseweb/lemon/fileDownload?fileName=ads/20160525/1464165152571.png";
    private MyDiaLog diaLog;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.em_activity_chat);
        initDiaLog();
        activityInstance = this;
        toChatUsername = getIntent().getExtras().getString("userId");
        flag = getIntent().getExtras().getString("flag");
        tochatusername = toChatUsername;
        if (flag == null) {
            //未知来源
        } else if (flag.equals("list")) {
            //从绘画列表里，直接从SharedPreferenced里面获取
            chatFragment = new ChatFragment();
            chatFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
        } else if (flag.equals("notlist")) {
            //不是从会话列表里跳转时，通过网络获取相关信息
            diaLog.show();
            LoginEntity loginEntity = (LoginEntity) PreferencesUtils.getObject(ChatActivity.this, "loginEntity");
            AjaxParams params = new AjaxParams();
            params.put("userUUID", loginEntity.getUserUUID());
            params.put("username", tochatusername);
            params.put("logo", "0");
            FinalHttp fh = new FinalHttp();
            fh.get(JnHouse_Record.Find_User, params, new AjaxCallBack<Object>() {

                @Override
                public void onFailure(Throwable t, int errorNo, String strMsg) {
                    Log.i("####", "onFailure");
                    if (diaLog.isShowing()) diaLog.dismiss();
                }

                @Override
                public void onLoading(long count, long current) {
                    super.onLoading(count, current);
                }

                @Override
                public void onStart() {
                    super.onStart();
                }

                @Override
                public void onSuccess(Object t) {
                    try {
                        Log.i("####", t.toString());
                        Gson gson = new Gson();
                        FindUserEntity findUserEntity = gson.fromJson(t.toString(), new TypeToken<FindUserEntity>() {
                        }.getType());
                        ToChatUser user = new ToChatUser();
                        user.setAvatar(findUserEntity.getAvatar());
                        user.setNickname(findUserEntity.getRealname());
                        PreferencesUtils.putObject(ChatActivity.this, tochatusername, user);
                        chatFragment = new ChatFragment();
                        chatFragment.setArguments(getIntent().getExtras());
                        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (diaLog.isShowing()) diaLog.dismiss();
                    }
                }
            });
        }
    }


    public void initDiaLog() {
        diaLog = new MyDiaLog(ChatActivity.this);
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // make sure only one chat activity is opened
        String username = intent.getExtras().getString("userId");
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
        if (EasyUtils.isSingleActivity(this)) {
            Intent intent = new Intent(this, MainTabsActivity.class);
            startActivity(intent);
        }
    }

    public String getToChatUsername() {
        return toChatUsername;
    }
}
