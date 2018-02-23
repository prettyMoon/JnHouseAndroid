package jnhouse.topwellsoft.com.jnhouse_android.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import jnhouse.topwellsoft.com.jnhouse_android.R;


public class Main2Activity extends Activity {


    private WebView webViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         webViews = new WebView(this);

        WebSettings settings = webViews.getSettings();
        settings.setJavaScriptEnabled(true);
        webViews.loadUrl("http://192.168.0.126:8080/jnhouseweb/mobile/shop.html?pdi=253937");
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        //设置WebView可触摸放大缩小
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);


        webViews.setWebChromeClient(new WebChromeClient() {
                                       @Override
                                       public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                                           AlertDialog.Builder b2 = new AlertDialog.Builder(Main2Activity.this)
                                                   .setTitle("").setMessage(message)
                                                   .setPositiveButton("ok",
                                                           new AlertDialog.OnClickListener() {
                                                               @Override
                                                               public void onClick(DialogInterface dialog,
                                                                                   int which) {
                                                                   result.confirm();
                                                                   // MyWebView.this.finish();
                                                               }
                                                           });

                                           b2.setCancelable(false);
                                           b2.create();
                                           b2.show();
                                           return true;
                                       }
                                   });
                //设置Web视图
        webViews.setWebViewClient(new WebViewClient(){


                @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }


        });

        setContentView(webViews);
    }
}