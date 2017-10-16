package com.fh.service.system.dealer;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fh.controller.base.BaseService;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.service.common.CommonService;
import com.fh.util.PageData;
import com.fh.util.PrimaryUtil;
import com.fh.util.SysDataCode;
import com.fh.util.Tools;
import com.fh.util.oss.OSSUploadUtil;

@SuppressWarnings("unchecked")
@Service("dealerService")
public class DEALERService extends BaseService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name = "commonService")
	private CommonService commonService;

	/*
	 * 新增
	 */
	public void save(PageData pd) throws Exception {
		dao.save("DEALERMapper.save", pd);
	}

	/*
	 * 删除
	 */
	public void delete(PageData pd) throws Exception {
		dao.delete("DEALERMapper.delete", pd);
	}

	/*
	 * 修改
	 */
	public void edit(PageData pd) throws Exception {
		dao.update("DEALERMapper.edit", pd);
	}

	/**
	 * 修改服务站信息
	 * 
	 * @throws Exception
	 * */
	public int updateDealerMsg(PageData pd, MultipartFile[] carNumFiles,MultipartFile[] ownerFiles, MultipartFile[] loadFiles)
			throws Exception {
		Integer update = (Integer) dao.update("DEALERMapper.updateDealerMsg",pd);
		// 上传身份证图片
		if (!carNumFiles[0].isEmpty()) {
			// 先删除数据库的值
			pd.put("useType", SysDataCode.FILE_USE_TYPE_01);
			dao.delete("DEALERMapper.deleteDealerImgFIles", pd);
		}
		int carLen = carNumFiles.length;
		for (int i = 0; i < carLen; i++) {
			MultipartFile carFile = carNumFiles[i];
			if (null != carFile && !carFile.isEmpty()) {
				String path=OSSUploadUtil.uploadFile(carFile);
				String fileName = carFile.getOriginalFilename();
				PageData cp = new PageData();
				cp.put("objectId", pd.get("DEALER_ID"));
				cp.put("fileId", PrimaryUtil.getPrimary());
				cp.put("fileType", SysDataCode.FILE_TYPE_01);
				cp.put("fileUrl", path);
				cp.put("fileName", fileName);
				cp.put("userId", pd.get("userFileId"));
				cp.put("useType", SysDataCode.FILE_USE_TYPE_01);
				// 插入到附件表
				dao.save("DEALERMapper.insertFileImgInDealer", cp);
			}
		}
		// 上传营业执照
		if (!ownerFiles[0].isEmpty()) {
			// 先删除数据库的值
			pd.put("useType", SysDataCode.FILE_USE_TYPE_05);
			dao.delete("DEALERMapper.deleteDealerImgFIles", pd);
		}
		int ownerLen = ownerFiles.length;
		for (int j = 0; j < ownerLen; j++) {
			MultipartFile ownerFile = ownerFiles[j];
			if (null != ownerFile && !ownerFile.isEmpty()) {
				String path=OSSUploadUtil.uploadFile(ownerFile);
				String fileName = ownerFile.getOriginalFilename();
				PageData op = new PageData();
				op.put("objectId", pd.get("DEALER_ID"));
				op.put("fileId", PrimaryUtil.getPrimary());
				op.put("fileType", SysDataCode.FILE_TYPE_01);
				op.put("fileUrl", path);
				op.put("fileName", fileName);
				op.put("userId", pd.get("userFileId"));
				op.put("useType", SysDataCode.FILE_USE_TYPE_05);
				// 插入到附件表
				dao.save("DEALERMapper.insertFileImgInDealer", op);
			}
		}
		// 上传道路运输许可证
		if (!loadFiles[0].isEmpty()) {
			// 先删除数据库的值
			pd.put("useType", SysDataCode.FILE_USE_TYPE_07);
			dao.delete("DEALERMapper.deleteDealerImgFIles", pd);
		}
		int loadLen = loadFiles.length;
		for (int k = 0; k < loadLen; k++) {
			MultipartFile loadFile = loadFiles[k];
			if (null != loadFile && !loadFile.isEmpty()) {
				String path=OSSUploadUtil.uploadFile(loadFile);
				String fileName = loadFile.getOriginalFilename();
				PageData lp = new PageData();
				lp.put("objectId", pd.get("DEALER_ID"));
				lp.put("fileId", PrimaryUtil.getPrimary());
				lp.put("fileType", SysDataCode.FILE_TYPE_01);
				lp.put("fileUrl", path);
				lp.put("fileName", fileName);
				lp.put("userId", pd.get("userFileId"));
				lp.put("useType", SysDataCode.FILE_USE_TYPE_07);
				// 插入到附件表
				dao.save("DEALERMapper.insertFileImgInDealer", lp);
			}
		}
		if (update == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 新增服务站信息
	 * 
	 * @throws Exception
	 * */
	public int insertDealerMsg(PageData pd, MultipartFile[] carNumFiles,
			MultipartFile[] ownerFiles, MultipartFile[] loadFiles)
			throws Exception {
		// 查询组织机构
		Map<String, Object> orgId = (Map<String, Object>) dao.findForObject(
				"DEALERMapper.getBaseOrgId", null);
		pd.put("ORG_ID", orgId.get("ORG_ID"));
		pd.put("dealerCode", PrimaryUtil.getRandomStr(10));
		Integer add = (Integer) dao.save("DEALERMapper.insertDealerMsg", pd);
		// 上传身份证图片
		int carLen = carNumFiles.length;
		for (int i = 0; i < carLen; i++) {
			MultipartFile carFile = carNumFiles[i];
			if (null != carFile && !carFile.isEmpty()) {
				String path=OSSUploadUtil.uploadFile(carFile);
				String fileName = carFile.getOriginalFilename();
				PageData cp = new PageData();
				cp.put("objectId", pd.get("DEALER_ID"));
				cp.put("fileId", PrimaryUtil.getPrimary());
				cp.put("fileType", SysDataCode.FILE_TYPE_01);
				cp.put("fileUrl", path);
				cp.put("fileName", fileName);
				cp.put("userId", pd.get("userFileId"));
				cp.put("useType", SysDataCode.FILE_USE_TYPE_01);
				// 插入到附件表
				dao.save("DEALERMapper.insertFileImgInDealer", cp);
			}
		}
		// 上传营业执照
		int ownerLen = ownerFiles.length;
		for (int j = 0; j < ownerLen; j++) {
			MultipartFile ownerFile = ownerFiles[j];
			if (null != ownerFile && !ownerFile.isEmpty()) {
				String path=OSSUploadUtil.uploadFile(ownerFile);
				String fileName = ownerFile.getOriginalFilename();
				PageData op = new PageData();
				op.put("objectId", pd.get("DEALER_ID"));
				op.put("fileId", PrimaryUtil.getPrimary());
				op.put("fileType", SysDataCode.FILE_TYPE_01);
				op.put("fileUrl", path);
				op.put("fileName", fileName);
				op.put("userId", pd.get("userFileId"));
				op.put("useType", SysDataCode.FILE_USE_TYPE_05);
				// 插入到附件表
				dao.save("DEALERMapper.insertFileImgInDealer", op);
			}
		}
		// 上传道路运输许可证
		int loadLen = loadFiles.length;
		for (int k = 0; k < loadLen; k++) {
			MultipartFile loadFile = loadFiles[k];
			if (null != loadFile && !loadFile.isEmpty()) {
				String path=OSSUploadUtil.uploadFile(loadFile);
				String fileName = loadFile.getOriginalFilename();
				PageData lp = new PageData();
				lp.put("objectId", pd.get("DEALER_ID"));
				lp.put("fileId", PrimaryUtil.getPrimary());
				lp.put("fileType", SysDataCode.FILE_TYPE_01);
				lp.put("fileUrl", path);
				lp.put("fileName", fileName);
				lp.put("userId", pd.get("userFileId"));
				lp.put("useType", SysDataCode.FILE_USE_TYPE_07);
				// 插入到附件表
				dao.save("DEALERMapper.insertFileImgInDealer", lp);
			}
		}
		// 默认创建一个职位
		PageData post = new PageData();
		Long postId = PrimaryUtil.getPrimary();
		post.put("postId", postId);
		post.put("postName", pd.get("DEALER_NAME") + "职位");
		post.put("postType", SysDataCode.ORG_TYPE_DEALER);
		post.put("postStatus", SysDataCode.ISVALID_YES);
		post.put("postOrgId", pd.get("DEALER_ID"));
		post.put("createBy", pd.get("userId"));
		dao.save("DEALERMapper.insertPostMsg", post);
		// 默认创建职位与角色的关联
		PageData postRole = new PageData();
		Long postRoleId = PrimaryUtil.getPrimary();
		postRole.put("postRoleId", postRoleId);
		postRole.put("postId", postId);
		postRole.put("createBy", pd.get("userId"));
		dao.save("DEALERMapper.insertPostRoleMsg", postRole);
		if (add == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	/*
	 * 列表
	 */
	public List<PageData> list(Page page) throws Exception {
		return (List<PageData>) dao.findForList("DEALERMapper.datalistPage",page);
	}

	/*
	 * 列表(全部)
	 */
	public List<PageData> listAll(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("DEALERMapper.listAll", pd);
	}

	/*
	 * 通过id获取数据
	 */
	public PageData findById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("DEALERMapper.findById", pd);
	}

	/*
	 * 批量删除
	 */
	public void deleteAll(String[] ArrayDATA_IDS) throws Exception {
		dao.delete("DEALERMapper.deleteAll", ArrayDATA_IDS);
	}

	/**
	 * 省份
	 */
	public List<PageData> getProvinceList(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("DEALERMapper.getProvinceList",
				pd);
	}

	/**
	 * 经销商
	 */
	public List<PageData> getProvinceDealerList(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList(
				"DEALERMapper.getProvinceDealerList", pd);
	}

	/**
	 * 获取经销商信息，通过appid、wechatId、dealerId，微信工具类使用
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getDealerInfo(PageData pd) throws Exception {
		return (PageData) dao.findForObject(
				"ToolWechatEventMapperE.getDealerWechatInfoByObject", pd);
	}

	/**
	 * 评价维护列表
	 */
	public List<PageData> getEvaluaelistPage(Page page) throws Exception {
		return (List<PageData>) dao.findForList(
				"DEALERMapper.getEvaluaelistPage", page);
	}

	/**
	 * 统计已有的条数
	 * */
	public List<PageData> countEvalueNum(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("DEALERMapper.countEvalueNum",
				pd);
	}

	/**
	 * 新增评价
	 * 
	 * @throws Exception
	 */
	public void addEvaluateMsg(PageData pd) throws Exception {
		dao.save("DEALERMapper.addEvaluateMsg", pd);
	}

	/**
	 * 查询一条评价信息
	 * */
	public PageData evaluateMsg(PageData pd) throws Exception {
		return (PageData) dao.findForObject("DEALERMapper.evaluateMsg", pd);
	}

	/**
	 * 修改评价
	 * 
	 * @throws Exception
	 */
	public void updateEvaluateMsg(PageData pd) throws Exception {
		dao.update("DEALERMapper.updateEvaluateMsg", pd);
	}

	/**
	 * 查询附件信息
	 * */
	public List<PageData> getFileList(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("DEALERMapper.getFileList", pd);
	}
	
	/***调转到评价修改页面**/
	public List<PageData> jumpDepositPage(Page page) throws Exception {
		return (List<PageData>) dao.findForList("DEALERMapper.jumpDepositPagelistPage", page);
	}
	
	/***根据订单扣钱 **/
    public void depositMyMoneymsg(PageData pd) throws Exception {
         String orderMsg = Tools.checkObj(pd.get("orderAnDriver"));
         if (!Tools.isEmpty(orderMsg)) {
        	 Long depositId = PrimaryUtil.getPrimary(); // 提现ID
        	 Double depositAmount = 0.0;
        	 String[] arr = orderMsg.split(",");
        	 for (int i=0;i<arr.length;i++) {
        		 String[] arrTwo = arr[i].split("@");
        		 String orderId = arrTwo[0];  //订单ID
        		 String driverId = arrTwo[1]; //司机ID
        		 String priceNum = arrTwo[2]; // 订单价格
        		 depositAmount = depositAmount+Double.valueOf(priceNum); // 累加金额
        		 // String driverName = arrTwo[3]; //司机姓名
        		 PageData pm = new PageData();
        		 pm.put("orderId", orderId);
        		 pm.put("driverId", driverId);
        		 pm.put("priceNum", priceNum);
        		 //根据当前的司机ID查询主账号的ID,若是主账号，那么会返回主账号的ID
        		PageData amontPd = this.commonService.getAmontUserByDriverId(pm);
        		pm.put("amontUserId", Tools.checkObj(amontPd.get("USER_ID")));
        		 //将订单改为已提现状态
        		 dao.update("DEALERMapper.updateOrderDepositStatus", pm);
        		 //将司机账号的钱扣除
        		 dao.update("DEALERMapper.updateUserMoney", pm);
        		//插入钱包记录
    	  		PageData moneyPd = new PageData();
    	  		moneyPd.put("moneyType", SysDataCode.MONEY_DETAIL_04);
    	  		moneyPd.put("businessType", SysDataCode.MONEY_BUSINESS_DETAIL_03);
    	  		moneyPd.put("businessId",orderId);
    	  		moneyPd.put("depositId",depositId);
    	  		moneyPd.put("amount", -Double.parseDouble(priceNum));
    	  		moneyPd.put("userId", Tools.checkObj(amontPd.get("USER_ID")));
    	  		moneyPd.put("userName", Tools.checkObj(amontPd.get("NAME")));
    	  		dao.save("DEALERMapper.saveAmount", moneyPd);
        	 } // i循环结束
        	 //插入一条提现记录
        	 pd.put("depositId", depositId);
        	 pd.put("depositAmount", -depositAmount);
        	 dao.save("DEALERMapper.saveDepositRecord", pd);
         }
    }
    
    /***承运商提现详情页面**/
	public List<PageData> jumpDepositDetaillistPage(Page page) throws Exception {
		return (List<PageData>) dao.findForList("DEALERMapper.jumpDepositDetaillistPage", page);
	}
	
	/***承运商提现详情页面里面的订单记录**/
	public List<PageData> jumpDepositDetailPageInOrder(Page page) throws Exception {
		return (List<PageData>) dao.findForList("DEALERMapper.jumpDepositDetailPageInOrder", page);
	}

}
