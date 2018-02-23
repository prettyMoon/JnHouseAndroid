package jnhouse.topwellsoft.com.jnhouse_android.ui.release;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.AlbumGridViewAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.util.AlbumHelper;
import jnhouse.topwellsoft.com.jnhouse_android.util.Bimp;
import jnhouse.topwellsoft.com.jnhouse_android.util.ImageBucket;
import jnhouse.topwellsoft.com.jnhouse_android.util.ImageItem;
import jnhouse.topwellsoft.com.jnhouse_android.util.LeaseShareBimp;
import jnhouse.topwellsoft.com.jnhouse_android.util.PublicWay;
import jnhouse.topwellsoft.com.jnhouse_android.util.Res;

/**
 * Created by topwellsoft on 2016/7/29.
 */
public class LeaseShareAlbumActivity extends Activity {
    //显示手机里的所有图片的列表控件
    private GridView gridView;
    //当手机里没有图片时，提示用户没有图片的控件
    private TextView tv;
    //gridView的adapter
    private AlbumGridViewAdapter gridImageAdapter;
    //完成按钮
    private Button okButton;
    // 返回按钮
    private Button back;
    // 取消按钮
    private Button cancel;
    private Intent intent;
    // 预览按钮
    private Button preview;
    private Context mContext;
    private ArrayList<ImageItem> dataList;
    private AlbumHelper helper;
    public static List<ImageBucket> contentList;
    public static Bitmap bitmap;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Res.getLayoutID("plugin_camera_album"));
        PublicWay.activityList.add(this);
        mContext = this;
        //注册一个广播，这个广播主要是用于在GalleryActivity进行预览时，防止当所有图片都删除完后，再回到该页面时被取消选中的图片仍处于选中状态
        IntentFilter filter = new IntentFilter("data.broadcast.action");
        registerReceiver(broadcastReceiver, filter);
        bitmap = BitmapFactory.decodeResource(getResources(),Res.getDrawableID("plugin_camera_no_pictures"));
        init();
        initListener();
        //这个函数主要用来控制预览和完成按钮的状态
        isShowOkBt();
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            //mContext.unregisterReceiver(this);
            // TODO Auto-generated method stub
            gridImageAdapter.notifyDataSetChanged();
            finish();
        }
    };

    // 预览按钮的监听
    private class PreviewListener implements View.OnClickListener {
        public void onClick(View v) {
            if (LeaseShareBimp.tempSelectBitmap_leaseshare.size() > 0) {
                intent.putExtra("position", "1");
                intent.setClass(LeaseShareAlbumActivity.this, LeaseShareGalleryActivity.class);
                startActivity(intent);
            }
        }

    }

    // 完成按钮的监听
    private class AlbumSendListener implements View.OnClickListener {
        public void onClick(View v) {
            overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
            if (getIntent() != null){
                int id = getIntent().getIntExtra( "id" , 0);
                if (id == 0){
                    intent.putExtra("id",0);
                } else {
                    if (id == 1){
                        intent.putExtra("id",1);
                    }else {
                        if (id == 2){
                            intent.putExtra("id",2);
                        }
                    }
                }
            }
            intent.setClass(mContext, Lease.class);
            setResult(0,intent);
            startActivity(intent);
            finish();
        }

    }

    // 返回按钮监听
    private class BackListener implements View.OnClickListener {
        public void onClick(View v) {
            intent.setClass(LeaseShareAlbumActivity.this, LeaseShareImageFile.class);
            startActivity(intent);
        }
    }

    // 取消按钮的监听
    private class CancelListener implements View.OnClickListener {
        public void onClick(View v) {
            LeaseShareBimp.tempSelectBitmap_leaseshare.clear();
            if (getIntent() != null){
                int id = getIntent().getIntExtra( "id" , 0);
                if (id == 0){
                    intent.putExtra("id",0);
                } else {
                    if (id == 1){
                        intent.putExtra("id",1);
                    }else {
                        if (id == 2){
                            intent.putExtra("id",2);
                        }
                    }
                }
            }
            intent.setClass(mContext, Lease.class);
            startActivity(intent);
        }
    }

    // 初始化，给一些对象赋值
    private void init() {
        helper = AlbumHelper.getHelper();
        helper.init(getApplicationContext());

        contentList = helper.getImagesBucketList(false);
        dataList = new ArrayList<ImageItem>();
        for(int i = 0; i<contentList.size(); i++){
            dataList.addAll( contentList.get(i).imageList );
        }

        back = (Button) findViewById(Res.getWidgetID("back"));
        cancel = (Button) findViewById(Res.getWidgetID("cancel"));
        cancel.setOnClickListener(new CancelListener());
        back.setOnClickListener(new BackListener());
        preview = (Button) findViewById(Res.getWidgetID("preview"));
        preview.setOnClickListener(new PreviewListener());
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        gridView = (GridView) findViewById(Res.getWidgetID("myGrid"));
        gridImageAdapter = new AlbumGridViewAdapter(this,dataList,
                LeaseShareBimp.tempSelectBitmap_leaseshare);
        gridView.setAdapter(gridImageAdapter);
        tv = (TextView) findViewById(Res.getWidgetID("myText"));
        gridView.setEmptyView(tv);
        okButton = (Button) findViewById(Res.getWidgetID("ok_button"));
        okButton.setText(Res.getString("finish")+"(" + LeaseShareBimp.tempSelectBitmap_leaseshare.size()
                + "/"+PublicWay.num+")");
    }

    private void initListener() {

        gridImageAdapter
                .setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(final ToggleButton toggleButton,
                                            int position, boolean isChecked,Button chooseBt) {
                        if (LeaseShareBimp.tempSelectBitmap_leaseshare.size() >= PublicWay.num) {
                            toggleButton.setChecked(false);
                            chooseBt.setVisibility(View.GONE);
                            if (!removeOneData(dataList.get(position))) {
                                Toast.makeText(LeaseShareAlbumActivity.this, Res.getString("only_choose_num"),
                                        Toast.LENGTH_SHORT).show();
                            }
                            return;
                        }
                        if (isChecked) {
                            chooseBt.setVisibility(View.VISIBLE);
                            LeaseShareBimp.tempSelectBitmap_leaseshare.add(dataList.get(position));
                            okButton.setText(Res.getString("finish")+"(" + LeaseShareBimp.tempSelectBitmap_leaseshare.size()
                                    + "/"+PublicWay.num+")");

                        } else {
                            LeaseShareBimp.tempSelectBitmap_leaseshare.remove(dataList.get(position));
                            chooseBt.setVisibility(View.GONE);
                            okButton.setText(Res.getString("finish")+"(" + LeaseShareBimp.tempSelectBitmap_leaseshare.size() + "/"+PublicWay.num+")");
                        }
                        isShowOkBt();
                    }
                });

        okButton.setOnClickListener(new AlbumSendListener());

    }

    private boolean removeOneData(ImageItem imageItem) {
        if (LeaseShareBimp.tempSelectBitmap_leaseshare.contains(imageItem)) {
            LeaseShareBimp.tempSelectBitmap_leaseshare.remove(imageItem);
            okButton.setText(Res.getString("finish")+"(" +LeaseShareBimp.tempSelectBitmap_leaseshare.size() + "/"+PublicWay.num+")");
            return true;
        }
        return false;
    }

    public void isShowOkBt() {
        if (LeaseShareBimp.tempSelectBitmap_leaseshare.size() > 0) {
            okButton.setText(Res.getString("finish")+"(" + LeaseShareBimp.tempSelectBitmap_leaseshare.size() + "/"+PublicWay.num+")");
            preview.setPressed(true);
            okButton.setPressed(true);
            preview.setClickable(true);
            okButton.setClickable(true);
            okButton.setTextColor(Color.WHITE);
            preview.setTextColor(Color.WHITE);
        } else {
            okButton.setText(Res.getString("finish")+"(" + LeaseShareBimp.tempSelectBitmap_leaseshare.size() + "/"+PublicWay.num+")");
            preview.setPressed(false);
            preview.setClickable(false);
            okButton.setPressed(false);
            okButton.setClickable(false);
            okButton.setTextColor(Color.parseColor("#E1E0DE"));
            preview.setTextColor(Color.parseColor("#E1E0DE"));
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            intent.setClass(LeaseShareAlbumActivity.this, LeaseShareImageFile.class);
            startActivity(intent);
        }
        return false;

    }
    @Override
    protected void onRestart() {
        isShowOkBt();
        super.onRestart();
    }


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("ExitApp");
        this.registerReceiver(this.broadcastReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(this.broadcastReceiver);
    }

}
