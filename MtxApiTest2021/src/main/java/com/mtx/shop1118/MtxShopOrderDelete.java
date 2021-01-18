package com.mtx.shop1118;

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
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class MtxShopOrderDelete {
	public static String delete(CloseableHttpClient httpClient, int id) throws URISyntaxException, ClientProtocolException, IOException {
    	//2、发起post请求需要创建HttpPost
    	HttpPost httpPost = new HttpPost();
    	httpPost.setURI(new URI("http://192.168.43.203/mtx/index.php?s=/index/order/delete.html"));
        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
    	//3、装载请求body
    	NameValuePair idDelete = new BasicNameValuePair("id", String.valueOf(id));
    	ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(idDelete);
        HttpEntity entity = new UrlEncodedFormEntity(params);
        httpPost.setEntity(entity);

       //4、发起post请求，获取应答
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
       //5、响应内容转字符串,获取应答body
	    String res = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
	    System.out.println(res);
	    return res;
    }
}
