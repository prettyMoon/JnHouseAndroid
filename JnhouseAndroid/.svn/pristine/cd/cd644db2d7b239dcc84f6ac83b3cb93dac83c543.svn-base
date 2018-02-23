package jnhouse.topwellsoft.com.jnhouse_android.ui.secondary;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
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
import jnhouse.topwellsoft.com.jnhouse_android.model.SecondHouseDetailEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.SecondHouseTrListEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.houseloan.MainActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.agent.AgentCardActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.EmChatHelper;
import jnhouse.topwellsoft.com.jnhouse_android.ui.login.TpLoginFragmentActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;
import jnhouse.topwellsoft.com.jnhouse_android.util.Application.JnHouseApplication;
import jnhouse.topwellsoft.com.jnhouse_android.util.ImageUtil;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;
import jnhouse.topwellsoft.com.jnhouse_android.view.FluidLayout;

/**
 * Created by admin on 2016/6/7.
 */
public class IndexSecondHouseDetail extends Activity implements View.OnClickListener {
    @Bind(R.id.broker_mobile)
    TextView broker_mobile;
    @Bind(R.id.broker_name)
    TextView broker_name;
    @Bind(R.id.second_detail_vp_ad)
    ViewPager second_detail_vp_ad;
    @Bind(R.id.second_detail_container)
    LinearLayout second_detail_container;
    @Bind(R.id.second_detail_house_feature)
    FluidLayout second_detail_house_feature;
    @Bind(R.id.second_detail_title)
    TextView second_detail_title;
    @Bind(R.id.second_detail_price)
    TextView second_detail_price;
    @Bind(R.id.second_detail_type)
    TextView second_detail_type;
    @Bind(R.id.second_detail_totalarea)
    TextView second_detail_totalarea;
    @Bind(R.id.second_detail_house_jj)
    TextView second_detail_house_jj;
    @Bind(R.id.second_detail_house_floor)
    TextView second_detail_house_floor;
    @Bind(R.id.second_detail_house_toward)
    TextView second_detail_house_toward;
    @Bind(R.id.second_detail_house_fitment)
    TextView second_detail_house_fitment;
    @Bind(R.id.second_detail_house_age)
    TextView second_detail_house_age;
//    @Bind(R.id.second_detail_house_facilities)
//    TextView second_detail_house_facilities;
    @Bind(R.id.second_detail_house_desc)
    TextView second_detail_house_desc;
    @Bind(R.id.second_detail_house_created)
    TextView second_detail_house_created;
    @Bind(R.id.second_detail_house_updated)
    TextView second_detail_house_updated;
    @Bind(R.id.second_detail_ly)
    LinearLayout second_detail_ly;
    @Bind(R.id.look_house)
    LinearLayout second_detail_look;
    @Bind(R.id.second_detail_progressbar)
    LinearLayout second_detail_progressbar;
    @Bind(R.id.second_detail_fl_favor)
    FrameLayout second_detail_fl_favor;
    @Bind(R.id.second_borough_bank)
    TextView bank;
    @Bind(R.id.second_elementary_school)
    TextView elementary_school;
    @Bind(R.id.second_middle_school)
    TextView middle_school;
    @Bind(R.id.second_borough_shop)
    TextView shop;
    @Bind(R.id.second_borough_hospital)
    TextView borough_hospital;
    @Bind(R.id.second_borough_bus)
    TextView bus;
    @Bind(R.id.second_detail_house_zoushi)
    WebView zoushi;
    TextView index_second_house_title;
    ImageView index_second_house_title_back;
    @Bind(R.id.avatar)
    ImageView avatar;
    @Bind(R.id.dkcs)
    TextView dkcs;
    TextView index_second_house_fenxiang, index_second_house_guanzhu;
    @Bind(R.id.second_detail_house_sj)
    TextView house_sj;
    @Bind(R.id.second_detail_house_yg)
    TextView house_yg;
    TextView jubao;
    TextView liuyan;
    @Bind(R.id.borogh_name)
    TextView borogh_name;
    @Bind(R.id.bottom_navigation)
    LinearLayout Navigation;
     private Button btn_second_order,btn_second_msm,loan_btn;

    private String houseId, boroughId, broker_id,mobile,brokername;
    private String avatarurl,fenxiangstr;

    protected ImageManager imageManager;
    SecondHouseTrListEntity s = new SecondHouseTrListEntity();
    private List<SecondHouseTrListEntity> mlist;


    private ChoseTimeDialog timeDialog;
    private MyDiaLog diaLog;
    private String see_time = "", see_demand = "";

