/**
 * TreeNode.java
 * Created at 2017年3月9日
 * Created by yuhaiping
 * Copyright (C) 2017 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.fh.entity.system;

import java.io.Serializable;

/**
 * 树节点类
 * */
public class TreeNode implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer pId;
    private Long menuId;
    private Long buttonId;
    private String name;
    private Boolean checked;
    private Boolean open;
    private Boolean check;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getButtonId() {
        return buttonId;
    }

    public void setButtonId(Long buttonId) {
        this.buttonId = buttonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public TreeNode(Integer id, Integer pId, Long menuId, Long buttonId, String name, Boolean checked, Boolean open,
            Boolean check) {
        super();
        this.id = id;
        this.pId = pId;
        this.menuId = menuId;
        this.buttonId = buttonId;
        this.name = name;
        this.checked = checked;
        this.open = open;
        this.check = check;
    }

    public TreeNode() {
        super();
    }

}
