package com.fh.service.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.organization.BaseOrg;
import com.fh.entity.system.SysData;
import com.fh.util.Const;
import com.fh.util.FileUtil;
import com.fh.util.JpushUtils;
import com.fh.util.Logger;
import com.fh.util.PageData;
import com.fh.util.PrimaryUtil;
import com.fh.util.SmsUtil;
import com.fh.util.SysDataCode;
import com.fh.util.Tools;
/**
 * 公共的查询sys_code方法
 * @author baby_ljie
 *
 */
@SuppressWarnings("unchecked")
@Service("commonService")
public class CommonService {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	/**
	 * 根据code的类型获取code列表
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getSysDataCodeByCodeType(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("CommonMapper.getSysDataCodeByCodeType", pd);
	}
	/**
	 * 查询车系列表
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getAutoMatList(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("CommonMapper.getAutoMatList", pd);
	}
	
	/**
	 * 查询省份
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getProvinceList() throws Exception {
		return (List<PageData>) dao.findForList("CommonMapper.getProvinceList", null);
	}
	
	/**
	 * 根据省份ID获取城市
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getCityByProovinceId(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("CommonMapper.getCityByProovinceId", pd);
	}
	
	/**
	 * 查询大区区域列表
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getBaseOrg(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("CommonMapper.getBaseOrg", pd);
	}
	/**
	 * 查询片区城市
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getBaseOrgCity(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("CommonMapper.getBaseOrgCity", pd);
	}
	
	
	/**
	 	查询所有大区片区结构列表  ahua772
	 	用于：显示机构树形结构
	 */
	public List<PageData> getAllBaseOrg(PageData pd) throws Exception {
		//pd.put("org_id", "5a5d67b1ffde11e688c271864ce8a78e");
		return (List<PageData>) dao.findForList("CommonMapper.getAllBaseOrg", pd);
	}
	
	/**
	 * 
	 * 根据机构id查询经销商列表 ahua772 
	 * 用于：选择服务站 公用方法
	 */
    public List<PageData> getDealerList(Page page) throws Exception {
		return (List<PageData>) dao.findForList("CommonMapper.getDealerlistPage", page);
	}
	
	
	/**
 	查询所有大区   ahua772
 	用于：显示机构树形结构
	 */
	public List<PageData> getChooseBaseOrg(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("CommonMapper.getChooseBaseOrg", pd);
	}
	
	/**
	 * 
	 * 根据大区ID查询城市列表 ahua772 
	 * 用于：选择机构 公用方法
	 */
	public List<PageData> getOrgList(Page page) throws Exception {
		return (List<PageData>) dao.findForList("CommonMapper.getOrglistPage", page);
	}
	
	
	/**
	 * 
	 * 根据机构id查询经销商ID和其机构下的机构ID
	 * 用于：选择服务站 公用方法
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<String> getDelaerAndOrg(PageData page) throws Exception {
	    return (List<String>) dao.findForList("CommonMapper.getDelaerAndOrg", page);
	}
	
    /**
     * <p>Description: 根据机构数组获取经销商</p>
     * @param pd
     * @return
     * @throws Exception
     */
	public List<PageData> getDelaerByOrgArr(PageData pd) throws Exception {
	    return (List<PageData>) dao.findForList("CommonMapper.getDelaerByOrgArr", pd);
	}
	
    /**
     * <p>Description: 根据机构ID获取机构信息</p>
     * @param pd
     * @return
     * @throws Exception
     */
    public BaseOrg getOrgByOrgId(PageData pd) throws Exception{
        return (BaseOrg) dao.findForObject("FeedbackMapperE.getOrgByOrgId", pd);
    }
	
   /**
     * 查询文件
     * @param pd
     * @return
     * @throws Exception
     */
    public List<PageData> getFileBuObject(PageData pd) throws Exception{
        return (List<PageData>) dao.findForList("CommonMapper.getFileByObject", pd);
    }
    
