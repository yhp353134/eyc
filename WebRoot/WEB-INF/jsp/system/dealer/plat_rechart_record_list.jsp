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
<%@ include file="../admin/top.jsp"%>  
<style type="text/css">
select {width: 130px;}
</style>
</head>
<body>
		<div style="width: 98%;margin-top: 5px;position: relative;left:1%;"></div>
			<table>
				<tr>
					<form action="sourceGoods/platformRechargenOwnerRecord.do"  method="post"  id="userPost">
					<input type="hidden" value="${pd.userId }" name="userId"/>
					<td>
						<span>
							<input style="margin-top: 10px;" autocomplete="off"  id="nav-search-input" type="text" name="recordId" placeholder="这里输入用户名" value="${pd.recordId }"/>
						</span>
					</td>
					<td>
						<button class="btn btn-mini btn-light" type="submit"   title="查询"><i id="nav-search-icon" class="icon-search"></i></button>
						<button type="button"  class="btn btn-mini btn-light"  title="重置" onclick="resetButton()"><i id="nav-search-icon" class="icon-repeat"></i></button>
					</td>
					</form>
				</tr>
			</table>
		</div>
		<div style="width: 100%;margin-top: 0px;">
			<table id="table_bug_report" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center" >充值编号</th>
						<th class="center" >充值金额</th>
						<th class="center" >充值时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${platList }"  var="user">
						<tr>
							<td class="center">${user.RECORD_ID }</td>
							<td class="center">${user.AMOUNT }</td> 
							<td class="center">${user.CREATE_DATE }</td> 
						</tr>
					</c:forEach>
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
		
<!-- 引入 -->
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script type="text/javascript" src="<%=path%>/static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
<script type="text/javascript">
top.hangge();
$(function(){
	
});

//重置
function resetButton() {
	$("input[name='recordId']").val("");
}


</script>
</body>
</html>

