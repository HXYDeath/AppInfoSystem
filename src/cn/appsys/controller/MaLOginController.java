package cn.appsys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.appsys.pojo.BackendUser;
import cn.appsys.service.backend.BackendUserService;
import cn.appsys.tools.Constants;

@Controller
@RequestMapping("manager")
public class MaLOginController {
	
	@Resource
	private BackendUserService backendUserService;
	
	@RequestMapping("login")
	private String Login() {
		return "backendlogin";
	}
	
	@RequestMapping(value="dologin",method=RequestMethod.POST)
	private String dologin(@RequestParam String userCode,@RequestParam String userPassword,HttpServletRequest request,HttpSession session) {
		BackendUser user = null;
		try {
			user = backendUserService.getLoginUser(userCode, userPassword);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(user != null) {
			session.setAttribute(Constants.USER_SESSION, user);
			return "redirect:/manager/backend/main";
		}else {
			return "backendlogin";
		}
	}
	
	@RequestMapping("/backend/main")
	private String main(HttpSession session) {
		if(session.getAttribute(Constants.USER_SESSION) == null) {
			return "redirect:/manager/login";
		}
		return "/backend/main";
	}
	
	@RequestMapping(value="logout")
	private String outLogin(HttpSession session) {
		session.removeAttribute(Constants.USER_SESSION);
		return "backendlogin";
	}
}
