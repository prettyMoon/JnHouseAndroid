package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/5.
 */
public class PropertymarketEntity implements Serializable {
    private String id;
    private String borough_name;
    private String borough_thumb;

    public String getBorough_avgprice() {
        return borough_avgprice;
    }

    public void setBorough_avgprice(String borough_avgprice) {
        this.borough_avgprice = borough_avgprice;
    }

    public String getBorough_thumb() {
        return borough_thumb;
    }

    public void setBorough_thumb(String borough_thumb) {
        this.borough_thumb = borough_thumb;
    }

    public String getBorough_name() {
        return borough_name;
    }

    public void setBorough_name(String borough_name) {
        this.borough_name = borough_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String borough_avgprice;

}
