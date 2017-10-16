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

<div style="width: 100%;position: relative;">
	<div style="width: 96%;position: relative;left:2%;">
		<div style="width: 100%;position: relative;">
			<table>
				<tr>
					<form action="role/list.do" method="post" id="roleMainForm">
					<td align="center">
						<span>
							<input style="width: 130px;margin-top: 10px;" autocomplete="off" id="nav-search-input" type="text" name="roleName" value="${roleName }"  placeholder="请输入角色名称" />
							<i id="nav-search-icon"></i>
						</span>
					</td>
					<td align="left" >
						<select class="chzn-select"  id="roleType"  name="roleType"  data-placeholder="请选择角色">
							<option value="">全部</option>
						  <c:choose>
						  		<c:when test="${ORG_TYPE eq '10120002' }">
						  			<option value="10120002" >公司角色</option>
						  		</c:when>
						  		<c:otherwise>
						  			<option value="10120001" <c:if test="${roleType eq '10120001' }">selected="selected"</c:if>>平台角色</option>
						  			<option value="10120002" <c:if test="${roleType eq '10120002' }">selected="selected"</c:if>>公司角色</option>
						  		</c:otherwise>
						  </c:choose>
						</select>
					</td>
					<td>
						<button type="submit"  class="btn btn-mini btn-light"  title="查询"><i id="nav-search-icon" class="icon-search"></i></button>
						<button type="button"  class="btn btn-mini btn-light"  title="重置" onclick="resetButton()"><i id="nav-search-icon" class="icon-repeat"></i></button>
					</td>
					</form>
				</tr>
			</table>
		</div>
		<div style="width: 100%;margin-top: 2px;">
			<table id="table_bug_report" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">角色名称</th>
						<th class="center">角色类型</th>
						<th class="center">所属机构</th>
						<th class="center">状态</th>
						<th class="center">创建人</th>
						<th class="center">创建时间</th>
						<th class="center">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${roleList }" var="rol">
						<tr>
							<input type="hidden"  value="${rol.ROLE_ID }"/>
							<td class="center">${rol.ROLE_NAME }</td>
							<td class="center">
								<c:if test="${rol.ROLE_TYPE eq '10120001' }">平台角色</c:if>
								<c:if test="${rol.ROLE_TYPE eq '10120002' }">公司角色</c:if>
							</td> 
							<td class="center">${rol.ORG_NAME } ${rol.DEALER_NAME} </td>
							<td class="center">
								<c:if test="${rol.ROLE_STATUS eq '10021001' }">有效</c:if>
								<c:if test="${rol.ROLE_STATUS eq '10021002' }">无效</c:if>
							</td> 
							<td class="center">${rol.CREATE_BY }</td>
							<td class="center">${rol.CREATE_DATE }</td>
							<td class="center">
								<c:if test="${userType eq '10131002' or userType eq '10131004' or userType eq '10131001'}">
									<c:if test="${QX.edit eq '1' }">
										<button class='btn btn-mini btn-info' onclick="editRole(this)"><i class='icon-edit'></i></button>
									</c:if>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="page-header position-relative">
			<table style="width:100%;">
				<tr>
					<td style="vertical-align:top;">
						 <c:if test="${QX.add eq '1' }"> 
							<button type="buttion"  class="btn btn-success btn-small" onclick="addRole()">新增</button>
						</c:if> 
						<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
					</td>
				</tr>
			</table>
		</div>
		<!-- 背景层结束 -->
	</div>
</div>

<script type="text/javascript" src="static/js/bootbox.min.js"></script>
<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
<script type="text/javascript">
top.hangge();

//重置
function resetButton() {
	cleanSelected('roleType','全部');
	$("#nav-search-input").val("");
}

$(function(){
	$(".chzn-select").chosen(); 
	$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
});

