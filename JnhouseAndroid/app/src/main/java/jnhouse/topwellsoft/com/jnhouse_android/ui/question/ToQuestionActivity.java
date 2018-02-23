package jnhouse.topwellsoft.com.jnhouse_android.ui.question;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.PreferencesUtils;
import com.topwellsoft.androidutils.StyleUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.MyQuestionListAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.MyQuestionListEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.QuestionDataEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.QuestionSortEntity;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

/**
 * Created by Administrator on 2016/7/11.
 */
public class ToQuestionActivity extends Activity implements View.OnClickListener {
    private int tv_id[] = {R.id.sort0, R.id.sort1, R.id.sort2, R.id.sort3, R.id.sort4, R.id.sort5};
    private TextView[] tv = new TextView[6];
    private ArrayList<QuestionSortEntity> list;
    private ImageView img_back;
    private TextView tv_title, tv_right;
    private int sortFlag = -1;
    private Button btn_publish;
    private EditText edit_title, edit_addition;
    private MyDiaLog diaLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StyleUtils.initStatusBar(getWindow());
        setContentView(R.layout.activity_to_question);
        initView();
        initDialog();
    }

    public void initDialog() {
        diaLog = new MyDiaLog(ToQuestionActivity.this);
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(true);
    }

    public void initView() {
        img_back = (ImageView) this.findViewById(R.id.question_img_back);
        tv_title = (TextView) this.findViewById(R.id.tv_middle);
        btn_publish = (Button) this.findViewById(R.id.btn_publish);
        edit_addition = (EditText) this.findViewById(R.id.edit_addition);
        edit_title = (EditText) this.findViewById(R.id.edit_title);
        tv_right = (TextView) this.findViewById(R.id.tv_right);
        tv_title.setText("我要提问");
        tv_right.setText("我的提问");
        tv_right.setOnClickListener(this);
        img_back.setOnClickListener(this);
        btn_publish.setOnClickListener(this);
        list = (ArrayList<QuestionSortEntity>) getIntent().getExtras().getSerializable("category");
        for (int i = 0; i < 6; i++) {
            tv[i] = (TextView) this.findViewById(tv_id[i]);
            tv[i].setText(list.get(i).getCatalogName());
            tv[i].setOnClickListener(this);
        }
    }

    public boolean CheckDataCompleteness() {
        if (edit_title.getText().toString().equals("")) {
            ToastUtil.makeText(ToQuestionActivity.this, "请输入您的问题", ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
            return false;
        } else if (edit_addition.getText().toString().equals("")) {
            ToastUtil.makeText(ToQuestionActivity.this, "请输入您的问题描述", ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
            return false;
        } else if (sortFlag == -1) {
            ToastUtil.makeText(ToQuestionActivity.this, "请选择问题类别", ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.question_img_back://返回
                finish();
                break;
            case R.id.btn_publish:
                if (CheckDataCompleteness()) {
                    diaLog.show();
                    try {
                        getMyQuestionServerData(JnHouse_Record.Do_Ask);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.tv_right://我的提问
                Intent intent = new Intent(ToQuestionActivity.this, QuestionListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("flag", "mine");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.sort0:
                sortFlag = 0;
                setFade(0);
                break;
            case R.id.sort1:
                sortFlag = 1;
                setFade(1);
                break;
            case R.id.sort2:
                sortFlag = 2;
                setFade(2);
                break;
            case R.id.sort3:
                sortFlag = 3;
                setFade(3);
                break;
            case R.id.sort4:
                sortFlag = 4;
                setFade(4);
                break;
            case R.id.sort5:
                sortFlag = 5;
                setFade(5);
                break;
        }
    }

    public void setFade(int position) {
        for (int i = 0; i < 6; i++) {
            tv[i].setBackgroundResource(R.drawable.question_tab_bottom);
            tv[i].setTextColor(Color.parseColor("#f75555"));
        }
        tv[position].setBackgroundResource(R.drawable.question_tab_bottom_pressed);
        tv[position].setTextColor(Color.parseColor("#ffffff"));
    }

    /**
     * 我的提问——请求方法
     *
     * @param url
     */

    public void getMyQuestionServerData(String url) throws IOException {
        LoginEntity info = (LoginEntity) PreferencesUtils.getObject(this, "loginEntity");
        if (info == null || info.getUserUUID() == null) {
            ToastUtil.makeText(  this, "请重新登录",
                    ToastUtil.LENGTH_SHORT)
                    .setAnimation(R.style.PopToast).show();
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put("user_id", info.getUser_id());
        params.put("user_name", info.getUsername());
        params.put("userUUID", info.getUserUUID());
        params.put("title", edit_title.getText().toString());
        params.put("content", edit_addition.getText().toString());
        params.put("catalog", list.get(sortFlag).getCatalogId());

        FinalHttp fh = new FinalHttp();
        fh.get(url, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("####", "onFailure");
                diaLog.dismiss();
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
                    MyQuestionListEntity myQuestionListEntity = gson.fromJson(t.toString(), new TypeToken<MyQuestionListEntity>() {
                    }.getType());
                    String jsonString = t.toString();
                    if (jsonString.startsWith("{\"code\":1")) {
                        ToastUtil.makeText(ToQuestionActivity.this, "您未登录", ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
                    } else if (jsonString.startsWith("{\"code\":0")) {
                        ToastUtil.makeText(ToQuestionActivity.this, "提问成功", ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
                        finish();
                    }
                    diaLog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
