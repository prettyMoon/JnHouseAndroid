package jnhouse.topwellsoft.com.jnhouse_android.view;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import jnhouse.topwellsoft.com.jnhouse_android.R;

/**
 * Created by admin on 2016/6/1.
 */
public class HeaderZinxunViewView extends HeaderViewInterface<String> {

    private LinearLayout zixun_ll;

    public HeaderZinxunViewView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(String s, ListView listView) {
        View view = mInflate.inflate(R.layout.tp_index_zixun_title, listView, false);
        listView.addHeaderView(view);
//        zixun_ll = (LinearLayout)view.findViewById(R.id.zixun_ll);
//        zixun_ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext,"AA",Toast.LENGTH_LONG).show();
//            }
//        });
    }
}
