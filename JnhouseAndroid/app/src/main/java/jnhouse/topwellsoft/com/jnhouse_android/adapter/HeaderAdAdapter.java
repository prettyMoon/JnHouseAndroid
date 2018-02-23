package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import jnhouse.topwellsoft.com.jnhouse_android.view.HeaderAdViewView;

/**
 * Created by Administrator on 16-5-22.
 */
public class HeaderAdAdapter extends PagerAdapter {

    private Context mContext;
    private List<ImageView> ivList; // ImageView的集合
    private int count = 1; // 广告的数量

    public HeaderAdAdapter(Context context, List<ImageView> ivList) {
        super();
        this.mContext = context;
        this.ivList = ivList;
        if (ivList != null && ivList.size() > 0) {
            count = ivList.size();
        }
    }

    @Override
    public int getCount() {
        if (count == 1) {
            return 1;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final int newPosition = position % count;
        // 先移除在添加，更新图片在container中的位置（把iv放至container末尾）
        ImageView iv = ivList.get(newPosition);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HeaderAdViewView.gg_list != null && HeaderAdViewView.gg_list.size() > newPosition) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(HeaderAdViewView.gg_list.get(newPosition).getLink()));
                    mContext.startActivity(intent);
                }
            }
        });
        container.removeView(iv);


        container.addView(iv);
        return iv;
    }
}
