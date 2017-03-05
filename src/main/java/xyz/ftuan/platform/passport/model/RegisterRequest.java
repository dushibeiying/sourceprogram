package xyz.ftuan.platform.passport.model;

import xyz.ftuan.platform.passport.util.MD5Utils;

/**
 * Created by LUOXC on 2017/2/26.
 */
public class RegisterRequest {

    private String mobile;
    private String password;

    public String getMD5Password() {
        return MD5Utils.encode(password);
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
