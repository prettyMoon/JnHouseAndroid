package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by topwellsoft on 2016/7/14.
 */
public class MineSellOfficeEntity implements Serializable {
    private String id;
    private String house_title;
    private String buiding_type;
    private String house_price;
    private String house_area;
    private String state;
    private String borough_name;
    private String house_img;

    public String getHouse_img() {
        return house_img;
    }

    public void setHouse_img(String house_img) {
        this.house_img = house_img;
    }

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

    public String getBuiding_type() {
        return buiding_type;
    }

    public void setBuiding_type(String buiding_type) {
        this.buiding_type = buiding_type;
    }

    public String getHouse_price() {
        return house_price;
    }

    public void setHouse_price(String house_price) {
        this.house_price = house_price;
    }

    public String getHouse_area() {
        return house_area;
    }

    public void setHouse_area(String house_area) {
        this.house_area = house_area;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBorough_name() {
        return borough_name;
    }

    public void setBorough_name(String borough_name) {
        this.borough_name = borough_name;
    }
}
