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

public class MtxShopOrderCancelTest {
CloseableHttpClient httpClient;
	
	@BeforeClass
	public void init() throws ClientProtocolException, URISyntaxException, IOException {
		httpClient = HttpClients.createDefault();
		
		String name = "jinny";
		String pwd = "123456";
		String expect = "登录成功";
		String res = MtxShopLogin.login(httpClient,name,pwd);
		Assert.assertTrue(res.contains(expect));
	}
	
	@Test(description  = "shop-order-cancel-正例")
	public void test01_orderCancel() throws ClientProtocolException, URISyntaxException, IOException {
		int id = 80;
		String expectCancel = "取消成功";
		String resCancel = MtxShopOrderCancel.cancel(httpClient,id);
		Assert.assertTrue(resCancel.contains(expectCancel));
	}
	
	@DataProvider
	public Object[][] cancelData(){
		Object[][] objects = {
				{80,"取消成功"},
				{100,""},
				
		};
		return objects;
	}
	
	@Test(description = "shop-order-cancel-数据驱动",dataProvider = "cancelData")
	public void test02_orderCancel(int id, String expect ) throws ClientProtocolException, URISyntaxException, IOException {
		String res =  MtxShopOrderCancel.cancel(httpClient, id);
		Assert.assertTrue(res.contains(expect));
	}
}
