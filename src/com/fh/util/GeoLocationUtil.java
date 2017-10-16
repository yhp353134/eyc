package com.fh.util;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.net.URL;  
import java.net.URLEncoder;  
import java.util.HashMap;  
import java.util.Map;

import net.sf.json.JSONObject;



public class GeoLocationUtil {
	public static final String APPKEY = "ChnFOO009OcxDctVF6iU11fnbR3X49xE"; 
	
	/** 
     * 返回输入地址的经纬度坐标 
     * key lng(经度),lat(纬度) 
     */  
    public static Map<String,String> getGeocoderLatitude(String address){  
        BufferedReader in = null;  
        try {  
            //将地址转换成utf-8的16进制  
            address = URLEncoder.encode(address, "UTF-8");  
            URL tirc = new URL("http://api.map.baidu.com/geocoder/v2/?address="+ address +"&output=json&ak="+ APPKEY);  
              
            in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));  
            String res;  
            StringBuilder sb = new StringBuilder("");  
            while((res = in.readLine())!=null){  
                sb.append(res.trim());  
            }  
            String str = sb.toString();  
            Map<String,String> map = null;  

            if(Tools.notEmpty(str)){
                int lngStart = str.indexOf("lng\":");  
                int lngEnd = str.indexOf(",\"lat");  
                int latEnd = str.indexOf("},\"precise");  
                if(lngStart > 0 && lngEnd > 0 && latEnd > 0){  
                    String lng = str.substring(lngStart+5, lngEnd);  
                    String lat = str.substring(lngEnd+7, latEnd);  
                    map = new HashMap<String,String>();  
                    map.put("lng", lng);  
                    map.put("lat", lat);  
                    return map;  
                }  
            }  
        }catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }
    
    
    /**
     * 根据 经纬度获取省份城市
     * @param lat
     * @param lng
     * @return
     */
    public static Map<String,String> getGeocoderAddress(String lat,String lng){  
        BufferedReader in = null;  
        try {  
            //pois=0表示不获取周边信息
            URL tirc = new URL("http://api.map.baidu.com/geocoder/v2/?location="+lat+","+lng+"&output=json&pois=0&ak="+ APPKEY);  
              
            in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));  
            String res;  
            StringBuilder sb = new StringBuilder("");  
            while((res = in.readLine())!=null){  
                sb.append(res.trim());  
            }  
            String str = sb.toString();  
            Map<String,String> map = null;  
            if(Tools.notEmpty(str)){  
            	JSONObject addJson = JSONObject.fromObject(str);
//            	System.out.println("json1=="+addJson)
            	JSONObject myAdd = addJson.getJSONObject("result").getJSONObject("addressComponent");
            	
                map = new HashMap<String,String>(); 
                map.put("province", myAdd.getString("province"));  
                map.put("city", myAdd.getString("city"));  
                return map;
            }  
        }catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }
    
    public static void main(String args[]){  
        Map<String, String> json = GeoLocationUtil.getGeocoderLatitude("重庆市南岸区");  
        System.out.println("lng : "+json.get("lng"));  
        System.out.println("lat : "+json.get("lat"));  
        
        Map<String, String> addressMap = GeoLocationUtil.getGeocoderAddress("29.541514618902509", "106.66717849903546");
        System.out.println("省份 : "+addressMap.get("province"));  
        System.out.println("城市 : "+addressMap.get("city"));
    }

}
