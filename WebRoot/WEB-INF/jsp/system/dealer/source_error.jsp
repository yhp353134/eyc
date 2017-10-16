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
<style type="text/css">
select {width: 130px;}
.formTempClass2 {display: block;}
.formTempClass1 {display: none;}
</style>
</head>
<body>
		<div style="width: 100%;margin-top: 10px;"></div>
		<div style="width: 100%;margin-top: 0px;">
			<table id="table_bug_report" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">行号</th>
						<th class="center">错误原因</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${returnList }"  var="item">
						<tr>
							<td  class="center">第 ${item.errorNum } 行</td>
							<td class="center">${item.errorMsg }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="page-header position-relative">
			<table style="width:100%;">
				<tr>
					<td class="center">
						<%--  <c:if test="${QX.add eq '1' }">  --%>
							<button type="buttion"  class="btn btn-danger btn-mini" onclick="returnBack()">返 回</button>
						<%-- </c:if>  --%>
						<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
					</td>
				</tr>
			</table>
		</div>
<!-- 引入 -->
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/ace-elements.min.js"></script>
<script src="static/js/ace.min.js"></script>
<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
<script type="text/javascript">
top.hangge();
function returnBack(){
	window.location.href= globalContextPath+"/sourceGoods/sourceList.do";
}
</script>
</body>
</html>

