package com.fh.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
	
	public static void main(String[] args) {
		// 测试
		String a = "您  好  你说大话  g  ";
		System.out.println(a);
		System.out.println(replaceSpaceAll(a));
	}
	
	/**
	 * 检查字符是否时数字
	 */
	public static boolean checkNumber(String str) {
		Pattern pattern = Pattern.compile("^\\d+(\\.\\d+)?$");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * <p>Description: 将字符串转换为指定长度的小数</p>
	 * @param str 要转换的字符串
	 * @param lenth 要保留的小数位数的长度
	 * @return String 
	 */
	public static String parseDubleNum(String str,int lenth){
		StringBuffer form =  new StringBuffer("#.");
		String num = "0";
		for (int i=0; i<lenth; i++) {
			form.append(num);
		}
		double point = Double.parseDouble(str);
		java.text.DecimalFormat df = new DecimalFormat(form.toString());
		return df.format(point);
	}
	
	/***
	 * 将所有字符里面的空格去掉
	 * */
	public static String replaceSpaceAll(String str) {
		return str.replaceAll("\\s*", "");
	}

	/**
	 * 随机生成六位数验证码
	 * 
	 * @return
	 */
	public static int getRandomNum() {
		Random r = new Random();
		return r.nextInt(900000) + 100000;// (Math.random()*(999999-100000)+100000)
	}

	/**
	 * 随机生成四位数验证码
	 * 
	 * @return
	 */
	public static int getRandom4Num() {
		Random r = new Random();
		return r.nextInt(9000) + 1000;// (Math.random()*(9999-1000)+1000)
	}

	/**
	 * 检查map里面的非空值
	 */
	public static String checkObj(Object obj) {
		String obst = obj == null ? "" : obj.toString();
		return obst;
	}
	
	/***
	 * 对象转字符串
	 * **/
	public static String getObjectValue(Object obj) {
		return checkObj(obj);
	}

	/**
	 * 检测字符串是否不为空(null,"","null")
	 * 
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s) {
		return s != null && !"".equals(s) && !"null".equals(s);
	}

	/**
	 * 检测字符串是否为空(null,"","null")
	 * 
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s) || "null".equals(s);
	}

	/**
	 * 字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @param splitRegex
	 *            分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",\\s*");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date) {
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date) {
		if (notEmpty(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		} else {
			return null;
		}
	}
	
	
	public static Date str3Date(String date) {
		if (notEmpty(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		} else {
			return null;
		}
	}
	
	
	

	/**
	 * 按照yyyy-MM-dd 的格式，字符串转日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date str2Dateadd(String date) {
		if (notEmpty(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		} else {
			return null;
		}
	}

	/**
	 * 按照参数format的格式，日期转字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 把时间根据时、分、秒转换为时间段
	 * 
	 * @param StrDate
	 */
	public static String getTimes(String StrDate) {
		String resultTimes = "";

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date now;

		try {
			now = new Date();
			java.util.Date date = df.parse(StrDate);
			long times = now.getTime() - date.getTime();
			long day = times / (24 * 60 * 60 * 1000);
			long hour = (times / (60 * 60 * 1000) - day * 24);
			long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long sec = (times / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

			StringBuffer sb = new StringBuffer();
			// sb.append("发表于：");
			if (hour > 0) {
				sb.append(hour + "小时前");
			} else if (min > 0) {
				sb.append(min + "分钟前");
			} else {
				sb.append(sec + "秒前");
			}

			resultTimes = sb.toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return resultTimes;
	}

	/**
	 * 写txt里的单行内容
	 * 
	 * @param filePath
	 *            文件路径
	 * @param content
	 *            写入的内容
	 */
	public static void writeFile(String fileP, String content) {
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../"; // 项目路径
		filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
		if (filePath.indexOf(":") != 1) {
			filePath = File.separator + filePath;
		}
		try {
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath), "utf-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(content);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		try {
			Pattern regex = Pattern
					.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 检测KEY是否正确
	 * 
	 * @param paraname
	 *            传入参数
	 * @param FKEY
	 *            接收的 KEY
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean checkKey(String paraname, String FKEY) {
		paraname = (null == paraname) ? "" : paraname;
		return MD5.md5(paraname + DateUtil.getDays() + ",fh,").equals(FKEY);
	}

	/**
	 * 读取txt里的单行内容
	 * 
	 * @param filePath
	 *            文件路径
	 */
	public static String readTxtFile(String fileP) {
		try {

			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../"; // 项目路径
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if (filePath.indexOf(":") != 1) {
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding); // 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件,查看此路径是否正确:" + filePath);
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		return "";
	}

	/**
	 * 读取服务器配置文件
	 * 
	 * @param fileP
	 * @return
	 */
	public static String readServerNum(String fileP) {
		try {

			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../"; // 项目路径
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if (filePath.indexOf(":") != 1) {
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding); // 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			} else {
				return "0";
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		return "0";
	}

	/**
	 * 根据url获取文件名称
	 * 
	 * @param url
	 * @return
	 */
	public static String getFileNameByUrl(String url) {
		String fileName = "";
		String suffixes = "avi|mpeg|3gp|mp3|mp4|wav|jpeg|gif|jpg|png|apk|exe|txt|html|htm|java|doc";
		String file = url.substring(url.lastIndexOf('/') + 1);// 截取url最后的数据
		Pattern pat = Pattern.compile("[\\w]+[\\.](" + suffixes + ")");// 正则判断
		Matcher mc = pat.matcher(file);
		while (mc.find()) {
			fileName = mc.group();// 截取文件名后缀名
			System.out.println("fileName:" + fileName);
		}
		return fileName;
	}

	/**
	 * signature 签名加密算法
	 * 
	 * @param decript
	 * @return
	 */
	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 将时间戳转换为日期格式
	 * 
	 * @param dateNum
	 * @return
	 */
	public static String getDateByNum(String dateNum) {
		try {

			Long subscribeTime = Long.parseLong(dateNum);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(new Date(subscribeTime * 1000L));

			return date;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 服务预约站内消息模板
	 */
	public static String getApplyStr(String flag, String cusName, String itemTxt, String dateStr, String result) {
		String str = "";
		if (flag.equals("10")) {// 车主自己提交预约
			str = "<p>尊敬的车主" + cusName + "，您好，您的预约已提交</p><p></p><p>预约项目：" + itemTxt + "</p><p>预约时间：" + dateStr
					+ "</p><p>预约结果：" + result + "</p>";
		} else if (flag.equals("11")) {// Dcrc用户收到的车主预约
			str = "<p>车主" + cusName + "有新的预约</p><p></p><p>预约项目：" + itemTxt + "</p><p>预约时间：" + dateStr + "</p><p>预约结果："
					+ result + "</p>";
		} else if (flag.equals("12")) {// 车主收到处理结果
			str = "<p>尊敬的车主" + cusName + "，您好，您的预约已处理</p><p></p><p>预约项目：" + itemTxt + "</p><p>预约时间：" + dateStr
					+ "</p><p>预约结果：" + result + "</p>";
		}

		return str;
	}

	/**
	 * list 转Str
	 * @param list
	 * @param key  pagedata 的健
	 * @param separator  分隔符
	 * @return
	 */
	public static String listToString(List<PageData> list,String key, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
				sb.append(list.get(i).get(key));
			} else {
				sb.append(list.get(i).get(key));
				sb.append(separator);
			}
		}
		return sb.toString();
	}
	public static String[] ListToStr(List<PageData> list,String key, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
				sb.append(list.get(i).get(key));
			} else {
				sb.append(list.get(i).get(key));
				sb.append(separator);
			}
		}
		return sb.toString().split(",");
	}

}
