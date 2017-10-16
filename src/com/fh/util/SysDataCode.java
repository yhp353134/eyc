package com.fh.util;
/**
 * 
 * @author Administrator 2017年3月3日
 * 基础数据字典  表：Sys_Data
 */
public class SysDataCode {
//	public static String DEPOT_PHONE="400-1077-988";
	/**
	 * 是否
	 */
	public static int STATUS_TYPE=1001;
	/**
	 * 是否-是
	 */
	public static int STATUS_TYPE_01=10011001;
	/**
	 * 是否-否
	 */
	public static int STATUS_TYPE_02=10011002;
	/**
	 * 状态
	 */
	public static int ISVALID=1002;
	/**
	 * 状态-有效
	 */
	public static int ISVALID_YES=10021001;
	/**
	 * 状态-无效
	 */
	public static int ISVALID_NO=10021002;
	
	 /**
     * 文件路径
     */
    public static int FILE_PATH = 1108;
    /**
     * 	车层级
     */
    public static int CAR_MODLE_TYPE = 1005;
    /**
     * 品牌
     */
    public static int CAR_MODLE_TYPE_01 = 10051001;
    /**
     * 车系
     */
    public static int CAR_MODLE_TYPE_02 = 10051002;

    

	/**
	 * 机构类型，区分车厂和服务站 1012
	 */
	public static int ORG_TYPE=1012;
    /**
     * 机构类型-车厂 10120001
     */
    public static int ORG_TYPE_DEPOT=10120001;
    /**
     * 机构类型-服务站 10120002
     */
    public static int ORG_TYPE_DEALER=10120002;
    /**
     * 机构类型-长安铃木和服务站 10120003
     */
    public static int ORG_TYPE_ICAR_DEALER=10120003;
 
	
	
	/**
	 * 性别类型
	 */
	public static int SEX_TYPE=1011;
	/**
	 * 性别类型-男
	 *
	 */
	public static int SEX_TYPE_01=10110001;
	/**
	 * 性别类型-女
	 *
	 */
	public static int SEX_TYPE_02=10110002;
	/**
	 * 性别类型-其他
	 *
	 */
	public static int SEX_TYPE_03=10110003;
	
	/**
	 * 用户类型
	 */
	public static int USR_TYPE=1013;
	/**
	 * 用户类型-平台用户
	 */
	public static int USR_TYPE_01=10131001;
	/**
	 * 用户类型-B端货主
	 */
	public static int USR_TYPE_02=10131002;
	/**
	 * 用户类型-B端司机
	 */
	public static int USR_TYPE_03=10131003;
	/**
	 * 用户类型-C端货主
	 */
	public static int USR_TYPE_04=10131004;
	/**
	 * 用户类型-C端司机
	 */
	public static int USR_TYPE_05=10131005;
	/**
	 * 用户类型-VTC司机
	 */
	public static int USR_TYPE_06=10131006;
	
	/**
	 *用户审核状态
	 */
	public static int EXAMINE_STATUS=1014;
	/**
	 * 审核状态-待审核
	 */
	public static int EXAMINE_STATUS_01=10141001;
	/**
	 * 审核状态-审核通过
	 */
	public static int EXAMINE_STATUS_02=10141002;
	/**
	 * 审核状态-审核驳回
	 */
	public static int EXAMINE_STATUS_03=10141003;
    /**
     * 审核状态-待认证状态
     */
	public static int EXAMINE_STATUS_04=10141004;
	
	/**
	 *公司类型
	 */
	public static int DEALER_TYPE=1015;
	/**
	 * 物流公司
	 */
	public static int DEALER_TYPE_01=10151001;
	/**
	 * 经纪人
	 */
	public static int DEALER_TYPE_02=10151002;
	/**
	 * 货主公司
	 */
	public static int DEALER_TYPE_03=10151003;
	/**
	 * VTC
	 */
	public static int DEALER_TYPE_04=10151004;
	/**
	 *个人货主
	 */
	public static int DEALER_TYPE_05=10151005;
	
	/**
	 * 登陆类型
	 */
	public static int LOGIN_TYPE=1016;
	/**
	 * 密码登陆
	 */
	public static int LOGIN_TYPE_01=10161001;
	/**
	 * 短信登陆
	 */
	public static int LOGIN_TYPE_02=10161002;
	
	/**
	 * 文件类型
	 */
	public static int FILE_TYPE=1017;
	/**
	 * 图片
	 */
	public static int FILE_TYPE_01=10171001;
	
	/**
	 * 图片文件使用类型
	 */
	public static int FILE_USE_TYPE=1018;
	public static int FILE_USE_TYPE_01=10181001;  // 身份证
	public static int FILE_USE_TYPE_02=10181002; //驾驶证
	public static int FILE_USE_TYPE_03=10181003; //行驶证
	public static int FILE_USE_TYPE_04=10181004; // 车辆图片
	public static int FILE_USE_TYPE_05=10181005; //营业执照
	public static int FILE_USE_TYPE_06=10181006; //头像
	public static int FILE_USE_TYPE_07=10181007; //道路运输许可证
	public static int FILE_USE_TYPE_08=10181008; //订单相关图片
	public static int FILE_USE_TYPE_09=10181009; //意见反馈图片
	public static int FILE_USE_TYPE_10=10181010; //广告图片
	public static int FILE_USE_TYPE_11=10181011; //VTC图片
	
