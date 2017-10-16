package com.fh.util.oss;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.GenericRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;

/** 
 *  
 * @ClassName: OSSUploadUtil 
 * @Description: 阿里云OSS文件上传工具类 
 * @author AggerChen 
 * @date 2016年11月3日 下午12:03:24 
 */  
public class OSSUploadUtil {
	
	private static OSSConfig config = null;

	 /** 
     *  
     * @MethodName: uploadFile 
     * @Description: OSS单文件上传 
     * @param file   
     * @param fileType 文件后缀 
     * @return String 文件地址 
     */  
	public static String uploadFile(MultipartFile file) throws Exception{

		String fileRealName = file.getOriginalFilename();
		CommonsMultipartFile cf = (CommonsMultipartFile) file;
		// ���myfile��MultipartFile��
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		
		String fileType = "";
		if (fileRealName.indexOf(".") != -1) {
			String[] names = fileRealName.split("\\.");
			fileType = names[names.length - 1].toString();
			if(fileType.indexOf("?")!=-1){
				fileType = fileType.substring(0, fileType.lastIndexOf("?"));
			}		 
					
		}
		config = config == null ? new OSSConfig() : config;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String fileName = "SYS_FILES/" + ymd + "/" + UUID.randomUUID().toString().toUpperCase().replace("-", "") + "."
				+ fileType; //文件名，根据UUID来  
		return putObject(fi.getInputStream(), fileType, fileName);
	}
	
	/**
	 * 
	 * @param is
	 * @param fileType
	 * @return
	 * @throws Exception
	 */
	public static String uploadFile(InputStream is,String fileType) throws Exception{

		config = config == null ? new OSSConfig() : config;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String fileNameT = "SYS_FILES/" + ymd + "/" + UUID.randomUUID().toString().toUpperCase().replace("-", "") + "."
				+ fileType; //文件名，根据UUID来  
		return putObject(is, fileType, fileNameT);
	}
	/**
	 * 编辑器编辑初始化素材（专用）
	 * @param is
	 * @param fileType
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static String uploadFile1(InputStream is,String fileType,String fileName) throws Exception{

		config = config == null ? new OSSConfig() : config;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String fileNameT = "EditorImg/"+fileName;
		return putObjectno(is, fileType, fileNameT);
	}


	   /** 
     *  
     * @MethodName: updateFile 
     * @Description: 更新文件:只更新内容，不更新文件名和文件地址。 
     *      (因为地址没变，可能存在浏览器原数据缓存，不能及时加载新数据，例如图片更新，请注意) 
     * @param file 
     * @param fileType 
     * @param oldUrl 
     * @return String 
     */  
	 public static String updateFile(MultipartFile file,String oldUrl) throws Exception{
		 
		 String fileName = getFileName(oldUrl);
		 String fileType="";
			 if(fileName.indexOf(".")!=-1){
			 String[] names=fileName.split("\\.");
			 fileType=names[names.length-1].toString();
		 }
		
		 
		 if(fileName==null) return null;
		 return putObject(file.getInputStream(),fileType,fileName);
	 }

	 /** 
	     *  
	     * @MethodName: replaceFile 
	     * @Description: 替换文件:删除原文件并上传新文件，文件名和地址同时替换 
	     *      解决原数据缓存问题，只要更新了地址，就能重新加载数据) 
	     * @param file 
	     * @param fileType 文件后缀 
	     * @param oldUrl 需要删除的文件地址 
	     * @return String 文件地址 
	     */  
	public static String replaceFile(MultipartFile file,String oldUrl) throws Exception{
		boolean flag = deleteFile(oldUrl);		//��ɾ��ԭ�ļ�
		if(!flag){
			//�����ļ��Ĺ���ʱ�䣬���������Զ�ɾ����
		}
		return uploadFile(file);
	}
	
