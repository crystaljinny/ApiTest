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

public class MtxShopOrder {
	public static String buy(CloseableHttpClient httpClient, int goodsId, int stock, String buy_type, int address_id,
			int payment_id, String spec, String user_note)
			throws URISyntaxException, ClientProtocolException, IOException {
    	//2、发起post请求需要创建HttpPost
    	HttpPost httpPost = new HttpPost();
    	httpPost.setURI(new URI("http://192.168.43.203/mtx/index.php?s=/index/buy/add.html"));
        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
    	//3、装载请求body
    	NameValuePair goodsIdBuy = new BasicNameValuePair("goods_id", String.valueOf(goodsId));
    	NameValuePair stockBuy = new BasicNameValuePair("stock", String.valueOf(stock));
    	NameValuePair buyTypeBuy = new BasicNameValuePair("buy_type", buy_type);
    	NameValuePair addressIdBuy = new BasicNameValuePair("address_id", String.valueOf(address_id));
    	NameValuePair paymentIdBuy = new BasicNameValuePair("payment_id", String.valueOf(payment_id));
    	NameValuePair specBuy = new BasicNameValuePair("spec", spec);
    	NameValuePair userNoteBuy = new BasicNameValuePair("user_note", user_note);
    	ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(goodsIdBuy);
    	params.add(stockBuy);
    	params.add(buyTypeBuy);
    	params.add(addressIdBuy);
    	params.add(paymentIdBuy);
    	params.add(specBuy);
    	params.add(userNoteBuy);
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
