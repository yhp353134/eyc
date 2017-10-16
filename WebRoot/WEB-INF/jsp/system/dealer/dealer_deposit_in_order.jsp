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
<%@ include file="../../system/admin/top.jsp"%> 
</head>
<body>
<div style="width: 100%;position: relative;" id="zhongxin">
<div style="margin-top: 10px;width: 98%;position: relative;left:1%;">
			<table id="table_report" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">订单编号</th>
						<th class="center">订单价格</th>
						<th class="center">起运城市</th>
						<th class="center">到达城市</th>
						<th class="center">车型</th>
						<th class="center">司机</th>
					</tr>
				</thead>
				<tbody>
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty orderList}">
						<c:forEach items="${orderList}" var="var">
							<tr>
								<td class="center">${var.ORDER_ID}</td>
								<td class="center">${var.PRICE}</td>
								<td class="center">${var.B_CITY_NAME}</td>
								<td class="center">${var.E_CITY_NAME}</td>
								<td class="center">${var.MODLE_NAME}</td>
								<td class="center">${var.DRIVER_NAME}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center" >没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
		<div class="page-header position-relative">
		<table style="width:100%;">
			<tr>
				 <td style="vertical-align:top;">
					<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
				</td> 
			</tr>
		</table>
		<table style="width:100%;">
			<tr>
				 <td class="center">
					<a class="btn btn-mini btn-danger" onclick="cancel()">关闭页面</a>
				</td> 
			</tr>
		</table>
		</div> 
</div>
</div>
<div id="zhongxin2" class="center" style="display:none"><img src="static/images/jzx.gif" style="width: 50px;" /><br/><h4 class="lighter block green"></h4></div>
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->	
		<script type="text/javascript">
		top.hangge();
		function cancel() {
			top.Dialog.close();
		}
		</script>
		
	</body>
</html>

