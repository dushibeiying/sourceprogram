package xyz.ftuan.platform.passport.service;

import java.util.List;

import xyz.ftuan.platform.passport.model.BookProfile;
import xyz.ftuan.platform.passport.model.Request;

public interface BookService {
    
    void addBook(Request request);
    
    BookProfile findBookById(Long id);
    
    void deleteById(int[] ids);
    
    List<BookProfile> findAllBook();

    byte[] exportById(int[] ids);
}
