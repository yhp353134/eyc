package com.fh.controller.app.register;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.app.register.AppUserRegisterService;
import com.fh.util.AppConstant;
import com.fh.util.AppUtil;
import com.fh.util.PageData;
import com.fh.util.SysDataCode;
import com.fh.util.Tools;

/**
 * 2017年8月14日
 * @author ljie
 *
 */
@Controller
@RequestMapping("/app/appuserRegister/")
public class AppUserRegisterController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource(name = "appUserRegisterService")
	private AppUserRegisterService appUserRegisterService;
	
	/**
	 * 发送验证码
	 * @return
	 */
	@RequestMapping("/sendSmsCode")
	@ResponseBody
	public Map<String, Object> sendSmsCode(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		
		try {
			PageData pd = this.getPageData();
			String phone = pd.getString("phone");//电话号码
			String sms_type = pd.getString("sms_type");//验证码类型
			if(Tools.isEmpty(phone)||Tools.isEmpty(sms_type)){
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else{
				//注册验证
				if("SMS_10001".equals(sms_type)){
					PageData userByPhone = appUserRegisterService.getSysUserByPhone(pd);
					if(null!=userByPhone&&userByPhone.size()>0){
						resultMap.put("status", AppConstant.ERR_CODE_1007);
						resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1007));
						return resultMap;
					}
				}
				String res =  appUserRegisterService.saveSendSmsCode(pd);
				/*if(AppConstant.ERR_CODE_1007.equals(res)){
					resultMap.put("status",AppConstant.ERR_CODE_1007);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1007));
				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status",  AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey( AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		
	      return	resultMap;
	}
	

	/**
	 * 司机用户注册
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping("/driverUserRegister")
	@ResponseBody
	public Map<String, Object> driverUserRegister(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String phone = pd.getString("phone");//电话
			String user_name = pd.getString("user_name");//用户名
			String ver_code = pd.getString("ver_code");//验证码
//			String car_no = pd.getString("car_no");//身份证
			String password = pd.getString("password");//密码
			String plate_num = pd.getString("plate_num");//车牌
			String dealer_id = pd.getString("dealer_id");//经销商id
			String car_num = pd.getString("car_num");//车位数
			String factory_year = pd.getString("factory_year");//出厂年份
			if (Tools.isEmpty(phone)||Tools.isEmpty(user_name)||Tools.isEmpty(ver_code)/*||Tools.isEmpty(car_no)*/
					||Tools.isEmpty(factory_year)||Tools.isEmpty(password)
					||Tools.isEmpty(plate_num)||Tools.isEmpty(car_num)||Tools.isEmpty(dealer_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			} else {
				//根据dealerId查询当前机构的类型
				PageData userTypePd = this.appUserRegisterService.getDealerMsgById(pd);
				if (null == userTypePd || "null".equals(userTypePd) || userTypePd.size() ==0) {
					resultMap.put("status", AppConstant.ERR_CODE_1003);
					resultMap.put("msg", "选择的挂靠的公司在库中不存在");
					return resultMap;
				}
				String dealerType = Tools.checkObj(userTypePd.get("DEALER_TYPE"));
				if ("10151001".equals(dealerType)) {
					//b端司机
					pd.put("user_type", SysDataCode.USR_TYPE_03);
				} else if ("10151005".equals(dealerType)) {
					//c端司机
					pd.put("user_type", SysDataCode.USR_TYPE_05);
				} else if ("10151004".equals(dealerType)) {
					// VTC司机
					pd.put("user_type", SysDataCode.USR_TYPE_06);
				} else {
					pd.put("user_type", -1);
				}
				pd.put("is_main_count", SysDataCode.STATUS_TYPE_01);
				//根据电话查询用户表中是否注册过该账号
				PageData pagedata = appUserRegisterService.getSysUserByPhone(pd);
				if(null!=pagedata&&pagedata.size()>0){
					resultMap.put("status", AppConstant.ERR_CODE_1007);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1007));
					return resultMap;
				}
				pd.put("verify_date", 30);
				//根据验证码  电话   时间查询验证信息
				PageData vercode = appUserRegisterService.getEverCodeByPhoneAndVercodeType(pd);
				if(null==vercode||vercode.size()<=0){
					resultMap.put("status", AppConstant.ERR_CODE_1009);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1009));
					return resultMap;
				}
				//验证车牌号
			    boolean flag =	appUserRegisterService.getEvehicleByPlateNum(pd);
			    if(flag){
			    	resultMap.put("status", AppConstant.ERR_CODE_1020);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1020));
					return resultMap;
			    }
			    //保存用户信息
				PageData sysUser = appUserRegisterService.saveUserToSysUser(pd);
				resultMap.put(AppConstant.RESULT,sysUser);
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
	 * 添加司机用户
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping("/addDriverUserRegister")
	@ResponseBody
	public Map<String, Object> addDriverUserRegister(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String user_id = pd.getString("user_id");//当前用户id
			String phone = pd.getString("phone");//电话
			String user_name = pd.getString("user_name");//用户名
			String car_no = pd.getString("car_no");//身份证
			String password = pd.getString("password");//密码
			String fileids = pd.getString("file_id");//文件id
			if (Tools.isEmpty(user_id)||Tools.isEmpty(fileids)||Tools.isEmpty(phone)||Tools.isEmpty(user_name)||Tools.isEmpty(car_no)
					||Tools.isEmpty(password)
					) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			} else {
				Boolean flag = appUserRegisterService.getEuserVehicleListByUserId(pd);
				if(!flag){
					resultMap.put("status", AppConstant.ERR_CODE_1019);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1019));
					return resultMap;
				}
				//判断是否注册过的
				PageData data = appUserRegisterService.getSysUserByPhone(pd);
				if(null!=data&&data.size()>0){
					resultMap.put("status", AppConstant.ERR_CODE_1007);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1007));
					return resultMap;
				}
				pd.put("user_type", SysDataCode.USR_TYPE_03);
				appUserRegisterService.addDriverUserRegister(pd);
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
	 * 货主用户注册
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping("/ownerUserRegister")
	@ResponseBody
	public Map<String, Object> ownerUserRegister(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String phone = pd.getString("phone");//电话
			String user_name = pd.getString("user_name");//用户名
			String ver_code = pd.getString("ver_code");//验证码
			String user_type = pd.getString("user_type");//用户类型
			String password = pd.getString("password");//密码
			if (Tools.isEmpty(phone)||Tools.isEmpty(user_name)||Tools.isEmpty(user_type)||Tools.isEmpty(ver_code)
					||Tools.isEmpty(password)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			} else {
				//货主验证验证
				PageData pagedata = appUserRegisterService.getSysUserByPhone(pd);
				if(null!=pagedata&&pagedata.size()>0){
					resultMap.put("status", AppConstant.ERR_CODE_1007);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1007));
					return resultMap;
				}
				pd.put("verify_date", 30);
				// 根据验证码  电话   时间查询验证信息
				PageData vercode = appUserRegisterService.getEverCodeByPhoneAndVercodeType(pd);
				if(null==vercode||vercode.size()<=0){
					resultMap.put("status", AppConstant.ERR_CODE_1009);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1009));
					return resultMap;
				}
				//保存用户信息
				PageData data = appUserRegisterService.ownerUserRegister(pd);
				resultMap.put(AppConstant.RESULT, data);
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
	 * 用户登陆
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/userLogin")
	@ResponseBody
	public Map<String,Object> userLogin(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String user_name = pd.getString("user_name");
			String password = pd.getString("password");
			String over_code = pd.getString("over_code");
			String login_type = pd.getString("login_type");
			if (Tools.isEmpty(user_name)||Tools.isEmpty(login_type)&&(Tools.notEmpty(password)||Tools.notEmpty(over_code))) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else{
				PageData mp ;
				if(String.valueOf(SysDataCode.LOGIN_TYPE_01).equals(login_type)){
					mp =  appUserRegisterService.getUserByMessageInfoByPassword(pd);
//					return mp;
				}else{
					pd.put("verify_date", 10);
				   PageData data = appUserRegisterService.getEverCodeByPhoneAndVercodeType(pd);
				   if(null==data){
					    resultMap.put("status", AppConstant.ERR_CODE_1009);
						resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1009));
						return resultMap;
				   }
					mp =  appUserRegisterService.getUserByMessageInfoByVerCode(pd);
				}
				if(null==mp||mp.size()<=0){
					resultMap.put("status", AppConstant.ERR_CODE_1011);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1011));
					return resultMap;
				}
