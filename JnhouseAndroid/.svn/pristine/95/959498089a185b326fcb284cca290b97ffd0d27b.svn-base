package jnhouse.topwellsoft.com.jnhouse_android.view;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.FilterLeftAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.FilterOneAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.FilterRightAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.FilterTypeAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.HouseTowardAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.model.FilterData;
import jnhouse.topwellsoft.com.jnhouse_android.model.FilterEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.FilterTwoEntity;
import jnhouse.topwellsoft.com.jnhouse_android.util.ModelUtil;

/**
 * Created by Administrator on 16-5-22.
 */
public class FilterView extends LinearLayout implements View.OnClickListener {

    @Bind(R.id.tv_area)
    TextView tvCategory;
    @Bind(R.id.iv_category_arrow)
    ImageView ivCategoryArrow;
    @Bind(R.id.tv_price)
    TextView tvSort;
    @Bind(R.id.tv_more)
    TextView tvMore;
    @Bind(R.id.iv_filter_more)
    ImageView ivMoreArrow;
    @Bind(R.id.iv_sort_arrow)
    ImageView ivSortArrow;
    @Bind(R.id.tv_type)
    TextView tvFilter;
    @Bind(R.id.iv_filter_arrow)
    ImageView ivFilterArrow;
    @Bind(R.id.ll_category)
    LinearLayout llCategory;
    @Bind(R.id.ll_sort)
    LinearLayout llSort;
    @Bind(R.id.ll_filter)
    LinearLayout llFilter;
    @Bind(R.id.ll_more)
    LinearLayout llMore;
    @Bind(R.id.lv_left)
    ListView lvLeft;
    @Bind(R.id.lv_right)
    ListView lvRight;
    @Bind(R.id.ll_head_layout)
    LinearLayout llHeadLayout;
    @Bind(R.id.ll_content_list_view)
    LinearLayout llContentListView;
    @Bind(R.id.view_mask_bg)
    View viewMaskBg;
    @Bind(R.id.second_search_price_section)
    LinearLayout second_search_price_section;
    @Bind(R.id.second_search_price_linear)
    FrameLayout second_search_price_linear;
    @Bind(R.id.second_search_price_list)
    ListView second_search_price_list;
    @Bind(R.id.second_search_price_min)
    EditText second_search_price_min;
    @Bind(R.id.second_search_price_max)
    EditText second_search_price_max;
    @Bind(R.id.second_search_price_sure)
    Button second_search_price_sure;
    @Bind(R.id.second_search_frame)
    FrameLayout second_search_frame;
    @Bind(R.id.second_search_type)
    ListView second_search_type;
    @Bind(R.id.second_search_type_sure)
    Button second_search_type_sure;
    @Bind(R.id.second_search_more_layout)
    FrameLayout second_search_more_layout;
    @Bind(R.id.gridView1)
    FixedGridView gridView1;
    @Bind(R.id.gridView2)
    FixedGridView gridView2;
    @Bind(R.id.gridView3)
    FixedGridView gridView3;
    @Bind(R.id.gridView4)
    FixedGridView gridView4;
    @Bind(R.id.gridView5)
    FixedGridView gridView5;
    @Bind(R.id.gridView6)
    FixedGridView gridView6;
    @Bind(R.id.gridView_btn)
    Button gridView_btn;
    @Bind(R.id.gridView_empty)
    Button gridView_empty;

    private Context mContext;
    private Activity mActivity;
    private boolean isStickyTop = false; // 是否吸附在顶部
    private boolean isShowing = false;
    private int filterPosition = -1;
    private int panelHeight;
    private FilterData filterData;

    private FilterTwoEntity selectedCategoryEntity; // 被选择的分类项
    private FilterEntity selectedSortEntity; // 被选择的排序项
    private FilterEntity selectedFilterEntity; // 被选择的筛选项
    private FilterEntity selectedfeatureEntity; // 更多选项
    private FilterEntity selectedtowardEntity; // 更多选项
    private FilterEntity selectedareaTypEntity;
    private FilterEntity selectedageTypEntity;
    private FilterEntity selectedhouseTypEntity;
    private FilterEntity selectedlistSortEntity;

