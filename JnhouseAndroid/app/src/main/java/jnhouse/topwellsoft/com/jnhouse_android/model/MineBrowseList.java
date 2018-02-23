package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by topwellsoft on 2016/7/12.
 */
public class MineBrowseList implements Serializable {
    private List<CollectionEntity> gz_list;

    public List<CollectionEntity> getGz_list() {
        return gz_list;
    }

    public void setGz_list(List<CollectionEntity> gz_list) {
        this.gz_list = gz_list;
    }
}
