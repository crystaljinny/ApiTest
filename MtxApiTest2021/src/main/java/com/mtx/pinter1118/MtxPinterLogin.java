package com.mtx.pinter1118;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.mtx.http.MtxHttpClient;

public class MtxPinterLogin {
    public static String login(CloseableHttpClient httpClient,String name, String pwd) throws URISyntaxException, ClientProtocolException, IOException {
    	//1、发起请求的客户端对象
//    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	//2、发起post请求需要创建HttpPost
    	HttpPost httpPost = new HttpPost();
    	httpPost.setURI(new URI("http://192.168.43.203:8080/pinter/bank/api/login"));
    	//3、装载请求body
    	NameValuePair userName = new BasicNameValuePair("userName", name);
    	NameValuePair password = new BasicNameValuePair("password", pwd);
    	ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(userName);
    	params.add(password);
        HttpEntity entity = new UrlEncodedFormEntity(params);
        httpPost.setEntity(entity);
       //4、发起post请求，获取应答
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
       //5、响应内容转字符串,获取应答body
	    String res = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
	    System.out.println(res);
	    return res;
    }
    
    public static String login(String uri, Map<String, String> params) throws ClientProtocolException, URISyntaxException, IOException {
    	return MtxHttpClient.sendPost(uri, params);
    }
    
    public static void main(String[] args) throws ClientProtocolException, URISyntaxException, IOException {
    	String uri = "http://192.168.43.203:8080/pinter/bank/api/login";
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("userName", "admin");
    	params.put("password", "123456");
    	login(uri, params);
	}
}
