package com.fh.controller.system.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.common.CommonService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.MapDistance;
import com.fh.util.PageData;
import com.fh.util.PathUtil;
import com.fh.util.TwoDimensionCode;

/**
 * 类名称：ToolController 创建人：FH 创建时间：2015年4月4日
 * 
 * @version
 */
@Controller
@RequestMapping(value = "/tool")
public class ToolController extends BaseController {

	@Resource(name = "commonService")
	private CommonService commonService;

	/**
	 * 去接口测试页面
	 */
	@RequestMapping(value = "/interfaceTest")
	public ModelAndView editEmail() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/tools/interfaceTest");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 接口内部请求
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/severTest")
	@ResponseBody
	public Object severTest() {
		Map<String, String> map = new HashMap<String, String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "success", str = "", rTime = "";
		try {
			long startTime = System.currentTimeMillis(); // 请求起始时间_毫秒
			URL url = new URL(pd.getString("serverUrl"));
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod(pd.getString("requestMethod")); // 请求类型
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			long endTime = System.currentTimeMillis(); // 请求结束时间_毫秒
			String temp = "";
			while ((temp = in.readLine()) != null) {
				str = str + temp;
			}
			rTime = String.valueOf(endTime - startTime);
		} catch (Exception e) {
			errInfo = "error";
		}
		map.put("errInfo", errInfo); // 状态信息
		map.put("result", str); // 返回结果
		map.put("rTime", rTime); // 服务器请求时间 毫秒
		return AppUtil.returnObject(new PageData(), map);
	}

	/**
	 * 发送邮件页面
	 */
	@RequestMapping(value = "/goSendEmail")
	public ModelAndView goSendEmail() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/tools/email");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 二维码页面
	 */
	@RequestMapping(value = "/goTwoDimensionCode")
	public ModelAndView goTwoDimensionCode() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/tools/twoDimensionCode");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 生成二维码
	 * 
	 * @param args
	 * @throws Exception
	 */
	@RequestMapping(value = "/createTwoDimensionCode")
	@ResponseBody
	public Object createTwoDimensionCode() {
		Map<String, String> map = new HashMap<String, String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "success", encoderImgId = this.get32UUID() + ".png"; // encoderImgId此处二维码的图片名
		String encoderContent = pd.getString("encoderContent"); // 内容
		if (null == encoderContent) {
			errInfo = "error";
		} else {
			try {
				String filePath = PathUtil.getClasspath()
						+ Const.FILEPATHTWODIMENSIONCODE + encoderImgId; // 存放路径
				TwoDimensionCode.encoderQRCode(encoderContent, filePath, "png"); // 执行生成二维码
			} catch (Exception e) {
				errInfo = "error";
			}
		}
		map.put("result", errInfo); // 返回结果
		map.put("encoderImgId", encoderImgId); // 二维码图片名
		return AppUtil.returnObject(new PageData(), map);
	}

	/**
	 * 解析二维码
	 * 
	 * @param args
	 * @throws Exception
	 */
	@RequestMapping(value = "/readTwoDimensionCode")
	@ResponseBody
	public Object readTwoDimensionCode() {
		Map<String, String> map = new HashMap<String, String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "success", readContent = "";
		String imgId = pd.getString("imgId");// 内容
		if (null == imgId) {
			errInfo = "error";
		} else {
			try {
				String filePath = PathUtil.getClasspath()
						+ Const.FILEPATHTWODIMENSIONCODE + imgId; // 存放路径
				readContent = TwoDimensionCode.decoderQRCode(filePath);// 执行读取二维码
			} catch (Exception e) {
				errInfo = "error";
			}
		}
		map.put("result", errInfo); // 返回结果
		map.put("readContent", readContent); // 读取的内容
		return AppUtil.returnObject(new PageData(), map);
	}

