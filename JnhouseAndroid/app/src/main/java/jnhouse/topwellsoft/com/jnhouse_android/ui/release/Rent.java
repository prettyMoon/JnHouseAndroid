package jnhouse.topwellsoft.com.jnhouse_android.ui.release;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
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

import java.util.ArrayList;
import java.util.List;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.BoroughDetail;
import jnhouse.topwellsoft.com.jnhouse_android.model.LeaseEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.ZfptFacilitiesList;
import jnhouse.topwellsoft.com.jnhouse_android.util.EventCommunityUtil;
import jnhouse.topwellsoft.com.jnhouse_android.util.EventUtil;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

public class Rent extends AppCompatActivity implements View.OnClickListener{
    private EditText mEditText_community,mEditText_location,mEditText_price,mEditText_measure,mEditText_floor,mEditText_title,mEditText_description,mEditText_contacts,mEditText_phone,mEditText_time;
    private Spinner mSpinner_room,mSpinner_office,mSpinner_kitchen,mSpinner_toilet;
    private ImageView mImageView_agent_one,mImageView_agent_two,mImageView_agent_three;
    private Button mButton_zero,mButton_one,mButton_two,mButton_three,mButton_five,mButton_six,mButton_seven,mButton_eight;
    private RadioGroup mRadioGroup_sex;
    private CheckBox mCheckBox_choose;
    private Button mButton_back,mButton_release;
    private String sex;
    private LinearLayout mLinearLayout;
    private TextView mTextView_cityarea,mTextView_cityarea2;
    private Button mButton_description_community,mButton_choose_community;
    private Button mButton_four,mButton_nine;
    private Spinner mSpinner_rent_type,mSpinner_rent_money;
    private static final int[] COLOR = new int[]{R.color.app_main_color,R.color.app_font_color_grey};
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

    private String borough_id;
    private String borough_name;
    private String area_id;
    private String trade_id;
    private String house_room;
    private String house_hall;
    private String house_toilet;
    private String rent_type;
    private String rent_money;
    private List<String> installation;
    private String title;
    private String describes;
    private String user_id;
    private String contacts;
    private String phone;
    private List<String> borkers = new ArrayList<String>();
//    private String rent_unite_type;

