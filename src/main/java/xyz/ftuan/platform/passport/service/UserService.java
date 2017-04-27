package xyz.ftuan.platform.passport.service;

import xyz.ftuan.platform.passport.model.ChangePasswordRequest;
import xyz.ftuan.platform.passport.model.LoginRequest;
import xyz.ftuan.platform.passport.model.RegisterRequest;
import xyz.ftuan.platform.passport.model.UserProfile;

/**
 * Created by LUOXC on 2017/2/26.
 */
public interface UserService {
    void register(RegisterRequest request);

    /**
     * 登录
     * @param request
     */
    void login(LoginRequest request);
    
    /**
     * 修改密码
     * @param request
     */
    void changePassword(ChangePasswordRequest request);
        
    byte[] queryUserBySurname(String request);
    
    UserProfile findUserById(Long id);
}
