package com.fh.service.app.register;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.service.app.appcommon.AppCommonService;
import com.fh.service.app.order.AppOrderService;
import com.fh.service.common.CommonService;
import com.fh.util.AppConstant;
import com.fh.util.AppUtil;
import com.fh.util.PageData;
import com.fh.util.PrimaryUtil;
import com.fh.util.SmsUtil;
import com.fh.util.SysDataCode;
import com.fh.util.Tools;
import com.fh.util.oss.OSSUploadUtil;


@Service("appUserRegisterService")
public class AppUserRegisterService {
	
	@Resource(name = "daoSupport")
    private DaoSupport dao;
	
	@Resource(name="appOrderService")
	private  AppOrderService  appOrderService;
	@Resource(name="appCommonService")
	private  AppCommonService  appCommonService;
	@Resource(name="commonService")
	private  CommonService  commonService;
	
    /**
     * 发送验证码
     * @param pd
     * @return
     * @throws Exception 
     */
	public String saveSendSmsCode(PageData pd) throws Exception {
		int random4Num = Tools.getRandom4Num();
//		List<PageData>  list = this.getUserListByPhone(pd);
//		if(CollectionUtils.isNotEmpty(list)&&list.size()>0){
//			return AppConstant.ERR_CODE_1007 ;
//		}else{
//			
//		}
		//保存手机验证码
		pd.put("VERIFY_CODE", random4Num);
		pd.put("VERIFY_STATUS", SysDataCode.ISVALID_YES);
		pd = this.saveSendSmsCodeByPhone(pd);
		
		PageData data = new PageData();
		data.put("ver_code", String.valueOf(random4Num));
		//发送验证码
		SmsUtil.SendSMS(pd.getString("sms_type"), data, pd.getString("phone"), "");
		return AppConstant.SUCCESS;
	}
	
	/**
	 * 保存
	 * @param pd
	 * @throws Exception 
	 */
	public PageData saveSendSmsCodeByPhone(PageData pd) throws Exception {
		long pk_id = PrimaryUtil.getPrimary();
		pd.put("VER_ID", pk_id);
		dao.save("AppUserRegisterMapper.saveSendSmsCodeByPhone", pd);
		return pd;
	}
	@SuppressWarnings("unchecked")
	public List<PageData> getUserListByPhone(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppUserRegisterMapper.getUserListByPhone", pd);
	}
    /**
     * 保存用户信息
     * @param pd
     * @return
     * @throws Exception 
     */
	public PageData saveUserToSysUser(PageData pd) throws Exception {
		/******************保存用户信息**********************/
		long primary = PrimaryUtil.getPrimary();
		pd.put("user_id", primary);
		String password = pd.getString("password");
		String phone = pd.getString("phone");
		String passwd = new SimpleHash("SHA-1", phone, password).toString();
		pd.put("passwd", passwd);
		pd.put("audit_status", SysDataCode.EXAMINE_STATUS_04);
		pd.put("status", SysDataCode.ISVALID_YES);
		dao.save("AppUserRegisterMapper.saveUserToSysUser", pd);
		/******************保存用户信息**********************/
		
		PageData post =  this.getSysPostByDealerId(pd);
		//保存职位
		PageData sysuerpost = new PageData();
		sysuerpost.put("user_post_id", PrimaryUtil.getPrimary());
		sysuerpost.put("user_id", primary);
		sysuerpost.put("post_id",post.get("POST_ID") );
//		sysuerpost.put("create_by",primary );
		this.saveSysUserPost(sysuerpost);

		/***********************保存车辆信息start***************************/
		this.saveVehicle(pd);
		/***********************保存车辆信息 end ***************************/
		this.updateEverCodeStatus(pd);
		PageData data = this.getSysUserByUserId(pd);
		data.put("PLATE_NUM", pd.get("plate_num"));
		data.put("CAR_NUM", pd.get("car_num"));
		data.put("VEHICLE_ID", pd.get("vehicle_id"));
		this.updateEverCodeStatus(pd);
		return data;
	}
	
	/**
	 * 保存职位用户关系
	 * @param sysuerpost
	 * @throws Exception 
	 */
	public void saveSysUserPost(PageData sysuerpost) throws Exception {
		dao.save("AppUserRegisterMapper.saveSysUserPost", sysuerpost);
	}

	/**
	 * 根据dealer_id 查询sys_post
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	public PageData getSysPostByDealerId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppUserRegisterMapper.getSysPostByDealerId", pd);
	}

	/**用户id查询用户
	 * user_id
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getSysUserByUserId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppUserRegisterMapper.getSysUserByUserId", pd);
	}

	/**
	 * 修改验证码状态
	 * @param pd
	 * phone,
	 * ver_code
	 * @throws Exception 
	 */
     public void updateEverCodeStatus(PageData pd) throws Exception {
		 dao.update("AppUserRegisterMapper.updateEverCodeStatus", pd);
	}

