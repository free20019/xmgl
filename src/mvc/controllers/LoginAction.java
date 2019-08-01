package mvc.controllers;

import javax.servlet.http.HttpServletRequest;


import mvc.service.LoginServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping(value = "/login")
public class LoginAction {
	private LoginServer loginServer;

	public LoginServer getLoginServer() {
		return loginServer;
	}
	@Autowired
	public void setLoginServer(LoginServer loginServer) {
		this.loginServer = loginServer;
	}

	@RequestMapping("/login")
	@ResponseBody
	public String query_pic(HttpServletRequest request,@RequestParam("username") String username,@RequestParam("password") String password) {
		String msg = loginServer.login(username,password,request);	
	    return msg;
	}

	@RequestMapping(value = "/xt2")
	@ResponseBody
	public String xt2(HttpServletRequest request) {
		String msg = "{}";
		Object userObject = request.getSession().getAttribute("username");
		System.out.println(userObject);
		if(null == userObject){
			msg = "{\"code\":510}";
		}else{
			String user = (String)userObject;
			if(user.isEmpty()){
				msg = "{\"code\":510}";
			}else{
				msg = "{\"code\":520}";
			}
		}
		//logger.info(msg);
		return msg;
	}
}
