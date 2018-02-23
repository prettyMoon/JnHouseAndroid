package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by topwellsoft on 2016/7/14.
 */
public class MineRentEntity implements Serializable {
    private String id;
    private String state;
    private String house_hall;
    private String house_room;
    private String house_toilet;
    private String house_kitchen;
    private String house_balcony;
    private String house_area;
    private String house_title;
    private String rent_price;
    private String rent_price_unit;
    private String borough_name;
    private String house_img;

    public String getRent_price_unit() {
        return rent_price_unit;
    }

    public void setRent_price_unit(String rent_price_unit) {
        this.rent_price_unit = rent_price_unit;
    }

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHouse_hall() {
        return house_hall;
    }

    public void setHouse_hall(String house_hall) {
        this.house_hall = house_hall;
    }

    public String getHouse_room() {
        return house_room;
    }

    public void setHouse_room(String house_room) {
        this.house_room = house_room;
    }

    public String getHouse_toilet() {
        return house_toilet;
    }

    public void setHouse_toilet(String house_toilet) {
        this.house_toilet = house_toilet;
    }

    public String getHouse_kitchen() {
        return house_kitchen;
    }

    public void setHouse_kitchen(String house_kitchen) {
        this.house_kitchen = house_kitchen;
    }

    public String getHouse_balcony() {
        return house_balcony;
    }

    public void setHouse_balcony(String house_balcony) {
        this.house_balcony = house_balcony;
    }

    public String getHouse_area() {
        return house_area;
    }

    public void setHouse_area(String house_area) {
        this.house_area = house_area;
    }

    public String getHouse_title() {
        return house_title;
    }

    public void setHouse_title(String house_title) {
        this.house_title = house_title;
    }

    public String getRent_price() {
        return rent_price;
    }

    public void setRent_price(String rent_price) {
        this.rent_price = rent_price;
    }

    public String getBorough_name() {
        return borough_name;
    }

    public void setBorough_name(String borough_name) {
        this.borough_name = borough_name;
    }
}
