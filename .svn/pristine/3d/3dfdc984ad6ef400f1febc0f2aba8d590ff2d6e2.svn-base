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
<link rel="stylesheet" href="static/raty/jquery.raty.css" />
<script type="text/javascript" src="static/raty/jquery.raty.js"></script>
<style type="text/css">
input {font-size: 12px; }
select {font-size: 12px;}
a>img {width: 100px;height: 100px;}
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
			<th colspan="4">订单详情</th>
		</thead>
		<tbody>
		<tr>
			<td style="text-align: right;"  >订单编号：</td>
			<td align="left">
				${sourcePd.ORDER_ID }
			</td>
			<td style="text-align: right;">车架号：</td>
			<td align="left">
				${sourcePd.VIN }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;"  >货主电话：</td>
			<td align="left">
				${sourcePd.OWNER_PHONE }
			</td>
			<td style="text-align: right;" >订单状态：</td>
			<td align="left">
				<script>setDataName('${sourcePd.STATUS }')</script>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;" >货主姓名：</td>
			<td align="left">
				${sourcePd.OWNER_NAME }
			</td>
			<td style="text-align: right;">指导价：</td>
			<td align="left">
				${sourcePd.DIRECT_PRICE }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;">是否含税：</td>
			<td align="left">
				<script>setDataName('${sourcePd.ISTAKE }')</script>
			</td>
			<td style="text-align: right;">订单价格：</td>
			<td align="left">
				${sourcePd.PRICE }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;"  >起始地：</td>
			<td align="left">
				${sourcePd.B_CITY_NAME }
			</td>
			<td style="text-align: right;" >起始地详细地址：</td>
			<td align="left">
				${sourcePd.B_ADD }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;" >目的地：</td>
			<td align="left">
				${sourcePd.E_CITY_NAME }
			</td>
			<td style="text-align: right;" >目的地详细地址：</td>
			<td align="left">
				${sourcePd.E_ADD }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;" >接车时间：</td>
			<td align="left">
				${sourcePd.BEGIN_DATE }
			</td>
			<td style="text-align: right;" >运达时间：</td>
			<td align="left">
				${sourcePd.END_DATE }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;" >订单时间：</td>
			<td align="left">
				${sourcePd.ADD_TIME }
			</td>
			<td style="text-align: right;" >货主确认交车时间：</td>
			<td align="left">
				${sourcePd.GET_TIME }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;" >车辆品牌：</td>
			<td align="left">
				${carPd.NAME }
			</td>
			<td style="text-align: right;" >车辆车型：</td>
			<td align="left">
				${sourcePd.MODLE_NAME }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;">发货人：</td>
			<td align="left">
				${sourcePd.PAY_NAME }
			</td>
			<td style="text-align: right;">发货人电话：</td>
			<td align="left">
				${sourcePd.PAY_PHONE }
			</td>
		</tr>
		<tr>
			
			<td style="text-align: right;">收货人：</td>
			<td align="left">
				${sourcePd.RE_NAME }
			</td>
			<td style="text-align: right;">收货人电话：</td>
			<td align="left">
				${sourcePd.RE_PHONE }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;" >司机：</td>
			<td align="left">
				${sourcePd.DRIVER_NAME }
			</td>
			<td style="text-align: right;">司机电话：</td>
			<td align="left">
				${sourcePd.DRIVER_PHONE }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;">备注：</td>
			<td align="left" colspan="3">
				${sourcePd.DES }
			</td>
		</tr>
		<c:if test="${not empty  orderGuiList}">
		<tr>
			<td style="text-align: right;vertical-align: middle;">物流信息：</td>
			<td align="left" colspan="3">
				<c:forEach items="${orderGuiList }" var="item">
					<div style="display: inline;position: relative;left: 10px;">
						<font><script>setDataName('${item.NODE_ID }')</script></font>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="gray">${item.CREATE_DATE }</font>
					</div>
					<div style="width: 150px;height: 1px;border-top: 1px dotted  lightgray;position: relative;left: 10px;"></div>
					<div style="width: 150px;height: 5px;"></div>
				</c:forEach>
			</td>
		</tr>
		</c:if>
		<tr>
			<td style="text-align: right;vertical-align: middle;">收车图片：</td>
			<td align="left" colspan="3">
				<c:forEach items="${picList }" var="item">
					<c:if test="${item.USE_DETIAL_TYPE eq '10191007'}">
						<div style="position: relative;display: inline-block;">
							<a target="_Blank"  href="${item.URL}">
								<img style="cursor: pointer;"  src="${item.URL}" />
							</a>
						</div>
					</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;vertical-align: middle;">交车图片：</td>
			<td align="left" colspan="3">
				<c:forEach items="${picList }" var="item">
					<c:if test="${item.USE_DETIAL_TYPE eq '10191008'}">
						<div style="position: relative;display: inline-block;">
							<a target="_Blank"  href="${item.URL}">
								<img style="cursor: pointer;"  src="${item.URL}" />
							</a>
						</div>
					</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;vertical-align: middle;">综合评价：</td>
			<td align="left" colspan="3">
				<div id="totalEvalue" style="display: inline;"></div>
				<input type="hidden" value="${dp.CONMENT }" id="totalEvalueHid"/>
			</td>
		</tr>
		<c:if test="${not empty commentList }">
			<tr>
				<td style="text-align: right;vertical-align: middle;">评价星级：</td>
				<td align="left" colspan="3">
					<c:forEach items="${commentList }" var="com" varStatus="in">
						<div style="display: inline;position: relative;left: 10px;">
							<font >${com.PROJECT_NAME } </font>&nbsp;&nbsp;
							<div class="detail${in.index+1 }" style="display: inline;"></div>
							<input type="hidden" value="${com.CONMENT }"/>
						</div>
						<div style="width: 150px;height: 1px;border-top: 1px dotted  lightgray;position: relative;left: 10px;"></div>
						<div style="width: 150px;height: 5px;"></div>
					</c:forEach>
				</td>
			</tr>
		</c:if>
		<tr>
			<td style="text-align: right;vertical-align: middle;">订单评价：</td>
			<td align="left" colspan="3">
				${dp.CONTENT }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;vertical-align: middle;">评价图片：</td>
			<td align="left" colspan="3">
				<c:forEach items="${picList }" var="item">
					<c:if test="${item.USE_DETIAL_TYPE eq '10191009'}">
						<div style="position: relative;display: inline-block;">
							<a target="_Blank"  href="${item.URL}">
								<img style="cursor: pointer;"  src="${item.URL}" />
							</a>
						</div>
					</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;vertical-align: middle;">E运车评价：</td>
			<td align="left" colspan="3">
				${dp.E_CONTENT }
			</td>
		</tr>
		<tr align="center">
			<td align="center" style="text-align: center;" colspan="4">
				<a class="btn btn-mini btn-danger" onclick="cancel()">关  闭</a>
			</td>
		</tr>
		</tbody>
	</table>
	<!-- 背景结束 -->
	</div>
</div>
<script type="text/javascript">
$(function(){
	for (var i=1;i<7;i++) {
		$(".detail"+i).each(function(){
			var val = $(this).parent().find("input[type='hidden']").val();
			$(this).raty({ 
				path: 'static/raty/images',
				readOnly:true,
				score: val
			});
		});
	}
	var totalEvalueHid = $("#totalEvalueHid").val();
	$("#totalEvalue").raty({ 
		path: 'static/raty/images',
		readOnly:true,
		score: totalEvalueHid
	});
	
});
/***关闭窗体  */
 function cancel(){
 	top.Dialog.close();
 }
</script>
</body>
</html>
