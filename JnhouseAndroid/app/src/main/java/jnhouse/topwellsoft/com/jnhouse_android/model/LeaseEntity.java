package jnhouse.topwellsoft.com.jnhouse_android.model;

import com.topwellsoft.jnhouse_android.realtime_order.model.BrokerInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by topwellsoft on 2016/7/16.
 */
public class LeaseEntity implements Serializable {
    private int code;
    private List<BrokerEntity> data_list;

    public List<BrokerEntity> getData_list() {
        return data_list;
    }

    public void setData_list(List<BrokerEntity> data_list) {
        this.data_list = data_list;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
