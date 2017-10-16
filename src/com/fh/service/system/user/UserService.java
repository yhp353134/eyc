package com.fh.service.system.user;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import com.fh.controller.base.BaseService;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.system.User;
import com.fh.util.JsonResult;
import com.fh.util.PageData;
import com.fh.util.PrimaryUtil;
import com.fh.util.SysDataCode;
import com.fh.util.Tools;

@SuppressWarnings("unchecked")
@Service("userService")
public class UserService extends BaseService{

    @Resource(name = "daoSupport")
    private DaoSupport dao;

    //======================================================================================

    /*
     * 通过id获取数据
     */
    public PageData findByUiId(PageData pd) throws Exception {
        return (PageData) dao.findForObject("UserXMapper.findByUiId", pd);
    }

    /*
     * 通过loginname获取数据
     */
    public PageData findByUId(PageData pd) throws Exception {
        return (PageData) dao.findForObject("UserXMapper.findByUId", pd);
    }

    /*
     * 通过邮箱获取数据
     */
    public PageData findByUE(PageData pd) throws Exception {
        return (PageData) dao.findForObject("UserXMapper.findByUE", pd);
    }
    
    /*
     * 查询首页总数
     */
    public PageData searchIndexNumMsg(PageData pd) throws Exception {
        return (PageData) dao.findForObject("UserXMapper.searchIndexNumMsg", pd);
    }
    
    /*
     * 查询首页报表
     */
    public List<PageData> getIndexMsgList(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("UserXMapper.getIndexMsgList", pd);
    }

    /*
     * 通过编号获取数据
     */
    public PageData findByUN(PageData pd) throws Exception {
        return (PageData) dao.findForObject("UserXMapper.findByUN", pd);
    }

    /*
     * 保存用户
     */
    public void saveU(PageData pd) throws Exception {
        dao.save("UserXMapper.saveU", pd);
    }

    /*
     * 修改用户
     */
    public void editU(PageData pd) throws Exception {
        dao.update("UserXMapper.editU", pd);
    }

    /*
     * 换皮肤
     */
    public void setSKIN(PageData pd) throws Exception {
        dao.update("UserXMapper.setSKIN", pd);
    }

    /*
     * 删除用户
     */
    public void deleteU(PageData pd) throws Exception {
        dao.delete("UserXMapper.deleteU", pd);
    }

    /*
     * 批量删除用户
     */
    public void deleteAllU(String[] USER_IDS) throws Exception {
        dao.delete("UserXMapper.deleteAllU", USER_IDS);
    }

    /*
     * 用户列表(用户组)
     */
    public List<PageData> listPdPageUser(Page page) throws Exception {
        return (List<PageData>) dao.findForList("UserXMapper.userlistPage", page);
    }

    /*
     * 用户列表(全部)
     */
    public List<PageData> listAllUser(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("UserXMapper.listAllUser", pd);
    }

    /*
     * 用户列表(供应商用户)
     */
    public List<PageData> listGPdPageUser(Page page) throws Exception {
        return (List<PageData>) dao.findForList("UserXMapper.userGlistPage", page);
    }

    /*
     * 保存用户IP
     */
    public void saveIP(PageData pd) throws Exception {
        dao.update("UserXMapper.saveIP", pd);
    }

    /*
     * 登录判断
     */
    public PageData getUserByNameAndPwd(PageData pd) throws Exception {
        return (PageData) dao.findForObject("UserXMapper.getUserInfo", pd);
    }
    
    /*
     * 登录判断
     */
    public PageData getUserByUserId(PageData pd) throws Exception {
        return (PageData) dao.findForObject("UserXMapper.getUserByUserId", pd);
    }

    /**
     * 查询用户有多少个职位
     * */
    public List<PageData> getUserPostByUserName(@Param("postUserName") String postUserName) throws Exception {
        return (List<PageData>) dao.findForList("UserXMapper.getUserPostList", postUserName);
    }

