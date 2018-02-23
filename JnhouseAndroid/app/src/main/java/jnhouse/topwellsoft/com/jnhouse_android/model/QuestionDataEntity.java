package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/11.
 */
public class QuestionDataEntity implements Serializable {
    private String askId;// 问题id
    private String askName;// 标题
    private String catalogName;//类别
    private String askTime;// 提问时间
    private String answerNum;//回答量

    public String getAnswerContent() {
        return answerContent;
    }

    public String getAnswerNum() {
        return answerNum;
    }

    public String getAskTime() {
        return askTime;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public String getAskName() {
        return askName;
    }

    public String getAskId() {
        return askId;
    }

    private String answerContent;// 回答内容简写
}
