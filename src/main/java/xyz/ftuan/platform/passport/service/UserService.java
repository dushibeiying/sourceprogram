package xyz.ftuan.platform.passport.service;

import xyz.ftuan.platform.passport.domain.User;
import xyz.ftuan.platform.passport.model.RegisterRequest;

/**
 * Created by LUOXC on 2017/2/26.
 */
public interface UserService {
    void register(RegisterRequest request);

    User findUserById(Long id);
}
