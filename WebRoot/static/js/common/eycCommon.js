//获取数据字典中文名
function setDataName(dataCode){ 
	if (checkNullStr(dataCode)) {
		document.write('');
	} else {
		document.write(getDateName(dataCode));
	}
}
function getDateName(dataCode){ 
	var itemValue = null;
	for(var i=0;i<codeData.data.length;i++){
		if(codeData.data[i].datakey == dataCode){
			itemValue= codeData.data[i].dataname;
		}
	}
	return itemValue==null?"":itemValue;
}

//创建下拉框里面的值
function setSelectOption(groupCode,isSelect,selectedValue) {
	document.write(getDataByGroup(groupCode,isSelect,selectedValue));
}
function getDataByGroup(groupCode,isSelect,selectedValue) {
	var tempVal = null;
	if (isSelect == true) {
		tempVal =tempVal+ '<option value="">--请选择--</option><br/>';
	}
	for(var j=0;j<codeData.data.length;j++){
		if(codeData.data[j].datagroup == groupCode){
			if (codeData.data[j].datakey == selectedValue) {
				tempVal = tempVal + '<option value="'+codeData.data[j].datakey+'" selected="selected">'+codeData.data[j].dataname+'</option><br/>';
			} else {
				tempVal = tempVal + '<option value="'+codeData.data[j].datakey+'">'+codeData.data[j].dataname+'</option><br/>';
			}
		}
	}
	return tempVal;
}

//清除下拉框
function cleanSelected(id,text){
	$('#'+id+' option').removeAttr("selected");
	var a = $('#'+id+'_chzn').children().eq(0);
	$(a).removeClass("chzn-single");
	$(a).addClass("chzn-single chzn-default");
	$(a).children().eq(0).text(text);
}
//下拉框设置默认样式
function setSelectStyle(id) {
	$('#'+trim(id)+'_chzn').children().eq(0).addClass("chzn-single chzn-default");
}

//删除左右两端的空格
function trim(str){ 
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

//检查字符串
function checkNullStr(objStr) {
	if (objStr == null || objStr == '' || objStr == 'undefind' || objStr.length == 0) {
		return true;
	} else {
		return false;
	}
}