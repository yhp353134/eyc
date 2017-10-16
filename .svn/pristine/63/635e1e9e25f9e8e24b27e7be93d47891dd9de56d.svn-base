package com.fh.service.system.dealer;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fh.controller.base.BaseService;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.service.app.appcommon.AppCommonService;
import com.fh.service.common.CommonService;
import com.fh.util.PageData;
import com.fh.util.PrimaryUtil;
import com.fh.util.SmsUtil;
import com.fh.util.SysDataCode;
import com.fh.util.Tools;
import com.fh.util.oss.OSSUploadUtil;

/***
 * 货源和订单
 * */
@Service("sourceGoodsService")
public class SourceGoodsService extends BaseService {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name = "appCommonService")
	private AppCommonService appCommonService;
	
	@Resource(name = "commonService")
	private CommonService commonService;

	/**货源列表*/
	@SuppressWarnings("unchecked")
	public List<PageData> getSourcelistPage(Page page) throws Exception {
		return (List<PageData>) dao.findForList("SourceGoodsMapper.getSourcelistPage", page);
	}
	
	/**订单列表*/
	@SuppressWarnings("unchecked")
	public List<PageData> getOrderlistPage(Page page) throws Exception {
		return (List<PageData>) dao.findForList("SourceGoodsMapper.getOrderlistPage", page);
	}
	
	/**货源详情*/
	public PageData getSourceDetailMsg(PageData pd) throws Exception {
		return (PageData) dao.findForObject("SourceGoodsMapper.getSourceDetailMsg", pd);
	}
	
	/**车型查询品牌*/
	public PageData getBrandByModelId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("SourceGoodsMapper.getBrandByModelId", pd);
	}
	
	/**订单轨迹*/
	@SuppressWarnings("unchecked")
	public List<PageData> getorderGuiList(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("SourceGoodsMapper.getorderGuiList", pd);
	}
	
	/**货源报价列表*/
	@SuppressWarnings("unchecked")
	public List<PageData> getSourceConfirmList(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("SourceGoodsMapper.getSourceConfirmList", pd);
	}
	
	/**订单评价记录*/
	@SuppressWarnings("unchecked")
	public List<PageData> getorderCommentList(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("SourceGoodsMapper.getorderCommentList", pd);
	}
	
	/**订单产生的图片*/
	@SuppressWarnings("unchecked")
	public List<PageData> getOrderPicList(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("SourceGoodsMapper.getOrderPicList", pd);
	}

	/**货源信息*/
	public PageData getSourceSingleMsg(PageData pd) throws Exception {
		return (PageData) dao.findForObject("SourceGoodsMapper.getSourceSingleMsg", pd);
	}
	
	/**获取评论*/
	public PageData getCommentBySourceId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("SourceGoodsMapper.getCommentBySourceId", pd);
	}
	
	/**货主信息*/
	public PageData getSourceOwnerMsg(@Param("telphone") String telphone) throws Exception {
		return (PageData) dao.findForObject("SourceGoodsMapper.getSourceOwnerMsg", telphone);
	}
	
	/**根据中文名匹配城市*/
	public PageData getCityMsgByName(@Param("cityName") String cityName) throws Exception {
		return (PageData) dao.findForObject("SourceGoodsMapper.getCityMsgByName", cityName);
	}
	
	/**根据中文名匹配车型*/
	public PageData getModelMsgByName(@Param("modelName") String modelName) throws Exception {
		return (PageData) dao.findForObject("SourceGoodsMapper.getModelMsgByName", modelName);
	}
	
	/**根据VIN匹配车辆*/
	public PageData getCarMsgByVin(@Param("vin") String vin) throws Exception {
		return (PageData) dao.findForObject("SourceGoodsMapper.getCarMsgByVin", vin);
	}
	
	/**货源信息*/
	public String checkUserMsgInSys(PageData pd) throws Exception {
		String sendId = "";
		PageData pdTemp = (PageData)dao.findForObject("SourceGoodsMapper.checkUserMsgInSys", pd);
		if (null == pdTemp || 0 == pdTemp.size()) {
			Long praId = PrimaryUtil.getPrimary();
			sendId = praId+"";
			pd.put("praId", praId);
			dao.save("SourceGoodsMapper.insertUserMsgSys", pd);
		} else {
			sendId = pdTemp.get("CONTACTS_ID").toString();
		}
		//返回联系人ID
		return sendId;
	}
	
	/**添加货源信息*/
	public void insertSourceGoodsMsg(List<Map<String,Object>> paramList) throws Exception {
		for (int i=0;i<paramList.size();i++) {
			Map<String,Object> one = paramList.get(i);
			dao.save("SourceGoodsMapper.insertSourceGoodsMsg", one);
		}
	}
	
	/**订单确认交车*/
	public void updateOrderStatus(PageData pd) throws Exception {
		//存系统消息
		Object orderId=pd.get("orderId");
		PageData msgPd = new PageData();
		msgPd.put("msgTitle", "确认收车");
		msgPd.put("msgContent", "提醒您，编号" + orderId + "的订单已经完成了，服务商正在等待您的评价，别让思念等的太久，赶紧登录APP写个评价吧！");
	    //群发就不要这两个参数
	    msgPd.put("receiveUserId", pd.get("driverId"));
	    msgPd.put("receiveUserName", pd.get("driverName"));
	    msgPd.put("msgUserType", SysDataCode.STATUS_TYPE_01);
	    PageData bcPd = this.commonService.saveMaMessage(msgPd);
		this.commonService.saveMaMessageCopy(bcPd);
		Object driverId=pd.get("driverId"); //司机ID
		//插入钱包记录
		PageData moneyPd = new PageData();
		moneyPd.put("moneyType", SysDataCode.MONEY_DETAIL_01);
		moneyPd.put("businessType", SysDataCode.MONEY_BUSINESS_DETAIL_01);
		moneyPd.put("businessId", orderId);
		moneyPd.put("amount", -Double.parseDouble(pd.get("price")==null?"0":pd.get("price").toString()));
		moneyPd.put("userId", pd.get("userId"));
		moneyPd.put("userName", pd.get("userName"));
		dao.save("SourceGoodsMapper.saveAmount", moneyPd);
		PageData moneyPds = new PageData();
		moneyPds.put("moneyType", SysDataCode.MONEY_DETAIL_01);
		moneyPds.put("businessType", SysDataCode.MONEY_BUSINESS_DETAIL_01);
		moneyPds.put("businessId", orderId);
		moneyPds.put("amount", Double.parseDouble(pd.get("price")==null?"0":pd.get("price").toString()));
		moneyPds.put("userId", driverId);
		moneyPds.put("userName", pd.get("userName"));
		dao.save("SourceGoodsMapper.saveAmount", moneyPds);
		//根据用户ID和orderId更新用户的金额
		dao.update("SourceGoodsMapper.updateUserAmount", pd);
		pd.put("userId", driverId);
		dao.update("SourceGoodsMapper.updateUserAmount", pd);
		//更改订单状态
		dao.update("SourceGoodsMapper.updateOrderStatus", pd);
	}
	
	/**获取线路*/
	@SuppressWarnings("unchecked")
	public Map<String,Object> getLineIdByLoad(PageData pd) throws Exception {
		return (Map<String,Object>)dao.findForObject("SourceGoodsMapper.getLineIdByLoad", pd);
	}
	
	/***确认报价 
	 * @throws Exception */
	public int confirmPriceByOwner(PageData pd) throws Exception {
		//获取货源Id 
		String sourceId = pd.get("hidOrderId")==null?"": pd.get("hidOrderId").toString();
		if (Tools.isEmpty(sourceId)) {
			return 0;
		}
		//报价ID
		String priceId = pd.get("hiddenPriceId")==null?"": pd.get("hiddenPriceId").toString();
		//司机信息
		String driverId = pd.get("hiddenDriverId")==null?"": pd.get("hiddenDriverId").toString();
		String driverName = pd.get("hiddenDriverName")==null?"": pd.get("hiddenDriverName").toString();
		PageData tp = new PageData();
		tp.put("priceId", priceId);
		tp.put("userId", pd.get("userId"));
		//根据价格ID获取基本信息
		PageData pricePd = (PageData)dao.findForObject("SourceGoodsMapper.getPricemsgByPriveId", tp);
		if (null == pricePd || "null".equals(pricePd)) {
			return 0;
		} else {
			//更新报价表
			dao.update("SourceGoodsMapper.updatePriceById", tp);
			//更新线索
			pricePd.put("orderId", Long.parseLong(sourceId));
			dao.update("SourceGoodsMapper.updateSourceByPricepd", pricePd);
			//给司机发送信息
			PageData msgPd = new PageData();
			msgPd.put("msgTitle", "确认报价");
			PageData comtentPd = new PageData();
			comtentPd.put("order_no", sourceId);
			msgPd.put("msgContent", SmsUtil.getMessageAndReplaceKey("SMS_10017", comtentPd));
			msgPd.put("businessId", sourceId);
			msgPd.put("businessType", SysDataCode.NOTICE_TYPE_JIGUANG_03);
			msgPd.put("receiveUserId", driverId);
			msgPd.put("receiveUserName", driverName);
			msgPd.put("msgUserType", SysDataCode.STATUS_TYPE_02);
			this.commonService.sendMessageByJpushAlisaByDriver(driverId, msgPd);
			// this.appCommonService.sendSmsMessage("SMS_10017", dd, pricePd.get("TEL")==null?"":pricePd.get("TEL").toString(), null);
		}
		return 1;
	}
	
	/***货主撤销货源或者订单 
	 * @throws Exception */
	public void revokeDataByIdAndType(PageData pd) throws Exception {
		Object dataType= pd.get("dataType"); // 1:表示货源   2:表示订单
		if ("1".equals(dataType)) {
			pd.put("dataStatus", SysDataCode.ORDER_STATUS_TYPE_03);
			dao.update("SourceGoodsMapper.revokeDataByIdAndType", pd);
		} else if ("2".equals(dataType)) {
			pd.put("dataStatus", SysDataCode.ORDER_STATUS_TYPE_05);
			dao.update("SourceGoodsMapper.revokeDataByIdAndType", pd);
		}
	}
	
	 /***承运商司机报价未成功的货源* */
	@SuppressWarnings("unchecked")
	public List<PageData> dealerDriverSourceFailerlistPage(Page page) throws Exception {
		return (List<PageData>) dao.findForList("SourceGoodsMapper.dealerDriverSourceFailerlistPage", page);
	}
	
	 /***承运商查看司机的订单信息* */
	@SuppressWarnings("unchecked")
	public List<PageData> dealerDriverSourceOrderlistPage(Page page) throws Exception {
		return (List<PageData>) dao.findForList("SourceGoodsMapper.dealerDriverSourceOrderlistPage", page);
	}
	
	/***承运商查看全国货源* */
	@SuppressWarnings("unchecked")
	public List<PageData> dealerDriverSourceChinalistPage(Page page) throws Exception {
		return (List<PageData>) dao.findForList("SourceGoodsMapper.dealerDriverSourceChinalistPage", page);
	}
	
	 /***承运商审核司机的报价列表* */
	@SuppressWarnings("unchecked")
	public List<PageData> dealerDriverExamineSourcelistPage(Page page) throws Exception {
		return (List<PageData>) dao.findForList("SourceGoodsMapper.dealerDriverExamineSourcelistPage", page);
	}
	
	/***承运商审核司机的货源 */
	public void dealerExamineDriverMsg(PageData pd) throws Exception {
	   String examineFlag = pd.getString("wxamineFlag");  // 1表示通过  2 表示驳回
       if ("1".equals(examineFlag)) {
    	   //通过
    	   pd.put("priceStatus", SysDataCode.EXAMINE_STATUS_02);
    	   //修改订单状态(不能修改，因为有多个承运商在抢货源)
    	   // dao.update("SourceGoodsMapper.updateDealerDriverExamineStatus", pd);
	       //给货主发通知
		   PageData paramd = (PageData)dao.findForObject("SourceGoodsMapper.getOwnerMsgByPriceId", pd);
		   PageData msgPd = new PageData();
		   msgPd.put("msgTitle", "司机报价");
		   PageData comtentPd = new PageData();
		   comtentPd.put("order_no",  paramd.get("ORDER_ID"));
		   msgPd.put("msgContent", SmsUtil.getMessageAndReplaceKey("SMS_90016", comtentPd));
		   msgPd.put("businessId", paramd.get("ORDER_ID"));
		   msgPd.put("businessType", SysDataCode.NOTICE_TYPE_JIGUANG_01);
		   msgPd.put("receiveUserId", paramd.get("USER_ID"));
		   msgPd.put("receiveUserName", paramd.get("NAME"));
		   msgPd.put("msgUserType", SysDataCode.STATUS_TYPE_01);
		   this.commonService.sendMessageByJpushAlisa(Tools.checkObj(paramd.get("USER_ID")), msgPd);
       } else {
    	   //驳回
    	   //给司机发通知
		   PageData paramd = (PageData)dao.findForObject("SourceGoodsMapper.getOwnerMsgByPriceId", pd);
		   PageData msgPd = new PageData();
		   msgPd.put("msgTitle", "审核报价");
		   PageData comtentPd = new PageData();
		   comtentPd.put("order_no",  paramd.get("ORDER_ID"));
		   msgPd.put("msgContent", "提醒您，您的货源编号为："+paramd.get("ORDER_ID")+"的报价未通过审核,驳回原因："+pd.get("examineSuggest")+",请及时重新报价");
		   msgPd.put("businessId", paramd.get("ORDER_ID"));
		   msgPd.put("businessType", SysDataCode.NOTICE_TYPE_JIGUANG_01);
		   msgPd.put("receiveUserId", paramd.get("DRIVER_ID"));
		   msgPd.put("receiveUserName", paramd.get("DRIVER_NAME"));
		   msgPd.put("msgUserType", SysDataCode.STATUS_TYPE_02);
		   this.commonService.sendMessageByJpushAlisaByDriver(String.valueOf(paramd.get("DRIVER_ID")), msgPd);
    	   pd.put("priceStatus", SysDataCode.EXAMINE_STATUS_03);
       }
       //修改报价信息
	   dao.update("SourceGoodsMapper.dealerDriverExamineReson", pd);
	}
	
	/**司机列表*/
	@SuppressWarnings("unchecked")
	public List<PageData> getDriverList(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("SourceGoodsMapper.getDriverList", pd);
	}
	
	/**插入一条提醒报价记录**/
	public void insertOfferNoticeMsg(PageData pd) throws Exception {
		dao.save("SourceGoodsMapper.insertOfferNoticeMsg", pd);
	}
	
	/**查询报价列表*/
	@SuppressWarnings("unchecked")
	public List<PageData> getOfferPriceList(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("SourceGoodsMapper.getOfferPriceList", pd);
	}
	
	/**查询通知列表*/
	@SuppressWarnings("unchecked")
	public List<PageData> getNoticePriceList(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("SourceGoodsMapper.getNoticePriceList", pd);
	}
	
	/***平台给货主充值* */
	@SuppressWarnings("unchecked")
	public List<PageData> platformRechargeInOwnerlistPage(Page page) throws Exception {
		return (List<PageData>) dao.findForList("SourceGoodsMapper.platformRechargeInOwnerlistPage", page);
	}
	
	/***平台给货主充值记录* */
	@SuppressWarnings("unchecked")
	public List<PageData> platformRechargenOwnerRecordlistPage(Page page) throws Exception {
		return (List<PageData>) dao.findForList("SourceGoodsMapper.platformRechargenOwnerRecordlistPage", page);
	}
	
	/***平台给货主充值* */
	@SuppressWarnings("unchecked")
	public List<PageData> platformRechargeInOwnerDilog(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("SourceGoodsMapper.platformRechargeInOwnerDilog", pd);
	}
	
	/***平台给货主充值 */
	public void platformRechargeInOwnerMoney(PageData pd) throws Exception {
		//更新用户的金额
		dao.update("SourceGoodsMapper.updateUserMoneyByUserIdInOwner", pd);
		//插入钱包记录
  		PageData moneyPd = new PageData();
  		moneyPd.put("moneyType", SysDataCode.MONEY_DETAIL_02);
  		moneyPd.put("businessType", SysDataCode.MONEY_BUSINESS_DETAIL_02);
  		moneyPd.put("businessId", pd.get("userId"));
  		moneyPd.put("amount", Double.parseDouble(pd.get("priceNum")==null?"0":pd.get("priceNum").toString()));
  		moneyPd.put("userId", pd.get("userId"));
  		moneyPd.put("userName", pd.get("userName"));
  		dao.save("SourceGoodsMapper.saveAmount", moneyPd);
	}
	
	/***意见反馈页面* */
	@SuppressWarnings("unchecked")
	public List<PageData> feedbackList(Page page) throws Exception {
		return (List<PageData>) dao.findForList("SourceGoodsMapper.feedbacklistPage", page);
	}
	
	/**货源详情*/
	public PageData feedbackDetail(PageData pd) throws Exception {
		return (PageData) dao.findForObject("SourceGoodsMapper.feedbackDetail", pd);
	}
	
	/***意见反馈图片* */
	@SuppressWarnings("unchecked")
	public List<PageData> feedbackPicList(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("SourceGoodsMapper.feedbackPicList", pd);
	}
	
	/***广告页面**/
	@SuppressWarnings("unchecked")
	public List<PageData> advertList(Page page) throws Exception {
		return (List<PageData>) dao.findForList("SourceGoodsMapper.advertList", page);
	}
	
	/***新增广告 */
	public void addAdverMsg(PageData pd,MultipartFile file) throws Exception {
		//上传广告
		Long advertId = PrimaryUtil.getPrimary();
		pd.put("advertId", advertId);
		dao.save("SourceGoodsMapper.addAdverMsg", pd);
		if (null != file && !file.isEmpty()) {
			//上传广告图片
			String path=OSSUploadUtil.uploadFile(file);
			String fileName = file.getOriginalFilename();
			PageData cp = new PageData();
			cp.put("objectId", advertId);
			cp.put("fileId", PrimaryUtil.getPrimary());
			cp.put("fileType", SysDataCode.FILE_TYPE_01);
			cp.put("fileUrl", path);
			cp.put("fileName", fileName);
			cp.put("userId", pd.get("userId"));
			cp.put("useType", SysDataCode.FILE_USE_TYPE_10);
			// 插入到附件表
			dao.save("DEALERMapper.insertFileImgInDealer", cp);
		}
	}
	
	/***修改广告 */
	public void updateAdverMsg(PageData pd,MultipartFile file) throws Exception {
		//修改广告信息内容
		dao.update("SourceGoodsMapper.updateAdverMsg", pd);
		//插入新的文件
		if (null != file && !file.isEmpty()) {
			//先将原来的图片状态改为不可用
			dao.update("SourceGoodsMapper.updateAdverFileMsg", pd);
			//上传广告图片
			String path=OSSUploadUtil.uploadFile(file);
			String fileName = file.getOriginalFilename();
			PageData cp = new PageData();
			cp.put("objectId", Tools.checkObj(pd.get("advertId")));
			cp.put("fileId", PrimaryUtil.getPrimary());
			cp.put("fileType", SysDataCode.FILE_TYPE_01);
			cp.put("fileUrl", path);
			cp.put("fileName", fileName);
			cp.put("userId", pd.get("userId"));
			cp.put("useType", SysDataCode.FILE_USE_TYPE_10);
			// 插入到附件表
			dao.save("DEALERMapper.insertFileImgInDealer", cp);
		}
	}
	
	/**广告详情*/
	public PageData adverDetialById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("SourceGoodsMapper.adverDetialById", pd);
	}
	
	/**广告图片*/
	public String getAdverDetialPicById(PageData pd) throws Exception {
		return (String) dao.findForObject("SourceGoodsMapper.getAdverDetialPicById", pd);
	}
	
	
}
