package com.fh.service.app.appcommon;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fh.dao.DaoSupport;
import com.fh.service.common.CommonService;
import com.fh.util.Logger;
import com.fh.util.PageData;
import com.fh.util.PrimaryUtil;
import com.fh.util.SmsUtil;
import com.fh.util.SysDataCode;
import com.fh.util.Tools;
import com.fh.util.oss.OSSUploadUtil;

@Service("appCommonService")
@EnableAsync
@Configurable
public class AppCommonService {
	private static Logger logger = Logger.getLogger(AppCommonService.class);
	@Resource(name = "daoSupport")
    private DaoSupport dao;
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
    /**
     * 文件上传
     * @param cf
     * @param request
     * @return
     * @throws Exception 
     */
	public String uploadFile(MultipartFile file, HttpServletRequest request) throws Exception {
		String filename = file.getOriginalFilename();
		String fileType = filename.substring(filename.lastIndexOf("."),filename.length()); 
		/***************************压缩文件  start*********************************************/
		InputStream is = file.getInputStream();
		//图片大于150k，则进行尺寸大小不变的压缩
		if(fileType.toLowerCase().equals("tiff") ||fileType.toLowerCase().equals("jpeg") ||fileType.toLowerCase().equals("jpg") || fileType.toLowerCase().equals("png") || fileType.toLowerCase().equals("bmp") || fileType.toLowerCase().equals("gif") ){
			
			BufferedImage bi;
			bi = Thumbnails.of(is).scale(1).asBufferedImage();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		    ImageOutputStream imageOutput = ImageIO.createImageOutputStream(byteArrayOutputStream);
		    ImageIO.write(bi, fileType, imageOutput);
		    is = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			int filesize = is.available()/1024; //文件大小
			// System.out.println("=======000===is="+is.available());
			if(filesize>1024*2 ){
				double sc = (double)((float)150 /(float)filesize );
				bi = Thumbnails.of(is).scale(1).outputQuality(sc).asBufferedImage();
				byteArrayOutputStream = new ByteArrayOutputStream();
			    imageOutput = ImageIO.createImageOutputStream(byteArrayOutputStream);
			    ImageIO.write(bi, fileType, imageOutput);
			    is = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			    // System.out.println("==========is="+is.available());
			    filesize = is.available()/1024;
			    if(filesize>150){
			    	//图片大于2M，则进行尺寸大小的压缩，等比缩放
				    sc = (double)((float)1024*2 /(float)filesize );
					// System.out.println("==========sc="+sc);
					bi = Thumbnails.of(is).scale(sc).asBufferedImage();
					byteArrayOutputStream = new ByteArrayOutputStream();
				    imageOutput = ImageIO.createImageOutputStream(byteArrayOutputStream);
				    ImageIO.write(bi, fileType, imageOutput);
				    is = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
				    // System.out.println("=========11=is="+is.available());
			    }
			}
		}
		/***************************压缩文件  end*********************************************/
		
		String filepath = OSSUploadUtil.uploadFile(file);
//		FileStore 	fileStore = FileStore.getInstance();
//		String fileName = fileStore.write(filename, is);
//		String path = fileStore.getDomainURL(fileName);//文件存储的绝对路径
		String use_type = request.getParameter("use_type");//使用类型
		String use_detail_type = request.getParameter("use_detail_type");//使用详细类型
		String user_id = request.getParameter("user_id");//用户id
		long file_id = PrimaryUtil.getPrimary();
		PageData data = new PageData();
		filename = filename.substring(0,filename.lastIndexOf(".")-1);
		data.put("file_name", filename);
		data.put("file_path", filepath);
		data.put("file_id", file_id);
		data.put("user_id", user_id);
		logger.info("=================user_id"+user_id);
		data.put("use_detail_type", use_detail_type);
//		data.put("file_size", is.);
		data.put("file_type", SysDataCode.FILE_TYPE_01);
		data.put("use_type", Integer.valueOf(use_type));
		dao.save("AppCommonMapper.saveEfiles", data);
		return String.valueOf(file_id);
	}
	
	
	/**
	 * 获取车型
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getEautoMat(PageData pd) throws Exception {
		return  (List<PageData>) dao.findForList("AppCommonMapper.getEautoMat", pd);
	}
	/**
	 * 根据主键修改附件业务主键
	 * @param pd
	 * @throws Exception 
	 */
	public void updateEfilesByFileId(PageData pd) throws Exception {
		dao.update("AppCommonMapper.updateEfilesByFileId", pd);
	}


	public PageData getEautoMatBybrandList(List<PageData> brandList) throws Exception {
		PageData data = new PageData();
		for (PageData pd : brandList) {
			pd.put("parent_id", pd.get("PARENT_ID"));
			pd.remove("level");
			List<PageData> eautoMat = this.getEautoMat(pd);
			data.put(pd.get("ID")+"", eautoMat);
		}
		return data;
	}
	/**
	 * 发送匹配线路的司机短信，要求，传入 起运城市   目的地址
	 * @param pd
	 * @return
	 * src_city_id 起始城市  
	 * des_city_id  到达城市
	 * @throws Exception 
	 */
	@Async
	public PageData sendOrderLineSmsMessage(PageData pd) throws Exception {
		List<PageData> list =  this.getElineBySrcCityIdAndDesCityId(pd);
		this.sendSmsByOrder(list,pd);
		return pd;
	}


