package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by topwellsoft on 2016/7/14.
 */
public class MineWantEntity implements Serializable {
    private String id;//id:主键、state:委托状态、house_hall:户型-厅、house_room:户型-室、house_toilet:户型-卫、
    // want_money:期望租金、title:委托标题、borough:小区名称
    private String state;
    private String house_hall;
    private String house_room;
    private String house_toilet;
    private String want_money;
    private String title;
    private String borough;

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

    public String getWant_money() {
        return want_money;
    }

    public void setWant_money(String want_money) {
        this.want_money = want_money;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }
}