//新增组
function addRole(){
	 top.jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="新增角色";
	 diag.URL = '<%=basePath%>role/toAdd.do';
	 diag.Width = 700;
	 diag.Height = 500;
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

//修改
function editRole(obj){
	//获取roleId
	var roleId= $(obj).parent().parent().find("input[type='hidden']").val();
	if(roleId == null || roleId == 'undefind' || roleId == '') {
		bootbox.alert("角色ID为空，不能修改角色信息");
		return false;
	}
	 top.jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="修改角色";
	 diag.URL = '<%=basePath%>role/toEdit.do?roleId='+roleId;
	 diag.Width = 700;
	 diag.Height = 500;
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

//删除
function delRole(ROLE_ID,msg,ROLE_NAME){
	bootbox.confirm("确定要删除["+ROLE_NAME+"]吗?", function(result) {
		if(result) {
			var url = "<%=basePath%>role/delete.do?ROLE_ID="+ROLE_ID+"&guid="+new Date().getTime();
			top.jzts();
			$.get(url,function(data){
				if("success" == data.result){
					if(msg == 'c'){
						top.jzts();
						document.location.reload();
					}else{
						top.jzts();
						window.location.href="role.do";
					}
					
				}else if("false" == data.result){
					top.hangge();
					bootbox.dialog("删除失败，请先删除此部门下的职位!", 
							[
							  {
								"label" : "关闭",
								"class" : "btn-small btn-success",
								"callback": function() {
									//Example.show("great success");
									}
								}]
						);
				}else if("false2" == data.result){
					top.hangge();
					bootbox.dialog("删除失败，请先删除此职位下的用户!", 
							[
							  {
								"label" : "关闭",
								"class" : "btn-small btn-success",
								"callback": function() {
									//Example.show("great success");
									}
								}]
						);
				}
			});
		}
	});
}

</script>

<script type="text/javascript">
//扩展权限 ==============================================================
var hcid1 = '';
var qxhc1 = '';
function kf_qx1(id,kefu_id,msg){
	if(id != hcid1){
		hcid1 = id;
		qxhc1 = '';
	}
	var value = 1;
	var wqx = $("#"+id).attr("checked");
	if(qxhc1 == ''){
		if(wqx == 'checked'){
			qxhc1 = 'checked';
		}else{
			qxhc1 = 'unchecked';
		}
	}
	if(qxhc1 == 'checked'){
		value = 0;
		qxhc1 = 'unchecked';
	}else{
		value = 1;
		qxhc1 = 'checked';
	}
		var url = "<%=basePath%>role/kfqx.do?kefu_id="+kefu_id+"&msg="+msg+"&value="+value+"&guid="+new Date().getTime();
		$.get(url,function(data){
			if(data=="success"){
				//document.location.reload();
			}
		});
}

var hcid2 = '';
var qxhc2 = '';
function kf_qx2(id,kefu_id,msg){
	if(id != hcid2){
		hcid2 = id;
		qxhc2='';
	}
	var value = 1;
	var wqx = $("#"+id).attr("checked");
	if(qxhc2 == ''){
		if(wqx == 'checked'){
			qxhc2 = 'checked';
		}else{
			qxhc2 = 'unchecked';
		}
	}
	if(qxhc2 == 'checked'){
		value = 0;
		qxhc2 = 'unchecked';
	}else{
		value = 1;
		qxhc2 = 'checked';
	}
		var url = "<%=basePath%>role/kfqx.do?kefu_id="+kefu_id+"&msg="+msg+"&value="+value+"&guid="+new Date().getTime();
		$.get(url,function(data){
			if(data=="success"){
				//document.location.reload();
			}
		});
}

var hcid3 = '';
var qxhc3 = '';
function kf_qx3(id,kefu_id,msg){
	if(id != hcid3){
		hcid3 = id;
		qxhc3='';
	}
	var value = 1;
	var wqx = $("#"+id).attr("checked");
	if(qxhc3 == ''){
		if(wqx == 'checked'){
			qxhc3 = 'checked';
		}else{
			qxhc3 = 'unchecked';
		}
	}
	if(qxhc3 == 'checked'){
		value = 0;
		qxhc3 = 'unchecked';
	}else{
		value = 1;
		qxhc3 = 'checked';
	}
		var url = "<%=basePath%>role/kfqx.do?kefu_id="+kefu_id+"&msg="+msg+"&value="+value+"&guid="+new Date().getTime();
		$.get(url,function(data){
			if(data=="success"){
				//document.location.reload();
			}
		});
}

var hcid4 = '';
var qxhc4 = '';
function kf_qx4(id,kefu_id,msg){
	if(id != hcid4){
		hcid4 = id;
		qxhc4='';
	}
	var value = 1;
	var wqx = $("#"+id).attr("checked");
	if(qxhc4 == ''){
		if(wqx == 'checked'){
			qxhc4 = 'checked';
		}else{
			qxhc4 = 'unchecked';
		}
	}
	if(qxhc4 == 'checked'){
		value = 0;
		qxhc4 = 'unchecked';
	}else{
		value = 1;
		qxhc4 = 'checked';
	}
		var url = "<%=basePath%>role/kfqx.do?kefu_id="+kefu_id+"&msg="+msg+"&value="+value+"&guid="+new Date().getTime();
		$.get(url,function(data){
			if(data=="success"){
				//document.location.reload();
			}
		});
}

//保存信件数
function c1(id,msg,value,kefu_id){
		if(isNaN(Number(value))){
			alert("请输入数字!");
			$("#"+id).val(0);
			return;
		}else{
		var url = "<%=basePath%>role/gysqxc.do?kefu_id="+kefu_id+"&msg="+msg+"&value="+value+"&guid="+new Date().getTime();
		$.get(url,function(data){
			if(data=="success"){
				//document.location.reload();
			}
		});
		}
}

//菜单权限
function editRights(ROLE_ID){
	 top.jzts();
	 var diag = new top.Dialog();
	 diag.Drag = true;
	 diag.Title = "菜单权限";
	 diag.URL = '<%=basePath%>role/auth.do?ROLE_ID='+ROLE_ID;
	 diag.Width = 280;
	 diag.Height = 370;
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
}

//按钮权限
function roleButton(ROLE_ID,msg){
	top.jzts();
	if(msg == 'add_qx'){
		var Title = "授权新增权限";
	}else if(msg == 'del_qx'){
		Title = "授权删除权限";
	}else if(msg == 'edit_qx'){
		Title = "授权修改权限";
	}else if(msg == 'cha_qx'){
		Title = "授权查看权限";
	}
	
	 var diag = new top.Dialog();
	 diag.Drag = true;
	 diag.Title = Title;
	 diag.URL = '<%=basePath%>role/button.do?ROLE_ID='+ROLE_ID+'&msg='+msg;
	 diag.Width = 200;
	 diag.Height = 370;
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
}

</script>
</body>
</html>

