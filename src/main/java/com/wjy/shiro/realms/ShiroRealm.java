package com.wjy.shiro.realms;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class ShiroRealm extends AuthorizingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		System.out.println(usernamePasswordToken.getUsername().equals("aaa"));
		System.out.println(new String(usernamePasswordToken.getPassword()).equals("123456"));
		Object credentials = null;
		if(usernamePasswordToken.getUsername().equals("aaa")){
			credentials = "b8d63fc060e2b5651e8cb4e71ba61e6f";
		}else if(usernamePasswordToken.getUsername().equals("admin")){
			credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
		}else{
			throw new AuthenticationException("密码错误或用户不存在");
		}
		Object principal = usernamePasswordToken.getPrincipal();
		String realmName = getName();
		ByteSource credentialsSalt = ByteSource.Util.bytes(principal);
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		
		return simpleAuthenticationInfo;
	}
	
	//授权实现的方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("doGetAuthorizationInfo ...");
		Object principal = principals.getPrimaryPrincipal();
		
		Set<String> roles = new HashSet<>();
		roles.add("user");
		if("admin".equals(principal)){
			roles.add("admin");
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		return info;
	}
	
	public static void main(String[] args) {
		SimpleHash simpleHash = new SimpleHash("MD5", "123456", "admin", 1024);
		System.out.println(simpleHash);
	}


}
