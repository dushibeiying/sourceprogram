package xyz.ftuan.platform.passport.service;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.ftuan.platform.passport.domain.User;
import xyz.ftuan.platform.passport.domain.UserMapper;
import xyz.ftuan.platform.passport.exception.ServiceException;
import xyz.ftuan.platform.passport.model.ChangePasswordRepuest;
import xyz.ftuan.platform.passport.model.LoginRequest;
import xyz.ftuan.platform.passport.model.RegisterRequest;
import xyz.ftuan.platform.passport.model.UserProfile;
import xyz.ftuan.platform.passport.util.TimeUtils;

/**
 * Created by LUOXC on 2017/2/26.
 */
@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserMapper userMapper;

	@Override
	public void register(RegisterRequest request) {
		validateMobile(request);
		User user = new User();
		user.setMobile(request.getMobile());
		user.setPassword(request.getMD5Password());
		user.setNickname(request.getNickname());
		user.setCreateTime(TimeUtils.currentTimeSeconds());
		userMapper.insert(user);
		logger.info( "register success, the mobile is {}.", request.getMobile());
	}

	private void validateMobile(RegisterRequest request) {
		if (StringUtils.isEmpty(request.getMobile())) {
			logger.info( "register fail, mobile is empty.");
			throw new ServiceException(10000, "mobile is empty.");
		}
		User user = userMapper.selectByMobile(request.getMobile());
		if (Objects.nonNull(user)) {
			logger.info( "register fail, the account has exist of the mobile {}.", request.getMobile());
			throw new ServiceException(10001, "account is exist.");
		}
	}

	public void login(LoginRequest request) {
		if (StringUtils.isEmpty(request.getMobile())) {
			throw new ServiceException(10001, "mobile is empty.");
		}
		User user = userMapper.selectByMobile(request.getMobile());
		if (Objects.isNull(user)) {
			logger.info( "login fail, the account is not exist of the mobile {}.", request.getMobile());
			throw new ServiceException(10000, "account or password error");
		}
		if (!Objects.equals(user.getPassword(), request.getMD5Password())) {
			logger.info( "login fail, the password is wrong of the user {}.", request.getMobile());
			throw new ServiceException(10000, "account or password error");
		}
		logger.info("login success of the user {}.", request.getMobile());
	}

	public void changePassword(ChangePasswordRepuest request){
		// 验证请求参数
		validateRequest(request);
		// 获取要修改的用户信息
		User user = userMapper.selectByMobile(request.getMobile());
		// 验证用户密码是否可以被修改
		if (Objects.isNull(user)) {
			logger.info( "change password fail, the account is not exist of the mobile {}.", request.getMobile());
			throw new ServiceException(10000, "account or password error");
		}
		if (!Objects.equals(user.getPassword(), request.getMD5Password())) {
			logger.info( "change password fail, the password is wrong of the user {}.", request.getMobile());
			throw new ServiceException(10000, "account or password error");
		}
		// 更新密码
		user.setPassword(request.getMD5NewPassword());
		user.setUpdateTime(TimeUtils.currentTimeSeconds());
		userMapper.updateByPrimaryKey(user);		
		logger.info("change password success of the user {}.", request.getMobile());
	}

	private void validateRequest(ChangePasswordRepuest request) {
		if (StringUtils.isEmpty(request.getMobile())) {
			throw new ServiceException(10001, "mobile is empty.");
		}
		if(StringUtils.isEmpty(request.getPassword())){
			throw new ServiceException(10002, "please input old password.");
		}
		if(StringUtils.isEmpty(request.getNewPassword())){
			throw new ServiceException(10002, "please input the new password.");
		}
	}
	@Override
	public UserProfile findUserById(Long id) {
		User user = userMapper.selectById(id);
		return UserProfile.newUserProfile(user);
	}

	@Override
	public byte[] queryUserBySurname(String request) {
		List<User>  users = userMapper.selectBySurname(request);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
	    int iRow = 0;
	    writeRow(sheet, iRow++, "注册时间","昵称","手机号码");
        for(User user : users) {
            writeRow(sheet, iRow++, DateUtils.formatDate(new Date(user.getCreateTime().longValue() * 1000),"yyyy/M/d"),user.getNickname(),user.getMobile());
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

    private void writeRow(HSSFSheet sheet, int iRow, String regTime,String name,String mobile) {
        HSSFRow row= sheet.createRow(iRow);  
        HSSFCell cell = row.createCell(0);  
        cell.setCellValue(regTime);
        cell = row.createCell(1);  
        cell.setCellValue(name);
        cell = row.createCell(2);  
        cell.setCellValue(mobile);
    }
}
