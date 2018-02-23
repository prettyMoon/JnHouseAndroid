package jnhouse.topwellsoft.com.jnhouse_android.ui.release;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.PreferencesUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.BoroughDetail;
import jnhouse.topwellsoft.com.jnhouse_android.model.LeaseEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.util.Application.Configs;
import jnhouse.topwellsoft.com.jnhouse_android.util.Bimp;
import jnhouse.topwellsoft.com.jnhouse_android.util.EventCommunityUtil;
import jnhouse.topwellsoft.com.jnhouse_android.util.EventUtil;
import jnhouse.topwellsoft.com.jnhouse_android.util.FileUtils;
import jnhouse.topwellsoft.com.jnhouse_android.util.ImageItem;
import jnhouse.topwellsoft.com.jnhouse_android.util.PublicWay;
import jnhouse.topwellsoft.com.jnhouse_android.util.Res;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

/**
 * Created by topwellsoft on 2016/6/22.
 * 二手房出售
 */
public class SellSecond extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {
    private ImageView mImageButton_agent_one, mImageButton_agent_two, mImageButton_agent_three;
    private EditText mEditText_title, mEditText_location, mEditText_price, mEditText_measure, mEditText_name;
    private Spinner mSpinner_room, mSpinner_office, mSpinner_kitchen, mSpinner_toilet, mSpinner_decoration, mSpinner_orientation, mSpinner_balcony;
    private EditText mEditText_floor, mEditText_total, mEditText_age, mEditText_source, mEditText_contacts, mEditText_phone, mEditText_time_start, mEditText_time_end, mEditText_top;
    private CheckBox mCheckBox_choose;
    private Button mButton_release, mButton_description_community, mButton_choose_community;
    private Context mContext;
    private LinearLayout mLinearLayout;
    private TextView mTextView_room, mTextView_office, mTextView_kitchen, mTextView_toilet, mTextView_decoration, mTextView_orientation;
    private TextView mTextView_city, mTextView_cityarea, mTextView_cityarea2;
    private static final String tag = "SellSecond";
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int PICK_IMAGE_ACTIVITY_REQUEST_CODE = 200;


    private String city_id;
    private String cityarea_id;
    private String cityarea2_id;
    private String house_price;
    private String house_area;
    private String house_topfloor;
    private String house_floor;
    private String house_toward;
    private String house_fitment;
    private String house_age;
    private String house_title;
    private String borough_id;
    private String borough_name;
    private String house_desc;
    private String user_name;
    private String user_id;
    private String user_phone;
    private String tel_time_start;
    private String tel_time_end;
    private String house_room;
    private String house_hall;
    private String house_toilet;
    private List<String> borker_id = new ArrayList<String>();
//    private String rent_unite_type;

    private GridViewDefined noScrollgridview;
    private GridAdapter adapter;
    private View parentView;
    private PopupWindow pop = null;
    private LinearLayout ll_popup;
    public static Bitmap bimap;

    private ArrayList<String> sellPathList = new ArrayList<String>();
    private File updateDir = null;
    private String path = null;
    private ArrayList<String> di_value = new ArrayList<String>();
    private String[] facilities = new String[10];

    private String city;
    private String cityarea;
    private String cityarea2;

    private String borough_lng;
    private String borough_lat;

    private CheckBox mCheckBox_one;
    private CheckBox mCheckBox_two;
    private CheckBox mCheckBox_three;

    private List<String> broker_list = new ArrayList<String>();

    private boolean isChecked_1;
    private boolean isChecked_2;
    private boolean isChecked_3;

