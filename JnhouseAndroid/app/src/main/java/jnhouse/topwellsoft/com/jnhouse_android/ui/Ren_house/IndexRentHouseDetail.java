package jnhouse.topwellsoft.com.jnhouse_android.ui.Ren_house;

import android.app.Activity;
import android.content.Intent;
import android.content.PeriodicSync;
import android.graphics.Color;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.PreferencesUtils;
import com.topwellsoft.androidutils.StyleUtils;
import com.topwellsoft.jnhouse_android.realtime_order.ChoseTimeDialog;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.manage.ImageManager;
import jnhouse.topwellsoft.com.jnhouse_android.model.ConsultDetailEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.RegisterEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.RentHouseDetailEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.agent.AgentCardActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.EmChatHelper;
import jnhouse.topwellsoft.com.jnhouse_android.ui.login.TpLoginFragmentActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;
import jnhouse.topwellsoft.com.jnhouse_android.ui.secondary.report;
import jnhouse.topwellsoft.com.jnhouse_android.util.ImageUtil;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;
import jnhouse.topwellsoft.com.jnhouse_android.view.FluidLayout;

public class IndexRentHouseDetail extends Activity implements View.OnClickListener {

    TextView rent_guanzhu, rnet_fenxiang;
    @Bind(R.id.rent_detail_title)
    TextView rent_detail_title;
    @Bind(R.id.rent_detail_house_created)
    TextView rent_detail_created;
    @Bind(R.id.rent_detail_house_updated)
    TextView rent_detail_house_updated;
    @Bind(R.id.rent_house_detail_rent_type)
    TextView rent_type;
    @Bind(R.id.rent_house_detail_deposit)
    TextView rent_depsit;
    @Bind(R.id.rent_house_detail_feature)
    FluidLayout house_feature;//房源特色
    @Bind(R.id.rent_detail_price)
    TextView house_price;//租金
    @Bind(R.id.rent_detail_room)
    TextView house_room;//户型
    @Bind(R.id.rent_detail_totalarea)
    TextView house_totalarea;// 面积
    @Bind(R.id.rent_house_detail_floor)
    TextView house_floor;//楼层
    @Bind(R.id.rent_house_detail_toward)
    TextView house_toward;//朝向
    @Bind(R.id.rent_house_detail_fitment)
    TextView house_fitment;// 装修
    @Bind(R.id.rent_house_detail_support)
    TextView house_support;//配套设施
    @Bind(R.id.rent_house_detail_desc)
    TextView house_desc;//房源描述
    @Bind(R.id.rent_realname)
    TextView realname;//置业顾问真实姓名
    @Bind(R.id.rent_avatar)
    ImageView avatar;//置业顾问头像
    @Bind(R.id.rent_broker_mobile)
    TextView broker_mobile;//置业顾问联系方式
    @Bind(R.id.second_detail_progressbar)
    LinearLayout second_detail_progressbar;
    @Bind(R.id.rent_detail_fl_favor)
    FrameLayout rent_detail_fl_favor;
    @Bind(R.id.rent_detail_vp_ad)
    ViewPager rent_detail_vp;
    @Bind(R.id.rent_detail_container)
    LinearLayout rent_detail_countainer;
    TextView index_rent_house_title;
    ImageView index_rent_house_title_back;
    @Bind(R.id.rent_call_phone)
    ImageButton call_phone;
    @Bind(R.id.rent_sms)
    ImageButton rent_sms;
    @Bind(R.id.rent_borough_bank)
    TextView bank;
    @Bind(R.id.rent_borough_hospital)
    TextView hospital;
    @Bind(R.id.rent_borough_bus)
    TextView bus;
    @Bind(R.id.rent_elementary_school)
    TextView elementary_school;
    @Bind(R.id.rent_middle_school)
    TextView middle_school;
    @Bind(R.id.rent_borough_shop)
    TextView shop;
    @Bind(R.id.imgbtn_rent_order)
    ImageButton imgbtn_rent_order;
    @Bind(R.id.rent_chat)
    ImageButton chat;
    private String houseId, boroughId, broker_id, avatarurl, phone;
    protected ImageManager imageManager;
    private TextView report;
    private ChoseTimeDialog timeDialog;
    private MyDiaLog diaLog;
    private String see_time = "", see_demand = "", fengxiangstr;

