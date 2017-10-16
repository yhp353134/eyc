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
img {width: 150px;height: 150px;}
</style>
<script type="text/javascript">
	top.hangge();
</script>
</head>
<body>
<div style="width: 100%;position: relative;" id="zhongxin">
	<div style="width: 96%;position: relative;left: 2%;">
	<table style="width: 100%;font-size: 12px;margin-top: 10px;" class="table table-striped table-bordered table-hover">
	<thead>
			<th colspan="4">用户审核</th>
		</thead>
		<tbody>
		<tr>
			<td style="text-align: right;">用户名：</td>
			<td align="left">
				${ps.USER_NAME }
			</td>
			<td style="text-align: right;">用户姓名：</td>
			<td align="left">
				${ps.NAME }
			</td>
		</tr>
		<tr>
		<td style="text-align: right;">性别：</td>
		<td align="left">
			<script>setDataName('${ps.SEX}')</script>
		</td>
			<td style="text-align: right;">联系电话：</td>
			<td align="left">
				${ps.TEL }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;">审核状态：</td>
			<td align="left">
				<script>setDataName('${ps.AUDIT_STATUS}')</script>
			</td>
			<td style="text-align: right;">邮箱：</td>
			<td align="left">${ps.EMAIL }</td>
		</tr>
		<tr>
			<td style="text-align: right;">注册时间：</td>
			<td align="left">
				${ps.REGISTER_DATE}
			</td>
			<td style="text-align: right;">身份证：</td>
			<td align="left">${ps.CARNUM }</td>
		</tr>
		<tr>
			<td style="text-align: right;">用户类型：</td>
			<td align="left">
				<script>setDataName('${ps.USER_TYPE}')</script>
			</td>
			<td style="text-align: right;">所属机构：</td>
			<td align="left">${ps.DEALER_NAME }</td>
		</tr>
		<tr>
			<td style="text-align: right;">身份证正面：</td>
			<td align="left">
				<c:if test="${not empty ppList}">
					<c:forEach items="${ppList }" var="pp">
						<c:if test="${pp.USE_DETIAL_TYPE eq '10191001' }">
							<div style="position: relative;">
							<div style="position: relative;left:75px;top:0px;z-index: 100;">
								<label>
									<input type="checkbox" checked="checked" name="fileImg"  value="${pp.FILE_ID}"/>
									<span class="lbl"></span>
								</label>
							</div>
							<a target="_Blank"  href="${pp.URL}">
								<img style="cursor: pointer;"  src="${pp.URL}" />
							</a>
							</div>
						</c:if>
					</c:forEach>
				</c:if>
			</td>
			<td style="text-align: right;">身份证反面：</td>
			<td align="left">
				<c:if test="${not empty ppList}">
					<c:forEach items="${ppList }" var="pp">
						<c:if test="${pp.USE_DETIAL_TYPE eq '10191002' }">
							<div style="position: relative;">
							<div style="position: relative;left:75px;top:0px;z-index: 100;">
								<label>
									<input type="checkbox" checked="checked" name="fileImg"   value="${pp.FILE_ID}"/>
									<span class="lbl"></span>
								</label>
							</div>
							<a target="_Blank"  href="${pp.URL}">
								<img style="cursor: pointer;"  src="${pp.URL}" />
							</a>
							</div>
						</c:if>
					</c:forEach>
				</c:if>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;">手持省份证：</td>
			<td align="left" colspan="3">
				<c:if test="${not empty ppList}">
					<c:forEach items="${ppList }" var="pp">
						<c:if test="${pp.USE_DETIAL_TYPE eq '10191004' }">
							<div style="position: relative;">
							<div style="position: relative;left:75px;top:0px;z-index: 100;">
								<label>
									<input type="checkbox" checked="checked" name="fileImg"  value="${pp.FILE_ID}"/>
									<span class="lbl"></span>
								</label>
							</div>
							<a target="_Blank"  href="${pp.URL}">
								<img style="cursor: pointer;"  src="${pp.URL}" />
							</a>
							</div>
						</c:if>
					</c:forEach>
				</c:if>
			</td>
		</tr>
		<form action="user/examineUser.do" name="form1"  id="form1"  method="post">
		<tr>
				<input type="hidden" id="userId"  name="userId" value="${ps.USER_ID }">
				<input type="hidden" id="examineFlag"  name="examineFlag">
				<input type="hidden" id="fileId"  name="fileId">
				<input type="hidden" id="examineType"  name="examineType" value="2">
				<input type="hidden" id="telphoneNum"  name="telphoneNum" value="${ps.TEL }">
				<input type="hidden" id="userName"  name="userName" value="${ps.USER_NAME }">
				<td style="text-align: right;">审核意见：</td>
				<td style="text-align: left;" colspan="3">
					<textarea rows="3" style="width: 400px;" id="examineSuggest"  name="examineSuggest"></textarea>
				</td>
		</tr>
		<c:if test="${not empty pp.URL}">
			<tr class="left">
				<td align="center" style="text-align: left;" colspan="4">
					<font color="red">
						提示：若用户提交的营业执照信息不合格，请将图片上方的方框取消勾选<br/>
						<!-- &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
						 2、审核意见请认真填写，该信息会通过短信的方式推送给用户 -->
						</font>
				</td>
			</tr>
		</c:if>
		<tr align="center">
			<td align="center" style="text-align: center;" colspan="4">
				<a class="btn btn-mini btn-primary" onclick="saveUser();">通  过</a>&nbsp;&nbsp;
				<a class="btn btn-mini btn-success" onclick="returnUser();">驳  回</a>&nbsp;&nbsp;
				<a class="btn btn-mini btn-danger" onclick="cancel()">关 闭</a>
			</td>
		</tr>
		</form>
		</tbody>
	</table>
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

//通过按钮
function saveUser(){
		$("#examineFlag").val("1");
		$("#form1").submit();
		$("#zhongxin").css("display","none");
		$("#zhongxin2").show();
}

//驳回按钮
function returnUser(){
		var chinName = $("#examineSuggest").val();
		if (checkStr(chinName)) {
			$("#examineSuggest").tips({
				side:3,
	            msg:"请输入审核意见",
	            bg:'#AE81FF',
	            time:2
	        });
			$("#examineSuggest").focus();
			return false;
		}
		//获取选择图片的ID
		var fileArray = new Array();
		$("input[name='fileImg']").each(function(){
			if (!$(this).is(':checked')) {
				var fileId = $(this).val();
				fileArray.push(fileId);
			}
		});
		$("#fileId").val(fileArray);
		$("#examineFlag").val("2");
		$("#form1").submit();
		$("#zhongxin").css("display","none");
		$("#zhongxin2").show();
}


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

/**
 *关闭窗体 
 */
 function cancel(){
 	top.Dialog.close();
 }
</script>
</body>
</html>
