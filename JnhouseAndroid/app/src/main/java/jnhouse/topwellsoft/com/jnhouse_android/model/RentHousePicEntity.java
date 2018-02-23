package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by chenchen on 2016/6/27.
 */
public class RentHousePicEntity implements Serializable {
    private String pic_url;
    private String pic_desc;

    public String getPic_desc() {
        return pic_desc;
    }

    public void setPic_desc(String pic_desc) {
        this.pic_desc = pic_desc;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }
}