    private List<FilterEntity> filterEntityList = ModelUtil.getFilterData();
    private List<String> mList1 = new ArrayList<String>();//用来存放选中的数据
    private List<String> mList2 = new ArrayList<String>();//用来存放选中的数据
    private List<String> mList3 = new ArrayList<String>();//用来存放选中的数据
    private List<String> mList4 = new ArrayList<String>();//用来存放选中的数据
    private List<String> mList5 = new ArrayList<String>();//用来存放选中的数据
    private List<String> mList6 = new ArrayList<String>();//用来存放选中的数据
    private List<Integer> list1 = new ArrayList<Integer>();
    private List<Integer> list2 = new ArrayList<Integer>();
    private List<Integer> list3 = new ArrayList<Integer>();
    private List<Integer> list4 = new ArrayList<Integer>();
    private List<Integer> list5 = new ArrayList<Integer>();
    private List<Integer> list6 = new ArrayList<Integer>();

    private FilterLeftAdapter leftAdapter;
    private FilterRightAdapter rightAdapter;
    private FilterOneAdapter sortAdapter;
    private FilterOneAdapter filterAdapter;
    private FilterTypeAdapter filterTypeAdapter;
    private HouseTowardAdapter houseTowardAdapter;
    private HouseTowardAdapter houseTowardAdapter2;
    private HouseTowardAdapter houseTowardAdapter3;
    private HouseTowardAdapter houseTowardAdapter4;
    private HouseTowardAdapter houseTowardAdapter5;
    private HouseTowardAdapter houseTowardAdapter6;

