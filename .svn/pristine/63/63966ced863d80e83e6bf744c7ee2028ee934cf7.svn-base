$(function() {
	if(typeof($.cookie('menusf')) == "undefined"){
		$("#sidebar").attr("class","menu-min");
	}else{
		$("#sidebar").attr("class","");
	}
});

//保存缩放菜单状态
function menusf(){
	if(document.getElementsByName('menusf')[0].checked){
		$.cookie('menusf', '', { expires: -1 });
		$("#sidebar").attr("class","menu-min");
	}else{
		$.cookie('menusf', 'ok');
		$("#sidebar").attr("class","");
	}
	
	//当前页为主页时 那么缩放菜单时需要刷新页面
	var tabNmae =  $(window.frames["mainFrame"].document).find("#tab1_index1").find(".tab_item2_selected");
	if (tabNmae != null && tabNmae != '' && tabNmae !='undefind') {
		var tabs = $(tabNmae).html();
		if(tabs.indexOf("主页") >= 0)
		{
			$(window.frames["mainFrame"].document).contents().find("#page_tab1_index1")[0].contentWindow.reloadReport();
		}
	}
}
