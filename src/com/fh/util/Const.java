package com.fh.util;

import org.springframework.context.ApplicationContext;

import com.fh.util.oss.SystemConfig;
/**
 * 项目名称：
 * @author:fh
 * 
*/
public class Const {
	
	//webservice 所用到的字符串
	public static final String USER_NAME = "userName";//校验用户名标签名
	public static final String USER_PASSWORD = "password";//校验用户秘密标签名
	//文件夹路径
	public static final String SPACE_SIGN_DENGYU ="-------------------------------------------------------";
	public static final String CODE = "code"; //返回字符串
	public static final String MSG = "msg";  //返回字符穿
	public static final String SPACE_SIGN = "\r\n";  //空格符号
	
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String SESSION_menuList = "menuList";			//当前菜单
	public static final String SESSION_allmenuList = "allmenuList";		//全部菜单
	public static final String SESSION_QX = "QX";
	public static final String SESSION_userpds = "userpds";	
	public static final String SESSION_POST_LIST = "sessionPostList";               //职位列表
	public static final String SESSION_POST_NUMBER = "sessionPostNum";               //职位个数
	public static final String SESSION_USERROL = "USERROL";				//用户对象
	public static final String SESSION_USERNAME = "USER_NAME";			//用户名
	public static final String SESSION_ORG_ID = "ORG_ID";			    //机构ID
	public static final String SESSION_DEALER_ID = "DEALER_ID";			//经销商ID
	public static final String SESSION_POST_ID = "postId";            //职位ID
	public static final String SESSION_POST_NAME = "postName";            //职位名称
	public static final String SESSION_ORG_LEVEL = "ORG_LEVEL";            //机构级别
	public static final String SESSION_ORG_TYPE = "ORG_TYPE";			//判断用户是属于经销商或车厂
	public static final String SESSION_APP_ID = "APP_ID";			    //经销商微信APP_ID
	public static final String TRUE = "T";
	public static final String FALSE = "F";
	public static final String LOGIN = "/login_toLogin.do";				//登录地址
	public static final String SYSNAME = "admin/config/SYSNAME.txt";	//系统名称路径
	public static final String PAGE	= "admin/config/PAGE.txt";			//分页条数配置路径
	public static final String EMAIL = "admin/config/EMAIL.txt";		//邮箱服务器配置路径
	public static final String SMS1 = "admin/config/SMS1.txt";			//短信账户配置路径1
	public static final String SMS2 = "admin/config/SMS2.txt";			//短信账户配置路径2
	public static final String FWATERM = "admin/config/FWATERM.txt";	//文字水印配置路径
	public static final String IWATERM = "admin/config/IWATERM.txt";	//图片水印配置路径
	public static final String WEIXIN	= "admin/config/WEIXIN.txt";	//微信配置路径
	public static final String WEIXINMEDAI	= "admin/config/WEIXINMATERIAL.txt";	//微信文件配置路径
	public static final String SERVERNUM	= "admin/config/SERVERNUM.txt"; //服务器编号
	public static final String IMAGE_URL	= "Image";	//图片目录
	public static final String VOICE_URL	= "Voice";	//声音目录
	public static final String VIDEO_URL	= "Video";	//视频目录 
	public static final String FILEPATHIMG = "uploadFiles/uploadImgs/";	//图片上传路径
	public static final String FILEPATHFILE = "uploadFiles/file/";		//文件上传路径
	public static final String FILEPATHTWODIMENSIONCODE = "uploadFiles/twoDimensionCode/"; //二维码存放路径
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)|(app)|(static)|(version)|(plugins)|(main)|(homePage)|(bussMain)|(fandom)|(weixin)).*";	//不对匹配该值的访问路径拦截（正则）
	
	
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化
	
	/**
	 * APP Constants
	 */
	//app注册接口_请求协议参数)
	public static final String[] APP_REGISTERED_PARAM_ARRAY = new String[]{"countries","uname","passwd","title","full_name","company_name","countries_code","area_code","telephone","mobile"};
	public static final String[] APP_REGISTERED_VALUE_ARRAY = new String[]{"国籍","邮箱帐号","密码","称谓","名称","公司名称","国家编号","区号","电话","手机号"};
	
	//app根据用户名获取会员信息接口_请求协议中的参数
	public static final String[] APP_GETAPPUSER_PARAM_ARRAY = new String[]{"USERNAME"};
	public static final String[] APP_GETAPPUSER_VALUE_ARRAY = new String[]{"用户名"};
	

	/**
	 * 微信素材上传大小限制：2M
	 */
	public static final long UPLOAD_MATERIAL_SIZE =1024*1024*2;
	
	/**
	 * 原始微信模板ID，用于会话通知 DCRC人员
	 * {{first.DATA}}
	 * 用户名称：{{keyword1.DATA}}
	 * 咨询内容：{{keyword2.DATA}}
	 * {{remark.DATA}}
	 */
	public static final String WECHAT_SESSION_NOTICE_TEMPLATE_ID="OPENTM202119578";
	//会话通知页面地址
    public static final String WECHAT_SESSION_URL = "user.jsp";
    
	//扫描二维码成功页面地址，不需要认证，配置url全路径
    public static final String WECHAT_QRCODE_SUCCESS_URL = SystemConfig.getConfigWechat("WECHAT_QRCODE_SUCCESS_URL");
	//扫描二维码失败页面地址
    public static final String WECHAT_QRCODE_FAILE_URL   = SystemConfig.getConfigWechat("WECHAT_QRCODE_FAILE_URL");
     
    /**
     * //微信模板消息
     * 原始模板ID：OPENTM202119578   模板名称：用户咨询提醒
     * 原始模板ID：TM00114   模板名称：服务预约通知
     * 原始模板ID：TM00277   模板名称：问题咨询处理通知
     * 原始模板ID：OPENTM206962205   模板名称：车辆保养到期提醒
     * 原始模板ID：TM00619   模板名称：汽车保险到期提醒
     * 原始模板ID：TM00623   模板名称：汽车质保到期提醒
     * 原始模板ID：OPENTM203349177   模板名称：车辆到期年审通知
     * 原始模板ID：OPENTM401589946   模板名称：首保到期提醒
     * 原始模板ID：TM00629   模板名称：试驾预约模板
     * 原始模板ID：OPENTM202286257   模板名称：新询价单模板
     * "OPENTM202119578",
     */
    public static  final String[] WECHAT_TEMPLATE = {"TM00114","TM00277","OPENTM206962205","TM00619","TM00623","OPENTM203349177","OPENTM401589946","OPENTM403110637", "OPENTM408655637","OPENTM202119578","TM00629","OPENTM202286257"};
    
    public static void main(String[] args) throws Exception {
    	System.out.println("===WECHAT_QRCODE_SUCCESS_URL"+Const.WECHAT_QRCODE_SUCCESS_URL);
    }
	
}
