package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/5.
 */
public class ConsultList implements Serializable {
    private ArrayList<AdvertisementEntity> gg_list;//广告
    private ArrayList<HeadLineEntity> tt_list;//今日头条
    private ArrayList<DealEntity> es_list;//成交
    private ArrayList<PropertymarketEntity> ls_list;//楼市
    private ArrayList<SpecificEntity> zt_list;//专题

    public ArrayList<ReviewEntity> getYz_list() {
        return yz_list;
    }

    public void setYz_list(ArrayList<ReviewEntity> yz_list) {
        this.yz_list = yz_list;
    }

    public ArrayList<SpecificEntity> getZt_list() {
        return zt_list;
    }

    public void setZt_list(ArrayList<SpecificEntity> zt_list) {
        this.zt_list = zt_list;
    }

    public ArrayList<PropertymarketEntity> getLs_list() {
        return ls_list;
    }

    public void setLs_list(ArrayList<PropertymarketEntity> ls_list) {
        this.ls_list = ls_list;
    }

    public ArrayList<DealEntity> getEs_list() {
        return es_list;
    }

    public void setEs_list(ArrayList<DealEntity> es_list) {
        this.es_list = es_list;
    }

    public ArrayList<HeadLineEntity> getTt_list() {
        return tt_list;
    }

    public void setTt_list(ArrayList<HeadLineEntity> tt_list) {
        this.tt_list = tt_list;
    }

    public ArrayList<AdvertisementEntity> getGg_list() {
        return gg_list;
    }

    public void setGg_list(ArrayList<AdvertisementEntity> gg_list) {
        this.gg_list = gg_list;
    }

    private ArrayList<ReviewEntity> yz_list;

}
