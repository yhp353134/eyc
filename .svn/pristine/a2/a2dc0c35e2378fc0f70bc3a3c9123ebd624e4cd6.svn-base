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
a>img {width: 80px;height: 80px;}
</style>
<script type="text/javascript">
	top.hangge();
</script>
</head>
<body>
<div style="width: 100%;position: relative;" id="zhongxin">
	<div style="width: 98%;position: relative;left: 1%;">
	<table style="width: 100%;font-size: 12px;margin-top: 10px;" class="table table-striped table-bordered table-hover">
	<thead>
			<th colspan="4">意见反馈详情</th>
		</thead>
		<tbody>
		<tr>
			<td style="text-align: right;"  >反馈编号：</td>
			<td align="left">
				${feedPd.FEENDBACK_ID }
			</td>
			<td style="text-align: right;" >反馈人：</td>
			<td align="left">
				${feedPd.NAME }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;"  >反馈人类型：</td>
			<td align="left">
					<script>setDataName('${feedPd.FEEDBACK_TYPE }')</script>
			</td>
			<td style="text-align: right;" >联系方式：</td>
			<td align="left">
					${feedPd.FEEDBACK_LINK }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;"  >创建人：</td>
			<td align="left">
				${feedPd.NAME }
			</td>
			<td style="text-align: right;" >反馈时间：</td>
			<td align="left">
				${feedPd.CREATE_DATE }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;"  >反馈内容：</td>
			<td align="left" colspan="3">
				${feedPd.FEEDBACK_CONTENT }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;" >图片：</td>
			<td align="left" colspan="3">
				<c:forEach items="${picList }" var="item">
					<div style="position: relative;display: inline-block;">
						<a target="_Blank"  href="${item.URL}">
							<img style="cursor: pointer;"  src="${item.URL}" />
						</a>
					</div>
				</c:forEach>
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
/***关闭窗体  */
 function cancel(){
 	top.Dialog.close();
 }
</script>
</body>
</html>
