<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = path;
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="<%=path%>/static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="<%=path%>/static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=path%>/static/css/font-awesome.min.css" />
		<!-- 下拉框 -->
		<link rel="stylesheet" href="<%=path%>/static/css/chosen.css" />
		
		<link rel="stylesheet" href="<%=path%>/static/css/ace.min.css" />
		<link rel="stylesheet" href="<%=path%>/static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="<%=path%>/static/css/ace-skins.min.css" />
		
		<link rel="stylesheet" href="<%=path%>/static/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="<%=path%>/static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="<%=path%>/static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
$(function() {
	//下拉框
	$(".chzn-select").chosen(); 
	$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
	
	//日期框
	$('.date-picker').datepicker();
	
	//单选框
	  $(':checkbox').on('click' , function(){ 
		  if($(this).attr('checked')){  
              $(':checkbox').removeAttr('checked');  
              $(this).attr('checked','checked');  
          }  	
	}); 		
	
});
  
  
	//点击确定后触发的事件
	function makepep(){
		//获取当前选中的用户
		var str = '';
		var name='';
		var flag = true;
		for(var i=0;i < $(".ids").length;i++)
		{
			if($(".ids")[i].checked){
				 if(str=='') str += $(".ids")[i].value;
				 else str += ',' + $(".ids")[i].value;
				 }
			if($(".ids")[i].checked){
				 if(name=='') name += $(".ids")[i].name;
				 else name += ',' + $(".ids")[i].name;
				 }
			if($(".ids")[i].checked){
				flag = false;
			}
	     }
		if(flag){
			bootbox.alert("请勾选一个用户再点击确定！");
			return;
		}
		 $("#idInfo").val(str);
		 $("#username").val(name);
		 $("#zhongxin11").hide();
		top.Dialog.close();
	}
	
	
	
</script>
	</head>
<body>
    <input type="hidden" name="idInfo" id="idInfo" value=""> 
    <input type="hidden" name="username" id="username" value=""> 
    <div id="zhongxin11">
    <form action="<%=path%>/fans/toDcrcUser.do" name="Form" id="Form" method="post">
    <input type="hidden" name="ORG_ID" id="ORG_ID" value="${pd.ORG_ID }"> 
    <table id="table_seach" class="table table-striped table-bordered ">
    <tr>
       <td style="text-align: right;vertical-align:top;width: 47px;padding-top: 13px;">用户名:</td>
       <td><input type="text" value="${pd.NAME }" name="NAME" id="NAME" style="vertical-align:top;width: 120px;"  placeholder="这里输入用户名"  /></td>
       <td style="width: 60px;"> <button   class="btn btn-mini btn-info" >查询</button></td>
     </tr> 
    </table>      
</form>
         <div id="zhongxin12"><font color="red">置该用户成为服务站的DCRC，接收客户预约信息。特别注意不要将我们的客户设置成DCRC身份。</font></div>
          <table id="table_report12" class="table table-striped table-bordered table-hover">
				<thead>
				   
					<tr>
					<th class="center">
						
					</th>
						<th class="center">序号</th>
						<th class="center">用户名</th>
						<th class="center">用户姓名</th>
					</tr>
				</thead>   
          <tbody>
					
				<!-- 开始循环 -->
				<c:choose>
					<c:when test="${not empty varList}">
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' class='ids' name="${var.NAME}"  value="${var.USER_ID}" /><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
								<td class="center username">${var.USER_NAME}</td>
								<td class="center name">${var.NAME}</td>							
																		    
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
			<div class="page-header position-relative">
		<table style="width:100%;">
			<tr>
				<td style="vertical-align:top;">
				</td>
				<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
			</tr>
		</table>
		</div>
    	 <div style="width:550px;height:30px "> 
   <div style="width: 55%;
	float: left;
	position: relative;"></div>
   <div style="width: 45%;float: right;position: relative;  ">
   <button class="btn btn-mini btn-primary" type="button" onclick="makepep();" > 确定</button> 
    &nbsp;
   <button class="btn btn-mini btn-danger" type="button" onclick="top.Dialog.close();" > 返回 </button>
   </div>
  </div>  
</div>		
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='<%=path%>/static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="<%=path%>/static/js/bootstrap.min.js"></script>
		<script src="<%=path%>/static/js/ace-elements.min.js"></script>
		<script src="<%=path%>/static/js/ace.min.js"></script>
		<script type="text/javascript" src="<%=path%>/static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="<%=path%>/static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="<%=path%>/static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="<%=path%>/static/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
		});
		
		
		</script>
</body>
</html>