    /**
     * <p>Description: 根据用户所属机构获取经销商ID</p>
     * @param session
     * @param orgType 机构类型
     * @param isPqOrgId 是否包含片区的机构ID
     * @return
     * @throws Exception
     */
    public PageData getDealersPd(Session session, String orgType, boolean isPqOrgId) throws Exception{
        PageData resultPd = new PageData();
        if(orgType.equals(String.valueOf(SysDataCode.ORG_TYPE_DEALER))){
            // 用户登录名
            String dealerId = session.getAttribute(Const.SESSION_DEALER_ID).toString().trim(); 
            resultPd.put("DEALER_ID", dealerId); //经销商ID
            resultPd.put("orgLevel", 3);
        }else{
            String orgId = session.getAttribute(Const.SESSION_DEALER_ID).toString();
            PageData paramPd = new PageData();
            paramPd.put("org_id", orgId);
            BaseOrg baseOrg = this.getOrgByOrgId(paramPd);
            paramPd.clear();
            List<String> idList = new ArrayList<String>();
            if(baseOrg.getOrgLevel() == 1){
                paramPd.put("parent_org_id", orgId);
                paramPd.put("unionAll", isPqOrgId);
            }else if(baseOrg.getOrgLevel() == 2){
                paramPd.put("org_id", orgId);
            }
            idList = this.getDelaerAndOrg(paramPd);
            if(baseOrg.getOrgLevel() == 1){
                idList.add(orgId);
            }
            if(baseOrg.getOrgLevel() != 0){
                resultPd.put("dealerList", idList);
            }
            resultPd.put("ORG_LEVEL", baseOrg.getOrgLevel());
        }
        return resultPd;
    }
    
