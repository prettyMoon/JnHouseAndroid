package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/12.
 */
public class MyQuestionListEntity implements Serializable {
    private ArrayList<MyQuestionEntity> wd_list;

    public ArrayList<MyQuestionEntity> getWd_list() {
        return wd_list;
    }
}
