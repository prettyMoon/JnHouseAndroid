package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by topwellsoft on 2016/6/29.
 */
public class CommunityEntity implements Serializable {
    private List<String> bor_list;
    private int bor_id;
    private String borough;
    private String bor_name;

    public List<String> getBor_list() {
        return bor_list;
    }

    public void setBor_list(List<String> bor_list) {
        this.bor_list = bor_list;
    }

    public int getBor_id() {
        return bor_id;
    }

    public void setBor_id(int bor_id) {
        this.bor_id = bor_id;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getBor_name() {
        return bor_name;
    }

    public void setBor_name(String bor_name) {
        this.bor_name = bor_name;
    }
}
