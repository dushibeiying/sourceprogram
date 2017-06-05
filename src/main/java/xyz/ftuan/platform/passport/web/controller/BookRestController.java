package xyz.ftuan.platform.passport.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

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
import xyz.ftuan.platform.passport.model.Request;
import xyz.ftuan.platform.passport.service.BookService;

@RestController
@RequestMapping("/rest/books")
public class BookRestController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/managed", method = RequestMethod.GET)
    public ApiResponse findAllBook(){
        return new ApiResponse.ApiResponseBuilder().data(bookService.findAllBook()).build();
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
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResponse findBookById(@PathVariable Long id) {
        BookProfile bookProfile = bookService.findBookById(id);
        return new ApiResponse.ApiResponseBuilder().data(bookProfile).build();
    }   

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public ApiResponse deleteById(@RequestBody int[] ids) {
        try{
            bookService.deleteById(ids);
            return ApiResponse.SUCCESS;
        }catch(ServiceException e){
            return new ApiResponseBuilder().code(e.getStatus()).message(e.getMessage()).build();
        }
    }
    
    @RequestMapping("/exportById")
    public void exportById(@RequestParam("ids") int[] ids, HttpServletResponse response )throws IOException {
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
