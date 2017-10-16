package com.fh.task;

import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.SchedulerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.fh.service.app.order.AppOrderTaskService;
import com.fh.util.PageData;
/**
 * 关闭货源进入待确认货源
 * @author ljie
 *
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class AppOrderLockTask  extends  QuartzJobBean  {
	 /**
     * 日志输出
     * */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		 SchedulerContext schCtx;
	        try {
	            schCtx = arg0.getScheduler().getContext();
	            ApplicationContext appCtx = (ApplicationContext) schCtx.get("applicationContext");
	            AppOrderTaskService server = (AppOrderTaskService) appCtx.getBean("appOrderTaskService");
	            this.AppOrderLock(server);
	        } catch (Exception e) {
	        	 e.printStackTrace();
	        	logger.error(e.toString());
	          
	        }
	}
	private void AppOrderLock(AppOrderTaskService server) {
		try {
			List<PageData> list = server.getAppOrderLockList();
			logger.info("---------定时任务获取到需待确认的结果集==="+list);
			for (PageData pd : list) {
				try {
					logger.info("定时任务获取到需待确认的结果集==="+pd);
					server.updateAppOrderLockStatusByOrderId(pd);
				} catch (Exception e) {
					e.printStackTrace();
					logger.info("订单处理异常-----------");
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("处理超时订单异常");
		}
	}


}
