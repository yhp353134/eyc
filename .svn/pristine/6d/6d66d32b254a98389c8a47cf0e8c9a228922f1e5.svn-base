package com.fh.controller.ws.auth;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.fh.util.Const;

/**
 * 客户端拦截器
 * */
public class ClientAuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public ClientAuthInterceptor(String username, String password) {
		super(Phase.PREPARE_SEND);
		this.username = username;
		this.password = password;
	}
	
	private String username;
	private String password;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void handleMessage(SoapMessage soap) throws Fault {
	    String path = Thread.currentThread().getContextClassLoader().getResource("ws.properties").getPath();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String qname = properties.getProperty("wsdl_qname");
		List<Header> headers = soap.getHeaders();
		Document doc = DOMUtils.createDocument();
		Element auth = doc.createElement("auth");
		Element username = doc.createElement(Const.USER_NAME);
		Element password = doc.createElement(Const.USER_PASSWORD);
		username.setTextContent(this.username);
		password.setTextContent(this.password);
		auth.appendChild(username);
		auth.appendChild(password);
		// doc.appendChild(auth);
		//这里需要填写命名空间，让拦截器知道往哪个空间添加头信息
		headers.add(0, new Header(new QName(qname), auth));
		
		/*import javax.xml.namespace.QName;
		import javax.xml.rpc.ParameterMode;
		import javax.xml.soap.SOAPElement;
		import javax.xml.soap.SOAPException;
		import org.apache.axiom.om.OMAbstractFactory;
		import org.apache.axiom.om.OMElement;
		import org.apache.axiom.om.OMFactory;
		import org.apache.axiom.om.OMNamespace;
		import org.apache.axis.client.Call;
		import org.apache.axis.client.Service;
		import org.apache.axis.encoding.XMLType;
		import org.apache.axis.message.SOAPHeaderElement;
		import org.apache.axis2.AxisFault;
		import org.apache.axis2.addressing.EndpointReference;
		import org.apache.axis2.client.Options;
		import org.apache.axis2.rpc.client.RPCServiceClient;
	   SOAPHeaderElement auth_header = new SOAPHeaderElement(NAME_PACE,AUTH_HEADER); 
        auth_header.setPrefix("");
        SOAPHeaderElement user = new SOAPHeaderElement(NAME_PACE,USERNAME);  
        user.addTextNode(username);
        SOAPHeaderElement pwd = new SOAPHeaderElement(NAME_PACE,PASSWORD); 
        pwd.addTextNode(password);
        user.setPrefix("");
        pwd.setPrefix("");
        auth_header.addChildElement(user);
        auth_header.addChildElement(pwd);
        
        第二种方式
        SOAPHeaderElement soapHeaderElement = new SOAPHeaderElement(NAME_PACE,AUTH_HEADER); 
        SOAPElement addChildElement;
        try {
            addChildElement = soapHeaderElement.addChildElement(AUTH_HEADER);
            addChildElement.addChildElement(USERNAME).setValue(username);   
            addChildElement.addChildElement(PASSWORD).setValue(password);
        */
		
	}
	
}
