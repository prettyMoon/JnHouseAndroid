package jnhouse.topwellsoft.com.jnhouse_android.ui.mine;



import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import jnhouse.topwellsoft.com.jnhouse_android.Fragment.CommunityFragment;
import jnhouse.topwellsoft.com.jnhouse_android.Fragment.MineSellOfficeFragment;
import jnhouse.topwellsoft.com.jnhouse_android.Fragment.MineSellSecondFragment;
import jnhouse.topwellsoft.com.jnhouse_android.Fragment.MineSellStoreFragment;
import jnhouse.topwellsoft.com.jnhouse_android.Fragment.NewHouseFragment;
import jnhouse.topwellsoft.com.jnhouse_android.Fragment.RentFragment;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.MyFragmentAdapter;

public class MineSell extends AppCompatActivity{
    private ImageView mImageView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    //碎片数组
    private Fragment[] fragments = {new MineSellSecondFragment(),new MineSellOfficeFragment(),new MineSellStoreFragment()};
    //标签集合
    private String[] titles = {"二手房","写字楼","商铺"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_sell);

        init();
    }

    private void init() {
        tabLayout = (TabLayout) findViewById(R.id.mine_sell_tablayout);
        viewPager = (ViewPager) findViewById(R.id.mine_sell_vp);
        mImageView = (ImageView) findViewById(R.id.mine_sell_back_iv);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        //数据填充,初始化标签
        for (int i = 0; i < titles.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }
        //设置滑动模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //绑定适配器
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        //标签与内容碎片关联，实现联动
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));


    }

}
