package com.mtx.shop1118;

import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class MtxShopLoginTest {
CloseableHttpClient httpClient;
	
	@BeforeClass
	public void init() {
		httpClient = HttpClients.createDefault();
	}
	
	@Test(description = "shop-login-正例")
    public void test01_login() throws URISyntaxException, ClientProtocolException, IOException {
		String name = "jinny";
		String pwd = "123456";
		String expect = "登录成功";
		String res = MtxShopLogin.login(httpClient,name,pwd);
		Assert.assertTrue(res.contains(expect));
	}
	
	@Test(description = "shop-login-反例-用户名错")
    public void test02_login() throws URISyntaxException, ClientProtocolException, IOException {
		String name = "admin1";
		String pwd = "123456";
		String expect = "帐号不存在";
		String res = MtxShopLogin.login(httpClient,name,pwd);
		Assert.assertTrue(res.contains(expect));
	}
	
	@Test(description = "shop-login-反例-密码错")
    public void test03_login() throws URISyntaxException, ClientProtocolException, IOException {
		String name = "jinny";
		String pwd = "123456456456";
		String expect = "密码错误";
		String res = MtxShopLogin.login(httpClient,name,pwd);
		Assert.assertTrue(res.contains(expect));
	}
	
	@DataProvider
	public Object[][] loginData(){
		Object[][] objects = {
				{"jinny","123456","登录成功"},
				{"admin1","123456","帐号不存在"},
				{"jinny","1234567890","密码错误"},
				{"","1234567890","登录账号有误"},
				{"jinny","","密码格式 6~18 个字符之间"},
				
		};
		return objects;
	}
	
	@Test(description = "shop-login-数据驱动",dataProvider = "loginData")
	public void test04_login(String name, String pwd, String expect ) throws ClientProtocolException, URISyntaxException, IOException {
		String res =  MtxShopLogin.login(httpClient, name, pwd);
		Assert.assertTrue(res.contains(expect));
	}
}
