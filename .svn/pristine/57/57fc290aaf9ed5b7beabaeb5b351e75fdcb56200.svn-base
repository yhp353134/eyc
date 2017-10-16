<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- jsp文件头和头部 -->
<%@ include file="top.jsp"%>

<style type="text/css">
	.commitopacity{position:absolute; width:100%; height:100px; background:#7f7f7f; filter:alpha(opacity=50); -moz-opacity:0.8; -khtml-opacity: 0.5; opacity: 0.5; top:0px; z-index:99999;}
	#sidebar{
	    background-color: #F9F9F9;
	    overflow-y: auto;	    
	    overflow-x: hidden;    
	}
	#sidebar.menu-min{
	    /*width: 100px*/
	}
	.menudiv{
	    position: absolute;
	    z-index: 999;
	    display: none;
	}
	.menudiv>ul{
	    border: 1px solid #e5e5e5;
	    margin: 0;
	    padding: 0;
	    background-color: #FFF;
	    border-top: 1px solid #e5e5e5;	    
	}
	.menudiv>ul>li{
	    border-top: 1px solid #e5e5e5;	 
	    background-color: #F9F9F9;
	    list-style-type:none;
	    position: relative;
	    margin-left: 0;    
	}
	.menudiv>ul>li>a{
	    padding-left: 37px;
	    position: relative;
	    display: block;
	    color: #616161;
	    padding: 7px 8px 8px 10px;
	    margin: 0    
	}		
</style>

<script>
$(function(){
	$("#sidebar").find('.nav-list>li').on('hover',function(){
		if($("#sidebar").hasClass('menu-min')){
			var menus = $(this).find('.submenu');
			var num = menus.find('li').length;
			$(".menudiv>.level2menu").html(menus.html());
			var screenHeight = document.documentElement.clientHeight-51;
			var y = this.offsetTop+44;
			var menuHeight = num*35;
			if(y+menuHeight>screenHeight){
				y = screenHeight-menuHeight+51;
			}
			$(".menudiv").css('display','block').css('top',y).css('left','41px');			
		};
	});
	$("#main-content").hover(function(){
		$(".menudiv").css('display','none');
	});
	$("#sidebar-collapse").hover(function(){
		$(".menudiv").css('display','none');
	});	
});
</script>

</head>
<body onload="isTops()">
<div class="menudiv"><ul class="level2menu"></ul></div>

	<!-- 页面顶部¨ -->
	<%@ include file="head.jsp"%>

	<div class="container-fluid" id="main-container">
		<a href="#" id="menu-toggler"><span></span></a>
		<!-- menu toggler -->

		<!-- 左侧菜单 -->
		<%@ include file="left.jsp"%>

		<div id="main-content" class="clearfix">

			<div id="jzts" style="display:none; width:100%; position:fixed; z-index:99999999;">
			<div class="commitopacity" id="bkbgjz"></div>
			<div style="padding-left: 70%;padding-top: 1px;">
				<div style="float: left;margin-top: 3px;"><img src="static/images/loadingi.gif" /> </div>
				<div style="margin-top: -5px;"><h4 class="lighter block red">&nbsp;加载中 ...</h4></div>
			</div>
			</div>
			<div>
				<iframe name="mainFrame" id="mainFrame" frameborder="0" src="tab.do" style="margin:0 auto;width:100%;height:100%;"></iframe>
			</div>
			<%-- <!-- 换肤 -->
			<div id="ace-settings-container">
				<div class="btn btn-app btn-mini btn-warning" id="ace-settings-btn">
					<i class="icon-cog"></i>
				</div>
				<div id="ace-settings-box">
					<div>
						<div class="pull-left">
							<select id="skin-colorpicker" class="hidden">
								<option data-class="default" value="#438EB9"
									<c:if test="${user.SKIN =='default' }">selected</c:if>>#438EB9</option>
								<option data-class="skin-1" value="#222A2D"
									<c:if test="${user.SKIN =='skin-1' }">selected</c:if>>#222A2D</option>
								<option data-class="skin-2" value="#C6487E"
									<c:if test="${user.SKIN =='skin-2' }">selected</c:if>>#C6487E</option>
								<option data-class="skin-3" value="#D0D0D0"
									<c:if test="${user.SKIN =='skin-3' }">selected</c:if>>#D0D0D0</option>
							</select>
						</div>
						<span>&nbsp; 选择皮肤</span>
					</div>
					<div>
						<label><input type='checkbox' name='menusf' id="menusf"
							onclick="menusf();" /><span class="lbl" style="padding-top: 5px;">菜单缩放</span></label>
					</div>
				</div>
			</div> --%>
			<!--/#ace-settings-container-->

		</div>
		<!-- #main-content -->
	</div>
	<!--/.fluid-container#main-container-->
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<!-- 引入 -->
		
		<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
		<script type="text/javascript" src="static/js/myjs/menusf.js"></script>
		
		<!--引入属于此页面的js -->
		<script type="text/javascript" src="static/js/myjs/index.js"></script>
		
		<script type="text/javascript">
		function isTops(){
			var t = self.frameElement;   
			if(t && (t.tagName=="FRAME"||t.tagName=="IFRAME")){   
				top.location.href = location.href;
			}
		}
		//切换职位，这里的切换职位是在头上切换  登录弹出来的框是在logn.jsp里面
		function changePost() {
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
		
		//点击首页跳转的地方
		function jumpIndex() {
			var form = $('<form></form>');  
		 	var action = '<%=path%>/login_default.do';
			form.attr('action', action);  
		    form.attr('method', 'post');  
		    form.attr('target', '_self');  
		    // 附加到Form  
		    //提交表单  *注意此处的写法，要先将创建的form渲染到body之中才可触发submit()事件，否则是不能触发的  
		    form.appendTo(document.body).submit();
		}
		</script>
</body>
</html>