	/** 
     *  
     * @MethodName: deleteFile 
     * @Description: 单文件删除 
     * @param fileUrltemp 需要删除的文件url 
     * @return boolean 是否删除成功 
     */  
	public static boolean deleteFile(String fileUrltemp){
		config = config==null?new OSSConfig():config;
		String fileUrl="";
		String suffx=fileUrltemp.substring(0,fileUrltemp.indexOf("/SYS_FILES"));
		if(suffx.equals(config.getEndpoint())){
			fileUrl=fileUrltemp.replace(suffx,config.getEndpoint().replaceFirst("http://","http://"+config.getBucketName()+".") ); 	
		}	
		String bucketName = OSSUploadUtil.getBucketName(fileUrl);		//����url��ȡbucketName
		String fileName = OSSUploadUtil.getFileName(fileUrl);			//����url��ȡfileName
		if(bucketName==null||fileName==null) return false;
		OSSClient ossClient = null; 
		try {
			ossClient = new OSSClient(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret()); 
			GenericRequest request = new DeleteObjectsRequest(bucketName).withKey(fileName);
			ossClient.deleteObject(request);
		} catch (Exception oe) {
            oe.printStackTrace();
            return false;
        } finally {
            ossClient.shutdown();
        }
		return true;
	}
	 /** 
     *  
     * @MethodName: batchDeleteFiles 
     * @Description: 批量文件删除(较快)：适用于相同endPoint和BucketName 
     * @param fileUrls 需要删除的文件url集合 
     * @return int 成功删除的个数 
     */  
	public static int deleteFile(List<String> fileUrls){
		int deleteCount = 0;	//�ɹ�ɾ���ĸ���
		String bucketName = OSSUploadUtil.getBucketName(fileUrls.get(0));		//����url��ȡbucketName
		List<String> fileNames = OSSUploadUtil.getFileName(fileUrls);			//����url��ȡfileName
		if(bucketName==null||fileNames.size()<=0) return 0;
		OSSClient ossClient = null; 
		try {
			ossClient = new OSSClient(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret()); 
			DeleteObjectsRequest request = new DeleteObjectsRequest(bucketName).withKeys(fileNames);
			DeleteObjectsResult result = ossClient.deleteObjects(request);
			deleteCount = result.getDeletedObjects().size();
		} catch (OSSException oe) {
            oe.printStackTrace();
            throw new RuntimeException("OSS�����쳣:", oe);
        } catch (ClientException ce) {
        	ce.printStackTrace();
			throw new RuntimeException("OSS�ͻ����쳣:", ce);
        } finally {
            ossClient.shutdown();
        }
		return deleteCount;
		
	}
	
	/** 
     *  
     * @MethodName: batchDeleteFiles 
     * @Description: 批量文件删除(较慢)：适用于不同endPoint和BucketName 
     * @param fileUrls 需要删除的文件url集合 
     * @return int 成功删除的个数 
     */  
	public static int deleteFiles(List<String> fileUrls){
		int count = 0;
		for (String url : fileUrls) {
			if(deleteFile(url)){
				count++;
			}
		}
		return count;
	}
	
