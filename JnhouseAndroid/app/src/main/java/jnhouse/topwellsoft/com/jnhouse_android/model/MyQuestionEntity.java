package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/12.
 */
public class MyQuestionEntity implements Serializable {
    private String askId;// 问题id
    private String askName;//标题
    private String catalogName;// 类别
    private String askTime;//提问时间
    private String answerNum;//  回答量
    private String browseNum;//浏览量

    public String getAskId() {
        return askId;
    }

    public String getAskName() {
        return askName;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public String getAskTime() {
        return askTime;
    }

    public String getAnswerNum() {
        return answerNum;
    }

    public String getBrowseNum() {
        return browseNum;
    }
}