	/**
	 * 文件详细类型
	 */
	public static int FILE_DETAIL_TYPE=1019;
	/**
	 * 正面
	 */
	public static int FILE_DETAIL_TYPE_01=10191001;  //正面
	/**
	 * 反面
	 */
	public static int FILE_DETAIL_TYPE_02=10191002; //反面
	/**
	 * 侧面
	 */
	public static int FILE_DETAIL_TYPE_03=10191003; //侧面
	/**
	 * 手持图片
	 */
	public static int FILE_DETAIL_TYPE_04=10191004;//手持图片
	/**
	 * 主图
	 */
	public static int FILE_DETAIL_TYPE_05=10191005;//主图
	/**
	 * 副本
	 */
	public static int FILE_DETAIL_TYPE_06=10191006;//副本
	/**
	 * 收车图片
	 */
	public static int FILE_DETAIL_TYPE_07=10191007;//收车图片
	/**
	 * 交车图片
	 */
	public static int FILE_DETAIL_TYPE_08=10191008;//交车图片
	/**
	 * 评价图片
	 */
	public static int FILE_DETAIL_TYPE_09=10191009;//评价图片
	/**
	 *车辆图片
	 */
	public static int FILE_DETAIL_TYPE_10=10191010;//背面
	/**
	 *VTC取车图片
	 */
	public static int FILE_DETAIL_TYPE_11=10191011;//VTC取车图片
	/**
	 *VTC送车图片
	 */
	public static int FILE_DETAIL_TYPE_12=10191012;//VTC送车图片
	
	/**
	 * 付款标志
	 */
	public static int PAY_USER_TYPE=1020;
	/**
	 * 未付款
	 */
	public static int PAY_USER_TYPE_01=10201001;
	/**
	 * 已付款
	 */
	public static int PAY_USER_TYPE_02=10201002;
	/**
	 * 订单状态
	 */
	public static int ORDER_STATUS_TYPE=1021;
	/**
	 * 待发布
	 */
	public static int ORDER_STATUS_TYPE_00=10211000;
	/**
	 * 货源报价中
	 */
	public static int ORDER_STATUS_TYPE_01=10211001;
	/**
	 * 货源待确认
	 */
	public static int ORDER_STATUS_TYPE_02=10211002;
    /**
     * 货源撤销（货源）
     */
	public static int ORDER_STATUS_TYPE_03=10211003;
    /**
     * 下单成功
     */
	public static int ORDER_STATUS_TYPE_04=10211004;

	/**
	 * 订单撤销
	 */
	public static int ORDER_STATUS_TYPE_05=10211005;
    /**
     * 已起运
     */
	public static int ORDER_STATUS_TYPE_06=10211006;
	/**
	 * 已到达
	 */
	public static int ORDER_STATUS_TYPE_07=10211007;
    /**
     * 司机已交车
     */
	public static int ORDER_STATUS_TYPE_08=10211008;
	/**
	 * 货主确认交车
	 */
	public static int ORDER_STATUS_TYPE_09=10211009;
    /**
     * 已付款
     */
	public static int ORDER_STATUS_TYPE_10=10211010;
   /**
    * 已经评价
    */
	public static int ORDER_STATUS_TYPE_11=10211011;
	/**
    * VTC收车(货主)
    */
	public static int ORDER_STATUS_TYPE_12=10211012;
	/**
    * VTC交车(司机)
    */
	public static int ORDER_STATUS_TYPE_13=10211013;	
	/**
    * VTC收车(司机)
    */
	public static int ORDER_STATUS_TYPE_14=10211014;	
	/**
    * VTC交车(货主)
    */
	public static int ORDER_STATUS_TYPE_15=10211015;	
	/**
    *待支付定金
    */
	public static int ORDER_STATUS_TYPE_16=10211016;	
	
	/**
	 * 生成订单方式
	 */
	public static int  CREATE_ORDER_TYPE=1022;
	/**
	 * 自动生成
	 */
	public static int  CREATE_ORDER_TYPE_01=10221001;
	/**
	 * 手动生成
	 */
	public static int  CREATE_ORDER_TYPE_02=10221002;
	/**
	 * 上报类型
	 */
	public static int  REPORT_TYPE=1023;
	/**
	 * 自动上报
	 */
	public static int  REPORT_TYPE_01=10231001;
    /**
     * 手动上报
     */
	public static int  REPORT_TYPE_02=10231002;
	/**
	 * 位置类型
	 */
	public static int  ADDRESS_TYPE=1024;
	/**
	 * 起运
	 */
	public static int  ADDRESS_TYPE_01=10241001;
    /**
     * 在途
     */
	public static int  ADDRESS_TYPE_02=10241002;
	/**
	 * 到达
	 */
	public static int  ADDRESS_TYPE_03=10241003;
	/**
	 * 消息类型
	 */
	public static int  MSG_TYPE=1025;
	/**
	 * 系统通知
	 */
	public static int  MSG_TYPE_01=10251001;
    /**
     * 系统提醒
     */
	public static int  MSG_TYPE_02=10251002;
	
