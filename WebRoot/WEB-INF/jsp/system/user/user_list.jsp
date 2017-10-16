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
					<form action="user/userList.do"  method="post"  id="userPost">
					<td>
						<span>
							<input style="margin-top: 10px;" autocomplete="off"  id="nav-search-input" type="text" name="loginName" placeholder="这里输入用户名" value="${loginName }"/>
						</span>
					</td>
					<td>
						<span>
							<input style="margin-top: 10px;" autocomplete="off" id="nav-search-input" type="text" name="chineName" placeholder="这里输入用户姓名" value="${chineName }"/>
						</span>
					</td>
					<c:if test="${orgType eq '10120001' }">
						<td>
							<input type="hidden" name="orgType" id="orgType"  value="${pd.orgType }"/>
							<input type="text" name="orgName"  autocomplete="off"  id="nav-search-input"   value="${pd.orgName }" placeholder="请选择公司"  style="margin-top: 10px;"  readonly="readonly" onclick="chooseorg()"/>
						</td>
					</c:if>
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
						<th class="center" width="8%">用户名</th>
						<th class="center" width="10%">用户姓名</th>
						<th class="center" width="5%">性别</th>
						<th class="center" width="18%">职位</th>
						<th class="center" width="10%">联系电话</th>
						<th class="center" width="18%">所属机构</th>
						<th class="center" width="6%">状态</th>
						<!-- <th class="center" width="10%">创建人</th> -->
						<th class="center" width="10%">创建时间</th>
						<th class="center" width="5%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userList }"  var="user">
						<tr>
							<input type="hidden"  value="${user.USER_ID }"/>
							<td class="center">${user.USER_NAME }</td>
							<td class="center">${user.NAME }</td>
							<td class="center">${user.SEX }</td> 
							<td class="center">${user.POST_NAME }</td> 
							<td class="center">${user.TEL }</td> 
							<td class="center">${user.ORG_NAME }</td> 
							<td class="center">${user.STATUS_NAME }</td> 
							<%-- <td class="center">${user.CREATE_BY }</td> --%>
							<td class="center">${user.CREATE_DATE }</td>
							<td class="center">
								 <c:if test="${QX.edit eq '1' }">
								 	<c:choose>
								 		<c:when test="${pd.thisUserName eq 'admin'}">
								 			<button class='btn btn-mini btn-info' onclick="editUser(this)"><i class='icon-edit'></i></button>
								 		</c:when>
								 		<c:when test="${pd.thisUserName ne 'admin' and user.THIS_USER eq '0' and pd.isAmont eq '10011001'}">
								 			<button class='btn btn-mini btn-info' onclick="editUser(this)"><i class='icon-edit'></i></button>
								 		</c:when>
								 	</c:choose>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		<div class="page-header position-relative">
			<table style="width:100%;">
				<tr>
					<td style="vertical-align:top;">
						 <c:if test="${QX.add eq '1' }"> 
						 		<c:choose>
							 		<c:when test="${pd.thisUserName eq 'admin'}">
							 			<button type="buttion"  class="btn btn-success btn-mini" onclick="addUser()">新增</button>
							 		</c:when>
							 		<c:when test="${pd.thisUserName ne 'admin' and pd.isAmont eq '10011001'}">
							 			<button type="buttion"  class="btn btn-success btn-mini" onclick="addUser()">新增</button>
							 		</c:when>
							 	</c:choose>
						</c:if> 
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
//重置
function resetButton() {
	$("input[name='loginName']").val("");
	$("input[name='chineName']").val("");
	$("#orgType").val("");
	$("input[name='orgName']").val("");
}

//新增组
function addUser(){
	 top.jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="新增用户";
	 diag.URL = '<%=basePath%>user/addUser.do';
	 diag.Width = 800;
	 diag.Height = 450;
	 diag.CancelEvent = function(){ //关闭事件
		 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
			 if('${page.currentPage}' == '0'){
				 top.jzts();
				 setTimeout("self.location=self.location",100);
			 }else{
				 nextPage(${page.currentPage});
			 }
		}
		diag.close();
	 };
	 diag.show();
}

function chooseorg() {
	 top.jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="选择公司";
	 diag.URL = '<%=path%>/tool/dealer_ztree.do';
	 diag.Width = 880;
	 diag.Height = 550;
	 diag.CancelEvent = function(){ //关闭事件
		var strDealerName = diag.innerFrame.contentWindow.document.getElementById('strDealerName').value;
		var strDealerId = diag.innerFrame.contentWindow.document.getElementById('strDealerId').value;
		$("#orgType").val(strDealerId);
		$("input[name='orgName']").val(strDealerName);
		diag.close();
	 };
	 diag.show();
}

//修改
function editUser(obj){
	//获取roleId
	var userId= $(obj).parent().parent().find("input[type='hidden']").val();
	if(userId == null || userId == 'undefind' || userId == '') {
		bootbox.alert("用户ID为空，不能修改用户信息");
		return false;
	}
	 top.jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="修改用户";
	 diag.URL = '<%=basePath%>user/jumpEditePage.do?userId='+userId;
	 diag.Width = 800;
	 diag.Height = 520;
	 diag.CancelEvent = function(){ //关闭事件
		 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
			 if('${page.currentPage}' == '0'){
				 top.jzts();
				 setTimeout("self.location=self.location",100);
			 }else{
				 nextPage(${page.currentPage});
			 }
		}
		diag.close();
	 };
	 diag.show();
}

</script>
</body>
</html>

