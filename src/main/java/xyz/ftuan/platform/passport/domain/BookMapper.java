package xyz.ftuan.platform.passport.domain;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.ftuan.platform.passport.domain.Book;
import xyz.ftuan.platform.passport.model.BookProfile;

@Mapper
@Repository
public interface BookMapper {
   
    int deleteByPrimaryKey(Integer id);

    void batchDeleteEmps(int[] id);
    
    List<Book> selectByIds(int[] id);
    
    int insert(Book record);
    
    int insertSelective(Book record);
    
    int updateByPrimaryKeySelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Book record);

    BookProfile selectById(@Param("id") Long id);

    Book selectByName(@Param("name") String name);
    
    List<Book> selectAll();

}
