package com.wjy.shiro.services;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;

public class ShiroService {
	
	@RequiresRoles(value={"admin"}, logical=Logical.OR)
	public void testShiroAnnotation(){
		System.out.println("test Shiro Annotation");
	} 
}
