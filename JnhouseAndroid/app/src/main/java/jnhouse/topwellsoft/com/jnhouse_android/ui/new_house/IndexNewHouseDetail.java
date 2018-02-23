package jnhouse.topwellsoft.com.jnhouse_android.ui.new_house;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.Draw_Adapter;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.NearAndBrowseAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.ConsultDetailEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.NewHouseDetailEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.NewHouseDrawEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.RegisterEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.houseloan.MainActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.login.TpLoginFragmentActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;
import jnhouse.topwellsoft.com.jnhouse_android.ui.secondary.IndexBoroughDetail;
import jnhouse.topwellsoft.com.jnhouse_android.util.Application.JnHouseApplication;
import jnhouse.topwellsoft.com.jnhouse_android.util.ImageUtil;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;
import jnhouse.topwellsoft.com.jnhouse_android.view.Login;

public class IndexNewHouseDetail extends AppCompatActivity implements View.OnClickListener {
    private String houseId, fenxiangstr;
    private Draw_Adapter madapter;
    private NearAndBrowseAdapter nearAdapter;
    private NearAndBrowseAdapter browseAdapter;
    @Bind(R.id.call_phone)
    ImageButton call_phone;
    @Bind(R.id.new_house_sell_phone)
    TextView sellphone;
    @Bind(R.id.new_detail_house_jj)
    TextView borough_avgprice;//均价
    @Bind(R.id.new_house_borough_parking)
    TextView borough_parking;//停车位
    @Bind(R.id.new_house_borough_costs)
    TextView borough_costs;//物业费
    @Bind(R.id.new_house_borough_type)
    TextView borough_type;//建筑类型
    @Bind(R.id.new_house_property)
    TextView property;//产权年限
    @Bind(R.id.new_houuse_address)
    TextView address;//地址
    @Bind(R.id.borough_number)
    TextView borough_number;
    @Bind(R.id.new_house_borough_volume)
    TextView volume;//容积率
    @Bind(R.id.desc_environment)
    TextView enviroment;//绿化率
    @Bind(R.id.borough_company)
    TextView company;//物业公司
    @Bind(R.id.borough_developer)
    TextView developer;//开发商
    @Bind(R.id.new_house_fitment)
    TextView house_fitment;//装修状况
    @Bind(R.id.house_permit)
    TextView permit;//销售许可
    @Bind(R.id.new_house_completion)
    TextView completion;//入住时间
    @Bind(R.id.sell_time)
    TextView selltime;//开盘时间
    TextView index_new_house_title;
    ImageView index_new_house_back;
    TextView index_new_house_fenxiang, index_new_house_guanzhu;
    @Bind(R.id.second_detail_progressbar)
    LinearLayout new_detail_progressbar;
    @Bind(R.id.new_detail_fl_favor)
    FrameLayout new_detail_fl_favor;
    @Bind(R.id.draw_size)
    TextView draw_sise;
    @Bind(R.id.huxing)
    RecyclerView draw;
    @Bind(R.id.recomment_near)
    RecyclerView draw1;
    @Bind(R.id.recomment_browse)
    RecyclerView draw2;
    @Bind(R.id.zh_avg)
    RatingBar zh_avg;
    @Bind(R.id.eval_size)
    TextView eval_size;//楼盘评价数
    @Bind(R.id.jg_avg)
    TextView jg_avg;//评价价格平均分
    @Bind(R.id.dd_avg)
    TextView dd_avg;//评价地段平均分
    @Bind(R.id.pt_avg)
    TextView pt_avg;//评价配套平均分
    @Bind(R.id.jt_avg)
    TextView jt_avg;//评价交通平均分
    @Bind(R.id.hj_avg)
    TextView hj_avg;//评价环境平均分
    private List<NewHouseDrawEntity> huxinglist;
    private Float avg;//评分值
    @Bind(R.id.new_detail_container)
    LinearLayout new_detail_container;
    @Bind(R.id.new_detail_vp_ad)
    ViewPager new_dettail_vp;
    private String phone;
    @Bind(R.id.new_elementary_school)
    TextView new_elementary_school;
    @Bind(R.id.new_borough_bank)
    TextView bank;
    @Bind(R.id.new_middle_school)
    TextView middle_school;
    @Bind(R.id.new_borough_hospital)
    TextView hospital;
    @Bind(R.id.new_borough_shop)
    TextView shop;
    @Bind(R.id.new_borough_bus)
    TextView bus;
    @Bind(R.id.pingjia)
    LinearLayout pingjia;
    @Bind(R.id.newhouse_map)
    WebView house_map;
    private Button loanbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_housedetail_layout);
        ButterKnife.bind(this);
        initListener();
        StyleUtils.initStatusBar(getWindow());
        Intent intent = getIntent();
        houseId = intent.getStringExtra("houseId");
        getPDAServerData();
        draw.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, true));
        draw1.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, true));
        draw2.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, true));
        call_phone.setOnClickListener(this);
        loanbtn = (Button) findViewById(R.id.new_loanbtn);
        loanbtn.setOnClickListener(this);


    }


    private void initListener() {

        index_new_house_title = (TextView) findViewById(R.id.index_second_house_title);
        index_new_house_back = (ImageView) findViewById(R.id.index_second_house_title_back);
        index_new_house_fenxiang = (TextView) findViewById(R.id.index_second_house_fenxiang);
        index_new_house_guanzhu = (TextView) findViewById(R.id.index_second_house_guanzhu);
        index_new_house_fenxiang.setVisibility(View.VISIBLE);
        index_new_house_guanzhu.setVisibility(View.VISIBLE);
        index_new_house_title.setVisibility(View.VISIBLE);
        pingjia.setOnClickListener(this);
        index_new_house_back.setOnClickListener(this);
        index_new_house_fenxiang.setOnClickListener(this);
        index_new_house_guanzhu.setOnClickListener(this);
    }

    private void getPDAServerData() {

        AjaxParams params = new AjaxParams();
        LoginEntity info = (LoginEntity) PreferencesUtils.getObject(IndexNewHouseDetail.this, "loginEntity");
//        if (info == null || info.getUserUUID() == null) {
//            ToastUtil.makeText( this, "请重新登录",
//                    ToastUtil.LENGTH_SHORT)
//                    .setAnimation(R.style.PopToast).show();
//            return;
//        }
        params.put("house_id", houseId);//房源ID
//        params.put("user_id", info.getUser_id());//ID
        FinalHttp fh = new FinalHttp();
        fh.get(JnHouse_Record.Key_new_house_detail, params, new AjaxCallBack<Object>() {

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
                    final NewHouseDetailEntity newHouseDetailEntity = gson.fromJson(t.toString(), new TypeToken<NewHouseDetailEntity>() {
                    }.getType());
                    if (newHouseDetailEntity != null) {
                        new_detail_fl_favor.setVisibility(View.VISIBLE);
                        new_detail_progressbar.setVisibility(View.GONE);
                        index_new_house_title.setText(newHouseDetailEntity.getBorough_title());
                        address.setText(newHouseDetailEntity.getBorough_address());
                        property.setText(newHouseDetailEntity.getProperty() + "年");
                        address.setText(newHouseDetailEntity.getBorough_address());
                        property.setText(newHouseDetailEntity.getProperty() + "年");
                        completion.setText(newHouseDetailEntity.getBorough_completion());
                        borough_type.setText(newHouseDetailEntity.getBorough_type());
                        borough_number.setText(newHouseDetailEntity.getBorough_number());
                        volume.setText(newHouseDetailEntity.getBorough_volume());
                        enviroment.setText(newHouseDetailEntity.getDesc_environment());
                        borough_parking.setText(newHouseDetailEntity.getBorough_parking());
                        developer.setText(newHouseDetailEntity.getBorough_developer());
                        borough_costs.setText(newHouseDetailEntity.getBorough_costs());
                        house_fitment.setText(newHouseDetailEntity.getHouse_fitment());
                        permit.setText(newHouseDetailEntity.getHouse_permit());
                        selltime.setText(newHouseDetailEntity.getSell_time());
                        huxinglist = newHouseDetailEntity.getDraw_list();
                        if (newHouseDetailEntity.getDraw_list_size() != null && newHouseDetailEntity.getDraw_list_size() != "") {
                            draw_sise.setText("(" + newHouseDetailEntity.getDraw_list_size() + ")");
                        }
                        if (newHouseDetailEntity.getEval_size() != null && newHouseDetailEntity.getEval_size() != "") {
                            eval_size.setText("(" + newHouseDetailEntity.getEval_size() + ")");
                        }
                        jg_avg.setText(newHouseDetailEntity.getJg_avg());
                        jt_avg.setText(newHouseDetailEntity.getJt_avg());
                        dd_avg.setText(newHouseDetailEntity.getDd_avg());
                        pt_avg.setText(newHouseDetailEntity.getPt_avg());
                        hj_avg.setText(newHouseDetailEntity.getHj_avg());
                        avg = Float.parseFloat(newHouseDetailEntity.getZh_avg());
                        bus.setText(newHouseDetailEntity.getBorough_bus());
                        shop.setText(newHouseDetailEntity.getBorough_shop());
                        middle_school.setText(newHouseDetailEntity.getMiddle_school());
                        new_elementary_school.setText(newHouseDetailEntity.getElementary_school());
                        bank.setText(newHouseDetailEntity.getBorough_bank());
                        hospital.setText(newHouseDetailEntity.getBorough_hospital());
                        phone = newHouseDetailEntity.getSell_phone();
                        sellphone.setText(newHouseDetailEntity.getSell_phone());
                        zh_avg.setRating(avg);
                        if (newHouseDetailEntity.getPic_list() != null && newHouseDetailEntity.getPic_list().size() > 0) {
                            ImageUtil imageUtil = new ImageUtil(new_dettail_vp, new_detail_container, IndexNewHouseDetail.this);
                            imageUtil.dealWithTheViewNewHouse(newHouseDetailEntity.getPic_list());
                        }

                        ArrayList<Object> list = new ArrayList<Object>();
                        for (int i = 0; i < newHouseDetailEntity.getNear_borough_list().size(); i++) {
                            list.add(newHouseDetailEntity.getNear_borough_list().get(i));
                        }
                        nearAdapter = new NearAndBrowseAdapter(IndexNewHouseDetail.this, list);
                        draw1.setAdapter(nearAdapter);
                        ArrayList<Object> list1 = new ArrayList<Object>();
                        for (int i = 0; i < newHouseDetailEntity.getBrowse_list().size(); i++) {
                            list1.add(newHouseDetailEntity.getBrowse_list().get(i));
                        }
                        browseAdapter = new NearAndBrowseAdapter(IndexNewHouseDetail.this, list1);
                        draw2.setAdapter(browseAdapter);
                        madapter = new Draw_Adapter(IndexNewHouseDetail.this, huxinglist);
                        madapter.setOnItemClickListener(new Draw_Adapter.OnItemClickListener() {
                            @Override
                            public void OnItemClickListener(View view, int position) {
                                ArrayList<String> list = new ArrayList<String>();
                                for (int i = 0; i < huxinglist.size(); i++) {
                                    list.add(huxinglist.get(i).getPic_url());
                                }
                                Intent intent = new Intent(IndexNewHouseDetail.this, MoreImagesActivity.class);
                                intent.putExtra("urls", list);
                                startActivity(intent);
                            }
                        });
                        browseAdapter.setOnItemClickListener(new NearAndBrowseAdapter.OnItemClickListener() {
                            @Override
                            public void OnItemClickListener(View view, int position) {
                                Intent intent = new Intent(IndexNewHouseDetail.this, IndexNewHouseDetail.class);
                                intent.putExtra("houseId", newHouseDetailEntity.getBrowse_list().get(position).getB_borough_id());
                                startActivity(intent);
                            }
                        });
                        nearAdapter.setOnItemClickListener(new NearAndBrowseAdapter.OnItemClickListener() {
                            @Override
                            public void OnItemClickListener(View view, int position) {
                                Intent intent = new Intent(IndexNewHouseDetail.this, IndexNewHouseDetail.class);
                                intent.putExtra("houseId", newHouseDetailEntity.getBrowse_list().get(position).getB_borough_id());
                                startActivity(intent);
                            }
                        });
                        draw.setAdapter(madapter);
                        fenxiangstr = newHouseDetailEntity.getBorough_title() + "  " + newHouseDetailEntity.getBorough_avgprice() + "元/平" + " " + "(来自济房网手机APP)" + "http://www.jnhouse.com/lpzs/detail.php?id=" + houseId;
                        borough_avgprice.setText(newHouseDetailEntity.getBorough_avgprice() + "元/m²");

                        WebSettings settings = house_map.getSettings();
                        settings.setJavaScriptEnabled(true);
                        String url = "http://api.map.baidu.com/staticimage/v2?ak=AtsciaXkZ9qa87FAsjQHgynjufLH2GWn&width=500&height=300&center=济南&markers=恒大绿洲|116.783225,36.56368&zoom=10&markerStyles=l,A,0xff0000";
                        house_map.postUrl(url, null);
                        settings.setLoadWithOverviewMode(true);
                        settings.setUseWideViewPort(true);
                        //设置WebView可触摸放大缩小
//                        settings.setBuiltInZoomControls(true);
                        settings.setJavaScriptCanOpenWindowsAutomatically(true);
                        //设置Web视图
                        house_map.setWebViewClient(new WebViewClient() {


                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                                view.postUrl(url, null);
                                return true;
                            }


                        });


                        //全局变量
                        JnHouseApplication application = ((JnHouseApplication) getApplicationContext());
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
                getGuanZhuPDAServerData();
                break;
            case R.id.call_phone:
                final AlertDialog.Builder builder = new AlertDialog.Builder(IndexNewHouseDetail.this);
                builder.setTitle("确认拨打热线");
                builder.setMessage(phone);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callphone();
                        CallCounter();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
                break;

            case R.id.pingjia:
                Intent intent = new Intent();
                intent.putExtra("houseId", houseId);
                intent.setClass(IndexNewHouseDetail.this, evaluate.class);
                startActivity(intent);
                break;
            case R.id.new_loanbtn:
                Intent intent1 = new Intent();
                intent1.setClass(IndexNewHouseDetail.this, MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.index_second_house_fenxiang:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent.putExtra(Intent.EXTRA_TEXT, fenxiangstr);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, getTitle()));
                break;
        }
    }


    private void getGuanZhuPDAServerData() {

        LoginEntity info = (LoginEntity) PreferencesUtils.getObject(this, "loginEntity");
        if (info == null || info.getUserUUID() == null) {
            ToastUtil.makeText(this, "请重新登录",
                    ToastUtil.LENGTH_SHORT)
                    .setAnimation(R.style.PopToast).show();
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put("type", "3");//房源类型
        params.put("house_id", houseId);//房源ID
        params.put("user_id", info == null ? "" : info.getUser_id());//用户ID
        params.put("userUUID", info == null ? "" : info.getUserUUID());//登录标识
        FinalHttp fh = new FinalHttp();
        fh.get(JnHouse_Record.Key_second_home_favor, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                ToastUtil.makeText(IndexNewHouseDetail.this, "关注失败",
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
                            case 1: //未登录
                                ToastUtil.makeText(IndexNewHouseDetail.this, "未登录", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                Intent intent = new Intent();
                                intent.setClass(IndexNewHouseDetail.this, TpLoginFragmentActivity.class);
                                startActivity(intent);
                                break;
                            case -1://异常
                                break;
                            case 701://关注成功
                                ToastUtil.makeText(IndexNewHouseDetail.this, "关注成功", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                break;
                            case 702: //取消关注
                                ToastUtil.makeText(IndexNewHouseDetail.this, "取消关注", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
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

    //拨打电话计数
    private void CallCounter() {
        AjaxParams params = new AjaxParams();
        params.put("house_id", houseId);
        FinalHttp fp = new FinalHttp();
        fp.get(JnHouse_Record.Call_Counter, params, new AjaxCallBack<Object>() {
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }

            @Override
            public void onSuccess(Object o) {
                super.onSuccess(o);
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
            }

            @Override
            public void onStart() {

            }
        });

    }


}