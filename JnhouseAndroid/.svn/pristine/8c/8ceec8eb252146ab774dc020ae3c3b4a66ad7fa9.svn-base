package jnhouse.topwellsoft.com.jnhouse_android.ui.new_house;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import java.util.ArrayList;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.manage.ImageManager;

/**
 * Created by Administrator on 2016/7/15.
 */
public class MoreImagesActivity extends Activity {
    private ViewPager viewPager;
    private ArrayList<View> views;
    private ImageManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_img_activity);
        manager = new ImageManager(MoreImagesActivity.this);
        findViewById(R.id.zoom_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        viewPager = (ViewPager) findViewById(R.id.img_viewpager);
        Intent intent = getIntent();
        ArrayList<String> urls = intent.getStringArrayListExtra("urls");

        views = new ArrayList<>();
        for (String url : urls) views.add(getZoomView(url));

        viewPager.setAdapter(new MoreImagesAdapter());

        viewPager.setCurrentItem(intent.getIntExtra("num", 0));
    }

    private View getZoomView(String imgurl) {

        final ImageView csv = new ImageView(MoreImagesActivity.this, null);
        csv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        manager.loadUrlImage(imgurl, csv);
        return csv;
    }

    class MoreImagesAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position), 0);
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (views.get(position).getParent() != null)
                container.removeView(views.get(position));
        }
    }
}

