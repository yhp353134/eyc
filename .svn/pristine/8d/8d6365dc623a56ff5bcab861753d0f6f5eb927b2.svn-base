var urlpath="";
CheckUrlPathMethod();
function AccordionMenu(options) {
    this.config = {
        containerCls        : '.cm-menu',                // 外层容器
        menuArrs            :  '',                         //  JSON传进来的数据
        type                :  'click',                    // 默认为click 也可以mouseover
        renderCallBack      :  null,                       // 渲染html结构后回调
        clickItemCallBack   : function (e) {
            //点击事件处理
            var html =e[0];
            //当点击新的菜单选项的时候清空列表，刷新数据
            $(".optionUl").html("");
            //获取数据集
            GetUlInitData(html.id);
            // console.log(html.id);
        }                         // 每点击某一项时候回调
    };
    this.cache = {

    };
    this.init(options);
}


AccordionMenu.prototype = {

    constructor: AccordionMenu,

    init: function(options){
        this.config = $.extend(this.config,options || {});
        var self = this,
            _config = self.config,
            _cache = self.cache;

        // 渲染html结构
        $(_config.containerCls).each(function(index,item){

            self._renderHTML(item);

            // 处理点击事件
            self._bindEnv(item);
        });
    },
    _renderHTML: function(container){
        var self = this,
            _config = self.config,
            _cache = self.cache;
        var ulhtml = $('<ul></ul>');
        $(_config.menuArrs).each(function(index,item){
            var lihtml = $('<li><h2 id="000">'+item.name+'</h2></li>');

            if(item.submenu && item.submenu.length > 0) {
                self._createSubMenu(item.submenu,lihtml);
            }
            $(ulhtml).append(lihtml);
        });
        $(container).append(ulhtml);

        _config.renderCallBack && $.isFunction(_config.renderCallBack) && _config.renderCallBack();

        // 处理层级缩进
        self._levelIndent(ulhtml);
    },
    /**
     * 创建子菜单
     * @param {array} 子菜单
     * @param {lihtml} li项
     */
    _createSubMenu: function(submenu,lihtml){
        var self = this,
            _config = self.config,
            _cache = self.cache;
        var subUl = $('<ul></ul>'),
            callee = arguments.callee,
            subLi;

        $(submenu).each(function(index,item){
            var url = item.id || 'javascript:void(0)';

            subLi = $('<li><a id="'+url+'">'+item.name+'</a></li>');
            if(item.submenu && item.submenu.length > 0) {
                //
                // $(subLi).children('a').prepend('<img src="img/blank.gif" alt=""/>');
                callee(item.submenu, subLi);
            }
            $(subUl).append(subLi);
        });
        $(lihtml).append(subUl);
    },
    /**
     * 处理层级缩进
     */
    _levelIndent: function(ulList){
        var self = this,
            _config = self.config,
            _cache = self.cache,
            callee = arguments.callee;

        var initTextIndent = 2,
            lev = 1,
            $oUl = $(ulList);

        while($oUl.find('ul').length > 0){
            initTextIndent = parseInt(initTextIndent,10)-0.6  + 'em';
            $oUl.children().children('ul').addClass('lev-' + lev)
                .children('li').css('text-indent', initTextIndent);
            $oUl = $oUl.children().children('ul');
            lev++;
        }
        $(ulList).find('ul').hide();
        $(ulList).find('ul:first').show();
    },
    /**
     * 绑定事件
     */
    _bindEnv: function(container) {
        var self = this,
            _config = self.config;
        $('h2,a',container).unbind(_config.type);
        $('h2,a',container).bind(_config.type,function(e){

            if($(this).siblings('ul').length > 0) {
                $(this).siblings('ul').slideToggle('slow').end().children('img').toggleClass('unfold');
            }

            $(this).parent('li').siblings().find('ul').hide().end().find('img.unfold').removeClass('unfold');
            $(this).parents("ul").find("a").removeClass("chooesbox").addClass("nochooesbox");
            if($(this)[0].id=="000"){

            }else{
                $(this).removeClass("nochooesbox").addClass("chooesbox");
                _config.clickItemCallBack && $.isFunction(_config.clickItemCallBack) && _config.clickItemCallBack($(this));
            }
        });
    }
};

function GetUlInitData(Id) {
    $.getJSON("data/"+Id+".json",function(data){
        var str="";
        // console.log(data);
        $.each(data.list,function(i,n){
            str+="<li class='ItemBox'><div>"+n["item"]+"</div></li>";
        });
        $(".optionUl").append(str);
            $(".ItemBox").on("click",function(){
                // UE.getEditor('editor').execCommand("insertHtml", '<section class="unieditor">' + $(this).html() + "</section><br />")
                UE.getEditor('editor').execCommand("insertHtml", '<section class="unieditor">' + replaceHtmlUrl($(this).html()) + "</section>")
                }
            );
    });
}
//效验URLPath
function CheckUrlPathMethod() {
    //如果有参数就直接返回参数
    if(urlpath != ""){
    	alert(3);
        return urlpath;
    } else {
    	$.getJSON("UrlConfig.json",function(data){
            urlpath=data.url;
        });
    }
}

function replaceHtmlUrl(value) {
	//下面是获取服务器的地址
	var curWwwPath=window.document.location.href; 
	var pathName=window.document.location.pathname; 
	var pos=curWwwPath.indexOf(pathName); 
	var localhostPaht=curWwwPath.substring(0,pos); 
	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1); 
	var pt = localhostPaht+projectName+urlpath;
    var changeHtml=value;
    if(value.indexOf("url(img")>0){
        changeHtml = changeHtml.replace(/url\(img/g,"url("+pt);
//        console.log("替换结果url="+changeHtml);
    }
    if(value.indexOf("src=\"img")>0){
    	
        changeHtml = changeHtml.replace(/src=\"img/g,"src=\""+pt);
//        console.log("替换结果1=src"+changeHtml);
    }
    // console.log("修改过后的="+changeHtml);
    return changeHtml;
}