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
	<!-- jsp文件头和头部 -->
	<%@ include file="../admin/top.jsp"%> 
	<link rel="stylesheet" href="plugins/zTree/3.5/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="plugins/zTree/3.5/jquery.ztree.core-3.5.js"></script>
<body>
		
<div class="container-fluid" id="main-container">
<div id="zhongxin">
<table style="width:100%;" border="0">
	<tr>
		<td style="width:15%;" valign="top" bgcolor="#F9F9F9">
			<div style="width:15%;">
				<ul id="leftTree" class="ztree"></ul>
			</div>
		</td>
		<td style="width:85%;" valign="top" >
			<iframe name="treeFrame" id="treeFrame" frameborder="0" src="<%=basePath%>/tool/dealerList.do?multChoice=${pd.multChoice }" style="margin:0 auto;width:100%;" ></iframe>
				<c:if test="${pd.multChoice eq 'true' }">
				<div>
					<div class="widget-header widget-header-flat wi1dget-header-large">
						<h5 class="lighter">已选择结果</h5>
					</div>
					<div class="widget-body">
						<div class="widget-main">
							 <div class="step-content row-fluid position-relative" id="selectedDealer"></div> 
						</div><!--/widget-main-->
					</div><!--/widget-body-->
			 	</div>
			 		&nbsp;&nbsp;&nbsp;
			 		<a class="btn btn-mini btn-success" onclick="makeAll();">确 定</a>
					<a class="btn btn-mini btn-danger" onclick="cancel()">清 空</a>				
			 	</c:if>
		</td>
	</tr>
	<input type="hidden" id="strDealerName" value=""/>
	<input type="hidden" id="strDealerId" value=""/>
</table>
</div>
<div style="height: 20px;"></div>
</div>
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript">
			$(top.hangge());
		</script>
		<SCRIPT type="text/javascript">
		function removeBox(obj){
			$(window.frames["treeFrame"].document).find("#"+$(obj).attr("sid")).attr("checked",false);//取消复选框
			$(obj).remove();//从已选择列表移除
		}
		//根据所选角色获取对应的菜单
		function getAllNodes() {
				$.ajax({
					type: "POST",
					url: '<%=basePath%>tool/getAllBaseOrg.do?multChoice=${pd.multChoice }',
			    	data: {"roleTye":'11111'},
					dataType:'json',
					cache: false,
					success: function(data){
						 var zNodes = data.nodeList;
						 var setting = {
									view: {
										showIcon: showIconForTree
									},
									data: {
										simpleData: {
											enable: true
										}
									}
								};
						$.fn.zTree.init($("#leftTree"), setting, zNodes);
					}
				});
		}
		
		function showIconForTree(treeId, treeNode) {
			return !treeNode.isParent;
		};

		$(document).ready(function(){
			getAllNodes();
			$("#treeFrame").load(function () {//设置treeFrame动态高度
			    var mainheight = $(this).contents().find("body").height();
			    $(this).height(mainheight);
			});
		});
		
		//批量操作
		function makeAll(){
			var dealerName = '';
			var dealerId = '';
			
			$("button[name='dealers']").each(function(idx,item){
				if(dealerName=='') dealerName += $(item).val();
			  	else dealerName += ',' + $(item).val();
			  	
			  	if(dealerId=='') dealerId += $(item).attr("sid");
			  	else dealerId += ';' + $(item).attr("sid");
			});
			if(dealerId==''){
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
				return;
			}else{
				$("#strDealerId").val(dealerId);
				$("#strDealerName").val(dealerName);
				$("#zhongxin").hide();
				top.Dialog.close();
			}
		}
		
		function cancel(){
			$("#strDealerId").val("");
			$("#strDealerName").val("");
			$("#zhongxin").hide();
			top.Dialog.close();
		 }
				
	</SCRIPT>
	</body>
</html>

