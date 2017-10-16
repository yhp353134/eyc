package com.fh.controller.app.alipay;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.fh.controller.base.BaseController;
import com.fh.service.app.alipay.AliPayService;
import com.fh.util.AppConstant;
import com.fh.util.AppUtil;
import com.fh.util.PageData;
import com.fh.util.Tools;
import com.fh.util.alipay.AliAppPayClient;
import com.fh.util.alipay.AlipayConfig;

/**
 * 支付宝接口
 * 
 * @author ljie
 *
 */
@Controller
@RequestMapping("app/alipay/")
public class AliPayController extends BaseController {
	@Resource(name = "aliPayService")
	private AliPayService aliPayService;

	/**
	 * 获取订单信息(创建订单信息支付定金)
	 * 
	 * @return
	 */
	@RequestMapping("/getDepositOrderInfo")
	@ResponseBody
	public Map<String, Object> getDepositOrderInfo(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String user_id = pd.getString("user_id");
			String order_id = pd.getString("order_id");
			String amount = pd.getString("amount");
			if (Tools.isEmpty(user_id) ||Tools.isEmpty(order_id) || Tools.isEmpty(amount)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			} else {
				PageData data = aliPayService.getEorderByOrderId(pd);
				if (null == data || data.size() <= 0) {
					resultMap.put("status", AppConstant.ERR_CODE_1023);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1023));
					return resultMap;
				}
				// AliAppPayClient aliAppPayClient = new AliAppPayClient();
				// String depositOrder =
				// aliAppPayClient.createAppDepositOrder(data);
				// String rsa256Sign = aliAppPayClient.rsa256Sign(depositOrder);
				PageData p = aliPayService.getAmoutByuserId(pd);
				String wallet_amount =String.valueOf(p.get("AMOUNT"));
				BigDecimal wallet_decimal = new BigDecimal(wallet_amount);
				BigDecimal decimal1 = new BigDecimal(amount);
				double amountT = wallet_decimal.doubleValue();//钱包金额
				double amountTS = decimal1.doubleValue();//前端传递的金额
				double final_amount = amountTS-amountT;
				logger.info("计算差值============="+final_amount);
				if(final_amount<=0.0d){
//					resultMap.put("status", AppConstant.ERR_CODE_1002);
//					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1002));
					resultMap.put(AppConstant.RESULT, "余额充足");
					return resultMap;
				}
				pd.put("final_amount", String.valueOf(final_amount));//差额
				String depositOrder = aliPayService.createAppDepositOrder(pd);
				Map<String, Object>  mp = new HashMap<String, Object>();
				mp.put("WALLET_AMOUNT", wallet_amount);//钱包余额
				mp.put("FINAL_AMOUNT", final_amount);//差额
				mp.put("DEPOSIT_ORDER", depositOrder);//订单信息
				mp.put("RECORD_ID", pd.getString("RECORD_ID"));
				resultMap.put(AppConstant.RESULT, mp);
			}
		} catch (Exception e) {

		}
		return  AppUtil.returnMap(resultMap);
	}

	/**
	 * 异步通知付款状态的Controller
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws FileNotFoundException
	 * @throws AlipayApiException
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/notify")
	@ResponseBody
	public String notifyUrl(HttpServletRequest request)  {
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		    String name = (String) iter.next();
		    String[] values = (String[]) requestParams.get(name);
		    String valueStr = "";
		    for (int i = 0; i < values.length; i++) {
		        valueStr = (i == values.length - 1) ? valueStr + values[i]
		                    : valueStr + values[i] + ",";
		  	}
		    //乱码解决，这段代码在出现乱码时使用。
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		//切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
		//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
			
		
		
//		PageData pd = this.getPageData();
		logger.info("验证支付宝返回的结果开始========");
		System.out.println(params.toString());
		logger.info("验签数据====="+params.toString());
		// ----测试打印-----
		PrintStream out = System.out;
		PrintStream ps;
		try {
			ps = new PrintStream("D:/software/enterprise/apache-tomcat-7.0.67/logs/log.txt");
			logger.info(ps.toString());
			System.setOut(ps);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			logger.info("支付宝异步通知日志异常=====");
		}
		// System.out.println(pd.toString());
		// 切换回打印到控制台
		System.setOut(out);
		logger.info("程序运行完毕，请查看日志");
		// System.out.println("程序运行完毕，请查看日志");
		logger.info("创建支付宝客户端开始===");
//		AliAppPayClient aliAppPayClient = AliAppPayClient.getIncetent();// 获取支付宝客户端
		logger.info("创建支付宝客户端结束===");
		boolean signVerfied=false;
		try {
			signVerfied = AlipaySignature.rsaCheckV1(params,  AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET,AlipayConfig.SIGNTYPE);
			logger.info("验签开始===========================");
//			signVerfied = aliAppPayClient.rsaCheckV2(pd);
			logger.info("验签结束=====signVerfied="+signVerfied+"=====================");
			
		} catch (AlipayApiException e1) {
			e1.printStackTrace();
			logger.info("支付宝异步通知验签失败=====================");
			return "failure";
		}// 验签
		if (signVerfied) {
			// TODO 验签成功后
			// 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
			// 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
			// 3、校验通知中的seller_id（或者seller_email)
			// 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
			// 4、验证app_id是否为该商户本身
			// 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
			String out_trade_no = params.get("out_trade_no");
			String app_id = params.get("app_id");
			PageData data2 = new PageData();
			data2.put("RECORD_ID", out_trade_no);
			try {
				PageData byRecord = aliPayService.getEalipayByRecordId(data2);
				if(null==byRecord||byRecord.size()<=0){
					logger.info("验签失败,单据号不存在=====out_trade_no："+out_trade_no+"========");
					return  "failure";
				}
				String total_fee = String.valueOf(byRecord.get("TOTAL_AMOUNT"));//支付金额
				String total_amount = params.get("total_amount");//支付宝返回的金额
				BigDecimal bigDecimal =  new BigDecimal(total_fee);
				BigDecimal bigDecimal1 =  new BigDecimal(total_amount);
				double doubleValue = bigDecimal.doubleValue();
				double doubleValue1 = bigDecimal1.doubleValue();
				if((doubleValue-doubleValue1)!=0){
					logger.info("金额不等，验签失败=====total_fee："+total_fee+"===total_amount:"+total_amount+"=====");
					return  "failure";
				}
				String seller_id = params.get("seller_id");
				String seller_email = params.get("seller_email");
				if(AlipayConfig.seller_id.equals(seller_id)||AlipayConfig.seller_email.equals(seller_email)){
				}else {
					logger.info("不是该商家下的支付宝，验签失败=====seller_id："+seller_id+"===seller_email:"+seller_email+"=====");
					return  "failure";
				}
				if(!AlipayConfig.APPID.equals(app_id)){
					logger.info("appid不一致，验签失败=====appid："+app_id+"========");
					return  "failure";
				}
				/*在上述验证通过后商户必须根据支付宝不同类型的业务通知
				，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
				在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，
				支付宝才会认定为买家付款成功。*/
				String trade_status = params.get("trade_status");
				logger.info("付款状态===trade_status"+trade_status); 
				if(AlipayConfig.TRADE_SUCCESS.equals(trade_status)||AlipayConfig.TRADE_FINISHED.equals(trade_status)){
					 aliPayService.handleAliPayDeposit(params);
				 }else {
					 return "failure";
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("支付宝获取异步通知保存异常==============");
				return "failure";
			}
		    return  "success";
		} else {
			// TODO 验签失败则记录异常日志，并在response中返回failure.
			logger.info("验签失败忽略结果=====");
			return "failure";
		}
	}

	/**
	 * 获取需要支付的金额
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/getPaymentbalanceinfo")
	@ResponseBody
	public Map<String, Object> getPaymentbalanceinfo(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String order_id = pd.getString("order_id");
			if (Tools.isEmpty(order_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			} else {
				PageData data = aliPayService.getEorderByOrderId(pd);
				if (null == data || data.size() <= 0) {
					resultMap.put("status", AppConstant.ERR_CODE_1023);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1023));
					return resultMap;
				}
				AliAppPayClient aliAppPayClient = new AliAppPayClient();
				String depositOrder = aliAppPayClient.createPaymentbalanceOrder(data);
				// String rsa256Sign = aliAppPayClient.rsa256Sign(depositOrder);
				resultMap.put(AppConstant.RESULT, depositOrder);
			}
		} catch (Exception e) {

		}
		return AppUtil.returnMap(resultMap);
	}

	/**
	 * 获取钱包数据判断是否满足支付定金的金额
	 * 
	 * @return
	 */
	@RequestMapping("/getAmount")
	@ResponseBody
	public Map<String, Object> getAmount(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			String res  = "failure";//金额不足
			PageData pd = this.getPageData();
			String amountS = pd.getString("amount");
			String user_id = pd.getString("user_id");
			if (Tools.isEmpty(user_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			} else {
				PageData p = aliPayService.getAmoutByuserId(pd);
				String amount =String.valueOf(p.get("AMOUNT"));
				BigDecimal decimal = new BigDecimal(amount);
				BigDecimal decimal1 = new BigDecimal(amountS);
				double amountT = decimal.doubleValue();//钱包金额
				double amountTS = decimal1.doubleValue();//前端传递的金额
//				Map<String, Object>  mp = new HashMap<String, Object>();
				double final_amount = amountTS-amountT;
				logger.info("计算差值============="+final_amount);
				if(final_amount>=0.0d){
					res ="success";//余额充足
				}else {
					resultMap.put("status", AppConstant.ERR_CODE_1025);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1025));
				}
				resultMap.put(AppConstant.RESULT, res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return AppUtil.returnMap(resultMap);
	}
	/**
	 * 根据user_id获取钱包金额
	 * 
	 * @return
	 */
	@RequestMapping("/getAmountByUserId")
	@ResponseBody
	public Map<String, Object> getAmountByUserId(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String user_id = pd.getString("user_id");
			if (Tools.isEmpty(user_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			} else {
				PageData p = aliPayService.getAmoutByuserId(pd);
//				String amount =String.valueOf(p.get("AMOUNT"));
//				BigDecimal decimal = new BigDecimal(amount);
//				BigDecimal decimal1 = new BigDecimal(amountS);
//				double amountT = decimal.doubleValue();//钱包金额
//				double amountTS = decimal1.doubleValue();//前端传递的金额
			
				resultMap.put(AppConstant.RESULT, p);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return AppUtil.returnMap(resultMap);
	}
	
	
	/**
	 * 根据RECORD_ID获取支付宝记录表交易状态(验证是否充值成功并扣除金额)
	 * 
	 * @return
	 */
	@RequestMapping("/getEaliPayStatusByRecordId")
	@ResponseBody
	public Map<String, Object> getEaliPayStatusByRecordId(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String user_id = pd.getString("user_id");
			String record_id = pd.getString("record_id");
			String amount = pd.getString("amount");
			if (Tools.isEmpty(user_id)||Tools.isEmpty(record_id)||Tools.isEmpty(amount)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			} else {
				
				//判断用户是否存在
				PageData userPd = aliPayService.getAmoutByuserId(pd);
				if(null==userPd||userPd.size()<=0){
					resultMap.put("status", AppConstant.ERR_CODE_1011);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1011));
					return resultMap;
				}
				//判断是否充值成功
				String p = aliPayService.getEaliPayStatusByRecordId(pd);
				resultMap.put(AppConstant.RESULT, p);
				if("failure".equals(p)){
					resultMap.put("status", AppConstant.ERR_CODE_1026);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1026));
					return resultMap;
				}else if ("success".equals(p)) {
					String AMOUNT = String.valueOf(userPd.get("AMOUNT"));//账户余额
					BigDecimal bigDecimal =  new BigDecimal(amount);//定金
					BigDecimal bigDecimal1 =  new BigDecimal(AMOUNT);//账户余额
					double doubleValue = bigDecimal.doubleValue();
					double doubleValue1 = bigDecimal1.doubleValue();
					if(doubleValue-doubleValue1<0){
						resultMap.put("status", AppConstant.ERR_CODE_1002);
						resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1002));
						return resultMap;
					}
					//查询到的余额
