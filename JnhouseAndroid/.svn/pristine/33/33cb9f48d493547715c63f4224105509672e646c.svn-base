package com.topwellsoft.jnhouse_android.realtime_order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import jnhouse.topwellsoft.com.jnhouse_android.R;

/**
 * Created by Administrator on 2016/8/8.
 */
public class LoactionAdapter extends BaseAdapter {
    private ArrayList<Map<String, String>> list;
    private Context context;
    private LayoutInflater inflater;

    public LoactionAdapter(Context context, ArrayList<Map<String, String>> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Map getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.search_listitem_layout, null);
        }
        holder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
        holder.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
        holder.tv_address.setText(list.get(position).get("addr"));
        holder.tv_desc.setText(list.get(position).get("desc"));
        return convertView;
    }

    class ViewHolder {
        public TextView tv_address;
        public TextView tv_desc;
    }
}
