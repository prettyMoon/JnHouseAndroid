package jnhouse.topwellsoft.com.jnhouse_android.ui;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.topwellsoft.androidutils.PreferencesUtils;
import com.topwellsoft.androidutils.StyleUtils;
import com.topwellsoft.jnhouse_android.global_app.SettingActivity;
import com.topwellsoft.jnhouse_android.visit_schedule.VisitScheduleFragment;

import java.util.List;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.model.CmdMessageEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.NullEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.Constant;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.EmChatHelper;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.fragment.chat_fragment.ConversationListFragment;
import jnhouse.topwellsoft.com.jnhouse_android.ui.login.TpLoginFragmentActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.login.UsernameResetActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.OperationDialog;
import jnhouse.topwellsoft.com.jnhouse_android.util.TabManager;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

/**
 * Created by admin on 2016/5/23.
 */
public class MainTabsActivity extends FragmentActivity implements View.OnClickListener {

    private RelativeLayout radio_maintab_a, radio_maintab_b, radio_maintab_c,
            radio_maintab_n;
    private TabManager mTabManager;
    private TabHost mTabHost;
    private long firstTime = 0;
    private TextView unreadLabel;
    private boolean isConflictDialogShow;
    private android.app.AlertDialog.Builder conflictBuilder;
    private int currentTabIndex = -1;
    private ConversationListFragment conversationListFragment;
    private EMChatManager manager;
    private OperationDialog operationDialog;
    public static float bottomHeight;
    public static float sx = 0, sy = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StyleUtils.initStatusBar(getWindow());
        setContentView(R.layout.main_tabs_activity);
        LinearLayout linearLayout = (LinearLayout) this.findViewById(R.id.layout_bottom);
        bottomHeight = linearLayout.getHeight();
        initView();
        sx = getWindow().getWindowManager().getDefaultDisplay().getWidth() - 120;
        sy = getWindow().getWindowManager().getDefaultDisplay().getWidth();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            String packageName = getPackageName();
//            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
//            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
//                Intent intent = new Intent();
//                intent.setAction(android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
//                intent.setData(Uri.parse("package:" + packageName));
//                startActivity(intent);
//            }
//        }
        manager = EMClient.getInstance().chatManager();
        manager.addMessageListener(messageListener);