    private String broker1_id;
    private String broker2_id;
    private String broker3_id;
    private String getBroker1_name;
    private String getBroker2_name;
    private String getBroker3_name;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        Res.init(getActivity());
        bimap = BitmapFactory.decodeResource(
                getResources(),
                R.drawable.icon_addpic_unfocused);
        PublicWay.activityList.add(getActivity());
        parentView = getActivity().getLayoutInflater().inflate(R.layout.release_leaseshort, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.release_sellsecond, container, false);
        Init();
        noScrollgridview = (GridViewDefined) view.findViewById(R.id.noScrollgridview);
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new GridAdapter(getActivity());
        adapter.update();
        noScrollgridview.setAdapter(adapter);
        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == Bimp.tempSelectBitmap.size()) {
                    Log.i("ddddddd", "----------");
                    ll_popup.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.activity_translate_in));
                    pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
                } else {
                    Intent intent = new Intent(getActivity(),
                            SellGalleryActivity.class);
                    intent.putExtra("position", "1");
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });
//        mSpinner_balcony = (Spinner) view.findViewById(R.id.sellsecond_balcony_spinner);
        mEditText_top = (EditText) view.findViewById(R.id.sellsecond_total_et);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.sellsecond_checkbox_linearlayout);
//        mTextView_city = (TextView) view.findViewById(R.id.city_id_tv);
        mTextView_cityarea = (TextView) view.findViewById(R.id.cityarea_id_tv);
//        mTextView_cityarea2 = (TextView) view.findViewById(R.id.cityarea2_id_tv);
        mButton_choose_community = (Button) view.findViewById(R.id.sellsecond_choosecommunity_bt);
        mButton_description_community = (Button) view.findViewById(R.id.sellsecond_description_bt);
        mImageButton_agent_one = (ImageView) view.findViewById(R.id.sellsecond_agent_one_ib);
        mImageButton_agent_two = (ImageView) view.findViewById(R.id.sellsecond_agent_two_ib);
        mImageButton_agent_three = (ImageView) view.findViewById(R.id.sellsecond_agent_three_ib);
        mEditText_price = (EditText) view.findViewById(R.id.sellsecond_price_et);
        mEditText_measure = (EditText) view.findViewById(R.id.sellsecond_measure_et);
        mEditText_title = (EditText) view.findViewById(R.id.sellsecond_name_et);
        mSpinner_room = (Spinner) view.findViewById(R.id.sellsecond_room_spinner);
        mSpinner_office = (Spinner) view.findViewById(R.id.sellsecond_office_spinner);
//        mSpinner_kitchen = (Spinner) view.findViewById(R.id.sellsecond_kitchen_spinner);
        mSpinner_toilet = (Spinner) view.findViewById(R.id.sellsecond_toilet_spinner);
        mSpinner_decoration = (Spinner) view.findViewById(R.id.sellsecond_decoration_spinner);
        mSpinner_orientation = (Spinner) view.findViewById(R.id.sellsecond_orientation_spinner);
        mEditText_floor = (EditText) view.findViewById(R.id.sellsecond_floor_et);
        mEditText_total = (EditText) view.findViewById(R.id.sellsecond_total_et);
        mEditText_age = (EditText) view.findViewById(R.id.sellsecond_age_et);
        mEditText_source = (EditText) view.findViewById(R.id.sellsecond_source_et);
        mEditText_contacts = (EditText) view.findViewById(R.id.sellsecond_contacts_et);
        mEditText_phone = (EditText) view.findViewById(R.id.sellsecond_phone_et);
        mEditText_time_start = (EditText) view.findViewById(R.id.sellsecond_time_start_et);
        mEditText_time_end = (EditText) view.findViewById(R.id.sellsecond_time_end_et);
        mCheckBox_choose = (CheckBox) view.findViewById(R.id.sellsecond_choose_cb);
        mButton_release = (Button) view.findViewById(R.id.sellsecond_release_bt);

        final LoginEntity info;

        info = (LoginEntity) PreferencesUtils.getObject(getActivity(), "loginEntity");
        mEditText_contacts.setText(info.getRealname());
        mEditText_phone.setText(info.getUsername());

        mCheckBox_one = (CheckBox) view.findViewById(R.id.position1_checkbox);
        mCheckBox_two = (CheckBox) view.findViewById(R.id.position2_checkbox);
        mCheckBox_three = (CheckBox) view.findViewById(R.id.position3_checkbox);

        mCheckBox_one.setOnClickListener(this);
        mCheckBox_two.setOnClickListener(this);
        mCheckBox_three.setOnClickListener(this);

        mButton_choose_community.setOnClickListener(this);
        mButton_description_community.setOnClickListener(this);
        mCheckBox_choose.setOnClickListener(this);
        mButton_release.setOnClickListener(this);
