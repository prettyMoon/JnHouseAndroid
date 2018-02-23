package jnhouse.topwellsoft.com.jnhouse_android.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.HeaderAdAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.manage.ImageManager;
import jnhouse.topwellsoft.com.jnhouse_android.util.DensityUtil;

/**
 * Created by admin on 2016/5/25.
 */
public class IndexHeaderImageView {

    private static final int TYPE_CHANGE_AD = 0;
    private LinearLayout llIndexContainer;
    private List<ImageView> ivList;
    private boolean isStopThread = false;
    private ImageManager mImageManager;
    private Thread mThread;
    private ViewPager viewPager;
    private Context mContext;
    private List<String> list;

    public IndexHeaderImageView(LinearLayout llIndexContainer,ViewPager vpAd,Context mContext, List<String> list){
        this.llIndexContainer = llIndexContainer;
        this.viewPager = vpAd;
        this.mContext = mContext;
        this.list = list;
        ivList = new ArrayList<>();
        mImageManager = new ImageManager(mContext);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == TYPE_CHANGE_AD) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        }
    };

    public void dealWithTheView() {
        ivList.clear();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ivList.add(createImageView(list.get(i)));
        }
        HeaderAdAdapter photoAdapter = new HeaderAdAdapter(mContext, ivList);
        viewPager.setAdapter(photoAdapter);
        addIndicatorImageViews(size);
        setViewPagerChangeListener(size);
        startADRotate();
    }

    // 创建要显示的ImageView
    public ImageView createImageView(String url) {
        ImageView imageView = new ImageView(mContext);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageManager.loadUrlImage(url, imageView);
        return imageView;
    }

    // 添加指示图标
    public void addIndicatorImageViews(int size) {
        llIndexContainer.removeAllViews();
        for (int i = 0; i < size; i++) {
            ImageView iv = new ImageView(mContext);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5));
            if (i != 0) {
                lp.leftMargin = DensityUtil.dip2px(mContext, 7);
            }
            iv.setLayoutParams(lp);
            iv.setBackgroundResource(R.drawable.xml_round_orange_grey_sel);
            iv.setEnabled(false);
            if (i == 0) {
                iv.setEnabled(true);
            }
            llIndexContainer.addView(iv);
        }
    }

    // 为ViewPager设置监听器
    public void setViewPagerChangeListener(final int size) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (ivList != null && ivList.size() > 0) {
                    int newPosition = position % size;
                    for (int i = 0; i < size; i++) {
                        llIndexContainer.getChildAt(i).setEnabled(false);
                        if (i == newPosition) {
                            llIndexContainer.getChildAt(i).setEnabled(true);
                        }
                    }
                }
            }

            @Override
            public void onPageScrolled(int position, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    // 启动循环广告的线程
    public void startADRotate() {
        // 一个广告的时候不用转
        if (ivList == null || ivList.size() <= 1) {
            return;
        }
        if (mThread == null) {
            mThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    // 当没离开该页面时一直转
                    while (!isStopThread) {
                        // 每隔5秒转一次
                        SystemClock.sleep(10000);
                        // 在主线程更新界面
                        mHandler.sendEmptyMessage(TYPE_CHANGE_AD);
                    }
                }
            });
            mThread.start();
        }
    }

    // 停止循环广告的线程，清空消息队列
    public void stopADRotate() {
        isStopThread = true;
        if (mHandler != null && mHandler.hasMessages(TYPE_CHANGE_AD)) {
            mHandler.removeMessages(TYPE_CHANGE_AD);
        }
    }
}
