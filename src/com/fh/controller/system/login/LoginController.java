package com.fh.controller.system.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.system.Button;
import com.fh.entity.system.Menu;
import com.fh.entity.system.User;
import com.fh.service.system.menu.MenuService;
import com.fh.service.system.role.RoleService;
import com.fh.service.system.user.UserService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.JsonResult;
import com.fh.util.PageData;
import com.fh.util.SysDataCode;
import com.fh.util.Tools;

/*
 * 总入口
 */
@Controller
public class LoginController extends BaseController {

    /**
     * <p>
     * Field userService: 用户sevice
     * </p>
     */
    @Resource(name = "userService")
    private UserService userService;

    /**
     * <p>
     * Field menuService: 菜单service
     * </p>
     */
    @Resource(name = "menuService")
    private MenuService menuService;

    /**
     * <p>
     * Field roleService: 权限service
     * </p>
     */
    @Resource(name = "roleService")
    private RoleService roleService;
    
    public static void main(String[] args) {
    	String passwd = new SimpleHash("SHA-1", "18580591296", "a123456").toString();
    	System.out.println(passwd);
	}

    /**
     * 请求登录，验证用户
     */
    @RequestMapping(value = "/login_login", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object login() throws Exception {
        //错误字符串
        String errInfo = "";
        //返回的值
        Map<String, String> map = new HashMap<String, String>();
        //获取参数
        PageData pd = new PageData();
        pd = this.getPageData();
        Object userId = pd.get("userId");
        if (null != userId && userId != "") {
        	// 当userId 不等于null时  是后台一个用户切换的功能  ，目前可以不用管
            //根据用户名去获取用户信息
            /*PageData d = new PageData();
            d.put("userId", Long.parseLong(userId.toString()));
            d=this.userService.getUserByUserId(d);
            if (d != null) {
                //shiro管理的session
                Session session = SecurityUtils.getSubject().getSession();
                //设置用户信息
                User user = new User();
                user.setUSER_ID(Long.parseLong(d.get("USER_ID").toString()));//用户ID
                user.setUSER_NAME(d.getString("USER_NAME"));//登录名
                user.setNAME(d.getString("NAME"));//中文名
                user.setSKIN("default");//皮肤
                session.setAttribute(Const.SESSION_USER, user);
                session.setAttribute(Const.SESSION_USERNAME, user.getUSER_NAME());
                session.setAttribute(Const.SESSION_APP_ID, "wxccbc3ec1ecd549f4"); // 微信APPID
                //清楚验证码
                session.removeAttribute(Const.SESSION_SECURITY_CODE);
                //查询用户有几个职位
                List<PageData> postList = this.userService.getUserPostByUserName(d.getString("USER_NAME").trim());
                if (1 < postList.size()) {
                    errInfo = "hasMorePost";
                } else if (1 == postList.size()) {
                    PageData mapData = postList.get(0);
                    map.put("postId", mapData.get("POST_ID").toString());
                }
                session.setAttribute(Const.SESSION_POST_NUMBER, postList.size());
                session.setAttribute(Const.SESSION_POST_LIST, postList);
                //shiro加入身份验证
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(d.getString("USER_NAME"), d.getString("PASSWORD"));
                try {
                    //登录
                    subject.login(token);
                } catch (AuthenticationException e) {
                    errInfo = "身份验证失败！";
                }
            } else {
                //用户名或密码有误
                errInfo = "usererror";
            }
            if (Tools.isEmpty(errInfo)) {
                //验证成功
                errInfo = "success";
            }*/
        } else {
          //获取前台传过来参数
            String KEYDATA[] = pd.getString("KEYDATA").replaceAll("qq313596790fh", "").replaceAll("QQ978336446fh", "").split(",fh,");
            if (null != KEYDATA && KEYDATA.length == 3) {
                //shiro管理的session
                Session session = SecurityUtils.getSubject().getSession();
                //获取session中的验证码
                String sessionCode = (String) session.getAttribute(Const.SESSION_SECURITY_CODE);
                //取前台穿过来的验证码
                String code = KEYDATA[2];
                if (null == code || "".equals(code)) {
                    errInfo = "nullcode"; //验证码为空
                } else {
                    String USERNAME = KEYDATA[0];
                    String PASSWORD = KEYDATA[1];
                    pd.put("userName", USERNAME.trim());
                    //校验验证码
                    if (Tools.notEmpty(sessionCode) && sessionCode.equalsIgnoreCase(code)) {
                        //密码加密
                        String passwd = new SimpleHash("SHA-1", USERNAME, PASSWORD).toString();
                        pd.put("passwd", passwd.trim());
                        //根据用户名和密码获取用户信息
                        pd = this.userService.getUserByNameAndPwd(pd);
                        if (pd != null) {
                            //设置用户信息
                            User user = new User();
                            user.setUSER_ID(Long.parseLong(pd.get("USER_ID").toString()));//用户ID
                            user.setUSER_NAME(pd.getString("USER_NAME"));//登录名
                            user.setNAME(pd.getString("NAME"));//中文名
                            user.setDEALER_ID(Long.parseLong(pd.get("DEALER_ID").toString()));
                            user.setUSER_TYPE(Integer.parseInt(pd.get("USER_TYPE").toString()));
                            user.setIS_MAIN_COUNT(Integer.parseInt(pd.get("IS_MAIN_COUNT").toString()));
                            user.setSKIN("default");//皮肤
                            session.setAttribute(Const.SESSION_USER, user);
                            session.setAttribute(Const.SESSION_USERNAME, user.getUSER_NAME());
                            //清楚验证码
                            session.removeAttribute(Const.SESSION_SECURITY_CODE);
                            //查询用户有几个职位
                            List<PageData> postList = this.userService.getUserPostByUserName(USERNAME.trim());
                            if (1 < postList.size()) {
                                errInfo = "hasMorePost";
                            } else if (1 == postList.size()) {
                                PageData mapData = postList.get(0);
                                map.put("postId", mapData.get("POST_ID").toString());
                            }
                            session.setAttribute(Const.SESSION_POST_NUMBER, postList.size());
                            session.setAttribute(Const.SESSION_POST_LIST, postList);
                            //shiro加入身份验证
                            Subject subject = SecurityUtils.getSubject();
                            UsernamePasswordToken token = new UsernamePasswordToken(USERNAME, PASSWORD);
                            try {
                                //登录
                                subject.login(token);
                            } catch (AuthenticationException e) {
                                errInfo = "身份验证失败！";
                            }
                        } else {
                            //用户名或密码有误
                            errInfo = "usererror";
                        }
                    } else {
                        //验证码输入有误
                        errInfo = "codeerror";
                    }
                    if (Tools.isEmpty(errInfo)) {
                        //验证成功
                        errInfo = "success";
                    }
                }
            } else {
                errInfo = "error"; //缺少参数
            }
        }
        map.put("result", errInfo);
        return AppUtil.returnObject(new PageData(), map);
    }

    /**
     * 当职位有多个时，跳转到职位的页面
     * */
    @RequestMapping(value = "/main/post/postList")
    public ModelAndView postList() {
        ModelAndView mv = this.getModelAndView();
        Session session = SecurityUtils.getSubject().getSession();
        mv.addObject("postList", session.getAttribute(Const.SESSION_POST_LIST));
        mv.setViewName("system/admin/post");
        return mv;
    }

    /**
     * 访问系统首页,同时查询用户的权限
     * @throws Exception 
     */
    @RequestMapping(value = "/main/post/admin")
    public ModelAndView login_index() throws Exception {
        //获取数据
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        //职位ID
        String postId = pd.getString("changeMenu");
        if (Tools.isEmpty(postId)) {
            mv.setViewName("system/admin/login");
            return mv;
        }
        try {
            //根据职位ID获取机构代码
            PageData postData = this.menuService.getPostByPostId(Long.parseLong(postId));
            SecurityUtils.getSubject().getSession().setAttribute("postName", postData.get("POST_NAME"));
            SecurityUtils.getSubject().getSession().setAttribute("postId", postId);
            //shiro管理的session
            Session session = SecurityUtils.getSubject().getSession();
            Object orgCode = postData.get("ORG_CODE");
            if (null == orgCode) {
                return mv;
            } else {
                session.setAttribute(Const.SESSION_DEALER_ID, orgCode.toString().trim()); // 服务站ID
            }
            Object postType = postData.get("POST_TYPE");
            if (null == postType) {
                return mv;
            } else {
                session.setAttribute(Const.SESSION_ORG_TYPE, postType.toString().trim()); // 机构类型
                // 机构级别
                if (SysDataCode.ORG_TYPE_DEPOT == Integer.parseInt(postType.toString())) {
                    if (Tools.notEmpty(Tools.checkObj(postData.get("ORG_LEVEL")))) {
                        session.setAttribute(Const.SESSION_ORG_LEVEL, postData.get("ORG_LEVEL").toString().trim());
                    }
                } else {
                    //经销商的级别默认为4级
                    session.setAttribute(Const.SESSION_ORG_LEVEL, "3");
                }
            }
            User user = (User) session.getAttribute(Const.SESSION_USER);
            //获取职位个数ID 前台判断是否需要切换职位
            Object postNum = session.getAttribute(Const.SESSION_POST_NUMBER);
            mv.addObject("postNum", postNum);
            if (null != user) {
                //获取菜单List权限
                List<Menu> allmenuList = this.menuService.getMenuListByPostId(Long.parseLong(postId));
                for (Menu m : allmenuList) {
                    m.setHasMenu(true);
                    Long menuId = m.getMENU_ID();
                    pd.put("postId", postId);
                    pd.put("menuId", menuId);
                    //根据菜单ID去获取当前菜单下的子集
                    List<Menu> subMenuList = this.menuService.getMenuListByMenuId(pd);
                    //判断菜单下面是否还有子菜单
                    if (0 == subMenuList.size()) {
                        m.setHasMenu(false);
                        pd.put("menuSmall", menuId);
                        List<Button> BigButtons = this.menuService.getButtonListByMenuId(pd);
                        if (0 < BigButtons.size()) {
                            m.setButtons(BigButtons);
                        }
                    } else {
                        //有二级菜单
                        m.setHasMenu(true);
                        //设置子菜单
                        m.setSubMenu(subMenuList);
                      //根据用户名和菜单ID去获取当前菜单的按钮权限
                        for (Menu h : subMenuList) {
                            Long menId = h.getMENU_ID();
                            h.setHasMenu(true);
                            pd.put("menuSmall", menId);
                            List<Button> buttons = this.menuService.getButtonListByMenuId(pd);
                            if (0 < buttons.size()) {
                                h.setButtons(buttons);
                            }
                        }
                    }
                    //外层for循环结束
                }
                session.removeAttribute(Const.SESSION_allmenuList);
                session.setAttribute(Const.SESSION_allmenuList, allmenuList);
                mv.addObject("menuList", allmenuList);
            } else {
                mv.setViewName("system/admin/login");//session失效后跳转登录页面
            }
            mv.addObject("user", user);
            mv.setViewName("system/admin/index");
        } catch (Exception e) {
            mv.setViewName("system/admin/login");
            logger.error(e.getMessage(), e);
            throw new Exception(e.toString());
        }
        pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
        mv.addObject("pd", pd);
        return mv;
    }
    

    /**
     * 获取登录用户的IP
     * 
     * @throws Exception
     */
    public void getRemortIP(String USERNAME) throws Exception {
        PageData pd = new PageData();
        HttpServletRequest request = this.getRequest();
        String ip = "";
        if (request.getHeader("x-forwarded-for") == null) {
            ip = request.getRemoteAddr();
        } else {
            ip = request.getHeader("x-forwarded-for");
        }
        pd.put("USERNAME", USERNAME);
        pd.put("IP", ip);
        userService.saveIP(pd);
    }

    /**
     * 访问登录页
     * 
     * @return
     */
    @RequestMapping(value = "/login_toLogin")
    public ModelAndView toLogin() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
        mv.setViewName("system/admin/login");
        mv.addObject("pd", pd);
        mv.addObject("acgj", "1");
        return mv;
    }

