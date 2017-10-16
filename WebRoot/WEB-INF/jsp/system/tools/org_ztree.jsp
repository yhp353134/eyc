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
			<iframe name="treeFrame" id="treeFrame" frameborder="0" src="<%=basePath%>/tool/orgList.do?multChoice=${pd.multChoice }" style="margin:0 auto;width:100%;" ></iframe>
		<c:if test="${pd.multChoice eq 'true' }">
			<div class="widget-box" style="margin:10px">
				<div class="widget-header widget-header-blue widget-header-flat wi1dget-header-large">
							<h4 class="lighter">已选择结果</h4>
				</div>
				<div class="widget-body">
						 
					<div class="widget-main">
						 <div class="step-content row-fluid position-relative" id="selectedDealer">
								 
						 </div> 
					</div><!--/widget-main-->
				</div><!--/widget-body-->
		 	</div>
		 		&nbsp;&nbsp;&nbsp;<a class="btn btn-small btn-success" onclick="makeAll();">确 定</a>
				<a class="btn btn-small btn-danger" onclick="cancel()">清 空</a>				
		 	</c:if>
		</td>
	</tr>
		<input type="hidden" id="strOrgName" value=""/>
		<input type="hidden" id="strOrgId" value=""/>
		<input type="hidden" id="strOrgLevel" value=""/>
</table>

</div>



</div><!--/.fluid-container#main-container-->
		
		
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
					url: '<%=basePath%>tool/getChooseBaseOrg.do?multChoice=${pd.multChoice }',
			    	//data: {"org_level":'1'},
					dataType:'json',
					cache: false,
					success: function(data){

						 var zNodes = data.nodeList;
						 //alert('zNodes=='+JSON.stringify(zNodes));
						 //JSON.parse()
						 //$.each(zNodes,function(idx,item){
							//alert(item.name);
						 //});
						 
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
			//$.fn.zTree.init($("#leftTree"), setting, zNodes);
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
			var orgLevel = '';
			var isSameType = true;
			
			$("button[name='dealers']").each(function(idx,item){
				if(dealerName=='') dealerName += $(item).val();
			  	else dealerName += ',' + $(item).val();
			  	
			  	if(dealerId=='') dealerId += $(item).attr("sid");
			  	else dealerId += ';' + $(item).attr("sid");
			  	
			  	
			  	var tempLevel = $(item).attr("orglevel");
			  	if(orgLevel==''){
			  		orgLevel = tempLevel;
			  	}else{
			  		if(orgLevel==tempLevel)
				  		orgLevel = tempLevel;
			  		else
			  			isSameType = false;
			  	}
			});
			if(isSameType==false){
				bootbox.dialog("请选择相同类型的机构!", 
						[
						  {
							"label" : "关闭",
							"class" : "btn-small btn-success",
							"callback": function() {}
							}
						 ]
					);
				return;
			}
		
			if(dealerId==''){
				bootbox.dialog("您没有选择任何内容!", 
						[
						  {
							"label" : "关闭",
							"class" : "btn-small btn-success",
							"callback": function() {}
							}
						 ]
					);
				return;
			}else{
				//alert(dealerId);
				$("#strOrgId").val(dealerId);
				$("#strOrgName").val(dealerName);
				$("#strOrgLevel").val(orgLevel);
				$("#zhongxin").hide();
				top.Dialog.close();
			}
		}
		
		function cancel(){
			$("#strOrgId").val("");
			$("#strOrgName").val("");
			$("#strOrgLevel").val("");
			$("#zhongxin").hide();
			top.Dialog.close();
		}	
	</SCRIPT>
	</body>
</html>