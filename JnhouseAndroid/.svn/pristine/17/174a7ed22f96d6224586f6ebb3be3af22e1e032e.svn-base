package jnhouse.topwellsoft.com.jnhouse_android.ui.Ren_house;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.StyleUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.RentingHouseAdapter;

import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.FilerRentData;
import jnhouse.topwellsoft.com.jnhouse_android.model.FilterData;
import jnhouse.topwellsoft.com.jnhouse_android.model.FilterEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.FilterTwoEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.RentHouseList;
import jnhouse.topwellsoft.com.jnhouse_android.model.RentingHouseEntity;

import jnhouse.topwellsoft.com.jnhouse_android.model.SecondHouseList;

import jnhouse.topwellsoft.com.jnhouse_android.util.CustomProgressDialog;
import jnhouse.topwellsoft.com.jnhouse_android.util.DateTimeUtils;
import jnhouse.topwellsoft.com.jnhouse_android.util.ModelUtil;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;
import jnhouse.topwellsoft.com.jnhouse_android.view.FilterRentView;
import jnhouse.topwellsoft.com.jnhouse_android.view.FilterView;
import jnhouse.topwellsoft.com.jnhouse_android.view.HeaderFilterViewView;
import jnhouse.topwellsoft.com.jnhouse_android.view.SmoothListView.SmoothListView;

