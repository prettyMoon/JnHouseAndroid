package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by chenchen on 2016/6/15.
 */
public class NewHouseFeature implements Serializable {
    private  String di_value;
    private  String di_caption;

    public String getDi_value() {
        return di_value;
    }

    public void setDi_value(String di_value) {
        this.di_value = di_value;
    }

    public String getDi_caption() {
        return di_caption;
    }

    public void setDi_caption(String di_caption) {
        this.di_caption = di_caption;
    }
}