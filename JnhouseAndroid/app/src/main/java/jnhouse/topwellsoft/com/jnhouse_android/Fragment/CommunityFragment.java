package jnhouse.topwellsoft.com.jnhouse_android.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topwellsoft.androidutils.PreferencesUtils;
import com.topwellsoft.androidutils.StyleUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.CollectionListViewAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.CollectionEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.MyCollectionList;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

/**
 * Created by topwellsoft on 2016/7/4.
 */
public class CommunityFragment extends Fragment {
    private ListView mListView;
    private CollectionListViewAdapter mListViewAdapter;
    private List<CollectionEntity> mCollectionList;


    private String user_id;
    private String type = "4";
    private String cur_index = "1";
    private String userUUID;

    private Button mButton_del;
    private String ids;
    private MyDiaLog diaLog;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_community_fragment_item,container,false);
//        Toast.makeText(getActivity(),"onCreateView",Toast.LENGTH_SHORT).show();
        mListView = (ListView) view.findViewById(R.id.community_fragment_lv);
        mCollectionList = new ArrayList<CollectionEntity>();
        mButton_del = (Button) view.findViewById(R.id.community_del_btn);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                mButton_del.setVisibility(View.VISIBLE);
                mListViewAdapter.setCanShown();
                mButton_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            deleteFavorServerData(JnHouse_Record.Key_del_my_favor);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                mListViewAdapter.notifyDataSetChanged();
                return true;
            }
        });
        StyleUtils.initStatusBar(getActivity().getWindow());
        diaLog = new MyDiaLog(getActivity());

        return view;
    }

    private void initDialog() {
        diaLog.setCancelable(true);
        diaLog.show();
    }

    //获取要删除的问题的id
    public String getIds() {
        String str = "";
        for (int i = 0; i < mListViewAdapter.getIsSelect().size(); i++) {
            if (i == mListViewAdapter.getIsSelect().size() - 1) {
                str += mCollectionList.get(Integer.parseInt(mListViewAdapter.getIsSelect().get(i))).getHouse_id();
            } else {
                str += mCollectionList.get(Integer.parseInt(mListViewAdapter.getIsSelect().get(i))).getHouse_id() + ",";
            }
        }
        return str;
    }

    public void deleteFavorServerData(String url) throws IOException {
//        Toast.makeText(this,"getFavorServerData",Toast.LENGTH_SHORT).show();
        LoginEntity info = (LoginEntity) PreferencesUtils.getObject(getActivity(), "loginEntity");
        if (info == null || info.getUserUUID() == null) {
            ToastUtil.makeText(  this.getActivity(), "请重新登录",
                    ToastUtil.LENGTH_SHORT)
                    .setAnimation(R.style.PopToast).show();
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put("ids",getIds());
        params.put("type", type);//关注类型：1二手房2租房3新房4小区
        params.put("userUUID", info.getUserUUID());//登录标识

        FinalHttp fh = new FinalHttp();
        fh.get(url, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Toast.makeText(getActivity(),"错误代码:"+errorNo,Toast.LENGTH_LONG).show();
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
                    String jsonString = t.toString();
                    if (jsonString.startsWith("{\"code\":1")) {
                        ToastUtil.makeText(getActivity(), "未登录", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                    } else if (jsonString.startsWith("{\"code\":-1")) {
                        ToastUtil.makeText(getActivity(), "删除异常", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                    } else if (jsonString.startsWith("{\"code\":0")) {
                        ToastUtil.makeText(getActivity(), "删除成功", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                        mListViewAdapter.setCanShown();
                        for (int i = 0; i < mListViewAdapter.getIsSelect().size(); i++) {
                            mCollectionList.remove(Integer.parseInt(mListViewAdapter.getIsSelect().get(i)));
                        }
                        mListViewAdapter.notifyDataSetChanged();
                    }
                    diaLog.dismiss();
                    mButton_del.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        if (diaLog.isShowing()) {
            diaLog.dismiss();
        }

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser&&isVisible()){
            try {

                getFavorServerData(JnHouse_Record.Key_my_favor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    public void getFavorServerData(String url) throws IOException {
//        Toast.makeText(this,"getFavorServerData",Toast.LENGTH_SHORT).show();
        LoginEntity info = (LoginEntity) PreferencesUtils.getObject(getActivity(), "loginEntity");
        if (info == null || info.getUserUUID() == null) {
            ToastUtil.makeText(  this.getActivity(), "请重新登录",
                    ToastUtil.LENGTH_SHORT)
                    .setAnimation(R.style.PopToast).show();
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put("cur_index", cur_index);//当前页
        params.put("user_id",info.getUser_id());//用户id
        params.put("type", type);//关注类型：1二手房2租房3新房4小区
        params.put("userUUID", info.getUserUUID());//登录标识
        FinalHttp fh = new FinalHttp();
        fh.get(url, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Toast.makeText(getActivity(),"错误代码:"+errorNo,Toast.LENGTH_LONG).show();
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
                    MyCollectionList mList = gson.fromJson(t.toString(), new TypeToken<MyCollectionList>() {
                    }.getType());
                    Log.i("#####",t.toString());
//                    Toast.makeText(Browse.this,jsonObject.toString(),Toast.LENGTH_LONG).show();
                    if (mList.getGz_list() != null && mList.getGz_list().size() > 0) {
                        Log.i("#####","if");
                        mCollectionList = mList.getGz_list();
//                        Toast.makeText(getActivity(),"size:"+mCollectionList.size(),Toast.LENGTH_SHORT).show();
                        Log.i("#####","mCollectionList:"+mCollectionList.toString());
                        mListViewAdapter = new CollectionListViewAdapter(getActivity(),mCollectionList);
                        Log.i("#####","mListViewAdapter:"+mListViewAdapter.getCount());
                        mListView.setAdapter(mListViewAdapter);
                        Log.i("#####","mListView:"+mListView.toString());
                        //setListViewHightBaseedOnChildren(mListView);

                    }
                    if (mList.getGz_list().size() == 0){
                        Toast.makeText(getActivity(),"暂无数据",Toast.LENGTH_SHORT).show();
                    }
                    if (diaLog.isShowing()) {
                        diaLog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    public void setListViewHightBaseedOnChildren(ListView listview) {
        ListAdapter adapter = listview.getAdapter();
        if (adapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View item = adapter.getView(i, null, listview);
            item.measure(0, 0);
            totalHeight += item.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = totalHeight + (listview.getDividerHeight() * (adapter.getCount() - 1));
        listview.setLayoutParams(params);
    }
}
