package com.fh.controller.app.transport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.app.transport.AppTransportService;
import com.fh.util.AppConstant;
import com.fh.util.AppUtil;
import com.fh.util.PageData;
import com.fh.util.SysDataCode;
import com.fh.util.Tools;

/**
 * 2017年8月15日
 * 
 * @author quhy
 * 
 */
@Controller
@RequestMapping("/app/appTransport")
public class AppTransportController extends BaseController {
	@Resource(name = "appTransportService")
	private AppTransportService appTransportService;
	private Tools tools;

	

	/*************************************************** 路线相关 *************************************/


	/**
	 * 增加路线
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addRoute")
	@ResponseBody
	public Map<String, Object> addRoute(HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			Object userId = pd.get("userId");                             //必填    
			Object   srcRegionCode           = pd.get("srcRegionCode");  //必填  
			Object   desRegionCode           = pd.get("desRegionCode"); //必填  
			
		    if (userId != null && !userId.equals("") && srcRegionCode != null 
		    	&& !srcRegionCode.equals("") && desRegionCode != null && !desRegionCode.equals("")) {
				 appTransportService.addRoute(pd);
			}else {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",
					AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}

	/**
	 * 修改路线
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/editRoute")
	@ResponseBody
	public Map<String, Object> editRoute(HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			Object userId = pd.get("userId");                                   //必填                            
			Object carId = pd.get("carId");                                  //必填    
			Object   srcRegionCode           = pd.get("srcRegionCode");         //必填    
			Object   desRegionCode           = pd.get("desRegionCode");          //必填    
			 if (userId != null && !userId.equals("") && srcRegionCode != null 
				&& !srcRegionCode.equals("") && desRegionCode != null && !desRegionCode.equals("")
				&& carId != null && !carId.equals("")) {
		    	appTransportService.editRoute(pd);
			}else {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",
					AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}

	/**
	 * 删除路线
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteRoute")
	@ResponseBody
	public Map<String, Object> deleteRoute(HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			Object carId = pd.get("carId");                                 //必填    
		    if (carId != null && !carId.equals("")) {
		    	appTransportService.deleteRoute(pd);
			}else {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",
					AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}

	/**
	 * 启用/停止 路线
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/switchRoute")
	@ResponseBody
	public Map<String, Object> switchRoute(HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			Object userId = pd.get("userId");                                   //必填
			Object carId = pd.get("carId");                                     //必填
		    if (userId != null && !userId.equals("") && carId != null && !carId.equals("")   ) {
		    	appTransportService.switchRoute(pd);
			}else {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
			}
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",
					AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}

	/**
	 * 查询当前用户下面线路
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping("/selectLine")
	@ResponseBody
	public Map<String, Object> selectLine(HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();

		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			Object userId = pd.get("userId");                             //必填
		    if (userId != null && !userId.equals("")) {
		    	List<PageData> lineList = appTransportService.selectLine(pd);
				resultMap.put("lineList", lineList);
			}else {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",
					AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;

	}

	/************************************* 运力相关 ***********************************************/

	/**
	 * 运力中心 传入参数
	 * */
	// 参数 频次 起始地 目的地
	@RequestMapping("/selectRoute")
	@ResponseBody
	public Map<String, Object> selectRoute(HttpServletRequest request,
			HttpServletResponse response, Page page) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			Object   srcRegionCode           = pd.get("srcRegionCode");
			Object   desRegionCode           = pd.get("desRegionCode");
			String currentPage = pd.getString("currentPage");
			String currentResult = pd.getString("currentResult");
			Object carNum = pd.get("carNum");

