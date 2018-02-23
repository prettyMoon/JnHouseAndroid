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
import jnhouse.topwellsoft.com.jnhouse_android.model.MineRentEntity;

/**
 * Created by topwellsoft on 2016/7/14.
 */
public class MineRentListViewAdapter extends BaseAdapter {
    private List<MineRentEntity> contentList;
    private Context context;
    private LayoutInflater inflater;
    private ImageManager manager;

    public ArrayList<String> getIsSelect() {
        return isSelect;
    }

    private ArrayList<String> isSelect;
    private boolean canShown = false;

    public MineRentListViewAdapter(Context context, List<MineRentEntity> contentList) {
        isSelect = new ArrayList<String>();
        this.contentList = contentList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        manager = new ImageManager(context);
    }

    public MineRentListViewAdapter() {
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
            convertView = inflater.inflate(R.layout.list_view_mine_rent_item, null);
            Log.i("#####","getView_if  "+position);
        }

        holder.layout = (LinearLayout) convertView.findViewById(R.id.layout_del);
        holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.rentlist_checkbox);
        holder.img = (ImageView) convertView.findViewById(R.id.mine_sell_thumb_iv);
        holder.tvId = (TextView) convertView.findViewById(R.id.mine_rent_id_tv);
        holder.tvState = (TextView) convertView.findViewById(R.id.mine_rent_state_tv);
        holder.tvHouse_hall = (TextView) convertView.findViewById(R.id.mine_rent_house_hall_tv);
        holder.tvHouse_room = (TextView) convertView.findViewById(R.id.mine_rent_house_room_tv);
        holder.tvHouse_toilet = (TextView) convertView.findViewById(R.id.mine_rent_house_toilet_tv);
        holder.tvHouse_kitchen = (TextView) convertView.findViewById(R.id.mine_rent_house_kitchen_tv);
        holder.tvHouse_balcony = (TextView) convertView.findViewById(R.id.mine_rent_house_balcony_tv);
        holder.tvHouse_area = (TextView) convertView.findViewById(R.id.mine_rent_house_area_tv);
        holder.tvHouse_title = (TextView) convertView.findViewById(R.id.mine_rent_house_title_tv);
        holder.tvRent_price = (TextView) convertView.findViewById(R.id.mine_rent_house_price_tv);
        holder.tvBorough_name = (TextView) convertView.findViewById(R.id.mine_rent_borough_name_tv);
        holder.tvRent_price_unit = (TextView) convertView.findViewById(R.id.mine_rent_house_price_unit_tv);

        if (canShown) {
            holder.layout.setVisibility(View.VISIBLE);

        } else {
            holder.layout.setVisibility(View.GONE);
        }

        holder.tvId.setText(contentList.get(position).getId());
        holder.tvState.setText(contentList.get(position).getState());
        holder.tvHouse_hall.setText(contentList.get(position).getHouse_hall());
        holder.tvHouse_room.setText(contentList.get(position).getHouse_room());
        holder.tvHouse_toilet.setText(contentList.get(position).getHouse_toilet());
        holder.tvHouse_kitchen.setText(contentList.get(position).getHouse_kitchen());
        holder.tvHouse_balcony.setText(contentList.get(position).getHouse_balcony());
        holder.tvHouse_area.setText(contentList.get(position).getHouse_area());
        holder.tvHouse_title.setText(contentList.get(position).getHouse_title());
        holder.tvRent_price.setText(contentList.get(position).getRent_price());
        holder.tvBorough_name.setText(contentList.get(position).getBorough_name());
        holder.tvRent_price_unit.setText(contentList.get(position).getRent_price_unit());


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
        public TextView tvId, tvState, tvHouse_hall,tvHouse_room,tvHouse_toilet,tvHouse_kitchen,tvHouse_balcony,tvHouse_area,tvHouse_title,tvRent_price,tvBorough_name,tvRent_price_unit;
        private ImageView img;
        private CheckBox mCheckBox;
        private LinearLayout layout;
    }
}
