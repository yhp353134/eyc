package com.fh.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.map.util.JSONPObject;

public class AppUtil  {
	
	protected static Logger logger = Logger.getLogger(AppUtil.class);
	public static void main(String[] args) {
		PageData pd = new PageData();
		pd.put("username", "zhangsan");
		checkParam("registered", pd);
	}
	//检查参数是否完整
	public static boolean checkParam(String method, PageData pd){
		boolean result = false;
		int falseCount = 0;
		String[] paramArray = new String[20];
		String[] valueArray = new String[20];
		String[] tempArray  = new String[20];  //临时数组
		if(method=="registered"){// 注册
			paramArray = Const.APP_REGISTERED_PARAM_ARRAY;  //参数
			valueArray = Const.APP_REGISTERED_VALUE_ARRAY;  //参数名称
		}else if(method=="getAppuserByUsernmae"){//根据用户名获取会员信息
			paramArray = Const.APP_GETAPPUSER_PARAM_ARRAY;  
			valueArray = Const.APP_GETAPPUSER_VALUE_ARRAY;
		}
		int size = paramArray.length;
		for(int i=0;i<size;i++){
			String param = paramArray[i];
			if(!pd.containsKey(param)){
				tempArray[falseCount] = valueArray[i]+"--"+param;
				falseCount += 1;
			}
		}
		if(falseCount>0){
			logger.error(method+"接口，请求协议中缺少 "+falseCount+"个 参数");
			for(int j=1;j<=falseCount;j++){
				logger.error("   第"+j+"个："+ tempArray[j-1]);
			}
		} else {
			result = true;
		}
		return result;
	}
	
	/**
	 * 设置分页的参数
	 * @param pd
	 * @return
	 */
	public static PageData setPageParam(PageData pd){
		String page_now_str = pd.get("page_now").toString();
		int pageNowInt = Integer.parseInt(page_now_str)-1;
		String page_size_str = pd.get("page_size").toString(); //每页显示记录数
		int pageSizeInt = Integer.parseInt(page_size_str);
		String page_now = pageNowInt+"";
		String page_start = (pageNowInt*pageSizeInt)+"";
		pd.put("page_now", page_now);
		pd.put("page_start", page_start);
		return pd;
	}
	
	/**
	 * 设置list中的distance
	 */
	public static List<PageData>  setListDistance(List<PageData> list, PageData pd){
		List<PageData> listReturn = new ArrayList<PageData>();
		String user_longitude = "";
		String user_latitude = "";
		
		try{
			user_longitude = pd.get("user_longitude").toString(); //"117.11811";
			user_latitude  = pd.get("user_latitude").toString();  //"36.68484";
		} catch(Exception e){
			logger.error("缺失参数--user_longitude和user_longitude");
			logger.error("lost param：user_longitude and user_longitude");
		}
		
		PageData pdTemp = new PageData();
		int size = list.size();
		for(int i=0;i<size;i++){
			pdTemp = list.get(i);
			String longitude = pdTemp.get("longitude").toString();
			String latitude = pdTemp.get("latitude").toString();
			String distance = MapDistance.getDistance(
						user_longitude,	user_latitude,
						longitude,		latitude
					);
			pdTemp.put("distance", distance);
			pdTemp.put("size", distance.length());
			listReturn.add(pdTemp);
		}
		return listReturn;
	}
	
	public static Object returnObject(PageData pd, Map map){
		if(pd.containsKey("callback")){
			String callback = pd.get("callback").toString();
			return new JSONPObject(callback, map);
		}else{
			return map;
		}
	}
	/**
	 * 接口返回时 默认的map对象
	 * @return map
	 */
	public static Map<String, Object> getResultMap(){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "1000");
		result.put("msg", AppUtil.getMessageInfoByKey("1000"));
		result.put("result", "");
		return result;
	}
	/**
	 * 根据key，获得对应的值
	 * @param key
	 * @return
	 */
	public static String getMessageInfoByKey(String key){
		if(Tools.isEmpty(key)){
			return "";
		}
		Map<Object,Object> map = GetMessageInfo.getMap();
		String value = (String) map.get(key);
		return value;
	}
	
//	public static Map<String, Object> returnMap(Map<String, Object> resultMap) {
//		Map<String,Object> result = new HashMap<String,Object>();
//		for (Entry<String, Object> entry : resultMap.entrySet()) {
//			if (entry.getValue() instanceof Map) {
//				result.put(entry.getKey(), returnMap((Map<String, Object>) entry.getValue()));
//			} else {
//				if (null == entry.getValue() || "null".equals(entry.getValue())) {
//					result.put(entry.getKey(), "");
//				} else {
//					if(entry.getValue() instanceof Long){
//						result.put(entry.getKey(), ""+entry.getValue()+"");
//					}else{
//						result.put(entry.getKey(), entry.getValue());
//					}
//				}
//			}
//		}
//		return result;
//	}
	public static Map<String, Object> returnMap(Map<String, Object> resultMap) {
		Map<String,Object> result = new HashMap<String,Object>();
		for (Entry<String, Object> entry : resultMap.entrySet()) {
			if (entry.getValue() instanceof Map) {
				result.put(entry.getKey(), returnMap((Map<String, Object>) entry.getValue()));
			} else if (entry.getValue() instanceof ArrayList) {
					Object obj = entry.getValue();
					List list = (ArrayList)obj;
					List myList = new ArrayList();
					
					for(int i=0;i<list.size();i++){
						if(list.get(i) instanceof Map){
							myList.add(returnMap((Map<String, Object>) list.get(i)));
						}
					}
					result.put(entry.getKey(), myList);
			} else {
					if (null == entry.getValue() || "null".equals(entry.getValue())) {
						result.put(entry.getKey(), "");
					} else {
						if(entry.getValue() instanceof Long){
							result.put(entry.getKey(), ""+entry.getValue()+"");
						}else{
							result.put(entry.getKey(), entry.getValue());
						}
					}
			}

		}
		return result;
	}
	/**
	 * 微信-下载媒体文件
	 * @param accessToken
	 * @param mediaId
	 * @param savePath
	 * @return
	 */
	public static InputStream downloadMedia(String accessToken, String mediaId) {
		InputStream bis=null;
		// 拼接请求地址
		String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
		System.out.println(requestUrl);
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			bis = conn.getInputStream();
			//bis.close();
			
			conn.disconnect();
		} catch (Exception e) {
			String error = String.format("下载媒体文件失败：%s", e);
			System.out.println(error);
		}
		return bis;
	}
	/**
	 * 微信-获取文件类型
	 * @param accessToken
	 * @param mediaId
	 * @return
	 */
	public static String getFileType(String accessToken, String mediaId) {
		String fileType="";
		// 拼接请求地址
		String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
		System.out.println(requestUrl);
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			fileType = conn.getHeaderField("Content-Type");
			fileType=fileType.split("/")[1];
			conn.disconnect();
		} catch (Exception e) {
			String error = String.format("下载媒体文件失败：%s", e);
			System.out.println(error);
		}
		return fileType;
	}
	/**
	 * 微信-获取连接
	 * @param accessToken
	 * @param mediaId
	 * @return
	 */
	public static HttpURLConnection getUrlConnection(String accessToken, String mediaId) {
		HttpURLConnection conn=null;
		// 拼接请求地址
		String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
		System.out.println(requestUrl);
		try {
			URL url = new URL(requestUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			
		} catch (Exception e) {
			String error = String.format("下载媒体文件失败：%s", e);
			System.out.println(error);
		}
		return conn;
	}   	
}
