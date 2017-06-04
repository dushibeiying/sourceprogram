package xyz.ftuan.platform.passport.domain;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    void batchDeleteEmps(int[] id);
    
    List<User> selectByIds(int[] id);
    
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectById(@Param("id") Long id);

    User selectByMobile(@Param("mobile") String mobile);
    
    List<User> selectBySurname(@Param("nickname") String surname);

    User selectByNickname(@Param("nickname") String nickname);
    
    List<User> selectAll();
}
