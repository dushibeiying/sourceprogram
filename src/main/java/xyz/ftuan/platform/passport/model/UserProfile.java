package xyz.ftuan.platform.passport.model;

import xyz.ftuan.platform.passport.domain.User;

import java.util.Objects;

/**
 * Created by LUOXC on 2017/3/5.
 */
public class UserProfile {

    private Long id;
    private String mobile;
    private Integer createTime;

    public static UserProfile newUserProfile(User user) {
        if (Objects.isNull(user)) {
            return null;
        }
        UserProfile userProfile = new UserProfile();
        userProfile.id = user.getId();
        userProfile.mobile = user.getMobile();
        userProfile.createTime = user.getCreateTime();
        return userProfile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }


}
