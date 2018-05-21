package cn.appsys.service.developer;

import cn.appsys.pojo.DevUser;

public interface DevUserService {

	/**
	 * 登录
	 * @param devCode
	 * @return
	 * @throws Exception
	 */
	public DevUser login(String devCode,String dePwd)throws Exception;
}
