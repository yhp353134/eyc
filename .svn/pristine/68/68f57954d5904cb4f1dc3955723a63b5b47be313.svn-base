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
<base href="<%=basePath%>">	
<!-- jsp文件头和头部 -->
<%@ include file="../../system/admin/top.jsp"%> 
<style type="text/css">
select {width: 130px;}
.formTempClass2 {display: block;}
.formTempClass1 {display: none;}
</style>
</head>
<body>
		<div style="width: 100%;margin-top: 10px;"></div>
			<table>
				<tr>
					<form action="sourceGoods/dealerDriverSourceChina.do"  method="post"   id="userPost">
					<td>
						<%-- <span>
							<input style="margin-top: 10px;" autocomplete="off"  id="nav-search-input" type="text" name="loginName" placeholder="这里输入手机号" value="${pd.loginName }"/>
						</span>
						<span>
							<input style="margin-top: 10px;" autocomplete="off" id="nav-search-input" type="text" name="chineName" placeholder="这里输入货主姓名" value="${pd.chineName }"/>
						</span> --%>
						&nbsp;&nbsp;
						起始地:
						<select class="chzn-select" name="provinceId"  id="provinceId"  style="width: 90px;margin-top: 10px;color: gray;" onchange="changBeginWord()">
							<option value="0">--省份--</option>
							<c:forEach items="${provinceList }" var="item">
								<option value="${item.REGION_CODE }"  <c:if test="${pd.provinceId eq item.REGION_CODE }">selected="selected"</c:if>> ${item.REGION_NAME }</option>
							</c:forEach>
						</select>
						<input type="hidden" id="cityIdHiden" value="${pd.cityId }"/>
						<select class="chzn-select" name="cityId" id="cityId"  style="vertical-align:top;width: 110px;margin-top: 10px;color: gray;">
							<option value="0">--城市--</option>
						</select>
						&nbsp;&nbsp;
						目的地:
						<select class="chzn-select" name="provinceId1"  id="provinceId1"  style="width: 90px;margin-top: 10px;color: gray;" onchange="changBeginWord1()">
							<option value="0">--省份--</option>
							<c:forEach items="${provinceList }" var="item">
								<option value="${item.REGION_CODE }"  <c:if test="${pd.provinceId1 eq item.REGION_CODE }">selected="selected"</c:if>> ${item.REGION_NAME }</option>
							</c:forEach>
						</select>
						<input type="hidden" id="cityIdHiden1" value="${pd.cityId1 }"/>
						<select class="chzn-select" name="cityId1" id="cityId1"  style="vertical-align:top;width: 110px;margin-top: 10px;color: gray;">
							<option value="0">--城市--</option>
						</select>
						&nbsp;&nbsp;
						车型:
						<select class="chzn-select" name="matId"  id="matId"  style="vertical-align:top;width: 150px;margin-top: 10px;color: gray;" onchange="changMat()">
							<option value="0">--品牌--</option>
							<c:forEach items="${matList }" var="items">
								<option value="${items.ID }"  <c:if test="${pd.matId eq items.ID }">selected="selected"</c:if>> ${items.NAME }</option>
							</c:forEach>
						</select>
						<input type="hidden" id="carIdHid" value="${pd.carId }"/>
						<select class="chzn-select" name="carId" id="carId"  style="vertical-align:top;width: 120px;margin-top: 10px;color: gray;">
							<option value="0">--车型--</option>
						</select>
					</td>
					<td>
						<button class="btn btn-mini btn-light" type="submit"   title="查询"><i id="nav-search-icon" class="icon-search"></i></button>
						<button type="button"  class="btn btn-mini btn-light"  title="重置" onclick="resetButton()"><i id="nav-search-icon" class="icon-repeat"></i></button>
					</td>
					</form>
				</tr>
			</table>
		</div>
		<div style="width: 100%;margin-top: 10px;">
			<table id="table_bug_report" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">货源编号</th>
						<th class="center">货主姓名</th>
						<th class="center" >起始地</th>
						<th class="center">目的地</th>
						<th class="center" >接车时间</th>
						<th class="center" >运达时间</th>
						<th class="center" >指导价</th>
						<th class="center" >车型</th>
						<th class="center" >发货人</th>
						<th class="center" >收货人</th>
						<th class="center" >已报价人数</th>
						<c:if test="${orgType eq '10120002' and useType eq '10131001' }">
							<th class="center">提醒次数</th>
						</c:if>
						<th class="center">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sourceList }"  var="user">
						<tr>
							<input type="hidden"  value="${user.ORDER_ID }"/>
							<td class="center">${user.ORDER_ID }</td>
							<td class="center">${user.OWNER_NAME }</td>
							<td class="center">${user.B_CITY_NAME }</td> 
							<td class="center">${user.E_CITY_NAME }</td> 
							<td class="center">${user.BEGIN_DATE }</td> 
							<td class="center">${user.END_DATE }</td> 
							<td class="center">${user.DIRECT_PRICE }</td> 
							<td class="center">${user.MODLE_NAME }</td> 
							<td class="center">${user.PAY_NAME }</td> 
							<td class="center">${user.RE_NAME }</td>
							<td class="center">${user.PRICE_FLAG }</td>
							<c:if test="${orgType eq '10120002' and useType eq '10131001' }">
								<td class="center">${user.NOTICE_NUM }</td>
							</c:if>
							<td class="center">
								<c:if test="${QX.alerts eq '1'}">
									<button  class='btn btn-mini btn-primary'  onclick="alertDriver(this)">提  醒</button>
								</c:if>
								<button  class='btn btn-mini btn-success'  onclick="detailSource(this)">详  情</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		<div class="page-header position-relative">
			<table style="width:100%;">
				<tr>
					<td style="vertical-align:top;">
						<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
					</td>
				</tr>
			</table>
		</div>
