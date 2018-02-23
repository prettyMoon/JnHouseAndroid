package jnhouse.topwellsoft.com.jnhouse_android.ui.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;

public class Friend extends AppCompatActivity implements View.OnClickListener{
    private ImageView mImageView;
    private WebView mWebView;
    private WebSettings mWebSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        initView();
        initListner();
        initdata();

    }

    private void initdata() {
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(JnHouse_Record.InvitFriend);
/*        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setUseWideViewPort(true);*/
/*        mWebSettings.setSupportZoom(true);
        mWebSettings.setDisplayZoomControls(true);
        mWebSettings.setSupportZoom(true);*/
    }

    private void initListner() {
        mImageView.setOnClickListener(this);
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.friend_back_iv);
        mWebView= (WebView) findViewById(R.id.friend_webview);
        mWebSettings=mWebView.getSettings();
    }

    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()){
            mWebView.goBack();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.friend_back_iv:
                onBackPressed();
                break;
        }
    }
}
