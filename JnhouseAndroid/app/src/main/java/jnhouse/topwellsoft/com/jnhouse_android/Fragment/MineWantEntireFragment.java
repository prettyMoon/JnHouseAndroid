package jnhouse.topwellsoft.com.jnhouse_android.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
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
import jnhouse.topwellsoft.com.jnhouse_android.adapter.MineSellSecondListViewAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.adapter.MineWantListViewAdapter;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.MineSellSecondEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.MineSellSecondList;
import jnhouse.topwellsoft.com.jnhouse_android.model.MineWantEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.MineWantList;
import jnhouse.topwellsoft.com.jnhouse_android.ui.login.TpLoginFragmentActivity;
import jnhouse.topwellsoft.com.jnhouse_android.ui.question.MyDiaLog;
import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

/**
 * Created by topwellsoft on 2016/7/14.
 */
public class MineWantEntireFragment extends Fragment {
    private ListView mListView;
    private MineWantListViewAdapter mListViewAdapter;
    private List<MineWantEntity> mWantEntityList;
    /*user_id:用户id
    currentPage:页码
    rent_type:出售委托类型（ 0:整租，1：合租，2：日租短租）
    state:处理状态*/

    private String user_id;
    private String currentPage = "1";
    private String rent_type = "0";
    private String state = "0";

    private Button mButton_del;
    private MyDiaLog diaLog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_mine_want_entire_fragment,container,false);
        mListView = (ListView) view.findViewById(R.id.mine_wantentire_lv);
        mWantEntityList = new ArrayList<MineWantEntity>();
        mButton_del = (Button) view.findViewById(R.id.wantlist_del_btn);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                mButton_del.setVisibility(View.VISIBLE);
                mListViewAdapter.setCanShown();
                mButton_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            deleteFavorServerData(JnHouse_Record.Key_del_my_want);
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
        initDialog();
        try {
            getWantEntireServerData(JnHouse_Record.Mine_want);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;

    }

    private void initDialog() {
        diaLog = new MyDiaLog(getActivity());
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaLog.setCancelable(true);
        diaLog.show();
    }

    //获取要删除的问题的id
    public String getIds() {
        String str = "";
        for (int i = 0; i < mListViewAdapter.getIsSelect().size(); i++) {
            if (i == mListViewAdapter.getIsSelect().size() - 1) {
                str += mWantEntityList.get(Integer.parseInt(mListViewAdapter.getIsSelect().get(i))).getId();
            } else {
                str += mWantEntityList.get(Integer.parseInt(mListViewAdapter.getIsSelect().get(i))).getId() + ",";
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
//        params.put("sell_type", "2");
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
                            mWantEntityList.remove(Integer.parseInt(mListViewAdapter.getIsSelect().get(i)));
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

    }


    private void getWantEntireServerData(String url) throws IOException {
        final LoginEntity info = (LoginEntity) PreferencesUtils.getObject(getActivity(), "loginEntity");
        if (info == null || info.getUserUUID() == null) {
            ToastUtil.makeText(  this.getActivity(), "请重新登录",
                    ToastUtil.LENGTH_SHORT)
                    .setAnimation(R.style.PopToast).show();
            return;
        }
        AjaxParams params = new AjaxParams();
        /*user_id:用户id
    currentPage:页码
    rent_type:出售委托类型（ 0:整租，1：合租，2：日租短租）
    state:处理状态*/
        params.put("user_id",info.getUser_id());//用户id
        params.put("userUUID",info.getUserUUID());
        params.put("currentPage", currentPage);//当前页
        params.put("rent_type", rent_type);
        params.put("state", state);//登录标识
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
                    MineWantList mList = gson.fromJson(t.toString(), new TypeToken<MineWantList>() {
                    }.getType());
                    Log.i("#####",t.toString());
//                    Toast.makeText(Browse.this,jsonObject.toString(),Toast.LENGTH_LONG).show();
                    if (mList.getWantRentList() != null && mList.getWantRentList().size() > 0) {
                        switch (mList.getCode()){
                            case 0:
                                Log.i("getCode","user_id:" + info.getUser_id() + "/t" + "sell_type:" +rent_type+"/t" + "currentPage:"+ currentPage + "state:" + state );
                                Log.i("#####","if");
                                mWantEntityList = mList.getWantRentList();
//                                      Toast.makeText(getActivity(),"size:"+mCollectionList.size(),Toast.LENGTH_SHORT).show();
//                                      Log.i("#####","mCollectionList:"+mSellSecondList.toString());
                                mListViewAdapter = new MineWantListViewAdapter(getActivity(),mWantEntityList);
//                                      Log.i("#####","mListViewAdapter:"+mListViewAdapter.getCount());
                                mListView.setAdapter(mListViewAdapter);
//                                      Log.i("#####","mListView:"+mListView.toString());
                                //setListViewHightBaseedOnChildren(mListView);
                                break;
                            case 1:
                                Toast.makeText(getActivity(),"未登录",Toast.LENGTH_SHORT).show();
                                PreferencesUtils.clearData(getActivity(),"loginEntity");
                                Intent intent = new Intent();
                                intent.setClass(getActivity(), TpLoginFragmentActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                break;
                            case -1:
                                Toast.makeText(getActivity(),"异常",Toast.LENGTH_SHORT).show();
                                break;
                            case 1003:
                                Toast.makeText(getActivity(),"列表为空",Toast.LENGTH_SHORT).show();
                                break;
                        }
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


}
