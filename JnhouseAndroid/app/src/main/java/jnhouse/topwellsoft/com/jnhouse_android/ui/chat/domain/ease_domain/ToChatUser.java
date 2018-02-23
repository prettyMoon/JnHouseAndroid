package jnhouse.topwellsoft.com.jnhouse_android.ui.chat.domain.ease_domain;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/22.
 */
public class ToChatUser implements Serializable {
    private String userid;
    private String username;
    private String mobile;
    private String nickname;
    private String avatar;
    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public ToChatUser(String userid, String username, String mobile, String nickname) {
    }

    public ToChatUser() {
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
