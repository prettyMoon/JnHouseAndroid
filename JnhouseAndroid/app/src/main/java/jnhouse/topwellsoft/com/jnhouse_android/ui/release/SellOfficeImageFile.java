package jnhouse.topwellsoft.com.jnhouse_android.ui.release;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import jnhouse.topwellsoft.com.jnhouse_android.adapter.FolderAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.util.LeaseShareBimp;
import jnhouse.topwellsoft.com.jnhouse_android.util.PublicWay;
import jnhouse.topwellsoft.com.jnhouse_android.util.Res;

/**
 * Created by topwellsoft on 2016/7/29.
 */
public class SellOfficeImageFile extends Activity {

    private FolderAdapter folderAdapter;
    private Button bt_cancel;
    private Context mContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Res.getLayoutID("plugin_camera_image_file"));
        PublicWay.activityList.add(this);
        mContext = this;
        bt_cancel = (Button) findViewById(Res.getWidgetID("cancel"));
        bt_cancel.setOnClickListener(new CancelListener());
        GridView gridView = (GridView) findViewById(Res.getWidgetID("fileGridView"));
        TextView textView = (TextView) findViewById(Res.getWidgetID("headerTitle"));
        textView.setText(Res.getString("photo"));
        folderAdapter = new FolderAdapter(this);
        gridView.setAdapter(folderAdapter);
    }

    private class CancelListener implements View.OnClickListener {// 取消按钮的监听
        public void onClick(View v) {
            //清空选择的图片
            LeaseShareBimp.tempSelectBitmap_leaseshare.clear();
            Intent intent = new Intent();
            intent.putExtra("id",1);
            intent.setClass(mContext, Sell.class);
            startActivity(intent);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.putExtra("id",1);
            intent.setClass(mContext, Sell.class);
            startActivity(intent);
        }

        return true;
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

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
