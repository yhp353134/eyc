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
<div id="zhongxin">
			<table id="table_report" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">
							<label><input type='checkbox' /><span class="lbl"></span></label>
						</th>
						<th class="center">订单编号</th>
						<th class="center">起运城市</th>
						<th class="center">到达城市</th>
						<th class="center">价格</th>
						<th class="center">司机</th>
						<th class="center">车型</th>
					</tr>
				</thead>
				<tbody>
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty orderList}">
						<c:forEach items="${orderList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' class='orderArr'  value="${var.ORDER_ID}" /><span class="lbl"></span></label>
									<input type="hidden" value="${var.DRIVER_ID }" class="drverId"/>
									<input type="hidden" value="${var.PRICE }" class="priceNum"/>
									<input type="hidden" value="${var.DRIVER_NAME }" class="driverName"/>
								</td>
								<td class="center">${var.ORDER_ID}</td>
								<td class="center">${var.B_CITY_NAME}</td>
								<td class="center">${var.E_CITY_NAME}</td>
								<td class="center">${var.PRICE}</td>
								<td class="center">${var.DRIVER_NAME}</td>
								<td class="center">${var.MODLE_NAME}</td>
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
		</div> 
		<table class="table table-striped table-bordered table-hover">
			<tr>
				 <td class="center" style="vertical-align:bottom;">
					<a class="btn btn-mini btn-primary" onclick="depositMoney();">提 现</a>&nbsp;&nbsp;
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取  消</a>
				</td> 
			</tr>
		</table>
</div>
<form action="dealer/depositMyMoneymsg.do" name="form1"  id="form1"  method="post">
	<input type="hidden" id="orderAnDriver" name="orderAnDriver"/>
	<input type="hidden" id="dealerId" name="dealerId" value="${pd.dealerId }"/>
</form>
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->	
		<script type="text/javascript">
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
		
		//提现
		function depositMoney(){
			bootbox.confirm("确定要提现吗?", function(result) {
				if(result) {
					var arr = new Array();
					$(".orderArr").each(function(){
						if ($(this).is(':checked')) {
							var orderId = $(this).val();
							var driverId = $(this).parent().parent().find(".drverId").val();
							var priceNum = $(this).parent().parent().find(".priceNum").val();
							var driverName = $(this).parent().parent().find(".driverName").val();
							arr.push(orderId+"@"+driverId+"@"+priceNum+"@"+driverName);
						}
					});
					if(arr.length==0) {
						bootbox.alert("请选择订单");
					} else {
						$("#orderAnDriver").val(arr);
						$("#form1").submit();
						$("#zhongxin").css("display","none");
						$("#zhongxin2").show();
						top.jzts();
					}
				}else{
					top.hangge();
				} 
			});
		}
		
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++)
					{
						  if(document.getElementsByName('ids')[i].checked){
						  	if(str=='') str += document.getElementsByName('ids')[i].value;
						  	else str += ',' + document.getElementsByName('ids')[i].value;
						  }
					}
					if(str==''){
						bootbox.dialog("您没有选择任何内容!", 
							[
							  {
								"label" : "关闭",
								"class" : "btn-small btn-success",
								"callback": function() {
									//Example.show("great success");
									}
								}
							 ]
						);
						
						$("#zcheckbox").tips({
							side:3,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						
						return;
					}else{
						if(msg == '确定要初始化选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=path%>/wechatTemplate/SynchronizationDealerInfomation.do?tm='+new Date().getTime(),
						    	data: {DEALER_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
                                    if(data.msg=="no" &&data.mess !=""){
                                    	bootbox.alert(data.mess);
                                    	$(top.hangge());
                                    }else if(data.msg=="no"){
                                    	bootbox.alert("初始化失败!");
                                    	$(top.hangge());
                                    }else{
	                                    top.hangge();
                                    	bootbox.alert("初始化成功，请勿重复点击初始化！",function(){
	                                    	$("#chushihua").removeAttr("onclick");
                                    	});
                                    }
								}
							});
						}
					}
				}
			});
		}
		</script>
		
	</body>
</html>

