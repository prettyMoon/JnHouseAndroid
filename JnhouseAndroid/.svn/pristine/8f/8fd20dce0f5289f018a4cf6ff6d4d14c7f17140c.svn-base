package jnhouse.topwellsoft.com.jnhouse_android.ui.question;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.StyleUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.QuestionListAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.QuestionDataEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.QuestionDataListEntity;

/**
 * Created by Administrator on 2016/7/11.
 */
public class QuestionAndAnswerActivity extends Activity implements View.OnClickListener {
    private ListView lv_popular;
    private TextView tv_more, tv_title, tv_right;
    private ImageView img_back;
    private ImageView img_ttt;
    private EditText edit_ttt;
    private QuestionListAdapter adapter;
    private int tv_id[] = {R.id.sort0, R.id.sort1, R.id.sort2, R.id.sort3, R.id.sort4, R.id.sort5};
    private TextView[] tv = new TextView[6];
    private LinearLayout layout_search;
    private QuestionDataListEntity questionDataListEntity;
    private MyDiaLog diaLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StyleUtils.initStatusBar(getWindow());
        setContentView(R.layout.activity_question_and_answer);
        initVeiw();
        initDialog();
        getQuestionServerData(JnHouse_Record.Wd_Index);
        lv_popular.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(QuestionAndAnswerActivity.this, QuestionDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", adapter.getList().get(position).getAskId());
                bundle.putString("flag", "theirs");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initDialog() {
        diaLog = new MyDiaLog(QuestionAndAnswerActivity.this);
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(true);
        diaLog.show();
    }

    private void initVeiw() {
        img_ttt = (ImageView) this.findViewById(R.id.img_ttt);
        edit_ttt = (EditText) this.findViewById(R.id.edit_ttttt);
        lv_popular = (ListView) this.findViewById(R.id.listview_popular);
        img_back = (ImageView) this.findViewById(R.id.question_img_back);
        tv_right = (TextView) this.findViewById(R.id.tv_right);
        tv_more = (TextView) this.findViewById(R.id.popular_more);
        tv_title = (TextView) this.findViewById(R.id.tv_middle);
        layout_search = (LinearLayout) this.findViewById(R.id.question_layout_search);
        tv_title.setText("问答");
        tv_right.setText("我要提问");
        layout_search.setOnClickListener(this);
        img_back.setOnClickListener(this);
        tv_right.setOnClickListener(this);
        img_ttt.setOnClickListener(this);
        edit_ttt.setOnClickListener(this);
        tv_more.setOnClickListener(this);
        for (int i = 0; i < 6; i++) {
            tv[i] = (TextView) this.findViewById(tv_id[i]);
            tv[i].setOnClickListener(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        diaLog.show();
        getQuestionServerData(JnHouse_Record.Wd_Index);
    }

    public void getQuestionServerData(String url) {

        AjaxParams params = new AjaxParams();
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
                    questionDataListEntity = gson.fromJson(t.toString(), new TypeToken<QuestionDataListEntity>() {
                    }.getType());
                    Log.i("#####", t.toString());
                    ArrayList<QuestionDataEntity> list = new ArrayList<QuestionDataEntity>();
                    for (int i = 0; i < questionDataListEntity.getDatalist().size(); i++) {
                        if (i >= 3) break;
                        list.add(questionDataListEntity.getDatalist().get(i));
                    }
                    adapter = new QuestionListAdapter(QuestionAndAnswerActivity.this, list);
                    lv_popular.setAdapter(adapter);
                    setListViewHightBaseedOnChildren(lv_popular);
                    for (int i = 0; i < questionDataListEntity.getSortlist().size(); i++) {
                        tv[i].setText(questionDataListEntity.getSortlist().get(i).getCatalogName());
                    }
                    diaLog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
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

    public Intent getTargetIntent(int position) {
        Intent intent = new Intent(QuestionAndAnswerActivity.this, QuestionListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", questionDataListEntity.getSortlist().get(position).getCatalogId());
        bundle.putString("flag", "theirs");
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        Bundle bundle = null;
        switch (v.getId()) {
            case R.id.layout_search://搜索
            case R.id.img_ttt:
            case R.id.edit_ttt:
                intent = new Intent(QuestionAndAnswerActivity.this, QuestionSearchActivity.class);
                startActivity(intent);
                break;
            case R.id.question_img_back://返回
                finish();
                break;
            case R.id.popular_more://更多
                Log.i("#####", "sssssssssssss");
                intent = new Intent(QuestionAndAnswerActivity.this, QuestionListActivity.class);
                bundle = new Bundle();
                bundle.putString("id", "");
                bundle.putString("flag", "more");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.tv_right:
                intent = new Intent(QuestionAndAnswerActivity.this, ToQuestionActivity.class);
                bundle = new Bundle();
                bundle.putSerializable("category", questionDataListEntity.getSortlist());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.sort0:
                startActivity(getTargetIntent(0));
                break;
            case R.id.sort1:
                startActivity(getTargetIntent(1));
                break;
            case R.id.sort2:
                startActivity(getTargetIntent(2));
                break;
            case R.id.sort3:
                startActivity(getTargetIntent(3));
                break;
            case R.id.sort4:
                startActivity(getTargetIntent(4));
                break;
            case R.id.sort5:
                startActivity(getTargetIntent(5));
                break;
            default:
                break;

        }
    }
}
