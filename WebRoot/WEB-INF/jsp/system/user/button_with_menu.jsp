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
<!-- jsp文件头和头部 -->
<%@ include file="../admin/top.jsp"%>  
<style type="text/css">
select {width: 130px;}
</style>
</head>
<body>
	<div class="center"  style="margin-top: 20px;">
			<strong class='high' style='color:red;'> 此设置必须在建角色之前设置，否则会出现按钮权限控制不到</strong>
	</div>
	<form action="user/saveButtonWithMenu.do" name="form1"  id="form1"  method="post">
		<div style="width: 100%;margin-top: 10px;margin-bottom: 20px;" class="center">
			&nbsp;&nbsp;&nbsp;&nbsp;请选择菜单：
		 	<select name="menuId"  id="menuId"  style="width: 160px;margin-top: 10px;color: gray;" class="chzn-select" >
				<c:forEach items="${menuList }" var="item">
					<option value="${item.MENU_ID }">${item.MENU_NAME }</option>
				</c:forEach>
		  	</select>
		 </div>
		<div class="center">
			<c:forEach items="${buttonList }" var="items">
				<label style="display: inline;">
					<input type="checkbox"  class="buttonId" name="buttonId" value="${items.BUT_ID }" >
					<span class="lbl">${items.BUT_NAME }</span>
				</label>
				&nbsp;&nbsp;
			</c:forEach>
		</div>
		<div style="text-align: center;margin-top: 20px;" colspan="2">
				<a class="btn btn-mini btn-primary" onclick="saveUser()">确  定</a>&nbsp;&nbsp;
		</div>
	</form>
<!-- 引入 -->
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script src="static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
<script type="text/javascript">
top.hangge();
$(function(){
	$(".chzn-select").chosen(); 
});

function saveUser() {
	var menuIds = $("#menuId").val();
	var buttonId = "";
	$("input[name='buttonId']").each(function(){
		if ($(this).is(':checked')) {
			buttonId = buttonId+$(this).val()+",";
		}
	});
	if (checkNullStr(buttonId)){
		alert("请选择按钮");
		return;
	}
	$.ajax({
		type: "post",
		url:globalContextPath+'/user/saveButtonWithMenu.do?tm='+new Date().getTime(),
		dataType:'text',
		data:{
			"menuId":menuIds,
			"buttonId":buttonId
		},
		cache:false,
		success:function(e){
			alert(e);
			$("input[name='buttonId']").each(function(){
				$(this).attr("checked",false);
			});
		},
		error:function(e) {
			alert("系统错误");
		}
	});
}
</script>
</body>
</html>