			if (srcRegionCode != null && !srcRegionCode.equals("")) {
	            pd.put("srcRegionCode", srcRegionCode.toString());
	        }
		    if (desRegionCode != null && !desRegionCode.equals("")) {
	            pd.put("desRegionCode", desRegionCode.toString());
	        }
			if (carNum != null && !carNum.equals("")) {
				pd.put("carNum", Integer.parseInt(carNum.toString()));
			}
			pd.put("useType", SysDataCode.FILE_USE_TYPE_04);
			pd.put("useDetialType", SysDataCode.FILE_DETAIL_TYPE_05);
			pd.put("fileStatus", SysDataCode.ISVALID_YES);
			pd.put("status", SysDataCode.ISVALID_YES);
			page.setPd(pd);
			
			if(currentPage!=null && !currentPage.equals("")){
				page.setCurrentPage(Integer.parseInt(currentPage));	
			}
			if(currentResult!=null && !currentResult.equals("")){
				page.setCurrentResult(Integer.parseInt(currentResult));
			}
			List<PageData> routeList = appTransportService.selectRoute(page);
			resultMap.put("routeList", routeList);
			resultMap.put("page", page);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",
					AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}

		return resultMap;

	}

	/**
	 * 运力中心详情
	 * */
	// 参数 car_id
	@RequestMapping("/selectRouteDetail")
	@ResponseBody
	public Map<String, Object> selectRouteDetail(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			PageData pp = new PageData();
			Page page = new Page();
			List<List<PageData>> routeCommentHistoryPhoto = new ArrayList<>();
			List<PageData> routeCommentHistoryPhotoTemp = null;
			List<PageData> lineList = null; //线路
			PageData routeDetail = null;
			// 判断传入的值
			Object carId = pd.get("carId"); //两个参数必填其一
			Object userId = pd.get("userId");
			if ((userId != null && !userId.equals("")) ||  (carId != null && !carId.equals("") )) {
				if (userId != null && !userId.equals("")) {                           //如果用户id 不无空  就查出当前用户下的运力列表   
					lineList = appTransportService.selectLine(pd);
				}
				if (lineList != null && lineList.size() > 0) {                     //如果查出来的运力列表不为空  则取一条运力id 
					pd.put("carId", lineList.get(0).get("CAR_ID"));
					pd.put("userId", null);
				}
				if (carId != null && !carId.equals("")) {                         //如果carId 不为空 则将carId 存进去
					pd.put("carId", Long.valueOf(carId.toString()));
					pd.put("userId", null);
				}
				pd.put("useType", SysDataCode.FILE_USE_TYPE_06);
				pd.put("useDetialType", SysDataCode.FILE_DETAIL_TYPE_01);
				pd.put("fileStatus", SysDataCode.ISVALID_YES);
				page.setPd(pd);
				page.setCurrentPage(1);
				page.setCurrentResult(3);
				//如果通过userId查不到运力列表  则用userId来查
				if((lineList != null && lineList.size() > 0 ) || (carId != null && !carId.equals(""))){      
					 routeDetail = appTransportService.selectRouteDetail(pd); // 运力详情
					logger.info(routeDetail);	
				}else{
					pd.put("userId", Long.valueOf(userId.toString()));
					pd.put("carId",null);
					 routeDetail = appTransportService.selectRouteDetailByUserId(pd); // 运力详情
					logger.info(routeDetail);
				}
				List<PageData> routeDriverDetail = appTransportService.selectDriverDetail(pd);// 运力详情司机信息列表
				List<PageData> routeLineDetail = appTransportService.selectLineDetail(pd); // 运力详情优势路线
				List<PageData> routeCarDetail = appTransportService.selectCarDetail(pd); // 运力详情车辆照片
				PageData routeComment = appTransportService.selectRouteComment(pd); // 运力详情综合评价
				List<PageData> routeCommentDetail = appTransportService.selectRouteCommentDetail(pd);// 运力详情各项评价
				PageData routeCommentTotal = appTransportService.selectRouteCommentTotal(pd);// 运力评价总数
				resultMap.put("routeDetail", routeDetail);
				resultMap.put("routeDriverDetail", routeDriverDetail);
				resultMap.put("routeLineDetail", routeLineDetail);
				resultMap.put("routeCarDetail", routeCarDetail);
				resultMap.put("routeComment", routeComment);
				resultMap.put("routeCommentDetail", routeCommentDetail);
				resultMap.put("routeCommentTotal", routeCommentTotal);
				List<PageData> routeCommentHistory = appTransportService.selectRouteCommentHistory(page);// 运力历史评价
				// 循环得出历史评论的图片
				pp.put("useDetialType", SysDataCode.FILE_DETAIL_TYPE_09);
				pp.put("useType", SysDataCode.FILE_USE_TYPE_08);
				for (int i = 0; i < routeCommentHistory.size(); i++) {
					Long orderId = (Long) routeCommentHistory.get(i).get("ORDER_ID");
					pp.put("orderId", orderId);
					routeCommentHistoryPhotoTemp = appTransportService
							.selectRouteCommentHistoryPhoto(pp);// 运力历史评价图片
					routeCommentHistoryPhoto.add(routeCommentHistoryPhotoTemp);
				}
				resultMap.put("routeCommentHistoryPhoto",routeCommentHistoryPhoto);
				resultMap.put("routeCommentHistory", routeCommentHistory);
				
			}else{
		    	resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",
					AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}

	/**
	 * 运力中心详情历史评价
	 * */
	// 参数 car_id
	@RequestMapping("/selectRouteCommentHistory")
	@ResponseBody
	public Map<String, Object> selectRouteCommentHistory(
			HttpServletRequest request, HttpServletResponse response, Page page) {

		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {

			PageData pd = this.getPageData();
			String currentPage = pd.getString("currentPage");
			String currentResult = pd.getString("currentResult");
			logger.info(pd);
			PageData pp = new PageData();
			List<List<PageData>> routeCommentHistoryPhoto = new ArrayList<>();
			List<PageData> routeCommentHistoryPhotoTemp = null;

			Object carId = pd.get("carId");
			if (carId != null && !carId.equals("")) {
				pd.put("carId", Long.valueOf(carId.toString()));
			}
			pd.put("useType", SysDataCode.FILE_USE_TYPE_06);
			pd.put("useDetialType", SysDataCode.FILE_DETAIL_TYPE_01);
			pd.put("fileStatus", SysDataCode.ISVALID_YES);
			
			page.setPd(pd);

			if(currentPage!=null && !currentPage.equals("")){
				page.setCurrentPage(Integer.parseInt(currentPage));	
			}
			if(currentResult!=null && !currentResult.equals("")){
				page.setCurrentResult(Integer.parseInt(currentResult));
			}
			
			List<PageData> routeCommentHistory = appTransportService
					.selectRouteCommentHistory(page);// 运力历史评价

			pp.put("useDetialType", SysDataCode.FILE_DETAIL_TYPE_09);
			pp.put("useType", SysDataCode.FILE_USE_TYPE_08);
			for (int i = 0; i < routeCommentHistory.size(); i++) {
				Long orderId = (Long) routeCommentHistory.get(i)
						.get("ORDER_ID");
				pp.put("orderId", orderId);
				routeCommentHistoryPhotoTemp = appTransportService
						.selectRouteCommentHistoryPhoto(pp);// 运力历史评价图片
				routeCommentHistoryPhoto.add(routeCommentHistoryPhotoTemp);
			}

			resultMap.put("routeCommentHistoryPhoto", routeCommentHistoryPhoto);
			resultMap.put("routeCommentHistory", routeCommentHistory);
			resultMap.put("page", page);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",
					AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));

			return resultMap;
		}
		return resultMap;

	}


	/********************************************* 个人中心 ***************************************************************/

	/**
	 * 个人中心初始化
	 * 
	 * @param request
	 * @return
	 */
	// 参数：user_id 传出
	@RequestMapping("/initCenter")
	@ResponseBody
	public Map<String, Object> initCenter(HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			Object   userId           = pd.get("userId");
	    	if (userId!=null && !userId.equals("")) {
	    		PageData center = appTransportService.initCenter(pd);
				resultMap.put("center", center); 
	        }else {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", "1005");
			resultMap.put("msg", AppUtil.getMessageInfoByKey("1005"));
			return resultMap;
		}
		return AppUtil.returnMap(resultMap);

	}

	/********************************************* 订单相关 **************************************************************/

	/**
	 * 订单列表
	 * */
	@SuppressWarnings("static-access")
	@RequestMapping("/selectOrderList")
	@ResponseBody
	public Map<String, Object> selectOrderList(HttpServletRequest request,
			HttpServletResponse response, Page page) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();

		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			Object userId = pd.get("userId");
			Object bCityName = pd.get("bCityName");
			Object eCityName = pd.get("eCityName");
			Object modleId = pd.get("modleId");
			Object autoId = pd.get("autoId");
			Object vin = pd.get("vin");
			String status = pd.getString("status");
			Object orderId = pd.get("orderId");
			Object ownerPhone = pd.get("ownerPhone");
			Object beginDateFrom = pd.get("beginDateFrom");
			Object beginDateTo = pd.get("beginDateTo");
			Object endDateFrom = pd.get("endDateFrom");
			Object endDateTo = pd.get("endDateTo");
			Object driverPhone = pd.get("driverPhone");
			Object driverId = pd.get("driverId");
			
			
			if(userId == null && driverId == null  && status == null){
				
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				
			}else {
				
				if (userId != null && !userId.equals("")) {
					pd.put("userId", Long.valueOf(userId.toString()));
				}
				if (orderId != null && !orderId.equals("")) {
					pd.put("orderId", Long.valueOf(orderId.toString()));
				}
				if (driverId != null && !driverId.equals("")) {
					pd.put("driverId", Long.valueOf(driverId.toString()));
				}

				if (beginDateFrom != null) {
					pd.put("beginDateFrom",tools.str3Date(beginDateFrom.toString()));
				}
				if (beginDateTo != null) {
					pd.put("beginDateTo", tools.str3Date(beginDateTo.toString()));
				}
				if (endDateFrom != null) {
					pd.put("endDateFrom", tools.str3Date(endDateFrom.toString()));
				}
				if (endDateTo != null) {
					pd.put("endDateTo", tools.str3Date(endDateTo.toString()));
				}
				if (bCityName != null && !bCityName.equals("")) {
					pd.put("bCityName", Long.valueOf(bCityName.toString()));
				}
				if (eCityName != null && !eCityName.equals("")) {
					pd.put("eCityName", Long.valueOf(eCityName.toString()));
				}

				if (modleId != null && !modleId.equals("")) {
					pd.put("modleId", Long.valueOf(modleId.toString()));
				}
				if (autoId != null && !autoId.equals("")) {
					pd.put("autoId", Long.valueOf(autoId.toString()));
				}
				String[] statusList = tools.str2StrArray(status);
				if (statusList != null && statusList.length > 0) {
					List<Long> status1 = new ArrayList<Long>();
					for (int i = 0; i < statusList.length; i++) {
						if (statusList[i] != null && !statusList[i].equals("")) {
							status1.add(Long.valueOf(statusList[i]));
						}
					}
					pd.put("statusList", status1);

				}
				if (vin != null) {
					pd.put("vin", vin.toString());
				}

				if (ownerPhone != null && !ownerPhone.equals("")) {
					pd.put("ownerPhone", ownerPhone.toString());
				}

				if (driverPhone != null && !driverPhone.equals("")) {
					pd.put("driverPhone", driverPhone.toString());
				}
				pd.put("orderFlag", SysDataCode.STATUS_TYPE_01);
				pd.remove("status");
				page.setPd(pd);

				List<PageData> orderList = appTransportService
						.selectOrderList(page);
				resultMap.put("orderList", orderList);
				resultMap.put("page", page);
				
				
				
				
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",
					AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}

		return resultMap;

	}

	/**
	 * 订单详情
	 * */
	@RequestMapping("/selectOrderListDetail")
	@ResponseBody
	public Map<String, Object> selectOrderListDetail(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			Object orderId = pd.get("orderId");
			if(orderId != null && !orderId.equals("")){
				//获取订单详情
				Map<String,Object> orderListDetail = appTransportService.selectOrderListDetail(pd);
				List<PageData> orderContrail = appTransportService.selectOrderContrail(pd);
				List<PageData> orderDriver = appTransportService.selectOrderDriver(pd);
				pd.put("useType", SysDataCode.FILE_USE_TYPE_08);
				pd.put("useDetialType", SysDataCode.FILE_DETAIL_TYPE_07);
				List<PageData> orderPhotoGet = appTransportService.selectOrderPhoto(pd);
				pd.put("useType", SysDataCode.FILE_USE_TYPE_08);
				pd.put("useDetialType", SysDataCode.FILE_DETAIL_TYPE_08);
				List<PageData> orderPhotoSubmit = appTransportService.selectOrderPhoto(pd);
				
				//评论相关数据
				pd.put("useType", SysDataCode.FILE_USE_TYPE_06);
				pd.put("useDetialType", SysDataCode.FILE_DETAIL_TYPE_01);
				pd.put("fileStatus", SysDataCode.ISVALID_YES);
				PageData orderComment = appTransportService.selectOrderComment(pd);
				List<PageData> orderCommentPhoto = appTransportService.selectOrderCommentPhoto(pd);
				PageData orderLocation = appTransportService.selectOrderLocation(pd);
				List<PageData> orderCommentDetail = appTransportService.selectOrderCommentDetail(pd);
				resultMap.put("orderDetail", orderListDetail);
				resultMap.put("orderContrail", orderContrail);
				resultMap.put("orderDriver", orderDriver);
				resultMap.put("orderPhotoGet", orderPhotoGet);
				resultMap.put("orderPhotoSubmit", orderPhotoSubmit);
				resultMap.put("orderComment", orderComment);
				resultMap.put("orderCommentPhoto", orderCommentPhoto);
				resultMap.put("orderLocation", orderLocation);
				resultMap.put("orderCommentDetail", orderCommentDetail);
			}else {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
			}
		} catch (Exception e) {
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}

	/************************************************ 车主撤单 收车 确认到达 交车 车主确认交车 评价 ****************************************************************************/

	/**
	 * 车主撤单
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping("/cancelOrder")
	@ResponseBody
	public Map<String, Object> cancelOrder(HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			Object orderId = pd.get("orderId");
			Object userId = pd.get("userId");
			String remark = pd.getString("remark");
			
			if(orderId != null && !orderId.equals("") && userId != null && !userId.equals("") && remark !=null && !remark.equals("")){
				appTransportService.cancelOrder(pd);
			}else {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",
					AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}

	/**
	 * 司机上传图片收交车
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping("/getOrder")
	@ResponseBody
	public Map<String, Object> getOrder(HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			Object orderId = pd.get("orderId");
			Object userId = pd.get("userId");
			String flag = pd.getString("flagId");
			if(orderId != null && !orderId.equals("") && userId != null && !userId.equals("") && flag != null && !flag.equals("") ){
				if (flag.equals("1")) {
					// 上传图片收车
					appTransportService.getOrder(pd);
				} else if (flag.equals("2")) {
					// 上传图片交车
					appTransportService.submitOrder(pd);
				}
			}else {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",
					AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}

	/**
	 * 司机确认达到
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping("/arriveOrder")
	@ResponseBody
	public Map<String, Object> arriveOrder(HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			Object orderId = pd.get("orderId");
			Object userId = pd.get("userId");
			if(orderId != null && !orderId.equals("") && userId != null && !userId.equals("") ){
				//确认到达
				appTransportService.arriveOrder(pd);
			}else {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}

	/**
	 * 货主确认交车
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping("/confirmOrder")
	@ResponseBody
	public Map<String, Object> confirmOrder(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			Object orderId = pd.get("orderId");
			Object userId = pd.get("userId");
			if(orderId != null && !orderId.equals("") && userId != null && !userId.equals("") ){
				//确认交车
				appTransportService.confirmOrder(pd);
			}else {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}

	/**
	 * 增加评价
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addComment")
	@ResponseBody
	public Map<String, Object> addComment(HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			Object orderId = pd.get("orderId");
			Object userId = pd.get("userId");
			if(orderId != null && !orderId.equals("") && userId != null && !userId.equals("") ){
			appTransportService.addComment(pd);
			}else {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",
					AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}

	/*************************************************************** 消息钱包 *******************************************************************************/

	/**
	 * 消息列表
	 * */
	@RequestMapping("/selectMsg")
	@ResponseBody
	public Map<String, Object> selectMsg(HttpServletRequest request,
			HttpServletResponse response, Page page) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			Object userId = pd.get("userId");
			if (userId != null && !userId.equals("")) {
				pd.put("userId", Long.valueOf(userId.toString()));
				page.setPd(pd);
				List<PageData> msgList = appTransportService.selectMsg(page);
				resultMap.put("msgList", msgList);
				resultMap.put("page", page);
			}else {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",
					AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}

	/**
	 * 钱包列表
	 * */
	@RequestMapping("/selectMoney")
	@ResponseBody
	public Map<String, Object> selectMoney(HttpServletRequest request,HttpServletResponse response, Page page) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info(pd);
			Object userId = pd.get("userId");
			if (userId != null && !userId.equals("")) {
				pd.put("userId", Long.valueOf(userId.toString()));
				page.setPd(pd);
				List<PageData> moneyList = appTransportService.selectMoney(page);
				PageData moneyTotal = appTransportService.selectMoneyTotal(pd);
				resultMap.put("moneyList", moneyList);
				resultMap.put("moneyTotal", moneyTotal);
				resultMap.put("page", page);
			}else {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",
					AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * 意见反馈接口
	 * */
	@RequestMapping("/feedBackSave")
	@ResponseBody
	public Map<String, Object> feedBackSave(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info("feedBackSave="+pd.toString());
			if (null == pd || "null".equals(pd) || pd.size() == 0) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}
			this.appTransportService.feedBackSave(pd);
			resultMap.put("status", AppConstant.ERR_CODE_1000);
			resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1000));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * 广告列表
	 * */
	@RequestMapping("/getAdvertList")
	@ResponseBody
	public List<Map<String, Object>> getAdvertList(HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return this.appTransportService.getAdvertList();
	}
	
	/**
	 * 广告详情
	 * */
	@RequestMapping("/getAdvertDetail")
	@ResponseBody
	public Map<String, Object> getAdvertDetail(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			logger.info("getAdvertDetail="+pd.toString());
			if (null == pd || "null".equals(pd) || pd.size() == 0) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}
			Map<String, Object> advertMap = this.appTransportService.getAdvertDetail(pd);
			resultMap.put("status", AppConstant.ERR_CODE_1000);
			resultMap.put("msg",advertMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg",AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}
	
	/****
	 * 读消息
	 * */
	@RequestMapping("/readMsgByMsgId")
	public void readMsgByMsgId(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		PageData pd = this.getPageData();
		logger.info("readMsgByMsgId="+pd.toString());
		String rangFlag = Tools.checkObj(pd.get("rangFlag"));
		if (!Tools.isEmpty(rangFlag)) {
			this.appTransportService.readMsgByMsgId(pd);
		}
	}
	
	/****
	 * 消息中心读取
	 * */
	@RequestMapping("/readAllMsgByMsgId")
	public void readAllMsgByMsgId(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		PageData pd = this.getPageData();
		logger.info("readMsgByMsgId="+pd.toString());
		this.appTransportService.readAllMsg(pd);
	}
	
}
