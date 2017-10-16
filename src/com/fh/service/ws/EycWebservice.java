package com.fh.service.ws;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.service.system.dealer.SourceGoodsService;
import com.fh.util.Logger;
import com.fh.util.PageData;
import com.fh.util.Tools;

@Service("eycWebservice")
@Configuration
@EnableAsync
public class EycWebservice {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name = "sourceGoodsService")
	private SourceGoodsService sourceGoodsService;

	/**插入货源 **/
	public void sendOwnerSourceDetail(List<Map<String, Object>> sourceList,Long logId) throws Exception {
		for (int i=0;i<sourceList.size();i++) {
			Map<String, Object> map  = sourceList.get(i);
			dao.save("WebserviceMapper.insertSourceMsg", map);
		}
		//修改当前货源的状态
		dao.update("WebserviceMapper.updateInterLog", logId);
	}
	
	/**查询货主是否存在 **/
	public PageData getOwnerCount(String ownerPhone) throws Exception {
		return this.sourceGoodsService.getSourceOwnerMsg(ownerPhone);
	}
	
	/**
	 * 校验联系系在库中是否存在，不存在就添加
	 * **/
	@SuppressWarnings("unchecked")
	@Async
	public synchronized void checkUserIncontact(Map<String, Object> singleMap) throws Exception{
		//发货人
		String sendUser = Tools.checkObj(singleMap.get("SEND_USER"));
		String sendUserPhone = Tools.checkObj(singleMap.get("SEND_USER_PHONE"));
		singleMap.put("TMP_NAME", Tools.replaceSpaceAll(sendUser));
		singleMap.put("TMP_PHNOE", Tools.replaceSpaceAll(sendUserPhone));
		//查询当前用户是否存在
		List<PageData> isUsers = (List<PageData>)dao.findForList("WebserviceMapper.checkUserIncontact", singleMap);
		if (null == isUsers || "null".equals(isUsers) || isUsers.isEmpty() || isUsers.size()==0) {
			//用户不存在，就新增
			dao.save("WebserviceMapper.insertUserIncontact", singleMap);
		}
		//接收人
		String recevieUser = Tools.checkObj(singleMap.get("RECEVICE_USER"));
		String recevieUserPhone = Tools.checkObj(singleMap.get("RECEVICE_USER_PHONE"));
		singleMap.put("TMP_NAME", Tools.replaceSpaceAll(recevieUser));
		singleMap.put("TMP_PHNOE", Tools.replaceSpaceAll(recevieUserPhone));
		//查询当前用户是否存在
		List<PageData> isUsersTwo = (List<PageData>)dao.findForList("WebserviceMapper.checkUserIncontact", singleMap);
		if (null == isUsersTwo || "null".equals(isUsersTwo) || isUsersTwo.isEmpty() || isUsersTwo.size()==0) {
			//用户不存在，就新增
			dao.save("WebserviceMapper.insertUserIncontact", singleMap);
		}
	}
	
	/**插入日志 **/
	@Async
	public void insertInterLog(PageData pd) throws Exception {
		dao.save("WebserviceMapper.insertInterLog", pd);
	}
	
}
