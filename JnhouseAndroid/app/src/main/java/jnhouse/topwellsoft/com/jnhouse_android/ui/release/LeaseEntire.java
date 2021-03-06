package jnhouse.topwellsoft.com.jnhouse_android.ui.release;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import jnhouse.topwellsoft.com.jnhouse_android.model.ZfptFacilitiesList;
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
 */
public class LeaseEntire extends Fragment implements View.OnClickListener, OnCheckedChangeListener, AdapterView.OnItemSelectedListener {
    private ImageView mImageButton_sculpture, mImageButton_agent_one, mImageButton_agent_two, mImageButton_agent_three;
    private EditText mEditText_location, mEditText_money, mEditText_measure, mEditText_floor, mEditText_title, mEditText_description, mEditText_contacts, mEditText_phone, mEditText_time;
    private Spinner mSpinner_room, mSpinner_office, mSpinner_kitchen, mSpinner_toilet, mSpinner_balcony;
    private Button mButton_choose_community, mButton_description_community, mButton_zero, mButton_one, mButton_two, mButton_three, mButton_five, mButton_six, mButton_seven, mButton_eight;
    private RadioButton mRadioButton_man, mRadioButton_woman;
    private CheckBox mCheckBox_choose;
    private Button mButton_release;
    private RadioGroup mRadioGroup_sex;
    private LinearLayout mLinearLayout;
    private TextView mTextView_city, mTextView_cityarea, mTextView_cityarea2;
    private EditText mEditText_time_end;
    private Button mButton_four, mButton_nine;
    private static final int[] COLOR = new int[]{R.color.app_main_color, R.color.app_font_color_grey};
    public static int counts_bed = 0;
    public static int counts_television = 0;
    public static int counts_net = 0;
    public static int counts_heater = 0;
    public static int counts_fridge = 0;
    public static int counts_air_conditioner = 0;
    public static int counts_heating = 0;
    public static int counts_washer = 0;
    public static int counts_five = 0;
    public static int counts_ten = 0;


    private static final String tag = "leaseentire";
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int PICK_IMAGE_ACTIVITY_REQUEST_CODE = 200;
    private String sex;
    private final int REQUEST_CODE = 1;

    private String rent_type = "0";
    private String borough_id;
    private String borough_name;
    private String city_id;
    private String cityarea_id;
    private String cityarea2_id;
    private String rent_price;
    private String house_room;
    private String house_hall;
    private String house_toilet;
    private String house_kitchen;
    private String house_balcony;
    private String house_area;
    private String house_topfloor;
    private String house_floor;
    private String house_facility;
    private String house_title;
    private String house_desc;
    private String user_name;
    private String user_id;
    private String user_phone;
    private String tel_time_start;
    private String tel_time_end;
    private String rent_price_unit = "0";
    private List<String> borkers = new ArrayList<String>();
//    private String rent_unite_type;

    private GridViewDefined noScrollgridview;
    private GridLeaseEntireAdapter adapter;
    private View parentView;
    private PopupWindow pop = null;
    private LinearLayout ll_popup;
    public static Bitmap bimap;
    private String[] facilities = new String[10];

    private Context context;

    private File updateDir = null;
    private String path = null;
    private boolean[] checked = new boolean[10];
    private ArrayList<String> di_value = new ArrayList<String>();
    private ArrayList<String> pathList = new ArrayList<String>();
    private EditText mEditText_top;

    private int now = 0;
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
        parentView = getActivity().getLayoutInflater().inflate(R.layout.release_leaseentire, null);
//        setContentView(parentView);
        Init();
//        Bimp.tempSelectBitmap.clear();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.release_leaseentire, container, false);

        getSupportingFacilities();
        noScrollgridview = (GridViewDefined) view.findViewById(R.id.noScrollgridview);
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new GridLeaseEntireAdapter(getActivity());
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
                            GalleryActivity.class);
                    intent.putExtra("position", "1");
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });


        mSpinner_balcony = (Spinner) view.findViewById(R.id.lease_entire_balcony_spinner);
        mEditText_top = (EditText) view.findViewById(R.id.lease_entire_topfloor_et);
//        mTextView_city = (TextView) view.findViewById(R.id.city_id_tv);
        mTextView_cityarea = (TextView) view.findViewById(R.id.cityarea_id_tv);