    /*
     * 跟新登录时间
     */
    public void updateLastLogin(PageData pd) throws Exception {
        dao.update("UserXMapper.updateLastLogin", pd);
    }

    /*
     * 通过id获取数据
     */
    public User getUserAndRoleById(String USER_ID) throws Exception {
        return (User) dao.findForObject("UserMapper.getUserAndRoleById", USER_ID);
    }

    /**
     * 查询用户列表
     * */
    public List<PageData> getUserlistPage(Page page) throws Exception {
        return (List<PageData>) dao.findForList("UserXMapper.getUserlistPage", page);
    }
    
    /**
     * 查询注册用户列表
     * */
    public List<PageData> getRegistUserlistPage(Page page) throws Exception {
        return (List<PageData>) dao.findForList("UserXMapper.getRegistUserlistPage", page);
    }

    /**
     * 检查用户名是否可用
     * */
    public int checkLoginName(PageData pd) throws Exception {
        String LoginName = pd.getString("loginName");
        if (Tools.isEmpty(LoginName)) {
            return 0;
        }
        List<PageData> userList = (List<PageData>) dao.findForList("UserXMapper.checkLoginName", LoginName.trim());
        if (0 < userList.size()) {
            return 0;
        }
        return 1;
    }

    /**
     * 查询所有的职位
     * */
    public List<PageData> getAllPostList(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("UserXMapper.getAllPostList", pd);
    }

    /**
     * 添加用户
     * */
    public int saveUserMsg(PageData pd) throws Exception {
        Long uuid = PrimaryUtil.getPrimary();
        User user = new User();
        user.setUSER_ID(uuid);
        String loginName = pd.getString("loginName");//用户名
        if (Tools.isEmpty(loginName)) {
            return 0;
        } else {
            user.setUSER_NAME(loginName);
        }
        String passWordAgin = pd.getString("passWordAgin");//确认密码
        if (Tools.isEmpty(passWordAgin)) {
            return 0;
        }
        String passWord = pd.getString("passWord");//密码
        if (Tools.isEmpty(passWord)) {
            return 0;
        }
        if (!passWord.equalsIgnoreCase(passWordAgin)) {
            return 0;
        }
        String passwd = new SimpleHash("SHA-1", loginName, passWord).toString();
        user.setPASSWORD(passwd);
        String chinName = pd.getString("chinName");//用户姓名
        if (Tools.isEmpty(chinName)) {
            return 0;
        } else {
            user.setNAME(chinName);
        }
        String gendar = pd.getString("gendar");//性别
        if (Tools.isEmpty(gendar)) {
            return 0;
        } else {
            user.setSEX(Integer.parseInt(gendar));
        }
        String telPhone = pd.getString("telPhone");//电话
        user.setTEL(telPhone);
        String email = pd.getString("email");//邮箱
        user.setEMAIL(email);
        // user.setDEALER_ID(Long.parseLong(pd.get("dealerId").toString()));
        user.setCARNUM(pd.get("carNum")==null?"":pd.get("carNum").toString());
        user.setCREATE_BY(Long.parseLong(pd.get("userId").toString()));
        user.setCREATE_DATE(new Date());
        user.setSTATUS(SysDataCode.ISVALID_YES);
        user.setAUDIT_STATUS(SysDataCode.EXAMINE_STATUS_02);
        user.setREGISTER_DATE(new Date());
        //货主添加子账号
        // Object isAmont = pd.get("userType");
        /*if ("10131002".equals(isAmont.toString()) || "10131004".equals(isAmont.toString())) {
    		
    	} */
        if ("10120002".equals(pd.get("orgType"))) {
    		user.setIS_MAIN_COUNT(SysDataCode.STATUS_TYPE_02);
    		user.setUSER_TYPE(SysDataCode.USR_TYPE_02);
    	} else {
    		user.setIS_MAIN_COUNT(SysDataCode.STATUS_TYPE_01);
    		user.setUSER_TYPE(SysDataCode.USR_TYPE_01);
    	}
        String postArr = pd.getString("postArr");
        if (!Tools.isEmpty(postArr)) {
        	String postId = postArr.split(",")[0];
        	Long postArrPostId = Long.parseLong(postId);
        	//根据postId 获取经销商ID（前提是只能一个职位）
            String orgId = (String)dao.findForObject("UserXMapper.getDealerIdByPostIds", postArrPostId);
            if (!Tools.isEmpty(orgId)) {
            	user.setDEALER_ID(Long.parseLong(orgId));
            }
        }
        //添加用户
        dao.save("UserXMapper.saveUserMsg", user);
        if (!Tools.isEmpty(postArr)) {
            String[] arr = postArr.split(",");
            for (int i = 0; i < arr.length; i++) {
                String one = arr[i];
                if (Tools.isEmpty(one)) {
                    continue;
                }
                //根据职位ID
                
                pd.put("userPostId", PrimaryUtil.getPrimary());
                pd.put("userId", uuid);
                pd.put("postId", one);
                pd.put("whenCtr", new Date());
                //插入用户跟职位的关系
                dao.save("UserXMapper.saveUserPostMsg", pd);
            }
        }
        return 1;
    }

