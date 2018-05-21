package cn.appsys.service.backend;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.appsys.dao.backenduser.BackendUserMapper;
import cn.appsys.pojo.BackendUser;

@Service("backendUserService")
public class BackendUserServiceImpl implements BackendUserService{

	@Resource
	BackendUserMapper backendUserMapper;
	
	@Override
	public BackendUser getLoginUser(String userCode,String pwd) throws Exception {
		BackendUser bUser = backendUserMapper.getLoginUser(userCode);
		if(bUser != null) {
			if(bUser.getUserPassword().equals(pwd)) {
				return bUser;
			}
		}
		return null;
	}

}
