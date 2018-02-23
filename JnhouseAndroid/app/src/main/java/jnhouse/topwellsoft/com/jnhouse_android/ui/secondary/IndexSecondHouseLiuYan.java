package jnhouse.topwellsoft.com.jnhouse_android.ui.secondary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.PreferencesUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.RegisterEntity;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

/**
 * Created by chenchen on 2016/7/12.
 */
public class IndexSecondHouseLiuYan extends Activity {
    EditText content ;
    TextView search;
    Button sub;
    ImageView back;
    private String houseid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_liu_yan);
        content = (EditText) findViewById(R.id.liuyan_content);
        sub = (Button) findViewById(R.id.liuyan_sub);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getPDAServerData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        search=(TextView)findViewById(R.id.search_maintab_right);
        search.setVisibility(View.GONE);
        back=(ImageView)findViewById(R.id.index_second_house_title_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Intent intent = getIntent();
        houseid = intent.getStringExtra("houseId");
    }

    private void getPDAServerData() throws IOException {
        AjaxParams params = new AjaxParams();
        LoginEntity info = (LoginEntity) PreferencesUtils.getObject(this, "loginEntity");

        if (info == null || info.getUserUUID() == null) {
            ToastUtil.makeText(this, "请重新登录",
                    ToastUtil.LENGTH_SHORT)
                    .setAnimation(R.style.PopToast).show();
            return;
        }
        params.put("user_id", info.getUser_id());
        params.put("house_id", houseid);
        params.put("content", content.getText().toString());
        params.put("username", info.getUsername());
        params.put("userUUID", info.getUserUUID());
        FinalHttp fp = new FinalHttp();
        fp.get(JnHouse_Record.message, params, new AjaxCallBack<Object>() {
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                ToastUtil.makeText(IndexSecondHouseLiuYan.this, "留言失败", ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
            }

            @Override
            public void onSuccess(Object o) {
                try {
                    JSONObject json = new JSONObject(o.toString());
                    Gson gson = new Gson();
                    Toast.makeText(IndexSecondHouseLiuYan.this, json.toString(), Toast.LENGTH_LONG).show();
                    RegisterEntity register = gson.fromJson(o.toString(), new TypeToken<RegisterEntity>() {
                    }.getType());
                    if (register != null) {
                        switch (register.getCode()) {
                            case 1:
                                ToastUtil.makeText(IndexSecondHouseLiuYan.this, "未登录", ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
                                break;

                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
        });

    }

}
