package com.topwellsoft.jnhouse_android.realtime_order;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/9.
 */
public class ByHouseEntity implements Serializable {
    private int code;
    private int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
