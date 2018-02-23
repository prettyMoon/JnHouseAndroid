package jnhouse.topwellsoft.com.jnhouse_android.ui.calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.PreferencesUtils;
import com.topwellsoft.jnhouse_android.constant.GlobalParas;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

/**
 * Created by DELL on 2016/8/15.
 */

public class CancelCalendarActivity extends Activity {
    private EditText editText;
    private TextView tv_title;
    private TextView tv_right;
    private MyDiaLog diaLog;
    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_calendar);
        initView();
        initDialog();
    }

    private void initDialog() {
        diaLog = new MyDiaLog(this);
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(true);
        diaLog.show();
    }

    public void initView() {
        img_back = (ImageView) this.findViewById(R.id.question_img_back);
        tv_right = (TextView) this.findViewById(R.id.tv_right);
        tv_title = (TextView) this.findViewById(R.id.tv_middle);
        editText = (EditText) this.findViewById(R.id.edit_reason);
        tv_title.setText("取消日程");
        tv_right.setText("取消");
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().equals("")) {
                    showToast("请输入取消理由");
                } else {
                    CancelCalendar(JnHouse_Record.Order_cancel);
                }
            }
        });
    }

    public void CancelCalendar(String url) {
        LoginEntity loginEntity = (LoginEntity) PreferencesUtils.getObject(this, "loginEntity");
        AjaxParams params = new AjaxParams();
        params.put("userUUID", loginEntity.getUserUUID());
        params.put("user_id", loginEntity.getUser_id());
        params.put("or_id", getIntent().getStringExtra("id"));
        params.put("type", "0");
        params.put("cancel_reason", editText.getText().toString().trim());
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
                    CalendarDetailEntity calendarDetailEntity = gson.fromJson(t.toString(), new TypeToken<CalendarDetailEntity>() {
                    }.getType());
                    switch (calendarDetailEntity.getCode()) {
                        case 1:
                            showToast("您未登录");
                            break;
                        case -1:
                            showToast("异常");
                            break;
                        case 0:
                            showToast("取消成功");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent();
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }
                            }, 1500);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    public void showToast(String str) {
        ToastUtil.makeText(this, str, ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
    }
}
