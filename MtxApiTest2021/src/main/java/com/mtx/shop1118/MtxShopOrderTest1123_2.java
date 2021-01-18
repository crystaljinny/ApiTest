package com.mtx.shop1118;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mtx.http.MtxHttpClient;
import com.mtx.util.PropertyUtil;

/**
 * 
 * 从properties配置文件读取请求的参数+使用sendPost发送请求
 * 
 *
 */
public class MtxShopOrderTest1123_2 {
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
		params = PropertyUtil.getAllKeyValues("src/main/resources/shopparams/order.properties");
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Requested-With", "XMLHttpRequest");
		String res = MtxHttpClient.sendPost(uri, params, headers);
		Assert.assertTrue(res.contains("提交成功"));
	}
	
	@Test(dependsOnMethods = "test01_login")
	public void test03_addOrder() throws ClientProtocolException, URISyntaxException, IOException {
		String uri = "http://192.168.43.203/mtx/index.php?s=/index/buy/add.html";
		
		Map<String, String> params = new HashMap<String, String>();
		params = PropertyUtil.getAllKeyValues("src/main/resources/shopparams/order.properties");
		params.put("stock", "100");//修改购买数量
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Requested-With", "XMLHttpRequest");
		String res = MtxHttpClient.sendPost(uri, params, headers);
		Assert.assertTrue(res.contains("购买数量超过商品库存数量"));
	}
	
	@Test(dependsOnMethods = "test01_login")
	public void test04_addOrder() throws ClientProtocolException, URISyntaxException, IOException {
		String uri = "http://192.168.43.203/mtx/index.php?s=/index/buy/add.html";
		
		Map<String, String> params = new HashMap<String, String>();
		params = PropertyUtil.getAllKeyValues("src/main/resources/shopparams/order.properties");
		params.remove("user_note");//去掉非必填项
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Requested-With", "XMLHttpRequest");
		String res = MtxHttpClient.sendPost(uri, params, headers);
		Assert.assertTrue(res.contains("提交成功"));
	}
	
	@Test(dependsOnMethods = "test01_login")
	public void test05_addOrder() throws ClientProtocolException, URISyntaxException, IOException {
		String uri = "http://192.168.43.203/mtx/index.php?s=/index/buy/add.html";
		
		Map<String, String> params = new HashMap<String, String>();
		params = PropertyUtil.getAllKeyValues("src/main/resources/shopparams/order.properties");
		params.put("goods_id", "100");  //购买不存在的商品
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Requested-With", "XMLHttpRequest");
		String res = MtxHttpClient.sendPost(uri, params, headers);
		Assert.assertTrue(res.contains("资源不存在或已被删除"));
	}
	
	@Test(dependsOnMethods = "test01_login")
	public void test06_addOrder() throws ClientProtocolException, URISyntaxException, IOException {
		String uri = "http://192.168.43.203/mtx/index.php?s=/index/buy/add.html";
		
		Map<String, String> params = new HashMap<String, String>();
		params = PropertyUtil.getAllKeyValues("src/main/resources/shopparams/order.properties");
		params.remove("goods_id");  //去掉必填项
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Requested-With", "XMLHttpRequest");
		String res = MtxHttpClient.sendPost(uri, params, headers);
		Assert.assertTrue(res.contains("商品id有误"));
	}
}
