package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenchen on 2016/7/13.
 */
public class BoroughDetail implements Serializable {
    private String house_id;//小区id(必填)
    private String user_id;//用户id(可空)
    private String borough_name;//小区名
    private String borough_address;//小区地址
    private String borough_type;//房屋类型
    private String desc_environment;//绿化率
    private String borough_developer;//开发商
    private String borough_company;//物业公司
    private String borough_costs;//物业费（元/m²）
    private String borough_completion;//建筑年代
    private String desc_support;//小区描述
    private String layout_map;//经纬度
    private String cjsl_s;//本小区二手房成交数量
    private String cjsl_r;//本小区租房成交数量
    //周边配套
    private String elementary_school;//小学
    private String middle_school;//初高中
    private String borough_shop;//商场
    private String borough_hospital;//医院
    private String borough_bank;// 银行
    private String borough_bus;//交通路线
    private String bxqesfsl;//本小区二手房房源数量
    private String bxqzfsl;//本小区租房房源数量
    private String is_fav;//是否关注此房源（0未关注1已关注）
    private List<BoroughPicList> pic_list;

    public String getHouse_id() {
        return house_id;
    }

    public void setHouse_id(String house_id) {
        this.house_id = house_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBorough_name() {
        return borough_name;
    }

    public void setBorough_name(String borough_name) {
        this.borough_name = borough_name;
    }

    public String getBorough_address() {
        return borough_address;
    }

    public void setBorough_address(String borough_address) {
        this.borough_address = borough_address;
    }

    public String getBorough_type() {
        return borough_type;
    }

    public void setBorough_type(String borough_type) {
        this.borough_type = borough_type;
    }

    public String getDesc_environment() {
        return desc_environment;
    }

    public void setDesc_environment(String desc_environment) {
        this.desc_environment = desc_environment;
    }

    public String getBorough_developer() {
        return borough_developer;
    }

    public void setBorough_developer(String borough_developer) {
        this.borough_developer = borough_developer;
    }

    public String getBorough_company() {
        return borough_company;
    }

    public void setBorough_company(String borough_company) {
        this.borough_company = borough_company;
    }

    public String getBorough_costs() {
        return borough_costs;
    }

    public void setBorough_costs(String borough_costs) {
        this.borough_costs = borough_costs;
    }

    public String getBorough_completion() {
        return borough_completion;
    }

    public void setBorough_completion(String borough_completion) {
        this.borough_completion = borough_completion;
    }

    public String getDesc_support() {
        return desc_support;
    }

    public void setDesc_support(String desc_support) {
        this.desc_support = desc_support;
    }

    public String getLayout_map() {
        return layout_map;
    }

    public void setLayout_map(String layout_map) {
        this.layout_map = layout_map;
    }

    public String getCjsl_s() {
        return cjsl_s;
    }

    public void setCjsl_s(String cjsl_s) {
        this.cjsl_s = cjsl_s;
    }

    public String getCjsl_r() {
        return cjsl_r;
    }

    public void setCjsl_r(String cjsl_r) {
        this.cjsl_r = cjsl_r;
    }

    public String getElementary_school() {
        return elementary_school;
    }

    public void setElementary_school(String elementary_school) {
        this.elementary_school = elementary_school;
    }

    public String getMiddle_school() {
        return middle_school;
    }

    public void setMiddle_school(String middle_school) {
        this.middle_school = middle_school;
    }

    public String getBorough_shop() {
        return borough_shop;
    }

    public void setBorough_shop(String borough_shop) {
        this.borough_shop = borough_shop;
    }

    public String getBorough_hospital() {
        return borough_hospital;
    }

    public void setBorough_hospital(String borough_hospital) {
        this.borough_hospital = borough_hospital;
    }

    public String getBorough_bank() {
        return borough_bank;
    }

    public void setBorough_bank(String borough_bank) {
        this.borough_bank = borough_bank;
    }

    public String getBorough_bus() {
        return borough_bus;
    }

    public void setBorough_bus(String borough_bus) {
        this.borough_bus = borough_bus;
    }

    public String getBxqesfsl() {
        return bxqesfsl;
    }

    public void setBxqesfsl(String bxqesfsl) {
        this.bxqesfsl = bxqesfsl;
    }

    public String getBxqzfsl() {
        return bxqzfsl;
    }

    public void setBxqzfsl(String bxqzfsl) {
        this.bxqzfsl = bxqzfsl;
    }

    public String getIs_fav() {
        return is_fav;
    }

    public void setIs_fav(String is_fav) {
        this.is_fav = is_fav;
    }

    public List<BoroughPicList> getPic_list() {
        return pic_list;
    }

    public void setPic_list(List<BoroughPicList> pic_list) {
        this.pic_list = pic_list;
    }
}
