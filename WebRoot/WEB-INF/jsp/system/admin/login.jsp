<%
response.setHeader("Pragma","No-cache");    
response.setHeader("Cache-Control","no-cache");    
response.setDateHeader("Expires", -10);   
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String acgj = request.getParameter("acgj");
	
%>
	
<!DOCTYPE html>
<html lang="en">

<head>
<base href="<%=basePath%>">
<title>${pd.SYSNAME}</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
<link rel="stylesheet" href="static/login/bootstrap.min.css" />
<link rel="stylesheet" href="static/login/css/camera.css" />
<link rel="stylesheet" href="static/login/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="static/login/matrix-login.css" />
<link href="static/login/font-awesome.css" rel="stylesheet" />
<!-- <script type="text/javascript" src="static/js/jquery-1.5.1.min.js"></script> -->
<%@ include file="top.jsp"%>
</head> 
<body >
   <div id="backgroud" style="background-image: url(static/login/images/banner_slide_01.jpg);background-repeat: no-repeat;background-size:cover;" >
	<div
		style="width:100%;text-align: center;margin: 0 auto;position: absolute;">
		<div id="loginbox">
			<form action="" method="post" name="loginForm" id="loginForm">
				<div class="control-group normal_text">
					<h3>
						<img src="static/login/deng.png" alt="Logo"  style="max-width: 30%;"/>
					</h3>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg"><i><img height="37"	src="static/login/user.png" /></i></span>
							<input type="text" name="loginname"  id="loginname" value="" placeholder="请输入用户名" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly"><i><img height="37" src="static/login/suo.png" /></i></span>
							<input type="password" name="password" id="password" placeholder="请输入密码"	value="" />
						</div>
					</div>
				</div>
				<!-- <div style="float:right;padding-right:10%;">
					<div style="float: left;margin-top:3px;margin-right:2px;">
						<font color="white">记住密码</font>
					</div>
					<div style="float: left;">
						<input name="form-field-checkbox" id="saveid" type="checkbox"
							onclick="savePaw();" style="padding-top:0px;" />
					</div>
				</div> -->
				<div class="form-actions" style="background-color: rgba(0, 0, 0, 0.1);">
					<div style="width:86%;padding-left:8%;">

						<div style="float: left;">
							<i><img src="static/login/yan.png" /></i>
						</div>
						<div style="float: left;" class="codediv">
							<input type="text" name="code" id="code" class="login_code"
								style="height:16px; padding-top:0px;" />
						</div>
						<div style="float: left;">
							<i><img style="height:22px;" id="codeImg" alt="点击更换"
								title="点击更换" src="" /></i>
						</div>
						<span class="pull-right"> <a href="javascript:quxiao();" class="btn btn-success">取消</a></span> 
						<span class="pull-right"><a onclick="severCheck();" class="btn btn-info" id="to-recover">登录</a></span>
					</div>
		
				</div>
			</form>
			<div class="controls">
				<div class="main_input_box">
					<font style="color: black;background-color: "><span id="nameerr">Copyright ©重庆长安民生物流股份有限公司</span></font>
				</div>
				<div class="main_input_box" ><!-- #ff848 -->
					<font style="color: #28b779;" size="2"><b>建议使用谷歌浏览器访问本系统，其它浏览器可能导致某些功能无法正常使用。</b>
					</font>
					<br>
					<font style="color: black;">
					<a style="text-decoration:underline;color:#28b779;font-weight:bold;"  href="http://sw.bos.baidu.com/sw-search-sp/software/7164c4c6bc6e0/ChromeStandalone_58.0.3029.110_Setup.exe" target="_blank">谷歌浏览器Win7以上版本点此处下载</a>
					<br>
					<a style="text-decoration:underline;color:#28b779;font-weight:bold;"  href="http://219.153.34.151/tech.down.sina.com.cn/20160408/93ff8f5e/49.0.2623.112_chrome_installer.exe?fn=&ssig=vpNngUjdxe&Expires=1496731095&KID=sae,230kw3wk15&ip=1496651895,14.106.125.152&corp=1" target="_blank">谷歌浏览器XP版本点此处下载</a>
					</font>
				</div>
			</div>
		</div>
	</div>
	<div id="templatemo_banner_slide" class="container_wapper">
		<div class="camera_wrap camera_emboss"  id="camera_slide">
			<div data-src="static/login/images/banner_slide_01.png"></div>
		</div>
	</div>
</div>
	<script type="text/javascript">
	/* 设置登录页的背景图片 */
	$("#backgroud").css("height",$(window).outerHeight()).css("width",$(window).outerWidth());