//				resultMap.put(AppConstant.RESULT, mp);
				return mp;
			}
			
		} catch (Exception e) {
		    e.printStackTrace();
		    resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
//		return resultMap;
	}
    /**
     * 司机验证(车辆认证)
     * @param request
     * @param response
     * @return
     */
	@RequestMapping("/driverSubmitVerify")
	@ResponseBody
	public Map<String, Object> driverSubmitVerify(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd= this.getPageData();
			String user_id = pd.getString("user_id");
			String plate_num = pd.getString("plate_num");//车牌号
			String[] file_ids =  request.getParameterValues("file_id");//文件id
			if(Tools.isEmpty(user_id)||Tools.isEmpty(plate_num)||file_ids.length<=0){
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else{
				pd.put("file_ids",file_ids);
				appUserRegisterService.driverSubmitVerify(pd);
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
	 * 货主验证（经销商信息）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/ownerSubmitVerify")
	@ResponseBody
	public Map<String, Object> ownerSubmitVerify(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd= this.getPageData();
			String user_id = pd.getString("user_id");//用户id
			String file_id = pd.getString("file_id");//文件id
			String dealer_name = pd.getString("dealer_name");//经销商名称
			if(Tools.isEmpty(user_id)||Tools.isEmpty(file_id)||Tools.isEmpty(dealer_name)){
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else{
				//保存用户的验证
				this.appUserRegisterService.ownerSubmitVerify(pd);
				//查询用户信息
				PageData user = appUserRegisterService.getSysUserByUserId(pd);
				resultMap.put(AppConstant.RESULT, user);
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
	 * 获取承运商
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getAllCarrier")
	@ResponseBody
	public Map<String, Object> getAllCarrier(HttpServletRequest request,HttpServletResponse response,Page page) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd= this.getPageData();
			// String dealer_name = pd.getString("dealer_name");//承运商名称
			page.setPd(pd);
			List<PageData> list =  appUserRegisterService.getAllCarrier(page);
			Map<String, Object>  map = new HashMap<String, Object>();
			map.put("list", list);
			map.put("page", page);
			resultMap.put(AppConstant.RESULT, map);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		
		return AppUtil.returnMap(resultMap);
	}
    /**
     * 联系人列表
     * @param request
     * @param response
     * @return
     */
	@RequestMapping("/getEcontacts")
	@ResponseBody
	public Map<String, Object> getEcontacts(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd= this.getPageData();
			String user_id = pd.getString("user_id");//用户id
			if (Tools.isEmpty(user_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				List<PageData> list =  appUserRegisterService.getEcontacts(pd);
				resultMap.put(AppConstant.RESULT, list);
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
	 * 保存联系人
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/saveEcontacts")
	@ResponseBody
	public Map<String, Object> saveEcontacts(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd= this.getPageData();
			String user_id = pd.getString("user_id");//用户id
			String user_name = pd.getString("con_name");//联系人名字
			String phone = pd.getString("con_phone");//联系电话
			String con_num = pd.getString("con_num");//联系人身份证
			if (Tools.isEmpty(user_id)||Tools.isEmpty(user_name)||Tools.isEmpty(phone)||Tools.isEmpty(con_num)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				appUserRegisterService.saveEcontacts(pd);
//				resultMap.put(AppConstant.RESULT, list);
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
	 * 修改联系人
	 * @return
	 */
	@RequestMapping("/updateEcontacts")
	@ResponseBody
	public Map<String, Object> updateEcontacts(HttpServletRequest request,HttpServletResponse response) {
		    response.setHeader("Access-Control-Allow-Origin", "*");
			Map<String, Object> resultMap = AppUtil.getResultMap();
			try {
				PageData pd= this.getPageData();
				String user_id = pd.getString("user_id");//用户id
				String user_name = pd.getString("con_name");//联系人名字
				String phone = pd.getString("con_phone");//联系人电话
				String con_num = pd.getString("con_num");//联系人身份证
				String contacts_id = pd.getString("contacts_id");//联系人id
				if (Tools.isEmpty(user_id)||Tools.isEmpty(contacts_id)||Tools.isEmpty(user_name)||Tools.isEmpty(phone)||Tools.isEmpty(con_num)) {
					resultMap.put("status", AppConstant.ERR_CODE_1003);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
					return resultMap;
				}else {
					appUserRegisterService.updateEcontacts(pd);
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
	 * 删除联系人
	 * @return
	 */
	@RequestMapping("/deleteEcontacts")
	@ResponseBody
	public Map<String, Object> deleteEcontacts(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd= this.getPageData();
			String contacts_id = pd.getString("contacts_id");//联系人id
			if (Tools.isEmpty(contacts_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				appUserRegisterService.deleteEcontacts(pd);
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
	 * 获取司机列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getSysUserDriverList")
	@ResponseBody
	public Map<String, Object> getSysUserDriverList(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd= this.getPageData();
			String user_id = pd.getString("user_id");//用户
			String dealer_id = pd.getString("dealer_id");//承运商id
			if (Tools.isEmpty(user_id)||Tools.isEmpty(dealer_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				List<PageData> list = appUserRegisterService.getSysUserDriverList(pd);
				resultMap.put(AppConstant.RESULT, list);
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
	 * 绑定司机
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/bindDriver")
	@ResponseBody
	public Map<String, Object> bindDriver(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd= this.getPageData();
			String user_id = pd.getString("user_id");//当前登陆用户id
			String bind_user_id = pd.getString("bind_user_id");//要绑定的司机user_id
			if (Tools.isEmpty(bind_user_id)||Tools.isEmpty(user_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				Boolean flag = appUserRegisterService.getEuserVehicleListByUserId(pd);
				if(!flag){
					resultMap.put("status", AppConstant.ERR_CODE_1019);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1019));
					return resultMap;
				}
				PageData bindDriver = appUserRegisterService.bindDriver(pd);
				return bindDriver;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
//		return AppUtil.returnMap(resultMap);
	}
	/**
	 * 解绑司机
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/unbindDriver")
	@ResponseBody
	public Map<String, Object> unbindDriver(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd= this.getPageData();
			String unbind_user_id = pd.getString("unbind_user_id");//要取消绑定的司机user_id
			if (Tools.isEmpty(unbind_user_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				 appUserRegisterService.unbindDriver(pd);
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
	 * 获取图片
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getPicture")
	@ResponseBody
	public Map<String, Object> getPicture(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd= this.getPageData();
			String use_type = pd.getString("use_type");//使用类型
			String user_detail_type = pd.getString("user_detail_type");//使用详细类型
			String object_id = pd.getString("object_id");//业务主键
			if (Tools.isEmpty(use_type)||Tools.isEmpty(user_detail_type)||Tools.isEmpty(object_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				PageData data = appUserRegisterService.getPictureByInfo(pd);
				resultMap.put(AppConstant.RESULT, data);
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
	 * 获取图片列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getPicturelist")
	@ResponseBody
	public Map<String, Object> getPicturelist(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd= this.getPageData();
			String use_type = pd.getString("use_type");//使用类型
//			String user_detail_type = pd.getString("user_detail_type");//使用详细类型
			String object_id = pd.getString("object_id");//业务主键
			if (Tools.isEmpty(use_type)||Tools.isEmpty(object_id)) {
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				List<PageData> data = appUserRegisterService.getPictureByInfoList(pd);
				resultMap.put(AppConstant.RESULT, data);
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
     * 头像上传
     * user_id
     * @param request
     * @param response
     * @return
     */
	@RequestMapping("/uploadFileHeadPicture")
	@ResponseBody
    public Map<String, Object> uploadFileHeadPicture(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		String user_id = request.getParameter("user_id");//用户id
		if(Tools.isEmpty(user_id)){
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
							Map<String, Object> mp = appUserRegisterService.uploadFileHeadPicture(cf,request);
                            return mp;
						} catch (Exception e) {
							e.printStackTrace();
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
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updatePassword")
	@ResponseBody
	public Map<String, Object> updatePassword(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String user_id = pd.getString("user_id");//用户id
			String password = pd.getString("password");//密码
			String phone = pd.getString("phone");//电话
			String ver_code = pd.getString("ver_code");//验证码
			if(Tools.isEmpty(password)||Tools.isEmpty(user_id)||Tools.isEmpty(phone)||Tools.isEmpty(ver_code)){
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				pd.put("verify_date", 10);
				PageData data= appUserRegisterService.getEverCodeByPhoneAndVercodeType(pd);
				if(null==data||data.size()<=0){
					resultMap.put("status", AppConstant.ERR_CODE_1009);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1009));
					return resultMap;
				}else {
					appUserRegisterService.updatePassword(pd);
				}
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
	 * 忘记密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/forgetPassword")
	@ResponseBody
	public Map<String, Object> forgetPassword(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String password = pd.getString("password");//密码
			String phone = pd.getString("phone");//电话
			String ver_code = pd.getString("ver_code");//验证码
			if(Tools.isEmpty(password)||Tools.isEmpty(phone)||Tools.isEmpty(ver_code)){
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				pd.put("verify_date", 10);
				PageData data= appUserRegisterService.getEverCodeByPhoneAndVercodeType(pd);
				if(null==data||data.size()<=0){
					resultMap.put("status", AppConstant.ERR_CODE_1009);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1009));
					return resultMap;
				}else {
					appUserRegisterService.updatePasswordByPhoneNum(pd);
				}
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
	 * 司机身份认证
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/driverAuthentication")
	@ResponseBody
	public Map<String, Object> driverAuthentication(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String user_id = pd.getString("user_id");//用户id
			String idcard_num = pd.getString("idcard_num");//身份证
			String file_id = pd.getString("file_id");//文件id
			String dealer_id = pd.getString("dealer_id");//经销商id  不是必选
			if(Tools.isEmpty(user_id)||Tools.isEmpty(file_id)||Tools.isEmpty(idcard_num)){
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				appUserRegisterService.driverAuthentication(pd);
				PageData user = appUserRegisterService.getSysUserByUserId(pd);
				resultMap.put(AppConstant.RESULT, user);
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
	 * 车辆身份认证
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/carAuthentication")
	@ResponseBody
	public Map<String, Object> carAuthentication(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String plate_num = pd.getString("plate_num");//车牌号
			String file_id = pd.getString("file_id");//文件id
			String user_id = pd.getString("user_id");//用户id
			if(Tools.isEmpty(plate_num)||Tools.isEmpty(file_id)||Tools.isEmpty(user_id)){
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				
				appUserRegisterService.carAuthentication(pd);
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
	 * 营业执照认证（暂不用）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/licenseAuthentication")
	@ResponseBody
	public Map<String, Object> licenseAuthentication(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String dealer_name = pd.getString("dealer_name");
			String file_id = pd.getString("file_id");
			String user_id = pd.getString("user_id");
			if(Tools.isEmpty(dealer_name)||Tools.isEmpty(file_id)||Tools.isEmpty(user_id)){
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				PageData data = appUserRegisterService.getBaseDealerByDealerName(pd);
				if(null!=data &&data.size()>0){
					resultMap.put("status", AppConstant.ERR_CODE_1008);
					resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1008));
				}else {
					appUserRegisterService.licenseAuthentication(pd);
				}
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
	 * 根据user_id查询绑定用户
	 */
	@RequestMapping("/getbindUserByUserId")
	@ResponseBody
	public Map<String, Object> getbindUserByUserId(HttpServletRequest request,HttpServletResponse response) {
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
				 List<PageData> data = appUserRegisterService.getbindUserByUserId(pd);
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
	 * 获取用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getSysUserMessageByUserId")
	@ResponseBody
	public Map<String, Object> getSysUserMessageByUserId(HttpServletRequest request,HttpServletResponse response) {
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
				 PageData data = appUserRegisterService.getSysUserMessageByUserId(pd);
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
	 * 获取用户的信息(认证信息判断)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getSysUserAuthenticationByUserId")
	@ResponseBody
	public Map<String, Object> getSysUserAuthenticationByUserId(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String user_id = Tools.checkObj(pd.get("user_id"));//用户id
			String child_id = Tools.checkObj(pd.get("child_id")); //子账号用户id
			if(Tools.isEmpty(user_id) || Tools.isEmpty(child_id)){
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				PageData data = null;
				//查询子账号状态
				if (user_id.equals(child_id)) {
					data = appUserRegisterService.getSysUserAuthenticationByUserId(pd);
				} else {
					data= appUserRegisterService.getSysUserAuthenticationChildByUserId(pd);
				}
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
	 * 获取用户的认证图片（user_id可能是车辆的id可能是用户id）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getFileUrlByUserId")
	@ResponseBody
	public Map<String, Object> getFileUrlByUserId(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = AppUtil.getResultMap();
		try {
			PageData pd = this.getPageData();
			String user_id = pd.getString("user_id");//用户id 或者车辆id
			logger.info("user_id:============="+user_id);
			if(Tools.isEmpty(user_id)){
				logger.info("");
				resultMap.put("status", AppConstant.ERR_CODE_1003);
				resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1003));
				return resultMap;
			}else {
				 pd.put("object_id", user_id);
				 List<PageData> data = appUserRegisterService.getFileUrlByObjectId(pd);
				 logger.info("获取到的结果集===="+data);
				 resultMap.put(AppConstant.RESULT, data);
				 logger.info("获取到的结果集==resultMap=="+resultMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", AppConstant.ERR_CODE_1005);
			resultMap.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1005));
			return resultMap;
		}
		return resultMap;
	}
	
}