    /**
     * 修改用户
     * */
    public int updateUserMsg(PageData pd) throws Exception {
        User user = new User();
        String userId = pd.getString("userId");
        if (Tools.isEmpty(userId)) {
            return 0;
        } else {
            user.setUSER_ID(Long.parseLong(userId));
        }
        String loginName = pd.getString("loginName");//用户名
        if (Tools.isEmpty(loginName)) {
            return 0;
        } else {
            user.setUSER_NAME(loginName);
        }
        String passWordAgin = pd.getString("passWordAgin");//确认密码
        String passWord = pd.getString("passWord");//密码
        if (Tools.notEmpty(passWord)) {
            if (!passWord.equalsIgnoreCase(passWordAgin)) {
                return 0;
            }
            String passwd = new SimpleHash("SHA-1", loginName, passWord).toString();
            user.setPASSWORD(passwd);
        }
        String chinName = pd.getString("chinName");//用户姓名
        if (Tools.isEmpty(chinName)) {
            return 0;
        } else {
            user.setNAME(chinName);
        }
        String gendar = pd.getString("gendar");//性别
        if (!Tools.isEmpty(gendar)) {
        	user.setSEX(Integer.parseInt(gendar));
        }
        String telPhone = pd.getString("telPhone");//电话
        user.setTEL(telPhone);
        String email = pd.getString("email");//邮箱
        user.setEMAIL(email);
        user.setUPDATE_BY(Long.parseLong(pd.get("loginUserId").toString()));
        user.setUPDATE_DATE(new Date());
        user.setCARNUM(pd.get("carNum")==null?"":pd.get("carNum").toString());
        String userStatus = Tools.checkObj(pd.get("userStatus"));
        if (Tools.notEmpty(userStatus)) {
            user.setSTATUS(Integer.parseInt(userStatus));
            if (Integer.parseInt(userStatus) == SysDataCode.ISVALID_NO) {
            	//将当前用户挂的承运商更改为无效状态
                Object isAmount = pd.get("isAmount");
                if (null != isAmount && "10011001".equals(isAmount)) {
                	dao.update("UserXMapper.updateDealerStatus", pd);
                }
            }
        }
        String postArr = pd.getString("postArr");
        if (!Tools.isEmpty(postArr)) {
        	String postId = postArr.split(",")[0];
        	Long postArrPostId = Long.parseLong(postId);
        	//根据postId 获取经销商ID（前提是只能一个职位）
            String orgId = (String)dao.findForObject("UserXMapper.getDealerIdByPostIds", postArrPostId);
            if (!Tools.isEmpty(orgId)) {
            	user.setDEALER_ID(Long.parseLong(orgId));
            }
        }
        //修改用户
        dao.update("UserXMapper.updateUserMsgOne", user);
        //根据userId 先删除关联的职位
        dao.delete("UserXMapper.deleteUserMsgOne", user);
        if (!Tools.isEmpty(postArr)) {
            String[] arr = postArr.split(",");
            for (int i = 0; i < arr.length; i++) {
                String one = arr[i];
                if (Tools.isEmpty(one)) {
                    continue;
                }
                pd.put("userPostId",PrimaryUtil.getPrimary());
                pd.put("userId", userId);
                pd.put("postId", one);
                pd.put("whenCtr", new Date());
                //插入用户跟职位的关系
                dao.save("UserXMapper.saveUserPostMsg", pd);
            }
        }
        return 1;
    }

