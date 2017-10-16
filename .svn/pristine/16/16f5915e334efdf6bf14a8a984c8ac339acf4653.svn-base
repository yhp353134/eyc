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
			<!-- 检索  -->
			<form action="sourceGoods/feedbackList.do" method="post" name="Form" id="Form" style="margin: 0px;">
			<table>
				<tr>
					<td> 
						&nbsp;&nbsp;&nbsp;&nbsp;
						用户类型:
					 	<select name="useType"  id="useType"  style="width: 120px;margin-top: 10px;color: gray;" class="chzn-select" >
							<script type="text/javascript">setSelectOption('1034',true,'${pd.useType}')</script>
					  	</select>
					  	
					</td> 
					<td>
						<span>
							<input type="text"  style="margin-top: 10px;" autocomplete="off" id="nav-search-input" name="feedUser" placeholder="反馈人" value="${pd.feedUser }"/>
						</span>
					</td>
					<td>
						<span>
							<input type="text"  style="margin-top: 10px;" autocomplete="off" id="nav-search-input" name="feedLink" placeholder="联系方式" value="${pd.feedLink }"/>
						</span>
					</td>
					<td>
						<button class="btn btn-mini btn-light" onclick="search();"  title="查询"><i id="nav-search-icon" class="icon-search"></i></button>
						<button type="button"  class="btn btn-mini btn-light"  title="重置" onclick="resetButton()"><i id="nav-search-icon" class="icon-repeat"></i></button>
					</td>
				</tr>
			</table>
			</form>
			<!-- 检索  --> 
			<table id="table_report" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">反馈人</th>
						<th class="center">联系方式</th>
						<th class="center">反馈时间</th>
						<!-- <th class="center">反馈内容</th> -->
						<th class="center">反馈人类型</th>
						<th class="center">操作</th>
					</tr>
				</thead>
				<tbody>
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty feedList}">
						<c:forEach items="${feedList}" var="var" varStatus="vs">
								<td class="center">${var.NAME}</td>
								<td class="center">${var.FEEDBACK_LINK}</td>
								<td class="center">${var.CREATE_DATE}</td>
								<%-- <td class="center">${var.FEEDBACK_CONTENT}</td> --%>
								<td class="center"><script>setDataName('${var.FEEDBACK_TYPE }');</script></td>
								<td class="center">
									<button  class='btn btn-mini btn-success'  onclick="detailFeedback('${var.FEENDBACK_ID}')">详  情</button>
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
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->	
		<script type="text/javascript">
		//重置
		function resetButton() {
			$("input[name='feedUser']").val("");
			$("input[name='feedLink']").val("");
			cleanSelected("useType","--请选择--");
		}
		
		$(function() {
			top.hangge();
			$(".chzn-select").chosen(); 
			//设置默认样式
			setSelectStyle("useType");
		});

		//检索
		function search(){
			$("#Form").submit();
		}
		
		//详情
		function detailFeedback(obj){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="反馈详情";
			 diag.URL = globalContextPath+'/sourceGoods/feedbackDetail.do?feedId='+obj+'&curren'+new Date()+1;
			 diag.Width = 700;
			 diag.Height = 350;
			 diag.CancelEvent = function(){ //关闭事件
				 diag.close();
			 };
			 diag.show();
		}
		</script>
	</body>
</html>

