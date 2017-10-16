package com.fh.controller.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://eyc.service.com")
public interface EycService {

    /**
     * 传输货源接口 sendOwnerSourceDetail
     * @throws Exception 异常信息
     * */
    @WebMethod
    public String sendOwnerSourceDetail(@WebParam(name = "sourceMsg") String sourceMsg) throws Exception;

   

}
