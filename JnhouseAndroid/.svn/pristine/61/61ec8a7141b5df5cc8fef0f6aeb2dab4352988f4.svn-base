package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.manage.ImageManager;
import jnhouse.topwellsoft.com.jnhouse_android.model.AgentEntity;

/**
 * Created by Administrator on 2016/7/14.
 */
public class AgentListAdapter extends BaseAdapter {
    private ArrayList<AgentEntity> list;
    private Context context;
    private LayoutInflater inflater;
    private ImageManager manager;

    public AgentListAdapter(Context context, ArrayList<AgentEntity> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        manager = new ImageManager(this.context);
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
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.agent_listitem, null);
        }
        holder.tv_address = (TextView) convertView.findViewById(R.id.agent_address);
        // holder.tv_job = (TextView) convertView.findViewById(R.id.agent_job);
        holder.tv_name = (TextView) convertView.findViewById(R.id.agent_name);
        holder.tv_num = (TextView) convertView.findViewById(R.id.agent_evaluateNum);
        holder.tv_rate = (TextView) convertView.findViewById(R.id.agent_rate);
        holder.img_headicon = (ImageView) convertView.findViewById(R.id.agent_headicon);
        //填充数据
        if(list.get(position).getCompany_name().equals("")){
            holder.tv_address.setText("未知");

        }else{
            holder.tv_address.setText(list.get(position).getCompany_name());
        }
        //holder.tv_job.setText(list.get(position).get);
        holder.tv_name.setText(list.get(position).getRealname());
        holder.tv_num.setText(list.get(position).getPfsum());
        holder.tv_rate.setText(list.get(position).getZh_p() + "%");
        manager.loadCircleImage(list.get(position).getAvatar(), holder.img_headicon);
        return convertView;
    }


    class ViewHolder {
        public TextView tv_name, tv_job, tv_address, tv_rate, tv_num;
        public ImageView img_headicon;
    }
}