	/**
      * 保存车辆信息
      * @param pd
     * @throws Exception 
      */
	public void saveVehicle(PageData pd) throws Exception {
		PageData vehicle = this.getVehicleByVin(pd);
		if(null!=vehicle&&vehicle.containsKey("VEHICLE_ID")){
			String VEHICLE_ID = String.valueOf(vehicle.get("VEHICLE_ID"));
			pd.put("vehicle_id", Long.valueOf(VEHICLE_ID));
			this.saveEUserVehicle(pd);
		}else {
			long vehicle_id = PrimaryUtil.getPrimary();
			pd.put("vehicle_id", vehicle_id);
			pd.put("status", SysDataCode.ISVALID_YES);
			pd.put("create_by", pd.get("user_id"));
			pd.put("car_num", Long.valueOf(pd.getString("car_num")));
			dao.save("AppUserRegisterMapper.saveVehicle", pd);
			this.saveEUserVehicle(pd);
		}
		
		
	}
	/**
	 * 根据vin查询车辆信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getVehicleByVin(PageData pd ) throws Exception {
		PageData data = (PageData) dao.findForObject("AppUserRegisterMapper.getVehicleByVin", pd);
		return data ;
	}
	
	/**
	 * 保存车辆跟司机关系
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData saveEUserVehicle(PageData pd ) throws Exception {
		long primary = PrimaryUtil.getPrimary();
		pd.put("relation_id",primary);
		dao.save("AppUserRegisterMapper.saveEUserVehicle", pd);
		return pd ;
	}
    /**
     * 根据登陆信息查询用户数据(密码登陆)
     * @param pd
     * @return
     * @throws Exception 
     */
	public PageData getUserByMessageInfoByPassword(PageData pd) throws Exception {
		PageData pageData = new PageData();
		String password = pd.getString("password");
		String user_name = pd.getString("user_name");
//		String phone = pd.getString("phone");
		String passwd = new SimpleHash("SHA-1", user_name, password).toString();
		pd.put("passwd", passwd);
		PageData data =  (PageData) dao.findForObject("AppUserRegisterMapper.getUserByMessageInfoByPassword", pd);
		if(null==data||data.size()<=0){//用户不存在
			pageData.put("status", AppConstant.ERR_CODE_1011);
			pageData.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1011));
			pageData.put("result", "");
			return pageData;
		}
		if(passwd.equals(data.getString("PASSWORD"))){
			pageData.put("status", "1000"); 
			pageData.put("msg", AppUtil.getMessageInfoByKey("1000"));
			//如果是司机的获取司机的车牌号  车位数
			if(String.valueOf(SysDataCode.USR_TYPE_05).equals(String.valueOf(data.get("USER_TYPE")))
					||String.valueOf(SysDataCode.USR_TYPE_03).equals(String.valueOf(data.get("USER_TYPE")))){
				PageData da =  new PageData();
				da.put("user_id", data.get("USER_ID"));
				PageData vehicle = this.getEvehicleByUserId(da);
				if(null!=vehicle &&vehicle.size()>0){
					data.put("PLATE_NUM", vehicle.get("PLATE_NUM"));
					data.put("CAR_NUM", vehicle.get("CAR_NUM"));
					data.put("VEHICLE_ID", vehicle.get("VEHICLE_ID"));
				}
				String user_id = String.valueOf(data.get("USER_ID"));
				String IS_MAIN_COUNT = String.valueOf(data.get("IS_MAIN_COUNT"));
				if(String.valueOf(SysDataCode.STATUS_TYPE_02).equals(IS_MAIN_COUNT)){
					PageData mainCount = this.getMainCountByChildCount(data);
					if(null!=mainCount&&mainCount.size()>0){
						data.put("USER_ID",mainCount.get("USER_ID"));//主账号
						data.put("CHILD_USER_ID", user_id);//子账号
					}else {
						pageData.put("status", AppConstant.ERR_CODE_1024);
						pageData.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1024));
						pageData.put("result", "");
						return pageData;
					}
				}else {//如果两个一致的说明为主账号
					data.put("CHILD_USER_ID", user_id);//子账号
				}
			}
			pageData.put("result", data);
			return pageData;
		}else {
			pageData.put("status", AppConstant.ERR_CODE_1012);
			pageData.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1012));
			pageData.put("result", "");
			return pageData;
		}
	}
	/**
	 * 根据子账号id查询主账号
	 * @param data
	 * @return
	 * @throws Exception 
	 */
	public PageData getMainCountByChildCount(PageData data) throws Exception {
		return (PageData) dao.findForObject("AppUserRegisterMapper.getMainCountByChildCount", data);
	}

	/**
	 * 获取车辆信息
	 * @param da
	 * @return
	 * @throws Exception 
	 */
    public PageData getEvehicleByUserId(PageData da) throws Exception {
		return (PageData) dao.findForObject("AppUserRegisterMapper.getEvehicleByUserId", da);
	}

	/**
     * 短信登陆
     * @param pd
     * @return
     * @throws Exception
     */
	public PageData getUserByMessageInfoByVerCode(PageData pd) throws Exception {
		PageData pageData = new PageData();
		PageData data =  (PageData) dao.findForObject("AppUserRegisterMapper.getUserByMessageInfoByVerCode", pd);
		if(null==data||data.size()<=0){//用户不存在的情况
			return null;
		}
		PageData data2 =  new PageData();
		data2.put("ver_code", pd.getString("over_code"));
		data2.put("phone", pd.getString("user_name"));
		this.updateEverCodeStatus(data2);
		pageData.put("status", "1000"); 
		pageData.put("msg", AppUtil.getMessageInfoByKey("1000"));
		//如果是司机的获取司机的车牌号  车位数
		if(String.valueOf(SysDataCode.USR_TYPE_05).equals(String.valueOf(data.get("USER_TYPE")))
				||String.valueOf(SysDataCode.USR_TYPE_03).equals(String.valueOf(data.get("USER_TYPE")))){
			PageData da =  new PageData();
			da.put("user_id", data.get("USER_ID"));
			PageData vehicle = this.getEvehicleByUserId(da);
			if(null!=vehicle &&vehicle.size()>0){
				data.put("PLATE_NUM", vehicle.get("PLATE_NUM"));
				data.put("CAR_NUM", vehicle.get("CAR_NUM"));
				data.put("VEHICLE_ID", vehicle.get("VEHICLE_ID"));
			}
			String user_id = String.valueOf(data.get("USER_ID"));
			String IS_MAIN_COUNT = String.valueOf(data.get("IS_MAIN_COUNT"));
			if(String.valueOf(SysDataCode.STATUS_TYPE_02).equals(IS_MAIN_COUNT)){
				PageData mainCount = this.getMainCountByChildCount(data);
				if(null!=mainCount&&mainCount.size()>0){
					data.put("USER_ID",mainCount.get("USER_ID"));//主账号
					data.put("CHILD_USER_ID", user_id);//子账号
				}else {
					pageData.put("status", AppConstant.ERR_CODE_1024);
					pageData.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1024));
					pageData.put("result", "");
					return pageData;
				}
			}else {//如果两个一致的说明为主账号
				data.put("CHILD_USER_ID", user_id);//子账号
			}
		}
		pageData.put("result", data);
		
		return pageData;
	}
  /**
   * 司机验证车辆
   * @param pd
 * @throws Exception 
   */
	public void driverSubmitVerify(PageData pd) throws Exception {
		PageData data = (PageData) dao.findForObject("AppUserRegisterMapper.getEvehicleByCarNum", pd);
		if(null!=data&&data.size()>0){
			pd.put("vehicle_id", data.get("VEHICLE_ID"));
			dao.update("AppUserRegisterMapper.updateEvehicleByVehicleId", pd);
		}
		
	}
	/**
	 * 货主验证
	 * @param pd
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> ownerSubmitVerify(PageData pd) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		//查询用户信息
		PageData user = this.getSysUserByUserId(pd);
		if(!user.containsKey("DEALER_ID")){//不存在经销商的情况
			//根据服务站名称   电话  查询用户
			user.put("dealer_name", pd.get("dealer_name"));
			PageData data = (PageData) dao.findForObject("AppUserRegisterMapper.getSysUserByDealerNameAndPhone", user);
			if(null!= data&&data.size()>0){
				map.put("status", AppConstant.ERR_CODE_1008);
				map.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1008));
				dao.update("AppUserRegisterMapper.updateSysUserByPhone", pd);
			}else{
				List<PageData>  list  =  (List<PageData>) dao.findForList("AppUserRegisterMapper.getBaseDealerByDealerName",pd );
				if (CollectionUtils.isEmpty(list)&&list.size()<=0) {//经销商不存在
					PageData data2 = new PageData();
					data2.put("user_id", Long.valueOf(pd.getString("user_id")));
					data2.put("dealer_name", pd.get("dealer_name"));
					data2.put("file_id", pd.get("file_id"));
					data2.put("dealer_type", pd.get("dealer_type"));
					//保存经销商信息
					PageData Dealerpd = this.saveDealerMessage(data2);
					//建立职位跟用户的关系
					PageData data3 = new PageData();
					data3.put("post_id", Dealerpd.get("post_id"));
					data3.put("user_id", Long.valueOf(pd.getString("user_id")));
					data3.put("user_post_id", PrimaryUtil.getPrimary());
					dao.save("AppUserRegisterMapper.saveSysUserPost", data3);
					//修改用户表状态
					PageData data4 = new PageData();
					data4.put("user_id", pd.getString("user_id"));
					data4.put("audit_status", SysDataCode.EXAMINE_STATUS_01);
					this.updateSysUserStatusByUserId(data4);
					PageData data5 = new PageData();
					data5.put("object_id",Dealerpd.get("dealer_id") );
					String fileIds = Tools.checkObj(pd.get("file_id"));
					String[] file_id = fileIds.split(",");
					data5.put("file_ids",file_id);
					//将上传的图片写上业务ID
					this.updateEfilesByFileId(data5);
				}else{
					//经销商存在   账户不存在====这种情况现在不存在
					map.put("status", AppConstant.ERR_CODE_1008);
					map.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1008));
				}
			}
		}else {
			String dealer_id = String.valueOf(user.get("DEALER_ID"));
			PageData  data = new PageData();
			data.put("object_id",dealer_id);
			data.put("file_status", SysDataCode.ISVALID_NO);
			// this.updatEfilesStatusByObjectId(data);// 修改之前的为无效,原来只有一张，现在是多张
			String fileId = Tools.checkObj(pd.get("file_id"));
			if (!Tools.isEmpty(fileId)) {
				data.put("file_ids", fileId.split(","));
				this.updateEfilesByFileId(data);//绑定新的附件
			}
			//修改用户表状态
			PageData data4 = new PageData();
			data4.put("user_id", pd.getString("user_id"));
			data4.put("audit_status", SysDataCode.EXAMINE_STATUS_01);
			this.updateSysUserStatusByUserId(data4);
		}
		return map ; 
	}
	/**
	 * 修改附件无效
	 * @param data
	 * @throws Exception 
	 */
	public void updatEfilesStatusByObjectId(PageData data) throws Exception {
		  dao.update("AppUserRegisterMapper.updatEfilesStatusByObjectId", data);
	}

	/**
	 * 修改附件状态
	 * @param pd
	 * @throws Exception
	 */
	public void updateEfilesByFileId(PageData pd) throws Exception {
		 dao.update("AppUserRegisterMapper.updateEfilesByFileId", pd);
	}

	/**
	 * 修改用户表
	 * user_id
	 * audit_status
	 * @param data4
	 * @throws Exception 
	 */
    public void updateSysUserStatusByUserId(PageData data4) throws Exception {
		dao.update("AppUserRegisterMapper.updateSysUserStatusByUserId",data4);
	}

	/**
     * 保存经销商信息
     * @param pd
     * @throws Exception 
     */
	@SuppressWarnings("unchecked")
	public PageData saveDealerMessage(PageData pd) throws Exception {
		//查询组织机构
    	Map<String,Object>   orgId =  (Map<String,Object>)dao.findForObject("DEALERMapper.getBaseOrgId", null);
    	long dealer_id = PrimaryUtil.getPrimary();
    	pd.put("ORG_ID", orgId.get("ORG_ID"));
    	pd.put("DEALER_ID", dealer_id);
    	pd.put("DEALER_NAME", pd.getString("dealer_name"));
    	pd.put("STATUS", SysDataCode.ISVALID_NO);
    	pd.put("CREATE_BY",pd.get("user_id"));
    	pd.put("CREATE_DATE",new Date());
    	pd.put("DEALER_FLAG", SysDataCode.EXAMINE_STATUS_01);
    	String dealerType= Tools.checkObj(pd.get("dealer_type"));
    	if (Tools.isEmpty(dealerType)) {
    		pd.put("DEALER_TYPE", SysDataCode.DEALER_TYPE_03);
    	} else {
    		pd.put("DEALER_TYPE", dealerType);
    	}
        dao.save("DEALERMapper.insertDealerMsg", pd);
        //默认创建一个职位
        PageData post = new PageData();
        Long postId = PrimaryUtil.getPrimary();
        post.put("postId", postId);
        post.put("postName", pd.get("DEALER_NAME")+"职位");
        post.put("postType", SysDataCode.ORG_TYPE_DEALER);
        post.put("postStatus", SysDataCode.ISVALID_YES);
        post.put("postOrgId", pd.get("DEALER_ID"));
        post.put("createBy", pd.get("user_id"));
        dao.save("DEALERMapper.insertPostMsg", post);
        
        //默认创建职位与角色的关联
        PageData postRole = new PageData();
        Long postRoleId = PrimaryUtil.getPrimary();
        postRole.put("postRoleId", postRoleId);
        postRole.put("postId", postId);
        postRole.put("createBy", pd.get("user_id"));
        dao.save("DEALERMapper.insertPostRoleMsgByOwner", postRole);
        PageData obj = new PageData();
        obj.put("dealer_id", pd.get("DEALER_ID"));
        obj.put("user_id", pd.get("user_id"));
        dao.update("AppUserRegisterMapper.updateSysUserDealerByUserId", obj);
        PageData data =  new PageData();
        data.put("dealer_id", dealer_id);
        data.put("post_id", postId);
        return data;
	}
	
    /**
     * 货主注册
     * @param pd
     * @throws Exception 
     */
	public PageData ownerUserRegister(PageData pd) throws Exception {
		/******************保存用户信息**********************/
		long primary = PrimaryUtil.getPrimary();
		pd.put("user_id", primary);
		String password = pd.getString("password");
		@SuppressWarnings("unused")
		String user_name = pd.getString("user_name");
		String phone = pd.getString("phone");
		String passwd = new SimpleHash("SHA-1", phone, password).toString();
		pd.put("passwd", passwd);
		pd.put("audit_status", SysDataCode.EXAMINE_STATUS_04);
		pd.put("status", SysDataCode.ISVALID_YES);//是否有效
		pd.put("is_main_count", SysDataCode.STATUS_TYPE_01);//是否主账号
		String user_type = pd.get("user_type")+"";
		pd.put("user_type", Integer.valueOf(user_type));
		dao.save("AppUserRegisterMapper.saveUserToSysUser", pd);
		/******************保存用户信息**********************/
		this.updateEverCodeStatus(pd);
		PageData user = this.getSysUserByUserId(pd);
		return  user;
	}
	
    /**
     * 获取承运商
     * @param page
     * @return
     * @throws Exception 
     */
	@SuppressWarnings("unchecked")
	public List<PageData> getAllCarrier(Page page) throws Exception {
		return (List<PageData>) dao.findForList("AppUserRegisterMapper.getAllCarrierlistPage", page);
	}

	public PageData getEverCodeByPhoneAndVercode(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppUserRegisterMapper.getEverCodeByPhoneAndVercode", pd);
	}
	
	//根据dealerId查询公司类型
	public PageData getDealerMsgById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppUserRegisterMapper.getDealerMsgById", pd);
	}
	
    /**
     * 获取联系人列表
     * @param pd
     * @return
     * @throws Exception 
     */
	@SuppressWarnings("unchecked")
	public List<PageData> getEcontacts(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppUserRegisterMapper.getEcontacts", pd);
	}
    /**
     * 保存联系人
     * @param pd
     * @throws Exception 
     */
	public void saveEcontacts(PageData pd) throws Exception {
       pd.put("contacts_id", PrimaryUtil.getPrimary());
       PageData user = appOrderService.getSysUserByUserId(pd);
       if (null!= user &&user.containsKey("DEALER_ID")) {
    	   pd.put("dealer_id", user.get("DEALER_ID"));
	    }
       dao.save("AppUserRegisterMapper.saveEcontacts", pd);
	}
    /**
     * 修改联系人
     * @param pd
     * @throws Exception 
     */
	public void updateEcontacts(PageData pd) throws Exception {
		dao.save("AppUserRegisterMapper.updateEcontacts", pd);
	}
     /**
      * 删除联系人
      * @param pd
     * @throws Exception 
      */
	public void deleteEcontacts(PageData pd) throws Exception {
		 dao.delete("AppUserRegisterMapper.deleteEcontacts", pd);
	}
    /**
     * 获取司机列表
     * @param pd
     * @return
     * @throws Exception 
     */
	@SuppressWarnings("unchecked")
	public List<PageData> getSysUserDriverList(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppUserRegisterMapper.getSysUserDriverList", pd);
	}
    /**
     * 取消绑定司机
     * @param pd
     *参数   unbind_user_id
     * @throws Exception 
     */
	public void unbindDriver(PageData pd) throws Exception {
		 dao.delete("AppUserRegisterMapper.unbindDriver", pd);
	}
    /**
     * 绑定司机
     * @param pd
     * @throws Exception 
     */
	public PageData bindDriver(PageData pd) throws Exception {
        PageData  data  = this.getEuserVehicleByUserId(pd);	
        if(null==data||data.size()<=0){
        	data = new PageData();
        	data.put("status", AppConstant.ERR_CODE_1013);
        	data.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1013));
			return data;
        }
		
        pd.put("RELATION_ID", PrimaryUtil.getPrimary());
        pd.put("VEHICLE_ID", data.get("VEHICLE_ID"));
        pd.put("USER_ID", pd.getString("bind_user_id")) ;
        dao.save("AppUserRegisterMapper.bindDriver", pd);
        data = new PageData();
        data.put("status", "1000");
        data.put("msg", AppUtil.getMessageInfoByKey("1000"));
        data.put("result", "");
        return data;
	}

	public PageData getEuserVehicleByUserId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppUserRegisterMapper.getEuserVehicleByUserId", pd);
	}
    /**
     * 获取图片信息
     * @param pd
     * @return
     * user_type
     * user_detail_type
     * object_id
     * @throws Exception 
     */
	public PageData getPictureByInfo(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppUserRegisterMapper.getPictureByInfo", pd);
	}
    /**
     * 上传头像
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
	public Map<String, Object> uploadFileHeadPicture(CommonsMultipartFile file, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = AppUtil.getResultMap();
		String filename = file.getOriginalFilename();
		String fileType = filename.substring(filename.lastIndexOf("."),filename.length()); 
		/***************************压缩文件  start*********************************************/
		InputStream is = file.getInputStream();
		//图片大于150k，则进行尺寸大小不变的压缩
		if(fileType.toLowerCase().equals("tiff") ||fileType.toLowerCase().equals("jpeg") ||fileType.toLowerCase().equals("jpg") || fileType.toLowerCase().equals("png") || fileType.toLowerCase().equals("bmp") || fileType.toLowerCase().equals("gif") ){
			
			BufferedImage bi;
			bi = Thumbnails.of(is).scale(1).asBufferedImage();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		    ImageOutputStream imageOutput = ImageIO.createImageOutputStream(byteArrayOutputStream);
		    ImageIO.write(bi, fileType, imageOutput);
		    is = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			int filesize = is.available()/1024; //文件大小
			System.out.println("=======000===is="+is.available());
			if(filesize>1024*2 ){
				double sc = (double)((float)150 /(float)filesize );
				bi = Thumbnails.of(is).scale(1).outputQuality(sc).asBufferedImage();
				byteArrayOutputStream = new ByteArrayOutputStream();
			    imageOutput = ImageIO.createImageOutputStream(byteArrayOutputStream);
			    ImageIO.write(bi, fileType, imageOutput);
			    is = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			    System.out.println("==========is="+is.available());
			    filesize = is.available()/1024;
			    if(filesize>150){
			    	//图片大于2M，则进行尺寸大小的压缩，等比缩放
				    sc = (double)((float)1024*2 /(float)filesize );
					System.out.println("==========sc="+sc);
					bi = Thumbnails.of(is).scale(sc).asBufferedImage();
					byteArrayOutputStream = new ByteArrayOutputStream();
				    imageOutput = ImageIO.createImageOutputStream(byteArrayOutputStream);
				    ImageIO.write(bi, fileType, imageOutput);
				    is = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
				    System.out.println("=========11=is="+is.available());
			    }
			}
		}
		/***************************压缩文件  end*********************************************/
