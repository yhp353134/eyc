<%@page import="java.util.Enumeration"%>
<%@page import="java.util.jar.Attributes.Name"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	//获取项目路径
	String rootPath = application.getRealPath( "/" );
	Enumeration<String> nes =  request.getParameterNames();
		
// 	while(nes.hasMoreElements()){
// 		System.out.println(nes.nextElement().toString());
// 	}
	//System.out.println(rootPath);
	String result = new ActionEnter( request, rootPath ).exec();
	System.out.println(result);
	out.write(result.replace(rootPath.replace("\\", "/"), ""));
	
%>