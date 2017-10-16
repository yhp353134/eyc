package com.fh.service.system.role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import com.fh.controller.base.BaseService;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.system.Button;
import com.fh.entity.system.Menu;
import com.fh.entity.system.OrgEntry;
import com.fh.entity.system.Post;
import com.fh.entity.system.Role;
import com.fh.entity.system.TreeNode;
import com.fh.entity.system.TreeNodeDefined;
import com.fh.entity.system.User;
import com.fh.util.Const;
import com.fh.util.JsonResult;
import com.fh.util.PageData;
import com.fh.util.PrimaryUtil;
import com.fh.util.Tools;

@SuppressWarnings("unchecked")
@Service("roleService")
public class RoleService extends BaseService{

    @Resource(name = "daoSupport")
    private DaoSupport dao;

    /**
     * 查询角色列表
     * */
    public List<PageData> getRoleList(Page page) throws Exception {
        return (List<PageData>) dao.findForList("RoleMapper.getRolelistPage", page);
    }

    /**
     * 查询职位列表
     * */
    public List<PageData> getPostList(Page page) throws Exception {
        return (List<PageData>) dao.findForList("RoleMapper.getPostlistPage", page);
    }
    
    /**
     * 查询用户列表
     * */
    public List<PageData> getUserlistPage(Page page) throws Exception {
        return (List<PageData>) dao.findForList("RoleMapper.getUserlistPage", page);
    }
    
    /**
     * 查询职位列表
     * */
    public List<PageData> getPostListEdite(Page page) throws Exception {
        return (List<PageData>) dao.findForList("RoleMapper.getPostListEditelistPage", page);
    }

    /**
     * 获取角色对应的菜单
     * */
    public JsonResult<List<TreeNode>> getAllRoleNode(PageData pd) throws Exception {
        //获取菜单信息
        List<Menu> oneMenu = (List<Menu>) dao.findForList("RoleMapper.getMenuList", pd);
        if (null == oneMenu) {
            return null;
        }
        int a = 1;
        int b = 1000;
        int c = 10000;
        int d = 100000;
        List<TreeNode> list = new ArrayList<TreeNode>();
        for (int i = 0; i < oneMenu.size(); i++) {
            Long menuId = oneMenu.get(i).getMENU_ID();
            String menuName = oneMenu.get(i).getMENU_NAME();
            list.add(new TreeNode(a, 0, menuId, null, menuName, false, true, true));
            pd.put("menuId", menuId);
            //根据菜单ID获取是否有子菜单
            List<Menu> twoMenu = (List<Menu>) dao.findForList("RoleMapper.getMenuListSmall", pd);
            if (0 == twoMenu.size()) {
                //如果没有二级菜单就直接放按钮
                List<Button> button = (List<Button>) dao.findForList("RoleMapper.getButtonList", pd);
                if (0 < button.size()) {
                    for (int j = 0; j < button.size(); j++) {
                        Long buttonId = button.get(j).getBUT_ID();
                        String buttonName = button.get(j).getBUT_NAME();
                        list.add(new TreeNode(a + b, a, menuId, buttonId, buttonName, false, true, true));
                        b++;
                    }
                }
            } else {
                for (int k = 0; k < twoMenu.size(); k++) {
                    Long menuTwoId = twoMenu.get(k).getMENU_ID();
                    String menuTwoName = twoMenu.get(k).getMENU_NAME();
                    list.add(new TreeNode(a + c, a, menuTwoId, null, menuTwoName, false, true, true));
                    pd.put("menuId", menuTwoId);
                    List<Button> twob = (List<Button>) dao.findForList("RoleMapper.getButtonList", pd);
                    if (0 < twob.size()) {
                        //加载菜单
                        for (int l = 0; l < twob.size(); l++) {
                            Long bId = twob.get(l).getBUT_ID();
                            String bName = twob.get(l).getBUT_NAME();
                            list.add(new TreeNode(a + c + d, a + c, menuTwoId, bId, bName, false, true, true));
                            d++;
                        }
                    }
                    c++;
                }
            }
            //最外层for循环结束
            a++;
        }
        return new JsonResult<List<TreeNode>>(Const.TRUE, list);
    }

