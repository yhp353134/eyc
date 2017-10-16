	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%
	String IcarContextpath = request.getContextPath();
     %>
    <!-- 定义全局变量 -->
    <script type="text/javascript">
      var IcarContextpath =  '<%=IcarContextpath %>';
      var globalContextPath =  '<%=IcarContextpath %>';
    </script>
	<meta charset="utf-8" />
	<title>${pd.SYSNAME}</title>
	<meta charset="utf-8">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="mobile-web-app-capable" content="yes">
	<meta name="format-detection"  content="telephone=no">
	<meta http-equiv="x-rim-auto-match" content="none">
	<meta http-equiv="pragma" content="no-cache">  
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">  
	<meta http-equiv="expires" content="0"> 
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<!-- basic styles -->
	<link href="<%=IcarContextpath %>/static/css/bootstrap.min.css" rel="stylesheet" />
	<link href="<%=IcarContextpath %>/static/css/bootstrap-responsive.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="<%=IcarContextpath %>/static/css/font-awesome.min.css" />
	<!-- 下拉框-->
	<link rel="stylesheet" href="<%=IcarContextpath %>/static/css/chosen.css" />
	<!-- ace styles -->
	<link rel="stylesheet" href="<%=IcarContextpath %>/static/css/ace.css" />
	<link rel="stylesheet" href="<%=IcarContextpath %>/static/css/ace-responsive.min.css" />
	<link rel="stylesheet" href="<%=IcarContextpath %>/static/css/ace-skins.min.css" />
	<script type="text/javascript" src="<%=IcarContextpath %>/static/js/jquery-1.7.2.js"></script>
	<!--引入弹窗组件start-->
	<script type="text/javascript" src="<%=IcarContextpath %>/plugins/attention/zDialog/zDrag.js"></script>
	<script type="text/javascript" src="<%=IcarContextpath %>/plugins/attention/zDialog/zDialog.js"></script>
	<!--引入弹窗组件end-->
	<script type="text/javascript" src="<%=IcarContextpath %>/static/js/common.js"></script>
	<!-- 引入日期控件 -->
	<script type="text/javascript"  src="<%=IcarContextpath%>/static/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript"  src="<%=IcarContextpath%>/static/js/common/sysData.js"></script>
	<script type="text/javascript"  src="<%=IcarContextpath%>/static/js/common/eycCommon.js"></script>
	
	