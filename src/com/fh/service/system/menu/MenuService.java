package com.fh.service.system.menu;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.system.Button;
import com.fh.entity.system.Menu;
import com.fh.util.PageData;

@SuppressWarnings("unchecked")
@Service("menuService")
public class MenuService {

    @Resource(name = "daoSupport")
    private DaoSupport dao;

    /**
     * 根据职位ID获取菜单LIST
     * */
    public List<Menu> getMenuListByPostId(@Param("postPar") Long postPar) throws Exception {
        return (List<Menu>) dao.findForList("MenuMapper.getMenuListByPostId", postPar);
    }
    
    /**
     * 根据职位ID获取职位信息
     * */
    public PageData getPostByPostId(@Param("postId") Long postId) throws Exception {
        return (PageData) dao.findForObject("MenuMapper.getPostByPostId", postId);
    }
    
    /**
     * 根据菜单ID获取菜单LIST
     * */
    public List<Menu> getMenuListByMenuId(PageData pd) throws Exception {
        return (List<Menu>) dao.findForList("MenuMapper.getMenuListByMenuId", pd);
    }
    
    /**
     * 根据菜单ID和用户名获取按钮权限LIST
     * */
    public List<Button> getButtonListByMenuId(PageData pd) throws Exception {
        return (List<Button>) dao.findForList("MenuMapper.getButtonListByMenuId", pd);
    }

    public void deleteMenuById(@Param("MENU_ID") Long MENU_ID) throws Exception {
        dao.save("MenuMapper.deleteMenuById", MENU_ID);

    }

    public PageData getMenuById(PageData pd) throws Exception {
        return (PageData) dao.findForObject("MenuMapper.getMenuById", pd);

    }

    //取最大id
    public PageData findMaxId(PageData pd) throws Exception {
        return (PageData) dao.findForObject("MenuMapper.findMaxId", pd);

    }

    public List<Menu> listAllParentMenu() throws Exception {
        return (List<Menu>) dao.findForList("MenuMapper.listAllParentMenu", null);

    }

    public void saveMenu(Menu menu) throws Exception {
        if (menu.getMENU_ID() != null) {
            //dao.update("MenuMapper.updateMenu", menu);
            dao.save("MenuMapper.insertMenu", menu);
        } else {
            dao.save("MenuMapper.insertMenu", menu);
        }
    }

    public List<Menu> listSubMenuByParentId(@Param("parentId") Long parentId) throws Exception {
        return (List<Menu>) dao.findForList("MenuMapper.listSubMenuByParentId", parentId);

    }

    public List<Menu> listAllMenu() throws Exception {
        List<Menu> rl = this.listAllParentMenu();
        for (Menu menu : rl) {
            List<Menu> subList = this.listSubMenuByParentId(menu.getMENU_ID());
            menu.setSubMenu(subList);
        }
        return rl;
    }

    public List<Menu> listAllSubMenu() throws Exception {
        return (List<Menu>) dao.findForList("MenuMapper.listAllSubMenu", null);

    }

    /**
     * 编辑
     */
    public PageData edit(PageData pd) throws Exception {
        return (PageData) dao.findForObject("MenuMapper.updateMenu", pd);
    }

    /**
     * 保存菜单图标 (顶部菜单)
     */
    public PageData editicon(PageData pd) throws Exception {
        return (PageData) dao.findForObject("MenuMapper.editicon", pd);
    }

    /**
     * 更新子菜单类型菜单
     */
    public PageData editType(PageData pd) throws Exception {
        return (PageData) dao.findForObject("MenuMapper.editType", pd);
    }
}