    /**
     * 进入tab标签
     * 
     * @return
     */
    @RequestMapping(value = "/tab")
    public String tab() {
        return "system/admin/tab";
    }

    /**
     * 进入首页后的默认页面
     * 
     */
    @RequestMapping(value = "/login_default")
    public ModelAndView defaultPage() throws Exception {
        ModelAndView mv = this.getModelAndView();
        /*PageData pd = new PageData();
        pd = this.getPageData();
      //查询首页的数据
        //机构ID
        Long orgId = this.getDealerId();
        //机构级别
        String level = this.getLevel();
        pd.put("orgId", orgId);
        pd.put("level", level);
        pd.put("dataNum", 15); //查询多少天的数据
        pd.put("beginTime", DateUtil.formateDate(DateUtil.getDateBefore(new Date(), 15), "yyyy-MM-dd"));
        pd.put("endTime", DateUtil.formateDate(new Date(), "yyyy-MM-dd"));
        pd.put("sta", this.getOrgType());
        //查询首页总数数据
//        PageData ps = this.userService.searchIndexNumMsg(pd);
        //粉丝关注取消关注数据
        PageData allfans = this.userService.getFansAllNum(pd);
        mv.addObject("allfans", allfans);
        //绑定数据
        PageData VehicleBindNum = this.userService.getVehicleBindNum(pd);
        mv.addObject("VehicleBindNum", VehicleBindNum);
        //养修预约
        PageData ordNum = this.userService.getordNum(pd);
        mv.addObject("ordNum", ordNum);
        //服务活动
        PageData activityNum = this.userService.getactivityNum(pd);
        mv.addObject("activityNum", activityNum);
        //意见反馈
        PageData SuggestNum = this.userService.getSuggestNum(pd);
        mv.addObject("SuggestNum", SuggestNum);
        // 群发消息 
        PageData messageSendNum = this.userService.getmessageSendNum(pd);
        mv.addObject("messageSendNum", messageSendNum);
        // 会话消息 
        PageData sessionNum = this.userService.getsessionNum(pd);
        mv.addObject("sessionNum", sessionNum);
        // 自动提醒 
        PageData WarnNum = this.userService.getWarnNum(pd);
        mv.addObject("WarnNum", WarnNum);
        //接入数
        PageData dealerAccess = this.userService.getAccessTokenSuccess();
        //查询首页报表
        List<PageData> plist = this.userService.getIndexMsgList(pd);
        String json = JsonUtils.listToJson(plist);
//        mv.addObject("ps", ps);
        dealerAccess.put("ORG_TYPE", this.getOrgType());
        mv.addObject("dealerAccess", dealerAccess);
        mv.addObject("plist", json);*/
        mv.setViewName("system/admin/default");
        return mv;
    }

