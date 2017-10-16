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
		<div style="width: 100%;margin-top: 10px;"></div>
			<table>
				<tr>
					<form action="user/driverInDealerList.do"  method="post"  id="userPost">
					<td>
						<span>
							<input style="margin-top: 10px;" autocomplete="off"  id="nav-search-input" type="text" name="loginName" placeholder="这里输入用户名" value="${pd.loginName }"/>
						</span>
					</td>
					<td>
						<span>
							<input style="margin-top: 10px;" autocomplete="off" id="nav-search-input" type="text" name="chineName" placeholder="这里输入用户姓名" value="${pd.chineName }"/>
						</span>
					</td>
					<td> 
						&nbsp;&nbsp;&nbsp;&nbsp;
						用户状态:
					 	<select name="userStatus"  id="userStatus"  style="width: 120px;margin-top: 10px;" class="chzn-select">
							<script type="text/javascript">setSelectOption('1014',true,'${pd.userStatus}')</script>
					  	</select>
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
						<th class="center" >用户名</th>
						<th class="center" >用户姓名</th>
						<th class="center" >性别</th>
						<th class="center" >联系电话</th>
						<th class="center" >审核状态</th>
						<th class="center" >用户类型</th>
						<th class="center" >所属机构</th>
						<th class="center" >注册时间</th>
						<th class="center" >创建时间</th>
						<th class="center" >操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userList }"  var="user">
						<tr>
							<input type="hidden"  name="USERID"  value="${user.USER_ID }"/>
							<td class="center">${user.USER_NAME }</td>
							<td class="center">${user.NAME }</td>
							<td class="center"><script>setDataName('${user.SEX}')</script></td> 
							<td class="center">${user.TEL }</td> 
							<td class="center"><script>setDataName('${user.AUDIT_STATUS}')</script></td> 
							<td class="center"><script>setDataName('${user.USER_TYPE}')</script></td> 
							<td class="center">${user.DEALER_NAME }</td> 
							<td class="center">${user.CREATE_DATE }</td> 
							<td class="center">${user.REGISTER_DATE }</td> 
							<td class="center" >
								<button class='btn btn-mini btn-success'  onclick="detailUser(this)">详情</button>
							</td>
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
	$(".chzn-select").chosen(); 
	//设置默认样式
	setSelectStyle("userType");
});

//重置
function resetButton() {
	$("input[name='loginName']").val("");
	$("input[name='chineName']").val("");
	cleanSelected("userStatus","--请选择--");
}


 //用户详情
function detailUser(obj){
	 var userId = $(obj).parent().parent().find("input[name='USERID']").val();
	 var userType = $(obj).parent().parent().find("input[name='USERTYPE']").val();
	 top.jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="用户详情";
	 if (userType == '10131002' || userType == '10131004') {
		 //货主
		 diag.URL = '<%=basePath%>user/detailRegistUserOwner.do?userId='+userId;
	 } else {
		 //司机
		 diag.URL = '<%=basePath%>user/detailRegistUserDriver.do?userId='+userId;
	 }
	 diag.Width = 900;
	 diag.Height = 500;
	 diag.CancelEvent = function(){ //关闭事件
		 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
			 
		}
		diag.close();
	 };
	 diag.show();
}



</script>
</body>
</html>

