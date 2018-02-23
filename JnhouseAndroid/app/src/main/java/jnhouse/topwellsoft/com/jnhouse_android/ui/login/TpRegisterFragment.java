package jnhouse.topwellsoft.com.jnhouse_android.ui.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.RegisterEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

public class TpRegisterFragment extends Fragment implements View.OnClickListener {
    private EditText phone;
    private EditText code;
    private EditText password;
    private EditText recommend;
    private Button getCode;
    private Button register;
    private int  count;
    private Timer timer;
    private final int MSG_WHAT=1;
    private MyDiaLog diaLog;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_WHAT:
                    getCode.setText("("+count+")"+"重发");
                    if(count>0){
                        count--;
                    }else{
                        getCode.setEnabled(true);
                        getCode.setText("获取验证码");
                        //关闭定时器
                        timer.cancel();
                        timer=null;

                    }
                    break;
            }
        }
    };






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_register, container, false);
        phone = (EditText) view.findViewById(R.id.register_phone);
        code = (EditText) view.findViewById(R.id.register_code);
        recommend = (EditText) view.findViewById(R.id.register_recommend);
        password = (EditText) view.findViewById(R.id.register_password);
        getCode = (Button) view.findViewById(R.id.register_getCode);
        register = (Button) view.findViewById(R.id.register);
        getCode.setOnClickListener(this);
        register.setOnClickListener(this);
        initDiaLog();
        return view;
    }
    public void initDiaLog() {
        diaLog = new MyDiaLog(getActivity());
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(true);
    }

    public void countDown(){
        getCode.setEnabled(false);
        if(count<1){
            count=60;

                timer=new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Message message=Message.obtain();
                        message.what=MSG_WHAT;
                        handler.sendEmptyMessage(message.what);
                    }
                },0,1*1000);
            }

        }




    @Override
    public void onClick(View v) {

        AjaxParams params = new AjaxParams();
        switch (v.getId()) {
            case R.id.register_getCode:
                if (phone.getText().toString().trim().equals("")) {
                    ToastUtil.makeText(getActivity(), "请输入正确的手机号", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                } else {
                    params.put("phone", phone.getText().toString().trim());
                    params.put("fs", "0");
                    params.put("logo", "0");
                    params.put("tjr_phone", recommend.getText().toString().trim());
                    //验证码的倒计时
                   countDown();
                    getPDAServerData(JnHouse_Record.Key_send_code, params, "0");

                    diaLog.show();
                }
                break;
            case R.id.register:
                if (phone.getText().toString().trim().equals("")) {
                    ToastUtil.makeText(getActivity(), "请输入正确的手机号", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                } else if (code.getText().toString().trim().equals("")) {
                    ToastUtil.makeText(getActivity(), "请输入验证码", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                } else if (password.getText().toString().trim().equals("")) {
                    ToastUtil.makeText(getActivity(), "请输入密码", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
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
                ToastUtil.makeText(getActivity(), "发送失败", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
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
                                ToastUtil.makeText(getActivity(), "注册异常,请重试！", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                break;
                            case 0:
                                if (flag.equals("0")) { //发送成功
                                    ToastUtil.makeText(getActivity(), "发送成功", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                } else if (flag.equals("1")) { //注册成功
                                    ToastUtil.makeText(getActivity(), "注册成功", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                }
                                break;
                            case 102:  //手机号已存在
                                ToastUtil.makeText(getActivity(), "该手机号已存在", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();


                                /*    getCode.setEnabled(true);
                                    getCode.setText("获取验证码");
                                    if (timer!=null){
                                        timer.cancel();
                                        timer=null;
                                    }*/


                                break;
                            case 103: //手机号不存在
                                ToastUtil.makeText(getActivity(), "该手机号不存在", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                            case 104://发送失败
                                ToastUtil.makeText(getActivity(), "发送失败", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                break;
                            case 105: //验证码错误或超时
                                ToastUtil.makeText(getActivity(), "验证码错误或超时", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                break;
                        }
                        diaLog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,0-9]))\\d{8}$");
//                                     ^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$
        Matcher m = p.matcher(mobiles);
        return m.matches();

    }
}
