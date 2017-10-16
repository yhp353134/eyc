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
<%@ include file="../admin/top.jsp"%>
<link href="static/css/bootstrap.min.css" rel="stylesheet" />
<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
<style type="text/css">
input {font-size: 12px;width: 125px; }
.showmsg {
	position: absolute; top: 0px;  background-color: #777;     
	z-index: 70; left: 0px;     
	opacity:0.5; -moz-opacity:0.5;     
	z-index: 1000;
	text-align: center;
}
.ovfHiden{overflow: hidden;height: 100%;}
.pointDiv{
	width: 80%;
	height: 450px;
	z-index: 1001;
	margin-top:0px;
	background-color: white;
	position: absolute;
	top:5%;
	left:10%;
	border:3px solid #438EB9;
	display: none;
}
.allMapHead{
z-index: 1002;
background-color:#B8B8B8;
position: absolute;
left:89%;
top:5%;
cursor: pointer;
display: none;
}
.allMapSearch{
	z-index: 1003;
	position: absolute;
	left:11%;
	top:6%;
	display: none;
}
.anchorBL{display:none;}
  .filess{
        float:left;
        padding:5px;
        color:#999;
        height:24px;
        line-height:24px;
        border:1px #ccc solid;
        width:200px;
        margin-right:4px;
    }
    .files{
        float:left;
        display:inline-block;
        padding:4px 16px;
        color:#fff;
        font:12px "Microsoft YaHei", Verdana, Geneva, sans-serif;
        cursor:pointer;
        background-color:#6FB3E0;
        line-height:25px;
        text-decoration:none;
        border-radius:2px;
    }
