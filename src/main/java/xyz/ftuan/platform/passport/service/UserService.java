package xyz.ftuan.platform.passport.service;

import xyz.ftuan.platform.passport.model.ChangePasswordRepuest;
import xyz.ftuan.platform.passport.model.LoginRequest;
import xyz.ftuan.platform.passport.model.RegisterRequest;
import xyz.ftuan.platform.passport.model.UserProfile;

/**
 * Created by LUOXC on 2017/2/26.
 */
public interface UserService {
    void register(RegisterRequest request);

    void login(LoginRequest request);
    
    void changePassword(ChangePasswordRepuest request);
    
    UserProfile findUserById(Long id);
}
