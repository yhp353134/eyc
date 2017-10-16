package com.fh.service.system.request;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.system.SysRequestLog;

/**
 * 请求日志记录
 * @author chensh
 * @date 2017-03-14
 * 
 */
@SuppressWarnings("unchecked")
@Service("requestLogService")
public class RequestLogService {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	public void save(SysRequestLog reqlog)throws Exception{
		dao.save("RequestLogMapper.insertRequestLog", reqlog);
	}

}
