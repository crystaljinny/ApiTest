package com.mtx.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;



public class MtxHttpClient {
	private static Logger logger = Logger.getLogger(MtxHttpClient.class);//当前类class
    public static CloseableHttpClient httpClient;
    public static HttpGet httpGet;
    public static HttpPost httpPost;
    public static CloseableHttpResponse httpResponse;
    /**
     * 这是静态代码块，针对httpClient对象进行初始化赋值操作
     */
    static {
    	httpClient = HttpClients.createDefault();
    }
    
    public static String sendGet(String uri, String params, Map<String, String> headers) throws URISyntaxException, ClientProtocolException, IOException {
    	logger.info("url:"+uri);
    	logger.info("params:"+params);

    	httpGet =  new HttpGet();
    	if (!params.isEmpty()) {
    		httpGet.setURI(new URI(uri+params));
		}else {
			httpGet.setURI(new URI(uri));
		}
    	
    	if(!headers.isEmpty()) {
    		Set<Map.Entry<String, String>> headerSet = headers.entrySet();
            for (Map.Entry<String, String> header : headerSet) {
            	logger.info("header:"+header.getKey()+"="+header.getValue());
				httpGet.setHeader(header.getKey(), header.getValue());
			}
    	}
	    CloseableHttpResponse httpResponse =  httpClient.execute(httpGet);
	    String res = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
//	    System.out.println(res);
	    logger.info("response:"+res);
	    return res;
    }
    
    public static String sendGet(String uri, String params) throws ClientProtocolException, URISyntaxException, IOException {
    	Map<String,String> headers = new HashMap<String, String>();
    	String res = sendGet(uri, params, headers);
    	return res;
    }
    
    public static String sendGet(String uri) throws ClientProtocolException, URISyntaxException, IOException {
    	Map<String,String> headers = new HashMap<String, String>();
    	String params = "";
    	String res = sendGet(uri, params, headers);
    	return res;
    }
    
    public static String sendPost(String uri, Map<String, String> params, Map<String, String> headers) throws URISyntaxException, ClientProtocolException, IOException {
    	logger.info("url:"+uri);

    	httpPost = new HttpPost();
    	httpPost.setURI(new URI(uri));
    	//装载请求body
    	ArrayList<NameValuePair> listParams = new ArrayList<NameValuePair>();
    	if(!params.isEmpty()) {
    		Set<Map.Entry<String, String>> entrySet = params.entrySet();
    		for (Map.Entry<String, String> param : entrySet) {
    			logger.info("param:"+param.getKey()+"="+param.getValue());
    			NameValuePair pair = new BasicNameValuePair(param.getKey(),param.getValue());
    			listParams.add(pair);
			}
    	}
    	//添加Header
    	if(!headers.isEmpty()) {
    		Set<Map.Entry<String, String>> headerSet = headers.entrySet();
            for (Map.Entry<String, String> header : headerSet) {
            	logger.info("header:"+header.getKey()+"="+header.getValue());
				httpPost.setHeader(header.getKey(), header.getValue());
			}
    	}
        HttpEntity entity = new UrlEncodedFormEntity(listParams);
        httpPost.setEntity(entity);
       //发起post请求，获取应答
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
       //响应内容转字符串,获取应答body
	    String res = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
//	    System.out.println(res);
	    logger.info("response:"+res);
    	return res;
    }
    
    public static String sendPost(String uri, Map<String, String> params) throws URISyntaxException, ClientProtocolException, IOException {
    	Map<String,String> headers = new HashMap<String, String>();
    	String res = sendPost(uri, params, headers);
    	return res;
    }
    /**
     * Json格式post请求
     * @param uri
     * @param params
     * @param headers
     * @return
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String sendPostJson(String uri, String params,  Map<String, String> headers) throws URISyntaxException, ClientProtocolException, IOException {
    	logger.info("url:"+uri);
    	logger.info("params:"+params);
    	httpPost = new HttpPost();
    	httpPost.setURI(new URI(uri));
    	//添加Header
    	if(!headers.isEmpty()) {
    		Set<Map.Entry<String, String>> headerSet = headers.entrySet();
            for (Map.Entry<String, String> header : headerSet) {
            	logger.info("header:"+header.getKey()+"="+header.getValue());
				httpPost.setHeader(header.getKey(), header.getValue());
			}
    	} 	
    	HttpEntity entity = new StringEntity(params, "utf-8");
    	httpPost.setEntity(entity);
    	//完成请求数据处理
    	//3、发起post请求，获取应答
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        //4、响应内容转字符串,获取应答body
	    String res = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
	    System.out.println(res);
	    return res;
    }
    
    public static String sendPostJson(String uri, JSONObject params, Map<String, String> headers) throws ClientProtocolException, URISyntaxException, IOException {
    	String paramsStr = params.toString();
    	return sendPostJson(uri, paramsStr, headers);
    }
    
    public static String sendPostJson(String uri, String params) throws ClientProtocolException, URISyntaxException, IOException {
    	Map<String, String> header = new HashMap<String, String>();
    	return sendPostJson(uri, params,header);
    			
    }
    
    public static void main(String[] args) throws ClientProtocolException, URISyntaxException, IOException {
		String uri = "http://192.168.43.203:8080/pinter/com/getSku";
		String params = "?id=1&location=beijing";
		sendGet(uri, params);
    	
//    	String uri = "http://192.168.43.203/mtx/index.php?s=/index/user/login.html";
//    	Map<String, String> params = new HashMap<String, String>();
//    	params.put("accounts", "jinny");
//    	params.put("pwd", "123456");
//    	Map<String, String> headers = new HashMap<String, String>();
//    	headers.put("X-Requested-With", "XMLHttpRequest");
//    	sendPost(uri, params, headers);
    	
//    	String uri = "http://192.168.43.203:8080/pinter/bank/api/login";
//    	Map<String, String> params = new HashMap<String, String>();
//    	params.put("userName", "admin");
//    	params.put("password", "123456");
//    	sendPost(uri, params);
		
//    	String uri = "http://192.168.43.203:8090/login";
//    	Map<String, String> params = new HashMap<String, String>();
//    	params.put("username", "admin");
//    	params.put("password", "123456");
//    	Map<String,String> headers = new HashMap<String, String>();
//    	sendPost(uri, params,headers);
	}
    
}