</style>
</head>
<body>
<div style="position: relative;width: 100%;" id="zhongxin">
<div style="position: relative;left: 1%;width: 98%;">
	<form action="dealer/addDealerMsg.do"  name="form1" id="form1"  method="post"  enctype="multipart/form-data">
			<table class="table table-striped table-bordered table-hover"  style="margin-top: 10px;">
				<tr>
					<td width="13%" style="text-align: right;">公司名称:</td>
					<td style="text-align: left;"    width="17%">
						<input type="text"  style="width: 150px;" id="DEALER_NAME"  name="DEALER_NAME" placeholder="这里输入公司名称" />
					 	<strong class='high' style='color:red;'> *</strong>
					 </td>
					 <td align="right"  style="text-align: right;">车队数量：</td>
						<td align="left">
							<input type="text" name="CARS" id="CARS" value="1" maxlength="32" placeholder="这里输入微信原始ID" />
						</td>
				</tr>
				<tr>
					<td align="right" width="13%"  style="text-align: right;">联系人：</td>
					<td width="17%"  align="left" >
						<input type="text" name="LINKMAN" id="LINKMAN"  maxlength="32" placeholder="这里输入联系人" title="联系人"/>
					</td>
					<td align="right"  width="13%"  style="text-align: right;">联系电话：</td>
					<td align="left"  width="17%">
						<input type="text" name="LINKTEL" id="LINKTEL"  maxlength="32" placeholder="这里输入联系电话" title="联系电话"/>
						<strong class='high' style='color:red;'> *</strong>
					</td>
				</tr>
				<tr>
					<td align="right" width="13%"  style="text-align: right;">电子邮件：</td>
					<td width="17%"  align="left" >
						<input type="text" name="EMAIL" id="EMAIL" maxlength="32" placeholder="这里输入电子邮件" title="电子邮件"/>
					</td>
					<td align="right"  width="13%"  style="text-align: right;">传真：</td>
					<td align="left"  width="17%">
						<input type="text" name="FAX" id="FAX" maxlength="32" placeholder="这里输入传真" title="传真"/>
					</td>
				</tr>
				<tr style="display: none;">
					<td align="right" width="13%"  style="text-align: right;">服务热线：</td>
					<td width="17%"  align="left" >
						<input type="text" name="HELP_TEL" id="HELP_TEL"  maxlength="32" placeholder="这里输入救援电话" title="救援电话"/>
						<!-- <strong class='high' style='color:red;'> *</strong> -->
					</td>
					<td align="right"  width="13%"  style="text-align: right;">预约热线：</td>
					<td align="left"  width="17%">
						<input type="text" name="ORDER_TEL" id="ORDER_TEL"  maxlength="32" placeholder="这里输入预约电话" title="预约电话"/>
					</td>
				</tr>
				<tr>
					<td align="right" width="13%"  style="text-align: right;">经度：</td>
					<td width="17%"  align="left" >
						<input type="text"  name="LONGITUDE" id="LONGITUDE"   placeholder="这里输入经度" title="经度" style="width: 125px;"/>
						<!-- <strong class='high' style='color:red;'> *</strong> -->
					</td>
					<td align="right"  width="13%"  style="text-align: right;">纬度：</td>
					<td align="left"  width="17%">
						<input type="text" style="width: 100px;"  name="LATITUDE" id="LATITUDE"  placeholder="这里输入纬度" title="纬度" />
						<!-- <strong class='high' style='color:red;'> *</strong> -->
						<button type="button" class="btn btn-mini btn-light"  style="margin-top: -10px;" onclick="choosePoint(1)">
							位置
						</button>
					</td>
				</tr>
				<tr>
					<!-- <td align="right" width="13%"  style="text-align: right;">预约折扣：</td>
					<td width="17%"  align="left" >
						<input type="text" name="ORDER_DISCOUNT" id="ORDER_DISCOUNT"  maxlength="32" placeholder="这里输入预约折扣" title="预约折扣"/>
					</td> -->
					<td align="right"  width="13%"  style="text-align: right;">地址：</td>
					<td align="left" >
						<input type="text" name="ADDRESS" id="ADDRESS"  maxlength="32" placeholder="这里输入地址" title="地址" style="width: 200px;"/>
					</td>
					<td align="right"  width="13%"  style="text-align: right;">类型：</td>
					<td align="left">
						<select class="chzn-select"  name="personType" id="personType" data-placeholder="请选择" style="vertical-align:top;width: 120px;margin-top: 10px;">
							<option selected="selected" value="10151001">承运商</option>
							<option value="10151002">经纪人</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">身份证：</td>
					<td style="text-align: left;" colspan="5">
 						<input type="file" name="carNumFiles"  multiple="multiple" />
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">营业执照：</td>
					<td style="text-align: left;" colspan="5">
						<input type="file"  name="ownerFiles"  multiple="multiple" >
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">道路运输许可证：</td>
					<td style="text-align: left;" colspan="5">
						<input type="file"  name="loadFiles"  multiple="multiple" >
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">公司信息：</td>
					<td style="text-align: left;" colspan="5">
						<textarea name="COMPANY_INFO"  id="CONTENT"  rows="3" style="width: 450px;"></textarea>
					</td>
				</tr>
				<tr>					
					<td style="text-align: center;" colspan="5">
						<a class="btn btn-mini btn-primary" onclick="save();">新 增</a>&nbsp;&nbsp;
						<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
					</td>
				</tr>
			</table>
			</form>
	</div>
</div>
<div class="showmsg"></div>
<div>
<!-- 经纬度设置 -->
<div>
	<div class="allMapHead"><img src="<%=path%>/static/images/X.gif" width="20px" height="20px" onclick="hiddenDiv()"></div>
	<div class="allMapSearch">
		<input id="region" type="textbox"  placeholder="省级以下的城市" style="width:100px;">
		<input id="keyword" type="textbox" placeholder="街道详细地址" style="width:200px;">
	    <input type="button" value="search" onclick="searchKeyword()">
	</div>
	<div  id="allmap" class="pointDiv"></div>
</div>

