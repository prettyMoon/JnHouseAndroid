package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by chenchen on 2016/7/14.
 */
public class LiuLanEntity implements Serializable {

    private String kf_date;//看房日期
    private String broker_id;//带看人id
    private String broker;//带看人
    private  String broker_phone;//带看人手机号
    public String getKf_date() {
        return kf_date;
    }

    public void setKf_date(String kf_date) {
        this.kf_date = kf_date;
    }

    public String getBroker_id() {
        return broker_id;
    }

    public void setBroker_id(String broker_id) {
        this.broker_id = broker_id;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getBroker_phone() {
        return broker_phone;
    }

    public void setBroker_phone(String broker_phone) {
        this.broker_phone = broker_phone;
    }


}