//		FileStore 	fileStore = FileStore.getInstance();
//		String fileName = fileStore.write(filename, is);
//		String path = fileStore.getDomainURL(fileName);//文件存储的绝对路径
		String filepath = OSSUploadUtil.uploadFile(file);
		String user_id = request.getParameter("user_id");
		long file_id = PrimaryUtil.getPrimary();
		PageData data = new PageData();
		filename = filename.substring(0,filename.lastIndexOf(".")-1);
		data.put("file_name", filename);
		data.put("file_path", filepath);
		data.put("file_id", file_id);
		data.put("user_id", user_id);
		data.put("use_detail_type", SysDataCode.FILE_DETAIL_TYPE_01);
//		data.put("file_size", is.);
		data.put("file_type", SysDataCode.FILE_TYPE_01);
		data.put("use_type", SysDataCode.FILE_USE_TYPE_06);
		//先将所有用户头像改为无效
		data.put("file_status", SysDataCode.ISVALID_NO);
		dao.update("AppCommonMapper.updateEfilesStatusByUserId", data);
//		dao.update("AppCommonMapper.updateEfilesStatusByfileId", data);
        data.remove("file_status");
        data.put("object_id", user_id);
		dao.save("AppCommonMapper.saveEfiles", data);
//		resultMap.put("file_url", path);
		resultMap.put(AppConstant.RESULT, filepath);
		return resultMap;
	}
    /**
     * 添加司机信息
     * @param pd
     * @throws Exception 
     */
	public Map<String, Object> addDriverUserRegister(PageData pd) throws Exception {
		Map<String, Object> resultMap = AppUtil.getResultMap();
		PageData user = this.getSysUserByUserId(pd);
		/******************保存用户信息**********************/
		PageData data = new PageData();
		long primary = PrimaryUtil.getPrimary();//子账号的userID
		data.put("user_id", primary);
		String password = pd.getString("password");
		String user_name = pd.getString("user_name");
		String phone = pd.getString("phone");
		String passwd = new SimpleHash("SHA-1", phone, password).toString();
		String car_no = pd.getString("car_no");//身份证
		data.put("passwd", passwd);
		data.put("car_no", car_no);
		data.put("user_name", user_name);
		data.put("dealer_id", user.get("DEALER_ID"));
		data.put("audit_status", SysDataCode.EXAMINE_STATUS_01);
		data.put("status", SysDataCode.ISVALID_YES);
		data.put("phone", phone);
		data.put("user_type", SysDataCode.USR_TYPE_03);
		data.put("is_main_count", SysDataCode.STATUS_TYPE_02);
		dao.save("AppUserRegisterMapper.saveUserToSysUser", data);
		/******************保存用户信息**********************/
		//保存职位关系
		PageData post = this.getSysUserPostByUserId(user);
		PageData data2 =  new PageData();
		data2.put("post_id", post.get("POST_ID"));
		data2.put("user_id", primary);
		data2.put("user_post_id", PrimaryUtil.getPrimary());
		dao.save("AppUserRegisterMapper.saveSysUserPost", data2);
		//车辆关系
		PageData vehicle = this.getEuserVehicleByUserId(pd);
		PageData data3 = new PageData();
		data3.put("vehicle_id", vehicle.get("VEHICLE_ID"));
//		data3.put("relation_id", PrimaryUtil.getPrimary());
		data3.put("user_id",primary);
//		dao.save("AppUserRegisterMapper", data3);
		this.saveEUserVehicle(data3);
		//修改附件
		String fileids = pd.getString("file_id");
		String[] file_ids = fileids.split(",");
		pd.put("file_ids", file_ids);
		pd.put("object_id", primary);
		dao.update("AppUserRegisterMapper.updateObjectIdbyFileId", pd);
		//发送短信
		PageData paramPd =new PageData();
		paramPd.put("send_user_id", pd.getString("user_id"));
		paramPd.put("phone", user.getString("USER_NAME"));
		//被绑定的用户发送短信
		appCommonService.sendSmsMessage("SMS_40009", paramPd, phone, "");
		//登陆的用户发送(极光)
		/*String contents = SmsUtil.getMessageInfoByKey("SMS_40008");
		PageData msgPd = new PageData();
		msgPd.put("msgTitle", "子账号添加");
		msgPd.put("msgContent", contents);
		msgPd.put("receiveUserId", String.valueOf(user.get("USER_ID")));
		msgPd.put("receiveUserName", String.valueOf(user.get("NAME")));
		this.commonService.sendMessageByJpushAlisaByDriver(String.valueOf(user.get("USER_ID")), msgPd);*/
		return resultMap;
	}
	/**
	 * 根据user_id获取职位信息
	 * @param user
	 * @return
	 * @throws Exception 
	 */
    public PageData getSysUserPostByUserId(PageData user) throws Exception {
		return (PageData) dao.findForObject("AppUserRegisterMapper.getSysUserPostByUserId", user);
	}

	/**
     * 修改密码
     * @param pd
     * @throws Exception 
     */
	public void updatePassword(PageData pd) throws Exception {
		String password = pd.getString("password");
		String phone = pd.getString("phone");
		String passwd = new SimpleHash("SHA-1", phone, password).toString();
		pd.put("password", passwd);
		dao.update("AppUserRegisterMapper.updatePassword", pd);
		this.updateEverCodeStatus(pd);
	}
    /**
     * 司机身份认证
     * @param pd
     * @throws Exception 
     */
	public void driverAuthentication(PageData pd) throws Exception {
		String fileids = pd.getString("file_id");
		String[] file_ids = fileids.split(",");
		pd.put("file_ids", file_ids);
		pd.put("object_id", pd.getString("user_id"));
		pd.put("file_status", SysDataCode.ISVALID_NO);
		//之前的修改为无效
		dao.update("AppUserRegisterMapper.updateDriverAuthentication", pd);
        pd.put("file_status", SysDataCode.ISVALID_YES);
		//绑定关系
        dao.update("AppUserRegisterMapper.driverAuthentication", pd);
        //修改用户表
        pd.put("audit_status", SysDataCode.EXAMINE_STATUS_04);
        dao.update("AppUserRegisterMapper.updateSysUserStatusByUserId", pd);
	}
    /**
     * 车辆信息认证
     * @param pd
     * @throws Exception 
     */
	public void carAuthentication(PageData pd) throws Exception {
		String fileids = pd.getString("file_id");
		String[] file_ids = fileids.split(",");
		pd.put("file_ids", file_ids);
		PageData data = this.getEVehicleByPlateNum(pd);
		pd.put("object_id", data.get("VEHICLE_ID"));
		//之前的修改为无效
		pd.put("file_status", SysDataCode.ISVALID_NO);
		dao.update("AppUserRegisterMapper.updateDriverAuthentication", pd);
		//绑定关系
		pd.put("file_status", SysDataCode.ISVALID_YES);
        dao.update("AppUserRegisterMapper.driverAuthentication", pd);
        //修改用户表去审核
        pd.put("audit_status", SysDataCode.EXAMINE_STATUS_01);
        dao.update("AppUserRegisterMapper.updateSysUserStatusByUserId", pd);
	}
	/**
	 * 根据车牌号查询车辆信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
    private PageData getEVehicleByPlateNum(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppUserRegisterMapper.getEVehicleByPlateNum", pd);
	}

	/**
     * 验证营业执照
     * @param pd
     */
	public void licenseAuthentication(PageData pd) {
		PageData  obj = new PageData();
		obj.put("dealer_id", PrimaryUtil.getPrimary());
//		dao.save("", obj)
	}
    /**
     * 根据经销商名字，查询经销商
     * @param pd
     * @return
     * @throws Exception 
     */
	public PageData getBaseDealerByDealerName(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppUserRegisterMapper.getBaseDealerByDealerName", pd);
	}
    /**
     * 根据验证码  电话   时间查询验证信息
     * @param pd
     * verify_date
     * ver_code
     * phone
     * @return
     * @throws Exception 
     */
	public PageData getEverCodeByPhoneAndVercodeType(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppUserRegisterMapper.getEverCodeByPhoneAndVercodeType", pd);
	}
    /**
     * 根据电话查询用户表中是否注册过该账号
     * @param pd
     * @return
     * @throws Exception 
     */
	public PageData getSysUserByPhone(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppUserRegisterMapper.getSysUserByPhone", pd);
	}
    /**
     * 根据电话号码修改密码 
     * @param pd
     * @throws Exception 
     */
	public void updatePasswordByPhoneNum(PageData pd) throws Exception {
		String password = pd.getString("password");
		String phone = pd.getString("phone");
		String passwd = new SimpleHash("SHA-1", phone, password).toString();
		pd.put("password", passwd);
		dao.update("AppUserRegisterMapper.updatePasswordByPhoneNum", pd);
		this.updateEverCodeStatus(pd);
		
	}
    /**
     * 根据用户id查询绑定用户信息
     * @param pd
     * @return
     * @throws Exception 
     */
	@SuppressWarnings("unchecked")
	public List<PageData> getbindUserByUserId(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppUserRegisterMapper.getbindUserByUserId", pd);
	}
   /**
    * 根据用户id查询用户信息
    * @param pd
    * @return
 * @throws Exception 
    */
	public PageData getSysUserMessageByUserId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppUserRegisterMapper.getSysUserMessageByUserId", pd);
	}
