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
</head>
<body>
<div style="width: 100%;position: relative;" id="zhongxin">
	<div style="width: 98%;position: relative;left: 1%;">
	<form action="user/uddatePassword.do" name="formPost"  id="formPost"  method="post">
	<table style="width: 100%;font-size: 14px;margin-top: 10px;" id="bigTable" class="table table-striped table-bordered table-hover">
		<tbody>
		<tr>
			<td width="25%" style="text-align: right;">原始密码：</td>
			<td align="left">
				<input type="password" id="oldPassword"  name="oldPassword"/>
				<strong class='high' style='color:red;'> *</strong>
			</td>
		</tr>
		<tr >
			<td style="text-align: right;">新密码：</td>
			<td align="left">
				<input type="password" id="password"  name="password"/>
				<strong class='high' style='color:red;'> *</strong>
			</td> 
		</tr>
		<tr>
			<td style="text-align: right;">确认新密码：</td>
			<td align="left">
				<input type="password" id="newPassword"  name="newPassword"/>
				<strong class='high' style='color:red;'> *</strong>
			 </td>
		</tr>
		<tr>
		<tr height="40px;">
			<td align="center"  style="text-align: center;" colspan="2">
				<a class="btn btn-mini btn-primary" onclick="updatePassword()">修  改</a>&nbsp;&nbsp;&nbsp;&nbsp;
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
top.hangge();
function yu() {
	alert(11);
}

//修改密码
function updatePassword() {
	var oldPassword = $("#oldPassword").val();
	var password = $("#password").val();
	var newPassword = $("#newPassword").val();
	 if (check(oldPassword)) {
		//先去校验原密码是否正确
		 $.ajax({
				type: "POST",
				url: '<%=path%>/user/checkOldPass.do?tm='+new Date().getTime(),
		    	data: {
		    		"oldPw":oldPassword
		    	},
				dataType:'json',
				cache: false,
				success: function(data) {
					var isc = data.returnCode;
					var msg = data.result;
					if (isc == '1') {
						if (!check(password)) {
							$("#password").tips({
								side:3,
					            msg:"请输入密码",
					            bg:'#AE81FF',
					            time:2
					        });
							$("#password").focus();
							return false;
						}
						if (!check(newPassword)) {
							$("#newPassword").tips({
								side:3,
					            msg:"请输入新密码",
					            bg:'#AE81FF',
					            time:2
					        });
							$("#newPassword").focus();
							return false;
						}
						if (newPassword != password) {
							$("#newPassword").tips({
								side:3,
					            msg:"两次密码不一样",
					            bg:'#AE81FF',
					            time:2
					        });
							$("#newPassword").focus();
							return false;
						}
						$.ajax({
							type: "POST",
							url: '<%=path%>/user/updatePassword.do?tm='+new Date().getTime(),
					    	data: {
								"password":password,
								"newPassword":newPassword
							},
							dataType:'json',
							cache: false,
							success: function(re){
								var code = re.returnCode;
								var remsg = re.result;
								bootbox.alert(remsg,function() {
									top.Dialog.close();
								});
							}
						});
					} else {
						//失败
						$("#oldPassword").tips({
							side:3,
				            msg:msg,
				            bg:'#AE81FF',
				            time:2
				        });
						$("#oldPassword").focus();
						return false;
					}
				}
			});
	} else {
		$("#oldPassword").tips({
			side:3,
            msg:'请输入原密码',
            bg:'#AE81FF',
            time:2
        });
		$("#oldPassword").focus();
		return false;
	}
}

//检查非空
function check(obj) {
	if (obj == null || obj == '' || obj == 'undefind' || obj.length==0) {
		return false;
	} else {
		return true;
	}
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
