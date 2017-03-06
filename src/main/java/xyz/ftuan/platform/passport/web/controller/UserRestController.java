package xyz.ftuan.platform.passport.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.ftuan.platform.passport.model.ApiResponse;
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
    public ApiResponse register(@RequestBody RegisterRequest request) {
        userService.register(request);
        return ApiResponse.SUCCESS;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResponse findUserById(@PathVariable Long id) {
        UserProfile userProfile = userService.findUserById(id);
        return new ApiResponse.ApiResponseBuilder().data(userProfile).build();
    }

}
