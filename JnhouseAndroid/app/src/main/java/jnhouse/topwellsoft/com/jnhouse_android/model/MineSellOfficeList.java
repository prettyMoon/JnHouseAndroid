package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by topwellsoft on 2016/7/14.
 */
public class MineSellOfficeList implements Serializable {
    private int code;//0 获取成功 、 1:未登录、 -1:异常、  905:二手列表为空 、 906:写字楼委托为空、907:商铺委托为空
    private List<MineSellOfficeEntity> buidingList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<MineSellOfficeEntity> getBuidingList() {
        return buidingList;
    }

    public void setBuidingList(List<MineSellOfficeEntity> buidingList) {
        this.buidingList = buidingList;
    }
}
