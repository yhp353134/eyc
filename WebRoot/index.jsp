<%
response.setHeader("Pragma","No-cache");    
response.setHeader("Cache-Control","no-cache");    
response.setDateHeader("Expires", -10);   
%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String acgj = request.getParameter("acgj");
if(acgj==null){
	acgj = "";
}
//System.out.println("----------index.jsp-------acgj="+acgj);
%>
<jsp:forward page="/login_toLogin?acgj=1" />
