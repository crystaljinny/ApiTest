package com.mtx.shop1118;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.mtx.http.MtxHttpClient;

/*
 * 使用sendPost发送请求
 */
public class MtxShopOrderTest1123_1 {
    @Test
    public void test01_login() throws ClientProtocolException, URISyntaxException, IOException {
    	String uri = "http://192.168.43.203/mtx/index.php?s=/index/user/login.html";
		Map<String, String> params = new HashMap<String, String>();
    	params.put("accounts", "jinny");
    	params.put("pwd", "123456");
    	Map<String, String> headers = new HashMap<String, String>();
    	headers.put("X-Requested-With", "XMLHttpRequest");
    	String res = MtxHttpClient.sendPost(uri, params, headers);
    	Assert.assertTrue(res.contains("登录成功"));
    }
    
    @Test(dependsOnMethods = "test01_login")
    public void test02_addOrder() throws ClientProtocolException, URISyntaxException, IOException {
    	String uri = "http://192.168.43.203/mtx/index.php?s=/index/buy/add.html";
		Map<String, String> params = new HashMap<String, String>();
    	params.put("goods_id", "1");
    	params.put("stock", "1");
    	params.put("buy_type", "goods");
    	params.put("address_id", "7");
    	params.put("payment_id", "1");
    	params.put("spec", "[]");
    	params.put("user_note", "");
    	Map<String, String> headers = new HashMap<String, String>();
    	headers.put("X-Requested-With", "XMLHttpRequest");
    	String res = MtxHttpClient.sendPost(uri, params, headers);
    	Assert.assertTrue(res.contains("提交成功"));
    }
   
}
