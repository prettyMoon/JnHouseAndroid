package jnhouse.topwellsoft.com.jnhouse_android.ui.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.RegisterEntity;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener{
    /*private Button mButton_back,mButton_commit;
    private EditText mEditText_present,mEditText_new,mEditText_confirm;*/
    private EditText phone;
    private EditText code;
    private EditText password;
    private Button getCode;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        /*mButton_back = (Button) findViewById(R.id.change_password_back_button);
        mButton_commit = (Button) findViewById(R.id.change_password_commit_bt);
        mEditText_present = (EditText) findViewById(R.id.change_password_present_et);
        mEditText_new = (EditText) findViewById(R.id.change_password_new_et);
        mEditText_confirm = (EditText) findViewById(R.id.change_password_confirm_et);
        mButton_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mButton_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        phone = (EditText) findViewById(R.id.register_phone);
        code = (EditText) findViewById(R.id.register_code);
        password = (EditText) findViewById(R.id.register_password);
        getCode = (Button) findViewById(R.id.register_getCode);
        register = (Button) findViewById(R.id.register);
        getCode.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        AjaxParams params = new AjaxParams();
        switch (view.getId()) {
            case R.id.register_getCode:
                if (phone.getText().toString().trim().equals("")) {
                    ToastUtil.makeText(this, "请输入正确的手机号", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                } else {
                    params.put("phone", phone.getText().toString().trim());
                    params.put("fs", "0");
                    params.put("logo", "0");
                    getPDAServerData(JnHouse_Record.Key_send_code, params, "0");
                }
                break;
            case R.id.register:
                if (phone.getText().toString().trim().equals("")) {
                    ToastUtil.makeText(this, "请输入正确的手机号", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                } else if (code.getText().toString().trim().equals("")) {
                    ToastUtil.makeText(this, "请输入验证码", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                } else if (password.getText().toString().trim().equals("")) {
                    ToastUtil.makeText(this, "请输入密码", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                } else {
                    params.put("phone", phone.getText().toString().trim());
                    params.put("code", code.getText().toString().trim());
                    params.put("password", password.getText().toString().trim());
                    params.put("logo", "0");
                    getPDAServerData(JnHouse_Record.Key_register, params, "1");
                }
                break;
        }
    }

    public void getPDAServerData(String url, AjaxParams params, final String flag) {

        FinalHttp fh = new FinalHttp();
        fh.get(url, params, new AjaxCallBack<Object>() {
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                ToastUtil.makeText(ChangePassword.this, "发送失败", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
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
                    Log.d("", "onSuccess: " + registerEntity.getCode());
                    if (registerEntity != null) {
                        switch (registerEntity.getCode()) {
                            case -1: //注册出现异常
                                ToastUtil.makeText(ChangePassword.this, "重置出现异常！", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                break;
                            case 0:
                                if (flag.equals("0")) { //发送成功
                                    ToastUtil.makeText(ChangePassword.this, "发送成功", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                } else if (flag.equals("1")) { //注册成功
                                    ToastUtil.makeText(ChangePassword.this, "重置成功", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                }
                                break;
                            case 102:  //手机号已存在
                                ToastUtil.makeText(ChangePassword.this, "该手机号已存在", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                break;
                            case 103: //手机号不存在
                                ToastUtil.makeText(ChangePassword.this, "该手机号不存在", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                            case 104://发送失败
                                ToastUtil.makeText(ChangePassword.this, "发送失败", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                break;
                            case 105: //验证码错误或超时
                                ToastUtil.makeText(ChangePassword.this, "验证码错误或超时", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                break;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();

    }

}
