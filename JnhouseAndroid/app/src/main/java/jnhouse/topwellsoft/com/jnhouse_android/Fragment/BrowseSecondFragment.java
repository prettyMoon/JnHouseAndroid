package jnhouse.topwellsoft.com.jnhouse_android.Fragment;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import jnhouse.topwellsoft.com.jnhouse_android.adapter.BrowseListViewAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.BrowseEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.MyBrowseList;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;
import jnhouse.topwellsoft.com.jnhouse_android.ui.secondary.IndexSecondHouseDetail;

import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

/**
 * Created by topwellsoft on 2016/7/13.
 */
public class BrowseSecondFragment extends Fragment {
    private ListView mListView;
    private BrowseListViewAdapter mListViewAdapter;
    private List<BrowseEntity> mBrowseList;


    private String user_id;
    private String house_type = "2";
    private String cur_index = "1";
    private String userUUID;

    private Button mButton_del;
    private String ids;
    private MyDiaLog diaLog;

    private MyBrowseList mList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_browse_second_fragment_item, container, false);
//        Toast.makeText(getActivity(),"onCreateView",Toast.LENGTH_SHORT).show();
        mListView = (ListView) view.findViewById(R.id.browse_second_fragment_lv);
        mBrowseList = new ArrayList<BrowseEntity>();
        mButton_del = (Button) view.findViewById(R.id.browse_second_del_btn);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                mButton_del.setVisibility(View.VISIBLE);
                mListViewAdapter.setCanShown();
                mButton_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            deleteFavorServerData(JnHouse_Record.Key_del_my_history);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                mListViewAdapter.notifyDataSetChanged();
                return true;
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("houseId", mList.getLl_list().get(i).getHouse_id());
                intent.setClass(getActivity(), IndexSecondHouseDetail.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        StyleUtils.initStatusBar(getActivity().getWindow());
        diaLog = new MyDiaLog(getActivity());
        try {
            initDialog();
            getHistoryServerData(JnHouse_Record.Key_my_history);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

   /* @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser&&isVisible()){
            try {
                initDialog();
                getHistoryServerData(JnHouse_Record.Key_my_history);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }*/

    private void initDialog() {

        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(true);
        diaLog.show();
    }

    //获取要删除的问题的id
    public String getIds() {
        String str = "";
        for (int i = 0; i < mListViewAdapter.getIsSelect().size(); i++) {
            if (i == mListViewAdapter.getIsSelect().size() - 1) {
                str += mBrowseList.get(Integer.parseInt(mListViewAdapter.getIsSelect().get(i))).getId();
            } else {
                str += mBrowseList.get(Integer.parseInt(mListViewAdapter.getIsSelect().get(i))).getId() + ",";
            }
        }
        return str;
    }

    public void deleteFavorServerData(String url) throws IOException {
//        Toast.makeText(this,"getFavorServerData",Toast.LENGTH_SHORT).show();
        LoginEntity info = (LoginEntity) PreferencesUtils.getObject(getActivity(), "loginEntity");
        if (info == null || info.getUserUUID() == null) {
            ToastUtil.makeText(this.getActivity(), "请重新登录",
                    ToastUtil.LENGTH_SHORT)
                    .setAnimation(R.style.PopToast).show();
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put("ids", getIds());
        params.put("userUUID", info.getUserUUID());//登录标识

        FinalHttp fh = new FinalHttp();
        fh.get(url, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Toast.makeText(getActivity(), "错误代码:" + errorNo, Toast.LENGTH_LONG).show();
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
                            mBrowseList.remove(Integer.parseInt(mListViewAdapter.getIsSelect().get(i)));
                        }
                        mListViewAdapter.notifyDataSetChanged();
                    }
                    if (diaLog.isShowing()) {
                        diaLog.dismiss();
                    }
                    mButton_del.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        diaLog.dismiss();

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }




    public void getHistoryServerData(String url) throws IOException {
//        Toast.makeText(this,"getFavorServerData",Toast.LENGTH_SHORT).show();
        LoginEntity info = (LoginEntity) PreferencesUtils.getObject(getActivity(), "loginEntity");
        if (info == null || info.getUserUUID() == null) {
            ToastUtil.makeText(this.getActivity(), "请重新登录",
                    ToastUtil.LENGTH_SHORT)
                    .setAnimation(R.style.PopToast).show();
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put("cur_index", "1");//当前页
        params.put("user_id", info.getUser_id());//用户id
        params.put("house_type", "2");//
        params.put("userUUID", info.getUserUUID());//登录标识
        FinalHttp fh = new FinalHttp();
        fh.get(url, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Toast.makeText(getActivity(), "错误代码:" + errorNo, Toast.LENGTH_LONG).show();
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
                    mList = gson.fromJson(t.toString(), new TypeToken<MyBrowseList>() {
                    }.getType());
                    Log.i("#####", t.toString());
//                    Toast.makeText(Browse.this,jsonObject.toString(),Toast.LENGTH_LONG).show();
                    if (mList.getLl_list() != null && mList.getLl_list().size() > 0) {
                        Log.i("#####", "if");
                        mBrowseList = mList.getLl_list();
//                        Toast.makeText(getActivity(),"size:"+mCollectionList.size(),Toast.LENGTH_SHORT).show();
                        Log.i("#####", "mCollectionList:" + mBrowseList.toString());
                        mListViewAdapter = new BrowseListViewAdapter(getActivity(), mBrowseList);
                        Log.i("#####", "mListViewAdapter:" + mListViewAdapter.getCount());
                        mListView.setAdapter(mListViewAdapter);
                        Log.i("#####", "mListView:" + mListView.toString());
                        //setListViewHightBaseedOnChildren(mListView);

                    }
                    if (diaLog.isShowing()) {
                        diaLog.dismiss();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        diaLog.dismiss();
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
