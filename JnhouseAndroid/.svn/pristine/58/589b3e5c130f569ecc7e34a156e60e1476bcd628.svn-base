package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.model.RentingHouseEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.ViewHolder;
import jnhouse.topwellsoft.com.jnhouse_android.view.FluidLayout;

/**
 * Created by chenchen on 2016/6/21.
 */
public class RentingHouseAdapter extends BaseListAdapter<RentingHouseEntity> {
    private List<RentingHouseEntity> mList;
    private boolean isNoData;
    private int mHeight;
    public static final int ONE_SCREEN_COUNT = 20;
    public static final int ONE_REQUEST_COUNT = 19;

    public RentingHouseAdapter(Context context) {
        super(context);
    }

    public RentingHouseAdapter(Context context, List<RentingHouseEntity> list) {
        super(context, list);
        this.mList = list;
    }

    //设置数据
    public void setData(List<RentingHouseEntity> list) {
        addALL(list);
        isNoData = false;
        if (list.size() == 1 && list.get(0).isNoData()) {
            isNoData = list.get(0).isNoData();
            mHeight = list.get(0).getHeight();
        }
        notifyDataSetChanged();
    }

    // 设置数据
    public void setDataFilter(List<RentingHouseEntity> list) {
        clearAll();
        addALL(list);
        isNoData = false;
        if (list.size() == 1 && list.get(0).isNoData()) {
            // 暂无数据布局
            isNoData = list.get(0).isNoData();
            mHeight = list.get(0).getHeight();
        }
        notifyDataSetChanged();
    }


    // 创建不满一屏的空数据
    public List<RentingHouseEntity> createEmptyList(int size) {
        List<RentingHouseEntity> emptyList = new ArrayList<>();
        if (size <= 0) return emptyList;
        for (int i = 0; i < size; i++) {
            emptyList.add(new RentingHouseEntity());
        }
        return emptyList;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (isNoData) {
            view = mInflater.inflate(R.layout.item_no_data_layout, null);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight);
            RelativeLayout rootView = ButterKnife.findById(view, R.id.rl_root_view);
            rootView.setLayoutParams(params);
            return view;
        }
        final ViewHolder holder;
        if (view != null && view instanceof LinearLayout) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = mInflater.inflate(R.layout.tp_rent_house_list_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);

        }
        RentingHouseEntity entity = mList.get(i);
        mImageManager.loadUrlImage(entity.getHouse_thumb(), holder.imageView);
        holder.tvTitle.setText(entity.getHouse_title());
        holder.tvRoom.setText(entity.getHouse_room() + " " + entity.getHouse_totalarea() + "m² " +getContent(entity.getHouse_toward()) );
        holder.tvPrice.setText(Integer.parseInt(entity.getHouse_price().split("\\.")[0]) + "元/月");
        holder.tvName.setText(entity.getBorough_name());
        genTag(entity.getHouse_feature(), holder.fluidLayout);

        return view;
    }
    private void genTag(String laber, FluidLayout fluidLayout) {
        fluidLayout.removeAllViews();
        fluidLayout.setGravity(Gravity.CENTER);
        List<String> tags = new ArrayList<>();
        if (laber != null && !laber.equals("")) {
            String[] s = laber.split(",");
            for (int i = 0; i < s.length; i++) {
                tags.add(s[i]);
            }
        }

        if (tags != null && tags.size() > 0) {
            for (int i = 0; i < tags.size(); i++) {
                TextView tv = new TextView(mContext);
                tv.setText(tags.get(i));
                tv.setTextSize(10);

                if (i == 12) {
                    if (!true) {
                        tv.setHeight(100);
                        tv.setGravity(Gravity.CENTER);
                    }
                    tv.setBackgroundResource(R.drawable.text_bg_highlight);

                } else {
                    if (true) {
                        tv.setBackgroundResource(R.drawable.text_bg);
                    }
                }

                if (i % 8 == 0) {
                    tv.setTextColor(Color.parseColor("#FF0000"));
                } else if (i % 28 == 0) {
                    tv.setTextColor(Color.parseColor("#66CD00"));
                } else {
                    tv.setTextColor(Color.parseColor("#666666"));
                }

                FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(3,3,3,3);
                fluidLayout.addView(tv, params);
            }
        }

    }


    static class ViewHolder {

        @Bind(R.id.rent_house_item_thumb)
        ImageView imageView;
        @Bind(R.id.rent_house_item_title)
        TextView tvTitle;
        @Bind(R.id.rent_house_item_room)
        TextView tvRoom;
        @Bind(R.id.rent_house_item_price)
        TextView tvPrice;
        @Bind(R.id.rent_house_item_borough_name)
        TextView tvName;
        @Bind(R.id.rent_house_fluid_layout)
        FluidLayout fluidLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }



    public String getContent(String str) {
        if (str == null || str.equals("")) {
            return "";
        } else {
            return str;
        }
    }
}