    /**
     * 获取角色对应的菜单
     * */
    public JsonResult<List<TreeNodeDefined>> getPostNode(PageData pd) throws Exception {
        //获取第一级机构
        List<OrgEntry> oneOrg = (List<OrgEntry>) dao.findForList("RoleMapper.getOrgFirstList", null);
        List<TreeNodeDefined> list = new ArrayList<TreeNodeDefined>();
        for (int i = 0; i < oneOrg.size(); i++) {
            list.add(new TreeNodeDefined(oneOrg.get(i).getORG_ID(), oneOrg.get(i).getPARENT_ORG_ID(), oneOrg.get(i)
                    .getORG_ID()+"", null, oneOrg.get(i).getORG_NAME(), false, true, false));
        }
        return new JsonResult<List<TreeNodeDefined>>(Const.TRUE, list);
    }

    /**
     * 获取角色选项
     * */
    public List<PageData> getRoleDetilList(Page page) throws Exception {
        return (List<PageData>) dao.findForList("RoleMapper.getRoleDetillistPage", page);
    }

    /***
     * 添加角色
     * 
     * @throws Exception
     * */
    public int addRoles(PageData pd) throws Exception {
        Object roleName = pd.get("roleName");
        Object roleType = pd.get("roleType");
        Object userName = pd.get("userName");
        Object startDate = pd.get("startDate");
        pd.put("createDate", new Date());
        Role role = new Role();
        Long uuid = PrimaryUtil.getPrimary();
        pd.put("rolId", uuid);
        role.setROLE_ID(uuid);
        role.setROLE_STATUS(10021001);
        if (null != roleName) {
            role.setROLE_NAME(roleName.toString());
        }
        if (null != roleType) {
            role.setROLE_TYPE(Integer.parseInt(roleType.toString()));
        }
        if (null != userName) {
            role.setCREATE_BY(userName.toString());
            pd.put("createBy", userName.toString());
        } else {
            role.setCREATE_BY("admin");
            pd.put("createBy", "admin");
        }
        if (null != startDate) {
            role.setCREATE_DATE(new Date());
        }
        role.setOrgId(Long.parseLong(Tools.checkObj(pd.get("orgId"))));
        //添加角色记录
        dao.save("RoleMapper.insertRoles", role);
        //添加菜单权限
        String  menuArr = pd.getString("menuArr");
        if (!Tools.isEmpty(menuArr)) {
            String men = menuArr.toString();
            String[] marr = men.split(",");
            for (int i = 0; i < marr.length; i++) {
                String menuId = marr[i];
                pd.put("roleMenuId", PrimaryUtil.getPrimary());
                pd.put("menuId", Long.parseLong(menuId));
                //插入角色对应的菜单
                dao.save("RoleMapper.insertRoleMenu", pd);
            }
        }
        //添加菜单按钮权限 格式：10010@1000,10010@1002,10204@1000,10204@1002
        String buttonArr = pd.getString("buttonArr");
        if (!Tools.isEmpty(buttonArr)) {
            String bu = buttonArr.toString();
            String[] bt = bu.split(",");
            for (int j = 0; j < bt.length; j++) {
                String smb = bt[j];
                if (Tools.isEmpty(smb)) {
                    continue;
                }
                String[] smbarr = smb.split("@");
                if (0 < smbarr.length) {
                    //菜单ID
                    String menIds = smbarr[0];
                    if (Tools.isEmpty(menIds)) {
                        continue;
                    }
                    //按钮ID
                    String buttId = smbarr[1];
                    pd.put("buttMenuId", PrimaryUtil.getPrimary());
                    pd.put("muBuId", Long.parseLong(menIds));
                    pd.put("butMenuId", Long.parseLong(buttId));
                    dao.save("RoleMapper.insertMenuButton", pd);
                }
            }
        }
        return 1;
    }