	 /** 
     *  
     * @MethodName: putObject 
     * @Description: 上传文件 
     * @param file 
     * @param fileType 
     * @param fileName 
     * @return String 
     */  
	private static String putObject(InputStream is,String fileType,String fileNewName){
		
		config = config==null?new OSSConfig():config;
		String url = null;		//Ĭ��null
		OSSClient ossClient = null;  
		try {
			System.out.println("==========sc===fileType="+fileType);
			//========2017-04-12 chensh 添加图片压缩========begin
			//图片大于150k，则进行尺寸大小不变的压缩
			if(fileType.toLowerCase().equals("tiff") ||fileType.toLowerCase().equals("jpeg") ||fileType.toLowerCase().equals("jpg") || fileType.toLowerCase().equals("png") || fileType.toLowerCase().equals("bmp") || fileType.toLowerCase().equals("gif") ){
				
				BufferedImage bi;
				bi = Thumbnails.of(is).scale(1).asBufferedImage();
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			    ImageOutputStream imageOutput = ImageIO.createImageOutputStream(byteArrayOutputStream);
			    ImageIO.write(bi, fileType, imageOutput);
			    is = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
				int filesize = is.available()/1024; //文件大小
				System.out.println("=======000===is="+is.available());
				if(filesize>150 ){
					double sc = (double)((float)150 /(float)filesize );
					bi = Thumbnails.of(is).scale(1).outputQuality(sc).asBufferedImage();
					byteArrayOutputStream = new ByteArrayOutputStream();
				    imageOutput = ImageIO.createImageOutputStream(byteArrayOutputStream);
				    ImageIO.write(bi, fileType, imageOutput);
				    is = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
				    System.out.println("==========is="+is.available());
				    filesize = is.available()/1024;
				    if(filesize>150){
				    	//图片大于150k，则进行尺寸大小的压缩，等比缩放
					    sc = (double)((float)150 /(float)filesize );
						
						System.out.println("==========sc="+sc);
						bi = Thumbnails.of(is).scale(sc).asBufferedImage();
						byteArrayOutputStream = new ByteArrayOutputStream();
					    imageOutput = ImageIO.createImageOutputStream(byteArrayOutputStream);
					    ImageIO.write(bi, fileType, imageOutput);
					    is = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
					    System.out.println("=========11=is="+is.available());
				    }
				}
			}
			//========2017-04-12 chensh 添加图片压缩========end
			ossClient = new OSSClient(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret()); 
			ObjectMetadata meta = new ObjectMetadata();				// �����ϴ�Object��Metadata
			meta.setContentType(OSSUploadUtil.contentType(fileType));		// �����ϴ���������
			meta.setCacheControl("no-cache");					// ������ʱ��ҳ�Ļ�����Ϊ  
			PutObjectRequest request = new PutObjectRequest(config.getBucketName(), fileNewName,is,meta);			//�����ϴ�����
			ossClient.putObject(request);
			
			if(config.getNginxHTTPServer().equals("off")||config.getNginxHTTPServer().equals("OFF")){
				url = config.getEndpoint().replaceFirst("http://","http://"+config.getBucketName()+".")+"/"+fileNewName;		//�ϴ��ɹ��ٷ��ص��ļ�·��
			}else{
				url= config.getNginxHTTPUrl()+"/"+fileNewName;
			}
			
		} catch (OSSException oe) {
            oe.printStackTrace();
            return null;
        } catch (ClientException ce) {
        	ce.printStackTrace();
        	return null;
        } catch(Exception e){
			e.printStackTrace();
		}
		finally {
            ossClient.shutdown();
        }
		return url;
	}
	
	private static String putObjectno(InputStream is,String fileType,String fileNewName){
		
		config = config==null?new OSSConfig():config;
		String url = null;		//Ĭ��null
		OSSClient ossClient = null;  
		try {
			
			ossClient = new OSSClient(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret()); 
			ObjectMetadata meta = new ObjectMetadata();				// �����ϴ�Object��Metadata
			meta.setContentType(OSSUploadUtil.contentType(fileType));		// �����ϴ���������
			meta.setCacheControl("no-cache");					// ������ʱ��ҳ�Ļ�����Ϊ  
			PutObjectRequest request = new PutObjectRequest(config.getBucketName(), fileNewName,is,meta);			//�����ϴ�����
			ossClient.putObject(request);
			
			if(config.getNginxHTTPServer().equals("off")||config.getNginxHTTPServer().equals("OFF")){
				url = config.getEndpoint().replaceFirst("http://","http://"+config.getBucketName()+".")+"/"+fileNewName;		//�ϴ��ɹ��ٷ��ص��ļ�·��
			}else{
				url= config.getNginxHTTPUrl()+"/"+fileNewName;
			}
			
		} catch (OSSException oe) {
            oe.printStackTrace();
            return null;
        } catch (ClientException ce) {
        	ce.printStackTrace();
        	return null;
        } catch(Exception e){
			e.printStackTrace();
		}
		finally {
            ossClient.shutdown();
        }
		return url;
	}
	
