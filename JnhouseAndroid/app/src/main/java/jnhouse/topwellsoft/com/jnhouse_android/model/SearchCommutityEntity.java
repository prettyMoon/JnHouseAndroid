package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by topwellsoft on 2016/7/11.
 */
public class SearchCommutityEntity implements Serializable {
    private String bor_id; //小区id
    private String borough; //小区名称
    private String city_id; //城市
    private String cityarea_id; //城区
    private String cityarea2_id; //商圈

    public String getBor_id() {
        return bor_id;
    }

    public void setBor_id(String bor_id) {
        this.bor_id = bor_id;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCityarea_id() {
        return cityarea_id;
    }

    public void setCityarea_id(String cityarea_id) {
        this.cityarea_id = cityarea_id;
    }

    public String getCityarea2_id() {
        return cityarea2_id;
    }

    public void setCityarea2_id(String cityarea2_id) {
        this.cityarea2_id = cityarea2_id;
    }
}
