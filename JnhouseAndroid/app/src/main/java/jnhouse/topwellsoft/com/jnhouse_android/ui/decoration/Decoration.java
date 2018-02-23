package jnhouse.topwellsoft.com.jnhouse_android.ui.decoration;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.PreferencesUtils;
import com.topwellsoft.androidutils.StyleUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.DecorationEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.MainActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.MainTabsActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.release.ChooseCommunity;
import jnhouse.topwellsoft.com.jnhouse_android.util.EventCommunityUtil;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;
import jnhouse.topwellsoft.com.jnhouse_android.view.FilterView;

/**
 * Created by topwellsoft on 2016/6/21.
 * 装修界面
 */
public class Decoration extends Activity implements View.OnClickListener {
    private EditText mEditText_name, mEditText_borough, mEditText_phone;
    private TextView mTextView_house_room;
    private TextView mTextView_borough;
    ImageView backIcon;
    private Button mButton_application;
    private LinearLayout linearLayout;
    private String borought_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StyleUtils.initStatusBar(this.getWindow());
        setContentView(R.layout.tp_decoration);
        EventBus.getDefault().register(this);
        linearLayout = (LinearLayout) findViewById(R.id.choosecommunity_linearlayout);
        mEditText_name = (EditText) findViewById(R.id.name_edittext);
        mTextView_borough = (TextView) findViewById(R.id.borough_edittext);
        mEditText_phone = (EditText) findViewById(R.id.phone_edittext);
        mTextView_house_room = (TextView) findViewById(R.id.house_room_textview);
        mButton_application = (Button) findViewById(R.id.application_button);
//        mButton_application.setOnClickListener(this);
        mTextView_house_room.setOnClickListener(this);
        linearLayout.setOnClickListener(this);

     //   backIcon = (ImageView) findViewById(R.id.topPanel).findViewById(R.id.back_icon);
     //   backIcon.setOnClickListener(this);
    }

    public void Click(View view) {
        switch (view.getId()) {
            case R.id.back_icon:
                onBackPressed();
                break;
            case R.id.application_button:
                if (!"".equals(mEditText_name.getText().toString()) && !"".equals(mTextView_borough.getText().toString()) && !"".equals(mEditText_phone.getText().toString())) {
//                    getPDAServerData();
                    if (isMobileNO(mEditText_phone.getText().toString())) {
                        getPDAServerData();
                    } else {
                        Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "信息输入不完全", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.house_room_textview:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                //    指定下拉列表的显示数据
                final String[] rooms1 = {"一居室", "两居室", "三居室", "四居室", "五居室及以上"};
                //    设置一个下拉的列表选择项
                builder1.setItems(rooms1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mTextView_house_room = (TextView) findViewById(R.id.house_room_textview);
                        mTextView_house_room.setText(rooms1[i]);
                    }
                });
                builder1.create().show();
                break;
            case R.id.house_room_ib:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                //    指定下拉列表的显示数据
                final String[] rooms = {"一居室", "两居室", "三居室", "四居室", "五居室及以上"};
                //    设置一个下拉的列表选择项
                builder.setItems(rooms, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mTextView_house_room = (TextView) findViewById(R.id.house_room_textview);
                        mTextView_house_room.setText(rooms[i]);
                    }
                });
                builder.create().show();
                break;
            case R.id.choosecommunity_linearlayout:
                Intent intent = new Intent();
                intent.setClass(Decoration.this, ChooseCommunity.class);
                startActivity(intent);
                break;
        }
    }

    @Subscribe
    public void onEventMainThread(EventCommunityUtil eventUtil) {
        borought_name = eventUtil.getMsg();
        mTextView_borough.setText(borought_name);
    }

    public void getPDAServerData() {
        Log.i("#####","点击了getPDAServer");
        AjaxParams params = new AjaxParams();
        params.put("name", mEditText_name.getText().toString().trim());
//        params.put("borough", mEditText_borough.getText().toString().trim());
        params.put("borough", borought_name);
        params.put("house_room", mTextView_house_room.getText().toString().trim());
        params.put("phone", mEditText_phone.getText().toString().trim());

        FinalHttp fh = new FinalHttp();
        fh.get(JnHouse_Record.Key_decoration, params, new AjaxCallBack<Object>() {
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                Log.i("#####","" + errorNo + "\t" + strMsg);
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

                    JSONObject jsonObject = new JSONObject(t.toString());
                    Gson gson = new Gson();
                    DecorationEntity decorationEntity = gson.fromJson(t.toString(), new TypeToken<DecorationEntity>() {
                    }.getType());

                    if (decorationEntity != null) {
                        Log.i("#####","decorationEntity.getCode():" + decorationEntity.getCode());
                        switch (decorationEntity.getCode()) {

                            case 0:
                                //提交成功
                                PreferencesUtils.putObject(Decoration.this, "DecorationEntity", decorationEntity);
                                Intent intent = new Intent();
                                intent.setClass(Decoration.this, Confirm.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                break;

                            case 202:
                                //申请失败
                                ToastUtil.makeText(Decoration.this, "申请失败",
                                        ToastUtil.LENGTH_SHORT)
                                        .setAnimation(R.style.PopToast).show();
                                break;
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("#####","JSONException");
                }
            }
        });
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.house_room_textview:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                //    指定下拉列表的显示数据
                final String[] rooms1 = {"一居室", "两居室", "三居室", "四居室", "五居室及以上"};
                //    设置一个下拉的列表选择项
                builder1.setItems(rooms1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mTextView_house_room = (TextView) findViewById(R.id.house_room_textview);
                        mTextView_house_room.setText(rooms1[i]);
                    }
                });
                builder1.create().show();
                break;
            case R.id.house_room_ib:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                //    指定下拉列表的显示数据
                final String[] rooms = {"一居室", "两居室", "三居室", "四居室", "五居室及以上"};
                //    设置一个下拉的列表选择项
                builder.setItems(rooms, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mTextView_house_room = (TextView) findViewById(R.id.house_room_textview);
                        mTextView_house_room.setText(rooms[i]);
                    }
                });
                builder.create().show();
                break;
            case R.id.choosecommunity_linearlayout:
                Intent intent = new Intent();
                intent.setClass(Decoration.this, ChooseCommunity.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
