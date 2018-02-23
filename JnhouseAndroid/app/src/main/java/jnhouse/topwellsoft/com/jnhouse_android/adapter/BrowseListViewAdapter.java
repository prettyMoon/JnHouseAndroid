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
import jnhouse.topwellsoft.com.jnhouse_android.model.BrowseEntity;

/**
 * Created by topwellsoft on 2016/7/13.
 */
public class BrowseListViewAdapter extends BaseAdapter {
    private List<BrowseEntity> contentList;
    private Context context;
    private LayoutInflater inflater;
    private ImageManager manager;

    public ArrayList<String> getIsSelect() {
        return isSelect;
    }

    private ArrayList<String> isSelect;
    private boolean canShown = false;

    public BrowseListViewAdapter(Context context, List<BrowseEntity> contentList) {
        isSelect = new ArrayList<String>();
        this.contentList = contentList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        manager = new ImageManager(context);
        Log.i("#####","CollectionListViewAdapter");
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
//        holder.img = (ImageView) convertView.findViewById(R.id.collection_thumb_iv);
        final ListViewHolder holder = new ListViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_view_browse_item, null);
            Log.i("#####","getView_if  "+position);
        }

        holder.layout = (LinearLayout) convertView.findViewById(R.id.layout_del);
        holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.browse_checkbox);
        holder.img = (ImageView) convertView.findViewById(R.id.browse_thumb_iv);
        holder.tvTitle = (TextView) convertView.findViewById(R.id.browse_title_tv);
        holder.tvRoom = (TextView) convertView.findViewById(R.id.browse_room_tv);
        holder.tvArea = (TextView) convertView.findViewById(R.id.browse_area_tv);
        holder.tvToward = (TextView) convertView.findViewById(R.id.browse_toward_tv);
        holder.tvPrice = (TextView) convertView.findViewById(R.id.browse_price_tv);
        holder.tvBorough = (TextView) convertView.findViewById(R.id.browse_borough_tv);
        if (canShown) {
            holder.layout.setVisibility(View.VISIBLE);

        } else {
            holder.layout.setVisibility(View.GONE);
        }
        holder.tvTitle.setText(contentList.get(position).getHouse_title());
        holder.tvRoom.setText(contentList.get(position).getHouse_room());
        holder.tvArea.setText(contentList.get(position).getHouse_area());
        holder.tvToward.setText(contentList.get(position).getHouse_toward());
        holder.tvPrice.setText(contentList.get(position).getHouse_price());
        holder.tvBorough.setText(contentList.get(position).getHouse_borough());
        manager.loadUrlImage(contentList.get(position).getHouse_thumb(), holder.img);
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
        public TextView tvTitle, tvRoom, tvArea,tvToward,tvPrice,tvBorough;
        private ImageView img;
        private CheckBox mCheckBox;
        private LinearLayout layout;
    }
}
