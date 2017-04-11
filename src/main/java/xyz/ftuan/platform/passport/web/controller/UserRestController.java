package xyz.ftuan.platform.passport.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xyz.ftuan.platform.passport.exception.ServiceException;
import xyz.ftuan.platform.passport.model.ApiResponse;
import xyz.ftuan.platform.passport.model.ApiResponse.ApiResponseBuilder;
import xyz.ftuan.platform.passport.model.ChangePasswordRepuest;
import xyz.ftuan.platform.passport.model.LoginRequest;
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
    	try{
	        userService.register(request);
	        return ApiResponse.SUCCESS;
    	}catch(ServiceException e){
    		return new ApiResponseBuilder().code(e.getStatus()).message(e.getMessage()).build();
    	}
    }

    @RequestMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest request) {    	
    	try{
	        userService.login(request);
	        return ApiResponse.SUCCESS;
    	}catch(ServiceException e){
    		return new ApiResponseBuilder().code(e.getStatus()).message(e.getMessage()).build();
    	}	   
    }
    
    @RequestMapping("/changepassword")
    public ApiResponse changepassword(@RequestBody ChangePasswordRepuest request) {    	
    	try{
	        userService.changePassword(request);
	        return ApiResponse.SUCCESS;
    	}catch(ServiceException e){
    		return new ApiResponseBuilder().code(e.getStatus()).message(e.getMessage()).build();
    	}	   
    }
    
    @RequestMapping("/surname")
    public ApiResponse queryUserBySurname(@RequestBody String request) {    	
    	try{
	        userService.queryUserBySurname(request);
	        return ApiResponse.SUCCESS;
    	}catch(ServiceException e){
    		return new ApiResponseBuilder().code(e.getStatus()).message(e.getMessage()).build();
    	}	   
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResponse findUserById(@PathVariable Long id) {
        UserProfile userProfile = userService.findUserById(id);
        return new ApiResponse.ApiResponseBuilder().data(userProfile).build();
    }

}
