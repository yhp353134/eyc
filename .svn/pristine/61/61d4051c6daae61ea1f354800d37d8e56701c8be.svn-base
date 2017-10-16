package com.fh.controller.app.location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.service.app.location.AppLocationService;
import com.fh.util.AppConstant;
import com.fh.util.AppUtil;
import com.fh.util.PageData;
import com.fh.util.Tools;
/**
 * 位置上报
 * @author ljie
 *
 */
@Controller
@RequestMapping("/app/appLocation")
public class AppLocationController extends BaseController {
	@Resource(name="appLocationService")
	private  AppLocationService   appLocationService;
    /**
     * 位置上报
     * @param request
     * @param response
     * @param page
     * @return
     */
	@RequestMapping("/locationReport")
	@ResponseBody
	public Map<String, Object> locationReport(HttpServletRequest request ,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String user_id = pd.getString("user_id");//登陆用户id
//           String order_id = pd.getString("order_id");//订单id
           String logitude = pd.getString("logitude");//经度
           String latitude = pd.getString("latitude");//纬度
           String address = pd.getString("address");//地址
			if (Tools.isEmpty(user_id)/*||Tools.isEmpty(order_id)*/||Tools.isEmpty(logitude)||Tools.isEmpty(latitude)||Tools.isEmpty(address)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				 Map<String, Object> loMap = appLocationService.locationReport(pd);
				 return loMap;
				/*if (CollectionUtils.isEmpty(list)||list.size()<=0) {
					resultMap.put("status", AppConstant.ERR_CODE_1014);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1014));
					return resultMap;
				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
//		return  AppUtil.returnMap(resultMap);
	}
	
}
