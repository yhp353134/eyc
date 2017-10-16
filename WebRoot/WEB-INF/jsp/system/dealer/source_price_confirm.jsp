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
<%@ include file="../../system/admin/top.jsp"%> 
</head>
<body>
		<div style="width: 100%;margin-top: 10px;" id="zhongxin">
			<table id="table_bug_report" class="table table-striped table-bordered table-hover" style="width: 98%;position: relative;left: 1%;">
				<thead>
					<tr>
						<th class="center" colspan="5">报价列表</th>
					</tr>
				</thead>
				<tbody>
					<tr>
							<td class="center">选择</td>
							<td class="center">报价</td>
							<td class="center">报价时间</td> 
							<td class="center">承运商</td> 
							<td class="center">司机</td> 
						</tr>
					<c:forEach items="${priceList }"  var="user">
						<tr>
							<td class="center" style="cursor: pointer;">
								<label>
									<input type="radio"  class="priceId" name="priceId" value="${user.PRICE_ID}"/>
									<span class="lbl"></span>
								</label>
								<input type="hidden" value="${user.DRIVER_ID }"  class="DRIVER_ID"/>
								<input type="hidden" value="${user.DRIVER_NAME }"  class="DRIVER_NAME"/>
							</td>
							<td class="center">${user.PRICE }</td>
							<td class="center">${user.PRICE_DATE }</td> 
							<td class="center">${user.COM_NAME }</td> 
							<td class="center">${user.DRIVER_NAME }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		<div class="page-header position-relative">
			<table style="width:100%;">
				<tr>
					<td style="vertical-align:top;" class="center">
						<button type="buttion"  class="btn  btn-mini  btn-primary" onclick="confirmPrice()">确认报价</button>
						<button type="buttion"  class="btn  btn-mini btn-danger" onclick="cancle()">取  消</button>
					</td>
				</tr>
			</table>
		</div>
		
		<form action="sourceGoods/confirmPriceByOwner.do"  id="form1" method="post">
			<input type="hidden" id="hidOrderId" name="hidOrderId" value="${pd.sourceId }"/>
			<input type="hidden" id="hiddenPriceId" name="hiddenPriceId"/>
			<input type="hidden" id="hiddenDriverId" name="hiddenDriverId"/>
			<input type="hidden" id="hiddenDriverName" name="hiddenDriverName"/>
		</form>
<!-- 引入 -->
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script src="static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
<script type="text/javascript">
top.hangge();
function confirmPrice() {
	var priceId = $("input[name='priceId']:checked").val();
	var driverId = $("input[name='priceId']:checked").parent().parent().find(".DRIVER_ID").val();
	var driverName = $("input[name='priceId']:checked").parent().parent().find(".DRIVER_NAME").val();
	var hidOrderId = $("#hidOrderId").val();
	$("#hiddenPriceId").val(priceId);
	$("#hiddenDriverId").val(driverId);
	$("#hiddenDriverName").val(driverName);
	if (checkNullStr(priceId)) {
		bootbox.alert("请选择一家报价");
	} else{
		$("#form1").submit();
		$("#zhongxin").css("display","none");
	} 
}

function cancle() {
	top.Dialog.close();
}

</script>
</body>
</html>

