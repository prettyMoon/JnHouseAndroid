package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by DELL on 2016/8/16.
 */
public class OrderingEntity implements Serializable {
    private int code;
    private String or_id;//：订单id
    private String user_id;//：用户id
    private String user_phone;//：用户手机
    private String user_name;//：用户昵称
    private String order_title;//：位置名称
    private String order_lng;//：位置经度
    private String order_lat;//：位置纬度
    private int house_type;//：预约类型1二手房2新房3租房
    private String show_time;//：看房时间：随时看房、16-06-15周三（全天、上午、下午、晚上）
    private String show_condition;//：看房要求
    private String state;//：状态：预约中
    private String order_time;//：下单时间

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getOr_id() {
        return or_id;
    }

    public void setOr_id(String or_id) {
        this.or_id = or_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getOrder_title() {
        return order_title;
    }

    public void setOrder_title(String order_title) {
        this.order_title = order_title;
    }

    public String getOrder_lng() {
        return order_lng;
    }

    public void setOrder_lng(String order_lng) {
        this.order_lng = order_lng;
    }

    public String getOrder_lat() {
        return order_lat;
    }

    public void setOrder_lat(String order_lat) {
        this.order_lat = order_lat;
    }

    public int getHouse_type() {
        return house_type;
    }

    public void setHouse_type(int house_type) {
        this.house_type = house_type;
    }

    public String getShow_time() {
        return show_time;
    }

    public void setShow_time(String show_time) {
        this.show_time = show_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getShow_condition() {
        return show_condition;
    }

    public void setShow_condition(String show_condition) {
        this.show_condition = show_condition;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }
}