    public void initDialog() {
        diaLog = new MyDiaLog(this);
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(false);
    }

    public void initTimeDialog() {
        timeDialog = new ChoseTimeDialog(this, true);
        timeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        timeDialog.setCancelable(true);
        timeDialog.show();
        timeDialog.setListener(new ChoseTimeDialog.ButtonListener() {
            @Override
            public void SureListener() {
                see_time = timeDialog.getTime();
                Log.i("###see_time", see_time);
                see_demand = timeDialog.getEditContent();
                OrderNewHouse(JnHouse_Record.Customer_Order);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rent_housedetail_layout);
        ButterKnife.bind(this);
        imageManager = new ImageManager(IndexRentHouseDetail.this);
        initListener();
        StyleUtils.initStatusBar(getWindow());
        Intent intent = getIntent();
        houseId = intent.getStringExtra("houseId");
        getPDAServerData();
        rent_sms.setOnClickListener(this);
        call_phone.setOnClickListener(this);
        chat.setOnClickListener(this);
        initDialog();
    }



    private void initListener() {
        imgbtn_rent_order.setOnClickListener(this);
        index_rent_house_title = (TextView) findViewById(R.id.index_second_house_title);
        index_rent_house_title_back = (ImageView) findViewById(R.id.index_second_house_title_back);
        rent_guanzhu = (TextView) findViewById(R.id.index_second_house_guanzhu);
        rnet_fenxiang = (TextView) findViewById(R.id.index_second_house_fenxiang);
        rnet_fenxiang.setVisibility(View.VISIBLE);
        rent_guanzhu.setVisibility(View.VISIBLE);
        index_rent_house_title.setVisibility(View.VISIBLE);
        index_rent_house_title_back.setOnClickListener(this);
        rnet_fenxiang.setOnClickListener(this);
        rent_guanzhu.setOnClickListener(this);
        report = (TextView) findViewById(R.id.rent_jubao);
        report.setOnClickListener(this);
        avatar.setOnClickListener(this);
    }

    private void getPDAServerData() {
        AjaxParams params = new AjaxParams();
        LoginEntity info = (LoginEntity) PreferencesUtils.getObject(IndexRentHouseDetail.this, "loginEntity");
//        if (info == null || info.getUserUUID() == null) {
//            ToastUtil.makeText(IndexRentHouseDetail.this, "请重新登录",
//                    ToastUtil.LENGTH_SHORT)
//                    .setAnimation(R.style.PopToast).show();
//            return;
//        }
        params.put("house_id", houseId);
//        params.put("user_id", info.getUser_id());
        FinalHttp fn = new FinalHttp();
        fn.get(JnHouse_Record.Key_rent_house_detail, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
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
                    JSONObject json = new JSONObject(t.toString());
                    Gson gson = new Gson();
                    RentHouseDetailEntity rentHouseDetailEntity = gson.fromJson(t.toString(), new TypeToken<RentHouseDetailEntity>() {
                    }.getType());
                    if (rentHouseDetailEntity != null) {
                        rent_detail_fl_favor.setVisibility(View.VISIBLE);
                        second_detail_progressbar.setVisibility(View.GONE);
                        rent_type.setText(rentHouseDetailEntity.getRent_type());
                        rent_detail_title.setText(rentHouseDetailEntity.getHouse_title());
                        rent_detail_created.setText(rentHouseDetailEntity.getCreated());
                        rent_detail_house_updated.setText(rentHouseDetailEntity.getUpdated());
                        rent_depsit.setText(rentHouseDetailEntity.getHouse_deposit());
                        index_rent_house_title.setText(rentHouseDetailEntity.getBorough_name());
                        house_price.setText(Integer.parseInt(rentHouseDetailEntity.getHouse_price().split("\\.")[0]) + rentHouseDetailEntity.getPrice_type());
                        house_room.setText(rentHouseDetailEntity.getHouse_room());
                        house_fitment.setText(rentHouseDetailEntity.getHouse_fitment());
                        house_floor.setText(rentHouseDetailEntity.getHouse_floor());
                        house_totalarea.setText(rentHouseDetailEntity.getHouse_totalarea() + "m²");
                        house_toward.setText(rentHouseDetailEntity.getHouse_toward());
                        house_support.setText(rentHouseDetailEntity.getHouse_support());
                        bus.setText(rentHouseDetailEntity.getBorough_bus());
                        elementary_school.setText(rentHouseDetailEntity.getElementary_school());
                        middle_school.setText(rentHouseDetailEntity.getMiddle_school());
                        shop.setText(rentHouseDetailEntity.getBorough_shop());
                        bank.setText(rentHouseDetailEntity.getBorough_bank());
                        hospital.setText(rentHouseDetailEntity.getBorough_hospital());
                        house_desc.setText(killHtml(rentHouseDetailEntity.getHouse_desc()));
                        avatarurl = rentHouseDetailEntity.getAvatar();
                        broker_id = rentHouseDetailEntity.getBroker_id();
                        phone = rentHouseDetailEntity.getBroker_mobile();
                        boroughId = rentHouseDetailEntity.getBorough_id();
                        fengxiangstr=rentHouseDetailEntity.getHouse_title()+rentHouseDetailEntity.getHouse_totalarea()+"m²"+rentHouseDetailEntity.getHouse_room()+rentHouseDetailEntity.getHouse_price()+"元/月"+"(来自济房网APP)"+"http://www.jnhouse.com/czqz/detail.php?id="+houseId;
                        if (avatarurl != null) {
                            imageManager.loadCircleImage(avatarurl, avatar);
                        }
                        realname.setText(rentHouseDetailEntity.getRealname());
                        broker_mobile.setText(rentHouseDetailEntity.getBroker_mobile());
                        genTag(rentHouseDetailEntity.getHouse_feature(), house_feature);
                        if (rentHouseDetailEntity.getPic_list() != null && rentHouseDetailEntity.getPic_list().size() > 0) {
                            ImageUtil imageUtil = new ImageUtil(rent_detail_vp, rent_detail_countainer, IndexRentHouseDetail.this);
                            imageUtil.dealWithTheViewRentHouse(rentHouseDetailEntity.getPic_list());
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.index_second_house_title_back:

                onBackPressed();
                break;
            case R.id.index_second_house_guanzhu:
                try {
                    getGuanZhuPDAServerData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rent_call_phone:
                callphone();
                break;
            case R.id.rent_sms:
                sms();
                break;
            case R.id.rent_jubao:
                Intent intent1 = new Intent();
                intent1.putExtra("houseId", houseId);
                intent1.putExtra("house_type", "1");
                intent1.setClass(IndexRentHouseDetail.this, jnhouse.topwellsoft.com.jnhouse_android.ui.secondary.report.class);
                startActivity(intent1);
                break;
            case R.id.rent_avatar:
                Intent intent4 = new Intent();
                intent4.putExtra("id", broker_id);
                intent4.setClass(IndexRentHouseDetail.this, AgentCardActivity.class);
                startActivity(intent4);
                break;
            case R.id.imgbtn_rent_order:
                initTimeDialog();
                break;
            case R.id.index_second_house_fenxiang:
                intent1=new Intent(Intent.ACTION_SEND);
                intent1.setType("text/plain");
                intent1.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent1.putExtra(Intent.EXTRA_TEXT, fengxiangstr);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent1, getTitle()));
                break;
            case  R.id.rent_chat:
                LoginEntity info = (LoginEntity) PreferencesUtils.getObject(IndexRentHouseDetail.this, "loginEntity");
                if (info == null || info.getUserUUID() == null) {
                    ToastUtil.makeText(  this, "请重新登录",
                            ToastUtil.LENGTH_SHORT)
                            .setAnimation(R.style.PopToast).show();
                    Intent intent7 =new Intent();
                    intent7.setClass(IndexRentHouseDetail.this, TpLoginFragmentActivity.class);
                    startActivity(intent7);
                    return;
                }else {
                    EmChatHelper.startChatActivity(IndexRentHouseDetail.this, phone, "notlist");}
                break;
        }
    }

    public void OrderNewHouse(String url) {
        diaLog.show();
        timeDialog.dismiss();
        LoginEntity loginEntity = (LoginEntity) PreferencesUtils.getObject(this, "loginEntity");
        if (loginEntity == null || loginEntity.getUserUUID() == null) {
            ToastUtil.makeText(  this, "请重新登录",
                    ToastUtil.LENGTH_SHORT)
                    .setAnimation(R.style.PopToast).show();
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put("userUUID", loginEntity.getUserUUID());
        params.put("user_id", loginEntity.getUser_id());
        params.put("user_phone", loginEntity.getUsername());
        params.put("user_name", loginEntity.getRealname());
        params.put("borough_id", boroughId);
        params.put("house_id", houseId);
        params.put("house_type", "3");
        params.put("visit_time", see_time);
//        params.put("visit_time", "2016-12-12周&nbsp;三（全天）");
        params.put("show_condition", see_demand);
        params.put("broker_id", "253948");
        params.put("broker_phone", "18363061513");
        params.put("broker_name", "红利");
        FinalHttp fh = new FinalHttp();
        fh.post(url, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("####", "onFailure");
                Log.i("####", errorNo + "");
                if (diaLog.isShowing()) {
                    diaLog.dismiss();
                }
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
                Log.i("####", "onSuccess");
                Log.i("####", t.toString());
                try {
                    JSONObject jsonObject = new JSONObject(t.toString());
                    Gson gson = new Gson();
                    ConsultDetailEntity consultDetailEntity = gson.fromJson(t.toString(), new TypeToken<ConsultDetailEntity>() {
                    }.getType());
                    switch (consultDetailEntity.getCode()) {
                        case 1:
                            showToast("您未登录");
                            break;
                        case -1:
                            showToast("异常");
                            break;
                        case 0:
                            showToast("预约中");
//                            if (timeDialog.isShowing())
                            break;
                        case 1902:
                            showToast("预约失败");
                            break;

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (diaLog.isShowing()) {
                        diaLog.dismiss();
                    }
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
                tv.setTextSize(10);

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
                params.setMargins(3,3,3, 3);
                fluidLayout.addView(tv, params);
            }
        }

    }


    private void getGuanZhuPDAServerData() throws IOException {

        LoginEntity info = (LoginEntity) PreferencesUtils.getObject(this, "loginEntity");
        if (info == null || info.getUserUUID() == null) {
            ToastUtil.makeText(  this, "请重新登录",
                    ToastUtil.LENGTH_SHORT)
                    .setAnimation(R.style.PopToast).show();
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put("type", "2");//房源类型
        params.put("house_id", houseId);//房源ID
        params.put("user_id", info == null ? "" : info.getUser_id());//用户ID
        params.put("userUUID", info == null ? "" : info.getUserUUID());//登录标识
        FinalHttp fh = new FinalHttp();
        fh.get(JnHouse_Record.Key_second_home_favor, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                ToastUtil.makeText(IndexRentHouseDetail.this, "关注失败",
                        ToastUtil.LENGTH_SHORT)
                        .setAnimation(R.style.PopToast).show();
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
                    RegisterEntity registerEntity = gson.fromJson(t.toString(), new TypeToken<RegisterEntity>() {
                    }.getType());

                    if (registerEntity != null) {

                        switch (registerEntity.getCode()) {

                            //未登录
                            case 1:
                                ToastUtil.makeText(IndexRentHouseDetail.this, "未登录",
                                        ToastUtil.LENGTH_SHORT)
                                        .setAnimation(R.style.PopToast).show();
                                Intent intent = new Intent();
                                intent.setClass(IndexRentHouseDetail.this, TpLoginFragmentActivity.class);
                                startActivity(intent);
                                break;

                            //异常
                            case -1:

                                break;

                            //关注成功
                            case 701:

                                ToastUtil.makeText(IndexRentHouseDetail.this, "关注成功",
                                        ToastUtil.LENGTH_SHORT)
                                        .setAnimation(R.style.PopToast).show();


                                break;

                            //取消关注
                            case 702:

                                ToastUtil.makeText(IndexRentHouseDetail.this, "取消关注",
                                        ToastUtil.LENGTH_SHORT)
                                        .setAnimation(R.style.PopToast).show();

                                break;
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sms() {
        Intent intent = new Intent();

        intent.setAction(intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phone));
        startActivity(intent);
    }

    public void callphone() {
        Intent intent = new Intent();
        intent.setAction(intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }

    public void showToast(String str) {
        ToastUtil.makeText(this, str, ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
    }




    //干掉HTML
    public static String killHtml(String s){
        if(!s.equals("")||s!=null){
            String str=s.replaceAll("<[.[^<]]*>","");
            String strs=str.replaceAll("&nbsp;", "");

            return strs;
        }else{
            return s;
        }
    }
}
