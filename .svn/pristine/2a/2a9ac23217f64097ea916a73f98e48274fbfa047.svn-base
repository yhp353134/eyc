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
</script>
</head>
<body>
<div style="width: 100%;position: relative;" id="zhongxin">
	<div style="width: 96%;position: relative;left: 2%;">
	<form action="role/addPost.do" name="formPost"  id="formPost"  method="post">
	<table style="width: 100%;font-size: 12px;margin-top: 10px;" id="bigTable" class="table table-striped table-bordered table-hover">
		<thead>
			<th colspan="2">职位详情</th>
		</thead>
		<tbody>
		<tr>
			<td align="right" width="15%">职位名称：</td>
			<td align="left">
				<input type="text" id="postName"  name="postName">
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
					  			<option value="10120001" selected="selected">平台职位</option>
						  		<option value="10120002" >公司职位</option>
					  		</c:otherwise>
					   </c:choose>
				</select>
			</td>
		</tr>
		<tr id="bigTableTr">
			<td align="right" width="15%">所属机构：</td>
			<td align="left">
				<div id="orgDiv">
					<input type=hidden  id="orgName"  name="orgName" value='<c:if test="${not empty pm }">${pm.ORG_ID }</c:if>'>
					<input type="text" id="orgNameChi"  name="orgNameChi" readonly="readonly"  value='<c:if test="${not empty pm }">${pm.PLACE_NAME }</c:if>'>&nbsp;
					<c:if test="${ORG_TYPE eq '10120001' }">
						<input type="button" value="选择公司" onclick="chooseDealer()" class="btn btn-purple btn-mini"  style="height: 30px;margin-top: -10px;"/>
					</c:if>
					<strong class='high' style='color:red;'> *</strong>
				</div>
				<div id="orgDivDe">
					<input type="hidden" id="orgArr" name="orgArr"/>
					<input type="text" id="orgArrName" readonly="readonly">&nbsp;
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
						</tbody>
					</table>
				</div>
			</td>
		</tr>
		<tr height="40px;">
			<td align="center"  style="text-align: center;" colspan="2">
				<a class="btn btn-mini btn-primary" onclick="save();">保  存</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="btn btn-mini btn-danger" onclick="cancel()">取  消</a>
			</td>
		</tr>
		</tbody>
	</table>
	<input type="hidden" id="roleArr"  name="roleArr"/>
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
	// $('.date-picker').datepicker();
	//加载树
	//getAllNodes();
	chooseTypeOrg();
});

//根据所选角色获取对应的菜单
function chooseTypeOrg() {
	var types = $("#form-field-select-1").val();
	$("#orgArr").val(null);
	$("#roleArr").val(null);
	if (types == '10120001') {
		//平台
		$("#orgDiv").hide();
		$("#orgDivDe").show();
		$("#orgName").val("");
		$("#orgNameChi").val("");
		$("#roleListTb").find("tbody").find("tr").remove();
	} else {
		$("#orgArr").val("");
		$("#orgArrName").val("");
		//经销商
		$("#bigTreee").remove();
		$("#orgDivDe").hide();
		$("#orgDiv").show();
		$("#roleListTb").find("tbody").find("tr").remove();
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
 
//根据所选角色获取对应的菜单
function getAllNodes() {
	var types = $("#form-field-select-1").val();
	$("#orgArr").val(null);
	$("#roleArr").val(null);
	if (types == '10120001') {
		//平台
		$("#orgDiv").hide();
		$("#roleListTb").find("tbody").find("tr").remove();
	    $("#bigTableTr").after('<tr height="35px" id="bigTreee" ><td align="right"></td><td align="left"><div class="zTreeDemoBackground left"><ul id="treeList" class="ztree"></ul></div></td></tr>');
	    $.ajax({
			type: "POST",
			url: '<%=basePath%>role/getPostNode.do?tm='+new Date().getTime(),
	    	data: {"roleTye":types},
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
							data: {
								simpleData: {
									enable: true
								}
							},
							callback: {
								onClick: onCheck
							}
				 };
					//拼接树结构
					$.fn.zTree.init($("#treeList"), setting, zNodes);
			}
		});
	
	} else {
		$("#orgArr").val("");
		//经销商
		$("#bigTreee").remove();
		$("#orgDiv").show();
		$("#roleListTb").find("tbody").find("tr").remove();
	}
}

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
	//选择的职位类型（平台或者公司）
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
				 $("#roleListTb").append('<tr><input type="hidden" value="'+ars[0]+'"/><td class="center">'+ars[1]+'</td><td class="center">'+ars[2]+'</td><td class="center">'+ars[3]+'</td><td class="center">'+ars[4]+'</td><td class="center"><a class="btn btn-mini btn-danger" onclick="delteRole(this)")><i class="icon-trash"></i></a></td></tr>');
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
	var hids = $(obj).parent().parent().parent().find("input[type='hidden']").val();
	$(obj).parent().parent().parent().find("tr:eq("+ind+")").remove();
	var roleArr = $("#roleArr").val();
	var aa = new Array();
	if (!checkStr(roleArr)) {
		var sp = roleArr.split(",");
		for (var i=0;i<sp.length;i++) {
			var tmp = sp[i];
			if (checkStr(tmp)) {
				continue;
			}
			aa.push(sp[i]);
		}
	}
	var index = $.inArray(hids,aa);
	if(index>=-1){
		 aa.splice(index,1);
 	}
	$("#roleArr").val(aa);
}


//检查表单
function checkForm() {
	 //角色名称
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
function save(){
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

function checkStr(objStr) {
	if (objStr == null || objStr == '' || objStr == 'undefind' || objStr.length == 0) {
		return true;
	} else {
		return false;
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
