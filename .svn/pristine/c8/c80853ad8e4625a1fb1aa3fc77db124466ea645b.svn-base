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
	<form action="user/saveUserMsg.do" name="form1"  id="form1"  method="post">
	<table style="width: 100%;font-size: 12px;margin-top: 10px;" class="table table-striped table-bordered table-hover">
	<thead>
			<th colspan="4">用户详情</th>
		</thead>
		<tbody>
		<tr>
			<td style="text-align: right;" width="15%" >用户名：</td>
			<td align="left">
				<input type="text" id="loginName"  name="loginName">
				<strong class='high' style='color:red;'> *</strong>
			</td>
			<td style="text-align: right;" >用户姓名：</td>
			<td align="left">
				<input type="text" id="chinName"  name="chinName">
				<strong class='high' style='color:red;'> *</strong>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;" width="15%">密码：</td>
			<td align="left">
				<input type="password" id="passWord"  name="passWord">
				<strong class='high' style='color:red;'> *</strong>
			</td>
			<td style="text-align: right;" width="15%">确认密码：</td>
			<td align="left">
				<input type="password" id="passWordAgin"  name="passWordAgin">
				<strong class='high' style='color:red;'> *</strong>
			</td>
		</tr>
		<tr>
			
		</tr>
		<tr>
			
		</tr>
		<tr>
			<td style="text-align: right;">联系电话：</td>
			<td align="left">
				<input type="text" id="telPhone"  name="telPhone">
				<strong class='high' style='color:red;'> *</strong>
			</td>
			<td style="text-align: right;">身份证号：</td>
			<td align="left">
				<input type="text" id="carNum"  name="carNum">
			</td>
		</tr>
		<tr>
			<td style="text-align: right;">性别：</td>
			<td align="left" colspan="3">
				<label>
					<input type="radio"  value="10110001" name="gendar"  checked="checked"/>
					<span class="lbl">男</span>&nbsp;&nbsp;
					<input type="radio"  value="10110002" name="gendar" />
					<span class="lbl">女</span>
				</label>		
			</td>
		</tr>
		<c:if test="${userType eq '10131002' or userType eq '10131004' }">
			<tr>
				<td style="text-align: right;"><strong class='high' style='color:red;'>提示：</strong></td>
				<td align="left" colspan="3">
					<strong class='high' style='color:red;'>用户名请使用手机号码注册，否则APP端不能登录成功</strong>
				</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="4">
				<div style="margin-top: 20px;">
					<input type="button" value="选择职位" class="btn btn-purple btn-mini"  style="height: 30px;" onclick="choosePost()"/>
				</div>
				<div style="margin-top: 10px;">
					<table style="width: 100%;" id="postListTb" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th class="center">序号</th>
								<th class="center">职位名称</th>
								<th class="center">操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</td>
		</tr>
		<tr align="center">
			<td align="center" style="text-align: center;" colspan="4">
				<a class="btn btn-mini btn-primary" onclick="saveUser();">保  存</a>&nbsp;&nbsp;
				<a class="btn btn-mini btn-danger" onclick="cancel()">取  消</a>
			</td>
		</tr>
		</tbody>
	</table>
	<input type="hidden" id="postArr" name="postArr"/>
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
<link rel="stylesheet" href="plugins/zTree/3.5/zTreeStyle.css" />
<script type="text/javascript" src="plugins/zTree/3.5/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="plugins/zTree/3.5/jquery.ztree.excheck-3.5.min.js"></script>
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
	var loginName = $("#loginName").val();
	if(checkStr(loginName)) {
		$("#loginName").tips({
			side:3,
            msg:"请输入用户名",
            bg:'#AE81FF',
            time:2
        });
		$("#loginName").focus();
		return false;
	} else {
		if (/[\u4e00-\u9fa5]/g.test(loginName)) {
			$("#loginName").tips({
				side:3,
	            msg:"用户名中不能包含中文",
	            bg:'#AE81FF',
	            time:2
	        });
			$("#loginName").focus();
			return false;
		}
		$.ajax({
			type: "POST",
			url: '<%=basePath%>user/checkLoginName.do?tm='+new Date().getTime(),
	    	data: {"loginName":loginName },
			dataType:'json',
			cache: false,
			success: function(data){
				if (data == '0') {
					$("#loginName").val("");
					$("#loginName").tips({
						side:3,
			            msg:"该用户名已被注册",
			            bg:'#AE81FF',
			            time:2
			        });
					$("#loginName").focus();
					return false;
				} else {
					var passWord = $("#passWord").val();
					if (checkStr(passWord)) {
						$("#passWord").tips({
							side:3,
				            msg:"密码不能为空",
				            bg:'#AE81FF',
				            time:2
				        });
						$("#passWord").focus();
						return false;
					}
					var passWordAgin = $("#passWordAgin").val();
					if ($.trim(passWord) != $.trim(passWordAgin)){
						$("#passWordAgin").tips({
							side:3,
				            msg:"两次秘密不一致",
				            bg:'#AE81FF',
				            time:2
				        });
						$("#passWordAgin").focus();
						return false;
					}
					var chinName = $("#chinName").val();
					if (checkStr(chinName)) {
						$("#chinName").tips({
							side:3,
				            msg:"请输入用户姓名",
				            bg:'#AE81FF',
				            time:2
				        });
						$("#chinName").focus();
						return false;
					}
					var telPhone = $("#telPhone").val();
					if (checkStr(telPhone)) {
						$("#telPhone").tips({
							side:3,
				            msg:"电话号码不能为空",
				            bg:'#AE81FF',
				            time:2
				        });
						$("#telPhone").focus();
						return false;
					}
					if (telPhone != null && telPhone != '') {
						if(!(/^1[34578]\d{9}$/.test(telPhone))){ 
							$("#telPhone").tips({
								side:3,
					            msg:"手机号码有误",
					            bg:'#AE81FF',
					            time:2
					        });
							$("#telPhone").focus();
							return false;
					    } 
					}
					var email = $("#email").val();
					if (!checkStr(email)) {
						var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
						if (!filter.test(email)) {
							$("#email").tips({
								side:3,
					            msg:"请输入正确的邮箱格式",
					            bg:'#AE81FF',
					            time:2
					        });
							$("#email").focus();
							return false;
						}
					}
					var postArr = $("input[name='postArr']").val();
					if (checkStr(postArr)) {
						bootbox.alert("请选择职位");
						return false;
					}
					$("#form1").submit();
					$("#zhongxin").css("display","none");
					$("#zhongxin2").show();
				}
			}
		});
	}
}


