<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">	
<%@ include file="../../system/admin/top.jsp"%> 
</head>
<body>
<div id="zhongxin" style="overflow: hidden;">
	<div class="center" style="margin-top: 30px;">
		<form action="sourceGoods/revokeDataByIdAndType.do"  name="form1"   id="form1"  method="post">
			<input type="hidden" value="1" name="dataType"/>
			<input type="hidden" value="${pd.sourceId }" name="primaryId"/>
			撤销原因：<textarea rows="3" style="width: 250px;" id="revokeTxt" name="revokeTxt"></textarea>
		</form> 
	</div>
	<div>
		<table style="width:100%;">
			<tr>
				<td style="text-align: center;">
					<a class="btn btn-mini btn-primary" onclick="revokeData()">撤  销</a>&nbsp;&nbsp;
					<a class="btn btn-mini btn-danger" onclick="cancel()">取  消</a>
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
<script type="text/javascript">
top.hangge();
function revokeData() {
	var revokeTxt = $("#revokeTxt").val();
	if (checkNullStr(revokeTxt)) {
		$("#revokeTxt").tips({
			side:3,
            msg:"请填写撤销原因",
            bg:'#AE81FF',
            time:2
        });
		$("#revokeTxt").focus();
		return false;
	} else {
		$("#form1").submit();
		$("#zhongxin").css("display","none");
	}
}

function cancel() {
	top.Dialog.close();
}
</script>
</html>