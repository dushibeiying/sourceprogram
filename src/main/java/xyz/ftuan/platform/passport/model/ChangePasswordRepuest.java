package xyz.ftuan.platform.passport.model;

import xyz.ftuan.platform.passport.util.MD5Utils;

/**
 * Created by LUOXC on 2017/2/26.
 */
public class ChangePasswordRepuest {

    private String mobile;
    private String password;
    private String newPassword;
    

    public String getMD5Password() {
        return MD5Utils.encode(password);
    }
    
    public String getMD5NewPassword() {
        return MD5Utils.encode(newPassword);
    }


	/**
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}


	/**
	 * @param mobile 要设置的 mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password 要设置的 password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword 要设置的 newPassword
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
  
}
