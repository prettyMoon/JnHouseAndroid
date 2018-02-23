package jnhouse.topwellsoft.com.jnhouse_android.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import jnhouse.topwellsoft.com.jnhouse_android.R;


public class ModifyAvatarDialog extends Dialog implements View.OnClickListener{

	private LayoutInflater factory;
	private Button mImg;
	private Button mPhone;
	private Button mCancel;

	public ModifyAvatarDialog(Context context) {
		this(context, R.style.transparentFrameWindowStyle);
		factory = LayoutInflater.from(context);
	}
	
	public ModifyAvatarDialog(Context context, int theme) {
		super(context, theme);
		factory = LayoutInflater.from(context);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo_choose_dialog);
		mImg = (Button) this.findViewById(R.id.gl_choose_img);
		mPhone = (Button) this.findViewById(R.id.gl_choose_phone);
		mCancel = (Button) this.findViewById(R.id.gl_choose_cancel);
		
		Window window = this.getWindow();
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
//		wl.y = getWindowManager().getDefaultDisplay().getHeight();
		// 以下这两句是为了保证按钮可以水平满屏
		wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

		// 设置显示位置
		this.onWindowAttributesChanged(wl);
		window.setGravity(Gravity.BOTTOM);
		
		mImg.setOnClickListener(this);
		mPhone.setOnClickListener(this);
		mCancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.gl_choose_img:
			doGoToImg();
			break;
		case R.id.gl_choose_phone:
			doGoToPhone();
			break;
		case R.id.gl_choose_cancel:
			dismiss();
			break;
		}
	}
	
	public void doGoToImg(){
		
	}
	public void doGoToPhone(){
		
	}
}
