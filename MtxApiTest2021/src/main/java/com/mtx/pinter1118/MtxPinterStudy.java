package com.mtx.pinter1118;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

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
import org.testng.Assert;
import org.testng.annotations.Test;

public class MtxPinterStudy {
	@Test(description = "pinter-bank-login-正例")
    public void test01_login() throws URISyntaxException, ClientProtocolException, IOException {
    	//1、发起请求的客户端对象
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	//2、发起post请求需要创建HttpPost
    	HttpPost httpPost = new HttpPost();
    	httpPost.setURI(new URI("http://192.168.1.105:8080/pinter/bank/api/login"));
    	//3、装载请求body
    	NameValuePair userName = new BasicNameValuePair("userName", "admin");
    	NameValuePair password = new BasicNameValuePair("password", "1234");
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
	   //6、断言 About Maven
	    Assert.assertTrue(res.contains("\"code\":\"0\",\"message\":\"success\""));
    }
	
	@Test(description = "pinter-bank-login-反例-用户名错")
    public void test02_login() throws URISyntaxException, ClientProtocolException, IOException {
    	//1、发起请求的客户端对象
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	//2、发起post请求需要创建HttpPost
    	HttpPost httpPost = new HttpPost();
    	httpPost.setURI(new URI("http://192.168.43.203:8080/pinter/bank/api/login"));
    	//3、装载请求body
    	NameValuePair userName = new BasicNameValuePair("userName", "admin1");
    	NameValuePair password = new BasicNameValuePair("password", "1234");
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
	   //6、断言 About Maven
	    Assert.assertTrue(res.contains("\"code\":\"0\",\"message\":\"success\""));
    }
	
	@Test(description = "pinter-bank-login-反例-密码错")
    public void test03_login() throws URISyntaxException, ClientProtocolException, IOException {
    	//1、发起请求的客户端对象
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	//2、发起post请求需要创建HttpPost
    	HttpPost httpPost = new HttpPost();
    	httpPost.setURI(new URI("http://192.168.43.203:8080/pinter/bank/api/login"));
    	//3、装载请求body
    	NameValuePair userName = new BasicNameValuePair("userName", "admin1");
    	NameValuePair password = new BasicNameValuePair("password", "1234567890");
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
	   //6、断言 About Maven
	    Assert.assertTrue(res.contains("\"code\":\"0\",\"message\":\"success\""));
    }
}
