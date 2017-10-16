<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">	
<!-- jsp文件头和头部 -->
<%@ include file="../system/admin/top.jsp"%>  
<style type="text/css">
select {width: 130px;}
</style>
</head>
<body>
		<div style="width: 100%;margin-top: 10px;"></div>
			<table>
				<tr>
					<form action="sourceGoods/advertList.do"  method="post"  id="userPost">
					<td> 
						&nbsp;&nbsp;&nbsp;&nbsp;
						广告类型:
					 	<select name="useType"  id="useType"  style="width: 120px;margin-top: 10px;color: gray;" class="chzn-select" >
							<script type="text/javascript">setSelectOption('1035',true,'${pd.useType}')</script>
					  	</select>
					</td> 
					<td>
						<span>
							<input style="margin-top: 10px;" autocomplete="off" id="nav-search-input" type="text" name="chineName" placeholder="这里输入标题" value="${pd.chineName }"/>
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
						<th class="center" >标题</th>
						<th class="center" >类型</th>
						<th class="center" >作者</th>
						<th class="center" >发布时间</th>
						<!-- <th class="center" >浏览量</th>
						<th class="center" >收藏量</th>
						<th class="center" >分享量</th>
						<th class="center" >转发量</th> -->
						<th class="center" >操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${advertList }"  var="var">
						<tr>
							<input type="hidden" value="${var.NEW_ID }" />
							<td class="center" style="cursor: pointer;" onmouseover="diplayAlert(this,'${var.NEW_TITLE }')"  onmouseout="diplayAdvertNone(this)">
								<c:choose>
									<c:when test="${fn:length(var.NEW_TITLE)>20}">${fn:substring(var.NEW_TITLE, 0, 20)}......</c:when>
									<c:otherwise>
										${var.NEW_TITLE }
									</c:otherwise>
								</c:choose>
							</td>
							<td class="center"><script>setDataName('${var.NEW_TYPE }')</script></td> 
							<td class="center">${var.CREATE_BY }</td>
							<td class="center">${var.CREATE_DATE }</td> 
							<%-- <td class="center">${var.SEE_COUNT }</td> 
							<td class="center">${var.COLL_COUNT }</td> 
							<td class="center">${var.SHARED_COUNT }</td> 
							<td class="center">${var.FORWARD_COUNT }</td>  --%>
							<td class="center">
								<c:if test="${QX.edit eq '1' }"> 
									<button type="buttion"  class="btn btn-primary btn-mini" onclick="updateAdvert('${var.NEW_ID}')">修  改</button>
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
							<button type="buttion"  class="btn btn-success btn-mini" onclick="addAdvert()">新  增</button>
						</c:if> 
						<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
					</td>
				</tr>
			</table>
		</div>
<!-- 引入 -->
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script type="text/javascript" src="<%=path%>/static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
<script type="text/javascript">
top.hangge();
$(function(){
	$(".chzn-select").chosen(); 
	//设置默认样式
	setSelectStyle("useType");
});

function addAdvert() {
	 top.jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="新增";
	 diag.URL = globalContextPath+'/sourceGoods/advertAddPage.do?curren'+new Date()+1;
	 diag.Width = 1000;
	 diag.Height = 570;
	 diag.CancelEvent = function(){ //关闭事件
		 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
			 top.jzts();
			 window.location.href= globalContextPath+'/sourceGoods/advertList.do';
		 }
		diag.close();
	 };
	 diag.show();
}

//提示未显示的内容
function diplayAlert(obj,content) {
	$(obj).tips({
		side:3,
        msg: content,
        bg:'#AE81FF',
        time:100,
        x:-15,
        y:200
    });
}

function updateAdvert(advertId){
	 top.jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="修改";
	 diag.URL = globalContextPath+'/sourceGoods/advertUpdatePage.do?advertId='+advertId+'&curren'+new Date()+1;
	 diag.Width = 1000;
	 diag.Height = 570;
	 diag.CancelEvent = function(){ //关闭事件
		 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
			 top.jzts();
			 window.location.href= globalContextPath+'/sourceGoods/advertList.do';
		 }
		diag.close();
	 };
	 diag.show();
}

//取消未显示的内容
function diplayAdvertNone(obj){
	$('.jq_tips_box').remove();
}

//重置
function resetButton() {
	$("input[name='chineName']").val("");
	cleanSelected("useType","--请选择--");
}
</script>
</body>
</html>

