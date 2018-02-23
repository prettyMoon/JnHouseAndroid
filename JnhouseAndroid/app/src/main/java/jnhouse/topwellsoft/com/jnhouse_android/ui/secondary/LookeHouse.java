package jnhouse.topwellsoft.com.jnhouse_android.ui.secondary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.Bind;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.LiuLanAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.LiuLanEntity;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

/**
 * Created by chenchen on 2016/7/14.
 */
public class LookeHouse  extends Activity{

    private  String houseId,houseType;
     private ListView liulan_list;
   private LiuLanAdapter mAdaper;
    private List<LiuLanEntity>mList;
    @Bind(R.id.look_number)
    TextView look_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tp_liulan);
        liulan_list=(ListView)findViewById(R.id.liulan_list);
        Intent intent=getIntent();
       houseId= intent.getStringExtra("houseId");
        houseType=intent.getStringExtra("house_type");
        getLookHouse();
    }

    private  void getLookHouse(){
        AjaxParams params =new AjaxParams();
        params.put("house_id",houseId);
        params.put("house_type",houseType);
        FinalHttp fp =new FinalHttp();
        fp.get(JnHouse_Record.Look_Record, params, new AjaxCallBack<Object>() {
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
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
                   JSONObject json=new JSONObject(o.toString());
                   Gson gson=new Gson();
                   LiuLanEntity liulan=gson.fromJson(o.toString(),new TypeToken<LiuLanEntity>(){}.getType());
                   if (liulan!=null){
                        mAdaper=new LiuLanAdapter(LookeHouse.this,mList);
                       liulan_list.setAdapter(mAdaper);
                   }

               }catch (JSONException e){
                   e.printStackTrace();

               }
            }
        });
    }
}