<!-- 引入 -->
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script src="static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
<script type="text/javascript">
top.hangge();
$(function(){
	$(".chzn-select").chosen(); 
	setSelectStyle("provinceId");
	setSelectStyle("provinceId1");
	setSelectStyle("cityId");
	setSelectStyle("cityId1");
	setSelectStyle("matId");
	setSelectStyle("carId");
	changMat();
	changBeginWord();
	changBeginWord1();
})
//重置
function resetButton() {
	$("input[name='loginName']").val("");
	$("input[name='chineName']").val("");
	$('#cityId').empty();
	$('#cityId1').empty();
	$('#carId').empty();
	cleanSelected("provinceId","--省份--");
	cleanSelected("cityId","--城市--");
	cleanSelected("provinceId1","--省份--");
	cleanSelected("cityId1","--城市--");
	cleanSelected("matId","--品牌--");
	cleanSelected("carId","--车型--");
}

//起始地连动
function changBeginWord(){
	var provinceId = $("#provinceId").val();
	if (provinceId == '0') {
		$("#cityId").empty(); 
		$("#cityId").append('<option value="0">--城市--</option>');
	} else {
		$.ajax({
			type: "POST",
			url: globalContextPath+'/sourceGoods/getCityByProvinceId.do?tm='+new Date().getTime(),
			dataType:'json',
			data:{
				"provinceId":provinceId
			},
			cache: false,
			success: function(data){
				$("#cityId").empty(); 
				var carIdHid = $("#cityIdHiden").val();
				for(var i=0;i<data.length;i++) {
					if (carIdHid == data[i].REGION_CODE) {
						$("#cityId").append('<option value="'+data[i].REGION_CODE+'" selected="selected">'+data[i].REGION_NAME+'</option>');
					} else {
						$("#cityId").append('<option value="'+data[i].REGION_CODE+'">'+data[i].REGION_NAME+'</option>');
					}
				}
				$("#cityId").trigger("liszt:updated");
	            $("#cityId").chosen();
			}
		});
	}
}

//目的地地连动
function changBeginWord1(){
	var provinceId = $("#provinceId1").val();
	if (provinceId == '0') {
		$("#cityId1").empty(); 
		$("#cityId1").append('<option value="0">--城市--</option>');
	} else {
		$.ajax({
			type: "POST",
			url: globalContextPath+'/sourceGoods/getCityByProvinceId.do?tm='+new Date().getTime(),
			dataType:'json',
			data:{
				"provinceId":provinceId
			},
			cache: false,
			success: function(data){
				$("#cityId1").empty(); 
				var carIdHid = $("#cityIdHiden1").val();
				for(var i=0;i<data.length;i++) {
					if (carIdHid == data[i].REGION_CODE) {
						$("#cityId1").append('<option value="'+data[i].REGION_CODE+'" selected="selected">'+data[i].REGION_NAME+'</option>');
					} else {
						$("#cityId1").append('<option value="'+data[i].REGION_CODE+'">'+data[i].REGION_NAME+'</option>');
					}
				}
				$("#cityId1").trigger("liszt:updated");
	            $("#cityId1").chosen();
			}
		});
	}
}

