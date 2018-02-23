package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.topwellsoft.jnhouse_android.realtime_order.RealtimeOrderActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.model.ChannelEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.Ren_house.IndexRentHouse;
import jnhouse.topwellsoft.com.jnhouse_android.ui.consult.ConsultActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.decoration.Decoration;
import jnhouse.topwellsoft.com.jnhouse_android.ui.financial.FinancialStart;
import jnhouse.topwellsoft.com.jnhouse_android.ui.new_house.IndexNewHouse;
import jnhouse.topwellsoft.com.jnhouse_android.ui.release.Release;
import jnhouse.topwellsoft.com.jnhouse_android.ui.secondary.IndexSecondHandHouse;

/**
 * Created by Administrator on 16-5-22.
 */
public class HeaderChannelAdapter extends BaseListAdapter<ChannelEntity> {

    public HeaderChannelAdapter(Context context, List<ChannelEntity> list) {
        super(context, list);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_channel, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ChannelEntity entity = getItem(position);

        holder.tvTitle.setText(entity.getTitle());
        mImageManager.loadResImage(entity.getImage_url(), holder.ivImage);
        holder.channel_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(mContext, IndexSecondHandHouse.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        mContext.startActivity(intent);

                        break;
                    case 1:
                        intent.setClass(mContext,IndexNewHouse.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        mContext.startActivity(intent);
                        break;
                    case  2:
                        intent.setClass(mContext, IndexRentHouse.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                        mContext.startActivity(intent);
                        break;
                    case 3:
                        intent.setClass(mContext,Release.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        mContext.startActivity(intent);
                        break;
                    case 4:
                        //intent.setClass(mContext, MapFindHouse.class);
                        intent.setClass(mContext, RealtimeOrderActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        mContext.startActivity(intent);
                        break;
                    case 5:
                        intent.setClass(mContext, FinancialStart.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        mContext.startActivity(intent);
                        break;
                    case 6:
                        intent.setClass(mContext, Decoration.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        mContext.startActivity(intent);
                        break;
                    case 7:
                        intent.setClass(mContext,ConsultActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        mContext.startActivity(intent);
                        break;

                }

            }
        });

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_image)
        ImageView ivImage;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.channel_ll)
        RelativeLayout channel_ll;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
