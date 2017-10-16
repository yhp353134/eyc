package com.baidu.ueditor.upload;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import com.fh.util.oss.OSSConfig;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class BinaryUploader {
	public static OSSConfig config=null;
	public static final State save(HttpServletRequest request,
			Map<String, Object> conf) {
		FileItemStream fileStream = null;
		boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}

		ServletFileUpload upload = new ServletFileUpload(
				new DiskFileItemFactory());

        if ( isAjaxUpload ) {
            upload.setHeaderEncoding( "UTF-8" );
        }

		try {
			FileItemIterator iterator = upload.getItemIterator(request);

			while (iterator.hasNext()) {
				fileStream = iterator.next();

				if (!fileStream.isFormField())
					break;
				fileStream = null;
			}

			if (fileStream == null) {
				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			}
			String savePath = (String) conf.get("savePath");
			String originFileName = fileStream.getName();
			String suffix = FileType.getSuffixByFilename(originFileName);
			originFileName = originFileName.substring(0,originFileName.length() - suffix.length());
			savePath = savePath + suffix;
			long maxSize = ((Long) conf.get("maxSize")).longValue();
			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}
			savePath = PathFormat.parse(savePath, originFileName);
			String physicalPath = (String) conf.get("rootPath") + savePath;
			InputStream is = fileStream.openStream();
			//State storageState = StorageManager.saveFileByInputStream(is,physicalPath, maxSize);
			   /** 
			    * add by liuxh 将百度编辑器上传文件存储到阿里云OSS
			    */  
			   OSSClient ossClient=null;
			   String fullName=fileStream.getName();
			   String fileRealName=fileStream.getName().substring(fileStream.getName().indexOf("."));
			   SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
			   String ymd=sdf.format(new Date());
			   //String fileName = "UEDITOR_FILES/"+ymd+"/"+new StringBuffer().append(new java.util.Date().getTime()).append(fileRealName).toString(); 
			   String fileName = "UEDITOR_FILES/"+ymd+"/"+UUID.randomUUID().toString().toUpperCase().replace("-", "")+fileRealName;	//�ļ���������UUID��
			   State storageState = null;  
			   //storageState = StorageManager.saveFileByInputStream(is,physicalPath, maxSize);
			   try {  
				    config = config==null?new OSSConfig():config;
				    ossClient = new OSSClient(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret()); 
				    //========2017-04-12 chensh 添加图片压缩========begin
					String fileType = fileRealName.substring(1);
					//图片大于150k，则进行尺寸大小不变的压缩
					if(fileType.toLowerCase().equals("tiff") ||fileType.toLowerCase().equals("jpeg") ||fileType.toLowerCase().equals("jpg") || fileType.toLowerCase().equals("png") || fileType.toLowerCase().equals("bmp") || fileType.toLowerCase().equals("gif") ){
						int filesize = is.available()/10; //文件大小
						BufferedImage bi;
						bi = Thumbnails.of(is).scale(1).outputQuality(0.8).asBufferedImage();
						ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
					    ImageOutputStream imageOutput = ImageIO.createImageOutputStream(byteArrayOutputStream);
					    ImageIO.write(bi, fileType, imageOutput);
					    is = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
					    //System.out.println("==========is="+is.available());
					    filesize = is.available()/1024;
					    if(filesize>150){
					    	//图片大于150k，则进行尺寸大小的压缩，等比缩放
						    double sc = (double)((float)150 /(float)filesize );
							//System.out.println("==========sc="+sc);
							bi = Thumbnails.of(is).scale(sc).asBufferedImage();
							byteArrayOutputStream = new ByteArrayOutputStream();
						    imageOutput = ImageIO.createImageOutputStream(byteArrayOutputStream);
						    ImageIO.write(bi, fileType, imageOutput);
						    is = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
						    //System.out.println("=========11=is="+is.available());
					    }
						
					}else{
						storageState = StorageManager.saveFileByInputStream(fileStream.openStream(),physicalPath, maxSize);
					}
					//========2017-04-12 chensh 添加图片压缩========end
				    PutObjectRequest PRequest = new PutObjectRequest(config.getBucketName(), fileName,is);	
				    ossClient.putObject(PRequest);  
				    String url="";
					if(config.getNginxHTTPServer().equals("off")||config.getNginxHTTPServer().equals("OFF")){
						url = config.getEndpoint().replaceFirst("http://","http://"+config.getBucketName()+".")+"/"+fileName;
					}else{
						url= config.getNginxHTTPUrl()+"/"+fileName;
					}
					System.out.println("=========开始保存图片=url="+url);
					if(fileType.toLowerCase().equals("tiff") ||fileType.toLowerCase().equals("jpeg") ||fileType.toLowerCase().equals("jpg") || fileType.toLowerCase().equals("png") || fileType.toLowerCase().equals("bmp") || fileType.toLowerCase().equals("gif") ){
						storageState = StorageManager.saveFileByInputStream(fileStream.openStream(),physicalPath, maxSize);
					}else{
						storageState.putInfo("state", "SUCCESS");
						storageState.putInfo("type", suffix);
					}
					storageState.putInfo("state", "SUCCESS");
				    storageState.putInfo("url",url);  
				    //storageState.putInfo("type", suffix);
				    storageState.putInfo("title", fullName);  
				    storageState.putInfo("original", fullName);  
			   } catch (Exception e) {  
				    storageState.putInfo("state", "上传失败!");  
				    storageState.putInfo("url","");  
				    storageState.putInfo("title", "");  
				    storageState.putInfo("original", "");  
			   } finally{
				   if(ossClient!=null){
					   ossClient.shutdown();  
				   }
			   }
			is.close();

			if (storageState.isSuccess()) {
				//storageState.putInfo("url", PathFormat.format(savePath));
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}
			return storageState;
		} catch (FileUploadException e) {
			e.printStackTrace();
			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}
}
