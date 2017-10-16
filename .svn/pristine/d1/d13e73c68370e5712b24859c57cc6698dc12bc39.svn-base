package com.fh.controller.app.order;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.app.appcommon.AppCommonService;
import com.fh.service.app.order.AppOrderService;
import com.fh.service.common.CommonService;
import com.fh.util.AppConstant;
import com.fh.util.AppUtil;
import com.fh.util.PageData;
import com.fh.util.SysDataCode;
import com.fh.util.Tools;
@Controller
@RequestMapping("/app/appOrder")
public class AppOrderController extends  BaseController {
	
	@Resource(name="appOrderService")
	private  AppOrderService  appOrderService;
	@Resource(name="appCommonService")
	private  AppCommonService  appCommonService;
	@Resource(name="commonService")
	private  CommonService  commonService;
	
	/**
	 * 配货大厅（司机端）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getSupplyGoods")
	@ResponseBody
	public Map<String, Object> getSupplyGoods(HttpServletRequest request ,HttpServletResponse response,Page page) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
           String user_id = pd.getString("user_id");//用户id
			if (Tools.isEmpty(user_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				pd.put("order_flag", SysDataCode.STATUS_TYPE_02);
				pd.put("status", SysDataCode.ORDER_STATUS_TYPE_01);
				page.setPd(pd);
				List<PageData> list = appOrderService.getSupplyGoodsList(page);
				if (CollectionUtils.isEmpty(list)||list.size()<=0) {
					resultMap.put("status", AppConstant.ERR_CODE_1014);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1014));
					return resultMap;
				}
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("list", list);
				map.put("page", page);
				resultMap.put(AppConstant.RESULT, map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  AppUtil.returnMap(resultMap);
	}
	
	/**
	 * 货源中心（司机端）查询所有的抢单中，
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getAllSupplyGoods")
	@ResponseBody
	public Map<String, Object> getAllSupplyGoods(HttpServletRequest request ,HttpServletResponse response,Page page) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			    PageData pd = this.getPageData();
			    String user_id = pd.getString("user_id");
			    if (Tools.isEmpty(user_id)) {
			    	resultMap.put("status", AppConstant.ERR_CODE_1003);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
					return resultMap;
				}else {
					pd.put("order_flag", SysDataCode.STATUS_TYPE_02);
					pd.put("status", SysDataCode.ORDER_STATUS_TYPE_01);
					page.setPd(pd);
					
					List<PageData> list = appOrderService.getAllSupplyGoodsList(page);
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("list", list);
					map.put("page", page);
					resultMap.put(AppConstant.RESULT, map);
				}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  AppUtil.returnMap(resultMap);
	}
	
	/**
	 * 货源中心（货主端）待确认货源--询价管理
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getWaitSupplyGoodsCore")
	@ResponseBody
	public Map<String, Object> getWaitSupplyGoodsCore(HttpServletRequest request ,HttpServletResponse response,Page page) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String user_id = pd.getString("user_id");//用户id
			if(Tools.isEmpty(user_id)){
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				pd.put("order_flag", SysDataCode.STATUS_TYPE_02);
				page.setPd(pd);
				List<PageData> list = appOrderService.getWaitSupplyGoodsCore(page);
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("list", list);
				map.put("page", page);
				resultMap.put(AppConstant.RESULT, map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  AppUtil.returnMap(resultMap);
	}
	/**
	 * 货源中心（货主端）抢单中
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getOfferSupplyGoodsCore")
	@ResponseBody
	public Map<String, Object> getOfferSupplyGoodsCore(HttpServletRequest request ,HttpServletResponse response,Page page) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String user_id = pd.getString("user_id");
			if(Tools.isEmpty(user_id)){
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				pd.put("status", SysDataCode.ORDER_STATUS_TYPE_01);
				pd.put("order_flag", SysDataCode.STATUS_TYPE_02);
				page.setPd(pd);
				List<PageData> list = appOrderService.getOfferSupplyGoodsCore(page);
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("list", list);
				map.put("page", page);
				resultMap.put(AppConstant.RESULT, map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  AppUtil.returnMap(resultMap);
	}
	/**
	 * 货源中心（货主端）需重发
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getAgainSupplyGoodsCore")
	@ResponseBody
	public Map<String, Object> getAgainSupplyGoodsCore(HttpServletRequest request ,HttpServletResponse response,Page page) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String user_id = pd.getString("user_id");
			if(Tools.isEmpty(user_id)){
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				page.setPd(pd);
				List<PageData> list = appOrderService.getAgainSupplyGoodsCoreList(page);
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("list", list);
				map.put("page", page);
				resultMap.put(AppConstant.RESULT, map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  AppUtil.returnMap(resultMap);
	}
	
	/**
	 * 获取起始城市
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping("/getEregionCity")
	@ResponseBody
	public Map<String, Object> getStartCity(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			Map<String, Object> map = new HashMap<String,Object>();
            //起始城市省份			
			pd.put("city_type", SysDataCode.CITY_TYPE_01);
			List<PageData> listAreaStart = appOrderService.getStartCity(pd);
			map.put("listAreaStart", listAreaStart);
			 //到大城市省份
//			List<PageData> listAreaEnd = appOrderService.getEndCity(pd);
//			map.put("listAreaEnd", listAreaEnd);
			
			//起始城市			
			pd.put("city_type", SysDataCode.CITY_TYPE_02);
//			List<PageData> listCityStart = appOrderService.getStartCity(pd);
//			map.put("listCityStart", listCityStart);
			PageData listCityStart = appOrderService.getAllCity(listAreaStart);
			map.put("listCityStart", listCityStart);
			/*	//到达城市			
			List<PageData> listCityEnd = appOrderService.getEndCity(pd);
			map.put("listCityEnd", listCityEnd);*/
			
