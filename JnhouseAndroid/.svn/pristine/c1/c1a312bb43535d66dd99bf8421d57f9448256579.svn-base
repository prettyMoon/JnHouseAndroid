package jnhouse.topwellsoft.com.jnhouse_android.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.HeaderChannelAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.TravelingAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.ChannelEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.HomeIndexEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.HomeZXEntity;
import jnhouse.topwellsoft.com.jnhouse_android.util.ModelUtil;
import jnhouse.topwellsoft.com.jnhouse_android.util.MyScrollView;
import jnhouse.topwellsoft.com.jnhouse_android.view.FixedGridView;
import jnhouse.topwellsoft.com.jnhouse_android.view.IndexHeaderImageView;

/**
 *
 * Created by admin on 2016/5/23.
 */
public class TpIndexMainFragment extends Fragment  {

    RelativeLayout rlBar;
    MyScrollView main_scrollView;
    FixedGridView fixedGridView;
    ViewPager viewPager;
    LinearLayout llIndexContainer;
    ListView index_house_listView;

    private TravelingAdapter mAdapter;

    private List<ChannelEntity> channelList = new ArrayList<>(); // 八个按钮
    private List<String> adList = new ArrayList<>(); // 轮播图
    private List<HomeZXEntity> zxList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tp_index_main, container, false);
        initView(view);
        initChannelData();
        getPDAServerData();
        return view;
    }

    public void initView(View view ){
        rlBar = (RelativeLayout) view.findViewById(R.id.rl_bar1);
        main_scrollView = (MyScrollView) view.findViewById(R.id.main_scrollView);
        fixedGridView = (FixedGridView) view.findViewById(R.id.index_channel);
        viewPager = (ViewPager) view.findViewById(R.id.vp_ad);
        llIndexContainer = (LinearLayout) view.findViewById(R.id.ll_index_container);
        index_house_listView = (ListView) view.findViewById(R.id.tp_index_house_listView);
        main_scrollView.smoothScrollTo(0,0);
    }

    public void initChannelData(){
        channelList = ModelUtil.getChannelData();
        HeaderChannelAdapter adapter = new HeaderChannelAdapter(getActivity(), channelList);
        fixedGridView.setAdapter(adapter);

        adList = ModelUtil.getAdData();
        IndexHeaderImageView indexHeaderImageView = new IndexHeaderImageView(llIndexContainer,viewPager,getActivity(),adList);
        indexHeaderImageView.dealWithTheView();

    }

    public void getPDAServerData(){

        AjaxParams params = new AjaxParams();
        FinalHttp fh = new FinalHttp();
        fh.get(JnHouse_Record.Key_home_index, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Toast.makeText(getActivity(),"加载失败",Toast.LENGTH_SHORT).show();
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

                Log.d("AAAAA", t.toString());

                try {

                    JSONObject jsonObject = new JSONObject(t.toString());
//                  Result result = gson.fromJson(jsonObject.getString("data"), new TypeToken<Result>() { }.getType());
//                  JSONObject jsonObject = new JSONObject(t.toString()).getJSONObject("data");
//                  list = gson.fromJson( jsonObject.getString("dataList"), new TypeToken<List<Result>>() { }.getType());
                    Gson gson = new Gson();
                    HomeIndexEntity indexEntity = gson.fromJson(t.toString(),new TypeToken<HomeIndexEntity>() {}.getType());

                    if(indexEntity != null){
                        Log.d("AAAAAAAA", "onSuccess: "+ indexEntity.getZx_list().size());
//                        zxList = indexEntity.getZx_list();
//                        index_house_listView.setLoadMoreEnable(zxList.size() > TravelingAdapter.ONE_REQUEST_COUNT);
//                        mAdapter.setData(zxList);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
