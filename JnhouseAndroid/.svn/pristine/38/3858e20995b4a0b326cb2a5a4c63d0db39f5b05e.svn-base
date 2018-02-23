package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.topwellsoft.androidutils.GlobalObjects;
import com.topwellsoft.androidutils.PreferencesUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.constant.JnHouse_Record;
import jnhouse.topwellsoft.com.jnhouse_android.manage.ImageManager;
import jnhouse.topwellsoft.com.jnhouse_android.model.AnswerEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.LoginEntity;

import jnhouse.topwellsoft.com.jnhouse_android.util.ToastUtil;

import static jnhouse.topwellsoft.com.jnhouse_android.R.id.net;

/**
 * Created by Administrator on 2016/7/12.
 */
public class QuestionDetailAdapter extends BaseAdapter {
    private ArrayList<AnswerEntity> list;
    private Context context;
    private LayoutInflater inflater;
    private boolean isPraise;
    private int praise_num;
    private ImageManager manager;

    public QuestionDetailAdapter(Context context, ArrayList<AnswerEntity> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(this.context);
        manager = new ImageManager(context);
    }


    public int getPraise_num() {
        return praise_num;
    }

    public void setPraise_num(int num) {
        praise_num = num;
    }

    @Override

    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.answer_list_layout, null);
        }
        holder.tv_nickname = (TextView) convertView.findViewById(R.id.tv_nickname);
        holder.headicon = (ImageView) convertView.findViewById(R.id.comment_head_icon);
        holder.tv_answer = (TextView) convertView.findViewById(R.id.lv_tv_comment);
        holder.tv_answer_num = (TextView) convertView.findViewById(R.id.tv_answer_num);
        holder.img_praise = (ImageView) convertView.findViewById(R.id.img_praise);
        //数据
        holder.tv_nickname.setText(list.get(position).getAnsUserName());
        holder.tv_answer.setText(list.get(position).getAnswerContent());
        holder.tv_answer_num.setText(list.get(position).getPraiseNum());
//        if (!list.get(position).getAvatar().equals("")) {
//            manager.loadUrlImage(list.get(position).getAvatar(), holder.headicon);
//        }
        if (list.get(position).getIs_praise().equals("1")) {
            isPraise = true;
            holder.img_praise.setImageResource(R.drawable.answer_praise_pressed);
        } else if (list.get(position).getIs_praise().equals("0")) {
            isPraise = false;
            holder.img_praise.setImageResource(R.drawable.answer_praise);
        }
        manager.loadCircleImage(list.get(position).getAvatar(), holder.headicon);
        setPraise_num(Integer.parseInt(list.get(position).getPraiseNum()));
        holder.img_praise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.img_praise.setClickable(false);
                if (Integer.parseInt(list.get(position).getPraiseNum()) == 0 && list.get(position).getIs_praise().equals("1")) {
                    holder.img_praise.setClickable(true);
                    return;
                }
                try {
                    Praise(JnHouse_Record.Do_Praise, position, holder.tv_answer_num, getPraise_num(), holder.img_praise);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return convertView;
    }

    //点赞请求借口
    public void Praise(String url, int position, final TextView tv, final int num, final ImageView iv) throws IOException {
        LoginEntity info = (LoginEntity) PreferencesUtils.getObject(context, "loginEntity");
        if (info == null || info.getUserUUID() == null) {
            ToastUtil.makeText(GlobalObjects.theApplication, "请重新登录",
                    ToastUtil.LENGTH_SHORT)
                    .setAnimation(R.style.PopToast).show();
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put("answerId", list.get(position).getAnswerId());
        params.put("userId", info.getUser_id());
        params.put("userName", info.getUsername());
        params.put("userUUID", info.getUserUUID());
        FinalHttp fh = new FinalHttp();
        fh.get(url, params, new AjaxCallBack<Object>() {
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("####", "onFailure");
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
                    Log.i("#####", t.toString());
                    if (jsonString.startsWith("{\"code\":1")) {
                        Log.i("#####", "sdfhaksdhfjadsk");
                        ToastUtil.makeText(context, "您未登录", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                    } else if (jsonString.startsWith("{\"code\":-1")) {
                        ToastUtil.makeText(context, "操作异常", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                    } else if (jsonString.startsWith("{\"code\":703")) {
                        ToastUtil.makeText(context, "点赞成功", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                        iv.setImageResource(R.drawable.answer_praise_pressed);
                        tv.setText("" + (num + 1));
                        setPraise_num(num + 1);
                    } else if (jsonString.startsWith("{\"code\":704")) {
                        ToastUtil.makeText(context, "取消点赞", ToastUtil.LENGTH_SHORT).setAnimation(R.style.PopToast).show();
                        iv.setImageResource(R.drawable.answer_praise);
                        tv.setText("" + (num - 1));
                        setPraise_num(num - 1);
                    }
                    iv.setClickable(true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class ViewHolder {
        public ImageView headicon;
        public TextView tv_nickname, tv_answer, tv_answer_num;
        private ImageView img_praise;

    }
}
