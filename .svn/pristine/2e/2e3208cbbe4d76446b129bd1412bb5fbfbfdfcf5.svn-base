/**
 * JsonUtils.java
 * Created at 2016-07-07
 * Created by yuhaiping
 * Copyright (C) 2016 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.fh.util;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Description: json工具类
 */
public class JsonUtils {

	/**
	 * 将map集合转换为json语句表示
	 *
	 * @param map
	 *            集合
	 * @return 得到的Map解析的json语句
	 */
	public static String mapToJson(Map<?, ?> map) {
		// 将集合解析为 json对象语句
		JSONObject jsonObject = JSONObject.fromObject(map);
		// 返回json语句
		return jsonObject.toString();
	}

	/**
	 * <p>
	 * Description: 将list转换为json
	 * </p>
	 * 
	 * @param list
	 *            参数
	 * @return string json字符串
	 */
	public static String listToJson(List<?> list) {
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}

	/**
	 * 字符串转json对象为map
	 * 
	 * @param jsonStr
	 *            字符串
	 * @return JSONObject 返回的对象
	 */
	public static JSONObject strToJson(String jsonStr) {
		JSONObject jsStr = JSONObject.fromObject(jsonStr);
		return jsStr;
	}
	
	/**
	 * 字符串转json对象为List
	 * 
	 * @param jsonStr
	 *            字符串
	 * @return JSONObject 返回的对象
	 */
	public static JSONArray strToJsonList(String jsonStr) {
		JSONArray jsArr =JSONArray.fromObject(jsonStr);
		return jsArr;
	}
	
}
