package jnhouse.topwellsoft.com.jnhouse_android.ui.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
import jnhouse.topwellsoft.com.jnhouse_android.model.GetPasswordEntity;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

public class TpGetPassword extends AppCompatActivity implements View.OnClickListener {
    private EditText phone;
    private EditText code;
    private EditText password;
    private Button getCode;
    private Button getpassword;
    private ImageView comeBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_password);
          phone=(EditText) findViewById(R.id.password_phone);
          code=(EditText) findViewById(R.id.get_code);
          password=(EditText) findViewById(R.id.get_password);
          getCode=(Button) findViewById(R.id.password_getCode);
          getpassword =(Button) findViewById(R.id.getpassword);
        comeBack= (ImageView) findViewById(R.id.new_house_title_back);
        comeBack.setOnClickListener(this);
        getCode.setOnClickListener(this);
        getpassword.setOnClickListener(this);
    }


    public void getPDAServerData(String url, AjaxParams params, final String flag){
        FinalHttp finalHttp=new FinalHttp();
        finalHttp.get(url, params, new AjaxCallBack<Object>() {
            @Override
            public void onFailure(Throwable t,int errorNo,String strMsg){
                ToastUtil.makeText(TpGetPassword.this, "发送失败",
                        ToastUtil.LENGTH_SHORT)
                        .setAnimation(R.style.PopToast).show();
            }
            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
            }

            @Override
            public void onStart() {
                super.onStart();
            }
            public  void onSuccess(Object t){
                try {
                    JSONObject json= new JSONObject(t.toString());
                    Gson gson=new Gson();
                    GetPasswordEntity getPasswordEntit=gson.fromJson(t.toString(), new TypeToken<GetPasswordEntity>() {
                    }.getType());
                    Log.d("","onSuccess"+getPasswordEntit.getCode());
                    if (getPasswordEntit!=null){
                        switch (getPasswordEntit.getCode()){
                            case -1:
                                ToastUtil.makeText(TpGetPassword.this,"重置异常，请重试",
                                        ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
                                break;
                            case 0:
                                if (flag.equals("0")){
                                    ToastUtil.makeText(TpGetPassword.this,"发送成功",
                                            ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
                                }else if (flag.equals("1")){
                                    ToastUtil.makeText(TpGetPassword.this, "重置成功",
                                            ToastUtil.LENGTH_SHORT)
                                            .setAnimation(R.style.PopToast).show();
                                      onBackPressed();

                                }

                                break;
                            case 103:
                                ToastUtil.makeText(TpGetPassword.this,"手机号不存在",
                                        ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                break;
                            case 105:
                                ToastUtil.makeText(TpGetPassword.this,"验证码错误或超时",
                                        ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                break;
                        }
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

                }

        );
    }
    public boolean isMobileNO(String mobiles) {

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();

    }

    @Override
    public void onClick(View view) {
        AjaxParams params=new AjaxParams();
            switch (view.getId()){
                case R.id.new_house_title_back:
                    finish();
                    break;
                case R.id.password_getCode:
                    if(phone.getText().toString().trim().equals("")){
                        ToastUtil.makeText(TpGetPassword.this, "请输入正确的手机号",
                                ToastUtil.LENGTH_SHORT)
                                .setAnimation(R.style.PopToast).show();
                    }else{
                        params.put("phone", phone.getText().toString().trim());
                        params.put("fs","1");
                        params.put("logo", "0");
                        //验证密码倒计时
                      //  countDown();
                        getPDAServerData(JnHouse_Record.Key_send_code,params,"0");

                    }
                    break;
                case R.id.getpassword:
                    if(phone.getText().toString().trim().equals("")){
                        ToastUtil.makeText(TpGetPassword.this, "请输入正确的手机号",
                                ToastUtil.LENGTH_SHORT)
                                .setAnimation(R.style.PopToast).show();
                    }else if(code.getText().toString().trim().equals("")){
                        ToastUtil.makeText(TpGetPassword.this, "请输入验证码",
                                ToastUtil.LENGTH_SHORT)
                                .setAnimation(R.style.PopToast).show();
                    }else if(password.getText().toString().trim().equals("")){
                        ToastUtil.makeText(TpGetPassword.this, "请输入密码",
                                ToastUtil.LENGTH_SHORT)
                                .setAnimation(R.style.PopToast).show();
                    }else{
                        params.put("phone", phone.getText().toString().trim());
                        params.put("code", code.getText().toString().trim());
                        params.put("password", password.getText().toString().trim());
                        params.put("logo", "0");
                        getPDAServerData(JnHouse_Record.Key_pwd_reset,params,"1");
                    }


            }
    }


}
