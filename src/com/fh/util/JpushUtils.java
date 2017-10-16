package com.fh.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/***
 * 极光推送消息
 * */
@Configuration
@EnableAsync
public class JpushUtils {
	
	//日志
	private static final Logger LOG = LoggerFactory.getLogger(JpushUtils.class);
	private static final String JPUSH_CONFIG="jpushconfig.properties";
	private static JPushClient jpushClient = null;
	
	/***jpush初始化货主**/
	@SuppressWarnings("deprecation")
	private static void init(){
		try {
			String appKey = getConfigResource("appKey");
			LOG.info(appKey);
			if (Tools.isEmpty(appKey)) {
				throw new IOException("消息发送异常：appKey为空，消息发送失败");
			}
			String masterSecret = getConfigResource("masterSecret");
			LOG.info(masterSecret);
			if (Tools.isEmpty(masterSecret)) {
				throw new IOException("消息发送异常：masterSecret为空，消息发送失败");
			}
			int time = 60 * 60 * 24;
			jpushClient = new JPushClient(masterSecret, appKey.trim(), time);
		} catch (IOException e) {
			LOG.info(e.toString());
		}
	}
	
	/***jpush初始化司机**/
	@SuppressWarnings("deprecation")
	private static void initDriver(){
		try {
			String appKey = getConfigResource("appKeyDriver");
			LOG.info(appKey);
			if (Tools.isEmpty(appKey)) {
				throw new IOException("消息发送异常：appKey为空，消息发送失败");
			}
			String masterSecret = getConfigResource("masterSecretDriver");
			LOG.info(masterSecret);
			if (Tools.isEmpty(masterSecret)) {
				throw new IOException("消息发送异常：masterSecret为空，消息发送失败");
			}
			int time = 60 * 60 * 24;
			jpushClient = new JPushClient(masterSecret, appKey.trim(), time);
		} catch (IOException e) {
			LOG.info(e.toString());
		}
	}
	
	/**获取配置信息**/
	public static String getConfigResource(String key) throws IOException{
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		InputStream in = loader.getResourceAsStream(JPUSH_CONFIG);
		properties.load(in);
		String value = properties.getProperty(key);
		value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
		in.close();
		return value;
	}

	/*** 群发消息
	 * @param content 消息内容
	 * * */
	public static PushPayload buildPushObject_all_all_alert(String content) {
		return PushPayload.alertAll(content);
	}