<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
<script src="static/1.9.1/jquery.min.js"></script>
<!-- 引入 -->
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script src="static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
<script type="text/javascript" src="https://3gimg.qq.com/lightmap/components/geolocation/geolocation.min.js"></script><!-- 腾讯获取坐标需要引入的 -->  
<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp&key=4BNBZ-ULLW4-JUSUB-DSR4O-GX67V-GYFHZ"></script>
<script type="text/javascript">
var marker = null;
var searchService,markers = [];
function addMaps () {
		var center = new qq.maps.LatLng(39.982163,116.306070);
        var map = new qq.maps.Map(
            document.getElementById("allmap"),
            {
                center: center,
                disableDefaultUI: true,  //不用所有的控件
                zoom: 14
            }
        );
        marker = new qq.maps.Marker({
            position: center,
            draggable: true,
            map: map
        });
        var anchor = new qq.maps.Point(0, 0),
    	    //	size = new qq.maps.Size(100, 30),
    	    origin = new qq.maps.Point(0, 0),
    	    markerIcon = new qq.maps.MarkerImage(
    		"<%=path%>/static/images/dinwei.png",
    		null, 
    		origin,
    		anchor
    	);
    	marker.setIcon(markerIcon);
    	marker.setDraggable(true);
    	marker.setAnimation(qq.maps.MarkerAnimation.DOWN);
    	//添加到提示窗
        var info = new qq.maps.InfoWindow({
            map: map
        });
     	//标注点击事件 dragend
       qq.maps.event.addListener(marker, 'dragend', function(event) {
    	  var  j = event.latLng.getLng();
    	  var  w = event.latLng.getLat();
    	  geocoder = new qq.maps.Geocoder();   
    	  var latLng = new qq.maps.LatLng(w, j);
    	  geocoder.getAddress(latLng);
    	  geocoder.setComplete(function(result) {
              map.setCenter(latLng);
              info.open(); 
              var addr = result.detail.address;
              info.setContent("地址："+addr+"<br/>经度：" +j + "<br/>纬度："+ w+
            			"<br/><div style='text-align:right;'>"+
            			"<button class='btn btn-mini btn-primary' type='button' onclick='setLngandLat("+j+",\""+addr+"\","+w+")'>确定</button></div>");
              info.setPosition(latLng); 
    	  });
       });
       var latlngBounds = new qq.maps.LatLngBounds();
       //调用Poi检索类
       searchService = new qq.maps.SearchService({
           complete : function(results){
               var pois = results.detail.pois;
               if (pois.length > 1) {
            	   for(var i = 0;i < 1; i++){
                       var poi = pois[i];
                       latlngBounds.extend(poi.latLng);  
                       var markerOne = new qq.maps.Marker({
                           map:map,
                           position: poi.latLng
                       });
                       markerOne.setTitle(i+1);
                       var anchord = new qq.maps.Point(0, 0);
    	           	    var origind = new qq.maps.Point(0, 0);
    	           	    var markerIcond = new qq.maps.MarkerImage(
    		           		"<%=path%>/static/images/dinwei.png",
    		           		null, 
    		           		origind,
    		           		anchord
    	           		);
    	           	markerOne.setDraggable(true);
                    markerOne.setIcon(markerIcond);
                    //标注点击事件 dragend
                    qq.maps.event.addListener(markerOne, 'dragend', function(event) {
                 	  var  j = event.latLng.getLng();
                 	  var  w = event.latLng.getLat();
                 	  geocoder = new qq.maps.Geocoder();   
                 	  var latLng = new qq.maps.LatLng(w, j);
                 	  geocoder.getAddress(latLng);
                 	  geocoder.setComplete(function(result) {
                           map.setCenter(latLng);
                           info.open(); 
                           var addr = result.detail.address;
                           info.setContent("地址："+addr+"<br/>经度：" +j + "<br/>纬度："+ w+
                         			"<br/><div style='text-align:right;'>"+
                         			"<button class='btn btn-mini btn-primary' type='button' onclick='setLngandLat("+j+",\""+addr+"\","+w+")'>确定</button></div>");
                           info.setPosition(latLng); 
                 	  });
                    });
                    markers.push(markerOne);
                   //循环结束
                   }
               }
               map.fitBounds(latlngBounds);
           }
       });
}
//设置地图		
function choosePoint(o) {
    $(".showmsg").height($(document).height());
    $(".showmsg").width($(document).width());
    $(".pointDiv").css("display","block");
    $(".showmsg").css("display","block");
    $(".allMapHead").css("display","block");
    $(".allMapSearch").css("display","block");
    var ltde = $("#LONGITUDE").val();
	var lte = $("#LATITUDE").val();
    var geolocation,geocoder=null;
    if (ltde == null || ltde == '' || lte == null || lte == '') {
    	//获取当前位置
    	//geolocation = new qq.maps.Geolocation("4BNBZ-ULLW4-JUSUB-DSR4O-GX67V-GYFHZ", "icar");
        //geolocation.getLocation(sucCallback);
    	addMaps();
    } else {
    	//设置map
        var center = new qq.maps.LatLng(lte,ltde);
        var map = new qq.maps.Map(
            document.getElementById("allmap"),
            {
                center: center,
                disableDefaultUI: true,  //不用所有的控件
                zoom: 14
            }
        );
       marker = new qq.maps.Marker({
            position: center,
            draggable: true,
            map: map
        });
        var anchor = new qq.maps.Point(0, 0),
    	    //	size = new qq.maps.Size(100, 30),
    	    origin = new qq.maps.Point(0, 0),
    	    markerIcon = new qq.maps.MarkerImage(
    		"<%=path%>/static/images/dinwei.png",
    		null, 
    		origin,
    		anchor
    	);
    	marker.setIcon(markerIcon);
    	marker.setDraggable(true);
    	marker.setAnimation(qq.maps.MarkerAnimation.DOWN);
    	//添加到提示窗
        var info = new qq.maps.InfoWindow({
            map: map
        });
     	//标注点击事件 dragend
       qq.maps.event.addListener(marker, 'dragend', function(event) {
    	  var  j = event.latLng.getLng();
    	  var  w = event.latLng.getLat();
    	  geocoder = new qq.maps.Geocoder();   
    	  var latLng = new qq.maps.LatLng(w, j);
    	  geocoder.getAddress(latLng);
    	  geocoder.setComplete(function(result) {
              map.setCenter(latLng);
              info.open(); 
              var addr = result.detail.address;
              info.setContent("地址："+addr+"<br/>经度：" +j + "<br/>纬度："+ w+
            			"<br/><div style='text-align:right;'>"+
            			"<button class='btn btn-mini btn-primary' type='button' onclick='setLngandLat("+j+",\""+addr+"\","+w+")'>确定</button></div>");
              info.setPosition(latLng); 
    	  });
       });
       var latlngBounds = new qq.maps.LatLngBounds();
       //调用Poi检索类
       searchService = new qq.maps.SearchService({
           complete : function(results){
               var pois = results.detail.pois;
               if (pois.length > 1) {
            	   for(var i = 0;i < 1; i++){
                       var poi = pois[i];
                       latlngBounds.extend(poi.latLng);  
                       var markerOne = new qq.maps.Marker({
                           map:map,
                           position: poi.latLng
                       });
                       markerOne.setTitle(i+1);
                       var anchord = new qq.maps.Point(0, 0);
    	           	    var origind = new qq.maps.Point(0, 0);
    	           	    var markerIcond = new qq.maps.MarkerImage(
    		           		"<%=path%>/static/images/dinwei.png",
    		           		null, 
    		           		origind,
    		           		anchord
    	           		);
    	           	markerOne.setDraggable(true);
                    markerOne.setIcon(markerIcond);
                    //标注点击事件 dragend
                    qq.maps.event.addListener(markerOne, 'dragend', function(event) {
                 	  var  j = event.latLng.getLng();
                 	  var  w = event.latLng.getLat();
                 	  geocoder = new qq.maps.Geocoder();   
                 	  var latLng = new qq.maps.LatLng(w, j);
                 	  geocoder.getAddress(latLng);
                 	  geocoder.setComplete(function(result) {
                           map.setCenter(latLng);
                           info.open(); 
                           var addr = result.detail.address;
                           info.setContent("地址："+addr+"<br/>经度：" +j + "<br/>纬度："+ w+
                         			"<br/><div style='text-align:right;'>"+
                         			"<button class='btn btn-mini btn-primary' type='button' onclick='setLngandLat("+j+",\""+addr+"\","+w+")'>确定</button></div>");
                           info.setPosition(latLng); 
                 	  });
                    });
                    markers.push(markerOne);
                   //循环结束
                   }
               }
               map.fitBounds(latlngBounds);
           }
       });
    }
}