/**
 *选择职位 
 */
function choosePost(){
	top.jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="选择职位";
	 diag.URL = '<%=basePath%>user/chosePost.do';
	 diag.Width = 900;
	 diag.Height = 450;
	 diag.CancelEvent = function(){ //关闭事件
		 if(diag.innerFrame.contentWindow.document.getElementById('zhongguo').style.display == 'none'){
			 var str = diag.innerFrame.contentWindow.document.getElementById('postListArr').value;
			 var arr = str.split(",");
			 var postArr = [];
			 $("#postListTb").find("tbody").find("tr").remove();
			 if (arr.length >1) {
				 bootbox.alert("只能选择一个职位，请重新选择");
			 } else {
				 var q = 1;
				 for (var i=0;i<arr.length;i++) 
				 {
					 var arrstr = arr[i];
					 if (checkStr(arrstr)) {
						 continue;
					 }
					 var ars = arrstr.split("@");
					 postArr.push(ars[0]);
					 $("#postListTb").append('<tr><input type="hidden" value="'+ars[0]+'"/><td class="center">'+q+'</td><td class="center">'+ars[1]+'</td><td class="center"><a class="btn btn-mini btn-danger" onclick="deltePost(this)")><i class="icon-trash"></i></a></td></tr>');
					 q++;
				 }
				 $("#postArr").val(postArr);
			 }
		}
		diag.close();
	 };
	 diag.show();
}

function deltePost(obj) {
	var ind = $(obj).parent().parent().index();
	var hids = $(obj).parent().parent().parent().find("input[type='hidden']").val();
	$(obj).parent().parent().parent().find("tr:eq("+ind+")").remove();
	var roleArr = $("#postArr").val();
	var aa = new Array();
	if (!checkStr(roleArr)) {
		var sp = roleArr.split(",");
		for (var i=0;i<sp.length;i++) {
			var tmp = sp[i];
			if (checkStr(tmp)) {
				continue;
			}
			aa.push(sp[i]);
		}
	}
	var index = $.inArray(hids,aa);
	if(index>=-1){
		 aa.splice(index,1);
 	}
	$("#postArr").val(aa);
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
