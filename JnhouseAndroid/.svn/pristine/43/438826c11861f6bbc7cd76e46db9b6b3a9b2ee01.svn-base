package jnhouse.topwellsoft.com.jnhouse_android.ui.circleimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Jan on 2015/1/19.
 */
public class MyRoundImageView extends ImageView {
    public MyRoundImageView(Context context) {
        super(context);
    }

    public MyRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Paint paint = new Paint();  {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GRAY);
        paint.setAntiAlias(true);
    }
    private Canvas myCanvas=new Canvas();
    private Canvas canvasRound=new Canvas();
    private PorterDuffXfermode src= new PorterDuffXfermode(PorterDuff.Mode.SRC);
    private PorterDuffXfermode src_In= new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private RectF rectF;
    private float round=10;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF=new RectF(0,0,w,h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(rectF==null)
            rectF= new RectF(0, 0, getWidth(), getHeight());

        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        myCanvas.setBitmap(bitmap);
        super.onDraw(myCanvas);

        Bitmap bitmap1= Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        canvasRound.setBitmap(bitmap1);

        paint.setXfermode(src);
        canvasRound.drawRoundRect(rectF, round, round, paint);
        paint.setXfermode(src_In);
        canvasRound.drawBitmap(bitmap,0,0, paint);

        canvas.drawBitmap(bitmap1, 0,0, null);

        bitmap1.recycle();
        bitmap1=null;
        bitmap.recycle();
        bitmap=null;
    }

    public void setRound(float round) {
        this.round = round;
    }
}