			resultMap.put(AppConstant.RESULT, map);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  AppUtil.returnMap(resultMap);
	}
	
	
	/**
	 * 配货大厅（货主端）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getOwnerSupplyGoods")
	@ResponseBody
	public Map<String, Object> getOwnerSupplyGoods(HttpServletRequest request ,HttpServletResponse response,Page page) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
           String user_id = pd.getString("user_id");
			if (Tools.isEmpty(user_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				page.setPd(pd);
				List<PageData> list = appOrderService.getOwnerSupplyGoodsList(page);
				resultMap.put(AppConstant.RESULT, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  AppUtil.returnMap(resultMap);
	}
	
	/**
	 * 发布货源旧版本
	 * @return map
	 */
	@RequestMapping("/publishSupplyGoods")
	@ResponseBody
	public Map<String, Object> publishSupplyGoods(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String user_id = pd.getString("user_id");//货主id
			String begin_date = pd.getString("begin_date");//起运日期
			String end_date = pd.getString("end_date");//到达日期
			String b_add = pd.getString("b_add");//起运地址
			String e_add = pd.getString("e_add");//到达地址
			String direct_price = pd.getString("direct_price");//指导价
			String is_tax = pd.getString("is_tax");//是否含税
			String vin = pd.getString("vin");//车架号
			String b_add_detail_name = pd.getString("b_add_detail_name");//起运详细地址
			String e_add_detail_name = pd.getString("e_add_detail_name");//到达详细地址
			String modle_id = pd.getString("modle_id");//车型id
			String b_city_id = pd.getString("b_city_id");//起运地址
			String e_city_id = pd.getString("e_city_id");//到达城市
			/*String des = pd.getString("des"); //备注
			String consignee_user_id = pd.getString("consignee_user_id");//发货人id
			String consignor_user_id = pd.getString("consignor_user_id");//收货人id
			String consignee_user_name = pd.getString("consignee_user_name");//发货人姓名
			String consignor_user_name = pd.getString("consignor_user_name");//收货人姓名
			String consignee_user_phone = pd.getString("consignee_user_phone");//发货人phone
			String consignor_user_phone = pd.getString("consignor_user_phone");//收货人phone*/
			if((Tools.isEmpty(user_id)||Tools.isEmpty(begin_date)||Tools.isEmpty(end_date)
					||Tools.isEmpty(b_add)||Tools.isEmpty(e_add)||Tools.isEmpty(direct_price)
					||Tools.isEmpty(is_tax)||Tools.isEmpty(vin)||Tools.isEmpty(b_add_detail_name)
					||Tools.isEmpty(e_add_detail_name)||Tools.isEmpty(modle_id)
					||Tools.isEmpty(b_city_id)||Tools.isEmpty(e_city_id)
					)){
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				PageData user = appOrderService.getSysUserByUserId(pd);
				if(!String.valueOf(SysDataCode.EXAMINE_STATUS_02).equals(String.valueOf(user.get("AUDIT_STATUS")))){
					resultMap.put("status", AppConstant.ERR_CODE_1017);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1017));
					return resultMap;
				}
				appOrderService.savePublishSupplyGoods(pd);
			    pd.put("src_city_id",b_city_id );
			    pd.put("des_city_id",e_city_id);
			    Set<PageData> tempSet = new HashSet<PageData>();
			    tempSet.add(pd);
			    this.commonService.sendMessageByJpushAlisaByLine(tempSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  resultMap;
	}
	
	/**
	 * 发布货源新版本
	 * @return map
	 */
	@RequestMapping("/publishSupplyGoodsNews")
	@ResponseBody
	public Map<String, Object> publishSupplyGoodsNews(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String user_id = pd.getString("user_id");//货主ID
			String begin_date = pd.getString("begin_date");//起运日期
			String end_date = pd.getString("end_date");//到达日期
			String b_add = pd.getString("b_add");	//起运地址
			String e_add = pd.getString("e_add");	//到达地址
			String direct_price = pd.getString("direct_price");	//指导价
			String is_tax = pd.getString("is_tax");	//是否含税
			String vin = pd.getString("vin");	//车架号
			String b_add_detail_name = pd.getString("b_add_detail_name");//起运详细地址
			String e_add_detail_name = pd.getString("e_add_detail_name");//到达详细地址
			String modle_id = pd.getString("modle_id");	 //车型id
			String b_city_id = pd.getString("b_city_id");	//起运地址
			String e_city_id = pd.getString("e_city_id");	//到达城市
			String is_took = Tools.checkObj(pd.get("is_took"));  // 是否自行取车  10011002  否   10011001 是
			String is_take = Tools.checkObj(pd.get("is_take"));  // 是否上门收车	10011002  否   10011001 是
			String is_took_vtc_id = Tools.checkObj(pd.get("is_took_vtc_id"));  //到达VTC的id
			String is_take_vtc_id = Tools.checkObj(pd.get("is_take_vtc_id"));  //起运VTC的id
			/*String des = pd.getString("des"); //备注
			String consignee_user_id = pd.getString("consignee_user_id");//发货人id
			String consignor_user_id = pd.getString("consignor_user_id");//收货人id
			String consignee_user_name = pd.getString("consignee_user_name");//发货人姓名
			String consignor_user_name = pd.getString("consignor_user_name");//收货人姓名
			String consignee_user_phone = pd.getString("consignee_user_phone");//发货人phone
			String consignor_user_phone = pd.getString("consignor_user_phone");//收货人phone*/
			if((Tools.isEmpty(user_id)||Tools.isEmpty(begin_date)||Tools.isEmpty(end_date)
					||Tools.isEmpty(b_add)||Tools.isEmpty(e_add)||Tools.isEmpty(direct_price)
					||Tools.isEmpty(is_tax)||Tools.isEmpty(vin)||Tools.isEmpty(b_add_detail_name)
					||Tools.isEmpty(e_add_detail_name)||Tools.isEmpty(modle_id)
					||Tools.isEmpty(b_city_id)||Tools.isEmpty(e_city_id)
					|| Tools.isEmpty(is_took) || Tools.isEmpty(is_take)
					|| Tools.isEmpty(is_took_vtc_id) || Tools.isEmpty(is_take_vtc_id)
					)){
				//比传参数校验
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				//查询用户信息
				PageData user = appOrderService.getSysUserByUserId(pd);
				if(!String.valueOf(SysDataCode.EXAMINE_STATUS_02).equals(String.valueOf(user.get("AUDIT_STATUS")))){
					resultMap.put("status", AppConstant.ERR_CODE_1017);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1017));
					return resultMap;
				}
				//发布货源
				Long sourceId = appOrderService.savePublishSupplyGoods(pd);
			    pd.put("src_city_id",b_city_id );
			    pd.put("des_city_id",e_city_id);
			    Set<PageData> tempSet = new HashSet<PageData>();
			    tempSet.add(pd);
			    //极光推送
			    this.commonService.sendMessageByJpushAlisaByLine(tempSet);
			    resultMap.put("status", AppConstant.ERR_CODE_1000);
			    resultMap.put("sourceId", sourceId);
				resultMap.put("msg", "货源发布成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  resultMap;
	}
	
	/**
	 * 获取车型品牌(暂时不用)
	 * @return
	 */
	@RequestMapping("/getEautoMat")
	@ResponseBody
	public Map<String, Object> getEautoMat(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
//			pd.put("model_flag", 0);
			pd.put("level", SysDataCode.CAR_MODLE_TYPE_01);
			List<PageData> brandList = appCommonService.getEautoMat(pd);
//			pd.put("model_flag", 1);
			PageData modle = appCommonService.getEautoMatBybrandList(brandList);
			Map<String, Object> mp =  new HashMap<String,Object>();
			mp.put("brandList", brandList);
			mp.put("modle", modle);
			resultMap.put(AppConstant.RESULT, mp);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  AppUtil.returnMap(resultMap);
	}
	/**
	 * 获取品牌
	 * @return
	 */
	@RequestMapping("/getEautoMatBrandInfo")
	@ResponseBody
	public Map<String, Object> getEautoMatBrandInfo(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			pd.put("level", SysDataCode.CAR_MODLE_TYPE_01);
			List<PageData> brandList = appCommonService.getEautoMat(pd);
//			PageData modle = appCommonService.getEautoMatBybrandList(brandList);
			Map<String, Object> mp =  new HashMap<String,Object>();
			mp.put("brandList", brandList);
//			mp.put("modle", modle);
			resultMap.put(AppConstant.RESULT, brandList);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  AppUtil.returnMap(resultMap);
	}
	/**
	 * 获取车系
	 * @return
	 */
	@RequestMapping("/getEautoMatBrandByParentId")
	@ResponseBody
	public Map<String, Object> getEautoMatBrandByParentId(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String parent_id = pd.getString("parent_id");
			if (Tools.isEmpty(parent_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				List<PageData> modleList = appCommonService.getEautoMat(pd);
//			PageData modle = appCommonService.getEautoMatBybrandList(brandList);
//				Map<String, Object> mp =  new HashMap<String,Object>();
//				mp.put("modleList", modleList);
				resultMap.put(AppConstant.RESULT, modleList);
				
			}
//			pd.put("level", SysDataCode.CAR_MODLE_TYPE_01);
//			mp.put("modle", modle);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  AppUtil.returnMap(resultMap);
	}
	
	/**
	 * 获取货源详细信息(货源id)司机
	 * @return
	 */
	@RequestMapping("/getOrderDetailInfoByOrderId")
	@ResponseBody
	public Map<String, Object> getOrderDetailInfoByOrderId(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String order_id = pd.getString("order_id");//订单id
			if (Tools.isEmpty(order_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				PageData data =  new PageData();
				//货源明细
				PageData OrderDetail = appOrderService.getOrderDetailInfoByOrderId(pd);
				//报价明细
				List<PageData> listPrice =  appOrderService.getgetOrderDetailPriceInfoByOrderId(pd);
				data.put("OrderDetail", OrderDetail);
				data.put("listPrice", listPrice);
				resultMap.put(AppConstant.RESULT, data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * 获取货源详细信息(货源id)货主
	 * @return
	 */
	@RequestMapping("/getOrderDetailInfoByOrderIdNew")
	@ResponseBody
	public Map<String, Object> getOrderDetailInfoByOrderIdNew(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String order_id = pd.getString("order_id");//订单id
			if (Tools.isEmpty(order_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				PageData data =  new PageData();
				//货源明细
				PageData OrderDetail = appOrderService.getOrderDetailInfoByOrderId(pd);
				//报价明细
				List<PageData> listPrice =  appOrderService.getgetOrderDetailPriceInfoByOrderIdNew(pd);
				data.put("OrderDetail", OrderDetail);
				data.put("listPrice", listPrice);
				resultMap.put(AppConstant.RESULT, data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * 根据司机id查询车辆
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getEvehicleByDriverId")
	@ResponseBody
	public Map<String, Object> getEvehicleByDriverId(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String driver_id = pd.getString("driver_id");//司机id
			if (Tools.isEmpty(driver_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				PageData  data  =  new PageData();
				PageData evehicle = appOrderService.getEvehicleByDriverId(pd);//查询车辆
				pd.put("vehicle_id", evehicle.get("VEHICLE_ID"));
				//联系人
				List<PageData> userlist= appOrderService.getSysUserAndEvehicleByDriverId(pd);
				//线路
				List<PageData> linelist = appOrderService.getElineByVehicleId(pd);
				pd.put("use_type", SysDataCode.FILE_USE_TYPE_04);
				List<PageData>  evehiclePiclist = appOrderService.getEfilesByObjectId(pd);
				data.put("userlist", userlist);
				data.put("linelist", linelist);
				data.put("evehiclePiclist", evehiclePiclist);
				resultMap.put(AppConstant.RESULT, data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}
	/**
	 * 司机抢单报价
	 * @return
	 */
	@RequestMapping("/grabOrderAndSubPrice")
	@ResponseBody
	public Map<String, Object> grabOrderAndSubPrice(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String driver_id = pd.getString("driver_id");//司机id
			String order_id = pd.getString("order_id");//订单id
			String offer_price = pd.getString("offer_price");//报价价格
			String child_user_id = pd.getString("child_user_id");//子账号
			if (Tools.isEmpty(order_id)||Tools.isEmpty(driver_id)||Tools.isEmpty(offer_price)||Tools.isEmpty(child_user_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				pd.put("user_id", driver_id);
				PageData user = appOrderService.getSysUserByUserId(pd);
				//若用户未审核通过,不能进行报价
				if(!String.valueOf(SysDataCode.EXAMINE_STATUS_02).equals(String.valueOf(user.get("AUDIT_STATUS")))){
					resultMap.put("status", AppConstant.ERR_CODE_1017);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1017));
					return resultMap;
				}
				Map<String, Object> map = appOrderService.grabOrderAndSubPrice(pd);
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
	}
	
	/**
	 * 司机修改报价
	 * @return
	 */
	@RequestMapping("/updateOrderAndSubPrice")
	@ResponseBody
	public Map<String, Object> updateOrderAndSubPrice(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String driver_id = pd.getString("driver_id");//司机id
			String order_id = pd.getString("order_id");//订单id
			String offer_price = pd.getString("offer_price");//报价价格
			String price_id = pd.getString("price_id");//报价价格
			if (Tools.isEmpty(order_id)||Tools.isEmpty(driver_id)||Tools.isEmpty(offer_price)||Tools.isEmpty(price_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				pd.put("user_id", driver_id);
				PageData user = appOrderService.getSysUserByUserId(pd);
				//若用户未审核通过,不能进行报价
				if(!String.valueOf(SysDataCode.EXAMINE_STATUS_02).equals(String.valueOf(user.get("AUDIT_STATUS")))){
					resultMap.put("status", AppConstant.ERR_CODE_1017);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1017));
					return resultMap;
				}
				logger.info("修改报价参数："+pd);
				Map<String, Object> map = appOrderService.updateOrderAndSubPrice(pd);
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
	}
	
	/**
	 * 货主确认报价（货主）
	 * @return
	 */
	@RequestMapping("/ownerConfirmPrice")
	@ResponseBody
	public Map<String, Object> ownerConfirmPrice(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String order_id = pd.getString("order_id");//订单id
			String price_id = pd.getString("price_id");//价格id
			String user_id = pd.getString("user_id");//用户id
			if (Tools.isEmpty(order_id)||Tools.isEmpty(price_id)||Tools.isEmpty(user_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				 appOrderService.ownerConfirmPrice(pd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  resultMap;
	}
	/**
	 * 货主撤销货源
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cancelOwnerSupply")
	@ResponseBody
	public Map<String, Object> cancelOwnerSupply(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String order_id = pd.getString("order_id");//订单id
			String user_id = pd.getString("user_id");//用户id
			String revoke_remark = pd.getString("revoke_remark");//撤销原因
			if (Tools.isEmpty(order_id)||Tools.isEmpty(user_id)||Tools.isEmpty(revoke_remark)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				 appOrderService.cancelOwnerSupply(pd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  resultMap;
	}
	
	/**
	 *货主货源重发（2017-09版本）
	 */
	@RequestMapping("/againPublishGoods")
	@ResponseBody
	public Map<String,Object> againPublishGoods(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String order_id = pd.getString("order_id");//订单id
			String user_id = pd.getString("user_id");//用户id
			if (Tools.isEmpty(order_id)||Tools.isEmpty(user_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				 pd.put("status", SysDataCode.ORDER_STATUS_TYPE_03);
				 pd.put("is_repeat", SysDataCode.STATUS_TYPE_01);
				 //货源重发
				 appOrderService.againPublishGoods(pd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  resultMap;
	}
	
	/**
	 *货主货源重发（新版本）2017-10-30版本
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/againPublishGoodsNew")
	@ResponseBody
	public Map<String,Object> againPublishGoodsNew(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String order_id = pd.getString("order_id");//订单id
			String user_id = Tools.checkObj(pd.get("user_id")); //用户id
			String begin_date = pd.getString("begin_date");//期望交车时间
			String end_date = pd.getString("end_date");//期望运达时间
			String direct_price = pd.getString("direct_price");//期望价格
			String is_took = Tools.checkObj(pd.get("is_took"));  // 是否自行取车  10011002  否   10011001 是
			String is_take = Tools.checkObj(pd.get("is_take"));  // 是否上门收车	10011002  否   10011001 是
			String is_tax = Tools.checkObj(pd.get("is_tax"));  // 是否含税	10011002  否   10011001 是
			if (Tools.isEmpty(order_id) || Tools.isEmpty(user_id) 
					|| Tools.isEmpty(begin_date) || Tools.isEmpty(is_tax)
					|| Tools.isEmpty(end_date) || Tools.isEmpty(direct_price)
					|| Tools.isEmpty(is_took) || Tools.isEmpty(is_take)
				) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				 pd.put("status", SysDataCode.ORDER_STATUS_TYPE_03);
				 pd.put("is_repeat", SysDataCode.STATUS_TYPE_01);
				 //重发
				 appOrderService.againPublishGoodsNew(pd);
			}
			resultMap.put("status", AppConstant.ERR_CODE_1000);
			resultMap.put("msg","重发成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  resultMap;
	}
	
	/**
	 * 重发里面的撤销
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/againPublishGoodsRevoke")
	@ResponseBody
	public Map<String,Object> againPublishGoodsRevoke(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String sourceId = Tools.checkObj(pd.get("sourceId")); //货源ID
			if (Tools.isEmpty(sourceId)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				 this.appOrderService.againPublishGoodsRevoke(pd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  resultMap;
	}
	
	/**
	 * 司机端（我的抢单）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/myGrabSingle")
	@ResponseBody
	public Map<String,Object> myGrabSingle(HttpServletRequest request ,HttpServletResponse response,Page page) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			page.setPd(pd);
//			String order_id = pd.getString("order_id");//订单id
			String flag = pd.getString("flag");//状态
			String user_id = pd.getString("user_id");//用户id
			if (Tools.isEmpty(user_id)||Tools.isEmpty(flag)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
//				 pd.put("status", SysDataCode.ORDER_STATUS_TYPE_03);
//				 pd.put("is_repeat", SysDataCode.STATUS_TYPE_01);
				 List<PageData> list ;
				 if(AppConstant.SUCCESS_01.equals(flag)){
					 list =  appOrderService.getmyGrabSinglelistPage(page);
				 }else {
					 list =  appOrderService.getfailMyGrabSinglelistPage(page);
				} 
				 Map<String, Object> map = new HashMap<String, Object>();
				 map.put("page", page);
				 map.put("list", list);
				 resultMap.put(AppConstant.RESULT, map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  resultMap;
	}
	
	/***获取司机信息***/
	@RequestMapping("/getDriverDetailMsg")
	@ResponseBody
	public Map<String,Object> getDriverDetailMsg(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String driverId = pd.getString("driverId");//司机ID
			if (Tools.isEmpty(driverId)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				 //查询司机信息
				 PageData  returPd =  this.appOrderService.getDriverDetailMsg(pd);
				 resultMap.put(AppConstant.RESULT, returPd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}
	
	/***传入经纬度，获取最短的距离***/
	@RequestMapping("/getShortDistanceByLonAndLat")
	@ResponseBody
	public Map<String,Object> getShortDistanceByLonAndLat(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String lon = pd.getString("lon");//经度
			String lat = pd.getString("lat");//纬度
			if (Tools.isEmpty(lon) || Tools.isEmpty(lat)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				 PageData  returPd =  this.appOrderService.getShortDistanceByLonAndLat(pd);
				 resultMap.put(AppConstant.RESULT, returPd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}
	
	/***传入经纬度，获取最短的距离的列表***/
	@RequestMapping("/getDistanceByLonAndLatList")
	@ResponseBody
	public Map<String, Object> getDistanceByLonAndLatList(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String lon = pd.getString("lon");//经度
			String lat = pd.getString("lat");//纬度
			if (Tools.isEmpty(lon) || Tools.isEmpty(lat)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			} else {
				 List<PageData>  returPd =  this.appOrderService.getDistanceByLonAndLatList(pd);
				 resultMap.put(AppConstant.RESULT, returPd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * VTC未完成得列表
	 */
	@RequestMapping("/getVtcListByIdAndStatus")
	@ResponseBody
	public Map<String, Object> getVtcListByIdAndStatus(HttpServletRequest request ,HttpServletResponse response,Page page) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			    PageData pd = this.getPageData();
			    logger.info("参数："+pd);
			    String userId = Tools.checkObj(pd.get("userId")); // 用户ID
			    String vtcStatus  = Tools.checkObj(pd.get("vtcStatus")); // VTC状态类型:10381001: 取车  10381002 送车
			    if (Tools.isEmpty(userId) || Tools.isEmpty(vtcStatus)) {
			    	resultMap.put("status", AppConstant.ERR_CODE_1003);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
					return resultMap;
				}else {
					page.setPd(pd);
					List<PageData> list = appOrderService.getVtcListByIdAndStatuslistPage(page);
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("vtcList", list);
					map.put("page", page);
					resultMap.put(AppConstant.RESULT, map);
				}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  AppUtil.returnMap(resultMap);
	}
	
	/**
	 * VTC查看订单详情
	 * */
	@RequestMapping("/getVtcOrderDetail")
	@ResponseBody
	public Map<String, Object> getVtcOrderDetail(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			String orderId = Tools.checkObj(pd.get("orderId")); //订单ID
			String userId = Tools.checkObj(pd.get("userId")); //用户ID
			if(Tools.isEmpty(orderId) || Tools.isEmpty(userId)){
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
			}else {
				//查询订单详情
				Map<String,Object> orderMap = this.appOrderService.getVtcOrderDetail(pd);
				//查询接/送车的图片
				pd.put("fileDetail", SysDataCode.FILE_DETAIL_TYPE_11);
				List<PageData>  getPicList = this.appOrderService.getVtcListByIdPicList(pd);
				pd.put("fileDetail", SysDataCode.FILE_DETAIL_TYPE_12);
				List<PageData>  sendPicList = this.appOrderService.getVtcListByIdPicList(pd);
				resultMap.put("orderMap", orderMap);
				resultMap.put("getPicList", getPicList);
				resultMap.put("sendPicList", sendPicList);
			}
		} catch (Exception e) {
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}
	
	
	/**
	 *  短途司机从货主那里收件
	 */
	@RequestMapping("/vtcFromOwnerGetSomething")
	@ResponseBody
	public Map<String,Object> vtcFromOwnerGetSomething(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			String orderId =  Tools.checkObj(pd.get("orderId")); //订单id
			String userId = Tools.checkObj(pd.get("userId")); //用户id
			String fileId =  Tools.checkObj(pd.get("fileId")); //文件ID
			if (Tools.isEmpty(orderId) || Tools.isEmpty(userId) || Tools.isEmpty(fileId)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				//处理业务
				this.appOrderService.vtcFromOwnerGetSomething(pd);
			}
			resultMap.put("status", AppConstant.ERR_CODE_1000);
			resultMap.put("msg","取车成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  resultMap;
	}
	
	/**
	 *  短途司机交车给VTC
	 */
	@RequestMapping("/vtcFromDealerPutSome")
	@ResponseBody
	public Map<String,Object> vtcFromDealerPutSome(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			String orderId =  Tools.checkObj(pd.get("orderId")); //订单id
			String userId = Tools.checkObj(pd.get("userId")); //用户id
			if (Tools.isEmpty(orderId) || Tools.isEmpty(userId)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				//处理业务
				this.appOrderService.vtcFromDealerPutSome(pd);
			}
			resultMap.put("status", AppConstant.ERR_CODE_1000);
			resultMap.put("msg","交车成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  resultMap;
	}
	
	/**
	 *  短途司机从vtc取货
	 */
	@RequestMapping("/driverFromVtcGetSource")
	@ResponseBody
	public Map<String,Object> driverFromVtcGetSource(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			String orderId =  Tools.checkObj(pd.get("orderId")); //订单id
			String userId = Tools.checkObj(pd.get("userId")); //用户id
			if (Tools.isEmpty(orderId) || Tools.isEmpty(userId)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				//处理业务
				this.appOrderService.driverFromVtcGetSource(pd);
			}
			resultMap.put("status", AppConstant.ERR_CODE_1000);
			resultMap.put("msg","交车成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  resultMap;
	}
	
	/**
	 *  短途司机将货交给货主
	 */
	@RequestMapping("/driverToOwnerPutSource")
	@ResponseBody
	public Map<String,Object> driverToOwnerPutSource(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			String orderId =  Tools.checkObj(pd.get("orderId")); //订单id
			String userId = Tools.checkObj(pd.get("userId")); //用户id
			String fileId =  Tools.checkObj(pd.get("fileId")); //文件ID
			if (Tools.isEmpty(orderId) || Tools.isEmpty(userId) || Tools.isEmpty(fileId)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				//处理业务
				this.appOrderService.driverToOwnerPutSource(pd);
			}
			resultMap.put("status", AppConstant.ERR_CODE_1000);
			resultMap.put("msg","交车成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  resultMap;
	}
	
	/**
	 *  用户支付款接口(针对货主交定金，货主付尾款)
	 */
	@RequestMapping("/ownerPayMoney")
	@ResponseBody
	public Map<String,Object> ownerPayMoney(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info("参数："+pd);
			String orderId =  Tools.checkObj(pd.get("orderId")); //订单id
			String userId = Tools.checkObj(pd.get("userId")); //用户id
			String payNum = Tools.checkObj(pd.get("payNum")); //文件ID
			String payType =  Tools.checkObj(pd.get("payType")); //文件ID
			String payDetailType = Tools.checkObj(pd.get("payDetailType")); //文件ID
			if (Tools.isEmpty(orderId) || Tools.isEmpty(userId) 
					|| Tools.isEmpty(payNum) || Tools.isEmpty(payType)
					|| Tools.isEmpty(payDetailType)
				) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				// 首先查询当前用户的账户余额
				PageData my = this.appOrderService.getUserAcountNum(pd);
				pd.put("userName", my.get("NAME"));
				String userAmount = Tools.checkObj(my.get("AMOUNT"));
				//如果账户余额不足，则不能扣款
				if (Double.parseDouble(payNum) > Double.parseDouble(userAmount)) {
					resultMap.put("status", AppConstant.ERR_CODE_1001);
					resultMap.put("msg", "账户余额不足");
					return resultMap;
				} else {
					// 进行扣款
					this.appOrderService.payMoneyAndInRecord(pd);
				}
			}
			resultMap.put("status", AppConstant.ERR_CODE_1000);
			resultMap.put("msg","扣款成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return  resultMap;
	}
	
}
