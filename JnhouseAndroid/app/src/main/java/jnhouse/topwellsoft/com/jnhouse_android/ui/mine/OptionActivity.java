package jnhouse.topwellsoft.com.jnhouse_android.ui.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.topwellsoft.androidutils.PreferencesUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;

public class OptionActivity extends AppCompatActivity {
    private ImageView comeback;
    private TextView middleTitle;
    private String stringContent;
    private EditText editText;
    private WebView webview;
    private WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        initView();
        setdata();
        setListner();
    }

    private void setdata() {
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient());
      /*  webview.addJavascriptInterface(this,);*/
        webview.loadUrl(JnHouse_Record.Opinion);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);



    }

    private void initData(String string) {
        AjaxParams params=new AjaxParams();
        LoginEntity entity= (LoginEntity) PreferencesUtils.getObject(this,"loginEntity");
        if(entity!=null){
            params.put("user_id",entity.getUser_id());
            params.put("user_name",entity.getUsername());
            params.put("phone",entity.getUsername());
        }else{
            params.put("user_id","");
            params.put("user_name","");
            params.put("phone","");
        }
        params.put("content",string);
        FinalHttp http=new FinalHttp();
        http.get(JnHouse_Record.Opinion, params, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {

                Toast.makeText(OptionActivity.this, s, Toast.LENGTH_SHORT).show();


 /*               Gson gson=new Gson();
                MyOption optionEntity=gson.fromJson(s,new TypeToken<MyOption>(){}.getType());
                int  code=optionEntity.getCode();
                switch (code){
                    case 0:
                        ToastUtil.makeText(OptionActivity.this, "反馈成功",
                                ToastUtil.LENGTH_SHORT)
                                .setAnimation(R.style.PopToast).show();
                        break;
                    case -1:
                        ToastUtil.makeText(OptionActivity.this,"反馈失败",
                                ToastUtil.LENGTH_SHORT)
                                .setAnimation(R.style.PopToast).show();
                        break;
                }
*/

            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                Toast.makeText(OptionActivity.this, "委托失败" + "\t" + errorNo + "\t" + strMsg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
            }
        });







    }

    private void initView() {
        comeback= (ImageView) findViewById(R.id.question_img_back);
        middleTitle= (TextView) findViewById(R.id.tv_middle);
        middleTitle.setText("意见反馈");
//        editText= (EditText) findViewById(R.id.suggestion_et);
        webview= (WebView) findViewById(R.id.webview_option);
        webSettings=webview.getSettings();
    }
    public void setListner(){
        comeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

  /*  public void onClick(View view) {
        if(view!=null){
            switch (view.getId()){
                case R.id.suggestion_commit_btn:
                    stringContent=editText.getText().toString().trim();
                    initData(stringContent);
                    break;
            }
        }
    }*/
}
