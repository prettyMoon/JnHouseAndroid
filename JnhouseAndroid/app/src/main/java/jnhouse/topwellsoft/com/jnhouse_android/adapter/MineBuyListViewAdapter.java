package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.model.MineBuyEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.MineRentEntity;

/**
 * Created by topwellsoft on 2016/7/14.
 */
public class MineBuyListViewAdapter extends BaseAdapter {
    private List<MineBuyEntity> contentList;
    private Context context;
    private LayoutInflater inflater;

    public ArrayList<String> getIsSelect() {
        return isSelect;
    }

    private ArrayList<String> isSelect;
    private boolean canShown = false;

    public MineBuyListViewAdapter(Context context, List<MineBuyEntity> contentList) {
        isSelect = new ArrayList<String>();
        this.contentList = contentList;
        this.context = context;
        inflater = LayoutInflater.from(context);
//        manager = new ImageManager(context);
    }

    public MineBuyListViewAdapter() {
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
            convertView = inflater.inflate(R.layout.list_view_mine_buy_item, null);
            Log.i("#####","getView_if  "+position);
        }

        holder.layout = (LinearLayout) convertView.findViewById(R.id.layout_del);
        holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.minebuylist_checkbox);
//        holder.img = (ImageView) convertView.findViewById(R.id.collection_thumb_iv);
        holder.tvId = (TextView) convertView.findViewById(R.id.mine_buy_id_tv);
        holder.tvHouse_title = (TextView) convertView.findViewById(R.id.mine_buy_house_title_tv);
        holder.tvWant_price = (TextView) convertView.findViewById(R.id.mine_buy_house_price_tv);
        holder.tvState = (TextView) convertView.findViewById(R.id.mine_buy_state_tv);
        holder.tvBorough_name = (TextView) convertView.findViewById(R.id.mine_buy_borough_name_tv);
        holder.tvHouse_room = (TextView) convertView.findViewById(R.id.mine_buy_house_room_tv);
        holder.tvHouse_hall = (TextView) convertView.findViewById(R.id.mine_buy_house_hall_tv);
        holder.tvHouse_toilet = (TextView) convertView.findViewById(R.id.mine_buy_house_toilet_tv);

        if (canShown) {
            holder.layout.setVisibility(View.VISIBLE);

        } else {
            holder.layout.setVisibility(View.GONE);
        }

        holder.tvId.setText(contentList.get(position).getId());
        holder.tvHouse_title.setText(contentList.get(position).getHouse_title());
        holder.tvWant_price.setText(contentList.get(position).getWant_price());
        holder.tvState.setText(contentList.get(position).getState());
        holder.tvBorough_name.setText(contentList.get(position).getBorough_name());
        holder.tvHouse_room.setText(contentList.get(position).getWant_room());
        holder.tvHouse_hall.setText(contentList.get(position).getWant_hall());
        holder.tvHouse_toilet.setText(contentList.get(position).getWant_toilet());


//        manager.loadUrlImage(contentList.get(position).getHouse_thumb(), holder.img);
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
        //id：委托主键、house_title:委托标题、want_price：期望价格、state：状态、borough_name：小区名称、
        // 期望户型-室：want_room、期望户型-厅:want_hall、期望户型-卫:want_toilet
        public TextView tvId, tvHouse_title,tvWant_price,tvState,tvBorough_name,tvHouse_room,tvHouse_hall,tvHouse_toilet;
//        private ImageView img;
        private CheckBox mCheckBox;
        private LinearLayout layout;
    }
}
