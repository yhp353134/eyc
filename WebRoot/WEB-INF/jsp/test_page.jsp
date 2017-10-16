<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">	
<%@ include file="system/admin/top.jsp"%> 
<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "<%=path%>/plugins/eycuditor/";</script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/plugins/eycuditor/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/plugins/eycuditor/ueditor/ueditor.all.js"></script>
</head>
<body>

<%-- <div style="width: 90%;margin-top: 10px;">
	<textarea  name="context"  id="textarea1"  style="display: none;"></textarea>
	<iframe id="valFrame1" scrolling="no" frameborder="0" src="<%=path%>/plugins/eycuditor/index.html" style="margin:0 auto;width:100%; overflow: auto;height: 605px;"></iframe>
</div> --%>
<div>
	<input type="button" onclick="getContent()" value="获取内容"/>
</div>

<form action="testLoad/fileUpload.do" name="form1" id="form1"  method="post"  enctype="multipart/form-data">
	<input type="file"  name="memoFile"/>
	<input type="submit" value="提交">
</form>

<script type="text/javascript">


function getContent() {
	/* var window = document.getElementById("valFrame1").contentWindow;
	var content = window.getContent();
	alert(content); */
	//调用
	
	$.ajax({
		  url: "https://www.baidu.com",
		  type: 'GET',
		  cache: false,
		  success:function(e){
			  alert(e.status);
		  },
		  complete: function(response) {
			  alert(response.status);
			   if(response.status == 200) {
			    	alert('有效');
			   } else {
			    	alert('无效');
			   }
		  }
		 }); 
		
}

</script>
</body>
</html>