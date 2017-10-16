package com.fh.service.app.location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.util.AppConstant;
import com.fh.util.AppUtil;
import com.fh.util.PageData;
import com.fh.util.Tools;

@Service("appLocationService")
public class AppLocationService {

	@Resource(name = "daoSupport")
    private DaoSupport dao;
    /**
     * 订单位置上报
     * @param pd
     * @throws Exception 
     */
	public Map<String, Object> locationReport(PageData pd) throws Exception {
//        String orderId = pd.getString("order_id");		
//        String user_id = pd.getString("user_id");	
        PageData data =  this.getSysUserByUserId(pd);
//        String[] order_ids = orderId.split(",");
//        pd.put("order_ids", order_ids);
        pd.put("project_name", data.getString("USER_NAME"));
        pd.put("project_code", data.getString("NAME"));
        List<PageData> orderList = this.getEOrderByOrderIdsAndstatus(pd);
        Map<String, Object> map = new HashMap<String,Object>();
        if (CollectionUtils.isEmpty(orderList)||orderList.size()<=0) {
        	map.put("status", AppConstant.ERR_CODE_1015); 
        	map.put("msg", AppUtil.getMessageInfoByKey(AppConstant.ERR_CODE_1015));
			return  map;
		}
        String[] listToStr = Tools.ListToStr(orderList, "ORDER_ID", ",");
        pd.put("orderList", listToStr);
        dao.save("AppLocationMapper.saveLocationAddress", pd);
        
        map.put("status", "1000");
        map.put("msg", AppUtil.getMessageInfoByKey("1000"));
		map.put("result", "");
		return  map;
	}
	/**
	 * 根据user_id  查询用户
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getSysUserByUserId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppLocationMapper.getSysUserByUserId", pd);
	}
	/**
	 * 
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getEOrderByOrderIdsAndstatus(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppLocationMapper.getEOrderByOrderIdsAndstatus", pd);
	}
	
	
	
}
