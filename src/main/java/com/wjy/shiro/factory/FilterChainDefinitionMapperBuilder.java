package com.wjy.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapperBuilder {
	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("/login.jsp", "anon");
		map.put("/shiro/login", "anon");
		map.put("/shiro/logout", "logout");
		map.put("/admin.jsp","authc,roles[admin]");
	    map.put("/user.jsp", "authc,roles[user]");
	    map.put("/list.jsp", "user");
		map.put("/**","authc");
		return map;
	}
}
