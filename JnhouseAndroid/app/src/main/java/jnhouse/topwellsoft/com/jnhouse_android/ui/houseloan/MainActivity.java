package jnhouse.topwellsoft.com.jnhouse_android.ui.houseloan;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.topwellsoft.androidutils.StyleUtils;

import jnhouse.topwellsoft.com.jnhouse_android.R;


@SuppressLint("InflateParams") public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";
    private ViewPager mPager;
    private ArrayList<Fragment> fragmentsList;
    private ImageView ivBottomLine,backbtn;
    private TextView tvTab1, tvTab2, tvTab3,title;
    ListFragment mFrag;
    private int currIndex = 0;
    private int bottomLineWidth;
    private int offset = 0;
    private int position_one;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StyleUtils.initStatusBar(getWindow());
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        InitWidth();
        InitTextView();
        InitViewPager();
    }
    private void InitTextView() {
        tvTab1 = (TextView) findViewById(R.id.tv_tab1);
        tvTab2 = (TextView) findViewById(R.id.tv_tab2);
        tvTab3 = (TextView) findViewById(R.id.tv_tab3);
       title=(TextView)findViewById(R.id.index_title) ;
        title.setText("房贷计算器");
        title.setVisibility(View.VISIBLE);
        backbtn=(ImageView)findViewById(R.id.title_back) ;
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvTab1.setOnClickListener(new MyOnClickListener(0));
        tvTab2.setOnClickListener(new MyOnClickListener(1));
        tvTab3.setOnClickListener(new MyOnClickListener(2));
    }

    private void InitViewPager() {
        mPager = (ViewPager) findViewById(R.id.vPager);
        fragmentsList = new ArrayList<Fragment>();
        LayoutInflater mInflater = getLayoutInflater();
        mInflater.inflate(R.layout.lay1, null);
        Fragment fragment1 = new GongJiJinFragment();
        Fragment fragment2 = new ShangDaiFragment();
        Fragment fragment3=new ZuHeFragment();

        fragmentsList.add(fragment1);
        fragmentsList.add(fragment2);
        fragmentsList.add(fragment3);
        
        mPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentsList));
        mPager.setCurrentItem(0);
        mPager.setOffscreenPageLimit(5);
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void InitWidth() {
        ivBottomLine = (ImageView) findViewById(R.id.iv_bottom_line);
        bottomLineWidth = BitmapFactory.decodeResource(getResources(),
        		R.drawable.tab_tag_selected).getWidth();// 获取图片宽度;
        Log.d(TAG, "cursor imageview width=" + bottomLineWidth);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        int screenH = dm.heightPixels;
        Log.e(TAG, "screenW"+screenW+":"+"screenH"+screenH+"bottomLineWidth:"+bottomLineWidth);
        offset = (int) ((screenW / 3.0 - bottomLineWidth) / 2);
        Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		ivBottomLine.setImageMatrix(matrix);//設置初始位置

        Log.i("MainActi`vity", "offset=" + offset);

        position_one = (int) (screenW / 3.0);
//        position_two = position_one * 2;
//        position_three = position_one * 3;
    }

    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mPager.setCurrentItem(index);
        }
    };

    public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
        	Animation animation = new TranslateAnimation(position_one * currIndex, position_one
        	* arg0, 0, 0);// 显然这个比较简洁，只有一行代码。
        	currIndex = arg0;
        	animation.setFillAfter(true);// True:图片停在动画结束位置
        	animation.setDuration(300);
        	ivBottomLine.startAnimation(animation);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
}