	 /** 
     *  
     * @MethodName: contentType 
     * @Description: 获取文件类型 
     * @param FileType 
     * @return String 
     */  
	private static String contentType(String fileType){
		fileType = fileType.toLowerCase();
		String contentType = "";
		
		if(fileType.equals("bmp")){
			contentType = "image/bmp";
		}else if(fileType.equals("gif")){
			contentType = "image/gif";
		}else if(fileType.equals("png")||fileType.equals("jpeg")||fileType.equals("jpg")){
			contentType = "image/jpeg";
		}else if(fileType.equals("html")){
			contentType = "text/html";
		}else if(fileType.equals("txt")){
			contentType = "text/plain";
		}else if(fileType.equals("vsd")){
			contentType = "application/vnd.visio";
		}else if(fileType.equals("ppt")||fileType.equals("pptx")){
			contentType = "application/vnd.ms-powerpoint";
		}else if(fileType.equals("doc")||fileType.equals("docx")){
			contentType = "application/msword";
		}else if(fileType.equals("xml")){
			contentType = "text/xml";
		}else if(fileType.equals("mp4")){
			contentType = "video/mp4";
		}else{
			contentType = "application/octet-stream";
		}
		
		return contentType;
     }  
	
	/**
	 * 
	 * @MethodName: getBucketName
	 * @Description: ����url��ȡbucketName
	 * @param fileUrl �ļ�url
	 * @return String bucketName
	 */
	private static String getBucketName(String fileUrl){
		String http = "http://";
		String https = "https://";
		int httpIndex = fileUrl.indexOf(http);
		int httpsIndex = fileUrl.indexOf(https);
		int startIndex  = 0;
		if(httpIndex==-1){
			if(httpsIndex==-1){
				return null;
			}else{
				startIndex = httpsIndex+https.length();
			}
		}else{
			startIndex = httpIndex+http.length();
		}
		int endIndex = fileUrl.indexOf(".oss-"); 
		return fileUrl.substring(startIndex, endIndex);
	}
	
	/** 
     *  
     * @MethodName: getFileName 
     * @Description: 根据url获取fileName 
     * @param fileUrl 文件url 
     * @return String fileName 
     */  
	private static String getFileName(String fileUrl){
		String str = "aliyuncs.com/";
		int beginIndex = fileUrl.indexOf(str);
		if(beginIndex==-1) return null;
		return fileUrl.substring(beginIndex+str.length());
	}

	public static Object[] getOSSObjectInputStream(String key) {
		Object[] obj = new Object[3];
		config = config==null?new OSSConfig():config;
		OSSClient ossClient = new OSSClient(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret());
		try {
			String splitStr="SYS_FILES";
			String str2[]=key.split(splitStr);	
			OSSObject ossObj = ossClient.getObject(config.getBucketName(), splitStr+str2[1]);
			Long length=ossObj.getObjectMetadata().getContentLength();
			obj[0]=ossObj.getObjectContent();
			obj[1]=length;
			obj[2]=ossClient;
			System.out.println("===getOSSObjectInputStream=========");
		} catch (Exception e) {
			System.out.println("====getOSSObjectInputStream=======e="+e.toString());
			e.printStackTrace();
			return  null;
		}finally {
			//ossClient.shutdown();
		} 
		
		return obj;
	}  
	/**
	 * 百度编辑器下的文件
	 * @param key
	 * @return
	 */
	public static Object[] getOSSObjectInputStream1(String key) {
		String splitStr="UEDITOR_FILES";
		Object[] obj = new Object[2];
		config = config==null?new OSSConfig():config;
		OSSClient ossClient = new OSSClient(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret());
		try {
			String str2[]=key.split(splitStr);	
			OSSObject ossObj = ossClient.getObject(config.getBucketName(), splitStr+str2[1]);
			Long length=ossObj.getObjectMetadata().getContentLength();
			obj[0]=ossObj.getObjectContent();
			obj[1]=length;
		} catch (Exception e) {
			e.printStackTrace();
			return  null;
		}finally {
			ossClient.shutdown();
		} 
		
		return obj;
	}   

	   
    /** 
     *  
     * @MethodName: getFileName 
     * @Description: 根据url获取fileNames集合 
     * @param fileUrl 文件url 
     * @return List<String>  fileName集合 
     */  
	private static List<String> getFileName(List<String> fileUrls){
		List<String> names = new ArrayList<String>();
		for (String url : fileUrls) {
			names.add(getFileName(url));
		}
		return names;
	}
	 public static void main(String[] args) 
	    {
	        System.out.println("打印main方法的输入参数");
	        for(int i=0;i<args.length;i++){
	            System.out.println(args[i]);
	        }
	    }
}

