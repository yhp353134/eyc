<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<%@ include file="top.jsp"%>
</head>
<body>
<div style="width: 100%;text-align: center;margin-top: 100px;">
	<img  src="static/images/eyunchedefeat.png">
</div>
</body>
<script type="text/javascript">
$(top.hangge());
//字符串转json
function strToJson(str){ 
	var json = (new Function("return " + str))(); 
	return json; 
} 
</script>
</html>
