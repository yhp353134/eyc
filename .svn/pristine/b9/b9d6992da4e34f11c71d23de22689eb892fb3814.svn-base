package com.fh.controller.system.user;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.app.appcommon.AppCommonService;
import com.fh.service.system.role.RoleService;
import com.fh.service.system.user.UserService;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.SysDataCode;
import com.fh.util.Tools;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Resource(name = "roleService")
    private RoleService roleService;
    
    @Resource(name = "userService")
    private UserService userService;
    
    @Resource(name = "appCommonService")
    private AppCommonService appCommonService;
    
    
    /***
     * 批量修改密码成功
     * @throws Exception 
     * */
    @RequestMapping(value = "/batchUpdatePassword")
    @ResponseBody
    public String batchUpdatePassword () throws Exception {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        PageData pd = new PageData();
        pd = this.getPageData();
        String pw = pd.getString("password");
        String tm = pd.getString("nowTime");
        if (Tools.isEmpty(pw)) {
            return "password must not null";
        }
        if (Tools.isEmpty(tm)) {
            return "nowTime must not null";
        }
        if (tm.length()<18) {
            return "时间必须传年月日 时分秒的格式，必须到秒";
        }
        pd.put("nowTimes", sdf.format(sf.parseObject(tm)));
        //根据时间去查询用户
       List<PageData> userList = this.userService.gerUserByTime(pd);
       for (int i=0;i<userList.size();i++) {
           PageData d = userList.get(i);
           String userId = Tools.checkObj(d.get("USER_ID"));
           String userName = Tools.checkObj(d.get("USER_NAME"));
           if (Tools.isEmpty(userName)) {
               break;
           }
           String passwordNew = new SimpleHash("SHA-1", userName, pw.trim()).toString();
           PageData par = new PageData();
           par.put("newPassword", passwordNew);
           par.put("parKey", Long.parseLong(userId));
           //更新密码
           this.userService.updateNewPassword(par);
       } 
        return "update success";
    }
    
    /***
     * 给某个用户设置信息
     * */
    @RequestMapping(value = "/userSetMsg")
    public ModelAndView userSetMsg(Page page) throws Exception {
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
            pd.put("org_id", this.getDealerId());
            String orgType= pd.getString("orgType");
            if (Tools.notEmpty(orgType)) {
                pd.put("orgType", Long.parseLong(orgType));
            }
            pd.put("sta", this.getOrgType());
            page.setPd(pd);
            List<PageData> userList = this.userService.getUserlistPage(page);
            mv.addObject(Const.SESSION_QX, this.getPermission());
            mv.addObject("loginName", loginName);
            mv.addObject("chineName", chineName);
            mv.addObject("userList", userList);
            mv.addObject("orgType", this.getOrgType());
            mv.addObject("pd", pd);
            mv.setViewName("system/user/user_set_msg");
    } catch (Exception e) {
        logger.error(e.toString());
    }
        return mv;
    }
    

    /***
     * 查询用户列表
     * */
    @RequestMapping(value = "/userList")
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
            pd.put("org_id", this.getDealerId());
            String orgType= pd.getString("orgType");
            if (Tools.notEmpty(orgType)) {
                pd.put("orgType", Long.parseLong(orgType));
            }
            pd.put("sta", this.getOrgType());
            pd.put("dealerId", this.getDealerId());
            pd.put("userType", this.getUser().getUSER_TYPE());
            page.setPd(pd);
            List<PageData> userList = this.userService.getUserlistPage(page);
            mv.addObject(Const.SESSION_QX, this.getPermission());
            mv.addObject("loginName", loginName);
            mv.addObject("chineName", chineName);
            mv.addObject("userList", userList);
            mv.addObject("orgType", this.getOrgType());
            pd.put("thisUserName", this.getUser().getUSER_NAME());
            pd.put("isAmont", this.getUser().getIS_MAIN_COUNT());
            mv.addObject("pd", pd);
            mv.setViewName("system/user/user_list");
    } catch (Exception e) {
        logger.error(e.toString());
    }
        return mv;
    }

    /**
     * 跳转到新增用户界面
     * */
    @RequestMapping(value = "/addUser")
    public ModelAndView addUser() {
        ModelAndView mv = this.getModelAndView();
        mv.addObject("userType", this.getUser().getUSER_TYPE());
        mv.setViewName("system/user/user_add");
        return mv;
    }

    /**
     * 检查用户名是否可用
     * */
    @RequestMapping(value = "/checkLoginName")
    @ResponseBody
    public int checkLoginName() throws Exception {
        PageData pd = new PageData();
        pd = this.getPageData();
        return this.userService.checkLoginName(pd);
    }

    /**
     * 选择职位
     * @throws Exception 
     * */
    @RequestMapping(value = "/chosePost")
    public ModelAndView chosePost(Page page) throws Exception {
        ModelAndView mv = this.getModelAndView();
        //获取参数
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            //查询条件
            String postName = pd.getString("postNameChines");
            String postType = pd.getString("postType");
            Session session = SecurityUtils.getSubject().getSession();
            String orgType = "";
            if (null == session.getAttribute(Const.SESSION_ORG_TYPE)) {
                return mv;
            } else {
                orgType = session.getAttribute(Const.SESSION_ORG_TYPE).toString();
                if (Integer.parseInt(orgType) == SysDataCode.ORG_TYPE_DEPOT) {
                    if (!Tools.isEmpty(postType)) {
                        pd.put("postType", postType.toString());
                    } else {
                        pd.put("postType", null);
                    }
                } else {
                    if (!Tools.isEmpty(postType)) {
                        pd.put("postType", postType.toString());
                    } else {
                        pd.put("postType", "10120002");
                    }
                }
            }
            if (null != postName) {
                pd.put("postName", postName.toString().trim());
            } else {
                pd.put("postName", null);
            }
            //车厂可以看到所有的职位，经销商只能看到自己的职位
            String orgTypePost = this.getOrgType();
            if (SysDataCode.ORG_TYPE_DEALER == Integer.parseInt(orgTypePost)) {
                pd.put("orgCode", this.getDealerId());
            }
            page.setPd(pd);
            //查询所有的职位
            List<PageData> postList = this.roleService.getPostList(page);
            String hidArr = pd.getString("hidArr");//职位ID字符串
            if (!Tools.isEmpty(hidArr)) {
                String[] postIdArr = hidArr.split(",");
                for (int i=0;i<postList.size();i++) {
                    for (int j=0;j<postIdArr.length;j++) {
                        String postOne = postList.get(i).get("POST_ID")==null?"":postList.get(i).get("POST_ID").toString().trim();
                        String postArr = postIdArr[j].trim();
                        if (postOne.equals(postArr)) {
                            postList.get(i).put("CHECKED", "1");
                        } 
                    }
                }
            } 
            //按钮权限
            mv.addObject(Const.SESSION_QX, this.getPermission());
            mv.addObject(Const.SESSION_ORG_TYPE, orgType);
            mv.addObject("postName", postName);
            mv.addObject("postType", postType);
            mv.addObject("postList", postList);
            mv.addObject("pd", pd);
            mv.setViewName("system/user/post_detail");
        } catch (Exception e) {
            logger.error(e.toString());
            throw new Exception(e.toString());
        }
        return mv;
    }

    /**
     * 选择职位(修改)
     * @throws Exception 
     * */
    @RequestMapping(value = "/chosePostEdit")
    public ModelAndView chosePostEdit(Page page) throws Exception {
        ModelAndView mv = this.getModelAndView();
        //获取参数
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            String postName = pd.getString("postNameChines");
            String postType = pd.getString("postType");
            Session session = SecurityUtils.getSubject().getSession();
            String orgType = "";
            if (null == session.getAttribute(Const.SESSION_ORG_TYPE)) {
                return mv;
            } else {
                orgType = session.getAttribute(Const.SESSION_ORG_TYPE).toString();
                if (Integer.parseInt(orgType) == SysDataCode.ORG_TYPE_DEPOT) {
                    if (!Tools.isEmpty(postType)) {
                        pd.put("postType", postType.toString());
                    } else {
                        pd.put("postType", null);
                    }
                } else {
                    if (!Tools.isEmpty(postType)) {
                        pd.put("postType", postType.toString());
                    } else {
                        pd.put("postType", "10120002");
                    }
                }
            }
            if (null != postName) {
                pd.put("postName", postName.toString().trim());
            } else {
                pd.put("postName", null);
            }
            pd.put("userId", pd.getString("userId"));
            //车厂可以看到所有的职位，经销商只能看到自己的职位
            String orgTypePost = this.getOrgType();
            if (SysDataCode.ORG_TYPE_DEALER == Integer.parseInt(orgTypePost)) {
                pd.put("orgCode", this.getDealerId());
            }
            page.setPd(pd);
            List<PageData> postList = this.roleService.getPostListEdite(page);
            // hidArr 只有在点击上一页下一页时才会传入，若第一次进入是没有职位名称的
            String postId = pd.getString("hidArr");
            if (Tools.notEmpty(postId)) {
                String hidArr = "";
                String hidNameArr = "";
                String postListArr = "";
                for (int i = 0; i < postList.size(); i++) {
                    if ("yes".equals(postList.get(i).getString("ISHAS"))) {
                        postListArr = postListArr + postList.get(i).get("POST_ID") + "@"
                                + postList.get(i).getString("POST_NAME") + ",";
                        hidNameArr = hidNameArr + postList.get(i).getString("POST_NAME") + ",";
                        hidArr = hidArr + postList.get(i).get("POST_ID") + ",";
                    }
                }
                if (!Tools.isEmpty(hidArr)) {
                    pd.put("hidArr", hidArr.substring(0, hidArr.length() - 1));
                    pd.put("hidNameArr", hidNameArr.substring(0, hidNameArr.length() - 1));
                    pd.put("postListArr", postListArr.substring(0, postListArr.length() - 1));
                }
            } 
            //按钮权限
            mv.addObject(Const.SESSION_QX, this.getPermission());
            mv.addObject(Const.SESSION_ORG_TYPE, orgType);
            mv.addObject("postName", postName);
            mv.addObject("postType", postType);
            mv.addObject("postList", postList);
            mv.addObject("pd", pd);
            mv.setViewName("system/user/post_detil_edit");
        } catch (Exception e) {
            logger.error(e.toString());
            throw new Exception(e.toString());
        }
        return mv;
    }

    /**
     * 跳转到iframe页面
     * */
    @RequestMapping(value = "/jumpUserIframe")
    public ModelAndView jumpUserIframe(HttpServletRequest request) throws UnsupportedEncodingException {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String pll = new String(pd.getString("pdl").getBytes("iso8859-1"), "utf-8");
        pd.put("values", pll);
        mv.addObject("pd", pd);
        mv.setViewName("system/user/post_iframe");
        return mv;
    }

    /**
     * 跳转到iframe页面
     * */
    @RequestMapping(value = "/jumpUserIframeEdite")
    public ModelAndView jumpUserIframeEdite(HttpServletRequest request) throws UnsupportedEncodingException {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String pll = new String(pd.getString("pdl").getBytes("iso8859-1"), "utf-8");
        pd.put("values", pll);
        mv.addObject("pd", pd);
        mv.setViewName("system/user/post_iframe_edite");
        return mv;
    }

    /**
     * 添加用户
     * */
    @RequestMapping(value = "/saveUserMsg")
    public ModelAndView saveUserMsg() {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            pd.put("userChinese", getUser().getNAME());
            pd.put("userId", getUser().getUSER_ID());
            pd.put("dealerId", this.getDealerId());
            pd.put("userType", this.getUser().getUSER_TYPE());
            pd.put("orgType", this.getOrgType());
            int isR = this.userService.saveUserMsg(pd);
            if (isR == 0) {
                throw new Exception("添加用户失败");
            }
            mv.addObject("msg", "success");
            mv.setViewName("save_result");
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return mv;
    }

    /**
     * 跳转到修改用户界面
     * */
    @RequestMapping(value = "/jumpEditePage")
    public ModelAndView jumpEditePage() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String userId = pd.getString("userId");
        if (Tools.isEmpty(userId)) {
            return null;
        }
        //根据用户ID去查询用户信息
        pd.put("userId", userId);
        PageData ps = this.userService.getUserMsgById(pd);
        //根据用户ID
        List<PageData> psList = this.userService.getUserPostById(pd);
        mv.addObject("ps", ps);
        mv.addObject("psList", psList);
        mv.setViewName("system/user/user_edit");
        return mv;
    }

    /**
     * 修改用户
     * @throws Exception 
     * */
    @RequestMapping(value = "/updateUserMsg")
    public ModelAndView updateUserMsg() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            pd.put("userChinese", getUser().getNAME());
            pd.put("loginUserId", this.getUser().getUSER_ID());
            int isR = this.userService.updateUserMsg(pd);
            if (isR == 0) {
                throw new Exception("修改用户失败");
            }
            mv.addObject("msg", "success");
            mv.setViewName("save_result");
        } catch (Exception e) {
            logger.error(e.toString());
            throw new Exception(e.toString());
        }
        return mv;
    }
    
    /***
     * 查询注册用户列表
     * */
    @RequestMapping(value = "/registUserList")
    public ModelAndView registUserList(Page page) throws Exception {
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
            pd.put("sta", this.getOrgType());
            page.setPd(pd);
            List<PageData> userList = this.userService.getRegistUserlistPage(page);
            mv.addObject(Const.SESSION_QX, this.getPermission());
            mv.addObject("loginName", loginName);
            mv.addObject("chineName", chineName);
            mv.addObject("userList", userList);
            mv.addObject("userType", this.getUser().getUSER_TYPE());
            mv.addObject("pd", pd);
            mv.setViewName("system/user/user_regist_list");
    } catch (Exception e) {
        logger.error(e.toString());
    }
        return mv;
    }
    
    /**
     * 注册用户详情司机
     * @throws Exception 异常
     * */
    @RequestMapping(value = "/detailRegistUserDriver")
    public ModelAndView detailRegistUserDriver() throws Exception {
    	ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String userId = pd.getString("userId");
        if (Tools.isEmpty(userId)) {
            return null;
        }
        //根据用户ID去查询用户信息
        pd.put("userId", userId);
        PageData ps = this.userService.getUserMsgById(pd);
        //获取附件列表(身份证)
        List<PageData> fileList = this.userService.getFileImgList(pd);
        //获取附件列表(车辆)
        PageData carpd = this.userService.getCarMsg(pd);
        if (null != carpd && !"null".equals(carpd)) {
        	pd.put("userId", carpd.get("VEHICLE_ID"));
        } else {
        	pd.put("userId", null);
        }
        List<PageData> carFileList = this.userService.getFileImgList(pd);
        mv.addObject("ps", ps);
        mv.addObject("fileList", fileList);
        mv.addObject("carpd", carpd);
        mv.addObject("carFileList", carFileList);
        mv.setViewName("system/user/user_regist_detail_driver");
        return mv;
    }
    
    /**
     * 注册用户审核司机
     * @throws Exception 异常
     * */
    @RequestMapping(value = "/examineRegistUserDriver")
    public ModelAndView examineRegistUserDriver() throws Exception {
    	ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String userId = pd.getString("userId");
        if (Tools.isEmpty(userId)) {
            return null;
        }
        Object isAmont = pd.get("isAmont"); //表示是否是子账号标识
        //根据用户ID去查询用户信息
        pd.put("userId", userId);
        PageData ps = this.userService.getUserMsgById(pd);
        //获取附件列表(身份证)
        List<PageData> fileList = this.userService.getFileImgList(pd);
        //获取附件列表(车辆)
        PageData carpd = this.userService.getCarMsg(pd);
        if (null != carpd && !"null".equals(carpd)) {
        	 pd.put("userId", carpd.get("VEHICLE_ID")==null?"":carpd.get("VEHICLE_ID"));
        } else {
        	 pd.put("userId", null);
        }
        List<PageData> carFileList = this.userService.getFileImgList(pd);
        mv.addObject("ps", ps);
        mv.addObject("fileList", fileList);
        mv.addObject("carpd", carpd);
        mv.addObject("isAmont", isAmont);
        mv.addObject("carFileList", carFileList);
        mv.setViewName("system/user/user_regist_examine_driver");
        return mv;
    }
    
    /**
     * 注册用户详情货主(B端)
     * @throws Exception 异常
     * */
    @RequestMapping(value = "/detailRegistUserOwner")
    public ModelAndView detailRegistUserOwner() throws Exception {
    	ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String userId = pd.getString("userId");
        if (Tools.isEmpty(userId)) {
            return null;
        }
        //根据用户ID去查询用户信息
        pd.put("userId", userId);
        PageData ps = this.userService.getUserMsgById(pd);
        //获取附件列表
        if (null != ps && !"null".equals(ps)) {
        	pd.put("dealerId", ps.get("DEALER_ID"));
        } else {
        	pd.put("dealerId", null);
        }
        PageData  pp = this.userService.getOwnerFileImg(pd);
        mv.addObject("ps", ps);
        mv.addObject("pp", pp);
        mv.setViewName("system/user/user_regist_detail_owner");
        return mv;
    }
    
    /**
     * 注册用户详情货主(C端)
     * @throws Exception 异常
     * */
    @RequestMapping(value = "/detailRegistUserPersonOwner")
    public ModelAndView detailRegistUserPersonOwner() throws Exception {
    	ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String userId = pd.getString("userId");
        if (Tools.isEmpty(userId)) {
            return null;
        }
        //根据用户ID去查询用户信息
        pd.put("userId", userId);
        PageData ps = this.userService.getUserMsgById(pd);
        //获取附件列表
        if (null != ps && !"null".equals(ps)) {
        	pd.put("dealerId", ps.get("DEALER_ID"));
        } else {
        	pd.put("dealerId", null);
        }
        List<PageData> ppList = this.userService.getPersonOwnerFileImg(pd);
        mv.addObject("ps", ps);
        mv.addObject("ppList", ppList);
        mv.setViewName("system/user/user_regist_detail_person_owner");
        return mv;
    }
    
    /**
     * 注册用户审核货主(B端)
     * @throws Exception 异常
     * */
    @RequestMapping(value = "/examineRegistUserOwner")
    public ModelAndView examineRegistUserOwner() throws Exception {
    	ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String userId = pd.getString("userId");
        if (Tools.isEmpty(userId)) {
            return null;
        }
        //根据用户ID去查询用户信息
        pd.put("userId", userId);
        PageData ps = this.userService.getUserMsgById(pd);
        if (null != ps && !"null".equals(ps)) {
        	 pd.put("dealerId", ps.get("DEALER_ID"));
        } else {
        	 pd.put("dealerId", null);
        }
        //获取附件列表
        PageData  pp = this.userService.getOwnerFileImg(pd);
        mv.addObject("ps", ps);
        mv.addObject("pp", pp);
        mv.setViewName("system/user/user_regist_examine_owner");
        return mv;
    }
    
    /**
     * 注册用户审核货主(C端)
     * @throws Exception 异常
     * */
    @RequestMapping(value = "/examineRegistUserPersonOwner")
    public ModelAndView examineRegistUserPersonOwner() throws Exception {
    	ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String userId = pd.getString("userId");
        if (Tools.isEmpty(userId)) {
            return null;
        }
        //根据用户ID去查询用户信息
        pd.put("userId", userId);
        PageData ps = this.userService.getUserMsgById(pd);
        if (null != ps && !"null".equals(ps)) {
        	 pd.put("dealerId", ps.get("DEALER_ID"));
        } else {
        	 pd.put("dealerId", null);
        }
        //获取附件列表
        List<PageData>  pp = this.userService.getPersonOwnerFileImg(pd);
        mv.addObject("ps", ps);
        mv.addObject("ppList", pp);
        mv.setViewName("system/user/user_regist_examine_person_owner");
        return mv;
    }
    
    /**
     * 用户审核
     * @throws Exception 异常
     * */
    @RequestMapping(value = "/examineUser")
    public ModelAndView examineUser() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            pd.put("userChinese", getUser().getUSER_ID());
            Object examineType = pd.get("examineType"); // 1：司机  2：货主
            //货主审核
        	Object userId = pd.get("userId");
            if (null == userId || "".equals(userId)) {
            	return null;
            }
            Object examineFlag = pd.get("examineFlag");   // 1 :审核通过  2：审核驳回
            String telphoneNum = pd.getString("telphoneNum"); //电话号码
            String examineSuggest = pd.getString("examineSuggest"); // 审核意见
            String userName = pd.getString("userName");  //用户账号
            Object fileId = pd.get("fileId");  //不合格图片的ID（包括货主的营业执照，司机的身份证图片）
            Object carFileId = pd.get("carFileId");  //车辆信息图片的ID
            //准备一个审核参数表
            PageData exm = new PageData();
            exm.put("userId", userId);
            exm.put("examineRemark", examineSuggest);
            if ("2".equals(examineType)) {
            	//货主审核
                if ("1".equals(examineFlag)) {
                	//通过
                	exm.put("auditStatus", SysDataCode.EXAMINE_STATUS_02);
                	this.userService.examineUser(exm);
                	//给用户发送一条短信
                	if (!Tools.isEmpty(telphoneNum)) {
                		PageData paramPd = new PageData();
                		paramPd.put("userName", userName);
                		paramPd.put("send_user_id", this.getUser().getUSER_ID());
                		paramPd.put("businessId", Tools.checkObj(userId));
                		paramPd.put("businessType", SysDataCode.NOTICE_TYPE_JIGUANG_04);
                		paramPd.put("msgUserType", SysDataCode.STATUS_TYPE_01);
                		this.appCommonService.sendSmsMessage("SMS_10023", paramPd, telphoneNum.trim(), "");
                	}
                } else {
                	//驳回
                	exm.put("auditStatus", SysDataCode.EXAMINE_STATUS_03);
                	this.userService.examineUser(exm);
                	//将用户不合格的图片状态改为不可用
                	if (null != fileId && !"".equals(fileId)) {
                		String[] file_ids = fileId.toString().split(",");
                		PageData dataPerson = new PageData();
                		dataPerson.put("file_ids", file_ids);
                		this.userService.updatePersonUserImgFileStatus(dataPerson);
                	}
                	//给用户发送一条短信
                	if (!Tools.isEmpty(telphoneNum)) {
                		PageData paramPd4 = new PageData();
                		paramPd4.put("userName", userName);
                		paramPd4.put("userRemark", examineSuggest);
                		paramPd4.put("send_user_id", this.getUser().getUSER_ID());
                		paramPd4.put("businessId", Tools.checkObj(userId));
                		paramPd4.put("businessType", SysDataCode.NOTICE_TYPE_JIGUANG_05);
                		paramPd4.put("msgUserType", SysDataCode.STATUS_TYPE_01);
                		this.appCommonService.sendSmsMessage("SMS_10024", paramPd4, telphoneNum.trim(), "");
                	}
                }
            } else {
            	// 司机审核
                if ("1".equals(examineFlag)) {
                	//通过
                	exm.put("auditStatus", SysDataCode.EXAMINE_STATUS_02);
                	this.userService.examineUser(exm);
                	//给用户发送一条短信
                	if (!Tools.isEmpty(telphoneNum)) {
                		PageData paramPd1 = new PageData();
                		paramPd1.put("userName", userName);
                		paramPd1.put("send_user_id", this.getUser().getUSER_ID());
                		paramPd1.put("businessId", Tools.checkObj(userId));
                		paramPd1.put("businessType", SysDataCode.NOTICE_TYPE_JIGUANG_04);
                		paramPd1.put("msgUserType", SysDataCode.STATUS_TYPE_02);
                		this.appCommonService.sendSmsMessage("SMS_10025", paramPd1, telphoneNum.trim(), "");
                	}
                } else {
                	//驳回
                	exm.put("auditStatus", SysDataCode.EXAMINE_STATUS_03);
                	this.userService.examineUser(exm);
                	//将取消选中的图片改为不可用
                	if (null != fileId && !"".equals(fileId)) {
                		String[] fileArr = fileId.toString().split(",");
                		for (int i=0;i<fileArr.length;i++) {
                			String filePra = fileArr[i];
                			this.userService.updateUserImgFileStatus(Long.parseLong(filePra.toString()));
                		}
                	}
                	if (null != carFileId && !"".equals(carFileId)) {
                		String[] fileCarArr = carFileId.toString().split(",");
                		for (int i=0;i<fileCarArr.length;i++) {
                			String fileCarPra = fileCarArr[i];
                			this.userService.updateUserImgFileStatus(Long.parseLong(fileCarPra.toString()));
                		}
                	}
                	//给用户发送一条短信
                	if (!Tools.isEmpty(telphoneNum)) {
                		PageData paramPd3 = new PageData();
                		paramPd3.put("userName", userName);
                		paramPd3.put("userRemark", examineSuggest);
                		paramPd3.put("send_user_id", this.getUser().getUSER_ID());
                		paramPd3.put("businessId", Tools.checkObj(userId));
                		paramPd3.put("businessType", SysDataCode.NOTICE_TYPE_JIGUANG_05);
                		paramPd3.put("msgUserType", SysDataCode.STATUS_TYPE_02);
                		this.appCommonService.sendSmsMessage("SMS_10024", paramPd3, telphoneNum.trim(), "");
                	}
                }
            }
            mv.addObject("msg", "success");
            mv.setViewName("save_result");
        } catch (Exception e) {
            logger.error(e.toString());
            throw new Exception(e.toString());
        }
        return mv;
    }

    @RequestMapping(value = "/buttonList")
    public ModelAndView buttonList(Page page) throws Exception {
        ModelAndView mv = this.getModelAndView(); 
        try {
            List<PageData> buttonList = this.userService.getButtonlistPage(page);
            mv.addObject("buttonList", buttonList);
            mv.setViewName("system/user/button_list");
    } catch (Exception e) {
        logger.error(e.toString());
    }
        return mv;
    }

    @RequestMapping(value = "/addButtonPage")
    public ModelAndView addButtonPage() {
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("system/user/button_add");
        return mv;
    }
    
    @RequestMapping(value = "/saveButton")
    public ModelAndView saveButton() {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            this.userService.saveButton(pd);
            mv.addObject("msg", "success");
            mv.setViewName("save_result");
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return mv;
    }
    
    @RequestMapping(value = "/buttonWithMenu")
    public ModelAndView buttonWithMenu() throws Exception {
        ModelAndView mv = this.getModelAndView(); 
        try {
        	//获取菜单列表
        	 List<PageData> menuList = this.userService.getMenuList();
             List<PageData> buttonList = this.userService.getAllButtonList();
            mv.addObject("menuList", menuList);
            mv.addObject("buttonList", buttonList);
            mv.setViewName("system/user/button_with_menu");
    } catch (Exception e) {
        logger.error(e.toString());
    }
        return mv;
    }
    
    @RequestMapping(value ={ "/saveButtonWithMenu"},method = RequestMethod.POST)
    @ResponseBody
    public String saveButtonWithMenu() {
    	 String yes ="";
    	try{
	    	 PageData pd = new PageData();
	    	 pd=this.getPageData();
	    	 yes = this.userService.saveButtonWithMenu(pd);
    	}catch(Exception e) {
    		logger.error(e.toString());
    	}
    	return yes;
    }
    
    /***
     * 承运商查看自己下面的所有司机
     * */
    @RequestMapping(value = "/driverInDealerList")
    public ModelAndView driverInDealerList(Page page) throws Exception {
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
            page.setPd(pd);
            List<PageData> userList = this.userService.driverInDealerlistPage(page);
            mv.addObject(Const.SESSION_QX, this.getPermission());
            mv.addObject("userList", userList);
            mv.addObject("pd", pd);
            mv.setViewName("system/user/user_driver_in_dealer");
    } catch (Exception e) {
        logger.error(e.toString());
    }
        return mv;
    }


}
