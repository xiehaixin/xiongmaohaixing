package com.xiongmaohaixin.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 
 * @FileName: HttpClientUtil.java
 * @Package com.evian.mobile.util
 * @Description: HTTP请求
 * @author EVIAN(PA)
 * @date 2016年3月2日 上午9:39:03
 * @version V3.0
 */
public class HttpClientUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	private static final String CHARSET = "utf-8";


	/**
	 * HTTP get请求服务到指定的URL
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		String msg = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000).setSocketTimeout(5000).build();
			// 创建get请求.
			HttpGet httpget = new HttpGet(url);
			httpget.setConfig(requestConfig);
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					// 获取响应实体
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						// 打印响应内容
						msg = EntityUtils.toString(entity, CHARSET);
					}
				}else{
					logger.error("{},[url:{}]", new Object[] {"get访问接口错误，返回状态："+response.getStatusLine().getStatusCode(), url});
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			logger.error("[url:{}], [exception:{}]", new Object[] { url, e });
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (Exception e) {
				
			}
		}
		return msg;
	}

	static HostnameVerifier hv = new HostnameVerifier() {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			// TODO Auto-generated method stub
			return true;
		}
	};

	private static void trustAllHttpsCertificates() {
		try {
			javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
			javax.net.ssl.TrustManager tm = new MiTM();
			trustAllCerts[0] = tm;
			javax.net.ssl.SSLContext sc;
			sc = javax.net.ssl.SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, null);
			javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
					.getSocketFactory());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static class MiTM implements javax.net.ssl.TrustManager,
			javax.net.ssl.X509TrustManager {
		@Override
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}

		@Override
		public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
		}

		@Override
		public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
		}
	}

	/**
	 * 发送https请求
	 * @param requestUrl 请求地址
	 * @param outputStr 提交的数据
	 * @return 返回微信服务器响应的信息
	 */
	public static String post(String requestUrl, String outputStr) {
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			//TrustManager[] tm = { new MyX509TrustManager() };
			//SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			//sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			//SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
//			trustAllHttpsCertificates();
//			HttpsURLConnection.setDefaultHostnameVerifier(hv);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			//conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod("POST");
			conn.setRequestProperty("content-type", "application/x-www-form-urlencoded"); 
			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			return buffer.toString();
		} catch (ConnectException ce) {
			logger.info("连接URL: "+requestUrl+" 超时：{}", ce);
		} catch (Exception e) {
			logger.info("连接URL: "+requestUrl+" 异常：{}", e);
		}
		return null;
	}
	

	
	private static String basicNameValuePairToString(List<BasicNameValuePair> list){
		if(list == null || list.size() == 0){return "无参数";}
		String result = "";
		for(BasicNameValuePair cur: list){
			result += cur.getName() + "=" + cur.getValue() + "&";
		}
		return result;
	}

}