//联动车型
function changMat(){
	var matId = $("#matId").val();
	if (matId == '0') {
		$("#carId").empty(); 
		$("#carId").append('<option value="0">--车型--</option>');
	} else {
		$.ajax({
			type: "POST",
			url: globalContextPath+'/sourceGoods/getMatListByMatId.do?tm='+new Date().getTime(),
			dataType:'json',
			data:{
				"matId":matId
			},
			cache: false,
			success: function(data){
				$("#carId").empty(); 
				var carIdHid = $("#carIdHid").val();
				for(var i=0;i<data.length;i++) {
					if (carIdHid == data[i].ID) {
						$("#carId").append('<option value="'+data[i].ID+'" selected="selected">'+data[i].NAME+'</option>');
					} else {
						$("#carId").append('<option value="'+data[i].ID+'">'+data[i].NAME+'</option>');
					}
				}
				$("#carId").trigger("liszt:updated");
	            $("#carId").chosen();
			}
		});
	}
}

//提醒司机
function alertDriver(obj) {
	var sourceId = $(obj).parent().parent().find("input[type='hidden']").val();
	var returnval="";
	$.ajax({
		type: "POST",
		url: globalContextPath+'/app/appCommon/getBussniesStatusById.do?tm='+new Date().getTime(),
		data:{
			"objectId":sourceId
		},
		cache: false,
		async: false, 
		success:function(res){
			returnval = res;
		},
		error:function(){
			alert("系统错误");
		}
	});
	if (returnval == "10211001" || returnval=="10211002") {
		var beginCity = $(obj).parent().parent().find("td").eq(1).html();
		var endCity = $(obj).parent().parent().find("td").eq(2).html();
		top.jzts();
		var diag = new top.Dialog();
		diag.Drag=true;
		diag.Title ="选择司机";
		diag.URL =globalContextPath+ '/sourceGoods/alertDriverGetSource.do?endCity='+endCity+'&beginCity='+beginCity+'&sourceId='+sourceId;
		diag.Width = 470;
		diag.Height = 250;
		diag.CancelEvent = function(){ //关闭事件
			if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				top.jzts();
				window.location.href=globalContextPath+"/sourceGoods/dealerDriverSourceChina.do";
			}
			diag.close();
		};
		diag.show();
	} else {
		bootbox.alert("此货源已处理，不可再提醒");
	}
}

//详情
function detailSource(obj) {
	var sourceId = $(obj).parent().parent().find("input[type='hidden']").val();
	top.jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="货源详情";
	 diag.URL =globalContextPath+ '/sourceGoods/sourceDetail.do?sourceId='+sourceId;
	 diag.Width = 800;
	 diag.Height = 450;
	 diag.CancelEvent = function(){ //关闭事件
		 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){ }
		diag.close();
	 };
	 diag.show();
}

//确认报价
function confirmPrice(obj) {
	var sourceId = $(obj).parent().parent().find("input[type='hidden']").val();
	top.jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="确认报价";
	 diag.URL =globalContextPath+ '/sourceGoods/goToSourcePriceConfirm.do?sourceId='+sourceId;
	 diag.Width = 600;
	 diag.Height = 300;
	 diag.CancelEvent = function(){ //关闭事件
		 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
			 top.jzts();
			window.location.href=globalContextPath+"/sourceGoods/sourceList.do";
		}
		diag.close();
	 };
	 diag.show();
}

function cancelSource(obj) {
	var sourceId = $(obj).parent().parent().find("input[type='hidden']").val();
	if(checkNullStr(sourceId)) {
		alert("系统错误");
		return;
	}
	top.jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="货源撤销";
	 diag.URL =globalContextPath+ '/sourceGoods/goToSourcePriceRevoke.do?sourceId='+sourceId;
	 diag.Width = 500;
	 diag.Height = 200;
	 diag.CancelEvent = function(){ //关闭事件
		 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
			 top.jzts();
			window.location.href=globalContextPath+"/sourceGoods/sourceList.do";
		}
		diag.close();
	 };
	 diag.show();
}
</script>
</body>
</html>

