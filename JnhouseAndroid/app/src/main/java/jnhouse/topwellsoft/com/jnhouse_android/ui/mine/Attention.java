package jnhouse.topwellsoft.com.jnhouse_android.ui.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import jnhouse.topwellsoft.com.jnhouse_android.Fragment.CommunityFragment;
import jnhouse.topwellsoft.com.jnhouse_android.Fragment.NewHouseFragment;
import jnhouse.topwellsoft.com.jnhouse_android.Fragment.RentFragment;
import jnhouse.topwellsoft.com.jnhouse_android.Fragment.SecondFragment;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.MyFragmentAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.util.TabManager;

public class Attention extends AppCompatActivity{
    private ImageView mImageView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button mButton,mButton_delete;
    private TextView mTextView;
    //碎片数组
    private Fragment[] fragments = {new SecondFragment(),new RentFragment(),new CommunityFragment(),new NewHouseFragment()};
    //标签集合
    private String[] titles = {"二手房","租房","小区","新房"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_attention);

        init();
    }

    private void init() {
        mButton_delete = (Button) findViewById(R.id.attention_delete_btn);
        tabLayout = (TabLayout) findViewById(R.id.attention_tablayout);
        viewPager = (ViewPager) findViewById(R.id.attention_vp);
//        mImageView = (ImageView) findViewById(R.id.attention_back_iv);
        mButton = (Button) findViewById(R.id.back_button);
        mTextView = (TextView) findViewById(R.id.attention_edit_tv);
        /*mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mButton_delete.setVisibility(View.VISIBLE);
            }
        });*/

        mButton.setOnClickListener(new View.OnClickListener() {
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
