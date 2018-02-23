package jnhouse.topwellsoft.com.jnhouse_android.ui.financial;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.topwellsoft.androidutils.StyleUtils;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;

public class FinancialEnd extends Activity {

    private WebView webView;
    private ImageView comeBack;
    private TextView textViewTitle;
    private WebSettings webSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StyleUtils.initStatusBar(this.getWindow());
        setContentView(R.layout.activity_financial_end);
        initView();
        initData();
        setData();
        setListner();

    }

    private void setListner() {
        comeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setData() {
     /* webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);*/
        webSettings = webView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setAppCacheEnabled(true);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/gefdemoweb";
        Log.e(null, path);
        webSettings.setAppCachePath(path);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadsImagesAutomatically(true);

    }

    private void initData() {
        textViewTitle.setText("金融");
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(JnHouse_Record.Financial);

    }


    private void initView() {
        webView = (WebView) findViewById(R.id.financialEnd_webView);
        comeBack = (ImageView) findViewById(R.id.consult_img_back);
        textViewTitle = (TextView) findViewById(R.id.tv_consult_title);

    }
}
