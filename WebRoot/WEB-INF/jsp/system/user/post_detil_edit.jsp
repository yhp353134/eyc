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
<div class="row-fluid"  id="zhongguo" style="width: 100%;position: relative;">
<div style="width: 98%;position: relative;left:1%;">
   <form action="user/chosePost.do" method="post" id="userForm">
		<table border='0' style="margin-top: 10px;">
			<tr>
				<td>
					<span class="input-icon">
						<input autocomplete="off" id="nav-search-input" type="text" name="postNameChines"  placeholder="这里输入关键词" />
						<i id="nav-search-icon" class="icon-search"></i>
					</span>
				</td>
				<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
			</tr>
		</table>
		<table id="table_report"  style="text-align: center;margin-top: 3px;" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
						<th class="center">
							<label><input type="checkbox"  id="zcheckbox" /><span class="lbl"></span></label>
						</th>
						<th class="center">职位名称</th>
						<th class="center">职位类型</th>
						<th class="center">所属机构</th>
						<th class="center">创建人</th>
						<th class="center">创建时间</th>
					</tr>
			</thead>
			<tbody>
			<!-- 开始循环 -->	
				<c:if test="${not empty postList}">
					<c:forEach items="${postList }" var="post">
						<tr>  
							<input type="hidden"  value="${post.POST_ID }"/>
							<td class='center' style="width: 30px;">
								<label><input type="checkbox"  class="postName" name="postName"  value="${post.POST_ID }" onclick="chooseOne(this)" <c:if test="${post.ISHAS eq 'yes' }">checked="checked"</c:if> /><span class="lbl"></span></label>
							</td>
							<td class="center" class="postValName">${post.POST_NAME }</td>
							<td class="center">
								<c:if test="${post.POST_TYPE eq '10120001' }">平台职位</c:if>
								<c:if test="${post.POST_TYPE eq '10120002' }">公司职位</c:if>
							</td> 
							<td class="center">${post.ORG_NAME }</td>
							<td class="center">${post.CREATE_BY }</td>
							<td class="center">${post.CREATE_DATE }</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
	</div>
	<div class="page-header position-relative">
			<table style="width:100%;">
				<tr>
					<td style="vertical-align:top;">
						<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
					</td>
				</tr>
			</table>
		</div>
		<input type="hidden"  id="hidArr" name="hidArr"  value="${pd.hidArr }"/>	
		<input type="hidden"  id="hidNameArr" name="hidNameArr" value="${pd.hidNameArr }"/>	
		<input type="hidden"  id="postListArr" name="postListArr" value="${pd.postListArr }"/>	
</form>
<iframe id="valFrame"  frameborder="0" src="<%=basePath%>/user/jumpUserIframeEdite.do?pdl=${pd.hidNameArr }"  style="margin:0 auto;width:100%;"></iframe>		
<!-- 引入 -->
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/ace-elements.min.js"></script>
<script src="static/js/ace.min.js"></script>
<script type="text/javascript" src="static/js/bootbox.min.js"></script>
<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
<script type="text/javascript">
top.hangge();
$(function() {
		//复选框
		$('table th input:checkbox').on('click' , function(){
			var that = this;
			$(".postName").each(function() {
				this.checked = that.checked;
				$(this).closest('tr').toggleClass('selected');
				confirm();
			});
		});
});

//检索
function search(){
	top.jzts();
	$("#userForm").submit();
}

function chooseOne(obj) {
	var thisVal = $(obj).val();
	var thisName = $(obj).parent().parent().parent().find("td:eq(1)").html();
	var hidVal = $("#hidArr").val();
	var hidNameArr = $("#hidNameArr").val();
	var postListArr = $("#postListArr").val();
	var bb = new Array();
	var cc = new Array();
	var dd= new Array();
	if (!checkStr(hidVal)) {
		var arr = hidVal.split(",");
		for (var i =0;i<arr.length;i++) {
			var arrOne = arr[i];
			if (checkStr(arrOne)) {
				continue;
			}
			bb.push(arr[i]);
		}
	}
	if (!checkStr(hidNameArr)) {
		var arr = hidNameArr.split(",");
		for (var i =0;i<arr.length;i++) {
			var arrOne = arr[i];
			if (checkStr(arrOne)) {
				continue;
			}
			cc.push(arr[i]);
		}
	}
	if (!checkStr(postListArr)) {
		var arr = postListArr.split(",");
		for (var i =0;i<arr.length;i++) {
			var arrOne = arr[i];
			if (checkStr(arrOne)) {
				continue;
			}
			dd.push(arr[i]);
		}
	}
	var valFrame = $(document.getElementById('valFrame').contentWindow.document.body);  
	if ($(obj).is(':checked') == true) {
		bb.push(thisVal);
		cc.push(thisName);
		$(valFrame).find("#iframInput").val(cc);
		$("#hidArr").val(bb);
		$("#hidNameArr").val(cc);
		var tmpStr = thisVal+"@"+thisName;
		dd.push(tmpStr);
		$("#postListArr").val(dd);
	} else {
		var index = $.inArray(thisVal,bb);
		if(index>=-1){
			 bb.splice(index,1);
	 	}
		var index1 = $.inArray(thisName,cc);
		if(index1>=-1){
			cc.splice(index1,1);
	 	}
		var tmpStr1 = thisVal+"@"+thisName;
		var index2 = $.inArray(tmpStr1,dd);
		if(index2>=-1){
			dd.splice(index2,1);
	 	}
		$(valFrame).find("#iframInput").val(cc);
		$("#hidArr").val(bb);
		$("#hidNameArr").val(cc);
		$("#postListArr").val(dd);
	} 
}
	
function confirm(){
	var temp = [];
	var temp1 = [];
	var temp2 = [];
	$('input[name="postName"]:checked').each(function(){ 
		if ($(this).is(":checked")) {
			temp.push($(this).val());
			temp1.push($(this).parent().parent().parent().find("td:eq(1)").html());
			var str = $(this).val()+"@"+$(this).parent().parent().parent().find("td:eq(1)").html();
			temp2.push(str);
		} 
	}); 
	$("#hidArr").val(temp);
	$("#hidNameArr").val(temp1);
	$("#postListArr").val(temp2);
	var valFrame = $(document.getElementById('valFrame').contentWindow.document.body);  
	$(valFrame).find("#iframInput").val(temp1);
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

