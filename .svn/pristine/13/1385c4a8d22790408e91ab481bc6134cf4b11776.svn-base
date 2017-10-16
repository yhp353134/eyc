<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=path%>">
		<jsp:include page="${path}/WEB-INF/jsp/system/admin/top.jsp" />
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
		<script type="text/javascript" src="<%=path %>/weixin/js/ajaxfileupload.js"></script>
		<script type="text/javascript" src="<%=path %>/static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		
<script type="text/javascript">
	//保存
	function save(){
			if($("#pic_file").val()==""&&$("#JOBTITLE").val()==""){
				bootbox.alert("请至少修改一项再保存！");
				return;
			}
			$("#zhongxin").hide();
			$("#zhongxin2").show();
			var CONS_ID =$("#CONS_ID").val();
			var JOBTITLE = $("#JOBTITLE").val();
			var SORT = $("#SORT").val();
			var url = '<%=path%>/consultant/saveConsultant.do';
			url  += encodeURI('?JOBTITLE='+JOBTITLE+'&CONS_ID='+CONS_ID+'&SORT='+SORT+'&tm='+new Date().getTime());
		     var formData = new FormData();
			var name = $("input").val();
			formData.append("file",$("#pic_file")[0].files[0]);
			formData.append("JOBTITLE",JOBTITLE);
			$.ajax({
				url : url, 
				type : 'POST', 
				data : formData, 
				// 告诉jQuery不要去处理发送的数据
				processData : false, 
				// 告诉jQuery不要去设置Content-Type请求头
				contentType : false,
				beforeSend:function(){
// 				console.log("正在进行，请稍候");
				},
				success : function(json) { 
// 					console.log("success");
// 					console.log(json.RESULT);
					 if(json.RESULT=="MAX_SIZE"){
			        		bootbox.alert("您选择的图片大于2M，请重新选择!",function(){
			        			$("#zhongxin2").hide();
							    $("#zhongxin").show();
			        		});
						}else if(json.RESULT=="SUCCESS"){
			        		bootbox.alert("操作成功!",function(){
				        		top.Dialog.close();
			        		});
						}else {
			        		bootbox.alert("操作成功!",function(){
			        			$("#zhongxin2").hide();
							    $("#zhongxin").show();
			        		});
						}
				}, 
				error : function(responseStr) { 
// 				console.log("error");
				} 
				}); 
			
			/* $.ajaxFileUpload
				(

				    {
				    	url: url, //用于文件上传的服务器端请求地址
//				        secureuri: false,           //一般设置为false
				        fileElementId: $("#pic_file").attr("id"), //文件上传控件的id属性  <input type="file" id="file" name="file" /> 注意，这里一定要有name值   
				        dataType: 'JSON',//返回值类型 一般设置为json
				         //$("form").serialize(),表单序列化。指把所有元素的ID，NAME 等全部发过去
				        data:$("#Form").serialize(),
						contentType: false,      
			            cache: false, 
			            type: 'POST',
			            processData:false,
				        complete: function () {//只要完成即执行，最后执行
				        },
				        success: function (json)  //服务器成功响应处理函数
				        {   
				        	 if(json.RESULT=="MAX_SIZE"){
				        		bootbox.alert("您选择的图片大于2M，请重新选择!",function(){
				        			$("#zhongxin2").hide();
								    $("#zhongxin").show();
				        		});
							}else if(json.RESULT=="SUCCESS"){
				        		bootbox.alert("操作成功!",function(){
     				        		top.Dialog.close();
				        		});
							}else {
				        		bootbox.alert("操作成功!",function(){
				        			$("#zhongxin2").hide();
								    $("#zhongxin").show();
				        		});
							}
				        },error: function (data, status, e)//服务器响应失败处理函数
	                    {
// 				        	alert(e);
// 				        	alert(data.RESULT);
// 				        	alert(status);
				        	bootbox.alert("上传失败---！",function(){
				        		$("#zhongxin2").hide();
							    $("#zhongxin").show();
				        	});
	                    }
				    }
				) */
// 		$("#Form").submit();
		
	}
	
