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
	<form action="role/updatePost.do" name="formPost"  id="formPost"  method="post">
	<table style="width: 100%;font-size: 12px;margin-top: 10px;" id="bigTable" class="table table-striped table-bordered table-hover">
		<thead>
			<th colspan="2">职位详情</th>
		</thead>
		<tbody>
		<tr>
			<td align="right" width="15%">职位名称：</td>
			<td align="left">
				<input type="hidden" id="postId"  name="postId"  value="${postMsg.POST_ID }"/>
				<input type="text" id="postName"  name="postName"  value="${postMsg.POST_NAME }">
				<strong class='high' style='color:red;'> *</strong>
			</td>
		</tr>
		<tr >
			<td align="right">职位类型：</td>
			<td align="left">
				<select id="form-field-select-1" name="postType"  onchange="chooseTypeOrg()">
					<c:choose>
					  		<c:when test="${ORG_TYPE eq '10120002' }">
					  			<option value="10120002" >公司职位</option>
					  		</c:when>
					  		<c:otherwise>
					  			<option value="10120001"  <c:if test="${postMsg.POST_TYPE eq '10120001' }">selected="selected"</c:if>>平台职位</option>
						  		<option value="10120002"  <c:if test="${postMsg.POST_TYPE eq '10120002' }">selected="selected"</c:if>>公司职位</option>
					  		</c:otherwise>
					   </c:choose>
				</select>
			</td> 
		</tr>
		<tr>
			<td align="right">职位状态：</td>
			<td align="left">
				<select id="postStatus" name="postStatus">
					<option value="10021001" <c:if test="${postMsg.POST_STATUS eq '10021001' }">selected="selected"</c:if>>有效</option>
					<option value="10021002"  <c:if test="${postMsg.POST_STATUS eq '10021002' }">selected="selected"</c:if>>无效</option>
				</select>
			</td>
		</tr>
		<tr id="bigTableTr">
			<td align="right" width="15%">所属机构：</td>
			<td align="left">
				<div id="orgDiv">
					<input type=hidden id="orgName"  name="orgName"  <c:if test="${postMsg.POST_TYPE eq '10120002' }">value="${postMsg.ORG_ID }"</c:if>>
					<input type="text" id="orgNameChi"  name="orgNameChi" readonly="readonly" <c:if test="${postMsg.POST_TYPE eq '10120002' }">value="${postMsg.PLACE_NAME }"</c:if>>&nbsp;
					<c:if test="${ORG_TYPE eq '10120001' }">
						<input type="button" value="选择公司" onclick="chooseDealer()"  class="btn btn-purple btn-mini" style="height: 30px;margin-top: -10px;"/>
					</c:if>
					<strong class='high' style='color:red;'> *</strong>
				</div>
				<div id="orgDivDe">
					<input type="hidden" id="orgArr" name="orgArr" <c:if test="${postMsg.POST_TYPE eq '10120001' }">value="${postMsg.ORG_ID }"</c:if>/>
					<input type="text" id="orgArrName" readonly="readonly" <c:if test="${postMsg.POST_TYPE eq '10120001' }">value="${postMsg.PLACE_NAME }"</c:if>>&nbsp;
					<input type="button" value="选择机构" onclick="chooseOrg()" class="btn btn-purple btn-mini"  style="height: 30px;margin-top: -10px;"/>
					<strong class='high' style='color:red;'> *</strong>
				</div>
			 </td>
		</tr>
		<tr>
			<td colspan="2">
				<div style="margin-top: 20px;">
					<input type="button" value="选择角色" class="btn btn-purple btn-mini" style="height: 30px;" onclick="chooseRole()"/>
				</div>
				<div style="margin-top: 10px;">
					<table style="width: 100%;" id="roleListTb" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th class="center">角色名称</th>
								<th class="center">角色类型</th>
								<th class="center">创建人</th>
								<th class="center">创建时间</th>
								<th class="center">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${roleList}"  var="rols">
								<tr>
									<td class="center"><input type="hidden"  class="roleIdList" value="${rols.ROLE_ID }"/>${rols.ROLE_NAME }</td>
									<td class="center">
										<c:if test="${rols.ROLE_TYPE eq '10120001' }">平台角色</c:if>
										<c:if test="${rols.ROLE_TYPE eq '10120002' }">公司角色</c:if>
									</td>
									<td class="center">${rols.NAME }</td>
									<td class="center">${rols.CREATE_DATE }</td>
									<td class="center"><a class="btn btn-mini btn-danger" onclick="delteRole(this)")><i class="icon-trash"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</td>
		</tr>
		<tr height="40px;">
			<td align="center"  style="text-align: center;" colspan="2">
				<a class="btn btn-mini btn-primary" onclick="update();">修  改</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="btn btn-mini btn-danger" onclick="cancel()">取  消</a>
			</td>
		</tr>
		</tbody>
	</table>
	<input type="hidden" id="roleArr" name="roleArr"  value='${roleStr}'/>
	</form>
	<!-- 背景结束 -->
	</div>
</div>
<div id="zhongxin2" class="center" style="display:none"><img src="static/images/jzx.gif" style="width: 50px;" /><br/><h4 class="lighter block green"></h4></div>
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script src="static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/js/bootbox.min.js"></script>
<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
<link rel="stylesheet" href="plugins/zTree/3.5/zTreeStyle.css" />
<script type="text/javascript" src="plugins/zTree/3.5/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="plugins/zTree/3.5/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//加载树
	chooseTypeOrg();
});
 
