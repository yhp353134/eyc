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
<meta charset="utf-8" />
<title></title>
<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="static/css/bootstrap.min.css" rel="stylesheet" />
<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
<style type="text/css">
input {font-size: 12px;width: 125px; }
a>img {width: 60px;height: 60px;} 
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
</style>
</head>
<body>

<div style="position: relative;width: 100%;" id="zhongxin">
<div style="position: relative;left: 1%;width: 98%;">
	<form action="dealer/updateDealerMsg.do"  name="form1" id="form1"  method="post"  enctype="multipart/form-data">
		<input type="hidden" name="DEALER_ID" id="DEALER_ID" value="${pd.DEALER_ID}"/>
			<table class="table table-striped table-bordered table-hover"  style="margin-top: 10px;">
				<tr>
					<td  style="text-align: right;">公司名称:</td>
					<td style="text-align: left;"    >
						<input type="text"  id="DEALER_NAME" value="${pd.DEALER_NAME}"  placeholder="这里输入公司名称" readonly="readonly"/>
					 </td>
					 <td   style="text-align: right;">车队数量：</td>
						<td align="left">
							<input type="text" name="CARS" id="CARS" value="${pd.CARS}" maxlength="32" placeholder="这里输入微信原始ID" />
						</td>
				</tr>
				<tr>
					<td    style="text-align: right;">联系人：</td>
					<td   >
						<input type="text" name="LINKMAN" id="LINKMAN" value="${pd.LINKMAN}" maxlength="32" placeholder="这里输入联系人" title="联系人"/>
					</td>
					<td     style="text-align: right;">联系电话：</td>
					<td  >
						<input type="text" name="LINKTEL" id="LINKTEL" value="${pd.LINKTEL}" maxlength="32" placeholder="这里输入联系电话" title="联系电话"/>
						<strong class='high' style='color:red;'> *</strong>
					</td>
				</tr>
				<tr>
					<td    style="text-align: right;">电子邮件：</td>
					<td   >
						<input type="text" name="EMAIL" id="EMAIL" value="${pd.EMAIL}" maxlength="32" placeholder="这里输入电子邮件" title="电子邮件"/>
					</td>
					<td     style="text-align: right;">传真：</td>
					<td  >
						<input type="text" name="FAX" id="FAX" value="${pd.FAX}" maxlength="32" placeholder="这里输入传真" title="传真"/>
					</td>
				</tr>
				<tr style="display: none;">
					<td    style="text-align: right;">服务热线：</td>
					<td   >
						<input type="text" name="HELP_TEL" id="HELP_TEL" value="${pd.HELP_TEL}" maxlength="32" placeholder="这里输入救援电话" title="救援电话"/>
						<strong class='high' style='color:red;'> *</strong>
					</td>
					<td     style="text-align: right;">预约热线：</td>
					<td  >
						<input type="text" name="ORDER_TEL" id="ORDER_TEL" value="${pd.ORDER_TEL}" maxlength="32" placeholder="这里输入预约电话" title="预约电话"/>
						<strong class='high' style='color:red;'> *</strong>
					</td>
				</tr>
				<tr>
					<td    style="text-align: right;">经度：</td>
					<td   >
						<input type="text"  name="LONGITUDE" id="LONGITUDE" value="${pd.LONGITUDE}"  placeholder="这里输入经度" title="经度" style="width: 125px;"/>
					</td>
					<td     style="text-align: right;">纬度：</td>
					<td  >
						<input type="text" style="width: 100px;"  name="LATITUDE" id="LATITUDE" value="${pd.LATITUDE}"  placeholder="这里输入纬度" title="纬度" />
						<button type="button" class="btn btn-mini btn-light"  style="margin-top: -10px;" onclick="choosePoint(1)">
							位置
						</button>
					</td>
				</tr>
				<tr>
					<td  style="text-align: right;">状态：</td>
					<td>
						<select style="width: 140px;" id="userStatus" name="userStatus">
							<script type="text/javascript">setSelectOption('1002',true,'${pd.STATUS}')</script>
						</select>
					</td>
					<td     style="text-align: right;">地址：</td>
					<td  colspan="3">
						<input type="text" name="ADDRESS" id="ADDRESS" value="${pd.ADDRESS}" maxlength="32" placeholder="这里输入地址" title="地址" style="width: 200px;"/>
					</td>
				</tr>
				<%-- <tr>
					<td    style="text-align: right;">微信号：</td>
					<td   >
						<input type="text" name="WECHAT_NUM" id="WECHAT_NUM" value="${pd.WECHAT_NUM}" maxlength="32" placeholder="这里输入微信号" />
						<strong class='high' style='color:red;'> *</strong>
					</td>
					<td     style="text-align: right;">微信号名称：</td>
					<td  >
						<input type="text" name="WECHAT_NAME" id="WECHAT_NAME" value="${pd.WECHAT_NAME}" maxlength="32" placeholder="这里输入微信名称" />
						<strong class='high' style='color:red;'> *</strong>
					</td>
				</tr>
				<tr>
					<td    style="text-align: right;">微信APPID：</td>
					<td   >
						<input type="text" name="APP_ID" id="APP_ID" value="${pd.APP_ID}" maxlength="32" placeholder="这里输入APPID" />
						<strong class='high' style='color:red;'> *</strong>
					</td>
					<td     style="text-align: right;">车队数量：</td>
					<td  >
						<input type="text" name="WECHAT_ID" id="WECHAT_ID" value="${pd.CARS}" maxlength="32" placeholder="这里输入微信原始ID" />
						<strong class='high' style='color:red;'> *</strong>
					</td>
				</tr>
				<tr>
					<td   style="text-align: right;">Token：</td>
					<td   >
						<input type="text" name="TOKEN" id="TOKEN" value="${pd.TOKEN}" maxlength="32" placeholder="这里输入Token" />
						<strong class='high' style='color:red;'> *</strong>
					</td>
					<td     style="text-align: right;">Appsecret：</td>
					<td  >
						<input type="text" name="APPSECRET" id="APPSECRET" value="${pd.APPSECRET}" maxlength="32" placeholder="这里输入Appsecret" />
						<strong class='high' style='color:red;'> *</strong>
					</td>
				</tr> --%>
				<tr>
					<td style="text-align: right;">身份证：</td>
					<td style="text-align: left;" colspan="5">
 						<div style="display: inline-block;">
 							<c:forEach items="${fileList }" var="item">
 								<c:if test="${item.USE_TYPE eq '10181001' }">
 									<a target="_Blank" href="${item.URL }">
		 								<img src="${item.URL }">
		 							</a>
 								</c:if>
 							</c:forEach>
 						</div>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">营业执照：</td>
					<td style="text-align: left;" colspan="5">
 						<div style="display: inline-block;">
 							<c:forEach items="${fileList }" var="item">
 								<c:if test="${item.USE_TYPE eq '10181005' }">
 									<a target="_Blank" href="${item.URL }">
		 								<img src="${item.URL }">
		 							</a>
 								</c:if>
 							</c:forEach>
 						</div>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">道路运输许可证：</td>
					<td style="text-align: left;" colspan="5">
 						<div style="display: inline-block;">
 							<c:forEach items="${fileList }" var="item">
 								<c:if test="${item.USE_TYPE eq '10181007' }">
 									<a target="_Blank" href="${item.URL }">
		 								<img src="${item.URL }">
		 							</a>
 								</c:if>
 							</c:forEach>
 						</div>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">身份证修改：</td>
					<td style="text-align: left;" colspan="5">
 						<input type="file" name="carNumFiles"  multiple="multiple" />
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">营业执照修改：</td>
					<td style="text-align: left;" colspan="5">
						<input type="file"  name="ownerFiles"  multiple="multiple" >
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">道路运输许可证修改：</td>
					<td style="text-align: left;" colspan="5">
						<input type="file"  name="loadFiles"  multiple="multiple" >
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">公司信息：</td>
					<td style="text-align: left;" colspan="5">
						<textarea name="COMPANY_INFO"  id="CONTENT"  rows="3" style="width: 450px;">${pd.COMPANY_INFO }</textarea>
						<%-- <iframe id="valFrame1" scrolling="no" frameborder="0" src="<%=path%>/weixin/plugins/CmWxEdit/index.html" style="margin:0 auto;width:100%; overflow: auto;height: 600px;"></iframe> --%>
					</td>
				</tr>
				<tr>					
					<td style="text-align: center;" colspan="5">
						<a class="btn btn-mini btn-primary" onclick="save();">修改</a>&nbsp;&nbsp;
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
<script src="static/js/ace-elements.min.js"></script>
<script src="static/js/ace.min.js"></script>
<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
<script type="text/javascript" src="https://3gimg.qq.com/lightmap/components/geolocation/geolocation.min.js"></script><!-- 腾讯获取坐标需要引入的 -->  
<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp&key=4BNBZ-ULLW4-JUSUB-DSR4O-GX67V-GYFHZ"></script>
<script type="text/javascript">
$(function(){
	$("#userStatus option[value='']").remove();
});