//清除地图上的marker
function clearOverlays(overlays){
    var overlay;
    while(overlay = overlays.pop()) {
        overlay.setMap(null);
    }
}
function searchKeyword() {
    var keyword = document.getElementById("keyword").value;
    var region = document.getElementById("region").value;
    marker.setMap(null);
    clearOverlays(markers);
    if (region == null || region =='') {
    	 searchService.setLocation("北京");
    } else {
    	 searchService.setLocation(region);
    }
    if (keyword == null || keyword == '') {
    	 searchService.search("火车站");
    } else {
    	 searchService.search(keyword);
    }
}

	
//设置经纬度
function setLngandLat(g,h,t) {
	$("#LONGITUDE").val(g);
	$("#LATITUDE").val(t);
	$("#ADDRESS").val(h);
	$(".pointDiv").css("display","none");
	$(".showmsg").css("display","none");
	$(".allMapHead").css("display","none");
	$(".allMapSearch").css("display","none");
}

function hiddenDiv() {
	$(".pointDiv").css("display","none");
	$(".showmsg").css("display","none");
	$(".allMapHead").css("display","none");
	$(".allMapSearch").css("display","none");
}

//二维码修改图片
function chanImage() {
	$('#imageFileHas').click();
}
//关于图片修改
function chanImageAobut() {
	$('#aboutImageFileHas').click();
}
		
