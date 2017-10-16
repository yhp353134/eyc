package com.fh.interceptor;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fh.entity.system.Button;
import com.fh.entity.system.Menu;
import com.fh.entity.system.SysRequestLog;
import com.fh.entity.system.User;
import com.fh.service.system.request.RequestLogService;
import com.fh.util.Const;
import com.fh.util.Constants;
import com.fh.util.Tools;
import com.fh.util.UuidUtil;

/**
 * 
 * 类名称：LoginHandlerInterceptor.java 类描述：
 * 
 * @author FH 作者单位： 联系方式： 创建时间：2015年1月1日
 * @version 1.6
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {
	public static final ThreadLocal<SysRequestLog> REQRESLOG = new NamedThreadLocal<SysRequestLog>("SysRequestLog");

	/**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger loggger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * <p>
     * Field als: 日志服务
     * </p>
     */
    @Autowired
    private RequestLogService rls;
	
    @SuppressWarnings({ "unchecked" })
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        SysRequestLog log = new SysRequestLog();
        log.setBeginTime(new Date());
        //System.out.println("======path=="+path);
        if (path.matches(Const.NO_INTERCEPTOR_PATH) || request.getRequestURI().indexOf(".txt")>=0 ) {
//        	loggger.info("访问路径======"+path);
        	REQRESLOG.set(log);
        	return true;
        } else {
        	REQRESLOG.set(log);
        	
            //shiro管理的session
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession();
            User user = (User) session.getAttribute(Const.SESSION_USER);
            if (user != null) {
                //判断是否拥有当前点击菜单的权限（内部过滤,防止通过url进入跳过菜单权限）
                List<Menu> menuList = (List<Menu>) session.getAttribute(Const.SESSION_allmenuList); //获取菜单列表
                path = path.substring(1, path.length());//获取当前点击页面
                //切换职位时时空的，所以应该绕过去
                if (null == menuList) {
                    return true;
                }
                for (int i = 0; i < menuList.size(); i++) {
                    if (null != menuList.get(i).getSubMenu()) {
                        //二级菜单
                        for (int j = 0; j < menuList.get(i).getSubMenu().size(); j++) {
                            String urls = menuList.get(i).getSubMenu().get(j).getNEMU_URL();
                            if (Tools.isEmpty(urls)) {
                                //待做
                            } else {
                                //找到当前页面，然后获取该页面对应的按钮权限
                                if (menuList.get(i).getSubMenu().get(j).getNEMU_URL().split(".do")[0].equals(path
                                        .split(".do")[0])) {
                                    if (!menuList.get(i).getSubMenu().get(j).isHasMenu()) {//判断有无此菜单权限
                                        response.sendRedirect(request.getContextPath() + Const.LOGIN);
                                        return false;
                                    } else {
                                        //按钮判断
                                        Map<String, String> map = new HashMap<String, String>();
                                        //通过menuid和登录用户获取该用户对应的菜单按钮权限
                                        List<Button> buttonList = menuList.get(i).getSubMenu().get(j).getButtons();
                                        if (null != buttonList) {
                                            for (Button bs : buttonList) {
                                                map.put(bs.getBUT_CODE(), bs.getBUT_VALUE());
                                            }
                                        }
                                        session.removeAttribute(Const.SESSION_QX);
                                        session.setAttribute(Const.SESSION_QX, map); //重新分配按钮权限
                                    }
                                }
                            }
                            //二级菜单循环结束
                        }
                    } else {
                        //因为一级菜单也有URL  所以需要在没有子菜单的情况下设置一级菜单的按钮权限
                        String bigrul = menuList.get(i).getNEMU_URL();
                        if (Tools.isEmpty(bigrul)) {
                            //待做
                        } else {
                            if (menuList.get(i).getNEMU_URL().split(".do")[0].equals(path.split(".do")[0])) {
                                Map<String, String> mapSmall = new HashMap<String, String>();
                                List<Button> buttonListSmall = menuList.get(i).getButtons();
                                if (null != buttonListSmall) {
                                    for (Button bs : buttonListSmall) {
                                        mapSmall.put(bs.getBUT_CODE(), bs.getBUT_VALUE());
                                    }
                                }
                                session.removeAttribute(Const.SESSION_QX);
                                session.setAttribute(Const.SESSION_QX, mapSmall); //重新分配按钮权限
                            }
                        }
                    }
                }
                return true;
            } else {
                //登陆过滤，调整到登录
                response.sendRedirect(request.getContextPath() + Const.LOGIN);
                return false;
            }
        }
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {


    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    	
    	SysRequestLog log = REQRESLOG.get();
    	int logflag = request.getRequestURI().indexOf(".do");
	    if (log != null && logflag>=0) {
	    	log.setLogId(UuidUtil.get32UUID()); // 请求 ID
	    	log.setEndTime(new Date());// 结束时间
	    	long rt = log.getEndTime().getTime() - log.getBeginTime().getTime();
	    	log.setRequestTime(Integer.parseInt(rt+"")); // 请求耗时
	    	try {
	            log.setLoginName((String) SecurityUtils.getSubject().getPrincipal()); // 用户名
	            log.setSessionId(SecurityUtils.getSubject().getSession().getId().toString()); // 会话ID
	        } catch (UnavailableSecurityManagerException e) {
	            this.loggger.info("UnavailableSecurityManagerException error");
	        }
	    	
	    	log.setScheme(request.getScheme()); // 返回协议名称，http
	        log.setServerName(request.getServerName()); // 获得服务器名，本地IP
	        log.setServerPort(Integer.valueOf(request.getServerPort())); // 获取服务器端口号
	        log.setRequestUri(request.getRequestURI()); // 请求路径
	        log.setMethod(request.getMethod()); // 请求方法 POST,GET,等
	        log.setRequestParams(getRequestParamers(request)); // 请求参数
	        if (!Tools.isEmpty(log.getRequestParams()) && log.getRequestParams().length() > 2500) {
                log.setRequestParams(log.getRequestParams().substring(0, 2500));
            }
	        log.setMobilePat(request.getRequestURI().indexOf("weixin")>0 ?1 :0); // 是否移动端访问
	        log.setCharacterEncoding(request.getCharacterEncoding()); // 返回网页使用的编码，在网页的charset中的值
	        log.setContentLength(Integer.valueOf(request.getContentLength())); // 只用于POST请求，表示所发送数据的字节数
	        log.setLocalAddr(request.getLocalAddr()); // 获取本地地址，根据访问方法不同而不同，为127.0.0.1或者公网ip
	        log.setLocalName(request.getLocalName()); // 获取本地IP的名称，如127.0.0.1就是localhost
	        log.setLocalPort(Integer.valueOf(request.getLocalPort())); // 获取端口号)
	        
	        // JVM信息
	        // 最大内存
	        log.setJvmMaxMemory(Integer.valueOf(Runtime.getRuntime().maxMemory()/Constants.NUMBER_1024/Constants.NUMBER_1024 +""));
	        // 已分配内存
	        log.setJvmTotalMemory(Integer.valueOf(Runtime.getRuntime().totalMemory() / Constants.NUMBER_1024 / Constants.NUMBER_1024+""));
	        // 已分配内存中的剩余空间
	        log.setJvmFreeMemory(Integer.valueOf(Runtime.getRuntime().freeMemory() / Constants.NUMBER_1024 / Constants.NUMBER_1024+""));
	        // 最大可用内存
	        log.setJvmUserMemory(log.getJvmMaxMemory() - log.getJvmTotalMemory() + log.getJvmFreeMemory());
	        
	    	
	    	log.setCreateDate(new Date());
            this.rls.save(log);
	    }
	    // 清理
        REQRESLOG.remove();
    	
    }
    
    
    /**
     * 获得请求参数信息
     * 
     * @param request 请求对象
     * @return 请求参数
     */
    private static String getRequestParamers(HttpServletRequest request) {
        StringBuilder params = new StringBuilder();
        @SuppressWarnings("rawtypes")
        Enumeration names = request.getParameterNames();
        params.append("?");
        while (names.hasMoreElements()) {
            String paramName = names.nextElement().toString();
            String[] values = request.getParameterValues(paramName);
            if (values.length == 1) {
                params.append(paramName + "=" + values[0] + "&");
            } else {
                for (int i = 0; i < values.length; i++) {
                    params.append(paramName + "[" + i + "]=" + values[i] + "&");
                }
            }
        }
        return params.toString().substring(0, params.length() - 1);
    }

}
