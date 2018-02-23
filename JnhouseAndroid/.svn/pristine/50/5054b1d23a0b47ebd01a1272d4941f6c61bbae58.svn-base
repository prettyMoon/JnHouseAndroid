package jnhouse.topwellsoft.com.jnhouse_android.ui.question;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.QuestionSingleListAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.QuestionSingleListEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.QuestionSingleSortEntity;
import jnhouse.topwellsoft.com.jnhouse_android.util.DateTimeUtils;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;
import jnhouse.topwellsoft.com.jnhouse_android.view.SmoothListView.SmoothListView;

/**
 * Created by Administrator on 2016/7/12.
 */
public class QuestionSearchActivity extends Activity implements SmoothListView.ISmoothListViewListener, View.OnClickListener {

    private String index;
    private TextView tv_search;
    private EditText edit;
    private ImageView img_back;
    private SmoothListView listView;
    private ArrayList<QuestionSingleSortEntity> list = new ArrayList<QuestionSingleSortEntity>();
    private QuestionSingleListAdapter adapter;
    private int page = 0;
    private MyDiaLog diaLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_search);
        initView();
        initDialog();
    }

    public void initDialog() {
        diaLog = new MyDiaLog(QuestionSearchActivity.this);
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(true);
    }

    public void initView() {
        tv_search = (TextView) this.findViewById(R.id.search_right);
        img_back = (ImageView) this.findViewById(R.id.search_img_back);
        edit = (EditText) this.findViewById(R.id.edit_middle);
        listView = (SmoothListView) this.findViewById(R.id.smoothlistview_search);
        listView.setSmoothListViewListener(this);
        tv_search.setOnClickListener(this);
        img_back.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(QuestionSearchActivity.this, QuestionDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", list.get(position - 1).getAskId());
                bundle.putString("flag", "theirs");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.stopRefresh();
                page = 0;
                listView.setRefreshTime(DateTimeUtils.getTimeStr(DateTimeUtils.getNowTime(), "yyyy-MM-dd hh:mm:ss"));
                getQuestionServerData(JnHouse_Record.Wd_List, 1);
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.stopLoadMore();
                page++;
                getQuestionServerData(JnHouse_Record.Wd_List, -1);
            }
        }, 2000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_img_back:
                finish();
                break;
            case R.id.search_right:
                diaLog.show();
                InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                if (CheckDataCompleteness()) {
                    index = edit.getText().toString();
                    getQuestionServerData(JnHouse_Record.Wd_List, -1);
                } else {
                    ToastUtil.makeText(QuestionSearchActivity.this, "请输入关键词", Toast.LENGTH_LONG).setAnimation(R.style.PopToast).show();
                }
                break;
            default:
                break;
        }

    }

    private boolean CheckDataCompleteness() {
        Log.i("######", edit.getText().toString());
        if (edit.getText().toString().equals("")) {
            return false;
        }
        return true;
    }

    /**
     * 搜索  请求
     *
     * @param url
     * @param flag
     */
    private void getQuestionServerData(String url, final int flag) {

        AjaxParams params = new AjaxParams();
        params.put("ask_key", index);
        params.put("catalogId", "");
        params.put("cur_index", page + "");
        FinalHttp fh = new FinalHttp();
        fh.get(url, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("####", "onFailure");
                listView.stopRefresh();
                listView.stopLoadMore();
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
                    QuestionSingleListEntity questionSingleListEntity = gson.fromJson(t.toString(), new TypeToken<QuestionSingleListEntity>() {
                    }.getType());
                    if (t.toString().endsWith("\"wd_list\":[]}")) {
                        ToastUtil.makeText(QuestionSearchActivity.this, "未查询到相关结果", ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
                    }
                    if (page == 0) {
                        if (flag > 0) {
                            listView.stopRefresh();
                        }
                        list = questionSingleListEntity.getWd_list();
                        adapter = new QuestionSingleListAdapter(QuestionSearchActivity.this, list);
                        listView.setAdapter(adapter);
                    } else {
                        for (int i = 0; i < questionSingleListEntity.getWd_list().size(); i++) {
                            list.add(questionSingleListEntity.getWd_list().get(i));
                        }
                        listView.stopLoadMore();
                        adapter.notifyDataSetChanged();
                    }
                    diaLog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