/**
 * 获取图片列表
 * @param pd
 * @return
 * @throws Exception 
 */
@SuppressWarnings("unchecked")
public List<PageData> getPictureByInfoList(PageData pd) throws Exception {
	return (List<PageData>) dao.findForList("AppUserRegisterMapper.getPictureByInfoList", pd);
}
/**
 * 根据user_id 获取用户
 * @param pd
 * @return
 * @throws Exception 
 */
public PageData getSysUserAuthenticationByUserId(PageData pd) throws Exception {
	return (PageData) dao.findForObject("AppUserRegisterMapper.getSysUserAuthenticationByUserId", pd);
}

public PageData getSysUserAuthenticationChildByUserId(PageData pd) throws Exception {
	return (PageData) dao.findForObject("AppUserRegisterMapper.getSysUserAuthenticationChildByUserId", pd);
}

/**
 * 根据  object_id查询附件列表
 * @param pd
 * @return
 * @throws Exception 
 */
@SuppressWarnings("unchecked")
public List<PageData> getFileUrlByObjectId(PageData pd) throws Exception {
	return (List<PageData>) dao.findForList("AppUserRegisterMapper.getFileUrlByObjectId",pd);
}

@SuppressWarnings("unchecked")
public Boolean getEuserVehicleListByUserId(PageData pd) throws Exception {
	List<PageData> list = (List<PageData>) dao.findForList("AppUserRegisterMapper.getEuserVehicleListByUserId", pd);
	if(CollectionUtils.isNotEmpty(list)&&list.size()>=2){
		return false;
	}else {
      return  true;		
	}
}
/**
 * 验证车牌号
 * @param pd
 * @return
 * @throws Exception 
 */
@SuppressWarnings("unchecked")
public boolean getEvehicleByPlateNum(PageData pd) throws Exception {
	List<PageData> list = (List<PageData>) dao.findForList("AppUserRegisterMapper.getEvehicleByPlateNum", pd);
	if (CollectionUtils.isNotEmpty(list)&&list.size()>0) {
		return  true;
	}else {
		return false;
	}
}

}
