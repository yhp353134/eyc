package com.fh.service.app.alipay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.service.app.appcommon.AppCommonService;
import com.fh.util.Logger;
import com.fh.util.PageData;
import com.fh.util.PrimaryUtil;
import com.fh.util.SysDataCode;
import com.fh.util.Tools;
import com.fh.util.alipay.AliAppPayClient;
import com.fh.util.alipay.AlipayConfig;
@Service("aliPayService")
public class AliPayService {
	private static Logger logger = Logger.getLogger(AppCommonService.class);
	@Resource(name = "daoSupport")
    private DaoSupport dao;
	/**
	 * 根据order_id查询订单
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	public PageData getEorderByOrderId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AliPayMapper.getEorderByOrderId", pd);
	}
	/**
	 * 处理中支付宝异步返回的数据
	 * @param pd
	 * @throws Exception 
	 */
	public void handleAliPayAmount(PageData pd) throws Exception {
//		 dao.save("AliPayMapper.saveAlipayAmount", pd);
		PageData data = new PageData();
		dao.update("AliPayMapper.eidtEalipayByRecordId", data);
	}
	/**
	 * 生成定金的支付宝订单信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public String createAppDepositOrder(PageData pd) throws Exception {
		PageData  data = new PageData();
		AliAppPayClient aliAppPayClient = new AliAppPayClient();
		long OUT_TRADE_NO = PrimaryUtil.getPrimary();
		data.put("OUT_TRADE_NO", String.valueOf(OUT_TRADE_NO));
		data.put("RECORD_ID", OUT_TRADE_NO);
		data.put("TOTAL_AMOUNT", pd.getString("final_amount"));//订单金额
		data.put("ORDER_ID", pd.getString("order_id"));
		data.put("USER_ID", pd.getString("user_id"));//用户id
		this.saveAliPayAmount(data);
		pd.put("RECORD_ID", String.valueOf(OUT_TRADE_NO));//用于返回给前端支付成功后做校验
		String depositOrder = aliAppPayClient.createAppDepositOrder(pd);
		return  depositOrder;
	}
	
	private void saveAliPayAmount(PageData pd) throws Exception {
		 dao.save("AliPayMapper.saveAlipayAmount", pd);
		
	}
	/**
	 * 获取钱包
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	public synchronized PageData getAmoutByuserId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AliPayMapper.getAmoutByuserId", pd);
	}
	/**
	 * 处理定金回调处理
	 * @param pd
	 * @throws Exception 
	 */
	public void handleAliPayDeposit(Map<String, String> pd) throws Exception {
		/*notify_time	通知时间	Date	是	通知的发送时间。格式为yyyy-MM-dd HH:mm:ss	2015-14-27 15:45:58
			notify_type	通知类型	String(64)	是	通知的类型	trade_status_sync
			notify_id	通知校验ID	String(128)	是	通知校验ID	ac05099524730693a8b330c5ecf72da9786
			app_id	支付宝分配给开发者的应用Id	String(32)	是	支付宝分配给开发者的应用Id	2.01407E+15
			charset	编码格式	String(10)	是	编码格式，如utf-8、gbk、gb2312等	utf-8
			version	接口版本	String(3)	是	调用的接口版本，固定为：1.0	1
			sign_type	签名类型	String(10)	是	商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2	RSA2
			sign	签名	String(256)	是	请参考异步返回结果的验签	601510b7970e52cc63db0f44997cf70e
			trade_no	支付宝交易号	String(64)	是	支付宝交易凭证号	2.01311E+27
			out_trade_no	商户订单号	String(64)	是	原支付请求的商户订单号	6.82379E+15
			out_biz_no	商户业务号	String(64)	否	商户业务ID，主要是退款通知中返回退款申请的流水号	HZRF001
			buyer_id	买家支付宝用户号	String(16)	否	买家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字	2.0881E+15
			buyer_logon_id	买家支付宝账号	String(100)	否	买家支付宝账号	15901825620
			seller_id	卖家支付宝用户号	String(30)	否	卖家支付宝用户号	2.0881E+15
			seller_email	卖家支付宝账号	String(100)	否	卖家支付宝账号	zhuzhanghu@alitest.com
			trade_status	交易状态	String(32)	否	交易目前所处的状态，见交易状态说明	TRADE_CLOSED
			total_amount	订单金额	Number(9,2)	否	本次交易支付的订单金额，单位为人民币（元）	20
			receipt_amount	实收金额	Number(9,2)	否	商家在交易中实际收到的款项，单位为元	15
			invoice_amount	开票金额	Number(9,2)	否	用户在交易中支付的可开发票的金额	10
			buyer_pay_amount	付款金额	Number(9,2)	否	用户在交易中支付的金额	13.88
			point_amount	集分宝金额	Number(9,2)	否	使用集分宝支付的金额	12
			refund_fee	总退款金额	Number(9,2)	否	退款通知中，返回总退款金额，单位为元，支持两位小数	2.58
			subject	订单标题	String(256)	否	商品的标题/交易标题/订单标题/订单关键字等，是请求时对应的参数，原样通知回来	当面付交易
			body	商品描述	String(400)	否	该订单的备注、描述、明细等。对应请求时的body参数，原样通知回来	当面付交易内容
			gmt_create	交易创建时间	Date	否	该笔交易创建的时间。格式为yyyy-MM-dd HH:mm:ss	2015/4/27 15:45
			gmt_payment	交易付款时间	Date	否	该笔交易的买家付款时间。格式为yyyy-MM-dd HH:mm:ss	2015/4/27 15:45
			gmt_refund	交易退款时间	Date	否	该笔交易的退款时间。格式为yyyy-MM-dd HH:mm:ss.S	45:57.3
			gmt_close	交易结束时间	Date	否	该笔交易结束时间。格式为yyyy-MM-dd HH:mm:ss	2015/4/29 15:45
			fund_bill_list	支付金额信息	String(512)	否	支付成功的各个渠道金额信息，详见资金明细信息说明	[{“amount”:“15.00”,“fundChannel”:“ALIPAYACCOUNT”}]
			passback_params	回传参数	String(512)	否	公共回传参数，如果请求时传递了该参数，则返回给商户时会在异步通知时将该参数原样返回。本参数必须进行UrlEncode之后才可以发送给支付宝	merchantBizType%3d3C%26merchantBizNo%3d2016010101111
			voucher_detail_list	优惠券信息	String	否	本交易支付时所使用的所有优惠券信息，详见优惠券信息说明	[{“amount”:“0.20”,“merchantContribute”:“0.00”,“name”:“一键创建券模板的券名称”,“otherContribute”:“0.20”,“type”:“ALIPAY_DISCOUNT_VOUCHER”,“memo”:“学生卡8折优惠”]
			*/
		//第一步处理支付宝返回的数据
		PageData data = new PageData();
		SimpleDateFormat sdf=  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		data.put("RECORD_ID", pd.get("out_trade_no"));
		data.put("NOTIFY_TIME", sdf.parse(pd.get("notify_time")));
		data.put("NOTIFY_ID", pd.get("notify_id"));
		data.put("NOTIFY_TYPE", pd.get("notify_type"));
		data.put("APP_ID", pd.get("app_id"));
		data.put("CHARSET", pd.get("charset"));
		data.put("VERSION", pd.get("version"));
		data.put("OUT_BIZ_NO", pd.get("out_biz_no"));
		data.put("BUYER_ID", pd.get("buyer_id"));
		data.put("BUYER_LOGON_ID", pd.get("buyer_logon_id"));
		data.put("SELLER_ID", pd.get("seller_id"));
		data.put("SELLER_EMAIL", pd.get("seller_email"));
		data.put("TOTAL_AMOUNT", pd.get("total_amount"));
		data.put("RECEIPT_AMOUNT", pd.get("receipt_amount"));
		data.put("INVOICE_AMOUNT", pd.get("invoice_amount"));
		data.put("BUYER_PAY_AMOUNT", pd.get("buyer_pay_amount"));
		data.put("POINT_AMOUNT", pd.get("point_amount"));
		data.put("REFUND_FEE", pd.get("refund_fee"));
		data.put("SIGN_TYPE", pd.get("sign_type"));
		data.put("SIGN", pd.get("sign"));
		data.put("SUBJECT", pd.get("subject"));
		data.put("TRADE_NO", pd.get("trade_no"));
		data.put("TRADE_STATUS", pd.get("trade_status"));
		data.put("BODY", pd.get("body"));
		data.put("FUND_BILL_LIST", pd.get("fund_bill_list"));
		data.put("PASSBACK_PARAMS", pd.get("passback_params"));
		data.put("VOUCHER_DETAIL_LIST", pd.get("voucher_detail_list"));
		if(!Tools.isEmpty(pd.get("gmt_create"))){
			data.put("GMT_CREATE", sdf.parse(pd.get("gmt_create")));
		}
		if(!Tools.isEmpty(pd.get("gmt_payment"))){
			data.put("GMT_PAYMENT", sdf.parse(pd.get("gmt_payment")));
		}
		if(!Tools.isEmpty(pd.get("gmt_close"))){
			data.put("GMT_CLOSE", sdf.parse(pd.get("gmt_close")));
		}
		if(!Tools.isEmpty(pd.get("gmt_refund"))){
			data.put("GMT_REFUND", sdf.parse(pd.get("gmt_refund")));
		}
		dao.update("AliPayMapper.eidtEalipayByRecordId", data);
		//第二步向记录表中增加一条充值记录
		PageData data2 =  new PageData();
		String total_amount = pd.get("total_amount");
		String out_trade_no = pd.get("out_trade_no");
		data2.put("RECORD_ID", out_trade_no);
		PageData dd = this.getEalipayByRecordId(data2);
		data2.remove("RECORD_ID");
		data2.put("RECORD_ID", PrimaryUtil.getPrimary());
		data2.put("ADD_DATE", new Date());
		data2.put("TYPE", SysDataCode.MONEY_DETAIL_02);
		data2.put("AMOUNT", total_amount);
		data2.put("USER_ID", dd.get("USER_ID"));
		data2.put("CREATE_BY", dd.get("USER_ID"));
		data2.put("CREATE_DATE", new Date());
		dao.save("AliPayMapper.saveEamountRecord", data2);
		
		//第三步向用户的钱包金额增加充值的金额
		PageData  data3 = new PageData();
		data3.put("USER_ID", dd.get("USER_ID"));
		data3.put("AMOUNT", total_amount);
		dao.update("AliPayMapper.updateSysUserAmountByUserId", data3);
		
	}
	/**
	 * 根据支付宝记录的主键查询数据
	 * @param data2
	 * @return
	 * @throws Exception 
	 * RECORD_ID
	 */
	public PageData getEalipayByRecordId(PageData data2) throws Exception {
		return (PageData) dao.findForObject("AliPayMapper.getEalipayByRecordId", data2);
	}
	/**
	 * 获取支付宝交易记录数据状态
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	public String getEaliPayStatusByRecordId(PageData pd) throws Exception {
		pd.put("RECORD_ID", pd.getString("record_id"));
		PageData record = this.getEalipayByRecordId(pd);
		String status = record.getString("TRADE_STATUS");
		if(AlipayConfig.TRADE_SUCCESS.equals(status)||AlipayConfig.TRADE_FINISHED.equals(status)){
			return  "success";
		}else {
			return "failure";
		}
	}
	/**
	 * 支付宝充值信息准备
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	public PageData alipayRechargeByUserId(PageData pd) throws Exception {
		String user_id = pd.getString("user_id");
		String amount = pd.getString("amount");
		AliAppPayClient aliAppPayClient = new AliAppPayClient();
		PageData  data  =  new PageData();
		data.put("totle_amount", amount);
		String RECORD_ID = String.valueOf(PrimaryUtil.getPrimary()); 
		data.put("RECORD_ID",RECORD_ID);
		String orderInfo = aliAppPayClient.alipayRecharge(data);
		pd.put("RECORD_ID",RECORD_ID);
//		long OUT_TRADE_NO = PrimaryUtil.getPrimary();
		data.put("OUT_TRADE_NO", RECORD_ID);
		data.put("RECORD_ID", RECORD_ID);
		data.put("TOTLE_AMOUNT", amount);
		data.put("ORDER_ID", pd.getString("order_id"));
		data.put("USER_ID", user_id);//用户id
		this.saveAliPayAmount(data);
		
		PageData  data2 =  new PageData();
		data2.put("RECORD_ID", RECORD_ID);
		data2.put("payInfo", orderInfo);
		return data2;
	}
	/**
	 * 扣除账户余额
	 * @param pd
	 * @throws Exception 
	 */
	public void debitAmountByUserId(PageData pd) throws Exception {
		//扣除用户金额
		dao.update("AliPayMapper.debitAmountByUserId",pd);
		//增加扣款记录
		PageData data2 =  new PageData();
		String total_amount = pd.getString("amount");
		data2.put("RECORD_ID", PrimaryUtil.getPrimary());
		PageData dd = this.getEalipayByRecordId(data2);
		data2.remove("RECORD_ID");
		data2.put("RECORD_ID", PrimaryUtil.getPrimary());
		data2.put("ADD_DATE", new Date());
		data2.put("TYPE", SysDataCode.MONEY_DETAIL_05);
		data2.put("AMOUNT", "-"+total_amount);
		data2.put("USER_ID", pd.getString("user_id"));
		data2.put("CREATE_BY", pd.getString("user_id"));
		data2.put("BUSINESS_ID", dd.get("ORDER_ID"));
		data2.put("BUSINESS_TYPE", SysDataCode.MONEY_BUSINESS_DETAIL_01);
		data2.put("CREATE_DATE", new Date());
		dao.save("AliPayMapper.saveEamountRecord", data2);
		//修改订单状态
		PageData  data =  new PageData();
		data.put("order_id", pd.getString("order_id"));
		data.put("status", SysDataCode.ORDER_STATUS_TYPE_00);//待发布
        dao.update("AliPayMapper.udapteEorderByOrderId", data);		
	}
	/**
	 * 充值
	 * @param pd
	 * @throws Exception 
	 */
	public void rechargeAmountByUserId(PageData pd) throws Exception {
		//充值用户金额
		dao.update("AliPayMapper.rechargeAmountByUserId",pd);
		//增加扣款记录
		PageData data2 =  new PageData();
		String total_amount = pd.getString("amount");
		data2.put("RECORD_ID", PrimaryUtil.getPrimary());
//				PageData dd = this.getEalipayByRecordId(data2);
		data2.remove("RECORD_ID");
		data2.put("RECORD_ID", PrimaryUtil.getPrimary());
		data2.put("ADD_DATE", new Date());
		data2.put("TYPE", SysDataCode.MONEY_DETAIL_02);
		data2.put("AMOUNT", total_amount);
		data2.put("USER_ID", pd.getString("user_id"));
		data2.put("CREATE_BY", pd.getString("user_id"));
		data2.put("CREATE_DATE", new Date());
		dao.save("AliPayMapper.saveEamountRecord", data2);
	}
	
	
	
	
}
