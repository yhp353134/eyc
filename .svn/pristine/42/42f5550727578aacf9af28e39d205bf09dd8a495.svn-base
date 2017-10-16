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
<title></title>
<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="static/css/bootstrap.min.css" rel="stylesheet" />
<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link rel="stylesheet" href="static/css/font-awesome.min.css" />
<link rel="stylesheet" href="static/css/ace.min.css" />
<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
<link rel="stylesheet" href="static/css/ace-skins.min.css" />
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
<style type="text/css">
input {font-size: 12px; }
select {font-size: 12px;}
</style>
<script type="text/javascript">
	top.hangge();
	//保存
	function save(){
		if($("#roleName").val()==""){
			$("#roleName").focus();
			return false;
		}
			$("#form1").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
	}
</script>
</head>
<body>
<div style="width: 100%;position: relative;" id="zhongxin">
	<div style="width: 96%;position: relative;left: 2%;">
	<form action="role/toEditRole.do" name="form1"  id="form1"  method="post">
	<table style="width: 100%;font-size: 12px;margin-top: 10px;" class="table table-striped table-bordered table-hover">
		<thead>
			<th colspan="2">角色详情</th>
		</thead>
		<tbody>
		<tr>
			<td align="right" width="15%">角色名称：</td>
			<td align="left">
				<input type="text" id="roleName"  name="roleName" value="${roleMsg.ROLE_NAME }">
				<strong class='high' style='color:red;'> *</strong>
			</td>
		</tr>
		<tr>
			<td align="right">角色类型：</td>
			<td align="left">
				<select id="form-field-select-1" name="roleType"  onchange="getAllNodes()" readonly="readonly">
					<c:choose>
				  		<c:when test="${roleMsg.ROLE_TYPE eq '10120002' }">
				  			<option value="10120002" >公司角色</option>
				  		</c:when>
				  		<c:otherwise>
				  			<option value="10120001" >平台角色</option>
				  		</c:otherwise>
				   </c:choose>
				</select>
			</td>
		</tr>
		<c:if test="${ORG_TYPE eq '10120001' }">
			<tr>
				<td align="right" >所属机构：</td>
				<td align="left"  >
					<div id="dealerDivHtml">
						平台
					</div>
					<div class="dealerDiv">
						<input type=hidden  id="orgName"  name="orgName"' value='${roleMsg.DEALER_ID }'>
						<input type="text" id="orgNameChi"  name="orgNameChi" readonly="readonly"   value='${roleMsg.DEALER_NAME }'>&nbsp;
						<input type="button" value="选择公司" onclick="chooseDealer()" class="btn btn-purple btn-mini"  style="height: 30px;margin-top: -10px;"/>
						<strong class='high' style='color:red;'> *</strong>
					</div>
				</td>
			</tr>
		</c:if>
		<tr>
			<td align="right">角色状态：</td>
			<td align="left">
				<select id="roleStatus"  name="roleStatus">
					<option value="10021001" <c:if test="${roleMsg.ROLE_STATUS eq '10021001' }">selected="selected"</c:if>>有效</option>
					<option value="10021002" <c:if test="${roleMsg.ROLE_STATUS eq '10021002' }">selected="selected"</c:if>>无效</option>
				</select>
			</td>
		</tr>
		<tr height="35px">
			<td align="right">菜单权限：</td>
			<td align="left"></td>
		</tr>
		<tr>
			<td align="right"></td>
			<td align="left">
				<div class="zTreeDemoBackground left">
					<ul id="treeList" class="ztree"></ul>
				</div>
			</td>
		</tr>
		<tr>
			<td align="center" style="text-align: center;" colspan="2">
				<a class="btn btn-mini btn-primary" onclick="edite();">修  改</a>&nbsp;&nbsp;
				<a class="btn btn-mini btn-danger" onclick="cancel()">取  消</a>
			</td>
		</tr>
		</tbody>
	</table>
	<input type="hidden" id="menuArr" name="menuArr"/>
	<input type="hidden" id="buttonArr"  name="buttonArr"/>
	<input type="hidden" value="${roleId }"  id="roleIdSing" name="roleId"/>
	</form>
	<input type="hidden" id="orgType" value='${ORG_TYPE }'>
	<!-- 背景结束 -->
	</div>
</div>