    public void initDialog() {
        diaLog = new MyDiaLog(this);
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(false);
    }

    public void initTimeDialog() {
        timeDialog = new ChoseTimeDialog(IndexSecondHouseDetail.this, true);
        timeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        timeDialog.setCancelable(true);
        timeDialog.setListener(new ChoseTimeDialog.ButtonListener() {
            @Override
            public void SureListener() {
                see_time = timeDialog.getTime();
                Log.i("###see_time", see_time);
                see_demand = timeDialog.getEditContent();
                OrderNewHouse(JnHouse_Record.Customer_Order);
            }
        });
        timeDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ershou_housedetail_layout);
        ButterKnife.bind(this);
        imageManager = new ImageManager(IndexSecondHouseDetail.this);
        initListener();
        StyleUtils.initStatusBar(getWindow());
        Intent intent = getIntent();
        houseId = intent.getStringExtra("houseId");
       getPDAServerData();
        initDialog();
        btn_second_order=(Button)findViewById(R.id.btn_second_order);
        btn_second_order.setOnClickListener(this);
        btn_second_msm=(Button)findViewById(R.id.btn_second_msm);
        btn_second_msm.setOnClickListener(this);
        loan_btn=(Button)findViewById(R.id.loan_btn);
        loan_btn.setOnClickListener(this);
    }

    public void OrderNewHouse(String url) {
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
        params.put("house_type", "1");
        params.put("visit_time", see_time);
        params.put("show_condition", see_demand);
        params.put("broker_id", broker_id);
        params.put("broker_phone", mobile);
        params.put("broker_name",brokername);
        FinalHttp fh = new FinalHttp();
        fh.post(url, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("####", "onFailure");
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
                            if (timeDialog.isShowing())
                                timeDialog.dismiss();
                            break;
                        case 1902:
                            showToast("预约失败");
                            break;

                    }
                    if (diaLog.isShowing()) {
                        diaLog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    private void initListener() {

        index_second_house_title = (TextView) findViewById(R.id.index_second_house_title);
        index_second_house_title_back = (ImageView) findViewById(R.id.index_second_house_title_back);
        index_second_house_fenxiang = (TextView) findViewById(R.id.index_second_house_fenxiang);
        index_second_house_guanzhu = (TextView) findViewById(R.id.index_second_house_guanzhu);
        liuyan = (TextView) findViewById(R.id.second_liuyan);
        liuyan.setOnClickListener(this);
        index_second_house_guanzhu.setVisibility(View.VISIBLE);
        index_second_house_fenxiang.setVisibility(View.VISIBLE);
        index_second_house_fenxiang.setOnClickListener(this);
        index_second_house_guanzhu.setOnClickListener(this);
        index_second_house_title.setVisibility(View.VISIBLE);
        index_second_house_title_back.setOnClickListener(this);
        second_detail_ly.setOnClickListener(this);
        jubao = (TextView) findViewById(R.id.second_jubao);
        jubao.setOnClickListener(this);
        second_detail_look.setOnClickListener(this);
        avatar.setOnClickListener(this);
        TextView boro_detail = (TextView) findViewById(R.id.boro_detail);
        boro_detail.setOnClickListener(this);

    }

    private void getPDAServerData() {

        AjaxParams params = new AjaxParams();
        LoginEntity info = (LoginEntity) PreferencesUtils.getObject(IndexSecondHouseDetail.this, "loginEntity");
//        if (info == null || info.getUserUUID() == null) {
//            ToastUtil.makeText(  this, "请重新登录",
//                    ToastUtil.LENGTH_SHORT)
//                    .setAnimation(R.style.PopToast).show();
//            Intent intent =new Intent();
//            intent.setClass(IndexSecondHouseDetail.this, TpLoginFragmentActivity.class);
//            startActivity(intent);
//            return;
//        }
        params.put("house_id", houseId);//房源ID
//        if(info.getUser_id()!=null||info.getUser_id()=="") {
//            params.put("user_id", info.getUser_id());//ID
//        }
        FinalHttp fh = new FinalHttp();
        fh.get(JnHouse_Record.Key_second_home_detail, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {

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
                    SecondHouseDetailEntity secondHouseDetailEntity = gson.fromJson(t.toString(), new TypeToken<SecondHouseDetailEntity>() {
                    }.getType());
                    if (secondHouseDetailEntity != null) {
                        second_detail_fl_favor.setVisibility(View.VISIBLE);
                        second_detail_progressbar.setVisibility(View.GONE);
                        index_second_house_title.setText(secondHouseDetailEntity.getBorough_name());
                        second_detail_title.setText(secondHouseDetailEntity.getHouse_title());
                        second_detail_price.setText(secondHouseDetailEntity.getHouse_price() + "万");
                        second_detail_type.setText(secondHouseDetailEntity.getHouse_room());
                        second_detail_totalarea.setText(secondHouseDetailEntity.getHouse_totalarea() + "m²");
                        second_detail_house_jj.setText(secondHouseDetailEntity.getHouse_jj() + "元/m²");
                        second_detail_house_floor.setText(secondHouseDetailEntity.getHouse_floor());
                        second_detail_house_toward.setText(secondHouseDetailEntity.getHouse_toward());
                        second_detail_house_fitment.setText(secondHouseDetailEntity.getHouse_fitment());
                        second_detail_house_age.setText(secondHouseDetailEntity.getHouse_age());
                        dkcs.setText(secondHouseDetailEntity.getDkcs());

                        fenxiangstr=secondHouseDetailEntity.getHouse_title()+secondHouseDetailEntity.getHouse_room()+secondHouseDetailEntity.getHouse_price()+"万(来自济房网手机APP)"+"http://www.jnhouse.com/esf/detail.php?id="+houseId;
                        borogh_name.setText(secondHouseDetailEntity.getBorough_name());
                        second_detail_house_created.setText(secondHouseDetailEntity.getCreated());
                        second_detail_house_updated.setText(secondHouseDetailEntity.getUpdated());
                        broker_mobile.setText(secondHouseDetailEntity.getBroker_mobile());
                        broker_name.setText(secondHouseDetailEntity.getRealname());
                        house_yg.setText(secondHouseDetailEntity.getHouse_yg() + "元");
                        house_sj.setText(secondHouseDetailEntity.getHouse_sj() + "元");
                        avatarurl = secondHouseDetailEntity.getAvatar();
                        imageManager.loadCircleImage(avatarurl, avatar);
                        elementary_school.setText(secondHouseDetailEntity.getElementary_school());
                        middle_school.setText(secondHouseDetailEntity.getMiddle_school());
                        bus.setText(secondHouseDetailEntity.getBorough_bus());
                        borough_hospital.setText(secondHouseDetailEntity.getBorough_hospital());
                        bank.setText(secondHouseDetailEntity.getBorough_bank());
                        broker_id = secondHouseDetailEntity.getBroker_id();
                        genTag(secondHouseDetailEntity.getHouse_feature(), second_detail_house_feature);
                        boroughId = secondHouseDetailEntity.getBorough_id();
                        if (secondHouseDetailEntity.getPic_list() != null && secondHouseDetailEntity.getPic_list().size() > 0) {
                            ImageUtil imageUtil = new ImageUtil(second_detail_vp_ad, second_detail_container, IndexSecondHouseDetail.this);
                            imageUtil.dealWithTheView(secondHouseDetailEntity.getPic_list());
                        }
                        if (secondHouseDetailEntity.getBroker_mobile()==null||secondHouseDetailEntity.getBroker_mobile()==""){
                            Navigation.setVisibility(View.GONE);
                        }else {
                            mobile=secondHouseDetailEntity.getBroker_mobile();
                        }
                        mobile=secondHouseDetailEntity.getBroker_mobile();
                        WebSettings settings = zoushi.getSettings();
                        settings.setJavaScriptEnabled(true);
                        String url = JnHouse_Record.runChart+"tr_list=" + secondHouseDetailEntity.getTr_List() + "&tr_list_label=" + secondHouseDetailEntity.getBorough_name_encode() + "&tr_list_jn=" + secondHouseDetailEntity.getBs_List() + "&tr_list_jn_label=" + secondHouseDetailEntity.getBorough_name_jn_encode();
                        zoushi.postUrl(url, null);
                        settings.setLoadWithOverviewMode(true);
                        settings.setUseWideViewPort(true);
                        //设置WebView可触摸放大缩小
                        settings.setBuiltInZoomControls(true);
                        settings.setJavaScriptCanOpenWindowsAutomatically(true);

                        //设置Web视图
                        zoushi.setWebViewClient(new WebViewClient() {


                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                                view.postUrl(url, null);
                                return true;
                            }


                        });
//                        second_detail_house_desc.loadDataWithBaseURL(null, secondHouseDetailEntity.getHouse_desc(), "text/html", "utf-8", null);
                     second_detail_house_desc.setText(killHtml(secondHouseDetailEntity.getHouse_desc()));
                        //全局变量
                        JnHouseApplication application = ((JnHouseApplication) getApplicationContext());
                        application.setSecondHouselyEntity(secondHouseDetailEntity.getLylist());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
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
                params.setMargins(3, 3, 3, 3);
                fluidLayout.addView(tv, params);
            }
        }

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()) {

            case R.id.second_detail_ly:

                intent.setClass(IndexSecondHouseDetail.this, IndexSecondHousely.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

            case R.id.index_second_house_guanzhu:

                try {
                    getGuanZhuPDAServerData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                ll_guanzhu.setClickable(false);
                break;

            case R.id.index_second_house_title_back:

                onBackPressed();
                break;

            case R.id.second_jubao:
                Intent intent1 = new Intent();
                intent1.putExtra("houseId", houseId);
                intent1.putExtra("house_type", "1");
                intent1.setClass(IndexSecondHouseDetail.this, report.class);
                startActivity(intent1);
                break;
            case R.id.second_liuyan:
                Intent intent2 = new Intent();
                intent2.putExtra("houseId", houseId);
                intent2.setClass(IndexSecondHouseDetail.this, IndexSecondHouseLiuYan.class);
                startActivity(intent2);
                break;
            case R.id.look_house:
                Intent intent3 = new Intent();
                intent3.putExtra("houseId", houseId);
                intent3.putExtra("house_type", "1");
                intent3.setClass(IndexSecondHouseDetail.this, LookeHouse.class);
                startActivity(intent3);
                break;
            case R.id.avatar:
                Intent intent4 = new Intent();
                intent4.putExtra("id", broker_id);
                intent4.setClass(IndexSecondHouseDetail.this, AgentCardActivity.class);
                startActivity(intent4);
                break;
            case R.id.boro_detail:
                Intent intent5 = new Intent();
                intent5.putExtra("borough_id", boroughId);
                intent5.setClass(IndexSecondHouseDetail.this, IndexBoroughDetail.class);
                startActivity(intent5);
            case R.id.btn_second_order:
                initTimeDialog();
                break;
            case R.id.index_second_house_fenxiang:
                intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent.putExtra(Intent.EXTRA_TEXT, fenxiangstr);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, getTitle()));
                break;
            case R.id.btn_second_msm:
                LoginEntity info = (LoginEntity) PreferencesUtils.getObject(IndexSecondHouseDetail.this, "loginEntity");
                if (info == null || info.getUserUUID() == null) {
                    ToastUtil.makeText(  this, "请重新登录",
                            ToastUtil.LENGTH_SHORT)
                            .setAnimation(R.style.PopToast).show();
                    Intent intent7 =new Intent();
                    intent7.setClass(IndexSecondHouseDetail.this, TpLoginFragmentActivity.class);
                    startActivity(intent7);
                    return;
                }else {
                EmChatHelper.startChatActivity(IndexSecondHouseDetail.this, mobile, "notlist");}
               break;
            case R.id.loan_btn:
                Intent intent6=new Intent ();
                intent6.setClass(IndexSecondHouseDetail.this, MainActivity.class);
                startActivity(intent6);
                break;

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
        if (info == null || info.getUserUUID() == null) {
            ToastUtil.makeText(  this, "请重新登录",
                    ToastUtil.LENGTH_SHORT)
                    .setAnimation(R.style.PopToast).show();
            return;
        }
        params.put("type", "1");//房源类型
        params.put("house_id", houseId);//房源ID
        params.put("user_id", info == null ? "" : info.getUser_id());//用户ID
        params.put("userUUID", info == null ? "" : info.getUserUUID());//登录标识
        FinalHttp fh = new FinalHttp();
        fh.get(JnHouse_Record.Key_second_home_favor, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                ToastUtil.makeText(IndexSecondHouseDetail.this, "关注失败",
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
                                ToastUtil.makeText(IndexSecondHouseDetail.this, "未登录", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                Intent intent = new Intent();
                                intent.setClass(IndexSecondHouseDetail.this, TpLoginFragmentActivity.class);
                                startActivity(intent);
                                break;
                            //异常
                            case -1:
                                break;
                            //关注成功
                            case 701:
                                ToastUtil.makeText(IndexSecondHouseDetail.this, "关注成功", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                break;
                            //取消关注
                            case 702:
                                ToastUtil.makeText(IndexSecondHouseDetail.this, "取消关注", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                break;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showToast(String str) {
        ToastUtil.makeText(IndexSecondHouseDetail.this, str, ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
    }
}
