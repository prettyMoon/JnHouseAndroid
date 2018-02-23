package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by topwellsoft on 2016/7/11.
 */
public class SearchCommutityList implements Serializable {
    private List<SearchCommutityEntity> mSearchCommutityList;

    public List<SearchCommutityEntity> getmSearchCommutityList() {
        return mSearchCommutityList;
    }

    public void setmSearchCommutityList(List<SearchCommutityEntity> mSearchCommutityList) {
        this.mSearchCommutityList = mSearchCommutityList;
    }
}
