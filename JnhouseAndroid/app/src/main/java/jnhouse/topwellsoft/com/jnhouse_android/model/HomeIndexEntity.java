package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/5/26.
 * 首页bean
 */
public class HomeIndexEntity implements Serializable {

    private String city_id;//城市Id
    private String city_name;//城市名称
    private String mar_month;//上月
    private String price;//全市均价
    private String volume;//成交量
    private List<HomeZXEntity> zx_list;//咨询列表
    private List<GGEntity> gg_list;//广告列表

    public List<GGEntity> getGg_list() {
        return gg_list;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getMar_month() {
        return mar_month;
    }

    public void setMar_month(String mar_month) {
        this.mar_month = mar_month;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public List<HomeZXEntity> getZx_list() {
        return zx_list;
    }

    public void setZx_list(List<HomeZXEntity> zx_list) {
        this.zx_list = zx_list;
    }
}