$(function() {
	top.hangge();
	$(".chzn-select").chosen(); 
	//function结束
});

function afg() {
	var contents = $("#CONTENT").val();
	var wd = document.getElementById("valFrame1").contentWindow;
	wd.setContent(contents);
}
//检查非空
function check(objStr) {
	if (objStr == null || objStr == '' || objStr == 'undefind' || objStr.length == 0) {
		return true;
	} else {
		return false;
	}
}
function strToJson(str){ 
	var json = (new Function("return " + str))(); 
	return json; 
} 

//新增
function save() {
	/* var window = document.getElementById("valFrame1").contentWindow;
	var content = window.getContent();
	$("#CONTENT").val(content); */
	if (check($("#DEALER_NAME").val())) {
		$("#DEALER_NAME").tips({
			side:3,
            msg:"经销商名称不能为空",
            bg:'#AE81FF',
            time:2
        });
		$("#DEALER_NAME").focus();
		return false;
	}
	if (check($("#LINKTEL").val())) {
		$("#LINKTEL").tips({
			side:3,
            msg:"销售热线不能为空",
            bg:'#AE81FF',
            time:2
        });
		$("#LINKTEL").focus();
		return false;
	}
	/* if (check($("#HELP_TEL").val())) {
		$("#HELP_TEL").tips({
			side:3,
            msg:"服务热线不能为空",
            bg:'#AE81FF',
            time:2
        });
		$("#HELP_TEL").focus();
		return false;
	}
	if (check($("#ORDER_TEL").val())) {
		$("#ORDER_TEL").tips({
			side:3,
            msg:"预约热线不能为空",
            bg:'#AE81FF',
            time:2
        });
		$("#ORDER_TEL").focus();
		return false;
	} */
	$("#form1").submit();
	$("#zhongxin").css("display","none");
	$("#zhongxin2").show();
}
</script>
</body>
</html>