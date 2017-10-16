package com.fh.util.alipay;

public class AlipayConfig {
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
//	public static String partner = "2088102171337753";
	//正式环境
//	public static String partner = "2088911966018522";
	// 商户appid
	public static String APPID = "2016081600254405";//沙箱环境
//	public static String APPID = "2017101109242850";//正式环境
	// 私钥 pkcs8格式的私钥
	//沙箱环境
	public static String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCkGiJ28cOxBinyWzFRpO96bvLclRIufjEu6TOoJK1fplvr0mf1JG1NFQxwpGSIPjKZhzioGm4Y7ogwgiXJZ0gWO7/fyfzgM2MAuDpT7rX33wc5E6c2MsolztseHOu7FqfnaKOiiDNqLkXvLDUHhrqXT0vwBzRMetPzmL1pkhg5LTcgWCHJptTp5/U2gbiat/hKdRazXaqAEyOkQ586DRYsqNci6Qv/UBX2ZZ5cvjcdjk3uVXIrLIfi9L96Y5kXYbuF1VXsz1dOi/vQ3myR+kd8RU1m8uh5ENd6hrBXWezmKmqNmbX9PeDk6JWJJH32GClVxG+E+AjLo9JbkKuQJ6AfAgMBAAECggEALWB0q0NLA4ZazwNcbeEXv1kymsA12NlYLEqb2CDcOBi+TkVOLcE24Buz5unuSqY4Q6/agNFeJFLBIfi7DigbDNrUwcMOw8gx1h8bMd5OPw5S5vuCP0BoGXBLstX4cTFgmGKc7PKQlQnamLo2p7AfpHtu2h6D3aoRNcjEh464WAaw+n0zOq2Gqmr5VGAdQwkAz+DsC5dNrbKZ3VJocqmuisNchdhJLERCzyvZs3jCMTrG0Cdvb6tWNhFjtgsg1+IvvS2gP/xw81OMOia2TEVolKjK5dcuQJdZJb9U1hUvoihimI3wVQ9oNolURwkMxPZ6adv7wBqrNw6FmYot+g1DUQKBgQDexcVfIMSF3Oy4CdIi5vrSBNYOn63YGu7bVs2Air7wKHDQOfb/tCbVYej0pbF2H+lgkoy+hpyGswoQ88Qjm6pa5wKmb+56jbkcoiC5DMF7rsPG9Yw9Sv/MTABmLGeV8W55SA9p+QBPmmjyOeBwgMjd4jCXYawNWIOc56vX0BQp5QKBgQC8lB888LE+IMN4oMHBrPuP+2eLOEiYjBICSvim2VDJiczYarMruzhordIPLlPJWpUwjyT0Envcqh3LYVEvivyTAR+9R922h77nPSo+JMx2ViyhnQXCfepBjZ4DkmicTEoveKumscYYmF7Eq9T+M5NkpfmlKxa07yj1O535v0uxswKBgQCdjIcKwPTbqyPrAF8R+gMXNv5gN0POWzXj/560sutYk3E9glESBEmWjXkgVHKOFQGLH/nT4QWgKc0Lsipb+IJhaZExPmYNXF+3RV4ZIVFQ9IdXFV+KFZmU1YdOu0bC/41ezry/+K7knVJtRMtbJWlpqscsuJHiml/yPtIEfg9UlQKBgQCyGs+aFdXA0N7tKh91VXROm2pC8giWkwxbQAWyR+NmRZDEAZpxPiTbsPeJQP7/WF85eqizIFX9pmn3cVVlKsa77L7WXW7Ie1LRQVKJWOYHCgKjLROIirXZ7fAEfZ3xqJgUV8r+6D4pcpdm9sd2wq19zMiIt7Z9crfjTcg5XfQWwwKBgCb24NvXg8fSdZb309NBqiPMpTH2OaBf5vcejQkiNvYeFNbrnVarwzquBueueVAvisuQYO9F1Nj/tX/HdsaNOnUz/CEaw6tDUFo/46qjuM3fIEa4hXyKJgwVqDCUj/tS8U/yW91J5cHa1Tl2z1wLnZKL+LySawO5TUZBNNKd7iwy";
	//正式环境
//	public static String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCIeQxlZ9O5CJOrLlgcHogoiNrgUI83psj78m6xtIj6VxE3mfKN+WmbaAe2BZUf/kS7lfREiLCOCXmTkOHvKwqJ+zd+pMUXT2ZBL3ixBGu7o7d2Owid8Xk8wP8DKvcRhanXhTM6AxyiEXSWl6MKmmvLfUt7PfFFW4YMFtcuAUfld3rpviw+u6b9K+IfGiGNpXvZ0syDbUOxlkVhb5PPZuTQLdeXFUUXr6HmycSFkLv523tqJ0AX3w9aDnYyxbeYvuQu7ri/H/lFG/F/x3/rcC9DbvY/b9udE/+eo9CZVkeSrjIC0nUxaswVQ3SEoHKQq1C8Pvm+ON2OoVHCUyx4pyzdAgMBAAECggEAIe0Vq8/XzlPXgeKPfM4KQyliYAkm0UrhA+EMq5iZxMIIQa4/ohOysTN3V0SkAcQQCiIJC1B/s4+fbZPC1tX4VLzi2tTTOiw+202Vz0uuDINyhC3AwqLY5MWFn5/6s5xq0G0Igw9q+s8kEJu5qFUjHv8S2JxZckiCVlcHPySKXvyn1aWcSDOahiCCsofAyokLSZ+og0DvaQg48ZLAtuNrRdp2vi9gcynK3qYcJRzGpPPCD3mb6b50l61d/kmzGJhYNyIBgY0ewyOgR07Cd32MxiiekeTrZGTOwqguqJAcL7ZydJGSylpmTIkyvxD5X3rdvD0lcKqa7O/Yg8kPZlv9AQKBgQD6txEdPa0X0snzrxGwDjGkWRIaKB1vpZLZO28TcAajQBIDcMnn/y32wyxKn2+ytkIbj77CgIx24QZC9TX8UsEi3J8Kwy4WLaRAI42G08TFumk6c4HRE0PsO3zgxwfxuKZZIXveCkc5vBnu6O6V4GbJrPM4ZnhPji5/484Kni7NrQKBgQCLWX8VMrZlh5vOVbjRwKo+agQ64+5tYI5sDrAzyroJ+x616DmpG50qiPtFjf6o0gyJWR/3yZFZVb2hXT6A+QFTNh5EZe++wjORwxGoCifrdPNOZJuO0rgURpIUlCnuhjTQ2buK1bnHqg/TiqqAWbcm+L/Rd65FWdg92zoZQG1h8QKBgQDxK5x45lVVL1043dVD58N09oFlS+VU+o7SDEwDBKY972nkiWCPnHZYgtJeThQicxQQcyvq6cE0sa1WR47Q08IZiOQsdCgnCWyQjiKb6aXFFlYW9fmQZtnVqjur9Yj8ysSpxH3kzzpInVrM6KBo5924bnfgbyGYVYsBVt9ic1tOWQKBgCG/iweXQJVWQmW2XQxvnMuA4YVhrlzPBa8l34arkmLYbpefpldOWMH0auZtfT9ln6Oj8hoQtyLroQFzk6+onqmIccwe4/FPFy42EBCiZQihSkuyEfqFDg/bB4X0XDNoTWJHWSRGnre8eBjDfIeZMsI9fDc+xPYGzFguv+NdtyABAoGBAJQ2npn0+/Pq5E1oOI9q2Tf+p2bF6RsI4iWWjkG9BRXU3IIKP3mMNukD9MkwvwEr6jysIvCBoZFz2yLR2diyb/ngrw2fb5r0uIar/c1wZcqz6jp0/gmfNzX6sBwylbN/OjXqXuicP0Flfc6GFZlfxGm++ED6hfMuYnAmp2wROBOA";
	// 支付宝公钥
	//沙箱环境
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyE39L0yqDafg74DjnElNb4J35mdKQHcUsvCk5P+I+CG42AHHb3dUiPKy+pv8NY2mrx0oq7zWEM+5k6rBja4saikcpng+BDjAg/KZT93WPpeQgHYHeyL2TVSd+z//VWa1gREKgLZxXdw9HkHnkMQ4un5Qr74JQ0HzxHYtWYstydiTwWczaTpQyklEljatO42ZygJzZRbyDZZ/eW/x6osMBJuY0S7g8ZJiwr4dBKxQfJvo+gYeKHb5C5UU6LEg75Z2vWaHHM4xW3FGqHdtE05oeZiyV5xL9CInJyd4CIpe13S1M2PGlIaZZ3aTDuZgR7x8yRbwFZTaFP7MmyxlO2AxpQIDAQAB";
	//正式环境
//	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAicDLBqvJ8bPiA9wgYNO15/e1Xm3xTvb9WL8jnZC2zWxhvon7Uf/hXLwu9utymWY9WNMRsM5va/PK039yWqA78VqPpFmBvbUsWivLbegR6Mxr9+L1/sHKq3xa62B1CuBS9gqhTk7bCgFt1O7Yb34w7PLHYYMh/NnzqPYzEYfragpeqwLr3DLoG8/ydMr9QgdZeeBtlMqop0Z5QR74TrEpuGGPPIPl7erKsz+3nYP095rMeoiMqRacny+r02ALnOtU0iDWipkw5YkPzLJ5o6NxZzV8ZJwnpEqmP+WCJ9L4pPOCodm6KTP8cQ48qdjX0It9zl1J3SpTpGQHWJW3QM0MNwIDAQAB";

	
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	// public static String notify_url = "http://www.baidu.com/notify_url.jsp";
	public static String notify_url = "114.55.41.71:9090/eyc/app/alipay/notify";
	public static String notify_recharge_url = "114.55.41.71:9090/eyc/app/alipay/notifyRecharge";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	// 商户可以自定义同步跳转地址
	public static String return_url = "http://商户网关地址/alipay.trade.wap.pay-JAVA-UTF-8/return_url.jsp";
	// 请求网关地址(线上)
	// public static String URL = "https://openapi.alipay.com/gateway.do";
	// 请求网关地址(调试)
	public static String URL = "http://openapi.alipaydev.com/gateway.do";
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";

	// 日志记录目录
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE = "RSA2";
	// PID（seller_id）
//	public static String seller_id = "2088911966018522";
	//沙箱环境
	public static String seller_id = "2088102171337753";
	// 账号
//	public static String seller_email = "eyunche@camsl.com";
	//沙箱账号
	public static String seller_email = "efdyms5936@sandbox.com";
	// TRADE_SUCCESS或TRADE_FINISHED时，
	public static String TRADE_SUCCESS = "TRADE_SUCCESS";// 交易成功
	public static String TRADE_FINISHED = "TRADE_SUCCESS";// 交易完成

}
