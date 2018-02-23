package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/5.
 */
public class AdvertisementEntity implements Serializable {
    private  String path;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String link;
}
