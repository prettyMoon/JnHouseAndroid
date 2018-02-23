package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by chenchen on 2016/7/27.
 */
public class SecondHouseBsListEntity implements Serializable {
    private  String price_s;
    private  String trend_date;

    public String getPrice_s() {
        return price_s;
    }

    public void setPrice_s(String price_s) {
        this.price_s = price_s;
    }

    public String getTrend_date() {
        return trend_date;
    }

    public void setTrend_date(String trend_date) {
        this.trend_date = trend_date;
    }
}
