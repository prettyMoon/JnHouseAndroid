package jnhouse.topwellsoft.com.jnhouse_android.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/12.
 */
public class AnswerEntity implements Serializable {
    private String ansUserId;//回答人id
    private String ansUserName;//回答人
    private String avatar;//回答人头像
    private String answerContent;//回答内容
    private String ansTime;// 回答时间
    private String praiseNum;//点赞量
    private String is_praise;//是否赞过   1是0否
    private String answerId;

    public String getAnswerId() {
        return answerId;
    }

    public String getAnsUserId() {
        return ansUserId;
    }

    public String getAnsUserName() {
        return ansUserName;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public String getPraiseNum() {
        return praiseNum;
    }

    public String getIs_praise() {
        return is_praise;
    }

    public String getAnsTime() {
        return ansTime;
    }
}
