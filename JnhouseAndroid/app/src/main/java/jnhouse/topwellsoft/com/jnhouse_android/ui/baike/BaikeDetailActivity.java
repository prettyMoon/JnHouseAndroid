package jnhouse.topwellsoft.com.jnhouse_android.ui.baike;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.StyleUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.BaikeDetailEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.ConsultDetailEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;

/**
 * Created by Administrator on 2016/7/5.
 */
public class BaikeDetailActivity extends Activity {
    private TextView tv_title, tv_time;
    private WebView detail_web;
    private Bundle bundle;
    private ImageView img_detail_back;
    private TextView tv_consult_title;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StyleUtils.initStatusBar(getWindow());
        setContentView(R.layout.activity_baike_detail);
        init();
        initDiaLog();
        getConsultDetailServerData(JnHouse_Record.Baike_Detail);
    }



    public void initDiaLog() {
        dialog = new MyDiaLog(BaikeDetailActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.show();
    }

    public void init() {
        bundle = getIntent().getExtras();
        img_detail_back = (ImageView) this.findViewById(R.id.consult_img_back);
        tv_title = (TextView) this.findViewById(R.id.baike_detail_title);
        tv_time = (TextView) this.findViewById(R.id.baike_detail_time);
        detail_web = (WebView) this.findViewById(R.id.baike_detail_web);
        tv_consult_title = (TextView) this.findViewById(R.id.tv_consult_title);
        tv_consult_title.setText("百科详情");
        img_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getConsultDetailServerData(String url) {

        AjaxParams params = new AjaxParams();
        params.put("newsId", bundle.getString("id"));
        FinalHttp fh = new FinalHttp();
        fh.get(url, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("####", "onFailure");
                if (dialog.isShowing()) {
                    dialog.dismiss();
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
                    BaikeDetailEntity baikeDetailEntity = gson.fromJson(t.toString(), new TypeToken<BaikeDetailEntity>() {
                    }.getType());
                    tv_title.setText(baikeDetailEntity.getTitle());
                    tv_time.setText(baikeDetailEntity.getPub_time());
                    detail_web.loadDataWithBaseURL(null, baikeDetailEntity.getContent(), "text/html", "utf-8", null);
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
