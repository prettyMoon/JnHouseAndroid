package jnhouse.topwellsoft.com.jnhouse_android.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.topwellsoft.androidutils.PreferencesUtils;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.circleimageview.CircleImageView;
import jnhouse.topwellsoft.com.jnhouse_android.ui.decoration.Decoration;
import jnhouse.topwellsoft.com.jnhouse_android.ui.houseloan.MainActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.login.TpLoginFragmentActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.mine.Attention;
import jnhouse.topwellsoft.com.jnhouse_android.ui.mine.Browse;
import jnhouse.topwellsoft.com.jnhouse_android.ui.mine.Friend;
import jnhouse.topwellsoft.com.jnhouse_android.ui.mine.Information;
import jnhouse.topwellsoft.com.jnhouse_android.ui.mine.MyLease;
import jnhouse.topwellsoft.com.jnhouse_android.util.Application.Configs;
/**
 * Created by admin on 2016/5/23.
 */
public class TpMineFragment extends Fragment {
    private TextView mTextView_infomation,mTextView_attention,mTextView_entrust,mTextView_browse,mTextView_decoration,mTextView_mall,mTextView_friend,mTextView_calculator;
    private ImageButton mImageButton_setting,mImageButton_back;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int PICK_IMAGE_ACTIVITY_REQUEST_CODE = 200;
    private static final String tag = "mine";

    private CircleImageView imageView;
//    private LoginEntity userInfo;
    private LinearLayout layout_confirmation;

    private Button mButton_relogin;
    private Button mButton_exit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tp_mine_fragmen, container,
                false);
        imageView = (CircleImageView) view.findViewById(R.id.mine_head_portrait_iv);
        mTextView_infomation = (TextView) view.findViewById(R.id.mine_infomation_tv);
        mTextView_attention = (TextView) view.findViewById(R.id.mine_attention_tv);
        mTextView_entrust = (TextView) view.findViewById(R.id.mine_entrust_tv);
        mTextView_browse = (TextView) view.findViewById(R.id.mine_browse_tv);
        mTextView_decoration = (TextView) view.findViewById(R.id.mine_decoration_tv);
        mTextView_mall = (TextView) view.findViewById(R.id.mine_mall_tv);
        mTextView_friend = (TextView) view.findViewById(R.id.mine_friend_tv);
        mTextView_calculator = (TextView) view.findViewById(R.id.mine_calculator_tv);

//        mImageButton_setting = (ImageButton) view.findViewById(R.id.mine_setting_ib);
        mImageButton_back = (ImageButton) view.findViewById(R.id.mine_back_ib);

        layout_confirmation = (LinearLayout) view.findViewById(R.id.layout_confirmation);

//        mButton_relogin = (Button) view.findViewById(R.id.mine_relogin_btn);
        mButton_exit = (Button) view.findViewById(R.id.mine_exit_btn);

        final Intent intent = new Intent();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("hx_state", Activity.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("state", true)) {
            layout_confirmation.setVisibility(View.GONE);
        } else {
            layout_confirmation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                 intent.setClass(getActivity(), UsernameResetActivity.class);
                    startActivity(intent);
                }
            });
        }

        final LoginEntity info;

        info = (LoginEntity) PreferencesUtils.getObject(getActivity(), "loginEntity");
//        mTextView_infomation.setText(info.getUsername());
        if (info != null){
            mTextView_infomation.setText(info.getRealname());

        }else {
            mButton_exit.setVisibility(View.GONE);
        }
