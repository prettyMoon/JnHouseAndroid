package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by topwellsoft on 2016/7/13.
 */
public class MyBrowseList implements Serializable {
    private List<BrowseEntity> ll_list;

    public List<BrowseEntity> getLl_list() {
        return ll_list;
    }

    public void setLl_list(List<BrowseEntity> ll_list) {
        this.ll_list = ll_list;
    }
}
