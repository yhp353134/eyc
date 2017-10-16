<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
 <meta http-equiv="content-type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<%@ include file="../admin/top.jsp"%>  
<title></title>
<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="static/css/bootstrap.min.css" rel="stylesheet" />
<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link rel="stylesheet" href="static/css/font-awesome.min.css" />
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
<style type="text/css">
input {font-size: 12px; }
select {font-size: 12px;}
</style>
<script type="text/javascript">
	top.hangge();
</script>
</head>
<body>
<div style="width: 100%;position: relative;" id="zhongxin">
	<div style="width: 96%;position: relative;left: 2%;">
	<table style="width: 100%;font-size: 12px;margin-top: 10px;" class="table table-striped table-bordered table-hover">
	<thead>
			<th colspan="4">审核货源</th>
		</thead>
		<tbody>
		<tr>
			<td style="text-align: right;" >货源编号：</td>
			<td align="left" >
				${sourcePd.ORDER_ID }
			</td>
			<td style="text-align: right;" >货主姓名：</td>
			<td align="left" >
				${sourcePd.OWNER_NAME }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;" >货源状态：</td>
			<td align="left">
				<script>setDataName('${sourcePd.STATUS }')</script>
			</td>
			<td style="text-align: right;"  >货主电话：</td>
			<td align="left">
				${sourcePd.OWNER_PHONE }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;"  >起始地：</td>
			<td align="left">
				${sourcePd.B_CITY_NAME }
			</td>
			<td style="text-align: right;" >起始地详细地址：</td>
			<td align="left">
				${sourcePd.B_ADD }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;" >目的地：</td>
			<td align="left">
				${sourcePd.E_CITY_NAME }
			</td>
			<td style="text-align: right;" >目的地详细地址：</td>
			<td align="left">
				${sourcePd.E_ADD }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;" >接车时间：</td>
			<td align="left">
				${sourcePd.BEGIN_DATE }
			</td>
			<td style="text-align: right;" >运达时间：</td>
			<td align="left">
				${sourcePd.END_DATE }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;" >车辆品牌：</td>
			<td align="left">
				${carPd.NAME }
			</td>
			<td style="text-align: right;" >车辆车型：</td>
			<td align="left">
				${sourcePd.MODLE_NAME }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;">车架号：</td>
			<td align="left">
				${sourcePd.VIN }
			</td>
			<td style="text-align: right;">参考运费：</td>
			<td align="left">
				${sourcePd.DIRECT_PRICE }
			</td>
		</tr>
		<tr>
			<td style="text-align: right;">发货人：</td>
			<td align="left">
				${sourcePd.PAY_NAME }
			</td>
			<td style="text-align: right;">收货人：</td>
			<td align="left">
				${sourcePd.RE_NAME }
			</td>
		</tr>
		<c:if test="${not empty offerList }">
			<tr>
				<td style="text-align: right;vertical-align: middle;">报价列表：</td>
				<td align="left" colspan="3">
					<c:forEach items="${offerList }" var="item">
					<div style="display: inline;position: relative;left: 10px;">
						<font>报价：${item.PRICE }</font>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<font>报价时间：${item.PRICE_DATE }</font>
						<br/>
					</div>
					<div style="width: 270px;height: 1px;border-top: 1px dotted  lightgray;position: relative;left: 10px;"></div>
					<div style="width: 150px;height: 5px;"></div>
				</c:forEach>
				</td>
			</tr>
		</c:if>
		<form action="sourceGoods/dealerExamineDriverMsg.do" name="form1"  id="form1"  method="post">
			<input type="hidden" value="${pd.sourceId }"  name="sourceId"/>
			<input type="hidden" value="${pd.priceId }"  name="priceId"/>
			<input type="hidden" name="wxamineFlag"/>
			<tr>
				<td style="text-align: right;">审核意见：</td>
				<td align="left" colspan="3">
					<textarea rows="3" style="width: 400px;" id="examineSuggest"  name="examineSuggest"></textarea>
				</td>
			</tr>
			<tr align="center">
				<td align="center" style="text-align: center;" colspan="4">
					<a class="btn btn-mini btn-primary" onclick="agrees()">通  过</a>
					<a class="btn btn-mini btn-success" onclick="returns()">驳  回</a>
					<a class="btn btn-mini btn-danger" onclick="cancel()">关  闭</a>
				</td>
			</tr>
		</form>
		</tbody>
	</table>
	<!-- 背景结束 -->
	</div>
</div>
<script src="static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/js/bootbox.min.js"></script>
<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
<script type="text/javascript">
//通过
function agrees() {
	$("input[name='wxamineFlag']").val("1");
	$("#form1").submit();
	$("#zhongxin").css("display","none");
}

//驳回
function returns() {
	$("input[name='wxamineFlag']").val("2");
	var examineSuggest = $("#examineSuggest").val();
	if (checkNullStr(examineSuggest)) {
		$("#examineSuggest").tips({
			side:3,
            msg:"审核意见不能为空",
            bg:'#AE81FF',
            time:2
        });
		$("#examineSuggest").focus();
		return false;
	}
	$("#form1").submit();
	$("#zhongxin").css("display","none");
}

/***关闭窗体  */
 function cancel(){
 	top.Dialog.close();
 }
</script>
</body>
</html>
