package jnhouse.topwellsoft.com.jnhouse_android.ui.maintab_search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import jnhouse.topwellsoft.com.jnhouse_android.R;

import android.os.Message;

import com.topwellsoft.androidutils.PreferencesUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;

import jnhouse.topwellsoft.com.jnhouse_android.adapter.SearchHistoryAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;
import jnhouse.topwellsoft.com.jnhouse_android.ui.release.Rent;

import jnhouse.topwellsoft.com.jnhouse_android.util.PersonInfo;


/**
 * Created by Administrator on 2016/7/28.
 */
public class MainTabSearchActivity extends FragmentActivity implements View.OnClickListener {
    private ImageView img_back;
    private TextView tv_search;
    public EditText editText;
    public static MyDiaLog diaLog;
    private Spinner spinner;
    private FragmentManager fm;
    private Fragment fragment[] = new Fragment[3];
    private int currentFragment = 0;
    private FrameLayout container;
    private ListView list_history;
    private SearchHistoryAdapter adapter;
    private ArrayList<String> search_history;
    private static final String FLAG = "search_history";
    public static String key = "";
    private Animation showAnimation;
    private Animation hiddenAnimation;
    private String type[] = {"二手房", "新房", "租房"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintab_search);
        initView();
        initDiaLog();
        final String[] mItems = getResources().getStringArray(R.array.spingarr);
        ArrayAdapter<String> _Adapter = new ArrayAdapter<String>(this, R.layout.main_liushuihao_stylespinner, mItems);
        editText.setOnKeyListener(new View.OnKeyListener() {//点击键盘搜索时
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(MainTabSearchActivity.this.getCurrentFocus()
                            .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    ExecuteWhenSerach();
                }
                return false;
            }
        });

    }


    public void ExecuteWhenSerach() {
        key = editText.getText().toString();
        if (editText.getText().toString().equals("")) {

        } else {
            search_history.remove(search_history.size() - 1);
            search_history.add(editText.getText().toString() + "——" + type[currentFragment]);
            PreferencesUtils.putObject(MainTabSearchActivity.this, FLAG, search_history);
            Sendmessage(currentFragment);
            list_history.setVisibility(View.GONE);
            container.setVisibility(View.VISIBLE);
            container.startAnimation(showAnimation);
        }

    }

    public void Sendmessage(int i) {
        Message msg;
        switch (i) {
            case 0:
                msg = new Message();
                msg.arg1 = 1000;
                fm.beginTransaction().replace(R.id.maintab_container, fragment[0]).commit();
                ((UsedHouseFragment) fragment[0]).threeHandler.sendMessage(msg);
                break;
            case 1:
                msg = new Message();
                msg.arg1 = 1001;
                fm.beginTransaction().replace(R.id.maintab_container, fragment[1]).commit();
                ((NewHouseFragment) fragment[1]).oneHandler.sendMessage(msg);
                break;
            case 2:
                msg = new Message();
                msg.arg1 = 1002;
                fm.beginTransaction().replace(R.id.maintab_container, fragment[2]).commit();
                ((RentHouseFragment) fragment[2]).twohandler.sendMessage(msg);
                break;
        }
    }

    public void initView() {
        showAnimation = AnimationUtils.loadAnimation(MainTabSearchActivity.this, R.anim.slide_in_animation);
        hiddenAnimation = AnimationUtils.loadAnimation(MainTabSearchActivity.this, R.anim.slide_out_animation);

        fm = getSupportFragmentManager();

        fragment[0] = new UsedHouseFragment();
        fragment[1] = new NewHouseFragment();
        fragment[2] = new RentHouseFragment();

        spinner = (Spinner) this.findViewById(R.id.maintab_spinner);
        editText = (EditText) this.findViewById(R.id.edit_maintab);
        img_back = (ImageView) this.findViewById(R.id.search_maintab_back);
        tv_search = (TextView) this.findViewById(R.id.search_maintab_right);
        container = (FrameLayout) this.findViewById(R.id.maintab_container);
        list_history = (ListView) this.findViewById(R.id.list_history);

        tv_search.setOnClickListener(this);
        img_back.setOnClickListener(this);
        editText.setOnClickListener(this);
        fm.beginTransaction().add(R.id.maintab_container, fragment[0]).commit();
        search_history = (ArrayList<String>) PreferencesUtils.getObject(MainTabSearchActivity.this, FLAG);
        if (search_history == null || search_history.size() == 0) {
            search_history = new ArrayList<String>();
            search_history.add("暂无搜索记录");
        }
        adapter = new SearchHistoryAdapter(MainTabSearchActivity.this, search_history);
        list_history.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentFragment = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        list_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//listview 的点击事件
                if (position < adapter.getCount() - 1) {
                    key = adapter.getItem(position);
                    Log.i("#####", key);
                    String str[] = key.split("——");
                    Log.i("#####", str[1]);
                    key = str[1];
                    editText.setText(str[0]);
                    Sendmessage(getCurrentFragment(str[1]));
                    list_history.setVisibility(View.GONE);
                    container.setVisibility(View.VISIBLE);
                    container.startAnimation(showAnimation);
                } else {
                    search_history.clear();
                    PreferencesUtils.putObject(MainTabSearchActivity.this, FLAG, search_history);
                    search_history.add("暂无搜索记录");
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    public int getCurrentFragment(String str) {
        int result = 0;
        if (str.equals("二手房")) {
            result = 0;
        } else if (str.equals("新房")) {
            result = 1;
        } else if (str.equals("租房")) {
            result = 2;
        }
        return result;
    }

    public void initDiaLog() {
        diaLog = new MyDiaLog(MainTabSearchActivity.this);
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_maintab_back:
                finish();
                break;
            case R.id.search_maintab_right:
                ExecuteWhenSerach();

                break;
            case R.id.edit_maintab:
                if (container.isShown()) {
                    container.setVisibility(View.GONE);
                }
                if (!list_history.isShown()) {
                    list_history.setVisibility(View.VISIBLE);
                    list_history.startAnimation(showAnimation);
                }
                break;
            default:
                break;
        }
    }

}
