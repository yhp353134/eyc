package com.fh.controller.system.dealer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.common.CommonService;
import com.fh.service.system.dealer.SourceGoodsService;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.PageData;
import com.fh.util.PrimaryUtil;
import com.fh.util.SmsUtil;
import com.fh.util.SysDataCode;
import com.fh.util.Tools;

/***
 * 货源和订单
 * */
@Controller
@RequestMapping(value = "/sourceGoods")
public class SourceGoodsController extends BaseController {

	/**service* */
	@Resource(name = "sourceGoodsService")
	private SourceGoodsService sourceGoodsService;
	@Resource(name = "commonService")
	private CommonService commonService;

	/***货源列表 * */
	@RequestMapping(value = "/sourceList")
	public ModelAndView userList(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			String loginName = pd.getString("loginName");
			String chineName = pd.getString("chineName");
			if (!Tools.isEmpty(loginName)) {
				pd.put("loginName", loginName);
			}
			if (!Tools.isEmpty(chineName)) {
				pd.put("chineName", chineName);
			}
			pd.put("dealerId", this.getDealerId());
			pd.put("orgType", this.getOrgType());
			pd.put("useType", this.getUser().getUSER_TYPE());
			page.setPd(pd);
			List<PageData> userList = this.sourceGoodsService.getSourcelistPage(page);
			PageData matPd = new PageData();
			matPd.put("matLevel", SysDataCode.CAR_MODLE_TYPE_01);
			List<PageData> matList = this.commonService.getAutoMatList(matPd);//车型
			List<PageData> provinceList = this.commonService.getProvinceList(); // 省份
			mv.addObject("userList", userList);
			mv.addObject("matList", matList);
			mv.addObject("provinceList", provinceList);
			mv.addObject("useType", this.getUser().getUSER_TYPE());
			mv.addObject("pd", pd);
			mv.addObject("userId", this.getUser().getUSER_ID());
			mv.addObject(Const.SESSION_QX, this.getPermission());
			mv.setViewName("system/dealer/source_list");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	/***跳转到货源确认报价页面 * */
	@RequestMapping(value = "/goToSourcePriceConfirm")
	public ModelAndView goToSourcePriceConfirm() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			String sourceId = pd.getString("sourceId");
			if (Tools.isEmpty(sourceId)) {
				return mv;
			} else {
				pd.put("sourceId",sourceId);
			}
			List<PageData> priceList = this.sourceGoodsService.getSourceConfirmList(pd);
			mv.addObject("pd", pd);
			mv.addObject("priceList", priceList);
			mv.setViewName("system/dealer/source_price_confirm");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	/***跳转到货源撤销页面 * */
	@RequestMapping(value = "/goToSourcePriceRevoke")
	public ModelAndView goToSourcePriceRevoke() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.addObject("pd", pd);
			mv.setViewName("system/dealer/source_revoke");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	/***跳转到订单撤销页面 * */
	@RequestMapping(value = "/goToOrderPriceRevoke")
	public ModelAndView goToOrderPriceRevoke() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.addObject("pd", pd);
			mv.setViewName("system/dealer/order_revoke");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	/***货主撤销货源或者订单 @throws Exception */
	@RequestMapping(value = "/revokeDataByIdAndType")
    public ModelAndView revokeDataByIdAndType() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        pd.put("userId", this.getUser().getUSER_ID());
        this.sourceGoodsService.revokeDataByIdAndType(pd);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }
	
