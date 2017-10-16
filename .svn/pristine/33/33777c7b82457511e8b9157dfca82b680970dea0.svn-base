package com.fh.service.common;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.organization.BaseDealer;

@Service("baseDealerService")
public class BaseDealerService {

    @Resource(name = "daoSupport")
    private DaoSupport dao;
    
    /**
     * <p>Description: 根据经销商ID获取经销商信息</p>
     * @param dealerId
     * @return
     * @throws Exception 
     */
    public BaseDealer getBaseDealerById (Long dealerId) throws Exception{
        return (BaseDealer) dao.findForObject("BaseDealerMapper.selectByPrimaryKey", dealerId);
    }
    public BaseDealer getBaseDealerByAppid (String appId) throws Exception{
        return (BaseDealer) dao.findForObject("BaseDealerMapper.selectByAppid", appId);
    }
    
    public BaseDealer getBaseDealerByUserName (String userName) throws Exception{
        return (BaseDealer) dao.findForObject("CommonMapper.getBaseDealerByUserName", userName);
    }
    
    
}
