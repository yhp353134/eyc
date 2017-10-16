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
 <meta http-equiv="content-type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<%@ include file="../admin/top.jsp"%>  
<title></title>
<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="static/css/bootstrap.min.css" rel="stylesheet" />
<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link rel="stylesheet" href="static/css/font-awesome.min.css" />
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
<style type="text/css">
input {font-size: 12px; }
select {font-size: 12px;}
</style>
<script type="text/javascript">
	top.hangge();
</script>
</head>
<body>
<div style="width: 100%;position: relative;" id="zhongxin">
	<div style="width: 96%;position: relative;left: 2%;">
	<form action="user/saveButton.do" name="form1"  id="form1"  method="post">
	<table style="width: 100%;font-size: 12px;margin-top: 10px;" class="table table-striped table-bordered table-hover">
	<thead>
			<th colspan="4">按钮</th>
		</thead>
		<tbody>
		<tr>
			<td style="text-align: right;" width="25%" >按钮中文名：</td>
			<td align="left">
				<input type="text" id="buttonName"  name="buttonName">
				<strong class='high' style='color:red;'> *</strong>
			</td>
		</tr>
		<tr>
		<td style="text-align: right;" >按钮CODE：</td>
			<td align="left">
				<input type="text" id="buttonCode"  name="buttonCode">
				<strong class='high' style='color:red;'> *</strong>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;">按钮值：</td>
			<td align="left">
				<input type="text" id="buttonValue"  name="buttonValue">
				<strong class='high' style='color:red;'> *必须输入数字</strong>
			</td>
		</tr>
		<tr align="center">
			<td align="center" style="text-align: center;" colspan="2">
				<a class="btn btn-mini btn-primary" onclick="saveUser();">保  存</a>&nbsp;&nbsp;
				<a class="btn btn-mini btn-danger" onclick="cancel()">取  消</a>
			</td>
		</tr>
	</tbody>
	</table>
	</form>
	<!-- 背景结束 -->
	</div>
</div>

<div id="zhongxin2" class="center" style="display:none"><img src="static/images/jzx.gif" style="width: 50px;" /><br/><h4 class="lighter block green"></h4></div>
<script src="static/1.9.1/jquery.min.js"></script>
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script src="static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/js/bootbox.min.js"></script>
<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
<script type="text/javascript">

/**
 *检查字符串 
 */
function checkStr(objStr) {
	if (objStr == null || objStr == '' || objStr == 'undefind' || objStr.length == 0) {
		return true;
	} else {
		return false;
	}
}

/***
 * 点击保存按钮
 */
function saveUser(){
	$("#form1").submit();
	$("#zhongxin").css("display","none");
	$("#zhongxin2").show();
}

/**
 *关闭窗体 
 */
 function cancel(){
 	top.Dialog.close();
 }
</script>
</body>
</html>
