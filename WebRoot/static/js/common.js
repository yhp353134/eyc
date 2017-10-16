function cleanSelected(id,text){
	$('#'+id+' option').removeAttr("selected");
	var a = $('#'+id+'_chzn').children().eq(0);
	$(a).removeClass("chzn-single");
	$(a).addClass("chzn-single chzn-default");
	$(a).children().eq(0).text(text);
}