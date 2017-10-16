package com.fh.util;

import org.apache.commons.lang.StringUtils;

public class ReturnCode {
	
	public static String getErrMes(Integer errcode){
		String errmes = "";
		switch(errcode){
		case -1 : errmes  =  "系统繁忙";break;
		case 0 : errmes  =  "请求成功";break;
		case 40001 : errmes  =  "获取access_token时AppSecret错误，或者access_token无效";break;
		case 40002 : errmes  =  "不合法的凭证类型";break;
		case 40003 : errmes  =  "不合法的OpenID";break;
		case 40004 : errmes  =  "不合法的媒体文件类型";break;
		case 40005 : errmes  =  "不合法的文件类型";break;
		case 40006 : errmes  =  "不合法的文件大小";break;
		case 40007 : errmes  =  "不合法的媒体文件id";break;
		case 40008 : errmes  =  "不合法的消息类型";break;
		case 40009 : errmes  =  "不合法的图片文件大小";break;
		case 40010 : errmes  =  "不合法的语音文件大小";break;
		case 40011 : errmes  =  "不合法的视频文件大小";break;
		case 40012 : errmes  =  "不合法的缩略图文件大小";break;
		case 40013 : errmes  =  "不合法的APPID";break;
		case 40014 : errmes  =  "不合法的access_token";break;
		case 40015 : errmes  =  "不合法的菜单类型";break;
		case 40016 : errmes  =  "不合法的按钮个数";break;
		case 40017 : errmes  =  "不合法的按钮个数";break;
		case 40018 : errmes  =  "不合法的按钮名字长度";break;
		case 40019 : errmes  =  "不合法的按钮KEY长度";break;
		case 40020 : errmes  =  "不合法的按钮URL长度";break;
		case 40021 : errmes  =  "不合法的菜单版本号";break;
		case 40022 : errmes  =  "不合法的子菜单级数";break;
		case 40023 : errmes  =  "不合法的子菜单按钮个数";break;
		case 40024 : errmes  =  "不合法的子菜单按钮类型";break;
		case 40025 : errmes  =  "不合法的子菜单按钮名字长度";break;
		case 40026 : errmes  =  "不合法的子菜单按钮KEY长度";break;
		case 40027 : errmes  =  "不合法的子菜单按钮URL长度";break;
		case 40028 : errmes  =  "不合法的自定义菜单使用用户";break;
		case 40029 : errmes  =  "不合法的oauth_code";break;
		case 40030 : errmes  =  "不合法的refresh_token";break;
		case 40031 : errmes  =  "不合法的openid列表";break;
		case 40032 : errmes  =  "不合法的openid列表长度";break;
		case 40033 : errmes  =  "不合法的请求字符，不能包含\\uxxxx格式的字符";break;
		case 40035 : errmes  =  "不合法的参数";break;
		case 40038 : errmes  =  "不合法的请求格式";break;
		case 40039 : errmes  =  "不合法的URL长度";break;
		case 40050 : errmes  =  "不合法的分组id";break;
		case 40051 : errmes  =  "分组名字不合法";break;
		case 40130 : errmes  =  "至少需要同时发送两个openid";break;
		case 41001 : errmes  =  "缺少access_token参数";break;
		case 41002 : errmes  =  "缺少appid参数";break;
		case 41003 : errmes  =  "缺少refresh_token参数";break;
		case 41004 : errmes  =  "缺少secret参数";break;
		case 41005 : errmes  =  "缺少多媒体文件数据";break;
		case 41006 : errmes  =  "缺少media_id参数";break;
		case 41007 : errmes  =  "缺少子菜单数据";break;
		case 41008 : errmes  =  "缺少oauth code";break;
		case 41009 : errmes  =  "缺少openid";break;
		case 42001 : errmes  =  "access_token超时";break;
		case 42002 : errmes  =  "refresh_token超时";break;
		case 42003 : errmes  =  "oauth_code超时";break;
		case 43001 : errmes  =  "需要GET请求";break;
		case 43002 : errmes  =  "需要POST请求";break;
		case 43003 : errmes  =  "需要HTTPS请求";break;
		case 43004 : errmes  =  "需要接收者关注";break;
		case 43005 : errmes  =  "需要好友关系";break;
		case 44001 : errmes  =  "多媒体文件为空";break;
		case 44002 : errmes  =  "POST的数据包为空";break;
		case 44003 : errmes  =  "图文消息内容为空";break;
		case 44004 : errmes  =  "文本消息内容为空";break;
		case 45001 : errmes  =  "多媒体文件大小超过限制";break;
		case 45002 : errmes  =  "消息内容超过限制";break;
		case 45003 : errmes  =  "标题字段超过限制";break;
		case 45004 : errmes  =  "描述字段超过限制";break;
		case 45005 : errmes  =  "链接字段超过限制";break;
		case 45006 : errmes  =  "图片链接字段超过限制";break;
		case 45007 : errmes  =  "语音播放时间超过限制";break;
		case 45008 : errmes  =  "图文消息超过限制";break;
		case 45009 : errmes  =  "接口调用超过限制";break;
		case 45010 : errmes  =  "创建菜单个数超过限制";break;
		case 45015 : errmes  =  "回复时间超过限制";break;
		case 45016 : errmes  =  "系统分组，不允许修改";break;
		case 45017 : errmes  =  "分组名字过长";break;
		case 45018 : errmes  =  "分组数量超过上限";break;
		case 46001 : errmes  =  "不存在媒体数据";break;
		case 46002 : errmes  =  "不存在的菜单版本";break;
		case 46003 : errmes  =  "不存在的菜单数据";break;
		case 46004 : errmes  =  "不存在的用户";break;
		case 47001 : errmes  =  "解析JSON/XML内容错误";break;
		case 48001 : errmes  =  "api功能未授权";break;
		case 50001 : errmes  =  "用户未授权该api";break;
		
		}
		return errmes;	
	}

	public static String getErrMes(String errcode) {
		if (StringUtils.isEmpty(errcode)) {
			return "errcode为空";
		}
		String errmes = getErrMes(Integer.parseInt(errcode));
		return errmes;
	}
}
