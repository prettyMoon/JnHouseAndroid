package jnhouse.topwellsoft.com.jnhouse_android.ui.release;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import jnhouse.topwellsoft.com.jnhouse_android.util.EventCommunityUtil;
import jnhouse.topwellsoft.com.jnhouse_android.util.EventUtil;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

public class Buy extends AppCompatActivity implements View.OnClickListener{
    private EditText mEditText_community,mEditText_location,mEditText_price,mEditText_measure,mEditText_demand,mEditText_description,mEditText_contacts,mEditText_phone,mEditText_time;
    private Spinner mSpinner_room,mSpinner_office,mSpinner_kitchen,mSpinner_toilet,mSpinner_tpye;
    private ImageView mImageView_agent_one,mImageView_agent_two,mImageView_agent_three;
    private RadioGroup mRadioGroup_sex;
    private CheckBox mCheckBox_choose;
    private Button mButton_back,mButton_release;
    private Button mButton_description_community,mButton_choose_community;
    private String sex;
    private LinearLayout mLinearLayout;
    private TextView mTextView_city,mTextView_cityarea,mTextView_cityarea2;
    private EditText mEditText_title,mEditText_time_start,mEditText_time_end;
    private Spinner mSpinner_want_price;

    private String borough_id;
    private String borough_name;
    private String sell_type;
    private String city_id;
    private String cityarea_id;
    private String cityarea2_id;
    private String want_price;
    private String want_room;
    private String want_hall;
    private String want_toilet;
    private String house_desc;
    private String house_title;
    private String user_name;
    private String user_id;
    private String user_phone;
    private String tel_time_start;
    private String tel_time_end;
    private List<String> borker_id = new ArrayList<String>();

    /*private String house_area;
    private String house_topfloor;
    private String house_floor;
    private String house_toward;
    private String house_fitment;
    private String house_age;*/

