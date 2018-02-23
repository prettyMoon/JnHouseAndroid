package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by chenchen on 2016/6/21.
 */
public class GetPasswordEntity implements Serializable {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private  int code;

}
