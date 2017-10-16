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
<div class="row-fluid"  id="zhongguo">
	<form action="role/jumpRolePage.do" method="post" id="userForm">
			<input type="hidden" id="roleDetailId" name="roleDetailId" value="${roleDetailId }">
			<input type="hidden" id="roleIdList" name="roleIdList" value="${roleIdList }"/>
			<input type="hidden" id="roleMsgList" name="roleMsgList" value="${roleMsgList }"/>
				<table border='0' style="margin-top: 10px;">
					<tr>
						<td>
							<span class="input-icon">
								<input autocomplete="off" id="roleName" type="text" name="roleName"  placeholder="这里输入关键词" style="width: 110px;" value="${roleName }"/>
								<i id="nav-search-icon" class="icon-search"></i>
							</span>
						</td>
						<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
					</tr>
				</table>
		</form>
		<table id="table_report"  style="text-align: center;margin-top: -15px;" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th class="center">
						<label><input type="checkbox"  id="zcheckbox" /><span class="lbl" ></span></label>
					</th>
					<th class="center">角色名称</th>
					<th class="center">角色类型</th>
					<th class="center">创建人</th>
					<th class="center">创建时间</th>
				</tr>
			</thead>
			<tbody>
			<!-- 开始循环 -->	
				<c:if test="${not empty roleDetilList}">
					<c:forEach items="${roleDetilList}" var="var"  varStatus="vs">
						<tr>
							<td class='center' style="width: 30px;">
								<label><input type='checkbox'  name='roleName'  value="${var.ROLE_ID}" onclick="chooseRoleList(this)" <c:if test="${var.IS_CHECK eq '1' }">checked="checked"</c:if>/><span class="lbl"></span></label>
							</td>
							<td class="center">${var.ROLE_NAME}</td>
							<td class="center">${var.ROLE_TYPE}</td>
							<td class="center">${var.NAME}</td>
							<td class="center">${var.CREATE_DATE}</td>
						</tr>
					</c:forEach>
				</c:if>
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
		<table style="width:100%;margin-top: -10px;">
			<tr>
				<td style="vertical-align:top;text-align: center;">
				    <a class="btn btn-mini btn-primary"  onclick="queding()">确  定</a>&nbsp;&nbsp;
					<a class="btn btn-mini btn-danger" onclick="cancel()">取  消</a>
				</td>
			</tr>
		</table>
		<input type="hidden" id="strTemp"/>
</div>
<!-- 引入 -->
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/ace-elements.min.js"></script>
<script src="static/js/ace.min.js"></script>
<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
<script type="text/javascript">
top.hangge();
$(function() {
		//复选框
		$('table th input:checkbox').on('click' , function(){
			var that = this;
			$(this).closest('table').find('tr > td:first-child input:checkbox').each(function(){
			this.checked = that.checked;
			$(this).closest('tr').toggleClass('selected');
			//下面是选中职位的值
			var roleId = $(this).val();
			var temp = new Array();//存放角色信息
			var idtemp =  new Array();//存放id
			//先获取以前选中的数据放在数组里面
			var roleMsgList = $("#roleMsgList").val();
			var roleIdList	=$("#roleIdList").val();
			if (!check(roleMsgList)) {
				var msgarr = roleMsgList.split(",");
				for (var i=0;i<msgarr.length;i++) {
					var msg = msgarr[i];
					if(check(msg)) {
						continue;
					}
					temp.push(msg);
				}
			}
			if (!check(roleIdList)) {
				var idgarr = roleIdList.split(",");
				for (var j=0;j<idgarr.length;j++) {
					var ids = idgarr[j];
					if(check(ids)) {
						continue;
					}
					idtemp.push(ids);
				}
			}
			if ($(this).is(':checked') == true) {
				//选中
				var val1 = $(this).parent().parent().parent().find("td").eq(1).html();
				var val2 = $(this).parent().parent().parent().find("td").eq(2).html();
				var val3 = $(this).parent().parent().parent().find("td").eq(3).html();
				var val4 = $(this).parent().parent().parent().find("td").eq(4).html();
				var b= roleId+"@"+val1+"@"+val2+"@"+val3+"@"+val4;
				temp.push(b);
				idtemp.push(roleId);
				$("#roleMsgList").val(temp);
				$("#roleIdList").val(idtemp);
			} else {
				//未选中,需要把字符串里面的数据删除
				var val5 = $(this).parent().parent().parent().find("td").eq(1).html();
				var val6 = $(this).parent().parent().parent().find("td").eq(2).html();
				var val7 = $(this).parent().parent().parent().find("td").eq(3).html();
				var val8 = $(this).parent().parent().parent().find("td").eq(4).html();
				var c= roleId+"@"+val5+"@"+val6+"@"+val7+"@"+val8;
				var idindex = $.inArray(roleId,idtemp);
				if(idindex>=-1){
					idtemp.splice(idindex,1);
			 	}
				var msgindex = $.inArray(c,temp);
				if(msgindex>=-1){
					temp.splice(msgindex,1);
			 	}
				$("#roleMsgList").val(temp);
				$("#roleIdList").val(idtemp);
			}
			});
		});
});

