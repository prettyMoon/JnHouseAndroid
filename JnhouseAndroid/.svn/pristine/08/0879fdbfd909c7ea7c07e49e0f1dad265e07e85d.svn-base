package jnhouse.topwellsoft.com.jnhouse_android.view;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.HeaderOperationAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.model.OperationEntity;

/**
 * Created by Administrator on 16-5-22.
 */
public class HeaderOperationViewView extends HeaderViewInterface<List<OperationEntity>> {

    @Bind(R.id.gv_operation)
    FixedGridView gvOperation;

    public HeaderOperationViewView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(List<OperationEntity> list, ListView listView) {
        View view = mInflate.inflate(R.layout.header_operation_layout, listView, false);
        ButterKnife.bind(this, view);

        dealWithTheView(list);
        listView.addHeaderView(view);
    }

    private void dealWithTheView(List<OperationEntity> list) {
        HeaderOperationAdapter adapter = new HeaderOperationAdapter(mContext, list);
        gvOperation.setAdapter(adapter);

    }

}
