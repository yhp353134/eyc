package com.fh.util;

import com.fh.util.oss.SystemConfig;

public class Constants {
	
	public static String PICTURE_VISIT_FILE_PATH = "";//图片访问的路径
	
	public static String PICTURE_SAVE_FILE_PATH = "";//图片存放的路径

	/**
     * <p>
     * Field NUMBER_1024: 数字1024
     * </p>
     */
    public static final int NUMBER_1024 = 1024;
    
    //微信图文消息原文地址                                                                                                     
    public static final String WECHAT_ORIGINAL_URL = "mobile/html/msgbox/msgInfoPage.html";
    //微信会话模板消息跳转地址                                                                                                     
    public static final String WECHAT_SESSION_URL = "mobile/html/index.html?tofrom=Chat";
    //服务活动原文地址                                                                                                     
    public static final String ACTIVITY_URL = "mobile/html/display/activity.html";
    //问卷调查原文地址                                                                                                     
    public static final String QUESTION_URL = "mobile/html/display/survey.html";
    //意见反馈原文地址                                                                                                     
    public static final String FEEDBACK_URL = "mobile/html/display/feedback.html";
    //预约受理详情原文地址(DCRC)                                                                                                    
    public static final String APPOINTMENT_URL = "mobile/html/display/appointment.html";
    //预约受理详情原文地址(客户)                                                                                                     
    public static final String APPOINTMENT_URL2 = "mobile/html/index.html?tofrom=CarReServeLogD";
    //站内原文地址
    public static final String STATION_URL = "mobile/html/display/systemInfo.html";
    //微信网页认证地址
    public static final String WechatOauthUrl = SystemConfig.getConfigWechat("WechatOauthUrl");
    //微信二维码扫描结果处理地址
    public static final String WechatQrcodeUrl = SystemConfig.getConfigWechat("WechatQrcodeUrl");
    /**
     * 短信发送
     */
    public static final String SMS_SEND_URL = SystemConfig.getConfigWechat("SMS_SEND_URL");
    public static final String SMS_SEND_COUNT = SystemConfig.getConfigWechat("SMS_SEND_COUNT");
    public static final String SMS_SEND_PASSWORD = SystemConfig.getConfigWechat("SMS_SEND_PASSWORD");
    /**
     * 平台电话
     */
    public static final String SYSTEM_ELEPHONE = SystemConfig.getConfigWechat("SYSTEM_ELEPHONE");
    
    
	public static String getPICTURE_VISIT_FILE_PATH() {
		return PICTURE_VISIT_FILE_PATH;
	}

	public static void setPICTURE_VISIT_FILE_PATH(String pICTURE_VISIT_FILE_PATH) {
		PICTURE_VISIT_FILE_PATH = pICTURE_VISIT_FILE_PATH;
	}

	public static String getPICTURE_SAVE_FILE_PATH() {
		return PICTURE_SAVE_FILE_PATH;
	}

	public static void setPICTURE_SAVE_FILE_PATH(String pICTURE_SAVE_FILE_PATH) {
		PICTURE_SAVE_FILE_PATH = pICTURE_SAVE_FILE_PATH;
	}

	public static void main(String[] args) {
		Constants.setPICTURE_SAVE_FILE_PATH("D:/Tomcat 6.0/webapps/FH/topic/");
		Constants.setPICTURE_VISIT_FILE_PATH("http://192.168.1.225:8888/FH/topic/");
	}
	
}
