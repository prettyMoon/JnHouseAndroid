package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.model.CommunityNameSearchEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.SearchCommutityEntity;

/**
 * Created by topwellsoft on 2016/7/11.
 */
public class ChooseCommunityAdapter extends BaseAdapter {
    private List<CommunityNameSearchEntity> mList;
    private LayoutInflater mInflater;

    public ChooseCommunityAdapter(Context context,List<CommunityNameSearchEntity> mList){
        this.mList = mList;
        mInflater = LayoutInflater.from(context);
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

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.layout_search_community_item,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTextView_community.setText(mList.get(i).getBorough().toString());
        return convertView;
    }


    //ViewHolder
    private  class ViewHolder{
        TextView mTextView_community;

        public ViewHolder(View view) {
            mTextView_community = (TextView) view.findViewById(R.id.search_community_name_tv);

        }
    }
}
