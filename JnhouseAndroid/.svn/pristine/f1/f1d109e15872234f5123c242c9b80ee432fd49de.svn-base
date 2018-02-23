package jnhouse.topwellsoft.com.jnhouse_android.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import jnhouse.topwellsoft.com.jnhouse_android.R;

public class CustomProgressDialog extends Dialog{
	
	public CustomProgressDialog(Context context, String strMessage) {  
        this(context, R.style.loading_dialog, strMessage);
    }  
  
    public CustomProgressDialog(Context context, int theme, String strMessage) {  
        super(context, theme);  
        this.setContentView(R.layout.progress_dialog);
        this.getWindow().getAttributes().gravity = Gravity.CENTER;  
        TextView tipTextView  = (TextView) this.findViewById(R.id.tipTextView);  
        ImageView spaceshipImage = (ImageView)this.findViewById(R.id.image);
        // 加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(  
                context, R.anim.progress_medium);
        // 使用ImageView显示动画
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);  
        tipTextView.setText(strMessage);// 设置加载信息
    }  
}
