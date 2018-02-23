package jnhouse.topwellsoft.com.jnhouse_android.ui.baike;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
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

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.BaikeListAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.BaikeSecondEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.BaikeinitEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;

/**
 * Created by Administrator on 2016/7/11.
 */
public class BaikeSecondActivity extends Activity {
    private ImageView img_back;
    private TextView tv_title;
    private ListView lv;
    private Bundle bundle;
    private BaikeListAdapter adapter;
    private BaikeSecondEntity baikeSecondEntity;
    private MyDiaLog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StyleUtils.initStatusBar(getWindow());
        setContentView(R.layout.baike_list_two);
        initView();
        initDiaLog();
        getBaikeServerData(JnHouse_Record.Baike_List);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BaikeSecondActivity.this, BaikeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", baikeSecondEntity.getaList().get(position).getNewsId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }



    public void initDiaLog() {
        dialog = new MyDiaLog(BaikeSecondActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.show();
    }

    public void initView() {
        bundle = getIntent().getExtras();
        img_back = (ImageView) this.findViewById(R.id.consult_img_back);
        tv_title = (TextView) this.findViewById(R.id.tv_consult_title);
        tv_title.setText(bundle.getString("name"));
        lv = (ListView) this.findViewById(R.id.baike_list);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getBaikeServerData(String url) {

        AjaxParams params = new AjaxParams();
        params.put("code", bundle.getString("code"));
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
                    baikeSecondEntity = gson.fromJson(t.toString(), new TypeToken<BaikeSecondEntity>() {
                    }.getType());
                    adapter = new BaikeListAdapter(BaikeSecondActivity.this, baikeSecondEntity.getaList());
                    lv.setAdapter(adapter);
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
