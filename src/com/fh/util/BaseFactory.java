package com.fh.util;

import java.util.Date;
import java.util.List;

/**
 * create date :2017年1月10日
 * @author baby_ljie
 *
 */
public class BaseFactory {
	/***
	 * 专门为拼接字符in ('a','b')而上(数组)
	 * @param arr
	 * @return
	 */
	public static String getSqlByarrIn(String [] arr){
		StringBuffer sbTemp=new StringBuffer(); 
		if(arr!=null && arr.length>0){
			int length =arr.length ;
			for (int i = 0; i < length; i++) {
				if(i==length-1){
					sbTemp.append("'"+arr[i]+"'");
				}else{
					sbTemp.append("'"+arr[i]+"',");
				}
			}
		}
		return sbTemp.toString();
	}
	/**
	 * 处理字符串数组
	 * 传入aa,bb,cc类型  返回  ['aa','bb','cc']
	 * @param arr
	 * @return
	 */
	public static String[] getSqlByarrIn(String arr){
		StringBuffer sbTemp=new StringBuffer(); 
		if(Tools.isEmpty(arr)){
			return null;
		}
		String[] aa = arr.split(",");
		if(arr!=null && aa.length>0){
			int length =aa.length ;
			for (int i = 0; i < length; i++) {
				if(i==length-1){
					sbTemp.append("'"+aa[i]+"'");
				}else{
					sbTemp.append("'"+aa[i]+"',");
				}
			}
		}
		return sbTemp.toString().split(",");
	}
	/**
	 * 处理字符串数组
	 * 传入aa,bb,cc类型  返回  [aa,bb,cc]
	 * @param arr
	 * @return
	 */
	public static String[] getSqlByarrIn2(String arr){
		StringBuffer sbTemp=new StringBuffer(); 
		if(Tools.isEmpty(arr)){
			return null;
		}
		String[] aa = arr.split(",");
		if(arr!=null && aa.length>0){
			int length =aa.length ;
			for (int i = 0; i < length; i++) {
				if(i==length-1){
					sbTemp.append(aa[i]);
				}else{
					sbTemp.append(aa[i]+",");
				}
			}
		}
		return sbTemp.toString().split(",");
	}
	/***
	 * 专门为拼接字符in ('a','b')而上(数组)
	 * @param arr
	 * @return
	 */
	public static String getSqlByarrIn2(String [] arr){
		StringBuffer sbTemp=new StringBuffer(); 
		if(arr!=null && arr.length>0){
			int length =arr.length ;
			for (int i = 0; i < length; i++) {
				if(i==length-1){
					sbTemp.append(arr[i]);
				}else{
					sbTemp.append(arr[i]+",");
				}
			}
		}
		return sbTemp.toString();
	}
	/**
	 * 传入aa,bb,cc类型  返回  [aa,bb,cc]   ; 分割的字符串
	 * @param arr
	 * @return
	 */
	public static String[] getSqlByarrIn3(String arr){
		StringBuffer sbTemp=new StringBuffer(); 
		if(Tools.isEmpty(arr)){
			return null;
		}
		String[] aa = arr.split(";");
		if(arr!=null && aa.length>0){
			int length =aa.length ;
			for (int i = 0; i < length; i++) {
				if(i==length-1){
					sbTemp.append("'"+aa[i]+"'");
				}else{
					sbTemp.append("'"+aa[i]+"',");
				}
			}
		}
		return sbTemp.toString().split(",");
	}
	
	public static String getStringbyList(List<PageData> list,String key) {
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i).get(key)).append(",");
		}
		return  sb.toString();
	}

}
