/**
 * OrgEntry.java
 * Created at 2017年3月11日
 * Created by yuhaiping
 * Copyright (C) 2017 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.fh.entity.system;

import java.io.Serializable;

/**
 * 机构实体类
 */
public class OrgEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long ORG_ID;
    private String ORG_CODE;
    private String ORG_NAME;
    private String ORG_LEVEL;
    private Long PARENT_ORG_ID;
    private String CREATE_BY;
    private String CREATE_DATE;
    private String UPDATE_BY;
    private String UPDATE_DATE;

    public Long getORG_ID() {
        return ORG_ID;
    }

    public void setORG_ID(Long oRG_ID) {
        ORG_ID = oRG_ID;
    }

    public String getORG_CODE() {
        return ORG_CODE;
    }

    public void setORG_CODE(String oRG_CODE) {
        ORG_CODE = oRG_CODE;
    }

    public String getORG_NAME() {
        return ORG_NAME;
    }

    public void setORG_NAME(String oRG_NAME) {
        ORG_NAME = oRG_NAME;
    }

    public String getORG_LEVEL() {
        return ORG_LEVEL;
    }

    public void setORG_LEVEL(String oRG_LEVEL) {
        ORG_LEVEL = oRG_LEVEL;
    }

    public Long getPARENT_ORG_ID() {
        return PARENT_ORG_ID;
    }

    public void setPARENT_ORG_ID(Long pARENT_ORG_ID) {
        PARENT_ORG_ID = pARENT_ORG_ID;
    }

    public String getCREATE_BY() {
        return CREATE_BY;
    }

    public void setCREATE_BY(String cREATE_BY) {
        CREATE_BY = cREATE_BY;
    }

    public String getCREATE_DATE() {
        return CREATE_DATE;
    }

    public void setCREATE_DATE(String cREATE_DATE) {
        CREATE_DATE = cREATE_DATE;
    }

    public String getUPDATE_BY() {
        return UPDATE_BY;
    }

    public void setUPDATE_BY(String uPDATE_BY) {
        UPDATE_BY = uPDATE_BY;
    }

    public String getUPDATE_DATE() {
        return UPDATE_DATE;
    }

    public void setUPDATE_DATE(String uPDATE_DATE) {
        UPDATE_DATE = uPDATE_DATE;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
