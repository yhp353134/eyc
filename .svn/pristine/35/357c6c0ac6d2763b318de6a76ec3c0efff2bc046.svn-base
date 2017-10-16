<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<%@ include file="../../system/admin/top.jsp"%> 
</head>
<body>
<div class="row-fluid"  id="zhongguo" style="width: 100%;position: relative;" id="iframDiv">
<div style="width: 98%;position: relative;left:1%;">
	<table style="width:100%;">
			<tr>
				<td style="width: 15%; text-align: right;border-bottom: 1px solid lightgray" ><font size="4" >所选职位：</font></td>
				<td style="vertical-align:top;text-align: left;border-bottom: 1px solid lightgray">
					<input type="text" id="iframInput" style="width: 100%;border: 0px;height: 40px;font-weight: 600;color: red;" value="${pd.values }"/>
				</td>
			</tr>
	</table>
	<br>
	<table style="width:100%;">
			<tr>
				<td style="vertical-align:top;text-align: center;">
				    <a class="btn btn-mini btn-primary"  onclick="confs()">确  定</a>&nbsp;&nbsp;
					<a class="btn btn-mini btn-danger" onclick="cancel()">取  消</a>
				</td>
			</tr>
	</table>
</div>
</div>
<script type="text/javascript">

function confs() {
	var cc= $(window.parent.document.body);
	$(cc).find("#zhongguo").css("display","none");
	top.Dialog.close();
}

/**
 *关闭窗体 
 */
 function cancel(){
	var zz= $(window.parent.document.body);
	$(zz).find("#hidArr").val("");
	$(zz).find("#hidNameArr").val("");
  	top.Dialog.close();
 }	

</script>
</body>
</html>