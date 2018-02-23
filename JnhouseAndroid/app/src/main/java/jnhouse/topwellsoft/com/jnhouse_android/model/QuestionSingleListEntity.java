package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/11.
 */
public class QuestionSingleListEntity implements Serializable {
    private ArrayList<QuestionSingleSortEntity> wd_list;

    public ArrayList<QuestionSingleSortEntity> getWd_list() {
        return wd_list;
    }
}
