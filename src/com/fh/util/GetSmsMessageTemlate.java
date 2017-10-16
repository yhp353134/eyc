package com.fh.util;

import java.io.*;
import java.util.*;

/***
 * 读取短信配置类
 * */
public class GetSmsMessageTemlate {
	
	private static Map<Object,Object> map=null;
	static{
		setMap(getMessageInfoByKey());
	}
	
	public static Map<Object,Object> getMessageInfoByKey(){
		 // 生成输入流  
        InputStream ins=GetSmsMessageTemlate.class.getResourceAsStream("smsconfig.properties");  
        // 生成properties对象  
        Properties p = new Properties();  
        try {  
            p.load(ins); 
            Map<Object,Object> map = p;
            return map;
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;
	}

	public static Map<Object,Object> getMap() {
		return map;
	}

	public static void setMap(Map<Object,Object> map) {
		GetSmsMessageTemlate.map = map;
	}  
}
