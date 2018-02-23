package jnhouse.topwellsoft.com.jnhouse_android.view;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import jnhouse.topwellsoft.com.jnhouse_android.R;

/**
 * Created by admin on 2016/6/1.
 */
public class HeaderAvgViewView  extends HeaderViewInterface<String> {

    private TextView index_average;
    private TextView index_strike;

    public HeaderAvgViewView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(String s, ListView listView) {
        View view = mInflate.inflate(R.layout.tp_index_house_aide, listView, false);
        index_average = (TextView) view.findViewById(R.id.index_average);
        index_strike = (TextView) view.findViewById(R.id.index_strike);
        listView.addHeaderView(view);
    }

    public void setData(float average, float strike){

        NumAnim.startAnim(index_average, average);
        NumAnim.startAnim(index_strike, strike);
    }
}
