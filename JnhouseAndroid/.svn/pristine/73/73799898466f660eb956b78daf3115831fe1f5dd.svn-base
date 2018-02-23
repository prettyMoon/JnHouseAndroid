package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.model.CollectionEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.MyReleaseEntity;

/**
 * Created by topwellsoft on 2016/7/8.
 */
public class MyReleaseAdapter extends BaseListAdapter<MyReleaseEntity> {
    private  List<MyReleaseEntity> mList;
    private boolean isNoData;
    private int mHeight;
    public static final int ONE_SCREEN_COUNT = 20; // 一屏能显示的个数，这个根据屏幕高度和各自的需求定
    public static final int ONE_REQUEST_COUNT = 19; // 一次请求的个数

    // 设置数据
    public void setData(List<MyReleaseEntity> list) {
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
    public List<MyReleaseEntity> createEmptyList(int size) {
        List<MyReleaseEntity> emptyList = new ArrayList<>();
        if (size <= 0) return emptyList;
        for (int i=0; i<size; i++) {
            emptyList.add(new MyReleaseEntity());
        }
        return emptyList;
    }

    public MyReleaseAdapter(Context context) {
        super(context);
    }

    public MyReleaseAdapter(Context context, List<MyReleaseEntity> list) {
        super(context, list);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        // 暂无数据
        if (isNoData) {
            convertView = mInflater.inflate(R.layout.item_no_data_layout, null);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight);
            RelativeLayout rootView = ButterKnife.findById(convertView, R.id.rl_root_view);
            rootView.setLayoutParams(params);
            return convertView;
        }
        // 正常数据
        final ViewHolder holder;
        if (convertView != null && convertView instanceof LinearLayout) {
            holder = (ViewHolder) convertView.getTag();

        } else {
            convertView = mInflater.inflate(R.layout.list_view_collection_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        }

        MyReleaseEntity entity = mList.get(i);
        mImageManager.loadUrlImage(entity.getHouse_thumb(), holder.imageView);
        holder.tvTitle.setText(entity.getHouse_title());
        holder.tvRoom.setText(entity.getHouse_room());
        holder.tvArea.setText(entity.getHouse_area());
        holder.tvToward.setText(entity.getHouse_toward());
        holder.tvPrice.setText(entity.getHouse_price());
        holder.tvBorough.setText(entity.getHouse_borough());
        holder.btnState.setText(entity.getHouse_state());

        return convertView;
    }


    static class ViewHolder {

        @Bind(R.id.my_release__thumb_iv)
        ImageView imageView;
        @Bind(R.id.my_release__title_tv)
        TextView tvTitle;
        @Bind(R.id.my_release_room_tv)
        TextView tvRoom;
        @Bind(R.id.my_release__area_tv)
        TextView tvArea;
        @Bind(R.id.my_release__toward_tv)
        TextView tvToward;
        @Bind(R.id.my_release__price_tv)
        TextView tvPrice;
        @Bind(R.id.my_release__borough_tv)
        TextView tvBorough;
        @Bind(R.id.my_release_state_btn)
        Button btnState;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }




}
