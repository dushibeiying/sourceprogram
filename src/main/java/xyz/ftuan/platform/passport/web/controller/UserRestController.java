package xyz.ftuan.platform.passport.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.ftuan.platform.passport.exception.ServiceException;
import xyz.ftuan.platform.passport.model.ApiResponse;
import xyz.ftuan.platform.passport.model.BookProfile;
import xyz.ftuan.platform.passport.model.ApiResponse.ApiResponseBuilder;
import xyz.ftuan.platform.passport.model.ChangePasswordRequest;
import xyz.ftuan.platform.passport.model.LoginRequest;
import xyz.ftuan.platform.passport.model.RegisterRequest;
import xyz.ftuan.platform.passport.model.Request;
import xyz.ftuan.platform.passport.model.UserProfile;
import xyz.ftuan.platform.passport.service.BookService;
import xyz.ftuan.platform.passport.service.UserService;

/**
 * Created by LUOXC on 2017/2/26.
 */
@RestController
@RequestMapping("/rest/users")
public class UserRestController {
    @Autowired
    private UserService userService;
    private BookService bookService;

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
    public ApiResponse login(@RequestBody LoginRequest request,  HttpSession session) {
     
        try{
            userService.login(request);
            session.setAttribute("mobile", request.getMobile());
            return ApiResponse.SUCCESS;
        }catch(ServiceException e){
            return new ApiResponseBuilder().code(e.getStatus()).message(e.getMessage()).build();
        }      
    }
    
    @RequestMapping("/profile")
    public ApiResponse profile(HttpSession session) {
     
        try{
            String mobile = (String) session.getAttribute("mobile");
            return new ApiResponseBuilder().data(mobile).build();
        }catch(ServiceException e){
            return new ApiResponseBuilder().code(e.getStatus()).message(e.getMessage()).build();
        }      
    }
    
    @RequestMapping("/changepassword")
    public ApiResponse changepassword(@RequestBody ChangePasswordRequest request) {    	
    	try{
	        userService.changePassword(request);
	        return ApiResponse.SUCCESS;
    	}catch(ServiceException e){
    		return new ApiResponseBuilder().code(e.getStatus()).message(e.getMessage()).build();
    	}	   
    }
    
    @RequestMapping("/surname")
    public void queryUserBySurname(@RequestParam("q") String request, HttpServletResponse response) throws IOException {    	
    	try{
    	    byte[] out = userService.queryUserBySurname(request);
    	    response.setContentType("application/vnd.ms-excel");  
            response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode("users.xls", "utf-8"));  
            OutputStream os = response.getOutputStream();  
            os.write(out);
            os.close();
    	}catch(ServiceException e){    		
    	}	   
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResponse findUserById(@PathVariable Long id) {
        UserProfile userProfile = userService.findUserById(id);
        return new ApiResponse.ApiResponseBuilder().data(userProfile).build();
    }
    
    @RequestMapping(value = "/managed", method = RequestMethod.GET)
    public ApiResponse findAllUser(){
        return new ApiResponse.ApiResponseBuilder().data(userService.findAllUser()).build();
    }

    @RequestMapping(value = "/bookmanaged", method = RequestMethod.GET)
    public ApiResponse findAllBook(){
        return new ApiResponse.ApiResponseBuilder().data(bookService.findAllBook()).build();
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public ApiResponse deleteById(@RequestBody int[] ids) {
        try{
            userService.deleteById(ids);
            return ApiResponse.SUCCESS;
        }catch(ServiceException e){
            return new ApiResponseBuilder().code(e.getStatus()).message(e.getMessage()).build();
        }
    }
    
    @RequestMapping("/exportById")
    public void exportById(@RequestParam("ids") int[] ids, HttpServletResponse response )throws IOException {
        try{
            byte[] out = userService.exportById(ids);
            response.setContentType("application/vnd.ms-excel");  
            response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode("users.xls", "utf-8"));  
            OutputStream os = response.getOutputStream();  
            os.write(out);
            os.close();
        }catch(ServiceException e){         
        }      
    }
    
    @RequestMapping("/addbook")
    public ApiResponse addbook(@RequestBody Request request) {
        try{
            bookService.addBook(request);
            return ApiResponse.SUCCESS;
        }catch(ServiceException e){
            return new ApiResponseBuilder().code(e.getStatus()).message(e.getMessage()).build();
        }
    }
    
    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public ApiResponse findBookById(@PathVariable Long id) {
        BookProfile bookProfile = bookService.findBookById(id);
        return new ApiResponse.ApiResponseBuilder().data(bookProfile).build();
    }
    
   

    @RequestMapping(value = "/deleteById2", method = RequestMethod.POST)
    public ApiResponse deleteById2(@RequestBody int[] ids) {
        try{
            bookService.deleteById(ids);
            return ApiResponse.SUCCESS;
        }catch(ServiceException e){
            return new ApiResponseBuilder().code(e.getStatus()).message(e.getMessage()).build();
        }
    }
    
    @RequestMapping("/exportById2")
    public void exportById2(@RequestParam("ids") int[] ids, HttpServletResponse response )throws IOException {
        try{
            byte[] out = bookService.exportById(ids);
            response.setContentType("application/vnd.ms-excel");  
            response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode("users.xls", "utf-8"));  
            OutputStream os = response.getOutputStream();  
            os.write(out);
            os.close();
        }catch(ServiceException e){         
        }      
    }
}
