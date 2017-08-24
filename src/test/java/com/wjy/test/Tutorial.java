package com.wjy.test;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class Tutorial {
	private static Logger LOG = Logger.getLogger(Tutorial.class);

	public static void main(String[] args) {
		LOG.info("My first Apache Shiro Application");
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		session.setAttribute("someKey", "aValue");
		//是否已经验证过了,即是否已经登录
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
			token.setRememberMe(true);
			try {
				currentUser.login(token);
			} catch (UnknownAccountException ue) {
				LOG.info("用户名不存在");
			}catch (IncorrectCredentialsException ice){
				LOG.info("密码错误");
			}catch (LockedAccountException lce){
				LOG.info("帐户被锁");
			}catch (AuthenticationException ae){
				LOG.info("验证出错");
			}
		}
		LOG.info("User [" + currentUser.getPrincipals() + "] logged in successfully");
		//是否拥有某个角色
		if(currentUser.hasRole("schwartz")){
			LOG.info("May the schwartz be with you!");
		}else{
			LOG.info("Hello, mere mortal");
		}
		
		if(currentUser.isPermitted("lightsaber:add")){
			LOG.info("You may use a lightsaber ring. Use it wisely.");
		}else{
			LOG.info("Sorry, lightsaber rings ary for schwartz masters only");
		}
		//判断用户是否有能力访问某一类型的特定实例的能力
		if(currentUser.isPermitted("user:delete:zhangsan")){
			LOG.info("You are permitted to 'delete' the user with license plate (id) 'zhangsan");
		}else{
			LOG.info("Sorry, you aren't allowed to delete the 'zhangsan' winnebago!");
		}
		System.exit(0);
	}
}