        if (savedInstanceState != null && savedInstanceState.getBoolean(Constant.ACCOUNT_REMOVED, false)) {
            EmChatHelper.getInstance().logout(false, null);
            finish();
            startActivity(new Intent(this, TpLoginFragmentActivity.class));
            return;
        } else if (savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false)) {
            finish();
            startActivity(new Intent(this, TpLoginFragmentActivity.class));
            return;
        }
        SharedPreferences sharedPreferences = getSharedPreferences("hx_state", Activity.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("state", true)) {
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    initOperationDialog();
                    operationDialog.show();
                }
            }, 2000);

        }
        SettingActivity.checkUpdate(this);


    }

    public void initOperationDialog() {
        operationDialog = new OperationDialog(MainTabsActivity.this);
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
                Intent intent = new Intent(MainTabsActivity.this, UsernameResetActivity.class);
                startActivity(intent);
                operationDialog.dismiss();
            }
        });
        operationDialog.show();
        operationDialog.setContent("检测到您未验证手机号码，不验证无法使用聊天服务，是否立即去验证？\n您也可以在  “我的”——验证手机号  完成验证");
        operationDialog.setBtn_right("去验证");
        operationDialog.setBtn_left("取消");
        operationDialog.setCancelable(false);
    }

    EMMessageListener messageListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            // notify new message
            for (EMMessage message : messages) {
                EmChatHelper.getInstance().getNotifier().onNewMsg(message);
            }
            refreshUIWithMessage();
            Log.i("###  messagesBody", "收到消息");
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            Log.i("###  ", "tuisong ");
            Intent intent = null;
            PendingIntent pi = null;
            Gson gson = new Gson();
            CmdMessageEntity cmdMessageEntity;
            Log.i("###  ", messages.get(0).getBody().toString());
            String str = messages.get(0).getBody().toString().substring(5, messages.get(0).getBody().toString().length() - 1);
            Log.i("###  ", "tuisong ");
            String service = Context.NOTIFICATION_SERVICE;
            NotificationManager nm = (NotificationManager) getSystemService(service);          //获得系统级服务，用于管理消息
            Notification n = new Notification();
            NullEntity nullEntity = gson.fromJson(str, new TypeToken<NullEntity>() {
            }.getType());
            switch (nullEntity.getLogo()) {
                case 1:
                    Log.i("###  ", "tuisong ");
                    cmdMessageEntity = gson.fromJson(str, new TypeToken<CmdMessageEntity>() {
                    }.getType());
                    n.icon = R.drawable.orange_time;                                                //设置图标
                    n.tickerText = "您的订单已被抢到";                                        // 设置消息
                    n.when = System.currentTimeMillis();
                    n.flags |= Notification.FLAG_ONGOING_EVENT; // 将此通知放到通知栏的"Ongoing"即"正在运行"组中
                    n.flags |= Notification.FLAG_SHOW_LIGHTS;
                    n.flags |= Notification.DEFAULT_ALL;
                    n.defaults = Notification.DEFAULT_LIGHTS;


                    //切换到看房选项卡
                    //intent = new Intent(MainTabsActivity.this, CalendarActivity.class);
                    //intent.putExtra("flag", "schedule");
                    currentTabIndex = 1;
                    mTabHost.setCurrentTab(1);
                    radio_maintab_a.setSelected(false);
                    radio_maintab_b.setSelected(true);
                    radio_maintab_c.setSelected(false);
                    radio_maintab_n.setSelected(false);


                    //消息触发后调用
                    pi = PendingIntent.getActivity(MainTabsActivity.this, 0, intent, 0);
                    //设置事件信息就是拉下标题后显示的内容
                    n.setLatestEventInfo(MainTabsActivity.this, "您的订单已被" + cmdMessageEntity.getBroker_name() + "抢到", "点击查看", pi);
                    nm.notify(1, n);      //发送通知
                    break;
                case 2:
                    cmdMessageEntity = gson.fromJson(str, new TypeToken<CmdMessageEntity>() {
                    }.getType());
                    n.icon = R.drawable.orange_time;                                                //设置图标
                    n.tickerText = "您的订单已被接到";                                        // 设置消息
                    n.when = System.currentTimeMillis();
                    n.flags |= Notification.FLAG_ONGOING_EVENT; // 将此通知放到通知栏的"Ongoing"即"正在运行"组中
                    n.flags |= Notification.FLAG_SHOW_LIGHTS;
                    n.flags |= Notification.DEFAULT_ALL;
                    n.defaults = Notification.DEFAULT_LIGHTS;

                    //切换到看房选项卡
                    //                  intent = new Intent(MainTabsActivity.this, CalendarActivity.class);
                    //                intent.putExtra("flag","schedule");
                    currentTabIndex = 1;
                    mTabHost.setCurrentTab(1);
                    radio_maintab_a.setSelected(false);
                    radio_maintab_b.setSelected(true);
                    radio_maintab_c.setSelected(false);
                    radio_maintab_n.setSelected(false);

                    //消息触发后调用
                    pi = PendingIntent.getActivity(MainTabsActivity.this, 0, intent, 0);
                    //设置事件信息就是拉下标题后显示的内容
                    n.setLatestEventInfo(MainTabsActivity.this, "您的订单已被" + cmdMessageEntity.getBroker_name() + "接到", "点击查看", pi);
                    nm.notify(1, n);      //发送通知
                    break;

            }
        }

        @Override
        public void onMessageReadAckReceived(List<EMMessage> messages) {
        }

        @Override
        public void onMessageDeliveryAckReceived(List<EMMessage> message) {
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
        }
    };

    private void refreshUIWithMessage() {
        runOnUiThread(new Runnable() {
            public void run() {

                // refresh unread count
                updateUnreadLabel();
                if (currentTabIndex == 2) {
                    // refresh conversation list
                    if (conversationListFragment != null) {
                        conversationListFragment.refresh();
                    }
                }
            }
        });
    }

    public void initView() {

        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();
        mTabManager = new TabManager(this, mTabHost, android.R.id.tabcontent);
        mTabManager.addTab(mTabHost.newTabSpec("首页").setIndicator(""), MainActivity.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("日程").setIndicator(""), VisitScheduleFragment.class, null);
        conversationListFragment = new ConversationListFragment();
        mTabManager.addTab(mTabHost.newTabSpec("消息").setIndicator(""), ConversationListFragment.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("我的").setIndicator(""), TpMineFragment.class, null);
        unreadLabel = (TextView) this.findViewById(R.id.unread_msg_number);
        radio_maintab_a = (RelativeLayout) findViewById(R.id.radio_maintab_a);
        radio_maintab_b = (RelativeLayout) findViewById(R.id.radio_maintab_b);
        radio_maintab_c = (RelativeLayout) findViewById(R.id.radio_maintab_c);
        radio_maintab_n = (RelativeLayout) findViewById(R.id.radio_maintab_n);
        radio_maintab_a.setSelected(true);
        radio_maintab_a.setOnClickListener(this);
        radio_maintab_b.setOnClickListener(this);
        radio_maintab_c.setOnClickListener(this);
        radio_maintab_n.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.radio_maintab_a:
                currentTabIndex = 0;
                mTabHost.setCurrentTab(0);
                radio_maintab_a.setSelected(true);
                radio_maintab_b.setSelected(false);
                radio_maintab_c.setSelected(false);
                radio_maintab_n.setSelected(false);
                break;
            case R.id.radio_maintab_b:
                final LoginEntity info;
//                info = (LoginEntity) PreferencesUtils.getObject(MainTabsActivity.this, "loginEntity");
//                if (info == null) {
//                    Intent intent = new Intent();
//                    intent.setClass(MainTabsActivity.this, TpLoginFragmentActivity.class);
//                    startActivity(intent);
//                } else {
                    currentTabIndex = 1;
                    mTabHost.setCurrentTab(1);
                    radio_maintab_a.setSelected(false);
                    radio_maintab_b.setSelected(true);
                    radio_maintab_c.setSelected(false);
                    radio_maintab_n.setSelected(false);
//                }
                break;
            case R.id.radio_maintab_c:

//                info = (LoginEntity) PreferencesUtils.getObject(MainTabsActivity.this, "loginEntity");
//                if (info == null) {
//                    Intent intent = new Intent();
//                    intent.setClass(MainTabsActivity.this, TpLoginFragmentActivity.class);
//                    startActivity(intent);
//                } else {
                    currentTabIndex = 2;
                    unreadLabel.setVisibility(View.INVISIBLE);
                    mTabHost.setCurrentTab(2);
                    radio_maintab_a.setSelected(false);
                    radio_maintab_b.setSelected(false);
                    radio_maintab_c.setSelected(true);
                    radio_maintab_n.setSelected(false);
//                }
                break;
            case R.id.radio_maintab_n:
                currentTabIndex = 3;
                mTabHost.setCurrentTab(3);
                radio_maintab_a.setSelected(false);
                radio_maintab_b.setSelected(false);
                radio_maintab_c.setSelected(false);
                radio_maintab_n.setSelected(true);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.removeMessageListener(messageListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.addMessageListener(messageListener);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 1000) {// 如果两次按键时间间隔大于1000毫秒，则不退出
                ToastUtil.makeText(this, "再按一次退出应用", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                firstTime = secondTime;// 更新firstTime
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getBooleanExtra(Constant.ACCOUNT_CONFLICT, false) && !isConflictDialogShow) {
            showToast(Constant.ACCOUNT_CONFLICT);
        } else if (intent.getBooleanExtra(Constant.ACCOUNT_REMOVED, false)) {
            showToast(Constant.ACCOUNT_REMOVED);
        }
    }


    public int getUnreadMsgCountTotal() {
        int unreadMsgCountTotal = 0;
        int chatroomUnreadMsgCount = 0;
        unreadMsgCountTotal = EMClient.getInstance().chatManager().getUnreadMsgsCount();
        for (EMConversation conversation : EMClient.getInstance().chatManager().getAllConversations().values()) {
            if (conversation.getType() == EMConversation.EMConversationType.ChatRoom)
                chatroomUnreadMsgCount = chatroomUnreadMsgCount + conversation.getUnreadMsgCount();
        }
        return unreadMsgCountTotal - chatroomUnreadMsgCount;
    }

    /**
     * update unread message count
     */
    public void updateUnreadLabel() {
        int count = getUnreadMsgCountTotal();
        if (count > 0) {
            unreadLabel.setVisibility(View.VISIBLE);
        } else {
            unreadLabel.setVisibility(View.INVISIBLE);
        }
    }

    public void showToast(String str) {
        ToastUtil.makeText(this, str, ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
    }
}
