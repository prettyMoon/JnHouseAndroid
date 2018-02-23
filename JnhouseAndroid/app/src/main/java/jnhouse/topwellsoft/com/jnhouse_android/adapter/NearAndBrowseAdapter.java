package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.manage.ImageManager;
import jnhouse.topwellsoft.com.jnhouse_android.model.BrowseBoroughEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.NearBoroughEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.NewHouseDrawEntity;
import jnhouse.topwellsoft.com.jnhouse_android.view.FluidLayout;

/**
 * Created by chenchen on 2016/7/7.
 */
public class NearAndBrowseAdapter extends RecyclerView.Adapter<NearAndBrowseAdapter.MyViewHolder> {
    private ArrayList<Object> mdata;
    private LayoutInflater mflater;
    private Context mContent;
    public ImageManager imageManager;
    private OnItemClickListener onItemClickListener;

    public NearAndBrowseAdapter(Context context, ArrayList<Object> datas) {
        this.mContent = context;
        mflater = LayoutInflater.from(context);
        this.mdata = datas;
    }

    public static class MyViewHolder extends ViewHolder {

        public ImageView imageView;
        public TextView title, price;
        public FluidLayout ft;

        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.index_draw_item_image);
            title = (TextView) view.findViewById(R.id.braw_title);
            price = (TextView) view.findViewById(R.id.index_draw_desc);
            ft = (FluidLayout) view.findViewById(R.id.draw_tag);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = mflater.inflate(R.layout.index_new_house_hxt_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int i) {
        Object entity = mdata.get(i);
        if (entity instanceof NearBoroughEntity) {
            holder.price.setText(((NearBoroughEntity) entity).getNear_borough_avgprice() + "元/m²");
            holder.title.setText(((NearBoroughEntity) entity).getNear_borough_name());
            imageManager = new ImageManager(mContent);
            imageManager.loadUrlImage(((NearBoroughEntity) entity).getNear_borough_thumb(), holder.imageView);
//            genTag(entity.getTag(),holder.ft);
        } else if (entity instanceof BrowseBoroughEntity) {
            holder.price.setText(((BrowseBoroughEntity) entity).getB_borough_price() + "元/m²");
            holder.title.setText(((BrowseBoroughEntity) entity).getB_borough_title());
            imageManager = new ImageManager(mContent);
            imageManager.loadUrlImage(((BrowseBoroughEntity) entity).getB_borough_thumb(), holder.imageView);
        }
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.OnItemClickListener(holder.itemView, holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mdata.size();

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
                TextView tv = new TextView(mContent);
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
                params.setMargins(12, 12, 12, 12);
                fluidLayout.addView(tv, params);
            }
        }

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //自定义item点击事件的点击事件
    public interface OnItemClickListener {
        void OnItemClickListener(View view, int position);
    }
}
