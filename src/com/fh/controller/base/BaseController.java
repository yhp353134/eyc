package com.fh.controller.base;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fh.entity.Page;
import com.fh.entity.system.User;
import com.fh.util.Const;
import com.fh.util.Logger;
import com.fh.util.PageData;
import com.fh.util.SysDataCode;
import com.fh.util.Tools;
import com.fh.util.UuidUtil;

public class BaseController {

    protected Logger logger = Logger.getLogger(this.getClass());

    /**
     * 得到PageData
     */
    public PageData getPageData() {
        return new PageData(this.getRequest());
    }

    /**
     * 得到ModelAndView
     */
    public ModelAndView getModelAndView() {
        return new ModelAndView();
    }

    /**
     * 得到request对象
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        return request;
    }

    /**
     * 得到32位的uuid
     * 
     * @return
     */
    public Long get32UUID() {
        return UuidUtil.get32UUID();
    }

    /**
     * 得到分页列表的信息
     */
    public Page getPage() {

        return new Page();
    }

    /**
     * 获取登陆用户
     * 
     */
    public User getUser() {
        User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_USER);
        return currentUser;
    }

    /**
     * 获取用户所在的机构级别
     * 
     */
    public String getLevel() {
        String level = (String) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_ORG_LEVEL);
        return level;
    }

    /**
     * 判断是否为车厂用户登陆 true 表示车厂用户 false表示经销商用户
     * 
     * @return
     */
    public boolean IsOrg() {
        //车厂
        if (String.valueOf(SysDataCode.ORG_TYPE_DEPOT).equals(this.getOrgType())) {
            return true;
        }
        //服务站
        else {
            return false;
        }
    }

    /**
     * 获取经销商id
     * 
     * @return
     * @throws Exception 异常
     */
    public Long getDealerId() throws Exception {
        String DealerId = (String) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_DEALER_ID);
        if (Tools.isEmpty(DealerId)) {
            throw new Exception("不能获取经销商代码");
        }
        return Long.parseLong(DealerId);
    }

    /***
     * 获取职位ID
     * */
    public Long getPostId() throws Exception {
        String postId = (String) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_POST_ID);
        if (Tools.isEmpty(postId)) {
            throw new Exception("不能获取职位ID");
        }
        return Long.parseLong(postId);
    }

    /***
     * 获取职位名称
     * */
    public String getPostName() throws Exception {
        String postName = (String) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_POST_NAME);
        return postName;
    }

    /**
     * 获取机构类型
     * 
     */
    public String getOrgType() {
        String OrgType = (String) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_ORG_TYPE);
        return OrgType;
    }

    /***
     * 获取权限
     * */
    @SuppressWarnings("unchecked")
    public Map<String, String> getPermission() {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        return (Map<String, String>) session.getAttribute(Const.SESSION_QX);
    }

    public static void logBefore(Logger logger, String interfaceName) {
        logger.info("");
        logger.info("start");
        logger.info(interfaceName);
    }

    public static void logAfter(Logger logger) {
        logger.info("end");
        logger.info("");
    }

    /**
     * 获取当前用户的 dealer_id 或者org_id 车厂返回org_id 服务站返回dealer_id 同时返回登录的机构类型
     * 
     * @param pd
     * @return
     * @throws Exception
     */
    public PageData getPageDataDealerIdOrOrgType(PageData pd) throws Exception {
        String orgType = this.getOrgType();
        if (String.valueOf(SysDataCode.ORG_TYPE_DEALER).equals(orgType)) {
            pd.put("DEALER_ID", this.getDealerId());
        } else {
            pd.put("ORG_ID", this.getDealerId());
        }
        pd.put("ORG_TYPE", orgType);
        pd.put("ORG_LEVEL", this.getLevel());
        pd.put("USER_ID", this.getUser().getUSER_ID());
        return pd;
    }

}
