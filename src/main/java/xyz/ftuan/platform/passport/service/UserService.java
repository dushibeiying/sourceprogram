package xyz.ftuan.platform.passport.service;

import xyz.ftuan.platform.passport.model.RegisterRequest;
import xyz.ftuan.platform.passport.model.UserProfile;

/**
 * Created by LUOXC on 2017/2/26.
 */
public interface UserService {
    void register(RegisterRequest request);

    void login(RegisterRequest request);
    
    UserProfile findUserById(Long id);
}