    /**
     * 用户注销
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout")
    public ModelAndView logout() {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();

        //shiro管理的session
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();

        session.removeAttribute(Const.SESSION_USER);
        session.removeAttribute(Const.SESSION_ROLE_RIGHTS);
        session.removeAttribute(Const.SESSION_allmenuList);
        session.removeAttribute(Const.SESSION_menuList);
        session.removeAttribute(Const.SESSION_QX);
        session.removeAttribute(Const.SESSION_userpds);
        session.removeAttribute(Const.SESSION_USERNAME);
        session.removeAttribute(Const.SESSION_USERROL);
        session.removeAttribute("changeMenu");

        //shiro销毁登录
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        pd = this.getPageData();
        String msg = pd.getString("msg");
        pd.put("msg", msg);

        pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
        mv.setViewName("system/admin/login");
        mv.addObject("pd", pd);
        return mv;
    }
    
    /**
     * 修改秘密页面
     * */
    @RequestMapping(value = "/toEditPassword")
    public ModelAndView toEditPassword() {
        ModelAndView mv = this.getModelAndView();
        // mv.addObject("msg", "success");
        mv.setViewName("system/admin/editePw");
        return mv;
    }
    
    /**
     * 检查原密码是否正确
     * @throws Exception 
     * */
    @RequestMapping(value = "/user/checkOldPass")
    @ResponseBody
    public JsonResult<String> checkOldPass() throws Exception {
        PageData pd =this.getPageData();
        Object pw = pd.get("oldPw");
        if (null == pw) {
            return new JsonResult<String>("0", "原始密码不能为空");
        }
        Object userName = SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_USERNAME);
        if (null == userName) {
            return new JsonResult<String>("0", "session超时，用户名丢失");
        }
        String passwd = new SimpleHash("SHA-1", userName.toString(), pw.toString()).toString();
        pd.put("oldPassword", passwd);
        pd.put("userName", userName);
        return this.userService.checkOldPass(pd);
    }
    
    /**
     * 修改密码
     * @throws Exception 
     * */
    @RequestMapping(value = "/user/updatePassword")
    @ResponseBody
    public JsonResult<String> updatePassword() throws Exception {
        PageData pd = this.getPageData();
        String password = pd.getString("password");
        if (Tools.isEmpty(password)) {
            return new JsonResult<String>("0", "密码不能为空");
        }
        Object userName = SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_USERNAME);
        if (null == userName) {
            return new JsonResult<String>("0", "session超时，用户名丢失");
        }
        String passwd = new SimpleHash("SHA-1", userName.toString(), password).toString();
        pd.put("password", passwd);
        pd.put("userName", userName);
        return this.userService.updatePassword(pd);
    }
   
}
