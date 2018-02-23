package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/14.
 */
public class AgentListEntity implements Serializable {
    private ArrayList<AgentEntity> data_list;

    public ArrayList<AgentEntity> getData_list() {
        return data_list;
    }
}
