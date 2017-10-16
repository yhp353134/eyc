package com.fh.service.app.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.service.app.appcommon.AppCommonService;
import com.fh.service.common.CommonService;
import com.fh.util.AppConstant;
import com.fh.util.AppUtil;
import com.fh.util.PageData;
import com.fh.util.PrimaryUtil;
import com.fh.util.SmsUtil;
import com.fh.util.SysDataCode;
import com.fh.util.Tools;

@Service("appOrderService")
public class AppOrderService {

	@Resource(name = "daoSupport")
    private DaoSupport dao;
	@Resource(name="appCommonService")
	private  AppCommonService  appCommonService;
	@Resource(name="commonService")
	private  CommonService  commonService;
    /**
     * 配货大厅
     * @param page
     * @return
     * @throws Exception 
     */
	@SuppressWarnings("unchecked")
	public List<PageData> getSupplyGoodsList(Page page) throws Exception {
		PageData pd = page.getPd();
		pd.getString("user_id");
		pd = this.getLineByUserId(pd);
        if (null==pd||pd.size()<=0) {//没有获取到匹配线路
			return null;
		}		
		page.setPd(pd);
		return (List<PageData>) dao.findForList("AppOrderMapper.getSupplyGoodsCorelistPage", page);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> getAllSupplyGoodsList(Page page) throws Exception {
//		PageData pd = page.getPd();
//		pd.getString("user_id");
//		pd = this.getLineByUserId(pd);
//		page.setPd(pd);
		return (List<PageData>) dao.findForList("AppOrderMapper.getSupplyGoodsCorelistPage", page);
	}
	/**
	 * 根据用户id获取路线
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public PageData getLineByUserId(PageData pd) throws Exception {
		List<PageData> list =  (List<PageData>) dao.findForList("AppOrderMapper.getLineByUserId", pd);
		if(CollectionUtils.isEmpty(list)||list.size()<=0){
			return null;
		}
		for (int i = 0; i < list.size(); i++) {
			pd.put("SRC_LINE_"+(i+1), list.get(i).get("SRC_CITY_ID"));
			pd.put("DES_LINE_"+(i+1), list.get(i).get("DES_CITY_ID"));
		}
		return pd;
	}
	/**
	 * 起始城市
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getStartCity(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppOrderMapper.getStartCity", pd);
	}
	/**
	 * 到达城市
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getEndCity(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppOrderMapper.getEndCity", pd);
	}
	
	/**
	 * 货源中心
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getSupplyGoodsCoreList(Page page) throws Exception {
		return (List<PageData>) dao.findForList("AppOrderMapper.getSupplyGoodsCorelistPage", page);
	}
	/**
	 * 货主的货源查询
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getOwnerSupplyGoodsList(Page page) throws Exception {
		return (List<PageData>) dao.findForList("AppOrderMapper.getOwnerSupplyGoodsList", page);
	}
	
	/**
	 * 保存发布货源
	 * @param pd
	 * @throws Exception 
	 */
	public Long savePublishSupplyGoods(PageData pd) throws Exception {
		Long pk_id = PrimaryUtil.getPrimary(); // 货源ID
		String consignee_user_id = pd.getString("consignee_user_id");	//发货人id
		String consignor_user_id = pd.getString("consignor_user_id");	//收货人id
		String consignee_user_name = pd.getString("consignee_user_name");	//发货人姓名
		String consignee_user_phone = pd.getString("consignee_user_phone");	//发货人phone
		String consignor_user_name = pd.getString("consignor_user_name");	//收货人姓名
		String consignor_user_phone = pd.getString("consignor_user_phone");	//收货人phone
		String user_id = pd.getString("user_id");	//货主id
		String modle_id = pd.getString("modle_id");	//车型id
		String is_tax = Tools.checkObj(pd.get("is_tax"));	//是否含税
		String is_took = Tools.checkObj(pd.get("is_took"));  // 是否取车  10011002  否   10011001 是
		String is_take = Tools.checkObj(pd.get("is_take"));  // 是否送车	10011002  否   10011001 是
		String is_tax_price = Tools.checkObj(pd.get("is_tax_price"));  //税费
		//如果含税，先存入含税的记录
		if ("10011001".equals(is_tax)) {
			PageData data10 = new PageData();
			data10.put("orderId", pk_id);
			data10.put("costType", SysDataCode.COST_TYPE_04);
			data10.put("amount", is_tax_price);
			data10.put("createBy", user_id);
			this.commonService.insertOrderCost(data10);
		}
		//如果不是自行取车，则会有取车费用
		if ("10011001".equals(is_took)) {
			PageData data11 = new PageData();
			data11.put("orderId", pk_id);
			data11.put("costType", SysDataCode.COST_TYPE_01);
			data11.put("amount", Tools.checkObj(pd.get("is_took_price")));
			data11.put("createBy", user_id);
			this.commonService.insertOrderCost(data11);
		}
		//如果上们收车，则有收车费用
		if ("10011001".equals(is_take)) {
			PageData data12 = new PageData();
			data12.put("orderId", pk_id);
			data12.put("costType", SysDataCode.COST_TYPE_02);
			data12.put("amount", Tools.checkObj(pd.get("is_take_price")));
			data12.put("createBy", user_id);
			this.commonService.insertOrderCost(data12);
		}
		
		//保存发货人
		if(Tools.isEmpty(consignee_user_id)) {
			PageData data =  new PageData();
			data.put("user_id", user_id);
			data.put("con_name", consignee_user_name);
			data.put("con_phone", consignee_user_phone);
			PageData data2 =  this.getBaseDealerByUserId(data);
			data.put("dealer_id", data2.get("DEALER_ID"));
			PageData Econtacts = this.saveEcontacts(data);
			pd.put("consignee_user_id", Econtacts.get("contacts_id"));
		}
		//保存收货人
		if(Tools.isEmpty(consignor_user_id)){
			PageData data =  new PageData();
			data.put("user_id", user_id);
			data.put("con_name", consignor_user_name);
			data.put("con_phone", consignor_user_phone);
			PageData data2 =  this.getBaseDealerByUserId(data);
			data.put("dealer_id", data2.get("DEALER_ID"));
			PageData Econtacts = this.saveEcontacts(data);
			pd.put("consignor_user_id", Econtacts.get("contacts_id"));
		}
		String begin_date = pd.getString("begin_date");//起运日期
		String end_date = pd.getString("end_date");//到达日期
		String b_add = pd.getString("b_add");//起运城市
		String b_city_id = pd.getString("b_city_id");//起运城市
		String b_add_detail_name = pd.getString("b_add_detail_name");//起运详细地址
		String e_add = pd.getString("e_add");//到达地址
		String e_city_id = pd.getString("e_city_id");//到达城市
		String e_add_detail_name = pd.getString("e_add_detail_name");//到达详细地址
		String direct_price = pd.getString("direct_price");//指导价
		String vin = pd.getString("vin");//车架号
		String is_took_vtc_id = Tools.checkObj(pd.get("is_took_vtc_id"));  //到达VTC的id
		String is_take_vtc_id = Tools.checkObj(pd.get("is_take_vtc_id"));  //起运VTC的id
		String total_price =  Tools.checkObj(pd.get("total_price"));  //总费用
		/***********************************start********************************************/
		//保存录入的货源
		 PageData  data  =  new PageData();
		 data.put("order_id", pk_id);
	     SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
	     data.put("begin_date", format.parse(begin_date));//起运日期
	     data.put("end_date", format.parse(end_date));//到达日期
	     data.put("b_add", b_add_detail_name);//起运详细地址
	     data.put("e_add", e_add_detail_name);//到达详细地址
	     data.put("b_city_id", Long.valueOf(b_city_id));//起运城市
	     data.put("b_city_name", b_add);//起运城市
	     data.put("e_city_name", e_add);//到达城市
	     data.put("e_city_id", Long.valueOf(e_city_id));//到达城市
	     data.put("vin",vin );//车架号
	     data.put("is_tax", Integer.valueOf(is_tax));//是否含税
	     data.put("publish_date", new Date());//发布时间
	     data.put("direct_price", Double.valueOf(direct_price));//指导价
	     if (!Tools.isEmpty(is_take_vtc_id)) {
	    	 data.put("is_take_vtc_id", Long.parseLong(is_take_vtc_id));//起运VTC的id
	     }
	     if (!Tools.isEmpty(is_took_vtc_id)) {
	    	 data.put("is_took_vtc_id", Long.parseLong(is_took_vtc_id));//到达VTC的id
	     }
	     if (!Tools.isEmpty(total_price)) {
	    	 data.put("total_price", Double.parseDouble(total_price));  //总费用
	     }
	     PageData eMat = new PageData();
	     eMat.put("id", modle_id);
	     PageData EautoMat =  this.getEautoMatById(eMat);
	     data.put("modle_id", EautoMat.get("ID"));
	     data.put("modle_name", EautoMat.get("NAME"));
	     PageData owner  =  new PageData();
	     owner.put("user_id", user_id);
	     //车主信息
		 PageData ownerUser = this.getSysUserByUserId(owner);
		 data.put("owner_id",ownerUser.get("USER_ID"));
		 data.put("owner_phone",ownerUser.get("USER_NAME"));
		 data.put("owner_name", ownerUser.get("NAME"));
		 data.put("dealer_id", ownerUser.get("DEALER_ID"));
	     //交车人
	     owner.remove("user_id");
	     owner.put("contacts_id", pd.get("consignor_user_id"));//发货人id
	     PageData Econtact = this.getEcontactsByContactsId(owner);
	     data.put("pay_id", Econtact.get("CONTACTS_ID"));
	     data.put("pay_name", Econtact.get("CON_NAME"));
	     data.put("pay_phone", Econtact.get("CON_PHONE"));
	     //收车人
	     owner.remove("user_id");
	     owner.put("contacts_id", pd.get("consignee_user_id"));//收货人id
	     PageData Econtact1 = this.getEcontactsByContactsId(owner);
	     data.put("re_id", Econtact1.get("CONTACTS_ID"));
	     data.put("re_name", Econtact1.get("CON_NAME"));
	     data.put("re_phone", Econtact1.get("CON_PHONE"));
	     data.put("user_id", pd.get("user_id"));
	     data.put("des", pd.getString("des"));//备注
	     data.put("is_took", is_took);
	     data.put("is_take", is_take);
	     /*******************************end************************************************/
		dao.save("AppOrderMapper.savePublishSupplyGoods", data);
		return pk_id;
	}
	
	/**
	 * 根据id查询物料表
	 * @param eMat
	 * @return
	 * @throws Exception 
	 */
	public PageData getEautoMatById(PageData eMat) throws Exception {
		return (PageData) dao.findForObject("AppOrderMapper.getEautoMatById", eMat);
	}
	/**
	 * 根据发货人id查询常用联系人
	 * @param owner
	 * @return
	 * @throws Exception 
	 */
	public PageData getEcontactsByContactsId(PageData owner) throws Exception {
		return (PageData) dao.findForObject("AppOrderMapper.getEcontactsByContactsId", owner);
	}
	/**
	 * 根据user_id查询经销商
	 * @param data
	 * @return
	 * @throws Exception 
	 */
	private PageData getBaseDealerByUserId(PageData data) throws Exception {
		return  (PageData) dao.findForObject("AppOrderMapper.getBaseDealerByUserId", data);
	}
	/**
	 * 保存常用联系人
	 * @param data
	 * @return
	 * @throws Exception 
	 */
	public PageData saveEcontacts(PageData data) throws Exception {
		long pk_id = PrimaryUtil.getPrimary();
		data.put("contacts_id", pk_id);
		dao.save("AppOrderMapper.saveEcontacts", data);
		return data;
	}
	/**
	 * 根据用户id查询用户信息
	 * @param pd
	 * user_id
	 * @return
	 * @throws Exception
	 */
	public PageData getSysUserByUserId(PageData pd) throws Exception {
		return  (PageData) dao.findForObject("AppOrderMapper.getSysUserByUserId", pd);
	}
	
	/**
	 * 获取货源详细信息
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	public PageData getOrderDetailInfoByOrderId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppOrderMapper.getOrderDetailInfoByOrderId", pd);
	}
	/**
	 * 根据货源查询报价明细
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getgetOrderDetailPriceInfoByOrderId(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppOrderMapper.getgetOrderDetailPriceInfoByOrderId", pd);
	}
	
	@SuppressWarnings("unchecked")
	public List<PageData> getgetOrderDetailPriceInfoByOrderIdNew(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppOrderMapper.getgetOrderDetailPriceInfoByOrderIdNew", pd);
	}
	
	/**
	 * 根据司机id查询车辆下的司机
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getSysUserAndEvehicleByDriverId(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppOrderMapper.getSysUserAndEvehicleByDriverId", pd);
	}
	/**
	 * 根据司机id查询车辆信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getEvehicleByDriverId(PageData pd) throws Exception {
		  return  (PageData) dao.findForObject("AppOrderMapper.getEvehicleByDriverId", pd);
	}
	/**
	 * 根据车辆id查询线路
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getElineByVehicleId(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppOrderMapper.getElineByVehicleId", pd);
	}
	/**
	 * 根据业务主键+类型查询附件
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getEfilesByObjectId(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppOrderMapper.getEfilesByObjectId", pd);
	}
	/**
	 * 抢单报价
	 * @param pd
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public synchronized  Map<String, Object> grabOrderAndSubPrice(PageData pd) throws Exception {
		List<PageData> list =  this.getEorderPriceByOrderId(pd);
		Map<String, Object> mp =  new HashMap<String,Object>();
		/*if (CollectionUtils.isNotEmpty(list)&&list.size()>=3) {
			mp.put("status", AppConstant.ERR_CODE_1016);
			mp.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1016));
			return mp;
		}else {*/
			String driver_id = pd.getString("driver_id");
			String child_user_id = pd.getString("child_user_id");//子账号id
			PageData  dd =  new PageData();
			dd.put("driver_id",driver_id );
			dd.put("order_id", Long.valueOf(pd.getString("order_id")));
			//已经报过价，不允许再报价
			List<PageData> list2 = (List<PageData>) dao.findForList("AppOrderMapper.getOrderPriceByOrderIdAndDriverId", dd);
			if(CollectionUtils.isNotEmpty(list2)&&list2.size()>0){
				mp.put("status", AppConstant.ERR_CODE_1021);
				mp.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1021));
				return mp;
			}
			PageData  param = new PageData();
			//获取用户
			PageData data = new PageData();
			data.put("user_id", driver_id);
			PageData sysUser = this.getSysUserByUserId(data);
			Long priceId = PrimaryUtil.getPrimary();
			param.put("price_id", priceId);
			param.put("order_id", Long.valueOf(pd.getString("order_id")));
			param.put("price_date", new Date());
			param.put("price",Double.valueOf(pd.getString("offer_price")) );
			PageData baseDealer = this.getBaseDealerByUserId(data);
			param.put("com_id", baseDealer.get("DEALER_ID"));
			param.put("com_name", baseDealer.getString("DEALER_NAME"));
			param.put("driver_id", sysUser.get("USER_ID"));
			param.put("driver_name", sysUser.get("NAME"));
			param.put("create_by", driver_id);
			param.put("business_by", child_user_id);
			param.put("create_date", new Date());
			dao.save("AppOrderMapper.saveGrabOrderAndSubPrice", param);
			/*if(list.size()==2){
				PageData  data2 =  new PageData();
				data2.put("order_id", Long.valueOf(pd.getString("order_id")));
				this.updateEorderStatusByOrderId(data2);
			}*/
			//先将原来的报价复制一份到附表里面
			pd.put("price_id", priceId);
			dao.save("AppOrderMapper.insertCopyThisPriceInNewTable", param);
			mp.put("status", "1000");
			mp.put("msg", AppUtil.getMessageInfoByKey("1000"));
			mp.put("result", "");
			/*PageData paramd = this.getSysUserByOrderId(pd);
			paramPd.put("order_no", pd.getString("order_id"));
			paramPd.put("send_user_id", driver_id);
			appCommonService.sendSmsMessage("xxxx", paramPd, paramd.getString("USER_NAME"), "");*/
			return mp;
