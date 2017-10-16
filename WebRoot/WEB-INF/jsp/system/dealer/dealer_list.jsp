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
					<%-- <c:if test="${type eq '10120001' }">
						<td style="vertical-align:top;"> 
							<input type="hidden" id="strOrgLevel" name="strOrgLevel" value="${pd.orgLevel }"/>
							<input type="hidden" id="dealerOrg" name="dealerOrg" value="${pd.dealerOrg }"/>
							<input type="text" id="dealerOrgName" name="dealerOrgName" value="${pd.dealerOrgName }" onclick="chooseOrg()" readonly="readonly" placeholder="请选择服务站"  style="margin-top: 10px;width: 130px;"/>
						</td>
					</c:if> --%>
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
						<!-- <th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th> -->
						<th class="center">序号</th>
						<th class="center">公司代码</th>
						<th class="center">公司名称</th>
						<th class="center">类型</th>
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
							   <%--  <td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${var.DEALER_ID}" /><span class="lbl"></span></label>
								</td> --%>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
								<td class="center">${var.DEALER_CODE}</td>
								<td class="center">${var.DEALER_NAME}</td>
								<td class="center"><script>setDataName('${var.DEALER_TYPE}')</script></td>
								<td class="center">${var.ORG_NAME}</td>
								<td class="center">${var.CREATE_BY}</td>
								<td class="center">${var.CREATE_DATE}</td>
								<td style="width: 30px;" class="center">
									<c:if test="${QX.edit eq '1' }">
										<button class='btn btn-mini btn-info' style="cursor:pointer;" title="修 改" onclick="edits('${var.DEALER_ID}');" ><i class='icon-edit'></i></button>
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
				 	<c:if test="${pd.orgType eq '10120001' }">
						<c:if test="${QX.add == 1 }">
							<a class="btn btn-small btn-success" onclick="add();">新增</a>
						</c:if>
					</c:if>
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
					var strDealerName = diag.innerFrame.contentWindow.document.getElementById("strOrgName").value;
				    var strDealerId = diag.innerFrame.contentWindow.document.getElementById("strOrgId").value;
				    var strOrgLevel = diag.innerFrame.contentWindow.document.getElementById("strOrgLevel").value;
					$("#dealerOrgName").val(strDealerName);
					$("#dealerOrg").val(strDealerId);
					$("#strOrgLevel").val(strOrgLevel);
				 }
				diag.close();
				top.hangge();
			 };
			 diag.show();
		}
		
		//检索
		function search(){
			$("#Form").submit();
		}
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>dealer/goToAdd.do';
			 diag.Width = 850;
			 diag.Height = 580;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 top.jzts();
					 window.location.href=globalContextPath+"/dealer/list.do"
				}
				 diag.close();
			 };
			 diag.show();
		}
		
		//修改
		function edits(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>dealer/goEdit.do?DEALER_ID='+Id;
			 diag.Width = 800;
			 diag.Height = 550;
			 diag.CancelEvent = function(){ //关闭事件
				 /* if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}  */
				diag.close();
			 };
			 diag.show();
		}
		
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++)
					{
						  if(document.getElementsByName('ids')[i].checked){
						  	if(str=='') str += document.getElementsByName('ids')[i].value;
						  	else str += ',' + document.getElementsByName('ids')[i].value;
						  }
					}
					if(str==''){
						bootbox.dialog("您没有选择任何内容!", 
							[
							  {
								"label" : "关闭",
								"class" : "btn-small btn-success",
								"callback": function() {
									//Example.show("great success");
									}
								}
							 ]
						);
						
						$("#zcheckbox").tips({
							side:3,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						
						return;
					}else{
						if(msg == '确定要初始化选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=path%>/wechatTemplate/SynchronizationDealerInfomation.do?tm='+new Date().getTime(),
						    	data: {DEALER_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
                                    if(data.msg=="no" &&data.mess !=""){
                                    	bootbox.alert(data.mess);
                                    	$(top.hangge());
                                    }else if(data.msg=="no"){
                                    	bootbox.alert("初始化失败!");
                                    	$(top.hangge());
                                    }else{
	                                    top.hangge();
                                    	bootbox.alert("初始化成功，请勿重复点击初始化！",function(){
	                                    	$("#chushihua").removeAttr("onclick");
                                    	});
                                    }
								}
							});
						}
					}
				}
			});
		}
		</script>
		
	</body>
</html>

