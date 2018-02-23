package jnhouse.topwellsoft.com.jnhouse_android.ui.calendar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.manage.ImageManager;
import jnhouse.topwellsoft.com.jnhouse_android.ui.chat.EmChatHelper;


/**
 * Created by Administrator on 2016/8/10.
 */
public class CalendarAdapter extends BaseAdapter {
    private ArrayList<CalendarEntity> list;
    private Context context;
    private LayoutInflater inflater;

    public CalendarAdapter(Context context, ArrayList<CalendarEntity> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CalendarEntity getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.calendar_listitem, null);
        }
        holder.tv_time = (TextView) convertView.findViewById(R.id.calendar_time);
        holder.tv_title = (TextView) convertView.findViewById(R.id.calendar_title);
        holder.tv_username = (TextView) convertView.findViewById(R.id.calendar_username);
        holder.img_userPic = (ImageView) convertView.findViewById(R.id.calendar_userPic);
        holder.img_chat = (ImageView) convertView.findViewById(R.id.calendar_chat);
        holder.img_call = (ImageView) convertView.findViewById(R.id.calendar_call);
        holder.img_message = (ImageView) convertView.findViewById(R.id.calendar_message);
        holder.tv_time.setText(list.get(position).getShow_time());
        holder.tv_title.setText(list.get(position).getOrder_title());
        holder.tv_username.setText(list.get(position).getBroker_name());
        new ImageManager(context).loadCircleImage(list.get(position).getBroker_pic(), holder.img_userPic);
        holder.img_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//发短信
                Uri smsToUri = Uri.parse("smsto:" + list.get(position).getBroker_phone());
                Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
                context.startActivity(intent);
            }
        });
        holder.img_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//打电话
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + list.get(position).getBroker_phone()));
                context.startActivity(intent);
            }
        });
        holder.img_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//打电话
                EmChatHelper.startChatActivity(context, list.get(position).getBroker_phone(), "notlist");
            }
        });
        return convertView;
    }

    class ViewHolder {
        public TextView tv_time, tv_title, tv_username;
        public ImageView img_userPic, img_chat, img_call, img_message;
    }
}
