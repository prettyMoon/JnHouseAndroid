package jnhouse.topwellsoft.com.jnhouse_android.ui.consult;

import android.app.Activity;
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
import jnhouse.topwellsoft.com.jnhouse_android.model.ConsultDetailEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;

/**
 * Created by Administrator on 2016/7/5.
 */
public class ConsultDetailActivity extends Activity {
    private TextView tv_title, tv_author, tv_time;
    private WebView detail_web;
    private Bundle bundle;
    private ImageView img_detail_back;
    private TextView tv_consult_title;
    private MyDiaLog diaLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StyleUtils.initStatusBar(getWindow());
        setContentView(R.layout.activity_consult_detail);
        init();
        initDialog();
        getConsultDetailServerData(JnHouse_Record.Key_buy_house_guide_detail);
    }



    public void init() {
        bundle = getIntent().getExtras();
        img_detail_back = (ImageView) this.findViewById(R.id.consult_img_back);
        tv_title = (TextView) this.findViewById(R.id.consult_detail_title);
        tv_author = (TextView) this.findViewById(R.id.detail_author);
        tv_time = (TextView) this.findViewById(R.id.detail_time);
        detail_web = (WebView) this.findViewById(R.id.detail_web);
        tv_consult_title = (TextView) this.findViewById(R.id.tv_consult_title);
        tv_consult_title.setText("咨询详情");
        img_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initDialog() {
        diaLog = new MyDiaLog(ConsultDetailActivity.this);
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(true);
        diaLog.show();
    }

    public void getConsultDetailServerData(String url) {

        AjaxParams params = new AjaxParams();
        params.put("id", bundle.getString("id"));
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
                    ConsultDetailEntity consultDetailEntity = gson.fromJson(t.toString(), new TypeToken<ConsultDetailEntity>() {
                    }.getType());
                    tv_title.setText(consultDetailEntity.getTitle());
                    tv_author.setText(consultDetailEntity.getAuthor());
                    tv_time.setText(consultDetailEntity.getTime());
                    detail_web.loadDataWithBaseURL(null, consultDetailEntity.getContent(), "text/html", "utf-8", null);
                    if (diaLog.isShowing()) {
                        diaLog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
