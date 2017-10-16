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
	<div style="margin-top: 10px;"></div>
	<!-- 检索  -->
	<form action="dealer/jumpEvaluPage.do" method="post" name="Form" id="Form" style="margin: 0px;">
	<table>
		<tr>
			<td>
				<select class="chzn-select" name="busPoint" id="busPoint" data-placeholder="请选择业务点" style="vertical-align:top;width: 120px;margin-top: 10px;">
					<option value="">全部</option>
					<option value="10100004" >服务预约</option>
					<option value="10100003" >服务活动</option>
					<option value="10100008" >维修过程</option>
			  	</select>
			</td>
			<td>
				<button class="btn btn-mini btn-light" onclick="search();"  title="查询"><i id="nav-search-icon" class="icon-search"></i></button>
			<!-- 	<button type="button"  class="btn btn-mini btn-light"  title="重置" onclick="resetButton()"><i id="nav-search-icon" class="icon-repeat"></i></button> -->
			</td>
		</tr>
	</table>
	</form>
	<div style="margin-bottom: 10px;"></div>
	<!-- 检索  --> 
	<table id="table_report" class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th class="center">评价名称</th>
				<th class="center">业务点</th>
				<th class="center">创建时间</th>
				<th class="center">状态</th>
				<th class="center">操作</th>
			</tr>
		</thead>
		<tbody>
		<!-- 开始循环 -->	
		<c:choose>
			<c:when test="${not empty evList}">
				<c:forEach items="${evList}" var="var" varStatus="vs">
					<tr>
						<td class="center">${var.TYPE_NAME}</td>
						<td class="center">${var.BUS_POINT_NAME}</td>
						<td class="center">${var.CREATE_DATE}</td>
						<td class="center">${var.TYPE_STATE_NAME}</td>
						<td style="width: 30px;" class="center">
							<c:if test="${QX.edit eq '1' }">
								<button class='btn btn-mini btn-info' style="cursor:pointer;" title="编辑" onclick="edits('${var.TYPE_ID}');" ><i class='icon-edit'></i></button>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${QX.cha == 0 }">
					<tr>
						<td colspan="100" class="center">您无权查看</td>
					</tr>
				</c:if>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="100" class="center" >没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
		</tbody>
	</table>
	
<div class="page-header position-relative">
<table style="width:100%;">
	<tr>
		 <td style="vertical-align:top;">
			<c:if test="${QX.add == 1 }">
				<a class="btn btn-small btn-success" onclick="addEvauate();">新增</a>
			</c:if>
			<c:if test="${QX.sync == 1 }">
			<a class="btn btn-small btn-success" onclick="makeAll('确定要初始化选中的数据吗?');" title="初始化数据" >初始化<!-- <i class='icon-trash'></i> --></a>
			</c:if>
		</td> 
		<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
	</tr>
</table>
</div> 
		
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript">
		$(top.hangge());
		$(function(){
			$(".chzn-select").chosen(); 
		});
		//重置
		function resetButton() {
			$("#busPoint").val("");
		}
		//检索
		function search(){
			$("#Form").submit();
		}
		//新增
		function addEvauate() {
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=path%>/dealer/jumpEvaluAdddPage.do';
			 diag.Width = 520;
			 diag.Height = 300;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				} 
				diag.close();
			 };
			 diag.show();
		}
		
		//修改
		function edits(id){
			if (id == null || id == "" || id == "undefind") {
				bootbox.alert("评价项ID为空，不能编辑");
			}
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=path%>/dealer/jumpEvaluUpdatePage.do?typeId='+id;
			 diag.Width = 520;
			 diag.Height = 300;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				} 
				diag.close();
			 };
			 diag.show();
		}
	</script>
	</body>
</html>

