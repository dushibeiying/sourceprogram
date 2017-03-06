package xyz.ftuan.platform.passport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ftuan.platform.passport.domain.User;
import xyz.ftuan.platform.passport.domain.UserMapper;
import xyz.ftuan.platform.passport.model.RegisterRequest;
import xyz.ftuan.platform.passport.model.UserProfile;
import xyz.ftuan.platform.passport.util.TimeUtils;

/**
 * Created by LUOXC on 2017/2/26.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(RegisterRequest request) {
        User user = new User();
        user.setMobile(request.getMobile());
        user.setPassword(request.getMD5Password());
        user.setCreateTime(TimeUtils.currentTimeSeconds());
        userMapper.insert(user);
    }

    @Override
    public UserProfile findUserById(Long id) {
        User user = userMapper.selectById(id);
        return UserProfile.newUserProfile(user);
    }
}
