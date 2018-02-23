package jnhouse.topwellsoft.com.jnhouse_android.view;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import jnhouse.topwellsoft.com.jnhouse_android.R;

/**
 * Created by Administrator on 16-5-22.
 */
public class HeaderDividerViewView extends HeaderViewInterface<String>  {

    public HeaderDividerViewView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(String s, ListView listView) {
        View view = mInflate.inflate(R.layout.header_divider_layout, listView, false);
        listView.addHeaderView(view);
    }
}
