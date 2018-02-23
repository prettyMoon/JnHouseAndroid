package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.model.OperationEntity;

/**
 * Created by Administrator on 16-5-22.
 */
public class HeaderOperationAdapter extends BaseListAdapter<OperationEntity> {

    public HeaderOperationAdapter(Context context) {
        super(context);
    }

    public HeaderOperationAdapter(Context context, List<OperationEntity> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_operation, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        OperationEntity entity = getItem(position);
        mImageManager.loadUrlImage(entity.getImage_url(), holder.ivImage);
        if (TextUtils.isEmpty(entity.getTitle())) {
            holder.tvSubtitle.setVisibility(View.INVISIBLE);
        } else {
            holder.tvSubtitle.setVisibility(View.VISIBLE);
            holder.tvSubtitle.setText(entity.getTitle());
        }

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_image)
        ImageView ivImage;
        @Bind(R.id.tv_subtitle)
        TextView tvSubtitle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
