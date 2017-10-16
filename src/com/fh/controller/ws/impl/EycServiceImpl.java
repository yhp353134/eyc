package com.fh.controller.ws.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fh.controller.ws.EycService;
import com.fh.service.system.dealer.SourceGoodsService;
import com.fh.service.ws.EycWebservice;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.JsonUtils;
import com.fh.util.PageData;
import com.fh.util.PrimaryUtil;
import com.fh.util.Tools;

@WebService(targetNamespace = "http://eyc.service.com")
public class EycServiceImpl implements EycService {

    /**
     * 日志输出
     * */
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取上下文
     * */
    public ApplicationContext getApplicationContext() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession()
                .getServletContext());
        return ac;
    }

    /**
     * 传输货源接口 sendOwnerSourceDetail
     * @throws Exception 异常信息
     * */
	@Override
	public String sendOwnerSourceDetail(String sourceMsg) throws Exception {
		log.info("=====传输货源接口(sendOwnerSourceDetail)开始=====");
		//注入service
		SourceGoodsService sourceGoodsService = (SourceGoodsService) this.getApplicationContext().getBean("sourceGoodsService");
        EycWebservice tmp = (EycWebservice) this.getApplicationContext().getBean("eycWebservice");
        Map<String, Object> map = null; //存放返回数据
        try {
        	//先将传入的参数存入数据库
        	PageData logPd = new PageData();
        	Long logId = PrimaryUtil.getPrimary();
        	logPd.put("logId", logId);
        	logPd.put("logMsg", sourceMsg);
        	tmp.insertInterLog(logPd);
            //传入的参数不为空，则解析
            JSONObject job = JsonUtils.strToJson(sourceMsg);
            String jobStr = job.get("sourceList") == null ? "" : job.get("sourceList").toString();
            if (Tools.isEmpty(jobStr)) {
            	map = new HashMap<String, Object>(); //存放返回数据
                map.put(Const.CODE, "0");
                map.put(Const.MSG, "数据不能为空");
                return JsonUtils.mapToJson(map);
            }
            @SuppressWarnings("unchecked")
			List<Map<String, Object>> sourceList = (List<Map<String, Object>>) JsonUtils.strToJsonList(jobStr);
            if (sourceList.size()>10) {
            	 map = new HashMap<String, Object>(); //存放返回数据
            	 map.put(Const.CODE, "0");
                 map.put(Const.MSG, "数据不能为空");
                 return JsonUtils.mapToJson(map);
            }
            List<Map<String, Object>> successListMap = new ArrayList<Map<String,Object>>();  // 成功的数据list
            //先校验
            for (int i=0;i<sourceList.size();i++) {
            	Map<String, Object> oneMap = sourceList.get(i);  // 接收一天传过来的数据
            	Map<String, Object> singleMap = new HashMap<String, Object>(); //存一条正确的数据
            	//货主电话
            	String ownerPhone = Tools.checkObj(oneMap.get("OWNER_PHONE"));
            	if (Tools.isEmpty(ownerPhone)) {
            		map = new HashMap<String, Object>();
               	 	map.put(Const.CODE, "0");
                    map.put(Const.MSG, "货主电话不能为空");
                    return JsonUtils.mapToJson(map);
            	}
            	PageData ownerPd =sourceGoodsService.getSourceOwnerMsg(Tools.replaceSpaceAll(ownerPhone));
            	if (null==ownerPd || ownerPd.size()==0 || ownerPd.isEmpty()) {
            		map = new HashMap<String, Object>();
               	 	map.put(Const.CODE, "0");
                    map.put(Const.MSG, "货主："+ownerPhone+"在系统中不存在");
                    return JsonUtils.mapToJson(map);
            	} else {
            		singleMap.put("OWNER_PHONE", ownerPhone);
            		singleMap.put("DEALER_ID", ownerPd.get("DEALER_ID"));
            		singleMap.put("OWNER_USER_ID", ownerPd.get("USER_ID"));
            		singleMap.put("OWNER_NAME", ownerPd.get("NAME"));
            	}
            	//起始城市
            	String beginCity = Tools.checkObj(oneMap.get("BEGIN_CITY"));
            	if(Tools.isEmpty(beginCity)) {
            		map = new HashMap<String, Object>();
               	 	map.put(Const.CODE, "0");
                    map.put(Const.MSG, "起始城市不能为空");
                    return JsonUtils.mapToJson(map);
            	}
            	PageData beginPd = sourceGoodsService.getCityMsgByName(Tools.replaceSpaceAll(beginCity));
            	if (null == beginPd || 0 == beginPd.size() || beginPd.isEmpty()) {
            		map = new HashMap<String, Object>();
               	 	map.put(Const.CODE, "0");
                    map.put(Const.MSG, "起始城市在系统不存在");
                    return JsonUtils.mapToJson(map);
            	} else {
            		singleMap.put("BEGIN_CITY", beginCity);
            		singleMap.put("BEGIN_CITY_ID", beginPd.get("REGION_CODE"));
            	}
            	//到达城市
            	String endCity = Tools.checkObj(oneMap.get("END_CITY"));
            	if(Tools.isEmpty(endCity)) {
            		map = new HashMap<String, Object>();
               	 	map.put(Const.CODE, "0");
                    map.put(Const.MSG, "到达城市不能为空");
                    return JsonUtils.mapToJson(map);
            	}
            	PageData endPd = sourceGoodsService.getCityMsgByName(Tools.replaceSpaceAll(endCity));
            	if (null == endPd || 0 == endPd.size() || endPd.isEmpty()) {
            		map = new HashMap<String, Object>();
               	 	map.put(Const.CODE, "0");
                    map.put(Const.MSG, "到达城市在系统中不存在");
                    return JsonUtils.mapToJson(map);
            	} else {
            		singleMap.put("END_CITY", endCity);
            		singleMap.put("END_CITY_ID", endPd.get("REGION_CODE"));
            	}
            	singleMap.put("BEGIN_CITY_ADDR", Tools.checkObj(oneMap.get("BEGIN_CITY_ADDR")));
            	singleMap.put("END_CITY_ADDR", Tools.checkObj(oneMap.get("END_CITY_ADDR")));
            	//期望交车时间
            	String expectDate = Tools.checkObj(oneMap.get("EXPECT_DEAL_DATE"));
            	if (Tools.isEmpty(expectDate)) {
            		map = new HashMap<String, Object>();
               	 	map.put(Const.CODE, "0");
                    map.put(Const.MSG, "期望交车时间不能为空");
                    return JsonUtils.mapToJson(map);
            	} else {
            		singleMap.put("EXPECT_DEAL_DATE", DateUtil.fomatDateByInterface(expectDate, "yyyy-MM-dd"));
            	}
            	//期望运达时间
            	String expectSend = Tools.checkObj(oneMap.get("EXPECT_SEND_DATE"));
            	if (Tools.isEmpty(expectSend)) {
            		map = new HashMap<String, Object>();
               	 	map.put(Const.CODE, "0");
                    map.put(Const.MSG, "期望运达时间不能为空");
                    return JsonUtils.mapToJson(map);
            	} else {
            		singleMap.put("EXPECT_SEND_DATE", DateUtil.fomatDateByInterface(expectSend, "yyyy-MM-dd"));
            	}
            	//指导价
            	String price = Tools.checkObj(oneMap.get("EXPECT_PRICE"));
            	if (Tools.isEmpty(price)) {
            		map = new HashMap<String, Object>();
               	 	map.put(Const.CODE, "0");
                    map.put(Const.MSG, "期望运费不能为空");
                    return JsonUtils.mapToJson(map);
            	} else {
            		if (Tools.checkNumber(price)) {
            			singleMap.put("EXPECT_PRICE", Tools.parseDubleNum(price,2));
            		} else {
            			map = new HashMap<String, Object>();
                   	 	map.put(Const.CODE, "0");
                        map.put(Const.MSG, "期望运费必须时数字类型");
                        return JsonUtils.mapToJson(map);
            		}
            	}
            	//是否含税
            	String isTax =  Tools.checkObj(oneMap.get("IS_TAX"));
            	if (Tools.isEmpty(isTax)) {
            		map = new HashMap<String, Object>();
               	 	map.put(Const.CODE, "0");
                    map.put(Const.MSG, "是否含税不能为空");
                    return JsonUtils.mapToJson(map);
            	} else {
            		if (Tools.checkNumber(isTax)) {
            			singleMap.put("IS_TAX", isTax);
            		} else {
            			map = new HashMap<String, Object>();
                   	 	map.put(Const.CODE, "0");
                        map.put(Const.MSG, "是否含税必须时数字类型");
                        return JsonUtils.mapToJson(map);
            		}
            	}
            	//车型
            	String modelName = Tools.checkObj(oneMap.get("MODEL_NAME"));
            	if (Tools.isEmpty(modelName)) {
            		map = new HashMap<String, Object>();
               	 	map.put(Const.CODE, "0");
                    map.put(Const.MSG, "车型不能为空");
                    return JsonUtils.mapToJson(map);
            	}
            	PageData modelPd = sourceGoodsService.getModelMsgByName(Tools.replaceSpaceAll(modelName));
            	if (null == modelPd || 0 == modelPd.size() || modelPd.isEmpty()) {
            		map = new HashMap<String, Object>();
               	 	map.put(Const.CODE, "0");
                    map.put(Const.MSG, "车型在库中不存在");
                    return JsonUtils.mapToJson(map);
            	} else {
            		singleMap.put("MODEL_ID", modelPd.get("ID"));
            		singleMap.put("MODEL_NAME", modelPd.get("NAME"));
            		singleMap.put("MODEL_CODE", modelPd.get("CODE"));
            	}
            	singleMap.put("VIN", Tools.checkObj(oneMap.get("VIN"))); //vin码
            	singleMap.put("REMARK", Tools.checkObj(oneMap.get("REMARK"))); //备注
            	//发货地联系信息
            	String sendUser = Tools.checkObj(oneMap.get("SEND_USER"));
            	String sendUserPhone = Tools.checkObj(oneMap.get("SEND_USER_PHONE"));
            	if(Tools.isEmpty(sendUserPhone)) {
            		map = new HashMap<String, Object>();
               	 	map.put(Const.CODE, "0");
                    map.put(Const.MSG, "发货地联系人姓名或者电话不能为空");
                    return JsonUtils.mapToJson(map);
            	}
            	singleMap.put("SEND_USER", Tools.replaceSpaceAll(sendUser));
            	singleMap.put("SEND_USER_PHONE", Tools.replaceSpaceAll(sendUserPhone));
            	// 接收地联系人信息
            	String recieveUser = Tools.checkObj(oneMap.get("RECEVICE_USER"));
            	String recieveUserPhone = Tools.checkObj(oneMap.get("RECEVICE_USER_PHONE"));
            	if(Tools.isEmpty(recieveUserPhone)) {
            		map = new HashMap<String, Object>();
               	 	map.put(Const.CODE, "0");
                    map.put(Const.MSG, "接收地联系人姓名或者电话不能为空");
                    return JsonUtils.mapToJson(map);
            	}
            	singleMap.put("RECEVICE_USER", Tools.replaceSpaceAll(recieveUser));
            	singleMap.put("RECEVICE_USER_PHONE", Tools.replaceSpaceAll(recieveUserPhone));
            	tmp.checkUserIncontact(singleMap);
            	successListMap.add(singleMap);
            }
        	//logId 方便将数据改成成功状态
            tmp.sendOwnerSourceDetail(successListMap,logId);
            //代表成功了
            map = new HashMap<String, Object>(); //存放返回数据
            map.put(Const.CODE, "1");
            map.put(Const.MSG, "处理成功");
        } catch (Exception e) {
        	map = new HashMap<String, Object>(); //存放返回数据
            map.put(Const.CODE, "0");
            map.put(Const.MSG, "excption："+e.toString());
            log.error(e.toString());
            return JsonUtils.mapToJson(map);
        }
        return JsonUtils.mapToJson(map);
	}

    //eycServerImpl类结束        
}
