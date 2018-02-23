package com.topwellsoft.baidumap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

public class CircleOverlay extends TextView {
    private BubbleDrawable bubbleDrawable;


    public CircleOverlay(Context context) {
        super(context);
        this.setTextSize(10);
        this.setTextColor(Color.WHITE);
        this.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        initView(null);
    }

    public CircleOverlay(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public CircleOverlay(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setARGB(155, 255, 106, 106);

        mPaint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 60, mPaint);
        super.onDraw(canvas);
    }


}
