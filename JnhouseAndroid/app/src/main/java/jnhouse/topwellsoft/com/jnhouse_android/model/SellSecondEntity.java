package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by topwellsoft on 2016/7/11.
 */
public class SellSecondEntity implements Serializable {
    private int code;//  1:未登录  -1:异常  0:委托成功   901:委托失败
    private String city_id;// 城市id
    private String cityarea_id;// 城区id
    private String cityarea2_id;// 商圈id
    private String house_price;// 房价
    private String house_area;// 面积
    private String house_topfloor;// 最高楼层
    private String house_floor;// 所在楼层
    private int house_toward;// 朝向  （1：东，2西，3：南，4北，5：东南，：6:西南， 7：东北，8：西北，9：南北，10：东西）
    private int house_fitment;// 装修 （1：毛坯，2：普通装修，3：精装修，4：豪华装修）
    private String house_age;// 房龄
    private String house_title;// 标题
    private String borough_id;// 小区id
    private String borough_name;// 小区名称
    private String house_desc;// 描述
    private String user_name;// 联系人
    private String user_id;// 联系人id
    private String user_phone;// 联系人手机
    private String tel_time_start;// 接电话时间-起
    private String tel_time_end;// 接电话时间-终
    private List<String> borker_id;// 选择的置业顾问 （置业顾问id，逗号分隔）

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCityarea_id() {
        return cityarea_id;
    }

    public void setCityarea_id(String cityarea_id) {
        this.cityarea_id = cityarea_id;
    }

    public String getCityarea2_id() {
        return cityarea2_id;
    }

    public void setCityarea2_id(String cityarea2_id) {
        this.cityarea2_id = cityarea2_id;
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

    public String getHouse_topfloor() {
        return house_topfloor;
    }

    public void setHouse_topfloor(String house_topfloor) {
        this.house_topfloor = house_topfloor;
    }

    public String getHouse_floor() {
        return house_floor;
    }

    public void setHouse_floor(String house_floor) {
        this.house_floor = house_floor;
    }

    public int getHouse_toward() {
        return house_toward;
    }

    public void setHouse_toward(int house_toward) {
        this.house_toward = house_toward;
    }

    public int getHouse_fitment() {
        return house_fitment;
    }

    public void setHouse_fitment(int house_fitment) {
        this.house_fitment = house_fitment;
    }

    public String getHouse_age() {
        return house_age;
    }

    public void setHouse_age(String house_age) {
        this.house_age = house_age;
    }

    public String getHouse_title() {
        return house_title;
    }

    public void setHouse_title(String house_title) {
        this.house_title = house_title;
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

    public String getHouse_desc() {
        return house_desc;
    }

    public void setHouse_desc(String house_desc) {
        this.house_desc = house_desc;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

    public String getTel_time_start() {
        return tel_time_start;
    }

    public void setTel_time_start(String tel_time_start) {
        this.tel_time_start = tel_time_start;
    }

    public String getTel_time_end() {
        return tel_time_end;
    }

    public void setTel_time_end(String tel_time_end) {
        this.tel_time_end = tel_time_end;
    }

    public List<String> getBorker_id() {
        return borker_id;
    }

    public void setBorker_id(List<String> borker_id) {
        this.borker_id = borker_id;
    }
}
