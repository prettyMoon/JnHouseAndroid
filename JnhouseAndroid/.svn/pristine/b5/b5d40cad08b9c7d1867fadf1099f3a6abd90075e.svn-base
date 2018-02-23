package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.manage.ImageManager;
import jnhouse.topwellsoft.com.jnhouse_android.model.CollectionEntity;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

/**
 * Created by topwellsoft on 2016/7/4.
 */
public class CollectionListViewAdapter extends BaseAdapter{
    private  List<CollectionEntity> contentList;
    private Context context;
    private LayoutInflater inflater;
    private ImageManager manager;
    /*private HashMap<Integer, View> mView ;
    public  HashMap<Integer, Integer> visiblecheck ;
    public  HashMap<Integer, Boolean> ischeck;*/
    public ArrayList<String> getIsSelect() {
        return isSelect;
    }

    private ArrayList<String> isSelect;
    private boolean canShown = false;
    private int i = 0;

    public CollectionListViewAdapter(Context context, List<CollectionEntity> contentList) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        // 正常数据
        Log.i("####","getView");
        final ListViewHolder holder = new ListViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_view_collection_item, null);
            Log.i("#####","getView_if  "+position);
        }

        holder.layout = (LinearLayout) convertView.findViewById(R.id.layout_del);
        holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.collection_checkbox);
        holder.img = (ImageView) convertView.findViewById(R.id.collection_thumb_iv);
        holder.tvTitle = (TextView) convertView.findViewById(R.id.collection_title_tv);
        holder.tvRoom = (TextView) convertView.findViewById(R.id.collection_room_tv);
        holder.tvArea = (TextView) convertView.findViewById(R.id.collection_area_tv);
        holder.tvToward = (TextView) convertView.findViewById(R.id.collection_toward_tv);
        holder.tvPrice = (TextView) convertView.findViewById(R.id.collection_price_tv);
        holder.tvBorough = (TextView) convertView.findViewById(R.id.collection_borough_tv);
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

    class ListViewHolder {
        public TextView tvTitle, tvRoom, tvArea,tvToward,tvPrice,tvBorough;
        private ImageView img;
        private CheckBox mCheckBox;
        private LinearLayout layout;
    }

}
