package com.fh.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取sping bean工具类
 * 
 * @author chensh
 * 
 */
public class BeanUtil implements ApplicationContextAware{
	private static ApplicationContext ct;

	private static ApplicationContext ctWeb;

	private static Logger logger = Logger.getLogger(BeanUtil.class);

	/**
	 * WEB容器方式得到sping容器bean
	 * 
	 * @param sc
	 * @param strName
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ct = applicationContext;
		logger.info(ct);
    }

	public static ApplicationContext getApplicationContext() {
        return ct;
    }

	/**
	 * 对于运行在WEB容器中的程序不要用这个,不能事务控制
	 * 
	 * @param strName
	 *            bean名称
	 * @return
	 */
	public static Object getBean(String beanName) {
        return ct.getBean(beanName);
    }

	
}
