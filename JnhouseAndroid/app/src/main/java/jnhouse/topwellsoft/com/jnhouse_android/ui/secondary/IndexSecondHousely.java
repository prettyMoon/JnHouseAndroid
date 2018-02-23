package jnhouse.topwellsoft.com.jnhouse_android.ui.secondary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.SecondHouselyAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.model.SecondHouselyEntity;
import jnhouse.topwellsoft.com.jnhouse_android.util.Application.JnHouseApplication;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;
import jnhouse.topwellsoft.com.jnhouse_android.view.SmoothListView.IndexSmoothListView;

/**
 * Created by admin on 2016/6/8.
 */
public class IndexSecondHousely extends Activity implements View.OnClickListener {

    IndexSmoothListView indexSmoothListView;
    TextView index_second_house_title;
    ImageView index_second_house_back;
    private SecondHouselyAdapter mAdapter;
    private List<SecondHouselyEntity> secondHouselyEntityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tp_index_second_house_ly);
        indexSmoothListView = (IndexSmoothListView) findViewById(R.id.second_house_ly_listView);
        index_second_house_title = (TextView) findViewById(R.id.index_title);
        index_second_house_back=(ImageView)findViewById(R.id.title_back);
        index_second_house_title.setText("房源留言");
        indexSmoothListView.setLoadMoreEnable(false);
        JnHouseApplication application = ((JnHouseApplication) getApplicationContext());
        ToastUtil.makeText(IndexSecondHousely.this,application.getSecondHouselyEntity().toString(),ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
        SecondHouselyEntity s = new SecondHouselyEntity();
        s.getUsername();
        s.getContent();
        s.getAdd_time();
        secondHouselyEntityList.add(s);
        mAdapter = new SecondHouselyAdapter(this,secondHouselyEntityList);
        indexSmoothListView.setAdapter(mAdapter);
        index_second_house_back.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.title_back:
                onBackPressed();
                break;
        }
    }
}