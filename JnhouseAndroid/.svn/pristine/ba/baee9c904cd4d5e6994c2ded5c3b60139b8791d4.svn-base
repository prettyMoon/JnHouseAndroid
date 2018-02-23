package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.manage.ImageManager;
import jnhouse.topwellsoft.com.jnhouse_android.model.MineSellOfficeEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.MineSellStoreEntity;

/**
 * Created by topwellsoft on 2016/7/14.
 */
public class MineSellStoreListViewAdapter extends BaseAdapter {
    private List<MineSellStoreEntity> contentList;
    private Context context;
    private LayoutInflater inflater;
    private ImageManager manager;

    public ArrayList<String> getIsSelect() {
        return isSelect;
    }

    private ArrayList<String> isSelect;
    private boolean canShown = false;


    public MineSellStoreListViewAdapter(Context context, List<MineSellStoreEntity> contentList) {
        isSelect = new ArrayList<String>();
        this.contentList = contentList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        manager = new ImageManager(context);
    }

    public MineSellStoreListViewAdapter() {
    }

    public void setCanShown() {
        this.canShown = !this.canShown;
    }

    public boolean isCanShown() {
        return canShown;
    }


    @Override
    public int getCount() {
        return contentList.size();
    }

    @Override
    public Object getItem(int i) {
        return contentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final ListViewHolder holder = new ListViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_view_mine_sellstore_item, null);
            Log.i("#####","getView_if  "+position);
        }

        holder.layout = (LinearLayout) convertView.findViewById(R.id.layout_del);
        holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.sellstorelist_checkbox);
        holder.img = (ImageView) convertView.findViewById(R.id.mine_sell_thumb_iv);
        holder.tvId = (TextView) convertView.findViewById(R.id.mine_sell_id_tv);
        holder.tvHouse_title = (TextView) convertView.findViewById(R.id.mine_sell_store_title_tv);
        holder.tvShop_type = (TextView) convertView.findViewById(R.id.mine_sell_store_style_tv);
        holder.tvHouse_price = (TextView) convertView.findViewById(R.id.mine_sell_store_price_tv);
        holder.tvHouse_area = (TextView) convertView.findViewById(R.id.mine_sell_store_area_tv);
        holder.tvState = (TextView) convertView.findViewById(R.id.mine_sell_state_tv);
        holder.tvBorough_name = (TextView) convertView.findViewById(R.id.mine_sell_borough_name_tv);

        if (canShown) {
            holder.layout.setVisibility(View.VISIBLE);

        } else {
            holder.layout.setVisibility(View.GONE);
        }

        holder.tvId.setText(contentList.get(position).getId());
        holder.tvHouse_title.setText(contentList.get(position).getHouse_title());
        holder.tvShop_type.setText(contentList.get(position).getShop_type());
        holder.tvHouse_price.setText(contentList.get(position).getHouse_price());
        holder.tvHouse_area.setText(contentList.get(position).getHouse_area());
        holder.tvState.setText(contentList.get(position).getState());
        holder.tvBorough_name.setText(contentList.get(position).getBorough_name());


        manager.loadUrlImage(contentList.get(position).getHouse_img(), holder.img);
        final String current = position + "";
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.mCheckBox.isChecked()) {
                    isSelect.add(current);
                } else {
                    if (isSelect.contains(current)) {
                        isSelect.remove(current);
                    }
                }
            }
        });
        return convertView;
    }

    public class ListViewHolder {
        public TextView tvId, tvHouse_title, tvShop_type,tvHouse_price,tvHouse_area,tvState,tvBorough_name;
        private ImageView img;
        private CheckBox mCheckBox;
        private LinearLayout layout;
    }
}