    /***
     * 修改角色
     * 
     * @throws Exception
     * */
    public int addEditeRoles(PageData pd) throws Exception {
        Object roleName = pd.get("roleName");
       // Object roleType = pd.get("roleType");
        Object userName = pd.get("userName");
        Object startDate = pd.get("startDate");
        Object roleId = pd.get("roleId");
        Object roleStatus = pd.get("roleStatus");
        if (null == roleId) {
            return 0;
        }
        String roleIds = roleId.toString().trim();
        pd.put("updateDate", new Date());
        Role role = new Role();
        pd.put("rolId", roleIds);
        if (null != roleStatus) {
            role.setROLE_STATUS(Integer.parseInt(roleStatus.toString()));
        }
        role.setROLE_ID(Long.parseLong(roleIds));
        if (null != roleName) {
            role.setROLE_NAME(roleName.toString());
        }
       /* if (null != roleType) {
            role.setROLE_TYPE(Integer.parseInt(roleType.toString()));
        }*/
        if (null != userName) {
            role.setUPDATE_BY(userName.toString());
            pd.put("updateBy", userName.toString());
        } else {
            pd.put("updateBy", "admin");
        }
        if (null != startDate) {
            role.setUPDATE_DATE(new Date());
        }
        role.setOrgId(Long.parseLong(Tools.checkObj(pd.get("orgId"))));
        //修改角色记录
        dao.update("RoleMapper.updateRoleMsg", role);
        //修改菜单权限
        Object menuArr = pd.get("menuArr");
        //删除对应的菜单记录
        dao.delete("RoleMapper.deleteRoleByIdAneMenu", pd);
        if (null != menuArr) {
            String men = menuArr.toString();
            String[] marr = men.split(",");
            for (int i = 0; i < marr.length; i++) {
                String menuId = marr[i];
                pd.put("roleMenuId", PrimaryUtil.getPrimary());
                pd.put("menuId", menuId);
                //修改角色对应的菜单
                dao.findForList("RoleMapper.insertRoleMenu", pd);
            }
        }
        //修改菜单按钮权限 格式：10010@1000,10010@1002,10204@1000,10204@1002
        Object buttonArr = pd.get("buttonArr");
        //先删除这个角色下面所有按钮的权限
        dao.delete("RoleMapper.deleteButtonByIdAndMenu", pd);
        if (null != buttonArr) {
            String bu = buttonArr.toString();
            String[] bt = bu.split(",");
            for (int j = 0; j < bt.length; j++) {
                String smb = bt[j];
                String[] smbarr = smb.split("@");
                if (0 < smbarr.length) {
                    //菜单ID
                    String menIds = smbarr[0];
                    if (Tools.isEmpty(menIds)) {
                        continue;
                    }
                    //按钮ID
                    String buttId = smbarr[1];
                    pd.put("buttMenuId", PrimaryUtil.getPrimary());
                    pd.put("muBuId", menIds);
                    pd.put("butMenuId", buttId);
                    dao.findForList("RoleMapper.insertMenuButton", pd);
                }
            }
        }
        return 1;
    }

    /**
     * 获取角色信息
     * */
    public PageData getRoleMap(PageData pd) throws Exception {
        return (PageData) dao.findForObject("RoleMapper.getRoleMap", pd);
    }

