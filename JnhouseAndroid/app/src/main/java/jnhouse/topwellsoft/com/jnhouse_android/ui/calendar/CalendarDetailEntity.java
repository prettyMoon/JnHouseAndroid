package jnhouse.topwellsoft.com.jnhouse_android.ui.calendar;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/10.
 */
public class CalendarDetailEntity implements Serializable {
    private int code;//: 1:未登录  -1:异常 0:成功  1302记录不存在

    private String user_id;//:用户id
    private String user_phone;//:用户手机
    private String user_name;//:用户名
    private String order_title;//:位置名称
    private String order_lng;//:位置经度
    private String order_lat;//:位置纬度
    private String house_id;//:房子id
    private String house_type;//:房子类型
    private String borough_id;//:小区id
    private String borough_name;//:小区名
    private String show_time;//:看房时间
    private String show_condition;//:看房要求
    private String broker_phone;//:置业顾问手机
    private String broker_name;//:置业顾问姓名
    private String state;//:订单状态
    private String is_add;//:是否为追加订单
    private String order_time;//:下单时间
    // 状态为预约成功、待评价时返回 ：
    private String bespoke_time;//预约成功时间、
    private String complete_time;//完成时间、
    private String evaluate_time;//评价时间

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public String getHouse_id() {
        return house_id;
    }

    public void setHouse_id(String house_id) {
        this.house_id = house_id;
    }

    public String getHouse_type() {
        return house_type;
    }

    public void setHouse_type(String house_type) {
        this.house_type = house_type;
    }

    public String getBorough_id() {
        return borough_id;
    }

    public void setBorough_id(String borough_id) {
        this.borough_id = borough_id;
    }

    public String getBorough_name() {
        return borough_name;
    }

    public void setBorough_name(String borough_name) {
        this.borough_name = borough_name;
    }

    public String getShow_time() {
        return show_time;
    }

    public void setShow_time(String show_time) {
        this.show_time = show_time;
    }

    public String getShow_condition() {
        return show_condition;
    }

    public void setShow_condition(String show_condition) {
        this.show_condition = show_condition;
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

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getBespoke_time() {
        return bespoke_time;
    }

    public void setBespoke_time(String bespoke_time) {
        this.bespoke_time = bespoke_time;
    }

    public String getComplete_time() {
        return complete_time;
    }

    public void setComplete_time(String complete_time) {
        this.complete_time = complete_time;
    }

    public String getEvaluate_time() {
        return evaluate_time;
    }

    public void setEvaluate_time(String evaluate_time) {
        this.evaluate_time = evaluate_time;
    }
}
