package com.fh.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

import com.fh.util.oss.OSSUploadUtil;
import com.swetake.util.Qrcode;


/**
 * 二维码
 * 创建人：FH 创建时间：2015年4月10日
 * @version
 */
public class TwoDimensionCode {

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 */
	public static void encoderQRCode(String content, String imgPath) {
		encoderQRCode(content, imgPath, "png", 2);
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param output
	 *            输出流
	 */
	public static void encoderQRCode(String content, OutputStream output) {
		encoderQRCode(content, output, "png", 2);
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 * @param imgType
	 *            图片类型
	 */
	public static void encoderQRCode(String content, String imgPath, String imgType) {
		encoderQRCode(content, imgPath, imgType, 2);
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param output
	 *            输出流
	 * @param imgType
	 *            图片类型
	 */
	public static void encoderQRCode(String content, OutputStream output,
			String imgType) {
		encoderQRCode(content, output, imgType, 2);
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 */
	public static void encoderQRCode(String content, String imgPath, String imgType,
			int size) {
		try {
			BufferedImage bufImg = qRCodeCommon(content, imgType, size);

			File imgFile = new File(imgPath);
			// 生成二维码QRCode图片
			ImageIO.write(bufImg, imgType, imgFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param output
	 *            输出流
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 */
	public static void encoderQRCode(String content, OutputStream output,
			String imgType, int size) {
		try {
			BufferedImage bufImg = qRCodeCommon(content, imgType, size);
			// 生成二维码QRCode图片
			ImageIO.write(bufImg, imgType, output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成二维码(QRCode)图片的公共方法
	 * 
	 * @param content
	 *            存储内容
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 * @return
	 */
	private static BufferedImage qRCodeCommon(String content, String imgType, int size) {
		BufferedImage bufImg = null;
		
		try {
			Qrcode qrcodeHandler = new Qrcode();
			// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			// 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
			qrcodeHandler.setQrcodeVersion(size);
			// 获得内容的字节数组，设置编码格式
			byte[] contentBytes = content.getBytes("utf-8");
			// 图片尺寸
			int imgSize = 67 + 12 * (size - 1);
			
			//System.out.println(imgSize);
			
			bufImg = new BufferedImage(imgSize, imgSize,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();
			// 设置背景颜色
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, imgSize, imgSize);

			// 设定图像颜色> BLACK
			gs.setColor(Color.BLACK);
			// 设置偏移量，不设置可能导致解析出错
			int pixoff = 2;
			// 输出内容> 二维码
			if (contentBytes.length > 0 && contentBytes.length < 800) {
				
				
				
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				throw new Exception("QRCode content bytes length = "
						+ contentBytes.length + " not in [0, 800].");
			}
			gs.dispose();
			bufImg.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufImg;
	}
	
	
	
	/**
	 * 生成微信二维码，返回的是oss储存的全路径
	 * @param appId 微信号appid
	 * @param businessType  业务点
	 * @param businessId  业务点id
	 * @param qrcodeType  二维码类型，参加活动|领取奖品
	 * @return
	 * @throws Exception
	 */
	public static String generateWechatQrcode(String appId,String businessType,String businessId,String qrcodeType) throws Exception{
		String strReturn = "";
		String state = appId+"=chumi="+businessType+"=chumi="+businessId+"=chumi="+qrcodeType;
		
		String redirect_uri = URLEncoder.encode(Constants.WechatQrcodeUrl,"UTF-8");
		String content = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_base&state="+state;
		System.out.println("====content=="+content);
		//生成二维码
		BufferedImage bi = qRCodeCommon(content, "jpg", 20);
		
//		File imgFile = new File("e:/ss.jpg");
//		// 生成二维码QRCode图片
//		ImageIO.write(bi, "jpg", imgFile);
//		
		//转换成InputStream
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
		ImageIO.write(bi, "jpg", imOut);
		InputStream is = new ByteArrayInputStream(bs.toByteArray());
		
		//上传oss，接收返回的path
		strReturn = OSSUploadUtil.uploadFile(is, "jpg");
		
		return strReturn;
	}

	/**
	 * 解析二维码（QRCode）
	 * 
	 * @param imgPath
	 *            图片路径
	 * @return
	 */
	public static String decoderQRCode(String imgPath) throws Exception{
		// QRCode 二维码图片的文件
		File imageFile = new File(imgPath);
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(imageFile);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new TwoDimensionCodeImage(
					bufImg)), "utf-8");
		} catch (IOException e) {
			//System.out.println("Error: " + e.getMessage());
			//e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			//System.out.println("Error: " + dfe.getMessage());
			//dfe.printStackTrace();
		}
		return content;
	}

	/**
	 * 解析二维码（QRCode）
	 * 
	 * @param input
	 *            输入流
	 * @return
	 */
	public static String decoderQRCode(InputStream input) {
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(input);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new TwoDimensionCodeImage(
					bufImg)), "utf-8");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
			dfe.printStackTrace();
		}
		return content;
	}

	public static void main(String[] args) {
		String imgPath = "e:/a.png";
		/*String encoderContent = "Hello 大大、小小,welcome to QRCode!"
				+ "\nMyblog [ http://sjsky.iteye.com ]"
				+ "\nEMail [ sjsky007@gmail.com ]";*/
		
		String encoderContent = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb0279f297ddd780c&redirect_uri=http%3A%2F%2Fwww.hongyunok.com%2Fsuzukiwx%2Fweixin%2Fwechatqrcode&response_type=code&scope=snsapi_base&state=wxb0279f297ddd780c=chumi=10100002=chumi=196816056101000013=chumi=10701002";
		TwoDimensionCode handler = new TwoDimensionCode();
//		handler.encoderQRCode(encoderContent, imgPath, "png",20);
		
		
		 try {
			 String turl = handler.generateWechatQrcode("wxa732e9e0417d779e", "10100002", "196816056101000013", "10701002");
			 System.out.println("===turl=="+turl);
		 } catch (Exception e) {
		 e.printStackTrace();
		 }
		System.out.println("========encoder success");

		//String decoderContent = handler.decoderQRCode(imgPath);
		System.out.println("解析结果如下：");
		//System.out.println(decoderContent);
		System.out.println("========decoder success!!!");
	}
}