    /**
     * 获取修改角色对应的菜单
     * */
    public JsonResult<List<TreeNode>> getAllEditeRoleNode(PageData pd) throws Exception {
        //获取菜单信息
        List<Menu> oneMenu = (List<Menu>) dao.findForList("RoleMapper.getEditeMenuList", pd);
        if (null == oneMenu) {
            return null;
        }
        int a = 1;
        int b = 1000;
        int c = 10000;
        int d = 100000;
        List<TreeNode> list = new ArrayList<TreeNode>();
        for (int i = 0; i < oneMenu.size(); i++) {
            Long menuId = oneMenu.get(i).getMENU_ID();
            String menuName = oneMenu.get(i).getMENU_NAME();
            if (oneMenu.get(i).isHasMenu()) {
                list.add(new TreeNode(a, 0, menuId, null, menuName, true, true, true));
            } else {
                list.add(new TreeNode(a, 0, menuId, null, menuName, false, true, true));
            }
            pd.put("menuId", menuId);
            //根据菜单ID获取是否有子菜单
            List<Menu> twoMenu = (List<Menu>) dao.findForList("RoleMapper.getEditeMenuListSmall", pd);
            if (0 == twoMenu.size()) {
                //如果没有二级菜单就直接放按钮
                List<Button> button = (List<Button>) dao.findForList("RoleMapper.getEditeButtonList", pd);
                if (0 < button.size()) {
                    for (int j = 0; j < button.size(); j++) {
                        Long buttonId = button.get(j).getBUT_ID();
                        String buttonName = button.get(j).getBUT_NAME();
                        if (button.get(j).isHasButton()) {
                            list.add(new TreeNode(a + b, a, menuId, buttonId, buttonName, true, true, true));
                        } else {
                            list.add(new TreeNode(a + b, a, menuId, buttonId, buttonName, false, true, true));
                        }
                        b++;
                    }
                }
            } else {
                for (int k = 0; k < twoMenu.size(); k++) {
                    Long menuTwoId = twoMenu.get(k).getMENU_ID();
                    String menuTwoName = twoMenu.get(k).getMENU_NAME();
                    if (twoMenu.get(k).isHasMenu()) {
                        list.add(new TreeNode(a + c, a, menuTwoId, null, menuTwoName, true, true, true));
                    } else {
                        list.add(new TreeNode(a + c, a, menuTwoId, null, menuTwoName, false, true, true));
                    }
                    pd.put("menuId", menuTwoId);
                    List<Button> twob = (List<Button>) dao.findForList("RoleMapper.getEditeButtonList", pd);
                    if (0 < twob.size()) {
                        //加载菜单
                        for (int l = 0; l < twob.size(); l++) {
                            Long bId = twob.get(l).getBUT_ID();
                            String bName = twob.get(l).getBUT_NAME();
                            if (twob.get(l).isHasButton()) {
                                list.add(new TreeNode(a + c + d, a + c, menuTwoId, bId, bName, true, true, true));
                            } else {
                                list.add(new TreeNode(a + c + d, a + c, menuTwoId, bId, bName, false, true, true));
                            }
                            d++;
                        }
                    }
                    c++;
                }
            }
            //最外层for循环结束
            a++;
        }
        return new JsonResult<List<TreeNode>>(Const.TRUE, list);
    }

    /**
     * 新增职位
     * */
    public int addPost(PageData pd) throws Exception {
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute(Const.SESSION_USER);
        Post post = new Post();
        Long uuid = PrimaryUtil.getPrimary();
        post.setPOST_ID(uuid);
        post.setPOST_NAME(pd.get("postName").toString());
        post.setCREATE_BY(user.getUSER_ID()+"");
        post.setCREATE_DATE(new Date());
        post.setPOST_TYPE(new Integer(pd.get("postType").toString()));
        post.setPOST_STATUS(10021001);
        String roleTypeStr = pd.getString("orgArr");//机构ID
        if (Tools.isEmpty(roleTypeStr)) {
            post.setORG_ID(pd.getString("orgName"));//服务站ID
        } else {
            post.setORG_ID(roleTypeStr);
        }
        pd.get("orgName");
        Object roleStr = pd.get("roleArr");
        if (null == roleStr) {
            return 0;
        }
        //新增职位记录
        dao.save("RoleMapper.insertPosts", post);
        //新增职位与角色的关系记录
        String str = roleStr.toString();
        String[] arrRole = str.split(",");
        for (int i = 0; i < arrRole.length; i++) {
            String roleId = arrRole[i];
            pd.put("postRoleId", PrimaryUtil.getPrimary());
            pd.put("roleId", roleId);
            pd.put("postId", uuid);
            pd.put("createBy", user.getUSER_NAME());
            pd.put("createTime", new Date());
            dao.save("RoleMapper.insertPostRoleTb", pd);
        }
        return 1;
    }

