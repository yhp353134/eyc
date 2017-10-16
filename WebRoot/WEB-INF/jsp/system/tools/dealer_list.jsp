﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	</head>
<body>
		
		
<div class="container-fluid" id="main-container">
<div id="page-content" class="clearfix">
	<div class="row-fluid">
	
			<!-- 检索  -->
			<form action="tool/dealerList.do?multChoice=${pd.multChoice }" method="post" name="dealerForm" id="dealerForm">
			<input type="hidden" name = "org_id" id="org_id" value="${pd.org_id }" /> 
			<input type="hidden" name = "org_level" id="org_level" value="${pd.org_level }" /> 
			
			<table border='0'>
				<tr>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="keyword" value="${pd.keyword }" placeholder="这里输入关键词"  style="width: 120px;"/>
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<td style="vertical-align:top;">
						<button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
				</tr>
			</table>
			<!-- 检索  -->
			<table id="table_report" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">
						<label>
						  <c:choose>
							<c:when test="${pd.multChoice eq 'true' }">
								<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						   </c:choose>
						</label>
						</th>
						<th>序号</th>
						<th>服务站CODE</th>
						<th>服务站名称</th>
						<th>所属区域</th>
						<th>联系人</th>
						<th>联系方式</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty dealerlist}">
						<c:forEach items="${dealerlist}" var="dealer" varStatus="vs">
									
							<tr>
								<td class='center' style="width: 30px;">
									<label>
									   <c:choose>
										<c:when test="${pd.multChoice eq 'true' }">
											<input type='checkbox' onclick="clickBox(this);" name='ids' value="${dealer.DEALER_NAME }" id="${dealer.DEALER_ID }" />
										</c:when>
										<c:otherwise>
											<input type='radio' onclick="clickRadio(this);" name="form-field-radio"  value="${dealer.DEALER_NAME }" id="${dealer.DEALER_ID }" />
										</c:otherwise>
									   </c:choose>
									
									<span class="lbl"></span>
									
									</label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
								<td>${dealer.DEALER_CODE }</td>
								<td>${dealer.DEALER_NAME }</td>
								<td>${dealer.ORG_NAME }</td>
								<td>${dealer.LINKMAN }</td>
								<td>${dealer.LINKTEL }</td>
							</tr>
						
						</c:forEach>
						
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center" >没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
					
				
				</tbody>
			</table>
			
			<div >
				<table style="width:100%;">
					<tr>
						<td style="vertical-align:top;">
							<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
 
	<!-- PAGE CONTENT ENDS HERE -->
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
	
		
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript">
		
		$(top.hangge());
		
		//检索
		function search(){
			top.jzts();
			$("#dealerForm").submit();
		}

		
		$(function() {
			//复选框
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					clickBox(this);
					$(this).closest('tr').toggleClass('selected');
				});	
			});
		});
		
		//列表复选框点击事件
		function clickBox(obj){
			if(obj.checked){
				//alert($("button[sid='"+obj.id+"']").val());
				//$(window.parent.document).find("button[sid='"+obj.id+"']").val()
				
				if($(window.parent.document).find("button[sid='"+obj.id+"']").val()==undefined){//不存在的情况才往里面加
				
					var chk = "<button class='btn-link' name='dealers' sid='"+obj.id+"' value='"+obj.value+"' onclick='removeBox(this);'>"+obj.value+"</button>";
					//向父页面selectedDealer赋值
					$(window.parent.document).find("#selectedDealer").append(chk);
				}
			}else{
				//alert($("button[sid='"+obj.id+"']").val());
				$("button[sid='"+obj.id+"']").remove();
				$(window.parent.document).find("button[sid='"+obj.id+"']").remove();
			}
		}
		
		//列表单选框点击事件
		function clickRadio(obj){
			window.parent.document.getElementById("strDealerName").value = obj.value;
			window.parent.document.getElementById("strDealerId").value = obj.id;
			//window.parent.document.getElementById("zhongxin").style.display="none";
			$(window.parent.document).find("#zhongxin").hide();
			top.Dialog.close();
		}
		</script>
		
	</body>
</html>