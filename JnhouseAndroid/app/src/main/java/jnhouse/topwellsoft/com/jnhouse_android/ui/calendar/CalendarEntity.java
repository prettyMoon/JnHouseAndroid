package jnhouse.topwellsoft.com.jnhouse_android.ui.calendar;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/10.
 */
public class CalendarEntity implements Serializable {
    private String id;//订单id、
    private String broker_pic;//置业顾问头像、
    private String broker_phone;//置业顾问手机、
    private String broker_name;//置业顾问昵称、
    private String order_title;//位置名称、
    private String house_id;//房源id、
    private String house_type;//房源类别、
    private String show_time;//看房时间、
    private String state;//订单状态、
    private String is_add;//是否为追加订单（0否1是）

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBroker_pic() {
        return broker_pic;
    }

    public void setBroker_pic(String broker_pic) {
        this.broker_pic = broker_pic;
    }

    public String getBroker_phone() {
        return broker_phone;
    }

    public void setBroker_phone(String broker_phone) {
        this.broker_phone = broker_phone;
    }

    public String getBroker_name() {
        return broker_name;
    }

    public void setBroker_name(String broker_name) {
        this.broker_name = broker_name;
    }

    public String getOrder_title() {
        return order_title;
    }

    public void setOrder_title(String order_title) {
        this.order_title = order_title;
    }

    public String getHouse_id() {
        return house_id;
    }

    public void setHouse_id(String house_id) {
        this.house_id = house_id;
    }

    public String getShow_time() {
        return show_time;
    }

    public void setShow_time(String show_time) {
        this.show_time = show_time;
    }

    public String getHouse_type() {
        return house_type;
    }

    public void setHouse_type(String house_type) {
        this.house_type = house_type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIs_add() {
        return is_add;
    }

    public void setIs_add(String is_add) {
        this.is_add = is_add;
    }
}
