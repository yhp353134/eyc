package com.fh.util;

import java.util.Random;


/**
 * 主键统一生产方法
 * @author Administrator
 *
 */
public class PrimaryUtil {
	//种子数
	public static long intseed = 10000;
	//服务器编号，数据库为0，服务器从1到9，默认1
	public static String intservername = String.valueOf(Tools.readServerNum(Const.SERVERNUM));

	/**
	 * 
	 * @Title: getKey18BySeed
	 * @Description: 13位时间戳截去前3位 + 1位服务器编号 + 7位内存中的种子（数初始1000000）加1 = 18位数字，
	 *               如果种子数超过9999990，则重置种子数=1000000
	 *               服务器编号从配置文件jdbc.properties中取得
	 * @author chensh (2017-04-07)
	 * @return 18位主键数字减少两位
	 */
	public static long getPrimary() {
		if (intseed > 99990)
			intseed = 10000;
		String intkey = String.valueOf(System.currentTimeMillis());
		String pkStr=intkey.substring(3, intkey.length())+ intservername;
		synchronized (PrimaryUtil.class) {
			return Long.parseLong(pkStr + String.valueOf(++intseed));
		}
	}
	
	/****
	 * 生成随机字符串
	 * */
	public static String getRandomStr(int Len) {
        String[] baseString = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g",
                "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A",
                "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                "V", "W", "X", "Y", "Z" };
        Random random = new Random();
        long randomNum = random.nextLong();
        int length = baseString.length;
        String randomString = "";
        for (int i = 0; i < length; i++) {
            randomString += baseString[random.nextInt(length)];
        }
        random = new Random(System.currentTimeMillis() + randomNum);
        String resultStr = "";
        for (int i = 0; i < Len; i++) {
            resultStr += randomString.charAt(random.nextInt(randomString.length() - 1));
        }
        return resultStr;
    }
	
	public static void main(String[] args) throws Exception {
	
	}
}
