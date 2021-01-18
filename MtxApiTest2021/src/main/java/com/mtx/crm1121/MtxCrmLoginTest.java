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

import com.mtx.util.PropertyUtil;


public class MtxCrmLoginTest {
CloseableHttpClient httpClient;
String host;
	
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
	
	@Test(description = "crm-login-数据驱动",dataProvider = "loginData")
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
	}
}
