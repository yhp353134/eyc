package com.fh.controller.app.appcommon;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fh.controller.base.BaseController;
import com.fh.service.app.appcommon.AppCommonService;
import com.fh.util.AppConstant;
import com.fh.util.AppUtil;
import com.fh.util.PageData;
import com.fh.util.Tools;

/***
 * 公共方法类
 * lijie
 * */
@Controller
@RequestMapping("/app/appCommon")
public class AppCommonController extends  BaseController {

	@Resource(name="appCommonService")
	private  AppCommonService  appCommonService;
	
	/**
	 * 文件上传
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/uploadFile")
	@ResponseBody
	public Map<String, Object> uploadFile(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		String use_type = request.getParameter("use_type");//使用类型
		String use_detail_type = request.getParameter("use_detail_type");//使用详细类型
		String user_id = request.getParameter("user_id");//用户id
		if(Tools.isEmpty(user_id)||Tools.isEmpty(use_detail_type)||Tools.isEmpty(use_type)){
			resultMap.put("status", AppConstant.ERR_CODE_1003);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
			return resultMap;
		}
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
		if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if (file != null) {
                    if (!file.isEmpty()) {
                    	CommonsMultipartFile cf = (CommonsMultipartFile)file;
						try {
							Map<String, Object> map = new HashMap<String, Object>();
							String buss_id =  appCommonService.uploadFile(file,request);
							map.put("file_id", buss_id);
							resultMap.put(AppConstant.RESULT, map);
						} catch (Exception e) {
							resultMap.put("status", AppConstant.ERR_CODE_1005);
							resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
							return resultMap;
						}
                    }
                }else {
                	resultMap.put("status", AppConstant.ERR_CODE_1018);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1018));
					return resultMap;
				}
            }
		}
		return resultMap;
	}
	
	/**根据货源ID查询当前订单的状态 */
	@RequestMapping("/getBussniesStatusById")
	@ResponseBody
	public String getBussniesStatusById(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String objectId = request.getParameter("objectId");
		PageData pm = new PageData();
		pm.put("objectId", objectId);
		PageData rt = this.appCommonService.getBussniesStatusById(pm);
		String status = Tools.checkObj(rt.get("STATUS"));
		if (Tools.isEmpty(status)) {
			return "";
		} else {
			return status;
		}
	}
	
}
