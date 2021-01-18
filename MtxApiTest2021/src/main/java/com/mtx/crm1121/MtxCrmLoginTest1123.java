package com.mtx.crm1121;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mtx.http.MtxHttpClient;
import com.mtx.util.PropertyUtil;


public class MtxCrmLoginTest1123 {
CloseableHttpClient httpClient;
String host;
String token;
	
	@BeforeClass
	public void init() {
		httpClient = HttpClients.createDefault();
		Properties properties = PropertyUtil.readProperties("src/main/resources/http.properties");
    	host = properties.getProperty("http.mtxcrm.url");
	}
	
	@DataProvider
	public Object[][] loginData(){
		Object[][] objects = {
				{"admin","123456","\"code\":0,\"Admin-Token\""},
				
		};
		return objects;
	}
	
	@Test(enabled = false, description = "crm-login-数据驱动",dataProvider = "loginData")
	public void test01_login(String name, String pwd, String expect ) throws ClientProtocolException, URISyntaxException, IOException {
		String res =  MtxCrmLogin.login(httpClient, name, pwd);
		Assert.assertTrue(res.contains(expect));
	}
	
	@Test
	public void test02_login() throws ClientProtocolException, URISyntaxException, IOException {
		String uri = host +"/login";
		Map<String, String> params = new HashMap<String, String>();
		params = PropertyUtil.getAllKeyValues("src/main/resources/crmparams/login.properties");
//    	params.put("username", "admin");
//    	params.put("password", "123456");
    	String res = MtxCrmLogin.login(uri, params);
    	Assert.assertTrue(res.contains("\"code\":0"));
    	JSONObject resJson = JSONObject.parseObject(res);
    	token =resJson.getString("Admin-Token"); //获取json第一层
    	System.out.println(token);
    	int code = resJson.getInteger("code");
    	System.out.println(code);
    	
    	JSONObject userJson = resJson.getJSONObject("user");  //获取json第二层
    	String deptName = userJson.getString("deptName");
    	System.out.println(deptName);    	
	}
	
	/*
	 * 查询客户信息，通过fastjson来获取json中的值
	 */
	@Test(dependsOnMethods = "test02_login")
	public void test03_customerList() throws ClientProtocolException, URISyntaxException, IOException {
		String uri = host + "/CrmCustomer/queryPageList";
		Map<String,String> headers = new HashMap<String, String>();
		headers.put("Admin-Token", token);
//		headers.put("Content-Type", "application/json");
		String param = "{\"page\":1,\"limit\":15,\"search\":\"\",\"type\":2}";
		String res = MtxHttpClient.sendPostJson(uri, param, headers);
		JSONObject resJson = JSONObject.parseObject(res);
		JSONObject dataJson = resJson.getJSONObject("data");  //第一层
		JSONArray listJsonArray = dataJson.getJSONArray("list");//第二层
		int count = listJsonArray.size();
		System.out.println(count);
		JSONObject jsonObject1 = listJsonArray.getJSONObject(0);  //第三层，array没有key，通过索引取值
		String customerName = jsonObject1.getString("customerName");//第四层
		int customerId = jsonObject1.getInteger("customerId");
		System.out.println(customerName);
		System.out.println(customerId);
		
	}
	
}
