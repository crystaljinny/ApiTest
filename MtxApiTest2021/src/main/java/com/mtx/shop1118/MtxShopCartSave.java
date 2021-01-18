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

public class MtxShopCartSave {
	public static String save(CloseableHttpClient httpClient, int goodsId, int stock) throws URISyntaxException, ClientProtocolException, IOException {
    	//2、发起post请求需要创建HttpPost
    	HttpPost httpPost = new HttpPost();
    	httpPost.setURI(new URI("http://192.168.43.203/mtx/index.php?s=/index/cart/save.html"));
        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
    	//3、装载请求body
    	NameValuePair goodsIdSave = new BasicNameValuePair("goods_id", String.valueOf(goodsId));
    	NameValuePair stockSave = new BasicNameValuePair("stock", String.valueOf(stock));
    	ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(goodsIdSave);
    	params.add(stockSave);
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
