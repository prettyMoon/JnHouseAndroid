package jnhouse.topwellsoft.com.jnhouse_android.ui.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;

public class AboutActivity extends AppCompatActivity {
    private WebView mWebView;
    private WebSettings webSettings;
    private ImageView comeBack;
    private TextView midletitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about2);
        initView();
        initData();
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

    private void initData() {
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl(JnHouse_Record.About);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient());

    }

    private void initView() {
        mWebView= (WebView) findViewById(R.id.about_webview);
        comeBack= (ImageView) findViewById(R.id.question_img_back);
        webSettings=mWebView.getSettings();
        midletitle= (TextView) findViewById(R.id.tv_middle);
        midletitle.setText("关于我们");
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()){
            mWebView.goBack();
        }else {
        super.onBackPressed();}
    }
}
