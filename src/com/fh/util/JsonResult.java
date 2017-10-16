/**
 * JsonResult.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.fh.util;

public class JsonResult<T> {
    /**
     * <p>
     * Field returnCode: 返回代码
     * </p>
     */
    private String returnCode;   
    /**
     * <p>
     * Field result: 返回结果
     * </p>
     */
    private T result;
    /**
     * <p>
     * Field msg: 返回消息
     * </p>
     */
    private String msg; 
    /**
     * <p>
     * Description: 构造方法
     * </p>
     * 
     * @param returnCode 返回代码
     * @param result 返回结果
     */
    public JsonResult(String returnCode, T result) {
        this.returnCode = returnCode;
        this.result = result;
        
    }
    /**
     * <p>
     * Description: 构造方法
     * </p>
     * 
     * @param returnCode 返回代码
     * @param result 返回结果
     * @param result 返回消息
     */
    public JsonResult(String returnCode, T result, String msg) {
        this.returnCode = returnCode;
        this.result = result;
        this.msg = msg;
    }    
    
    public String getReturnCode() {
        return this.returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }
    
    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
