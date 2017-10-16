package com.fh.controller.system.dealer;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.system.dealer.DEALERService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.PrimaryUtil;
import com.fh.util.SysDataCode;
import com.fh.util.Tools;

/** 
 * 类名称：DEALERController
 * 创建人：FH 
 * 创建时间：2017-03-15
 */
@Controller
@RequestMapping(value="/dealer")
public class DEALERController extends BaseController {
	
	@Resource(name="dealerService")
	private DEALERService dealerService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增DEALER");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("DEALER_ID", this.get32UUID());	//主键
		dealerService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除DEALER");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			dealerService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改DEALER");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		dealerService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 承运商列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表DEALER");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String dealerOrg = pd.getString("dealerOrg");
			if (Tools.isEmpty(dealerOrg)) {
			    pd.put("dealerOrg", this.getDealerId());
			}
			String strOrgLevel  = pd.getString("strOrgLevel");
			String level ="";
			if (Tools.isEmpty(strOrgLevel)) {
			    level = this.getLevel();
			} else {
			    level = strOrgLevel.trim();
			}
			pd.put("orgType", this.getOrgType());
			pd.put("orgLevel", level);
			page.setPd(pd);
			 //列出DEALER列表
			List<PageData> varList = dealerService.list(page);
			mv.setViewName("system/dealer/dealer_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject("type", this.getOrgType());
			mv.addObject(Const.SESSION_QX,this.getPermission());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/dealerDepositList")
	public ModelAndView dealerDepositList(Page page){
		logBefore(logger, "列表DEALER");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String dealerOrg = pd.getString("dealerOrg");
			if (Tools.isEmpty(dealerOrg)) {
			    pd.put("dealerOrg", this.getDealerId());
			}
			String strOrgLevel  = pd.getString("strOrgLevel");
			String level ="";
			if (Tools.isEmpty(strOrgLevel)) {
			    level = this.getLevel();
			} else {
			    level = strOrgLevel.trim();
			}
			pd.put("orgType", this.getOrgType());
			pd.put("orgLevel", level);
			page.setPd(pd);
			 //列出DEALER列表
			List<PageData> varList = dealerService.list(page);
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject("type", this.getOrgType());
			mv.addObject(Const.SESSION_QX,this.getPermission());	//按钮权限
			mv.setViewName("system/dealer/dealer_deposit_list");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增DEALER页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("system/dealer/dealer_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "去修改DEALER页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			//根据ID偶去承运商信息
			pd = dealerService.findById(pd);	
			//查询附件信息
			List<PageData>  fileList = this.dealerService.getFileList(pd);
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
			mv.addObject("fileList", fileList);
			mv.setViewName("system/dealer/dealer_edit");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goToAdd")
	public ModelAndView goToAdd(){
		ModelAndView mv = this.getModelAndView();
		try {
			mv.setViewName("system/dealer/dealer_add");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
     * 修改服务站信息
	 * @throws Exception 
     * */
	@RequestMapping(value="/updateDealerMsg",method=RequestMethod.POST)
	public ModelAndView updateDealerMsg(HttpServletRequest request ,
			@RequestParam MultipartFile[] carNumFiles,
			@RequestParam MultipartFile[] ownerFiles,
			@RequestParam MultipartFile[] loadFiles) throws Exception {
		ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            String DEALER_ID =request.getParameter("DEALER_ID");
            if (Tools.isEmpty(DEALER_ID)) {
                return mv;
            }
            pd.put("DEALER_ID", DEALER_ID);
            String LINKMAN =request.getParameter("LINKMAN");
            pd.put("LINKMAN", LINKMAN);
            String LINKTEL =request.getParameter("LINKTEL");
            pd.put("LINKTEL", LINKTEL);
            String EMAIL =request.getParameter("EMAIL");
            pd.put("EMAIL", EMAIL);
            String FAX =request.getParameter("FAX");
            pd.put("FAX", FAX);
            String HELP_TEL =request.getParameter("HELP_TEL");
            pd.put("HELP_TEL", HELP_TEL);
            String ORDER_TEL =request.getParameter("ORDER_TEL");
            pd.put("ORDER_TEL", ORDER_TEL);
            String LONGITUDE =request.getParameter("LONGITUDE");
            pd.put("LONGITUDE", LONGITUDE);
            String LATITUDE =request.getParameter("LATITUDE");
            pd.put("LATITUDE", LATITUDE);
            String WORK_BEGIN =request.getParameter("WORK_BEGIN");
            pd.put("WORK_BEGIN", WORK_BEGIN);
            String WORK_END =request.getParameter("WORK_END");
            pd.put("WORK_END", WORK_END);
            String ORDER_DISCOUNT =request.getParameter("ORDER_DISCOUNT");
            pd.put("ORDER_DISCOUNT", ORDER_DISCOUNT);
            String ADDRESS =request.getParameter("ADDRESS");
            pd.put("ADDRESS", ADDRESS);
            String APP_ID =request.getParameter("APP_ID");
            pd.put("APP_ID", APP_ID);
            String APPSECRET =request.getParameter("APPSECRET");
            pd.put("APPSECRET", APPSECRET);
            String TOKEN =request.getParameter("TOKEN");
            pd.put("TOKEN", TOKEN);
            String WECHAT_ID =request.getParameter("WECHAT_ID");
            pd.put("WECHAT_ID", WECHAT_ID);
           String COMPANY_INFO =request.getParameter("COMPANY_INFO"); //文本编辑器的值
           pd.put("COMPANY_INFO", COMPANY_INFO);
            String WECHAT_NUM =request.getParameter("WECHAT_NUM");
            pd.put("WECHAT_NUM", WECHAT_NUM);
            String WECHAT_NAME =request.getParameter("WECHAT_NAME");
            pd.put("WECHAT_NAME", WECHAT_NAME);
            String userStatus =request.getParameter("userStatus");
            pd.put("userStatus", userStatus);
            String CARS =request.getParameter("CARS");
            pd.put("CARS", CARS);
            pd.put("userId", getUser().getUSER_NAME());
            pd.put("userFileId", getUser().getUSER_ID());
            pd.put("UPDATE_BY", this.getUser().getUSER_NAME());
            pd.put("UPDATE_DATE", new Date());
            int isR = this.dealerService.updateDealerMsg(pd,carNumFiles,ownerFiles,loadFiles);
            if (isR == 0) {
                throw new Exception("修改物流公司信息失败");
            }
            mv.addObject("msg", "success");
            mv.setViewName("save_result");
        } catch (Exception e) {
            logger.error(e.toString());
            throw new Exception(e.toString());
        }
        return mv;
	}
	
	/**
     * 新增服务站信息
	 * @throws Exception 异常
     * */
	@RequestMapping(value="/addDealerMsg",method=RequestMethod.POST)
	public ModelAndView addDealerMsg(HttpServletRequest request ,
			@RequestParam MultipartFile[] carNumFiles,
			@RequestParam MultipartFile[] ownerFiles,
			@RequestParam MultipartFile[] loadFiles) throws Exception {
	    ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            Long DEALER_ID =  PrimaryUtil.getPrimary();
            pd.put("DEALER_ID", DEALER_ID);
            String DEALER_NAME =request.getParameter("DEALER_NAME");
            pd.put("DEALER_NAME", DEALER_NAME);
            String LINKMAN =request.getParameter("LINKMAN");
            pd.put("LINKMAN", LINKMAN);
            String LINKTEL =request.getParameter("LINKTEL");
            pd.put("LINKTEL", LINKTEL);
            String EMAIL =request.getParameter("EMAIL");
            pd.put("EMAIL", EMAIL);
            String FAX =request.getParameter("FAX");
            pd.put("FAX", FAX);
            String HELP_TEL =request.getParameter("HELP_TEL");
            pd.put("HELP_TEL", HELP_TEL);
            String ORDER_TEL =request.getParameter("ORDER_TEL");
            pd.put("ORDER_TEL", ORDER_TEL);
            String LONGITUDE =request.getParameter("LONGITUDE");
            pd.put("LONGITUDE", LONGITUDE);
            String LATITUDE =request.getParameter("LATITUDE");
            pd.put("LATITUDE", LATITUDE);
            String ORDER_DISCOUNT =request.getParameter("ORDER_DISCOUNT");
            pd.put("ORDER_DISCOUNT", ORDER_DISCOUNT);
            String ADDRESS =request.getParameter("ADDRESS");
            pd.put("ADDRESS", ADDRESS);
            String APP_ID =request.getParameter("APP_ID");
            pd.put("APP_ID", APP_ID);
            String APPSECRET =request.getParameter("APPSECRET");
            pd.put("APPSECRET", APPSECRET);
            String TOKEN =request.getParameter("TOKEN");
            pd.put("TOKEN", TOKEN);
            String WECHAT_ID =request.getParameter("WECHAT_ID");
            pd.put("WECHAT_ID", WECHAT_ID);
           String COMPANY_INFO =request.getParameter("COMPANY_INFO"); //文本编辑器的值
           pd.put("COMPANY_INFO", COMPANY_INFO);
            String WECHAT_NUM =request.getParameter("WECHAT_NUM");
            pd.put("WECHAT_NUM", WECHAT_NUM);
            String WECHAT_NAME =request.getParameter("WECHAT_NAME");
            pd.put("WECHAT_NAME", WECHAT_NAME);
            String personType =request.getParameter("personType");
            pd.put("DEALER_TYPE", personType);
            String CARS =request.getParameter("CARS");
            pd.put("CARS", CARS);
            pd.put("CREATE_BY", this.getUser().getUSER_NAME());
            pd.put("CREATE_DATE", new Date());
            pd.put("STATUS", SysDataCode.ISVALID_YES);
            pd.put("DEALER_FLAG", SysDataCode.EXAMINE_STATUS_02);
            pd.put("userId", getUser().getUSER_NAME());
            pd.put("userFileId", getUser().getUSER_ID());
            int isR = this.dealerService.insertDealerMsg(pd,carNumFiles,ownerFiles,loadFiles);
            if (isR == 0) {
                throw new Exception("新增公司信息失败");
            }
            mv.addObject("msg", "success");
            mv.setViewName("save_result");
        } catch (Exception e) {
            logger.error(e.toString());
            throw new Exception(e.toString());
        }
        return mv;
	}
	
	/**
	 * 批量删除
	 * @throws Exception 
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception {
		logBefore(logger, "批量删除DEALER");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				dealerService.deleteAll(ArrayDATA_IDS);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			throw new Exception(e.toString());
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}
	
	/**
     * 调转到评价维护页面
	 * @throws Exception  异常
     */
    @RequestMapping(value="/jumpEvaluPage")
    public ModelAndView jumpEvaluPage(Page page) throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        //查询评价列表
        page.setPd(pd);
        List<PageData> evList = this.dealerService.getEvaluaelistPage(page);
        mv.addObject("pd", pd);   
        mv.addObject("evList", evList);   
        mv.addObject(Const.SESSION_QX,this.getPermission());    //按钮权限
        mv.setViewName("system/dealer/evaluate_list");
        return mv;
    }   
    
    /**
     * 调转到评价新增页面
     * @throws Exception  异常
     */
    @RequestMapping(value="/jumpEvaluAdddPage")
    public ModelAndView jumpEvaluAdddPage() throws Exception{
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("system/dealer/evaluate_add");
        return mv;
    }  
    
    /**
     * 新增评价
     */
    @RequestMapping(value="/addEvaluateMsg")
    public ModelAndView addEvaluateMsg() throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        pd.put("typeId", PrimaryUtil.getPrimary()); //主键
        pd.put("userName", this.getUser().getUSER_NAME());
        pd.put("busType", 10281002);
        //根据业务分类和业务点去查询排序
        List<PageData> typeSort = (List<PageData>)this.dealerService.countEvalueNum(pd);
        pd.put("typeSort", (typeSort.size()+1));
        this.dealerService.addEvaluateMsg(pd);
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }
    
    /**
     * 调转到评价修改页面
     * @throws Exception  异常
     */
    @RequestMapping(value="/jumpEvaluUpdatePage")
    public ModelAndView jumpEvaluUpdatePage() throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String typeId = Tools.checkObj(pd.get("typeId"));
        if (Tools.isEmpty(typeId)) {
            throw new Exception("评价ID为空,不能修改");
        }
        //查询评价的基本信息
        PageData dp = this.dealerService.evaluateMsg(pd);
        mv.addObject("dp",dp);
        mv.setViewName("system/dealer/evaluate_edit");
        return mv;
    }  
    
    /**
     * 修改评价
     */
    @RequestMapping(value="/updateEvaluateMsg")
    public ModelAndView updateEvaluateMsg() throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        pd.put("userName", this.getUser().getUSER_NAME());
        pd.put("busType", 10281002);
        this.dealerService.updateEvaluateMsg(pd);
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }
    
    /***调转到评价修改页面**/
    @RequestMapping(value="/jumpDepositPage")
    public ModelAndView jumpDepositPage(Page page) throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        page.setPd(pd);
        List<PageData> orderList =this.dealerService.jumpDepositPage(page);
        mv.addObject("orderList",orderList);
        mv.addObject("pd",pd);
        mv.setViewName("system/dealer/dealer_deposit_order_list");
        return mv;
    }  
    
    /***根据订单扣钱 **/
    @RequestMapping(value="/depositMyMoneymsg")
    public ModelAndView depositMyMoneymsg() throws Exception {
    	ModelAndView mv = this.getModelAndView();
    	 PageData pd = new PageData();
         pd = this.getPageData();
         pd.put("createUser", this.getUser().getUSER_ID());
         this.dealerService.depositMyMoneymsg(pd);
         mv.addObject("msg","success");
         mv.setViewName("save_result");
         return mv;
    }
    
    /***承运商提现详情页面**/
    @RequestMapping(value="/jumpDepositDetailPage")
    public ModelAndView jumpDepositDetailPage(Page page) throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        page.setPd(pd);
        List<PageData> orderList =this.dealerService.jumpDepositDetaillistPage(page);
        mv.addObject("orderList",orderList);
        mv.addObject("pd",pd);
        mv.setViewName("system/dealer/dealer_deposit_record_list");
        return mv;
    }  
    
    /***承运商提现详情页面里面的订单记录**/
    @RequestMapping(value="/jumpDepositDetailPageInOrder")
    public ModelAndView jumpDepositDetailPageInOrder(Page page) throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        page.setPd(pd);
        List<PageData> orderList =this.dealerService.jumpDepositDetailPageInOrder(page);
        mv.addObject("orderList",orderList);
        mv.setViewName("system/dealer/dealer_deposit_in_order");
        return mv;
    } 
    
    
	
}
