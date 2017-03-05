package xyz.ftuan.platform.passport.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.ftuan.platform.passport.domain.User;
import xyz.ftuan.platform.passport.model.RegisterRequest;
import xyz.ftuan.platform.passport.model.UserProfile;
import xyz.ftuan.platform.passport.service.UserService;

/**
 * Created by LUOXC on 2017/2/26.
 */
@RestController
@RequestMapping("/rest/users")
public class UserRestController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        userService.register(request);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserProfile findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

}
