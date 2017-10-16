package com.fh.service.app.order;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.util.PageData;

@Service("appOrderTaskService")
public class AppOrderTaskService {

	@Resource(name = "daoSupport")
    private DaoSupport dao;

	@SuppressWarnings("unchecked")
	public List<PageData> getAppGenerateOrderList() throws Exception {
		return (List<PageData>) dao.findForList("AppOrderTaskMapper.getAppGenerateOrderList", null);
	}

	public void updateOrderStatusByOrderId(PageData pd) throws Exception {
		PageData data = this.getOrderPriceByOrderId(pd);
		//获取车辆信息和user电话
		PageData Evehicle =this.getEvehicleByDriverId(data);
		data.put("DRIVER_PHONE", Evehicle.get("TEL"));
		data.put("VEHICLE_ID", Evehicle.get("VEHICLE_ID"));
		this.updateOrderPriceByOrderId(data);
		//修改订单
		dao.update("AppOrderTaskMapper.updateOrderStatusByOrderId", data);
	}
	/**
	 * 根据司机id获取车辆信息
	 * @param data
	 * @return
	 * @throws Exception 
	 */
    private PageData getEvehicleByDriverId(PageData data) throws Exception {
    	
		return (PageData) dao.findForObject("AppOrderTaskMapper.getEvehicleByDriverId", data);
	}

	/**
     * 修改报价状态
     * @param pd
     * @throws Exception 
     */
	private void updateOrderPriceByOrderId(PageData pd) throws Exception {
		dao.update("AppOrderTaskMapper.updateOrderPriceByOrderId", pd);
	}

	private PageData getOrderPriceByOrderId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppOrderTaskMapper.getOrderPriceByOrderId", pd);
	}
    /**
     * 查询有报价的货源
     * @return
     * @throws Exception 
     */
	@SuppressWarnings("unchecked")
	public List<PageData> getAppOrderLockList() throws Exception {
		return (List<PageData>) dao.findForList("AppOrderTaskMapper.getAppOrderLockList", null);
	}
    /**
     * 修改订单状态为待确认状态
     * @param pd
     * @throws Exception 
     */
	public void updateAppOrderLockStatusByOrderId(PageData pd) throws Exception {
		  dao.update("AppOrderTaskMapper.updateAppOrderLockStatusByOrderId", pd);
	}
    /**
     * 获取三小时内没有报价的货源
     * @return
     * @throws Exception 
     */
	@SuppressWarnings("unchecked")
	public List<PageData> getOrderRevokeList() throws Exception {
		return (List<PageData>) dao.findForList("AppOrderTaskMapper.getOrderRevokeList", null);
	}
    /**
     * 修改为撤单状态
     * @param pd
     * @throws Exception 
     */
	public void updateAppOrderRevokeStatusByOrderId(PageData pd) throws Exception {
      dao.update("AppOrderTaskMapper.updateAppOrderRevokeStatusByOrderId", pd);		
	}
	
	
}
