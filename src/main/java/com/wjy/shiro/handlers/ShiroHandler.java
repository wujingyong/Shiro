package com.wjy.shiro.handlers;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wjy.shiro.services.ShiroService;

@Controller(value="shiroHandler")
@RequestMapping("/shiro")
public class ShiroHandler {
	@Autowired
	private ShiroService shiroService;
	private static Logger LOG = LoggerFactory.getLogger(ShiroHandler.class);
	
	@RequestMapping("/login")
	public String login(String username, String password){
		Subject currentUser = SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()){
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(true);
			try{
				currentUser.login(token);
			}catch(AuthenticationException ae){
				LOG.info("登录失败",ae);
			}
		}
		return "redirect:/list.jsp";
	}
	
	@RequestMapping("/testShiroAnnotation")
	public String testShiroAnnotation(){
		shiroService.testShiroAnnotation();
		return "redirect:/list.jsp";
	}
}
