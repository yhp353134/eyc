package com.fh.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 获取html中图片的url
 * @author baby_ljie
 *
 */
public class ImageUtil {
	
	// 获取img标签正则  
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>"; 
    private static final String IMGURL_REG1 = "backgroud.*url(.*?)"; 
    private static final String IMGURL_REG2= "url\\(.*?\\)"; 
    private static final String IMGURL_REG3= "src=\".*?\""; 
 // 获取src路径的正则  
    private static final String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)"; 
    /**
     * url
     * @param htmlStr
     * @return
     */
	public static List<String> getImgStrByUrl(String htmlStr){      
       Matcher matcher = Pattern.compile(IMGURL_REG2).matcher(htmlStr);  
       List<String> listImgUrl = new ArrayList<String>();  
       while (matcher.find()) {  
    	   String group = matcher.group();
           listImgUrl.add(group.replaceAll("url\\(", "").replaceAll("\\)", ""));  
       }  
           return listImgUrl;      
    }  
	/**
	 * src
	 * @param htmlStr
	 * @return
	 */
	public static List<String> getImgStrBySrc(String htmlStr){    
		 Matcher matcher1 = Pattern.compile(IMGURL_REG3).matcher(htmlStr);  
		 List<String> listImgUrl = new ArrayList<String>();
	       while (matcher1.find()) {  
	    	   String group = matcher1.group();
//	    	   listImgUrl.add(group);  
	    	   listImgUrl.add(group.replaceAll("src=\"", "").replaceAll("\"", ""));  
	       }  
	           return listImgUrl;  
	}
	
	public static List<String> getImgStrByUrlAndSrc(String htmlStr){      
	       Matcher matcher = Pattern.compile(IMGURL_REG2).matcher(htmlStr);  
	       List<String> listImgUrl = new ArrayList<String>();  
	       while (matcher.find()) {  
	    	   String group = matcher.group();
	           listImgUrl.add(group.replaceAll("url\\(", "").replaceAll("\\)", "").replaceAll("&quot;", ""));  
	       }  
	  	   Matcher matcher1 = Pattern.compile(IMGURL_REG3).matcher(htmlStr);  
	       while (matcher1.find()) {  
	    	   String group = matcher1.group();
//	    	   listImgUrl.add(group);  
	    	   listImgUrl.add(group.replaceAll("src=\"", "").replaceAll("\"", "").replaceAll("&quot;", ""));  
	       }  
	           return listImgUrl;      
	    }  
	
	


}
