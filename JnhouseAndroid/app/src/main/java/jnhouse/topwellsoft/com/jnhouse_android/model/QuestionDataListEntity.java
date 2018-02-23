package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/11.
 */
public class QuestionDataListEntity implements Serializable {
    private ArrayList<QuestionDataEntity> wd_list;
    private ArrayList<QuestionSortEntity> fl_list;

    public ArrayList<QuestionSortEntity> getSortlist() {
        return fl_list;
    }

    public ArrayList<QuestionDataEntity> getDatalist() {
        return wd_list;
    }

}
