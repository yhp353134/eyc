package com.fh.util.oss;

import java.io.IOException;

/**
 * @ClassName: OSSConfig
 * @Description: OSS������
 * @author AggerChen
 * @date 2016��11��4�� ����3:58:36
 */
public class OSSConfig{
    private  String endpoint;  		//���������ַ
    private  String accessKeyId;  	//����keyId
    private  String accessKeySecret;    //������Կ
    private  String bucketName;  	//��Ҫ�洢��bucketName
    private  String picLocation;  	//ͼƬ����·��
    private  String nginxHTTPServer; //nginxHttpServer开启状态  off 关闭  on开启,如果开启OSS上传后返回的URL为nginx反向代理的http地址，关闭返回阿里云OSS真实地址
    private  String nginxHTTPUrl;   //通过nginx反向代理内网访问OSS
   
	
    public OSSConfig() {
    	try {
			this.endpoint = SystemConfig.getConfigResource("endpoint");
			this.bucketName = SystemConfig.getConfigResource("bucketName");
			this.picLocation = SystemConfig.getConfigResource("picLocation");
			this.accessKeyId = SystemConfig.getConfigResource("accessKeyId");
			this.accessKeySecret = SystemConfig.getConfigResource("accessKeySecret");
			this.nginxHTTPUrl = SystemConfig.getConfigResource("nginxHTTPUrl");
			this.nginxHTTPServer = SystemConfig.getConfigResource("nginxHTTPServer");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getNginxHTTPServer() {
		return nginxHTTPServer;
	}

	public void setNginxHTTPServer(String nginxHTTPServer) {
		this.nginxHTTPServer = nginxHTTPServer;
	}

	public String getNginxHTTPUrl() {
		return nginxHTTPUrl;
	}

	public void setNginxHTTPUrl(String nginxHTTPUrl) {
		this.nginxHTTPUrl = nginxHTTPUrl;
	}

	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getAccessKeyId() {
		return accessKeyId;
	}
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	public String getAccessKeySecret() {
		return accessKeySecret;
	}
	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}
	public String getBucketName() {
		return bucketName;
	}
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	public String getPicLocation() {
		return picLocation;
	}
	public void setPicLocation(String picLocation) {
		this.picLocation = picLocation;
	}
}