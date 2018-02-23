package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenchen on 2016/7/4.
 */
public class FilterNewHouseData implements Serializable {
    private List<FilterTwoEntity> category;
    private List<FilterEntity> sorts;
    private List<FilterEntity> filters;
    private List<FilterEntity> feature;
    private List<FilterEntity> toward;

    private List<FilterEntity>  line;
    private  List<FilterEntity> borough_properties;
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


    public List<FilterEntity> getLine() {
        return line;
    }

    public void setLine(List<FilterEntity> line) {
        this.line = line;
    }

    public List<FilterEntity> getBorough_properties() {
        return borough_properties;
    }

    public void setBorough_properties(List<FilterEntity> borough_properties) {
        this.borough_properties = borough_properties;
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
