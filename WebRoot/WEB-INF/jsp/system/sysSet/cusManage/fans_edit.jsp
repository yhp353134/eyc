<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
			<script type="text/javascript" src="<%=path%>/static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<style type="text/css">
		.omitted{
	overflow: hidden;
	text-overflow: ellipsis; 
	white-space: nowrap; 
}
	ul,li{list-style:none; }
    #nav li{display:inline-block;margin:0 5px 5px 5px;background:url("<%=path%>/static/images/gougou.jpg") no-repeat bottom right;border:1px #ED0281 solid !important;padding:0 10px; line-height:24px;font-size:12px;float:left;}
    #nav li.h_nav_over{background:green;color:#fff;}
    #nav li.h_nav_over a{color:#fff;}
    a{text-decoration:none;}
}
	</style>
<script type="text/javascript">
	
	
</script>
	</head>
<body>
        <!--隐藏域  -->
       <input type="hidden" name="FANS_STATUSTEXT" id="FANS_STATUSTEXT" value="${pd.FANS_STATUSTEXT}" > 
       <input type="hidden" name="FOCUS_ID" id="FOCUS_ID" value="${pd.FOCUS_ID}" > 
       <input type="hidden" name="IDENTI_ADDVTEXT" id="IDENTI_ADDVTEXT" value="${pd.IDENTI_ADDVTEXT}" >
       <input type="hidden" name="DCRC_FLAG" id="DCRC_FLAG" value="${pd.DCRC_FLAG}" > 
       <!--主界面  -->
		<div id="zhongxin">
		<div class="widget-main" style="border: 1px solid #CCC;">
		  <!-- <div class="widget-header widget-header-blue widget-header-flat wi1dget-header-large">
				<h4 class="lighter" style="color: #669fc7;">基本资料</h4>
				
		   </div>   -->
		       <div id="info" style="height:110px;border: 1px solid #CCC;background: #f7f7f7;">  
		         <div id="d1" style="width:250px;float:left">
		           <div id="touxiang" style="width:130px;height:110px;float:left;line-height:95px;vertical-align:middle;text-align: center;"><img  class="touxiangImg" style="vertical-align:middle;width:73px;height:73px;" src="${pd.FANS_PIC}"/>
		                           <p style="line-height:5px;height:5px;">${pd.FANS_NAME}</p>  
		           </div>
		           <div id="neiron" style="width:120px;float:right">
		             <div id="teldate" style="height:55px;  position:relative;">
		             <c:choose>
                            <c:when test="${pd.DCRC_FLAG != '否' }">  
                        <div id="jiadcrc" style="width:100%;height:30px;position:relative;"> <img  class="vImg" style="width:16px;height:16px;margin-left:5px;margin-top:10px;" src="<%=path%>/static/images/dcrc1.png"/>
		                <span style="position:absolute;top:30%;left:29%;" id="dcrcnametext">${pd.NAME}</span> </div>                                                                                         
                            </c:when>
                            <c:otherwise>                                           
                        <div id="jiadcrc" style="width:100%;height:30px;display:none;position:relative;"> <img  class="vImg" style="width:16px;height:16px;margin-left:5px;margin-top:10px;" src="<%=path%>/static/images/dcrc1.png"/>
		                <span style="position:absolute;top:30%;left:29%;" id="dcrcnametext"></span> </div>                                                                             
                             </c:otherwise>
                      </c:choose>  
		             <div id="zz" style=" position:absolute;bottom:4px;height:18px;">
		             <img  class="telImg" style="vertical-align:middle;width:21px;height:18px;margin-left:2px;" src="<%=path%>/static/images/u27.png"/>
		             </div>
		              <span style="position:absolute;top:60%;left:29%" id=""> ${pd.PHONE}</span>
		             </div>
		             <div id="car" style="height:55px; position:relative;">
		             <img  class="carImg" style="vertical-align:middle;width:21px;height:18px;margin-left:2px;" src="<%=path%>/static/images/u31.png"/>
		              <span style="position:absolute;top:5%;left:30%" id="">${pd.SERIES_NAME}</span>
		              <c:choose>
                            <c:when test="${pd.IDENTI_ADDVTEXT != '未加V' }">  
                        <div id="jiav" style="width:100%;height:37px;position:relative;"> <img  class="vImg" style="width:16px;height:16px;margin-left:5px;margin-top:10px;" src="<%=path%>/static/images/jiav.png"/>
		                <span style="position:absolute;top:25%;left:30%;" id="vnametext">${pd.IDENTI_NAME}</span> </div>                                                                                         
                            </c:when>
                            <c:otherwise>                                           
                        <div id="jiav" style="width:100%;height:37px;display:none;position:relative;"> <img  class="vImg" style="width:16px;height:16px;margin-left:5px;margin-top:10px;" src="<%=path%>/static/images/jiav.png"/>
		                <span style="position:absolute;top:25%;left:30%;" id="vnametext"></span> </div>                                                                             
                             </c:otherwise>
                      </c:choose>                
		              </div>
		           </div>
		         </div>
		         <!--标签div  -->
		          <div id="nav" style="width:320px;float:left;height:110px;text-align: center; overflow:hidden;">
			           <ul class="score" id="content3" style="background-color: white;padding:auto;margin-top:5px;">
			            <c:forEach items="${labelList}" var="label" varStatus="vs">
			           <li id="${label.LABEL_ID}">${label.LABEL_NAME}</li>	
			           </c:forEach> 
			          <!--   <li id="">快乐</li>
			            <li id="">忙碌</li>
			            <li id="">工作狂</li> -->
		               </ul>
		          </div>
		          <!--关注状态  -->
		          <div id="status" style="vertical-align:middle;text-align: center;width:100px;height:110px; line-height:110px;float:left;">
		          <p><font  size="3" color="red"><strong>${pd.FANS_STATUSTEXT}</strong></font></p>  
		          </div>
		            <c:if test="${type.ORG_TYPE == '10120002' }">   
		          <!--加v操作与DCRC操作  -->
		          <div id="caozuo" style="float:right;width:180px;height:110px;">
		            <div style="height:55px;text-align: center;">
		             <button class="btn btn-mini btn-primary" type="button"  id="v" style="margin-top:20px;"></button>
		            </div>
		            <div style="height:55px;text-align: center;">
		             <button class="btn btn-mini btn-primary" type="button"  id="dcrc" ></button>
		            </div> 
		            </c:if>
		          </div>
		       </div>
		       <!--标题  -->
		       <div id="" style="height:20px;color: #669fc7;"><h4 class="lighter">&nbsp车主车辆信息</h4></div>
		       <!--分割线  -->
		        <hr style="margin:10px 0;"  />
		        <!--车主车辆信息  -->
		       <div id="carinfo" style="">
		        <table id="table_report" class="table table-striped table-bordered table-hover">
		        <tr>
		        <td style="width:70px;text-align: right;padding-top: 13px;">车主姓名:</td>
		        <td style="padding-top: 13px;">${pd.CUSTOMER_NAME} </td>
		        <td style="width:70px;text-align: right;padding-top: 13px;">联系电话:</td>
		        <td style="padding-top: 13px;">${pd.PHONE }</td>
		        </tr>
		        <tr>
		        <td style="width:70px;text-align: right;padding-top: 13px;">性别:</td>
		        <td style="padding-top: 13px;">${pd.SEX } </td>
		        <td style="width:70px;text-align: right;padding-top: 13px;">电子邮件:</td>
		        <td style="padding-top: 13px;">${pd.EMAIL} </td>
		        </tr>
		         <tr>
		        <td style="width:70px;text-align: right;padding-top: 13px;">车型:</td>
		        <td style="padding-top: 13px;">${pd.SERIES_NAME} </td>
		        <td style="width:70px;text-align: right;padding-top: 13px;">购车日期:</td>
		        <td style="padding-top: 13px;">${pd.BUY_DATE} </td>
		        </tr>
		         <tr>
		        <td style="width:70px;text-align: right;padding-top: 13px;">车牌号码:</td>
		        <td style="padding-top: 13px;">${pd.CAR_NO} </td>
		        <td style="width:70px;text-align: right;padding-top: 13px;">车架号:</td>
		        <td style="padding-top: 13px;">${pd.VIN} </td>
		        </tr>
		        </table>
		       </div>
		       
		     <!--标题  -->
		       <div id="" style="height:20px;color: #669fc7;"><h4 class="lighter">&nbsp服务预约</h4></div>
		       <!--分割线  -->
		        <hr style="margin:10px 0;"  />
		     <!--服务预约  -->
		      <div id="serorder" style="">
		         <table id="table_report" class="table table-striped table-bordered table-hover"  >
		         	<thead>
					<tr>
						<th >预约申请时间</th>
						<th >预约到店时间</th>
						<th >预约门店</th>
						<th >预约人</th>
						<th >预约电话</th>
						<th >预约状态</th>
					</tr>
				</thead>
				<tbody>
		          <c:forEach items="${AppointList}" var="Appoint" varStatus="vs">
		        <tr>
		        <td style="padding-top: 13px;" >${Appoint.CREATE_DATE}</td>
		        <td style="padding-top: 13px;width:170px;" >${Appoint.YEAR}-${Appoint.MONTH}-${Appoint.DAY} ${Appoint.TIME} </td>
		        <td style="padding-top: 13px;" >${Appoint.DEALER_NAME } </td>
		        <td style="padding-top: 13px;" >${Appoint.CUS_NAME } </td>
		        <td style="padding-top: 13px;" >${Appoint.CUS_PHONE_NO } </td>
		        <td style="padding-top: 13px;">${Appoint.STATUS } </td>
		        </tr>
		         </c:forEach> 
		         </tbody>
		        </table>
		      </div>
		      
		     <!--标题  -->
		       <div id="" style="height:20px;color: #669fc7;"><h4 class="lighter">&nbsp维修记录</h4></div>
		       <!--分割线  -->
		        <hr style="margin:10px 0;"  />
		      <!--维修记录  -->
		      <div id="fit" style="">
		         <table id="table_report" class="table table-striped table-bordered table-hover">
		         	<thead>
					<tr>
						<th >维修日期</th>
						<th >维修类型</th>
						<th >车牌号</th>
						<th >经销商</th>
						<th >工单类型</th>
					</tr>
				</thead>
				<tbody>
		            <c:forEach items="${FitList}" var="Fit" varStatus="vs">
		        <tr>
		        <td style="padding-top: 13px;">${Fit.FIT_DATE} </td>
		        <td style="padding-top: 13px;">${Fit.FIT_TYPE} </td>
		        <td style="padding-top: 13px;">${Fit.CAR_NO} </td>
		        <td style="padding-top: 13px;">${Fit.DEALER_NAME} </td>
		        <td style="padding-top: 13px;">${Fit.STATUS} </td>
		        </tr>
		        </c:forEach> 
		         </tbody>
		        </table>
		      </div> 
		       
		     <!--标题  -->
		       <div id="" style="height:20px;color: #669fc7;"><h4 class="lighter">&nbsp服务活动</h4></div>
		       <!--分割线  -->
		        <hr style="margin:10px 0;"  />
		      <!--服务活动  -->
		       <div id="huodon" style="">
		         <table id="table_report" class="table table-striped table-bordered table-hover">
		           <c:forEach items="${ActiviList}" var="Activi" varStatus="vs">
		        <tr>
		        <td style="width:70px;text-align: right;padding-top: 13px;">参与时间:</td>
		        <td style="padding-top: 13px;">${Activi.JOIN_DATE} </td>
		        <td style="width:70px;text-align: right;padding-top: 13px;">活动名称:</td>
		        <td style="padding-top: 13px;">${Activi.ACTIVITY_TITLE} </td>
		        <td style="width:70px;text-align: right;padding-top: 13px;">活动类型:</td>
		        <td style="padding-top: 13px;">${Activi.ACTIVITY_TYPE} </td>
		        </tr>
		         </c:forEach> 
		        </table>
		      </div> 
		      
		      <!--标题  -->
		       <div id="" style="height:20px;color: #669fc7;"><h4 class="lighter">&nbsp调查问卷</h4></div>
		       <!--分割线  -->
		        <hr style="margin:10px 0;"  />
		      <!--调查问卷 -->
		      <div id="diaocha" style="">
		         <table id="table_report" class="table table-striped table-bordered table-hover">
		          <c:forEach items="${QuestionnaireList}" var="Questionnaire" varStatus="vs">
		        <tr>
		        <td style="width:70px;text-align: right;padding-top: 13px;">参与时间:</td>
		        <td style="padding-top: 13px;">${Questionnaire.UP_DATE } </td>
		        <td style="width:70px;text-align: right;padding-top: 13px;">问卷名称:</td>
		        <td style="padding-top: 13px;">${Questionnaire.QUEST_NAME} </td>
		        <td style="width:70px;text-align: right;padding-top: 13px;">问卷类型:</td>
		        <td style="padding-top: 13px;">${Questionnaire.QUEST_TYPE} </td>
		        </tr>
		         </c:forEach> 
		        </table>
		      </div> 
		      
		 </div>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="<%=path%>/static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		

	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='<%=path%>/static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="<%=path%>/static/js/bootstrap.min.js"></script>
		<script src="<%=path%>/static/js/ace-elements.min.js"></script>
		<script src="<%=path%>/static/js/ace.min.js"></script>
		<script type="text/javascript" src="<%=path%>/static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="<%=path%>/static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript">