	@SuppressWarnings("unchecked")
	public List<PageData> getElineBySrcCityIdAndDesCityId(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppCommonMapper.getElineBySrcCityIdAndDesCityId", pd); 
	}
	/**
	 * 发送信息
	 * @param list
	 * @param pd2 
	 */
	public void sendSmsByOrder(List<PageData> list, PageData pd2)  {
//		String listToStr = Tools.listToString(list, "USER_NAME", ",");
		StringBuffer sb = new StringBuffer();
		int num =0;
		int count =0;
		for (PageData pd : list) {
			num ++;
			count = count +num;
			sb.append(pd.getString("USER_NAME")).append(",");
			try {
				if(num==99||count==list.size()){
					pd.put("src_city", pd.get("SRC_CITY"));
					pd.put("des_city", pd.get("DES_CITY"));
					pd.put("send_user_id", pd2.get("send_user_id"));
					String phone = sb.toString();
					SmsUtil.SendSMS("SMS_70014", pd, phone.substring(0, phone.lastIndexOf(",")), "");
					num =0;
					sb.setLength(0);
				}
			} catch (Exception e) {
				logger.info("发布货源发送短信异常==");
			}
		}
		
	}
	/**
	 * 异步发送短信接口
	 * @param template_code   模板code
	 * @param paramPd   模板 参数
	 * @param telPhone  接收消息的电话号码，多个以逗号隔开
	 * @param SMS_Supplier   短信运营商（暂不用） 传""
	 */
	@Async
	public  void sendSmsMessage(String template_code, PageData paramPd,String telPhone, String SMS_Supplier) {
		try {
			SmsUtil.SendSMS(template_code, paramPd, telPhone, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("异步发送短信接口异常==");
		}
	}
	
	/**
	 * 保存消息
	 * @param pd
	 * @throws Exception 
	 * send_user_id   发送人id
	 * receive_user_id  接收人id
	 */
	public void saveMaMsg(PageData pd) throws Exception {
		logger.info("保存消息=====start====");
		PageData  msgPd = new PageData();
		PageData  receivePd = new PageData();
		long msg_id = PrimaryUtil.getPrimary();
		String send_user_id = String.valueOf(pd.get("send_user_id"));
		String receive_phone = pd.getString("receive_phone");
		String[] receive_phones = Tools.str2StrArray(receive_phone);
		logger.info("receive_user_id=="+pd.get("send_user_id"));
		msgPd.put("msg_type", SysDataCode.MSG_TYPE_02);
		msgPd.put("msg_id",msg_id );
		logger.info("消息内容====="+pd.get("msg_content"));
		msgPd.put("msg_content",pd.get("msg_content"));
		msgPd.put("send_time", new Date());
		msgPd.put("end_time", new Date());
		msgPd.put("send_user",send_user_id);
		msgPd.put("range_flag", SysDataCode.STATUS_TYPE_02);
		msgPd.put("msgUserType  ", Tools.checkObj(pd.get("msgUserType")));
		dao.save("AppCommonMapper.saveMaMsg", msgPd);
		//存接收人消息
		Long receiveId = PrimaryUtil.getPrimary();
		receivePd.put("receiveId", receiveId);
		receivePd.put("msg_id", msg_id);
		receivePd.put("user_id", receive_phones);
		receivePd.put("msgContent", msgPd.getString("msg_content"));
		dao.save("AppCommonMapper.saveMaMsgReceive", receivePd);
		//循环电话给用户使用极光推送
		/*for(int i=0;i<receive_phones.length;i++) {
			String tel = receive_phones[i];
			PageData thisPd = new PageData();
			thisPd.put("thisTel", tel);
			PageData prParam = (PageData)dao.findForObject("AppCommonMapper.getUserMsgAndTypeByTelPhone", thisPd);
			if (null != prParam && !"null".equals(prParam)) {
				String userType = Tools.checkObj(prParam.get("USER_TYPE"));
				int num = this.commonService.getCountNoReadMsgNum(String.valueOf(prParam.get("USER_ID")), SysDataCode.STATUS_TYPE_02+"");
				if (String.valueOf(SysDataCode.USR_TYPE_02).equals(userType) || String.valueOf(SysDataCode.USR_TYPE_04).equals(userType)) {
					PageData jps = new PageData();
					jps.put("businessId", String.valueOf(pd.get("businessId")));
					jps.put("businessType", String.valueOf(pd.get("businessType")));
					JpushUtils.sendSingleUser(String.valueOf(prParam.get("USER_ID")), pd.getString("msg_content"), null, num,jps);
				} else if (String.valueOf(SysDataCode.USR_TYPE_03).equals(userType) || String.valueOf(SysDataCode.USR_TYPE_05).equals(userType)) {
					PageData jpp = new PageData();
					jpp.put("businessId", String.valueOf(pd.get("businessId")));
					jpp.put("businessType", String.valueOf(pd.get("businessType")));
					JpushUtils.sendSingleUserByDriver(String.valueOf(prParam.get("USER_ID")), pd.getString("msg_content"), null, num,jpp);
				}
			}
		}//循环结束*/
}
	
	/**根据货源ID查询当前订单的状态*/
	public PageData getBussniesStatusById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("AppCommonMapper.getBussniesStatusById", pd);
	}
	
}
