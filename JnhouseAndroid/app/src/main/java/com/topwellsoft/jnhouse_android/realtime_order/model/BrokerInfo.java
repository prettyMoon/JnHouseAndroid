package com.topwellsoft.jnhouse_android.realtime_order.model;
public class BrokerInfo{
    private String brokerLoginName;
    private Integer dbBrokerId;
    private double lat;
    private double lng;




    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getBrokerLoginName() {
        return brokerLoginName;
    }

    public void setBrokerLoginName(String brokerLoginName) {
        this.brokerLoginName = brokerLoginName;
    }

    public Integer getDbBrokerId() {
        return dbBrokerId;
    }

    public void setDbBrokerId(Integer dbBrokerId) {
        this.dbBrokerId = dbBrokerId;
    }
}
