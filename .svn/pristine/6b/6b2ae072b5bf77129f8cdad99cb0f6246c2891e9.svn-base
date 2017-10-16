/**
 * Created by mengh on 2017/5/3.
 */
// var ue = UE.getEditor('cm-editeBox');
function launchFullscreen(a) {
    if (a.requestFullscreen) {
        a.requestFullscreen()
    } else {
        if (a.mozRequestFullScreen) {
            a.mozRequestFullScreen()
        } else {
            if (a.msRequestFullscreen) {
                a.msRequestFullscreen()
            } else {
                if (a.webkitRequestFullscreen) {
                    a.webkitRequestFullScreen()
                }
            }
        }
    }
};

function exitFullscreen() {
    if (document.exitFullscreen) {
        document.exitFullscreen()
    } else {
        if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen()
        } else {
            if (document.webkitExitFullscreen) {
                document.webkitExitFullscreen()
            }
        }
    }
};

function fullscreenElement() {
    return document.fullscreenElement || document.webkitCurrentFullScreenElement || document.mozFullScreenElement || null
};
var d = UE.getEditor("editor", {
    toolbars: [
        [
            'source', //源代码
            'undo', //撤销
            'redo', //重做
            'bold', //加粗
            'italic', //斜体
            'underline', //下划线
            'strikethrough', //删除线
            'forecolor', //字体颜色
            'backcolor', //背景色
            'justifyleft', //居左对齐
            'justifycenter', //居中对齐
            'justifyright', //居右对齐
            'justifyjustify', //两端对齐
            'customstyle', //自定义标题
            'paragraph',  //段落
            'inserttitle', //插入标题
            'fontfamily', //字体
            'fontsize'//字号
            
        ],
        [
			'blockquote', //引用
			'pasteplain', //纯文本粘贴模式
			'link', //超链接
			'unlink', //取消链接
			'spechars', //特殊字符
			'insertorderedlist', //有序列表
            'insertunorderedlist', //无序列表
			'simpleupload', //单图上传
			'insertimage', //多图上传
			'attachment',
			'map', 			//地图
			'snapscreen',  //截屏
			'template',	//模板
			'insertcode',
			'emotion', //表情
			'date',		//日期
			'time',		//时间
			'inserttable', //插入表格
			'searchreplace',
			'background',
			'preview',
			'cleardoc' //清空文档
        ]
    ],
    labelMap:{
    },
    topOffset: 0,
    autoFloatEnabled: !1,
    autoHeightEnabled: !1,
    autotypeset: {
        removeEmptyline: !0
    },
    autoHeightEnabled: false,
    allowDivTransToP: false,
    autoFloatEnabled: false,
    enableAutoSave: true,
    catchRemoteImageEnable:false
});

$(function() {
    $("#phoneclose").on("click", function() {
        $("#previewbox").hide()
    }), $("#phone").on("click", function() {
    	$("#preview").html(d.getContent() + '<div><a style="font-size:12px;color:#607fa6" href="#" target="_blank" id="post-user">阅读原文</a> <em style="color:#8c8c8c;font-style:normal;font-size:12px;">阅读 100000+</em><span class="fr"><a style="font-size:12px;color:#607fa6" href="#" target="_blank">我要举报</a></span></div>')	
        "block" == $("#previewbox").css("display") ? $("#previewbox").hide() : $("#previewbox").show();
    })
});
//弹出事件显示处理
d.addListener("click", function(t, evt) {
    evt = evt || window.event;
    var el = evt.target || evt.srcElement;
    if (el.tagName == "IMG") {
        return;
    }
    //选择图文字信息后，弹出操作事件框
    if ($(el).parents('.unieditor').size() > 0) {
        el = $(el).parents('.unieditor:first').get(0);
        if (current_active_v3item) {
            current_active_v3item.removeAttr('style');
        }
        current_active_v3item = $(el);
        current_active_v3item.css({
            'border': '1px dotted rgb(255, 68, 1)',
            'padding': '2px'
        });
        clickPop.render();
        var html = clickPop.formatHtml('' + '<nobr class="otf-poptools">' + '<a onclick="$$.select()" stateful>' + '选中</a>' + '<a onclick="$$._remove()" stateful>' + '删除</a>' + '<a onclick="$$._blank()" stateful>' + '后空行</a>' + '<a onclick="$$._preblank()" stateful>' + '前空行</a>' + '</nobr>');
        var content = clickPop.getDom('content');
        content.innerHTML = html;
        clickPop.anchorEl = el;
        clickPop.showAnchor(clickPop.anchorEl);
        var client = new ZeroClipboard($(clickPop.getDom('content')).find('.copy'));
        client.on('ready', function(event) {
            client.on('copy', function(event) {
                $(clickPop.anchorEl).removeAttr('style');
                event.clipboardData.setData('text/html', $(clickPop.anchorEl).prop('outerHTML'));
                clickPop.hide();
                showSuccessMessage("已成功复制到剪切板");
            });
        });
        var cut_client = new ZeroClipboard($(clickPop.getDom('content')).find('.cut'));
        cut_client.on('ready', function(event) {
            cut_client.on('copy', function(event) {
                $(clickPop.anchorEl).removeAttr('style');
                event.clipboardData.setData('text/html', $(clickPop.anchorEl).prop('outerHTML'));
                clickPop.hide();
                $(clickPop.anchorEl).remove();
                showSuccessMessage("已成功剪切到剪切板");
            });
        });
    } else {
        if (current_active_v3item) {
            current_active_v3item.removeAttr('style');
            current_active_v3item = null;
        }
    }
});
var clickPop = new baidu.editor.ui.Popup({
    content: "",
    editor: d,
    _remove: function() {
        $(clickPop.anchorEl).remove();
        clickPop.hide();
    },
    _copy: function() {
        $(clickPop.anchorEl).prop('outerHTML');
        clickPop.hide();
    },
    select: function() {
        var range = d.selection.getRange();
        range.selectNode(clickPop.anchorEl);
        range.select();
    },
    _blank: function() {
        $('<p><br/></p>').insertAfter(clickPop.anchorEl);
    },
    _preblank: function() {
        $('<p><br/></p>').insertBefore(clickPop.anchorEl);
    },
    _video: function() {
        d.ui._dialogs['insertvideoDialog'] && d.ui._dialogs['insertvideoDialog'].open();
        d.ui._dialogs['insertvideoDialog'].anchorEl = clickPop.anchorEl;
    },
    className: 'edui-bubble'
});
d.ready(function() {
    d.addListener("contentChange", function() {
    	$("#preview").html(d.getContent() + '<div><a style="font-size:12px;color:#607fa6" href="#" target="_blank" id="post-user">阅读原文</a> <em style="color:#8c8c8c;font-style:normal;font-size:12px;">阅读 100000+</em><span class="fr"><a style="font-size:12px;color:#607fa6" href="#" target="_blank">我要举报</a></span></div>')
    })
        }),
    $(".tabs li a").on("click", function() {
        $(this).addClass("current").parent().siblings().each(function() {
            $(this).find("a").removeClass("current")
        }),
            $("#" + $(this).attr("tab")).show().siblings().hide()
    }),
    $(".ItemBox img,.ItemBox audio").each(function() {
        var a = $(this);
        a.attr("src", a.data("src"))
    });
var current_active_v3item = null;
