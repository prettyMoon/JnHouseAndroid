package jnhouse.topwellsoft.com.jnhouse_android.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.StyleUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.IndexZXListAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.TravelingAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.ChannelEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.FilterData;
import jnhouse.topwellsoft.com.jnhouse_android.model.FilterEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.FilterTwoEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.HomeIndexEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.HomeZXEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.OperationEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.TravelingEntity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.consult.ConsultDetailActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.maintab_search.MainTabSearchActivity;
import jnhouse.topwellsoft.com.jnhouse_android.util.ColorUtil;
import jnhouse.topwellsoft.com.jnhouse_android.util.DensityUtil;
import jnhouse.topwellsoft.com.jnhouse_android.util.ModelUtil;
import jnhouse.topwellsoft.com.jnhouse_android.view.FilterView;
import jnhouse.topwellsoft.com.jnhouse_android.view.FixedGridView;
import jnhouse.topwellsoft.com.jnhouse_android.view.HeaderAdViewView;
import jnhouse.topwellsoft.com.jnhouse_android.view.HeaderAvgViewView;
import jnhouse.topwellsoft.com.jnhouse_android.view.HeaderChannelViewView;
import jnhouse.topwellsoft.com.jnhouse_android.view.HeaderDividerViewView;
import jnhouse.topwellsoft.com.jnhouse_android.view.HeaderFilterViewView;
import jnhouse.topwellsoft.com.jnhouse_android.view.HeaderOperationViewView;
import jnhouse.topwellsoft.com.jnhouse_android.view.HeaderZinxunViewView;
import jnhouse.topwellsoft.com.jnhouse_android.view.SmoothListView.IndexSmoothListView;
import jnhouse.topwellsoft.com.jnhouse_android.view.SmoothListView.SmoothListView;

/**
 * Created by Administrator on 16-5-22.
 */
public class MainActivity extends Fragment implements IndexSmoothListView.ISmoothListViewListener {

    IndexSmoothListView smoothListView;
//    RelativeLayout rlBar;
//    TextView tvTitle;
//    View viewTitleBg;
//    View viewActionMoreBg;
//    FrameLayout flActionMore;

    private Context mContext;
    private Activity mActivity;
    private int mScreenHeight; // 屏幕高度
    private LinearLayout layout_search;
    private EditText editText;
    private List<String> adList = new ArrayList<>(); // 广告数据
    private List<ChannelEntity> channelList = new ArrayList<>(); // 频道数据
    private List<OperationEntity> operationList = new ArrayList<>(); // 运营数据
    private List<TravelingEntity> travelingList = new ArrayList<>(); // ListView数据
    private List<HomeZXEntity> zixunList = new ArrayList<>();
    private List<String> dividerList = new ArrayList<>();

    private HeaderAdViewView listViewAdHeaderView; // 广告视图
    private HeaderChannelViewView headerChannelView; // 频道视图

    private FilterData filterData;// 筛选数据
    private TravelingAdapter mAdapter; // 主页数据

    private View itemHeaderAdView; // 从ListView获取的广告子View
    private View itemHeaderFilterView; // 从ListView获取的筛选子View
    private boolean isScrollIdle = true; // ListView是否在滑动
    private boolean isStickyTop = false; // 是否吸附在顶部
    private boolean isSmooth = false; // 没有吸附的前提下，是否在滑动
    private int titleViewHeight = 50; // 标题栏的高度
    private int filterPosition = -1; // 点击FilterView的位置：分类(0)、排序(1)、筛选(2)

    private int adViewHeight = 180; // 广告视图的高度
    private int adViewTopSpace; // 广告视图距离顶部的距离

    private int filterViewPosition = 4; // 筛选视图的位置
    private int filterViewTopSpace; // 筛选视图距离顶部的距离

    private View rootView;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        if (rootView == null) {
            rootView = inflater.inflate(R.layout.activity_main, container, false);
            smoothListView = (IndexSmoothListView) rootView.findViewById(R.id.listView);
            layout_search = (LinearLayout) rootView.findViewById(R.id.layout_search);
            editText = (EditText) rootView.findViewById(R.id.edit_ttt);
            layout_search .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), MainTabSearchActivity.class);
                    startActivity(intent);
                }
            });
            editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), MainTabSearchActivity.class);
                    startActivity(intent);
                }
            });
            StyleUtils.initStatusBar(getActivity().getWindow());
            initData();
