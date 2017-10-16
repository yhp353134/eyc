package com.fh.controller.system.role;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.TreeNode;
import com.fh.entity.system.TreeNodeDefined;
import com.fh.entity.system.User;
import com.fh.service.system.menu.MenuService;
import com.fh.service.system.role.RoleService;
import com.fh.util.Const;
import com.fh.util.JsonResult;
import com.fh.util.PageData;
import com.fh.util.SysDataCode;
import com.fh.util.Tools;

/**
 * 类名称：RoleController 创建人：yuhaiping 创建时间：2014年6月30日
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

    @Resource(name = "menuService")
    private MenuService menuService;
    @Resource(name = "roleService")
    private RoleService roleService;

    /**
     * 获取权限
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getHC() {
        Session session = SecurityUtils.getSubject().getSession();
        return (Map<String, String>) session.getAttribute(Const.SESSION_QX);
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(Page page) throws Exception {
        ModelAndView mv = this.getModelAndView();
        //获取参数
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            Object roleName = pd.get("roleName");
            Object roleType = pd.get("roleType");
            Session session = SecurityUtils.getSubject().getSession();
            String orgType = "";
            if (null == session.getAttribute(Const.SESSION_ORG_TYPE)) {
                return mv;
            } else {
                orgType = session.getAttribute(Const.SESSION_ORG_TYPE).toString();
            }
            if (Integer.parseInt(orgType) == SysDataCode.ORG_TYPE_DEPOT) {
                //车厂
                if (null != roleType) {
                    pd.put("roleType", roleType.toString());
                } else {
                    pd.put("roleType", null);
                }
            } else {
                if (null != roleType) {
                    pd.put("roleType", roleType.toString());
                } else {
                    pd.put("roleType", "10120002");
                }
            }
            if (null != roleName) {
                pd.put("roleName", roleName.toString().trim());
            } else {
                pd.put("roleName", null);
            }
            pd.put("orgType", this.getOrgType());
            pd.put("dealerId", this.getDealerId());
            page.setPd(pd);
            List<PageData> roleList = this.roleService.getRoleList(page);
            //按钮权限
            mv.addObject(Const.SESSION_QX, this.getHC());
            mv.addObject(Const.SESSION_ORG_TYPE, orgType);
            mv.addObject("roleName", roleName);
            mv.addObject("roleType", roleType);
            mv.addObject("userType", this.getUser().getUSER_TYPE());
            mv.addObject("roleList", roleList);
            mv.setViewName("system/role/role_list");
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return mv;
    }

    /**
     * 跳转到新增角色页面
     * */
    @RequestMapping(value = "/toAdd")
    public ModelAndView toAddRole() {
        ModelAndView mv = this.getModelAndView();
        Session session = SecurityUtils.getSubject().getSession();
        String orgType = "";
        if (null == session.getAttribute(Const.SESSION_ORG_TYPE)) {
            return mv;
        } else {
            orgType = session.getAttribute(Const.SESSION_ORG_TYPE).toString();
        }
        mv.addObject(Const.SESSION_ORG_TYPE, orgType);
        mv.addObject("userName", (User) session.getAttribute(Const.SESSION_USER));
        mv.setViewName("system/role/role_add");
        return mv;
    }

    /**
     * 新增角色获取对应的菜单
     * 
     * @throws Exception
     * */
    @RequestMapping(value = "/getAllRoleNode")
    @ResponseBody
    public JsonResult<List<TreeNode>> getAllRoleNode() throws Exception {
        PageData pd = new PageData();
        pd = this.getPageData();
        Object roleTy = pd.get("roleTye");
        //如果角色类型为空，默认加载车厂的树结构
        if (null == roleTy) {
            roleTy = "10120001";
        }
        pd.put("orgType", this.getOrgType());
        pd.put("roleTypeVal", Integer.parseInt(roleTy.toString()));
        return this.roleService.getAllRoleNode(pd);
    }

    /**
     * 添加角色
     * @throws Exception 
     * */
    @RequestMapping(value = "/add")
    public ModelAndView addRole() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            String dealerCode = pd.getString("orgName");
            if (Tools.notEmpty(dealerCode)) {
                pd.put("orgId", dealerCode);
            } else {
                pd.put("orgId", this.getDealerId());
            }
            int isR = this.roleService.addRoles(pd);
            if (isR == 0) {
                throw new Exception("添加角色失败");
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
     * 跳转到修改角色界面页面
     * */
    @RequestMapping(value = "/toEdit")
    public ModelAndView toEdit() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        Object roleId = pd.get("roleId");
        if (null != roleId) {
            pd.put("singleRoleId", roleId.toString());
        } else {
            return null;
        }
        Session session = SecurityUtils.getSubject().getSession();
        String orgType = "";
        if (null == session.getAttribute(Const.SESSION_ORG_TYPE)) {
            return mv;
        } else {
            orgType = session.getAttribute(Const.SESSION_ORG_TYPE).toString();
        }
        mv.addObject(Const.SESSION_ORG_TYPE, orgType);
        mv.addObject("roleId", roleId);
        mv.addObject("roleMsg", this.roleService.getRoleMap(pd));
        mv.setViewName("system/role/role_edit");
        return mv;
    }

    /**
     * 修改获取角色对应的菜单
     * 
     * @throws Exception
     * */
    @RequestMapping(value = "/getEditeAllRoleNode")
    @ResponseBody
    public JsonResult<List<TreeNode>> getEditeAllRoleNode() throws Exception {
        PageData pd = new PageData();
        pd = this.getPageData();
        Object roleIdSing = pd.get("roleIdSing");
        if (null == roleIdSing) {
            return null;
        } else {
            pd.put("roleIdSing", roleIdSing.toString());
        }
        Object roleTy = pd.get("roleTye");
        //如果角色类型为空，默认加载车厂的树结构
        if (null == roleTy) {
            roleTy = "10120001";
        }
        pd.put("roleTypeVal", Integer.parseInt(roleTy.toString()));
        return this.roleService.getAllEditeRoleNode(pd);
    }

    /**
     * 修改角色
     * @throws Exception 
     * */
    @RequestMapping(value = "/toEditRole")
    public ModelAndView toEditRole() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            String dealerCode = pd.getString("orgName");
            if (Tools.notEmpty(dealerCode)) {
                pd.put("orgId", dealerCode);
            } else {
                pd.put("orgId", this.getDealerId());
            }
            int isR = this.roleService.addEditeRoles(pd);
            if (isR == 0) {
                throw new Exception("修改角色失败");
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
     * 职位列表
     */
    @RequestMapping(value = "/postList")
    public ModelAndView postList(Page page) throws Exception {
        ModelAndView mv = this.getModelAndView();
        //获取参数
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            Object postName = pd.get("postNameList");
            Object postType = pd.get("postType");
            Session session = SecurityUtils.getSubject().getSession();
            String orgType = "";
            if (null == session.getAttribute(Const.SESSION_ORG_TYPE)) {
                return mv;
            } else {
                orgType = session.getAttribute(Const.SESSION_ORG_TYPE).toString();
                if (Integer.parseInt(orgType) == SysDataCode.ORG_TYPE_DEPOT) {
                    if (null != postType) {
                        pd.put("postType", postType.toString());
                    } else {
                        pd.put("postType", null);
                    }
                } else {
                    if (null != postType) {
                        pd.put("postType", postType.toString());
                    } else {
                        pd.put("postType", "10120002");
                    }
                    pd.put("dealerId", this.getDealerId());
                }
            }
            if (null != postName) {
                pd.put("postName", postName.toString().trim());
            } else {
                pd.put("postName", null);
            }
            page.setPd(pd);
            List<PageData> postList = this.roleService.getPostList(page);
            //按钮权限
            mv.addObject(Const.SESSION_QX, this.getHC());
            mv.addObject(Const.SESSION_ORG_TYPE, orgType);
            mv.addObject("postNameList", postName);
            mv.addObject("postType", postType);
            mv.addObject("postList", postList);
            mv.setViewName("system/role/post_list");
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return mv;
    }

    /**
     * 跳转到新增职位界面
     * @throws Exception 
     * */
    @RequestMapping(value = "/toPostAddPage")
    public ModelAndView toPostAddPage() throws Exception {
        ModelAndView mv = this.getModelAndView();
        Session session = SecurityUtils.getSubject().getSession();
        String orgType = "";
        if (null == session.getAttribute(Const.SESSION_ORG_TYPE)) {
            return mv;
        } else {
            orgType = session.getAttribute(Const.SESSION_ORG_TYPE).toString();
        }
        //职位如果是车厂的，那么就不让他选择服务站了
        if (Integer.parseInt(orgType) == SysDataCode.ORG_TYPE_DEALER) {
            Long postId = this.getPostId();
            //根据职位去查询服务站信息
            PageData pd = new PageData();
            pd.put("postMsgId", postId);
            PageData dp = this.roleService.getPostMsg(pd);
            mv.addObject("pm", dp);
        }
        mv.addObject(Const.SESSION_ORG_TYPE, orgType);
        mv.addObject("userName", (User) session.getAttribute(Const.SESSION_USER));
        mv.addObject("bigOrSmall", SysDataCode.ORG_TYPE_DEPOT);
        mv.setViewName("system/role/post_add");
        return mv;
    }

    /**
     * 获取角色对应的职位
     * 
     * */
    @RequestMapping(value = "/getPostNode")
    @ResponseBody
    public JsonResult<List<TreeNodeDefined>> getPostNode() throws Exception {
        PageData pd = new PageData();
        pd = this.getPageData();
        Object roleTy = pd.get("roleTye");
        //如果角色类型为空，默认加载车厂的树结构
        if (null == roleTy) {
            roleTy = "10120001";
        }
        pd.put("postType", Integer.parseInt(roleTy.toString()));
        return this.roleService.getPostNode(pd);
    }

    /**
     * 根据当前选择的类型，获取对应的角色
     * */
    @RequestMapping(value = "/jumpRolePage")
    public ModelAndView jumpRolePage(Page page) throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        Object roleDetailId = pd.get("roleDetailId");
        String roleIdList = pd.getString("roleIdList");
        String roleMsgList = pd.getString("roleMsgList");
        List<PageData> roleDetilList = null;
        if (null == roleDetailId) {
            return mv;
        } else {
            pd.put("roleDetailId", roleDetailId.toString());
            pd.put("orgId", this.getDealerId());
            pd.put("orgType", this.getOrgType());
            pd.put("roleName", pd.getString("roleName"));
            page.setPd(pd);
            roleDetilList = this.roleService.getRoleDetilList(page);
            if (Tools.notEmpty(roleIdList)) {
                for (int i=0;i<roleDetilList.size();i++) {
                    String ids = Tools.checkObj(roleDetilList.get(i).get("ROLE_ID"));
                    String[] idArr = roleIdList.split(",");
                    for (int j=0;j<idArr.length;j++) {
                        String jone = idArr[j];
                        if (Long.parseLong(jone)==Long.parseLong(ids)) {
                            roleDetilList.get(i).put("IS_CHECK", "1");
                        }
                    }
                //外层for循环结束
                }
            }
        }
        mv.addObject("roleName", pd.getString("roleName"));
        mv.addObject("roleDetailId", roleDetailId);
        mv.addObject("roleDetilList", roleDetilList);
        mv.addObject("roleIdList", roleIdList);
        mv.addObject("roleMsgList", roleMsgList);
        mv.setViewName("system/role/role_detail");
        return mv;
    }

    /**
     * 添加职位
     * @throws Exception 
     * */
    @RequestMapping(value = "/addPost")
    public ModelAndView addPost() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            int isR = this.roleService.addPost(pd);
            if (isR == 0) {
                throw new Exception("添加职位失败");
            }
            mv.addObject("msg", "success");
            mv.setViewName("save_result");
        } catch (Exception e) {
            logger.error(e.toString());
            throw new Exception(e.toString());
        }
        return mv;
    }

    /***
     * 跳转到职位编辑界面
     * @throws Exception 
     * */
    @RequestMapping(value = "/jumpEditPost")
    public ModelAndView jumpEditPost() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            String postId = pd.getString("postId");
            pd.put("postMsgId", postId);
            //根据职位ID获取职位基本信息
            PageData dp = this.roleService.getPostMsg(pd);
            mv.addObject("postMsg", dp);
            //根据postId 查询角色列表
            List<PageData> rd = this.roleService.getRoleListByPost(pd);
            String rolArr = "";
            for (int i = 0; i < rd.size(); i++) {
                rolArr = rolArr + rd.get(i).get("ROLE_ID") + ",";
            }
            Session session = SecurityUtils.getSubject().getSession();
            String orgType = "";
            if (null == session.getAttribute(Const.SESSION_ORG_TYPE)) {
                return mv;
            } else {
                orgType = session.getAttribute(Const.SESSION_ORG_TYPE).toString();
            }
            mv.addObject("roleList", rd);
            mv.addObject("roleStr", rolArr);
            mv.addObject(Const.SESSION_ORG_TYPE, orgType);
            mv.setViewName("system/role/post_edit");
        } catch (Exception e) {
            logger.error(e.toString());
            throw new Exception(e.toString());
        }
        return mv;
    }

    /**
     * 获取编辑的树结构
     * **/
    @RequestMapping(value = "/getPostNodeEdite")
    @ResponseBody
    public JsonResult<List<TreeNodeDefined>> getPostNodeEdite() throws Exception {
        PageData pd = new PageData();
        pd = this.getPageData();
        String postId = pd.getString("postMsgIds");
        if (Tools.isEmpty(postId)) {
            return null;
        }
        return this.roleService.getPostNodeEdite(postId);
    }

    /**
     * 修改职位
     * @throws Exception 
     * */
    @RequestMapping(value = "/updatePost")
    public ModelAndView updatePost() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            int isR = this.roleService.updatePost(pd);
            if (isR == 0) {
                throw new Exception("修改职位失败");
            }
            mv.addObject("msg", "success");
            mv.setViewName("save_result");
        } catch (Exception e) {
            logger.error(e.toString());
            throw new Exception(e.toString());
        }
        return mv;
    }

}
