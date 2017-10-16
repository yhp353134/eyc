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
 * 撤销订单
 * 
 * @author ljie
 *
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class AppOrderRevokeTask extends QuartzJobBean {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		SchedulerContext schCtx;
		try {
			schCtx = arg0.getScheduler().getContext();
			ApplicationContext appCtx = (ApplicationContext) schCtx.get("applicationContext");
			AppOrderTaskService server = (AppOrderTaskService) appCtx.getBean("appOrderTaskService");
			this.AppOrderRevokeOrder(server);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());

		}
	}

	private void AppOrderRevokeOrder(AppOrderTaskService server) throws Exception {
	   List<PageData> list = 	server.getOrderRevokeList();
	   logger.info("=====定时任务需要撤销的结果集==="+list);
	   for (PageData pd : list) {
		try {
			logger.info("定时任务需要撤销=pd==的结果集==="+pd);
			server.updateAppOrderRevokeStatusByOrderId(pd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			logger.error("撤销订单定时任务错误！============================");
		}
	}
	}

}
