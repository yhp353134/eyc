/**
 * AuthInterceptor.java
 * Created at 2017年3月27日
 * Created by yuhaiping
 * Copyright (C) 2017 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.fh.controller.ws.auth;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
//这两个包不要导入错误
//这两个包不要导入错误



import com.fh.util.Const;

/***
 * 服务拦截器
 * */
public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public AuthInterceptor() {
		super(Phase.PRE_PROTOCOL);
		getAfter().add(SAAJInInterceptor.class.getName());
	}

	public AuthInterceptor(String phase) {
		super(phase);
	}

	// 消息拦截
	@Override
	public void handleMessage(SoapMessage soap) throws Fault {
		log.info("webservice message-start：======" + soap);
		String path = Thread.currentThread().getContextClassLoader().getResource("ws.properties").getPath();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String userName = properties.getProperty("user_name");
        String passWord = properties.getProperty("user_password");
		List<Header> headers = soap.getHeaders();
		// 检查headers是否存在
		if (headers == null | headers.size() < 1) {
			throw new Fault(new IllegalArgumentException("找不到Header，无法验证用户信息"));
		}
		Header header = headers.get(0);
		Element el = (Element) header.getObject();
		NodeList users = el.getElementsByTagName(Const.USER_NAME);
		NodeList passwords = el.getElementsByTagName(Const.USER_PASSWORD);
		// 检查是否有用户名和密码元素
		if (users.getLength() < 1) {
			throw new Fault(new IllegalArgumentException("找不到用户信息"));
		}
		String username = users.item(0).getTextContent().trim();
		if (passwords.getLength() < 1) {
			throw new Fault(new IllegalArgumentException("找不到密码信息"));
		}
		String password = passwords.item(0).getTextContent();
		// 检查用户名和密码是否正确
		if (!userName.equals(username) || !passWord.equals(password)) {
			throw new Fault(new IllegalArgumentException("用户名或密码不正确"));
		} else {
			log.info("===========ask success =============");
		}
	}

	// 类结束 
}