//        mButton_relogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PreferencesUtils.clearData(getActivity(),"loginEntity");
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), TpLoginFragmentActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//            }
//        });

        mButton_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferencesUtils.clearData(getActivity(),"loginEntity");
                Intent intent = new Intent();
                intent.setClass(getActivity(), MainTabsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                EMClient.getInstance().logout(true);
                startActivity(intent);
            }
        });

        mTextView_infomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginEntity info= (LoginEntity) PreferencesUtils.getObject(getActivity(), "loginEntity");
                if (info != null) {
//                    Intent intent = new Intent();
                    intent.setClass(getActivity(),Information.class);
                    intent.putExtra("REQUEST", "3");
                    intent.putExtra("CONTENT", mTextView_infomation.getText().toString());
                    intent.putExtra("AParams", "userName");
                    startActivityForResult(intent, Configs.RESULT_REQUEST_CODE_03);
//                    startActivity(intent);
//                    getActivity().finish();

                } else {
//                    Intent intent = new Intent();
                    intent.setClass(getActivity(), TpLoginFragmentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
//                    getActivity().finish();
                }
            }
        });

        mTextView_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginEntity info= (LoginEntity) PreferencesUtils.getObject(getActivity(), "loginEntity");
                if (info != null) {
                    intent.setClass(getActivity(), Attention.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
//                    getActivity().finish();

                } else {
                    intent.setClass(getActivity(), TpLoginFragmentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
//                    getActivity().finish();
                }
            }
        });
        mTextView_entrust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginEntity info= (LoginEntity) PreferencesUtils.getObject(getActivity(), "loginEntity");
                if (info != null) {
                    intent.setClass(getActivity(), MyLease.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
//                    getActivity().finish();

                } else {
                    intent.setClass(getActivity(), TpLoginFragmentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
//                    getActivity().finish();
                }
            }
        });
        mTextView_browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginEntity info= (LoginEntity) PreferencesUtils.getObject(getActivity(), "loginEntity");
                if (info != null) {
                    intent.setClass(getActivity(), Browse.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
//                    getActivity().finish();

                } else {
                    intent.setClass(getActivity(), TpLoginFragmentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
//                    getActivity().finish();
                }
            }
        });
        mTextView_decoration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(getActivity(), Decoration.class);
                startActivity(intent);
            }
        });
        mTextView_mall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                intent.setClass(getActivity(), Decoration.class);
//                startActivity(intent);
            }
        });
        mTextView_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(getActivity(), Friend.class);
                startActivity(intent);
            }
        });
        mTextView_calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             intent.setClass(getActivity(), jnhouse.topwellsoft.com.jnhouse_android.ui.houseloan.MainActivity.class);

             intent.setClass(getActivity(), loan_counter.class);

             intent.setClass(getActivity(),MainActivity.class);
               startActivity(intent);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginEntity info= (LoginEntity) PreferencesUtils.getObject(getActivity(), "loginEntity");
                if (info != null) {

                    Intent intent = new Intent();
                    intent.setClass(getActivity(),Information.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
//                    getActivity().finish();

                } else {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), TpLoginFragmentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
//                    getActivity().finish();
                }
            }
        });
//        mImageButton_setting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                intent.setClass(getActivity(), SettingActivity.class);
//                startActivity(intent);
//            }
//        });
        mImageButton_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        if (info != null){
            setImageBitmap(imageView);
        }
        return view;
    }

    public void setImageBitmap(ImageView imageView) {

        String myJpgPath = Environment.getExternalStorageDirectory()
                + Configs.saveHeadName;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bm = BitmapFactory.decodeFile(myJpgPath, options);
        if (bm != null) {
            imageView.setImageBitmap(bm);
        } else {
            imageView.setBackgroundResource(R.drawable.ic_launcher);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        final LoginEntity info = (LoginEntity) PreferencesUtils.getObject(getActivity(), "loginEntity");

        /*if (!"".equals(info.getRealname())) {
            mTextView_infomation.setText(info.getRealname());
        } else {
            mTextView_infomation.setText("");
        }*/
        if (info != null) {
            if (info.getRealname()!=null&&!"".equals(info.getRealname())) {
                mTextView_infomation.setText(info.getRealname());
            } else {
                mTextView_infomation.setText("点击设置昵称");
            }
            setImageBitmap(imageView);
        }

//        setImageBitmap(imageView);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case Configs.RESULT_REQUEST_CODE_03:
                LoginEntity userInfo = new LoginEntity();
                mTextView_infomation.setText(data.getExtras().getString("RESULT_REQUEST"));
                userInfo.setRealname(data.getExtras().getString("RESULT_REQUEST"));

                PreferencesUtils.putObject(getActivity(), "LoginEntity", userInfo);
                break;
        }
    }
}
