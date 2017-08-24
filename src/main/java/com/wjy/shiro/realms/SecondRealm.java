package com.wjy.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

public class SecondRealm extends AuthenticatingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		System.out.println("Send Realm");
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		Object credentials = null;
		if(usernamePasswordToken.getUsername().equals("aaa")){
			credentials = "c9f6004627008e5a0bf846a08c2f1dbd7352fba9";
		}else if(usernamePasswordToken.getUsername().equals("admin")){
			credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";
		}else{
			throw new AuthenticationException("密码错误或用户不存在");
		}
		Object principal = usernamePasswordToken.getPrincipal();
		String realmName = getName();
		ByteSource credentialsSalt = ByteSource.Util.bytes(principal);
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("secondRealm", credentials, credentialsSalt, realmName);
		
		return simpleAuthenticationInfo;
	}
	
	public static void main(String[] args) {
		SimpleHash simpleHash = new SimpleHash("SHA1", "123456", "aaa", 1024);
		System.out.println(simpleHash);
	}

}
