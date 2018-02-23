package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import jnhouse.topwellsoft.com.jnhouse_android.R;

/**
 * Created by Administrator on 2016/7/29.
 */
public class SearchHistoryAdapter extends BaseAdapter {
    private ArrayList<String> list;
    private Context context;
    private LayoutInflater inflater;

    public SearchHistoryAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
        if (!list.get(0).equals("暂无搜索记录")) list.add("清除搜索记录");
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_history, null);

        }
        holder.tv_list = (TextView) convertView.findViewById(R.id.history_item);
        if (position == list.size() - 1) {
            holder.tv_list.setText(list.get(position));
        } else {
            holder.tv_list.setText(list.get(list.size()-2-position));
        }


        return convertView;
    }

    class ViewHolder {
        public TextView tv_list;
    }
}
