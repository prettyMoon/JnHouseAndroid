package com.topwellsoft.jnhouse_android.map_find_house.model;





public class TradeAreaLevelNewHouseMarkerData   {
    private Integer id;
    private double lat;
    private double lng;
    private String tradeName;
    private int newHouseCount;


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



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public int getNewHouseCount() {
        return newHouseCount;
    }

    public void setNewHouseCount(int newHouseCount) {
        this.newHouseCount = newHouseCount;
    }
}