</script>
	</head>
<body>
	<form action="<%=path%>/consultant/saveConsultant.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
		<input type="hidden" name="CONS_ID" id="CONS_ID" value="${pd.CONS_ID}"/>
		<div id="zhongxin">
		<table class="table table-striped table-bordered table-hover">
		    <tr>
		       <td style="text-align: right;">姓名：</td>
		       <td>${pd.NAME }</td>
		       <td style="text-align: right;">身份证号：</td>
		       <td>${pd.CARD_NO }</td>
		    </tr>
		    <tr>
		       <td style="text-align: right;">性别：</td>
		       <td>${pd.SEX_NAME }</td>
		       <td style="text-align: right;">状态：</td>
		       <td>${pd.STATUS_NAME }</td>
		    </tr>
		    <tr>
		       <td style="text-align: right;">原职称：</td>
		       <td>${pd.JOBTITLE }</td>
		       <td style="text-align: right;">电话：</td>
		       <td>${pd.TEL}</td>
		    </tr>
		    <tr>
		       <td style="text-align: right;">排序号：</td>
		       <td colspan="3"><input type="text" id="SORT" name="SORT" placeholder="请输入排序号" style="width: 450px;"></td>
		    </tr>
		    <tr>
		       <td style="text-align: right;">新职称：</td>
		       <td colspan="3"><input type="text" id="JOBTITLE" name="JOBTITLE" placeholder="请输入新职称" style="width: 450px;" onpropertychange="titleChange(this);"  onkeydown="titleChange(this);" onkeyup="titleChange(this);" onMouseDown="titleChange(this);"  onMouseup="titleChange(this);" ></td>
		    </tr>
			<tr>
			    <td style="text-align: right;">原头像：</td>
				<td colspan="3"><a href="${pd.HEAD_URL }" target="view_window"><img alt="头像" src="${pd.HEAD_URL }" style="width: 60px;height: 60px;"></a></td>
			</tr>
			<tr>
			    <td style="text-align: right;">新图片：</td>
				<td colspan="3"><input type="file" name="pic_file" id="pic_file"  style="position:static"  accept="image/png,image/jpeg,image/x-png,image/x-ms-bmp" /></td>
<!-- 				<td></td> -->
<!-- 				<td></td> -->
			</tr>
			<tr>
				<td style="text-align: center;" colspan="4">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="<%=path%>/static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form>
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='<%=path%>/static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="<%=path%>/static/js/bootstrap.min.js"></script>
		<script src="<%=path%>/static/js/ace-elements.min.js"></script>
		<script src="<%=path%>/static/js/ace.min.js"></script>
		<script type="text/javascript" src="<%=path%>/static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="<%=path%>/static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		$('#pic_file').ace_file_input({
			no_file:'没有文件 ...',
			btn_choose:'选择',
			btn_change:'选择',
			droppable:false,
			onchange:null,
			thumbnail:false, //| true | large
// 			whitelist:'xls'
//				whitelist:'png|jpg|jpeg'
			//blacklist:'exe|php'
//				onchange:''
			//
		});
		
		 function titleChange(obj){
// 			   var id = $(obj).attr("id");
// 			   id = id.substr(id.length-1,1);
			   var val = $(obj).val().length;
			   if(val<=10){
			     $(obj).next().remove();
				 $(obj).after("<span style='color:green;margin-left:5px;' >"+val+"/10<span>")
			   }else{
				   var va = $(obj).val().substr(0,10);
				   $(obj).val(va);
				   $(obj).nextAll().remove();
// 				   $(obj).after().html("还可以输入" + (10-val) + "字");
				   $(obj).after("<span style='color:green;margin-left:5px;' >"+va.length+"/10<span>")
			   }
// 			   var nWordNum = $("#textContent").val().length;
// 				if(nWordNum<=600){
// 					$("#wordNum").html("还可以输入" + (600-nWordNum) + "字");
// 				}else{
// 					$("#wordNum").html("已超出<span style='color:red;'>" + (nWordNum-600) + "</span>字");
// 				}
		   }
		
		</script>
</body>
</html>