//        mSpinner_balcony.setOnItemSelectedListener(this);
        mSpinner_room.setOnItemSelectedListener(this);
        mSpinner_office.setOnItemSelectedListener(this);
//        mSpinner_kitchen.setOnItemSelectedListener(this);
        mSpinner_toilet.setOnItemSelectedListener(this);
        mSpinner_decoration.setOnItemSelectedListener(this);
        mSpinner_orientation.setOnItemSelectedListener(this);
        mLinearLayout.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sellsecond_release_bt:
                for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
                    saveToLocal(Bimp.tempSelectBitmap.get(i).getBitmap(), i);
                }
                getPDAServerData();
                break;
            case R.id.sellsecond_description_bt:
                Intent intent_description = new Intent();
                intent_description.setClass(getActivity(), Communitydescription.class);
                startActivity(intent_description);
                break;
            case R.id.sellsecond_choosecommunity_bt:
                Intent intent = new Intent();
                intent.setClass(getActivity(), ChooseCommunity.class);
                startActivity(intent);
                break;
            case R.id.sellsecond_choose_cb:
                if (mCheckBox_choose.isChecked()) {
                    pushBrokerData();
                } else {
                    mLinearLayout.setVisibility(View.GONE);
                }
                mCheckBox_choose.setFocusable(true);
                mCheckBox_choose.setFocusableInTouchMode(true);
                mCheckBox_choose.requestFocus();
                mCheckBox_choose.requestFocusFromTouch();
                break;
            case R.id.position1_checkbox:
                isChecked_1 = !isChecked_1;
                if (isChecked_1) {
                    broker_list.add(broker1_id);
                } else {
                    broker_list.remove(broker1_id);
                }
                break;
            case R.id.position2_checkbox:
                isChecked_2 = !isChecked_2;
                if (isChecked_2) {
                    broker_list.add(broker2_id);
                } else {
                    broker_list.remove(broker2_id);
                }
                break;
            case R.id.position3_checkbox:
                isChecked_3 = !isChecked_3;
                if (isChecked_3) {
                    broker_list.add(broker3_id);
                } else {
                    broker_list.remove(broker3_id);
                }
                break;

        }
    }

    public void getPDAServerData() {
        try {
            LoginEntity info = (LoginEntity) PreferencesUtils.getObject(getActivity(), "loginEntity");
            if (info == null || info.getUserUUID() == null) {
                ToastUtil.makeText(this.getActivity(), "请重新登录",
                        ToastUtil.LENGTH_SHORT)
                        .setAnimation(R.style.PopToast).show();
                return;
            }
            AjaxParams params = new AjaxParams();
            params.put("userUUID", info.getUserUUID());
            for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
                params.put("pictures", new File(sellPathList.get(i)));
            }
            params.put("city_id", city);
            params.put("cityarea_id", cityarea);
            params.put("cityarea2_id", cityarea2);
            params.put("house_price", mEditText_price.getText().toString().trim());
            params.put("house_area", mEditText_measure.getText().toString().trim());
            params.put("house_topfloor", mEditText_top.getText().toString().trim());
            params.put("house_floor", mEditText_floor.getText().toString().trim());
//            params.put("house_toward", house_toward);
            if ("东".equals(house_toward)) {
                params.put("house_toward", "1");
            }
            if ("西".equals(house_toward)) {
                params.put("house_toward", "2");
            }
            if ("南".equals(house_toward)) {
                params.put("house_toward", "3");
            }
            if ("北".equals(house_toward)) {
                params.put("house_toward", "4");
            }
            if ("东南".equals(house_toward)) {
                params.put("house_toward", "5");
            }
            if ("西南".equals(house_toward)) {
                params.put("house_toward", "6");
            }
            if ("东北".equals(house_toward)) {
                params.put("house_toward", "7");
            }
            if ("西北".equals(house_toward)) {
                params.put("house_toward", "8");
            }
            if ("南北".equals(house_toward)) {
                params.put("house_toward", "9");
            }
            if ("东西".equals(house_toward)) {
                params.put("house_toward", "10");
            }
//            params.put("house_fitment", mEditText_floor.getText().toString().trim());
            if ("毛坯".equals(house_fitment)) {
                params.put("house_fitment", "1");
            }
            if ("普通装修".equals(house_fitment)) {
                params.put("house_fitment", "2");
            }
            if ("精装修".equals(house_fitment)) {
                params.put("house_fitment", "3");
            }
            if ("豪华装修".equals(house_fitment)) {
                params.put("house_fitment", "4");
            }
            params.put("house_age", mEditText_age.getText().toString().trim());
            params.put("house_title", mEditText_title.getText().toString().trim());
            params.put("borough_id", borough_id);
            params.put("borough_name", mButton_choose_community.getText().toString().trim());
            params.put("house_desc", mButton_description_community.getText().toString().trim());
            params.put("user_name", info.getRealname().toString());
            params.put("user_id", info.getUser_id().toString());
            params.put("user_phone", info.getUsername().toString());
            params.put("tel_time_start", mEditText_time_start.getText().toString().trim());
            params.put("tel_time_end", mEditText_time_end.getText().toString().trim());
            params.put("borker_id", "");
            params.put("house_room", house_room);
            params.put("house_hall", house_hall);
            params.put("house_toilet", house_toilet);


            FinalHttp fh = new FinalHttp();
            fh.post(JnHouse_Record.Key_sell_second, params, new AjaxCallBack<Object>() {

                @Override
                public void onLoading(long count, long current) {
                    super.onLoading(count, current);
                }

                @Override
                public void onStart() {
                    super.onStart();
                }

                @Override
                public void onSuccess(Object t) {

                    try {

                        JSONObject jsonObject = new JSONObject(t.toString());
                        Gson gson = new Gson();
                        LeaseEntity leaseEntity = gson.fromJson(t.toString(), new TypeToken<LeaseEntity>() {
                        }.getType());

                        if (leaseEntity != null) {

                            switch (leaseEntity.getCode()) {

                                case 1:
                                    ToastUtil.makeText(getActivity(), "未登录",
                                            ToastUtil.LENGTH_SHORT)
                                            .setAnimation(R.style.PopToast).show();
                                    break;
                                case -1:
                                    ToastUtil.makeText(getActivity(), "异常",
                                            ToastUtil.LENGTH_SHORT)
                                            .setAnimation(R.style.PopToast).show();
                                    break;
                                case 0:
                                    ToastUtil.makeText(getActivity(), "委托成功",
                                            ToastUtil.LENGTH_SHORT)
                                            .setAnimation(R.style.PopToast).show();
                                    break;

                                case 901:
                                    ToastUtil.makeText(getActivity(), "委托失败",
                                            ToastUtil.LENGTH_SHORT)
                                            .setAnimation(R.style.PopToast).show();
                                    break;
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void Init() {

        pop = new PopupWindow(getActivity());

        View v = getActivity().getLayoutInflater().inflate(R.layout.item_popupwindows, null);

        ll_popup = (LinearLayout) v.findViewById(R.id.ll_popup);

        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(v);

        RelativeLayout parent = (RelativeLayout) v.findViewById(R.id.parent);
        Button bt1 = (Button) v
                .findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) v
                .findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) v
                .findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                photo();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),
                        SellAlbumActivity.class);
                intent.putExtra("id", 0);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
    }

    public String getString(String s) {
        String path = null;
        if (s == null)
            return "";
        for (int i = s.length() - 1; i > 0; i++) {
            s.charAt(i);
        }
        return path;
    }

    protected void onRestart() {
        adapter.update();
//        super.onRestart();
    }

    private static final int TAKE_PICTURE = 0x000001;

    public void photo() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE:
                if (Bimp.tempSelectBitmap.size() < 9 && resultCode == getActivity().RESULT_OK) {

                    String fileName = String.valueOf(System.currentTimeMillis());
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    FileUtils.saveBitmap(bm, fileName);

                    ImageItem takePhoto = new ImageItem();
                    takePhoto.setBitmap(bm);
                    Bimp.tempSelectBitmap.add(takePhoto);
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        onRestart();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    @SuppressLint("HandlerLeak")
    public class GridAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private int selectedPosition = -1;
        private boolean shape;

        public boolean isShape() {
            return shape;
        }

        public void setShape(boolean shape) {
            this.shape = shape;
        }

        public GridAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void update() {
            loading();
        }

        public int getCount() {
            if (Bimp.tempSelectBitmap.size() == 9) {
                return 9;
            }
            return (Bimp.tempSelectBitmap.size() + 1);
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int arg0) {
            return 0;
        }

        public void setSelectedPosition(int position) {
            selectedPosition = position;
        }

        public int getSelectedPosition() {
            return selectedPosition;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_published_grida,
                        parent, false);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView
                        .findViewById(R.id.item_grida_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (position == Bimp.tempSelectBitmap.size()) {
                holder.image.setImageBitmap(BitmapFactory.decodeResource(
                        getResources(), R.drawable.icon_addpic_unfocused));
                if (position == 9) {
                    holder.image.setVisibility(View.GONE);
                }
            } else {
                holder.image.setImageBitmap(Bimp.tempSelectBitmap.get(position).getBitmap());
            }

            return convertView;
        }

        public class ViewHolder {
            public ImageView image;
        }

        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        adapter.notifyDataSetChanged();
                        break;
                }
                super.handleMessage(msg);
            }
        };

        public void loading() {
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        if (Bimp.max == Bimp.tempSelectBitmap.size()) {
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                            break;
                        } else {
                            Bimp.max += 1;
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        }
                    }
                }
            }).start();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()) {
            case R.id.sellsecond_room_spinner:
                house_room = adapterView.getItemAtPosition(position).toString();

                break;
            case R.id.sellsecond_office_spinner:
                house_hall = adapterView.getItemAtPosition(position).toString();
                break;
            /*case R.id.sellsecond_kitchen_spinner:
                String kitchen = adapterView.getItemAtPosition(position).toString();
                break;*/
            case R.id.sellsecond_toilet_spinner:
                house_toilet = adapterView.getItemAtPosition(position).toString();
                break;
            case R.id.sellsecond_decoration_spinner:
                house_fitment = adapterView.getItemAtPosition(position).toString();
                break;
            case R.id.sellsecond_orientation_spinner:
                house_toward = adapterView.getItemAtPosition(position).toString();
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(mContext, "未选中", Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onEventMainThread(EventUtil eventUtil) {
        String msg = eventUtil.getMsg();
        mButton_description_community.setText(msg);
    }

    @Subscribe
    public void onEventMainThread(EventCommunityUtil eventUtil) {
        borough_id = eventUtil.getBor_id();
        String msg = eventUtil.getMsg();
        city = eventUtil.getCity();
        cityarea = eventUtil.getCityarea();
        cityarea2 = eventUtil.getCityarea2();
        mButton_choose_community.setText(msg);
//        mTextView_city.setText(city);
        if ("29".equals(cityarea)) {
            mTextView_cityarea.setText("历下");
        }
        if ("30".equals(cityarea)) {
            mTextView_cityarea.setText("槐荫");
        }
        if ("31".equals(cityarea)) {
            mTextView_cityarea.setText("市中");
        }
        if ("32".equals(cityarea)) {
            mTextView_cityarea.setText("历城");
        }
        if ("33".equals(cityarea)) {
            mTextView_cityarea.setText("章丘");
        }
        if ("34".equals(cityarea)) {
            mTextView_cityarea.setText("长清");
        }
        if ("35".equals(cityarea)) {
            mTextView_cityarea.setText("高新");
        }
        if ("36".equals(cityarea)) {
            mTextView_cityarea.setText("天桥");
        }
        if ("37".equals(cityarea)) {
            mTextView_cityarea.setText("济阳");
        }
        if ("38".equals(cityarea)) {
            mTextView_cityarea.setText("商河");
        }
        if ("39".equals(cityarea)) {
            mTextView_cityarea.setText("平阴");
        }
//        mTextView_cityarea2.setText(cityarea2);

        borougnDetailData();
    }

    public void pushBrokerData() {

        try {

            AjaxParams params = new AjaxParams();
            params.put("borough_id", borough_id);
            params.put("trade_id", cityarea2);
            params.put("borough_lng", borough_lng);
            params.put("borough_lat", borough_lat);
            params.put("wt_type", "1");
            params.put("wt_fl", "0");


            FinalHttp fh = new FinalHttp();
            fh.get(JnHouse_Record.Key_push_broker, params, new AjaxCallBack<Object>() {

                @Override
                public void onFailure(Throwable t, int errorNo, String strMsg) {
                    super.onFailure(t, errorNo, strMsg);
                    Log.i("#####", "pushBrokerData失败\t" + errorNo + "\t" + strMsg);
                    Toast.makeText(getActivity(), "pushBrokerData失败" + "\t" + errorNo + "\t" + strMsg, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onLoading(long count, long current) {
                    super.onLoading(count, current);
                }

                @Override
                public void onStart() {
                    super.onStart();
                }

                @Override
                public void onSuccess(Object t) {

                    try {

                        JSONObject jsonObject = new JSONObject(t.toString());
                        Gson gson = new Gson();
                        LeaseEntity leaseEntity = gson.fromJson(t.toString(), new TypeToken<LeaseEntity>() {
                        }.getType());

                        if (leaseEntity != null) {

                            switch (leaseEntity.getCode()) {

                                case 1:
                                    ToastUtil.makeText(getActivity(), "未登录",
                                            ToastUtil.LENGTH_SHORT)
                                            .setAnimation(R.style.PopToast).show();
                                    break;
                                case -1:
                                    ToastUtil.makeText(getActivity(), "异常",
                                            ToastUtil.LENGTH_SHORT)
                                            .setAnimation(R.style.PopToast).show();
                                    break;
                                case 0:
                                    ToastUtil.makeText(getActivity(), "成功",
                                            ToastUtil.LENGTH_SHORT)
                                            .setAnimation(R.style.PopToast).show();
                                    if (leaseEntity.getData_list().size() == 0) {
                                        Toast.makeText(getActivity(), "附近暂无置业顾问", Toast.LENGTH_SHORT).show();
                                        mCheckBox_choose.setChecked(false);
                                        mLinearLayout.setVisibility(View.GONE);
                                    }
                                    if (leaseEntity.getData_list().size() == 1) {
                                        broker1_id = leaseEntity.getData_list().get(0).getBroker_id();
                                        getBroker1_name = leaseEntity.getData_list().get(0).getBroker_name();

                                        mImageButton_agent_two.setVisibility(View.GONE);
                                        mImageButton_agent_three.setVisibility(View.GONE);
                                        mCheckBox_two.setVisibility(View.GONE);
                                        mCheckBox_three.setVisibility(View.GONE);

                                        String myJpgPath0 = leaseEntity.getData_list().get(0).getBroker_avar();
                                        BitmapFactory.Options options0 = new BitmapFactory.Options();
                                        options0.inSampleSize = 2;
                                        Bitmap bm0 = BitmapFactory.decodeFile(myJpgPath0, options0);
                                        mImageButton_agent_one.setImageBitmap(bm0);

                                        mLinearLayout.setVisibility(View.VISIBLE);
                                    }
                                    if (leaseEntity.getData_list().size() == 2) {
                                        broker1_id = leaseEntity.getData_list().get(0).getBroker_id();
                                        getBroker1_name = leaseEntity.getData_list().get(0).getBroker_name();
                                        broker2_id = leaseEntity.getData_list().get(1).getBroker_id();
                                        getBroker2_name = leaseEntity.getData_list().get(1).getBroker_name();

                                        mImageButton_agent_three.setVisibility(View.GONE);
                                        mCheckBox_three.setVisibility(View.GONE);

                                        String myJpgPath0 = leaseEntity.getData_list().get(0).getBroker_avar();
                                        BitmapFactory.Options options0 = new BitmapFactory.Options();
                                        options0.inSampleSize = 2;
                                        Bitmap bm0 = BitmapFactory.decodeFile(myJpgPath0, options0);
                                        mImageButton_agent_one.setImageBitmap(bm0);

                                        String myJpgPath1 = leaseEntity.getData_list().get(1).getBroker_avar();
                                        BitmapFactory.Options options1 = new BitmapFactory.Options();
                                        options1.inSampleSize = 2;
                                        Bitmap bm1 = BitmapFactory.decodeFile(myJpgPath1, options1);
                                        mImageButton_agent_two.setImageBitmap(bm1);

                                        mLinearLayout.setVisibility(View.VISIBLE);
                                    }
                                    if (leaseEntity.getData_list().size() == 3) {
                                        String myJpgPath0 = leaseEntity.getData_list().get(0).getBroker_avar();
                                        BitmapFactory.Options options0 = new BitmapFactory.Options();
                                        options0.inSampleSize = 2;
                                        Bitmap bm0 = BitmapFactory.decodeFile(myJpgPath0, options0);
                                        mImageButton_agent_one.setImageBitmap(bm0);

                                        String myJpgPath1 = leaseEntity.getData_list().get(1).getBroker_avar();
                                        BitmapFactory.Options options1 = new BitmapFactory.Options();
                                        options1.inSampleSize = 2;
                                        Bitmap bm1 = BitmapFactory.decodeFile(myJpgPath1, options1);
                                        mImageButton_agent_two.setImageBitmap(bm1);

                                        String myJpgPath2 = leaseEntity.getData_list().get(2).getBroker_avar();
                                        BitmapFactory.Options options2 = new BitmapFactory.Options();
                                        options2.inSampleSize = 2;
                                        Bitmap bm2 = BitmapFactory.decodeFile(myJpgPath2, options2);
                                        mImageButton_agent_three.setImageBitmap(bm2);

                                        mLinearLayout.setVisibility(View.VISIBLE);
                                    }

                                    break;

                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void borougnDetailData() {

        try {

            AjaxParams params = new AjaxParams();
            params.put("house_id", borough_id);
            params.put("user_id", "");

            FinalHttp fh = new FinalHttp();
            fh.get(JnHouse_Record.Key_Borough_Detail, params, new AjaxCallBack<Object>() {

                @Override
                public void onFailure(Throwable t, int errorNo, String strMsg) {
                    super.onFailure(t, errorNo, strMsg);
                    Log.i("#####", "LeaseEntire\t" + errorNo + "\t" + strMsg);
                    Toast.makeText(getActivity(), "委托失败" + "\t" + errorNo + "\t" + strMsg, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onLoading(long count, long current) {
                    super.onLoading(count, current);
                }

                @Override
                public void onStart() {
                    super.onStart();
                }

                @Override
                public void onSuccess(Object t) {

                    try {

                        JSONObject jsonObject = new JSONObject(t.toString());
                        Gson gson = new Gson();
                        BoroughDetail detail = gson.fromJson(t.toString(), new TypeToken<BoroughDetail>() {
                        }.getType());

                        if (detail != null) {
                            String map = detail.getLayout_map();
                            borough_lat = map.substring(1, map.indexOf(","));
                            borough_lng = map.substring(map.indexOf(",") + 1, map.indexOf(")"));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveToLocal(Bitmap bm, int i) {
        Toast.makeText(getActivity(), "saveToLocal", Toast.LENGTH_LONG).show();

//        for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
        updateDir = new File(Environment.getExternalStorageDirectory(),
                Configs.saveSellPath);
        if (!updateDir.exists()) {
            updateDir.mkdirs();
        }
        path = Environment.getExternalStorageDirectory()
                + Configs.saveSellPath + i + ".jpg";
        Log.i("#####", path);
        sellPathList.add(path);
        try {
            FileOutputStream fos = new FileOutputStream(path);
            bm.compress(Bitmap.CompressFormat.JPEG, 75, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//            addDataToArray(fileList,new File(path));
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
