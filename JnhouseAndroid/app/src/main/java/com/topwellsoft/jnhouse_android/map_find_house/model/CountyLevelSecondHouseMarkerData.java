package com.topwellsoft.jnhouse_android.map_find_house.model;

public class CountyLevelSecondHouseMarkerData   {
    private Integer id;
    private double lat;
    private double lng;
    private String countyName;
    private int secondHouseCount;


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

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSecondHouseCount() {
        return secondHouseCount;
    }

    public void setSecondHouseCount(int secondHouseCount) {
        this.secondHouseCount = secondHouseCount;
    }
}