//根据所选角色获取对应的菜单
function chooseTypeOrg() {
	var types = $("#form-field-select-1").val();
	if (types == '10120001') {
		//平台
		$("#orgDiv").hide();
		$("#orgDivDe").show();
		$("#orgName").val("");
		$("#orgNameChi").val("");
	} else {
		$("#orgArr").val("");
		$("#orgArrName").val("");
		//公司
		$("#bigTreee").remove();
		$("#orgDivDe").hide();
		$("#orgDiv").show();
	}
}

//选择机构
function chooseOrg() {
	 top.jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="选择机构";
	 diag.URL = '<%=path%>/tool/org_ztree.do?multChoice=false';
	 diag.Width = 860;
	 diag.Height = 510;
	 diag.CancelEvent = function(){ //关闭事件
		 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
			var strDealerName = diag.innerFrame.contentWindow.document.getElementById('strOrgName').value;
			var strDealerId = diag.innerFrame.contentWindow.document.getElementById('strOrgId').value;
			$("#orgArr").val(strDealerId);
			$("#orgArrName").val(strDealerName);
		 }
		diag.close();
	 };
	 diag.show();
}
 
//选择公司
function chooseDealer(){
	 top.jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="选择公司";
	 diag.URL = '<%=path%>/tool/dealer_ztree.do?multChoice=false';
	 diag.Width = 860;
	 diag.Height = 510;
	 diag.CancelEvent = function(){ //关闭事件
		 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
			var strDealerName = diag.innerFrame.contentWindow.document.getElementById('strDealerName').value;
			var strDealerId = diag.innerFrame.contentWindow.document.getElementById('strDealerId').value;
			$("#orgName").val(strDealerId);
			$("#orgNameChi").val(strDealerName);
		 }
		diag.close();
	 };
	 diag.show();
}

/**
 *选择角色 
 */
 function chooseRole() {
	var roleId = $("#form-field-select-1").val();
	 var diag = new Dialog();
	 diag.Drag=true;
	 diag.Title ="选择角色";
	 diag.URL = '<%=basePath%>role/jumpRolePage.do?roleDetailId='+roleId;
	 diag.Width = 800;
	 diag.Height = 400;
	 diag.CancelEvent = function(){ //关闭事件
		 if(diag.innerFrame.contentWindow.document.getElementById('zhongguo').style.display == 'none'){
			 var str = diag.innerFrame.contentWindow.document.getElementById('strTemp').value;
			 var arr = str.split(",");
			 var rolearr = [];
			 $("#roleListTb").find("tbody").find("tr").remove();
			 for (var i=0;i<arr.length;i++) 
			 {
				 var arrstr = arr[i];
				 var ars = arrstr.split("@");
				 rolearr.push(ars[0]);
				 $("#roleListTb").append('<tr><td class="center"><input type="hidden"  class="roleIdList" value="'+ars[0]+'"/>'+ars[1]+'</td><td class="center">'+ars[2]+'</td><td class="center">'+ars[3]+'</td><td class="center">'+ars[4]+'</td><td class="center"><a class="btn btn-mini btn-danger" onclick="delteRole(this)")><i class="icon-trash"></i></a></td></tr>');
			 }
			 $("#roleArr").val(rolearr);
		}
		diag.close();
	 };
	 diag.show();
}

/*
 * 删除节点
 */
function delteRole(obj) {
	var ind = $(obj).parent().parent().index();
	$(obj).parent().parent().parent().find("tr:eq("+ind+")").remove();
	var tm = [];
	$(".roleIdList").each(function(){
		 tm.push($(this).val());
	});
	$("#roleArr").val(tm);
}


//检查表单
function checkForm() {
	var postName = $("#postName").val();
	if(postName == null || postName == 'undefind' || postName == '') {
		$("#postName").tips({
			side:3,
            msg:"请输入职位名称",
            bg:'#AE81FF',
            time:2
        });
		$("#postName").focus();
		return false;
	}
	var orgNameChi = $("#orgNameChi").val();
	var orgArrName = $("#orgArrName").val();
	if((orgNameChi == null || orgNameChi == 'undefind' || orgNameChi == '') && (orgArrName == null || orgArrName == 'undefind' || orgArrName == '')) {
		bootbox.alert("请选择机构或者公司");
		return false;
	}
	var roleArr = $("#roleArr").val();
	if(roleArr == null || roleArr == 'undefind' || roleArr == '') {
		bootbox.alert("请选择角色");
		return false;
	}
	return true;
}

/***
 * 点击保存按钮
 */
function update(){
	if (checkForm()) {
		$("#formPost").submit();
		$("#zhongxin").css("display","none");
		$("#zhongxin2").show(); 
	}
}

function dataFilter(treeId, parentNode, childNodes) {
	if (parentNode.checkedEx === true) {
		for(var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].checked = parentNode.checked;
			childNodes[i].halfCheck = false;
			childNodes[i].checkedEx = true;
		}
	}
	return childNodes;
}
function onCheck(e,treeId,treeNode){
    var treeObj=$.fn.zTree.getZTreeObj("treeList");
    var nodes=treeObj.getSelectedNodes();
    var b=[];
    for(var i=0;i<nodes.length;i++){
		b.push(nodes[i].id);
    }
    $("#orgArr").val(b);
 }
function onAsyncSuccess(event, treeId, treeNode, msg) {
	cancelHalf(treeNode);
}
function cancelHalf(treeNode) {
	if (treeNode.checkedEx) return;
	var zTree = $.fn.zTree.getZTreeObj("treeList");
	treeNode.halfCheck = false;
	zTree.updateNode(treeNode);			
}


/**
 *关闭窗体 
 */
 function cancel(){
  top.Dialog.close();
 }
</script>
</body>
</html>
