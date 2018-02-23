package jnhouse.topwellsoft.com.jnhouse_android.ui.new_house;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.PreferencesUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.RegisterEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.login.TpLoginFragmentActivity;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

/**
 * Created by chenchen on 2016/7/12.
 */
public class evaluate extends Activity implements RatingBar.OnRatingBarChangeListener, View.OnClickListener {
    private RatingBar ratingBar1 ,ratingBar2, ratingBar3, ratingBar4, ratingBar5;
    private TextView search;
    private String is_anony,jg_x="1",dd_x="1",pt_x="1", jt_x="1",hj_x="1",houseId;
     private EditText remarks;
  private ImageView back;
       private Button sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_housecomment_layout);
        ratingBar1=(RatingBar)findViewById(R.id.rating1);
        ratingBar2=(RatingBar)findViewById(R.id.rating2);
        ratingBar3=(RatingBar)findViewById(R.id.rating3);
        ratingBar4=(RatingBar)findViewById(R.id.rating4);
        ratingBar5=(RatingBar)findViewById(R.id.rating5);
        ratingBar2.setOnRatingBarChangeListener(this);
        ratingBar1.setOnRatingBarChangeListener(this);
        ratingBar3.setOnRatingBarChangeListener(this);
        ratingBar4.setOnRatingBarChangeListener(this);
        ratingBar5.setOnRatingBarChangeListener(this);
        sub=(Button)findViewById(R.id.eveluatebtn);
        remarks=(EditText)findViewById(R.id.remarks);
         sub.setOnClickListener(this);
        search=(TextView)findViewById(R.id.search_maintab_right);
        search.setVisibility(View.GONE);
        back=(ImageView)findViewById(R.id.index_second_house_title_back);
        back.setOnClickListener(this);
        Intent intent=getIntent();
        houseId=intent.getStringExtra("houseId");


    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        switch (ratingBar.getId()){
            case R.id.rating1:
              jg_x= String.valueOf(Math.round(v));
            break;
            case R.id.rating2:
                dd_x= String.valueOf(Math.round(v));
                break;
            case R.id.rating3:
               pt_x= String.valueOf(Math.round(v));
                break;
            case R.id.rating4:
              jt_x= String.valueOf(Math.round(v));
                break;
            case R.id.rating5:
               hj_x= String.valueOf(Math.round(v));
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.eveluatebtn:
                getEveluateServer();
                break;
            case R.id.index_second_house_title_back:
                onBackPressed();
                break;

        }
    }

    private  void getEveluateServer(){
        AjaxParams params=new AjaxParams();
        LoginEntity info=(LoginEntity) PreferencesUtils.getObject(this, "loginEntity");
        params.put("user_id",info==null?"":info.getUser_id());
        params.put("username",info==null?"":info.getUsername());
        params.put("userUUID",info==null?"":info.getUserUUID());
        params.put("jg_x",jg_x);
        params.put("dd_x",dd_x);
        params.put("pt_x",pt_x);
        params.put("jt_x",jt_x);
        params.put("hj_x",hj_x);
        params.put("house_id",houseId);
        params.put("is_anony","0");
        params.put("remarks",remarks.getText().toString());
        FinalHttp fp=new FinalHttp();
        fp.get(JnHouse_Record.Eveluate, params, new AjaxCallBack<Object>() {
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
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
            public void onSuccess(Object o) {
                try {
                    JSONObject json=new JSONObject(o.toString());
                    Gson gson=new Gson();
                    RegisterEntity reg=gson.fromJson(o.toString(),new TypeToken<RegisterEntity>(){}.getType());
                       if(reg!=null){
                         switch (reg.getCode()){
                             case 1:
                                 ToastUtil.makeText(evaluate.this,"未登录",ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
                                 Intent intent =new Intent();
                                 intent.setClass(evaluate.this, TpLoginFragmentActivity.class);
                                 startActivity(intent);
                                 break;
                             case 705:
                                 ToastUtil.makeText(evaluate.this,"评价成功",ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
                                     break;
                             case 706:
                                 ToastUtil.makeText(evaluate.this,"已经评价过了",ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
                                 break;
                             case -1:
                                 ToastUtil.makeText(evaluate.this,"操作异常",ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
                                 break;

                         }
                       }
                }catch (JSONException e){
                    e.printStackTrace();

                }
            }
        });
    }
}
