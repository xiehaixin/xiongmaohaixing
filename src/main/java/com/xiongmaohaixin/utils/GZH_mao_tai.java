package com.xiongmaohaixin.utils;

import com.xiongmaohaixin.bean.MaoTaiProductsBean;
import com.xiongmaohaixin.bean.MaoTaiShopsBean;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:GZH_mao_tai
 * Package:com.evian.sqct
 * Description:请为该功能做描述
 *
 * @Date:2020/11/4 11:24
 * @Author:XHX
 */
public class GZH_mao_tai {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public MaoTaiShopsBean monitoringMaoTaiShops(String lamboKey, String token) throws IOException {
        Map<String,String> param = new HashMap<>();
        param.put("custId","******");
        String result = goPostHttps("/api/rsv-server/anon/consumer/getShops", param, lamboKey,token);
        return JacksonUtils.json2pojo(result,MaoTaiShopsBean.class);
    }

    public MaoTaiProductsBean helpBoxShopId(String lamboKey, String token, String shopId) throws IOException {
        Map<String,String> param = new HashMap<>();
        param.put("shopId",shopId);
        String result = goGetHttps("/api/rsv-server/anon/manage/rimItem/helpBox", param, lamboKey,token);
        return JacksonUtils.json2pojo(result, MaoTaiProductsBean.class);
    }

    /** 获取验证码 */
    public String registerGetSlideCode(String lamboKey, String token){
        Map<String,String> param = new HashMap<>();
        String result = goPostHttps("/api/rsv-server/anon/register/getSlideCode", param, lamboKey,token);
        return result;
    }

    public String myTest(String lamboKey, String token) throws IOException {
        Map<String,String> param = new HashMap<>();
        param.put("custId","******");
        String result = goPostHttps("https://xiehaixin.cn","/weixin/event/receptionCode", param, lamboKey,token);
        return result;
    }

    public String goPostHttps(String api, Map<String,String> param, String lamboKey, String token){
        return goPostHttps("https://reserve.moutai.com.cn",api,param,lamboKey,token);
    }

    private String goPostHttps(String urlLocation,String api, Map<String,String> param, String lamboKey, String token){
        String url = urlLocation+api;
        logger.info(url);
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);

            httpPost.setHeader("Cookie","lambo-sso-key_0_="+lamboKey);
            httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36 QBCore/4.0.1301.400 QQBrowser/9.0.2524.400 Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2875.116 Safari/537.36 NetType/WIFI MicroMessenger/7.0.5 WindowsWechat");
            httpPost.setHeader("token","");
            httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            httpPost.setHeader("Referer","https://reserve.moutai.com.cn/mconsumer/?a=1&token="+token);
            httpPost.setHeader("x-forwarded-for","113.116.21.149");
            httpPost.setHeader("x-real-ip","113.116.21.149");

            Header[] allHeaders = httpPost.getAllHeaders();
            StringBuilder log = new StringBuilder("\n");
            for (Header hd :allHeaders){
                log.append(hd.getName()).append("=").append(hd.getValue()).append("\n");
            }
            logger.info(log.toString());
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,"utf-8");
                httpPost.setEntity(entity);

                // 打印请求参数信息
                /*HttpEntity entity2 = httpPost.getEntity();
                String string = EntityUtils.toString(entity2,"utf-8");
                logger.info("--------------entity2 = "+entity2);*/
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            return resultString;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(response!=null){
                    response.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                logger.error("关闭response失败");
            }
        }
        return resultString;
    }

    private String goGetHttps(String api, Map<String,String> param, String lamboKey, String token){
        String url = "https://reserve.moutai.com.cn"+api;
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        String resultString = "";
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建Http Post请求
            HttpGet httpGet = new HttpGet(uri);

            httpGet.setHeader("Cookie","lambo-sso-key_0_="+lamboKey);
            httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36 QBCore/4.0.1301.400 QQBrowser/9.0.2524.400 Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2875.116 Safari/537.36 NetType/WIFI MicroMessenger/7.0.5 WindowsWechat");
            httpGet.setHeader("token","");
            httpGet.setHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            httpGet.setHeader("Referer","https://reserve.moutai.com.cn/mconsumer/?a=1&token="+token);

            // 执行http请求
            response = httpClient.execute(httpGet);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            return resultString;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(response!=null){
                    response.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                logger.error("关闭response失败");
            }
        }
        return resultString;
    }
}
