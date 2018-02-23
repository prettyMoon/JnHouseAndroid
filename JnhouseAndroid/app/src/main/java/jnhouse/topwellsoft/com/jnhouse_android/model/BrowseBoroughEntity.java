package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/29.
 */
public class BrowseBoroughEntity implements Serializable {
    private String b_borough_id;//楼盘id,
    private String b_borough_title;//楼盘标题,
    private String b_borough_thumb;//楼盘标题图,
    private String b_borough_price;//楼盘均价

    public String getB_borough_id() {
        return b_borough_id;
    }

    public void setB_borough_id(String b_borough_id) {
        this.b_borough_id = b_borough_id;
    }

    public String getB_borough_price() {
        return b_borough_price;
    }

    public void setB_borough_price(String b_borough_price) {
        this.b_borough_price = b_borough_price;
    }

    public String getB_borough_thumb() {
        return b_borough_thumb;
    }

    public void setB_borough_thumb(String b_borough_thumb) {
        this.b_borough_thumb = b_borough_thumb;
    }

    public String getB_borough_title() {
        return b_borough_title;
    }

    public void setB_borough_title(String b_borough_title) {
        this.b_borough_title = b_borough_title;
    }
}
