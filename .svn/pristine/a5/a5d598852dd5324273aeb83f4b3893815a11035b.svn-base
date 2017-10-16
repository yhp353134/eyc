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
	<base href="<%=basePath%>"><!-- jsp文件头和头部 -->
	<%@ include file="../../admin/top.jsp"%> 
	<style type="text/css">
	.omitted{
		overflow: hidden;
		text-overflow: ellipsis; 
		white-space: nowrap; 
	}
	.mask {       
            position: absolute; top: 0px; filter: alpha(opacity=60); background-color: #777;     
            z-index: 70; left: 0px;     
            opacity:0.5; -moz-opacity:0.5;     
        }     
	li {list-style-type:none;}
	li:hover {cursor:pointer; border:1px #ED0281 solid !important; }
	.score { border-bottom:0; width:100%; background:#438eb9;  color:#393939;}
	.score li {border:1px #CCC solid; height:26px; line-height:26px; margin:1px 4px 6px 3px;padding:0 20px;float:left}
	.score1 { border-bottom:0; width:100%; background:#438eb9;  color:#393939;}
	.score1 li {border:1px #CCC solid; height:26px; line-height:26px; margin:1px 4px 6px 3px;padding:0 20px;float:left}
	
	.score2 { border-bottom:0; width:100%; background:#438eb9;  color:#393939;}
	.score2 li {border:1px #CCC solid; height:26px; line-height:26px; margin:1px 4px 6px 3px;padding:0 20px;float:left;}
	
	.clear{ clear:both} 
	/* .bg {background:#438eb9;  color:#CCC;} */
	.bg {background:url("<%=path%>/static/images/gougou.jpg") no-repeat bottom right;border:1px #ED0281 solid !important;}
}   
	</style>
	</head>
<body>
<input type="hidden" name="labelindex" id="labelindex" value="${pd.LABEL_ID }" />
<input type="hidden" name="ORG_TYPE" id="ORG_TYPE" value="${pd.ORG_TYPE }" />
		
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<div class="row-fluid">
	
			<!-- 检索  -->
			<form action="<%=path%>/fans/list.do" method="post" name="Form" id="Form">
			<input type="hidden" name="strDealerId" id="strDealerId" value="${pd.strDealerId}" />
			<input type="hidden" name="LABEL_ID" id="LABEL_ID" value="" />
			<table>
				<tr>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="FANS_NAME" value="${pd.FANS_NAME}" placeholder="粉丝昵称" class="nichen" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<td style="width:20px;" > </td>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="SERIES_CODE" id="SERIES_CODE" data-placeholder="车型" style="vertical-align:top;width: 150px;">
							<option value=""></option>
							<option value="">全部</option>
							<c:forEach items="${carList}" var="car" varStatus="vs">
							 <option value="${car.CODE}" <c:if test="${pd.SERIES_CODE == car.CODE}">selected="selected"</c:if>>${car.NAME}</option>
							</c:forEach>
					  	</select>
					</td>
					<td style="width:20px;" > </td>
					<td><input class="Wdate" name="STARTDATE" id="STARTDATE" value="${pd.STARTDATE}" type="text"   style="width:130px;" placeholder="关注开始日期" onFocus="WdatePicker({el:$dp.$('STARTDATE'), maxDate:'#F{$dp.$D(\'ENDDATE\')}'})" /></td>
					<td style="padding-bottom:10px;width:10px;" class="center">至</td>
					<td><input class="Wdate" name="ENDDATE" id="ENDDATE" value="${pd.ENDDATE}" type="text"  style="width:130px;" placeholder="结束日期" onFocus="WdatePicker({el:$dp.$('ENDDATE'), minDate:'#F{$dp.$D(\'STARTDATE\')}'})"/></td>
					<td style="width:20px;" > </td>
					<td><input type="text" name="VIN" id="VIN" value="${pd.VIN}" maxlength="32"  title="车架号" style="width:170px;" placeholder="车架号"/> </td>
					<td style="width:20px;" > </td>
			        <c:if test="${pd.ORG_TYPE == '10120001' }">
					<td style="vertical-align:top;"> 
					 	<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="strDealerName" value="${pd.strDealerName}" class="strDealerName" placeholder="服务站" style="width:160px"; readonly="true"; /><!--添加点击事件  -->
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
				     </c:if>
					
				</tr>
				</table>
				<table>
				<tr>
					<td><input type="text" name="CAR_NO" id="CAR_NO" value="${pd.CAR_NO}" maxlength="32"  title="车牌号" style="width:137px;" placeholder="车牌号"/> </td>
					<td style="width:20px;" > </td>	
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="JUDGE" id="JUDGE" data-placeholder="是否车主" style="vertical-align:top;width: 150px;" onchange="changetype(this.value)">
							<option value=""></option>
							<option value="">全部</option>	
							<option value="shi" <c:if test="${pd.JUDGE == 'shi'}">selected="selected"</c:if>>是</option>
						    <option value="fou" <c:if test="${pd.JUDGE == 'fou'}">selected="selected"</c:if>>否</option>
					  	</select>
					</td>
					<td style="width:20px;" > </td>
				    <td class="timejudge" style="display:none;"><input class="Wdate" name="BLIDONEDATE" id="BLIDONEDATE" value="${pd.BLIDONEDATE}" type="text"  disabled="disabled"  style="width:130px;" placeholder="绑定开始日期" onFocus="WdatePicker({el:$dp.$('BLIDONEDATE'), maxDate:'#F{$dp.$D(\'BLIDTWODATE\')}'})" /></td>
					<td style="padding-bottom: 10px;width:10px;display:none;" class="timejudge center">至</td>
					<td class="timejudge" style="display:none"><input class="Wdate" name="BLIDTWODATE" id="BLIDTWODATE" value="${pd.BLIDTWODATE}" type="text"  disabled="disabled" style="width:130px;" placeholder="结束日期" onFocus="WdatePicker({el:$dp.$('BLIDTWODATE'), minDate:'#F{$dp.$D(\'BLIDONEDATE\')}'})"/></td>
					<td class="timejudge" style="width:20px;display:none"  >&nbsp&nbsp</td>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="DCRC_FLAG" id="DCRC_FLAG" data-placeholder="是否DCRC" style="vertical-align:top;width: 150px;">
							<option value=""></option>
							<option value="">全部</option>
							<c:forEach items="${statusList}" var="status" varStatus="vs">
							 <option value="${status.DATA_KEY}" <c:if test="${pd.DCRC_FLAG == status.DATA_KEY}">selected="selected"</c:if>>${status.DATA_NAME}</option>
							</c:forEach>
					  	</select>
					</td>
					<td style="width:20px;"> </td>
					<td style="vertical-align:top;" >
					<button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button>
					<a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i></a>
					<button class="btn btn-mini btn-light" type="button" onclick="reset1();" title="清空"><i id="nav-search-icon" class="icon-repeat"></i></button>									
                    </td>	
				</tr>
			</table>
			
			<c:if test="${pd.ORG_TYPE != '10120001' }">
			<table>
			<tr>
				<td>标签:</td>
				<td  ><button class="btn btn-mini btn-primary" type="button"  id="addla">创建标签</button></td>
				<td style="width:5px;"></td>
				<td  ><button class="btn btn-mini btn-primary" type="button" id="renamela">重命名</button></td>
				<td style="width:5px;"></td>
				<td ><button class="btn btn-mini btn-danger" type="button" id="deletela">删除</button></td>
				</tr>
			</table>
			<div>
			<ul class="score" id="content3" style="background-color: white;">
			<li id="" style="font-weight:bold;">全部标签</li> <!--默认标签  -->
			<c:forEach items="${labelList}" var="label" varStatus="vs">
			     <li id="${label.LABEL_ID}">${label.LABEL_NAME}</li>	
			</c:forEach>
		    </ul>
		    </div>
		    <!--清除浮动  -->
		    <div class="clear"></div>
		    </c:if>
		  </form>
		   <hr align=center width=100% color=#987cb9 size=1 style="margin: 10px 0;">
		     <div id="caozuo" style="width:100%">
		   <span style="font-weight:bold;">全部粉丝(${total.ALL_NUM})</span>
		    </div> 
			<!-- 检索  -->
		
		
			<table id="table_report" class="table table-striped table-bordered table-hover" style="table-layout:fixed;">
				
				<thead>
					<tr>
					   
						<th class="center checkbox11" style="width:30px;">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th>
						<th class="center" style="width:30px;">序号</th>
						<th class="center" style="width:140px;">粉丝信息</th>
						<c:if test="${pd.ORG_TYPE == '10120001' }">
						<th class="center" style="width:120px;">服务站</th>
						</c:if>
						<th class="center" style="width:120px;">用户标签</th>
						<th class="center" style="width:100px;">关注日期</th>
						<th class="center" style="width:93px;">取消日期</th>
						<th class="center" style="width:74px;">状态</th>
						<th class="center" style="width:80px;">车主名称</th>
						<th class="center" style="width:100px;">绑定时间</th>
						<th class="center" style="width:80px;">电话</th>
						<th class="center" style="width:94px;">车牌信息</th>
						<th class="center" style="width:169px;">车架号</th>
						<th class="center" style="width:75px;">车系</th>
						<th class="center" style="width:30px;">操作</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td class='center checkbox11' style="width: 30px;line-height:50px;">
									<label><input type='checkbox' name='ids' value="${var.FOCUS_ID}" /><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;line-height:50px;">${vs.index+1}</td>
										<td style="width:120px;">
										<div id="main" style="width:130px;height:50px;padding-left:10px;">
										 <div id="pic" style="width:50px;height:50px;float:left">
										        <c:choose>
                                              <c:when test="${var.FANS_PIC!= null && var.FANS_PIC!= ''}">  
                                                  <img  class="touxiangImg" style="vertical-align:middle;width:50px;height:50px;" src="${var.FANS_PIC}"/>                                                                                             
                                               </c:when>
                                               <c:otherwise>   
                                                  <img  class="touxiangImg" style="vertical-align:middle;width:50px;height:50px;" src="<%=path%>/static/images/fanspic.png"/>
                                               </c:otherwise> 
                                            </c:choose>
										<%--   <img  class="touxiangImg" style="vertical-align:middle;width:50px;height:50px;" src="${var.FANS_PIC}"/> --%>
										  
										 </div>
										 <div id="info" style="width:80px;height:50px;float:right" >
										   <div id="name" style="width:80px;height:30px;text-align:center;vertical-align:middle;line-height:30px;overflow:hidden;">${var.FANS_NAME}</div>
										    <div id="sex" style="width:80px;height:20px;text-align:center;">
										    <c:choose>
                                              <c:when test="${var.FANS_SEX== '10110001'}">  
                                                  <img  class="sexImg" style="vertical-align:middle;width:15px;height:20px;" src="<%=path%>/static/images/u123.jpg"/>                                                                                                
                                               </c:when>
                                               <c:when test="${var.FANS_SEX== '10110002'}"> 
                                                  <img  class="sexImg" style="vertical-align:middle;width:15px;height:20px;" src="<%=path%>/static/images/u121.jpg"/>                                                                                                   
                                               </c:when>
                                            </c:choose>
										   
										    </div>
										   </div>
										</div>
										</td>
										<c:if test="${pd.ORG_TYPE == '10120001' }">									
										<td class='center' style="line-height: 50px;width:90px;" >${var.DEALER_NAME}</td>
										</c:if>
										<td class='center tielabel omitted'  style="cursor:pointer;padding:22px;" onclick="tan('${var.FOCUS_ID}');">
										<span >
										<input type="hidden" name="LABELTEXTa" class="LABELTEXTa" value="${var.LABELTEXT}" />
										<%-- <c:choose>
                                              <c:when test="${fn:length(var.LABELTEXT)>15}">  
                                                 ${fn:substring(var.LABELTEXT ,0,15)} ...                                                                           
                                               </c:when>
                                               <c:otherwise>                                           
                                                  ${var.LABELTEXT}                                                             
                                               </c:otherwise>
                                            </c:choose> --%>
                                            ${var.LABELTEXT}    
										</span></td>
										<td class='center' style="line-height: 50px;">${var.FOLLOW_DATE}</td>
										<td class='center' style="line-height: 50px;">${var.CANCLE_DATE}</td>
										<td class='center' style="line-height: 50px;">${var.FANS_STATUS}</td>
										<td class='center' style="line-height: 50px;">${var.CUSTOMER_NAME}</td>
										<td class='center' style="line-height: 50px;">${var.BIND_DATE}</td>
										<td class='center' style="line-height: 50px;">${var.PHONE}</td>
										<td class='center' style="line-height: 50px; ">${var.CAR_NO}</td>
										<td class='center' style="line-height: 50px;">${var.VIN}</td>
										<td class='center' style="line-height: 50px;">${var.SERIES_NAME}</td>
								<td style="width: 30px;line-height:50px;" class="center">
<!-- 									<div class='hidden-phone visible-desktop btn-group'> -->
									
									<%-- 	<c:if test="${QX.edit != 1 && QX.del != 1 && QX.cha != 1}">
										<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="icon-lock" title="无权限"></i></span>
										</c:if> --%>
										
											<a class='btn btn-mini btn-success' title="详情" onclick="cha('${var.FOCUS_ID}');"><i class='icon-eye-open'></i></a>
											
											<%-- <c:if test="${QX.cha eq '1' }">
											<a class='btn btn-mini btn-info' title="贴标签" onclick="sellabelnew('${var.FOCUS_ID}');"><i class='icon-edit'></i></a>
											</c:if> --%>
											
<!-- 									</div> -->
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
					<c:if test="${pd.ORG_TYPE != '10120001' }">
					<a class="btn btn-small btn-success" onclick="makeAll('确定要为选中的数据贴标签吗?');">贴标签</a>
					</c:if>
				</td>
				<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
			</tr>
		</table>
		</div>
		</form>
	</div>
 
 
 
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		<!-- 返回顶部  -->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='<%=path%>/static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="<%=path%>/static/js/bootstrap.min.js"></script>
		<script src="<%=path%>/static/js/ace-elements.min.js"></script>
		<script src="<%=path%>/static/js/ace.min.js"></script>
	 	<%-- <script type="text/javascript" src="<%=path%>/static/css/bootstrap-datetimepicker.min.css"></script><!-- 日期框 -->  --%>
		
		<script type="text/javascript" src="<%=path%>/static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="<%=path%>/static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
	<%-- 	<script type="text/javascript" src="<%=path%>/static/js/bootstrap-datetimepicker.min.js"></script> <!-- 时间日期框 --> --%>
		<script type="text/javascript" src="<%=path%>/static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="<%=path%>/static/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript">
		var FOCUS_ID;//定义一个全局变量关注id
		$(function() {
			 //给选择经销商input框添加点击事件 
			 $(".strDealerName").click(function(e){
				 chooseDealer(); 
			 });
			 //获取当前是否车主状态
			 if($("#JUDGE").val()=="shi"){
				$(".timejudge").css("display","inline");
				$("#BLIDONEDATE").attr("disabled",false);	
				$("#BLIDTWODATE").attr("disabled",false);
				}
			 //给创建标签按钮添加点击事件并阻止事件冒泡
			 $("#addla").click(function(e){
				//阻止冒泡事件
				 e.stopPropagation();
				//弹出遮罩层
				  showMask();
				 $(".box").css("display","block");
				 $(".labelname").attr("placeholder","创建标签")
				 //清空输入框
				$(".labelname").val("");
				//当前触发事件的目标
					/* var target=e.target
					if(target.id=="addla"){
						$(target).attr("placeholder","创建标签")
					} */
			 });
			 
			 //给重命名标签按钮添加点击事件并阻止事件冒泡
			 $("#renamela").click(function(e){
					//阻止冒泡事件
					 e.stopPropagation();
					if($(".bg").length>1){
						bootbox.alert("重命名的标签不能超过两个!"); 
					}else if($(".bg").attr("id")==""){
						bootbox.alert("不能修改默认标签!"); 
					}else{
						//弹出遮罩层
						showMask();
					 $(".box").css("display","block");
					 $(".labelname").attr("placeholder","重命名标签");
					 $(".labelname").val($(".bg").html()); 
					}
				 });
			 //给删除标签按钮添加点击事件
			$("#deletela").click(function(e){
				if($(".bg").length>0){
					bootbox.confirm("确定要删除吗？(删除后粉丝所关联的当前标签也会随即删除)", function(result) {
						if(result) {
							 $(".bg").each(function(){
								 var id=$(this).attr("id");
								 //ajax删除数据
								 if(id!=""){  //id为空的不能删除
								 dellabel(id);
								 //dom树中移除
								 $(this).remove();
								 }else{
									 bootbox.alert("不能删除默认标签!"); 
								 }
							     });
						}
					});    
				}
					
							
		 }); 
			 
			   //给每个li标签添加点击事件
			 $(document).on("click",".score li",function(){
				 var aa=$(this).attr("class");
				 if(typeof(aa) == "undefined"||aa==""){
					 $(this).addClass("bg").siblings().removeClass("bg");
				}
				 //将当前li标签的id传入到隐藏域
				$("#LABEL_ID").val( $(this).attr("id"));
				 //提交表单
				search(); 
				 
			 }); 
			   //给每个li标签添加点击事件
			 $(document).on("click",".score1 li",function(){
				 var aa=$(this).attr("class");
				 if(typeof(aa) == "undefined"||aa==""){
					$(this).addClass("bg");
				}else{
					$(this).removeClass("bg");
				}  
				 
			 }); 
			  //给每个li标签添加点击事件
			 $(document).on("click",".score2 li",function(){
				 var aa=$(this).attr("class");
				 if(typeof(aa) == "undefined"||aa==""){
					$(this).addClass("bg");
				}else{
					$(this).removeClass("bg");
				}  
				 
			 });   
			   //标签box的确定事件
			 $("#ok").click(function(e){
				if( $(".labelname").attr("placeholder")=="创建标签"){
					//ajax请求数据增加标签
					 addlabel();
					//取消遮罩层
					hideMask();
				}else{
					//获得当前选中的li标签
				var content=$(".labelname").val();
				if(typeof(content)== "undefined"||content==""){
					bootbox.alert("内容不能为空!");
				}else{
					//ajax请求数据重命名标签
					renamelabel();
					//取消遮罩层
					hideMask();
				     }
				}	
					
				 });
			   
			 //标签box的取消事件
			 $("#cancel").click(function(e){
					 $(".box").css("display","none");
					//取消遮罩层
				    hideMask();
				 });
			//标签boxtie的取消事件
			 $("#cancel1").click(function(e){
					 $(".boxtie").css("display","none");
					//取消遮罩层
					hideMask();
				 });
			//标签boxtie的确定事件
			 $("#ok1").click(function(e){
				 //贴标签
				 var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++)
					{
						  if(document.getElementsByName('ids')[i].checked){
							  var dd=document.getElementsByName('ids')[i].value;
						  	if(str=='') str += document.getElementsByName('ids')[i].value;
						  	else str += ',' + document.getElementsByName('ids')[i].value;
						  }
					}
				 tiebiaoqian(str);
				 //隐藏
				 //取消遮罩层
				// hideMask();
				});
			//标签boxtan的取消事件 
			 $("#cancel2").click(function(e){
				//取消遮罩层
				 hideMask();
				 $(".boxtan").css("display","none");
			 });
			//标签boxtan的确定事件 
			 $("#ok2").click(function(e){
				//操作 
				var labelids='';
				$(".boxtan").find(".bg").each(function(){
					if(labelids=='') labelids += $(this).attr("id");
				  	else labelids += ',' + $(this).attr("id");
				});
				//请求操作
				var url = "<%=basePath%>/fans/setLabelByfocus.do?FOCUS_ID="+FOCUS_ID+"&LABEL_IDS="+labelids;
				$.get(url,function(data){
					nextPage(${page.currentPage});
				});
			 });
			
			
				 
		});
		
		
		
		$(top.hangge());
		
		//检索
		function search(){
			top.jzts();
			$("#Form").submit();
		}
		
		//兼容火狐、IE8   
	    //显示遮罩层    
	    function showMask(){     
	        $("#mask").css("height",$(document).height());     
	        $("#mask").css("width",$(document).width());     
	        $("#mask").show();     
	    }  
	    //隐藏遮罩层  
	    function hideMask(){     
	          
	        $("#mask").hide();     
	    }  
		
		//选择经销商
		function chooseDealer(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="选择服务站";
			 diag.URL = '<%=path%>/tool/dealer_ztree.do?multChoice=true';
			 diag.Width = 860;
			 diag.Height = 510;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					var strDealerName = diag.innerFrame.contentWindow.document.getElementById('strDealerName').value;
					var strDealerId = diag.innerFrame.contentWindow.document.getElementById('strDealerId').value;
					
					$("#strDealerId").val(strDealerId);
					$(".strDealerName").val(strDealerName);
				 }
				diag.close();
			 };
			 diag.show();
		}
		
		//删除标签
		function dellabel(id){
			//ajax请求数据
			$.ajax({
				type: "POST",
				url: '<%=basePath%>/fans/deleteLabel.do?tm='+new Date().getTime(),
		    	data: {"LABEL_ID":id},
				dataType:'json',
				//beforeSend: validateData,
				cache: false,
				error:function(data){
					bootbox.alert("删除标签失败!");
				},
				success: function(data){
					/* if(data.msg=="ok"){
					bootbox.alert("删除标签成功");
					} */
					//刷新页面
					nextPage(${page.currentPage});
				   }
			     });
		}
		
		//增加标签
		function addlabel(){
			var LABEL_NAME=$(".labelname").val(); //标签名
			if(typeof(LABEL_NAME)== "undefined"||LABEL_NAME==""){
				bootbox.alert("内容不能为空!");
			}else{
			//ajax请求数据
			$.ajax({
				type: "POST",
				url: '<%=basePath%>/fans/saveLabel.do?tm='+new Date().getTime(),
		    	data: {"LABEL_NAME":LABEL_NAME},
				dataType:'json',
				//beforeSend: validateData,
				cache: false,
				error:function(data){
					bootbox.alert("添加标签失败!");
				},
				success: function(data){
					if(data.msg=="ok"){
				//	bootbox.alert("添加标签成功");
					//视图上增加li
					var content=$(".labelname").val();
						$(".score").append('<li id="'+data.LABEL_ID+'" >'+content+'</li>');
						//隐藏box
						 $(".box").css("display","none");	
						//刷新页面
							nextPage(${page.currentPage});
					}else{
						bootbox.alert("标签名重复");
						}
					}
			     });
			}
		}
		
		//重命名标签
		function renamelabel(){
			var LABEL_NAME=$(".labelname").val(); //标签名
			//获得选中的标签id
			var LABEL_ID=$(".bg").attr("id");
			//ajax请求数据
			$.ajax({
				type: "POST",
				url: '<%=basePath%>/fans/reNameLabel.do?tm='+new Date().getTime(),
		    	data: {"LABEL_NAME":LABEL_NAME,"LABEL_ID":LABEL_ID},
				dataType:'json',
				//beforeSend: validateData,
				cache: false,
				error:function(data){
					bootbox.alert("重命名标签失败!");
				},
				success: function(data){
					if(data.msg=="ok"){
					//隐藏box
				    $(".box").css("display","none");
					//界面改变
					var content=$(".labelname").val();
					$(".bg").html(content);
					//刷新页面
					nextPage(${page.currentPage});
					}else if(data.msg=="chongming"){
						bootbox.alert("标签名重复");
					}
				   }
			     });
		}
		
		//获取标签列表
		function getFansLabel(Id){
			//ajax请求数据
			$.ajax({
				type: "POST",
				url: '<%=basePath%>/fans/getFansLabel.do?tm='+new Date().getTime(),
		    	data: {"FOCUS_ID":Id},
				dataType:'json',
				//beforeSend: validateData,
				cache: false,
			//	async:false, //同步
				error:function(data){
					bootbox.alert("获取标签失败!");
				},
				success: function(data){
					for(var i=0;i<data.length;i++){
						//var pp=$('#'+data[i].LABEL_ID).html();
						$(".score2 li").each(function(){
					//		$(this).removeClass("bg"); //初始化节点
							if($(this).attr("id")==data[i].LABEL_ID){
								$(this).addClass("bg");
							}
						});
								
					 }
				   }
			     });
		}
		
		//贴标签new
		function sellabelnew(Id){
			$(".boxtie").css("display","block");
			
		}
		
		//贴标签
		function tan(Id){
			//赋值给全局变量
			FOCUS_ID=Id;
			//弹出遮罩层
			showMask();
			$(".boxtan").css("display","block");
			$(".score2 li").removeClass("bg");
			//获取数据
			getFansLabel(Id)
		}
		
		
		//修改
		function cha(Id){
	//		 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="车主详情";
			 diag.URL = '<%=basePath%>/fans/listCustomer.do?FOCUS_ID='+Id;
			 diag.Width = 900;
			 diag.Height = 550;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
		}
		
		function checkEndTime(startTime,endTime){  
		    var start=new Date(startTime.replace("-", "/").replace("-", "/"));   
		    var end=new Date(endTime.replace("-", "/").replace("-", "/"));  
		    if(end<start){  
		        return false;  
		    }  
		    return true;  
		}  
		</script>
		
		<script type="text/javascript">
		
		$(function() {
			
			//下拉框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
		//	$('.date-picker').datepicker();
			
		/* 	$('.omitted').mouseover(function(e){
				var s=$(this).text();
				var temp=s.trim().substring(s.trim().length-3);
	if (temp=="...") {
		$('.jq_tips_box').remove();
		var msg =$(this).find("input",".LABELTEXTa").val();
		if(msg){
			$(this).tips({
				side:3,
				msg: msg,
				bg:'#AE81FF',
				time:0.1,
				x:-10,
				y:10
			});
		}
	}
}); */
//绑定事件
$('.omitted').mouseover(function(e){
	if (this.offsetWidth < this.scrollWidth) {
		$('.jq_tips_box').remove();
		var msg = $(this).html();
		if(msg){
			$(this).tips({
				side:2,
				msg: msg,
				bg:'#AE81FF',
				time:1000
			});
		}
	}
});
$('.omitted').mouseout(function(e){
	if (this.offsetWidth < this.scrollWidth) {
		$('.jq_tips_box').remove();
	}
});

			
			/*   $('#STARTDATE').datepicker({  
				//  pickerPosition: 'top-right',  
		        }).on('changeDate', function (ev) {  
		        	var starttime=$("#STARTDATE").val();
		        	var endtime=$("#ENDDATE").val();
		        	 if(endtime!=""&&!checkEndTime(starttime,endtime)){
		        		$("#ENDDATE").val("");
		        	} 
		        	$("#ENDDATE").datepicker('setStartDate',starttime);
		            $(this).datepicker('hide');  
		        });  */
			/*  
			 $('#ENDDATE').datepicker({  
				 pickerPosition: 'top-right',
		        }).on('changeDate', function (ev) {  
		        	var starttime=$("#STARTDATE").val();
		        	var endtime=$("#ENDDATE").val();
		        	
		        	$("#STARTDATE").datepicker('setEndDate',endtime);
		            $(this).datepicker('hide');  
		        });   */
			 
			
			//设置默认标签被选中
			if($("#labelindex").val()==""||typeof($("#labelindex").val())== "undefined"){
			$(".score li:first").addClass("bg").siblings().removeClass("bg");
			}else{
				var id=$("#labelindex").val();
				$('#'+id).addClass("bg").siblings().removeClass("bg");
			}
			
			//如果为车厂用户，则不能单独贴标签,使onclik失效
			if($("#ORG_TYPE").val()=='10120001'){
				//$("#tielabel").unbind("click");
				$(".checkbox11").css("display","none");
				$(".tielabel").removeAttr("onclick");
				$(".tielabel").css("cursor","default");
			}
			
			//复选框
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
					
			});
			
		});
		
		
		//批量操作
		function makeAll(msg){
		
				if(true) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++)
					{
						  if(document.getElementsByName('ids')[i].checked){
							  var dd=document.getElementsByName('ids')[i].value;
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
						if(msg == '确定要为选中的数据贴标签吗?'){
							
							//弹出遮罩层
							 showMask();
							$(".boxtie").css("display","block");
							

					
						}
					}
				}
			
		  
		}
		
		//批量操作dcrc
		function makeDcrc(msg){
		
				if(true) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++)
					{
						  if(document.getElementsByName('ids')[i].checked){
							  var dd=document.getElementsByName('ids')[i].value;
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
						if(msg == '确定要为选中粉丝绑定DCRC吗?'){
							
							//弹出iframe
							addsomeDcrc(${pd.dealerOrg});
						
							

					
						}
					}
				}
			
		  
		}
		
		//选择DCRC用户
		function addDcrc(dealerId){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="选择DCRC用户";
			 diag.URL = '<%=path%>/fans/toDcrcUser.do?ORG_ID='+dealerId;
			 diag.Width = 700;
			 diag.Height = 400;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin11').style.display == 'none'){
					 var USER_ID = diag.innerFrame.contentWindow.document.getElementById('idInfo').value;
					 var NAME= diag.innerFrame.contentWindow.document.getElementById('username').value;
		             var FOCUS_ID=$("#FOCUS_ID").val(); //关注id
		             //ajax执行请求
		             $.ajax({
		 				type: "POST",
		 				url: '<%=basePath%>/fans/addDcrc.do?tm='+new Date().getTime(),
		 		    	data: {"USER_ID":USER_ID,"FOCUS_ID":FOCUS_ID},
		 				dataType:'json',
		 				//beforeSend: validateData,
		 				cache: false,
		 				error:function(data){
		 					bootbox.alert("操作失败!");
		 				},
		 				success: function(data){
		 					if(data.msg=="ok"){
		 					bootbox.alert("操作成功");
		 					   //操作按钮变化
		 					   $("#dcrc").html("解绑DCRC");
				   	          	
		 						//将dcrc标志与名字显现
							    $("#jiadcrc").css("display","block");
							    $("#dcrcnametext").html(NAME);	
		 					}
		 						
		 					}
		 			     });
						
						
				 }
				diag.close();
			 };
			 diag.show();
		}
		
		//给多个粉丝增加dcrc
			function addsomeDcrc(dealerId){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="选择DCRC用户";
			 diag.URL = '<%=path%>/fans/toDcrcUser.do?ORG_ID='+dealerId;
			 diag.Width = 700;
			 diag.Height = 400;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin11').style.display == 'none'){
					 var USER_ID = diag.innerFrame.contentWindow.document.getElementById('idInfo').value;
					 var NAME= diag.innerFrame.contentWindow.document.getElementById('username').value;
					 var str = '';
						for(var i=0;i < document.getElementsByName('ids').length;i++)
						{
							  if(document.getElementsByName('ids')[i].checked){
								  var dd=document.getElementsByName('ids')[i].value;
							  	if(str=='') str += document.getElementsByName('ids')[i].value;
							  	else str += ',' + document.getElementsByName('ids')[i].value;
							  }
						}
		  
		             //ajax执行请求
		             $.ajax({
		 				type: "POST",
		 				url: '<%=basePath%>/fans/addsomeDcrc.do?tm='+new Date().getTime(),
		 		    	data: {"USER_ID":USER_ID,"IDS":str},
		 				dataType:'json',
		 				async:false,
		 				//beforeSend: validateData,
		 				cache: false,
		 				error:function(data){
		 					bootbox.alert("操作失败!");
		 				},
		 				success: function(data){
		 					nextPage(${page.currentPage});
		 					}
		 			     });
		       
		         		
				 }
				diag.close();
				
			 };
			 diag.show();
		}
		
		//多选贴标签 
		function tiebiaoqian(str){
			//标签id集合
			var labelids='';
			$(".score1").find(".bg").each(function(){
				if(labelids=='') labelids += $(this).attr("id");
			  	else labelids += ',' + $(this).attr("id");
			});
			if(labelids==""){
				bootbox.alert("请至少选择一个标签！");
				return ;
			}
			//隐藏遮罩层
			 $(".boxtie").css("display","none"); 
			//ajax请求数据
			$.ajax({
				type: "POST",
				url: '<%=basePath%>/fans/setLabelToCus.do?tm='+new Date().getTime(),
		    	data: {"FOCUS_IDS":str,"LABEL_IDS":labelids},
				dataType:'json',
				//beforeSend: validateData,
				cache: false,
				success: function(data){									     
							nextPage(${page.currentPage});
					
				}
			});
		}
		
		//导出excel
		function toExcel(){
			//获取当前查询条件-粉丝名
			var FANS_NAME=$("#nav-search-input").val();
			//获取当前查询条件-车型
			var SERIES_CODE=$("#SERIES_CODE").val();
			//获取当前查询条件-关注开始时间
			var STARTDATE=$("#STARTDATE").val();
			//获取当前查询条件-关注结束时间
			var ENDDATE=$("#ENDDATE").val();
			//获取当前查询条件-vin码
			var VIN=$("#VIN").val();
			//获取当前查询条件-车牌号
			var CAR_NO=$("#CAR_NO").val();
			//获取当前查询条件-是否车主
			var JUDGE=$("#JUDGE").val();
			//获取当前查询条件-绑定开始时间
			var BLIDONEDATE=$("#BLIDONEDATE").val();
			//获取当前查询时间-绑定结束时间
			var BLIDTWODATE=$("#BLIDTWODATE").val();
		if(JUDGE=="0"){	
window.location.href="<%=basePath%>/fans/excel.do?"+'FANS_NAME='+FANS_NAME
+'&SERIES_CODE='+SERIES_CODE+'&STARTDATE='+STARTDATE+'&ENDDATE='+ENDDATE+'&VIN='+VIN+'&CAR_NO='+CAR_NO+'&JUDGE='+JUDGE;
		} else if(JUDGE==""){
			window.location.href="<%=basePath%>/fans/excel.do?"+'FANS_NAME='+FANS_NAME
			+'&SERIES_CODE='+SERIES_CODE+'&STARTDATE='+STARTDATE+'&ENDDATE='+ENDDATE+'&VIN='+VIN+'&CAR_NO='+CAR_NO;
		}
		else{
window.location.href="<%=basePath%>/fans/excel.do?"+'FANS_NAME='+FANS_NAME
+'&SERIES_CODE='+SERIES_CODE+'&STARTDATE='+STARTDATE+'&ENDDATE='+ENDDATE+'&VIN='+VIN+'&CAR_NO='+CAR_NO+'&JUDGE='+JUDGE+'&BLIDONEDATE='+BLIDONEDATE+'&BLIDTWODATE='+BLIDTWODATE;	
		}
		
		}
		
	  //根据选择是否为车主而隐藏或者显示绑定时间控件
		function changetype(value){
			//获取当前状态的值
		if(value=="shi"){
			$(".timejudge").css("display","inline");
			$("#BLIDONEDATE").attr("disabled",false);	
			$("#BLIDTWODATE").attr("disabled",false);
		}else{
			$(".timejudge").css("display","none");
			$("#BLIDONEDATE").attr("disabled",true);	
			$("#BLIDTWODATE").attr("disabled",true);
		}	
			
		}
	  
		
		function cleanSelected(id, text){
			$('#'+id+' option').removeAttr("selected");
			var a = $('#'+id+'_chzn').children().eq(0);
			$(a).removeClass("chzn-single");
			$(a).addClass("chzn-single chzn-default");
			$(a).children().eq(0).text(text);
		}

		
		//重置
		function reset1(){
			cleanSelected('SERIES_CODE','车型');
			cleanSelected('JUDGE','是否车主');
			cleanSelected('DCRC_FLAG','是否DCRC');
			$(".nichen").val("");
			$("#STARTDATE").val("");
			$("#ENDDATE").val("");
			$("#VIN").val("");
			$("#CAR_NO").val("");
			$("#BLIDONEDATE").val("");
			$("#BLIDTWODATE").val("");
			$("#strDealerId").val("");
			$(".strDealerName").val("");
			
			//document.getElementById('Form').reset();
		}    
		</script>
		
		<!--创建标签的弹出框  -->
		<div  class="box" style="width:250px; height:100px;display:none;position: fixed;border:1px solid #CCC;top:22%;left:40%;background-color: white;z-index:99;">
		<input autocomplete="off" id="nav-search-input" type="text" name="FANS_NAME" value=""  style="margin-left:25px;margin-top:20px;width:190px;" class="labelname" maxlength="5" />
		<button class="btn btn-mini btn-primary" type="button" style="margin-left:80px;" id="ok">确定</button>
		<button class="btn btn-mini btn-danger" type="button" id="cancel">取消</button>
		</div>
		
		<!--贴标签的弹出框  -->
		<div  class="boxtie" style="width:400px; display:none;position: fixed;border:1px solid #CCC;top:22%;left:30%;background-color: white;z-index:99;">
		<ul class="score1" id="content3" style="background-color: white;margin-top:20px;width:380px;">
			 <c:forEach items="${labelList}" var="label" varStatus="vs">
			     <li id="${label.LABEL_ID}">${label.LABEL_NAME}</li>	
			</c:forEach> 
		    </ul>
		    <div class="clear"></div>
		    <div style="width:100%">
		<button class="btn btn-mini btn-primary" type="button" style="margin-left:160px;margin-bottom:10px;margin-top:10px;" id="ok1">确定</button>
		<button class="btn btn-mini btn-danger" type="button" id="cancel1" style="margin-bottom:10px;margin-top:10px;">取消</button>
		</div>
		</div>
		
		<!--修改单个的弹出层  -->
		<div  class="boxtan" style="width:400px; display:none;position: fixed;border:1px solid #CCC;top:22%;left:30%;background-color: white;z-index:99;padding:0,0;">
		<ul class="score2" id="content3" style="background-color: white;margin-top:20px;width:380px;">
			 <c:forEach items="${labelList}" var="label" varStatus="vs">
			     <li id="${label.LABEL_ID}" >${label.LABEL_NAME}</li>	
			</c:forEach>
		    </ul>
		    <div class="clear"></div>
		    <div style="width:100%">
		<button class="btn btn-mini btn-primary" type="button" style="margin-left:160px;margin-bottom:10px;margin-top:10px;" id="ok2">确定</button>
		<button class="btn btn-mini btn-danger" type="button" id="cancel2" style="margin-bottom:10px;margin-top:10px;">取消</button>
		</div>
		</div>
		
		<!--遮罩层  -->
		<div id="mask" class="mask"></div>
		
	</body>
</html>