var marker = null;
var searchService,markers = [];
/* var intervalIds=null;
//获取当前位置的回调函数
function sucCallback(p) {
	var str = JSON.stringify(p, null, 4);
	intervalIds = window.setInterval(addMaps(str), 3000);
} */
function addMaps () {
		/* 
		window.clearInterval(intervalIds);
		var json = strToJson(str);
		 var pro = json.province;
		var city = json.city;
		var addr = json.addr;
		var address= pro+city+addr;  
		var ltde = json.lng;
		var lte = json.lat;*/
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
		
$(function() {
	top.hangge();
	 <%-- $("#image9").css("background-image","url('<%=path%>/weixin/images/add.png')");
	$('#imageFile').ace_file_input({
		style:'well',
		btn_choose:'请上传二维码',
		btn_change:null,
		no_icon:'icon-cloud-upload',
		droppable:true,
		onchange:null,
		thumbnail:'small',
		allowedFileExtensions : ['jpg', 'png','gif'],
		before_change:function(files, dropped) {
			return true;
		}
	}).on('change', function(){});
	$('#imageFileHas').ace_file_input({
		style:'well',
		btn_choose:'请上传二维码',
		btn_change:null,
		no_icon:'icon-cloud-upload',
		droppable:true,
		onchange:null,
		thumbnail:'small',
		allowedFileExtensions : ['jpg', 'png','gif'],
		before_change:function(files, dropped) {
			return true;
		}
	}).on('change', function(){
		$('#imgeDivs').css("display","block");
	});
	
	//关于图片
	$('#aboutImageFile').ace_file_input({
		style:'well',
		btn_choose:'请上传关于图片',
		btn_change:null,
		no_icon:'icon-cloud-upload',
		droppable:true,
		onchange:null,
		thumbnail:'small',
		allowedFileExtensions : ['jpg', 'png','gif'],
		before_change:function(files, dropped) {
			return true;
		}
	}).on('change', function(){});
	$('#aboutImageFileHas').ace_file_input({
		style:'well',
		btn_choose:'请上传关于图片',
		btn_change:null,
		no_icon:'icon-cloud-upload',
		droppable:true,
		onchange:null,
		thumbnail:'small',
		allowedFileExtensions : ['jpg', 'png','gif'],
		before_change:function(files, dropped) {
			return true;
		}
	}).on('change', function(){
		$('#abhoutImgeDivs').css("display","block");
	});
	$(".remove").click(function(){
		$(this).parent().parent().css("display","none");
	});

	var iframe = document.getElementById("valFrame1");
    iframe.src="<%=path%>/weixin/plugins/CmWxEdit/index.html";
    if (iframe.attachEvent){
        iframe.attachEvent("onload", function(){
        	var contents = $("#CONTENT").val();
        	var wd = document.getElementById("valFrame1").contentWindow;
        	wd.setContent(contents);
         });
    } --%>
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

//修改
function save() {
	/* var window = document.getElementById("valFrame1").contentWindow;
	var content = window.getContent();
	$("#CONTENT").val(content); */
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
	/* if (check($("#LONGITUDE").val())) {
		$("#LONGITUDE").tips({
			side:3,
            msg:"经度不能为空",
            bg:'#AE81FF',
            time:2
        });
		$("#LONGITUDE").focus();
		return false;
	}
	if (check($("#LATITUDE").val())) {
		$("#LATITUDE").tips({
			side:3,
            msg:"纬度不能为空",
            bg:'#AE81FF',
            time:2
        });
		$("#LATITUDE").focus();
		return false;
	}
	if (check($("#ADDRESS").val())) {
		$("#ADDRESS").tips({
			side:3,
            msg:"地址不能为空",
            bg:'#AE81FF',
            time:2
        });
		$("#ADDRESS").focus();
		return false;
	}
	if (check($("#WECHAT_NUM").val())) {
		$("#WECHAT_NUM").tips({
			side:3,
            msg:"微信号不能为空",
            bg:'#AE81FF',
            time:2
        });
		$("#WECHAT_NUM").focus();
		return false;
	}
	if (check($("#WECHAT_NAME").val())) {
		$("#WECHAT_NAME").tips({
			side:3,
            msg:"微信名称不能为空",
            bg:'#AE81FF',
            time:2
        });
		$("#WECHAT_NAME").focus();
		return false;
	}
	if (check($("#APP_ID").val())) {
		$("#APP_ID").tips({
			side:3,
            msg:"微信APPID不能为空",
            bg:'#AE81FF',
            time:2
        });
		$("#APP_ID").focus();
		return false;
	}
	if (check($("#WECHAT_ID").val())) {
		$("#WECHAT_ID").tips({
			side:3,
            msg:"微信原始ID不能为空",
            bg:'#AE81FF',
            time:2
        });
		$("#WECHAT_ID").focus();
		return false;
	}
	if (check($("#TOKEN").val())) {
		$("#TOKEN").tips({
			side:3,
            msg:"TOKEN不能为空",
            bg:'#AE81FF',
            time:2
        });
		$("#TOKEN").focus();
		return false;
	}
	if (check($("#APPSECRET").val())) {
		$("#APPSECRET").tips({
			side:3,
            msg:"Appsecret不能为空",
            bg:'#AE81FF',
            time:2
        });
		$("#APPSECRET").focus();
		return false;
	} */
	$("#form1").submit();
	$("#zhongxin").css("display","none");
	$("#zhongxin2").show();
	//校验微信名称不能重复
	<%-- var dealerId = $("#DEALER_ID").val();
	var appId = $("#APP_ID").val(); //APPID
	var wechartId = $("#WECHAT_ID").val(); //原始ID
	$.ajax({
		type: "POST",
		url: '<%=path%>/serverask/checkAppIdSigle.do?tm='+new Date().getTime(),
    	data: {
    		"appId":appId,
    		"dealerId":dealerId,
    		"wechartId":wechartId
    	},
		dataType:'json',
		cache: false,
		success: function(data){
			var code= data.returnCode;
			if (code == '1') {
				$("#form1").submit();
				$("#zhongxin").hide();
				$("#zhongxin").css("display","none");
				$("#zhongxin2").show();
			}  else {
				bootbox.alert(data.result);
			}
		}
	}); --%>
}
</script>
</body>
</html>