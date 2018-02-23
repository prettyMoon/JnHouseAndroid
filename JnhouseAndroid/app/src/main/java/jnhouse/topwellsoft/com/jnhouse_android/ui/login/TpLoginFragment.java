package jnhouse.topwellsoft.com.jnhouse_android.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.topwellsoft.androidutils.PreferencesUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.TravelingAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.HomeIndexEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.MainTabsActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

public class TpLoginFragment extends Fragment implements OnClickListener {

    private Button btn_login;
    private EditText username, password;
    private LinearLayout toast_layout_root;
    private MyDiaLog diaLog;
    private TextView getpassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);
        username = (EditText) view.findViewById(R.id.username);
        password = (EditText) view.findViewById(R.id.password);
        btn_login = (Button) view.findViewById(R.id.login);
        btn_login.setOnClickListener(this);
        getpassword=(TextView)view.findViewById(R.id.getspassword);
        getpassword.setOnClickListener(this);
        initDiaLog();
        return view;
    }

    public void initDiaLog() {
        diaLog = new MyDiaLog(getActivity());
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(true);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login:
                if (username.getText().toString().trim().equals("")) {
                    ToastUtil.makeText(getActivity(), "请输入用户名", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                } else if (isMobileNO(password.getText().toString().trim())) {
                    ToastUtil.makeText(getActivity(), "请输入正确的手机号", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                } else if (password.getText().toString().trim().equals("")) {
                    ToastUtil.makeText(getActivity(), "请输入密码", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                } else {
                    diaLog.show();
                    getPDAServerData();
                }
                break;
            case R.id.getspassword:
                Intent intent=new Intent();
                intent.setClass(getActivity(),TpGetPassword.class);
                startActivity(intent);
                break;

        }
    }

    public void getPDAServerData() {
        AjaxParams params = new AjaxParams();
        params.put("username", username.getText().toString().trim());
        params.put("password", password.getText().toString().trim());
        params.put("logo", "0");
        FinalHttp fh = new FinalHttp();
        fh.get(JnHouse_Record.Key_login, params, new AjaxCallBack<Object>() {
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                if (diaLog.isShowing()) diaLog.dismiss();
                ToastUtil.makeText(getActivity(), "登录失败", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
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
                    final LoginEntity loginEntity = gson.fromJson(t.toString(), new TypeToken<LoginEntity>() {
                    }.getType());

                    if (loginEntity != null) {
                        switch (loginEntity.getCode()) {
                            case 0:
                                Log.i("####3", username.getText().toString().trim());
                                Log.i("####3", loginEntity.getHx_pwd());
                                EMClient.getInstance().login(username.getText().toString().trim(), loginEntity.getHx_pwd(), new EMCallBack() {//回调
                                    @Override
                                    public void onSuccess() {
                                        EMClient.getInstance().groupManager().loadAllGroups();
                                        EMClient.getInstance().chatManager().loadAllConversations();
                                        Log.i("###", "登录聊天服务器成功！");
                                        //保存app登录状态信息到本地
                                        if (diaLog.isShowing()) diaLog.dismiss();
                                        updateState(loginEntity);
                                    }

                                    @Override
                                    public void onProgress(int progress, String status) {
                                    }

                                    @Override
                                    public void onError(int code, String message) {
                                        Log.i("###", "登录聊天服务器失败！" + code);
                                        if (code == 204 || code == 101) { //如果未注册环信且原用户名是手机号则直接注册
                                            if (isMobileNO(username.getText().toString().trim())) {
                                                new Thread(new Runnable() {//
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            EMClient.getInstance().createAccount(username.getText().toString().trim(), loginEntity.getHx_pwd());
                                                            //注册成功后直接登录
                                                            EMClient.getInstance().login(username.getText().toString().trim(), loginEntity.getHx_pwd(), new EMCallBack() {
                                                                @Override
                                                                public void onSuccess() {
                                                                    EMClient.getInstance().groupManager().loadAllGroups();
                                                                    EMClient.getInstance().chatManager().loadAllConversations();
                                                                    Log.i("###22222", "登录聊天服务器成功！");
                                                                    //保存app登录状态信息到本地
                                                                    if (diaLog.isShowing())
                                                                        diaLog.dismiss();
                                                                    updateState(loginEntity);
                                                                }

                                                                @Override
                                                                public void onError(int i, String s) {
                                                                    ToastUtil.makeText(getActivity(), "登 录 失 败", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                                                }

                                                                @Override
                                                                public void onProgress(int i, String s) {

                                                                }
                                                            });
                                                        } catch (HyphenateException e) {
                                                            Log.i("###", "环信账号注册失败！");
                                                        }
                                                    }
                                                }).start();
                                            } else { //如果不是未注册环信且原用户名不是手机号，需要验证
                                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("hx_state", Activity.MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.putBoolean("state", false);
                                                editor.commit();
                                                if (diaLog.isShowing()) diaLog.dismiss();
                                                updateState(loginEntity);
                                                Log.i("######", "state   false");
                                            }

                                        } else {//未知原因导致登录环信失败
//                                            ToastUtil.makeText(getActivity(), "登录失败", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                        }
                                        if (diaLog.isShowing()) diaLog.dismiss();
                                    }
                                });
                                break;
                            case -1://登录异常
                                if (diaLog.isShowing()) diaLog.dismiss();
                                ToastUtil.makeText(getActivity(), "登录异常", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                break;
                            case 100: //用户名密码错误
                                if (diaLog.isShowing()) diaLog.dismiss();
                                ToastUtil.makeText(getActivity(), "用户名密码错误", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                                break;
                            default:
                                break;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void updateState(LoginEntity loginEntity) {
        PreferencesUtils.putObject(getActivity(), "loginEntity", loginEntity);
//        ToastUtil.makeText(getActivity(), loginEntity.getUser_id() + "  登录成功", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
        Intent intent = new Intent();
        intent.setClass(getActivity(), MainTabsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public boolean isMobileNO(String mobiles) {

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();

    }
}
