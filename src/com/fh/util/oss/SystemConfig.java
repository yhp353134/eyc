package com.fh.util.oss;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemConfig {
	
	private static final String CONFIG_PROPERTIES="config.properties";

	public static String getConfigResource(String key) throws IOException{
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		InputStream in = loader.getResourceAsStream(CONFIG_PROPERTIES);
		properties.load(in);
		String value = properties.getProperty(key);
		value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
		in.close();
		return value;
	}
	
	/**
	 * 获取微信配置信息
	 * @param key
	 * @return
	 */
	public static String getConfigWechat(String key){
		String value="";
		try{
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		InputStream in = loader.getResourceAsStream(CONFIG_PROPERTIES);
		properties.load(in);
		value = properties.getProperty(key);
		value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
		in.close();
		
		}catch(Exception e){
			
		}
		return value;
	}
}