//        mTextView_cityarea2 = (TextView) view.findViewById(R.id.cityarea2_id_tv);
        mImageButton_agent_one = (ImageView) view.findViewById(R.id.leaseentire_agent_one_ib);
        mImageButton_agent_two = (ImageView) view.findViewById(R.id.leaseentire_agent_two_ib);
        mImageButton_agent_three = (ImageView) view.findViewById(R.id.leaseentire_agent_three_ib);
        mEditText_money = (EditText) view.findViewById(R.id.leaseentire_money_et);
        mEditText_measure = (EditText) view.findViewById(R.id.leaseentire_measure_et);
        mEditText_floor = (EditText) view.findViewById(R.id.leaseentire_floor_et);
        mEditText_title = (EditText) view.findViewById(R.id.leaseentire_title_et);
        mEditText_contacts = (EditText) view.findViewById(R.id.leaseentire_contacts_et);
        mEditText_phone = (EditText) view.findViewById(R.id.leaseentire_phone_et);
        mEditText_time = (EditText) view.findViewById(R.id.leaseentire_time_et);
        mEditText_time_end = (EditText) view.findViewById(R.id.leaseentire_time_end_et);

        mLinearLayout = (LinearLayout) view.findViewById(R.id.leaseentire_checkbox_linearlayout);

        mRadioGroup_sex = (RadioGroup) view.findViewById(R.id.sex_group);

        mButton_choose_community = (Button) view.findViewById(R.id.leaseentire_choosecommunity_bt);
        mButton_description_community = (Button) view.findViewById(R.id.leaseentire_description_bt);
        mButton_zero = (Button) view.findViewById(R.id.bed);
        mButton_one = (Button) view.findViewById(R.id.television);
        mButton_two = (Button) view.findViewById(R.id.net);
        mButton_three = (Button) view.findViewById(R.id.heater);
        mButton_four = (Button) view.findViewById(R.id.btn5);
        mButton_five = (Button) view.findViewById(R.id.fridge);
        mButton_six = (Button) view.findViewById(R.id.air_conditioner);
        mButton_seven = (Button) view.findViewById(R.id.heating);
        mButton_eight = (Button) view.findViewById(R.id.washer);
        mButton_nine = (Button) view.findViewById(R.id.btn10);

        mSpinner_room = (Spinner) view.findViewById(R.id.leaseentire_room_spinner);

        mSpinner_office = (Spinner) view.findViewById(R.id.leaseentire_office_spinner);
        mSpinner_kitchen = (Spinner) view.findViewById(R.id.leaseentire_kitchen_spinner);
        mSpinner_toilet = (Spinner) view.findViewById(R.id.leaseentire_toilet_spinner);

        mRadioButton_man = (RadioButton) view.findViewById(R.id.contacts_man_rb);
        mRadioButton_woman = (RadioButton) view.findViewById(R.id.contacts_woman_rb);


        mCheckBox_choose = (CheckBox) view.findViewById(R.id.leaseentire_choose_cb);


        mButton_release = (Button) view.findViewById(R.id.leaseentire_release_bt);
        final LoginEntity info;

        info = (LoginEntity) PreferencesUtils.getObject(getActivity(), "loginEntity");
        mEditText_contacts.setText(info.getRealname());
        mEditText_phone.setText(info.getUsername());

        for (int i = 0; i < 10; i++) {
            checked[i] = false;
        }
        mButton_zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counts_bed == 0) {
                    mButton_zero.setBackgroundResource(COLOR[counts_bed]);
//                    Toast.makeText(getActivity(),"红色还是灰色",Toast.LENGTH_LONG).show();
                    counts_bed += 1;
                    di_value.add(11 + "");
                } else {
                    mButton_zero.setBackgroundResource(COLOR[counts_bed]);
                    counts_bed -= 1;
                    di_value.remove("11");
                }
            }
        });
        mButton_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counts_television == 0) {
                    mButton_one.setBackgroundResource(COLOR[counts_television]);
                    counts_television += 1;
                    di_value.add(1 + "");
                } else {
                    mButton_one.setBackgroundResource(COLOR[counts_television]);
                    counts_television -= 1;
                    di_value.remove("1");
                }
            }
        });
        mButton_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counts_net == 0) {
                    mButton_two.setBackgroundResource(COLOR[counts_net]);
                    counts_net += 1;
                    di_value.add(2 + "");
                } else {
                    mButton_two.setBackgroundResource(COLOR[counts_net]);
                    counts_net -= 1;
                    di_value.remove("2");
                }
            }
        });
        mButton_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counts_heater == 0) {
                    mButton_three.setBackgroundResource(COLOR[counts_heater]);
                    counts_heater += 1;
                    di_value.add(3 + "");
                } else {
                    mButton_three.setBackgroundResource(COLOR[counts_heater]);
                    counts_heater -= 1;
                    di_value.remove("3");
                }
            }
        });
        mButton_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counts_five == 0) {
                    mButton_four.setBackgroundResource(COLOR[counts_five]);
                    counts_five += 1;
                    di_value.add(8 + "");
                } else {
                    mButton_four.setBackgroundResource(COLOR[counts_five]);
                    counts_five -= 1;
                    di_value.remove("8");
                }
            }
        });
        mButton_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counts_fridge == 0) {
                    mButton_five.setBackgroundResource(COLOR[counts_fridge]);
                    counts_fridge += 1;
                    di_value.add(9 + "");
                } else {
                    mButton_five.setBackgroundResource(COLOR[counts_fridge]);
                    counts_fridge -= 1;
                    di_value.remove("9");
                }
            }
        });
        mButton_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counts_air_conditioner == 0) {
                    mButton_six.setBackgroundResource(COLOR[counts_air_conditioner]);
                    counts_air_conditioner += 1;
                    di_value.add(10 + "");
                } else {
                    mButton_six.setBackgroundResource(COLOR[counts_air_conditioner]);
                    counts_air_conditioner -= 1;
                    di_value.remove("10");
                }
            }
        });
        mButton_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counts_heating == 0) {
                    mButton_seven.setBackgroundResource(COLOR[counts_heating]);
                    counts_heating += 1;
                    di_value.add(12 + "");
                } else {
                    mButton_seven.setBackgroundResource(COLOR[counts_heating]);
                    counts_heating -= 1;
                    di_value.remove("12");
                }
            }
        });
        mButton_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counts_washer == 0) {
                    mButton_eight.setBackgroundResource(COLOR[counts_washer]);
                    counts_washer += 1;
                    di_value.add(16 + "");
                } else {
                    mButton_eight.setBackgroundResource(COLOR[counts_washer]);
                    counts_washer -= 1;
                    di_value.remove("16");
                }
            }
        });

        mButton_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counts_ten == 0) {
                    mButton_nine.setBackgroundResource(COLOR[counts_ten]);
                    counts_ten += 1;
                    di_value.add(18 + "");
                } else {
                    mButton_nine.setBackgroundResource(COLOR[counts_ten]);
                    counts_ten -= 1;
                    di_value.remove("18");
                }
            }
        });

        mCheckBox_one = (CheckBox) view.findViewById(R.id.position1_checkbox);
        mCheckBox_two = (CheckBox) view.findViewById(R.id.position2_checkbox);
        mCheckBox_three = (CheckBox) view.findViewById(R.id.position3_checkbox);

        mCheckBox_one.setOnClickListener(this);
        mCheckBox_two.setOnClickListener(this);
        mCheckBox_three.setOnClickListener(this);
