package jnhouse.topwellsoft.com.jnhouse_android.ui.release;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by topwellsoft on 2016/7/20.
 */
public class GridViewDefined extends GridView {
    public GridViewDefined(Context context) {
        super(context);
    }

    public GridViewDefined(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewDefined(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