//					pd.put("AMOUNT", userPd.get("AMOUNT"));
					//充值成功，扣款
					aliPayService.debitAmountByUserId(pd);
				}
				
//				String amount =String.valueOf(p.get("AMOUNT"));
//				BigDecimal decimal = new BigDecimal(amount);
//				BigDecimal decimal1 = new BigDecimal(amountS);
//				double amountT = decimal.doubleValue();//钱包金额
//				double amountTS = decimal1.doubleValue();//前端传递的金额
			
//				resultMap.put(AppConstant.RESULT, p);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return AppUtil.returnMap(resultMap);
	}
	/**
	 * 支付宝充值
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/alipayRechargeByUserId")
	@ResponseBody
	public Map<String, Object> alipayRechargeByUserId(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String user_id = pd.getString("user_id");
			String amount = pd.getString("amount");
			if (Tools.isEmpty(user_id)||Tools.isEmpty(amount)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			} else {
				PageData pad = aliPayService.alipayRechargeByUserId(pd);
				resultMap.put(AppConstant.RESULT, pad);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return AppUtil.returnMap(resultMap);
	}
	/**
	 * 支付宝充值异步返回地址
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/notifyRecharge")
	@ResponseBody
	public String notifyRecharge(HttpServletRequest request) {
		
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		    String name = (String) iter.next();
		    String[] values = (String[]) requestParams.get(name);
		    String valueStr = "";
		    for (int i = 0; i < values.length; i++) {
		        valueStr = (i == values.length - 1) ? valueStr + values[i]
		                    : valueStr + values[i] + ",";
		  	}
		    //乱码解决，这段代码在出现乱码时使用。
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
//		PageData pd = this.getPageData();
		logger.info("验证支付宝充值返回的结果开始========");
		logger.info("验签数据====="+params.toString());
		// ----测试打印-----
		PrintStream out = System.out;
		PrintStream ps;
		try {
			ps = new PrintStream("D:/software/enterprise/apache-tomcat-7.0.67/logs/log.txt");
			logger.info(ps.toString());
			System.setOut(ps);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			logger.info("支付宝异步通知日志异常=====");
		}
		 System.out.println(params.toString());
		// 切换回打印到控制台
		System.setOut(out);
		logger.info("程序运行完毕，请查看日志");
		// System.out.println("程序运行完毕，请查看日志");
		logger.info("创建支付宝客户端开始===");
		AliAppPayClient aliAppPayClient = AliAppPayClient.getIncetent();// 获取支付宝客户端
		logger.info("创建支付宝客户端结束===");
		boolean signVerfied=false;
		try {
			logger.info("验签开始===========================");
//			signVerfied = aliAppPayClient.rsaCheckV2(pd);
			signVerfied = AlipaySignature.rsaCheckV1(params,  AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET,AlipayConfig.SIGNTYPE);
			logger.info("验签结束=====signVerfied="+signVerfied+"=====================");
			
		} catch (AlipayApiException e1) {
			e1.printStackTrace();
			logger.info("支付宝异步通知验签失败=====================");
			return "failure";
		}// 验签
		if (signVerfied) {
			// TODO 验签成功后
			// 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
			// 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
			// 3、校验通知中的seller_id（或者seller_email)
			// 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
			// 4、验证app_id是否为该商户本身
			// 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
			String out_trade_no = params.get("out_trade_no");
			String app_id = params.get("app_id");
			PageData data2 = new PageData();
			data2.put("RECORD_ID", out_trade_no);
			try {
				PageData byRecord = aliPayService.getEalipayByRecordId(data2);
				if(null==byRecord||byRecord.size()<=0){
					logger.info("验签失败,单据号不存在=====out_trade_no："+out_trade_no+"========");
					return  "failure";
				}
				String total_fee = String.valueOf(byRecord.get("TOTAL_AMOUNT"));//支付金额
				String total_amount = params.get("total_amount");//支付宝返回的金额
				if(!total_fee.equals(total_amount)){
					logger.info("金额不等，验签失败=====total_fee："+total_fee+"===total_amount:"+total_amount+"=====");
					return  "failure";
				}
				String seller_id = params.get("seller_id");
				String seller_email = params.get("seller_email");
				if(AlipayConfig.seller_id.equals(seller_id)||AlipayConfig.seller_email.equals(seller_email)){
				}else {
					logger.info("不是该商家下的支付宝，验签失败=====seller_id："+seller_id+"===seller_email:"+seller_email+"=====");
					return  "failure";
				}
				if(!AlipayConfig.APPID.equals(app_id)){
					logger.info("appid不一致，验签失败=====appid："+app_id+"========");
					return  "failure";
				}
				/*在上述验证通过后商户必须根据支付宝不同类型的业务通知
				，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
				在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，
				支付宝才会认定为买家付款成功。*/
				String trade_status = params.get("trade_status");
				logger.info("付款状态===trade_status"+trade_status); 
				if(AlipayConfig.TRADE_SUCCESS.equals(trade_status)||AlipayConfig.TRADE_FINISHED.equals(trade_status)){
					 aliPayService.handleAliPayDeposit(params);
				 }else {
					 return "failure";
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("支付宝获取异步通知保存异常==============");
				return "failure";
			}
		    return  "success";
		} else {
			// TODO 验签失败则记录异常日志，并在response中返回failure.
			logger.info("验签失败忽略结果=====");
			return "failure";
		}
	}
	/**
	 * 充值验证余额(校验)
	 * @return
	 */
	@RequestMapping("/getCommonEaliPayStatusByRecordId")
	@ResponseBody
	public Map<String, Object> getCommonEaliPayStatusByRecordId(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String user_id = pd.getString("user_id");
			String record_id = pd.getString("record_id");
			if (Tools.isEmpty(user_id)||Tools.isEmpty(record_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			} else {
				
				//判断用户是否存在
				PageData userPd = aliPayService.getAmoutByuserId(pd);
				if(null==userPd||userPd.size()<=0){
					resultMap.put("status", AppConstant.ERR_CODE_1011);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1011));
					return resultMap;
				}
				//判断是否充值成功
				String p = aliPayService.getEaliPayStatusByRecordId(pd);
				resultMap.put(AppConstant.RESULT, p);
				if("failure".equals(p)){
					resultMap.put("status", AppConstant.ERR_CODE_1026);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1026));
					return resultMap;
				}else if ("success".equals(p)) {
//					String AMOUNT = String.valueOf(userPd.get("AMOUNT"));//账户余额
					/*BigDecimal bigDecimal =  new BigDecimal(amount);//定金
					BigDecimal bigDecimal1 =  new BigDecimal(AMOUNT);//账户余额
					double doubleValue = bigDecimal.doubleValue();
					double doubleValue1 = bigDecimal1.doubleValue();
					if(doubleValue-doubleValue1<0){
						resultMap.put("status", AppConstant.ERR_CODE_1002);
						resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1002));
						return resultMap;
					}*/
					//查询到的余额
//					pd.put("AMOUNT", userPd.get("AMOUNT"));
					//充值成功
					aliPayService.rechargeAmountByUserId(pd);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return AppUtil.returnMap(resultMap);
	}
	
	
	
}
