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
<%@ include file="../system/admin/top.jsp"%>  
<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "<%=path%>/plugins/eycuditor/";</script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/plugins/eycuditor/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/plugins/eycuditor/ueditor/ueditor.all.js"></script>
</head>
<body>
<div style="position: relative;width: 100%;" id="zhongxin">
<div style="position: relative;left: 1%;width: 98%;">
	<form action="sourceGoods/updateAdverMsg.do"  name="form1" id="form1"  method="post"  enctype="multipart/form-data">
			<input type="hidden"  name="advertId"  value="${pd.advertId }">
			<table class="table table-striped table-bordered table-hover"  style="margin-top: 10px;">
				<tr>
					<td style="text-align: right;width: 15%;" >标题：</td>
					<td style="text-align: left;">
						<input type="text"  name="title" id="title"  style="width: 350px;" value="${parmPd.NEW_TITLE }">
						<strong class='high' style='color:red;'> *</strong>
					</td>
				</tr>
				<c:if test="${not empty picUrl }">
					<tr>
						<td style="text-align: right;">标题图片：</td>
						<td style="text-align: left;" colspan="5">
							<div style="position: relative;display: inline-block;">
								<a target="_Blank"  href="${picUrl}">
									<img style="cursor: pointer;"  src="${picUrl}"  width="100px" height="100px"/>
								</a>
							</div>
						</td>
					</tr>
				</c:if>
				<tr>
					<td style="text-align: right;">修改图片：</td>
					<td style="text-align: left;" colspan="5">
						<input type="file"  name="loadFiles"  id="loadFiles">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">状态：</td>
					<td style="text-align: left;">
						<select name="advertStatus"  id="advertStatus"  style="width: 180px;margin-top: 10px;color: gray;" class="chzn-select" >
							<script type="text/javascript">setSelectOption('1002',false,'${parmPd.STATUS}')</script>
					  	</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">类型：</td>
					<td style="text-align: left;">
						<select name="advertType"  id="advertType"  style="width: 180px;margin-top: 10px;color: gray;" class="chzn-select" >
							<script type="text/javascript">setSelectOption('1035',false,'${parmPd.NEW_TYPE}')</script>
					  	</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">文档类型：</td>
					<td style="text-align: left;">
						<select name="contentType"  id="contentType"  style="width: 180px;margin-top: 10px;color: gray;" class="chzn-select" >
							<script type="text/javascript">setSelectOption('1036',false,'${parmPd.NEW_DETAIL_TYPE}')</script>
					  	</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">链接地址：</td>
					<td style="text-align: left;">
						<input type="text"  name="link" id="link"  style="width: 350px;" placeholder="这里输入文档链接地址" value="${parmPd.NEW_LINK }">
						<strong class='high' style='color:red;'>文档类型选择图文类型,内容必填，选择链接，链接地址必填</strong>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;vertical-align: middle;">内容：</td>
					<td style="text-align: left;">
						<div style="width: 99%;margin-top: 2px;">
							<textarea  name="context"   id="textarea1"  style="display: none;">${parmPd.CONTENT }</textarea>
							<iframe onload="loadFrame(this);" id="valFrame1" scrolling="no" frameborder="0" src="<%=path%>/plugins/eycuditor/index.html"  style="width:100%; height: 551px;"></iframe>
						</div>
					</td>
				</tr>
				<tr>					
					<td style="text-align: center;" colspan="5">
						<a class="btn btn-mini btn-primary" onclick="save();">修  改</a>&nbsp;&nbsp;
						<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
					</td>
				</tr>
			</table>
			</form>
	</div>
</div>
<div>
<div id="zhongxin2" class="center" style="display:none;"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
<script src="static/1.9.1/jquery.min.js"></script>
<!-- 引入 -->
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script src="static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
<script type="text/javascript">
top.hangge();
$(function(){
	$(".chzn-select").chosen(); 
	//设置默认样式
	setSelectStyle("advertType");
	setSelectStyle("advertStatus");
});
function loadFrame(obj){
	   var window = document.getElementById($(obj).attr("id")).contentWindow;
	   var textarea1 = $("#textarea1").val();
	   window.setContent(textarea1);
}

//检查非空
function check(objStr) {
	if (objStr == null || objStr == '' || objStr == 'undefind' || objStr.length == 0) {
		return true;
	} else {
		return false;
	}
}
function strToJson(str){ 
	var json = (new Function("return " + str))(); 
	return json; 
} 

//新增
function save() {
	var window = document.getElementById("valFrame1").contentWindow;
	var content = window.getContent();
	$("#textarea1").val(content);
	if (check($("#title").val())) {
		$("#title").tips({
			side:3,
            msg:"请填写标题",
            bg:'#AE81FF',
            time:2
        });
		$("#title").focus();
		return false;
	}
	var contentType = $("#contentType").val();
	if (contentType == "10361002") {
		var link = $("#link").val();
		var links = link.replace(/\s+/g,"");
		if (checkNullStr(links)) {
			$("#link").tips({
				side:3,
	            msg:"请输入链接地址",
	            bg:'#AE81FF',
	            time:2
	        });
			$("#link").focus();
			return false;
		}
		var url ="";
		var tmpUrl = links.substr(0,5).toLowerCase();
		if (tmpUrl=="https") {
			url = links;
		} else {
			url = links.substr(0,7).toLowerCase() == "http://" ? links : "http://" + links;
		}
		$("#link").val(url);
	}
	if (contentType == "10361001") {
		if (checkNullStr(content)) {
			bootbox.alert("请填写内容");
			return false;
		}
	}
	$("#form1").submit();
	$("#zhongxin").css("display","none");
	$("#zhongxin2").show();
}
</script>
</body>
</html>