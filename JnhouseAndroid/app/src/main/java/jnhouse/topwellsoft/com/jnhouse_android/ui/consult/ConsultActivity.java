package jnhouse.topwellsoft.com.jnhouse_android.ui.consult;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.StyleUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.ConsultHeadLineAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.ConsultSpecificAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.manage.ImageManager;
import jnhouse.topwellsoft.com.jnhouse_android.model.ConsultDetailEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.ConsultList;
import jnhouse.topwellsoft.com.jnhouse_android.model.DealEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.HeadLineEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.PropertymarketEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.SpecificEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;

/**
 * Created by Administrator on 2016/7/5.
 */
public class ConsultActivity extends Activity implements View.OnClickListener {
    private ListView lv_top, lv_bottom;
    private TextView tv_buildname, tv_buildprice, tv_buildtime;
    private TextView tv_housename, tv_houseprice;
    private LinearLayout layout_review;
    private ImageView img_back;
    private ArrayList<HeadLineEntity> top_list;
    private ArrayList<SpecificEntity> bottom_list;
    private ConsultHeadLineAdapter topAdapter;
    private ConsultSpecificAdapter bottomAdapter;
    private ImageManager manager;
    private ImageView img_top, img_market;
    private TextView tv_consult_title;
    private MyDiaLog diaLog;
    private ConsultList consultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StyleUtils.initStatusBar(getWindow());
        setContentView(R.layout.activity_consult);
        init();
        initDialog();
        getConsultServerData(JnHouse_Record.Key_buy_house_guide);
        lv_top.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ConsultActivity.this, ConsultDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", top_list.get(position).getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        lv_bottom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ConsultActivity.this, ConsultDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", bottom_list.get(position).getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }



    private void initDialog() {
        diaLog = new MyDiaLog(ConsultActivity.this);
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(true);
        diaLog.show();
    }

    public void init() {
        manager = new ImageManager(ConsultActivity.this);
        lv_bottom = (ListView) this.findViewById(R.id.consult_lv_bottom);
        lv_top = (ListView) this.findViewById(R.id.consult_lv_top);
        tv_buildname = (TextView) this.findViewById(R.id.build_name);
        tv_buildprice = (TextView) this.findViewById(R.id.build_price);
        tv_buildtime = (TextView) this.findViewById(R.id.buile_time);
        tv_housename = (TextView) this.findViewById(R.id.consult_build_name);
        tv_houseprice = (TextView) this.findViewById(R.id.consult_build_price);
        layout_review = (LinearLayout) this.findViewById(R.id.layout_review);
        img_back = (ImageView) this.findViewById(R.id.consult_img_back);
        img_top = (ImageView) this.findViewById(R.id.consult_img_top);
        img_market = (ImageView) this.findViewById(R.id.consult_img_buildmarket);
        tv_consult_title = (TextView) this.findViewById(R.id.tv_consult_title);
        tv_consult_title.setText("购房指南");
        top_list = new ArrayList<HeadLineEntity>();
        bottom_list = new ArrayList<SpecificEntity>();
        img_back.setOnClickListener(this);
        img_top.setOnClickListener(this);
    }


    public void getConsultServerData(String url) {
        AjaxParams params = new AjaxParams();
        FinalHttp fh = new FinalHttp();
        fh.get(url, params, new AjaxCallBack<Object>() {

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
                    consultList = gson.fromJson(t.toString(), new TypeToken<ConsultList>() {
                    }.getType());
                    Log.i("###", t.toString());
                    if (consultList.getTt_list() != null && consultList.getTt_list().size() > 0) {
                        top_list = consultList.getTt_list();
                        topAdapter = new ConsultHeadLineAdapter(ConsultActivity.this, top_list);
                        lv_top.setAdapter(topAdapter);
                        setListViewHightBaseedOnChildren(lv_top);
                    }
                    if (consultList.getZt_list() != null && consultList.getZt_list().size() > 0) {
                        bottom_list = consultList.getZt_list();
                        bottomAdapter = new ConsultSpecificAdapter(ConsultActivity.this, bottom_list);
                        lv_bottom.setAdapter(bottomAdapter);
                        setListViewHightBaseedOnChildren(lv_bottom);
                    }
                    if (consultList.getGg_list() != null && consultList.getGg_list().size() > 0) {
                        manager.loadUrlImage(consultList.getGg_list().get(0).getPath(), img_top);
                    }
                    if (consultList.getEs_list() != null && consultList.getEs_list().size() > 0) {//成交
                        DealEntity dealEntity = consultList.getEs_list().get(0);
                        tv_buildname.setText(dealEntity.getBorough_name());
                        tv_buildprice.setText(dealEntity.getPrice());
                        tv_buildtime.setText(dealEntity.getDeal_date());
                    }
                    if (consultList.getLs_list() != null && consultList.getLs_list().size() > 0) {//楼市
                        PropertymarketEntity propertymarketEntity = consultList.getLs_list().get(0);
                        tv_housename.setText(propertymarketEntity.getBorough_name());
                        tv_houseprice.setText(propertymarketEntity.getBorough_avgprice());
                        //加载网络图片
                        manager.loadUrlImage(consultList.getLs_list().get(0).getBorough_thumb(), img_market);
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

    public void setListViewHightBaseedOnChildren(ListView listview) {
        ListAdapter adapter = listview.getAdapter();
        if (adapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View item = adapter.getView(i, null, listview);
            item.measure(0, 0);
            totalHeight += item.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = totalHeight + (listview.getDividerHeight() * (adapter.getCount() - 1));
        listview.setLayoutParams(params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.consult_img_back:
                finish();
                break;
            case R.id.consult_img_top:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(consultList.getGg_list().get(0).getLink()));
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