    private LoginEntity info;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        EventBus.getDefault().register(this);
        init();
    }

    private void init() {



        mSpinner_want_price = (Spinner) findViewById(R.id.buy_want_price_spinner);
        mLinearLayout = (LinearLayout) findViewById(R.id.buy_checkbox_linearlayout);
//        mTextView_city = (TextView) findViewById(R.id.city_id_tv);
        mTextView_cityarea = (TextView) findViewById(R.id.cityarea_id_tv);
//        mTextView_cityarea2 = (TextView) findViewById(R.id.cityarea2_id_tv);
        mButton_choose_community = (Button) findViewById(R.id.buy_choosecommunity_bt);
        mButton_description_community = (Button) findViewById(R.id.buy_description_bt);
//        mEditText_price = (EditText) findViewById(R.id.buy_price_et);
//        mEditText_measure = (EditText) findViewById(R.id.buy_measure_et);
//        mEditText_demand = (EditText) findViewById(R.id.buy_demand_et);
        mEditText_contacts = (EditText) findViewById(R.id.buy_contacts_et);
        mEditText_phone = (EditText) findViewById(R.id.buy_phone_et);
        mEditText_time_start = (EditText) findViewById(R.id.buy_time_start_et);
        mEditText_time_end = (EditText) findViewById(R.id.buy_time_end_et);
        mEditText_title = (EditText) findViewById(R.id.buy_name_et);
        mSpinner_room = (Spinner) findViewById(R.id.buy_room_spinner);
        mSpinner_office = (Spinner) findViewById(R.id.buy_office_spinner);
//        mSpinner_kitchen = (Spinner) findViewById(R.id.buy_kitchen_spinner);
        mSpinner_toilet = (Spinner) findViewById(R.id.buy_toilet_spinner);
        mSpinner_tpye = (Spinner) findViewById(R.id.buy_type_spinner);

        mImageView_agent_one = (ImageView) findViewById(R.id.buy_agent_one_ib);
        mImageView_agent_two = (ImageView) findViewById(R.id.buy_agent_two_ib);
        mImageView_agent_three = (ImageView) findViewById(R.id.buy_agent_three_ib);

        mRadioGroup_sex = (RadioGroup) findViewById(R.id.sex_group);

        mCheckBox_choose = (CheckBox) findViewById(R.id.buy_choose_cb);

        mButton_back = (Button) findViewById(R.id.buy_back_button);
        mButton_release = (Button) findViewById(R.id.buy_release_bt);

        info = (LoginEntity) PreferencesUtils.getObject(this,"loginEntity");
        mEditText_contacts.setText(info.getRealname());
        mEditText_phone.setText(info.getUsername());

        mCheckBox_one = (CheckBox) findViewById(R.id.position1_checkbox);
        mCheckBox_two = (CheckBox) findViewById(R.id.position2_checkbox);
        mCheckBox_three = (CheckBox) findViewById(R.id.position3_checkbox);

        mCheckBox_one.setOnClickListener(this);
        mCheckBox_two.setOnClickListener(this);
        mCheckBox_three.setOnClickListener(this);

        mCheckBox_choose.setOnClickListener(this);
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
                want_room = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Buy.this, "未选中", Toast.LENGTH_SHORT).show();
            }
        });
        mSpinner_office.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                want_hall = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Buy.this, "未选中", Toast.LENGTH_SHORT).show();
            }
        });
        /*mSpinner_kitchen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String kitchen = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Buy.this, "未选中", Toast.LENGTH_SHORT).show();
            }
        });*/
        mSpinner_toilet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                want_toilet = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Buy.this, "未选中", Toast.LENGTH_SHORT).show();
            }
        });
        mSpinner_tpye.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sell_type = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Buy.this, "未选中", Toast.LENGTH_SHORT).show();
            }
        });
        mSpinner_want_price.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                want_price = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Buy.this, "未选中", Toast.LENGTH_SHORT).show();
            }
        });
        mButton_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mButton_choose_community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Buy.this,ChooseCommunity.class);
                startActivity(intent);
            }
        });
        mButton_description_community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_description = new Intent();
                intent_description.setClass(Buy.this,Communitydescription.class);
                startActivity(intent_description);
            }
        });
        mButton_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPDAServerData();
            }
        });
        mLinearLayout.setVisibility(View.GONE);
    }

    public void getPDAServerData() {
        AjaxParams params = new AjaxParams();
        params.put("userUUID", info.getUserUUID());
        params.put("borough_id", borough_id);
        params.put("borough_name", mButton_choose_community.getText().toString().trim());
        if ("二手房".equals(sell_type)){
            params.put("sell_type", "0");
        }
        if ("写字楼".equals(sell_type)){
            params.put("sell_type", "1");
        }
        if ("商铺".equals(sell_type)){
            params.put("sell_type", "2");
        }
        params.put("city_id", city);
        params.put("cityarea_id", cityarea);
        params.put("cityarea2_id", cityarea2);
        if ("30万以下".equals(want_price)){
            params.put("want_price", "0");
        }
        if ("30-60万".equals(want_price)){
            params.put("want_price", "1");
        }
        if ("60-100万".equals(want_price)){
            params.put("want_price", "2");
        }
        if ("100-150万".equals(want_price)){
            params.put("want_price", "3");
        }
        if ("150-200万".equals(want_price)){
            params.put("want_price", "4");
        }
        if ("200万以上".equals(want_price)){
            params.put("want_price", "5");
        }
        params.put("want_room", want_room);
        params.put("want_hall", want_hall);
        params.put("want_toilet", want_toilet);
        params.put("house_desc", mButton_description_community.getText().toString().trim());
        params.put("house_title", mEditText_title.getText().toString().trim());
        params.put("user_name", info.getRealname().toString());
        Log.e("#####","info.getRealname():"+info.getRealname());
        params.put("user_id", info.getUser_id().toString());
        params.put("user_phone", info.getUsername().toString());
        params.put("tel_time_start", mEditText_time_start.getText().toString().trim());
        params.put("tel_time_end", mEditText_time_end.getText().toString().trim());
        params.put("borker_id", "");


        FinalHttp fh = new FinalHttp();
        fh.get(JnHouse_Record.Key_buy, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                ToastUtil.makeText(Buy.this, "委托失败",
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

                try {

                    JSONObject jsonObject = new JSONObject(t.toString());
                    Gson gson = new Gson();
                    LeaseEntity leaseEntity = gson.fromJson(t.toString(), new TypeToken<LeaseEntity>() {
                    }.getType());

                    if (leaseEntity != null) {

                        switch (leaseEntity.getCode()) {

                            case 1:
                                ToastUtil.makeText(Buy.this, "未登录",
                                        ToastUtil.LENGTH_SHORT)
                                        .setAnimation(R.style.PopToast).show();
                                break;
                            case -1:
                                ToastUtil.makeText(Buy.this, "异常",
                                        ToastUtil.LENGTH_SHORT)
                                        .setAnimation(R.style.PopToast).show();
                                break;
                            case 0:
                                ToastUtil.makeText(Buy.this, "委托成功",
                                        ToastUtil.LENGTH_SHORT)
                                        .setAnimation(R.style.PopToast).show();
                                break;

                            case 904:
                                ToastUtil.makeText(Buy.this, "委托失败",
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
        city = eventUtil.getCity();
        cityarea = eventUtil.getCityarea();
        cityarea2 = eventUtil.getCityarea2();
        mButton_choose_community.setText(msg);
//        mTextView_city.setText(city);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void pushBrokerData() {

        try {

            AjaxParams params = new AjaxParams();
            params.put("borough_id", borough_id);
            params.put("trade_id", cityarea2);
            params.put("borough_lng", borough_lng);
            params.put("borough_lat", borough_lat);
            params.put("wt_type", "3");

            if ("二手房".equals(sell_type)){
                params.put("wt_fl", "0");
            }
            if ("写字楼".equals(sell_type)){
                params.put("wt_fl", "1");
            }
            if ("商铺".equals(sell_type)){
                params.put("wt_fl", "2");
            }


            FinalHttp fh = new FinalHttp();
            fh.get(JnHouse_Record.Key_push_broker, params, new AjaxCallBack<Object>() {

                @Override
                public void onFailure(Throwable t, int errorNo, String strMsg) {
                    super.onFailure(t, errorNo, strMsg);
                    Log.i("#####", "pushBrokerData失败\t" + errorNo + "\t" + strMsg);
                    Toast.makeText(Buy.this, "pushBrokerData失败" + "\t" + errorNo + "\t" + strMsg, Toast.LENGTH_LONG).show();
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
                                    ToastUtil.makeText(Buy.this, "未登录",
                                            ToastUtil.LENGTH_SHORT)
                                            .setAnimation(R.style.PopToast).show();
                                    break;
                                case -1:
                                    ToastUtil.makeText(Buy.this, "异常",
                                            ToastUtil.LENGTH_SHORT)
                                            .setAnimation(R.style.PopToast).show();
                                    break;
                                case 0:
                                    ToastUtil.makeText(Buy.this, "成功",
                                            ToastUtil.LENGTH_SHORT)
                                            .setAnimation(R.style.PopToast).show();
                                    if (leaseEntity.getData_list().size() == 0){
                                        Toast.makeText(Buy.this,"附近暂无置业顾问",Toast.LENGTH_SHORT).show();
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

            AjaxParams params = new AjaxParams();
            params.put("house_id", borough_id);
            params.put("user_id", "");

            FinalHttp fh = new FinalHttp();
            fh.get(JnHouse_Record.Key_Borough_Detail, params, new AjaxCallBack<Object>() {

                @Override
                public void onFailure(Throwable t, int errorNo, String strMsg) {
                    super.onFailure(t, errorNo, strMsg);
                    Log.i("#####", "LeaseEntire\t" + errorNo + "\t" + strMsg);
                    Toast.makeText(Buy.this, "委托失败" + "\t" + errorNo + "\t" + strMsg, Toast.LENGTH_LONG).show();
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buy_choose_cb:
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
