package xyz.ftuan.platform.passport.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.http.client.utils.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import xyz.ftuan.platform.passport.domain.Book;
import xyz.ftuan.platform.passport.domain.BookMapper;
import xyz.ftuan.platform.passport.model.BookProfile;
import xyz.ftuan.platform.passport.model.Request;
import xyz.ftuan.platform.passport.util.TimeUtils;

public class BookServiceImp implements BookService {
    
    private BookMapper bookMapper;
    
    public void addBook(Request request) {
        Book book = new Book();
        book.setName(request.getName());
        book.setIsbn(request.getIsbn());
        book.setPress(request.getPress());
        book.setType(request.getType());
        book.setUpdateTime(TimeUtils.currentTimeSeconds());
        bookMapper.insert(book);
        
    }

    public BookProfile findBookById(Long id) {       
        return bookMapper.selectById(id);
    }

    public void deleteById(int[] ids) {
        bookMapper.batchDeleteEmps(ids);      
    }

    public List<BookProfile> findAllBook() {
        List<Book> books = bookMapper.selectAll();
        return books.stream().map(BookProfile::newBookProfile).collect(Collectors.toList());
    }

    public byte[] exportById(int[] ids) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        int iRow = 0;
        writeRow(sheet, iRow++, "入库时间","书名","作者");
        
            List<Book> books = bookMapper.selectByIds(ids);
            for(Book book : books) {
            if(Objects.isNull(book)) {
                continue;
            }
            writeRow(sheet, iRow++, DateUtils.formatDate(new Date(book.getUpdateTime().longValue() * 1000),"yyyy/M/d"),book.getName(),book.getAuthor());
            }
        ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
        try {
            workbook.write(fileOut);  
            return fileOut.toByteArray();
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
            return new byte[0];
        }  finally{
            try {
                workbook.close();
                fileOut.close();
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
    }

    private void writeRow(HSSFSheet sheet, int iRow, String regTime,String name,String author) {
        HSSFRow row= sheet.createRow(iRow);  
        HSSFCell cell = row.createCell(0);  
        cell.setCellValue(regTime);
        cell = row.createCell(1);  
        cell.setCellValue(name);
        cell = row.createCell(2);  
        cell.setCellValue(author);
    }
}