    /**
     * 修改职位
     * */
    public int updatePost(PageData pd) throws Exception {
        String postId = pd.getString("postId");
        if (Tools.isEmpty(postId)) {
            return 0;
        }
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute(Const.SESSION_USER);
        Post post = new Post();
        post.setPOST_ID(Long.parseLong(postId));
        post.setPOST_NAME(pd.get("postName").toString());
        post.setUPDATE_BY(user.getUSER_ID()+"");
        post.setUPDATE_DATE(new Date());
        post.setPOST_TYPE(new Integer(pd.get("postType").toString()));
        String postStatus = pd.getString("postStatus");
        if (Tools.notEmpty(postStatus)) {
            post.setPOST_STATUS(Integer.parseInt(postStatus));
        }
        String roleTypeStr = pd.getString("orgArr");//机构ID
        if (Tools.isEmpty(roleTypeStr)) {
            post.setORG_ID(pd.getString("orgName"));//经销商ID
        } else {
            post.setORG_ID(roleTypeStr);
        }
        //修改职位记录
        dao.update("RoleMapper.updatePosts", post);
        Object roleStr = pd.get("roleArr");
        if (null == roleStr) {
            return 0;
        }
        //修改职位与角色的关系记录
        String str = roleStr.toString();
        String[] arrRole = str.split(",");
        //循环添加之前，先删除记录
        pd.put("postid", postId);
        dao.delete("RoleMapper.delterPostRoleMsg", pd);
        for (int i = 0; i < arrRole.length; i++) {
            String roleId = arrRole[i];
            pd.put("postRoleId", PrimaryUtil.getPrimary());
            pd.put("roleId", roleId);
            pd.put("postId", postId);
            pd.put("createBy", user.getUSER_NAME());
            pd.put("createTime", new Date());
            dao.save("RoleMapper.insertPostRoleTb", pd);
        }
        return 1;
    }

    /**
     * 查询职位信息
     * */
    public PageData getPostMsg(PageData pd) throws Exception {
        return (PageData) dao.findForObject("RoleMapper.getPostMsg", pd);
    }

    /**
     * 获取角色对应的机构
     * */
    public JsonResult<List<TreeNodeDefined>> getPostNodeEdite(String postId) throws Exception {
        //获取第一级机构
        List<OrgEntry> oneOrg = (List<OrgEntry>) dao.findForList("RoleMapper.getOrgFirstList", null);
        List<TreeNodeDefined> list = new ArrayList<TreeNodeDefined>();
        for (int i = 0; i < oneOrg.size(); i++) {
            if (postId.equals(oneOrg.get(i).getORG_ID())) {
                list.add(new TreeNodeDefined(oneOrg.get(i).getORG_ID(), oneOrg.get(i).getPARENT_ORG_ID(), oneOrg.get(i)
                        .getORG_ID()+"", null, oneOrg.get(i).getORG_NAME(), true, true, true));
            } else {
                list.add(new TreeNodeDefined(oneOrg.get(i).getORG_ID(), oneOrg.get(i).getPARENT_ORG_ID(), oneOrg.get(i)
                        .getORG_ID()+"", null, oneOrg.get(i).getORG_NAME(), false, true, false));
            }
        }
        return new JsonResult<List<TreeNodeDefined>>(Const.TRUE, list);
    }

    /**
     * 根据职位ID获取角色列表
     * */
    public List<PageData> getRoleListByPost(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("RoleMapper.getRoleListByPost", pd);
    }

    /**
     * 获取角色对应的菜单
     * */
    public List<Role> listAllERRoles() throws Exception {
        return (List<Role>) dao.findForList("RoleMapper.listAllERRoles", null);

    }

