package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/11.
 */
public class BaikeSecondEntity implements Serializable {
    public ArrayList<BaikeDataListEntity> getaList() {
        return aList;
    }

    public void setaList(ArrayList<BaikeDataListEntity> aList) {
        this.aList = aList;
    }

    private ArrayList<BaikeDataListEntity> aList;

}
