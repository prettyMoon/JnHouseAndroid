package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by topwellsoft on 2016/7/13.
 */
public class MineSellSecondEntity implements Serializable {
    private String id;
    private String house_title;
    private String house_price;
    private String house_room;
    private String house_hall;
    private String house_toilet;
    private String house_area;
    private String house_toward;
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

    public String getHouse_price() {
        return house_price;
    }

    public void setHouse_price(String house_price) {
        this.house_price = house_price;
    }

    public String getHouse_room() {
        return house_room;
    }

    public void setHouse_room(String house_room) {
        this.house_room = house_room;
    }

    public String getHouse_hall() {
        return house_hall;
    }

    public void setHouse_hall(String house_hall) {
        this.house_hall = house_hall;
    }

    public String getHouse_toilet() {
        return house_toilet;
    }

    public void setHouse_toilet(String house_toilet) {
        this.house_toilet = house_toilet;
    }

    public String getHouse_area() {
        return house_area;
    }

    public void setHouse_area(String house_area) {
        this.house_area = house_area;
    }

    public String getHouse_toward() {
        return house_toward;
    }

    public void setHouse_toward(String house_toward) {
        this.house_toward = house_toward;
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