    private int position = 0;
    private String[] facilities = new String[10];
    private ArrayList<String> di_value = new ArrayList<String>();

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        EventBus.getDefault().register(this);
        init();
        getSupportingFacilities();
    }

    private void init() {

        mSpinner_rent_money = (Spinner) findViewById(R.id.rent_money_spinner);
        mSpinner_rent_type = (Spinner) findViewById(R.id.rent_want_type_spinner);
        mButton_four = (Button) findViewById(R.id.btn5);
        mButton_nine = (Button) findViewById(R.id.btn10);
        mLinearLayout = (LinearLayout) findViewById(R.id.rent_checkbox_linearlayout);
        mTextView_cityarea = (TextView) findViewById(R.id.cityarea_id_tv);
//        mTextView_cityarea2 = (TextView) findViewById(R.id.cityarea2_id_tv);
        mButton_choose_community = (Button) findViewById(R.id.rent_choosecommunity_bt);
        mButton_description_community = (Button) findViewById(R.id.rent_description_bt);
//        mEditText_price = (EditText) findViewById(R.id.rent_price_et);
//        mEditText_measure = (EditText) findViewById(R.id.rent_measure_et);
//        mEditText_floor = (EditText) findViewById(R.id.rent_floor_et);
        mEditText_title = (EditText) findViewById(R.id.rent_title_et);
        mEditText_contacts = (EditText) findViewById(R.id.rent_contacts_et);
        mEditText_phone = (EditText) findViewById(R.id.rent_phone_et);
//        mEditText_time = (EditText) findViewById(R.id.rent_time_et);

        mButton_zero = (Button) findViewById(R.id.bed);
        mButton_one = (Button) findViewById(R.id.television);
        mButton_two = (Button) findViewById(R.id.net);
        mButton_three = (Button) findViewById(R.id.heater);
        mButton_five = (Button) findViewById(R.id.fridge);
        mButton_six = (Button) findViewById(R.id.air_conditioner);
        mButton_seven = (Button) findViewById(R.id.heating);
        mButton_eight = (Button) findViewById(R.id.washer);

        mSpinner_room = (Spinner) findViewById(R.id.rent_room_spinner);
        mSpinner_office = (Spinner) findViewById(R.id.rent_office_spinner);
//        mSpinner_kitchen = (Spinner) findViewById(R.id.rent_kitchen_spinner);
        mSpinner_toilet = (Spinner) findViewById(R.id.rent_toilet_spinner);

        mImageView_agent_one = (ImageView) findViewById(R.id.rent_agent_one_ib);
        mImageView_agent_two = (ImageView) findViewById(R.id.rent_agent_two_ib);
        mImageView_agent_three = (ImageView) findViewById(R.id.rent_agent_three_ib);

        mRadioGroup_sex = (RadioGroup) findViewById(R.id.sex_group);

        mCheckBox_choose = (CheckBox) findViewById(R.id.rent_choose_cb);

        mButton_back = (Button) findViewById(R.id.rent_back_button);
        mButton_release = (Button) findViewById(R.id.rent_release_bt);

        final LoginEntity info;

        info = (LoginEntity) PreferencesUtils.getObject(this, "loginEntity");
        mEditText_contacts.setText(info.getRealname());
        mEditText_phone.setText(info.getUsername());

        mCheckBox_one = (CheckBox) findViewById(R.id.position1_checkbox);
        mCheckBox_two = (CheckBox) findViewById(R.id.position2_checkbox);
        mCheckBox_three = (CheckBox) findViewById(R.id.position3_checkbox);

        mCheckBox_one.setOnClickListener(this);
        mCheckBox_two.setOnClickListener(this);
        mCheckBox_three.setOnClickListener(this);

        mCheckBox_choose.setOnClickListener(this);
        mButton_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ToastUtil.makeText(Rent.this,"wwww",ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
                if (mEditText_title.getText().toString().length() >= 6 && mEditText_title.getText().toString().length() <= 30){
                    if (mButton_description_community.getText().toString().length() < 300){
                        getPDAServerData();
                    }else {
                        ToastUtil.makeText(Rent.this,"描述请输入300字以内",ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
                    }
                }else {
                    ToastUtil.makeText(Rent.this,"标题请输入6-30个字",ToastUtil.LENGTH_LONG).setAnimation(R.style.PopToast).show();
                }


            }
        });
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
        mSpinner_room.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                house_room = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Rent.this, "未选中", Toast.LENGTH_SHORT).show();
            }
        });
        mSpinner_office.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                house_hall = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Rent.this, "未选中", Toast.LENGTH_SHORT).show();
            }
        });
        /*mSpinner_kitchen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String kitchen = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Rent.this, "未选中", Toast.LENGTH_SHORT).show();
            }
        });*/
        mSpinner_toilet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                house_toilet = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Rent.this, "未选中", Toast.LENGTH_SHORT).show();
            }
        });

        mSpinner_rent_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rent_type = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Rent.this, "未选中", Toast.LENGTH_SHORT).show();
            }
        });
        mSpinner_rent_money.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rent_money = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Rent.this, "未选中", Toast.LENGTH_SHORT).show();
            }
        });

        mButton_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mButton_zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counts_bed == 0) {
                    mButton_zero.setBackgroundResource(COLOR[counts_bed]);
//                    Toast.makeText(getActivity(),"红色还是灰色",Toast.LENGTH_LONG).show();
                    counts_bed += 1;
                    position +=1;
                    di_value.add(11+"");
                } else {
                    mButton_zero.setBackgroundResource(COLOR[counts_bed]);
                    counts_bed -= 1;
                    position -=1;
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
                    position +=1;
                    di_value.add(1+"");
                } else {
                    mButton_one.setBackgroundResource(COLOR[counts_television]);
                    counts_television -= 1;
                    position -=1;
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
                    position +=1;
                    di_value.add(2+"");
                } else {
                    mButton_two.setBackgroundResource(COLOR[counts_net]);
                    counts_net -= 1;
                    position -=1;
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
                    position +=1;
                    di_value.add(3+"");
                } else {
                    mButton_three.setBackgroundResource(COLOR[counts_heater]);
                    counts_heater -= 1;
                    position -=1;
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
                    position +=1;
                    di_value.add(8+"");
                } else {
                    mButton_four.setBackgroundResource(COLOR[counts_five]);
                    counts_five -= 1;
                    position -=1;
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
                    position +=1;
                    di_value.add(9+"");
                } else {
                    mButton_five.setBackgroundResource(COLOR[counts_fridge]);
                    counts_fridge -= 1;
                    position -=1;
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
                    position +=1;
                    di_value.add(10+"");
                } else {
                    mButton_six.setBackgroundResource(COLOR[counts_air_conditioner]);
                    counts_air_conditioner -= 1;
                    position -=1;
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
                    position +=1;
                    di_value.add(12+"");
                } else {
                    mButton_seven.setBackgroundResource(COLOR[counts_heating]);
                    counts_heating -= 1;
                    position -=1;
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
                    position +=1;
                    di_value.add(16+"");
                } else {
                    mButton_eight.setBackgroundResource(COLOR[counts_washer]);
                    counts_washer -= 1;
                    position -=1;
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
                    position +=1;
                    di_value.add(18+"");
                } else {
                    mButton_nine.setBackgroundResource(COLOR[counts_ten]);
                    counts_ten -= 1;
                    position -=1;
                    di_value.remove("18");
                }
            }
        });


        mButton_choose_community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Rent.this,ChooseCommunity.class);
                startActivity(intent);
            }
        });
        mButton_description_community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_description = new Intent();
                intent_description.setClass(Rent.this,Communitydescription.class);
                startActivity(intent_description);
            }
        });
        mLinearLayout.setVisibility(View.GONE);

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
                    if (list.getZfpt_list() != null && list.getZfpt_list().size() > 0){
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
                    Log.i("######","onSuccess");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getPDAServerData() {

        LoginEntity info = (LoginEntity) PreferencesUtils.getObject(this,"loginEntity");
        if (info == null || info.getUserUUID() == null) {
            ToastUtil.makeText(  this, "请重新登录",
                    ToastUtil.LENGTH_SHORT)
                    .setAnimation(R.style.PopToast).show();
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put("userUUID", info.getUserUUID());
        params.put("borough_id", borough_id);
        params.put("borough", mButton_choose_community.getText().toString().trim());
        params.put("area_id", cityarea);
        params.put("trade_id", cityarea2);
        params.put("house_room", house_room);
        params.put("house_hall", house_hall);
        params.put("house_toilet", house_toilet);
        if ("整租".equals(rent_type)){
            params.put("rent_type", "0");
        }
        if ("合租".equals(rent_type)){
            params.put("rent_type", "1");
        }
        if ("日短租".equals(rent_type)){
            params.put("rent_type", "2");
        }
        if ("500以下".equals(rent_money)){
            params.put("rent_money", "0");
        }
        if ("500-1000元".equals(rent_money)){
            params.put("rent_money", "1");
        }
        if ("1000-1500元".equals(rent_money)){
            params.put("rent_money", "2");
        }
        if ("1500-3000元".equals(rent_money)){
            params.put("rent_money", "3");
        }
        if ("3000元以上".equals(rent_money)){
            params.put("rent_money", "4");
        }
        for (int i = 0; i < position; i++) {
            params.put("installation", di_value.get(i).toString());
        }

        params.put("title", mEditText_title.getText().toString().trim());
        params.put("describes", mButton_description_community.getText().toString().trim());
        params.put("user_id", info.getUser_id());
        params.put("contacts", info.getRealname());
        params.put("phone", info.getUsername());
        params.put("brokers", "");
//        params.put("house_kitchen", house_kitchen);
//        params.put("house_balcony", rent_type.getText().toString().trim());
//        params.put("house_topfloor", rent_type.getText().toString().trim());
//        params.put("house_facility", rent_type.getText().toString().trim());
//        params.put("user_id", rent_type.getText().toString().trim());
//        params.put("rent_price_unit", rent_price_unit);


        FinalHttp fh = new FinalHttp();
        fh.get(JnHouse_Record.Key_rent, params, new AjaxCallBack<Object>() {
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                ToastUtil.makeText(Rent.this, "委托失败onFaliure",
                        ToastUtil.LENGTH_SHORT)
                        .setAnimation(R.style.PopToast).show();
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
                Log.i("----",t.toString());
                try {

                    JSONObject jsonObject = new JSONObject(t.toString());
                    Gson gson = new Gson();
                    LeaseEntity leaseEntity = gson.fromJson(t.toString(), new TypeToken<LeaseEntity>() {
                    }.getType());

                    if (leaseEntity != null) {

                        switch (leaseEntity.getCode()) {

                            case 1:
                                ToastUtil.makeText(Rent.this, "未登录",
                                        ToastUtil.LENGTH_SHORT)
                                        .setAnimation(R.style.PopToast).show();
                                break;
                            case -1:
                                ToastUtil.makeText(Rent.this, "异常",
                                        ToastUtil.LENGTH_SHORT)
                                        .setAnimation(R.style.PopToast).show();
                                break;
                            case 0:
                                ToastUtil.makeText(Rent.this, "委托成功",
                                        ToastUtil.LENGTH_SHORT)
                                        .setAnimation(R.style.PopToast).show();
                                break;

                            case 1001:
                                ToastUtil.makeText(Rent.this, "委托失败",
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
    }

    @Subscribe
    public void onEventMainThread(EventUtil eventUtil){
        String msg=eventUtil.getMsg();
        mButton_description_community.setText(msg);
    }

    @Subscribe
    public void onEventMainThread(EventCommunityUtil eventUtil){
        borough_id = eventUtil.getBor_id();
        String msg=eventUtil.getMsg();
        cityarea = eventUtil.getCityarea();
        cityarea2 = eventUtil.getCityarea2();
        mButton_choose_community.setText(msg);
        if ("29".equals(cityarea)){
            mTextView_cityarea.setText("历下");
        }
        if ("30".equals(cityarea)){
            mTextView_cityarea.setText("槐荫");
        }
        if ("31".equals(cityarea)){
            mTextView_cityarea.setText("市中");
        }
        if ("32".equals(cityarea)){
            mTextView_cityarea.setText("历城");
        }
        if ("33".equals(cityarea)){
            mTextView_cityarea.setText("章丘");
        }
        if ("34".equals(cityarea)){
            mTextView_cityarea.setText("长清");
        }
        if ("35".equals(cityarea)){
            mTextView_cityarea.setText("高新");
        }
        if ("36".equals(cityarea)){
            mTextView_cityarea.setText("天桥");
        }
        if ("37".equals(cityarea)){
            mTextView_cityarea.setText("济阳");
        }
        if ("38".equals(cityarea)){
            mTextView_cityarea.setText("商河");
        }
        if ("39".equals(cityarea)){
            mTextView_cityarea.setText("平阴");
        }
//        mTextView_cityarea2.setText(cityarea2);

        borougnDetailData();
    }

    public void pushBrokerData() {

        try {
            LoginEntity info = (LoginEntity) PreferencesUtils.getObject(Rent.this, "loginEntity");
            if (info == null || info.getUserUUID() == null) {
                ToastUtil.makeText(  this, "请重新登录",
                        ToastUtil.LENGTH_SHORT)
                        .setAnimation(R.style.PopToast).show();
                return;
            }
            AjaxParams params = new AjaxParams();
            params.put("borough_id", borough_id);
            params.put("trade_id", cityarea2);
            params.put("borough_lng", borough_lng);
            params.put("borough_lat", borough_lat);
            params.put("wt_type", "4");

            if ("整租".equals(rent_type)){
                params.put("wt_fl", "0");
            }
            if ("合租".equals(rent_type)){
                params.put("wt_fl", "1");
            }
            if ("日短租".equals(rent_type)){
                params.put("wt_fl", "2");
            }


            FinalHttp fh = new FinalHttp();
            fh.get(JnHouse_Record.Key_push_broker, params, new AjaxCallBack<Object>() {

                @Override
                public void onFailure(Throwable t, int errorNo, String strMsg) {
                    super.onFailure(t, errorNo, strMsg);
                    Log.i("#####", "pushBrokerData失败\t" + errorNo + "\t" + strMsg);
                    Toast.makeText(Rent.this, "pushBrokerData失败" + "\t" + errorNo + "\t" + strMsg, Toast.LENGTH_LONG).show();
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
                                    ToastUtil.makeText(Rent.this, "未登录",
                                            ToastUtil.LENGTH_SHORT)
                                            .setAnimation(R.style.PopToast).show();
                                    break;
                                case -1:
                                    ToastUtil.makeText(Rent.this, "异常",
                                            ToastUtil.LENGTH_SHORT)
                                            .setAnimation(R.style.PopToast).show();
                                    break;
                                case 0:
                                    ToastUtil.makeText(Rent.this, "成功",
                                            ToastUtil.LENGTH_SHORT)
                                            .setAnimation(R.style.PopToast).show();
                                    if (leaseEntity.getData_list().size() == 0){
                                        Toast.makeText(Rent.this,"附近暂无置业顾问",Toast.LENGTH_SHORT).show();
                                        mCheckBox_choose.setChecked(false);
                                        mLinearLayout.setVisibility(View.GONE);
                                    }
                                    if (leaseEntity.getData_list().size() == 1){
                                        broker1_id = leaseEntity.getData_list().get(0).getBroker_id();
                                        getBroker1_name = leaseEntity.getData_list().get(0).getBroker_name();

                                        mImageView_agent_two.setVisibility(View.GONE);
                                        mImageView_agent_three.setVisibility(View.GONE);
                                        mCheckBox_two.setVisibility(View.GONE);
                                        mCheckBox_three.setVisibility(View.GONE);

                                        String myJpgPath0 = leaseEntity.getData_list().get(0).getBroker_avar();
                                        BitmapFactory.Options options0 = new BitmapFactory.Options();
                                        options0.inSampleSize = 2;
                                        Bitmap bm0 = BitmapFactory.decodeFile(myJpgPath0, options0);
                                        mImageView_agent_one.setImageBitmap(bm0);

                                        mLinearLayout.setVisibility(View.VISIBLE);
                                    }
                                    if (leaseEntity.getData_list().size() == 2){
                                        broker1_id = leaseEntity.getData_list().get(0).getBroker_id();
                                        getBroker1_name = leaseEntity.getData_list().get(0).getBroker_name();
                                        broker2_id = leaseEntity.getData_list().get(1).getBroker_id();
                                        getBroker2_name = leaseEntity.getData_list().get(1).getBroker_name();

                                        mImageView_agent_three.setVisibility(View.GONE);
                                        mCheckBox_three.setVisibility(View.GONE);

                                        String myJpgPath0 = leaseEntity.getData_list().get(0).getBroker_avar();
                                        BitmapFactory.Options options0 = new BitmapFactory.Options();
                                        options0.inSampleSize = 2;
                                        Bitmap bm0 = BitmapFactory.decodeFile(myJpgPath0, options0);
                                        mImageView_agent_one.setImageBitmap(bm0);

                                        String myJpgPath1 = leaseEntity.getData_list().get(1).getBroker_avar();
                                        BitmapFactory.Options options1 = new BitmapFactory.Options();
                                        options1.inSampleSize = 2;
                                        Bitmap bm1 = BitmapFactory.decodeFile(myJpgPath1, options1);
                                        mImageView_agent_two.setImageBitmap(bm1);

                                        mLinearLayout.setVisibility(View.VISIBLE);
                                    }
                                    if (leaseEntity.getData_list().size() == 3){
                                        String myJpgPath0 = leaseEntity.getData_list().get(0).getBroker_avar();
                                        BitmapFactory.Options options0 = new BitmapFactory.Options();
                                        options0.inSampleSize = 2;
                                        Bitmap bm0 = BitmapFactory.decodeFile(myJpgPath0, options0);
                                        mImageView_agent_one.setImageBitmap(bm0);

                                        String myJpgPath1 = leaseEntity.getData_list().get(1).getBroker_avar();
                                        BitmapFactory.Options options1 = new BitmapFactory.Options();
                                        options1.inSampleSize = 2;
                                        Bitmap bm1 = BitmapFactory.decodeFile(myJpgPath1, options1);
                                        mImageView_agent_two.setImageBitmap(bm1);

                                        String myJpgPath2 = leaseEntity.getData_list().get(2).getBroker_avar();
                                        BitmapFactory.Options options2 = new BitmapFactory.Options();
                                        options2.inSampleSize = 2;
                                        Bitmap bm2 = BitmapFactory.decodeFile(myJpgPath2, options2);
                                        mImageView_agent_three.setImageBitmap(bm2);

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
            LoginEntity info = (LoginEntity) PreferencesUtils.getObject(Rent.this, "loginEntity");
            if (info == null || info.getUserUUID() == null) {
                ToastUtil.makeText(  this, "请重新登录",
                        ToastUtil.LENGTH_SHORT)
                        .setAnimation(R.style.PopToast).show();
                return;
            }
            AjaxParams params = new AjaxParams();
            params.put("house_id", borough_id);
            params.put("user_id", "");

            FinalHttp fh = new FinalHttp();
            fh.get(JnHouse_Record.Key_Borough_Detail, params, new AjaxCallBack<Object>() {

                @Override
                public void onFailure(Throwable t, int errorNo, String strMsg) {
                    super.onFailure(t, errorNo, strMsg);
                    Log.i("#####", "LeaseEntire\t" + errorNo + "\t" + strMsg);
                    Toast.makeText(Rent.this, "委托失败" + "\t" + errorNo + "\t" + strMsg, Toast.LENGTH_LONG).show();
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
                            borough_lat = map.substring(1,map.indexOf(","));
                            borough_lng = map.substring(map.indexOf(",")+1,map.indexOf(")"));
                            Log.i("#####","borough_lng:"+borough_lng);
                            Log.i("#####","纬borough_lat:"+borough_lat);
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
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rent_choose_cb:
                if (mCheckBox_choose.isChecked()){
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
                if (isChecked_1){
                    broker_list.add(broker1_id);
                }else {
                    broker_list.remove(broker1_id);
                }
                break;
            case R.id.position2_checkbox:
                isChecked_2 = !isChecked_2;
                if (isChecked_2){
                    broker_list.add(broker2_id);
                }else {
                    broker_list.remove(broker2_id);
                }
                break;
            case R.id.position3_checkbox:
                isChecked_3 = !isChecked_3;
                if (isChecked_3){
                    broker_list.add(broker3_id);
                }else {
                    broker_list.remove(broker3_id);
                }
                break;
        }
    }
}