package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.manage.ImageManager;
import jnhouse.topwellsoft.com.jnhouse_android.model.HomeZXEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.ViewHolder;

/**
 * Created by admin on 2016/5/27.
 */
public class IndexZXListAdapter extends  BaseListAdapter {

    private List<HomeZXEntity> mList;
    private LayoutInflater tLayoutInflater = null;
    private Context mContext;
    protected ImageManager mImageManager;

    public IndexZXListAdapter(Context context, List<HomeZXEntity> list) {
        super(context, list);
        this.mList = list;
        this.mContext = context;
        this.tLayoutInflater = LayoutInflater.from(context);
        mImageManager = new ImageManager(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public HomeZXEntity getItem(int arg0) {
        return mList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    public void addItemList(List<HomeZXEntity> list) {
        mList.addAll(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView != null){
            holder = (ViewHolder) convertView.getTag();
        }else{
            holder = new ViewHolder();
            convertView = tLayoutInflater.inflate(R.layout.tp_index_house_zxlist_item, parent, false);
            holder.index_title = (TextView) convertView.findViewById(R.id.index_house_zxlist_item_tv);
            holder.index_thumb = (ImageView) convertView.findViewById(R.id.index_house_zxlist_item_img);
            convertView.setTag(holder);
        }
        holder.index_title.setText(mList.get(position).getTitle());
        mImageManager.loadUrlImage(mList.get(position).getThumb(),holder.index_thumb);

        return convertView;
    }
}