	/***别名发送消息
	 * @param alias 别名
	 * @param content 消息内容
	 * @param map 带参数
	 * * */
	public static PushPayload buildPushObject_all_alias_alert(String alias,String content,String titile,int brage,Map<String,String> map) {
		/*return PushPayload.newBuilder().setPlatform(Platform.all())
				.setAudience(Audience.alias(alias))
				.setNotification(Notification.newBuilder().setAlert(content)
				.addPlatformNotification(AndroidNotification.newBuilder().setTitle(titile).addExtras(map).build())
				.addPlatformNotification(IosNotification.newBuilder().setBadge(0).setSound("happy").addExtras(map).build())
				.build()).build();*/
		 return PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.alias(alias))
	                .setNotification(Notification.newBuilder()
	                        .addPlatformNotification(AndroidNotification.newBuilder()
	                                .addExtras(map)
	                                .setAlert(content)
	                                .build())
	                        .addPlatformNotification(IosNotification.newBuilder().setBadge(brage)
	                                .addExtras(map)
	                                .setAlert(content)
	                                .setSound("happy")
	                                .build())
	                        .build())
	                .setOptions(Options.newBuilder()
	                        .setApnsProduction(false)//true-推送生产环境 false-推送开发环境（测试使用参数）
	                        .build())
	                .build();
	}

	/***标签发送消息
	 * @param strtag 标签
	 * @param content 消息内容
	 * @param title 标题
	 * * */
	public static PushPayload buildPushObject_android_and_ios(String strtag,String content, String title,int brage,Map<String,String> paraMap) {
		/*return PushPayload.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(Audience.tag(strtag))
				.setNotification(Notification.newBuilder().setAlert(content)
				.addPlatformNotification(AndroidNotification.newBuilder().setTitle(title).addExtras(paraMap).build())
				.addPlatformNotification(IosNotification.newBuilder().setBadge(0).setSound("happy").addExtras(paraMap).build())
				.build()).build();*/
		return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
               	.setAudience(Audience.tag(strtag))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .addExtras(paraMap)
                                .setAlert(content)
                                .setTitle(title)
                                .build())
                        .addPlatformNotification(IosNotification.newBuilder().setBadge(brage)
                                .addExtras(paraMap)
                                .setAlert(content)
                                .setSound("happy")
                                .build())
                        .build())
		                .setOptions(Options.newBuilder()
		                        .setApnsProduction(false)//true-推送生产环境 false-推送开发环境（测试使用参数）
		                        .build())
		                .build();
	}
	
	/**** 群发所有人
	 * @param content 消息内容
	 * * */
	public static void sendAllUser(String content) {
		try {
			init();
			PushPayload payload = buildPushObject_all_all_alert(content);
			jpushClient.sendPush(payload);
		} catch (Exception e) {
			LOG.error(e.toString());
		} 
	}
	
	/***别名发送消息
	 * @param alias 别名
	 * @param content 消息内容
	 * @param title 消息标题
	 * @param map 参数
	 * * */
	public static void sendSingleUser(String alias,String content,String title,int brage,Map<String,String> map) {
		try {
			init();
			PushPayload pushPayload = buildPushObject_all_alias_alert(alias,content,title,brage,map);
			jpushClient.sendPush(pushPayload);
		} catch (Exception e) {
			LOG.error(e.toString());
		} 
	}
	
	/***标签发送消息
	 * @param tag 标签
	 * @param content 消息内容
	 * @param title 标题
	 * @param paramMap 额外参数
	 * * */
	public static void sendByTag(String tag,String content, String title,int brage,Map<String,String> paramMap) {
		try {
			init();
			PushPayload pushPayload = buildPushObject_android_and_ios(tag,content,title,brage,paramMap);
			jpushClient.sendPush(pushPayload);
		} catch (Exception e) {
			LOG.error(e.toString());
		}
	}
	
	/**** 群发所有人
	 * @param content 消息内容
	 * * */
	public static void sendAllUserByDriver(String content) {
		try {
			initDriver();
			PushPayload payload = buildPushObject_all_all_alert(content);
			jpushClient.sendPush(payload);
		} catch (Exception e) {
			LOG.error(e.toString());
		} 
	}
	
	/***别名发送消息
	 * @param alias 别名
	 * @param content 消息内容
	 * @param title 消息标题
	 * @param map 参数
	 * * */
	public static void sendSingleUserByDriver(String alias,String content,String title,int brage,Map<String,String> map) {
		try {
			initDriver();
			PushPayload pushPayload = buildPushObject_all_alias_alert(alias,content,title,brage,map);
			jpushClient.sendPush(pushPayload);
		} catch (Exception e) {
			LOG.error(e.toString());
		} 
	}
	
	/***标签发送消息
	 * @param tag 标签
	 * @param content 消息内容
	 * @param title 标题
	 * @param paramMap 额外参数
	 * * */
	public static void sendByTagByDriver(String tag,String content, String title,int brage,Map<String,String> paramMap) {
		try {
			initDriver();
			PushPayload pushPayload = buildPushObject_android_and_ios(tag,content,title,brage,paramMap);
			jpushClient.sendPush(pushPayload);
		} catch (Exception e) {
			LOG.error(e.toString());
		}
	}

	public static void main(String[] args) {
		// sendSingleUser("3715187299110090","后台测试推送","通知",null);
		// sendByTagByDriver("10000","e运车升级新版本了","通知",3,null);
		sendSingleUserByDriver("6304623113110240","fdsf","1111",3,null);
	}
}