    public FilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.view_filter_layout, this);
        ButterKnife.bind(this, view);

        initData();
        initView();
        initListener();
    }

    private void initData() {

    }

    private void initView() {
        viewMaskBg.setVisibility(GONE);
        llContentListView.setVisibility(GONE);
    }

    private void initListener() {
        llCategory.setOnClickListener(this);
        llSort.setOnClickListener(this);
        llFilter.setOnClickListener(this);
        llMore.setOnClickListener(this);
        viewMaskBg.setOnClickListener(this);
        llContentListView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    boolean ll_category_flag = false;
    boolean ll_sort_flag = false;
    boolean ll_filter_flag = false;
    boolean ll_more_flag = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_category:
                filterPosition = 0;
                if (onFilterClickListener != null && ll_category_flag == false) {
                    onFilterClickListener.onFilterClick(filterPosition);
                    ll_category_flag = true;
                    ll_sort_flag = false;
                    ll_filter_flag = false;
                    ll_more_flag = false;
                } else {

                    hide();
                }

                break;

            case R.id.ll_sort:
                filterPosition = 1;
                if (onFilterClickListener != null && ll_sort_flag == false) {
                    onFilterClickListener.onFilterClick(filterPosition);
                    ll_category_flag = false;
                    ll_sort_flag = true;
                    ll_filter_flag = false;
                    ll_more_flag = false;
                } else {

                    hide();
                }

                break;

            case R.id.ll_filter:
                filterPosition = 2;
                if (onFilterClickListener != null && ll_filter_flag == false) {
                    onFilterClickListener.onFilterClick(filterPosition);
                    ll_category_flag = false;
                    ll_sort_flag = false;
                    ll_filter_flag = true;
                    ll_more_flag = false;
                } else {

                    hide();
                }
                break;

            case R.id.ll_more:
                filterPosition = 3;
                if (onFilterClickListener != null && ll_more_flag == false) {
                    onFilterClickListener.onFilterClick(filterPosition);
                    ll_category_flag = false;
                    ll_sort_flag = false;
                    ll_filter_flag = false;
                    ll_more_flag = true;
                } else {

                    hide();
                }
                break;

            case R.id.view_mask_bg:
                hide();

                break;
        }

    }

    // 复位筛选的显示状态
    public void resetFilterStatus() {

        tvCategory.setTextColor(mContext.getResources().getColor(R.color.app_font_color_black));
        ivCategoryArrow.setImageResource(R.mipmap.home_down_arrow);

        tvSort.setTextColor(mContext.getResources().getColor(R.color.app_font_color_black));
        ivSortArrow.setImageResource(R.mipmap.home_down_arrow);

        tvFilter.setTextColor(mContext.getResources().getColor(R.color.app_font_color_black));
        ivFilterArrow.setImageResource(R.mipmap.home_down_arrow);

        tvMore.setTextColor(mContext.getResources().getColor(R.color.app_font_color_black));
        ivMoreArrow.setImageResource(R.mipmap.home_down_arrow);
    }

    // 复位所有的状态
    public void resetAllStatus() {
        resetFilterStatus();
        hide();
    }

    // 显示筛选布局
    public void showFilterLayout(int position) {
        resetFilterStatus();
        switch (position) {
            case 0:
                setCategoryAdapter();
                break;
            case 1:
                setSortAdapter();
                break;
            case 2:
                setFilterAdapter();
                break;
            case 3:
                setMoreAdapter();
                break;
        }
        if (isShowing) return;
        show();
    }

    // 设置分类数据
    private void setCategoryAdapter() {
        tvCategory.setTextColor(mActivity.getResources().getColor(R.color.app_main_color));
        ivCategoryArrow.setImageResource(R.mipmap.home_up_arrow);
        lvLeft.setVisibility(VISIBLE);
        lvRight.setVisibility(VISIBLE);
        second_search_price_linear.setVisibility(GONE);
        second_search_more_layout.setVisibility(GONE);
        second_search_frame.setVisibility(GONE);
        if (selectedCategoryEntity == null) {
            selectedCategoryEntity = filterData.getCategory().get(0);
        }

        // 左边列表视图
        leftAdapter = new FilterLeftAdapter(mContext, filterData.getCategory());
        lvLeft.setAdapter(leftAdapter);
        leftAdapter.setSelectedEntity(selectedCategoryEntity);
        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCategoryEntity = filterData.getCategory().get(position);
                leftAdapter.setSelectedEntity(selectedCategoryEntity);

                // 右边列表视图
                rightAdapter = new FilterRightAdapter(mContext, selectedCategoryEntity.getList());
                lvRight.setAdapter(rightAdapter);
                lvRight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectedCategoryEntity.setSelectedFilterEntity(selectedCategoryEntity.getList().get(position));
                        rightAdapter.setSelectedEntity(selectedCategoryEntity.getSelectedFilterEntity());
                        hide();
                        if (onItemCategoryClickListener != null) {
                            onItemCategoryClickListener.onItemCategoryClick(selectedCategoryEntity);
                        }
                    }
                });
            }
        });

        // 如果右边有选中的数据，设置
        if (selectedCategoryEntity.getSelectedFilterEntity() != null) {
            rightAdapter = new FilterRightAdapter(mContext, selectedCategoryEntity.getList());
        } else {
            rightAdapter = new FilterRightAdapter(mContext, filterData.getCategory().get(0).getList());
        }
        lvRight.setAdapter(rightAdapter);
        lvRight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCategoryEntity.setSelectedFilterEntity(selectedCategoryEntity.getList().get(position));
                rightAdapter.setSelectedEntity(selectedCategoryEntity.getSelectedFilterEntity());
                tvCategory.setText(selectedCategoryEntity.getList().get(position).getKey());
                hide();
                if (onItemCategoryClickListener != null) {
                    onItemCategoryClickListener.onItemCategoryClick(selectedCategoryEntity);
                }
            }
        });
    }

    // 设置价格数据
    private void setSortAdapter() {
        tvSort.setTextColor(mActivity.getResources().getColor(R.color.app_main_color));
        ivSortArrow.setImageResource(R.mipmap.home_up_arrow);
        second_search_price_linear.setVisibility(VISIBLE);
        second_search_more_layout.setVisibility(GONE);
        second_search_frame.setVisibility(GONE);
        lvLeft.setVisibility(GONE);
        lvRight.setVisibility(GONE);
        sortAdapter = new FilterOneAdapter(mContext, filterData.getSorts());
        second_search_price_list.setAdapter(sortAdapter);
        second_search_price_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedSortEntity = filterData.getSorts().get(position);
                sortAdapter.setSelectedEntity(selectedSortEntity);
                tvSort.setText(selectedSortEntity.getKey());
                hide();
                if (onItemSortClickListener != null) {
                    onItemSortClickListener.onItemSortClick(selectedSortEntity);
                }
            }
        });

        second_search_price_sure.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedSortEntity = new FilterEntity();
                sortAdapter.setSelectedEntity(selectedSortEntity);
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(second_search_price_sure.getWindowToken(), 0);
                hide();
                if (onPriceFilterClickListener != null) {
                    onPriceFilterClickListener.onPriceFilterClick(second_search_price_min.getText().toString().trim(), second_search_price_max.getText().toString().trim());
                }
            }
        });

    }

    // 设置房型数据
    private void setFilterAdapter() {
        tvFilter.setTextColor(mActivity.getResources().getColor(R.color.app_main_color));
        ivFilterArrow.setImageResource(R.mipmap.home_up_arrow);
        second_search_frame.setVisibility(VISIBLE);
        second_search_price_linear.setVisibility(GONE);
        second_search_more_layout.setVisibility(GONE);
        lvLeft.setVisibility(GONE);
        lvRight.setVisibility(GONE);
        filterTypeAdapter = new FilterTypeAdapter(mContext, filterEntityList);
        second_search_type.setAdapter(filterTypeAdapter);
        second_search_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedFilterEntity = filterData.getFilters().get(position);
                filterTypeAdapter.setSelectedEntity(selectedFilterEntity);
                tvFilter.setText(selectedFilterEntity.getKey());
                hide();
                if (onItemFilterClickListener != null) {
                    onItemFilterClickListener.onItemFilterClick(selectedFilterEntity);
                }
//                if(filterEntityList.contains(selectedFilterEntity) && selectedFilterEntity.isSelected() == true){
//
//                    selectedFilterEntity.setSelected(false);
//                    filterEntityList.set(position,selectedFilterEntity);
//                    filterEntityLists.remove(selectedFilterEntity);
//                }else{
//
//                    selectedFilterEntity.setSelected(true);
//                    filterEntityList.set(position,selectedFilterEntity);
//                    filterEntityLists.add(selectedFilterEntity);
//                }
//               filterEntityLists.add(selectedFilterEntity);
//                filterTypeAdapter.setSelectedEntity(filterEntityList);
//                filterTypeAdapter.notifyDataSetChanged();
            }
        });