//		}
	}
	
	/**
	 *  司机修改报价
	 * @param pd
	 * @throws Exception 
	 */
	public synchronized  Map<String, Object> updateOrderAndSubPrice(PageData pd) throws Exception {
		Map<String, Object> mp =  new HashMap<String,Object>();
		//先将原来的报价复制一份到附表里面
		dao.save("AppOrderMapper.copyThisPriceInNewTable", pd);
		//修改现在的报价状态和值
		dao.update("AppOrderMapper.updateThisPriceStatusByPriceId", pd);
		mp.put("status", "1000");
		mp.put("msg", AppUtil.getMessageInfoByKey("1000"));
		mp.put("result", "");
		return mp;
	}
	
	/**
	 * 修改货源为待确认状态
	 * @param data2
	 * @throws Exception 
	 */
	public void updateEorderStatusByOrderId(PageData data2) throws Exception {
		 dao.update("AppOrderMapper.updateEorderStatusByOrderId1", data2);
	}
	/**
	 * 根据订单id查询用户信息
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	public PageData getSysUserByOrderId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppOrderMapper.getSysUserByOrderId", pd);
	}
	/**
	 * 根据货源id查询报价
	 * order_id
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getEorderPriceByOrderId(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppOrderMapper.getEorderPriceByOrderId", pd);
	}
	
	/**
	 * 货主确认报价
	 * @param pd
	 * @throws Exception 
	 */
	public void ownerConfirmPrice(PageData pd) throws Exception {
		String order_id = pd.getString("order_id");
		//修改报价表数据
		dao.update("AppOrderMapper.updateOwnerConfirmPrice", pd);
		//获取订单报价表数据
		PageData data = this.getEorderPriceByPriceId(pd);
		//获取司机信息and车辆信息
		PageData Vehicle = this.getVehicleByDriverId(data);
		data.put("DRIVER_PHONE", Vehicle.get("USER_NAME"));
		data.put("VEHICLE_ID", Vehicle.get("VEHICLE_ID"));
		dao.update("AppOrderMapper.updateEorderByOrderId", data);
		// 保存订单轨迹
		this.saveEorderContrail(pd);
		//给司机发送通知
		String driverId = Tools.checkObj(data.get("DRIVER_ID"));
		String driverName = Tools.checkObj(data.get("DRIVER_NAME"));
		PageData msgPd = new PageData();
		msgPd.put("msgTitle", "确认报价");
		PageData comtentPd = new PageData();
		comtentPd.put("order_no", order_id);
		msgPd.put("msgContent", SmsUtil.getMessageAndReplaceKey("SMS_10017", comtentPd));
		msgPd.put("businessId", order_id);
		msgPd.put("businessType", SysDataCode.NOTICE_TYPE_JIGUANG_03);
		msgPd.put("receiveUserId", driverId);
		msgPd.put("receiveUserName", driverName);
		msgPd.put("msgUserType", SysDataCode.STATUS_TYPE_02);
		this.commonService.sendMessageByJpushAlisaByDriver(driverId, msgPd);
		//发送短信,未用
		/*PageData  paramPd = new PageData();
		paramPd.put("send_user_id", pd.getString("user_id"));
		paramPd.put("order_no", pd.getString("order_id"));
		appCommonService.sendSmsMessage("xxxx", paramPd, data.getString("DRIVER_PHONE"), "");*/
	}
	/**
	 * 保存成交节点
	 * @param pd
	 * @throws Exception 
	 */
	public void saveEorderContrail(PageData pd) throws Exception {
		PageData user = this.getSysUserByUserId(pd);
		pd.put("project_id",user.get("USER_ID"));
		pd.put("project_name",user.get("NAME"));
		pd.put("node_id", SysDataCode.CONTRAIL_TYP_01);
        pd.put("cost_id", PrimaryUtil.getPrimary());
        dao.save("AppOrderMapper.saveEorderContrail", pd);
	}
	/**
	 * 根据司机id查询车辆信息and 用户信息
	 * @param data
	 * @return
	 * @throws Exception 
	 */
	private PageData getVehicleByDriverId(PageData data) throws Exception {
		return (PageData) dao.findForObject("AppOrderMapper.getVehicleByDriverId", data);
	}
	/**
	 * 根据报价id获取报价表数据
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	private PageData getEorderPriceByPriceId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppOrderMapper.getEorderPriceByPriceId", pd);
	}
	/**
	 * 根据省市查询城市
	 * @param listAreaStart
	 * @return
	 * @throws Exception 
	 */
	public PageData getAllCity(List<PageData> listAreaStart) throws Exception {
		PageData da = new PageData();
		for (PageData pd : listAreaStart) {
			pd.put("type", SysDataCode.CITY_TYPE_02);
			List<PageData>  data = this.getCityByProvId(pd);
			da.put("_"+String.valueOf(pd.get("REGION_CODE")), data);
		}
		return da;
	}
	@SuppressWarnings("unchecked")
	public List<PageData> getCityByProvId(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppOrderMapper.getCityByProvId", pd);
	}
	/**
	 * 货主货源待确认接口
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getWaitSupplyGoodsCore(Page page) throws Exception {
		return (List<PageData>) dao.findForList("AppOrderMapper.getWaitSupplyGoodsCorelistPage", page);
	}
	/**
	 * 货主抢单中的货源
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getOfferSupplyGoodsCore(Page page) throws Exception {
		return (List<PageData>) dao.findForList("AppOrderMapper.getOfferSupplyGoodsCorelistPage", page);
	}
	/**
	 * 货主需重发的货源
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getAgainSupplyGoodsCoreList(Page page) throws Exception {
		return (List<PageData>) dao.findForList("AppOrderMapper.getAgainSupplyGoodsCorelistPage", page);
	}
	/**
	 * 撤销货源
	 * @param pd
	 * @throws Exception 
	 */
	public void cancelOwnerSupply(PageData pd) throws Exception {
		//PageData order = this.getEorderPriceByOrderIdRePd(pd);
		//货主主动撤销，需要扣100元违约金，返回100元
		PageData moneyPd = new PageData();
  		moneyPd.put("moneyType", SysDataCode.MONEY_DETAIL_03);
  		moneyPd.put("businessType", SysDataCode.MONEY_BUSINESS_DETAIL_01);
  		moneyPd.put("businessId",Tools.checkObj(pd.get("order_id")));
  		moneyPd.put("amount", -100);
  		moneyPd.put("userId", Tools.checkObj(pd.get("user_id")));
  		moneyPd.put("userName", null);
  		dao.save("SourceGoodsMapper.saveAmount", moneyPd);
		PageData moneyPd2 = new PageData();
		moneyPd2.put("moneyType", SysDataCode.MONEY_DETAIL_03);
		moneyPd2.put("businessType", SysDataCode.MONEY_BUSINESS_DETAIL_01);
		moneyPd2.put("businessId",Tools.checkObj(pd.get("order_id")));
		moneyPd2.put("amount", 100);
		moneyPd2.put("userId", Tools.checkObj(pd.get("user_id")));
		moneyPd2.put("userName", null);
  		dao.save("SourceGoodsMapper.saveAmount", moneyPd2);
  		//给货主账号添加100元
  		dao.update("AppOrderMapper.ownerAddOneHunderd", pd);
		//更改货源状态
		pd.put("status", SysDataCode.ORDER_STATUS_TYPE_03);
		dao.update("AppOrderMapper.cancelOwnerSupply", pd);
	}
	
	/**
	 * 根据订单id查询订单
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unused")
	private PageData getEorderPriceByOrderIdRePd(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppOrderMapper.getEorderPriceByOrderId", pd);
	}
	
	/**
	 * 重新发布货源
	 * @param pd
	 * @throws Exception 
	 */
	public void againPublishGoods(PageData pd) throws Exception {
		dao.save("AppOrderMapper.againPublishGoods", pd);
		dao.update("AppOrderMapper.updateEorderStatusByOrderId", pd);
	}
	
	/**
	 * 重新发布货源新版本
	 * @param pd
	 * @throws Exception 
	 */
	public void againPublishGoodsNew(PageData pd) throws Exception {
		Long orderNewId = PrimaryUtil.getPrimary();
		pd.put("orderNewId", orderNewId);
		String user_id = Tools.checkObj(pd.get("user_id"));
		String is_took = Tools.checkObj(pd.get("is_took"));  // 是否自行取车  10011002  否   10011001 是
		String is_take = Tools.checkObj(pd.get("is_take"));  // 是否上门收车	10011002  否   10011001 是
		String is_tax = Tools.checkObj(pd.get("is_tax"));  // 是否含税	10011002  否   10011001 是
		String is_took_price = Tools.checkObj(pd.get("is_took_price")); // 取车费用
		String is_take_price = Tools.checkObj(pd.get("is_take_price")); //送车费用
		String is_tax_price = Tools.checkObj(pd.get("is_tax_price")); //税费
		//如果含税，先存入含税的记录
		if ("10011001".equals(is_tax)) {
			PageData data10 = new PageData();
			data10.put("orderId", orderNewId);
			data10.put("costType", SysDataCode.COST_TYPE_04);
			data10.put("amount", is_tax_price);
			data10.put("createBy", user_id);
			this.commonService.insertOrderCost(data10);
		}
		//如果不是自行取车，则会有取车费用
		if ("10011001".equals(is_took)) {
			PageData data11 = new PageData();
			data11.put("orderId", orderNewId);
			data11.put("costType", SysDataCode.COST_TYPE_01);
			data11.put("amount", is_took_price);
			data11.put("createBy", user_id);
			this.commonService.insertOrderCost(data11);
		}
		//如果上们收车，则有收车费用
		if ("10011001".equals(is_take)) {
			PageData data12 = new PageData();
			data12.put("orderId", orderNewId);
			data12.put("costType", SysDataCode.COST_TYPE_02);
			data12.put("amount", is_take_price);
			data12.put("createBy", user_id);
			this.commonService.insertOrderCost(data12);
		}
		dao.update("AppOrderMapper.againPublishGoodsNew", pd);
		dao.update("AppOrderMapper.updateEorderStatusByOrderId", pd);
	}
	
	/**
	 * 重新发布货源撤销
	 * @param pd
	 * @throws Exception 
	 */
	public void againPublishGoodsRevoke(PageData pd) throws Exception {
		dao.update("AppOrderMapper.againPublishGoodsRevoke", pd);
	}
	

	
	/**
	 * 我的抢单(待确认)
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getmyGrabSinglelistPage(Page page) throws Exception {
		
		return (List<PageData>) dao.findForList("AppOrderMapper.getmyGrabSinglelistPage", page);
	}
	/**
	 * 我的抢单(失败的)
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getfailMyGrabSinglelistPage(Page page) throws Exception {
		return (List<PageData>) dao.findForList("AppOrderMapper.getfailMyGrabSinglelistPage", page);
	}
	
	/***获取司机信息***/
	public PageData getDriverDetailMsg(PageData pd) throws Exception {
		return (PageData)dao.findForObject("AppOrderMapper.getDriverDetailMsg", pd);
	}
	
	/***获取最短距离的VTC***/
	public PageData getShortDistanceByLonAndLat(PageData pd) throws Exception {
		return (PageData)dao.findForObject("AppOrderMapper.getShortDistanceByLonAndLat", pd);
	}
	
	/***获取列表距离的VTC***/
	@SuppressWarnings("unchecked")
	public List<PageData> getDistanceByLonAndLatList(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("AppOrderMapper.getDistanceByLonAndLatList", pd);
	}
	
	/***获取VTC列表***/
	@SuppressWarnings("unchecked")
	public List<PageData> getVtcListByIdAndStatuslistPage(Page page) throws Exception {
		return (List<PageData>)dao.findForList("AppOrderMapper.getVtcListByIdAndStatuslistPage", page);
	}
	
	/***获取VTC订单详情***/
	@SuppressWarnings("unchecked")
	public Map<String,Object> getVtcOrderDetail(PageData pd) throws Exception {
		return (Map<String,Object>)dao.findForObject("AppOrderMapper.getVtcOrderDetail", pd);
	}
	
	/***获取VTC列表***/
	@SuppressWarnings("unchecked")
	public List<PageData> getVtcListByIdPicList(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("AppOrderMapper.getVtcListByIdPicList", pd);
	}
	
	/**
	 *  短途司机从货主那里收件
	 * @throws Exception  异常
	 */
	public void vtcFromOwnerGetSomething(PageData pd) throws Exception {
		//首先将文件的业务ID更改成当前的货源ID
		String fileId =  Tools.checkObj(pd.get("fileId")); //文件ID
		String[] fileIds = fileId.split(",");
		pd.put("fileIds", fileIds);
		pd.put("fileStatus", SysDataCode.ISVALID_YES);
		dao.update("AppOrderMapper.updateVtcFromOwnerFileCommon", pd);
		//更新短途司机的收货状态
		pd.put("vtcDriverStatus", SysDataCode.VTC_BUSSINESS_STATUS_02);
		dao.update("AppOrderMapper.updateVtcOrderStatusCommon", pd);
		//更新订单状态
		pd.put("orderStatus", SysDataCode.ORDER_STATUS_TYPE_12);
		dao.update("AppOrderMapper.updateOrderStatusByIdCommon", pd);
	}
	
	/**
	 *  短途司机交车给VTC
	 * @throws Exception  异常
	 */
	public void vtcFromDealerPutSome(PageData pd) throws Exception {
		//更新短途司机的收货状态
		pd.put("vtcDriverStatus", SysDataCode.VTC_BUSSINESS_STATUS_03);
		dao.update("AppOrderMapper.updateVtcOrderStatusCommon", pd);
		//更新订单状态
		pd.put("orderStatus", SysDataCode.ORDER_STATUS_TYPE_13);
		dao.update("AppOrderMapper.updateOrderStatusByIdCommon", pd);
	}
	
	/**
	 *   短途司机从vtc取货
	 * @throws Exception  异常
	 */
	public void driverFromVtcGetSource(PageData pd) throws Exception {
		//更新短途司机的收货状态
		pd.put("vtcDriverStatus", SysDataCode.VTC_BUSSINESS_STATUS_02);
		dao.update("AppOrderMapper.updateVtcOrderStatusCommon", pd);
		//更新订单状态
		pd.put("orderStatus", SysDataCode.ORDER_STATUS_TYPE_14);
		dao.update("AppOrderMapper.updateOrderStatusByIdCommon", pd);
	}
	
	/**
	 *   短途司机将货交给货主
	 * @throws Exception  异常
	 */
	public void driverToOwnerPutSource(PageData pd) throws Exception {
		//首先将文件的业务ID更改成当前的货源ID
		String fileId =  Tools.checkObj(pd.get("fileId")); //文件ID
		String[] fileIds = fileId.split(",");
		pd.put("fileIds", fileIds);
		pd.put("fileStatus", SysDataCode.ISVALID_YES);
		dao.update("AppOrderMapper.updateVtcFromOwnerFileCommon", pd);
		//更新短途司机的收货状态
		pd.put("vtcDriverStatus", SysDataCode.VTC_BUSSINESS_STATUS_03);
		dao.update("AppOrderMapper.updateVtcOrderStatusCommon", pd);
		//更新订单状态
		pd.put("orderStatus", SysDataCode.ORDER_STATUS_TYPE_15);
		dao.update("AppOrderMapper.updateOrderStatusByIdCommon", pd);
	}
	
	/** 首先查询当前用户的账户余额**/
	public PageData getUserAcountNum(PageData pd) throws Exception{
		return (PageData)dao.findForObject("AppOrderMapper.getUserAcountNum", pd);
	}
	
	/**进行扣款**/
	public void payMoneyAndInRecord(PageData pd) throws Exception {
		//在钱包表里面新增一条记录
  		PageData moneyPd = new PageData();
  		moneyPd.put("moneyType", pd.get("payType"));
  		moneyPd.put("businessType", pd.get("payDetailType"));
  		moneyPd.put("businessId",Tools.checkObj(pd.get("orderId")));
  		moneyPd.put("amount", -Double.parseDouble(pd.getString("payNum")));
  		moneyPd.put("userId", Tools.checkObj(pd.get("userId")));
  		moneyPd.put("userName", pd.getString("userName"));
  		dao.save("SourceGoodsMapper.saveAmount", moneyPd);
  		//将账户的钱减去
  		dao.update("AppOrderMapper.updateUserMoneyByPayNum", pd);
	}
	
}
