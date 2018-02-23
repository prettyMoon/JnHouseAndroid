package jnhouse.topwellsoft.com.jnhouse_android.ui.agent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.StyleUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.manage.ImageManager;
import jnhouse.topwellsoft.com.jnhouse_android.model.AgentDetailEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.EmChatHelper;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

/**
 * Created by Administrator on 2016/7/14.
 */
public class AgentCardActivity extends Activity implements View.OnClickListener {

    private TextView tv_title, tv_right, tv_name, tv_store, tv_rate, tv_haoping, tv_zhongping,
            tv_chaping, tv_seehouse, tv_recent_sale, tv_recent_see, tv_deal, tv_weituo, tv_consult;
    private ImageView img_headicon, img_back;
    private LinearLayout layout_share;
    private MyDiaLog diaLog;
    private ImageManager manager;
   private  String fenxiangstr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StyleUtils.initStatusBar(getWindow());
        setContentView(R.layout.activity_agent_detail);
        manager = new ImageManager(AgentCardActivity.this);
        initView();
        initDiaLog();
        getAgentCardServerData(JnHouse_Record.Broker_Card);
    }

    public void initDiaLog() {
        diaLog = new MyDiaLog(AgentCardActivity.this);
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(true);
        diaLog.show();
    }
    public void initView() {
        tv_title = (TextView) this.findViewById(R.id.tv_middle);
        tv_right = (TextView) this.findViewById(R.id.tv_right);
        tv_name = (TextView) this.findViewById(R.id.agent_name_detail);
        tv_store = (TextView) this.findViewById(R.id.agent_store_detail);
        tv_rate = (TextView) this.findViewById(R.id.favourable_rate);
        tv_haoping = (TextView) this.findViewById(R.id.tv_haoping);
        tv_zhongping = (TextView) this.findViewById(R.id.tv_zhongping);
        tv_chaping = (TextView) this.findViewById(R.id.tv_chaping);
        tv_seehouse = (TextView) this.findViewById(R.id.see_house_num);
        tv_recent_sale = (TextView) this.findViewById(R.id.to_sale_house_num);
        tv_recent_see = (TextView) this.findViewById(R.id.recent_seen);
        tv_deal = (TextView) this.findViewById(R.id.recent_deal);
        tv_weituo = (TextView) this.findViewById(R.id.depute);
        layout_share = (LinearLayout) this.findViewById(R.id.layout_share);
        img_headicon = (ImageView) this.findViewById(R.id.agent_headicon);
        img_back = (ImageView) this.findViewById(R.id.question_img_back);
        tv_consult = (TextView) this.findViewById(R.id.tv_consult_agent);
        tv_title.setText("置业顾问详情");
        tv_right.setVisibility(View.INVISIBLE);
        img_back.setOnClickListener(this);
        tv_consult.setOnClickListener(this);
        layout_share.setOnClickListener(this);
    }

    public String getSomeString(int i) {
        if (i > 0) {
            return i + "";
        } else {
            return "暂无";
        }
    }

    public String getSomeString(String str) {
        if (str.equals("")) {
            return "未知";
        } else {
            return str;
        }
    }

    public void getAgentCardServerData(String url) {
        AjaxParams params = new AjaxParams();
        params.put("broker_id", getIntent().getExtras().getString("id"));
        FinalHttp fh = new FinalHttp();
        fh.get(url, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("####", "onFailure");
                if (diaLog.isShowing())
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
                    Log.i("####", t.toString());
                    Gson gson = new Gson();
                    AgentDetailEntity agentDetailEntity = gson.fromJson(t.toString(), new TypeToken<AgentDetailEntity>() {
                    }.getType());
                    if (t.toString().startsWith("{\"code\":208")) {
                        ToastUtil.makeText(AgentCardActivity.this, "", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                        if (diaLog.isShowing())
                            diaLog.dismiss();
                    } else {// if (t.toString().startsWith("{\"code\":0"))
                        tv_name.setText(getSomeString(agentDetailEntity.getRealname()));
                        tv_store.setText(getSomeString(agentDetailEntity.getCompany_name()));
                        tv_seehouse.setText(agentDetailEntity.getDksl());
                        tv_recent_sale.setText(agentDetailEntity.getCjsl());
                        tv_deal.setText(getSomeString(agentDetailEntity.getCjrq()));
                        tv_recent_see.setText(getSomeString(agentDetailEntity.getDkrq()));
                        tv_weituo.setText(getSomeString(agentDetailEntity.getWtrq()));
                        fenxiangstr="置业顾问："+agentDetailEntity.getRealname()+"\t"+"所属门店:"+agentDetailEntity.getCompany_name()+"(来自济房网APP)http://www.jnhouse.com/brokerEstate/esf_be.php?broker_id="+getIntent().getExtras().getString("id");
                        int h = Integer.parseInt(agentDetailEntity.getHp_s());
                        int z = Integer.parseInt(agentDetailEntity.getZp_s());
                        int c = Integer.parseInt(agentDetailEntity.getCp_s());
                        int rate;
                        if ((h + c + z) == 0) {
                            rate = 0;
                        } else {
                            rate = (h / (h + c + z)) * 100;
                        }
                        tv_rate.setText("(" + rate + "%)");
                        tv_haoping.setText("（" + getSomeString(h) + "）");
                        tv_zhongping.setText("（" + getSomeString(z) + "）");
                        tv_chaping.setText("（" + getSomeString(c) + "）");
                    }
                    manager.loadCircleImage(agentDetailEntity.getAvatar(), img_headicon);
                    if (diaLog.isShowing())
                        diaLog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (diaLog.isShowing())
                        diaLog.dismiss();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.question_img_back://返回
                finish();
                break;
            case R.id.tv_consult_agent://咨询
                EmChatHelper.startChatActivity(AgentCardActivity.this, "17865169986", "notlist");
                break;
            case R.id.layout_share://分享
                intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent.putExtra(Intent.EXTRA_TEXT, fenxiangstr);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, getTitle()));
                break;
            default:
                break;
        }
    }
}