    /**
     * 	区域层级
     */
	public static int CITY_TYPE=1029;
	/**
	 * 省份
	 */
	public static int CITY_TYPE_01=10291001;
	/**
	 * 市
	 */
	public static int CITY_TYPE_02=10291002;
	/**
	 * 辖区
	 */
	public static int CITY_TYPE_03=10291003;
	
	
	 /**
     * 	接点类型
     */
	public static int CONTRAIL_TYPE=1030;
	/**
	 * 成交
	 */
	public static int CONTRAIL_TYP_01=10301001;
	/**
	 * 收车
	 */
	public static int CONTRAIL_TYP_02=10301002;
	/**
	 * 起运
	 */
	public static int CONTRAIL_TYP_03=10301003;
	/**
	 * 到达
	 */
	public static int CONTRAIL_TYP_04=10301004;
	/**
	 * 交车
	 */
	public static int CONTRAIL_TYP_05=10301005;
	
	/**
	 * 评价
	 */
	public static int CONTRAIL_TYP_06=10301006;
	/**
	 * 撤销
	 */
	public static int CONTRAIL_TYP_07=10301007;
	/**
	 * 确认交车
	 */
	public static int CONTRAIL_TYP_08=10301008;
	
	 /**
     * 	钱包明细类型
     */
	public static int MONEY_DETAIL=1031;
	/**
	 * 运费
	 */
	public static int MONEY_DETAIL_01=10311001;
	/**
	 * 充值
	 */
	public static int MONEY_DETAIL_02=10311002;
	/**
	 * 退款
	 */
	public static int MONEY_DETAIL_03=10311003;
	/**
	 * 提现
	 */
	public static int MONEY_DETAIL_04=10311004;
	/**
	 * 定金
	 */
	public static int MONEY_DETAIL_05=10311005;
	
	/**
     * 	钱包明细业务类型
     */
	public static int MONEY_BUSINESS_DETAIL=1032;
	public static int MONEY_BUSINESS_DETAIL_01=10321001;  //订单
	public static int MONEY_BUSINESS_DETAIL_02=10321002;  //充值
	public static int MONEY_BUSINESS_DETAIL_03=10321003;  //提现
	
	/**
	 * 极光推送值
	 */
	public static String JI_GUANG_SIGN_01 = "10000"; //司机标签
	public static String JI_GUANG_SIGN_02 = "20000"; //货主标签
	
	/**
	 * 极光通知类型
	 */
	public static int NOTICE_TYPE_JIGUANG=1033;
	public static int NOTICE_TYPE_JIGUANG_01 = 10331001;  //承运商审核司机报价
	public static int NOTICE_TYPE_JIGUANG_02 = 10331002;  //货主发布货源
	public static int NOTICE_TYPE_JIGUANG_03 = 10331003;  //货主确认报价
	public static int NOTICE_TYPE_JIGUANG_04 = 10331004;  //用户审核通过
	public static int NOTICE_TYPE_JIGUANG_05 = 10331005;  //用户审核驳回
	public static int NOTICE_TYPE_JIGUANG_06 = 10331006;  //添加子账号
	public static int NOTICE_TYPE_JIGUANG_07 = 10331007;  //司机确认交车
	
	/**
	 * 反馈类型
	 */
	public static int FEEDBACK_TYPE=1034;
	public static int FEEDBACK_TYPE_01=10341001;  //系统反馈
	
	/***广告类型**/
	public static int ADVERT=1035;
	public static int ADVERT_01=10351001;  //广告
	public static int ADVERT_02=10351002;  //帮助
	
	/***广告详细类型**/
	public static int ADVERT_DETAIL=1036;
	public static int ADVERT_DETAIL_01=10361001;  //图文、内容
	public static int ADVERT_DETAIL_02=10361002;  //链接
	
	/***费用类型**/
	public static int COST_TYPE=1037;
	public static int COST_TYPE_01=10371001;  //取车服务费
	public static int COST_TYPE_02=10371002;  //收车服务费
	public static int COST_TYPE_03=10371003;  //保险
	public static int COST_TYPE_04=10371004;  //税费
	public static int COST_TYPE_05=10371005;  //运费
	public static int COST_TYPE_06=10371006;  //平台收益
	
	/***VTC业务类型**/
	public static int VTC_BUSSINESS_TYPE=1038;
	public static int VTC_BUSSINESS_TYPE_01=10381001;  //取车
	public static int VTC_BUSSINESS_TYPE_02=10381002;  //送车
	
	/***VTC业务状态**/
	public static int VTC_BUSSINESS_STATUS=1039;
	public static int VTC_BUSSINESS_STATUS_01=10391001;  //待收车
	public static int VTC_BUSSINESS_STATUS_02=10391002;  //已收车
	public static int VTC_BUSSINESS_STATUS_03=10391003;  //已交车
	
}
