<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
<head>
<base href="<%=basePath%>" >
<!-- jsp文件头和头部 -->
<%@ include file="../../system/admin/top.jsp"%> 
</head>
<body>
<div id="zhongxin">
	<form action="<%=basePath %>sourceGoods/platformRechargeInOwnerMoney.do"  id="formDriver"  method="post">
		<div class="center" style="margin-top: 20px;">
		<table style="width: 100%;font-size: 12px;margin-top: 10px;" class="table table-striped table-bordered table-hover">
			<tbody>
				<tr>
					<td style="text-align: right;">请选择货主：</td>
					<td align="left">
						<select class="chzn-select"   id="userStr" name="userStr"  style="width: 200px;margin-top: 10px;color: gray;">
							<c:forEach items="${userList }" var="user">
								<option value="${user.USER_ID },${user.NAME }">${user.NAME }(余额：${user.AMOUNT })</option>
							</c:forEach>
						</select>
						<strong class='high' style='color:red;'> *</strong>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">请输入充值金额：</td>
					<td align="left">
						<input type="text" name="priceNum" id="priceNum"  style="width: 200px;height: 20px;"/>
						<strong class='high' style='color:red;'> *</strong>
					</td>
				</tr>
				<tr>
					<td class="center" colspan="2">
						<input type="button" class="btn btn-mini btn-primary" onclick="alertDriver();" value="充  值"/>
						&nbsp;&nbsp;
						&nbsp;&nbsp;
						<a class="btn btn-mini btn-danger" onclick="cancel()">取  消</a>
					</td>
				</tr>
			</tbody>
		</div>
		</form>
</div>
<div id="zhongxin2" class="center" style="display:none"><img src="static/images/jzx.gif" style="width: 50px;" /><br/><h4 class="lighter block green"></h4></div>
<script src="static/1.9.1/jquery.min.js"></script>
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script src="static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
<script type="text/javascript">
top.hangge();
$(function(){
	$(".chzn-select").chosen(); 
});

function alertDriver() {
	var userId= $("#userStr").val();
	if (checkNullStr(userId)) {
		$("#userStr").tips({
			side:3,
            msg:"请选择货主",
            bg:'#AE81FF',
            time:2
        });
		$("#userStr").focus();
		return false;
	}
	var priceNum = $("#priceNum").val();
	if (checkNullStr(priceNum)) {
		$("#priceNum").tips({
			side:3,
            msg:"请输入金额",
            bg:'#AE81FF',
            time:2
        });
		$("#priceNum").focus();
		return false;
	}
	if (isNaN(priceNum)) {
		$("#priceNum").tips({
			side:3,
            msg:"请输入数字",
            bg:'#AE81FF',
            time:2
        });
		$("#priceNum").focus();
		return false;
	}
	$("#formDriver").submit();
	$("#zhongxin").css("display","none");
	$("#zhongxin2").show();
}

function cancel(){
 	top.Dialog.close();
 }
</script>
</body>
</html>