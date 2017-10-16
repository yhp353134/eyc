package com.fh.util;

import java.io.*;
import java.util.*;

public class GetMessageInfo {
	private static Logger logger = Logger.getLogger(GetMessageInfo.class);
	private static Map<Object, Object> map = null;
	static {
		setMap(getMessageInfoByKey());
	}

	public static Map<Object, Object> getMessageInfoByKey() {
		// 生成输入流
		InputStream ins = GetMessageInfo.class.getResourceAsStream("msg.properties");
		// 生成properties对象
		Properties p = new Properties();
		try {
			p.load(ins);
			Map<Object, Object> map = p;
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String args[]) {
		// 生成输入流
		InputStream ins = GetMessageInfo.class.getResourceAsStream("msg.properties");
		// 生成properties对象
		Properties p = new Properties();
		try {
			p.load(ins);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 输出properties文件的内容
		logger.info("name:" + p.getProperty(""));
		// System.out.println("name:" + p.getProperty(""));
		// System.out.println("password:" + p.getProperty("10010002"));
	}

	public static Map<Object, Object> getMap() {
		return map;
	}

	public static void setMap(Map<Object, Object> map) {
		GetMessageInfo.map = map;
	}
	
}