public class IndexRentHouse extends AppCompatActivity implements SmoothListView.ISmoothListViewListener, View.OnClickListener {
    private SmoothListView smoothListView;
    private FilterRentView fvTopFilter;
    private Context mContext;
    private Activity mActivity;
    private FilterData filterData;
    private LinearLayout second_detail_progressbar;
    private EditText index_second_house_title_search;
    private TextView search_maintab_right;
    private ImageView index_second_house_title_back;
    private HeaderFilterViewView headerFilterViewView; // 分类筛选视图
    private RentingHouseAdapter mAdapter; // 主页数据
    private SecondHouseList secondHouseList;
    private List<RentingHouseEntity> rentHouseEntity = new ArrayList<>();
    private CustomProgressDialog progressDialog;
    private int filterPosition = -1; // 点击FilterView的位置：分类(0)、排序(1)、筛选(2)
    private int pageIndex = 1;
    private String area_id = "";//城区
    private String trade_id = "";//商圈
    private String price_type = "";//价格
    private String house_room = "";//房型
    private String price_min = "";//总价最低值
    private String price_max = "";//总价最高值
    private String house_feature = "";//房源特色(带,字符串)
    private String list_sort = "";//排序
    private String house_toward = "";//朝向
    private String area_type = "";//面积类型
    private String house_fitment = "";//装修
    private String house_type = "";//类型
    private String key_name = "";//关键字检索
    private String rent_type;
    private List<FilterEntity> featureList = new ArrayList<FilterEntity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tp_rent_house);
        fvTopFilter = (FilterRentView) findViewById(R.id.index_rent_house_top_filter);
        second_detail_progressbar = (LinearLayout) findViewById(R.id.second_detail_progressbar);
        index_second_house_title_search = (EditText) findViewById(R.id.index_second_house_title_search);
        index_second_house_title_search.setCursorVisible(true);
        search_maintab_right = (TextView) findViewById(R.id.search_maintab_right);
        search_maintab_right.setOnClickListener(this);
        index_second_house_title_back = (ImageView) findViewById(R.id.index_second_house_title_back);
        smoothListView = (SmoothListView) findViewById(R.id.ren_house_listView);
        smoothListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RentingHouseEntity rentingHouseEntity = mAdapter.getItem(i - 1);
                Intent intent = new Intent();
                intent.putExtra("houseId", rentingHouseEntity.getId());
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setClass(IndexRentHouse.this, IndexRentHouseDetail.class);
                startActivity(intent);
            }
        });
        index_second_house_title_back.setOnClickListener(this);
        StyleUtils.initStatusBar(getWindow());
        initData();
        initView();
        initListener();
        getPDAServerData(JnHouse_Record.Key_rent_house_index, "0");
    }



    private void initData() {

        mContext = this;
        mActivity = this;
        // 筛选数据
        filterData = new FilterData();
        filterData.setCategory(ModelUtil.getCategoryData());
        filterData.setSorts(ModelUtil.getListRentData());
        filterData.setRentType(ModelUtil.getRentType());
        filterData.setToward(ModelUtil.getHouseTowardData());
        filterData.setAreaType(ModelUtil.getAreaTypeData());
        filterData.setAgeType(ModelUtil.getFilterData());
        filterData.setHouseType(ModelUtil.getHouseTypeData());
        filterData.setListSort(ModelUtil.getListRentSortData());
        filterData.setFitment(ModelUtil.getHouseFitment());

    }

    private void initView() {
        index_second_house_title_search.setVisibility(View.VISIBLE);
        fvTopFilter.setFilterData(mActivity, filterData);
        mAdapter = new RentingHouseAdapter(this, rentHouseEntity);
        smoothListView.setAdapter(mAdapter);
    }

    private void initListener() {

        // (真正的)筛选视图点击
        fvTopFilter.setOnFilterClickListener(new FilterRentView.OnFilterClickListener() {
            @Override
            public void onFilterClick(int position) {
                filterPosition = position;
                fvTopFilter.showFilterLayout(position);
            }
        });

        // 区域Item点击
        fvTopFilter.setOnItemCategoryClickListener(new FilterRentView.OnItemCategoryClickListener() {
            @Override
            public void onItemCategoryClick(FilterTwoEntity entity) {
                second_detail_progressbar.setVisibility(View.VISIBLE);
                smoothListView.setVisibility(View.GONE);
                pageIndex = 1;
                area_id = entity.getSelectedFilterEntity().getValue();
//                progressDialog.show();
                getPDAServerData(JnHouse_Record.Key_rent_house_index, "1");
            }
        });

        // 价格Item点击
        fvTopFilter.setOnItemSortClickListener(new FilterRentView.OnItemSortClickListener() {
            @Override
            public void onItemSortClick(FilterEntity entity) {
                second_detail_progressbar.setVisibility(View.VISIBLE);
                smoothListView.setVisibility(View.GONE);
                pageIndex = 1;
                price_type = entity.getValue();

//           progressDialog.show();
                getPDAServerData(JnHouse_Record.Key_rent_house_index, "1");
            }
        });

        // 价格Item点击 最大最小
        fvTopFilter.setOnClickListener(new FilterRentView.OnPriceFilterClickListener() {
            @Override
            public void onPriceFilterClick(String min, String max) {
                second_detail_progressbar.setVisibility(View.VISIBLE);
                smoothListView.setVisibility(View.GONE);
                pageIndex = 1;
                price_type = "8";
                price_min = min;
                price_max = max;
//                progressDialog.show();
                getPDAServerData(JnHouse_Record.Key_rent_house_index, "1");
            }
        });

        // 出租类型Item点击
        fvTopFilter.setOnItemFilterClickListener(new FilterRentView.OnItemFilterClickListener() {
            @Override
            public void onItemFilterClick(FilterEntity entity) {
                second_detail_progressbar.setVisibility(View.VISIBLE);
                smoothListView.setVisibility(View.GONE);
                pageIndex = 1;
                rent_type = entity.getValue();
                getPDAServerData(JnHouse_Record.Key_rent_house_index, "1");
            }
        });
        //关键字检索 键盘回车监听
        index_second_house_title_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH || (keyEvent != null && keyEvent.getKeyCode() == keyEvent.KEYCODE_ENTER)) {
                    second_detail_progressbar.setVisibility(View.VISIBLE);
                    smoothListView.setVisibility(View.GONE);
                    pageIndex = 1;
                    key_name = index_second_house_title_search.getText().toString();
                    getPDAServerData(JnHouse_Record.Key_rent_house_index, "1");
                    return true;
                }
                return false;
            }
        });
        fvTopFilter.setOnClickListener(new FilterRentView.OnMoreFilterClickListener() {
            @Override
            public void onMoreFilterClick(List<String> mList1, List<String> mList2, List<String> mList3, List<String> mList4, List<String> mList5, List<String> mList6,List<String>mList7) {

                second_detail_progressbar.setVisibility(View.VISIBLE);
                smoothListView.setVisibility(View.GONE);
                pageIndex = 1;
                house_feature = "";
                if (mList1 != null && mList1.size() > 0) {
                    for (int i = 0; i < mList1.size(); i++) {
                        house_feature += mList1.get(i) + ",";
                    }
                }else {
                    house_feature="";
                }
                if (mList2 != null && mList2.size() > 0) {
                    house_toward = mList2.get(0);
                }else {
                    house_toward="";
                }
                if (mList3 != null && mList3.size() > 0) {
                    area_type = mList3.get(0);
                }else {
                    area_type="";
                }
                if (mList4 != null && mList4.size() > 0) {
                    house_room = mList4.get(0);
                }else {
                    house_room="";
                }
                if (mList5 != null && mList5.size() > 0) {
                    house_type = mList5.get(0);
                }else {
                    house_type="";
                }
                if (mList6 != null && mList6.size() > 0) {
                    list_sort = mList6.get(0);
                }else {
                    list_sort="";
                }
                if (mList7 != null && mList7.size() > 0) {
                    house_fitment = mList7.get(0);
                }else {
                    house_fitment="";
                }
                getPDAServerData(JnHouse_Record.Key_rent_house_index, "1");
            }
        });
        smoothListView.setRefreshEnable(true);
        smoothListView.setLoadMoreEnable(true);
        smoothListView.setSmoothListViewListener(this);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                smoothListView.stopRefresh();
                smoothListView.setRefreshTime(DateTimeUtils.getTimeStr(DateTimeUtils.getNowTime(), "yyyy-MM-dd hh:mm:ss"));
                pageIndex = 1;
                getPDAServerData(JnHouse_Record.Key_rent_house_list, "1");
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                smoothListView.stopLoadMore();
                pageIndex = pageIndex + 1;
                getPDAServerData(JnHouse_Record.Key_rent_house_list, "0");
            }
        }, 2000);
    }


    public void getPDAServerData(String url, final String flag) {
        AjaxParams params = new AjaxParams();
        params.put("pageIndex", pageIndex + "");
        params.put("key_name", key_name);//关键字检索
        params.put("area_id", area_id);//城区
        params.put("trade_id", trade_id);//商圈
        params.put("money_type", price_type);//价格
        params.put("price_min", price_min);//最小
        params.put("price_max", price_max);//最大
        params.put("house_room", house_room);//房型
        if (house_feature != null && !house_feature.equals("")) {
            params.put("house_feature", house_feature.substring(0, house_feature.length() - 1));//房源特色(带,字符串)
        } else {
            params.put("house_feature", "");//房源特色(带,字符串)
        }
        params.put("list_sort", list_sort);//排序
        params.put("house_toward", house_toward);//朝向
        params.put("area_type", area_type);//面积类型
        params.put("house_fitment", house_fitment);//装修
        params.put("house_type", house_type);//类型
        params.put("rent_type",rent_type);//
        FinalHttp fp = new FinalHttp();
        fp.get(url, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {

            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
            }

            @Override
            public void onSuccess(Object o) {
                try {
                    JSONObject json = new JSONObject(o.toString());
                    Gson gson = new Gson();
                    RentHouseList rentHouseList = gson.fromJson(o.toString(), new TypeToken<RentHouseList>() {
                    }.getType());
                    if (rentHouseList.getData_list() != null && rentHouseList.getData_list().size() > 0) {
                        second_detail_progressbar.setVisibility(View.GONE);
                        smoothListView.setVisibility(View.VISIBLE);
                        smoothListView.setLoadMoreEnable(rentHouseList.getData_list().size() > mAdapter.ONE_REQUEST_COUNT);
                        if (flag.equals("0")) {
                            mAdapter.setData(rentHouseList.getData_list());
                        } else {
                            mAdapter.setDataFilter(rentHouseList.getData_list());
                        }
                    } else {
                        second_detail_progressbar.setVisibility(View.VISIBLE);
                        if (pageIndex > 1) {
                            pageIndex = pageIndex - 1;
                        }
                        ToastUtil.makeText(IndexRentHouse.this, "没有符合条件的房源",
                                ToastUtil.LENGTH_SHORT)
                                .setAnimation(R.style.PopToast).show();
                        second_detail_progressbar.setVisibility(View.GONE);

                    }
                    if (flag.equals("0")) {
                        if (rentHouseList.getFeature_list() != null && rentHouseList.getFeature_list().size() > 0) {

                            for (int i = 0; i < rentHouseList.getFeature_list().size(); i++) {
                                FilterEntity filterEntity = new FilterEntity();
                                filterEntity.setKey(rentHouseList.getFeature_list().get(i).getDi_caption());
                                filterEntity.setValue(rentHouseList.getFeature_list().get(i).getDi_value());
                                featureList.add(filterEntity);
                            }
                        }
                        filterData.setFeature(featureList);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.index_second_house_title_back:
                onBackPressed();
                break;
            case R.id.search_maintab_right:
                second_detail_progressbar.setVisibility(View.VISIBLE);
                smoothListView.setVisibility(View.GONE);
                pageIndex = 1;
                key_name = index_second_house_title_search.getText().toString();
                getPDAServerData(JnHouse_Record.Key_rent_house_list, "1");
                break;

        }
    }
}
