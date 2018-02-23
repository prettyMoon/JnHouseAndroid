package jnhouse.topwellsoft.com.jnhouse_android.ui.question;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.PreferencesUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.QuestionDetailAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.QuestionListAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.AnswerEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.DetailEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.QuestionDataListEntity;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;
import jnhouse.topwellsoft.com.jnhouse_android.view.SmoothListView.SmoothListView;

/**
 * Created by Administrator on 2016/7/12.
 */
public class QuestionDetailActivity extends Activity implements View.OnClickListener {

    private ImageView img_back;
    private TextView tv_middle, tv_right, tv_title, tv_sort, tv_time, tv_content, tv_num;
    private ListView listView;
    private QuestionDetailAdapter questionDetailAdapter;
    private Bundle bundle;
    private MyDiaLog diaLog;
    private OperationDialog operationDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_question);
        initView();
        initDialog();
        getQuestionServerData(JnHouse_Record.Wd_Detail);
    }

    public void initOperationDialog() {
        operationDialog = new OperationDialog(QuestionDetailActivity.this);
        operationDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        operationDialog.setCancelable(true);
        operationDialog.setCanceledOnTouchOutside(true);
        operationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        operationDialog.setListener(new OperationDialog.ButtonListener() {
            @Override
            public void LeftListener() {
                operationDialog.dismiss();
                diaLog.show();
                deleteQuestion(JnHouse_Record.Ask_Del);
            }

            @Override
            public void RighttListener() {
                operationDialog.dismiss();
            }
        });
        operationDialog.show();
        operationDialog.setContent("确定删除该条提问吗？");
        operationDialog.setBtn_left("确认");
        operationDialog.setBtn_right("取消");

    }

    public void initView() {
        bundle = getIntent().getExtras();
        img_back = (ImageView) this.findViewById(R.id.question_img_back);
        tv_middle = (TextView) this.findViewById(R.id.tv_middle);
        tv_right = (TextView) this.findViewById(R.id.tv_right);

        if (bundle.getString("flag").equals("theirs")) {
            tv_right.setVisibility(View.INVISIBLE);
            tv_right.setOnClickListener(null);
        } else if (bundle.getString("flag").equals("mine")) {
            Log.i("#####", "mi   ne");
            tv_right.setVisibility(View.VISIBLE);
            tv_right.setText("删除");
            tv_right.setOnClickListener(this);
        }
        tv_title = (TextView) this.findViewById(R.id.detail_tv_title);
        tv_sort = (TextView) this.findViewById(R.id.request_detail_tab);
        tv_time = (TextView) this.findViewById(R.id.question_detail_time);
        tv_content = (TextView) this.findViewById(R.id.detail_tv_addition);
        tv_num = (TextView) this.findViewById(R.id.tv_answer_num);
        listView = (ListView) this.findViewById(R.id.lv_detail);
        tv_middle.setText("问答详情");
        img_back.setOnClickListener(this);

    }

    private void initDialog() {
        diaLog = new MyDiaLog(QuestionDetailActivity.this);
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(true);
        diaLog.show();
    }

    public void setListViewHightBaseedOnChildren(ListView listview) {
        ListAdapter adapter = listview.getAdapter();
        if (adapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View item = adapter.getView(i, null, listview);
            item.measure(0, 0);
            totalHeight += item.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = totalHeight + (listview.getDividerHeight() * (adapter.getCount() - 1));
        listview.setLayoutParams(params);
    }
    //删除问题

    public void deleteQuestion(String url) {
        LoginEntity info = (LoginEntity) PreferencesUtils.getObject(this, "loginEntity");
        if (info == null || info.getUserUUID() == null) {
            ToastUtil.makeText(  this, "请重新登录",
                    ToastUtil.LENGTH_SHORT)
                    .setAnimation(R.style.PopToast).show();
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put("askIds", bundle.getString("id"));
        params.put("userUUID", info.getUserUUID());
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
                    DetailEntity detailEntity = gson.fromJson(t.toString(), new TypeToken<DetailEntity>() {
                    }.getType());
                    String jsonString = t.toString();
                    if (jsonString.startsWith("{\"code\":1")) {
                        ToastUtil.makeText(QuestionDetailActivity.this, "未登录", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                    } else if (jsonString.startsWith("{\"code\":-1")) {
                        ToastUtil.makeText(QuestionDetailActivity.this, "删除异常", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                    } else if (jsonString.startsWith("{\"code\":0")) {
                        ToastUtil.makeText(QuestionDetailActivity.this, "删除成功", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);

                    }
                    diaLog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //获取数据
    public void getQuestionServerData(String url) {
        LoginEntity info = (LoginEntity) PreferencesUtils.getObject(this, "loginEntity");
        if (info == null || info.getUserUUID() == null) {
            ToastUtil.makeText(this, "请重新登录",
                    ToastUtil.LENGTH_SHORT)
                    .setAnimation(R.style.PopToast).show();
            return;
        }


        AjaxParams params = new AjaxParams();
        params.put("askId", bundle.getString("id"));
        params.put("userId", info.getUser_id());
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
                    DetailEntity detailEntity = gson.fromJson(t.toString(), new TypeToken<DetailEntity>() {
                    }.getType());
                    tv_title.setText(detailEntity.getAskName());
                    tv_content.setText(detailEntity.getExName());
                    tv_num.setText("共有" + detailEntity.getAnswerNum() + "个回答");
                    tv_sort.setText(detailEntity.getCatalogName());
                    tv_time.setText(detailEntity.getAskTime());
                    questionDetailAdapter = new QuestionDetailAdapter(QuestionDetailActivity.this, detailEntity.getAn_list());
                    listView.setAdapter(questionDetailAdapter);
                    setListViewHightBaseedOnChildren(listView);
                    diaLog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.question_img_back:
                finish();
                break;
            case R.id.tv_right://删除
                initOperationDialog();
                break;
            default:
                break;
        }
    }
}
