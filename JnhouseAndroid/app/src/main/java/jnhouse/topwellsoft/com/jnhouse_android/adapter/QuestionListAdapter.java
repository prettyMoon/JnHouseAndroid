package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.model.QuestionDataEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.QuestionDataListEntity;

/**
 * Created by Administrator on 2016/7/11.
 */
public class QuestionListAdapter extends BaseAdapter {
    private ArrayList<QuestionDataEntity> list;
    private Context context;
    private LayoutInflater inflater;

    public QuestionListAdapter(Context context, ArrayList<QuestionDataEntity> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public ArrayList<QuestionDataEntity> getList() {
        return list;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.question_list_item, null);
        }
        holder.tv_question = (TextView) convertView.findViewById(R.id.lv_tv_question);
        holder.tv_answer = (TextView) convertView.findViewById(R.id.lv_question_answer);
        holder.tv_time = (TextView) convertView.findViewById(R.id.lv_question_time);
        holder.tv_scan = (TextView) convertView.findViewById(R.id.lv_question_scan);
        holder.tv_tab = (TextView) convertView.findViewById(R.id.lv_question_tab);
        holder.tv_question.setText(list.get(position).getAskName());
        if (list.get(position).getAnswerContent() == null || list.get(position).getAnswerContent().equals("")) {
            holder.tv_answer.setText("暂时没有人回答");
        } else {
            holder.tv_answer.setText(list.get(position).getAnswerContent());
        }
        holder.tv_time.setText(list.get(position).getAskTime());
        holder.tv_scan.setText(list.get(position).getAnswerNum());
        holder.tv_tab.setText("  " + list.get(position).getCatalogName() + "  ");
        return convertView;
    }


    class ViewHolder {
        public TextView tv_question, tv_answer, tv_scan, tv_tab, tv_time;
    }
}
