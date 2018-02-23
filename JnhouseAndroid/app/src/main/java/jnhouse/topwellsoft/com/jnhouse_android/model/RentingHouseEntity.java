package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by chenchen on 2016/6/21.
 */
public class RentingHouseEntity implements Serializable {

    private String id;//房源id
    private String house_title;//房源标题
    private String house_thumb;//房源缩略图
    private String borough_name;//小区名称
    private String house_price;//租金
    private String house_totalarea;//面积
    private String house_room;//户型
    private String house_toward;//朝向
    private String price_type;//类型
    private String rent_type;//出租类型
    private String house_feature;//特色 带,的字符串，需自己分割
     //无数据
    private boolean isNoData = false;
    private int height;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHouse_title() {
        return house_title;
    }

    public void setHouse_title(String house_title) {
        this.house_title = house_title;
    }

    public String getHouse_thumb() {
        return house_thumb;
    }

    public void setHouse_thumb(String house_thumb) {
        this.house_thumb = house_thumb;
    }

    public String getBorough_name() {
        return borough_name;
    }

    public void setBorough_name(String borough_name) {
        this.borough_name = borough_name;
    }

    public String getHouse_price() {
        return house_price;
    }

    public void setHouse_price(String house_price) {
        this.house_price = house_price;
    }

    public String getHouse_totalarea() {
        return house_totalarea;
    }

    public void setHouse_totalarea(String house_totalarea) {
        this.house_totalarea = house_totalarea;
    }

    public String getHouse_room() {
        return house_room;
    }

    public void setHouse_room(String house_room) {
        this.house_room = house_room;
    }

    public String getHouse_toward() {
        return house_toward;
    }

    public void setHouse_toward(String house_toward) {
        this.house_toward = house_toward;
    }

    public String getPrice_type() {
        return price_type;
    }

    public void setPrice_type(String price_type) {
        this.price_type = price_type;
    }

    public String getRent_type() {
        return rent_type;
    }

    public void setRent_type(String rent_type) {
        this.rent_type = rent_type;
    }

    public String getHouse_feature() {
        return house_feature;
    }

    public void setHouse_feature(String house_feature) {
        this.house_feature = house_feature;
    }

    public boolean isNoData() {
        return isNoData;
    }

    public void setNoData(boolean noData) {
        isNoData = noData;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}