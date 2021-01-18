package com.mtx.crm1121;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

public class MtxCrmAddCustomer {
	public static String addCumstomer(CloseableHttpClient httpClient, String adminToken) throws URISyntaxException, ClientProtocolException, IOException {
    	//2、发起post请求需要创建HttpPost
    	HttpPost httpPost = new HttpPost();
    	httpPost.setURI(new URI("http://192.168.43.203:8090/CrmCustomer/addOrUpdate"));
    	httpPost.setHeader("Admin-Token",adminToken);
    	httpPost.setHeader("Content-Type", "application/json");
    	String requestJson ="{\"entity\":{\"customer_name\":\"王同学\",\"mobile\":\"18991112345\",\"telephone\":\"01028375678\",\"website\":\"http://testfan.cn\",\"next_time\":\"2020-04-02 00:00:00\",\"remark\":\"这是备注\",\"address\":\"北京市,北京城区,昌平区\",\"detailAddress\":\"霍营地铁\",\"location\":\"\",\"lng\":\"\",\"lat\":\"\"}}";
    	HttpEntity entity = new StringEntity(requestJson, "utf-8");
    	httpPost.setEntity(entity);
    	//完成请求数据处理
    	//3、发起post请求，获取应答
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        //4、响应内容转字符串,获取应答body
	    String res = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
	    System.out.println(res);
        return res;
    }
}
