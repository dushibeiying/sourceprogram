package xyz.ftuan.platform.passport.model;

import xyz.ftuan.platform.passport.domain.Book;

import java.util.Date;
import java.util.Objects;

import org.apache.http.client.utils.DateUtils;

public class BookProfile {

    private Long id;
    private String name;
    private String isbn;   
    private String author;
    private String press;
    private String type;    
    private Integer updateTime;

    public static BookProfile newBookProfile(Book book) {
        if (Objects.isNull(book)) {
            return null;
        }
        BookProfile bookProfile = new BookProfile();
        bookProfile.id = book.getId();
        bookProfile.name = book.getName();
        bookProfile.isbn = book.getIsbn();
        bookProfile.author = book.getAuthor();      
        bookProfile.press = book.getPress();
        bookProfile.type = book.getType();
        bookProfile.updateTime = book.getUpdateTime();
        return bookProfile;
    }

    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getPress() {
        return press;
    }

    public String getType() {
        return type;
    }

    public String getUpdateTime() {
        return DateUtils.formatDate(new Date(updateTime.longValue() * 1000), "yyyy-MM-dd");
    }


}
