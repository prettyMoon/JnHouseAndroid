package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/29.
 */
public class NearBoroughEntity implements Serializable {
    private String near_borough_id;//楼盘id,
    private String near_borough_name;//楼盘名称,
    private String near_borough_thumb;//楼盘标题图,
    private String near_borough_avgprice;//楼盘均价

    public String getNear_borough_id() {
        return near_borough_id;
    }

    public void setNear_borough_id(String near_borough_id) {
        this.near_borough_id = near_borough_id;
    }

    public String getNear_borough_avgprice() {
        return near_borough_avgprice;
    }

    public void setNear_borough_avgprice(String near_borough_avgprice) {
        this.near_borough_avgprice = near_borough_avgprice;
    }

    public String getNear_borough_thumb() {
        return near_borough_thumb;
    }

    public void setNear_borough_thumb(String near_borough_thumb) {
        this.near_borough_thumb = near_borough_thumb;
    }

    public String getNear_borough_name() {
        return near_borough_name;
    }

    public void setNear_borough_name(String near_borough_name) {
        this.near_borough_name = near_borough_name;
    }
}
