package jnhouse.topwellsoft.com.jnhouse_android.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.HeaderChannelAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.model.ChannelEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.agent.AgentListActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.baike.BaikeActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.TestActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.houseloan.MainActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.loan_counter;
import jnhouse.topwellsoft.com.jnhouse_android.ui.login.UsernameResetActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.QuestionAndAnswerActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.secondary.IndexBoroughDetail;

/**
 * Created by Administrator on 16-5-22.
 */
public class HeaderChannelViewView extends HeaderViewInterface<List<ChannelEntity>> implements View.OnClickListener {

    @Bind(R.id.gv_channel)
    FixedGridView gvChannel;
    @Bind(R.id.relative_ask)
    RelativeLayout relative_ask;
    @Bind(R.id.relative_baike)
    RelativeLayout relative_baike;
    @Bind(R.id.relative_agent)
    RelativeLayout relative_agent;
    @Bind(R.id.relative_counter)
    RelativeLayout relative_counter;
    @Bind(R.id.linear_average)
    LinearLayout linear_average;
    @Bind(R.id.linear_strike)
    LinearLayout linear_strike;
    @Bind(R.id.index_average)
    TextView tv_average;
    @Bind(R.id.index_strike)
    TextView tv_strike;

    public HeaderChannelViewView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(List<ChannelEntity> list, ListView listView) {
        View view = mInflate.inflate(R.layout.header_channel_layout, listView, false);
        ButterKnife.bind(this, view);
        dealWithTheView(list);
        listView.addHeaderView(view);
        gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(mContext, i + "", Toast.LENGTH_LONG).show();
            }
        });
        relative_ask.setOnClickListener(this);
        relative_baike.setOnClickListener(this);
        relative_agent.setOnClickListener(this);
        relative_counter.setOnClickListener(this);
        linear_average.setOnClickListener(this);
        linear_strike.setOnClickListener(this);
    }

    public void setData(Float price, Float volume) {

        NumAnim.startAnim(tv_average, price);
        NumAnim.startAnim(tv_strike, volume);
    }

    private void dealWithTheView(List<ChannelEntity> list) {
        int size = list.size();

        if (size <= 4) {
            gvChannel.setNumColumns(size);
        } else if (size == 6) {
            gvChannel.setNumColumns(3);
        } else if (size == 8) {
            gvChannel.setNumColumns(4);
        } else {
            gvChannel.setNumColumns(4);
        }

        HeaderChannelAdapter adapter = new HeaderChannelAdapter(mContext, list);
        gvChannel.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.relative_ask:
                Intent intent1 = new Intent();
                intent1.setFlags(intent1.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.setClass(mContext, QuestionAndAnswerActivity.class);
                mContext.startActivity(intent1);
                break;

            case R.id.relative_baike:
                Intent intent = new Intent();
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setClass(mContext, BaikeActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.relative_agent:
                Intent intent2 = new Intent();
                intent2.setFlags(intent2.FLAG_ACTIVITY_CLEAR_TOP);
                intent2.setClass(mContext, AgentListActivity.class);
//                intent2.setClass(mContext, IndexBoroughDetail.class);
//                intent2.setClass(mContext, UsernameResetActivity.class);
                mContext.startActivity(intent2);
                break;

            case R.id.relative_counter:
                Intent intent3 = new Intent();
                intent3.setFlags(intent3.FLAG_ACTIVITY_CLEAR_TOP);
                intent3.setClass(mContext, MainActivity.class);
                mContext.startActivity(intent3);
                break;

            case R.id.linear_average:
                Toast.makeText(mContext, "280365", Toast.LENGTH_LONG).show();
                break;

            case R.id.linear_strike:
                Toast.makeText(mContext, "210365", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
