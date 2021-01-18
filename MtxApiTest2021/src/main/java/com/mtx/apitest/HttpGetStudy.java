package com.mtx.apitest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HttpGetStudy {
    @Test
    public void mavenWelcomPage() throws URISyntaxException, ClientProtocolException, IOException {
    	//1、发起请求的客户端对象
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	//2、发起get请求需要创建HttpGet
    	HttpGet httpGet = new HttpGet();
	    httpGet.setURI(new URI("http://maven.apache.org/"));
	    //3、执行get请求，获取应答
	    CloseableHttpResponse httpResponse =  httpClient.execute(httpGet);
	    //4、响应内容转字符串,获取应答body
	    String res = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
	    System.out.println(res);
	    //5、断言 About Maven
	    Assert.assertTrue(res.contains("About Maven"));
    }
}