//            initView();
            initListener();
            getPDAServerData();
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }



    private void initData() {

        mContext = getActivity();
        mActivity = getActivity();
        mScreenHeight = DensityUtil.getWindowHeight(getActivity());
        // 广告数据
//        adList = ModelUtil.getAdData();
        // 频道数据
        channelList = ModelUtil.getChannelData();
//        // 运营数据
        operationList = ModelUtil.getOperationData();
//        // ListView数据
//        travelingList = ModelUtil.getTravelingData();
    }

    private void initView() {

        // 设置广告数据
        listViewAdHeaderView = new HeaderAdViewView(getActivity());
        listViewAdHeaderView.fillView(adList, smoothListView);

        //设置频道数据
        headerChannelView = new HeaderChannelViewView(getActivity());
        headerChannelView.fillView(channelList, smoothListView);

        mAdapter = new TravelingAdapter(getActivity(), zixunList);
        mAdapter.setData(ModelUtil.getZiXnNoDataEntity(0));
        smoothListView.setAdapter(mAdapter);

        filterViewPosition = smoothListView.getHeaderViewsCount() - 1;
    }

    private void initListener() {

        smoothListView.setRefreshEnable(false);
        smoothListView.setLoadMoreEnable(false);
        smoothListView.setSmoothListViewListener(this);
        smoothListView.setOnScrollListener(new SmoothListView.OnSmoothScrollListener() {
            @Override
            public void onSmoothScrolling(View view) {
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                isScrollIdle = (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (isScrollIdle && adViewTopSpace < 0) return;

                // 获取广告头部View、自身的高度、距离顶部的高度
                if (itemHeaderAdView == null) {
                    itemHeaderAdView = smoothListView.getChildAt(1 - firstVisibleItem);
                }
                if (itemHeaderAdView != null) {
                    adViewTopSpace = DensityUtil.px2dip(mContext, itemHeaderAdView.getTop());
                    adViewHeight = DensityUtil.px2dip(mContext, itemHeaderAdView.getHeight());
                }

                // 获取筛选View、距离顶部的高度
                if (itemHeaderFilterView == null) {
                    itemHeaderFilterView = smoothListView.getChildAt(filterViewPosition - firstVisibleItem);
                }
                if (itemHeaderFilterView != null) {
                    filterViewTopSpace = DensityUtil.px2dip(mContext, itemHeaderFilterView.getTop());
                }

                // 处理标题栏颜色渐变
//                handleTitleBarColorEvaluate();
            }
        });
        smoothListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                HomeZXEntity homeZXEntity = mAdapter.getItem(i - 3);
                Intent intent = new Intent(mContext, ConsultDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", homeZXEntity.getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    // 填充数据
    private void fillAdapter(List<HomeZXEntity> list) {
        if (list == null || list.size() == 0) {
            smoothListView.setLoadMoreEnable(false);
            int height = mScreenHeight - DensityUtil.dip2px(mContext, 95); // 95 = 标题栏高度 ＋ FilterView的高度
            mAdapter.setData(ModelUtil.getZiXnNoDataEntity(0));
        } else {
            smoothListView.setLoadMoreEnable(list.size() > TravelingAdapter.ONE_REQUEST_COUNT);
            mAdapter.setData(list);
        }
    }

    // 处理标题栏颜色渐变
//    private void handleTitleBarColorEvaluate() {
//        float fraction;
//        if (adViewTopSpace > 0) {
//            fraction = 1f - adViewTopSpace * 1f / 60;
//            if (fraction < 0f) fraction = 0f;
//            rlBar.setAlpha(fraction);
//            return;
//        }
//
//        float space = Math.abs(adViewTopSpace) * 1f;
//        fraction = space / (adViewHeight - titleViewHeight);
//        if (fraction < 0f) fraction = 0f;
//        if (fraction > 1f) fraction = 1f;
//        rlBar.setAlpha(1f);
//
//        if (fraction >= 1f) {
//            isStickyTop = true;
//            viewTitleBg.setAlpha(0f);
//            viewActionMoreBg.setAlpha(0f);
//            rlBar.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
//        } else {
//            viewTitleBg.setAlpha(1f - fraction);
//            viewActionMoreBg.setAlpha(1f - fraction);
//            rlBar.setBackgroundColor(ColorUtil.getNewColorByStartEndColor(mContext, fraction, R.color.transparent, R.color.orange));
//        }
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (listViewAdHeaderView != null) {
            listViewAdHeaderView.stopADRotate();
        }
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                smoothListView.stopRefresh();
                smoothListView.setRefreshTime("刚刚");
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                smoothListView.stopLoadMore();
            }
        }, 2000);
    }


    public void getPDAServerData() {

        AjaxParams params = new AjaxParams();
        FinalHttp fh = new FinalHttp();
        fh.get(JnHouse_Record.Key_home_index, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                initView();
                mAdapter.setData(ModelUtil.getZiXnNoDataEntity(0));
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
                    HomeIndexEntity indexEntity = gson.fromJson(t.toString(), new TypeToken<HomeIndexEntity>() {
                    }.getType());
                    HeaderAdViewView.gg_list = indexEntity.getGg_list();
                    if (indexEntity != null) {
                        adList.clear();
                        for (int i = 0; i < indexEntity.getGg_list().size(); i++) {
                            adList.add(indexEntity.getGg_list().get(i).getPath());
                        }
                        initView();
                        // 设置ListView数据
                        zixunList = indexEntity.getZx_list();
                        smoothListView.setLoadMoreEnable(zixunList.size() > TravelingAdapter.ONE_REQUEST_COUNT);
                        mAdapter.setData(zixunList);
                        headerChannelView.setData(Float.parseFloat(indexEntity.getPrice()), Float.parseFloat(indexEntity.getVolume()));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
