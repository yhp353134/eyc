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
<div style="width: 100%;position: relative;">
<div style="margin-top: 10px;width: 98%;position: relative;left:1%;">
			<table id="table_report" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">提现编号</th>
						<th class="center">提现金额</th>
						<th class="center">提现人</th>
						<th class="center">提现时间</th>
						<th class="center">操作</th>
					</tr>
				</thead>
				<tbody>
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty orderList}">
						<c:forEach items="${orderList}" var="var" varStatus="vs">
							<tr>
								<td class="center">${var.DEPOSIT_ID}</td>
								<td class="center">${var.AMOUNT}</td>
								<td class="center">${var.NAME}</td>
								<td class="center">${var.CREATE_DATE}</td>
								<td class="center">
									<button  class='btn btn-mini btn-success'  onclick="detailOrderDeposit('${var.DEPOSIT_ID}')">订单记录</button>
								</td>
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
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">关闭页面</a>
				</td> 
			</tr>
		</table>
		</div> 
</div>
</div>
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
		//重置
		function resetButton() {
			$("input[name='dealerName']").val("");
			$("input[name='dealerCode']").val("");
		}
		
		$(function() {
			top.hangge();
			//复选框
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox').each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
					});
				});
		});

		//检索
		function search(){
			$("#Form").submit();
		}
		
		//订单列表
		function detailOrderDeposit(id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="订单列表";
			 diag.URL =globalContextPath+'/dealer/jumpDepositDetailPageInOrder.do?depositId='+id;
			 diag.Width = 800;
			 diag.Height = 400;
			 diag.CancelEvent = function(){ //关闭事件
				diag.close();
				top.hangge();
			 };
			 diag.show();
		}
		</script>
		
	</body>
</html>