//		$(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
	
			// 绑定事件
			$('.omitted').mouseover(function(e){
				if (this.offsetWidth < this.scrollWidth) {
					$('.jq_tips_box').remove();
					var msg = $(this).text();
					if(msg){
						$(this).tips({
							side:3,
							msg: msg,
							bg:'#AE81FF',
							time:10,
							x:-10,
							y:10
						});
					}
				}
			});
			
			//判断粉丝的状态，调整button显示的值
			if($("#IDENTI_ADDVTEXT").val()=="未加V"){
				$("#v").html("加V认证");
			}else{
				$("#v").html("取消V认证");
			}
			//判断粉丝的是否为dcrc，调整button显示的值
			if($("#DCRC_FLAG").val()=="否"){
				$("#dcrc").html("设置成为DCRC");
			}else{
				$("#dcrc").html("取消DCRC身份");
			}
			
			//v操作增加点击事件
			$("#v").click(function(e){
				//判断当前按钮状态
				if($("#v").html()=="加V认证"){
				   $(".box").css("display","block");
				}else{
					var FOCUS_ID=$("#FOCUS_ID").val(); //关注id
					bootbox.confirm("确定要取消V认证吗?", function(result) {
						if(result) {
			//				top.jzts();
							var url = "<%=basePath%>/fans/cancelV.do?FOCUS_ID="+FOCUS_ID;
							$.get(url,function(data){
								if(data.msg=="ok"){
									$("#v").html("加V认证");	
								   	$("#jiav").css("display","none");
								    bootbox.alert("操作成功!");
								}
							});
						}
					});
				}	
			 });
			//为确定按钮增加点击事件
			$("#ok").click(function(e){
				var content=$(".name").val(); //输入的V名
				if(typeof(content)== "undefined"||content==""){
					bootbox.alert("内容不能为空!");
				}else{
					//ajax请求数据
					addVname();
					$("#v").html("取消V认证");	
					$("#jiav").css("display","block");
					$("#vnametext").html(content);
				}
				//初始化input
				$(".name").val("");
			 });
			//为取消按钮增加点击事件
			$("#cancel").click(function(e){
				$(".box").css("display","none");
				//初始化input
				$(".name").val("");
			 });
		
			
			//dcrc操作增加点击事件
			$("#dcrc").click(function(e){
				if($("#dcrc").html()=="设置成为DCRC"){
					addDcrc(${type.dealerOrg});	
				}
				else{
				var FOCUS_ID=$("#FOCUS_ID").val(); //关注id
				bootbox.confirm("确定要取消DCRC身份关联吗?", function(result) {
					if(result) {
		//				top.jzts();
						var url = "<%=basePath%>/fans/cancelDcrc.do?FOCUS_ID="+FOCUS_ID;
						$.get(url,function(data){
							if(data.msg=="ok"){
								$("#dcrc").html("设置成为DCRC");
							   	$("#jiadcrc").css("display","none");
							bootbox.alert("操作成功!");
							}
						});
					}
				});
				}
			 });
			
		});
		
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
		 					   $("#dcrc").html("取消DCRC身份");
				   	          	
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
		
		//增加V名
		function addVname(){
			var IDENTI_NAME=$(".name").val(); //v名
			var FOCUS_ID=$("#FOCUS_ID").val(); //关注id
			//ajax请求数据
			$.ajax({
				type: "POST",
				url: '<%=basePath%>/fans/addV.do?tm='+new Date().getTime(),
		    	data: {"IDENTI_NAME":IDENTI_NAME,"FOCUS_ID":FOCUS_ID},
				dataType:'json',
				//beforeSend: validateData,
				cache: false,
				error:function(data){
					bootbox.alert("加V失败!");
				},
				success: function(data){
					if(data.msg=="ok"){
					bootbox.alert("加V成功");
						//隐藏box
						 $(".box").css("display","none");	
					}
						
					}
			     });
		}
		
		
		</script>
		
		<!--加v认证的弹出框  -->
		<div  class="box" style="width:300px; height:150px;display:none;position: fixed;border:1px solid #CCC;top:16%;left:38%;background-color: white;z-index:99;">
		<p style="margin-top:25px;margin-left:12px;"><span style="color:red;font-weight:bold;">提示:</span>加V认证后,在粉丝圈发帖时将显示认证名称</p>
		<input autocomplete="off" id="nav-search-input" type="text" name="FANS_NAME" value=""  style="margin-left:55px;margin-top:10px;width:190px;" class="name" placeholder="V名称" maxlength="5"/>
		<button class="btn btn-mini btn-primary" type="button" style="margin-left:110px;" id="ok">确定</button>
		<button class="btn btn-mini btn-danger" type="button" id="cancel">取消</button>
		</div>
</body>
</html>