package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 16-5-22.
 */
public class FilterData implements Serializable {

    private List<FilterTwoEntity> category;
    private List<FilterEntity> sorts;
    private List<FilterEntity> filters;
    private List<FilterEntity> feature;
    private List<FilterEntity> toward;
    private List<FilterEntity> areaType;
    private List<FilterEntity> ageType;
    private List<FilterEntity> houseType;
    private List<FilterEntity> listSort;
    private List<FilterEntity>rentType;
    private List<FilterEntity>fitment;

    public List<FilterEntity> getFitment() {
        return fitment;
    }

    public void setFitment(List<FilterEntity> fitment) {
        this.fitment = fitment;
    }

    public List<FilterEntity> getRentType() {
        return rentType;
    }

    public void setRentType(List<FilterEntity> rentType) {
        this.rentType = rentType;
    }

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

    public List<FilterEntity> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterEntity> filters) {
        this.filters = filters;
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
