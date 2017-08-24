<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h4>List Page</h4>
    Welcome： <shiro:principal></shiro:principal>
    <br><br>
    <shiro:hasRole name="admin">
	    <a href="${pageContext.request.contextPath }/admin.jsp">Admin Page</a>
	    <br><br>
    </shiro:hasRole>
    <shiro:hasRole name="user">
	    <a href="${pageContext.request.contextPath }/user.jsp">User Page</a>
	    <br><br>
    </shiro:hasRole>
    <a href="${pageContext.request.contextPath }/shiro/testShiroAnnotation" >Test Shiro Annotation</a>
    <br><br>
    <a href="${pageContext.request.contextPath }/shiro/logout">Login Out</a>
    <br><br>
</body>
</html>