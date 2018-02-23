package com.topwellsoft.baidumap;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.topwellsoft.androidutils.GlobalObjects;


public class BaiduMapHelper {



    public static void initBaiduMap(Application theApplication) {

        SDKInitializer.initialize(theApplication);

        // IntentFilter iFilter = new IntentFilter();
        // iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
        // iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        // iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        // mReceiver = new SDKReceiver();
        //  registerReceiver(mReceiver, iFilter);
    }

    public static BitmapDescriptor compoundStringMarkerContent(String text) {
        BitmapDescriptor bdA = BitmapDescriptorFactory.fromBitmap(createBitmap(text));
        return bdA;
    }

    private static int width = 20;
    private static int height = 20;

    private static Bitmap createBitmap(String letter) {
        Bitmap
                imgTemp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(imgTemp);
        Paint paint = new Paint(); // 建立画笔
        paint.setDither(true);
        paint.setFilterBitmap(true);
        Rect src = new Rect(0, 0, width, height);
        Rect dst = new Rect(0, 0, width, height);
        canvas.drawBitmap(imgTemp, src, dst, paint);

        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG
                | Paint.DEV_KERN_TEXT_FLAG);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD); // 采用默认的宽度
        textPaint.setColor(Color.WHITE);
        // 调整字体在图片中的位置，此处根据分辨率来判断字体的大小了

        int scrHeigh = GlobalObjects.globalDM.heightPixels;
        if (scrHeigh > 1280) {
            textPaint.setTextSize(42.0f);
            canvas.drawText(String.valueOf(letter), width / 2 - 35,
                    height / 3 - 26, textPaint);
        } else {
            textPaint.setTextSize(25.0f);
            canvas.drawText(String.valueOf(letter), width / 2 - 27,
                    height / 3 - 20, textPaint);
        }
        canvas.save();
        canvas.restore();
        BitmapDrawable pic = new BitmapDrawable(GlobalObjects.theApplication.getResources(), imgTemp);

        return pic.getBitmap();

    }


    public static  Bitmap getViewBitmap(View addViewContent) {

        addViewContent.setDrawingCacheEnabled(true);

        addViewContent.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        addViewContent.layout(0, 0,
                addViewContent.getMeasuredWidth(),
                addViewContent.getMeasuredHeight());

        addViewContent.buildDrawingCache();
        Bitmap cacheBitmap = addViewContent.getDrawingCache();

        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

        return bitmap;
    }


}