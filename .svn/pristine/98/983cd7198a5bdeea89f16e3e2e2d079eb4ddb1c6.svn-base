package com.fh.service.app.transport;

import java.util.Date;
/**
 * 2017年8月15日
 * 
 * @author quhy
 * 
 */
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.service.common.CommonService;
import com.fh.util.AppConstant;
import com.fh.util.PageData;
import com.fh.util.PrimaryUtil;
import com.fh.util.SysDataCode;
import com.fh.util.Tools;


@SuppressWarnings("unchecked")
@Service("appTransportService")
public class AppTransportService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	private Tools tools;
	@Resource(name = "commonService")
	private CommonService commonService;

	/**
	 * 新增路线
	 * 
	 * **/
	public String addRoute(PageData pd) throws Exception {
		Object srcRegionCode = pd.get("srcRegionCode");
		Object desRegionCode = pd.get("desRegionCode");
		Object frequency = pd.get("frequency");
		Object isBack = pd.get("isBack");
		Object userId = pd.get("userId");
		Long uuid = PrimaryUtil.getPrimary();
		pd.put("carId", uuid);
		pd.put("addDate", new Date());
		pd.put("status", SysDataCode.ISVALID_YES);// 默认有效
		if (srcRegionCode != null && !srcRegionCode.equals("")) {
			pd.put("srcRegionCode", srcRegionCode.toString());
		}
		if (desRegionCode != null && !desRegionCode.equals("")) {
			pd.put("desRegionCode", desRegionCode.toString());
		}
		if (frequency != null && !frequency.equals("")) {
			pd.put("frequency", Integer.parseInt(frequency.toString()));
		}

		if (isBack != null) {

			if (isBack.toString().equals("yes")) {
				pd.put("isBack", SysDataCode.STATUS_TYPE_01);
			} else if (isBack.toString().equals("no")) {
				pd.put("isBack", SysDataCode.STATUS_TYPE_02);
			}
		}
		if (userId != null && !userId.equals("")) {
			pd.put("addId", Long.valueOf(userId.toString()));
		}
		dao.save("AppELineMapper.addRoute", pd);
		return AppConstant.ERR_CODE_1000;
	}

	/**
	 * 修改路线
	 * 
	 * **/
	public String editRoute(PageData pd) throws Exception {
		Object srcRegionCode = pd.get("srcRegionCode");
		Object desRegionCode = pd.get("desRegionCode");
		Object frequency = pd.get("frequency");
		Object isBack = pd.get("isBack");
		Object userId = pd.get("userId");
		Object carId = pd.get("carId");
		Object flag = pd.get("flag");
		pd.put("updateDate", new Date());
		if (carId != null && !carId.equals("")) {
			pd.put("carId", Long.valueOf(carId.toString()));
		}
		if (srcRegionCode != null && !srcRegionCode.equals("")) {
			pd.put("srcRegionCode", srcRegionCode.toString());
		}
		if (desRegionCode != null && !desRegionCode.equals("")) {
			pd.put("desRegionCode", desRegionCode.toString());
		}
		if (frequency != null && !frequency.equals("")) {
			pd.put("frequency", Integer.parseInt(frequency.toString()));
		}

		if (isBack != null) {

			if (isBack.toString().equals("yes")) {
				pd.put("isBack", SysDataCode.STATUS_TYPE_01);
			} else if (isBack.toString().equals("no")) {
				pd.put("isBack", SysDataCode.STATUS_TYPE_02);
			}
		}
		if (flag != null) {

			if (flag.toString().equals("yes")) {
				pd.put("status", SysDataCode.ISVALID_YES);
			} else if (flag.toString().equals("no")) {
				pd.put("status", SysDataCode.ISVALID_NO);
			}
		}
		if (userId != null && !userId.equals("")) {
			pd.put("updateId", Long.valueOf(userId.toString()));
		}

		dao.save("AppELineMapper.editRoute", pd);
		return AppConstant.ERR_CODE_1000;
	}

	/**
	 * 删除路线
	 * 
	 * **/
	public String deleteRoute(PageData pd) throws Exception {
		pd.put("carId", Long.valueOf(pd.getString("carId").toString()));
		dao.delete("AppELineMapper.deleteRoute", pd);
		return AppConstant.ERR_CODE_1000;
	}

	/**
	 * 启用路线
	 * 
	 * **/
	public String switchRoute(PageData pd) throws Exception {
		Object carId = pd.get("carId");
		Object userId = pd.get("userId");
		Object flag = pd.get("flag");

		pd.put("updateDate", new Date());
		if (flag != null) {

			if (flag.toString().equals("yes")) {
				pd.put("status", SysDataCode.ISVALID_YES);
			} else if (flag.toString().equals("no")) {
				pd.put("status", SysDataCode.ISVALID_NO);
			}
		}

		if (carId != null && !carId.equals("")) {
			pd.put("carId", Long.valueOf(carId.toString()));
		}
		if (userId != null && !userId.equals("")) {
			pd.put("updateId", Long.valueOf(userId.toString()));
		}
		dao.save("AppELineMapper.switchRoute", pd);
		return AppConstant.ERR_CODE_1000;
	}

	/**
	 * 查询路线列表
	 * */
	public List<PageData> selectLine(PageData pd) throws Exception {
		Object userId = pd.get("userId");
		if (userId != null && !userId.equals("")) {
			pd.put("userId", Long.valueOf(userId.toString()));
		}
		return (List<PageData>) dao.findForList("AppELineMapper.selectLine", pd);
	}

	/**
	 * 获取运力中心
	 * */
	public List<PageData> selectRoute(Page page) throws Exception {
		return (List<PageData>) dao.findForList("AppELineMapper.selectRoutelistPage", page);
	}

	/**
	 * 获取运力中心详情
	 * */
	public PageData selectRouteDetail(PageData pd) throws Exception {
		pd.put("useType", SysDataCode.FILE_USE_TYPE_04);
		pd.put("useDetialType", SysDataCode.FILE_DETAIL_TYPE_05);
		pd.put("orderFlag", SysDataCode.STATUS_TYPE_01);
		pd.put("fileStatus", SysDataCode.ISVALID_YES);
		return (PageData) dao.findForObject("AppELineMapper.selectRouteDetail",pd);
	}

	/**
	 * 获取运力中心详情用用户id
	 * */
	public PageData selectRouteDetailByUserId(PageData pd) throws Exception {
		pd.put("useType", SysDataCode.FILE_USE_TYPE_04);
		pd.put("useDetialType", SysDataCode.FILE_DETAIL_TYPE_05);
		pd.put("orderFlag", SysDataCode.STATUS_TYPE_01);
		pd.put("fileStatus", SysDataCode.ISVALID_YES);
		return (PageData) dao.findForObject("AppELineMapper.selectRouteDetailByUserId", pd);
	}

	/**
	 * 获取运力中心详情的司机信息
	 * */
	public List<PageData> selectDriverDetail(PageData pd) throws Exception {
		pd.put("useType", SysDataCode.FILE_USE_TYPE_06);
		pd.put("useDetialType", SysDataCode.FILE_DETAIL_TYPE_01);
		pd.put("fileStatus", SysDataCode.ISVALID_YES);
		pd.put("orderFlag", SysDataCode.STATUS_TYPE_01);
		return (List<PageData>) dao.findForList("AppELineMapper.selectDriverDetail", pd);
	}

	/**
	 * 获取运力中心详情的常用路线
	 * */
	public List<PageData> selectLineDetail(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppELineMapper.selectLineDetail", pd);
	}

	/**
	 * 获取运力中心详情的车辆照片
	 * */
	public List<PageData> selectCarDetail(PageData pd) throws Exception {

		pd.put("fileStatus", SysDataCode.ISVALID_YES);
		// pd.put("useDetialType", SysDataCode.FILE_DETAIL_TYPE_05);
		pd.put("useType", SysDataCode.FILE_USE_TYPE_04);
		return (List<PageData>) dao.findForList("AppELineMapper.selectCarDetail", pd);
	}

	/**
	 * 获取运力中心详情的评价以及比例
	 * */
	public PageData selectRouteComment(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppELineMapper.selectRouteComment", pd);
	}

	/**
	 * 获取运力中心详情的各项评价
	 * */
	public List<PageData> selectRouteCommentDetail(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppELineMapper.selectRouteCommentDetail", pd);
	}

	/**
	 * 获取运力中心详情的历史评价数量
	 * */
	public PageData selectRouteCommentTotal(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppELineMapper.selectRouteCommentTotal", pd);
	}

	/**
	 * 获取运力中心详情的历史评价
	 * */
	public List<PageData> selectRouteCommentHistory(Page page) throws Exception {
		return (List<PageData>) dao.findForList("AppELineMapper.selectRouteCommentHistorylistPage", page);
	}

	/**
	 * 获取运力中心详情的历史评价图片
	 * */
	public List<PageData> selectRouteCommentHistoryPhoto(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppELineMapper.selectRouteCommentHistoryPhoto", pd);
	}


	/**
	 * 个人中心
	 * */
	public PageData initCenter(PageData pd) throws Exception {
		Object userId = pd.get("userId");
		if (userId != null && !userId.equals("")) {
			pd.put("userId", Long.valueOf(userId.toString()));
		}
		pd.put("tel", com.fh.util.Constants.SYSTEM_ELEPHONE);
		pd.put("useType", SysDataCode.FILE_USE_TYPE_06);
		pd.put("useDetialType", SysDataCode.FILE_DETAIL_TYPE_01);
		pd.put("fileStatus", SysDataCode.ISVALID_YES);
		return (PageData) dao.findForObject("AppELineMapper.initCenter", pd);
	}

	/********************************************* 订单相关 **************************************************************/

	/**
	 * 获取订单列表
	 * */
	public List<PageData> selectOrderList(Page page) throws Exception {
		return (List<PageData>) dao.findForList("AppELineMapper.selectOrderListlistPage", page);
	}

	/**
	 * 获取订单详情
	 * */
	public Map<String,Object> selectOrderListDetail(PageData pd) throws Exception {
		Object orderId = pd.get("orderId");
		if (orderId != null && !orderId.equals("")) {
			pd.put("orderId", Long.valueOf(orderId.toString()));
		}
		pd.put("useType", SysDataCode.FILE_USE_TYPE_06);
		pd.put("useDetialType", SysDataCode.FILE_DETAIL_TYPE_01);
		pd.put("fileStatus", SysDataCode.ISVALID_YES);
		return (Map<String,Object>) dao.findForObject("AppELineMapper.selectOrderListDetail", pd);
	}

	/**
	 * 获取订单物流信息
	 * */

	public List<PageData> selectOrderContrail(PageData pd) throws Exception {
		Object orderId = pd.get("orderId");
		if (orderId != null && !orderId.equals("")) {
			pd.put("orderId", Long.valueOf(orderId.toString()));
		}
		return (List<PageData>) dao.findForList("AppELineMapper.selectOrderContrail", pd);
	}

	/**
	 * 获取订单物流司机信息
	 * */

	public List<PageData> selectOrderDriver(PageData pd) throws Exception {
		Object orderId = pd.get("orderId");
		if (orderId != null && !orderId.equals("")) {
			pd.put("orderId", Long.valueOf(orderId.toString()));
		}
		pd.put("useType", SysDataCode.FILE_USE_TYPE_06);
		pd.put("useDetialType", SysDataCode.FILE_DETAIL_TYPE_01);
		pd.put("fileStatus", SysDataCode.ISVALID_YES);
		return (List<PageData>) dao.findForList("AppELineMapper.selectOrderDriver", pd);
	}

	/**
	 * 获取订单相关图片
	 * */

	public List<PageData> selectOrderPhoto(PageData pd) throws Exception {
		Object orderId = pd.get("orderId");
		if (orderId != null && !orderId.equals("")) {
			pd.put("orderId", Long.valueOf(orderId.toString()));
		}
		return (List<PageData>) dao.findForList("AppELineMapper.selectOrderPhoto", pd);
	}

	/**
	 * 获取订单评价
	 * */
	public PageData selectOrderComment(PageData pd) throws Exception {
		Object orderId = pd.get("orderId");
		if (orderId != null && !orderId.equals("")) {
			pd.put("orderId", Long.valueOf(orderId.toString()));
		}
		return (PageData) dao.findForObject("AppELineMapper.selectOrderComment", pd);
	}

	/**
	 * 获取订单评价
	 * */
	public List<PageData> selectOrderCommentDetail(PageData pd) 	throws Exception {
		Object orderId = pd.get("orderId");
		if (orderId != null && !orderId.equals("")) {
			pd.put("orderId", Long.valueOf(orderId.toString()));
		}
		return (List<PageData>) dao.findForList("AppELineMapper.selectOrderCommentDetail", pd);
	}

	/**
	 * 获取订单评论图片
	 * */
	public List<PageData> selectOrderCommentPhoto(PageData pd) throws Exception {
		Object orderId = pd.get("orderId");
		if (orderId != null && !orderId.equals("")) {
			pd.put("orderId", Long.valueOf(orderId.toString()));
		}
		pd.put("useType", SysDataCode.FILE_USE_TYPE_08);
		pd.put("useDetialType", SysDataCode.FILE_DETAIL_TYPE_09);
		pd.put("fileStatus", SysDataCode.ISVALID_YES);
		return (List<PageData>) dao.findForList("AppELineMapper.selectOrderPhoto", pd);
	}

	/**
	 * 获取订单位置
	 * */

	public PageData selectOrderLocation(PageData pd) throws Exception {
		Object orderId = pd.get("orderId");
		if (orderId != null && !orderId.equals("")) {
			pd.put("orderId", Long.valueOf(orderId.toString()));
		}
		return (PageData) dao.findForObject("AppELineMapper.selectOrderLocation", pd);
	}


	/**
	 * 货主撤单
	 * */
	public String cancelOrder(PageData pd) throws Exception {
		Object orderId = pd.get("orderId");
		Object userId = pd.get("userId");
		String remark = pd.getString("remark");
		Long uuid = PrimaryUtil.getPrimary();
		// 改变订单状态
		if (orderId != null && !orderId.equals("")) {
			pd.put("orderId", Long.valueOf(orderId.toString()));
		}
		pd.put("status", SysDataCode.ORDER_STATUS_TYPE_05);
		// 增加接点
		pd.put("costId", uuid);
		if (userId != null && userId.equals("")) {
			pd.put("userId", Long.valueOf(userId.toString()));
		}
		if (remark != null && !remark.equals("")) {
			pd.put("remark", remark);
		}
		pd.put("nodeId", SysDataCode.CONTRAIL_TYP_07);
		dao.update("AppELineMapper.cancelOrder", pd);
		dao.save("AppELineMapper.saveOrderContrail", pd);
		return AppConstant.ERR_CODE_1000;
	}

	/**
	 * 司机上传图片收车
	 * */

	@SuppressWarnings("static-access")
	public String getOrder(PageData pd) throws Exception {
		Object orderId = pd.get("orderId");
		String fileId = pd.getString("fileId");
		Object userId = pd.get("userId");
		String des = pd.getString("des");
		Object logitude = pd.get("logitude");
		Object latitude = pd.get("latitude");
		String address = pd.getString("address");

		Long uuid = PrimaryUtil.getPrimary();

		// 改变订单状态
		if (orderId != null && !orderId.equals("")) {
			pd.put("orderId", Long.valueOf(orderId.toString()));
		}
		pd.put("status", SysDataCode.ORDER_STATUS_TYPE_06);
		dao.update("AppELineMapper.getOrder", pd);

		// 增加接点

		pd.put("costId", uuid);
		if (userId != null && userId.equals("")) {
			pd.put("userId", Long.valueOf(userId.toString()));
		}
		if (des != null) {
			pd.put("remark", des);
		}
		pd.put("nodeId", SysDataCode.CONTRAIL_TYP_02);
		dao.save("AppELineMapper.saveOrderContrail", pd);
		uuid = PrimaryUtil.getPrimary();
		pd.put("costId", uuid);
		pd.put("nodeId", SysDataCode.CONTRAIL_TYP_03);
		dao.save("AppELineMapper.saveOrderContrail", pd);

		// 存入照片
		String[] fileIds = tools.str2StrArray(fileId);
		pd.put("useType", SysDataCode.FILE_USE_TYPE_08);
		pd.put("useDetialType", SysDataCode.FILE_DETAIL_TYPE_07);

		if (fileIds != null && fileIds.length > 0) {

			for (int i = 0; i < fileIds.length; i++) {

				if (fileIds[i] != null && !fileIds[i].equals("")) {
					pd.put("fileId", Long.valueOf(fileIds[i]));
				}
				dao.save("AppELineMapper.saveFile", pd);
			}

		}
		// 添加消息
		PageData msg = new PageData();
		Long msgId = PrimaryUtil.getPrimary();
		Long msgReceiveId = PrimaryUtil.getPrimary();
		Long order = Long.valueOf(orderId.toString());
		msg.put("orderId", order);
		msg.put("msgId", msgId);
		msg.put("msgReceiveId", msgReceiveId);
		msg.put("msgType", SysDataCode.MSG_TYPE_02);
		msg.put("msgTitle", "收车通知");
		msg.put("msgContent", "【e运车】提醒您，编号" + order
				+ "的订单已经发车啦，请保证手机畅通，便于司机及时联系您！");
		msg.put("sendUserId", Long.valueOf(userId.toString()));
		msg.put("msgUserType", SysDataCode.STATUS_TYPE_01);
		msg.put("rangFlag", SysDataCode.STATUS_TYPE_02);
		dao.save("AppELineMapper.saveMsg", msg);
		dao.save("AppELineMapper.saveMsgReceive", msg);

		// 上报位置

		Long locationId = PrimaryUtil.getPrimary();
		PageData location = new PageData();
		location.put("type", SysDataCode.ADDRESS_TYPE_01);
		location.put("upType", SysDataCode.REPORT_TYPE_02);
		location.put("locationId", locationId);
		location.put("orderId", order);
		if (logitude != null && !logitude.equals("")) {
			location.put("logitude", Float.parseFloat(logitude.toString()));
		}
		if (latitude != null && !latitude.equals("")) {
			location.put("latitude", Float.parseFloat(latitude.toString()));
		}
		if (address != null && !address.equals("")) {
			location.put("address", address);
		}
		dao.save("AppELineMapper.saveLocation", location);
		return AppConstant.ERR_CODE_1000;
	}

	/**
	 * 司机确认到达
	 * */

	public String arriveOrder(PageData pd) throws Exception {
		Object orderId = pd.get("orderId");
		Object userId = pd.get("userId");
		Object logitude = pd.get("logitude");
		Object latitude = pd.get("latitude");
		String address = pd.getString("address");
		Long uuid = PrimaryUtil.getPrimary();
		if (orderId != null && !orderId.equals("")) {
			pd.put("orderId", Long.valueOf(orderId.toString()));
		}
		pd.put("status", SysDataCode.ORDER_STATUS_TYPE_07);
		pd.put("costId", uuid);
		if (userId != null && userId.equals("")) {
			pd.put("userId", Long.valueOf(userId.toString()));
		}
		pd.put("nodeId", SysDataCode.CONTRAIL_TYP_04);
		dao.update("AppELineMapper.arriveOrder", pd);
		dao.save("AppELineMapper.saveOrderContrail", pd);

		// 上报位置

		Long locationId = PrimaryUtil.getPrimary();
		PageData location = new PageData();
		Long order = Long.valueOf(orderId.toString());
		location.put("locationId", locationId);
		location.put("orderId", order);
		location.put("type", SysDataCode.ADDRESS_TYPE_03);
		location.put("upType", SysDataCode.REPORT_TYPE_02);
		if (logitude != null && !logitude.equals("")) {
			location.put("logitude", Float.parseFloat(logitude.toString()));
		}
		if (latitude != null && !latitude.equals("")) {
			location.put("latitude", Float.parseFloat(latitude.toString()));
		}
		if (address != null && !address.equals("")) {
			location.put("address", address);
		}

		dao.save("AppELineMapper.saveLocation", location);

		return AppConstant.ERR_CODE_1000;
	}

	/**
	 * 司机上传图片交车
	 * */

	@SuppressWarnings("static-access")
	public String submitOrder(PageData pd) throws Exception {
		Object orderId = pd.get("orderId");
		String fileId = pd.getString("fileId");
		Object userId = pd.get("userId");
		String des = pd.getString("des");
		Long uuid = PrimaryUtil.getPrimary();
		// 改变订单状态
		if (orderId != null && !orderId.equals("")) {
			pd.put("orderId", Long.valueOf(orderId.toString()));
		}
		pd.put("status", SysDataCode.ORDER_STATUS_TYPE_08);
		dao.update("AppELineMapper.submitOrder", pd);
		// 增加接点
		if (des != null) {
			pd.put("remark", des);
		}
		pd.put("costId", uuid);
		if (userId != null && userId.equals("")) {
			pd.put("userId", Long.valueOf(userId.toString()));
		}
		pd.put("nodeId", SysDataCode.CONTRAIL_TYP_05);
		dao.save("AppELineMapper.saveOrderContrail", pd);
		// 存入照片
		String[] fileIds = tools.str2StrArray(fileId);
		pd.put("useType", SysDataCode.FILE_USE_TYPE_08);
		pd.put("useDetialType", SysDataCode.FILE_DETAIL_TYPE_08);
		if (fileIds != null && fileIds.length > 0) {
			for (int i = 0; i < fileIds.length; i++) {
				if (fileIds[i] != null && !fileIds[i].equals("")) {
					pd.put("fileId", Long.valueOf(fileIds[i]));
				}
				dao.save("AppELineMapper.saveFile", pd);
			}
		}

		// 添加消息
		/*
		 * PageData msg = new PageData(); Long msgId = PrimaryUtil.getPrimary();
		 * Long msgReceiveId = PrimaryUtil.getPrimary(); Long order =
		 * Long.valueOf(orderId.toString()); msg.put("orderId", order);
		 * msg.put("msgId", msgId); msg.put("msgReceiveId", msgReceiveId);
		 * msg.put("msgType", SysDataCode.MSG_TYPE_02); msg.put("msgTitle",
		 * "交车通知"); msg.put("msgContent",
		 * "【e运车】提醒您，编号"+order+"的订单已经交车啦，请进入系统进行交车确认！"); msg.put("sendUserId",
		 * Long.valueOf(userId.toString())); msg.put("msgUserType",
		 * SysDataCode.STATUS_TYPE_01); msg.put("rangFlag",
		 * SysDataCode.STATUS_TYPE_02); dao.save("AppELineMapper.saveMsg", msg);
		 * dao.save("AppELineMapper.saveMsgReceive", msg);
		 */
		// 根据货源ID获取货主信息
		PageData ownerPd = this.commonService.getOwnerMsgBySourceId(pd);
		// 极光推送
		Long order = Long.valueOf(orderId.toString());
		PageData msgPd = new PageData();
		msgPd.put("msgTitle", "交车通知");
		msgPd.put("msgContent", "【e运车】提醒您，编号" + order + "的订单已经交车啦，请进入系统进行交车确认！");
		msgPd.put("businessId", orderId.toString());
		msgPd.put("businessType", SysDataCode.NOTICE_TYPE_JIGUANG_07);
		msgPd.put("receiveUserId", Tools.checkObj(ownerPd.get("USER_ID")));
		msgPd.put("receiveUserName", Tools.checkObj(ownerPd.get("OWNER_NAME")));
		msgPd.put("msgUserType", SysDataCode.STATUS_TYPE_01);
		this.commonService.sendMessageByJpushAlisa(
				Tools.checkObj(ownerPd.get("USER_ID")), msgPd);
		return AppConstant.ERR_CODE_1000;
	}

	/**
	 * 货主确认交车
	 * */

	public String confirmOrder(PageData pd) throws Exception {
		Object orderId = pd.get("orderId");
		Object userId = pd.get("userId");
		Long uuid = PrimaryUtil.getPrimary();
		if (orderId != null && !orderId.equals("")) {
			pd.put("orderId", Long.valueOf(orderId.toString()));
		}
		pd.put("status", SysDataCode.ORDER_STATUS_TYPE_09);
		pd.put("costId", uuid);
		if (userId != null && userId.equals("")) {
			pd.put("userId", Long.valueOf(userId.toString()));
		}
		pd.put("nodeId", SysDataCode.CONTRAIL_TYP_08);
		dao.update("AppELineMapper.confirmOrder", pd);
		dao.save("AppELineMapper.saveOrderContrail", pd);
		// 添加消息
		PageData msg = new PageData();
		Long msgId = PrimaryUtil.getPrimary();
		Long msgReceiveId = PrimaryUtil.getPrimary();
		Long order = Long.valueOf(orderId.toString());
		msg.put("orderId", order);
		msg.put("msgId", msgId);
		msg.put("msgReceiveId", msgReceiveId);
		msg.put("msgType", SysDataCode.MSG_TYPE_02);
		msg.put("msgTitle", "确认交车通知");
		msg.put("msgContent", "【e运车】提醒您，编号" + order
				+ "的订单已经完成了，服务商正在等待您的评价，别让思念等的太久，赶紧登录APP写个评价吧！");
		msg.put("sendUserId", Long.valueOf(userId.toString()));
		msg.put("msgUserType", SysDataCode.STATUS_TYPE_02);
		msg.put("rangFlag", SysDataCode.STATUS_TYPE_02);
		dao.save("AppELineMapper.saveMsg", msg);
		dao.save("AppELineMapper.saveMsgReceive", msg);
		// 钱包记录 货主

		PageData amount = new PageData();
		Long recordId = PrimaryUtil.getPrimary();
		amount.put("recordId", recordId);
		amount.put("type", SysDataCode.MONEY_DETAIL_01);
		amount.put("userId", userId);
		amount.put("orderId", order);
		amount.put("businessType", SysDataCode.MONEY_BUSINESS_DETAIL_01);
		dao.save("AppELineMapper.saveAmountUser", amount);

		// 钱包记录司机
		recordId = PrimaryUtil.getPrimary();
		amount.put("recordId", recordId);
		dao.save("AppELineMapper.saveAmountDriver", amount);

		// 更新金额
		dao.save("AppELineMapper.editAmountUser", amount);
		dao.save("AppELineMapper.editAmountDriver", amount);
		return AppConstant.ERR_CODE_1000;
	}

	/**
	 * 添加评价
	 * 
	 **/
	@SuppressWarnings("static-access")
	public String addComment(PageData pd) throws Exception {
		PageData pc = new PageData();
		PageData pcd = new PageData();

		// Object commentId = pd.get("commentId");
		Object orderId = pd.get("orderId");
		Object userId = pd.get("userId");

		Object content = pd.get("content");// 评论
		Object conment = pd.get("conment");// 评价
		Object eContent = pd.get("eContent");// 平台评论
		String fileId = pd.getString("fileId");// 照片

		Object sConment = pd.get("sConment");// 服务评价
		Object fConment = pd.get("fConment");// 发车评价
		Object wConment = pd.get("wConment");// 物流评价

		Long uuidC = PrimaryUtil.getPrimary();
		Long uuidCs = PrimaryUtil.getPrimary();
		Long uuidCf = PrimaryUtil.getPrimary();
		Long uuidCw = PrimaryUtil.getPrimary();
		pc.put("commentId", uuidC);

		pc.put("commentTime", new Date());

		if (orderId != null && !orderId.equals("")) {
			pc.put("orderId", Long.valueOf(orderId.toString()));
		}
		if (userId != null && !userId.equals("")) {
			pc.put("userId", Long.valueOf(userId.toString()));
		}

		if (content != null) {
			pc.put("content", content.toString());
		}
		if (conment != null) {
			pc.put("conment", Integer.parseInt(conment.toString()));
		}
		if (eContent != null) {
			pc.put("eContent", eContent.toString());
		}

		dao.save("AppELineMapper.addComment", pc);
		pc.put("status", SysDataCode.ORDER_STATUS_TYPE_11);
		dao.update("AppELineMapper.commentOrder", pc); // 改变订单状态
		dao.update("AppELineMapper.commentVehicle", pc);// 改变车辆的评分
		// 存入照片
		String[] fileIds = tools.str2StrArray(fileId);
		pd.put("useType", SysDataCode.FILE_USE_TYPE_08);
		pd.put("useDetialType", SysDataCode.FILE_DETAIL_TYPE_09);
		pd.put("orderId", orderId);
		if (fileIds != null && fileIds.length > 0) {
			for (int i = 0; i < fileIds.length; i++) {

				if (fileIds[i] != null && !fileIds[i].equals("")) {
					pd.put("fileId", Long.valueOf(fileIds[i]));
				}

				dao.save("AppELineMapper.saveFile", pd);
			}
		}
		if (sConment != null) {
			pcd.put("conment", Integer.parseInt(sConment.toString()));
		}
		pcd.put("commentId", uuidC);
		pcd.put("detId", uuidCs);
		pcd.put("projectName", "服务态度");
		pcd.put("projectCode", "sConment");
		dao.save("AppELineMapper.addCommentDetail", pcd);
		if (fConment != null) {
			pcd.put("conment", Integer.parseInt(fConment.toString()));
		}
		pcd.put("commentId", uuidC);
		pcd.put("detId", uuidCf);
		pcd.put("projectName", "发车速度");
		pcd.put("projectCode", "fConment");
		dao.save("AppELineMapper.addCommentDetail", pcd);

		if (wConment != null) {
			pcd.put("conment", Integer.parseInt(wConment.toString()));
		}
		pcd.put("commentId", uuidC);
		pcd.put("detId", uuidCw);
		pcd.put("projectName", "准点率");
		pcd.put("projectCode", "wConment");
		dao.save("AppELineMapper.addCommentDetail", pcd);
		// 增加节点
		Long uuid = PrimaryUtil.getPrimary();
		pd.put("costId", uuid);
		pd.put("userId", Long.valueOf(userId.toString()));
		pd.put("nodeId", SysDataCode.CONTRAIL_TYP_06);
		dao.save("AppELineMapper.saveOrderContrail", pd);
		return "1000";
	}

	/**
	 * 获取消息列表
	 * */
	public List<PageData> selectMsg(Page page) throws Exception {
		return (List<PageData>) dao.findForList("AppELineMapper.selectMsglistPage", page);
	}

	/**
	 * 获取钱包明细列表
	 * */
	public List<PageData> selectMoney(Page page) throws Exception {
		return (List<PageData>) dao.findForList("AppELineMapper.selectMoneylistPage", page);
	}

	/**
	 * 获取钱包总格
	 * */
	public PageData selectMoneyTotal(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppELineMapper.selectMoneyTotal",pd);
	}

	/**
	 * 意见反馈接口
	 * @throws Exception
	 * */
	public void feedBackSave(PageData pd) throws Exception {
		// 主键
		Long pm = PrimaryUtil.getPrimary();
		pd.put("feedbackId", pm);
		// 插入反馈图片的
		String feebbackPic = Tools.checkObj(pd.get("feedbackPic"));
		if (!Tools.isEmpty(feebbackPic)) {
			String[] arr = feebbackPic.split(",");
			for (int i = 0; i < arr.length; i++) {
				// 将图片文件业务ID修改成当前意见反馈的ID
				pd.put("picId", arr[i].trim());
				dao.update("AppELineMapper.updateFeedBackPicObectId", pd);
			}
		}
		// 插入反馈信息
		dao.save("AppELineMapper.feedBackSave", pd);
	}

	/**
	 * 广告列表
	 * */
	public List<Map<String, Object>> getAdvertList() throws Exception {
		return (List<Map<String, Object>>) dao.findForList("AppELineMapper.getAdvertList", null);
	}

	/**
	 * 广告详情
	 * */
	public Map<String, Object> getAdvertDetail(PageData pd) throws Exception {
		return (Map<String, Object>) dao.findForObject("AppELineMapper.getAdvertDetail", pd);
	}

	/****
	 * 读消息
	 * */
	public void readMsgByMsgId(PageData pd) throws Exception {
		String rangFlag = Tools.checkObj(pd.get("rangFlag"));
		if ("10011001".equals(rangFlag)) {
			// 全网消息
			dao.save("AppELineMapper.insertMsgByMsgIdSmall", pd);
		} else {
			dao.update("AppELineMapper.readMsgByMsgIdSmall", pd);
		}
	}

	/**
	 * 消息中心读取
	 * */
	public void readAllMsg(PageData pd) throws Exception {
		List<Map<String, Object>> list = (List<Map<String, Object>>) dao
				.findForList("AppELineMapper.getMsgList", pd);
		if (!list.isEmpty() && 0 < list.size()) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> one = list.get(i);
				String rangFlag = Tools.checkObj(one.get("RANGE_FLAG"));
				if ("10011001".equals(rangFlag)) {
					// 全网消息
					pd.put("msgId", one.get("MSG_ID"));
					dao.save("AppELineMapper.insertMsgByMsgIdSmall", pd);
				} else {
					pd.put("msgId", one.get("MSG_ID"));
					dao.update("AppELineMapper.readMsgByMsgIdSmall", pd);
				}
			}
		}
	}

}
