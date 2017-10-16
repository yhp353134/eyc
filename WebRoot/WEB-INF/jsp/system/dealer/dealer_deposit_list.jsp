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
			<form action="dealer/list.do" method="post" name="Form" id="Form" style="margin: 0px;">
			<table>
				<tr>
					<td>
						<span>
							<input type="text"  style="margin-top: 10px;" autocomplete="off" id="nav-search-input" name="dealerName" placeholder="公司名称" value="${pd.dealerName }"/>
						</span>
					</td>
					<td>
						<span>
							<input type="text"  style="margin-top: 10px;" autocomplete="off" id="nav-search-input" name="dealerCode" placeholder="公司代码" value="${pd.dealerCode }"/>
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
						<th class="center">序号</th>
						<th class="center">公司代码</th>
						<th class="center">公司名称</th>
						<th class="center">所属区域</th>
						<th class="center">创建人</th>
						<th class="center">创建时间</th>
						<th class="center">操作</th>
					</tr>
				</thead>
				<tbody>
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
								<td class="center">${var.DEALER_CODE}</td>
								<td class="center">${var.DEALER_NAME}</td>
								<td class="center">${var.ORG_NAME}</td>
								<td class="center">${var.CREATE_BY}</td>
								<td class="center">${var.CREATE_DATE}</td>
								<td class="center">
									<c:if test="${QX.deposit eq '1' }">
										<button class='btn btn-mini btn-info' style="cursor:pointer;" onclick="edits('${var.DEALER_ID}');" >提  现</button>
									</c:if>
									<button  class='btn btn-mini btn-success'  onclick="detailDeposit('${var.DEALER_ID}')">详  情</button>
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
			$("input[name='dealerName']").val("");
			$("input[name='dealerCode']").val("");
		}
		
		$(function() {
			top.hangge();
			//复选框
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox').each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
					});
				});
		});

		//检索
		function search(){
			$("#Form").submit();
		}
		
		//提现
		function edits(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="订单列表";
			 diag.URL =globalContextPath+'/dealer/jumpDepositPage.do?dealerId='+Id;
			 diag.Width = 800;
			 diag.Height = 400;
			 diag.CancelEvent = function(){ //关闭事件
				/* if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 top.jzts();
					 window.location.href=globalContextPath+"/dealer/dealerDepositList.do";
				}  */
				diag.close();
				top.hangge();
			 };
			 diag.show();
		}
		
		//详情
		function detailDeposit(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="提现记录";
			 diag.URL=globalContextPath+'/dealer/jumpDepositDetailPage.do?dealerId='+Id;
			 diag.Width = 900;
			 diag.Height = 500;
			 diag.CancelEvent = function(){ //关闭事件
				diag.close();
				top.hangge();
			 };
			 diag.show();
		}
		</script>
		
	</body>
</html>

