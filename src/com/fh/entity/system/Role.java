package com.fh.entity.system;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色权限类
 * */
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long ROLE_ID;
    private String ROLE_NAME;
    private Integer ROLE_TYPE;
    private Integer ROLE_STATUS;
    private String CREATE_BY;
    private Date CREATE_DATE;
    private String UPDATE_BY;
    private Date UPDATE_DATE;
    private Long orgId;

    public Long getROLE_ID() {
        return ROLE_ID;
    }

    public void setROLE_ID(Long rOLE_ID) {
        ROLE_ID = rOLE_ID;
    }

    public String getROLE_NAME() {
        return ROLE_NAME;
    }

    public void setROLE_NAME(String rOLE_NAME) {
        ROLE_NAME = rOLE_NAME;
    }

    public Integer getROLE_TYPE() {
        return ROLE_TYPE;
    }

    public void setROLE_TYPE(Integer rOLE_TYPE) {
        ROLE_TYPE = rOLE_TYPE;
    }

    public Integer getROLE_STATUS() {
        return ROLE_STATUS;
    }

    public void setROLE_STATUS(Integer rOLE_STATUS) {
        ROLE_STATUS = rOLE_STATUS;
    }

    public String getCREATE_BY() {
        return CREATE_BY;
    }

    public void setCREATE_BY(String cREATE_BY) {
        CREATE_BY = cREATE_BY;
    }

    public Date getCREATE_DATE() {
        return CREATE_DATE;
    }

    public void setCREATE_DATE(Date cREATE_DATE) {
        CREATE_DATE = cREATE_DATE;
    }

    public String getUPDATE_BY() {
        return UPDATE_BY;
    }

    public void setUPDATE_BY(String uPDATE_BY) {
        UPDATE_BY = uPDATE_BY;
    }

    public Date getUPDATE_DATE() {
        return UPDATE_DATE;
    }

    public void setUPDATE_DATE(Date uPDATE_DATE) {
        UPDATE_DATE = uPDATE_DATE;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

}
