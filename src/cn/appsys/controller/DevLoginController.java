package cn.appsys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.appsys.pojo.DevUser;
import cn.appsys.service.developer.DevUserService;
import cn.appsys.tools.Constants;

@Controller
@RequestMapping("/devlogin")
public class DevLoginController {
	
	@Resource
	private DevUserService devUserService;
	
	@RequestMapping("login")
	public String logins() {
		return "/devlogin";
	}
	
	/**
	 * 登录
	 * @param devCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/dologin")
	public String login(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		DevUser dUser = null;
		try {
			dUser = devUserService.login(request.getParameter("devCode"), request.getParameter("devPassword"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(dUser != null) {
			session.setAttribute(Constants.DEV_USER_SESSION, dUser); 
			return "redirect:/devlogin/main";
		}else {
			request.setAttribute("error", "用户名或者密码错误");
			return "/login";
		}
	}
	
	/**
	 * 页面跳转
	 * @param session
	 * @return
	 */ 
	@RequestMapping("/main")
	public String main(HttpSession session) {
		if(session.getAttribute(Constants.DEV_USER_SESSION) == null) {
			return "redirect:/login";
		}
		return "developer/main";
	}
}
