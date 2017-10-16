<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择职位</title>
<%@ include file="top.jsp"%>
</head>
<body>
<div id="postListDiv">
 <input type="hidden" id="formHiden" name="changeMenu"/>
			<table class="table table-striped table-bordered table-hover"  style="width: 98%;position: relative;left:1%;margin-top: 10px;">
				<tr height="30px">
					<th  colspan="2"  class="center">请选择职位</th>
				</tr>
				<c:forEach items="${postList }"  var="pos">
					<tr height="30px">
						<td  style="width: 20%;" class="center">
							<label>
								<input type="radio"  value="${pos.POST_ID }"  name="form-field-radio" />
								<span class="lbl"> </span>
							</label>	
						</td>
						<td align="left" >
							${pos.POST_NAME }
						</td>
					</tr>
				</c:forEach>
			</table>
		<div style="margin-top: 10px;position: relative;left:40%;">
			<input type="button" value="确定" onclick="jumpIndex()" class="btn btn-mini btn-primary" />&nbsp;&nbsp;
			<input type="button" value="取消" onclick="cancel()" class="btn btn-mini btn-danger" />
		</div>
</div>
</body>
<script type="text/javascript">
$(function(){
	document.documentElement.style.overflow='hidden'; //隐藏滚动条
});
function jumpIndex(){
	top.jzts();
	var vals = $("input[name='form-field-radio']:checked").val();
	if (vals != null && vals != "" && vals != "undefind") {
		$("#postListDiv").css("display","none");
	    $("#formHiden").val(vals);
		top.Dialog.close();
	} else {
		bootbox.alert("请选择职位");
	}
}
function cancel(){
	top.Dialog.close();
}
</script>
</html>