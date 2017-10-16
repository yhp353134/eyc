package com.fh.util.sysdata;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.fh.entity.system.SysData;
import com.fh.service.common.CommonService;
import com.fh.util.BeanUtil;
import com.fh.util.PageData;
import com.infoservice.filestore.FileStore;


/**
 * @author ZhaoLi 字典表操作类
 */
public class CodeDict{
	private static CodeDict instance = null;
	public static Date dt = null;
	
	public static Map<String,List<SysData>> dictMap =  null;
	private static Logger logger = Logger.getLogger(CodeDict.class);
	private  static CommonService  commonService=null;
//	private static POFactory factory = POFactoryBuilder.getInstance();
//	ActionContext act = ActionContext.getContext();
	
	public static final String tc_code_sql = "select code_id,code_desc,code_desc_en,type,type_name from tc_code order by type, num";
	
	private CodeDict() {
	}

	public static CodeDict getInstance() {
		if (instance == null) {
			
			synchronized (CodeDict.class) {
				if (instance == null) {
					instance = new CodeDict();
				}
				if (commonService==null) {
					commonService =  (CommonService) BeanUtil.getBean("commonService");
				}
			}
		}
		return instance;
	}
	public String init() {
		 OutputStream output = null;
		try{
			PropertyResourceBundle configBundle = (PropertyResourceBundle) PropertyResourceBundle.getBundle("filePathDefine");
			String isOpen = (String) configBundle.getString("isOpen");
			List<SysData> codeList = null;
			if(dt==null || hasCodeUpdate()){
				//将数据字典写入内在中
				setDictMap();
				logger.info("数据字典写入了内在中,大小:"+dictMap.size());
				codeList = selCodeList();
			}
			byte[] codeJson = null;
			if(codeList != null){
				codeJson = toJsonFormat("var codeData = ", codeList);
			}
//			String fid = null;
			if(codeJson != null){
//				fid = FileStore.getInstance().write("codeData.js",codeJson);
				/**
				 * 将文件写到js文件目录下tc_code.js
				 * 当开关打开时，才加载
				 */
				if(null != isOpen && "1".equals(isOpen)){
					String path = this.getClass().getClassLoader().getResource("").getPath();
					path = path.substring(0, path.indexOf("WEB-INF"))+"static/js/common/";
					String tempFileDir = path + "sysData.js";
					output = new FileOutputStream(tempFileDir);
					output.write(codeJson);
				}
			}
			String codeJsUrl = null;
			/*if(fid != null){
				codeJsUrl = FileStore.getInstance().getDomainURL(fid);
				FileConstant.codeJsUrl = codeJsUrl;
				logger.info("codeJsUrl"+FileConstant.codeJsUrl);
				dt = new Date();
			}*/
			return "";
//			return fid;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			return null;
		}finally{
			try {
				if(null != output)
					output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static boolean hasCodeUpdate(){
		if(true)return true;
		String sql = "select count(code_id) result from tc_code where update_date > ?";
		List<Object> params = Arrays.asList(new Object[]{dt});
		List<Integer> rsList = null;
		boolean rs = false;
		try {
		     PageData pd = new PageData();
//			 pd = commonService.getSysDataCount(pd); 
		//	POContext.beginTxn(DBService.getInstance().getDefTxnManager(), -1);
			/*rsList = factory.select(sql, params, new DAOCallback<Integer>(){
				public Integer wrapper(ResultSet rs, int idx) {
					Integer result;
					try {
						result = rs.getInt("result");
					} catch (SQLException e) {
						logger.error("检查数据字典表是否更新,发生错误");
						throw new DAOException(e);
					}
					return result;
				}});*/
			if(rsList.get(0) > 0){
				logger.info("TC_CODE表有更新!");
			}else{
				logger.info("TC_CODE表没更新!");
			}
	//		POContext.endTxn(true);
			rs = rsList.get(0) > 0;
		} catch (Exception e) {
		//	POContext.endTxn(false);
		}finally{
			//POContext.cleanTxn();
		}
		return rs;
	}
	
	public static List<SysData> selCodeList(){
		List<SysData> codeList = null;
		try {
		//	POContext.beginTxn(DBService.getInstance().getDefTxnManager(), -1);
			PageData pd = new PageData();
			codeList = commonService.getSysDataCode(pd);
			/*codeList = factory.select(tc_code_sql, null, new DAOCallback<CodeBean>(){
				public CodeBean wrapper(ResultSet rs, int idx) {
					CodeBean bean = new CodeBean();
					try {
						bean.setCodeDesc(rs.getString("code_desc"));
						bean.setCodeId(rs.getString("code_id"));
						bean.setType(rs.getString("type"));
						bean.setCodeDescEn(rs.getString("code_desc_en"));
					} catch (SQLException e) {
						throw new DAOException(e);
					}
					return bean;
				}});*/
		//	POContext.endTxn(true);
		} catch (Exception e) {
//			POContext.endTxn(false);
		} finally{
			logger.info("执行了查询字典表的SQL: "+tc_code_sql);
			//POContext.cleanTxn();
		}
		return codeList;
	}
	
	@SuppressWarnings("unchecked")
	public static byte[] toJsonFormat(String pre, List list){
		HashMap<String,Object> pa = new HashMap<String,Object>();
		pa.put("data", list);
		JsonConverter jc = new JsonConverter();
		byte[] bytes = null;
		try {
//			logger.info(new String(jc.sourceToDest(pa),"UTF-8"));
			bytes =  (pre+new String(jc.sourceToDest(pa),"UTF-8")).getBytes("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
	/**
	 * 获得缓存中数据字典表
	 * add by 
	 * @return
	 */
	public static Map<String,List<SysData>> getDictMap(){
		return dictMap;
	}
	/**
	 * 将字典信息写入Map中
	 * add by
	 * @throws Exception 
	 */
	public  static  void setDictMap() throws Exception{
		List<SysData> codeList =   commonService.getSysDataCode(null);
//				factory.select(new TcCodePO()); // 字典表
		Map<String,List<SysData>> dictMapTemp = new TreeMap<String,List<SysData>>();
		String codeType = null;
		List<SysData> codeValueList = null;
		for(SysData code:codeList){
			codeType = code.getGroupname();
			if(dictMapTemp.containsKey(codeType)){
				codeValueList = dictMapTemp.get(codeType);
				codeValueList.add(code);
			}else{
				codeValueList = new LinkedList<SysData>();
				codeValueList.add(code);
				dictMapTemp.put(codeType, codeValueList);
			}
		}
		dictMap = dictMapTemp;
	}
	/**
	 * 根据数据字典的类型，返回这个类型对应的数据字典对象的集合
	 * @param type
	 * @return
	 */
	public static List<SysData> getDictListByType(String type){
		List<SysData> codeList = dictMap.get(type);
		return codeList;
	}
	/**
	 * 根据数据字典的类型，返回这个类型对应的数据字典对象的数组
	 * @param type
	 * @return
	 */
	public static SysData[] getDictArrayByType(String type){
		List<SysData> codeList = dictMap.get(type);
		SysData[] tcArray = new SysData[codeList.size()];
		return (SysData[]) codeList.toArray(tcArray);
	}
	/**
	 * 根据数据字典的类型返回这个类型对应的数据字典对象的CodeName的集合
	 * @param type
	 * @return
	 */
	public static List<String> getDictNameListByType(String type){
		List<SysData> codeList = dictMap.get(type);
		List<String> codeNameList = new LinkedList<String>();
		for(SysData code:codeList){
			codeNameList.add(code.getDataname());
		}
		return codeNameList;
	}
	/**
	 * 根据数据字典的类型返回这个类型对应的数据字典对象的CodeName的数组
	 * @param type
	 * @return
	 */
	public static String[] getDictNameArrayByType(String type){
		List<SysData> codeList = dictMap.get(type);
		List<String> codeNameList = new LinkedList<String>();
		for(SysData code:codeList){
			codeNameList.add(code.getDataname());
		}
		String[] arrays = new String[codeNameList.size()];
		return (String[])(codeNameList.toArray(arrays));
	}
	/**
	 * 根据数据字典类型和数据描述，返回数据字典的codeID
	 * @param type
	 * @param codeDesc
	 * @return
	 */
	public static String getDictCodeByName(String type,String codeDesc){
		List<SysData> codeList = dictMap.get(type);
		String codeId = null;
		for(SysData code:codeList){
			if(code.getDataname().equals(codeDesc)){
				codeId =  String.valueOf(code.getDatakey());
			}
		}
		return codeId;
	}
	
	/**
	 * 根据数据字典类型和数据描述，返回数据字典的codeID
	 * @param type
	 * @param codeDesc
	 * @return
	 */
	public static String getDictDescById(String type,String id){
		List<SysData> codeList = dictMap.get(type);
		String codeDesc = "";
		for(SysData code:codeList){
			if(String.valueOf(code.getDataname()).equals(id)){
				codeDesc = code.getDataname();
				/*if(Util.getLanguageType().equals(ConsCode.LANGUAGE_TYPE_ZH_CN))
					codeDesc =  code.getCodeDesc();
				else
					codeDesc=code.getCodeDescEn();*/
			}
		}
		return codeDesc;
	}
	
}
