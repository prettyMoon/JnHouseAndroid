package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import net.tsz.afinal.http.AjaxCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.model.BaikeDataListEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.BaikeEntity;


/**
 * Created by Administrator on 2016/6/23.
 */
public class BaikeListAdapter extends BaseAdapter {
    private ArrayList<BaikeDataListEntity> list;
    private Context context;
    private LayoutInflater inflater;

    public BaikeListAdapter(Context context, ArrayList<BaikeDataListEntity> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.baike_listitem, null);
        }
        ViewHolder holder = new ViewHolder();
        holder.tv_title = (TextView) convertView.findViewById(R.id.baike_title);
        holder.tv_title.setText(list.get(position).getTitle());
        return convertView;
    }

    class ViewHolder {
        public TextView tv_title;
    }

}