    /**
     * <p>Description: 获取标签</p>
     * @param pd
     * @return
     * @throws Exception 
     */
	public List<PageData> getLabelList(PageData pd) throws Exception{
        return (List<PageData>) dao.findForList("CommonMapper.getLabelList", pd);
    }
    /**
     * 根据经销商id查询查询经销商信息
     * @param pd
     * @return
     * @throws Exception
     */
    public PageData getBaseDealerByDealerId(PageData pd) throws Exception {
    	return (PageData) dao.findForObject("CommonMapper.getBaseDealerByDealerId", pd);
	}
    /**
     * 根据app_id获取经销商信息
     * @param pd
     * @return
     * @throws Exception
     */
    public PageData getBaseDealerByAppId(PageData pd) throws Exception {
    	return (PageData) dao.findForObject("CommonMapper.getBaseDealerByAppId", pd);
    }
    /**
     * 根据org_id查询机构
     * @param pd
     * @return
     * @throws Exception 
     */
	public PageData getBaseOrgByOrgId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("CommonMapper.getBaseOrgByOrgId", pd);
	}
	

	/**
	 * <p>Description: 通过从数据库获取的相对路径，保存该路径下的图片文件路径信息到TOOL_SRC_IMG表</p>
	 * @return
	 * @throws Exception
	 */
	public int saveLocalFilePath() throws Exception{
        /********* 获取项目路径 begin*********/
        String path = this.getClass().getClassLoader().getResource("").getPath();
        path = path.substring(0, path.indexOf("WEB-INF")) + "weixin/plugins/CmWxEdit/";
        /********* 获取项目路径 end*********/
        /********* 从数据库获取文件的文件夹相对路径  begin*********/
        PageData pd = new PageData();
        pd.put("DATA_GROUP", SysDataCode.FILE_PATH);
        List<PageData> filePathList = this.getSysDataCodeByCodeType(pd);
        /********* 从数据库获取文件的文件夹相对路径 end*********/
        // 获取所有已保存的文件路径并用，隔开拼接成一个字符串
        String existedFilePath = (String)dao.findForObject("CommonMapper.getToolSrcImgGroupUrl", pd);
        // 文件路径信息List
        List<PageData> filePathInfoList = new ArrayList<PageData>();
        for(int m = 0 ; m < filePathList.size(); m++){
            PageData filePathPd = filePathList.get(m);
            String fileFolderPath = path+filePathPd.getString("DATA_NAME");// 文件夹绝对路径
            File fileFolder = new File(fileFolderPath);
            String[] fileList = fileFolder.list();
            if(fileList == null) return -1;
            for(int i = 0; i < fileList.length; i++){
                String fileName = fileList[i];
                String fileRelativePath = filePathPd.getString("DATA_NAME") + fileName;
                /********* 判断文件是否已保存路径，如果存在就不保存 begin*********/
                if(StringUtils.isNotEmpty(existedFilePath) && existedFilePath.indexOf(fileRelativePath) >= 0) continue; 
                /********* 判断文件是否已保存路径，如果存在就不保存 end*********/
                File file = new File(fileFolderPath+fileName);
                /********* 判断文件是否是图片 begin*********/
                boolean notPic = false;
                // 首先判断文件后缀名是否为.jpg,.png,.bmp
                notPic = !FileUtil.validFileSuffix(fileName, ".jpg",".png",".bmp",".gif",".jpeg");
                if (notPic) continue;
                ImageInputStream imgIn = null;
                try{
                    imgIn = ImageIO.createImageInputStream(file);
                    notPic = imgIn == null;
                }catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(imgIn != null)
                        try {
                            imgIn.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
                if (notPic) continue;
                /********* 判断文件是否是图片 end*********/
                // 文件保存信息
                PageData fileInfoPd = new PageData();
                fileInfoPd.put("IMAGE_ID", PrimaryUtil.getPrimary()); // 图片ID
//                fileInfoPd.put("FILE_TYPE", SysDataCode.MESSAGE_TYPE_04); // 文件类型
                fileInfoPd.put("FILE_NAME", fileName); // 文件名
                fileInfoPd.put("FILE_URL", fileRelativePath); // 文件路径
                filePathInfoList.add(fileInfoPd);
            }
            
        }
        Integer count = 0;
        if(filePathInfoList.size() > 0){
            PageData fileInfoPd = new PageData();
            fileInfoPd.put("CREATE_BY", "URL"); // 创建人
            fileInfoPd.put("CREATE_DATE", new Date()); // 创建时间
            fileInfoPd.put("filePathInfoList", filePathInfoList); // 创建时间
            count = (Integer) dao.save("CommonMapper.insertToolSrcImgByList", fileInfoPd);
        }
	    return count;
	}
	/***
	 * 查询数据字典
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	public List<SysData> getSysDataCode(PageData pd) throws Exception {
		return (List<SysData>) dao.findForList("CommonMapper.getSysDataCode", pd);
	}
	/**
	 * 获取数量
	 * @param pd
	 * @return
	 */
	public PageData getSysDataCount(PageData pd) {
		return null;
	}
	
	/**极光推送公共方法，按别名推送（货主）
	 * @throws Exception **/
	@Async
	public void sendMessageByJpushAlisa(String userId,PageData pd) {
		try {
			//非全网消息
			Long msgId = PrimaryUtil.getPrimary();
			pd.put("msgId", msgId);
			pd.put("rangFlag", SysDataCode.STATUS_TYPE_02); //表示非全网
			dao.save("CommonMapper.insertMaMsg", pd);
			Long msgSmallId = PrimaryUtil.getPrimary();
			pd.put("msgSmallId", msgSmallId);
			dao.save("CommonMapper.insertMaMsgReceive", pd);
			PageData jp = new PageData();
			jp.put("businessId", String.valueOf(pd.get("businessId")));
			jp.put("businessType", String.valueOf(pd.get("businessType")));
			jp.put("msgId", String.valueOf(msgSmallId));
			jp.put("rangFlag", String.valueOf(SysDataCode.STATUS_TYPE_02));
			JpushUtils.sendSingleUser(userId, 
					pd.get("msgContent")==null?"":pd.get("msgContent").toString(), 
					pd.get("msgTitle")==null?"":pd.get("msgTitle").toString(),
					getCountNoReadMsgNum(userId,Tools.checkObj(pd.get("msgUserType"))),
					jp);
		} catch(Exception e) {
			logger.error(e.toString());
		}
	}
	
	/**极光推送公共方法，按别名推送（司机）
	 * @throws Exception **/
	@Async
	public void sendMessageByJpushAlisaByDriver(String userId,PageData pd) throws Exception {
		//非全网消息
		Long msgId = PrimaryUtil.getPrimary();
		pd.put("msgId", msgId);
		pd.put("rangFlag", SysDataCode.STATUS_TYPE_02); //表示非全网
		dao.save("CommonMapper.insertMaMsg", pd);
		Long msgSmallId = PrimaryUtil.getPrimary();
		pd.put("msgSmallId", msgSmallId);
		dao.save("CommonMapper.insertMaMsgReceive", pd);
		PageData jp = new PageData();
		jp.put("businessId", String.valueOf(pd.get("businessId")));
		jp.put("businessType", String.valueOf(pd.get("businessType")));
		jp.put("msgId", String.valueOf(msgSmallId));
		jp.put("rangFlag", String.valueOf(SysDataCode.STATUS_TYPE_02));
		JpushUtils.sendSingleUserByDriver(String.valueOf(userId), 
				pd.get("msgContent")==null?"":pd.get("msgContent").toString(), 
				pd.get("msgTitle")==null?"":pd.get("msgTitle").toString(),
				getCountNoReadMsgNum(userId,Tools.checkObj(pd.get("msgUserType"))),		
				jp);
	}
	
	/**
	 * 极光推送公共方法，按别名推送 发多人，发消息内容不允许带有参数（货主）
	 * @throws Exception
	 **/
	@Async
	public void sendMessageByJpushAlisaList(PageData pd,List<PageData> listPageData) throws Exception {
		// 非全网消息
		Long msgId = PrimaryUtil.getPrimary();
		pd.put("msgId", msgId);
		// 表示非全网
		pd.put("rangFlag", SysDataCode.STATUS_TYPE_02); 
		dao.save("CommonMapper.insertMaMsg", pd);
		// 循环给司机发送信息
		for (PageData oneMsg : listPageData) {
			pd.put("receiveUserId", oneMsg.get("receiveUserId"));
			pd.put("receiveUserName", oneMsg.get("receiveUserName"));
			Long msgSmallId = PrimaryUtil.getPrimary();
			pd.put("msgSmallId", msgSmallId);
			dao.save("CommonMapper.insertMaMsgReceive", pd);
			PageData jp = new PageData();
			jp.put("businessId", String.valueOf(pd.get("businessId")));
			jp.put("businessType", String.valueOf(pd.get("businessType")));
			jp.put("msgId", String.valueOf(msgSmallId));
			jp.put("rangFlag", String.valueOf(SysDataCode.STATUS_TYPE_02));
			JpushUtils.sendSingleUser(pd.get("receiveUserId")==null?"":pd.get("receiveUserId").toString(), 
					pd.get("msgContent")==null?"":pd.get("msgContent").toString(), 
					pd.get("msgTitle")==null?"":pd.get("msgTitle").toString(),
					getCountNoReadMsgNum(Tools.checkObj(oneMsg.get("receiveUserId")),SysDataCode.STATUS_TYPE_01+""),		
					jp);
		}
	}
	
	/**
	 * 极光推送公共方法，按别名推送 发多人，发消息内容不允许带有参数（司机）
	 * @throws Exception
	 **/
	@Async
	public void sendMessageByJpushAlisaListByDirver(PageData pd,List<PageData> listPageData) throws Exception {
		// 非全网消息
		Long msgId = PrimaryUtil.getPrimary();
		pd.put("msgId", msgId);
		// 表示非全网
		pd.put("rangFlag", SysDataCode.STATUS_TYPE_02); 
		dao.save("CommonMapper.insertMaMsg", pd);
		// 循环给司机发送信息
		for (PageData oneMsg : listPageData) {
			pd.put("receiveUserId", oneMsg.get("receiveUserId"));
			pd.put("receiveUserName", oneMsg.get("receiveUserName"));
			Long msgSmallId = PrimaryUtil.getPrimary();
			pd.put("msgSmallId", msgSmallId);
			dao.save("CommonMapper.insertMaMsgReceive", pd);
			PageData jp = new PageData();
			jp.put("businessId", String.valueOf(pd.get("businessId")));
			jp.put("businessType", String.valueOf(pd.get("businessType")));
			jp.put("msgId", String.valueOf(msgSmallId));
			jp.put("rangFlag", String.valueOf(SysDataCode.STATUS_TYPE_02));
			JpushUtils.sendSingleUserByDriver(pd.get("receiveUserId")==null?"":pd.get("receiveUserId").toString(), 
					pd.get("msgContent")==null?"":pd.get("msgContent").toString(), 
					pd.get("msgTitle")==null?"":pd.get("msgTitle").toString(),
					getCountNoReadMsgNum(Tools.checkObj(oneMsg.get("receiveUserId")),SysDataCode.STATUS_TYPE_02+""),			
					jp);
		}
	}
	
	/**** 
	 * 极光推送公共方法，按标签推送和群发,消息内容不允许有变量 （货主）
	 * @throws Exception 
	 * **/
	@Async
	public void sendMessageByJpushSign(String sign,PageData pd) throws Exception {
		Long msgId = PrimaryUtil.getPrimary();
		pd.put("msgId", msgId);
		pd.put("rangFlag", SysDataCode.STATUS_TYPE_01); //表示全网
		dao.save("CommonMapper.insertMaMsg", pd);
		PageData jp = new PageData();
		jp.put("businessId", "");
		jp.put("businessType", "");
		jp.put("msgId", String.valueOf(msgId));
		jp.put("rangFlag", String.valueOf(SysDataCode.STATUS_TYPE_01));
		// 此为全网消息
		if (Tools.isEmpty(sign)) {
			JpushUtils.sendAllUser(pd.get("msgContent")==null?"":pd.get("msgContent").toString());
		} else {
			JpushUtils.sendByTag(String.valueOf(sign), 
					pd.get("msgContent")==null?"":pd.get("msgContent").toString(), 
					pd.get("msgTitle")==null?"":pd.get("msgTitle").toString(), 
					1,			
					jp);
		}
	}
	
	/**** 
	 * 极光推送公共方法，按标签推送和群发,消息内容不允许有变量 (司机)
	 * @throws Exception 
	 * **/
	@Async
	public void sendMessageByJpushSignByDriver(String sign,PageData pd) throws Exception {
		Long msgId = PrimaryUtil.getPrimary();
		pd.put("msgId", msgId);
		pd.put("rangFlag", SysDataCode.STATUS_TYPE_01); //表示全网
		dao.save("CommonMapper.insertMaMsg", pd);
		PageData jp = new PageData();
		jp.put("businessId", "");
		jp.put("businessType", "");
		jp.put("msgId", String.valueOf(msgId));
		jp.put("rangFlag", String.valueOf(SysDataCode.STATUS_TYPE_01));
		// 此为全网消息
		if (Tools.isEmpty(sign)) {
			JpushUtils.sendAllUser(pd.get("msgContent")==null?"":pd.get("msgContent").toString());
		} else {
			JpushUtils.sendByTagByDriver(String.valueOf(sign), 
					pd.get("msgContent")==null?"":pd.get("msgContent").toString(), 
					pd.get("msgTitle")==null?"":pd.get("msgTitle").toString(),1,jp);
		}
	}
	
	/**
	 * 极光推送公共方法，按别名推送 发多人，按照线路上的司机群发
	 * @throws Exception
	 **/
	@Async
	public void sendMessageByJpushAlisaByLine(Set<PageData> linePageData) throws Exception {
		PageData paramPd = new PageData();
		// 非全网消息
		Long msgId = PrimaryUtil.getPrimary();
		paramPd.put("msgId", msgId);
		paramPd.put("msgTitle", "系统提醒");
		paramPd.put("msgContent", "");
		// 表示非全网
		paramPd.put("rangFlag", SysDataCode.STATUS_TYPE_02); 
		paramPd.put("msgUserType", SysDataCode.STATUS_TYPE_02); // 2表示司机
		dao.save("CommonMapper.insertMaMsg", paramPd);
		// 循环线路
		for (PageData pd : linePageData) {
			PageData tpd = new PageData();
			tpd.put("src_city_id", pd.get("src_city_id"));
			tpd.put("des_city_id", pd.get("des_city_id"));
			List<PageData> list =  (List<PageData>)dao.findForList("CommonMapper.getDriverListByBeginAndEndLine", tpd);
			//循环司机
			for(int i=0;i<list.size();i++) {
				PageData onel = list.get(i);
				String userId = onel.get("USER_ID")==null?"":onel.get("USER_ID").toString();
				String name = onel.get("NAME")==null?"":onel.get("NAME").toString();
				PageData smallPd = new PageData();
				Long msgSmallId = PrimaryUtil.getPrimary();
				smallPd.put("msgSmallId", msgSmallId);
				smallPd.put("msgId", msgId);
				smallPd.put("receiveUserId", userId);
				smallPd.put("receiveUserName", name);
				PageData rp = new PageData();
				rp.put("src_city", onel.get("SRC_CITY"));
				rp.put("des_city", onel.get("DES_CITY"));
				String msgContent = SmsUtil.getMessageAndReplaceKey("SMS_70014", rp);
				smallPd.put("msgContent", msgContent);
				dao.save("CommonMapper.insertMaMsgReceive", smallPd);
				try{
					PageData jp = new PageData();
					jp.put("businessId", "");
					jp.put("businessType", SysDataCode.NOTICE_TYPE_JIGUANG_02+"");
					jp.put("msgId", String.valueOf(msgSmallId));
					jp.put("rangFlag", String.valueOf(SysDataCode.STATUS_TYPE_02));
					JpushUtils.sendSingleUserByDriver(String.valueOf(userId), msgContent, "系统提醒",
					getCountNoReadMsgNum(userId,SysDataCode.STATUS_TYPE_02+""),
					jp);
				} catch(Exception e){
					logger.error(e.toString());
				}
			} //司机循环结束
		} //路线循环结束
	}
	
	/**插入主消息*/
	public PageData saveMaMessage(PageData pd) throws Exception{
		Long msgId = PrimaryUtil.getPrimary();
		pd.put("msgId", msgId);
		// 表示非全网
		pd.put("rangFlag", SysDataCode.STATUS_TYPE_02); 
		dao.save("CommonMapper.insertMaMsg", pd);
		return pd;
	}
	
	/**插入子消息*/
	public void saveMaMessageCopy(PageData pd) throws Exception{
		Long msgId = PrimaryUtil.getPrimary();
		pd.put("msgSmallId", msgId);
		// 表示非全网
		pd.put("rangFlag", SysDataCode.STATUS_TYPE_02); 
		dao.save("CommonMapper.insertMaMsgReceive", pd);
	}
	
	/**根据dealerId获取dealerName*/
	public String getDealerNameByDealerId(PageData pd) throws Exception {
		PageData ps = (PageData)dao.findForObject("CommonMapper.getDealerNameByDealerId", pd);
		if (null != ps && !"null".equals(ps)) {
			return Tools.checkObj(ps.get("DEALER_NAME"));
		} else {
			return "";
		}
	}
    
	/**根据货源ID获取货主信息**/
	public PageData getOwnerMsgBySourceId(PageData pd) throws Exception{
		return (PageData)dao.findForObject("CommonMapper.getOwnerMsgBySourceId", pd);
	}
	
	/**根据当前的司机ID查询主账号的ID,若是主账号，那么会返回主账号的ID**/
	public PageData getAmontUserByDriverId(PageData pd) throws Exception{
		return (PageData)dao.findForObject("CommonMapper.getAmontUserByDriverId", pd);
	}
	
	/***查询用户的未读消息**/
	public int getCountNoReadMsgNum(String userId,String userType) throws Exception {
		PageData pd = new PageData();
		pd.put("userId", userId);
		pd.put("userType", userType);
		Integer rt = (Integer)dao.findForObject("CommonMapper.getCountNoReadMsgNum", pd);
		if (null == rt) {
			return 0;
		} else {
			return rt;
		}
	}
	
	/**插入订单费用**/
	public void insertOrderCost(PageData pd) throws Exception {
		dao.save("CommonMapper.insertOrderCost", pd);
	}
	
}