function check(objStr) {
	if (objStr == null || objStr == '' || objStr == 'undefind' || objStr.length == 0) {
		return true;
	} else {
		return false;
	}
}

//选中角色
function chooseRoleList(obj) {
	var roleId = $(obj).val();
	var temp = new Array();//存放角色信息
	var idtemp =  new Array();//存放id
	//先获取以前选中的数据放在数组里面
	var roleMsgList = $("#roleMsgList").val();
	var roleIdList	=$("#roleIdList").val();
	if (!check(roleMsgList)) {
		var msgarr = roleMsgList.split(",");
		for (var i=0;i<msgarr.length;i++) {
			var msg = msgarr[i];
			if(check(msg)) {
				continue;
			}
			temp.push(msg);
		}
	}
	if (!check(roleIdList)) {
		var idgarr = roleIdList.split(",");
		for (var j=0;j<idgarr.length;j++) {
			var ids = idgarr[j];
			if(check(ids)) {
				continue;
			}
			idtemp.push(ids);
		}
	}
	if ($(obj).is(':checked') == true) {
		//选中
		var val1 = $(obj).parent().parent().parent().find("td").eq(1).html();
		var val2 = $(obj).parent().parent().parent().find("td").eq(2).html();
		var val3 = $(obj).parent().parent().parent().find("td").eq(3).html();
		var val4 = $(obj).parent().parent().parent().find("td").eq(4).html();
		var b= roleId+"@"+val1+"@"+val2+"@"+val3+"@"+val4;
		temp.push(b);
		idtemp.push(roleId);
		$("#roleMsgList").val(temp);
		$("#roleIdList").val(idtemp);
	} else {
		//未选中,需要把字符串里面的数据删除
		var val5 = $(obj).parent().parent().parent().find("td").eq(1).html();
		var val6 = $(obj).parent().parent().parent().find("td").eq(2).html();
		var val7 = $(obj).parent().parent().parent().find("td").eq(3).html();
		var val8 = $(obj).parent().parent().parent().find("td").eq(4).html();
		var c= roleId+"@"+val5+"@"+val6+"@"+val7+"@"+val8;
		var idindex = $.inArray(roleId,idtemp);
		if(idindex>=-1){
			idtemp.splice(idindex,1);
	 	}
		var msgindex = $.inArray(c,temp);
		if(msgindex>=-1){
			temp.splice(msgindex,1);
	 	}
		$("#roleMsgList").val(temp);
		$("#roleIdList").val(idtemp);
	}
}
	
function queding(){
	var temp = $("#roleMsgList").val();
	$("#strTemp").val(temp);
	$("#zhongguo").css("display","none");
	Dialog.close();
}
	
/**
 *关闭窗体 
 */
 function cancel(){
  	Dialog.close();
 }	
</script>
</body>
</html>

