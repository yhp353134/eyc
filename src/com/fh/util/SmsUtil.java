package com.fh.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.LoggerFactory;

import com.fh.service.app.appcommon.AppCommonService;
import com.fh.util.sms.SmsHttpSenderUtil;

/**
 * 通过短信接口发送短信
 */
public class SmsUtil {

	public static final org.slf4j.Logger log = LoggerFactory.getLogger(SmsUtil.class);
	// 短信商 一 http://www.dxton.com/
	/**
	 * 给一个人发送单条短信
	 * 
	 * @param mobile
	 *            手机号
	 * @param code
	 *            短信内容
	 */
	public static void sendSms1(String mobile, String code) {
		String account = "", password = "";
		String strSMS1 = Tools.readTxtFile(Const.SMS1); // 读取短信1配置
		if (null != strSMS1 && !"".equals(strSMS1)) {
			String strS1[] = strSMS1.split(",fh,");
			if (strS1.length == 2) {
				account = strS1[0];
				password = strS1[1];
			}
		}
		String PostData = "";
		try {
			PostData = "account=" + account + "&password=" + password
					+ "&mobile=" + mobile + "&content="
					+ URLEncoder.encode(code, "utf-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("短信提交失败");
		}
		// System.out.println(PostData);
		String ret = SMS(PostData, "http://sms.106jiekou.com/utf8/sms.aspx");
		System.out.println(ret);
		/*
		 * 100 发送成功 101 验证失败 102 手机号码格式不正确 103 会员级别不够 104 内容未审核 105 内容过多 106
		 * 账户余额不足 107 Ip受限 108 手机号码发送太频繁，请换号或隔天再发 109 帐号被锁定 110 发送通道不正确 111
		 * 当前时间段禁止短信发送 120 系统升级
		 */

	}

	public static String SMS(String postData, String postUrl) {
		try {
			// 发送POST请求
			URL url = new URL(postUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setUseCaches(false);
			conn.setDoOutput(true);

			conn.setRequestProperty("Content-Length", "" + postData.length());
			OutputStreamWriter out = new OutputStreamWriter(
					conn.getOutputStream(), "UTF-8");
			out.write(postData);
			out.flush();
			out.close();

			// 获取响应状态
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("connect failed!");
				return "";
			}
			// 获取响应内容体
			String line, result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			in.close();
			return result;
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return "";
	}

	// ===================================================================================================================

	/**
	 * 
	 * 短信商 二 http://www.ihuyi.com/
	 * ==============================================
	 * =======================================
	 * 
	 */
	private static String Url = "http://106.ihuyi.com/webservice/sms.php?method=Submit";

	/**
	 * 给一个人发送单条短信
	 * 
	 * @param mobile
	 *            手机号
	 * @param code
	 *            短信内容
	 */
	public static void sendSms2(String mobile, String code) {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(Url);

		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType",
				"application/x-www-form-urlencoded;charset=UTF-8");

		String content = new String(code);

		String account = "", password = "";
		String strSMS2 = Tools.readTxtFile(Const.SMS2); // 读取短信2配置
		if (null != strSMS2 && !"".equals(strSMS2)) {
			String strS2[] = strSMS2.split(",fh,");
			if (strS2.length == 2) {
				account = strS2[0];
				password = strS2[1];
			}
		}

		NameValuePair[] data = {// 提交短信
				new NameValuePair("account", account),
				new NameValuePair("password", password), // 密码可以使用明文密码或使用32位MD5加密
				new NameValuePair("mobile", mobile),
				new NameValuePair("content", content), };

		method.setRequestBody(data);

		try {
			client.executeMethod(method);
			String SubmitResult = method.getResponseBodyAsString();
			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();
			code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");
			log.info(msg);
			log.info(smsid);
			if (code == "2") {
				// System.out.println("短信提交成功");
				log.info("短信提交成功");
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 给多个人发送单条短信
	 * 
	 * @param list
	 *            手机号验证码
	 */
	public static void sendSmsAll(List<PageData> list) {
		String code;
		String mobile;
		for (int i = 0; i < list.size(); i++) {
			code = list.get(i).get("code").toString();
			mobile = list.get(i).get("mobile").toString();
			sendSms2(mobile, code);
		}
	}

	// =================================================================================================

	/**
	 * 发送短信接口
	 * 
	 * @param template_code
	 *            模板id
	 * @param paramPd
	 *            参数
	 * @param telPhone
	 *            电话号码
	 * @param SMS_Supplier
	 *            短信供应商(现在为唯一的，不传) create_by work_ljie@163.com 2017年8月15日
	 *            EVALUATE
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String SendSMS(String template_code, PageData paramPd,
			String telPhone, String SMS_Supplier) throws Exception {
		String template_message = SmsUtil.getMessageInfoByKey(template_code);
		if (null != paramPd && paramPd.size() > 0) {
			Set<String> keySet = paramPd.keySet();
			for (String key : keySet) {
				String val = "\\$\\{" + key + "\\}";
				String value = String.valueOf(paramPd.get(key));
				template_message = template_message.replaceAll(val, value);
			}
		}
		String batchSend = SmsHttpSenderUtil.batchSend(telPhone,template_message, true, null);
		//用户获取验证码不存入消息库
		if (!"SMS_10001".equals(template_code)
				&& !"SMS_10002".equals(template_code)
				&& !"SMS_10003".equals(template_code)
				&& !"SMS_10004".equals(template_code)) {
			// 保存系统消息
			try {
				AppCommonService commonService = (AppCommonService) BeanUtil.getBean("appCommonService");
				paramPd.put("msg_content", template_message);
				paramPd.put("receive_phone", telPhone);
				commonService.saveMaMsg(paramPd);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return batchSend;
	}

	/**
	 * 根据key，获得对应的值
	 */
	public static String getMessageInfoByKey(String key) {
		if (Tools.isEmpty(key)) {
			return "";
		}
		Map<Object, Object> map = GetSmsMessageTemlate.getMap();
		String value = (String) map.get(key);
		return value;
	}
	
	/**根据key获取信息，同时替换里面的key,无替换的pd就传null,返回消息内容**/
	public static String getMessageAndReplaceKey(String key,PageData pd) {
		String tpl = getMessageInfoByKey(key);
		if (null != pd && pd.size() > 0) {
			@SuppressWarnings("unchecked")
			Set<String> keySet = pd.keySet();
			for (String sigleKey : keySet) {
				String val = "\\$\\{" + sigleKey + "\\}";
				String value = String.valueOf(pd.get(sigleKey));
				tpl = tpl.replaceAll(val, value);
			}
		}
		return tpl;
	}

}
