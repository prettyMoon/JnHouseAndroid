package jnhouse.topwellsoft.com.jnhouse_android.ui.secondary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.PreferencesUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.RegisterEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.login.TpLoginFragmentActivity;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

/**
 * Created by chenchen on 2016/7/13.
 */
public class report extends Activity implements View.OnClickListener {
    private String houseId;
    private String house_type;
    private RadioGroup radioGroup;
    private RadioButton radioButton0, radioButton1, radioButton2, radioButton3;
    private TextView title,search;
    private String type = "0";
    private EditText content;
    private Button btn;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioButton0 = (RadioButton) findViewById(R.id.radiobtn0);
        radioButton1 = (RadioButton) findViewById(R.id.radiobtn1);
        radioButton2 = (RadioButton) findViewById(R.id.radiobtn2);
        radioButton3 = (RadioButton) findViewById(R.id.radiobtn3);
        content = (EditText) findViewById(R.id.report_conent);
        Intent intent1 = getIntent();
        houseId = intent1.getStringExtra("houseId");
        house_type = intent1.getStringExtra("house_type");
        search=(TextView)findViewById(R.id.search_maintab_right);
        search.setVisibility(View.GONE);
        title = (TextView) findViewById(R.id.index_second_house_title);
        title.setText("虚假房源举报");
        title.setVisibility(View.VISIBLE);
        back = (ImageView) findViewById(R.id.index_second_house_title_back);
        back.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radiobtn0:
                        type = "0";
                        break;
                    case R.id.radiobtn1:
                        type = "1";
                        break;
                    case R.id.radiobtn2:
                        type = "2";
                        break;
                    case R.id.radiobtn3:
                        type = "3";
                }
            }
        });
        btn = (Button) findViewById(R.id.report_btn);
        btn.setOnClickListener(this);
    }

    private void getReportServerData() throws IOException {
        AjaxParams params = new AjaxParams();
        LoginEntity info = (LoginEntity) PreferencesUtils.getObject(this, "loginEntity");
        if (info == null || info.getUserUUID() == null) {
            ToastUtil.makeText(  this, "请重新登录",
                    ToastUtil.LENGTH_SHORT)
                    .setAnimation(R.style.PopToast).show();
            return;
        }
        params.put("house_id", houseId);
        params.put("user_id", info == null ? "" : info.getUser_id());
        params.put("house_type", house_type);
        params.put("type", type);
        params.put("userUUID", info == null ? "" : info.getUserUUID());
        content = (EditText) findViewById(R.id.report_conent);
        params.put("remarks", content.getText().toString());
        FinalHttp fp = new FinalHttp();
        fp.post(JnHouse_Record.report, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                ToastUtil.makeText(report.this, "举报失败",
                        ToastUtil.LENGTH_SHORT)
                        .setAnimation(R.style.PopToast).show();
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
            }

            @Override
            public void onSuccess(Object o) {
                try {
                    JSONObject json = new JSONObject(o.toString());
                    Gson gson = new Gson();
                    RegisterEntity registerEntity = gson.fromJson(o.toString(), new TypeToken<RegisterEntity>() {
                    }.getType());

                    if (registerEntity != null) {
                        switch (registerEntity.getCode()) {
                            case 1:
                                ToastUtil.makeText(report.this, "未登录",
                                        ToastUtil.LENGTH_SHORT)
                                        .setAnimation(R.style.PopToast).show();
                                Intent intent = new Intent();
                                intent.setClass(report.this, TpLoginFragmentActivity.class);
                                startActivity(intent);
                                break;
                            case -1:
                                ToastUtil.makeText(report.this, "网络异常",
                                        ToastUtil.LENGTH_SHORT)
                                        .setAnimation(R.style.PopToast).show();
                                break;
                            case 0:
                                ToastUtil.makeText(report.this, "举报成功",
                                        ToastUtil.LENGTH_SHORT)
                                        .setAnimation(R.style.PopToast).show();
                                onBackPressed();
                                break;
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
            case R.id.report_btn:
                try {
                    getReportServerData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case R.id.index_second_house_title_back:
                onBackPressed();
        }
    }
}
