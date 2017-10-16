package com.fh.controller.system;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;
import jcifs.smb.SmbSession;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.util.Const;
import com.infoservice.filestore.FileStore;

@Controller
@RequestMapping(value="/testLoad")
public class TestController extends BaseController{
	
	private static String domainip = "114.55.41.71";  
    private static String username = "administrator";  
    private static String password = "Wqy7602134";  
    private static String remoteurl = "smb://114.55.41.71/tmps";

	/***货源列表 * */
	@RequestMapping(value = "/testPage")
	public ModelAndView userList() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.addObject(Const.SESSION_QX, this.getPermission());
		mv.setViewName("test_page");
		return mv;
	}
	
	@RequestMapping(value = "/fileUpload")
	public void fileUpload(@RequestParam MultipartFile memoFile,HttpServletRequest request) throws Exception {
		 try {      
		
		FileStore store = FileStore.getInstance();
		String aa = memoFile.getOriginalFilename();
		byte[] bb= memoFile.getBytes();
		String fileName = store.write(memoFile.getOriginalFilename(), memoFile.getBytes());
		String url = store.getDomainURL(fileName);
		store.write(fileName, memoFile.getBytes());
		System.out.println("返回的路径："+url);
		
       /* 
        InputStream in = null;      
        OutputStream out = null;      
        SmbFile remoteFile = null;  
       
            CommonsMultipartFile cf= (CommonsMultipartFile)memoFile; 
            DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
            File localFile = fi.getStoreLocation();
            SimpleDateFormat sim1=new SimpleDateFormat("yyMMddHHmmssSSS");  
            //上传后的文件名  
           String newFileName="123"; 
           
           NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(domainip, username, password);  //先登录验证  
           remoteFile = new SmbFile(remoteurl+"//"+"dn01",auth);  
           remoteFile.connect();
           
            in = new BufferedInputStream(new FileInputStream(localFile));      
            out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));      
            byte[] buffer = new byte[1024];      
            while (in.read(buffer) != -1) {      
                out.write(buffer);      
                buffer = new byte[1024];      
            }      */
          
        } catch (Exception e) {      
            e.printStackTrace();      
        } finally {      
        }    
       
        System.out.println("==完成==="); 

	     /*NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, "administrator", "Wqy7602134");
	     // NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, null, null);
	     UniAddress dc = UniAddress.getByName("114.55.41.71");
	     SmbSession.logon(dc, auth);
	     System.out.println("==完成===");   */

	}
	
	  //把经纬度转为度（°） 
	 private static double rad(double d){  
	       return d * Math.PI / 180.0;  
	    } 
	
    /**  
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米  
     * @param lng1  
     * @param lat1  
     * @param lng2  
     * @param lat2  
     * @return  
     */  
    public static double getDistance(double lng1, double lat1, double lng2, double lat2){  
       double radLat1 = rad(lat1);  
       double radLat2 = rad(lat2);  
       double a = radLat1 - radLat2;  
       double b = rad(lng1) - rad(lng2);  
       double s = 2 * Math.asin(  
            Math.sqrt(  
                Math.pow(Math.sin(a/2),2)   
                + Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)  
            )  
        );  
       s = s * 6378137;  
       s = Math.round(s * 10000) / 10000;  
       return s;  
    }
	
	
	private void mian() {
		// getDistance();

	}
	
}


