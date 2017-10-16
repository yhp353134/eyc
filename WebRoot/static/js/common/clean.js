//清除
function clean() {
	$("input[type='text']").each(function(){
		$(this).removeAttr("value");
	});
	$("select").each(function() {
		var id = $(this).attr("id");
		var text = $(this).attr("data-placeholder");
		$('#' + id + ' option').removeAttr("selected");
		var a = $('#' + id + '_chzn').children().eq(0);
		$(a).removeClass("chzn-single");
		$(a).addClass("chzn-single chzn-default");
		$(a).children().eq(0).text(text);
	});
};