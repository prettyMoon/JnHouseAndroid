package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by topwellsoft on 2016/7/14.
 */
public class MineBuyEntity implements Serializable {
    //id：委托主键、house_title:委托标题、want_price：期望价格、state：状态、borough_name：小区名称、
    // 期望户型-室：want_room、期望户型-厅:want_hall、期望户型-卫:want_toilet
    private String id;
    private String house_title;
    private String want_price;
    private String state;
    private String borough_name;
    private String want_room;
    private String want_hall;
    private String want_toilet;

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

    public String getWant_price() {
        return want_price;
    }

    public void setWant_price(String want_price) {
        this.want_price = want_price;
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

    public String getWant_room() {
        return want_room;
    }

    public void setWant_room(String want_room) {
        this.want_room = want_room;
    }

    public String getWant_hall() {
        return want_hall;
    }

    public void setWant_hall(String want_hall) {
        this.want_hall = want_hall;
    }

    public String getWant_toilet() {
        return want_toilet;
    }

    public void setWant_toilet(String want_toilet) {
        this.want_toilet = want_toilet;
    }
}
