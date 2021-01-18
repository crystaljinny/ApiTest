package com.mtx.pinter1118;

import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class MtxPinterLoginTest {
CloseableHttpClient httpClient;
	
	@BeforeClass
	public void init() {
		httpClient = HttpClients.createDefault();
	}
	
	@Test(description = "pinter-bank-login-正例")
    public void test01_login() throws URISyntaxException, ClientProtocolException, IOException {
		String name = "admin";
		String pwd = "123456";
		String expect = "\"code\":\"0\",\"message\":\"success\",\"";
		String res = MtxPinterLogin.login(httpClient,name,pwd);
		Assert.assertTrue(res.contains(expect));
	}
	
	@Test(description = "pinter-bank-login-反例-用户名错")
    public void test02_login() throws URISyntaxException, ClientProtocolException, IOException {
		String name = "admin1";
		String pwd = "123456";
		String expect = "\"code\":\"0\",\"message\":\"success\",\"";
		String res = MtxPinterLogin.login(httpClient,name,pwd);
		Assert.assertTrue(res.contains(expect));
	}
	
	@Test(description = "pinter-bank-login-反例-密码错")
    public void test03_login() throws URISyntaxException, ClientProtocolException, IOException {
		String name = "admin";
		String pwd = "123456456456";
		String expect = "\"code\":\"0\",\"message\":\"success\",\"";
		String res = MtxPinterLogin.login(httpClient,name,pwd);
		Assert.assertTrue(res.contains(expect));
	}
}