    /**
     * 根据ID获取用户信息
     * */
    public PageData getUserMsgById(PageData pd) throws Exception {
        return (PageData) dao.findForObject("UserXMapper.getUserMsgById", pd);
    }

    /**
     * 根据ID获取职位信息
     * */
    public List<PageData> getUserPostById(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("UserXMapper.getUserPostById", pd);
    }
    
    /**
     * 检查原密码是否正确
     * @throws Exception 
     * */
    public JsonResult<String> checkOldPass(PageData pd) throws Exception {
        List<PageData> isCheck= (List<PageData>) dao.findForList("UserXMapper.checkOldPass", pd);
        if (0 == isCheck.size()) {
            return new JsonResult<String>("0", "原密码不正确");
        } else {
            return new JsonResult<String>("1", "");
        }
    }
    
    /**
     * 修改密码
     * @throws Exception 
     * */
    public JsonResult<String> updatePassword(PageData pd) throws Exception {
        Integer isup = (Integer)dao.update("UserXMapper.updatePassword", pd);
        if (null == isup || 0 == isup) {
            return new JsonResult<String>("0", "修改密码失败");
        } else {
            return new JsonResult<String>("1", "修改密码成功");
        }
    }
    
    /**
     * 根据时间查询用户列表
     */
    public List<PageData> gerUserByTime(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("UserXMapper.gerUserByTime", pd);
    }
    
    /**
     * 更新密码
     */
    public void updateNewPassword(PageData pd) throws Exception {
        dao.update("UserXMapper.updateNewPassword", pd);
    }
     /**
      * 获取接入成功的服务站
      * @return
     * @throws Exception 
      */
	public PageData getAccessTokenSuccess() throws Exception {
		return (PageData) this.dao.findForObject("UserXMapper.getAccessTokenSuccess", null);
	}
   /**
    * 获取粉丝数量
    * @param pd
    * @return
 * @throws Exception 
    */
	public PageData getFansAllNum(PageData pd) throws Exception {
		return (PageData) this.dao.findForObject("UserXMapper.getFansAllNum", pd);
	}
    /**
     * 获取绑定数据
     * @param pd
     * @return
     * @throws Exception
     */
	public PageData getVehicleBindNum(PageData pd) throws Exception {
		return (PageData) this.dao.findForObject("UserXMapper.getVehicleBindNum", pd);
	}
	/**
	 * 获取养修预约数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getordNum(PageData pd) throws Exception {
		return (PageData) this.dao.findForObject("UserXMapper.getordNum", pd);
	}
    /**
     * 获取服务活动数据统计
     * @param pd
     * @return
     * @throws Exception 
     */
	public PageData getactivityNum(PageData pd) throws Exception {
		return (PageData) this.dao.findForObject("UserXMapper.getactivityNum", pd);
	}
    /**
     * 意见反馈
     * @param pd
     * @return
     * @throws Exception
     */
	public PageData getSuggestNum(PageData pd) throws Exception {
		return (PageData) this.dao.findForObject("UserXMapper.getSuggestNum", pd);
    }
    /**
     * 群发消息
     * @param pd
     * @return
     * @throws Exception
     */
	public PageData getmessageSendNum(PageData pd) throws Exception {
		return (PageData) this.dao.findForObject("UserXMapper.getmessageSendNum", pd);
    }
    /**
     * 会话
     * @param pd
     * @return
     * @throws Exception
     */
	public PageData getsessionNum(PageData pd) throws Exception {
		return (PageData) this.dao.findForObject("UserXMapper.getsessionNum", pd);
    }