    public List<Role> listAllappERRoles() throws Exception {
        return (List<Role>) dao.findForList("RoleMapper.listAllappERRoles", null);

    }

    public List<Role> listAllRoles() throws Exception {
        return (List<Role>) dao.findForList("RoleMapper.listAllRoles", null);

    }

    //通过当前登录用的角色id获取管理权限数据
    public PageData findGLbyrid(PageData pd) throws Exception {
        return (PageData) dao.findForObject("RoleMapper.findGLbyrid", pd);
    }

    //通过当前登录用的角色id获取用户权限数据
    public PageData findYHbyrid(PageData pd) throws Exception {
        return (PageData) dao.findForObject("RoleMapper.findYHbyrid", pd);
    }

    //列出此角色下的所有用户
    public List<PageData> listAllUByRid(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("RoleMapper.listAllUByRid", pd);

    }

    //列出此角色下的所有会员
    public List<PageData> listAllAppUByRid(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("RoleMapper.listAllAppUByRid", pd);

    }

    /**
     * 列出此部门的所有下级
     */
    public List<Role> listAllRolesByPId(PageData pd) throws Exception {
        return (List<Role>) dao.findForList("RoleMapper.listAllRolesByPId", pd);

    }

    //列出K权限表里的数据 
    public List<PageData> listAllkefu(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("RoleMapper.listAllkefu", pd);
    }

    //列出G权限表里的数据 
    public List<PageData> listAllGysQX(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("RoleMapper.listAllGysQX", pd);
    }

    //删除K权限表里对应的数据
    public void deleteKeFuById(String ROLE_ID) throws Exception {
        dao.delete("RoleMapper.deleteKeFuById", ROLE_ID);
    }

    //删除G权限表里对应的数据
    public void deleteGById(String ROLE_ID) throws Exception {
        dao.delete("RoleMapper.deleteGById", ROLE_ID);
    }

    public void deleteRoleById(String ROLE_ID) throws Exception {
        dao.delete("RoleMapper.deleteRoleById", ROLE_ID);

    }

    public Role getRoleById(String roleId) throws Exception {
        return (Role) dao.findForObject("RoleMapper.getRoleById", roleId);

    }

    public void updateRoleRights(Role role) throws Exception {
        dao.update("RoleMapper.updateRoleRights", role);
    }

    /**
     * 权限(增删改查)
     */
    public void updateQx(String msg, PageData pd) throws Exception {
        dao.update("RoleMapper." + msg, pd);
    }

    /**
     * 客服权限
     */
    public void updateKFQx(String msg, PageData pd) throws Exception {
        dao.update("RoleMapper." + msg, pd);
    }

    /**
     * Gc权限
     */
    public void gysqxc(String msg, PageData pd) throws Exception {
        dao.update("RoleMapper." + msg, pd);
    }

    /**
     * 给全部子职位加菜单权限
     */
    public void setAllRights(PageData pd) throws Exception {
        dao.update("RoleMapper.setAllRights", pd);

    }

    /**
     * 添加
     */
    public void add(PageData pd) throws Exception {
        dao.findForList("RoleMapper.insert", pd);
    }

    /**
     * 保存客服权限
     */
    public void saveKeFu(PageData pd) throws Exception {
        dao.findForList("RoleMapper.saveKeFu", pd);
    }

    /**
     * 保存G权限
     */
    public void saveGYSQX(PageData pd) throws Exception {
        dao.findForList("RoleMapper.saveGYSQX", pd);
    }

    /**
     * 通过id查找
     */
    public PageData findObjectById(PageData pd) throws Exception {
        return (PageData) dao.findForObject("RoleMapper.findObjectById", pd);
    }

    /**
     * 编辑角色
     */
    public PageData edit(PageData pd) throws Exception {
        return (PageData) dao.findForObject("RoleMapper.edit", pd);
    }

}
