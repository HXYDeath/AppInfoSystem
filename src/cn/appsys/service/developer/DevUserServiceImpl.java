package cn.appsys.service.developer;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.appsys.dao.devuser.DevUserMapper;
import cn.appsys.pojo.DevUser;

@Service("devUserService")
public class DevUserServiceImpl implements DevUserService{

	@Resource
	private DevUserMapper devUserMapper;
	
	@Override
	public DevUser login(String devCode,String dePwd) throws Exception {
		DevUser dUser = devUserMapper.login(devCode);
		if(dUser!=null) {
			if(!dUser.getDevPassword().equals(dePwd)) {
				dUser = null;
			}
		}
		return dUser;
	}

}