	public PageData getWarnNum(PageData pd) throws Exception {
		return (PageData) this.dao.findForObject("UserXMapper.getWarnNum", pd);
    }
	
	 /**
     * 用户审核
     * @throws Exception 异常
     * */
	public void examineUser(PageData pd) throws Exception {
		this.dao.findForObject("UserXMapper.examineUser", pd);
		//根据userId获取dealerId
		PageData dealerPd = (PageData)dao.findForObject("UserXMapper.getDealerIdByUserId", pd);
		if (null != dealerPd && 0 < dealerPd.size()) {
			//修改承运商的状态
			pd.put("dealerStatusId", dealerPd.get("DEALER_ID"));
			dao.update("UserXMapper.updateDealerStatusById", pd);
		}
    }
	
	/**
	 * 获取货主的营业执照
	 * */
	public PageData getOwnerFileImg(PageData pd) throws Exception {
		return (PageData) this.dao.findForObject("UserXMapper.getOwnerFileImg", pd);
    }
	
	/**
	 * 获取货主的营业执照(c端)
	 * */
	public List<PageData> getPersonOwnerFileImg(PageData pd) throws Exception {
		return (List<PageData>) this.dao.findForList("UserXMapper.getPersonOwnerFileImg", pd);
    }
	
	 /**
     * 文件状态改为不可用
     * @throws Exception 异常
     * */
	public void updateUserImgFileStatus(Long fileId) throws Exception {
		this.dao.findForObject("UserXMapper.updateUserImgFileStatus", fileId);
    }
	
	 /**
     * 文件状态改为不可用(多张图片)
     * @throws Exception 异常
     * */
	public void updatePersonUserImgFileStatus(PageData pd) throws Exception {
		this.dao.findForObject("UserXMapper.updatePersonUserImgFileStatus", pd);
    }
	
	/**
     * 获取附件列表
     */
    public List<PageData> getFileImgList(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("UserXMapper.getFileImgList", pd);
    }
    
    /**
	 * 获取货主的营业执照
	 * */
	public PageData getCarMsg(PageData pd) throws Exception {
		return (PageData) this.dao.findForObject("UserXMapper.getCarMsg", pd);
    }

    public List<PageData> getButtonlistPage(Page page) throws Exception {
        return (List<PageData>) dao.findForList("UserXMapper.getButtonlistPage", page);
    }
    
    public void saveButton(PageData pd) throws Exception {
    	dao.save("UserXMapper.saveButton", pd);
    }
    
    public List<PageData> getMenuList() throws Exception {
        return (List<PageData>) dao.findForList("UserXMapper.getMenuList", null);
    }
    
    public List<PageData> getAllButtonList() throws Exception {
        return (List<PageData>) dao.findForList("UserXMapper.getAllButtonList", null);
    }
    
    public String saveButtonWithMenu(PageData pd) throws Exception {
    	 String buttonArr = pd.getString("buttonId");
    	 //先删除原来菜单的关系
    	 dao.delete("UserXMapper.deleteButtonWithMenuById", pd);
    	 String[] arr = buttonArr.split(",");
    	 for (int i=0;i<arr.length;i++) {
    		 String one = arr[i];
    		 pd.put("buttonId", one);
    		dao.save("UserXMapper.insertButtonWithMenuById", pd);
    	 }
    	 return "update success";
    }
    
    public List<PageData> driverInDealerlistPage(Page page) throws Exception {
        return (List<PageData>) dao.findForList("UserXMapper.driverInDealerlistPage", page);
    }
    
}