//        second_search_type_sure.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                hide();
//                if (onTypeFilterClickListener != null) {
//                    onTypeFilterClickListener.onTypeFilterClick(filterEntityLists);
//                }
//            }
//        });
    }

    //更多
    private void setMoreAdapter() {

        tvMore.setTextColor(mActivity.getResources().getColor(R.color.app_main_color));
        ivMoreArrow.setImageResource(R.mipmap.home_up_arrow);
        second_search_more_layout.setVisibility(VISIBLE);
        second_search_frame.setVisibility(GONE);
        second_search_price_linear.setVisibility(GONE);
        lvLeft.setVisibility(GONE);
        lvRight.setVisibility(GONE);

        houseTowardAdapter = new HouseTowardAdapter(filterData.getFeature(), list1, mContext);
        gridView1.setAdapter(houseTowardAdapter);
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedfeatureEntity = filterData.getFeature().get(position);
                int id = position;
                if (list1.contains(position)) {
                    for (int i = 0; i < list1.size(); i++) {
                        if (position == list1.get(i)) {
                            list1.remove(i);
                            mList1.remove(selectedfeatureEntity.getValue());
                        }
                    }
                } else {
                    list1.add(position);
                    //保存选中的数据
                    mList1.add(selectedfeatureEntity.getValue());
                }
                houseTowardAdapter.notifyDataSetChanged();
            }
        });

        houseTowardAdapter2 = new HouseTowardAdapter(filterData.getToward(), list2, mContext);
        gridView2.setAdapter(houseTowardAdapter2);
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedtowardEntity = filterData.getToward().get(position);
                int id = position;
                if (list2.contains(position)) {
                    for (int i = 0; i < list2.size(); i++) {
                        if (position == list2.get(i)) {
                            list2.remove(i);
                            mList2.remove(selectedtowardEntity.getValue());
                        }
                    }
                } else {
                    list2.clear();
                    mList2.clear();
                    list2.add(position);
                    //保存选中的数据
                    mList2.add(selectedtowardEntity.getValue());
                }
                houseTowardAdapter2.notifyDataSetChanged();
            }
        });
        houseTowardAdapter3 = new HouseTowardAdapter(filterData.getAreaType(), list3, mContext);
        gridView3.setAdapter(houseTowardAdapter3);
        gridView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedareaTypEntity = filterData.getAreaType().get(position);
                int id = position;
                if (list3.contains(position)) {
                    for (int i = 0; i < list3.size(); i++) {
                        if (position == list3.get(i)) {
                            list3.remove(i);
                            mList3.remove(selectedareaTypEntity.getValue());
                        }
                    }
                } else {
                    list3.clear();
                    mList3.clear();
                    list3.add(position);
                    //保存选中的数据
                    mList3.add(selectedareaTypEntity.getValue());
                }
                houseTowardAdapter3.notifyDataSetChanged();
            }
        });

        houseTowardAdapter4 = new HouseTowardAdapter(filterData.getAgeType(), list4, mContext);
        gridView4.setAdapter(houseTowardAdapter4);
        gridView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedageTypEntity = filterData.getAgeType().get(position);
                int id = position;
                if (list4.contains(position)) {
                    for (int i = 0; i < list4.size(); i++) {
                        if (position == list4.get(i)) {
                            list4.remove(i);
                            mList4.remove(selectedageTypEntity.getValue());
                        }
                    }
                } else {
                    list4.clear();
                    mList4.clear();
                    list4.add(position);
                    //保存选中的数据
                    mList4.add(selectedageTypEntity.getValue());
                }
                houseTowardAdapter4.notifyDataSetChanged();
            }
        });


        houseTowardAdapter5 = new HouseTowardAdapter(filterData.getHouseType(), list5, mContext);
        gridView5.setAdapter(houseTowardAdapter5);
        gridView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedageTypEntity = filterData.getHouseType().get(position);
                int id = position;
                if (list5.contains(position)) {
                    for (int i = 0; i < list5.size(); i++) {
                        if (position == list5.get(i)) {
                            list5.remove(i);
                            mList5.remove(selectedageTypEntity.getValue());
                        }
                    }
                } else {
                    list5.clear();
                    mList5.clear();
                    list5.add(position);
                    //保存选中的数据
                    mList5.add(selectedageTypEntity.getValue());
                }
                houseTowardAdapter5.notifyDataSetChanged();
            }
        });





        houseTowardAdapter6 = new HouseTowardAdapter(filterData.getListSort(), list6, mContext);
        gridView6.setAdapter(houseTowardAdapter6);
        gridView6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedlistSortEntity = filterData.getListSort().get(position);
                int id = position;
                if (list6.contains(position)) {
                    for (int i = 0; i < list6.size(); i++) {
                        if (position == list6.get(i)) {
                            list6.remove(i);
                            mList6.remove(selectedlistSortEntity.getValue());
                        }
                    }
                } else {
                    list6.clear();
                    mList6.clear();
                    list6.add(position);
                    //保存选中的数据
                    mList6.add(selectedlistSortEntity.getValue());
                }
                houseTowardAdapter6.notifyDataSetChanged();
            }
        });

        gridView_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();
                if (onMoreFilterClickListener != null) {
                    onMoreFilterClickListener.onMoreFilterClick(mList1, mList2, mList3, mList4, mList5, mList6);
                }
            }
        });

        gridView_empty.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                list1.clear();
                list2.clear();
                list3.clear();
                list4.clear();
                list5.clear();
                list6.clear();
                mList1.clear();
                mList2.clear();
                mList3.clear();
                mList4.clear();
                mList5.clear();
                mList6.clear();
                houseTowardAdapter.notifyDataSetChanged();
                houseTowardAdapter2.notifyDataSetChanged();
                houseTowardAdapter3.notifyDataSetChanged();
                houseTowardAdapter4.notifyDataSetChanged();
                houseTowardAdapter5.notifyDataSetChanged();
                houseTowardAdapter6.notifyDataSetChanged();
            }
        });
    }

    // 动画显示
    private void show() {
        isShowing = true;
        viewMaskBg.setVisibility(VISIBLE);
        llContentListView.setVisibility(VISIBLE);
        llContentListView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llContentListView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                panelHeight = llContentListView.getHeight();
                ObjectAnimator.ofFloat(llContentListView, "translationY", -panelHeight, 0).setDuration(200).start();
            }
        });
    }

    // 隐藏动画
    public void hide() {
        isShowing = false;

        ll_category_flag = false;
        ll_sort_flag = false;
        ll_filter_flag = false;
        ll_more_flag = false;

        resetFilterStatus();
        viewMaskBg.setVisibility(View.GONE);
        second_search_more_layout.setVisibility(View.GONE);
        second_search_frame.setVisibility(View.GONE);
        second_search_price_linear.setVisibility(View.GONE);
        lvLeft.setVisibility(View.GONE);
        lvRight.setVisibility(View.GONE);
        ObjectAnimator.ofFloat(llContentListView, "translationY", 0, -panelHeight).setDuration(200).start();
    }

    // 是否吸附在顶部
    public void setStickyTop(boolean stickyTop) {
        isStickyTop = stickyTop;
    }

    // 设置筛选数据
    public void setFilterData(Activity activity, FilterData filterData) {
        this.mActivity = activity;
        this.filterData = filterData;
    }

    // 是否显示
    public boolean isShowing() {
        return isShowing;
    }

    // 筛选视图点击
    private OnFilterClickListener onFilterClickListener;

    public void setOnFilterClickListener(OnFilterClickListener onFilterClickListener) {
        this.onFilterClickListener = onFilterClickListener;
    }

    public interface OnFilterClickListener {
        void onFilterClick(int position);
    }

    // 分类Item点击
    private OnItemCategoryClickListener onItemCategoryClickListener;

    public void setOnItemCategoryClickListener(OnItemCategoryClickListener onItemCategoryClickListener) {
        this.onItemCategoryClickListener = onItemCategoryClickListener;
    }

    public interface OnItemCategoryClickListener {
        void onItemCategoryClick(FilterTwoEntity entity);
    }

    // 排序Item点击
    private OnItemSortClickListener onItemSortClickListener;

    public void setOnItemSortClickListener(OnItemSortClickListener onItemSortClickListener) {
        this.onItemSortClickListener = onItemSortClickListener;
    }

    public interface OnItemSortClickListener {
        void onItemSortClick(FilterEntity entity);
    }

    // 筛选Item点击
    private OnItemFilterClickListener onItemFilterClickListener;

    public void setOnItemFilterClickListener(OnItemFilterClickListener onItemFilterClickListener) {
        this.onItemFilterClickListener = onItemFilterClickListener;
    }

    //    public interface OnItemFilterClickListener {