// 	$("#backgroud").css("height",$(document).height()).css("width",$(document).width());
	/* 设置登录页的背景图片 */
	/**
	 * 弹出网页信息
	 * str 字符串的html代码
	 * */
	function openDiv() {
		 var diag = new Dialog();
		 diag.Drag=true;
		 diag.Title ="选择职位";
		 diag.URL = '<%=basePath%>main/post/postList.do';
		 diag.Width = 550;
		 diag.Height = 230;
		 diag.CancelEvent = function(){
	        if(diag.innerFrame.contentWindow.document.getElementById('postListDiv').style.display == 'none'){
	        	var postId=$("#formHiden", diag.innerFrame.contentWindow.document).val();
				window.location.href="<%=basePath%>main/post/admin.do?changeMenu="+postId;
				 diag.close();
			 } else {
				 diag.close();
			 }
		 }
		 diag.show();
	}
	
//服务器校验
function severCheck(){
			if(check()){
				var loginname = $("#loginname").val();
				var password = $("#password").val();
				var code = "qq313596790fh"+loginname+",fh,"+password+"QQ978336446fh"+",fh,"+$("#code").val();
				$.ajax({
					type: "POST",
					url: 'login_login',
			    	data: {KEYDATA:code,tm:new Date().getTime()},
					dataType:'json',
					cache: false,
					success: function(data){
						if("success" == data.result){
							window.location.href="<%=basePath%>main/post/admin.do?changeMenu="+data.postId;
						} else if("hasMorePost" == data.result){
							openDiv();
						}	else if("usererror" == data.result){
							$("#loginname").tips({
								side : 1,
								msg : "用户名或密码有误",
								bg : '#FF5080',
								time : 15
							});
							$("#loginname").focus();
						}else if("codeerror" == data.result){
							$("#code").tips({
								side : 1,
								msg : "验证码输入有误",
								bg : '#FF5080',
								time : 15
							});
							$("#code").focus();
						}else{
							$("#loginname").tips({
								side : 1,
								msg : "缺少参数",
								bg : '#FF5080',
								time : 15
							});
							$("#loginname").focus();
						}
					}
				});
			}
		}
	
		$(document).ready(function() {
			changeCode();
			$("#codeImg").bind("click", changeCode);
		});

		$(document).keyup(function(event) {
			if (event.keyCode == 13) {
				$("#to-recover").trigger("click");
			}
		});
		window.onresize = function(){
			$("#backgroud").css("height",$(window).outerHeight()).css("width",$(window).outerWidth());
		}

		function genTimestamp() {
			var time = new Date();
			return time.getTime();
		}

		function changeCode() {
			$("#codeImg").attr("src", "code.do?t=" + genTimestamp());
		}

		//客户端校验
		function check() {

			if ($("#loginname").val() == "") {

				$("#loginname").tips({
					side : 2,
					msg : '用户名不得为空',
					bg : '#AE81FF',
					time : 3
				});
				$("#loginname").focus();
				return false;
			} else {
				$("#loginname").val(jQuery.trim($('#loginname').val()));
			}
			if ($("#password").val() == "") {

				$("#password").tips({
					side : 2,
					msg : '密码不得为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#password").focus();
				return false;
			}
			if ($("#code").val() == "") {

				$("#code").tips({
					side : 1,
					msg : '验证码不得为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#code").focus();
				return false;
			}

			$("#loginbox").tips({
				side : 1,
				msg : '正在登录 , 请稍后 ...',
				bg : '#68B500',
				time : 10
			});

			return true;
		}

		function savePaw() {
			if (!$("#saveid").attr("checked")) {
				$.cookie('loginname', '', {
					expires : -1
				});
				$.cookie('password', '', {
					expires : -1
				});
				$("#loginname").val('');
				$("#password").val('');
			}
		}

		function saveCookie() {
			if ($("#saveid").attr("checked")) {
				$.cookie('loginname', $("#loginname").val(), {
					expires : 7
				});
				$.cookie('password', $("#password").val(), {
					expires : 7
				});
			}
		}
		function quxiao() {
			$("#loginname").val('');
			$("#password").val('');
		}
		/**
		jQuery(function() {
			var loginname = $.cookie('loginname');
			var password = $.cookie('password');
			if (typeof(loginname) != "undefined"
					&& typeof(password) != "undefined") {
				$("#loginname").val(loginname);
				$("#password").val(password);
				$("#saveid").attr("checked", true);
				$("#code").focus();
			}
		});
		**/
	</script>
	<script>
		//TOCMAT重启之后 点击左侧列表跳转登录首页 
		if (window != top) {
			top.location.href = location.href;
		}
	</script>
	
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-1.7.2.js"></script>
	<!-- 下面4个是图片滑动 -->
	<!-- <script src="static/login/js/jquery.easing.1.3.js"></script>
	<script src="static/login/js/jquery.mobile.customized.min.js"></script> 
	<script src="static/login/js/camera.min.js"></script>
	<script src="static/login/js/templatemo_script.js"></script> -->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
</body>

</html>