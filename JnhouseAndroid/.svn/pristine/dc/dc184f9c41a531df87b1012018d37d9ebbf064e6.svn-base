package jnhouse.topwellsoft.com.jnhouse_android.ui.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TouchDelegate;
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

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.util.CustomProgressDialog;
import jnhouse.topwellsoft.com.jnhouse_android.util.EventUtil;
import jnhouse.topwellsoft.com.jnhouse_android.util.PersonInfo;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

public class Nickname extends AppCompatActivity {
    private EditText mEditText_nickname;
    private TextView mTextView;
    private ImageView mImageView;
    private Button mButton;
    private String code;
    private String AParams;
    private String content;
    private CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);
        mImageView = (ImageView) findViewById(R.id.nickname_iv_delete);
        mEditText_nickname = (EditText) findViewById(R.id.nickname_et);
        mTextView = (TextView) findViewById(R.id.nickname_save_tv);
        mButton = (Button) findViewById(R.id.back_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditText_nickname.setText("");
            }
        });
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(mEditText_nickname.getText().toString())) {
                    Toast.makeText(Nickname.this, "请输入昵称", Toast.LENGTH_LONG).show();
                } else {
//                    Toast.makeText(Nickname.this,"昵称修改成功", Toast.LENGTH_LONG).show();
                    try {
                        getPDAServerData();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    onBackPressed();
                }
            }
        });
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        code = intent.getStringExtra("REQUEST");
        AParams = intent.getStringExtra("AParams");
        content = intent.getStringExtra("CONTENT");
        mEditText_nickname.setText(content);
    }

    public void getPDAServerData() throws IOException {

        /*progressDialog = new CustomProgressDialog(this, "正在提交...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);*/

        final LoginEntity info = (LoginEntity) PreferencesUtils.getObject(this, "loginEntity");
        if (info == null || info.getUserUUID() == null) {
            ToastUtil.makeText(this, "请重新登录",
                    ToastUtil.LENGTH_SHORT)
                    .setAnimation(R.style.PopToast).show();
            return;
        }
//        final LoginEntity info = new LoginEntity();
        AjaxParams params = new AjaxParams();

        params.put("user_id", info.getUser_id());
        params.put("realname", mEditText_nickname.getText().toString());
        params.put("userUUID", info.getUserUUID());

        FinalHttp fh = new FinalHttp();
        fh.get(JnHouse_Record.Key_user_info, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {

                progressDialog.dismiss();
                Toast.makeText(Nickname.this, "信息提交失败",
                        Toast.LENGTH_SHORT).show();
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
                    Result result = gson.fromJson(t.toString(),
                            new TypeToken<Result>() {
                            }.getType());

                    if (result != null) {
                        switch (result.getCode()) {

                            case 0:
//                                progressDialog.dismiss();
                                EventBus.getDefault().post(new EventUtil(mEditText_nickname.getText().toString()));

                                Intent intent = new Intent();
                                // 把返回数据存入Intent
                                intent.putExtra("RESULT_REQUEST", mEditText_nickname.getText().toString());
                                // 设置返回数据
                                Nickname.this.setResult(Integer.parseInt(code), intent);
                                // 关闭Activity
                                Nickname.this.finish();
                                info.setRealname(mEditText_nickname.getText().toString());
                                PreferencesUtils.putObject(Nickname.this, "loginEntity", info);
                                Toast.makeText(Nickname.this,
                                        "修改成功", Toast.LENGTH_SHORT).show();
                                break;

                            case 1:
//                                progressDialog.dismiss();
                                Toast.makeText(Nickname.this,
                                        "未登录", Toast.LENGTH_SHORT).show();
                                break;

                            case -1:
                                Toast.makeText(Nickname.this,
                                        "异常", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