//        mImageButton_sculpture.setOnClickListener(this);
        mRadioButton_man.setOnClickListener(this);
        mRadioButton_woman.setOnClickListener(this);
        mCheckBox_choose.setOnClickListener(this);
        mButton_description_community.setOnClickListener(this);
        mButton_choose_community.setOnClickListener(this);
        mButton_release.setOnClickListener(this);

        mSpinner_room.setOnItemSelectedListener(this);
        mSpinner_office.setOnItemSelectedListener(this);
        mSpinner_kitchen.setOnItemSelectedListener(this);
        mSpinner_toilet.setOnItemSelectedListener(this);
        mSpinner_balcony.setOnItemSelectedListener(this);

        mLinearLayout.setVisibility(View.GONE);


        mRadioGroup_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.contacts_man_rb:
                        sex = "先生";
                        break;
                    case R.id.contacts_woman_rb:
                        sex = "女士";
                        break;
                    default:
                        break;
                }
            }
        });

        return view;
    }

    /*public ArrayList<File> addDataToArray(ArrayList<File> f,File newData)
    {
        f.add(newData);
        return f;
    }*/

    public void saveToLocal(Bitmap bm, int i) {
      //  Toast.makeText(getActivity(), "saveToLocal", Toast.LENGTH_LONG).show();

    //    for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
        updateDir = new File(Environment.getExternalStorageDirectory(),
                Configs.saveDescriptionPath);
        if (!updateDir.exists()) {
            updateDir.mkdirs();
        }
        path = Environment.getExternalStorageDirectory()
                + Configs.saveDescriptionPath + i + ".jpg";
        Log.i("#####", path);
        pathList.add(path);
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
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.leaseentire_choosecommunity_bt:
                Intent intent = new Intent();
                intent.setClass(getActivity(), ChooseCommunity.class);
                startActivity(intent);
                break;
            case R.id.leaseentire_description_bt:
                Intent intent_description = new Intent();
                intent_description.setClass(getActivity(), Communitydescription.class);
                intent_description.setFlags(intent_description.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivityForResult(intent_description,0);
                startActivity(intent_description);

                break;
            case R.id.leaseentire_choose_cb:

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
            case R.id.leaseentire_release_bt:
                Log.i("####", "点击了发布的button按钮");
                for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
                    saveToLocal(Bimp.tempSelectBitmap.get(i).getBitmap(), i);
                }
                if (mEditText_title.getText().toString().length() >= 6 && mEditText_title.getText().toString().length() <= 30) {
                    if (mButton_description_community.getText().toString().length() < 300) {
                        getPDAServerData();
                    } else {
                        ToastUtil.makeText(getActivity(), "描述请输入300字以内", ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
                    }
                } else {
                    ToastUtil.makeText(getActivity(), "标题请输入6-30个字", ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
                }
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
        /*for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
//            params.put("pictures", new File(listViews.get(i)));

        }*/
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
                params.put("pictures", new File(pathList.get(i)));
            }
            params.put("rent_type", rent_type);
            params.put("borough_id", borough_id);
            params.put("borough_name", mButton_choose_community.getText().toString().trim());
            params.put("city_id", city);
//            params.put("cityarea_id", mTextView_cityarea.getText().toString().trim());
            params.put("cityarea_id", cityarea);
            params.put("cityarea2_id", cityarea2);
            params.put("rent_price", mEditText_money.getText().toString().trim());
            params.put("house_room", house_room);
            params.put("house_hall", house_hall);
            params.put("house_toilet", house_toilet);
            params.put("house_kitchen", house_kitchen);
            params.put("house_balcony", house_balcony);
            params.put("house_area", mEditText_measure.getText().toString().trim());
            params.put("house_topfloor", mEditText_top.getText().toString());
            params.put("house_floor", mEditText_floor.getText().toString().trim());
//            params.put("house_facility", di_value.toString().trim());
            params.put("house_facility", di_value.toString().substring(1,di_value.toString().indexOf("]")).replace(" ",""));
            params.put("house_title", mEditText_title.getText().toString().trim());
            params.put("house_desc", mButton_description_community.getText().toString().trim());
            params.put("user_name", info.getRealname());
            params.put("user_id", info.getUser_id());
//            params.put("user_id", info.getUser_id());
            Log.e("#####","info.getUser_id:"+info.getUser_id());
            params.put("user_phone", info.getUsername());
            params.put("tel_time_start", mEditText_time.getText().toString().trim());
            params.put("tel_time_end", mEditText_time_end.getText().toString().trim());
            params.put("rent_price_unit", rent_price_unit);
            if (broker_list != null && broker_list.size() != 0) {
                params.put("borkers", broker_list.toString().substring(1, broker_list.toString().indexOf("]")).replace(" ", ""));
                Log.i("#####", "broker:" + broker_list.toString().substring(1, broker_list.toString().indexOf("]")).replace(" ", ""));
            } else {
                params.put("borkers", "");
                Log.i("#####", "broker未选择");
            }


            FinalHttp fh = new FinalHttp();
            fh.post(JnHouse_Record.Key_Lease, params, new AjaxCallBack<Object>() {

                @Override
                public void onFailure(Throwable t, int errorNo, String strMsg) {
                    super.onFailure(t, errorNo, strMsg);
                    Log.e("#####", "\t" + errorNo + "\t" + strMsg);
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
                        LeaseEntity leaseEntity = gson.fromJson(t.toString(), new TypeToken<LeaseEntity>() {
                        }.getType());

                        if (leaseEntity != null) {
                            Log.e("#####","经过了");

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

                                case 1002:
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void getSupportingFacilities() {
        AjaxParams params = new AjaxParams();

        FinalHttp fh = new FinalHttp();
        fh.get(JnHouse_Record.Key_supporting_facilities, params, new AjaxCallBack<Object>() {

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
                    ZfptFacilitiesList list = gson.fromJson(t.toString(), new TypeToken<ZfptFacilitiesList>() {
                    }.getType());
                    if (list.getZfpt_list() != null && list.getZfpt_list().size() > 0) {
                        for (int i = 0; i < list.getZfpt_list().size(); i++) {
                            String di_caption = list.getZfpt_list().get(i).getDi_caption();
                            facilities[i] = di_caption;
//                            Toast.makeText(getActivity(), di_caption, Toast.LENGTH_SHORT).show();
                            Log.i("#####", "描述：" + di_caption);
                        }
                        mButton_zero.setText(facilities[0]);
                        mButton_one.setText(facilities[1]);
                        mButton_two.setText(facilities[2]);
                        mButton_three.setText(facilities[3]);
                        mButton_four.setText(facilities[4]);
                        mButton_five.setText(facilities[5]);
                        mButton_six.setText(facilities[6]);
                        mButton_seven.setText(facilities[7]);
                        mButton_eight.setText(facilities[8]);
                        mButton_nine.setText(facilities[9]);
                    }
                    Log.i("######", "onSuccess");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()) {
            case R.id.leaseentire_room_spinner:
                house_room = adapterView.getItemAtPosition(position).toString();
                break;
            case R.id.leaseentire_office_spinner:
                house_hall = adapterView.getItemAtPosition(position).toString();
                break;
            case R.id.leaseentire_kitchen_spinner:
                house_kitchen = adapterView.getItemAtPosition(position).toString();
                break;
            case R.id.leaseentire_toilet_spinner:
                house_toilet = adapterView.getItemAtPosition(position).toString();
                break;
            case R.id.lease_entire_balcony_spinner:
                house_balcony = adapterView.getItemAtPosition(position).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(getActivity(), "未选中", Toast.LENGTH_SHORT).show();
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
                        AlbumActivity.class);
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

    @SuppressLint("HandlerLeak")
    public class GridLeaseEntireAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private int selectedPosition = -1;
        private boolean shape;
        private int current;

        public boolean isShape() {
            return shape;
        }

        public void setShape(boolean shape) {
            this.shape = shape;
        }

        public GridLeaseEntireAdapter(Context context) {
            inflater = LayoutInflater.from(context);
//            this.current = current;
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
            /*if (current != 0){
                Bimp.tempSelectBitmap.clear();
            }*/
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
    public void onResume() {
        super.onResume();
        onRestart();

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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            for (int i = 0; i < PublicWay.activityList.size(); i++) {
                if (null != PublicWay.activityList.get(i)) {
                    PublicWay.activityList.get(i).finish();
                }
            }
            System.exit(0);
        }
        return true;
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
        Log.e("msg",msg==null?"msg空的":"msg不是null");
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
            params.put("wt_type", "2");
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
                                        broker1_id = leaseEntity.getData_list().get(0).getBroker_id();
                                        getBroker1_name = leaseEntity.getData_list().get(0).getBroker_name();
                                        broker2_id = leaseEntity.getData_list().get(1).getBroker_id();
                                        getBroker2_name = leaseEntity.getData_list().get(1).getBroker_name();
                                        broker3_id = leaseEntity.getData_list().get(2).getBroker_id();
                                        getBroker3_name = leaseEntity.getData_list().get(2).getBroker_name();

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
                            Log.i("#####", "borough_lng:" + borough_lng);
                            Log.i("#####", "borough_lat:" + borough_lat);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Bimp.tempSelectBitmap.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