	/**
	 * 服务站选择树-->树页面
	 */
	@RequestMapping(value = "/dealer_ztree")
	public ModelAndView dealer_ztree() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.addObject("pd", pd);
			mv.setViewName("system/tools/dealer_ztree");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 服务站选择树-->树结果集 Ahua772
	 */
	@RequestMapping(value = "/getAllBaseOrg")
	@ResponseBody
	public JSONObject getAllBaseOrg() throws Exception {

		Map<String, Object> resultMap = AppUtil.getResultMap();
		PageData pd = new PageData();
		pd = this.getPageData();
		String orgType = this.getOrgType();// 职位类型
		if ("10120001".equals(orgType)) {// 只有车厂职位才加载orgid
			pd.put("org_id", this.getDealerId());
		} else {
			pd.put("org_id", "no");
		}

		List<PageData> treelist = commonService.getAllBaseOrg(pd);
		List<Map<String, String>> nodeList = new ArrayList<Map<String, String>>();
		for (int i = 0; i < treelist.size(); i++) {
			PageData tempPD = treelist.get(i);
			Map<String, String> node = new HashMap<String, String>();

			node.put("id", tempPD.get("ORG_ID").toString());
			node.put("pId", tempPD.get("PARENT_ORG_ID").toString());
			node.put("name", tempPD.getString("ORG_NAME"));

			int level = Integer.parseInt(tempPD.get("ORG_LEVEL").toString());
			if (level == 0) {// 0级表示：长安铃木
				node.put("open", "true");
				node.put("url","tool/dealerList.do?multChoice=" + pd.get("multChoice")+ "&org_level=" + level + "&org_id=");
			} else {
				node.put("url","tool/dealerList.do?multChoice=" + pd.get("multChoice")+ "&org_level=" + level + "&org_id="+ tempPD.get("ORG_ID"));
			}
			node.put("target", "treeFrame");
			nodeList.add(node);
		}
		resultMap.put("nodeList", nodeList);
		JSONObject jsobject = JSONObject.fromObject(resultMap);
		return jsobject;
	}

