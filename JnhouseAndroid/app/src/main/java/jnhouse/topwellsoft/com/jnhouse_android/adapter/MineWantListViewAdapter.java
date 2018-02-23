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
import jnhouse.topwellsoft.com.jnhouse_android.model.MineWantEntity;

/**
 * Created by topwellsoft on 2016/7/14.
 */
public class MineWantListViewAdapter extends BaseAdapter {
    private List<MineWantEntity> contentList;
    private Context context;
    private LayoutInflater inflater;

    public ArrayList<String> getIsSelect() {
        return isSelect;
    }

    private ArrayList<String> isSelect;
    private boolean canShown = false;

    public MineWantListViewAdapter(Context context, List<MineWantEntity> contentList) {
        isSelect = new ArrayList<String>();
        this.contentList = contentList;
        this.context = context;
        inflater = LayoutInflater.from(context);
//        manager = new ImageManager(context);
    }

    public MineWantListViewAdapter() {
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
            convertView = inflater.inflate(R.layout.list_view_mine_want_item, null);
            Log.i("#####","getView_if  "+position);
        }

        holder.layout = (LinearLayout) convertView.findViewById(R.id.layout_del);
        holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.wantlist_checkbox);
//        holder.img = (ImageView) convertView.findViewById(R.id.collection_thumb_iv);
        holder.tvId = (TextView) convertView.findViewById(R.id.mine_want_id_tv);
        holder.tvState = (TextView) convertView.findViewById(R.id.mine_want_state_tv);
        holder.tvHouse_hall = (TextView) convertView.findViewById(R.id.mine_want_house_hall_tv);
        holder.tvHouse_room = (TextView) convertView.findViewById(R.id.mine_want_house_room_tv);
        holder.tvHouse_toilet = (TextView) convertView.findViewById(R.id.mine_want_house_toilet_tv);
        holder.tvWant_money = (TextView) convertView.findViewById(R.id.mine_want_house_price_tv);
        holder.tvTitle = (TextView) convertView.findViewById(R.id.mine_want_house_title_tv);
        holder.tvBorough = (TextView) convertView.findViewById(R.id.mine_want_borough_name_tv);

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
        holder.tvWant_money.setText(contentList.get(position).getWant_money());
        holder.tvTitle.setText(contentList.get(position).getTitle());
        holder.tvBorough.setText(contentList.get(position).getBorough());


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
        //id:主键、state:委托状态、house_hall:户型-厅、house_room:户型-室、house_toilet:户型-卫、
        // want_money:期望租金、title:委托标题、borough:小区名称
        public TextView tvId,tvState, tvHouse_hall, tvHouse_room,tvHouse_toilet,tvWant_money,tvTitle,tvBorough;
//        private ImageView img;
        private CheckBox mCheckBox;
        private LinearLayout layout;
    }
}