	/***货主确认报价 * 
	 * @throws Exception */
	@RequestMapping(value = "/confirmPriceByOwner")
    public ModelAndView confirmPriceByOwner() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        pd.put("userChinese", getUser().getNAME());
        pd.put("userId", getUser().getUSER_ID());
        pd.put("dealerId", this.getDealerId());
        int tmp = this.sourceGoodsService.confirmPriceByOwner(pd);
        if (0 == tmp) {
        	 return mv;
        }
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }
	
	/***货源详情 * */
	@RequestMapping(value = "/sourceDetail")
	public ModelAndView sourceDetail() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			String sourceId = pd.getString("sourceId");
			if (Tools.isEmpty(sourceId)) {
				return mv;
			} else {
				pd.put("sourceId",sourceId);
			}
			if ("10120002".equals(this.getOrgType())) {
				pd.put("dealerId", this.getDealerId());
			}
			PageData sourcePd = this.sourceGoodsService.getSourceDetailMsg(pd);
			Object modelId = sourcePd.get("MODLE_ID"); //车型ID
			PageData carPd = null;
			if (null != modelId) {
				//根据ID查询品牌
				PageData ppp = new PageData();
				ppp.put("modelId", modelId);
				carPd = this.sourceGoodsService.getBrandByModelId(ppp);
			}
			//当用户是平台用户，同时用户类型是经销商=承运商
			if ("10120002".equals(this.getOrgType()) && "10131001".equals(String.valueOf(this.getUser().getUSER_TYPE()))) {
				//查询通知
				List<PageData> noticeList = this.sourceGoodsService.getNoticePriceList(pd);
				mv.addObject("noticeList", noticeList);
			}
			//查询报价列表
			List<PageData> offerList = this.sourceGoodsService.getOfferPriceList(pd);
			mv.addObject("sourcePd", sourcePd);
			mv.addObject("carPd", carPd);
			mv.addObject("offerList", offerList);
			mv.setViewName("system/dealer/source_detail");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	/***失败货源详情 * */
	@RequestMapping(value = "/sourceDetailFailer")
	public ModelAndView sourceDetailFailer() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			String sourceId = pd.getString("sourceId");
			if (Tools.isEmpty(sourceId)) {
				return mv;
			} else {
				pd.put("sourceId",sourceId);
			}
			if ("10120002".equals(this.getOrgType())) {
				pd.put("dealerId", this.getDealerId());
			}
			PageData sourcePd = this.sourceGoodsService.getSourceDetailMsg(pd);
			Object modelId = sourcePd.get("MODLE_ID"); //车型ID
			PageData carPd = null;
			if (null != modelId) {
				//根据ID查询品牌
				PageData ppp = new PageData();
				ppp.put("modelId", modelId);
				carPd = this.sourceGoodsService.getBrandByModelId(ppp);
			}
			//当用户是平台用户，同时用户类型是经销商=承运商
			if ("10120002".equals(this.getOrgType()) && "10131001".equals(String.valueOf(this.getUser().getUSER_TYPE()))) {
				//查询通知
				List<PageData> noticeList = this.sourceGoodsService.getNoticePriceList(pd);
				mv.addObject("noticeList", noticeList);
			}
			//查询报价列表
			List<PageData> offerList = this.sourceGoodsService.getOfferPriceList(pd);
			mv.addObject("sourcePd", sourcePd);
			mv.addObject("carPd", carPd);
			mv.addObject("offerList", offerList);
			mv.setViewName("system/dealer/source_deatil_failer");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	/***订单列表 * */
	@RequestMapping(value = "/orderList")
	public ModelAndView orderList(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			String orderId = pd.getString("orderId");  //订单号
			String orderVin = pd.getString("orderVin"); // VIN
			if (!Tools.isEmpty(orderId)) {
				pd.put("orderId", orderId);
			}
			if (!Tools.isEmpty(orderVin)) {
				pd.put("orderVin", orderVin);
			}
			pd.put("dealerId", this.getDealerId());
			pd.put("orgType", this.getOrgType());
			pd.put("useType", this.getUser().getUSER_TYPE());
			page.setPd(pd);
			List<PageData> orderList = this.sourceGoodsService.getOrderlistPage(page);
			PageData matPd = new PageData();
			matPd.put("matLevel", SysDataCode.CAR_MODLE_TYPE_01);
			List<PageData> matList = this.commonService.getAutoMatList(matPd);//车型
			List<PageData> provinceList = this.commonService.getProvinceList(); // 省份
			mv.addObject("orderList", orderList);
			mv.addObject("matList", matList);
			mv.addObject("provinceList", provinceList);
			mv.addObject("useType", this.getUser().getUSER_TYPE());
			mv.addObject("pd", pd);
			mv.addObject("userId", this.getUser().getUSER_ID());
			mv.setViewName("system/dealer/order_list");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	/***订单详情 * */
	@RequestMapping(value = "/orderDetail")
	public ModelAndView orderDetail() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			String orderId = pd.getString("orderId");
			if (Tools.isEmpty(orderId)) {
				return mv;
			} else {
				pd.put("sourceId",orderId);
			}
			PageData sourcePd = this.sourceGoodsService.getSourceDetailMsg(pd);
			Object modelId = sourcePd.get("MODLE_ID"); //车型ID
			PageData carPd = null;
			if (null != modelId) {
				//根据ID查询品牌
				PageData ppp = new PageData();
				ppp.put("modelId", modelId);
				carPd = this.sourceGoodsService.getBrandByModelId(ppp);
			}
			//根据orderId查询订单轨迹
			List<PageData> orderGuiList = this.sourceGoodsService.getorderGuiList(pd);
			//获取评价记录
			List<PageData> commentList = this.sourceGoodsService.getorderCommentList(pd);
			//查询图片列表
			List<PageData> picList =this.sourceGoodsService.getOrderPicList(pd);
			//根据sourceId查询评论内容
			PageData dp = this.sourceGoodsService.getCommentBySourceId(pd);
			mv.addObject("sourcePd", sourcePd);
			mv.addObject("carPd", carPd);
			mv.addObject("orderGuiList", orderGuiList);
			mv.addObject("commentList", commentList);
			mv.addObject("picList", picList);
			mv.addObject("dp", dp);
			mv.setViewName("system/dealer/order_detail");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	/***订单交车页面* */
	@RequestMapping(value = "/orderDealCar")
	public ModelAndView orderDealCar() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			String orderId = pd.getString("orderId");
			if (Tools.isEmpty(orderId)) {
				return mv;
			} else {
				pd.put("sourceId",orderId);
			}
			PageData sourcePd = this.sourceGoodsService.getSourceDetailMsg(pd);
			Object modelId = sourcePd.get("MODLE_ID"); //车型ID
			PageData carPd = null;
			if (null != modelId) {
				//根据ID查询品牌
				PageData ppp = new PageData();
				ppp.put("modelId", modelId);
				carPd = this.sourceGoodsService.getBrandByModelId(ppp);
			}
			//根据orderId查询订单轨迹
			List<PageData> orderGuiList = this.sourceGoodsService.getorderGuiList(pd);
			//查询图片列表
			List<PageData> picList =this.sourceGoodsService.getOrderPicList(pd);
			mv.addObject("sourcePd", sourcePd);
			mv.addObject("carPd", carPd);
			mv.addObject("orderGuiList", orderGuiList);
			mv.addObject("picList", picList);
			mv.setViewName("system/dealer/order_deal_car");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	 /** * 订单确认交车  @throws Exception */
    @RequestMapping(value = "/orderDealCarConfirm")
    public ModelAndView orderDealCarConfirm() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        pd.put("orderStatus", SysDataCode.ORDER_STATUS_TYPE_09);
        pd.put("userId", this.getUser().getUSER_ID());
        pd.put("userName", this.getUser().getUSER_NAME());
        //修改订单的状态
        this.sourceGoodsService.updateOrderStatus(pd);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }
	
	/***获取车型通过品牌**/
	@RequestMapping(value = "/getMatListByMatId")
	@ResponseBody
	public List<PageData> getMatListByMatId() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData matPd = new PageData();
		matPd.put("matLevel", SysDataCode.CAR_MODLE_TYPE_02);
		matPd.put("matParentId", pd.get("matId"));
		return this.commonService.getAutoMatList(matPd);
	}
	
	/***根据省份ID获取城市**/
	@RequestMapping(value = "/getCityByProvinceId")
	@ResponseBody
	public List<PageData> getCityByProvinceId() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData matPd = new PageData();
		matPd.put("provinceId", pd.get("provinceId"));
		return this.commonService.getCityByProovinceId(matPd);
	}

	/***导入货源* */
	@RequestMapping(value = "/importSourceExcel")
	public ModelAndView importSourceExcel(Page page,@RequestParam MultipartFile fileName) throws Exception {
		ModelAndView mv = this.getModelAndView();
		List<Map<String,Object>> returnList= new ArrayList<Map<String,Object>>(); // 返回错误信息
		List<Map<String,Object>> paramList= new ArrayList<Map<String,Object>>(); // 正确值
		Set<PageData> loadSet = new HashSet<PageData>(); // 存放线路集合
		Map<String,Object> returnMap = null;
		Map<String,Object> paramMap = null;
		PageData loadMap = null;
		//检查基本的文件
		String fileMsg = checkFile(fileName);
		if (!Tools.isEmpty(fileMsg)) {
			returnMap = new HashMap<String, Object>();
			returnMap.put("errorNum", "0");
			returnMap.put("errorMsg", fileMsg);
			returnList.add(returnMap);
		}
		//获得Workbook工作薄对象  
	    Workbook workbook = getWorkBook(fileName);  
	    if(workbook != null){  
	    	//获得当前sheet工作表  
	        Sheet sheet = workbook.getSheetAt(0);  
	        int totlNum =  sheet.getPhysicalNumberOfRows();
	        //获得当前sheet的结束行  
	        int lastRowNum = sheet.getLastRowNum();
	        returnMap = new HashMap<String, Object>();
	        if (300<lastRowNum) {
	        	returnMap.put("errorNum", "0");
				returnMap.put("errorMsg", "总行数不要大于300行");
				returnList.add(returnMap);
				totlNum = 1;
	        }
	        //循环除了第一行的所有行  
	        for(int rowNum =1;rowNum < totlNum;rowNum++){  
	        	//一条参数信息
	        	paramMap =  new HashMap<String, Object>();
	        	paramMap.put("sourceId", PrimaryUtil.getPrimary()); //货源ID
	        	paramMap.put("sourceStatus", SysDataCode.ORDER_STATUS_TYPE_00); //货源状态
	        	paramMap.put("sourceFlag", SysDataCode.STATUS_TYPE_02); //货源标识
	            //获得当前行  
	            Row row = sheet.getRow(rowNum);  
	            if(row == null){  
	                continue;  
	            }  
	            String ownerPhone =getCellValue(row.getCell(0)); // 货主电话
	            if (Tools.isEmpty(ownerPhone)) {
	            	returnMap = new HashMap<String, Object>();
	            	returnMap.put("errorNum", rowNum);
	    			returnMap.put("errorMsg", "货主电话不能为空");
	    			returnList.add(returnMap);
	    			continue;
	            } else {
	            	//查询货主信息
	            	PageData ownerPd = this.sourceGoodsService.getSourceOwnerMsg(ownerPhone.trim());
	            	if (null==ownerPd || ownerPd.size()==0) {
	            		returnMap = new HashMap<String, Object>();
	                	returnMap.put("errorNum", rowNum);
	        			returnMap.put("errorMsg", "货主账号在系统中不存在");
	        			returnList.add(returnMap);
	        			continue;
	            	} else {
	            		paramMap.put("ownerId", ownerPd.get("USER_ID"));
	            		paramMap.put("ownerTel", ownerPd.get("TEL"));
	            		paramMap.put("ownerName", ownerPd.get("NAME"));
	            		paramMap.put("ownerDealerId", ownerPd.get("DEALER_ID"));
	            	}
	            }
	            String beginWord =getCellValue(row.getCell(1)); // 起始城市
	            Object loadBegin = null;
	            if (Tools.isEmpty(beginWord)) {
	            	returnMap = new HashMap<String, Object>();
	            	returnMap.put("errorNum", rowNum);
	    			returnMap.put("errorMsg", "起始城市不能为空");
	    			returnList.add(returnMap);
	    			continue;
	            } else {
	            	PageData beginPd = this.sourceGoodsService.getCityMsgByName(beginWord.trim());
	            	if (null == beginPd || 0 == beginPd.size()) {
	            		returnMap = new HashMap<String, Object>();
	                	returnMap.put("errorNum", rowNum);
	        			returnMap.put("errorMsg", "起始城市在系统不存在");
	        			returnList.add(returnMap);
	        			continue;
	            	} else {
	            		paramMap.put("beginWordName", beginWord);
	            		paramMap.put("beginWordCode", beginPd.get("REGION_CODE"));
	            		loadBegin = beginPd.get("REGION_CODE");
	            	}
	            }
	            String endWord =getCellValue(row.getCell(2)); // 到达城市
	            Object endLoad = null;
	            if (Tools.isEmpty(endWord)) {
	            	returnMap = new HashMap<String, Object>();
	            	returnMap.put("errorNum", rowNum);
	    			returnMap.put("errorMsg", "到达城市不能为空");
	    			returnList.add(returnMap);
	    			continue;
	            } else {
	            	PageData endPd = this.sourceGoodsService.getCityMsgByName(endWord.trim());
	            	if (null == endPd || 0 == endPd.size()) {
	            		returnMap = new HashMap<String, Object>();
	                	returnMap.put("errorNum", rowNum);
	        			returnMap.put("errorMsg", "到达城市在系统不存在");
	        			returnList.add(returnMap);
	        			continue;
	            	} else {
	            		paramMap.put("endWordName", endWord);
	            		paramMap.put("endWordCode", endPd.get("REGION_CODE"));
	            		endLoad = endPd.get("REGION_CODE");
	            	}
	            }
	            loadMap = new PageData();
	            loadMap.put("src_city_id", loadBegin);
	            loadMap.put("des_city_id", endLoad);
	            loadSet.add(loadMap);
	            String beginWordLoad =getCellValue(row.getCell(3)); // 起始详细地址
	            if (Tools.isEmpty(beginWordLoad)) {
	            	returnMap = new HashMap<String, Object>();
	            	returnMap.put("errorNum", rowNum);
	    			returnMap.put("errorMsg", "起始详细地址不能为空");
	    			returnList.add(returnMap);
	    			continue;
	            } else {
	            	paramMap.put("beginWordLoad", beginWordLoad);
	            }
	            String endWordLoad =getCellValue(row.getCell(4)); // 目的详细地址
	            if (Tools.isEmpty(endWordLoad)) {
	            	returnMap = new HashMap<String, Object>();
	            	returnMap.put("errorNum", rowNum);
	    			returnMap.put("errorMsg", "目的详细地址不能为空");
	    			returnList.add(returnMap);
	    			continue;
	            } else {
	            	paramMap.put("endWordLoad", endWordLoad);
	            }
	            Date beginDate =row.getCell(5).getDateCellValue(); // 期望交车时间
	            if (null == beginDate) {
	            	returnMap = new HashMap<String, Object>();
	            	returnMap.put("errorNum", rowNum);
	    			returnMap.put("errorMsg", "期望交车时间不能为空");
	    			returnList.add(returnMap);
	    			continue;
	            } else {
	            	paramMap.put("beginDate", DateUtil.formateDate(beginDate, "yyyy-MM-dd"));
	            }
	            Date endDate =row.getCell(6).getDateCellValue();  // 期望运达时间
	            if (null == endDate) {
	            	returnMap = new HashMap<String, Object>();
	            	returnMap.put("errorNum", rowNum);
	    			returnMap.put("errorMsg", "期望运达时间不能为空");
	    			returnList.add(returnMap);
	    			continue;
	            } else {
	            	paramMap.put("endDate", DateUtil.formateDate(endDate, "yyyy-MM-dd"));
	            }
	            String expertPrice =getCellValue(row.getCell(7)); // 期望运费
	            if (Tools.isEmpty(expertPrice)) {
	            	returnMap = new HashMap<String, Object>();
	            	returnMap.put("errorNum", rowNum);
	    			returnMap.put("errorMsg", "期望运费不能为空");
	    			returnList.add(returnMap);
	    			continue;
	            } else {
	            	paramMap.put("expertPrice",Double.parseDouble(expertPrice));
	            }
	            String isTax =getCellValue(row.getCell(8)); // 是否含税
	            if (Tools.isEmpty(isTax)) {
	            	returnMap = new HashMap<String, Object>();
	            	returnMap.put("errorNum", rowNum);
	    			returnMap.put("errorMsg", "是否含税不能为空");
	    			returnList.add(returnMap);
	    			continue;
	            } else {
	            	if ("是".equals(isTax.trim())) {
	            		paramMap.put("isTax",SysDataCode.STATUS_TYPE_01);
	            	} else {
	            		paramMap.put("isTax",SysDataCode.STATUS_TYPE_02);
	            	}
	            }
	            String modelCar =getCellValue(row.getCell(9)); // 车型
	            if (Tools.isEmpty(modelCar)) {
	            	returnMap = new HashMap<String, Object>();
	            	returnMap.put("errorNum", rowNum);
	    			returnMap.put("errorMsg", "车型不能为空");
	    			returnList.add(returnMap);
	    			continue;
	            } else {
	            	PageData modelPd = this.sourceGoodsService.getModelMsgByName(modelCar.trim());
	            	if (null == modelPd || 0 == modelPd.size()) {
	            		returnMap = new HashMap<String, Object>();
	                	returnMap.put("errorNum", rowNum);
	        			returnMap.put("errorMsg", "车型在系统不存在");
	        			returnList.add(returnMap);
	        			break;
	            	} else {
	            		paramMap.put("modelId", modelPd.get("ID"));
	            		paramMap.put("modelName", modelPd.get("NAME"));
	            		paramMap.put("modelCode", modelPd.get("CODE"));
	            	}
	            }
	            String vin =getCellValue(row.getCell(10)); // 车架号
	            if (Tools.isEmpty(vin)) {
	            	returnMap = new HashMap<String, Object>();
	            	returnMap.put("errorNum", rowNum);
	    			returnMap.put("errorMsg", "车架号不能为空");
	    			returnList.add(returnMap);
	    			continue;
	            } else {
	            	/*PageData vinPd = this.sourceGoodsService.getCarMsgByVin(vin.trim());
	            	if (null == vinPd || 0 == vinPd.size()) {
	            		returnMap = new HashMap<String, Object>();
	                	returnMap.put("errorNum", rowNum);
	        			returnMap.put("errorMsg", "车辆(VIN)在系统不存在");
	        			returnList.add(returnMap);
	        			break;
	            	} else {
	            		paramMap.put("vin", vin);
	            		paramMap.put("carNo", vinPd.get("PLATE_NUM"));
	            		paramMap.put("vehiceId", vinPd.get("VEHICLE_ID"));
	            	}*/
	            	paramMap.put("vin", vin);
            		paramMap.put("carNo", null);
            		paramMap.put("vehiceId", null);
	            }
	            String sendName =getCellValue(row.getCell(11)); //发货地联系人姓名
	            if (Tools.isEmpty(sendName)) {
	            	returnMap = new HashMap<String, Object>();
	            	returnMap.put("errorNum", rowNum);
	    			returnMap.put("errorMsg", "发货地联系人姓名不能为空");
	    			returnList.add(returnMap);
	    			continue;
	            } else {
	            	paramMap.put("sendName", sendName);
	            }
	            String sendTelphone =getCellValue(row.getCell(12)); // 发货地联系人电话
	            if (Tools.isEmpty(sendTelphone)) {
	            	returnMap = new HashMap<String, Object>();
	            	returnMap.put("errorNum", rowNum);
	    			returnMap.put("errorMsg", "发货地联系人电话不能为空");
	    			returnList.add(returnMap);
	    			continue;
	            } else {
	            	//判断电话在通讯录中是否有，没有需要添加一条记录
	            	PageData tmpTelPd = new PageData();
	            	tmpTelPd.put("userId", paramMap.get("ownerId"));
	            	tmpTelPd.put("userChinName", sendName);
	            	tmpTelPd.put("sendTelphone", sendTelphone.trim());
	            	tmpTelPd.put("dealerId", this.getDealerId());
	            	String sendId = this.sourceGoodsService.checkUserMsgInSys(tmpTelPd);
	            	paramMap.put("sendId", sendId);
	            	paramMap.put("sendName", sendName);
	            	paramMap.put("sendTel", sendTelphone.trim());
	            }
	            String receiveName =getCellValue(row.getCell(13)); //收货地联系人姓名
	            if (Tools.isEmpty(receiveName)) {
	            	returnMap = new HashMap<String, Object>();
	            	returnMap.put("errorNum", rowNum);
	    			returnMap.put("errorMsg", "收货地联系人姓名不能为空");
	    			returnList.add(returnMap);
	    			continue;
	            } else {
	            	paramMap.put("receiveName", receiveName);
	            }
	            String recevieTelphone =getCellValue(row.getCell(14)); // 收货地联系人电话
	            if (Tools.isEmpty(recevieTelphone)) {
	            	returnMap = new HashMap<String, Object>();
	            	returnMap.put("errorNum", rowNum);
	    			returnMap.put("errorMsg", "收货地联系人电话不能为空");
	    			returnList.add(returnMap);
	    			continue;
	            } else {
	            	//判断电话在通讯录中是否有，没有需要添加一条记录
	            	PageData tmpTelPd = new PageData();
	            	tmpTelPd.put("userId", paramMap.get("ownerId"));
	            	tmpTelPd.put("userChinName", receiveName);
	            	tmpTelPd.put("sendTelphone", recevieTelphone.trim());
	            	tmpTelPd.put("dealerId", this.getDealerId());
	            	String recevieId = this.sourceGoodsService.checkUserMsgInSys(tmpTelPd);
	            	paramMap.put("recevieId", recevieId);
	            	paramMap.put("recevieName", receiveName);
	            	paramMap.put("recevieTel", recevieTelphone.trim());
	            }
	            paramMap.put("sourceRemark", getCellValue(row.getCell(15)));
	            paramList.add(paramMap);
	        //循环结束    
	        }  
	        
	        //给司机发送信息 暂时不发送消息
	        // this.commonService.sendMessageByJpushAlisaByLine(loadSet);
	       /* for (PageData map : loadSet) {  
	        	this.appCommonService.sendOrderLineSmsMessage(map);
	        } */
	        workbook.close();  
	    }  
		if (null != returnList && 0 < returnList.size()) {
			mv.addObject("returnList", returnList);
			mv.setViewName("system/dealer/source_error");
			return mv;
		} else {
			//添加货源信息
			this.sourceGoodsService.insertSourceGoodsMsg(paramList);
			return userList(page);
		}
	}
	
	public String checkFile(MultipartFile file){  
		 String tmp = "";
		//判断文件是否存在  
		if(null == file || file.isEmpty()){  
			tmp= "文件不存在！";
		}  
		//获得文件名  
		String fileName = file.getOriginalFilename();  
		//判断文件是否是excel文件  
		if(!fileName.endsWith("xls") && !fileName.endsWith("xlsx")){  
			tmp= "不是excel文件";
		}  
		return tmp; 
	 } 
	 
	 //切换不同版本的excel
	 public static Workbook getWorkBook(MultipartFile file) {  
	     //获得文件名  
	     String fileName = file.getOriginalFilename();  
	     //创建Workbook工作薄对象，表示整个excel  
	     Workbook workbook = null;  
	     try {  
	         //获取excel文件的io流  
	         InputStream is = file.getInputStream();  
	         //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象  
	         if(fileName.endsWith("xls")){  
	             //2003  
	             workbook = new HSSFWorkbook(is);  
	         }else if(fileName.endsWith("xlsx")){  
	             //2007  
	             workbook = new XSSFWorkbook(is);  
	         }  
	     } catch (IOException e) {  
	    	 
	     }  
	     return workbook;  
	 }  
	 
	 public String getCellValue(Cell cell){  
	     String cellValue = "";  
	     if(cell == null){  
	         return cellValue;  
	     }  
	     //把数字当成String来读，避免出现1读成1.0的情况  
	     if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){  
	         cell.setCellType(Cell.CELL_TYPE_STRING);  
	     }  
	     //判断数据的类型  
	     switch (cell.getCellType()){  
	         case Cell.CELL_TYPE_NUMERIC: //数字  
	             cellValue = String.valueOf(cell.getNumericCellValue());  
	             break;  
	         case Cell.CELL_TYPE_STRING: //字符串  
	             cellValue = String.valueOf(cell.getStringCellValue());  
	             break;  
	         case Cell.CELL_TYPE_BOOLEAN: //Boolean  
	             cellValue = String.valueOf(cell.getBooleanCellValue());  
	             break;  
	         case Cell.CELL_TYPE_FORMULA: //公式  
	             cellValue = String.valueOf(cell.getCellFormula());  
	             break;  
	         case Cell.CELL_TYPE_BLANK: //空值   
	             cellValue = "";  
	             break;  
	         case Cell.CELL_TYPE_ERROR: //故障  
	             cellValue = "非法字符";  
	             break;  
	         default:  
	             cellValue = "未知类型";  
	             break;  
	     }  
	     return cellValue;  
	 }  
	 
	 /***承运商查看司机报价失败的货源* */
	@RequestMapping(value = "/dealerDriverSourceFailer")
	public ModelAndView dealerDriverSourceFailer(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd.put("dealerId", this.getDealerId());
			page.setPd(pd);
			List<PageData> sourceList = this.sourceGoodsService.dealerDriverSourceFailerlistPage(page);
			PageData matPd = new PageData();
			matPd.put("matLevel", SysDataCode.CAR_MODLE_TYPE_01);
			List<PageData> matList = this.commonService.getAutoMatList(matPd);//车型
			List<PageData> provinceList = this.commonService.getProvinceList(); // 省份
			mv.addObject("matList", matList);
			mv.addObject("provinceList", provinceList);
			 mv.addObject("sourceList", sourceList);
			 mv.addObject("pd", pd);
			mv.setViewName("system/dealer/dealer_driver_source_failer");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	 /***承运商查看司机的订单信息* */
	@RequestMapping(value = "/dealerDriverSourceOrder")
	public ModelAndView dealerDriverSourceOrder(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd.put("dealerId", this.getDealerId());
			page.setPd(pd);
			List<PageData> orderList = this.sourceGoodsService.dealerDriverSourceOrderlistPage(page);
			PageData matPd = new PageData();
			matPd.put("matLevel", SysDataCode.CAR_MODLE_TYPE_01);
			List<PageData> matList = this.commonService.getAutoMatList(matPd);//车型
			List<PageData> provinceList = this.commonService.getProvinceList(); // 省份
			mv.addObject("matList", matList);
			mv.addObject("provinceList", provinceList);
			mv.addObject("orderList", orderList);
			mv.addObject("pd", pd);
			mv.setViewName("system/dealer/dealer_driver_source_order");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	/***承运商查看全国货源* */
	@RequestMapping(value = "/dealerDriverSourceChina")
	public ModelAndView dealerDriverSourceChina(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd.put("dealerId", this.getDealerId());
			page.setPd(pd);
			List<PageData> sourceList = this.sourceGoodsService.dealerDriverSourceChinalistPage(page);
			PageData matPd = new PageData();
			matPd.put("matLevel", SysDataCode.CAR_MODLE_TYPE_01);
			List<PageData> matList = this.commonService.getAutoMatList(matPd);//车型
			List<PageData> provinceList = this.commonService.getProvinceList(); // 省份
			mv.addObject("matList", matList);
			mv.addObject("provinceList", provinceList);
			mv.addObject("sourceList", sourceList);
			mv.addObject(Const.SESSION_QX, this.getPermission());
			mv.addObject("pd", pd);
			mv.addObject("orgType", this.getOrgType());
			mv.addObject("useType", this.getUser().getUSER_TYPE());
			mv.setViewName("system/dealer/dealer_source_inchina");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	/***承运商提醒司机报价* */
	@RequestMapping(value = "/alertDriverGetSource")
	public ModelAndView alertDriverGetSource() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			//根据dealerId 获取承运商下面的司机
			pd.put("dealerId", this.getDealerId());
			List<PageData> driverList =this.sourceGoodsService.getDriverList(pd);
			//查询当前货源的指导价
			PageData dp = this.sourceGoodsService.getSourceDetailMsg(pd);
			mv.addObject("pd", pd);
			mv.addObject("dp", dp);
			mv.addObject("driverList", driverList);
			mv.setViewName("system/dealer/dealer_driver");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	 /***承运商审核司机的报价列表* */
	@RequestMapping(value = "/dealerDriverExamineSource")
	public ModelAndView dealerDriverExamineSource(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd.put("dealerId", this.getDealerId());
			page.setPd(pd);
			List<PageData> sourceList = this.sourceGoodsService.dealerDriverExamineSourcelistPage(page);
			PageData matPd = new PageData();
			matPd.put("matLevel", SysDataCode.CAR_MODLE_TYPE_01);
			List<PageData> matList = this.commonService.getAutoMatList(matPd);//车型
			List<PageData> provinceList = this.commonService.getProvinceList(); // 省份
			mv.addObject("matList", matList);
			mv.addObject("provinceList", provinceList);
			mv.addObject("sourceList", sourceList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX, this.getPermission());
			mv.setViewName("system/dealer/dealer_driver_examine_source");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	 /***承运商审提醒司机* */
    @RequestMapping(value="/notcieOfferDriverAndDealer")
    public ModelAndView notcieOfferDriverAndDealer() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        pd.put("userChinese", getUser().getUSER_ID());
        pd.put("dealerId", this.getDealerId());
        String driverStr = Tools.checkObj(pd.get("diverId"));
        String driverId = "";
        String driverName = "";
        if (!Tools.isEmpty(driverStr)) {
        	driverId=driverStr.split(",")[0];
        	driverName = driverStr.split(",")[1];
        }
        PageData loadMap = new PageData();
        loadMap.put("dealerName", this.commonService.getDealerNameByDealerId(pd));
        loadMap.put("sourceId", pd.get("sourceId"));
        loadMap.put("src_city", pd.get("beginCity"));
        loadMap.put("des_city", pd.get("endCity"));
        loadMap.put("price_num", pd.get("priceNum"));
        pd.put("driversId", driverId);
        //插入一条提醒报价记录
        this.sourceGoodsService.insertOfferNoticeMsg(pd);
        //给司机推送一条消息
        PageData msgPd = new PageData();
		msgPd.put("msgTitle", "报价通知");
		msgPd.put("msgContent", SmsUtil.getMessageAndReplaceKey("SMS_70015", loadMap));
	    msgPd.put("receiveUserId", driverId);
	    msgPd.put("receiveUserName", driverName);
        this.commonService.sendMessageByJpushAlisaByDriver(String.valueOf(driverId), msgPd);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }
	
	/***承运商审核司机的货源页面* */
	@RequestMapping(value = "/dealerExamineDriverSource")
	public ModelAndView dealerExamineDriverSource() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			String sourceId = pd.getString("sourceId");
			if (Tools.isEmpty(sourceId)) {
				return mv;
			} else {
				pd.put("sourceId",sourceId);
			}
			pd.put("dealerId", this.getDealerId());
			PageData sourcePd = this.sourceGoodsService.getSourceDetailMsg(pd);
			Object modelId = sourcePd.get("MODLE_ID"); //车型ID
			PageData carPd = null;
			if (null != modelId) {
				//根据ID查询品牌
				PageData ppp = new PageData();
				ppp.put("modelId", modelId);
				carPd = this.sourceGoodsService.getBrandByModelId(ppp);
			}
			//查询报价列表
			List<PageData> offerList = this.sourceGoodsService.getOfferPriceList(pd);
			mv.addObject("sourcePd", sourcePd);
			mv.addObject("carPd", carPd);
			mv.addObject("offerList", offerList);
			mv.addObject("pd", pd);
			mv.setViewName("system/dealer/dealer_examine_driver_source");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	/***承运商审核司机的货源 */
	@RequestMapping(value = "/dealerExamineDriverMsg")
    public ModelAndView dealerExamineDriverMsg() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        pd.put("userId", this.getUser().getUSER_ID());
        this.sourceGoodsService.dealerExamineDriverMsg(pd);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }
	
	/***平台给货主充值* */
	@RequestMapping(value = "/platformRechargeInOwner")
	public ModelAndView platformRechargeInOwner(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			page.setPd(pd);
			List<PageData> userList = this.sourceGoodsService.platformRechargeInOwnerlistPage(page);
			mv.addObject("userList", userList);
			mv.addObject("pd", pd);
			mv.setViewName("system/dealer/platform_recharge");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	/***平台给货主充值页面* */
	@RequestMapping(value = "/platformRechargeInOwnerDialog")
	public ModelAndView platformRechargeInOwnerDialog() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			List<PageData> userList = this.sourceGoodsService.platformRechargeInOwnerDilog(pd);
			mv.addObject("userList", userList);
			mv.addObject("pd", pd);
			mv.setViewName("system/dealer/plamt_recharge_dialog");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	/***平台给货主充值 */
	@RequestMapping(value = "/platformRechargeInOwnerMoney")
    public ModelAndView platformRechargeInOwnerMoney() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String userStr = Tools.checkObj(pd.get("userStr"));
        if (!Tools.isEmpty(userStr)) {
        	  pd.put("userId", userStr.split(",")[0]);
        	  pd.put("userName", userStr.split(",")[1]);
        	  pd.put("myUser", this.getUser().getUSER_ID());
              this.sourceGoodsService.platformRechargeInOwnerMoney(pd);
        }
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }
	
	/***货主充值记录* */
	@RequestMapping(value = "/platformRechargenOwnerRecord")
	public ModelAndView platformRechargenOwnerRecord(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			page.setPd(pd);
			List<PageData> platList = this.sourceGoodsService.platformRechargenOwnerRecordlistPage(page);
			mv.addObject("platList", platList);
			mv.addObject("pd", pd);
			mv.setViewName("system/dealer/plat_rechart_record_list");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	/***意见反馈页面* */
	@RequestMapping(value = "/feedbackList")
	public ModelAndView feedbackList(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			page.setPd(pd);
			List<PageData> feedList = this.sourceGoodsService.feedbackList(page);
			mv.addObject("feedList", feedList);
			mv.addObject("pd", pd);
			mv.setViewName("system/dealer/feedback_list");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	/***意见反馈详情页面* */
	@RequestMapping(value = "/feedbackDetail")
	public ModelAndView feedbackDetail() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			PageData feedPd = this.sourceGoodsService.feedbackDetail(pd);
			//图片列表
			List<PageData> picList = this.sourceGoodsService.feedbackPicList(pd);
			mv.addObject("feedPd", feedPd);
			mv.addObject("picList", picList);
			mv.setViewName("system/dealer/feedback_detail");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	/***广告页面**/
	@RequestMapping(value = "/advertList")
	public ModelAndView advertList(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd.put("dealerId", this.getDealerId());
			page.setPd(pd);
			List<PageData> advertList = this.sourceGoodsService.advertList(page);
			mv.addObject("advertList", advertList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX, this.getPermission());
			mv.setViewName("advert/advert_list");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	/***广告页面新增页面**/
	@RequestMapping(value = "/advertAddPage")
	public ModelAndView advertAddPage() throws Exception {
		ModelAndView mv = this.getModelAndView();
		try {
			mv.setViewName("advert/advert_add");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	/***新增广告 */
	@RequestMapping(value = "/addAdverMsg")
    public ModelAndView addAdverMsg(@RequestParam MultipartFile loadFiles,HttpServletRequest request) throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String advertType = request.getParameter("advertType");
        String title = request.getParameter("title");
        String context = request.getParameter("context");
        String contentType = request.getParameter("contentType");
        String link = request.getParameter("link");
        pd.put("advertType", advertType);
        pd.put("title", title);
        pd.put("context", context);
        pd.put("contentType", contentType);
        pd.put("link", link);
        pd.put("userName", this.getUser().getUSER_NAME());
        pd.put("userId", this.getUser().getUSER_ID());
        String orgType = this.getOrgType();
		if ("10120001".equals(orgType)) {
			pd.put("dealerId", 0);
		} else {
			pd.put("dealerId", this.getDealerId());
		}
        this.sourceGoodsService.addAdverMsg(pd, loadFiles);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }
	
	/***广告页面修改页面**/
	@RequestMapping(value = "/advertUpdatePage")
	public ModelAndView advertUpdatePage() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		try {
			//查询单个广告
			PageData  parmPd = this.sourceGoodsService.adverDetialById(pd);
			//查询图片
			String url = this.sourceGoodsService.getAdverDetialPicById(pd);
			mv.addObject("parmPd", parmPd);
			mv.addObject("picUrl", url);
			mv.addObject("pd", pd);
			mv.setViewName("advert/advert_edit");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return mv;
	}
	
	/***修改广告 */
	@RequestMapping(value = "/updateAdverMsg")
    public ModelAndView updateAdverMsg(@RequestParam MultipartFile loadFiles,HttpServletRequest request) throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String advertId = request.getParameter("advertId");
        String advertType = request.getParameter("advertType");
        String title = request.getParameter("title");
        String context = request.getParameter("context");
        String advertStatus = request.getParameter("advertStatus");
        String contentType = request.getParameter("contentType");
        String link = request.getParameter("link");
        pd.put("advertId", advertId);
        pd.put("advertType", advertType);
        pd.put("title", title);
        pd.put("context", context);
        pd.put("advertStatus", advertStatus);
        pd.put("contentType", contentType);
        pd.put("link", link);
        pd.put("userName", this.getUser().getUSER_NAME());
        pd.put("userId", this.getUser().getUSER_ID());
        this.sourceGoodsService.updateAdverMsg(pd, loadFiles);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }
	
}
