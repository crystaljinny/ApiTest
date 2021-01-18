package com.mtx.apitest;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

//post表单
public class HttpPostStudy {
    @Test(description = "表单")
    public void pinterLoginPost() throws URISyntaxException, ClientProtocolException, IOException {
    	//1、发起请求的客户端对象
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	//2、发起post请求需要创建HttpPost
    	HttpPost httpPost = new HttpPost();
    	httpPost.setURI(new URI("http://172.20.10.2:8080/pinter/com/login"));
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
    
    @Test(description = "json")
    public void pinterRegisterPost() throws URISyntaxException, ClientProtocolException, IOException {
    	//1、发起请求的客户端对象
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	//2、发起post请求需要创建HttpPost
    	HttpPost httpPost = new HttpPost();
    	httpPost.setURI(new URI("http://172.20.10.2:8080/pinter/com/register"));
    	httpPost.setHeader("Content-Type", "application/json");
    	String requestJson ="{\"userName\":\"test\",\"password\":\"1234\",\"gender\":1,\"phoneNum\":\"110\",\"email\":\"beihe@163.com\",\"address\":\"Beijing\"}";
    	HttpEntity entity = new StringEntity(requestJson, "utf-8");
    	httpPost.setEntity(entity);
    	//完成请求数据处理
    	//3、发起post请求，获取应答
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        //4、响应内容转字符串,获取应答body
	    String res = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
	    System.out.println(res);
	    //5、断言 About Maven
	    Assert.assertTrue(res.contains("\"code\":\"0\",\"message\":\"注册成功\""));
    }
    
    @Test(description = "xml")
    public void webXmlPost() throws URISyntaxException, ClientProtocolException, IOException {
    	//1、发起请求的客户端对象
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	//2、发起post请求需要创建HttpPost
    	HttpPost httpPost = new HttpPost();
    	httpPost.setURI(new URI("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx"));
    	httpPost.setHeader("Content-Type", "text/xml");
    	String phone = "13581587675";
    	String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
    			"<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + 
    			"  <soap:Body>\r\n" + 
    			"    <getMobileCodeInfo xmlns=\"http://WebXml.com.cn/\">\r\n" + 
    			"      <mobileCode>"+phone+"</mobileCode>\r\n" + 
    			"      <userID></userID>\r\n" + 
    			"    </getMobileCodeInfo>\r\n" + 
    			"  </soap:Body>\r\n" + 
    			"</soap:Envelope>";
    	HttpEntity entity = new StringEntity(xml, "utf-8");
    	httpPost.setEntity(entity);
    	//完成请求数据处理
    	//3、发起post请求，获取应答
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        //4、响应内容转字符串,获取应答body
	    String res = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
	    System.out.println(res);
	    //5、断言 About Maven
	    Assert.assertTrue(res.contains(phone));
    }
    
    @Test(description = "xml")
    public void pinterUploadPost() throws URISyntaxException, ClientProtocolException, IOException {
    	//1、发起请求的客户端对象
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	//2、发起post请求需要创建HttpPost
    	HttpPost httpPost = new HttpPost();
    	httpPost.setURI(new URI("http://172.20.10.2:8080/pinter/file/api/upload"));
    	MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    	HttpEntity entity = builder.addBinaryBody("file", new File("C:\\Users\\wangjingjing\\Desktop\\123.log"),ContentType.APPLICATION_OCTET_STREAM,"123.log").build();
    	httpPost.setEntity(entity);
    	//完成请求数据处理
    	//3、发起post请求，获取应答
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        //4、响应内容转字符串,获取应答body
	    String res = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
	    System.out.println(res);
	    //5、断言 About Maven
	    Assert.assertTrue(res.contains("上传成功"));
    }
    
    @Test(description = "picture")
    public void pinterUpload2Post() throws URISyntaxException, ClientProtocolException, IOException {
    	//1、发起请求的客户端对象
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	//2、发起post请求需要创建HttpPost
    	HttpPost httpPost = new HttpPost();
    	httpPost.setURI(new URI("http://172.20.10.2:8080/pinter/file/api/upload2"));
    	MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    	HttpEntity entity = builder.addBinaryBody("file", new File("C:\\Users\\wangjingjing\\Desktop\\打印\\课表.jpg"),ContentType.APPLICATION_OCTET_STREAM,"课表.jpg").build();
    	httpPost.setEntity(entity);
    	//完成请求数据处理
    	//3、发起post请求，获取应答
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        //4、响应内容转字符串,获取应答body
	    String res = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
	    System.out.println(res);
	    //5、断言 About Maven
	    Assert.assertTrue(res.contains("上传成功"));
    }

    
}
