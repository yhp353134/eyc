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
<link rel="stylesheet" href="static/css/ace.min.css" />
<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
<link rel="stylesheet" href="static/css/ace-skins.min.css" />
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
	<form action="user/updateUserMsg.do" name="form1"  id="form1"  method="post">
	<table style="width: 100%;font-size: 12px;margin-top: 10px;" class="table table-striped table-bordered table-hover">
	<thead>
			<th colspan="4">用户详情</th>
		</thead>
		<tbody>
		<tr>
			<td style="text-align: right;" width="15%">用户名：</td>
			<td align="left">
				<input type="hidden" id="userId"  name="userId" value="${ps.USER_ID }">
				<input type="hidden" id="dealerId"  name="dealerId" value="${ps.DEALER_ID }">
				<input type="hidden" id="isAmount"  name="isAmount" value="${ps.IS_MAIN_COUNT }">
				<input type="text" id="loginName"  name="loginName" value="${ps.USER_NAME }">
				<strong class='high' style='color:red;'> *</strong>
			</td>
			<td style="text-align: right;">用户姓名：</td>
			<td align="left">
				<input type="text" id="chinName"  name="chinName" value="${ps.NAME }">
				<strong class='high' style='color:red;'> *</strong>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;" width="15%">新密码：</td>
			<td align="left">
				<input type="password" id="passWord"  name="passWord">
			</td>
			<td style="text-align: right;" width="15%">确认新密码：</td>
			<td align="left">
				<input type="password" id="passWordAgin"  name="passWordAgin">
			</td>
		</tr>
		<tr>
			<td style="text-align: right;">联系电话：</td>
			<td align="left">
				<input type="text" id="telPhone"  name="telPhone" value="${ps.TEL }">
			</td>
			<td style="text-align: right;">身份证号：</td>
			<td align="left">
				<input type="text" id="carNum"  name="carNum" value="${ps.CARNUM }">
			</td>
		</tr>
		<tr>
			<td style="text-align: right;">性别：</td>
			<td align="left">
				<label>
					<input type="radio"  value="10110001" name="gendar"  <c:if test="${ps.SEX eq '10110001' }">checked="checked"</c:if> />
					<span class="lbl">男</span>&nbsp;&nbsp;
					<input type="radio"  value="10110002" name="gendar"  <c:if test="${ps.SEX eq '10110002' }">checked="checked"</c:if> />
					<span class="lbl">女</span>
				</label>		
			</td>
			<td style="text-align: right;">状态：</td>
			<td align="left">
				<select id="userStatus" name="userStatus">
					<option value="10021001" <c:if test="${ps.STATUS eq '10021001' }">selected="selected"</c:if>>有效</option>
					<option value="10021002" <c:if test="${ps.STATUS eq '10021002' }">selected="selected"</c:if>>无效</option>
				</select>
			</td>
		</tr>
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
							<c:forEach items="${psList }"  var="pss"  varStatus="ab">
								<tr>
									<input type="hidden" class="psListInput" value="${pss.POST_ID }"/>
									<td class="center">${ab.index+1 }</td> 
									<td class="center">${pss.POST_NAME }</td>
									<td class="center"><a class="btn btn-mini btn-danger"  onclick="deltePost(this)"><i class="icon-trash"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</td>
		</tr>
		<tr align="center">
			<td align="center" style="text-align: center;" colspan="4">
				<a class="btn btn-mini btn-primary" onclick="saveUser();">修  改</a>&nbsp;&nbsp;
				<a class="btn btn-mini btn-danger" onclick="cancel()">取  消</a>
			</td>
		</tr>
		</tbody>
	</table>
	<input type="hidden"  id="postArr" name="postArr"/>
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
$(function(){
	var temp = [];
	$(".psListInput").each(function() {
		var v = $(this).val();
		temp.push(v);
	});
	$("#postArr").val(temp);
});


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
		var passWord = $("#passWord").val();
		var passWordAgin = $("#passWordAgin").val();
		if (!checkStr(passWord) || !checkStr(passWordAgin)){
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


/**
 *选择职位 
 */
function choosePost(){
	top.jzts();
	var us = $("#userId").val();
	if (checkStr(us)) {
		bootbox.alert("该用户名不存在，不能修改信息");
		return false;
	}
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="选择职位";
	 diag.URL = '<%=basePath%>user/chosePostEdit.do?hidArr='+1+'&userId='+us;
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
