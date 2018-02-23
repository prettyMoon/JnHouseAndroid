package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.model.SecondHouselyEntity;

/**
 * Created by admin on 2016/6/8.
 */
public class SecondHouselyAdapter extends BaseAdapter {

    private Context mContext;
    private List<SecondHouselyEntity> mList;
    private LayoutInflater tLayoutInflater = null;

    public SecondHouselyAdapter(Context context, List<SecondHouselyEntity> list) {

        this.mList = list;
        this.tLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addItemList(List<SecondHouselyEntity> lists) {
        mList.addAll(lists);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        // 暂无数据
        if (mList.size() == 0) {
            convertView = tLayoutInflater.inflate(R.layout.item_no_data_layout, null);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
            RelativeLayout rootView = ButterKnife.findById(convertView, R.id.rl_root_view);
            rootView.setLayoutParams(params);
            return convertView;
        }
        // 正常数据
        final ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = tLayoutInflater.inflate(R.layout.tp_index_second_house_ly_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        SecondHouselyEntity entity = mList.get(position);

        holder.tvUser.setText("用户 "+entity.getUsername());
        holder.tvContent.setText(entity.getContent());
        holder.tvDate.setText(entity.getAdd_time());
        return convertView;
    }

    static class ViewHolder {

        @Bind(R.id.second_house_ly_user)
        TextView tvUser;
        @Bind(R.id.second_house_ly_content)
        TextView tvContent;
        @Bind(R.id.second_house_ly_date)
        TextView tvDate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
