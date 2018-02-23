package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/6/6.
 */
public class SecondHouseList implements Serializable {

    private List<SecondHouseFeature> feature_list;
    private List<SecondHouseEntity> data_list;
    private String data_size;

    public List<SecondHouseFeature> getFeature_list() {
        return feature_list;
    }

    public void setFeature_list(List<SecondHouseFeature> feature_list) {
        this.feature_list = feature_list;
    }

    public List<SecondHouseEntity> getData_list() {
        return data_list;
    }

    public void setData_list(List<SecondHouseEntity> data_list) {
        this.data_list = data_list;
    }

    public String getData_size() {
        return data_size;
    }

    public void setData_size(String data_size) {
        this.data_size = data_size;
    }
}