//        void onItemFilterClick(List<FilterEntity> entity);
//    }
    public interface OnItemFilterClickListener {
        void onItemFilterClick(FilterEntity entity);
    }

    private OnTypeFilterClickListener onTypeFilterClickListener;

    public void setOnClickListener(OnTypeFilterClickListener onTypeFilterClickListener) {
        this.onTypeFilterClickListener = onTypeFilterClickListener;
    }

    public interface OnTypeFilterClickListener {
        void onTypeFilterClick(List<FilterEntity> entity);
    }

    private OnPriceFilterClickListener onPriceFilterClickListener;

    public void setOnClickListener(OnPriceFilterClickListener onPriceFilterClickListener) {
        this.onPriceFilterClickListener = onPriceFilterClickListener;
    }

    public interface OnPriceFilterClickListener {
        void onPriceFilterClick(String min, String max);
    }

    private OnMoreFilterClickListener onMoreFilterClickListener;

    public void setOnClickListener(OnMoreFilterClickListener onMoreFilterClickListener) {
        this.onMoreFilterClickListener = onMoreFilterClickListener;
    }

    public interface OnMoreFilterClickListener {
        void onMoreFilterClick(List<String> mList1, List<String> mList2, List<String> mList3, List<String> mList4, List<String> mList5, List<String> mList6);
    }
}