	/**
	 * 服务站选择树-->服务站列表 Ahua772
	 */
	@RequestMapping(value = "/dealerList")
	public ModelAndView dealerList(Page page) {
		ModelAndView mv = this.getModelAndView();
		try {
			PageData pd = new PageData();
			pd = this.getPageData();
			String orgType = this.getOrgType();// 职位类型
			if ("10120001".equals(orgType)) {// 只有车厂职位才加载orgid
				// 此时org_level不等于空表示：从左侧树形菜单点击请求的此方法，等于空表示：默认打开弹出框页面
				String org_level = pd.getString("org_level");
				if (null != org_level && !"".equals(org_level)) {
					if ("0".equals(org_level)) {// 长安铃木
						// 将org_id parent_org_id设置为空查询所有服务站
						pd.put("org_id", "");
						pd.put("parent_org_id", "");
					} else if ("1".equals(org_level)) {// 大区
						String org_id = pd.getString("org_id");
						if (null != org_id && !"".equals(org_id)) {
							org_id = org_id.trim();
							pd.put("org_id", "");
							pd.put("parent_org_id", org_id);// 按大区查询
						}
					} else if ("2".equals(org_level)) {// 片区
						String org_id = pd.getString("org_id");
						if (null != org_id && !"".equals(org_id)) {
							org_id = org_id.trim();
							pd.put("org_id", org_id);
						}
					}
				} else {
					String orgLevel = (String) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_ORG_LEVEL);
					if ("0".equals(orgLevel)) {
						// 将org_id parent_org_id设置为空查询所有服务站
						pd.put("org_id", "");
						pd.put("parent_org_id", "");
					} else if ("1".equals(orgLevel)) {
						pd.put("org_id", "");
						pd.put("parent_org_id", this.getDealerId());// 按大区查询
					} else if ("2".equals(orgLevel)) {
						pd.put("org_id", this.getDealerId());// 按片区查询
					}
				}
				// 查询条件
				String KEYW = pd.getString("keyword");
				if (null != KEYW && !"".equals(KEYW)) {
					KEYW = KEYW.trim();
					pd.put("KEYW", KEYW);
				}

			} else {// 不是车厂职位不能选择服务站
				pd.put("org_id", "no");
			}
			page.setPd(pd);
			List<PageData> dealerlist = commonService.getDealerList(page);
			mv.addObject("dealerlist", dealerlist);
			mv.addObject("pd", pd);
			mv.setViewName("system/tools/dealer_list");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 机构选择树-->树页面 Ahua772
	 */
	@RequestMapping(value = "/org_ztree")
	public ModelAndView org_ztree() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.addObject("pd", pd);
			mv.setViewName("system/tools/org_ztree");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 机构选择树-->树结果集 Ahua772
	 */
	@RequestMapping(value = "/getChooseBaseOrg")
	@ResponseBody
	public JSONObject getChooseBaseOrg() throws Exception {

		Map<String, Object> resultMap = AppUtil.getResultMap();
		PageData pd = new PageData();
		pd = this.getPageData();

		String orgType = this.getOrgType();// 职位类型
		if ("10120001".equals(orgType)) {// 只有车厂职位才加载orgid
			pd.put("org_id", this.getDealerId());

			String orgLevel = (String) SecurityUtils.getSubject().getSession()
					.getAttribute(Const.SESSION_ORG_LEVEL);
			if ("0".equals(orgLevel)) {
				pd.put("org_level", orgLevel);
			}
		} else {
			pd.put("org_id", "no");
		}

		List<PageData> treelist = commonService.getChooseBaseOrg(pd);
		List<Map<String, String>> nodeList = new ArrayList<Map<String, String>>();
		for (int i = 0; i < treelist.size(); i++) {
			PageData tempPD = treelist.get(i);
			Map<String, String> node = new HashMap<String, String>();
			node.put("id", tempPD.get("ORG_ID").toString());
			node.put("pId", tempPD.get("PARENT_ORG_ID").toString());
			node.put("name", tempPD.getString("ORG_NAME"));

			int level = Integer.parseInt(tempPD.get("ORG_LEVEL").toString());
			if (level == 0) {// 0级表示：长安铃木
				node.put("open", "true");
				node.put("url",
						"tool/orgList.do?multChoice=" + pd.get("multChoice")
								+ "&org_level=" + level + "&org_id=");
			} else {
				node.put(
						"url",
						"tool/orgList.do?multChoice=" + pd.get("multChoice")
								+ "&org_level=" + level + "&org_id="
								+ tempPD.get("ORG_ID"));
			}
			node.put("target", "treeFrame");
			nodeList.add(node);
		}

		resultMap.put("nodeList", nodeList);
		JSONObject jsobject = JSONObject.fromObject(resultMap);
		return jsobject;
	}

	/**
	 * 机构选择树 -->机构列表 Ahua772
	 */
	@RequestMapping(value = "/orgList")
	public ModelAndView orgList(Page page) {
		ModelAndView mv = this.getModelAndView();
		try {
			PageData pd = new PageData();
			pd = this.getPageData();

			String orgType = this.getOrgType();// 职位类型
			if ("10120001".equals(orgType)) { // 车厂职位
				String multChoice = pd.getString("multChoice");// 是否多选

				// 此时org_level不等于空表示：从左侧树形菜单点击请求的此方法，等于空表示：默认打开弹出框页面
				String org_level = pd.getString("org_level");
				if (null != org_level && !"".equals(org_level)) {
					if ("0".equals(org_level)) {// 长安铃木
						// 查询大区
						if ("true".equals(multChoice)) {
							pd.put("org_level", "1");
							pd.put("parent_org_id", this.getDealerId());// 按大区查询
						} else {
							pd.put("org_level", "");
							pd.put("allDQ", this.getDealerId()); // 单选时
																	// 查询长安铃木和大区
						}

					} else if ("1".equals(org_level)) {// 大区
						String org_id = pd.getString("org_id");
						if (null != org_id && !"".equals(org_id)) {
							org_id = org_id.trim();
							pd.put("org_id", "");
							pd.put("parent_org_id", org_id);// 按大区查询
							pd.put("org_level", "2");
						}
					}
				} else {
					String orgLevel = (String) SecurityUtils.getSubject()
							.getSession().getAttribute(Const.SESSION_ORG_LEVEL);
					if ("0".equals(orgLevel)) {
						// 查询大区
						if ("true".equals(multChoice)) {
							pd.put("org_level", "1");
							pd.put("parent_org_id", this.getDealerId());// 按大区查询
						} else {
							pd.put("org_level", "");
							pd.put("allDQ", this.getDealerId()); // 单选时
																	// 查询长安铃木和大区
						}

					} else if ("1".equals(orgLevel)) {

						pd.put("parent_org_id", this.getDealerId());// 按大区查询
						pd.put("org_level", "2");

					} else if ("2".equals(orgLevel)) {

						pd.put("org_id", this.getDealerId());// 按大区查询
						pd.put("org_level", "2");
					}
				}

				// 查询条件
				String KEYW = pd.getString("keyword");
				if (null != KEYW && !"".equals(KEYW)) {
					KEYW = KEYW.trim();
					pd.put("KEYW", KEYW);
				}

			} else {// 服务站职位没有查询权限
				pd.put("org_id", "no");
			}

			// page.setShowCount(6);
			page.setPd(pd);
			List<PageData> orglist = commonService.getOrgList(page);
			mv.addObject("orglist", orglist);
			mv.addObject("pd", pd);
			mv.setViewName("system/tools/org_list");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 地图页面
	 */
	@RequestMapping(value = "/map")
	public ModelAndView map() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/tools/map");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 获取地图坐标页面
	 */
	@RequestMapping(value = "/mapXY")
	public ModelAndView mapXY() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/tools/mapXY");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 根据经纬度计算距离
	 * 
	 * @param args
	 * @throws Exception
	 */
	@RequestMapping(value = "/getDistance")
	@ResponseBody
	public Object getDistance() {
		Map<String, String> map = new HashMap<String, String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "success", distance = "";
		try {
			distance = MapDistance.getDistance(pd.getString("ZUOBIAO_Y"),
					pd.getString("ZUOBIAO_X"), pd.getString("ZUOBIAO_Y2"),
					pd.getString("ZUOBIAO_X2"));
		} catch (Exception e) {
			errInfo = "error";
		}
		map.put("result", errInfo); // 返回结果
		map.put("distance", distance); // 距离
		return AppUtil.returnObject(new PageData(), map);
	}

}
