package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.model.MyQuestionEntity;
import jnhouse.topwellsoft.com.jnhouse_android.model.QuestionSingleSortEntity;

/**
 * Created by Administrator on 2016/7/11.
 */
public class MyQuestionListAdapter extends BaseAdapter {
    private ArrayList<MyQuestionEntity> list;
    private Context context;
    private LayoutInflater inflater;

    public ArrayList<String> getIsSelect() {
        return isSelect;
    }

    private ArrayList<String> isSelect;
    private boolean canShown = false;


    public MyQuestionListAdapter(Context context, ArrayList<MyQuestionEntity> list) {
        isSelect = new ArrayList<String>();
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public void setCanShown() {
        this.canShown = !this.canShown;
    }

    public boolean isCanShown() {
        return canShown;
    }

    public ArrayList<MyQuestionEntity> getList() {
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
        final ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.question_list_item, null);
        }
        holder.checkBox = (CheckBox) convertView.findViewById(R.id.list_checkbox);
        holder.layout_select = (LinearLayout) convertView.findViewById(R.id.layout_select);
        holder.layout_left = (LinearLayout) convertView.findViewById(R.id.layout_left);
        holder.tv_question = (TextView) convertView.findViewById(R.id.lv_tv_question);
        holder.tv_answer = (TextView) convertView.findViewById(R.id.lv_question_answer);
        holder.tv_time = (TextView) convertView.findViewById(R.id.lv_question_time);
        holder.tv_scan = (TextView) convertView.findViewById(R.id.lv_question_scan);
        holder.tv_tab = (TextView) convertView.findViewById(R.id.lv_question_tab);
        holder.tv_question.setText(list.get(position).getAskName());
        holder.tv_answer.setVisibility(View.GONE);
        if (canShown) {
            holder.layout_select.setVisibility(View.VISIBLE);

        } else {
            holder.layout_select.setVisibility(View.GONE);
        }
        //数据
        holder.tv_time.setText(list.get(position).getAskTime());
        holder.tv_scan.setText(list.get(position).getAnswerNum());
        holder.tv_tab.setText("  "+list.get(position).getCatalogName()+"  ");
        final String current = position + "";
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.checkBox.isChecked()) {
                    isSelect.add(current);
                } else {
                    if (isSelect.contains(current)) {
                        isSelect.remove(current);
                    }
                }
            }
        });
        return convertView;
    }


    class ViewHolder {
        public TextView tv_question, tv_answer, tv_scan, tv_tab, tv_time;
        public LinearLayout layout_select, layout_left;
        private CheckBox checkBox;
    }
}
