package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenchen on 2016/8/24.
 */
public class FilerRentData implements Serializable {
    private List<FilterTwoEntity> category;
    private List<FilterEntity> sorts;
    private List<FilterEntity> renttype;
    private List<FilterEntity> feature;
    private List<FilterEntity> toward;
    private List<FilterEntity> areaType;
    private List<FilterEntity> ageType;
    private List<FilterEntity> houseType;
    private List<FilterEntity> listSort;


    public List<FilterTwoEntity> getCategory() {
        return category;
    }

    public void setCategory(List<FilterTwoEntity> category) {
        this.category = category;
    }

    public List<FilterEntity> getSorts() {
        return sorts;
    }

    public void setSorts(List<FilterEntity> sorts) {
        this.sorts = sorts;
    }

    public List<FilterEntity> getRenttype() {
        return renttype;
    }

    public void setRenttype(List<FilterEntity> renttype) {
        this.renttype = renttype;
    }

    public List<FilterEntity> getFeature() {
        return feature;
    }

    public void setFeature(List<FilterEntity> feature) {
        this.feature = feature;
    }

    public List<FilterEntity> getToward() {
        return toward;
    }

    public void setToward(List<FilterEntity> toward) {
        this.toward = toward;
    }

    public List<FilterEntity> getAreaType() {
        return areaType;
    }

    public void setAreaType(List<FilterEntity> areaType) {
        this.areaType = areaType;
    }

    public List<FilterEntity> getAgeType() {
        return ageType;
    }

    public void setAgeType(List<FilterEntity> ageType) {
        this.ageType = ageType;
    }

    public List<FilterEntity> getHouseType() {
        return houseType;
    }

    public void setHouseType(List<FilterEntity> houseType) {
        this.houseType = houseType;
    }

    public List<FilterEntity> getListSort() {
        return listSort;
    }

    public void setListSort(List<FilterEntity> listSort) {
        this.listSort = listSort;
    }
}
