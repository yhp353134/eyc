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
	<table style="width: 100%;font-size: 12px;margin-top: 10px;" class="table table-striped table-bordered table-hover">
	<thead>
			<th colspan="4">货源详情</th>
		</thead>
		<tbody>
		<tr>
			<td style="text-align: right;"  >货源编号：</td>
			<td align="left">
				${sourcePd.ORDER_ID }
			</td>
			<td style="text-align: right;" >货主姓名：</td>
			<td align="left">
				${sourcePd.OWNER_NAME }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;"  >货主电话：</td>
			<td align="left">
				${sourcePd.OWNER_PHONE }
			</td>
			<td style="text-align: right;" >货源状态：</td>
			<td align="left">
				<script>setDataName('${sourcePd.STATUS }')</script>
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
			<td style="text-align: right;">车架号：</td>
			<td align="left">
				${sourcePd.VIN }
			</td>
			<td style="text-align: right;">指导价：</td>
			<td align="left">
				${sourcePd.DIRECT_PRICE }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;">发货人：</td>
			<td align="left">
				${sourcePd.PAY_NAME }
			</td>
			<td style="text-align: right;">收货人：</td>
			<td align="left">
				${sourcePd.RE_NAME }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;">失败原因：</td>
			<td align="left" colspan="3">
				<c:choose>
					<c:when test="${sourcePd.STATUS eq '10211003' and sourcePd.USER_ID eq sourcePd.REVOKE_USER_ID }">
						货主撤销了货源
						<c:if test="${not empty sourcePd.REVOKE_REMARK  }">：由于${sourcePd.REVOKE_REMARK }</c:if>
					</c:when>
					<c:when test="${sourcePd.STATUS eq '10211003' and sourcePd.USER_ID ne sourcePd.REVOKE_USER_ID }">
						系统撤销了您的报价
					</c:when>
					<c:otherwise>
						您的报价未被货主选中
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<c:if test="${not empty noticeList }">
			<tr>
				<td style="text-align: right;vertical-align: middle;">通知列表：</td>
				<td align="left" colspan="3">
					<c:forEach items="${noticeList }" var="item">
					<div style="display: inline;position: relative;left: 10px;">
						<font>提醒司机：${item.NAME }</font>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<font>提醒价格：${item.PRICE }</font>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<font>提醒时间：${item.NOTICE_DATE }</font>
						<br/>
					</div>
					<div style="width: 400px;height: 1px;border-top: 1px dotted  lightgray;position: relative;left: 10px;"></div>
					<div style="width: 150px;height: 5px;"></div>
				</c:forEach>
				</td>
			</tr>
		</c:if>
		<c:if test="${not empty offerList }">
			<tr>
				<td style="text-align: right;vertical-align: middle;">报价列表：</td>
				<td align="left" colspan="3">
					<c:forEach items="${offerList }" var="item">
					<div style="display: inline;position: relative;left: 10px;">
						<font>报价人：${item.DRIVER_NAME }</font>
						&nbsp;&nbsp;
						<font>所属承运商：${item.COM_NAME }</font>
						&nbsp;&nbsp;
						<font>报价：${item.PRICE }</font>
						&nbsp;&nbsp;
						<font>状态：${item.IS_SELECTED }</font>
						&nbsp;&nbsp;
						<font>报价时间：${item.PRICE_DATE }</font>
						<br/>
					</div>
					<div style="width: 580px;height: 1px;border-top: 1px dotted  lightgray;position: relative;left: 10px;"></div>
					<div style="width: 150px;height: 5px;"></div>
				</c:forEach>
				</td>
			</tr>
		</c:if>
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
/***关闭窗体  */
 function cancel(){
 	top.Dialog.close();
 }
</script>
</body>
</html>
