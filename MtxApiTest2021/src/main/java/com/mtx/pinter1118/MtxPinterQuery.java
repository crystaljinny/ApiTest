package com.mtx.pinter1118;

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

public class MtxPinterQuery {
    public static String query(CloseableHttpClient httpClient,String name) throws URISyntaxException, ClientProtocolException, IOException {
    	//1、发起请求的客户端对象
//    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	//2、发起get请求需要创建HttpGet
    	HttpGet httpGet = new HttpGet();
	    httpGet.setURI(new URI("http://192.168.1.105:8080/pinter/bank/api/query?userName="+name));
//	    httpGet.setHeader("Cookie", "PHPSESSID=1c6a6150de0bd213de816af062589593; testfan-id=a2f212ab-663c-4704-81e9-afb4192a268c");
	    //3、执行get请求，获取应答
	    CloseableHttpResponse httpResponse =  httpClient.execute(httpGet);
	    //4、响应内容转字符串,获取应答body
	    String res = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
	    System.out.println(res);
	    return res;
    }
}
