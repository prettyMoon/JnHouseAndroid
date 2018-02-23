package jnhouse.topwellsoft.com.jnhouse_android.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.HeaderAdAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.manage.ImageManager;
import jnhouse.topwellsoft.com.jnhouse_android.model.BoroughPicList;
import jnhouse.topwellsoft.com.jnhouse_android.model.NewHousePicEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.RentHousePicEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.SecondHousePicEntity;

/**
 * Created by admin on 2016/6/8.
 */
public class ImageUtil {

    private static final int TYPE_CHANGE_AD = 0;
    private Thread mThread;
    private List<ImageView> ivList = new ArrayList<>();
    private boolean isStopThread = false;
    private ImageManager mImageManager;
    private Context mContext;
    private ViewPager mViewPager;
    private LinearLayout mLinearLayout;

    public ImageUtil(ViewPager mViewPager, LinearLayout mLinearLayout, Context mContext) {
        this.mViewPager = mViewPager;
        this.mLinearLayout = mLinearLayout;
        this.mContext = mContext;
        mImageManager = new ImageManager(mContext);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == TYPE_CHANGE_AD) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            }
        }
    };

    public void dealWithTheView(List<SecondHousePicEntity> list) {
        ivList.clear();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ivList.add(createImageView(list.get(i).getPic_url()));
        }
        HeaderAdAdapter photoAdapter = new HeaderAdAdapter(mContext, ivList);
        mViewPager.setAdapter(photoAdapter);
        addIndicatorImageViews(size);
        setViewPagerChangeListener(size);
        startADRotate();
    }

    public void dealWithTheViewRentHouse(List<RentHousePicEntity> list) {
        ivList.clear();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ivList.add(createImageView(list.get(i).getPic_url()));
        }
        HeaderAdAdapter photoAdapter = new HeaderAdAdapter(mContext, ivList);
        mViewPager.setAdapter(photoAdapter);
        addIndicatorImageViews(size);
        setViewPagerChangeListener(size);
        startADRotate();
    }

    public void dealWithTheViewNewHouse(List<NewHousePicEntity> list) {
        ivList.clear();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ivList.add(createImageView(list.get(i).getPic_url()));
        }
        HeaderAdAdapter photoAdapter = new HeaderAdAdapter(mContext, ivList);
        mViewPager.setAdapter(photoAdapter);
        addIndicatorImageViews(size);
        setViewPagerChangeListener(size);
        startADRotate();
    }

    public void dealWithTheViewBorough(List<BoroughPicList> list) {
        ivList.clear();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ivList.add(createImageView(list.get(i).getPic_url()));
        }
        HeaderAdAdapter photoAdapter = new HeaderAdAdapter(mContext, ivList);
        mViewPager.setAdapter(photoAdapter);
        addIndicatorImageViews(size);
        setViewPagerChangeListener(size);
        startADRotate();
    }

    // 创建要显示的ImageView
    private ImageView createImageView(String url) {
        ImageView imageView = new ImageView(mContext);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageManager.loadUrlImage(url, imageView);
        return imageView;
    }

    // 添加指示图标
    private void addIndicatorImageViews(int size) {
        mLinearLayout.removeAllViews();
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
            mLinearLayout.addView(iv);
        }
    }

    // 为ViewPager设置监听器
    private void setViewPagerChangeListener(final int size) {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (ivList != null && ivList.size() > 0) {
                    int newPosition = position % size;
                    for (int i = 0; i < size; i++) {
                        mLinearLayout.getChildAt(i).setEnabled(false);
                        if (i == newPosition) {
                            mLinearLayout.getChildAt(i).setEnabled(true);
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
    private void startADRotate() {
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
                        SystemClock.sleep(5000);
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
