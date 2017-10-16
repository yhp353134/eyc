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
 * 生成订单
 * @author ljie
 *
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class AppOrderGenerateTask  extends  QuartzJobBean  {
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
	            this.generateAppOrder(server);
	        } catch (Exception e) {
	        	logger.error(e.toString());
	            try {
	                throw new Exception(e.toString());
	            } catch (Exception e1) {
	                e1.printStackTrace();
	            }
	        }
	}
	private void generateAppOrder(AppOrderTaskService server) {
		try {
//			System.err.println("==================");
			List<PageData> list = server.getAppGenerateOrderList();
			logger.info("_=========定时任务获取到需生成订单的结果集==="+list);
			for (PageData pd : list) {
				try {
					logger.info("定时任务获取到==pd==需生成订单的结果集==="+pd);
					server.updateOrderStatusByOrderId(pd);
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
		
		///待确认状态   一小时自动生成订单
		//三个小时未报满  自动进入待确认状态  
	}

}
