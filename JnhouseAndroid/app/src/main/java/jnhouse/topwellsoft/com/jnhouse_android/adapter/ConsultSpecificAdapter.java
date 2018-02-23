package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.manage.ImageManager;
import jnhouse.topwellsoft.com.jnhouse_android.model.SpecificEntity;

/**
 * Created by Administrator on 2016/7/5.
 */
public class ConsultSpecificAdapter extends BaseAdapter {

    private ArrayList<SpecificEntity> contentList;
    private Context context;
    private LayoutInflater inflater;
    private ImageManager manager;

    public ConsultSpecificAdapter(Context context, ArrayList<SpecificEntity> contentList) {
        this.contentList = contentList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        manager = new ImageManager(context);
    }


    @Override
    public int getCount() {
        return contentList.size();
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

        ListViewHolder holder = new ListViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_consult_lv, null);
        }

        holder.img = (ImageView) convertView.findViewById(R.id.lv_img);
        holder.tv_content = (TextView) convertView.findViewById(R.id.lv_tv_content);
        holder.tv_time = (TextView) convertView.findViewById(R.id.lv_tv_time);
        holder.tv_author = (TextView) convertView.findViewById(R.id.lv_tvweather);
        holder.tv_content.setText(contentList.get(position).getTitle());
        holder.tv_author.setText(contentList.get(position).getAuthor());
        holder.tv_time.setText(contentList.get(position).getTime());
        manager.loadUrlImage(contentList.get(position).getThumb(), holder.img);
        return convertView;
    }

    class ListViewHolder {
        public TextView tv_content, tv_author, tv_time;
        private ImageView img;
    }
}
