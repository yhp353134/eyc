package com.fh.util.alipay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.fh.util.Logger;
import com.fh.util.PageData;
import com.fh.util.Tools;

public class AliAppPayClient {

	private static Logger logger = Logger.getLogger(AliAppPayClient.class);
	// URL 支付宝网关（固定） https://openapi.alipay.com/gateway.do
	// APP_ID APPID即创建应用后生成 获取见上面创建应用并获取APPID
	// APP_PRIVATE_KEY 开发者应用私钥，由开发者自己生成 获取见上面配置密钥
	// FORMAT 参数返回格式，只支持json json（固定）
	// CHARSET 请求和签名使用的字符编码格式，支持GBK和UTF-8 开发者根据实际工程编码配置
	// ALIPAY_PUBLIC_KEY 支付宝公钥，由支付宝生成 获取详见上面配置密钥
	// SIGN_TYPE 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2 RSA2
	private static AliAppPayClient aliAppPayClient = new AliAppPayClient();
	// 实例化客户端
	private static AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
			AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,
			AlipayConfig.SIGNTYPE);

	/**
	 * 实例
	 * 
	 * @return
	 */
	public static AliAppPayClient getIncetent() {
		if (null == aliAppPayClient) {
			aliAppPayClient = new AliAppPayClient();
		}
		if (null == alipayClient) {
			alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY,
					AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
		}
		return aliAppPayClient;
	}

	/**
	 * 生成订单
	 * 
	 * @param data
	 */
	public String createAppOrder(PageData data) {
		String body = "";
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody("我是测试数据");// 商品描述，可空
		model.setSubject("App支付测试Java");// 订单名称，必填
		model.setOutTradeNo("outtradeno");// 商户订单号，商户网站订单系统中唯一订单号，必填
		model.setTimeoutExpress("30m");// 超时时间 可空
		model.setTotalAmount("0.01");// 付款金额，必填
		model.setProductCode("QUICK_MSECURITY_PAY");// 销售产品码 必填
		request.setBizModel(model);
		request.setNotifyUrl(AlipayConfig.notify_url);// 设置异步通知地址
		request.setReturnUrl("www.baicu.com");// 设置同步地址
		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			System.out.println(response.getBody());// 就是orderString
													// 可以直接给客户端请求，无需再做处理。
			body = response.getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return body;

	}

	/**
	 * 验签支付宝返回的结果
	 * 
	 * @param pd
	 * @return
	 * @throws AlipayApiException
	 */
	public boolean rsaCheckV2(PageData pd) throws AlipayApiException {
		Map<String, String> param = aliAppPayClient.paraFilter(pd);
		try {
			logger.info("AlipaySignature.rsaCheckV2(param, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET)");
			boolean signVerified1 = AlipaySignature.rsaCheckV2(param, AlipayConfig.ALIPAY_PUBLIC_KEY,
					AlipayConfig.CHARSET);
			logger.info("===signVerified1:" + signVerified1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			logger.info("AlipaySignature.rsaCheckV1(param, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET)");
			boolean signVerified2 = AlipaySignature.rsaCheckV1(param, AlipayConfig.ALIPAY_PUBLIC_KEY,
					AlipayConfig.CHARSET);
			logger.info("===signVerified2:" + signVerified2);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			logger.info(
					"AlipaySignature.rsaCheckV1(param, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET,AlipayConfig.SIGNTYPE)");
			boolean signVerified3 = AlipaySignature.rsaCheckV1(param, AlipayConfig.ALIPAY_PUBLIC_KEY,
					AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);
			logger.info("===signVerified3:" + signVerified3);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		boolean signVerified = false;
		try {
			logger.info(
					"AlipaySignature.rsaCheckV2(param, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET,AlipayConfig.SIGNTYPE)");
			signVerified = AlipaySignature.rsaCheckV2(param, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET,
					AlipayConfig.SIGNTYPE);
			logger.info("===signVerified:" + signVerified);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// boolean signVerified = AlipaySignature.rsaCheckV2(pd,
		// AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET);
		// AlipaySignature.rsaCheckV1(pd, AlipayConfig.ALIPAY_PUBLIC_KEY,
		// AlipayConfig.CHARSET);
		return signVerified;
	}

	/**
	 * 过滤sign and sign_type
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> paraFilter(PageData pd) {
		PageData result = new PageData();
		if (pd == null || pd.size() <= 0) {
			return result;
		}
		Set<String> keySet = pd.keySet();
		for (String key : keySet) {
			String value = (String) pd.get(key);
			if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
					|| key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			result.put(key, value);
		}
		return result;
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	@SuppressWarnings("unchecked")
	public static String createLinkString(PageData params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);// 排序
		// String prestr = "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = String.valueOf(params.get(key));
			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				sb.append(key).append("=").append(value);
				// prestr = prestr + key + "=" + value;
			} else {
				sb.append(key).append("=").append(value).append("&");
				// prestr = prestr + key + "=" + value + "&";
			}
		}
		// return prestr;
		return sb.toString();
	}

	public static void main(String[] args) {
		AliAppPayClient aliAppPayClient = AliAppPayClient.getIncetent();
		String createAppOrder = aliAppPayClient.createAppDepositOrder(null);
		System.out.println(createAppOrder);

	}

	/**
	 * 创建支付定金
	 * 
	 * @param data
	 */
	public static String createAppDepositOrder(PageData data) {
		String body = "";
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody("货主支付定金");// 商品描述，可空
		model.setSubject("货主支付定金");// 订单名称，必填
		logger.info("RECORD_ID===" + data.get("RECORD_ID"));
		logger.info("data.getString(final_amount)===" + data.getString("final_amount"));
		model.setOutTradeNo(String.valueOf(data.get("RECORD_ID")));// 商户订单号，商户网站订单系统中唯一订单号，必填
		model.setTimeoutExpress("30m");// 超时时间 可空
		model.setTotalAmount(data.getString("final_amount"));// 付款金额，必填
		model.setProductCode("QUICK_MSECURITY_PAY");// 销售产品码 必填
		// model.setSellerId(AlipayConfig.partner);//支付宝账号
		request.setBizModel(model);
		request.setNotifyUrl(AlipayConfig.notify_url);// 设置异步通知地址
		// request.setReturnUrl(AlipayConfig.return_url);// 设置同步地址
		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			System.out.println(response.getBody());// 就是orderString
													// 可以直接给客户端请求，无需再做处理。
			body = response.getBody();
			logger.info("处理好之后的订单信息===" + body);
		} catch (AlipayApiException e) {
			e.printStackTrace();
			logger.info("创建支付定金异常=======================");
		}
		return body;
	}

	/**
	 * rsa2签名
	 * 
	 * @param content
	 * @return
	 */
	public String rsa256Sign(String content) {
		String rsa256Sign = "";
		try {
			rsa256Sign = AlipaySignature.rsa256Sign(content, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.CHARSET);
		} catch (AlipayApiException e) {
			logger.info("支付宝签名错误==================");
			e.printStackTrace();
		}
		return rsa256Sign;
	}

	/**
	 * 单笔转账接口 String outBizNo =pd.getString("outBizNo"); String payeeType
	 * =pd.getString("payeeType"); String payeeAccount
	 * =pd.getString("payeeAccount"); String amount =pd.getString("amount");
	 * String payeeRealName =pd.getString("payeeRealName");
	 * 
	 * @return
	 * @throws Exception
	 */
	public String singleTransferAlipay(PageData pd) throws Exception {
		AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayFundTransToaccountTransferModel model = new AlipayFundTransToaccountTransferModel();
		/*
		 * 1、OutBizNo商户转账唯一订单号。发起转账来源方定义的转账单据ID，用于将转账回执通知给来源方。
		 * 不同来源方给出的ID可以重复，同一个来源方必须保证其ID的唯一性。 只支持半角英文、数字，及“-”、“_”。
		 * 2、payee_type收款方账户类型。可取值：
		 * 1、ALIPAY_USERID：支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。
		 * 2、ALIPAY_LOGONID：支付宝登录号，支持邮箱和手机号格式。
		 * 3、payee_account收款方账户。与payee_type配合使用。付款方和收款方不能是同一个账户。
		 * 4、amount转账金额，单位：元。 只支持2位小数，小数点前最大支持13位，金额必须大于等于0.1元。
		 * 最大转账金额以实际签约的限额为准。 5、 payee_real_name收款方真实姓名（最长支持100个英文/50个汉字）。
		 * 如果本参数不为空，则会校验该账户在支付宝登记的实名是否与收款方真实姓名一致。
		 * 6、payer_show_name付款方姓名（最长支持100个英文/50个汉字）。显示在收款方的账单详情页。如果该字段不传，
		 * 则默认显示付款方的支付宝认证姓名或单位名称。 7 remark 备注 5-7选填 1-4必填
		 * 
		 */
		String outBizNo = pd.getString("outBizNo");
		String payeeType = pd.getString("payeeType");
		String payeeAccount = pd.getString("payeeAccount");
		String amount = pd.getString("amount");
		String payeeRealName = pd.getString("payeeRealName");
		if (Tools.isEmpty(outBizNo) || Tools.isEmpty(payeeType) || Tools.isEmpty(payeeAccount)
				|| Tools.isEmpty(amount)) {
			logger.info("支付宝单笔付款缺少参数");
			throw new Exception("缺少参数");
		} else {
			model.setOutBizNo(outBizNo);
			model.setPayeeType(payeeType);
			model.setPayeeAccount(payeeAccount);
			model.setAmount(amount);
			model.setPayeeRealName(payeeRealName);
			model.setRemark("货主支付宝体现");// 备注
		}
		request.setBizModel(model);
		request.setNotifyUrl(AlipayConfig.notify_url);// 通知返回的地址
		AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
		if (response.isSuccess()) {
			/*
			 * { "alipay_fund_trans_toaccount_transfer_response": { "code":
			 * "10000", "msg": "Success", "out_biz_no": "3142321423432",
			 * "order_id": "20160627110070001502260006780837", "pay_date":
			 * "2013-01-01 08:08:08" }, "sign":
			 * "ERITJKEIJKJHKKKKKKKHJEREEEEEEEEEEE" }
			 */

			System.out.println("调用成功");
		} else {
			System.out.println("调用失败");
		}
		return response.getBody();
	}

	/**
	 * 创建后续支付的金额
	 * 
	 * @param data
	 *            ORDER_ID 订单id AMOUNT 订单金额
	 * @return
	 */
	public static String createPaymentbalanceOrder(PageData pd) {
		String body = "";
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody("货主支付货款");// 商品描述，可空
		model.setSubject("货主支付货款");// 订单名称，必填
		model.setOutTradeNo(String.valueOf(pd.get("ORDER_ID")));// 商户订单号，商户网站订单系统中唯一订单号，必填
		model.setTimeoutExpress("30m");// 超时时间 可空

		model.setTotalAmount(pd.getString("AMOUNT"));// 付款金额，必填
		model.setProductCode("QUICK_MSECURITY_PAY");// 销售产品码 必填
		// model.setSellerId(AlipayConfig.partner);//支付宝账号
		request.setBizModel(model);
		request.setNotifyUrl(AlipayConfig.notify_url);// 设置异步通知地址
		// request.setReturnUrl(AlipayConfig.return_url);// 设置同步地址
		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			System.out.println(response.getBody());// 就是orderString
													// 可以直接给客户端请求，无需再做处理。
			body = response.getBody();
			logger.info("处理好之后的订单信息===" + body);
		} catch (AlipayApiException e) {
			e.printStackTrace();
			logger.info("创建支付余额异常=======================");
		}
		return body;
	}

	/**
	 * 创建充值订单
	 * 
	 * @param data
	 * @return
	 */
	public String alipayRecharge(PageData data) {
		String body = "";
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody("货主充值");// 商品描述，可空
		model.setSubject("货主充值");// 订单名称，必填
		logger.info("RECORD_ID===" + data.get("RECORD_ID"));
		logger.info("data.getString(final_amount)===" + data.getString("final_amount"));
		model.setOutTradeNo(String.valueOf(data.get("RECORD_ID")));// 商户订单号，商户网站订单系统中唯一订单号，必填
		model.setTimeoutExpress("30m");// 超时时间 可空
		model.setTotalAmount(data.getString("totle_amount"));// 付款金额，必填
		model.setProductCode("QUICK_MSECURITY_PAY");// 销售产品码 必填
		// model.setSellerId(AlipayConfig.partner);//支付宝账号
		request.setBizModel(model);
		request.setNotifyUrl(AlipayConfig.notify_url);// 设置异步通知地址
		// request.setReturnUrl(AlipayConfig.return_url);// 设置同步地址
		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			System.out.println(response.getBody());// 就是orderString
													// 可以直接给客户端请求，无需再做处理。
			body = response.getBody();
			logger.info("处理好之后的订单信息===" + body);
		} catch (AlipayApiException e) {
			e.printStackTrace();
			logger.info("创建支付定金异常=======================");
		}
		return body;
	}
}