<div id="zhongxin2" class="center" style="display:none"><img src="static/images/jzx.gif" style="width: 50px;" /><br/><h4 class="lighter block green"></h4></div>
<!-- 引入 -->
<script src="static/1.9.1/jquery.min.js"></script>
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/ace-elements.min.js"></script>
<script src="static/js/ace.min.js"></script>
<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
<script type="text/javascript" src="static/js/bootbox.min.js"></script>
<link rel="stylesheet" href="plugins/zTree/3.5/zTreeStyle.css" />
<script type="text/javascript" src="plugins/zTree/3.5/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="plugins/zTree/3.5/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
<script type="text/javascript">
$(function(){
	$(".chzn-select").chosen(); 
	$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
	$('.date-picker').datepicker();
	var roleTye = $("#form-field-select-1").val();
	if (roleTye == 10120002) {
		$(".dealerDiv").css("display","none");
		$("#dealerDivHtml").css("display","block");
	} else {
		$(".dealerDiv").css("display","block");
		$("#dealerDivHtml").css("display","none");
	}
});

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

$(document).ready(function(){
	//加载树
	getAllNodes();
});
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
function onCheck(event, treeId, treeNode) {
	cancelHalf(treeNode)
	treeNode.checkedEx = true;
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

//检查表单
function checkForm() {
	//角色名称
	var roleName = $("#roleName").val();
	if(roleName == null || roleName == 'undefind' || roleName == '') {
		bootbox.alert("请输入角色名称");
		return false;
	}
	var orgType =  $("#orgType").val();
	if (orgType == 10120001) {
		var roleTye = $("#form-field-select-1").val();
		if (roleTye == 10120002) {
			var onme = $("#orgName").val();
			if(onme == null || onme == 'undefind' || onme == '') {
				bootbox.alert("请选择公司");
				return false;
			}
		} 
	}
	return true;
}

/***
 * 点击保存按钮
 */
function edite(){
	if (checkForm()) {
		//获取node被选中的值
		var treeObj=$.fn.zTree.getZTreeObj("treeList");
		var nodes=treeObj.getCheckedNodes(true);
		var b=[];
		var c=[];
		for(var i=0;i<nodes.length;i++){
		 //菜单ID
		 var menuId = nodes[i].menuId;
		 //按钮ID
		 var buttonId = nodes[i].buttonId;
			if ((menuId != null && menuId != 'undefind' && menuId != '') && 
					(buttonId == null || buttonId == "undefind" || buttonId == '')) {
				b.push(menuId);
			}
			if ((menuId != null && menuId != 'undefind' && menuId != '') && 
					(buttonId != null && buttonId != "undefind" && buttonId != '')) {
				var z = menuId+"@"+buttonId;
				c.push(z);
			}
		}
		$("#menuArr").val(b);
		$("#buttonArr").val(c);
		var menuArr = $("#menuArr").val();
		if(menuArr == null || menuArr == 'undefind' || menuArr == '') {
			bootbox.alert("请选择菜单");
			return false;
		}
		$("#form1").submit();
		$("#zhongxin").css("display","none");
		$("#zhongxin2").show();
	}
}
  
//根据所选角色获取对应的菜单
function getAllNodes() {
	var roleTye = $("#form-field-select-1").val();
	if (roleTye == null || roleTye == "" || roleTye == "undefind") {
		roleTye=10120001;
	} else { 
		var roleTye = $("#form-field-select-1").val();
		if (roleTye == 10120001) {
			$("#orgName").val("");
			$("#orgNameChi").val("");
			$(".dealerDiv").css("display","none");
			$("#dealerDivHtml").css("display","block");
		} else {
			$(".dealerDiv").css("display","block");
			$("#dealerDivHtml").css("display","none");
		}
		$.ajax({
			type: "POST",
			url: '<%=basePath%>role/getEditeAllRoleNode.do?tm='+new Date().getTime(),
	    	data: {"roleTye":roleTye,"roleIdSing":$("#roleIdSing").val() },
			dataType:'json',
			cache: false,
			success: function(data){
				 var zNodes = data.result;
				 var setting = {
							async: {
								enable: true,
								url:"../asyncData/getNodes.php",
								autoParam:["id", "name=n", "level=lv"],
								otherParam:{"chk":"chk"},
								dataFilter: dataFilter
							},
							check: {
								enable: true,
								autoCheckTrigger: true
							},
							data: {
								simpleData: {
									enable: true
								}
							},
							callback: {
								onCheck: onCheck,
								onAsyncSuccess: onAsyncSuccess
							}
					};
					//设置选中事件，有四种关联父(p) 关联子(s)（选中（Y）或不选中(N)）
					setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
					//拼接树结构
					$.fn.zTree.init($("#treeList"), setting, zNodes);
			}
		});
	}
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
